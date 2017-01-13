package com.woniu.eaypay.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
	
	private String encoding;
	@Override
	public void init(FilterConfig config) throws ServletException {
		if(config!=null){//获取config的初始化参数encoding
			encoding=config.getInitParameter("encoding");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		if(encoding!=null){
			request.setCharacterEncoding(encoding);
			response.setCharacterEncoding(encoding);
		}
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
