package com.woniu.eaypay.servlet;

import java.io.IOException;
import java.net.HttpRetryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderServlet extends HttpServlet {
	/**
	 * ¶©µ¥
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");
		if("login".equals(method)){
			String UserName = request.getParameter("userName");
			String password = request.getParameter("passWord");
			
			request.getRequestDispatcher("WEB-INF/manage/index.jsp").forward(request, response);
		}else if("Order".equals(method)){
			request.getRequestDispatcher("WEB-INF/manage/Order.jsp").forward(request, response);
		}else if(method==null){
			request.getRequestDispatcher("WEB-INF/manage/index.jsp").forward(request, response);
		}
	}
	
}