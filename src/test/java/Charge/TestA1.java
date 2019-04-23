package Charge;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bracelet.util.Des;
import com.bracelet.util.HttpRequest;
import com.bracelet.util.Md5;

public class TestA1 {
	public static void main(String[] args) throws Exception {

		Integer chargeCash = 10;// 充值金额必须以元为单位

		String orderId = System.currentTimeMillis() + ""; // 订单号必须唯一由商户自己生成
		String chargeAcct = "18813683383"; // 充值账号chargeCash
		// fenceService.insertChargeInfo(userName, orderId, chargeAcct,
		// chargeCash);//增加商户充值记录

		JSONObject jsona = new JSONObject();
		jsona.put("username", "test777");
		jsona.put("orderId", orderId);
		jsona.put("chargeAcct", chargeAcct);
		jsona.put("chargeCash", 10);
	
		
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
		jsona.put("dtCreate", ft.format(new Date()));
		jsona.put("itemId", 20057);
		jsona.put("retUrl", "");
		String jsonaString = jsona.toString() ;
		System.out.println("原始数据:"+jsonaString);
		System.out.println("加密后的数据:"+Des.encrypt(jsonaString));
		String requestString = Des.decrypt(Des.encrypt(jsonaString));// 解密
		System.out.println("解密密后的数据:"+requestString);
		
		 SimpleDateFormat ftt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 System.out.println(ftt.format(new Date()));
		
		 UUID uuid = UUID.randomUUID();   
		  System.out.println (uuid);  
		  /*98f1e877-07bd-4799-b3b1-34a2181c6051
		   * */
		  System.out.println(uuid.toString().length());
	
	}

}
