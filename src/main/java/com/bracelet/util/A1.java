package com.bracelet.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class A1 {
	private static Logger logger = LoggerFactory.getLogger(A1.class);

	
	private static final String agentAccount = "gd_chhf";
	private static final String url = "http://api.madaikr.com:50080/API";
	private static final String retUrl = "http://121.201.119.75:8080/mobilepay/common/returl?";

	public static String chongZhi(String orderId, String chargeAcct, Integer chargeCash, String scretKey) {

		JSONObject jsona = new JSONObject();
		jsona.put("action", "CZ");
		jsona.put("orderId", orderId);
		jsona.put("chargeAcct", chargeAcct);
		jsona.put("chargeCash", chargeCash);
		jsona.put("chargeType", 0);
		jsona.put("retUrl", retUrl);
		String jsonaString = jsona.toString() + scretKey;// 签名原文
		logger.info("jsonaString=" + jsonaString);
		String sign = Md5.encryption(jsonaString);

		JSONObject jsonAll = new JSONObject();
		jsonAll.put("sign", sign);
		jsonAll.put("agentAccount", agentAccount);
		jsonAll.put("busiBody", jsona);
		String jsonAllStr = JSONObject.toJSONString(jsonAll);
		logger.info(jsonAllStr);
		String reponse = sendPost(url, jsonAllStr);
		logger.info("A1充值接口返回="+reponse);
		return reponse;
	}

	public static String orderIdSelect(String orderId, String scretKey) {

		JSONObject jsona = new JSONObject();
		jsona.put("action", "CX");
		jsona.put("orderId", orderId);

		String jsonaString = jsona.toString() + scretKey;// 签名原文
		logger.info("jsonaString=" + jsonaString);
		String sign = Md5.encryption(jsonaString);
		logger.info("sign=" + sign);

		JSONObject jsonAll = new JSONObject();
		jsonAll.put("sign", sign);
		jsonAll.put("agentAccount", agentAccount);
		jsonAll.put("busiBody", jsona);
		String jsonAllStr = JSONObject.toJSONString(jsonAll);
		logger.info(jsonAllStr);

		String reponse = HttpRequest.sendPost(url, jsonAllStr);
		
		return reponse;
	}

	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
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
			// conn.setRequestProperty("content-type","application/json");
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

	public static void main(String[] args) {
		Integer chargeCash = 10;// 充值金额必须以元为单位

		String orderId = System.currentTimeMillis() + ""; // 订单号必须唯一由商户自己生成
		String chargeAcct = "13510181600"; // 充值账号chargeCash
		// fenceService.insertChargeInfo(userName, orderId, chargeAcct,
		// chargeCash);//增加商户充值记录

		JSONObject jsona = new JSONObject();
		jsona.put("action", "CZ");
		jsona.put("orderId", orderId);
		jsona.put("chargeAcct", chargeAcct);
		jsona.put("chargeType", 0);
		jsona.put("chargeCash", chargeCash);
		jsona.put("retUrl", "");
		String jsonaString = jsona.toString() + "";// 签名原文
		System.out.println("jsonaString=" + jsonaString);

		String sign = Md5.encryption(jsonaString);
		System.out.println("sign=" + sign);

		JSONObject jsonAll = new JSONObject();
		jsonAll.put("sign", sign);
		jsonAll.put("agentAccount", agentAccount);
		jsonAll.put("busiBody", jsona);
		String jsonAllStr = JSONObject.toJSONString(jsonAll);
		System.out.println(jsonAllStr);

		String reponse = HttpRequest.sendPost(url, jsonAllStr);
		System.out.println("请求上游充值接口返回的数据=" + reponse);
		JSONObject reponseJsonObject = (JSONObject) JSON.parse(reponse);
		Integer errorCode = reponseJsonObject.getInteger("errorCode"); // 1表示成功

	}

}
