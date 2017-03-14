package com.tom.firstcoin.service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tom.firstcoin.common.DefaultSetting;
import com.tom.firstcoin.common.bo.OreDescriptionHenzan;
import com.tom.firstcoin.common.bo.OreJsonHenzan;
import com.tom.firstcoin.common.bo.OreJsonHenzanElement;
import com.tom.utils.JsonParseUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author TommyDeng <250575979@qq.com>
 * @version 创建时间：2017年3月7日 上午11:20:39
 *
 */

@Slf4j
@Service
public class AntennaServiceImpl implements AntennaService {

	@Autowired
	DataAccessService dataAccessService;

	// 抓取任务运行标示
	volatile boolean isRunning = false;

	@Override
	public void executeHenzan() throws Exception {

		if (isRunning) {
			return;
		}

		isRunning = true;

		try {
			OreDescriptionHenzan oreDescriptionHenzan = new OreDescriptionHenzan();
			OreJsonHenzan result = null;

			while (true) {

				// restore one by one
				result = digOre(oreDescriptionHenzan);

				boolean exitFlag = restoreBatch(result);

				if (exitFlag) {
					break;
				}

				// 拿到最后一个id作为下一个批次的offset
				String offset = result.pricelive_list.get(oreDescriptionHenzan.getLimit() - 1).id;
				oreDescriptionHenzan.setOffset(offset);
			}
		} finally {
			isRunning = false;
		}

	}

	private OreJsonHenzan digOre(OreDescriptionHenzan oreDescriptionHenzan) throws Exception {
		URI uri = oreDescriptionHenzan.buildURI();

		Content content = null;
		int maxRetry = 3;
		int i = 1;
		while (i <= maxRetry) {
			try {
				log.info(uri.toString());
				content = Request.Get(uri).execute().returnContent();
				break;
			} catch (Exception e) {
				log.error(e.getMessage());
				i++;
			}
		}
		String contentStr = content.asString(DefaultSetting.CHARSET);
		return JsonParseUtils.generateJavaBean(contentStr, OreJsonHenzan.class);
	}

	/**
	 * @param result
	 * @return
	 */
	private boolean restoreBatch(OreJsonHenzan result) {
		int insertedRow = 0;
		if (result == null || result.getPricelive_list().size() == 0) {
			log.info("Empty list. restore exit." + insertedRow + "/0");
			return true;
		}

		for (OreJsonHenzanElement element : result.getPricelive_list()) {
			Map<String, Object> paramMap = new HashMap<>();

			paramMap.put("ITEM_ID", element.id);
			paramMap.put("ORE_ID", OreDescriptionHenzan.ORE_ID);
			long count = dataAccessService.queryForOneObject("BUSS001", paramMap, Long.class);

			if (count > 0) {
				log.info("Item id conflict <" + element.id + ">@<" + OreDescriptionHenzan.ORE_ID + ">. restore exit."
						+ insertedRow + "/10");
				return true;
			}
			paramMap.put("UNIQUE_CODE", UUID.randomUUID());
			paramMap.put("PROD_ID", element.getProd_id());
			paramMap.put("PROD_URL", element.getProd_url());
			paramMap.put("PROD_NAME", element.getProd_name());
			paramMap.put("PROD_PIC", element.getProd_pic());
			paramMap.put("MERCHANT_ID", element.getShop_id());
			paramMap.put("MERCHANT_NAME", element.getMerchant_name());
			paramMap.put("BRAND_ID", element.getFamous_brand());
			paramMap.put("BRAND_NAME", element.getFamous_brand_name());
			paramMap.put("PRICE", element.getProd_price());
			paramMap.put("PRICE_TXT", element.getProd_price_txt());
			paramMap.put("PRICE_STATUS", element.getProd_price_status());
			paramMap.put("DESCRIPTION", element.getDescription());
			paramMap.put("UPDATE_TIME", element.getUpdate_time());
			paramMap.put("BATCH_NO", element.getBatch_no());

			dataAccessService.update("BUSS002", paramMap);
			insertedRow++;
		}
		return false;
	}

	@Override
	public void backupHistory() {
		//一个月之前的数据备份
		//1.insert into backup
		dataAccessService.update("BUSS004", null);
		
		//2.delete
		dataAccessService.update("BUSS005", null);
	}

}
