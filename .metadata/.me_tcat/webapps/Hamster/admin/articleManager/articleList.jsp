<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>文章列表</title>
	<link rel="stylesheet" type="text/css" href="css/easyUi/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/easyUi/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/easyUi/demo.css">

	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/easyUi/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/admin/articleManager/articleList.js"></script>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    	<h2>文章列表管理</h2>
	<div style="margin:20px 0;"></div>
	
	<table id="dg" title="分类列表" class="easyui-datagrid" style="width:100%"
			url="admin/article?operation=articleList"
			toolbar="#toolbar"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="categoryName" width="50">所属分类</th>
				<th field="title" width="50">标题</th>
				<th field="description" width="50">简介</th>
				<th field="lastTime" width="50">修改时间</th>
			</tr>
		</thead>
	</table>
	
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addArticle()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editArticle()">编辑</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delArticle()">删除</a>
	</div>
	

  </body>
</html>
