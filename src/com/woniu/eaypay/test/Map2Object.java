package com.woniu.eaypay.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.junit.Test;

import com.woniu.eaypay.dao.UserData;
import com.woniu.eaypay.util.MyDateConvetor;


public class Map2Object {
	@Test
	public void map2Object(){
		
	try {
		Map<String,Object> data=new HashMap<>();
		data.put("id",1);	
		data.put("username", "admin");
		data.put("sex", '1');
		data.put("birthday", "2016-09-21");
		//ע����������ת����
		//�õ����е���������ת������Bean����
		ConvertUtilsBean convertUtils = BeanUtilsBean.getInstance().getConvertUtils();
		convertUtils.register(new MyDateConvetor(), Date.class);
		UserData user = new UserData();
		//����beanUtils��map������ӳ�䵽user������
		BeanUtils.populate(user, data);
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		e.printStackTrace();
	}
	}
	

}

