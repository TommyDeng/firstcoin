package com.tom.firstcoin.service;

/**
 * @author TommyDeng <250575979@qq.com>
 * @version 创建时间：2017年3月7日 上午11:19:07
 *
 */

public interface AntennaService {
	/**
	 * 抓取和处理henzan数据
	 * 
	 * @throws Exception
	 */
	void executeHenzan() throws Exception;

	/**
	 * 备份三个月前的数据
	 */
	void backupHistory();
}
