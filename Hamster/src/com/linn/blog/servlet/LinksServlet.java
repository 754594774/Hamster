package com.linn.blog.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.linn.blog.entity.extension.Category;
import com.linn.blog.entity.extension.Comment;
import com.linn.blog.entity.extension.Links;
import com.linn.blog.entity.system.Result;
import com.linn.blog.service.LinksServiceImpl;

public class LinksServlet extends HttpServlet {

	private LinksServiceImpl linksService = null;
	
	@Override
	public void init() throws ServletException {
		linksService = new LinksServiceImpl();
		super.init();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("operation");
		if (oper.equals("getLinks")){
			getLinks(request,response);
		} else if (oper.equals("linksList")){
			linksList(request,response);
		} else if (oper.equals("toLinksList")){
			toLinksList(request,response);
		} else if (oper.equals("addLinks")){
			addLinks(request,response);
		} else if (oper.equals("changeLinks")){
			changeLinks(request,response);
		} else if (oper.equals("delLinks")){
			delLinks(request,response);
		}
	}
		
	/**
	 * 删除友链
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void delLinks(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		Result result = new Result ();
		result.setMsg("OK");
		result.setSuccess(true);
		try {
			int count = linksService.delLinks(id);
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
	 * 修改友链
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void changeLinks(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		String remark = request.getParameter("remark");
		
		Links links = new Links();
		links.setId(Integer.parseInt(id));
		links.setName(name);
		links.setUrl(url);
		links.setRemark(remark);
		
		Result result = new Result ();
		result.setMsg("OK");
		result.setSuccess(true);
		try {
			int count = linksService.updateLinks(links);
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
	 * 添加友链
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void addLinks(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		String remark = request.getParameter("remark");
		
		Result result = new Result();
		result.setSuccess(true);
		result.setMsg("OK");
		try {
			Links links = new Links();
			links.setName(name);
			links.setUrl(url);
			links.setRemark(remark);

			int count = linksService.addLinks(links);
			
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
	 * 跳转到友链列表
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void toLinksList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/admin/linksManager/linksList.jsp").forward(request, response);
	}

	/**
	 * 友链列表
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void linksList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Links> linksList = new ArrayList<Links>();
		try {
			linksList = linksService.findLinks();
			resultMap.put("rows", linksList);
			resultMap.put("total",linksList.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			Gson g = new Gson();
			out.print(g.toJson(resultMap));
			out.flush();
			out.close();
		}
	}

	/**
	 * 查看所有友链
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getLinks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Links> linksList = new ArrayList<Links>();
		try {
			linksList = linksService.findLinks();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("linksList", linksList);	
		request.getRequestDispatcher("/links.jsp").forward(request, response);
	}
	
}
