package com.tom.firstcoin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tom.firstcoin.common.bo.sys.MapForm;
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
	@RequestMapping("/yangyu")
	public String yangyu(ModelMap map, Device device, HttpServletRequest request) throws Exception {

		MapForm mapForm = new MapForm();
		map.put("formData", mapForm);
		map.put("loginTips", "Please sign in");
		return "/yangyu/yangyuLogin";
	}

	@RequestMapping("/yangyuLogin")
	public String yangyuLogin(ModelMap map, Device device, HttpServletRequest request, @ModelAttribute MapForm mapForm)
			throws Exception {
		String userName = (String) mapForm.getProperties().get("userName");
		String password = (String) mapForm.getProperties().get("password");
		if ("admin".equals(userName) && "123456".equals(password)) {
			request.getSession().setAttribute("loginFlag", Boolean.TRUE);
			return "redirect:/yangyu/yangyuRewardList";
		}
		map.put("loginTips", "Try Again");
		mapForm.getProperties().clear();
		map.put("formData", mapForm);
		return "/yangyu/yangyuLogin";
	}

	/**
	 * 
	 * @param map
	 * @param device
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/yangyu/yangyuRewardList")
	public String yangyuRewardList(ModelMap map, Device device, HttpServletRequest request) throws Exception {
		List<Map<String, Object>> rewards = dataAccessService.queryMapList("YY001");
		map.put("rewards", rewards);
		return "/yangyu/yangyuRewardList";
	}

	@RequestMapping("/yangyu/yangyuRewardDetail")
	public String yangyuRewardDetail(ModelMap map, Device device, HttpServletRequest request, String rowId)
			throws Exception {
		MapForm mapForm = new MapForm();
		if (rowId != null) {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("id", rowId);

			Map<String, Object> row = dataAccessService.queryForMap("YY002", paramMap);
			mapForm.setProperties(row);
		}
		map.put("formData", mapForm);

		return "/yangyu/yangyuRewardDetail";
	}

	@RequestMapping("/yangyu/yangyuRewardSave")
	public String yangyuRewardSave(ModelMap map, @ModelAttribute MapForm mapForm) throws Exception {
		String rowId = (String) mapForm.getProperties().get("id");
		mapForm.getProperties().remove("id");// 插入时自增长，更新无需修改

		if (StringUtils.isBlank(rowId)) {
			dataAccessService.insertSingle("yangyu_rewards", mapForm.getProperties());

		} else {
			Map<String, Object> whereParamMap = new HashMap<>();
			whereParamMap.put("id", rowId);

			dataAccessService.updateSingle("yangyu_rewards", mapForm.getProperties(), whereParamMap);
		}
		return "redirect:/yangyu/yangyuRewardList";
	}

	@RequestMapping("/yangyu/yangyuRewardDelete")
	public String yangyuRewardDelete(ModelMap map, @ModelAttribute MapForm mapForm, String rowId) throws Exception {
		if (!StringUtils.isBlank(rowId)) {
			Map<String, Object> whereParamMap = new HashMap<>();
			whereParamMap.put("id", rowId);

			dataAccessService.deleteSingle("yangyu_rewards", whereParamMap);
		}
		return "redirect:/yangyu/yangyuRewardList";
	}
}
