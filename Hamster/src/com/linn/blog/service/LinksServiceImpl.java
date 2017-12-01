package com.linn.blog.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.linn.blog.common.Const;
import com.linn.blog.entity.extension.Comment;
import com.linn.blog.entity.extension.Links;
import com.linn.blog.utils.JDBCUtils;

public class LinksServiceImpl {

	/**
	 * 查找最新的评论
	 * @return
	 */
	public List<Links> findLinks() throws Exception{
		List<Links> linkList = new ArrayList<Links>();
		Links links = null;
		Connection conn = JDBCUtils.getMysqlConn();
		String sql = "select * from t_links";	
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			links = new Links();
			links.setId(rs.getInt("id"));
			links.setName(rs.getNString("name"));
			links.setUrl(rs.getNString("url"));
			links.setRemark(rs.getNString("remark"));
			linkList.add(links);
		}
		JDBCUtils.close(ps,conn);
		return linkList;
	}

	/**
	 * 添加友链
	 * @param links
	 * @return
	 */
	public int addLinks(Links links) throws Exception{
		String sql = "insert into t_links (name,url,remark) VALUES (?,?,?);";  
		Connection conn = JDBCUtils.getMysqlConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, links.getName());
		ps.setObject(2, links.getUrl());
		ps.setObject(3, links.getRemark());
		int count = ps.executeUpdate();
		return count;
	}

	/**
	 * 修改友链
	 * @param links
	 * @return
	 */
	public int updateLinks(Links links) throws Exception{
		String sql = "update t_links set name = ?,url = ?,remark = ? where id = ?;";  
		Connection conn = JDBCUtils.getMysqlConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setObject(1, links.getName());
		ps.setObject(2, links.getUrl());
		ps.setObject(3, links.getRemark());
		ps.setObject(4, links.getId());
		int count = ps.executeUpdate();
		JDBCUtils.close(ps, conn);
		
		return count;
	}

	/**
	 * 删除友链
	 * @param id
	 * @return
	 */
	public int delLinks(String id) throws Exception {
		String sql = "delete from t_links where id = ?;";  
		Connection conn = JDBCUtils.getMysqlConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setObject(1, id);
		int count = ps.executeUpdate();
		JDBCUtils.close(ps, conn);
		
		return count;
	}
}
