<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Hamster Blog-Content</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="博客系统,Hamster,李难难">
	<meta http-equiv="description" content="博客系统,Hamster,李难难">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
  <jsp:include page="partial/header.jsp" />
	<%  if (request.getAttribute("articles") == null ) {%>
		<jsp:forward page="article?operation=toIndex" ></jsp:forward>
	<%}%>
		<div class="main-content index-page clearfix">
			<div class="post-lists">
				<div class="post-lists-body">
				<c:forEach items="${articles}" var="article">
					<div class="post-list-item">
						<div class="post-list-item-container">
							<div class="item-thumb bg-deepgrey" style="background-image: url(${article.descriptionPic});"></div>
							<a href="article?operation=toArticle&articleId=${article.id}">
								<div class="item-desc">
									<p>
										${article.description}
									</p>
								</div> </a>
							<div class="item-slant reverse-slant bg-deepgrey"></div>
							<div class="item-slant"></div>
							<div class="item-label">
								<div class="item-title">
									<a href="article?operation=toArticle&articleId=${article.id}">${article.title}</a>
								</div>
								<div class="item-meta clearfix">
									<div class="item-meta-ico bg-ico-image"
										style="background: url(images/bg-ico.png) no-repeat; background-size: 40px auto;"></div>
									<div class="item-meta-cat">
										<a href="https://tale.biezhi.me/category/%E9%BB%98%E8%AE%A4%E5%88%86%E7%B1%BB">
											${article.categoryName}
										</a>
									</div>
								</div>
							</div>
						</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="lists-navigator clearfix">
				<ol class="page-navigator">
					<li class="current">
						<a href="https://tale.biezhi.me/page/1">上一页</a>
					</li>
					<li>
						<a href="https://tale.biezhi.me/page/2">下一页</a>
					</li>
				</ol>
			</div>
		</div>
	<jsp:include page="partial/footer.jsp" />
  </body>
</html>
