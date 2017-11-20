package com.linn.blog.servlet.admin;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.linn.blog.entity.system.User;
import com.linn.blog.service.admin.LoginServiceImpl;


/**
 * 后台登入 登出
 * @author admin
 *
 */
public class LoginServlet extends HttpServlet {
	
	private LoginServiceImpl loginServiceImpl = null; 
	
	@Override
	public void init() throws ServletException {
		
		super.init();
		loginServiceImpl = new LoginServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
			req.setCharacterEncoding("utf-8");
			String oper = req.getParameter("operation");
			if (oper.equals("login")){
				login(req,resp);
			} else if (oper.equals("logout")){
				logout(req,resp);
			}
	}

	/**
	 * 登出
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		req.getSession().removeAttribute("user");
		resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");
	}


	/**
	 * 登录
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws Exception 
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username").trim();
		String password = req.getParameter("password").trim();

		User user = null;
		if (username != null && password != null && !username.equals("") && !password.equals("")) {
			user = new User();
			user.setUsername(username);
			user.setPassword(password);
		
				int count = 0;
				try {
					count = loginServiceImpl.findUser(user);
				} catch (Exception e) {
					e.printStackTrace();
					req.setAttribute("message", "系统内部错误!");
					req.getRequestDispatcher("/admin/login.jsp").forward(req,resp); 
				}
				if (count == 1){
					HttpSession ss = req.getSession();
					ss.setMaxInactiveInterval(2*60*60);
					req.getSession().setAttribute("user",user);
					resp.sendRedirect(req.getContextPath() + "/admin/index.jsp");
				} else {
					req.setAttribute("message", "帐号或密码不正确!");
					req.getRequestDispatcher("/admin/login.jsp").forward(req,resp); 
				}
			
		} else {
			req.setAttribute("message", "帐号或密码不能为空!");
			req.getRequestDispatcher("/admin/login.jsp").forward(req,resp); 
		}
	}
}