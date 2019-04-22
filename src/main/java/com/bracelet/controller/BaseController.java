package com.bracelet.controller;

import com.bracelet.entity.BusinessUserInfo;
import com.bracelet.exception.BizException;
import com.bracelet.service.IDeviceService;
import com.bracelet.service.IFenceService;
import com.bracelet.service.ITokenInfoService;
import com.bracelet.service.IUserInfoService;
import com.bracelet.util.RespCode;
import com.bracelet.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

	@Autowired
	protected ITokenInfoService tokenInfoService;

	@Autowired
	IDeviceService ideviceService;
	@Autowired
	protected IUserInfoService userInfoService;

	@Autowired
	IFenceService fenceService; // 商户充值记录

	private Logger logger = LoggerFactory.getLogger(getClass());

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

	protected int[] checkUserTrue(String userName) {
		int[] arr = new int[] { 2, 0, 0, 0 };// 使用状态, 余额， 匹配的上游Id, 用户id
		if (StringUtils.isEmpty(userName)) {
			throw new BizException(RespCode.NOTEXIST_PARAM);
		}
		BusinessUserInfo userInfo = userInfoService.getBusinessUserInfoByUsername(userName);
		if (userInfo != null) {
			arr[0] = userInfo.getUse_status();
			arr[1] = userInfo.getBalance();
			arr[2] = userInfo.getShangyou_type();
			arr[3] = userInfo.getId();
		}
		return arr;
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
	public void insert3ErrorChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode,Integer id) {
		fenceService.insert3ErrorChargeInfo(userName, orderId, chargeAcct, chargeCash, errorCode,id);// 增加商户充值失败记录
	}
	
	
	
}
