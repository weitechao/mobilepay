package com.bracelet.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.BizResult;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsUtil {

	// 产品名称:云通信短信API产品,开发者无需替换
	static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	static final String domain = "dysmsapi.aliyuncs.com";
	private static Logger logger = LoggerFactory.getLogger(SmsUtil.class);

	public static SendSmsResponse sendSms(String name, String mobile,
			String tplCode, String tplParam) {
		logger.info("开始发送短信[mobile:" + mobile + ",tplCode:" + tplCode
				+ ",tplParam:" + tplParam + "]");
		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		SendSmsResponse result = null;
		Integer rstatus = 0;
		String rmsg = "";
		try {
			// 初始化acsClient,暂不支持region化
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
					Utils.accessKeyIdOfBeidou, Utils.accessKeySecretOfBeidou);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
					domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);
			// 组装请求对象-具体描述见控制台-文档部分内容
			SendSmsRequest request = new SendSmsRequest();
			// 必填:待发送手机号
			request.setPhoneNumbers(mobile);
			// 必填:短信签名-可在短信控制台中找到
			request.setSignName("不一订制和周卢江");
			// 必填:短信模板-可在短信控制台中找到
			request.setTemplateCode(tplCode);
			// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			request.setTemplateParam(tplParam);
			// hint 此处可能会抛出异常，注意catch
			result = acsClient.getAcsResponse(request);
			rmsg = result.getMessage();
			logger.info("收到短信结果[mobile:" + mobile + ",tplCode:" + tplCode
					+ ",tplParam:" + tplParam + "] ->: " + "Code="
					+ result.getCode() + ", Message=" + result.getMessage()
					+ ", RequestId=" + result.getRequestId() + ", BizId="
					+ result.getBizId());
		} catch (Exception e) {
			rstatus = 1;
			rmsg = e.getMessage();
			logger.info("短信发送错误:", e);
		}
		return result;
	}



	
	public static String  sendWUliu(String phone,String name,String booknum) throws ApiException {
		TaobaoClient client = new DefaultTaobaoClient(Utils.URL,
				Utils.APPKEY, Utils.SECRET);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");
		req.setSmsType("normal");
		req.setSmsFreeSignName(Utils.SMSFREESIGNNAME);
		req.setSmsParamString("{name:'"+name+"',booknum:'"+booknum+"'}");
		req.setRecNum(phone);
		req.setSmsTemplateCode("SMS_174806663");//   SMS_54255002
		AlibabaAliqinFcSmsNumSendResponse resp = client.execute(req);
		BizResult res = resp.getResult();
		return res.getMsg();
	}
	
	public static String  sendMsgMenSuo(String phone,String num) throws ApiException {
		TaobaoClient client = new DefaultTaobaoClient(Utils.URL,
				Utils.APPKEY, Utils.SECRET);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");
		req.setSmsType("normal");
		req.setSmsFreeSignName(Utils.SMSFREESIGNNAME);
		req.setSmsParamString("{\"number\":\"" + num + "\"}");
		req.setRecNum(phone);
		req.setSmsTemplateCode("SMS_115095090");//   SMS_54255002
		AlibabaAliqinFcSmsNumSendResponse resp = client.execute(req);
		BizResult res = resp.getResult();
		return res.getMsg();
	}
	  /*
     * 使用指纹打开
     * */
	public static String useFingerprintOpenDoorSendMsg(String device,String name,String tel) throws ApiException {

		TaobaoClient client = new DefaultTaobaoClient(Utils.URL,
				Utils.APPKEY, Utils.SECRET);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("");
		req.setSmsType(Utils.SMS_TYPE);
		req.setSmsFreeSignName(Utils.SMSFREESIGNNAME);
		req.setSmsParamString("{number:'"+device+"',name:'"+name+"'}");
		req.setRecNum(tel);
		req.setSmsTemplateCode(Utils.useFingerprintOpenDoor_SMSTEMPLATE_CODE);
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		return rsp.getMsg();
	}
	/*
	被撬开短信通知
	*/
	public static String pickALockSendMsg(String device,String tel) throws ApiException {

		TaobaoClient client = new DefaultTaobaoClient(Utils.URL,
				Utils.APPKEY, Utils.SECRET);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("");
		req.setSmsType(Utils.SMS_TYPE);
		req.setSmsFreeSignName(Utils.SMSFREESIGNNAME);//签名
		req.setSmsParamString( "{number:'"+device+"'}");
		req.setRecNum(tel);
		req.setSmsTemplateCode(Utils.pickALockSendMsg_SMSTEMPLATE_CODE);
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		return rsp.getMsg();
	}
	
	/*
	报警指纹短信通知
	*/
	public static String fingerSosSendMsg(String device,String name,String tel) throws ApiException {

		TaobaoClient client = new DefaultTaobaoClient(Utils.URL,
				Utils.APPKEY, Utils.SECRET);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("");
		req.setSmsType(Utils.SMS_TYPE);
		req.setSmsFreeSignName(Utils.SMSFREESIGNNAME);//签名
		req.setSmsParamString("{number:'"+device+"',name:'"+name+"'}");
		req.setRecNum(tel);
		req.setSmsTemplateCode(Utils.fingerSosSendMsg_SMSTEMPLATE_CODE);
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		return rsp.getMsg();
	}
	
	/*
	低电量报警
	*/
	public static String lowElectricSosMsg(String tel,String number,String name) throws ApiException {

		TaobaoClient client = new DefaultTaobaoClient(Utils.URL,
				Utils.APPKEY, Utils.SECRET);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("");
		req.setSmsType(Utils.SMS_TYPE);
		req.setSmsFreeSignName(Utils.SMSFREESIGNNAME);//签名
		req.setSmsParamString("{number:'"+number+"',name:'"+name+"'}");
		req.setRecNum(tel);
		req.setSmsTemplateCode(Utils.lowElectricSosMsgSendMsg_SMSTEMPLATE_CODE);
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		return rsp.getMsg();
	}
	
	
	public static SendSmsResponse  sendWatchVerificationCode(String name, String mobile,
			String tplCode, String tplParam) throws ApiException {
		logger.info("开始发送短信[mobile:" + mobile + ",tplCode:" + tplCode
				+ ",tplParam:" + tplParam + "]");
		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		SendSmsResponse result = null;
		Integer rstatus = 0;
		String rmsg = "";
		try {
			// 初始化acsClient,暂不支持region化
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
					Utils.accessKeyIdOfWatch, Utils.accessKeySecretOfWatch);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
					domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);
			// 组装请求对象-具体描述见控制台-文档部分内容
			SendSmsRequest request = new SendSmsRequest();
			// 必填:待发送手机号
			request.setPhoneNumbers(mobile);
			// 必填:短信签名-可在短信控制台中找到
			request.setSignName("亿多宝");
			// 必填:短信模板-可在短信控制台中找到
			request.setTemplateCode(tplCode);
			// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			request.setTemplateParam(tplParam);
			// hint 此处可能会抛出异常，注意catch
			result = acsClient.getAcsResponse(request);
			rmsg = result.getMessage();
			logger.info("收到短信结果[mobile:" + mobile + ",tplCode:" + tplCode
					+ ",tplParam:" + tplParam + "] ->: " + "Code="
					+ result.getCode() + ", Message=" + result.getMessage()
					+ ", RequestId=" + result.getRequestId() + ", BizId="
					+ result.getBizId());
		} catch (Exception e) {
			rstatus = 1;
			rmsg = e.getMessage();
			logger.info("短信发送错误:", e);
		}
		return result;
	}
	
	public static SendSmsResponse tijiao(String mobile,
			String booknum, String tplParam) {
		logger.info("开始发送短信[mobile:" + mobile + ",tplCode:" + "1"
				+ ",tplParam:" + tplParam + "]");
		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		SendSmsResponse result = null;
		Integer rstatus = 0;
		String rmsg = "";
		try {
			// 初始化acsClient,暂不支持region化
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
					Utils.accessKeyIdOfBeidou, Utils.accessKeySecretOfBeidou);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
					domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);
			// 组装请求对象-具体描述见控制台-文档部分内容
			SendSmsRequest request = new SendSmsRequest();
			// 必填:待发送手机号
			request.setPhoneNumbers(mobile);
			// 必填:短信签名-可在短信控制台中找到
			request.setSignName("不一订制和周卢江");
			// 必填:短信模板-可在短信控制台中找到
			request.setTemplateCode("SMS_177256291");
			// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			String name="";
			request.setTemplateParam("{name:'"+name+"',booknum:'"+booknum+"'}");
			// hint 此处可能会抛出异常，注意catch
			result = acsClient.getAcsResponse(request);
			rmsg = result.getMessage();
			logger.info("收到短信结果[mobile:" + mobile + ",tplCode:" + "11"
					+ ",tplParam:" + tplParam + "] ->: " + "Code="
					+ result.getCode() + ", Message=" + result.getMessage()
					+ ", RequestId=" + result.getRequestId() + ", BizId="
					+ result.getBizId());
		} catch (Exception e) {
			rstatus = 1;
			rmsg = e.getMessage();
			logger.info("短信发送错误:", e);
		}
		return result;
	}
	
	public static SendSmsResponse sendWuliu(String name, String mobile,
			String booknum, String tplParam) {
		logger.info("开始发送短信[mobile:" + mobile + ",tplCode:" + "1"
				+ ",tplParam:" + tplParam + "]");
		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		SendSmsResponse result = null;
		Integer rstatus = 0;
		String rmsg = "";
		try {
			// 初始化acsClient,暂不支持region化
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
					Utils.accessKeyIdOfBeidou, Utils.accessKeySecretOfBeidou);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
					domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);
			// 组装请求对象-具体描述见控制台-文档部分内容
			SendSmsRequest request = new SendSmsRequest();
			// 必填:待发送手机号
			request.setPhoneNumbers(mobile);
			// 必填:短信签名-可在短信控制台中找到
			request.setSignName("不一订制和周卢江");
			// 必填:短信模板-可在短信控制台中找到
			request.setTemplateCode("SMS_174806663");
			// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			request.setTemplateParam("{name:'"+name+"',booknum:'"+booknum+"'}");
			// hint 此处可能会抛出异常，注意catch
			result = acsClient.getAcsResponse(request);
			rmsg = result.getMessage();
			logger.info("收到短信结果[mobile:" + mobile + ",tplCode:" + "11"
					+ ",tplParam:" + tplParam + "] ->: " + "Code="
					+ result.getCode() + ", Message=" + result.getMessage()
					+ ", RequestId=" + result.getRequestId() + ", BizId="
					+ result.getBizId());
		} catch (Exception e) {
			rstatus = 1;
			rmsg = e.getMessage();
			logger.info("短信发送错误:", e);
		}
		return result;
	}

	
	public static void main(String[] args) throws ApiException {
		SendSmsResponse result=	tijiao("18735662247",
				"123456897", "1") ;
         // System.out.println(result.getMessage());
          System.out.println("a="+result.getCode());
		/*	String a=useFingerprintOpenDoorSendMsg("123456","tete","18735662247");
		System.out.println(a);
		String b=pickALockSendMsg("123456","18735662247");
		System.out.println(b);*/
	}
}
