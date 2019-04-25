package com.bracelet.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bracelet.entity.CxInfo;
import com.bracelet.service.IPushlogService;
import com.bracelet.service.IStepService;
import com.bracelet.service.IUserInfoService;
import com.bracelet.util.A1;
import com.bracelet.util.A1_1;
import com.bracelet.util.A2;
import com.bracelet.util.A3;
import com.bracelet.util.Des;
import com.bracelet.util.HttpClientGet;
import com.bracelet.util.StringUtil;
import com.bracelet.util.Utils;

@Controller
@RequestMapping("/agent")
public class AgentPhone extends BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	IUserInfoService userInfoService;

	@Autowired
	IStepService stepService;

	@Autowired
	IPushlogService phoneService;

	// 代理商充值接口
	@ResponseBody
	@RequestMapping(value = "/recharge", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String recharge(@RequestBody String body) {

		JSONObject bb = new JSONObject();
		try {
			// String requestString = Des.decrypt(body);// 解密
			logger.info("充值接口解密后的数据为=" + body);
			JSONObject jsonObject = (JSONObject) JSON.parse(body);
			String userName = jsonObject.getString("username"); // 商户号
			String scret = jsonObject.getString("scret"); // 商户号
			Map<String, Object> result = checkUserTrue(userName);// 使用状态 ，余额。
																	// 匹配的上游id，用户id
			/* use_status blance id use_id scret */
			if (Integer.valueOf(result.get("use_id") + "") == 0) {
				bb.put("code", 2);
				return bb.toString();
			}
			if (Integer.valueOf(result.get("use_status") + "") != 1) {
				bb.put("code", 0);
				return bb.toString();
			}
			if (!scret.equals(result.get("scret") + "")) {
				bb.put("code", 500);
				return bb.toString();
			}
			Integer chargeCash = jsonObject.getIntValue("chargeCash");// 充值金额必须以元为单位
			if (Integer.valueOf(result.get("blance") + "") < chargeCash) {
				bb.put("code", 3);
				return bb.toString();
			}
			if (Integer.valueOf(result.get("id") + "") == 0) {
				bb.put("code", 4);
				return bb.toString();
			}
			// 这里匹配的url秘钥什么的直接写死在查询消耗性能 id从1开始
			updateUserBalanceById(Integer.valueOf(result.get("use_id") + ""), chargeCash);
			
			
			Integer useInterface = Integer.valueOf(result.get("id") + "");
			 String bScret = getScretKeyById(useInterface);
			 logger.info("商户="+useInterface+"的秘钥bScret="+bScret); 
			
			String orderId = jsonObject.getString("orderId"); // 订单号必须唯一由商户自己生成
			String chargeAcct = jsonObject.getString("chargeAcct"); // 充值账号chargeCash
			if (useInterface == 1) {// 这是匹配第一个文档接口
				String dtCreate = jsonObject.getString("dtCreate");// yyyyMMddHHmmss
				String itemId = jsonObject.getString("itemId");// 商品编号
				String reponse = A1_1.chongZhi(dtCreate, orderId, chargeAcct, chargeCash, itemId,bScret);
				JSONObject reponseJsonObject = (JSONObject) JSON.parse(reponse);
				Integer errorCode = reponseJsonObject.getInteger("code"); // 1表示成功
				if (errorCode == 0) {

					bb.put("code", 1);
					insertSuccessInfo(userName, orderId, chargeAcct, chargeCash, errorCode);// 增加商户充值成功记录
				} else {
					bb.put("code", errorCode);
					insert1ErrorChargeInfo(userName, orderId, chargeAcct, chargeCash, errorCode,Integer.valueOf(result.get("use_id") + ""));// 增加商户充值成功记录
				}
			} else if (useInterface == 2) {
				String dtCreate = jsonObject.getString("dtCreate");// yyyyMMddHHmmss
				String itemId = jsonObject.getString("itemId");// 商品编号
				String reponse = A2.chongZhi(dtCreate, orderId, chargeAcct, chargeCash, itemId, bScret);
				JSONObject reponseJsonObject = (JSONObject) JSON.parse(reponse);
				Integer errorCode = reponseJsonObject.getInteger("code");
				if (errorCode == 0) {

					bb.put("code", 1);
					//insertSuccessInfo(userName, orderId, chargeAcct, chargeCash, 1);// 增加商户充值成功记录
				} else {
					bb.put("code", errorCode);
					insert2ErrorChargeInfo(userName, orderId, chargeAcct, chargeCash, errorCode,Integer.valueOf(result.get("use_id") + ""));// 增加商户充值成功记录
				}
			} else if (useInterface == 3) {

				String itemId = jsonObject.getString("itemId");// 商品编号
				String reponse = A3.chongZhi(Utils.getYyyyMMdd(), orderId, chargeAcct, chargeCash, itemId,bScret);

				JSONObject reponseJsonObject = (JSONObject) JSON.parse(reponse);
				String order = reponseJsonObject.getString("order");
				JSONObject OrderJson = (JSONObject) JSON.parse(order);
				String resultno = OrderJson.getString("resultno");

				if ("1".equals(resultno)) {
					insertSuccessInfo(userName, orderId, chargeAcct, chargeCash, 1);// 增加商户充值成功记录
					bb.put("code", 1);
				} else {
					insert3ErrorChargeInfo(userName, orderId, chargeAcct, chargeCash, Integer.valueOf(resultno),
							Integer.valueOf(result.get("use_id") + ""));// 增加商户充值成功记录
					bb.put("code", 7);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bb.toString();
	}

	// 代理商订单查询接口
	@ResponseBody
	@RequestMapping(value = "/orderIdSelect", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String orderIdSelect(@RequestBody String body) {
		JSONObject bb = new JSONObject();
		try {
			// String requestString = Des.decrypt(body);// 解密
			JSONObject jsonObject = (JSONObject) JSON.parse(body);
			String userName = jsonObject.getString("username"); // 商户号
			String scret = jsonObject.getString("scret"); // 商户号
			Map<String, Object> result = checkUserTrue(userName);// 使用状态 ，余额。
																	// 匹配的上游id，用户id
			/* use_status blance id use_id scret */
			if (Integer.valueOf(result.get("use_status") + "") != 1) {
				bb.put("code", 0);
				return bb.toString();
			}
			if (!scret.equals(result.get("scret") + "")) {
				bb.put("code", 500);
				return bb.toString();
			}

			String orderId = jsonObject.getString("orderId"); // 订单号必须唯一由商户自己生成
			bb.put("orderId", orderId);
			CxInfo cxInfoSuccess = fenceService.getChargeSuccessInfo(userName, orderId);
			if (cxInfoSuccess != null) {
				bb.put("code", 1);

				bb.put("orderStatuInt", 16);
				// bb.put("orderStatuText", "成功");
				return bb.toString();
			}
			Integer useInterface = Integer.valueOf(result.get("id") + "");
			if (useInterface != 3) {

				CxInfo cxInfoError = fenceService.getChargeErrorInfo(userName, orderId);
				if (cxInfoError != null) {
					bb.put("code", 1);
					bb.put("orderStatuInt", cxInfoError.getError_code());
					// bb.put("orderStatuText", "");
					return bb.toString();
				}

			} else {

				CxInfo cxInfoError = fenceService.getCharge3ErrorInfo(orderId);
				if (cxInfoError != null) {
					bb.put("code", 1);
					bb.put("orderStatuInt", cxInfoError.getError_code());
					// bb.put("orderStatuText", "");
					return bb.toString();
				}

			}

			if (useInterface == 1) {
				String reponse = A1.orderIdSelect(orderId, getScretKeyById(1));
				logger.info("请求上游查询接口返回的数据=" + reponse);
				JSONObject reponseJsonObject = (JSONObject) JSON.parse(reponse);

				Integer errorCode = reponseJsonObject.getInteger("errorCode"); // 1表示成功
				if (errorCode == 1) {
					bb.put("code", 1);
					bb.put("orderStatuInt", 16);
					// bb.put("orderStatuText",
					// reponseJsonObject.getString("orderStatuText"));

					insertSuccessInfo(userName, orderId, "1", reponseJsonObject.getInteger("orderPayment"), errorCode);// 增加商户充值成功记录
				} else {
					bb.put("code", errorCode);
					bb.put("orderStatuInt", reponseJsonObject.getString("orderStatuInt"));

					// bb.put("orderStatuText",
					// reponseJsonObject.getString("orderStatuText"));

					insertErrorChargeInfo(userName, orderId, "0", 0, errorCode);// 增加商户充值成功记录

					/*
					 * stepService.insertOrderErrorInfo(userName, orderId,
					 * Integer.valueOf(reponseJsonObject.getString(
					 * "orderStatuInt")),
					 * reponseJsonObject.getString("orderStatuText"));
					 */
					// 添加查询不正常的记录
				}

			} else if (useInterface == 2) {

				String reponse = A2.chaXun(orderId , getScretKeyById(2));
				JSONObject reponseJsonObject = (JSONObject) JSON.parse(reponse);
				Integer errorCode = reponseJsonObject.getInteger("code");
				Integer amount = reponseJsonObject.getInteger("amount");
				if (errorCode == 0) {
					bb.put("code", 1);
					insertSuccessInfo(userName, orderId, "1", amount, 1);// 增加商户充值成功记录
				} else {
					bb.put("code", errorCode);
					insertSuccessInfo(userName, orderId, "1", amount, errorCode);// 增加商户充值成功记录
				}
			} else if (useInterface == 3) {
				String reponse = A3.orderIdSelect(orderId);
				bb.put("code", 1);
				bb.put("orderStatuInt", 7);
			}

		} catch (Exception e) {
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
			// String requestString = Des.decrypt(body);// 解密

			JSONObject jsonObject = (JSONObject) JSON.parse(body);
			String userName = jsonObject.getString("username"); // 商户号
			String scret = jsonObject.getString("scret"); // 商户号

			Map<String, Object> result = checkUserTrue(userName);// 使用状态 ，余额。
																	// 匹配的上游id，用户id
			/* use_status blance id use_id scret */
			if (Integer.valueOf(result.get("use_status") + "") != 1) {
				bb.put("code", 0);
				return bb.toString();
			}
			if (!scret.equals(result.get("scret") + "")) {
				bb.put("code", 500);
				return bb.toString();
			}
			bb.put("code", 1);
			bb.put("balance", result.get("blance") + "");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bb.toString();
	}

	// 代理商充值接口批充
	@ResponseBody
	@RequestMapping(value = "/batchRecharge", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String batchRecharge(@RequestBody String body) {
		JSONObject bb = new JSONObject();
		try {
			// String requestString = Des.decrypt(body);// 解密

			JSONObject jsonObject = (JSONObject) JSON.parse(body);
			String userName = jsonObject.getString("username"); // 商户号

			String scret = jsonObject.getString("scret"); // 商户号
			Map<String, Object> result = checkUserTrue(userName);// 使用状态 ，余额。
																	// 匹配的上游id，用户id
			/* use_status blance id use_id scret */
			if (Integer.valueOf(result.get("use_status") + "") != 1) {
				bb.put("code", 0);
				return bb.toString();
			}
			if (!scret.equals(result.get("scret") + "")) {
				bb.put("code", 500);
				return bb.toString();
			}
			Integer totalAmount = jsonObject.getIntValue("totalAmount");// 充值金额必须以元为单位
			if (Integer.valueOf(result.get("blance") + "") < totalAmount) {
				bb.put("code", 3);
				return bb.toString();
			}
			if (Integer.valueOf(result.get("id") + "") == 0) {
				bb.put("code", 4);
				return bb.toString();
			}

			String orderId = jsonObject.getString("orderId"); // 订单号必须唯一由商户自己生成

			String batchChargeAcct = jsonObject.getString("batchChargeAcct"); // 充值账号chargeCash
			logger.info(batchChargeAcct);
			String batchChargeCash = jsonObject.getString("batchChargeCash"); // 充值账号对应的金额
			logger.info(batchChargeCash);
			String barchItemId = jsonObject.getString("barchItemId"); // 充值账号对应的金额
			logger.info(barchItemId);
			// fenceService.insertChargeInfo(userName, orderId,
			// chargeAcct, chargeCash);//增加商户充值记录

			if (!StringUtil.isEmpty(batchChargeAcct) && !StringUtil.isEmpty(batchChargeCash)
					&& !StringUtil.isEmpty(barchItemId)) {
				String[] chargeAcct = batchChargeAcct.split(",");
				String[] chargeCash = batchChargeCash.split(",");
				String[] itemId = barchItemId.split(",");

				if (chargeAcct.length != chargeCash.length || chargeAcct.length != itemId.length) {
					bb.put("code", 6);
				} else {
					StringBuffer sb = new StringBuffer();
					StringBuffer sbOrderid = new StringBuffer();
					updateUserBalanceById(Integer.valueOf(result.get("user_id") + ""), totalAmount); // 先扣钱
					Integer useInterface = Integer.valueOf(result.get("id") + "");
					
					 String bScret = getScretKeyById(useInterface);
					 logger.info("批冲商户="+useInterface+"的秘钥bScret="+bScret); 
					 
					if (useInterface == 1) {
						for (int i = 0; i < chargeAcct.length; i++) {

							String reponse = A1.chongZhi(orderId + i, chargeAcct[i], Integer.valueOf(chargeCash[i]),bScret);
							if (sbOrderid.length() > 0) {
								sbOrderid.append(",");
							}
							sbOrderid.append(orderId + i);
							JSONObject reponseJsonObject = (JSONObject) JSON.parse(reponse);
							Integer errorCode = reponseJsonObject.getInteger("errorCode"); // 1表示成功
							if (sb.length() > 0) {
								sb.append(",");
							}
							sb.append(errorCode);
							if (errorCode == 1) {

								insertSuccessInfo(userName, orderId + i, chargeAcct[i], Integer.valueOf(chargeCash[i]),
										errorCode);// 增加商户充值成功记录
							} else {

								insert1ErrorChargeInfo(userName, orderId + i, chargeAcct[i],
										Integer.valueOf(chargeCash[i]), errorCode, Integer.valueOf(result.get("user_id") + ""));// 增加商户充值成功记录
							}
						}
					} else if (useInterface == 2) {
						String dtCreate = jsonObject.getString("dtCreate");// yyyyMMddHHmmss
						for (int i = 0; i < chargeAcct.length; i++) {
							if (sbOrderid.length() > 0) {
								sbOrderid.append(",");
							}
							sbOrderid.append(orderId + i);
							String reponse = A2.chongZhi(dtCreate, orderId, chargeAcct[i],
									Integer.valueOf(chargeCash[i]), itemId[i],bScret);
							JSONObject reponseJsonObject = (JSONObject) JSON.parse(reponse);
							Integer errorCode = reponseJsonObject.getInteger("errorCode"); // 1表示成功
							if (sb.length() > 0) {
								sb.append(",");
							}
							sb.append(errorCode);
							if (errorCode == 0) {
								bb.put("code", 1);
								insertSuccessInfo(userName, orderId + i, chargeAcct[i], Integer.valueOf(chargeCash[i]),
										errorCode);// 增加商户充值成功记录
							} else {
								bb.put("code", errorCode);
								insert2ErrorChargeInfo(userName, orderId + i, chargeAcct[i],
										Integer.valueOf(chargeCash[i]),errorCode, Integer.valueOf(result.get("user_id") + ""));// 增加商户充值成功记录
							}
						}

					} else if (useInterface == 3) {
						for (int i = 0; i < chargeAcct.length; i++) {
							if (sbOrderid.length() > 0) {
								sbOrderid.append(",");
							}
							sbOrderid.append(orderId + i);

							String reponse = A3.chongZhi(Utils.getYyyyMMdd(), orderId, chargeAcct[i] + i,
									Integer.valueOf(chargeCash[i]), itemId[i], bScret);

							JSONObject reponseJsonObject = (JSONObject) JSON.parse(reponse);
							String order = reponseJsonObject.getString("order");
							JSONObject OrderJson = (JSONObject) JSON.parse(order);
							String resultno = OrderJson.getString("resultno");
							if (sb.length() > 0) {
								sb.append(",");
							}
							sb.append(resultno);
							if ("1".equals(resultno)) {
								insertSuccessInfo(userName, orderId + i, chargeAcct[i], Integer.valueOf(chargeCash[i]),
										Integer.valueOf(resultno));// 增加商户充值成功记录
								bb.put("code", 1);
							} else {
								insert3ErrorChargeInfo(userName, orderId + i, chargeAcct[i],
										Integer.valueOf(chargeCash[i]), 7, Integer.valueOf(result.get("user_id") + ""));// 增加商户充值成功记录
								bb.put("code", 7);
							}
						}
					}
					bb.put("orderId", sbOrderid.toString());
					bb.put("resultCode", sb.toString());
					bb.put("code", 1);
				}
			} else {
				bb.put("code", 5);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bb.toString();
	}

}
