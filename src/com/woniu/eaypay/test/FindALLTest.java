package com.woniu.eaypay.test;

import java.util.List;

import org.junit.Test;

import com.woniu.eaypay.dao.UserDao;
import com.woniu.eaypay.dao.UserData;

public class FindALLTest {
	@Test
	public void findAll(){
		UserDao dao = new UserDao();
		List<UserData> users = dao.findAll();
		if(users!=null){
			for(UserData u:users){
				System.out.println(u);
			}
		}
	}
	@Test
	public void deteById(){
		UserDao dao = new UserDao();
		dao.deleteById(7);
	}
}
