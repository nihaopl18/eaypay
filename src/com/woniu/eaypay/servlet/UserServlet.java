package com.woniu.eaypay.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.codec.digest.DigestUtils;

import com.woniu.eaypay.dao.UserDao;
import com.woniu.eaypay.dao.UserData;
import com.woniu.eaypay.util.MyDateConvetor;

public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDao userDao = new UserDao();
		//1.先获取做什么事情
		String method = request.getParameter("method");//获取变量method
		try {
			//注册数据类型转换器
			//得到所有的数据类型转换器的Bean对象
			ConvertUtilsBean convertUtils = BeanUtilsBean.getInstance().getConvertUtils();
			convertUtils.register(new MyDateConvetor(), Date.class);
			//封装request中的数据
			UserData temp = new UserData();			
			BeanUtils.populate(temp, request.getParameterMap());//将得到的参数全数封装到一个temp中去
			
			if("login".equals(method)){//用户登录
				//1.获取用户和密码
//			String username = request.getParameter("userName");
//			String password = request.getParameter("passWord");
				temp.setPassWord(DigestUtils.md5Hex(temp.getPassWord()));//密码取出来加密后再设置进去
				//2.查询数据库验证
					//2.1现在直接从temp中取出来在网页中得到的username和passWord
				UserData user =userDao.validateUserInfo(temp.getUserName(), temp.getPassWord());
				//3.根据验证结果
				if(user!=null){
					request.getSession().setAttribute("loginUser", user);
					//3.1成功--》区别用户的身份
					
					if(user.getStatus()==UserData.admin){
						request.getRequestDispatcher("WEB-INF/manage/index.jsp").forward(request, response);					
					}else{
//					request.getRequestDispatcher("indexGeneral.jsp").forward(request, response);
						response.sendRedirect(request.getContextPath()+"/indexGeneral.jsp");
					}
					
				}else{
					request.setAttribute("msg", "用户名或密码错误");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
				/**web-inf里面的文件不能通过浏览器读出来,只有通过转发才能用,那意味着我们每次在web-inf里跳转页面
				 * 都用建一个servlet,这样就很麻烦,---》servlet每次都要提交请求,它的数据是放在request里面
				 * 所以我们用一个额外的参数来实现web-inf里的网页跳转，这个额外的参数我们告诉它我们要做什么*/		
			}else if("user".equals(method)){//用户管理界面
				List<UserData> users = userDao.findAll();
				request.setAttribute("users", users);
				request.getRequestDispatcher("WEB-INF/manage/user.jsp").forward(request, response);
			}else if(method==null){
				request.getRequestDispatcher("WEB-INF/manage/index.jsp").forward(request, response);
			}else if("order".equals(method)){
				request.getRequestDispatcher("WEB-INF/manage/order.jsp").forward(request, response);
			}else if("user-add".equals(method)){
				request.getRequestDispatcher("WEB-INF/manage/user-add.jsp").forward(request, response);
			}else if("order-modify".equals(method)){
				request.getRequestDispatcher("WEB-INF/manage/order-modify.jsp").forward(request, response);
			}else if("save".equals(method)){
			
				temp.setStatus(temp.admin);
				userDao.saveUser(temp);
				response.sendRedirect(request.getContextPath()+"/UserServlet?method=user");
			}else if("delete".equals(method)){
				
				userDao.deleteById(temp.getId());
				response.sendRedirect(request.getContextPath()+"/UserServlet?method=user");
			}else if("modify".equals(method)){
				userDao.modifyById(temp.getId());
				response.sendRedirect(request.getContextPath()+"/UserServlet?method=user");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}