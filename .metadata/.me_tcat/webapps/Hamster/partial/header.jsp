<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
		<meta name="renderer" content="webkit">
		<meta name="viewport"
			content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta http-equiv="Cache-Control" content="no-transform">
		<meta http-equiv="Cache-Control" content="no-siteapp">
		<meta name="keywords" content="博客系统,Blade框架,Tale">
		<meta name="description" content="博客系统,Blade框架,Tale">
		<link rel="shortcut icon"
			href="https://tale.biezhi.me/templates/themes/default/static/img/favicon.png">
		<link rel="apple-touch-icon"
			href="https://tale.biezhi.me/templates/themes/default/static/img/apple-touch-icon.png">
		<title>首页 - Tale Blog</title>
		<link href="css/index/xcode.min.css" rel="stylesheet">
		<link href="css/index/style.min.css" rel="stylesheet">
		<script src="js/jquery.min.js"></script>
		<script src="js/index/index.js"></script>

		<!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/r29/html5.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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
	<body class="bg-grey" gtools_scp_screen_capture_injected="true">
		<!--[if lt IE 8]>
<div class="browsehappy" role="dialog">
    当前网页 <strong>不支持</strong> 你正在使用的浏览器. 为了正常的访问, 请 <a href="http://browsehappy.com/" target="_blank">升级你的浏览器</a>。
</div>
<![endif]-->
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