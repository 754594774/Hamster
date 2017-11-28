package com.linn.blog.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.linn.blog.common.Const;
import com.linn.blog.entity.extension.Category;
import com.linn.blog.utils.JDBCUtils;

/**
 * 文章分类service
 * @author Administrator
 *
 */
public class CategoryServiceImpl {

	/**
	 * 删除分类
	 * @param categoryId
	 * @throws Exception
	 */
	public int delCategory(String categoryId) throws Exception{
		Connection conn = JDBCUtils.getMysqlConn();
		String slq1 = "SELECT COUNT(*) articleCount FROM t_article WHERE category_id = ? AND is_deleted = ?;";
		PreparedStatement ps = conn.prepareStatement(slq1);
		ps.setObject(1, categoryId);
		ps.setObject(2, Const.NO);
		ResultSet rs = ps.executeQuery();
		int count = 0;
		while(rs.next()){
			count = rs.getInt("articleCount");
		}
		if(count > 0) {
			return -1;
		}
		
		String sql = "update t_category set is_deleted = ? where id = ?;";  
		ps = conn.prepareStatement(sql);
		
		ps.setObject(1, Const.YES);
		ps.setObject(2, categoryId);
		count = ps.executeUpdate();
		JDBCUtils.close(ps, conn);
		return count;
	}
	
	/**
	 * 更新分类信息
	 * @param category
	 * @throws Exception
	 */
	public int updateCategory(Category category) throws Exception{
		String sql = "update t_category set code = ?,name = ?,is_deleted = ? where id = ?;";  
		Connection conn = JDBCUtils.getMysqlConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		String id = String.valueOf(category.getId());
		String code = category.getCode();
		String name = category.getName();
		
		ps.setObject(1, code);
		ps.setObject(2, name);
		ps.setObject(3, Const.NO);
		ps.setObject(4, id);
		int count = ps.executeUpdate();
		JDBCUtils.close(ps, conn);
		
		return count;
	}
	/**
	 * 查找分类列表
	 * @return
	 */
	public List<Category> findCategoryList() throws Exception{
		List<Category> categorys = new ArrayList<Category>();
		String sql = "select * from t_category where is_deleted = ?;";  

		Connection conn = JDBCUtils.getMysqlConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, Const.NO);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Category category = new Category();
			String code = rs.getNString("code");
			String name = rs.getNString("name");
			int id = rs.getInt("id");
			category.setCode(code);
			category.setName(name);
			category.setId(id);
			categorys.add(category);
		}
		
		JDBCUtils.close(ps, conn);
		return categorys;
	}
	
	/**
	 * 添加分类
	 * @param article
	 * @throws Exception
	 */
	public int addCategory(Category category) throws Exception {
		
		String sql = "insert into t_category (code,name,is_deleted) VALUES (?,?,?);";  
		Connection conn = JDBCUtils.getMysqlConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, category.getCode());
		ps.setObject(2, category.getName());
		ps.setObject(3, Const.NO);
		int count = ps.executeUpdate();
		return count;
	}
	
}
