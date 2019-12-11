package excel;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bracelet.service.IStepService;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

public class TestExcel {
	
	
public static void main(String[] args) {
	
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
