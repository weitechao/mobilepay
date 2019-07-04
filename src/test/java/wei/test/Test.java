package wei.test;

import java.math.BigDecimal;

public class Test {
	public static void main(String[] args) {
		BigDecimal money = new BigDecimal("100")
				.multiply(new BigDecimal("20")
						.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN))
				.setScale(2, BigDecimal.ROUND_DOWN);
		
		/*money=new BigDecimal(money+"")
		.multiply(new BigDecimal("20")
				.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN))
		.setScale(2, BigDecimal.ROUND_DOWN);*/
		System.out.println(money);
		
		/*	
		System.out.println(money);*/
	/*	int payType =4;
		
		int goldType = 1;
		if(payType == 2 || payType == 3){
			goldType = 2;
		}
		System.out.println(goldType);*/
		
		/*int a= 100;
		System.out.println(a*87/100);*/
	}

}
