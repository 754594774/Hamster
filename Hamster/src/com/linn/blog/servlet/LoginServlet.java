package com.linn.blog.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.linn.blog.entity.system.Result;
import com.linn.blog.entity.system.User;
import com.linn.blog.service.UserServiceImpl;


/**
 * 后台登入 登出
 * @author admin
 *
 */
public class LoginServlet extends HttpServlet {
	
	private UserServiceImpl loginServiceImpl = null; 
	private static Logger logger = LoggerFactory.getLogger(LinksServlet.class);
	
	@Override
	public void init() throws ServletException {
		
		super.init();
		loginServiceImpl = new UserServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
			req.setCharacterEncoding("utf-8");
			String oper = req.getParameter("operation");
			logger.info("operation",oper);
			if (oper.equals("login")){
				login(req,resp);
			} else if (oper.equals("logout")){
				logout(req,resp);
			} else if(oper.equals("changePassword")){
				changePassword(req,resp);
			}
	}

	/**
	 * 修改密码
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void changePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		PrintWriter out=null;
		
		Result result = new Result();
		result.setSuccess(true);
		result.setMsg("修改成功，请重新登录");
		
		if (username ==null || password == null || confirmPassword == null) {
			result.setSuccess(false);
			result.setMsg("帐号或密码不能为空！");
		} else if (!password.equals(confirmPassword)) {
			result.setSuccess(false);
			result.setMsg("两次密码不一致！");
		} else {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			int count =0;
			try {
				count = loginServiceImpl.changeUser(user);

			} catch (Exception e) {
				logger.error("修改密码",e);
				result.setSuccess(false);
				result.setMsg("修改失败");
			}
			if (count <= 0){
				result.setSuccess(false);
				result.setMsg("修改失败");
			} else{
				req.getSession().removeAttribute("user");
			}
		}
		
		resp.setContentType("text/html;charset=utf-8");
		out = resp.getWriter();
		Gson g = new Gson();
		out.write(g.toJson(result));
		out.flush();
		out.close();
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

		if (username != null && password != null && !username.equals("") && !password.equals("")) {
			try {
				User user = loginServiceImpl.findUser(username,password);
				if (user != null){
					HttpSession ss = req.getSession();
					ss.setMaxInactiveInterval(2*60*60);
					req.getSession().setAttribute("user",user);
					resp.sendRedirect(req.getContextPath() + "/admin/index.jsp");
				} else {
					req.setAttribute("message", "帐号或密码不正确!");
					req.getRequestDispatcher("/admin/login.jsp").forward(req,resp); 
				}
			} catch (Exception e) {
				logger.error("登录",e);
				req.setAttribute("message", "系统内部错误!");
				req.getRequestDispatcher("/admin/login.jsp").forward(req,resp); 
				return;
			}
		} else {
			req.setAttribute("message", "帐号或密码不能为空!");
			req.getRequestDispatcher("/admin/login.jsp").forward(req,resp); 
		}
	}
}
