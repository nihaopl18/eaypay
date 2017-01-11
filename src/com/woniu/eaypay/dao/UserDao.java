package com.woniu.eaypay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.woniu.eaypay.util.DbUtil;

public class UserDao {

	/**
	 * ��֤�û���¼
	 * */
	public UserData validateUserInfo(String userName,String password){
		
		Connection connection = DbUtil.getConnection();//�õ����ݿ����Ӷ���
		if(connection !=null){
			PreparedStatement statement=null;
			ResultSet resultSet=null;//�����
			String sql="SELECT * from easybuy_user where eu_password=? and eu_user_name=? ";
			try {
				statement=connection.prepareStatement(sql);
				//�У�������
				statement.setString(1, userName);
				statement.setString(2, password);
				resultSet=statement.executeQuery();
				//��������			
				UserData user = null;
				if(resultSet.first()){//��������ֻҪһ��
					
					user=new UserData();
					user.setUserName(resultSet.getString("eu_user_name"));
					user.setPassWord(resultSet.getString("eu_password"));
					user.setAddress(resultSet.getString("eu_address"));
					
				}
				return user;//�������if(){}������Ϊnull
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				DbUtil.closeResource(connection,statement,resultSet);
			}
			
		}
		return null;
		
	}
	
}
