package com.bracelet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bracelet.dto.*;
import com.bracelet.entity.BloodOxygen;
import com.bracelet.entity.BloodSugar;
import com.bracelet.entity.CxInfo;
import com.bracelet.entity.HeartPressure;
import com.bracelet.entity.HeartRate;
import com.bracelet.entity.Location;
import com.bracelet.entity.ReturnSuccessInfo;
import com.bracelet.entity.Step;
import com.bracelet.service.IBloodOxygenService;
import com.bracelet.service.IBloodSugarService;
import com.bracelet.service.IHeartPressureService;
import com.bracelet.service.IHeartRateService;
import com.bracelet.service.ILocationService;
import com.bracelet.service.IStepService;
import com.bracelet.util.AESOperator;
import com.bracelet.util.StringUtil;
import com.bracelet.util.Utils;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {
	@Autowired
	IHeartPressureService heartPressureService;
	@Autowired
	ILocationService locationService;
	@Autowired
	IHeartRateService heartRateService;
	@Autowired
	IStepService stepService;
	@Autowired
	IBloodSugarService bloodSugarService;
	@Autowired
	IBloodOxygenService bloodOxygenService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	
	//第四个公司回调
	@ResponseBody
	@RequestMapping(value = "/a4", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String a4(@RequestBody String body) {
		logger.info("第四家运营商回调接口数据="+body);
		try {
		JSONObject reponseJsonObject = (JSONObject) JSON.parse(body);
	//	String merId = reponseJsonObject.getString("merId");
		String sign = reponseJsonObject.getString("sign");
		
		 JSONObject	OrderJson = (JSONObject) JSON.parse(AESOperator.decrypt(sign));
		
		String orderNo = OrderJson.getString("orderNo");
		String status = OrderJson.getString("status");
		String voucher = OrderJson.getString("voucher");
		
		
		
		logger.info("A4回调充值订单号状态="+orderNo+","+status);
		ReturnSuccessInfo returnInfo = fenceService.getReturnInfoByOrderId(orderNo);
		if(returnInfo!=null){
			return "OK";
		}
		
		CxInfo cxInfoError = fenceService.getCharge4ErrorInfo(orderNo);
		if(cxInfoError!=null){
			fenceService.insertReturnSuccessfulInfo(orderNo,cxInfoError.getUser_id());
			
			if("5".equals(status)){
				insertSuccessInfo(cxInfoError.getUsername(), orderNo,cxInfoError.getCharge_acct(), cxInfoError.getCharge_cash(), 1);// 增加商户充值成功记录
				
			}else{
				updateUserBalanceByIdInsert(cxInfoError.getUser_id(), cxInfoError.getCharge_cash());
				insertErrorChargeInfo(cxInfoError.getUsername(),orderNo,cxInfoError.getCharge_acct(), cxInfoError.getCharge_cash(), Integer.valueOf(status));// 增加商户充值成功记录
			}
			if(!StringUtil.isEmpty(cxInfoError.getRet_url())){
				
				String reponse = retUrl(cxInfoError.getRet_url(),cxInfoError.getUsername(),orderNo,cxInfoError.getCharge_cash(),status, voucher);
			
				logger.info("A4回调下游返回="+reponse);
			}
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "OK";
		
	}
	
	// 代理商充值接口
		@ResponseBody
		@RequestMapping(value = "/a3", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
		public String a3(@RequestBody String body) {
			logger.info("第三家运营商回调接口="+body);
			/*
			 * orderid=FL1904220133489  ------------ 0
			 * &sporderid=1555921197159 ------------1
			 * &userid=10001998  ------------2
			 * &merchantsubmittime=2019042216183 ------------3
			 * &resultno=9 ------------ 4
			 * &parvalue=10 ------------5
			 * &remark1= ------------6
			 * &fundbalance=9.58   ------------  7
			 * &mobile=13510181600       ------------ 8
			 * &sign=865CD4A150ED1F9F1BF954033B448F17   ------------9
			 * */
			String[] info =body.split("&");
			logger.info("A3回调充值状态="+info[4].split("=")[1]);
			ReturnSuccessInfo returnInfo = fenceService.getReturnInfoByOrderId(info[1].split("=")[1]);
			if(returnInfo!=null){
				return "OK";
			}
			
			CxInfo cxInfoError = fenceService.getCharge3ErrorInfo(info[1].split("=")[1]);
			if(cxInfoError!=null){
				fenceService.insertReturnSuccessfulInfo(info[1].split("=")[1],cxInfoError.getUser_id());
				
				if("1".equals(info[4].split("=")[1])){
					//updateUserBalanceById(cxInfoError.getUser_id(), cxInfoError.getCharge_cash());
					insertSuccessInfo(cxInfoError.getUsername(), info[1].split("=")[1], info[8].split("=")[1], Integer.valueOf(info[5].split("=")[1]), 1);// 增加商户充值成功记录
					//updateUserBalanceById(arr[3], chargeCash);
				}else{
					updateUserBalanceByIdInsert(cxInfoError.getUser_id(), cxInfoError.getCharge_cash());
					insertErrorChargeInfo(cxInfoError.getUsername(), info[1].split("=")[1], info[8].split("=")[1], Integer.valueOf(info[5].split("=")[1]), Integer.valueOf(info[4].split("=")[1]));// 增加商户充值成功记录
				}
				
				
				if(!StringUtil.isEmpty(cxInfoError.getRet_url())){
					
					String reponse = retUrl(cxInfoError.getRet_url(),cxInfoError.getUsername(),info[1].split("=")[1],cxInfoError.getCharge_cash(),info[4].split("=")[1],info[0].split("=")[1]);
					logger.info("A3回调下游返回="+reponse);
					/*StringBuffer sb= new StringBuffer(cxInfoError.getRet_url());
					sb.append("Action=CX&AgentAccount=").append(cxInfoError.getUsername()).append("&Orderid=").append(info[1].split("=")[1]).append("&Orderstatu_int=").append(info[4].split("=")[1])
					.append("&OrderPayment=").append(cxInfoError.getCharge_cash()).append("&Errorcode=").append(info[4].split("=")[1]);
					logger.info("第三家公司回调url="+sb.toString());
					sendGet(sb.toString());*/
				}
			}
			
			return "OK";
			
		}


	/*
	 * 第二家运营商的回调接口*/
	@ResponseBody
	@RequestMapping("/returl")
	public String returl1(@RequestParam String Action,@RequestParam String AgentAccount,
			@RequestParam String Agentbalance,	@RequestParam String Orderid,
			@RequestParam String Chargeid,	@RequestParam Integer Orderstatu_int,
			@RequestParam String Orderstatu_text,	@RequestParam String OrderPayment,
			@RequestParam String Errorcode,	@RequestParam String Errormsg,
			@RequestParam String Sign
			) {
		
		System.out.println(Action);
		System.out.println(AgentAccount);
		System.out.println(Agentbalance);
		System.out.println(Orderid);
		System.out.println(Chargeid);
		System.out.println(Orderstatu_int);
		System.out.println(Orderstatu_text);
		System.out.println(OrderPayment);
		System.out.println(Errorcode);
		System.out.println(Errormsg);
		System.out.println(Sign);
		/*Action=CX&AgentAccount=api_test&
		 * Agentbalance=98981.00&Orderid=SH2009_05150001&
		 * Chargeid=2893131209&Orderstatu_int=16&
		 * Orderstatu_text=缴费成功&OrderPayment=3.00&
		 * Errorcode=0000&Errormsg=&Sign=59976d41950c16007e35a2886203564f
		*/
			return "OK";
	}
	
	
	/*
	 * 第一家运营商的回调接口*/
	@ResponseBody
	@RequestMapping("/getReturl")
	public String returl2(@RequestParam String userId,@RequestParam String bizId,
			@RequestParam String ejId,	@RequestParam String downstreamSerialno,
			@RequestParam Integer status,	@RequestParam String sign,@RequestParam(value = "voucher", required = false) String voucher) {
		logger.info("第一家公司回调订单="+downstreamSerialno+";状态="+status);
		
		
		ReturnSuccessInfo returnInfo = fenceService.getReturnInfoByOrderId(downstreamSerialno);
		if(returnInfo!=null){
			return "success";
		}
		
		CxInfo cxInfoError = fenceService.getCharge1ErrorInfo(downstreamSerialno);
		if(cxInfoError != null){
			fenceService.insertReturnSuccessfulInfo(downstreamSerialno,cxInfoError.getUser_id());
			
			if(status==2){
				insertSuccessInfo(cxInfoError.getUsername(), ejId, cxInfoError.getCharge_acct(),  cxInfoError.getCharge_cash(), 1);// 增加商户充值成功记录
			}else{
				updateUserBalanceByIdInsert(cxInfoError.getUser_id(), cxInfoError.getCharge_cash());
				insertErrorChargeInfo(cxInfoError.getUsername(), ejId, cxInfoError.getCharge_acct(),  cxInfoError.getCharge_cash(), status);// 增加商户充值成功记录
			}
			if(!StringUtil.isEmpty(cxInfoError.getRet_url())){
				if(!StringUtil.isEmpty(voucher)){
					logger.info("第一家公司回调订单="+downstreamSerialno+";状态="+status+";流水号="+voucher);
					String reponse = retUrl(cxInfoError.getRet_url(),cxInfoError.getUsername(),downstreamSerialno,cxInfoError.getCharge_cash(),status+"",voucher);
					logger.info("A1回调下游返回="+reponse);
				}else{
					String reponse = retUrl(cxInfoError.getRet_url(),cxInfoError.getUsername(),downstreamSerialno,cxInfoError.getCharge_cash(),status+"","0");
					logger.info("A1回调下游返回="+reponse);
				}
				
				
				/*StringBuffer sb= new StringBuffer(cxInfoError.getRet_url());
				sb.append("Action=CX&AgentAccount=").append(cxInfoError.getUsername()).append("&Orderid=").append(downstreamSerialno).append("&Orderstatu_int=").append(status)
				.append("&OrderPayment=").append(cxInfoError.getCharge_cash()).append("&Errorcode=").append(status);
				logger.info("第一家公司回调url="+sb.toString());
				sendGet(sb.toString());*/
			}
		}
		/*
		 * http://xxxxxxx/xxx?userId=1&bizId=200&ejId=123188&downstreamSerialno=123188&status=2&sign=65b015dc3945c4cb677e0d39cea17237
		 * */
		
			return "success";
	}
	
	
	/*
	 * 第二家运营商的回调接口*/
	@ResponseBody
	@RequestMapping("/retBak")
	public String retBak(@RequestParam String userId,@RequestParam String bizId,
			@RequestParam String ejId,	@RequestParam String downstreamSerialno,
			@RequestParam Integer status,	@RequestParam String sign, @RequestParam(value = "voucher", required = false) String voucher) {
		logger.info("第二家公司回调订单="+downstreamSerialno+";状态="+status);
		
		ReturnSuccessInfo returnInfo = fenceService.getReturnInfoByOrderId(downstreamSerialno);
		if(returnInfo!=null){
			return "success";
		}
		
		CxInfo cxInfoError = fenceService.getCharge2ErrorInfo(downstreamSerialno);
		if(cxInfoError != null){
			fenceService.insertReturnSuccessfulInfo(downstreamSerialno,cxInfoError.getUser_id());
			if(status==2){
				insertSuccessInfo(cxInfoError.getUsername(), ejId, cxInfoError.getCharge_acct(),  cxInfoError.getCharge_cash(), 1);// 增加商户充值成功记录
			}else{
				updateUserBalanceByIdInsert(cxInfoError.getUser_id(), cxInfoError.getCharge_cash());
				insertErrorChargeInfo(cxInfoError.getUsername(), ejId, cxInfoError.getCharge_acct(),  cxInfoError.getCharge_cash(), status);// 增加商户充值成功记录
			}
			
			
			/*
			 * http://www.baidu.com?Action=CX&AgentAccount=api_test&Orderid=SH2009_05150001&Orderstatu_int=16&OrderPayment=3.00&Errorcode=1
			 * */
			if(!StringUtil.isEmpty(cxInfoError.getRet_url())){
				if(!StringUtil.isEmpty(voucher)){
					logger.info("第二家公司回调订单="+downstreamSerialno+";状态="+status+";流水号="+voucher);
					String reponse = retUrl(cxInfoError.getRet_url(),cxInfoError.getUsername(),downstreamSerialno,cxInfoError.getCharge_cash(),status+"",voucher);
				logger.info("A2回调下游成功返回="+reponse);
				}else{
					String reponse = retUrl(cxInfoError.getRet_url(),cxInfoError.getUsername(),downstreamSerialno,cxInfoError.getCharge_cash(),status+"","0");
				logger.info("A2回调下游返回="+reponse);
				
				}
			}
		}
		
		
		
		
			return "success";
	}
	
	@RequestMapping("/ok/{link}")
	public String test(@PathVariable String link,  @RequestParam String sign, @RequestParam String Action, ModelMap model) {
		logger.info(sign);
		logger.info(Action);
		Map<String, Object> stepDataMap = new HashMap<>();
			stepDataMap.put("step",link);
			model.put("stepDataMap", stepDataMap);
			return "ok";
	}
	
	

	@ResponseBody
	@RequestMapping(value = "/data/share/getlink", method = RequestMethod.POST)
	public HttpBaseDto getDataShareLink(String token) throws Exception {
		Long user_id = checkTokenAndUser(token);
		// 快照数据
		Map<String, Object> dataMap = new HashMap<>();
		// 血压
		HeartPressure heartPressure = heartPressureService.getLatest(user_id);
		if (heartPressure != null) {
			Map<String, Object> heartPressureDataMap = new HashMap<>();
			heartPressureDataMap.put("maxHeartPressure", heartPressure.getMax_heart_pressure());
			heartPressureDataMap.put("minHeartPressure", heartPressure.getMin_heart_pressure());
			List<Map<String, Object>> hpStatus = Utils.checkHeartPressure(heartPressure.getMax_heart_pressure(),
					heartPressure.getMin_heart_pressure());
			String maxStatus = "正常";
			String minStatus = "正常";
			for (Map<String, Object> statusMap : hpStatus) {
				int type = Integer.valueOf(String.valueOf(statusMap.get("type")));
				if (type == 1) {
					maxStatus = String.valueOf(statusMap.get("msg"));
				} else if (type == 2) {
					minStatus = String.valueOf(statusMap.get("msg"));
				}
			}
			heartPressureDataMap.put("maxStatus", maxStatus);
			heartPressureDataMap.put("minStatus", minStatus);
			heartPressureDataMap.put("timestamp", Utils.format14DateString(heartPressure.getUpload_time().getTime()));
			dataMap.put("heartPressure", heartPressureDataMap);
		}

		// 脉搏
		HeartRate heartRate = heartRateService.getLatest(user_id);
		if (heartRate != null) {
			Map<String, Object> heartRateDataMap = new HashMap<>();
			Map<String, Object> statusMap = Utils.checkHeartRate(heartRate.getHeart_rate());
			heartRateDataMap.put("heartRate", heartRate.getHeart_rate());
			heartRateDataMap.put("status", String.valueOf(statusMap.get("msg")));
			heartRateDataMap.put("timestamp", Utils.format14DateString(heartRate.getUpload_time().getTime()));
			dataMap.put("heartRate", heartRateDataMap);
		}

		// 步数
		Step step = stepService.getLatest(user_id);
		if (step != null) {
			Map<String, Object> stepDataMap = new HashMap<>();
			stepDataMap.put("step", step.getStep_number());
			dataMap.put("step", stepDataMap);
		}
		
		//血糖
		BloodSugar bloodSugar = bloodSugarService.getLatest(user_id);
		if (bloodSugar != null) {
			Map<String, Object> bloodSugarDataMap = new HashMap<>();
			bloodSugarDataMap.put("bloodSugar", bloodSugar.getBlood_sugar());
			bloodSugarDataMap.put("timestamp", Utils.format14DateString(bloodSugar.getUpload_time().getTime()));
			dataMap.put("bloodSugar", bloodSugarDataMap);
		}
		//血氧
		BloodOxygen bloodOxygen = bloodOxygenService.getLatest(user_id);
		if (bloodOxygen != null) {
			Map<String, Object> bloodOxygenDataMap = new HashMap<>();
			bloodOxygenDataMap.put("bloodOxygen", bloodOxygen.getBlood_oxygen());
			bloodOxygenDataMap.put("pluseRate", bloodOxygen.getPulse_rate());
			bloodOxygenDataMap.put("timestamp", Utils.format14DateString(bloodOxygen.getUpload_time().getTime()));
			dataMap.put("bloodOxygen", bloodOxygenDataMap);
		}
		
		dataMap.put("timestamp", Utils.format14DateString(new Date()));
		String link = JSON.toJSONString(dataMap);
		String sign = Utils.getMD5(Utils.MD5_MAGIC + link);
		String formatedLink = Base64.encodeBase64URLSafeString(link.getBytes("UTF-8"));
		String viewUrl = "/status/data/share/view/" + formatedLink + "?sign=" + sign;
		Map<String, Object> respMap = new HashMap<>();
		respMap.put("viewUrl", viewUrl);
		HttpBaseDto dto = new HttpBaseDto();
		dto.setData(respMap);
		return dto;
	}

	@RequestMapping("/data/share/view/{link}")
	public String dataShare(@PathVariable String link, @RequestParam String sign, ModelMap model) {
		// ½âÃÜlink
		String oriLink = new String(Base64.decodeBase64(link));
		String sign2 = Utils.getMD5(Utils.MD5_MAGIC + oriLink);
		if (sign.equals(sign2)) {
			JSONObject jsonObject = (JSONObject) JSON.parse(oriLink);
			// 血压
			JSONObject heartPressureJsonObj = jsonObject.getJSONObject("heartPressure");
			Map<String, Object> heartPressureDataMap = new HashMap<>();
			if (heartPressureJsonObj != null) {
				heartPressureDataMap.put("maxHeartPressure", heartPressureJsonObj.getString("maxHeartPressure"));
				heartPressureDataMap.put("minHeartPressure", heartPressureJsonObj.getString("minHeartPressure"));
				heartPressureDataMap.put("maxStatus", heartPressureJsonObj.getString("maxStatus"));
				heartPressureDataMap.put("minStatus", heartPressureJsonObj.getString("minStatus"));
				heartPressureDataMap.put("timestamp", heartPressureJsonObj.getString("timestamp"));
			}

			// 脉搏
			JSONObject heartRateJsonObj = jsonObject.getJSONObject("heartRate");
			Map<String, Object> heartRateDataMap = new HashMap<>();
			if (heartRateJsonObj != null) {
				heartRateDataMap.put("heartRate", heartRateJsonObj.getString("heartRate"));
				heartRateDataMap.put("status", heartRateJsonObj.getString("status"));
				heartRateDataMap.put("timestamp", heartRateJsonObj.getString("timestamp"));
			}

			// 步数
			JSONObject stepJsonObj = jsonObject.getJSONObject("step");
			Map<String, Object> stepDataMap = new HashMap<>();
			if (stepJsonObj != null) {
				stepDataMap.put("step", stepJsonObj.getString("step"));
			}
			
			// 血糖
			JSONObject bloodSugarJsonObj = jsonObject.getJSONObject("bloodSugar");
			Map<String, Object> bloodSugarDataMap = new HashMap<>();
			if (bloodSugarJsonObj != null) {
				bloodSugarDataMap.put("bloodSugar", bloodSugarJsonObj.getString("bloodSugar"));
				bloodSugarDataMap.put("timestamp",bloodSugarJsonObj.getString("timestamp"));
			}
			
			//血氧
			JSONObject bloodOxygenJsonObj = jsonObject.getJSONObject("bloodOxygen");
			Map<String, Object> bloodOxygenDataMap = new HashMap<>();
			if (bloodOxygenJsonObj != null) {
			    bloodOxygenDataMap.put("bloodOxygen", bloodOxygenJsonObj.getString("bloodOxygen"));
				bloodOxygenDataMap.put("pluseRate", bloodOxygenJsonObj.getString("pluseRate"));
				bloodOxygenDataMap.put("timestamp",bloodOxygenJsonObj.getString("timestamp"));
			}

			// 时间
			String timestamp = jsonObject.getString("timestamp");

			model.put("heartPressureDataMap", heartPressureDataMap);
			model.put("heartRateDataMap", heartRateDataMap);
			model.put("stepDataMap", stepDataMap);
			model.put("bloodSugarDataMap", bloodSugarDataMap);
			model.put("bloodOxygenDataMap", bloodOxygenDataMap);
			model.put("timestamp", timestamp);
			return "dataShare";
		}
		return "warn";
	}
	
	

	// http://121.196.232.11:8084/bracelet-server/status/data/share/view/eyJzdGVwIjp7InN0ZXAiOjIxNn0sInRpbWVzdGFtcCI6IjIwMTctMTItMjAgMTI6MDQ6NTcifQ?sign=3E18FF528DF15BE2D556D014E2089C00

}
