package com.linn.blog.servlet.admin;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.linn.blog.common.Const;
import com.linn.blog.entity.extension.Article;
import com.linn.blog.entity.extension.Category;
import com.linn.blog.entity.system.Result;
import com.linn.blog.utils.JDBCUtils;
import com.sun.xml.internal.bind.v2.TODO;

/**
 * 后台文章管理servet
 * @author 李难难
 * 
 */
public class ArticleServlet extends HttpServlet {
	
	private ServletConfig config = null;
	
	@Override
	public void destroy() {
		config = null;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("operation");
		
		if (oper.equals("toAddArticle")){
			
			toAddArticle(request,response);
		} else if (oper.equals("toArticleList")){
			
			toArticleList(request,response);
		} else if (oper.equals("articleList")){
			
			articleList(request, response);
		} else if (oper.equals("delArticle")){
			
			delArticle(request,response);
		} else if(oper.equals("addArticle")){
			addArticle(request, response);
		}
	}

	/**
	 * 发布文章
	 */
	private void addArticle(HttpServletRequest request, HttpServletResponse response){
		String uploadPath = config.getServletContext().getRealPath("/") + "upload\\";   // 用于存放上传文件的目录
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096);

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		upload.setSizeMax(1000000);
		
		Result result = new Result();
		result.setSuccess(true);
		result.setMsg("OK");
		PrintWriter out=null;
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			
			out = response.getWriter();
			List fileItems = upload.parseRequest(request);
			Iterator iter = fileItems.iterator();

			// 正则匹配，过滤路径取文件名
			String regExp = ".+\\\\(.+)$";

			// 过滤掉的文件类型
			String[] errorType = { ".exe", ".com", ".cgi", ".jsp" };
			Pattern p = Pattern.compile(regExp);
			
			Article article = new Article();
			String picName = getNextId() + ".jpg";
			article.setIntroPicName(picName);
			
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				// 忽略其他不是文件域的所有表单信息
				if (!item.isFormField()) {
					String name = item.getName();
					long size = item.getSize();
					if ((name == null || name.equals("")) && size == 0)
						continue;
					Matcher m = p.matcher(name);
					boolean isMatch = m.find();
					String path = uploadPath + picName;
					item.write(new File(path));
				}

