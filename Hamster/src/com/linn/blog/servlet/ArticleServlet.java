package com.linn.blog.servlet;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.linn.blog.entity.extension.Article;
import com.linn.blog.utils.JDBCUtils;

public class ArticleServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			req.setCharacterEncoding("utf-8");
			String oper = req.getParameter("operation");
			if (oper.equals("toArticle")){
				
				toArticle(req,resp);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 跳转到文章详细页面
	 * @param req
	 * @param resp
	 */
	private void toArticle(HttpServletRequest req, HttpServletResponse resp) {
			
		Connection conn =null;
		
		try {
			conn = JDBCUtils.getMysqlConn();
			//查找文章信息
			Article article = getArticle(req,resp,conn);
			
			req.setAttribute("article", article);
			
			ServletContext sc = getServletContext(); 
			RequestDispatcher rd = sc.getRequestDispatcher("/article.jsp"); 
			rd.forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.close(conn);
		}
	}
	
	/**
	 * 获取文章内容
	 * @param request
	 * @param response
	 */
	private Article getArticle(HttpServletRequest request, HttpServletResponse response,
			Connection conn) throws Exception{
		
		PreparedStatement ps=null;

		String sql = "SELECT a.`id`,a.`content`,a.`description`,a.`last_time`,a.`title`,c.`name` "+
			"category_name FROM t_article a JOIN t_category c ON a.category_id=c.id WHERE a.id=? AND a.is_draft=0; ";  
		
		StringBuilder sb = null;
		Reader reader =null;
		Article article=null;
		
		//查找文章信息
		ps = conn.prepareStatement(sql);
		
		String articleId = request.getParameter("articleId");
		ps.setObject(1, articleId);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			article = new Article();
			int id= rs.getInt("id");
			String categoryName = rs.getString("category_name");
			String title = rs.getNString("title");
			Clob c = rs.getClob("content");
			Timestamp lastTime = rs.getTimestamp("last_time");
			String intro = rs.getNString("description");
			
			reader = c.getCharacterStream();
			int temp = 0;
			sb = new StringBuilder();
			while((temp=reader.read())!=-1){
				sb.append((char)temp);
			}
			article.setId(id);
			article.setCategoryName(categoryName);
			article.setTitle(title);
			article.setContent(sb.toString());
			article.setLastTime(new Date(lastTime.getTime()));
			article.setIntro(intro);
		}
		return article;
	}
}