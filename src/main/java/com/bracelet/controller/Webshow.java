package com.bracelet.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bracelet.entity.BindDevice;
import com.bracelet.entity.BusinessUserInfo;
import com.bracelet.entity.CxInfo;
import com.bracelet.service.IFenceService;
import com.bracelet.service.IPushlogService;
import com.bracelet.service.IStepService;
import com.bracelet.service.IUserInfoService;
import com.bracelet.util.Des;
import com.bracelet.util.HttpRequest;
import com.bracelet.util.Md5;

@Controller
@RequestMapping("/webshow")
public class Webshow {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	IUserInfoService userInfoService;
	
	@Autowired
	IFenceService fenceService; //商户充值记录
	
	@Autowired
	IStepService stepService;
	
	@Autowired
	IPushlogService phoneService;
	private static final String scret= "0FE8E43F53BB5848";
	private static final String agentAccount= "api_test";
	private static final String url= "api_test";

	//用户列表查询查看
	@ResponseBody
	@RequestMapping(value = "/getUserList/{start}/{end}/{status}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String getUserList(@PathVariable Integer start,@PathVariable Integer end,@PathVariable Integer status) {
		JSONObject bb = new JSONObject();
		List<BusinessUserInfo> list = userInfoService.getBusinessUserListInfo(start,end,status);
		JSONArray jsonArray = new JSONArray();
		if (list != null && !list.isEmpty()) {
			for (BusinessUserInfo buser : list) {
				JSONObject dataMap = new JSONObject();
				dataMap.put("id", buser.getId());
				dataMap.put("username",buser.getUsername()+"");
				dataMap.put("createtime", buser.getCreatetime().getTime());
				dataMap.put("balance", buser.getBalance());
				dataMap.put("use_status", buser.getUse_status());
				jsonArray.add(dataMap);
			}
		}
		bb.put("code", 1);
		bb.put("list", jsonArray);

		return bb.toString();
	}
	//修改商户的使用状态
	@ResponseBody
	@RequestMapping(value = "/udpateUserStatusInfo/{id}/{status}/{username}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String udpateUserStatusInfo(@PathVariable Long id,@PathVariable Integer status,@PathVariable String username) {
		JSONObject bb = new JSONObject();
		if(userInfoService.updateStatusInfoById(id,status,username)){
			bb.put("code", 1);
		}else{
			bb.put("code", 1);
		}
		return bb.toString();
	}
	
	
/*	//用户增加
	@ResponseBody
	@RequestMapping(value = "/udpateUserStatusInfo/{id}/{status}/{username}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String udpateUserStatusInfo(@PathVariable Long id,@PathVariable Integer status,@PathVariable String username) {
		JSONObject bb = new JSONObject();
		if(userInfoService.updateStatusInfoById(id,status,username)){
			bb.put("code", 1);
		}else{
			bb.put("code", 1);
		}
		return bb.toString();
	}*/
	
	
	
	
	// 代理商订单查询接口
		@ResponseBody
		@RequestMapping(value = "/orderIdSelect", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
		public String orderIdSelect(@RequestBody String body) {
			JSONObject bb = new JSONObject();
			try {
				String requestString = Des.decrypt(body);//解密
				
				JSONObject jsonObject = (JSONObject) JSON.parse(requestString);
				String userName = jsonObject.getString("username");  //商户号
				
				BusinessUserInfo userInfo = userInfoService.getBusinessUserInfoByUsername(userName);
				if(userInfo == null) {
					bb.put("code", 0);
					return bb.toString();
				}else {
					if(userInfo.getUse_status()==1) {
						String orderId  = jsonObject.getString("orderId");  //订单号必须唯一由商户自己生成
						CxInfo cxInfo = fenceService.getChargeSuccessInfo(userName,orderId);
						if(cxInfo!=null){
							bb.put("code", 1);
							bb.put("orderId", orderId);
							bb.put("orderStatuInt", "16");
							bb.put("orderStatuText", "成功");
							return bb.toString();
						}
						
						JSONObject jsona = new JSONObject();
						jsona.put("action", "CX");
						jsona.put("orderId", orderId);
						
						String jsonaString= jsona.toString()+scret;//签名原文
						logger.info("jsonaString="+jsonaString);
						String sign = Md5.encryption(jsonaString);
						logger.info("sign="+sign);
						
						JSONObject jsonAll = new JSONObject();
						jsonAll.put("sign", sign);
						jsonAll.put("agentAccount", agentAccount);
						jsonAll.put("busiBody", jsona);
						String jsonAllStr =JSONObject.toJSONString(jsonAll);
						logger.info(jsonAllStr);
						
						String reponse = HttpRequest.sendPost(url,jsonAllStr);
						logger.info("请求上游查询接口返回的数据="+reponse);
						JSONObject reponseJsonObject = (JSONObject) JSON.parse(reponse);
						Integer errorCode = reponseJsonObject.getInteger("errorCode"); //1表示成功
						if(errorCode == 1) {
							bb.put("code", 1);
							bb.put("orderId", orderId);
							bb.put("orderStatuInt", reponseJsonObject.getString("orderStatuInt"));
							bb.put("orderStatuText", reponseJsonObject.getString("orderStatuText"));
							
							fenceService.insertPreviousLevelChargeInfo(userName, orderId, "1", reponseJsonObject.getInteger("orderPayment"), errorCode);//增加商户充值成功记录
						}else {
							bb.put("code", errorCode);
							bb.put("orderId", orderId);
							bb.put("orderStatuInt", reponseJsonObject.getString("orderStatuInt"));
							bb.put("orderStatuText", reponseJsonObject.getString("orderStatuText"));
							stepService.insertOrderErrorInfo(userName,orderId,Integer.valueOf(reponseJsonObject.getString("orderStatuInt")),reponseJsonObject.getString("orderStatuText"));
						//添加查询不正常的记录
						}
					
						return bb.toString();
					}else {
						bb.put("code", 2);
						return bb.toString();
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return bb.toString();
		}
		
		// 商户余额查询接口
				@ResponseBody
				@RequestMapping(value = "/getbalance", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
				public String getbalance(@RequestBody String body) {
					JSONObject bb = new JSONObject();
					try {
						String requestString = Des.decrypt(body);//解密
						
						JSONObject jsonObject = (JSONObject) JSON.parse(requestString);
						String userName = jsonObject.getString("username");  //商户号
						
						BusinessUserInfo userInfo = userInfoService.getBusinessUserInfoByUsername(userName);
						if(userInfo == null) {
							bb.put("code", 0);
							return bb.toString();
						}else {
							bb.put("code", 1);
							bb.put("balance", userInfo.getBalance());
							bb.put("status", userInfo.getUse_status());
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					return bb.toString();
				}
	
	public static void main(String[] args) {
		JSONObject jsona = new JSONObject();
		jsona.put("action", "CZ");
		jsona.put("orderId", "api_test20160526142659562");
		jsona.put("chargeAcct", "18735662247");
		jsona.put("chargeType", 0);
		jsona.put("chargeCash", 50);
		jsona.put("retUrl", "");
		String jsonaString= jsona.toString()+scret;
		System.out.println("jsonaString="+jsonaString);
		String sign = Md5.encryption(jsonaString);
		System.out.println("sign="+sign);
		
		
		
 
		JSONObject jsonAll = new JSONObject();
		jsonAll.put("sign", sign);
		jsonAll.put("agentAccount", "api_test");
		jsonAll.put("busiBody", jsona);
		
		String jsonAllStr =JSONObject.toJSONString(jsonAll);
		System.out.println(jsonAllStr);
	}

}
