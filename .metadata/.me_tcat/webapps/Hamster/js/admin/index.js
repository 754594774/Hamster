$(document).ready(
	function(){
		$('#dlg').dialog('close');
	}
)

/**
 * 弹出密码修改框
 */
function editPassword(){
	$('#dlg').dialog('open');
	url = 'admin/login';
}
/**
 * 保存修改
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function saveUser(){
	$('#fm').form('submit',{
		url: url,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			if (result.success){
				$('#dlg').dialog('close');		// close the dialog
				$.messager.show({
					title: 'OK',
					msg: result.msg
				});
			} else {
				$.messager.show({
					title: 'Error',
					msg: result.msg
				});
			}
		}
	});
}