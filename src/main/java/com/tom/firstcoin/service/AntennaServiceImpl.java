package com.tom.firstcoin.service;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import com.tom.firstcoin.common.DefaultSetting;
import com.tom.firstcoin.common.bo.OreDescriptionHenzan;
import com.tom.firstcoin.common.bo.OreJsonHenzan;
import com.tom.utils.JsonParseUtils;

/**
 * @author TommyDeng <250575979@qq.com>
 * @version 创建时间：2017年3月7日 上午11:20:39
 *
 */

public class AntennaServiceImpl implements AntennaService {

	@Override
	public void executeHenzan() throws Exception {

		// 抓取规则：当前时间<0-20>
		OreDescriptionHenzan oreDescriptionHenzan = new OreDescriptionHenzan();

		// fetch
		OreJsonHenzan result = digOre(oreDescriptionHenzan);

		// restore
	}

	private OreJsonHenzan digOre(OreDescriptionHenzan oreDescriptionHenzan) throws Exception {
		Content content = Request.Get(oreDescriptionHenzan.buildURI()).execute().returnContent();
		String contentStr = content.asString(DefaultSetting.CHARSET);
		return JsonParseUtils.generateJavaBean(contentStr, OreJsonHenzan.class);
	}

}