				//添加文章到数据库
				if(item.isFormField()) {
					System.out.println(item.getFieldName() + "==" + item.getString("utf-8").trim());
					if(item.getFieldName().equals("title")) {
					 	String title = item.getString("utf-8");
					 	article.setTitle(title.trim());
					}else if(item.getFieldName().equals("intro")){
						String intro = item.getString("utf-8");
						article.setIntro(intro.trim());
					}else if(item.getFieldName().equals("categoryId")){
						int categoryId = Integer.parseInt(item.getString());
						article.setCategoryId(categoryId);
					}else if(item.getFieldName().equals("content")){
						String content = item.getString("utf-8");
						article.setContent(content.trim());
					}
				}
			}
			//添加至数据库
			if(addArticle(article)<=0){
				
				result.setSuccess(false);
				result.setMsg("添加失败");
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("发布出错");
			e.printStackTrace();
		}finally{
			Gson g = new Gson();
			response.setContentType("text/html;charset=utf-8");
			out.write(g.toJson(result));
			out.flush();
			out.close();
		}
	}
	
	private long getNextId(){
		long nextId = 0;
		Connection conn =null;
		PreparedStatement ps=null;
		String sql = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='t_article';";  

		try {
			conn = JDBCUtils.getMysqlConn();
			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				nextId = rs.getInt("AUTO_INCREMENT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			nextId= System.currentTimeMillis();
		}finally{
			JDBCUtils.close(ps, conn);
		}
		return nextId;
	}
	
	private int addArticle(Article article){
		int count =0;
		
		Connection conn =null;
		PreparedStatement ps=null;
		String sql = "INSERT INTO t_article (category_id,title,content,description,description_pic,last_time,is_draft) VALUES (?,?,?,?,?,?,?);";  
		try{
			conn = JDBCUtils.getMysqlConn();
			ps = conn.prepareStatement(sql);
			
			article.setLastTime(new Date(System.currentTimeMillis()));
			
			ps.setObject(1, article.getCategoryId());
			ps.setObject(2, article.getTitle());
			ps.setClob(3, new BufferedReader(new InputStreamReader(
					new BufferedInputStream(
                    		new ByteArrayInputStream(article.getContent().getBytes("utf-8"))		
					),"utf-8")));
			ps.setObject(4, article.getIntro());
			ps.setObject(5, article.getIntroPicName());
			ps.setObject(6, article.getLastTime());
			ps.setObject(7, Const.NO);

			count = ps.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
			count = 0;
		}finally{
			JDBCUtils.close(ps, conn);
		}
		return count;
	}
	/**
	 * 删除文章
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void delArticle(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("utf-8");
		
		Connection conn =null;
		PreparedStatement ps=null;
		
		Result result = new Result();
		result.setSuccess(true);
		result.setMsg("OK");
		try {
			String sql = "delete from t_article where id = ?;";  
			
			conn = JDBCUtils.getMysqlConn();
			ps = conn.prepareStatement(sql);
			String id = request.getParameter("id");
			
			ps.setObject(1, id);
			int count = ps.executeUpdate();
			
		} catch (SQLException e) {
			result.setSuccess(false);
			result.setMsg("failed");
			e.printStackTrace();
		}finally{
			JDBCUtils.close(ps, conn);
			response.setContentType("text/html;charset=utf-8");
			Gson g = new Gson();
			PrintWriter out = response.getWriter();
			out.write(g.toJson(result));
			out.flush();
			out.close();
		}
	}

	/**
	 * 跳转到文章列表页面
	 * @param request
	 * @param response
	 */
	private void toArticleList(HttpServletRequest request, HttpServletResponse response){
		ServletContext sc = config.getServletContext(); 
		RequestDispatcher rd = sc.getRequestDispatcher("/admin/articleManager/articleList.jsp"); 
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文章列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void articleList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Connection conn =null;
		PreparedStatement ps=null;
		String sql = "SELECT a.id,c.name category_name,a.title,a.description,a.last_time FROM t_article a JOIN t_category c ON a.category_id=c.id;";  
		List<Article> items = new ArrayList<Article>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Gson g = new Gson();
		try {
			conn = JDBCUtils.getMysqlConn();
			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			int count = 0;
			while(rs.next()){
				count ++;
				Article article = new Article();
				
				int id = rs.getInt("id");
				String name = rs.getNString("category_name");
				String title = rs.getNString("title");
				String intro = rs.getNString("description");
				Timestamp lastTime = rs.getTimestamp("last_time");
				
				article.setId(id);
				article.setCategoryName(name);
				article.setTitle(title);
				article.setIntro(intro);
				article.setLastTime(new Date(lastTime.getTime()));
				items.add(article);
			}
			
			resultMap.put("rows", items);
			resultMap.put("total",count);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(ps, conn);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(g.toJson(resultMap));
			out.flush();
			out.close();
		}
	}

	/**
	 * 跳转到添加文章页面
	 * @param request
	 * @param response
	 */
	private void toAddArticle(HttpServletRequest request, HttpServletResponse response){

		Connection conn =null;
		PreparedStatement ps=null;
		String sql = "select * from t_category;";  

		List<Category> categorys = new ArrayList<Category>();//已添加分类
		Category category=null;
		try {
			conn = JDBCUtils.getMysqlConn();
			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				category = new Category();
				String code = rs.getNString("code");
				String name = rs.getNString("name");
				int id = rs.getInt("id");

				category.setCode(code);
				category.setId(id);
				category.setName(name);
				
				categorys.add(category);
			}
			request.setAttribute("categorys", categorys);
			
			ServletContext sc = config.getServletContext(); 
			RequestDispatcher rd = sc.getRequestDispatcher("/admin/articleManager/addArticle.jsp"); 
			rd.forward(request, response);
			
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(ps, conn);
		}
	}


}
