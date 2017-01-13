package com.woniu.eaypay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

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
				statement.setString(1, password);
				statement.setString(2, userName);
				resultSet=statement.executeQuery();
				
				//��������			
				UserData user = null;
				if(resultSet.first()){//��������ֻҪһ��
					user=new UserData();
					user.setUserName(resultSet.getString("eu_user_name"));
					user.setPassWord(resultSet.getString("eu_password"));
					user.setAddress(resultSet.getString("eu_address"));
					user.setStatus(resultSet.getInt("eu_status"));
					user.setId(resultSet.getInt("eu_user_id"));
					user.setBirthday(resultSet.getTimestamp("eu_birthday"));
					user.setEmail(resultSet.getString("eu_email"));
					user.setIdentity_code(resultSet.getString("eu_identity_code"));
					user.setMobile(resultSet.getString("eu_mobile"));
					user.setSex(resultSet.getString("eu_sex")!=null?resultSet.getString("eu_sex").charAt(0):'\0');
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
	public List<UserData> findAll(){
		Connection connection = DbUtil.getConnection();//�õ����ݿ����Ӷ���
		if(connection !=null){
			PreparedStatement statement=null;
			ResultSet resultSet=null;//�����
			String sql="SELECT * from easybuy_user ";
			try {
				statement=connection.prepareStatement(sql);
				resultSet=statement.executeQuery();				
				//��������			
				List<UserData> users=new ArrayList<UserData>();
				UserData temp=null;
				while(resultSet.next()){
					temp=new UserData();
					temp.setUserName(resultSet.getString("eu_user_name"));
					temp.setPassWord(resultSet.getString("eu_password"));
					temp.setAddress(resultSet.getString("eu_address"));
					temp.setStatus(resultSet.getInt("eu_status"));
					temp.setId(resultSet.getInt("eu_user_id"));
					temp.setBirthday(resultSet.getTimestamp("eu_birthday"));
					temp.setEmail(resultSet.getString("eu_email"));
					temp.setIdentity_code(resultSet.getString("eu_identity_code"));
					temp.setMobile(resultSet.getString("eu_mobile"));
					temp.setSex(resultSet.getString("eu_sex")!=null?resultSet.getString("eu_sex").charAt(0):'\0');
					users.add(temp);
				}
				return users.isEmpty()?null:users;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				DbUtil.closeResource(connection,statement,resultSet);
			}
		}
		return null;
	}
	//����û�
	public void saveUser(UserData user) {
		Connection connection = DbUtil.getConnection();//�õ����ݿ����Ӷ���
		if(connection !=null){
			PreparedStatement statement=null;
			String sql="INSERT into easybuy_user (eu_user_name,eu_password,"
					+ "eu_sex,eu_birthday,eu_identity_code,eu_email,eu_mobile,eu_address,eu_status) "
					+ "VALUES(?,?,?,?,?,?,?,?,?) ";
			try {
				if(user!=null){
					statement=connection.prepareStatement(sql);	
					statement.setString(1, user.getUserName());
					statement.setString(2, DigestUtils.md5Hex(user.getPassWord()));//�������
					statement.setObject(3, user.getSex(), Types.CHAR);//�Ա�
					statement.setObject(4, user.getBirthday(),Types.TIMESTAMP);
					statement.setString(5, user.getIdentity_code());
					statement.setString(6, user.getEmail());
					statement.setString(7, user.getMobile());
					statement.setString(8,user.getAddress());
					statement.setInt(9, user.getStatus());				
					
					statement.executeUpdate();
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				DbUtil.closeResource(connection,statement,null);
			}
		}
		
	}
	//ͨ��IDɾ��
	public void deleteById(int id) {
		Connection connection = DbUtil.getConnection();//�õ����ݿ����Ӷ���
		if(connection !=null){
			PreparedStatement statement=null;		
					 
			try {
				String sql="delete from easybuy_user where eu_user_id=?";
				 statement = connection.prepareStatement(sql);
				 statement.setInt(1,id);
				
				 statement.executeUpdate();//ÿ��ɾ������Ҫ����һ������
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				DbUtil.closeResource(connection,statement,null);
			}
		}
		
	}
	//ͨ��ID�޸�����
	public void modifyById(int id) {
		Connection connection = DbUtil.getConnection();//�õ����ݿ����Ӷ���
		if(connection !=null){
			PreparedStatement statement=null;		
					 
			try {
				String sql="";
				 statement = connection.prepareStatement(sql);
				 statement.setInt(1,id);
				
				 statement.executeUpdate();//ÿ��ɾ������Ҫ����һ������
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				DbUtil.closeResource(connection,statement,null);
			}
		}
	}
	
}
