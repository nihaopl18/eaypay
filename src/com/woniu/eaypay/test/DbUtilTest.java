package com.woniu.eaypay.test;

import java.sql.Connection;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.woniu.eaypay.dao.UserDao;
import com.woniu.eaypay.dao.UserData;
import com.woniu.eaypay.util.DbUtil;

public class DbUtilTest {

	@Test
	public void getConnection(){
		Connection connection = DbUtil.getConnection();
		System.out.println(connection);
	}
	
	@Test
	public void validateUser(){
		String userName="admin";
		String password="admin";
		password=DigestUtils.md5Hex(password);
		System.out.println(password+"³¤¶È:"+password.length());
		UserDao userDao=new UserDao();
		UserData user = userDao.validateUserInfo(userName, password);
		System.out.println(user);
	}
}
