package com.woniu.eaypay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.woniu.eaypay.util.DbUtil;

public class UserDao {

	/**
	 * 验证用户登录lalalalalal
	 * */
	public UserData validateUserInfo(String userName,String password){
		
		Connection connection = DbUtil.getConnection();
		if(connection !=null){
			PreparedStatement statement=null;
			ResultSet resultSet=null;
			String sql="select * from easybuy_user where eu_user_name=? and eu_password=?";
			try {
				statement=connection.prepareStatement(sql);
				//绑定数据
				statement.setString(1, userName);
				statement.setString(2, password);
				resultSet=statement.executeQuery(sql);
				//处理结果集			
				UserData user = null;
				if(resultSet.first()){
					
					user=new UserData();
					user.setUserName(resultSet.getString("eu_user_name"));
					user.setPassWord(resultSet.getString("eu_password"));
					user.setAddress(resultSet.getString("eu_address"));
					
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				DbUtil.closeResource(connection,statement,resultSet);
			}
			
		}
		return null;
		
	}
	
}
