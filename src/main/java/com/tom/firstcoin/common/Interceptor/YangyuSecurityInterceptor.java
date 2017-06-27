package com.tom.firstcoin.common.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author TommyDeng <250575979@qq.com>
 * @version 创建时间：2017年6月23日 下午5:51:44
 *
 */

public class YangyuSecurityInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	 private HttpSession httpSession;
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Boolean loginFlag = (Boolean) request.getSession().getAttribute("loginFlag");

		if (Boolean.TRUE.equals(loginFlag)) {
			return true;
		}

		response.sendRedirect("../yangyu");
		return false;

	}
}
