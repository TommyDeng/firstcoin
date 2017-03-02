package com.tom.firstcoin.common.bo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author TommyDeng <250575979@qq.com>
 * @version 创建时间：2016年9月21日 下午12:06:41
 *
 */
@Data
public class OreJsonHenzan implements Serializable {
	private static final long serialVersionUID = -3119718944105304809L;
	public Long current_time;
	public List<OreJsonHenzanElement> pricelive_list;
	
}
