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
    
    <title>${article.title }-Hamster Blog</title>
    
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
  </head>
  
  <body>
   <jsp:include page="partial/header.jsp" />
	 <article class="main-content page-page" itemscope="" itemtype="http://schema.org/Article">
	    <div class="post-header">
	        <h1 id="articleTitle" class="post-title" itemprop="name headline">
	            ${article.title}
	        </h1>
	        <div class="post-data">
	            <time datetime="${article.lastTime}" itemprop="datePublished">发布于 ${article.lastTime}</time>
	            / <a href="https://tale.biezhi.me/category/%E9%BB%98%E8%AE%A4%E5%88%86%E7%B1%BB">${article.categoryName}</a> / <a href="https://tale.biezhi.me/article/14#comments">31 条评论</a> 
	        </div>
	    </div>
	    <div id="post-content" class="post-content" itemprop="articleBody">
	        <p class="post-tags"></p>
				${article.content}
	        <p class="post-info">
	            本文由 <a href="">李难难</a> 创作，采用 <a href="https://creativecommons.org/licenses/by/4.0/" target="_blank" rel="external nofollow">知识共享署名4.0</a> 国际许可协议进行许可<br>本站文章除注明转载/出处外，均为本站原创或翻译，转载前请务必署名<br>最后编辑时间为:
	            2017/11/12 11:34
	        </p>
	    </div>
	</article>
	<div id="post-bottom-bar" class="post-bottom-bar">
	    <div class="bottom-bar-inner">
	        <div class="bottom-bar-items social-share left">
	            <span class="bottom-bar-item">Share : </span>
	            <span class="bottom-bar-item bottom-bar-facebook"><a href="https://www.facebook.com/sharer/sharer.php?u=https://tale.biezhi.me/article/14" target="_blank" title="步步向上的一年" rel="nofollow">facebook</a></span>
	            <span class="bottom-bar-item bottom-bar-twitter"><a href="https://twitter.com/intent/tweet?url=https://tale.biezhi.me/article/14&amp;text=%E6%AD%A5%E6%AD%A5%E5%90%91%E4%B8%8A%E7%9A%84%E4%B8%80%E5%B9%B4" target="_blank" title="步步向上的一年" rel="nofollow">Twitter</a></span>
	            <span class="bottom-bar-item bottom-bar-weibo"><a href="http://service.weibo.com/share/share.php?url=https://tale.biezhi.me/article/14&amp;title=%E6%AD%A5%E6%AD%A5%E5%90%91%E4%B8%8A%E7%9A%84%E4%B8%80%E5%B9%B4" target="_blank" title="步步向上的一年" rel="nofollow">Weibo</a></span>
	            <span class="bottom-bar-item bottom-bar-qrcode"><a href="https://pan.baidu.com/share/qrcode?w=300&amp;h=300&amp;url=https://tale.biezhi.me/article/14" target="_blank" rel="nofollow">QRcode</a></span>
	        </div>
	        <div class="bottom-bar-items right">
	            <span class="bottom-bar-item"><a href="https://tale.biezhi.me/article/2" title="第一篇文章">→</a></span>
	            <span class="bottom-bar-item"><a href="https://tale.biezhi.me/article/15" title="最近听的歌">←</a></span>
	            <span class="bottom-bar-item"><a href="https://tale.biezhi.me/article/14#footer">↓</a></span>
	            <span class="bottom-bar-item"><a href="https://tale.biezhi.me/article/14#">↑</a></span>
	        </div>
	    </div>
	</div>
	<div id="directory-content" class="directory-content" >
	    <div id="directory">
		    <ul>
		    	<li>
		    		<a href="#articleTitle">文章标题</a>
		    	</li>
		    	<li>
		    		<a href="#articleTitle">添加评论</a>
		    	</li>
		    	<li>
		    		<a href="#articleTitle">参与讨论</a>
		    	</li>
		    </ul>
	    </div>
	</div>
	<div id="14" class="comment-container">
	    <div id="comments" class="clearfix">
	        <span class="response"></span>
	        <div class="comment-form">
		        <textarea name="content" id="articleComment" class="form-control" placeholder="留下点什么." required="" minlength="5" maxlength="100"></textarea>
		        <input id="articleId" type="hidden" value="${article.id}">
		        <button class="submit" onclick="addComent()">提交</button>
	        </div>
	        <%--评论 --%>
	        <ul id="pn">
	        	<span style="font-size: 15px">留言区  ↓：
	        	</span>
	  		</ul>
	    </div>
	</div>
	<jsp:include page="partial/footer.jsp" />
  </body>
</html>
