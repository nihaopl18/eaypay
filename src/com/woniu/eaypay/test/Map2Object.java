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
		//注册数据类型转换器
		//得到所有的数据类型转换器的Bean对象
		ConvertUtilsBean convertUtils = BeanUtilsBean.getInstance().getConvertUtils();
		convertUtils.register(new MyDateConvetor(), Date.class);
		UserData user = new UserData();
		//借助beanUtils将map中数据映射到user对象中
		BeanUtils.populate(user, data);
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		e.printStackTrace();
	}
	}
	

}

