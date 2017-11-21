package com.linn.blog.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.linn.blog.common.Const;
import com.linn.blog.entity.extension.Article;
import com.linn.blog.utils.JDBCUtils;

/**
 * Article service
 * @author 李难难
 *
 */
public class ArticleServiceImpl {
	/**
	 * 添加文章
	 * @param article
	 * @return
	 * @throws Exception
	 */
	public int addArticle(Article article) throws Exception{
		String sql = "INSERT INTO t_article (category_id,title,content,description," 
			+ "description_pic,last_time,is_draft) VALUES (?,?,?,?,?,?,?);";  
		
		Connection conn = JDBCUtils.getMysqlConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		
		article.setLastTime(new Date(System.currentTimeMillis()));
		
		ps.setObject(1, article.getCategoryId());
		ps.setObject(2, article.getTitle());
		ps.setClob(3, new BufferedReader(new InputStreamReader(new BufferedInputStream(
				new ByteArrayInputStream(article.getContent().getBytes("utf-8"))),"utf-8")));
		ps.setObject(4, article.getDescription());
		ps.setObject(5, article.getDescriptionPic());
		ps.setObject(6, article.getLastTime());
		ps.setObject(7, Const.NO);

		int count = ps.executeUpdate();
		JDBCUtils.close(ps, conn);
		return count;
	}
	
	/**
	 * 删除文章
	 */
	public int delArticleById (String articleId) throws Exception {
		String sql = "delete from t_article where id = ?;";  
		Connection conn = JDBCUtils.getMysqlConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setObject(1, articleId);
		int count = ps.executeUpdate();
		JDBCUtils.close(ps, conn);
		return count;
	}
	
	public long getNextId(){
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
	
	/**
	 * 查看文章列表
	 * @return
	 */
	public List<Article> findArticleList() throws Exception{
		List<Article> articles = new ArrayList<Article>();//已添加分类
		String sql = "SELECT a.id,c.name category_name,a.title,a.description,a.description_pic,a.last_time FROM t_article a JOIN t_category c ON a.category_id=c.id;";  
		Connection conn = JDBCUtils.getMysqlConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		Article article=null;
		while(rs.next()){
			article = new Article();
			int id = rs.getInt("id");
			String categoryName = rs.getNString("category_name");
			String title = rs.getNString("title");
			String description = rs.getNString("description");
			Timestamp lastTime = rs.getTimestamp("last_time");
			String descriptionPic = rs.getString("description_pic");
			
			article.setId(id);
			article.setCategoryName(categoryName);
			article.setTitle(title);
			article.setDescription(description);
			article.setDescriptionPic(descriptionPic);
			articles.add(article);
		} 
		JDBCUtils.close(ps, conn);
		return articles;
	}
	/**
	 * 查找文章详细信息
	 */
	public Article findArticleById (String articleId) throws Exception {
		
		Article article = null;
		Reader reader =null;
		StringBuilder sb = null;//从数据读取的文章内容
		Connection conn = JDBCUtils.getMysqlConn();
		String sql = "SELECT a.`id`,a.`content`,a.`description`,a.`last_time`,a.`title`,c.`name` "+
			"category_name FROM t_article a JOIN t_category c ON a.category_id=c.id WHERE a.id=? AND a.is_draft=0; ";  
		
		//查找文章信息
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, articleId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			article = new Article();
			int id= rs.getInt("id");
			String categoryName = rs.getString("category_name");
			String title = rs.getNString("title");
			Clob c = rs.getClob("content");
			Timestamp lastTime = rs.getTimestamp("last_time");
			String description = rs.getNString("description");
			
			reader = c.getCharacterStream();
			int temp = 0;
			sb = new StringBuilder();
			while((temp=reader.read())!=-1){
				sb.append((char)temp);
			}
			article.setId(id);
			article.setCategoryName(categoryName);
			article.setTitle(title);
			article.setContent(sb.toString());
			article.setLastTime(new Date(lastTime.getTime()));
			article.setDescription(description);
		}
		reader.close();
		JDBCUtils.close(conn);
		return article;
		
	}
}
