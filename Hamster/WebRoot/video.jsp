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
    
    <title>实验室-Hamster Blog</title>
    
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
	
	<link href="http://vjs.zencdn.net/5.19/video-js.css" rel="stylesheet">
    <script src="http://vjs.zencdn.net/ie8/1.1/videojs-ie8.min.js"></script>
    <script src="http://vjs.zencdn.net/5.19/video.js"></script>
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
	 <article class="main-content page-page" itemscope="" itemtype="http://schema.org/Article">
	    <div class="post-header">
	        <h1 id="articleTitle" class="post-title" itemprop="name headline">
	            	测试短片
	        </h1>
			<blockquote>
			<p>还没有任何介绍</p>
			</blockquote>
			<p>
				 <video id="example_video_1" class="video-js vjs-default-skin" controls preload="none" width="640" height="264" poster="http://vjs.zencdn.net/v/oceans.png" data-setup="{}">
				    <source src="http://vjs.zencdn.net/v/oceans.mp4" type="video/mp4">
				    <source src="http://vjs.zencdn.net/v/oceans.webm" type="video/webm">
				    <source src="http://vjs.zencdn.net/v/oceans.ogv" type="video/ogg">
				    <track kind="captions" src="../shared/example-captions.vtt" srclang="en" label="English"></track>
				    <!-- Tracks need an ending tag thanks to IE9 -->
				    <track kind="subtitles" src="../shared/example-captions.vtt" srclang="en" label="English"></track>
				    <!-- Tracks need an ending tag thanks to IE9 -->
				    <!-- <p class="vjs-no-js">To view this video please enable JavaScript, and consider upgrading to a web browser that <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a></p> -->
				  </video>
			</p>
	        <div class="post-data"> 
	            <time  itemprop="datePublished">发布于 2017-11-26</time> 
	        </div>
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
