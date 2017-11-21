var editor;
KindEditor.ready(function(K) {
 	editor = K.create('textarea[name="content"]', {
	resizeType : 1,
	allowPreviewEmoticons : false,
	allowImageUpload : false,
	items : [
		'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
		'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
		'insertunorderedlist', '|', 'emoticons', 'image', 'link']
	});
});


/**
 * 本地预览图片
 * 
 */
function preview(obj){
	
	//获取当前fileup上传的本地路径
	var _file = $(obj).context.ownerDocument.activeElement.files[0];
	$("#previewPic").attr("src",getObjUrl(_file));
}
//获取fileup的本地浏览器对应的路径
function getObjUrl(file){
	var url = null;
	if(window.createObjectURL != undefined) {
		url = window.createObjectURL(file);
	} else if (window.URL != undefined) {
		url = window.URL.createObjectURL(file);
	} else if (window.webkitURL != undefined) {
		url = window.webkitURL.createObjectURL(false);
	}
	return url;
	
}

function addDraft(K){
	alert("保存草稿");
}

//添加
function addArticle(K) {
	editor.sync();
	$("#articleForm").form('submit', {
		type:'post',
	    url: 'admin/article?operation=addArticle',
	    data: $("#articleForm").serialize(),
	    dataType:'json', 
	  	success: function (result) {
			var jsonObj = eval('(' + result + ')');
			if (jsonObj.success){
				$.messager.show({title: '成功',msg: jsonObj.msg});
			} else {
				$.messager.show({title: '失败',msg: jsonObj.msg});
			}
	    }
	  }
	);
}



