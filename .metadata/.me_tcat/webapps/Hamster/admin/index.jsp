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

	<title>Full Layout - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="css/easyUi/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/easyUi/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/easyUi/demo.css">
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/easyUi/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/admin/index.js"></script>
	
	<script type="text/javascript">
		function showcontent(oper){
			if(oper=='toAddArticle' || oper=='toArticleList'){
				$('#mainFrame').attr('src','admin/article?operation=' + oper); 
			}else if(oper =='toCategoryList'){
				$('#mainFrame').attr('src','admin/category?operation=' + oper); 
			}
		}
	</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:70px;background:#B3DFDA;padding:0px;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div style="width: 50%;float: left;">
			<b><i><font size="10" color="white">Tale</font><font size="5" color="green">博客管理后台&nbsp&nbspV1.0</font></i></b>
		</div>
	    <div style="width: 40%;float: right;margin-top: 35px;">
	    	<button onclick="editPassword()" style="float: right;">修改密码</button>
	    	<form action="admin/login" method="post" style="float: right;">
				登录用户：<font size="3" color="green">${user.username}</font>
				<input type="hidden" name="operation" class="operation" value="logout">
				<input type="submit" value="退出登录">
			</form>
			
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'菜单管理'" style="width:200px;padding:10px;">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="文章管理" data-options="selected:true" style="padding:10px;">
				<a href="javascript:void(0)" onclick="showcontent('toAddArticle')" class="easyui-linkbutton" style="width:100%" data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top'">发布文章</a>
				<hr/>
				<a href="javascript:void(0)" onclick="showcontent('toArticleList')" class="easyui-linkbutton" style="width:100%" data-options="iconCls:'icon-large-shapes',size:'large',iconAlign:'top'">文章列表</a>
				<hr/>
				<a href="javascript:void(0)" onclick="showcontent('toCategoryList')" class="easyui-linkbutton" style="width:100%" data-options="iconCls:'icon-large-clipart',size:'large',iconAlign:'top'">文章分类</a>
			</div>
			<div title="音乐管理" style="padding:10px;">
				content2
			</div>
			<div title="友链管理" style="padding:10px">
				content3
			</div>
		</div>
	</div>
	<div data-options="region:'east',split:true,collapsed:false,title:'操作信息'" style="width:200px;padding:10px;">admin登陆了</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">友链：<a href="#">百度</a></div>
	<div id="content" data-options="region:'center',title:'工作台'">
		 <IFRAME name="mainFrame" src=""  height="100%" width="100%" frameBorder=0 scrolling="auto" src="" id="mainFrame"></IFRAME>
	</div>
	
	<div id="dlg" class="easyui-dialog" title="修改密码" style="width:300px;height:250px;padding:10px"
			data-options="
				buttons: [{
					text:'Ok',
					iconCls:'icon-ok',
					handler:function(){
						saveUser();
					}
				},{
					text:'Cancel',
					handler:function(){
						alert('cancel');;
					}
				}]
			">
			<form id="fm" method="post">
				<div style="margin-bottom:20px">
					<label for="username1" class="label-left">用户名:&nbsp&nbsp&nbsp</label>
					<input id="username" name="username" class="easyui-validatebox tb" data-options="required:true,validType:'length[3,10]'" 
						value="${user.username}" readonly="readonly">
				</div>
				<div style="margin-bottom:20px">
					<label for="password" class="label-left">新密码:&nbsp&nbsp&nbsp</label>
					<input id="password" name="password" class="easyui-validatebox tb" data-options="required:true,validType:'length[3,10]'">
				</div>
				<div style="margin-bottom:20px">
					<label for="confirmPassword" class="label-left">确认密码:</label>
					<input id="confirmPassword"  name="confirmPassword" class="easyui-validatebox tb" data-options="required:true,validType:'length[3,10]'">
				</div>
				<input type="hidden" name="operation" value="changePassword">
			</form>
	</div>
</body>
</html>