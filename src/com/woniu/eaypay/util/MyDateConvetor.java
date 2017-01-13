package com.woniu.eaypay.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class MyDateConvetor implements Converter {
/**
 * �Զ�����������ת����
 * 
 * data:�ȴ�ת��������
 * clazz����Ҫת�ɵ���������
 * */
	@Override
	public Object convert(Class clazz, Object data) {
		if(clazz==Date.class){//�Ƿ�Ҫת��Date����
			SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");//��ʽʱ��
			try {
				return format.parse(data.toString());//��string ת��������
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}

