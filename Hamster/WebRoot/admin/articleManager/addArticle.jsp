<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加文章</title>
 	<link rel="stylesheet" type="text/css" href="css/easyUi/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/easyUi/themes/color.css">
	<link rel="stylesheet" type="text/css" href="css/easyUi/demo.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/easyUi/jquery.easyui.min.js"></script>
	
	<link rel="stylesheet" href="js/editor/themes/default/default.css" />
	<link rel="stylesheet" href="js/editor/plugins/code/prettify.css" />
	<script charset="utf-8" src="js/editor/kindeditor-all-min.js"></script>
	<script charset="utf-8" src="js/editor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="js/editor/plugins/code/prettify.js"></script>
	<script type="text/javascript" src="js/admin/articleManager/addArticle.js"></script>

	<!-- 
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
	<form action="" method="post" name="articleForm" id="articleForm" enctype="multipart/form-data">
		<div class="easyui-panel" style="width:100%;height:160px;padding:5px;">
			<div style="width:80%;float: left;">
				<div style="padding:10px;">
						<select id="categoryId" class="easyui-combobox" name="categoryId" label="文章分类:" style="width:45%;" required="true">
						  	<c:forEach items="${categorys}" var="category">
						  		<option value="${category.id}" <c:if test="${article.categoryId eq category.id}">selected="selected"</c:if>>${category.name}</option>
			                </c:forEach>
						</select>
					<input id="title" name="title"  label="标题:" class="easyui-textbox" style="width:45%;" required="true" value="${article.title}">
				</div>
				<div style="padding:10px;">
					<input id="intro" name="description"  label="简介:" class="easyui-textbox" style="width: 90%" required="true" value="${article.description}">
				</div>
				<div  style="padding:10px;">
					<input id="introPic" name ="descriptionPic" label="简介图片:" class="easyui-filebox" data-options="onChange:function(){preview(this)},prompt:'选择简介图片...',buttonText:'&nbsp;选&nbsp;择&nbsp;'" style="width:45%;" required="true" value="${article.descriptionPic}">
				</div>
			</div>
			<div class="easyui-panel" style="width:15%;height: 140px;float: right;">
				<img id="previewPic" alt="预览" src="${article.descriptionPic}" style="width: 100%;height: 98%;" /> 
			</div>
		</div>
		<div style="margin: 10px 0">
				<textarea name="content" style="margin-top: 10px; width: 100%; height: 500px;" required="true">
						${article.content}
				</textarea>
		</div>
	    <input type="hidden" name="articleId" id="articleId" value="${article.id}">
	</form>
	<div style="margin: 10px 0;padding:10px 0;">
		<a href="javascript:void(0)" class="easyui-linkbutton c7" style="width: 100px; float: right" onclick="addDraft()">存为草稿</a>
		<label style="width: 5px; float: right">
			&nbsp
		</label>
		<a href="javascript:void(0)" class="easyui-linkbutton c6" style="width: 100px; float: right" onclick="addArticle()">发布文章</a>
	</div>
	</body>
</html>
