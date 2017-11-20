package com.linn.blog.servlet;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.linn.blog.entity.extension.Article;
import com.linn.blog.utils.JDBCUtils;

/**
 * blog主页
 * @author admin
 *
 */
public class IndexServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setCharacterEncoding("utf-8");
			
			String oper = req.getParameter("operation");
			if (oper.equals("toIndex")){
				
				toIndex(req,resp);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 显示blog主页面
	 * @param request
	 * @param response
	 */
	private void toIndex(HttpServletRequest request, HttpServletResponse response){
		String picPath = request.getContextPath() + "/upload/";   
	
		Connection conn =null;
		PreparedStatement ps=null;
		String sql = "SELECT a.id,c.name category_name,a.title,a.description,a.description_pic,a.last_time FROM t_article a JOIN t_category c ON a.category_id=c.id;";  

		List<Article> articles = new ArrayList<Article>();//已添加分类
		Article article=null;
		try {
			conn = JDBCUtils.getMysqlConn();
			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			int count = 0;
			while(rs.next()){
				count ++;
				article = new Article();
				
				int id = rs.getInt("id");
				String categoryName = rs.getNString("category_name");
				String title = rs.getNString("title");
				String intro = rs.getNString("description");
				Timestamp lastTime = rs.getTimestamp("last_time");
				String introPicName = rs.getString("description_pic");
				
				article.setId(id);
				article.setCategoryName(categoryName);
				article.setTitle(title);
				article.setIntro(intro);
				String pathPic = picPath + introPicName;
				article.setIntroPicName(pathPic);
				
				articles.add(article);
			}
			
			request.setAttribute("articles", articles);
			request.getRequestDispatcher("/index.jsp").forward(request, response);  
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.close(ps, conn);
		}
	}
	
}
