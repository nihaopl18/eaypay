package com.woniu.eaypay.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse respone)
			throws ServletException, IOException {
		String sign = request.getParameter("sign");
		if("login".equals(sign)){
			request.getRequestDispatcher("WEB-INF/manage/index.jsp").forward(request,respone);
		}else if("user".equals(sign)){
			request.getRequestDispatcher("WEB-INF/manage/user.jsp").forward(request,respone);
		}else if(sign==null){
			request.getRequestDispatcher("WEB-INF/manage/index.jsp").forward(request,respone);
		}
//			request.getRequestDispatcher("WEB-INF/manage/index.jsp").forward(request, response);
	
	
	
	}
	
}
