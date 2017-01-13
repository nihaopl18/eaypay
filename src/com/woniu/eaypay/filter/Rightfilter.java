package com.woniu.eaypay.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class Rightfilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		//1.�ų�����Ҫ��¼����
		if(request instanceof HttpServletRequest){
			String uri = ((HttpServletRequest) request).getRequestURI();
//			uri=uri.substring(uri.lastIndexOf("/"));
			String method = request.getParameter("method");
			if(method==null){
				String path= ((HttpServletRequest) request).getContextPath();
				 uri = uri.substring(path.length());
			}else{
				uri=uri.substring(uri.lastIndexOf("/"));
			}
			if(uri.equals("/")||uri.equals("/login.jsp")||"login".equals(method)||uri.startsWith("/css")||uri.startsWith("/images")||uri.startsWith("/scripts")){//��¼������¼����
				filterChain.doFilter(request, response);//ֱ�ӷ���
				return;
			}
			//2.�ж��û��Ƿ��¼��
			Object loginUser = ((HttpServletRequest) request).getSession().getAttribute("loginUser");
			if(loginUser!=null){
				filterChain.doFilter(request, response);//ֱ�ӷ���
				return;
			}else{
				request.setAttribute("msg", "���¼");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
	}

	@Override
	public void destroy() {

	}
}
