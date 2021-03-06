package com.linn.blog.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.linn.blog.entity.system.Result;
import com.linn.blog.entity.system.User;
import com.linn.blog.utils.JDBCUtils;

/**
 * 查询用户service
 * @author 李难难
 *
 */
public class UserServiceImpl {

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public int changeUser (User user) throws Exception{
		
		Connection conn =null;
		PreparedStatement ps=null;
		String sql = "update t_user set password = ? where username = ?;";  
		
		conn = JDBCUtils.getMysqlConn();
		ps = conn.prepareStatement(sql);
		
		ps.setObject(1, user.getPassword());
		ps.setObject(2, user.getUsername());
		int count = ps.executeUpdate();
		return count;
	}
	/**
	 * 查找用户
	 * @return
	 */
	public User findUser(String username,String password) throws Exception{
		User user = null;
		int count = 0;
		Connection conn =null;
		PreparedStatement ps=null;
		String sql = "select * from t_user t where t.username=? and t.password=?;";  

		conn = JDBCUtils.getMysqlConn();
		ps = conn.prepareStatement(sql);
		ps.setObject(1, username);
		ps.setObject(2, password);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			user = new User();
			user.setId(rs.getInt("id"));
			user.setPassword(rs.getString("password"));
			user.setUsername(rs.getString("username"));
		}

		JDBCUtils.close(ps, conn);
		return user;
	}
}
