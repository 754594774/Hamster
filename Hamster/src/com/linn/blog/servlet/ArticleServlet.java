package com.linn.blog.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.linn.blog.entity.extension.Article;
import com.linn.blog.entity.extension.Category;
import com.linn.blog.entity.extension.Comment;
import com.linn.blog.entity.system.Result;
import com.linn.blog.filter.AuthorizationFilter;
import com.linn.blog.service.ArticleServiceImpl;
import com.linn.blog.service.CategoryServiceImpl;
import com.linn.blog.service.CommentServiceImpl;
import com.linn.blog.utils.JDBCUtils;

/**
 * 后台文章管理servet
 * @author 李难难
 * 
 */
public class ArticleServlet extends HttpServlet {
 	private static Logger logger = LoggerFactory.getLogger(ArticleServlet.class);
 	
	private ServletConfig config = null;
	private ArticleServiceImpl articleService = null;
	private CategoryServiceImpl categoryService = null;
	private CommentServiceImpl commentService = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
		articleService = new ArticleServiceImpl();
		categoryService = new CategoryServiceImpl();
		commentService = new CommentServiceImpl();
	}
	
	@Override
	public void destroy() {
		config = null;
	}
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("operation");
		logger.info("operation",oper);
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
		} else if (oper.equals("toArticle")){
			toArticle(request,response);
		} else if (oper.equals("toIndex")){
			toIndex(request,response);
		} 
	}

	/**
	 * 显示blog主页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void toIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			List<Article> articles = articleService.findArticleList();
			List<Comment> newestComments  = commentService.findCommentNewest();
			List<Article> newestArticles = articleService.findArticleNewest();
			request.setAttribute("articles", articles);
			request.getSession().setAttribute("newestComments", newestComments);
			request.getSession().setAttribute("newestArticles", newestArticles);
			request.getRequestDispatcher("/index.jsp").forward(request, response);  
		} catch (Exception e) {
			logger.error("跳转到bolog主页面出错",e);	
			request.setAttribute("errorMessage", "系统内部错误!");
			request.getRequestDispatcher("/common/error.jsp").forward(request,response);
		} 
	}
	/**
	 * 跳转到文章展现页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void toArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String articleId = req.getParameter("articleId");
		//查找文章信息
		try {
			 Article article =	articleService.findArticleById(articleId);
			 req.setAttribute("article", article);
			 req.getRequestDispatcher("/article.jsp").forward(req, resp);
		} catch (Exception e) {
			logger.error("查找文章出错",e);
			req.setAttribute("errorMessage", "系统内部错误!");
			req.getRequestDispatcher("/common/error.jsp").forward(req,resp); 
		}
	}
	/**
	 * 发布文章
	 * @throws IOException 
	 */
	private void addArticle(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Result result = new Result();
		result.setSuccess(true);
		result.setMsg("OK");
		
		try {
			Article article = uploadFile(request);
			int count = 0;
			if(article.getId() != null) {
				count = articleService.updateArticle(article);
			} else {
				count = articleService.addArticle(article);
			}
			if(count<=0){
				result.setSuccess(false);
				result.setMsg("添加失败");
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("系统内部错误");
			logger.error("发布文章出错",e);
		}finally{
			Gson g = new Gson();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(g.toJson(result));
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 上传文件
	 * @throws Exception 
	 */
	private Article uploadFile(HttpServletRequest request) throws Exception{
		request.setCharacterEncoding("utf-8");
		Article article = new Article();
		// 用于存放上传文件的目录
		String uploadPath = config.getServletContext().getRealPath("/") + "upload/";   
		String picPath = request.getContextPath() + "/upload/";   
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096);

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		upload.setSizeMax(1000000);
		
		List fileItems = upload.parseRequest(request);
		Iterator iter = fileItems.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			//取出文本数据
			if(item.isFormField()) {
				if(item.getFieldName().equals("title")) {
				 	String title = item.getString("utf-8");
				 	article.setTitle(title.trim());
				}else if(item.getFieldName().equals("description")){
					String description = item.getString("utf-8");
					article.setDescription(description);
				}else if(item.getFieldName().equals("categoryId")){
					int categoryId = Integer.parseInt(item.getString());
					article.setCategoryId(categoryId);
				}else if(item.getFieldName().equals("content")){
					String content = item.getString("utf-8");
					article.setContent(content.trim());
				}else if(item.getFieldName().equals("articleId")){
					String articleId = item.getString("utf-8");
					if(articleId !=null && !articleId.equals(""))
					{
						article.setId(Integer.parseInt(articleId));
					}
				}
			}
			
			if (!item.isFormField()) {
				String picName = String.valueOf( System.currentTimeMillis());
				String name = item.getName();
				long size = item.getSize();
				if ((name == null || name.equals("")) && size == 0)
					continue;
				String path = uploadPath + picName;
				item.write(new File(path));
				//存储图片相对路径
				article.setDescriptionPic(picPath + picName);
			}
		}
		return article;
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
		Result result = new Result();
		result.setSuccess(true);
		result.setMsg("OK");
		try {
			String id = request.getParameter("articleId");
			articleService.delArticleById(id);
			
		} catch (Exception e) {
			logger.error("删除文章出错",e);
			result.setSuccess(false);
			result.setMsg("failed");
		
		}finally{
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
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void toArticleList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ServletContext sc = config.getServletContext(); 
		RequestDispatcher rd = sc.getRequestDispatcher("/admin/articleManager/articleList.jsp"); 
		rd.forward(request, response);
	}

	/**
	 * 文章列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void articleList(HttpServletRequest request, HttpServletResponse response) throws IOException{

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Gson g = new Gson();
		try {
			List<Article> articles = articleService.findArticleListAdmin();
			resultMap.put("rows", articles);
			resultMap.put("total",articles.size());
	
		} catch (Exception e) {
			logger.error("查找文章列表出错",e);
			e.printStackTrace();
		}finally{
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

		try {
			//查询文章的分类列表
			List<Category> categorys = categoryService.findCategoryList();
			request.setAttribute("categorys", categorys);
			//编辑文章，查询文章详细内容
			String articleId = request.getParameter("articleId");
			if(articleId != null) {
				Article article = articleService.findArticleById(articleId);
				request.setAttribute("article", article);
			} 
			//跳转到添加文章界面
			ServletContext sc = config.getServletContext(); 
			RequestDispatcher rd = sc.getRequestDispatcher("/admin/articleManager/addArticle.jsp"); 
			rd.forward(request, response);
			
		} catch (Exception e){
			e.printStackTrace();
			logger.error("跳转到添加文章界面",e);
		}
	}


}
