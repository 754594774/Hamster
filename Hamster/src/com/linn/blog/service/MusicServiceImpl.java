package com.linn.blog.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.linn.blog.common.Const;
import com.linn.blog.entity.extension.Music;
import com.linn.blog.utils.JDBCUtils;

public class MusicServiceImpl {

	/**
	 * 查找音乐
	 * @return
	 */
	public List<Music> findMusicListAll() throws Exception{
		List<Music> musics = new ArrayList<Music>();
		String sql = "SELECT * FROM t_music  WHERE is_deleted = ? ORDER BY display_order ASC;";  

		Connection conn = JDBCUtils.getMysqlConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, Const.NO);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Music music = new Music();
			String title = rs.getNString("title");
			String linkAddress = rs.getNString("link_address");
			int id = rs.getInt("id");
			int displayOrder = rs.getInt("display_order");
			music.setId(id);
			music.setTitle(title);
			music.setLinkAddress(linkAddress);
			music.setDisplayOrder(displayOrder);
			musics.add(music);
		}
		
		JDBCUtils.close(ps, conn);
		return musics;
	}

	/**
	 * 添加音乐
	 * @param music
	 * @return
	 */
	public int addMusic(Music music) throws Exception{
		String sql = "insert into t_music (title,link_address,is_deleted,display_order) VALUES (?,?,?,?);";  
		Connection conn = JDBCUtils.getMysqlConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, music.getTitle());
		ps.setObject(2, music.getLinkAddress());
		ps.setObject(3, Const.NO);
		ps.setObject(4, music.getDisplayOrder());
		int count = ps.executeUpdate();
		return count;
	}

	/**
	 * 编辑音乐
	 * @param music
	 * @return
	 */
	public int editMusic(Music music) throws Exception{
		String sql = "update t_music set title = ?,link_address = ?,display_order = ? where id = ?;";  
		Connection conn = JDBCUtils.getMysqlConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setObject(1, music.getTitle());
		ps.setObject(2, music.getLinkAddress());
		ps.setObject(3, music.getDisplayOrder());
		ps.setObject(4, music.getId());
		int count = ps.executeUpdate();
		JDBCUtils.close(ps, conn);
		
		return count;
	}

	/**
	 * 删除音乐分类
	 * @param id
	 * @return
	 */
	public int delMudsic(String id) throws Exception{
		String sql = "UPDATE t_music SET is_deleted = ? WHERE id = ?;";  
		Connection conn = JDBCUtils.getMysqlConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setObject(1, Const.YES);
		ps.setObject(2, id);
		int count = ps.executeUpdate();
		JDBCUtils.close(ps, conn);
		return count;
	}
}
