package com.tom.firstcoin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * @author TommyDeng <250575979@qq.com>
 * @version 创建时间：2016年9月14日 上午11:29:38
 *
 */

@Controller
@Slf4j
public class GreetingController {

	@RequestMapping("/")
	@ResponseBody
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String url = "http://www.henzan.com/live?from=plugin_toolbar";

		Document document = Jsoup.connect(url).get();
		String targetTxt = document.html();

		System.out.println("获取结果:\n" + targetTxt);
		return targetTxt;
	}

}
