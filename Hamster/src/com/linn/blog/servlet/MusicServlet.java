package com.linn.blog.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.linn.blog.entity.extension.Music;
import com.linn.blog.entity.system.Result;
import com.linn.blog.service.MusicServiceImpl;

public class MusicServlet extends HttpServlet {

	private MusicServiceImpl musicService = null;
	
	@Override
	public void init() throws ServletException {
		musicService = new MusicServiceImpl();
		super.init();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("operation");
		
		if (oper.equals("toMusicList")){
			toMusicList(request,response);
		} else if (oper.equals("musicList")){
			musicList(request,response);
		} else if (oper.equals("editMusic")) {
			editMusic(request,response);
		} else if (oper.equals("delMusic")) {
			delMusic(request,response);
		} else if(oper.equals("toMusicIndex")) {
			toMusicIndex(request,response);
		}
	}
	
	/**
	 * 跳转到前端音乐界面
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void toMusicIndex(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
			
		try {
			List<Music> musics = musicService.findMusicListAll();
			request.setAttribute("musics", musics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletContext sc = getServletContext(); 
		RequestDispatcher rd = sc.getRequestDispatcher("/music.jsp"); 
		rd.forward(request, response);
	}
	
	/**
	 * 跳转到音乐管理界面
	 * @param request
	 * @param response
	 */
	private void toMusicList(HttpServletRequest request,
			HttpServletResponse response) {
		ServletContext sc = getServletContext(); 
		RequestDispatcher rd = sc.getRequestDispatcher("/admin/musicManager/musicList.jsp"); 
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除音乐链接
	 * (状态改为已删除)
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void delMusic(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		Result result = new Result ();
		result.setMsg("OK");
		result.setSuccess(true);
		try {
			int count = musicService.delMudsic(id);
		} catch (Exception e) {
			result.setMsg("系统内部错误");
			result.setSuccess(false);
			e.printStackTrace();
		}finally{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			Gson g = new Gson();
			out.write(g.toJson(result));
			out.flush();
			out.close();
		}
	}

	/**
	 * 添加音乐链接
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void editMusic(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String title = request.getParameter("title").trim();
		String linkAddress = request.getParameter("linkAddress").trim();
		String displayOrder = request.getParameter("displayOrder").trim();
		
		Music music = new Music();
		music.setDisplayOrder(Integer.parseInt(displayOrder));
		music.setTitle(title);
		music.setLinkAddress(linkAddress);
		Result result = new Result();
		result.setSuccess(true);
		result.setMsg("OK");
		try {
			
			if (id == null){

				int count = musicService.addMusic(music);
			} else {
				music.setId(Integer.parseInt(id));
				int count = musicService.editMusic(music);
			}

		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("系统内部错误");
			e.printStackTrace();
		}finally{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			Gson g = new Gson();
			out.write(g.toJson(result));
			out.flush();
			out.close();
		}
	}

	/**
	 * 跳转到后台音乐管理
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void musicList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Gson g = new Gson();
		try {
			List<Music> musics = musicService.findMusicListAll();
			resultMap.put("rows", musics);
			resultMap.put("total",musics.size());
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(g.toJson(resultMap));
			out.flush();
			out.close();
		}
	}

}
