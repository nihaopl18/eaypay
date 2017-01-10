package com.woniu.eaypay.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

	public void query(){
		String url="jdbc:mysql://127.0.0.1:3306/nihaopl";
		String user="root";
		String password=null;
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet =null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");			
			connection = DriverManager.getConnection(url, user, password);
			String sql="select from * easybuy_user";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			
			List<UserData> users = new ArrayList<UserData>();
			
			while(resultSet.next()){
				UserData temp = new UserData();
				temp.setEu_address(resultSet.getString("eu_address"));
				temp.setEu_birthday(resultSet.getString("eu_birthday"));
				temp.setEu_email(resultSet.getString("eu_email"));
				temp.setEu_identity_code(resultSet.getString("eu_identity_code"));
				temp.setEu_mobile(resultSet.getString("eu_mobile"));
				temp.setEu_password(resultSet.getString("eu_password"));
				temp.setEu_sex(resultSet.getString("eu_sex"));
				temp.setEu_status(resultSet.getInt("eu_status"));
				temp.setEu_user_id(resultSet.getInt("eu_user_id"));
				temp.setEu_user_name(resultSet.getString("eu_user_name"));
				
				users.add(temp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				closeAll(connection, statement, resultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void closeAll(Connection connection, PreparedStatement statement,
			ResultSet resultSet) throws SQLException {
		if(resultSet!=null){
			resultSet.close();
		}
		if(statement!=null){
			statement.close();
		}
		if(connection!=null){
			connection.close();
		}
	}
}
