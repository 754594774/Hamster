<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%> 
		<footer id="footer" class="footer bg-white">
		<div class="footer-social">
			<div class="footer-container clearfix">
				<div class="social-list">
					<a class="social weibo" target="blank"
						href="http://weibo.com/biezhi">微博</a>

					<a class="social zhihu" target="blank"
						href="https://www.zhihu.com/people/biezhi">知乎</a>

					<a class="social rss" target="blank"
						href="https://tale.biezhi.me/feed">RSS</a>

					<a class="social github" target="blank"
						href="https://github.com/biezhi">Github</a>

					<a class="social twitter" target="blank"
						href="https://twitter.com/biezhii">Twitter</a>
				</div>
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
								一生有所追求.
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

		<script src="js/headroom.min.js"></script>
		<script src="js/highlight.min.js"></script>
		<script src="js/instantclick.min.js"></script>
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

		<div id="qb-sougou-search" style="display: none; opacity: 0;">
			<p>
				搜索
			</p>
			<p class="last-btn">
				复制
			</p>
			<%--<iframe src="saved_resource.html"></iframe>
		--%></div>
	</body>
</html>