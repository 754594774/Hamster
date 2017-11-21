package com.linn.blog.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.linn.blog.entity.extension.Comment;
import com.linn.blog.entity.system.Result;
import com.linn.blog.service.CommentServiceImpl;
import com.linn.blog.utils.JDBCUtils;

public class CommentServlet extends HttpServlet {

	private CommentServiceImpl commentService = null;
	@Override
	public void init() throws ServletException {
		super.init();
		commentService = new CommentServiceImpl(); 
	}
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("operation");
		if (oper.equals("getComments")){
			getComments(request,response);
		} else if(oper.equals("addComent")){
			addComent(request,response);
		}
	}
	/**
	 * 添加评论
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addComent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String pid = request.getParameter("pid");
		String memberName = request.getParameter("memberName");
		String cont = request.getParameter("cont");
		String articleId = request.getParameter("articleId");
		
		Comment comment = new Comment();
		comment.setPid(Integer.parseInt(pid));
		comment.setMemberName(memberName);
		comment.setCont(cont);
		comment.setArticleId(Integer.parseInt(articleId));
		
		Result result = new Result();
		result.setMsg("OK");
		result.setSuccess(true);
		try {
			int count = commentService.addComment(comment);
			System.out.println("添加评论结果：" + count);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("添加失败，系统错误");
			result.setSuccess(false);
		}finally{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = null;
			out = response.getWriter();
			Gson gs = new Gson();
			out.write(gs.toJson(result));
			out.flush();
			out.close();
		}
	}
	/**
	 * 查找文章的评论信息
	 * @param request
	 * @param response
	 * @param conn
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getComments(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		List<Comment> comments = new ArrayList<Comment>();
		String jsonMsg = "";
		String articleId = request.getParameter("articleId");
		try {
			comments = commentService.findCommentList(articleId);
			Gson gson = new Gson();
			jsonMsg = gson.toJson(comments);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(jsonMsg);
			out.flush();
			out.close();
		}
	}
	


}
