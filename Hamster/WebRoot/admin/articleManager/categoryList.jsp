<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加文章分类</title>
	<link rel="stylesheet" type="text/css" href="css/easyUi/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/easyUi/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/easyUi/demo.css">
		<style type="text/css">
		#fm{
			margin:0;
			padding:10px 30px;
		}
		.ftitle{
			font-size:14px;
			font-weight:bold;
			color:#666;
			padding:5px 0;
			margin-bottom:10px;
			border-bottom:1px solid #ccc;
		}
		.fitem{
			margin-bottom:5px;
		}
		.fitem label{
			display:inline-block;
			width:80px;
		}
	</style>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/easyUi/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/admin/articleManager/categoryList.js"></script>

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
    	<h2>文章分类列表管理</h2>
	<div style="margin:20px 0;"></div>
	
	<table id="dg" title="分类列表" class="easyui-datagrid" style="width:100%"
			url="admin/category?operation=categoryList"
			toolbar="#toolbar"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="code" width="50">分类代码</th>
				<th field="name" width="50">分类名称</th>
			</tr>
		</thead>
	</table>
	
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCategory()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCategory()">编辑</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeCategory()">删除</a>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:350px;height:200px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
		<div class="ftitle">分类信息</div>
		<form id="fm" method="post">
			<div class="fitem">
				<label>分类代码:</label>
				<input name="code" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem" >
				<label>分类名称:</label>
				<input name="name" class="easyui-validatebox" required="true">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveCategory()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
  </body>
</html>
