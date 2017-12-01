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
    
    <title>关于我-Hamster Blog</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/article/evalute.css" rel="stylesheet"></script>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="http://pv.sohu.com/cityjson?ie=utf-8"></script> 
   	<script type="text/javascript" src="js/article/evaluation.js"></script>
   	
   	<link href="css/index/xcode.min.css" rel="stylesheet">
	<link href="css/index/style.min.css" rel="stylesheet">
	<script src="js/jquery.min.js"></script>
	<script src="js/headroom.min.js"></script>
	<script src="js/highlight.min.js"></script>
	<script src="js/instantclick.min.js"></script>
  </head>
  
  <body>
	 <header id="header"	class="header bg-white animated headroom--not-bottom slideDown headroom--top">
			<div class="navbar-container">
				<a href="index.jsp" class="navbar-logo"> <img src="images/logo.png" alt="Tale Blog"></a>
				<div class="navbar-menu">
					<a href="music?operation=toMusicIndex">音乐</a>
					<a href="video.jsp">实验室</a>
					<a href="links?operation=getLinks">友链</a>
					<a href="about.jsp">关于</a>
				</div>
			</div>
	</header>
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
	<script type="text/javascript">
		var header = new Headroom(document.getElementById("header"), {
			tolerance : 10,
			offset : 80,
			classes : {
				initial : "animated",
				pinned : "slideDown",
				unpinned : "slideUp"
			}
		});
		header.init();
		$('#search-inp').keypress(function(e) {
			var key = e.which; //e.which是按键的值
				if (key == 13) {
					var q = $(this).val();
					if (q && q != '') {
						window.location.href = '/search/' + q;
					}
				}
			});
	</script>
	<script data-no-instant="">
			InstantClick.on('change', function(isInitialLoad) {
				var blocks = document.querySelectorAll('pre code');
				for ( var i = 0; i < blocks.length; i++) {
					hljs.highlightBlock(blocks[i]);
				}
				if (isInitialLoad === false) {
					if (typeof ga !== 'undefined')
						ga('send', 'pageview', location.pathname + location.search);
				}
			});
			InstantClick.init('mousedown');
	</script>
	<article class="main-content page-page">
	    <div class="post-header">
	        <h1 class="post-title" itemprop="name headline">友情链接</h1>
	        <div class="post-data">
	            <time datetime="2017-09-17" itemprop="datePublished">发布于 2017-09-17</time>
	        </div>
	    </div>
	    <div id="post-content" class="post-content"><h2>站长的小伙伴 </h2>
	<ul>
		<c:forEach items="${linksList}" var="links">
			<li>🔒 <a href="${links.url}" target="_blank">${links.name }</a></li>
		</c:forEach>
	</ul>
	<h2>链接须知</h2>
	<blockquote>
	<p>请在当页通过评论来申请友链 <br/>
	        请确定贵站可以稳定运营
	</p>

	</blockquote>

	</div>
	</article>
		<footer id="footer" class="footer bg-white">
			<div class="footer-meta">
				<div class="footer-container">
					<div class="meta-item meta-copyright">
						<div class="meta-copyright-info">
							<a href="" class="info-logo"> <img
									src="images/logo.png" alt="Tale Blog"> </a>
							<div class="info-text">
								<p>
									一生何求.
								</p>
								<p>
									Powered by Hamster</a>
								</p>
								<p>
									© 2017
									<a href="https://github.com/754594774" target="_blank">李难难</a>
								</p>
							</div>
						</div>
					</div>
					<div class="meta-item meta-posts">
						<h3 class="meta-title">
							最新文章
						</h3>
						<c:forEach items="${newestArticles}" var="newestArticle">
							<li>
								${ newestArticle.title}
							</li>
						</c:forEach>
					</div>
					<div class="meta-item meta-comments">
						<h3 class="meta-title">
							最新评论
						</h3>
						<c:forEach items="${newestComments}" var="newestComment">
							<li>
								${ newestComment.cont}
							</li>
						</c:forEach>
					</div>
				</div>
			</div>
	  	</footer>
  </body>
</html>
