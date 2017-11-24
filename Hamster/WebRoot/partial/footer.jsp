<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>footer page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="css/index/xcode.min.css" rel="stylesheet">
	<link href="css/index/style.min.css" rel="stylesheet">
	<script src="js/jquery.min.js"></script>
	<script src="js/index/index.js"></script>
	<script src="js/headroom.min.js"></script>
	<script src="js/highlight.min.js"></script>
	<script src="js/instantclick.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
  </head>
  <body>
  	<footer id="footer" class="footer bg-white">
		<div class="footer-social">
			<div class="footer-container clearfix">
				<div class="social-list">
					<a class="social weibo" target="blank"
						href="https://github.com/754594774/Hamster">github</a>
					<a href="images/wxCode.png" class="tooltip" title="二维码">	微信	</a>	
						
				</div>
				<div id="sp" style=""></div>
			</div>
		</div>
		<div class="footer-meta">
			<div class="footer-container">
				<div class="meta-item meta-copyright">
					<div class="meta-copyright-info">
						<a href="https://tale.biezhi.me/" class="info-logo"> <img
								src="images/logo.png" alt="Tale Blog"> </a>
						<div class="info-text">
							<p>
								一生何求.
							</p>
							<p>
								Powered by
								<a href="https://github.com/otale/tale" target="_blank"
									rel="nofollow">Tale</a>
							</p>
							<p>
								© 2017
								<a href="https://github.com/biezhi">李难难</a>
							</p>
						</div>
					</div>
				</div>

				<div class="meta-item meta-posts">
					<h3 class="meta-title">
						最新文章
					</h3>
					<li>
						<a href="https://tale.biezhi.me/article/15">最近听的歌</a>
					</li>
				</div>

				<div class="meta-item meta-comments">
					<h3 class="meta-title">
						最新评论
					</h3>
					<li>
						<a href="https://tale.biezhi.me/article/2#comment-472">test：test test</a>
					</li>
				</div>
			</div>
		</div>
	  </footer>
  </body>
</html>
