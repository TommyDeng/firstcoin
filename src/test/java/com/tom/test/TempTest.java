package com.tom.test;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.junit.Ignore;
import org.junit.Test;

import com.tom.firstcoin.common.DefaultSetting;
import com.tom.firstcoin.common.bo.OreDescriptionHenzan;
import com.tom.firstcoin.common.bo.OreJsonHenzan;
import com.tom.firstcoin.common.bo.OreJsonHenzanElement;
import com.tom.utils.JsonParseUtils;

import junit.framework.TestSuite;

/**
 * @author TommyDeng <250575979@qq.com>
 * @version 创建时间：2016年9月23日 下午12:03:41
 *
 */

@Ignore
public class TempTest extends TestSuite {

	@Test
	public void temp() throws Exception {
		OreDescriptionHenzan oreDescriptionHenzan = new OreDescriptionHenzan();

		Content content = Request.Get(oreDescriptionHenzan.buildURI()).execute().returnContent();
		String contentStr = content.asString(DefaultSetting.CHARSET);
		OreJsonHenzan resultJson = JsonParseUtils.generateJavaBean(contentStr, OreJsonHenzan.class);
		System.out.println(oreDescriptionHenzan.buildURI().toString());
		System.out.println(resultJson.getCurrent_time());

		String latestId = "";

		System.out.println("===================================================");
		for (OreJsonHenzanElement element : resultJson.getPricelive_list()) {
			System.out.println(element.getProd_name());
			// System.out.println(element.getProd_price());
			// System.out.println(element.getUpdate_time());
			latestId = element.getId();
		}

		oreDescriptionHenzan.setId(latestId);
		content = Request.Get(oreDescriptionHenzan.buildURI()).execute().returnContent();
		contentStr = content.asString(DefaultSetting.CHARSET);
		resultJson = JsonParseUtils.generateJavaBean(contentStr, OreJsonHenzan.class);

		System.out.println("===================================================");
		for (OreJsonHenzanElement element : resultJson.getPricelive_list()) {
			System.out.println(element.getProd_name());
			// System.out.println(element.getProd_price());
			// System.out.println(element.getUpdate_time());
			latestId = element.getId();
		}

		oreDescriptionHenzan.setId(latestId);
		content = Request.Get(oreDescriptionHenzan.buildURI()).execute().returnContent();
		contentStr = content.asString(DefaultSetting.CHARSET);
		resultJson = JsonParseUtils.generateJavaBean(contentStr, OreJsonHenzan.class);

		System.out.println("===================================================");
		for (OreJsonHenzanElement element : resultJson.getPricelive_list()) {
			System.out.println(element.getProd_name());
			// System.out.println(element.getProd_price());
			// System.out.println(element.getUpdate_time());
			latestId = element.getId();
		}
	}

}
