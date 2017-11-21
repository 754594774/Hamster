package com.linn.blog.servlet.admin;

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

import com.google.gson.Gson;
import com.linn.blog.entity.extension.Article;
import com.linn.blog.entity.extension.Category;
import com.linn.blog.entity.system.Result;
import com.linn.blog.utils.JDBCUtils;

public class CategoryServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("operation");
		
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
		
		Connection conn =null;
		PreparedStatement ps=null;
		
		Result result = new Result ();
		result.setMsg("OK");
		result.setSuccess(true);
		try {
			String sql = "delete from t_category where id = ?;";  
			
			conn = JDBCUtils.getMysqlConn();
			ps = conn.prepareStatement(sql);
			String id = request.getParameter("id");
			
			ps.setObject(1, id);
			int count = ps.executeUpdate();
			
		} catch (SQLException e) {
			result.setMsg("失败");
			result.setSuccess(false);
			e.printStackTrace();
		}finally{
			JDBCUtils.close(ps, conn);
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
		
		Connection conn =null;
		PreparedStatement ps=null;
		String sql = "update t_category set code = ?,name = ? where id = ?;";  
		Result result = new Result ();
		result.setMsg("OK");
		result.setSuccess(true);
		try {
			conn = JDBCUtils.getMysqlConn();
			ps = conn.prepareStatement(sql);
			String id = request.getParameter("id");
			String code = request.getParameter("code");
			String name = request.getParameter("name");
			
			ps.setObject(1, code);
			ps.setObject(2, name);
			ps.setObject(3, id);
			int count = ps.executeUpdate();
			
		} catch (SQLException e) {
			result.setMsg("failed");
			result.setSuccess(false);
			e.printStackTrace();
		}finally{
			JDBCUtils.close(ps, conn);
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
		RequestDispatcher rd = sc.getRequestDispatcher("/admin//articleManager/categoryList.jsp"); 
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 显示分类列表
	 * @throws IOException 
	 */
	private void categoryList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Connection conn =null;
		PreparedStatement ps=null;
		String sql = "select * from t_category;";  
		
		List<Category> items = new ArrayList<Category>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Gson g = new Gson();
		try {
			conn = JDBCUtils.getMysqlConn();
			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			int count = 0;
			while(rs.next()){
				count ++;
				Category categoryJson = new Category();
				String code = rs.getNString("code");
				String name = rs.getNString("name");
				int id = rs.getInt("id");
				categoryJson.setCode(code);
				categoryJson.setName(name);
				categoryJson.setId(id);
				items.add(categoryJson);
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
	 * 添加分类
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		Connection conn =null;
		PreparedStatement ps=null;
		String sql = "insert into t_category (CODE,NAME) VALUES (?,?);";  
		
		Result result = new Result();
		result.setSuccess(true);
		result.setMsg("OK");
		try {
			conn = JDBCUtils.getMysqlConn();
			ps = conn.prepareStatement(sql);
			
			String code = request.getParameter("code");
			String name = request.getParameter("name");
			
			ps.setObject(1, code);
			ps.setObject(2, name);

			int count = ps.executeUpdate();
			
		} catch (SQLException e) {
			result.setSuccess(false);
			result.setMsg("failed");
			e.printStackTrace();
		}finally{
			JDBCUtils.close(ps, conn);
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			Gson g = new Gson();
			out.write(g.toJson(result));
			out.flush();
			out.close();
		}
	}
	
}
