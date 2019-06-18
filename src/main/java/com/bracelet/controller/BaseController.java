package com.bracelet.controller;

import com.bracelet.entity.BusinessUserInfo;
import com.bracelet.entity.CompanyInfo;
import com.bracelet.exception.BizException;
import com.bracelet.service.IDeviceService;
import com.bracelet.service.IFenceService;
import com.bracelet.service.ITokenInfoService;
import com.bracelet.service.IUserInfoService;
import com.bracelet.util.RespCode;
import com.bracelet.util.StringUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	protected ITokenInfoService tokenInfoService;

	@Autowired
	IDeviceService ideviceService;
	@Autowired
	protected IUserInfoService userInfoService;

	@Autowired
	IFenceService fenceService; // 商户充值记录


	protected Long checkTokenAndUser(String token) {
		if (StringUtils.isEmpty(token)) {
			throw new BizException(RespCode.NOTEXIST_PARAM);
		}
		Long user_id = tokenInfoService.getUserIdByToken(token);
		if (user_id == null) {
			logger.info("[checkTokenAndUser] 通过token检查userid不存在，token:" + token);
			throw new BizException(RespCode.U_NOT_EXIST);
		}
		return user_id;
	}
	
	
	protected String getScretKeyById(Integer id) {
		String companySecret="1";
		CompanyInfo company =  tokenInfoService.getScretKeyByCompanyId(id);
		if(company!=null){
			companySecret=company.getSecret_key()+"";
		}
		return companySecret;
	}

	protected Map<String, Object> checkUserTrue(String userName) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("use_status", 2);
		result.put("blance", 0);
		result.put("id", 0);
		result.put("use_id", 0);
		result.put("scret", 0);
		result.put("ret_url", "");
		
	/*	int[] arr = new int[] { 2, 0, 0, 0, 0 };// 使用状态, 余额， 匹配的上游Id, 用户id
*/		if (StringUtils.isEmpty(userName)) {
			throw new BizException(RespCode.NOTEXIST_PARAM);
		}
		BusinessUserInfo userInfo = userInfoService.getBusinessUserInfoByUsername(userName);
		if (userInfo != null) {
			logger.info("**************************用户="+userName+"回调url查询得到"+userInfo.getUrl());
			/*arr[0] = userInfo.getUse_status();
			arr[1] = userInfo.getBalance();
			arr[2] = userInfo.getShangyou_type();
			arr[3] = userInfo.getId();
			arr[4] = userInfo.getScret();*/
			result.put("use_status", userInfo.getUse_status());
			result.put("blance", userInfo.getBalance());
			result.put("id",userInfo.getShangyou_type());
			result.put("use_id", userInfo.getId());
			result.put("scret", userInfo.getScret()+"");
			result.put("ret_url", userInfo.getUrl()+"");
		}
		return result;
	}

	protected void updateUserBalanceById(Integer id, Integer balance) {
		BusinessUserInfo userInfo = userInfoService.getBusinessUserInfoById(id);
		userInfoService.updateUserBalanceById(id, userInfo.getBalance()-balance);
	}
	protected void updateUserBalanceByIdInsert(Integer id, Integer balance) {
		BusinessUserInfo userInfo = userInfoService.getBusinessUserInfoById(id);
		userInfoService.updateUserBalanceById(id, userInfo.getBalance()+balance);
	}

	public void insertSuccessInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode) {
		fenceService.insertPreviousLevelChargeInfo(userName, orderId, chargeAcct, chargeCash, errorCode);// 增加商户充值成功记录
	}

	public void insertErrorChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode) {
		fenceService.insertPreviousLevelErrorChargeInfo(userName, orderId, chargeAcct, chargeCash, errorCode);// 增加商户充值失败记录
	}
	
	public void insert4ErrorChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode,Integer id, String retUrl) {
		fenceService.insert4ErrorChargeInfo(userName, orderId, chargeAcct, chargeCash, errorCode,id, retUrl);// 增加商户充值失败记录
	}
	
	public void insert3ErrorChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode,Integer id, String retUrl) {
		fenceService.insert3ErrorChargeInfo(userName, orderId, chargeAcct, chargeCash, errorCode,id, retUrl);// 增加商户充值失败记录
	}
	public void insert1ErrorChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode,Integer id, String retUrl ) {
		fenceService.insert1ErrorChargeInfo(userName, orderId, chargeAcct, chargeCash, errorCode,id,retUrl);// 增加商户充值失败记录
	}
	
	public void insert2ErrorChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode,Integer id, String retUrl) {
		fenceService.insert2ErrorChargeInfo(userName, orderId, chargeAcct, chargeCash, errorCode,id, retUrl);// 增加商户充值失败记录
	}
	
	/*回调*/
	public static String retUrl(String url,String userName,String orderid,Integer charge_cash,String code,String sn) {
		
		
		String result = "";
		BufferedReader in = null;
		try {
			//String urlNameString = url + "?" + param;
			
			StringBuffer sb= new StringBuffer(url);
			sb.append("Action=CX&AgentAccount=").append(userName).append("&Orderid=").append(orderid).append("&Orderstatu_int=").append(code)
			.append("&OrderPayment=").append(charge_cash).append("&Errorcode=").append(code).append("&sn=").append(sn);
			
			
			URL realUrl = new URL(sb.toString());
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
	
	
	
}
