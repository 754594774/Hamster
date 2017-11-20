function newCategory(){
	$('#dlg').dialog('open').dialog('setTitle','添加分类');
	$('#fm').form('clear');
	url = 'admin/category?operation=addCategory';
}
function editCategory(){
	var row = $('#dg').datagrid('getSelected');
	if (row){
		$('#dlg').dialog('open').dialog('setTitle','编辑分类');
		$('#fm').form('load',row);
		url = 'admin/category?operation=changeCategory&id='+row.id;
	}
}
function saveCategory(){
	$('#fm').form('submit',{
		url: url,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			if (result.success){
				$('#dlg').dialog('close');		// close the dialog
				$('#dg').datagrid('reload');	// reload the Category data
			} else {
				$.messager.show({
					title: 'Error',
					msg: result.msg
				});
			}
		}
	});
}
function removeCategory(){
	var row = $('#dg').datagrid('getSelected');
	if (row){
		$.messager.confirm('确认','确定删除该记录?',function(r){
			if (r){
				$.post('admin/category',{id:row.id,operation:'delCategory'},function(result){
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