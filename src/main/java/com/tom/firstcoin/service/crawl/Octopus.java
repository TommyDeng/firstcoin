package com.tom.firstcoin.service.crawl;

import java.util.Set;

/**
 * 抓取模块主体
 * 
 * @author TommyDeng <250575979@qq.com>
 * @version 创建时间：2017年3月3日 下午3:59:28
 *
 */
public interface Octopus {

	// 获取所有触须
	Set<Antenna> getAntennas();
	
	
}
