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
		//1.�Ȼ�ȡ��ʲô����
		String method = request.getParameter("method");//��ȡ����method
		try {
			//ע����������ת����
			//�õ����е���������ת������Bean����
			ConvertUtilsBean convertUtils = BeanUtilsBean.getInstance().getConvertUtils();
			convertUtils.register(new MyDateConvetor(), Date.class);
			//��װrequest�е�����
			UserData temp = new UserData();			
			BeanUtils.populate(temp, request.getParameterMap());//���õ��Ĳ���ȫ����װ��һ��temp��ȥ
			
			if("login".equals(method)){//�û���¼
				//1.��ȡ�û�������
//			String username = request.getParameter("userName");
//			String password = request.getParameter("passWord");
				temp.setPassWord(DigestUtils.md5Hex(temp.getPassWord()));//����ȡ�������ܺ������ý�ȥ
				//2.��ѯ���ݿ���֤
					//2.1����ֱ�Ӵ�temp��ȡ��������ҳ�еõ���username��passWord
				UserData user =userDao.validateUserInfo(temp.getUserName(), temp.getPassWord());
				//3.������֤���
				if(user!=null){
					request.getSession().setAttribute("loginUser", user);
					//3.1�ɹ�--�������û������
					
					if(user.getStatus()==UserData.admin){
						request.getRequestDispatcher("WEB-INF/manage/index.jsp").forward(request, response);					
					}else{
//					request.getRequestDispatcher("indexGeneral.jsp").forward(request, response);
						response.sendRedirect(request.getContextPath()+"/indexGeneral.jsp");
					}
					
				}else{
					request.setAttribute("msg", "�û������������");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
				/**web-inf������ļ�����ͨ�������������,ֻ��ͨ��ת��������,����ζ������ÿ����web-inf����תҳ��
				 * ���ý�һ��servlet,�����ͺ��鷳,---��servletÿ�ζ�Ҫ�ύ����,���������Ƿ���request����
				 * ����������һ������Ĳ�����ʵ��web-inf�����ҳ��ת���������Ĳ������Ǹ���������Ҫ��ʲô*/		
			}else if("user".equals(method)){//�û��������
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