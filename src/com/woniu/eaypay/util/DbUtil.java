package com.woniu.eaypay.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbUtil {
	
	private static Logger logger=LoggerFactory.getLogger(DbUtil.class);
	private static BasicDataSource dataSource;
	
	static {//静态代码块先于getConnection()方法运行,
		try {
			//把properties文件加载进来
			Properties properties = new Properties();
			//调用类加载器,得到数据流jdbc.properties,类路径获取加载资源,即class路径
			InputStream is = DbUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
			properties.load(is);//加载流
			
			is.close();//加载后关闭
			is=null;
			
			//初始化数据源
			dataSource=new BasicDataSource();
			//设置数据库连接的属性
			//为是维护方便将用户名，密码。数据库设置到属性文件jdbc.properties中
			dataSource.setDriverClassName(properties.getProperty("jdbc.driver"));
			dataSource.setUrl(properties.getProperty("jdbc.url"));
			dataSource.setUsername(properties.getProperty("jdbc.user"));
			dataSource.setPassword(properties.getProperty("jdbc.password"));
			//数据源的 初始大小
			dataSource.setInitialSize(5);
			dataSource.setMaxActive(20);
			dataSource.setMaxIdle(60);
			dataSource.setMaxWait(6000);
			logger.debug("数据源初始化成功");
		} catch (Exception e) {
			logger.debug("数据源初始化失败");
			e.printStackTrace();
		}
		
	}
	/**创建数据库连接-->获取数据库连接对象,首先需要数据源，首先去创建一个数据源new BasicDataSource();
	 * 在程序进行初始化时数据源就存在了，所以想到静态代码块,在代码块中创建数据源
	 * DbUtil只要一进行加载，我的数据源就进行初始化new BasicDataSource();
	 * 运行 getConnection()方法后，只要数据源部为空，我们就返回数据源，否则直接将它return一个null*/
	
	public static Connection getConnection(){
		try {
			if(dataSource!=null){
				return dataSource.getConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//关闭连接
	public static void closeResource(Connection connection,
			PreparedStatement statement, ResultSet resultSet) {
		try {
			if(resultSet!=null){
				resultSet.close();
			}
			if(statement!=null){
				statement.close();
			}
			if(connection!=null){
				connection.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}
	//关闭数据源
	public static void release(){
		try {
			if(dataSource!=null){
				dataSource.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
