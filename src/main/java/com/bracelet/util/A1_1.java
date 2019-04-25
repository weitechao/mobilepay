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

public class A1_1 {
	/*
	 * 
	 * 
	 * 
	 * 充值接口:http://112.126.86.187:7760/unicomAync/buy.do

查询接口：http://112.126.86.187:7760/unicomAync/queryBizOrder.do

余额查询接口：http://112.126.86.187:7760/unicomAync/queryBalance.do

key:fc939a8f2a2276e65395478fa55a1013cae4e284fefc9c28c5b80a24903b4551

查单平台:http://112.126.86.187:7765/index.do

用户名:xinchuanghai
密码: sup123456（请及时修改密码）
userId:589
写好代码请给我回调地址和白名单，谢谢
	 * */
	private static Logger logger = LoggerFactory.getLogger(A1_1.class);

    private static final String privateKeyy = "ea8ec80aae5d87b0bd023ddf8e558b5ef634f4e14069eea069f97e08f8e3e4a5";
	private static final String userId = "511";
	//private static final String itemId = "gd_chhf";//商品编号
	private static final String chongZhiUrl = "http://39.106.64.237:6160/unicomAync/buy.do";
	private static final String chaXunUrl = "http://39.106.64.237:6160/unicomAync/queryBizOrder.do";
//	private static final String retUrl = "http://121.201.119.75:9999/mobilepay/common/returl?";

	public static String chongZhi(String dtCreate, String orderId, String chargeAcct, Integer chargeCash,String itemId,String scretKey) {
        logger.info("原始数据="+dtCreate+itemId+orderId+chargeAcct+userId+scretKey);
		String sign = Md5.encryption(dtCreate+itemId+orderId+chargeAcct+userId+scretKey);
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("sign", sign);
		map.put("uid", chargeAcct);
		map.put("dtCreate", dtCreate);
		map.put("userId", userId);
		map.put("itemId", itemId);
		map.put("serialno", orderId);
		String jsonToString = HttpClientGet.sendGetToGaoDeA11(chongZhiUrl, map);
		logger.info("A1新接口请求返回="+jsonToString);
		
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
}
