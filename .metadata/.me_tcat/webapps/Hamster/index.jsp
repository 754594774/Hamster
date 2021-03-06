<%@ include file="/partial/header.jsp"%>
<%  if (request.getAttribute("articles") == null ) {%>
	<jsp:forward page="index?operation=toIndex" ></jsp:forward>
<%}%>
		<div class="main-content index-page clearfix">
			<div class="post-lists">
				<div class="post-lists-body">
				<c:forEach items="${articles}" var="article">
					<div class="post-list-item">
						<div class="post-list-item-container">
							<div class="item-thumb bg-deepgrey" style="background-image: url(${article.introPicName});"></div>
							<a href="article?operation=toArticle&articleId=${article.id}">
								<div class="item-desc">
									<p>
										${article.intro}
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
						<a href="https://tale.biezhi.me/page/1">1</a>
					</li>
					<li>
						<a href="https://tale.biezhi.me/page/2">2</a>
					</li>

					<li class="next">
						<a href="https://tale.biezhi.me/page/2">→</a>
					</li>
				</ol>
			</div>
		</div>
<%@ include file="/partial/footer.jsp"%>