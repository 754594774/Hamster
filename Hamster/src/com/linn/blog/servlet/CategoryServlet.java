package com.linn.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.linn.blog.entity.extension.Article;
import com.linn.blog.entity.extension.Category;
import com.linn.blog.entity.system.Result;
import com.linn.blog.service.CategoryServiceImpl;
import com.linn.blog.utils.JDBCUtils;

public class CategoryServlet extends HttpServlet {
	
	private CategoryServiceImpl categoryService = null;
	private static Logger logger = LoggerFactory.getLogger(CategoryServlet.class);
	
	@Override
	public void init() throws ServletException {
		super.init();
		categoryService = new CategoryServiceImpl();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("operation");
		logger.info("operation",oper );
		
		if(oper.equals("toCategoryList")){
			toCategoryList(request,response);
		}else if(oper.equals("categoryList")){
			categoryList(request,response);
		}else if(oper.equals("addCategory")){
			addCategory(request,response);
		}else if(oper.equals("changeCategory")){
			changeCategory(request,response);
		}else if(oper.equals("delCategory")){
			delCategory(request,response);
		}
	}

	/**
	 * 删除分类
	 * @throws IOException 
	 */
	private void delCategory(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		Result result = new Result ();
		result.setMsg("OK");
		result.setSuccess(true);
		try {
			int count = categoryService.delCategory(id);
			if(count == -1) {
				result.setMsg("该分类下有文章，删除失败");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			result.setMsg("系统内部错误");
			result.setSuccess(false);
			logger.error("删除分类出错",e);
		}finally{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			Gson g = new Gson();
			out.write(g.toJson(result));
			out.flush();
			out.close();
		}
	}
	/**
	 * 修改分类
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void changeCategory(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		Category category = new Category();
		category.setCode(code);
		category.setId(Integer.parseInt(id));
		category.setName(name);
		
		Result result = new Result ();
		result.setMsg("OK");
		result.setSuccess(true);
		try {
			int count = categoryService.updateCategory(category);
			if(count <= 0) {
				result.setMsg("更新失败");
				result.setSuccess(false);	
			}
		} catch (Exception e) {
			result.setMsg("系统内部错误");
			result.setSuccess(false);
			e.printStackTrace();
			logger.error("修改分类出错",e);
		}finally{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			Gson g = new Gson();
			out.write(g.toJson(result));
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 跳转到分类列表页面
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void toCategoryList(HttpServletRequest request, HttpServletResponse response) throws IOException{
			
		ServletContext sc = getServletContext(); 
		RequestDispatcher rd = sc.getRequestDispatcher("/admin/articleManager/categoryList.jsp"); 
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			logger.error("跳转到分类列表页面",e);
		} 

	}
	/**
	 * 显示分类列表
	 * @throws IOException 
	 */
	private void categoryList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Gson g = new Gson();
		try {
			List<Category> articles = categoryService.findCategoryList();
			resultMap.put("rows", articles);
			resultMap.put("total",articles.size());
		} catch (Exception e) {
			logger.error("查找分类列表",e);
		}finally{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(g.toJson(resultMap));
			out.flush();
			out.close();
		}
	}
	/**
	 * 添加分类
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		Result result = new Result();
		result.setSuccess(true);
		result.setMsg("OK");
		try {
			Category category = new Category();
			category.setCode(code);
			category.setName(name);
			int count = categoryService.addCategory(category);
			if(count <= 0) {
				result.setSuccess(false);
				result.setMsg("添加失败");
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("系统内部错误");
			logger.error("添加分类出错",e);
		}finally{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			Gson g = new Gson();
			out.write(g.toJson(result));
			out.flush();
			out.close();
		}
	}
	
}
