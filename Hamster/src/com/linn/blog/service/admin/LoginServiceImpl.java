package com.linn.blog.service.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.linn.blog.entity.system.User;
import com.linn.blog.utils.JDBCUtils;

/**
 * 查询用户service
 * @author 李难难
 *
 */
public class LoginServiceImpl {

	/**
	 * 查找用户
	 * @return
	 */
	public int findUser(User user) throws Exception{
		int count = 0;
		Connection conn =null;
		PreparedStatement ps=null;
		String sql = "select count(*) _count from t_user t where t.username=? and t.password=?;";  

		conn = JDBCUtils.getMysqlConn();
		ps = conn.prepareStatement(sql);
		ps.setObject(1, user.getUsername());
		ps.setObject(2, user.getPassword());
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			count = rs.getInt("_count");
		}

		JDBCUtils.close(ps, conn);
		return count;
	}
}