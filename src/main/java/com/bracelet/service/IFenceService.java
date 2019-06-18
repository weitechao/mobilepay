package com.bracelet.service;

import java.util.List;

import com.bracelet.datasource.DataSourceChange;
import com.bracelet.entity.CxInfo;
import com.bracelet.entity.Fence;
import com.bracelet.entity.OddShape;
import com.bracelet.entity.ReturnSuccessInfo;

public interface IFenceService {

	boolean insertChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash);

	boolean insertPreviousLevelChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode);

	boolean insertPreviousLevelErrorChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode);

	CxInfo getChargeSuccessInfo(String userName, String orderId);

	CxInfo getChargeErrorInfo(String userName, String orderId);

	boolean insert3ErrorChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode,Integer id, String retUrl);

	CxInfo getCharge3ErrorInfo(String string);

	boolean insert1ErrorChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode,Integer id, String retUrl);

	CxInfo getCharge1ErrorInfo(String ejId);

	boolean insert2ErrorChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode, Integer id, String retUrl);

	CxInfo getCharge2ErrorInfo(String ejId);

	ReturnSuccessInfo getReturnInfoByOrderId(String orderId);

	boolean insertReturnSuccessfulInfo(String orderId, Integer user_id);

	boolean insert4ErrorChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode, Integer id, String retUrl);

	CxInfo getCharge4ErrorInfo(String orderNo);

}
