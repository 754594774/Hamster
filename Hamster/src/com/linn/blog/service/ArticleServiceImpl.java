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
	 * 查看文章列表
	 * @return
	 */
	public List<Article> findArticleNewest() throws Exception{
		List<Article> articles = new ArrayList<Article>();
		String sql = "SELECT title FROM t_article LIMIT 0,6;";  
		Connection conn = JDBCUtils.getMysqlConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		Article article=null;
		while(rs.next()){
			article = new Article();
			String title = rs.getNString("title");
			article.setTitle(title);
			articles.add(article);
		} 
		JDBCUtils.close(ps, conn);
		return articles;
	}
	/**
	 * 更新文章
	 * @param article
	 * @return
	 */
	public int updateArticle (Article article) throws Exception{
		String descPic = "";
		
		Connection conn = JDBCUtils.getMysqlConn();
		if(article.getDescriptionPic() != null && article.getDescriptionPic() != ""){
			descPic = 	"  description_pic = ?,\n";
		}
		
		String sql = "UPDATE t_article\n" +
		"SET\n" + 
		"  category_id = ?,\n" + 
		"  title = ?,\n" + 
		"  content = ?,\n" + 
		"  last_time = ?,\n" + 
		"  description = ?,\n" + 
		descPic + 
		"  is_draft = ?\n" + 
		"WHERE id = ?;";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, article.getCategoryId());
		ps.setObject(2, article.getTitle());
		ps.setObject(3, article.getContent());
		ps.setObject(4, new Timestamp(System.currentTimeMillis()));
		ps.setObject(5, article.getDescription());
		if (article.getDescriptionPic() != null && article.getDescriptionPic() != ""){
			ps.setObject(6, article.getDescriptionPic());
			ps.setObject(7, 0);
			ps.setObject(8, article.getId());
		} else {
			ps.setObject(6, 0);
			ps.setObject(7, article.getId());
		}
		int count = ps.executeUpdate();
		JDBCUtils.close(ps, conn);
		
		return count;
	}
	
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
		
		article.setLastTime(new Timestamp(System.currentTimeMillis()));
		
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
			article.setLastTime(lastTime);
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
	 * 失败返回null
	 */
	public Article findArticleById (String articleId) throws Exception {
		
		Article article = null;
		Reader reader =null;
		StringBuilder sb = null;//从数据读取的文章内容
		Connection conn = JDBCUtils.getMysqlConn();
		String sql = "SELECT a.id,a.category_id,a.content,a.description,a.description_pic,a.last_time,a.title,c.name "+
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
			String descriptionPic = rs.getNString("description_pic");
			int categoryId = rs.getInt("category_id");
		
			reader = c.getCharacterStream();
			int temp = 0;
			sb = new StringBuilder();
			while((temp=reader.read())!=-1){
				sb.append((char)temp);
			}
			article.setId(id);
			article.setCategoryId(categoryId);
			article.setCategoryName(categoryName);
			article.setTitle(title);
			article.setContent(sb.toString());
			article.setLastTime(new Timestamp(System.currentTimeMillis()));
			article.setDescription(description);
			article.setDescriptionPic(descriptionPic);
		}
		reader.close();
		JDBCUtils.close(conn);
		return article;
	}
}
