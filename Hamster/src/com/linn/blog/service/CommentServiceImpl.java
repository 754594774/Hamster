package com.linn.blog.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.linn.blog.entity.extension.Comment;
import com.linn.blog.utils.JDBCUtils;


/**
 * 文章评论service
 * @author Administrator
 *
 */
public class CommentServiceImpl {

	/**
	 * 添加评论
	 */
	public int addComment (Comment comment) throws Exception{

		String sql = "INSERT INTO t_comment(pid,rootid,member_name,cont,pdate,isleaf,article_id) " 
			+"values(?,?,?,?,now(),?,?);";
		String updateParentLeadSql = "UPDATE t_comment SET isleaf = ? WHERE id = ?;";
		
		Connection conn = JDBCUtils.getMysqlConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setObject(1, comment.getPid());
		ps.setObject(2, 1);
		ps.setObject(3, comment.getMemberName());
		ps.setObject(4, comment.getCont());
		ps.setObject(5, 0);
		ps.setObject(6, comment.getArticleId());
		
		int count = ps.executeUpdate();
		
		//更改父节点为非叶子节点
		ps = conn.prepareStatement(updateParentLeadSql);
		ps.setObject(1, 1);
		ps.setObject(2, comment.getPid());
		count = ps.executeUpdate();
		JDBCUtils.close(ps, conn);
		
		return count;
	}
	
	/**
	 * 根据文章ID获取文章的评论列表
	 * @return
	 */
	public List<Comment> findCommentList(String articleId) throws Exception{

		List<Comment> comments = new ArrayList<Comment>();
		Connection conn = JDBCUtils.getMysqlConn();
		Comment comment = null;
		String sql = "select * from t_comment where pid = 0 and article_id =?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, articleId);

		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			comment = new Comment();
			comment.setCont(rs.getString("cont"));
			comment.setId(rs.getInt("id"));
			comment.setMemberName(rs.getString("member_name"));
			comment.setPdate(rs.getDate("pdate"));
			comment.setIsleaf(rs.getInt("isleaf"));
			comment.setArticleId(rs.getInt("article_id"));
			comments.add(comment);
			
			System.out.println(rs.getString("cont"));
			tree(conn, rs.getInt("id"), 1,comment.getChildComments());
		}
		JDBCUtils.close(ps,conn);
		return comments;
	}
	
	private void tree(Connection conn, int id, int level,List<Comment> comments) throws Exception{
		
		StringBuffer strPre = new StringBuffer("");
		for(int i=0; i<level; i++) {
			strPre.append("    ");
		}
		
		Comment comment = null;
		String sql = "select * from t_comment where pid = " + id;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			comment = new Comment();
			comment.setCont(rs.getString("cont"));
			comment.setId(rs.getInt("id"));
			comment.setMemberName(rs.getString("member_name"));
			comment.setPdate(rs.getDate("pdate"));
			comment.setIsleaf(rs.getInt("isleaf"));
			comment.setArticleId(rs.getInt("article_id"));
			comments.add(comment);
			
			System.out.println(strPre + rs.getString("cont"));
			if(rs.getInt("isleaf") != 0)
				tree(conn, rs.getInt("id"), level + 1,comment.getChildComments());
		}
		if(rs != null) {
			rs.close();
			rs = null;
		}
		if(stmt != null) {
			stmt.close();
			stmt = null;
		}
	}
}
