package com.bracelet.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class A5 {
	/*
	 * 
	 * 
	 * 
	 *
商户账户：18124036181 
商户名称：新创海 
登陆密码：900801 
MD5KEY：92eb189042af75a2a7f852a564285b87 
对接地址：http://api.taivei.com:18200/request 
查单地址：http://hf.taivei.com/mer/login 
	 * */
	private static Logger logger = LoggerFactory.getLogger(A5.class);

    private static final String privateKeyy = "92eb189042af75a2a7f852a564285b87";
	private static final String userId = "18124036181";
	private static final String back_url = "http://121.201.119.75:8080/mobilepay/common/a5?";
	//private static final String itemId = "gd_chhf";//商品编号
	private static final String chongZhiUrl = "http://api.taivei.com:18200/request";
	private static final String chaXunUrl = "http://39.106.64.237:6160/unicomAync/queryBizOrder.do";
//	private static final String retUrl = "http://121.201.119.75:9999/mobilepay/common/returl?";

	public static String chongZhi( String orderId, String chargeAcct, Integer chargeCash,String scretKey) {
        String text ="order"+userId+ orderId+ chargeAcct +chargeCash+"0"+ back_url+ privateKeyy;
		logger.info("原始数据="+text);
		String sign = Md5.encryption(text);
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("service", "order");
		map.put("merchant_id", userId);
		map.put("merchant_orderid", orderId);
		map.put("charge_number", chargeAcct);
		map.put("charge_money", chargeCash+"");
		map.put("busi_type", "0");
		map.put("back_url", back_url);
		map.put("package_type", "000");
		map.put("sign", sign);
		String jsonToString = HttpClientGet.sendGetToGaoDeA11(chongZhiUrl, map);
		logger.info("A5接口请求返回="+jsonToString);
		
		return jsonToString;
	}

	public static String chaXun(String orderId,String scretKey) {
		String sign = Md5.encryption(orderId+userId+scretKey);
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("sign", sign);
		map.put("userId", userId);
		map.put("serialno", orderId);
		String jsonToString = HttpClientGet.sendGetToGaoDe(chaXunUrl, map);
		return jsonToString;
	}
	
	public static void main(String[] args) {
		Integer chargeCash = 50;// 充值金额必须以元为单位

		String orderId = System.currentTimeMillis() + ""; // 订单号必须唯一由商户自己生成
		String chargeAcct = "13510181600"; // 充值账号chargeCash
		String reponse = chongZhi(orderId,chargeAcct, chargeCash,privateKeyy);
		System.out.println(reponse.substring(12, 16));

	}
}
