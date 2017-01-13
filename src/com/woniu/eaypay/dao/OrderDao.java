package com.woniu.eaypay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.woniu.eaypay.util.DbUtil;

public class OrderDao {

	/**
	 *  ����
	 * */
	public OrderData validateOrderInfo(String userName,String password){
		
		Connection connection = DbUtil.getConnection();//�õ����ݿ����Ӷ���
		if(connection !=null){
			PreparedStatement statement=null;
			ResultSet resultSet=null;//�����
			String sql="SELECT * from easybuy_order where eo_user_id=? and"
					+ " eo_user_name=? and eo_user_address=? and eo_status =? ";
			try {
				statement=connection.prepareStatement(sql);
				//�У�������
				statement.setString(1, userName);
				statement.setString(2, password);
				resultSet=statement.executeQuery();
				//��������			
				OrderData order = null;
				if(resultSet.first()){
					//��������ֻҪһ��
					order=new OrderData();
					order.setEo_id(resultSet.getInt("eo_user_id"));
					order.setEo_user_name(resultSet.getString("eo_user_name"));
					order.setEo_user_address(resultSet.getString("eo_user_address"));
					order.setEo_status(resultSet.getInt("eo_status"));
				}
				return order;    //�������if(){}������Ϊnull
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				DbUtil.closeResource(connection,statement,resultSet);
			}
			
		}
		return null;
		
	}
	
}
