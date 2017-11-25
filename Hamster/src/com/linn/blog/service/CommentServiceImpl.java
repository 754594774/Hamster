package com.linn.blog.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.linn.blog.entity.extension.Comment;
import com.linn.blog.utils.JDBCUtils;

/**
 * 文章评论service
 * @author 李难难
 *
 */
public class CommentServiceImpl {

	/**
	 * 查看所有对文章的评论
	 * （不包括对评论的回复）
	 * @return
	 */
	public List<Comment> findRootCommentAll() throws Exception{
		List<Comment> comments = new ArrayList<Comment>();
		Comment comment = null;
		Connection conn = JDBCUtils.getMysqlConn();
		String sql = "SELECT c.id,c.`member_name`,c.`cont`,c.`pdate`,a.`title` FROM t_comment c LEFT JOIN t_article a ON  c.`article_id` = a.`id` WHERE c.`pid` = 0;";	
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			comment = new Comment();
			comment.setId(rs.getInt("id"));
			comment.setMemberName(rs.getString("member_name"));
			comment.setCont(rs.getString("cont"));
			comment.setPdate(rs.getTimestamp("pdate"));
			comment.setArticleTitle("title");
			comments.add(comment);
		}
		JDBCUtils.close(ps,conn);
		return comments;
	}
	/**
	 * 通过文章id查找对文章的评论
	 */
	public List<Comment> findCommentByArticleId(String articleId) throws Exception{
		List<Comment> comments = new ArrayList<Comment>();
		Comment comment = null;
		Connection conn = JDBCUtils.getMysqlConn();
		String sql = "select * from t_comment where pid = 0 and article_id =?;";	
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, articleId);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			comment = new Comment();
			comment.setCont(rs.getString("cont"));
			comment.setId(rs.getInt("id"));
			comment.setPid(rs.getInt("pid"));
			comment.setMemberName(rs.getString("member_name"));
			comment.setPdate(rs.getTimestamp("pdate"));
			comment.setIsleaf(rs.getInt("isleaf"));
			comment.setArticleId(rs.getInt("article_id"));
			comments.add(comment);
		}
		JDBCUtils.close(ps,conn);
		return comments;
	}
	
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
		ps.setObject(2, comment.getRootid());
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
	 * 根据文章ID获取文章的评论列表树状结构
	 * 用于前台展现
	 * @return
	 */
	public List<Comment> findCommentListTree(String articleId) throws Exception{

		List<Comment> comments = findCommentByArticleId(articleId);
		Connection conn = JDBCUtils.getMysqlConn();
		for(Comment comment:comments) {
			tree(conn, comment.getId(), 1,comment.getChildComments());
		}
		JDBCUtils.close(conn);
		return comments;
	}
	
	private void tree(Connection conn, int id, int level,List<Comment> comments) throws Exception{
		StringBuffer strPre = new StringBuffer("");
		for(int i=0; i<level; i++) {
			strPre.append("    ");
		}
		
		Comment comment = null;
		String sql = "select * from t_comment where pid = " + id + " order by id asc;";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			comment = new Comment();
			comment.setCont(rs.getString("cont"));
			comment.setId(rs.getInt("id"));
			comment.setMemberName(rs.getString("member_name"));
			comment.setPdate(rs.getTimestamp("pdate"));
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
	/**
	 * 查找文章评论下面的所有回复
	 * @param rootId
	 * @return
	 */
	public List<Comment> findChildCommentList(String rootId) throws Exception{
		
		List<Comment> comments = new ArrayList<Comment>();
		Comment comment = null;
		Connection conn = JDBCUtils.getMysqlConn();
		String sql = "select * from t_comment where rootid = ? order by id asc;";	
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, rootId);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			comment = new Comment();
			comment.setCont(rs.getString("cont"));
			comment.setId(rs.getInt("id"));
			comment.setPid(rs.getInt("pid"));
			comment.setMemberName(rs.getString("member_name"));
			comment.setPdate(rs.getTimestamp("pdate"));
			comment.setIsleaf(rs.getInt("isleaf"));
			comments.add(comment);
		}
		JDBCUtils.close(ps,conn);
		return comments;
	}
}
