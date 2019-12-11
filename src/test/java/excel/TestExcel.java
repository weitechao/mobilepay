package excel;

import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bracelet.service.IStepService;
import com.bracelet.util.DbOperationUtils;
import com.bracelet.util.Utils;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})*/
public class TestExcel {
	
	

	
public static void main(String[] args) {
	
	JdbcTemplate jtdb = DbOperationUtils.getJdbcTemplate()   ;
	  
	 jtdb.update("insert into step (user_id, imei, step_number, createtime) values (?,?,?,?)",
				new Object[] { 1, "213", 1, Utils.getCurrentTimestamp() },
				new int[] { Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.TIMESTAMP });
	  
	  System.out.println(1);
//    standService.paginationByCondition(new StandQC());
//    standService.findById("1");
      

	
/*	ExcelReader reader = ExcelUtil.getReader("d:/20191203192209.xlsx");
	List<List<Object>> readAll = reader.read();
	System.out.println(readAll.get(0).get(0).toString());
	System.out.println(readAll.get(0).get(1).toString());
	System.out.println(readAll.get(0).get(2).toString());
	System.out.println(readAll.get(0).get(2).toString());
	System.out.println(readAll.get(0).get(3).toString());
	System.out.println(readAll.get(0).get(4).toString());
	System.out.println(readAll.get(0).get(5).toString());
	System.out.println(readAll.get(0).get(6).toString());
	System.out.println(readAll.get(1).get(0).toString());
	System.out.println(readAll.get(2).get(0).toString());
	System.out.println(readAll.get(3).get(0).toString());
	System.out.println(readAll.get(4).get(0).toString());
	System.out.println(readAll.get(5).get(0).toString());
	System.out.println(readAll.get(6).get(0).toString());
	System.out.println(readAll.get(7).get(0).toString());
	System.out.println(readAll.get(8).get(0).toString());
	System.out.println(readAll.get(9).get(0).toString());
	System.out.println(readAll.get(10).get(0).toString());
	System.out.println(readAll.get(11).get(0).toString());
	System.out.println(readAll.get(12).get(0).toString());
	System.out.println("*********************************************>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	System.out.println(readAll.get(217).get(0).toString());
	System.out.println(readAll.get(218).get(0).toString());
	System.out.println(readAll.get(219).get(0).toString());*/
	
	ExcelReader reader = ExcelUtil.getReader("d:/20191203192209.xlsx");
	List<Map<String,Object>> readAll = reader.readAll();
	Map<String,Object> map =readAll.get(0);
	
	System.out.println(readAll.get(0).get("货品编码"));
	System.out.println(readAll.get(1).get("货品编码"));
	System.out.println(readAll.get(2).get("货品编码"));
	System.out.println(readAll.get(0).get("货品名称"));
	System.out.println("*********************************************>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	System.out.println(readAll.get(218).get("货品编码"));
	System.out.println(readAll.get(219).get("货品编码"));
	System.out.println(readAll.get(220).get("货品编码"));
	
}


}
