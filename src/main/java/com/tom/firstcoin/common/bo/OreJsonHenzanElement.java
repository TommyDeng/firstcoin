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
public class OreJsonHenzanElement implements Serializable {
	private static final long serialVersionUID = 1874686864803357521L;
	public String id;
	public String seq_id;
	public String prod_id;
	public String prod_url;
	public String prod_name;
	public String prod_pic;
	public String shop_id;
	public String merchant_name;
	public String screen_shot;
	public String famous_brand;
	public String famous_brand_name;
	public String goods_id;
	public String discounted_price;
	public String prod_price_txt;
	public String description;
	public String update_time;
	public String tag_id1;
	public String tag_id2;
	public String tag_id21;
	public String pv;
	public String batch_no;
	public String prod_price_status;
	public String score;
	public String prod_price;
	public String channel_name;
	public String incoming;

}
