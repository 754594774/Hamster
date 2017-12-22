package com.linn.blog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linn.blog.entity.system.User;

public class AuthorizationFilter implements Filter {
 	private static Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);
	
	public void destroy() {
		logger.info("AuthorizationFilter destroy");
	}

	public void doFilter(ServletRequest req, ServletResponse rep,
			FilterChain fc) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)rep;
		
		String url = request.getRequestURI();
		String path = url.substring(url.lastIndexOf("/"));
		Boolean isWhiteList = path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".png")|| path.endsWith(".jpg");
		logger.info("过滤器url",url);
		if (isWhiteList){
			fc.doFilter(req, rep);
			return;
		}
		
		User user = (User) request.getSession().getAttribute("user");
		//验证用户是否登录
		Boolean isLogin = user == null && !path.equals("/login.jsp") && !path.equals("/login");
		if (isLogin){
			response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
			return;
		}
		
		fc.doFilter(req, rep);
	}

	public void init(FilterConfig arg0) throws ServletException {
		logger.info("AuthorizationFilter init");
	}

}
