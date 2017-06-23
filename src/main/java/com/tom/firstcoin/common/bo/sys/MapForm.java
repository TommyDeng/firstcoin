package com.tom.firstcoin.common.bo.sys;

import java.util.Map;

import org.springframework.util.LinkedCaseInsensitiveMap;

/**
 * @author TommyDeng <250575979@qq.com>
 * @version 创建时间：2017年6月20日 下午4:42:22
 *
 */

public class MapForm {

	private Map<String, Object> properties = new LinkedCaseInsensitiveMap<>();

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

}
