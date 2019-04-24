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

public class A3 {
	private static Logger logger = LoggerFactory.getLogger(A3.class);

	//private static final String scret = "xE6saCQxhSf7JwR4eS4QGNsKh6rjTdXf";
	private static final String agentAccount = "10001998";
	private static final String url = "http://111.23.12.12:9086/onlinepay.do";
	private static final String selectUrl = "http://111.23.12.12:9086/searchpay.do";
	private static final String retUrl = "http://121.201.119.75:8080/mobilepay/common/a3";

	public static String chongZhi(String shijian, String orderId, String chargeAcct, Integer chargeCash,String itemId,String scret) {
/*
 *userid=xxxx&productid=xxxxxxx&price=xxxx&num=xxx&mobile=xxxxx&spordertime=xxxxxxx&sporderid=xxxxx&key=xxxxxxx (这里的xxxxx为具体的参数值)
 * */
		StringBuffer sb =new StringBuffer();
		sb.append("userid="+agentAccount).append("&productid=").append("&price=").append(chargeCash)
		.append("&num=1").append("&mobile=").append(chargeAcct).append("&spordertime=").append(shijian).append("&sporderid=").append(orderId);
		//.append("&key=").append(scret);
		String sign = Md5.encryption(sb.toString()+"&key="+scret);
		sb.append("&sign=").append(sign).append("&back_url="+retUrl);
		logger.info("A3充值="+sb.toString());
		String reponse = sendPost(url, sb.toString());
		logger.info("A3充值接口返回="+reponse);
		String xmlJson = Xml2JsonUtil.xml2JSON(reponse).replace("[", "").replace("]", "");
		logger.info("A3充值接口返回xmlJson="+xmlJson);
		return xmlJson;
	}

	public static String orderIdSelect(String orderId) {
		/*
		 * userid=xxxx&sporderid=xxxxx这里的xxxxx为具体的参数值)
		 * */
				StringBuffer sb =new StringBuffer();
				sb.append("userid="+agentAccount).append("&sporderid=").append(orderId);
				
				//.append("&key=").append(scret);
				/*String sign = Md5.encryption(sb.toString()+"&key="+scret);
				sb.append("&sign=").append(sign).append("&back_url="+retUrl);*/
				logger.info("A3查询="+sb.toString());
				String reponse = sendPost(selectUrl, sb.toString());
				logger.info("A3查询接口返回="+reponse);
				String xmlJson = Xml2JsonUtil.xml2JSON(reponse).replace("[", "").replace("]", "");
				logger.info("A3查询接口返回xmlJson="+xmlJson);
				return xmlJson;
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
		String xmlJson = "sdfklahsdklf[asdfasd]][[][]".replace("[", "").replace("]", "");
		System.out.println(xmlJson);
	}

}
