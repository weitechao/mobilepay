package com.bracelet.service.impl;

import com.bracelet.datasource.DataSourceChange;
import com.bracelet.entity.CxInfo;
import com.bracelet.entity.Fence;
import com.bracelet.entity.OddShape;
import com.bracelet.entity.ReturnSuccessInfo;
import com.bracelet.entity.UserInfo;
import com.bracelet.service.IFenceService;
import com.bracelet.service.IFencelogService;
import com.bracelet.service.IUserInfoService;
import com.bracelet.util.SmsUtil;
import com.bracelet.util.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

@Service
public class FenceServiceImpl implements IFenceService {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	IUserInfoService userInfoService;
	@Autowired
	IFencelogService fencelogService;


	@Override
	public boolean insertChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash) {
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into business_recharge_record_info (username, order_id, charge_acct, charge_cash, createtime) values (?,?,?,?,?)",
				new Object[] { userName, orderId, chargeAcct, chargeCash, now },
				new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.TIMESTAMP});
		return i == 1;
	}

	@Override
	public boolean insertPreviousLevelChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode) {
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into business_recharge_cx_info (username, order_id, charge_acct, charge_cash, createtime,error_code) values (?,?,?,?,?,?)",
				new Object[] { userName, orderId, chargeAcct, chargeCash, now,errorCode },
				new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.TIMESTAMP, Types.INTEGER});
		return i == 1;
	}

	@Override
	public boolean insertPreviousLevelErrorChargeInfo(String userName, String orderId, String chargeAcct,
			Integer chargeCash, Integer errorCode) {
		logger.info("insertPreviousLevelErrorChargeInfo  orderid="+orderId);
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into business_error_cx_info (username, order_id, charge_acct, charge_cash, createtime,error_code) values (?,?,?,?,?,?)",
				new Object[] { userName, orderId, chargeAcct, chargeCash, now,errorCode },
				new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.TIMESTAMP, Types.INTEGER});
		return i == 1;
	}

	@Override
	public CxInfo getChargeSuccessInfo(String userName, String orderId) {
		String sql = "select * from business_recharge_cx_info where order_id=? and username=? LIMIT 1";
		List<CxInfo> list = jdbcTemplate.query(sql, new Object[] { orderId,userName }, new BeanPropertyRowMapper<CxInfo>(CxInfo.class));

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("get return null.user_id:" + orderId);
		}
		return null;
	}

	@Override
	public CxInfo getChargeErrorInfo(String userName, String orderId) {
		String sql = "select * from business_error_cx_info where order_id=? and username=? LIMIT 1";
		List<CxInfo> list = jdbcTemplate.query(sql, new Object[] { orderId,userName }, new BeanPropertyRowMapper<CxInfo>(CxInfo.class));

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("get return null.user_id:" + orderId);
		}
		return null;
	}

	@Override
	public boolean insert3ErrorChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode,Integer id, String retUrl) {
		logger.info("insert3ErrorChargeInfo  orderid="+orderId);
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into business_error3_cx_info (username, order_id, charge_acct, charge_cash, createtime,error_code,user_id, ret_url) values (?,?,?,?,?,?,?,?)",
				new Object[] { userName, orderId, chargeAcct, chargeCash, now,errorCode,id,retUrl },
				new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.TIMESTAMP, Types.INTEGER, Types.INTEGER, Types.VARCHAR});
		return i == 1;
	}

	@Override
	public CxInfo getCharge3ErrorInfo(String orderId) {
		String sql = "select * from business_error3_cx_info where order_id=?  LIMIT 1";
		List<CxInfo> list = jdbcTemplate.query(sql, new Object[] { orderId}, new BeanPropertyRowMapper<CxInfo>(CxInfo.class));

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("get return null.user_id:" + orderId);
		}
		return null;
	}

	@Override
	public boolean insert1ErrorChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode, Integer id, String retUrl) {
		logger.info("insert1ErrorChargeInfo  orderid="+orderId);
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into business_error1_cx_info (username, order_id, charge_acct, charge_cash, createtime,error_code,user_id, ret_url) values (?,?,?,?,?,?,?,?)",
				new Object[] { userName, orderId, chargeAcct, chargeCash, now,errorCode,id,retUrl },
				new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.TIMESTAMP, Types.INTEGER, Types.INTEGER, Types.VARCHAR});
		return i == 1;
	}

	@Override
	public CxInfo getCharge1ErrorInfo(String ejId) {
		String sql = "select * from business_error1_cx_info where order_id=?  LIMIT 1";
		List<CxInfo> list = jdbcTemplate.query(sql, new Object[] { ejId}, new BeanPropertyRowMapper<CxInfo>(CxInfo.class));

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("getCharge1ErrorInfo return null.user_id:" + ejId);
		}
		return null;
	}

	@Override
	public boolean insert2ErrorChargeInfo(String userName, String orderId, String chargeAcct, Integer chargeCash,
			Integer errorCode, Integer id, String retUrl) {
		logger.info("insert2ErrorChargeInfo  orderid="+orderId);
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into business_error2_cx_info (username, order_id, charge_acct, charge_cash, createtime,error_code,user_id, ret_url) values (?,?,?,?,?,?,?,?)",
				new Object[] { userName, orderId, chargeAcct, chargeCash, now,errorCode,id,retUrl },
				new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.TIMESTAMP, Types.INTEGER, Types.INTEGER, Types.VARCHAR});
		return i == 1;
	}

	@Override
	public CxInfo getCharge2ErrorInfo(String ejId) {
		String sql = "select * from business_error2_cx_info where order_id=?  LIMIT 1";
		List<CxInfo> list = jdbcTemplate.query(sql, new Object[] { ejId}, new BeanPropertyRowMapper<CxInfo>(CxInfo.class));

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("business_error2_cx_info return null.user_id:" + ejId);
		}
		return null;
	}

	@Override
	public ReturnSuccessInfo getReturnInfoByOrderId(String orderId) {
		String sql = "select id,order_id,createtime,user_id from return_success_info where order_id=?  LIMIT 1";
		List<ReturnSuccessInfo> list = jdbcTemplate.query(sql, new Object[] { orderId}, new BeanPropertyRowMapper<ReturnSuccessInfo>(ReturnSuccessInfo.class));

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("getReturnInfoByOrderId return null.user_id:" + orderId);
		}
		return null;
	}

	@Override
	public boolean insertReturnSuccessfulInfo(String orderId, Integer user_id) {
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into return_success_info (order_id, user_id, createtime) values (?,?,?)",
				new Object[] { orderId, user_id, now },
				new int[] { Types.VARCHAR, Types.INTEGER, Types.TIMESTAMP});
		return i == 1;
	}

}
