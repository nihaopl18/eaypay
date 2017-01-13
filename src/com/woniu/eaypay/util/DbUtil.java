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
	
	static {//��̬���������getConnection()��������,
		try {
			//��properties�ļ����ؽ���
			Properties properties = new Properties();
			//�����������,�õ�������jdbc.properties,��·����ȡ������Դ,��class·��
			InputStream is = DbUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
			properties.load(is);//������
			
			is.close();//���غ�ر�
			is=null;
			
			//��ʼ������Դ
			dataSource=new BasicDataSource();
			//�������ݿ����ӵ�����
			//Ϊ��ά�����㽫�û��������롣���ݿ����õ������ļ�jdbc.properties��
			dataSource.setDriverClassName(properties.getProperty("jdbc.driver"));
			dataSource.setUrl(properties.getProperty("jdbc.url"));
			dataSource.setUsername(properties.getProperty("jdbc.user"));
			dataSource.setPassword(properties.getProperty("jdbc.password"));
			//����Դ�� ��ʼ��С
			dataSource.setInitialSize(5);
			dataSource.setMaxActive(20);
			dataSource.setMaxIdle(60);
			dataSource.setMaxWait(6000);
			logger.debug("����Դ��ʼ���ɹ�");
		} catch (Exception e) {
			logger.debug("����Դ��ʼ��ʧ��");
			e.printStackTrace();
		}
		
	}
	/**�������ݿ�����-->��ȡ���ݿ����Ӷ���,������Ҫ����Դ������ȥ����һ������Դnew BasicDataSource();
	 * �ڳ�����г�ʼ��ʱ����Դ�ʹ����ˣ������뵽��̬�����,�ڴ�����д�������Դ
	 * DbUtilֻҪһ���м��أ��ҵ�����Դ�ͽ��г�ʼ��new BasicDataSource();
	 * ���� getConnection()������ֻҪ����Դ��Ϊ�գ����Ǿͷ�������Դ������ֱ�ӽ���returnһ��null*/
	
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
	//�ر�����
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
	//�ر�����Դ
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
