package com.tom.firstcoin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tom.firstcoin.common.DefaultSetting;
import com.tom.firstcoin.common.bo.OreJsonHenzan;
import com.tom.firstcoin.service.CommonService;
import com.tom.utils.JsonParseUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author TommyDeng <250575979@qq.com>
 * @version 创建时间：2016年9月14日 上午11:29:38
 *
 */

@Controller
@Slf4j
public class GreetingController extends BaseController {

	@Autowired
	CommonService commonService;

	@RequestMapping("/")
	public String index(ModelMap map,
			@RequestParam(name = "name", required = false, defaultValue = "anonymous") String name, Device device,
			HttpServletRequest request) throws Exception {
		String ipAddress = request.getHeader("X-Real-IP");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}

		String deviceType = "unknown";
		if (device.isNormal()) {
			deviceType = "normal";
		} else if (device.isMobile()) {
			deviceType = "mobile";
		} else if (device.isTablet()) {
			deviceType = "tablet";
		}

		map.put("host", name);
		log.info("Visitor<" + name + ">" + "IP<" + ipAddress + ">" + "Device<" + deviceType + ">.");
		
		Content content = Request.Get("http://www.henzan.com/api/pricelive_list").execute().returnContent();
		String contentStr = content.asString(DefaultSetting.CHARSET);
		OreJsonHenzan result = JsonParseUtils.generateJavaBean(contentStr, OreJsonHenzan.class);

		map.put("result", result);
		
		return "/main";
	}

	/*
	 * 关于
	 */
	@RequestMapping("/aboutme")
	public String aboutme(ModelMap map) throws Exception {

		return "/aboutme";
	}

	/*
	 * 版本简介
	 */
	@RequestMapping("/versionDetail")
	public String versionDetail(ModelMap map) throws Exception {

		return "/versionDetail";
	}

	/*
	 * 测试
	 */
	@RequestMapping("/test")
	public String test(ModelMap map) throws Exception {

		Content content = Request.Get("http://www.henzan.com/api/pricelive_list").execute().returnContent();
		String contentStr = content.asString(DefaultSetting.CHARSET);
		OreJsonHenzan result = JsonParseUtils.generateJavaBean(contentStr, OreJsonHenzan.class);

		map.put("result", result);

		return "/test";

	}
}
