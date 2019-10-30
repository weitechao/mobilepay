package com.bracelet.service;

public interface IAuthcodeService {

	void sendAuthCode(String tel);
	
	boolean verifyAuthCode(String tel, String code);

	void sendAuthCodeNew(String name, String tel, String orderid);

}
