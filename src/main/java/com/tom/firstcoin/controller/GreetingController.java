package com.tom.firstcoin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tom.firstcoin.common.DefaultSetting;
import com.tom.firstcoin.common.bo.OreJsonHenzan;
import com.tom.firstcoin.service.CommonService;
import com.tom.firstcoin.service.DataAccessService;
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

	@Autowired
	DataAccessService dataAccessService;

	@RequestMapping("/")
	public String index(ModelMap map, Device device, HttpServletRequest request) throws Exception {
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

		commonService.logVisit(ipAddress, deviceType);

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("LIMIT", 50);// 每页数
		paramMap.put("OFFSET", 0);// 偏移
		List<Map<String, Object>> resultList = dataAccessService.queryMapList("BUSS003", paramMap);

		map.put("resultList", resultList);
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


		return "/test";

	}
}
