package com.tom.firstcoin.common.bo;

import java.io.Serializable;
import java.net.URI;

import org.apache.http.client.utils.URIBuilder;

import com.tom.firstcoin.common.DefaultSetting;

import lombok.Data;

/**
 * @author TommyDeng <250575979@qq.com>
 * @version 创建时间：2016年9月21日 下午12:06:41
 *
 */
@Data
public class OreDescriptionHenzan implements Serializable {
	// http://www.henzan.com/api/pricelive_list?offset=0&limit=20&mid=0&pid=-1&cid=0&bn=1488878234

	private static final long serialVersionUID = 2012592443455621898L;

	public final static String ORE_URL = "http://www.henzan.com/api/pricelive_list";

	/**
	 * 起始偏移量
	 */
	String offset = "0";
	// 取数范围
	String limit = "20";

	// UNKNOWN
	String mid = "0";

	// UNKNOWN
	String pid = "-1";

	// UNKNOWN
	String cid = "0";

	// 时间戳
	String bn = String.valueOf(System.currentTimeMillis());

	/**
	 * 生产最终的uri
	 * 
	 * @return
	 * @throws Exception
	 */
	public URI buildURI() throws Exception {
		URIBuilder uriBuilder = new URIBuilder(ORE_URL);
		uriBuilder.setCharset(DefaultSetting.CHARSET);
		
		// build by order
		uriBuilder.addParameter("offset", offset);
		uriBuilder.addParameter("limit", limit);
		uriBuilder.addParameter("mid", mid);
		uriBuilder.addParameter("pid", pid);
		uriBuilder.addParameter("cid", cid);
		uriBuilder.addParameter("bn", bn);

		return uriBuilder.build();
	}
}
