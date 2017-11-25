$(document).ready(function(){
	//加载子网格
	$('#dg').datagrid({
    	view: detailview,
    	detailFormatter:function(index,row){
    		return '<div style="padding:2px"><table class="ddv"></table></div>';
    	},
    	onExpandRow: function(index,row){
    		var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
    		ddv.datagrid({
    			url:'admin/comment?operation=childCommentList&rootid='+row.id,
    			fitColumns:true,
    			singleSelect:true,
    			rownumbers:true,
    			loadMsg:'',
    			height:'auto',
    			columns:[[
    				{field:'id',title:'id',width:30},
    				{field:'pid',title:'pid',width:30},
    				{field:'memberName',title:'ip',width:100},
    				{field:'cont',title:'留言内容',width:200},
    				{field:'pdate',title:'留言时间',width:100}
    			]],
    			onResize:function(){
    				$('#dg').datagrid('fixDetailRowHeight',index);
    			},
    			onLoadSuccess:function(){
    				setTimeout(function(){
    					$('#dg').datagrid('fixDetailRowHeight',index);
    				},0);
    			}
    		});
    		$('#dg').datagrid('fixDetailRowHeight',index);
    	}
    });
})
/**
 * 删除评论
 */
function delArticle(){
	var row = $('#dg').datagrid('getSelected');
	alert(row.id);
	if (row){
		$.messager.confirm('确认','确定删除该记录?',function(r){
			if (r){
				$.post('admin/article',{articleId:row.id,operation:'delArticle'},function(result){
					if (result.success){
						$('#dg').datagrid('reload');	// reload the Category data
					} else {
						$.messager.show({	// show error message
							title: 'Error',
							msg: result.msg
						});
					}
				},'json');
			}
		});
	}
}

/**
 * 修改评论
 */
function editComment(){
	//查询文章信息
	var row = $('#dg').datagrid('getSelected');
	alert(row.id);
	if (row){
		//跳转至添加界面
		//window.parent.$('#mainFrame').attr('src','admin/article?operation=toAddArticle&articleId=' + row.id);
	}
}

