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
import com.linn.blog.utils.JDBCUtils;

public class CommentServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			String oper = request.getParameter("operation");
			if (oper.equals("getComments")){
				getComments(request,response);
			} else if(oper.equals("addComent")){
				addComent(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 添加评论
	 * @param request
	 * @param response
	 */
	private void addComent(HttpServletRequest request,
			HttpServletResponse response) {
		Connection conn =null;
		PreparedStatement ps=null;
		String sql = 
			"INSERT INTO t_comment(pid,rootid,member_name,cont,pdate,isleaf,article_id) values(?,?,?,?,now(),?,?);";
		
		String updateParentLeadSql = "UPDATE t_comment SET isleaf = ? WHERE id = ?;";
		try {
			conn = JDBCUtils.getMysqlConn();
			//插入新的评论
			ps = conn.prepareStatement(sql);
			
			String pid = request.getParameter("pid");
			String memberName = request.getParameter("memberName");
			String cont = request.getParameter("cont");
			String articleId = request.getParameter("articleId");
			
			ps.setObject(1, pid);
			ps.setObject(2, 1);
			ps.setObject(3, memberName);
			ps.setObject(4, cont);
			ps.setObject(5, 0);
			ps.setObject(6, articleId);
			
			int count = ps.executeUpdate();
			
			//更改父节点为非叶子节点
			ps = conn.prepareStatement(updateParentLeadSql);
			ps.setObject(1, 1);
			ps.setObject(2, pid);
			count = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(ps, conn);
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.write("success");
			out.flush();
			out.close();
		}
		
	}
	/**
	 * 查找文章的评论信息
	 * @param request
	 * @param response
	 * @param conn
	 * @throws Exception
	 */
	private void getComments(HttpServletRequest request, HttpServletResponse response){
		
		List<Comment> comments = new ArrayList<Comment>();
		
		Connection conn = null;;
		Comment comment = null;
		PreparedStatement ps=null;
		String result = "";
		String articleId = request.getParameter("articleId");
		try
		{
			String sql = "select * from t_comment where pid = 0 and article_id =?;";
			
			conn = JDBCUtils.getMysqlConn();
			ps = conn.prepareStatement(sql);
			ps.setObject(1, articleId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				comment = new Comment();
				comment.setCont(rs.getString("cont"));
				comment.setId(rs.getInt("id"));
				comment.setMemberName(rs.getString("member_name"));
				comment.setPdate(rs.getDate("pdate"));
				comment.setIsleaf(rs.getInt("isleaf"));
				comment.setArticle_id(rs.getInt("article_id"));
				comments.add(comment);
				
				System.out.println(rs.getString("cont"));
				tree(conn, rs.getInt("id"), 1,comment.getChildComments());
			}
			Gson gson = new Gson();
			result = gson.toJson(comments);
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			JDBCUtils.close(ps,conn);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.write(result);
			out.flush();
			out.close();
		}
	}
	
	private void tree(Connection conn, int id, int level,List<Comment> comments) {
		
		Statement stmt = null;
		ResultSet rs = null;
		Comment comment = null;
		
		StringBuffer strPre = new StringBuffer("");
		for(int i=0; i<level; i++) {
			strPre.append("    ");
		}
		
		try {
			stmt = conn.createStatement();
			String sql = "select * from t_comment where pid = " + id;
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				comment = new Comment();
				comment.setCont(rs.getString("cont"));
				comment.setId(rs.getInt("id"));
				comment.setMemberName(rs.getString("member_name"));
				comment.setPdate(rs.getDate("pdate"));
				comment.setIsleaf(rs.getInt("isleaf"));
				comment.setArticle_id(rs.getInt("article_id"));
				comments.add(comment);
				
				System.out.println(strPre + rs.getString("cont"));
				if(rs.getInt("isleaf") != 0)
					tree(conn, rs.getInt("id"), level + 1,comment.getChildComments());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
					rs = null;
				}
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 待定
	 * @throws IOException 
	 */
	private void defined(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		request.setCharacterEncoding("utf-8");
		
		Connection conn =null;
		PreparedStatement ps=null;
		String sql = "insert into article (pid,rootid,cont,pdate,isleaf,article_id) values (?, ?, ?,now(), ?,?);";  
		try {
			conn = JDBCUtils.getMysqlConn();
			ps = conn.prepareStatement(sql);
			
			String pid = request.getParameter("pid");
			String rootId = request.getParameter("rootId");
			String cont = request.getParameter("cont");
			String articleId = request.getParameter("article_id");
			
			ps.setObject(1, pid);
			ps.setObject(2, rootId);
			ps.setObject(3, cont);
			ps.setObject(4, 0);
			
			int count = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.close(ps, conn);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.flush();
			out.close();
		}
	}
}
