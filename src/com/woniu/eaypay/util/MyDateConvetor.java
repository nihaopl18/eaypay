package com.woniu.eaypay.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class MyDateConvetor implements Converter {
/**
 * 自定义数据类型转换器
 * 
 * data:等待转换的数据
 * clazz：将要转成的数据类型
 * */
	@Override
	public Object convert(Class clazz, Object data) {
		if(clazz==Date.class){//是否要转成Date类型
			SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");//格式时间
			try {
				return format.parse(data.toString());//把string 转换成日期
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}

