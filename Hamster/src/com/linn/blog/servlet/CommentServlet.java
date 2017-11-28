package com.linn.blog.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.linn.blog.entity.extension.Comment;
import com.linn.blog.entity.system.Result;
import com.linn.blog.service.CommentServiceImpl;

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
		} else if(oper.equals("commentList")){
			commentList(request,response);
		} else if(oper.equals("toCommentList")){
			toCommentList(request,response);
		} else if(oper.equals("childCommentList")) {
			childCommentList(request,response);
		} else if(oper.equals("editComment")) {
			editComment(request,response);
		}
	}
	/**
	 * 在前端屏蔽该评论极其回复
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void editComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String commentId = request.getParameter("commentId");
		String editType = request.getParameter("editType");
		
		Result result = new Result ();
		result.setMsg("OK");
		result.setSuccess(true);
		try {
			int count = commentService.editComment(commentId,editType);
		} catch (Exception e) {
			result.setMsg("系统内部错误");
			result.setSuccess(false);
			e.printStackTrace();
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
	 * 查找评论的回复
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void childCommentList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Comment> comments = new ArrayList<Comment>();
		String rootId = request.getParameter("rootid");
		try {
			comments = commentService.findChildCommentList(rootId);
			resultMap.put("rows", comments);
			resultMap.put("total",comments.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			Gson g = new Gson();
			out.print(g.toJson(resultMap));
			out.flush();
			out.close();
		}
		
	}
	/**
	 * 跳转到文章评论列表
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void toCommentList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/admin/articleManager/commentList.jsp").forward(request, response);
		
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
		String rootid = request.getParameter("rootid");
		
		Comment comment = new Comment();
		if(rootid == null){
			rootid = "0";
		}
		comment.setRootid(Integer.parseInt(rootid));
		comment.setPid(Integer.parseInt(pid));
		comment.setMemberName(memberName);
		comment.setCont(cont);
		comment.setArticleId(Integer.parseInt(articleId));
		
		Result result = new Result();
		result.setMsg("OK");
		result.setSuccess(true);
		try {
			int count = commentService.addComment(comment);
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
	 * 用于前端显示
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
			comments = commentService.findCommentListTree(articleId);
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
	/**
	 * 查找文章的评论信息
	 * 显示文章评论列表
	 * 对文章列表信息进行扩展
	 * @param request
	 * @param response
	 * @param conn
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void commentList(HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException{
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Comment> comments = new ArrayList<Comment>();
		String articleId = request.getParameter("articleId");
		try {
			
			if (articleId == null) {
				comments = commentService.findRootCommentAll();
			} else {
				comments = commentService.findCommentByArticleId(articleId);
			}
			System.out.println(comments);
			resultMap.put("rows", comments);
			resultMap.put("total",comments.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			Gson g = new Gson();
			out.print(g.toJson(resultMap));
			out.flush();
			out.close();
		}
	}


}
