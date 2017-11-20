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
    
    <title>登录管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="登录界面">
	<meta http-equiv="description" content="登录界面">
	<script src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/admin/login.js"></script>
	<link rel="stylesheet" type="text/css" href="css/login/login.css">
	
  </head>
  
  <body>
  	<div id="login">
  		<h1>登录管理</h1>
	    <form action="admin/login" id="loginForm" method="post">
			<p><input type="text" id="username" name="username" class="username" placeholder="用户名"/></p>
			<p><input type="password" id="password" name="password" class="password" placeholder="密码"/></p>
			<input type="hidden" name="operation" class="operation" value="login">
	    	<p><input type="submit" id="submit" value="登录""/></p>
	    	<p style="margin-top: 30px;">
	    		<font id="msg" color="red">${message}</font>
	    	</p>
		</form>
	</div>
  </body>
</html>
