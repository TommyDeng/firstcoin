package com.tom.firstcoin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	 * 登陆页面
	 * 
	 * @param map
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/yangyu")
	public String yangyu(ModelMap map, HttpServletRequest request) throws Exception {
		MapForm mapForm = new MapForm();
		map.put("formData", mapForm);
		map.put("loginTips", "Please sign in");
		return "/yangyu/yangyuSignin";
	}

	/**
	 * 登陆
	 * 
	 * @param map
	 * @param request
	 * @param mapForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/yangyuSignin")
	public String yangyuSignin(ModelMap map, HttpServletRequest request, HttpSession session,
			@ModelAttribute MapForm mapForm) throws Exception {
		String userName = (String) mapForm.getProperties().get("userName");
		String password = (String) mapForm.getProperties().get("password");
		if ("admin".equals(userName) && "123456".equals(password)) {
			session.setAttribute("loginFlag", Boolean.TRUE);
			session.setAttribute("loginUser", userName);
			return "redirect:/yangyu/yangyuRewardList";
		}
		map.put("loginTips", "Try Again");
		mapForm.getProperties().clear();
		map.put("formData", mapForm);
		return "/yangyu/yangyuSignin";
	}

	/**
	 * 登出
	 * 
	 * @param map
	 * @param request
	 * @param mapForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/yangyuSignout")
	public String yangyuSignout(ModelMap map, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @ModelAttribute MapForm mapForm) throws Exception {
		session.removeAttribute("loginFlag");
		session.removeAttribute("loginUser");
		session.invalidate();
		return "redirect:/yangyu";
	}

	/**
	 * 记录列表
	 * 
	 * @param map
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/yangyu/yangyuRewardList")
	public String yangyuRewardList(ModelMap map, HttpServletRequest request) throws Exception {
		List<Map<String, Object>> rewards = dataAccessService.queryMapList("YY001");
		map.put("rewards", rewards);
		return "/yangyu/yangyuRewardList";
	}

	/**
	 * 记录明细
	 * 
	 * @param map
	 * @param request
	 * @param rowId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/yangyu/yangyuRewardDetail")
	public String yangyuRewardDetail(ModelMap map, HttpServletRequest request, String rowId) throws Exception {
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

	/**
	 * 保存记录
	 * 
	 * @param map
	 * @param request
	 * @param mapForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/yangyu/yangyuRewardSave")
	public String yangyuRewardSave(ModelMap map, HttpServletRequest request, HttpSession session,
			@ModelAttribute MapForm mapForm) throws Exception {
		String rowId = (String) mapForm.getProperties().get("id");
		mapForm.getProperties().remove("id");// 插入时自增长，更新无需修改

		if (StringUtils.isBlank(rowId)) {
			mapForm.getProperties().put("creator", session.getAttribute("loginUser"));
			dataAccessService.insertSingle("yangyu_rewards", mapForm.getProperties());

		} else {
			Map<String, Object> whereParamMap = new HashMap<>();
			whereParamMap.put("id", rowId);

			dataAccessService.updateSingle("yangyu_rewards", mapForm.getProperties(), whereParamMap);
		}
		return "redirect:/yangyu/yangyuRewardList";
	}

	/**
	 * 删除记录
	 * 
	 * @param map
	 * @param request
	 * @param rowId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/yangyu/yangyuRewardDelete")
	public String yangyuRewardDelete(ModelMap map, HttpServletRequest request, String rowId) throws Exception {
		if (!StringUtils.isBlank(rowId)) {
			Map<String, Object> whereParamMap = new HashMap<>();
			whereParamMap.put("id", rowId);

			dataAccessService.deleteSingle("yangyu_rewards", whereParamMap);
		}
		return "redirect:/yangyu/yangyuRewardList";
	}

	/**
	 * 查看返现操作记录
	 * 
	 * @param map
	 * @param request
	 * @param rowId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/yangyu/yangyuFeedRecordsList")
	public String yangyuFeedRecordsList(ModelMap map, HttpServletRequest request, String rowId) throws Exception {
		if (!StringUtils.isBlank(rowId)) {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("rewards_id", rowId);
			List<Map<String, Object>> rewards = dataAccessService.queryMapList("YY005", paramMap);
			map.put("rewards", rewards);
		}
		return "/yangyu/yangyuFeedRecordsList";
	}

	/**
	 * 今日记录列表
	 * 
	 * @param map
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/yangyu/yangyuRewardToday")
	public String yangyuRewardToday(ModelMap map, HttpServletRequest request) throws Exception {
		List<Map<String, Object>> rewards = dataAccessService.queryMapList("YY003");
		map.put("rewards", rewards);
		return "/yangyu/yangyuRewardToday";
	}

	/**
	 * 馈赠操作
	 * 
	 * @param map
	 * @param request
	 * @param rowId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/yangyu/yangyuRewardFeed")
	public String yangyuRewardFeed(ModelMap map, HttpServletRequest request, HttpSession session, String rowId)
			throws Exception {
		if (!StringUtils.isBlank(rowId)) {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("id", rowId);
			dataAccessService.update("YY004", paramMap);

			paramMap.clear();
			paramMap.put("rewards_id", rowId);
			paramMap.put("creator", session.getAttribute("loginUser"));
			dataAccessService.insertSingle("yangyu_feed_record", paramMap);
		}
		return "redirect:/yangyu/yangyuRewardToday";
	}

}
