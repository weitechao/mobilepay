package com.bracelet.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bracelet.entity.Sys;

public class A4 {

	private static Logger logger = LoggerFactory.getLogger(A4.class);

//	private static final String privateKey = "fc939a8f2a2276e65395478fa55a1013cae4e284fefc9c28c5b80a24903b4551";
	private static final String userId = "156073891297301";
	//private static final String itemId = "gd_chhf";//商品编号
	private static final String chongZhiUrl = "http://118.31.225.1:8080/recharge/push";//test url
	//private static final String chongZhiUrl = "http://120.79.5.45:8080/recharge/push";//正式url
	private static final String chaXunUrl = "http://120.79.5.45:8080/recharge/query/order";
//	private static final String retUrl = "http://121.201.119.75:9999/mobilepay/common/returl?";

	public static String chongZhi(String dtCreate, String orderId, String chargeAcct, Integer chargeCash,String itemId,String scretKey,String isp,String province,String area) throws Exception {
		JSONObject jsona = new JSONObject();
		
		jsona.put("phone", chargeAcct);
		jsona.put("price", chargeCash+"");
		jsona.put("isp", isp);
		jsona.put("orderNo", orderId);
		jsona.put("province", province);
		jsona.put("area", area);
		jsona.put("datetime", dtCreate);
		jsona.put("notifyUrl", "http://121.201.119.75:8080/mobilepay/common/a4");
		String jsonaString = jsona.toString();// 签名原文
		logger.info("jsonaStringA4=" + jsonaString);
		String sign = URLEncoder.encode(AESOperator.encrypt(jsonaString), "utf-8");
       logger.info("A4  AESOperator 加密"+sign);
		JSONObject jsonAll = new JSONObject();
		jsonAll.put("sign", sign);
		jsonAll.put("merId", userId);
		//jsonAll.put("busiBody", jsona);
		String jsonAllStr = JSONObject.toJSONString(jsonAll);
		logger.info("A4 post发送的数据="+jsonAllStr);
		String reponse = sendPost(chongZhiUrl, jsonAllStr);
		logger.info("A4充值接口返回="+reponse);
		return reponse;
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
	
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			// conn.setRequestProperty("caller","InspectAPP");
			conn.setRequestProperty("content-type","application/json");
			// //设置参数格式 根据情况选取
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			//out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "gbk"));//处理中文乱码
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
         JSONObject jsona = new JSONObject();
		
		jsona.put("phone", "18665954447");
		jsona.put("price", 300+"");
		jsona.put("isp", "2");
		jsona.put("orderNo", System.currentTimeMillis()+"");
		jsona.put("province", "广东省");
		jsona.put("area", "广东深圳");
		jsona.put("datetime", Utils.getYyyyMMdd());
		jsona.put("notifyUrl", "http://121.201.119.75:8080/mobilepay/common/a4");
		String jsonaString = jsona.toString();// 签名原文
		logger.info("jsonaStringA4=" + jsonaString);
		
		String sign = AESOperator.encrypt(jsonaString);
		
		String urlString = URLEncoder.encode(sign, "utf-8");  //输出%C4%E3%BA%C3
		System.out.println("URLEncoder="+urlString);
		
       logger.info("A4_AESOperator_加密="+sign);
		JSONObject jsonAll = new JSONObject();
		jsonAll.put("sign", urlString);
		jsonAll.put("merId", userId);
		//jsonAll.put("busiBody", jsona);
		String jsonAllStr = JSONObject.toJSONString(jsonAll);
		logger.info("A4 post发送的数据="+jsonAllStr);
		
		String reponse = sendPost(chongZhiUrl, jsonAllStr);
		System.out.println("reponse返回="+reponse);
	}
/*	public static void main(String[] args) {
try {
	String sign ="0YOLC8Dk1Rpcz33yLOiyFmHzeOq6g4o6vQBmWvXO2X9pwhGYBJHgty%2FMugddPZnVRHHPWIOSL3p8%0Aw0%2BW5%2F%2FQrTVgFJ058KeuWiHC8jqj8RB37kbiFVlNER74w1pUvdC6VLYhPtEp5aU0ul%2Beo7yhOYMI%0AEL4wMJKREFWk%2FlTH%2FwD5CRGB7ePUg9TTCtlGTeAC";
	String urlString = URLDecoder.decode(sign, "UTF-8");
	System.out.println(
			AESOperator.decrypt(urlString));
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}*/
}
