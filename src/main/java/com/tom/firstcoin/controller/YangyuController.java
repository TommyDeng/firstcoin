package com.tom.firstcoin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tom.firstcoin.service.CommonService;
import com.tom.firstcoin.service.DataAccessService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author TommyDeng <250575979@qq.com>
 * @version 创建时间：2016年9月14日 上午11:29:38
 *
 */

@Controller
@Slf4j
public class YangyuController extends BaseController {

	@Autowired
	CommonService commonService;

	@Autowired
	DataAccessService dataAccessService;

	/**
	 * 
	 * @param map
	 * @param device
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/yangyuRewardList")
	public String yangyuRewardList(ModelMap map, Device device, HttpServletRequest request) throws Exception {
		List<Map<String, Object>> rewards = dataAccessService.queryMapList("YY001");
		map.put("rewards", rewards);
		return "/yangyu/yangyuRewardList";
	}

	@RequestMapping("/yangyuRewardDetail")
	public String yangyuRewardDetail(ModelMap map, Device device, HttpServletRequest request) throws Exception {

		return "/yangyu/yangyuRewardDetail";
	}

}
