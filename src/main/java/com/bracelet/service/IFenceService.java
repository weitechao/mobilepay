package com.bracelet.service;

import java.util.List;

import com.bracelet.datasource.DataSourceChange;
import com.bracelet.entity.CxInfo;
import com.bracelet.entity.Fence;
import com.bracelet.entity.OddShape;

public interface IFenceService {

	boolean insertChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash);

	boolean insertPreviousLevelChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode);

	boolean insertPreviousLevelErrorChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode);

	CxInfo getChargeSuccessInfo(String userName, String orderId);

	CxInfo getChargeErrorInfo(String userName, String orderId);

	boolean insert3ErrorChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode,Integer id);

	CxInfo getCharge3ErrorInfo(String string);

}
