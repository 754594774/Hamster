$(document).ready(function(){
	initData();
	initEvent();
});

//初始化事件
function initEvent(){
	//获取所有评论框
	var textarea = $("#pn").find("textarea.hf-text");
    //获取评论框所在div
	//var hf = $(".hf");
    //添加css样式
    //焦点事件
	textarea.focus(
		function(e){
			var hf = $(e.target).parent(".hf");
			hf.addClass('hf-on');
			var area = $(e.target);
		
			if(area.val() == '评论…'){
				area.val('');
			}
		}			
	)
	
	//去除评论框css样式
	//失焦事件
	textarea.blur(
		function(e){
			if(this.value==''){
				var hf = $(e.target).parent(".hf");
				hf.removeClass('hf-on');
				var area = $(e.target);
				area.val('评论…');	
			}	
		}			
	)
	
	//回复按钮
	//var textBtn=$(".hf-btn");
	//var textNub=$(".hf-nub");
	//键盘事件
	textarea.keyup(
		function(e){
			var textBtn = $(e.target).siblings(".hf-btn");
			var textNub = $(e.target).siblings(".hf-nub");
			var area = $(e.target);
			var len=area.val().length;
			if(len==0 /*|| len>100*/){
				textBtn.removeClass("hf-btn-on");
			}else{
				textBtn.addClass("hf-btn-on");
				/*this.style.color="#333";	*/						
			}
			textNub.text((len+"/100"));
		}			
	)
	
	//回复按钮点击事件，添加评论
	$(".hf-btn").click(
		function(e){
			
			//textarea标签，存储评论信息json
			var pComment= $(e.target).prev();
			var commentJson = pComment.val();
			var obj = jQuery.parseJSON(commentJson);
			var id = obj.id;
			var cont = $(e.target).siblings(".hf-text").val();
			var articleId = obj.articleId;
						
			$.post("comment",
			  {
				operation:"addComent",
			    pid:id,
			    memberName:"游客(" + returnCitySN.cip + ")",
			    cont:cont,
			    articleId:articleId
			  },
			  function(result){
			    window.location.reload();
			  });
		}
	)
	//回复留言
	$("#pn").find(".comment-dele").click(
		function(e){
			//名字
			var user= $(e.target).parent().prev().find(".user");
			//点击的innerHTML
			var txt=$(e.target).text();
			//判断当前点击的是否为回复
			if(txt=="回复"){
				var area = $(e.target).parents(".list0").find(".hf-text");
				//评论框触发焦点事件 也就是变高
				area.focus();
				//内容变为回复+当前人的名字
				area.val(" @"+user.text() + "：");
				//调用键盘事件
				area.keyup();
			}
		}
	)
}

//初始化数据
function initData(){
	
	var articleId = $("#articleId").val();
		$.post(
 		"comment", 
 		{
			operation : 'getComments',
			articleId : articleId
		}, 
		function(result) {
			var obj = jQuery.parseJSON(result);
			
			var html = '';
			for(var i=0;i<obj.length;i++){
				var childStr = '';
				//评论
				var comment = obj[i];
				//回复的子评论集合
				if(comment.isleaf != 0) {
					str = '';
					childStr += '<div class="comment-list">';
					tree(comment.childComments,comment.memberName);
					childStr += (str + '</div>');
				}
				html += 
					'<li class="list0">' + 
				   		'<div class="head"><img src="images/unicorn.png" alt=""/></div>' + 
				   		'<div class="content">' + 
				   		'<p class="text"><span class="name">' + comment.memberName + '：</span>'+ comment.cont +'</p>' +   
				   		childStr + 
				   		'<div class="hf">' +
				   			'<textarea type="text" class="hf-text" autocomplete="off" maxlength="100">评论…</textarea>' +
				   		    '<textarea style="display:none">' + JSON.stringify(comment) + '</textarea>' + 
				   			'<button class="hf-btn">回复</button>' +
				   			'<span class="hf-nub">0/100</span>' +
				   		'</div>' +
				   		'</div>' +
					'</li>'
			}
			
			//生成评论添加进页面元素
			$("#pn").append(html);
			initEvent();
		});
	
	var str = '';
	function tree(comments,toUser){
		for(var i=0;i<comments.length;i++){
			var comment = comments[i];
			str +=
	   			'<div class="comment" user="self">' + 
		   			'<div class="comment-left"><img src="images/unicorn.png" alt=""/></div>' + 
		   			'<div class="comment-right">' + 
		   				'<div class="comment-text"><span class="user">' + comment.memberName +'</span>' + '：'+ comment.cont +'</div>' + 
		   				'<div class="comment-date">' + comment.pdate + 
		   				 	'<textarea style="display:none">' + JSON.stringify(comment) + '</textarea>' + 
			   				'<a class="comment-dele" href="javascript:;">回复</a>' +
		   				'</div>' + 
		   			'</div>' +
		   		'</div>';
			if(comment.isleaf != 0) {
				tree(comment.childComments,comment.memberName)
			}
		}
	};
}

/**
 * 添加评论
 */
function addComent(){
	
	$.post("comment",
	  {
		operation:"addComent",
	    pid:"0",
	    memberName:"游客(" + returnCitySN.cip + ")",
	    qq:"无",
	    cont:$("#articleComment").val(),
	    articleId:$("#articleId").val()
	  },
	  function(result){
	    window.location.reload();
	  });
}

//获取当前时间回复评论时调用
function getTime(){
	var t=new Date();
	var y=t.getFullYear();
	var m=t.getMonth()+1;
	var d=t.getDate();
	var h=t.getHours();
	var mi=t.getMinutes();
	m=m<10?"0"+m:m;
	d=d<10?"0"+d:d;
	h=h<10?"0"+h:h;
	mi=mi<10?"0"+mi:mi;
	return y+"-"+m+"-"+d+""+h+":"+mi;
}