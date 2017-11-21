function delArticle(){
	var row = $('#dg').datagrid('getSelected');
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