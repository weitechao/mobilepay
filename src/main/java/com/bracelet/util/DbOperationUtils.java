package com.bracelet.util;

import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

public class DbOperationUtils {
	/**
	 * 数据库连接需要的字符串
	 * */
	public static final String username = "root";
	public static final String password = "123456";
	public static final String jdbcUrl = "jdbc:mysql://localhost:3306/watch?useUnicode=true&characterEncoding=utf8";
	public static final String driverName = "com.mysql.jdbc.Driver";
 
	public static JdbcTemplate getJdbcTemplate() {
 
		// com.alibaba.druid.pool.DruidDataSource
		DruidDataSource dataSource = new DruidDataSource();
 
		// 设置数据源属性参数
		dataSource.setPassword(password);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(username);
		dataSource.setDriverClassName(driverName);
 
		// 获取spring的JdbcTemplate
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		// 设置数据源
		jdbcTemplate.setDataSource(dataSource);
 
		return jdbcTemplate;
	}


}
