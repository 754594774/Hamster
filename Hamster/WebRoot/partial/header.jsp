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
    
    <title>header page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/index/xcode.min.css" rel="stylesheet">
	<link href="css/index/style.min.css" rel="stylesheet">
	<script src="js/jquery.min.js"></script>
	<script src="js/index/index.js"></script>
		<style>
			#instantclick {
				position: fixed;
				top: 0;
				left: 0;
				width: 100%;
				pointer-events: none;
				z-index: 2147483647;
				transition: opacity .25s .1s
			}
			.instantclick-bar {
				background: #29d;
				width: 100%;
				margin-left: -100%;
				height: 2px;
				transition: all .25s
			}
		</style>
  </head>
  <body>
    	<header id="header"	class="header bg-white animated headroom--not-bottom slideDown headroom--top">
		<div class="navbar-container">
			<a href="index.jsp" class="navbar-logo"> <img src="images/logo.png" alt="Tale Blog"></a>
			<div class="navbar-menu">
				<a href="https://tale.biezhi.me/archives">归档</a>
				<a href="https://tale.biezhi.me/links">友链</a>
				<a href="https://tale.biezhi.me/about">关于</a>
			</div>
			<div class="navbar-search" onclick="">
				<span class="icon-search"></span>
				<form role="search" onsubmit="return false;">
					<span class="search-box"> <input type="text" id="search-inp" class="input" placeholder="搜索..." maxlength="30" autocomplete="off"> </span>
				</form>
			</div>
			<div class="navbar-mobile-menu" onclick="">
				<span class="icon-menu cross"><span class="middle"></span>
				</span>
				<ul>
					<li>
						<a href="https://tale.biezhi.me/archives">归档</a>
					</li>
					<li>
						<a href="https://tale.biezhi.me/links">友链</a>
					</li>
					<li>
						<a href="https://tale.biezhi.me/about">关于</a>
					</li>
				</ul>
			</div>
		</div>
	</header>
  </body>
</html>
