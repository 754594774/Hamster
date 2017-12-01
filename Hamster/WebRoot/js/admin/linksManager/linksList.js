function addLinks(){
	$('#dlg').dialog('open').dialog('setTitle','添加友链');
	$('#fm').form('clear');
	url = 'admin/links?operation=addLinks';
}
function editLinks(){
	var row = $('#dg').datagrid('getSelected');
	if (row){
		$('#dlg').dialog('open').dialog('setTitle','编辑分类');
		$('#fm').form('load',row);
		url = 'admin/links?operation=changeLinks&id='+row.id;
	}
}
function saveLinks(){
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
function removeLinks(){
	var row = $('#dg').datagrid('getSelected');
	if (row){
		$.messager.confirm('确认','确定删除该记录?',function(r){
			if (r){
				$.post('admin/links',{id:row.id,operation:'delLinks'},function(result){
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