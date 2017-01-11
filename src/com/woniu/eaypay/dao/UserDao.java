package com.woniu.eaypay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.woniu.eaypay.util.DbUtil;

public class UserDao {

	/**
	 * 验证用户登录
	 * */
	public UserData validateUserInfo(String userName,String password){
		
		Connection connection = DbUtil.getConnection();//得到数据库连接对象
		if(connection !=null){
			PreparedStatement statement=null;
			ResultSet resultSet=null;//结果集
			String sql="SELECT * from easybuy_user where eu_password=? and eu_user_name=? ";
			try {
				statement=connection.prepareStatement(sql);
				//有？绑定数据
				statement.setString(1, userName);
				statement.setString(2, password);
				resultSet=statement.executeQuery();
				//处理结果集			
				UserData user = null;
				if(resultSet.first()){//这里我们只要一条
					
					user=new UserData();
					user.setUserName(resultSet.getString("eu_user_name"));
					user.setPassWord(resultSet.getString("eu_password"));
					user.setAddress(resultSet.getString("eu_address"));
					
				}
				return user;//不走这个if(){}它还是为null
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				DbUtil.closeResource(connection,statement,resultSet);
			}
			
		}
		return null;
		
	}
	
}
