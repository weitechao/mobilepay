package com.bracelet;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.bracelet.util.SmsUtil;

/**
 * Created on 17/6/7.
 * 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可)
 * 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dysmsapi.jar
 * <p>
 * 备注:Demo工程编码采用UTF-8
 * 国际短信发送请勿参照此DEMO
 */
public class SmsTest {

    public static void main(String[] args) throws ClientException, InterruptedException {
        //发短信
        String timeStr = "2017-09-25 10:20:30";
        // authcode
        SendSmsResponse response = SmsUtil.sendSms("验证码", "15210136945", "SMS_98965016", "{\"number\":\"123412\"}");
        // sos
        // SendSmsResponse response = SmsUtil.sendSms("15210136945", "SMS_99390009", "{\"number\":\"" + timeStr + "\"}");
        // 电子围栏
        // SendSmsResponse response = SmsUtil.sendSms("15210136945", "SMS_99420011", "{\"type\":\"" + "进入" + "\", \"time\":\"" + timeStr + "\"}");
        // SendSmsResponse response = SmsUtil.sendSms("15210136945", "SMS_99420011", "{\"type\":\"" + "离开" + "\", \"time\":\"" + timeStr + "\"}");

        Thread.sleep(3000L);

        //查明细
        if (response.getCode() != null && response.getCode().equals("OK")) {}

    }
}
