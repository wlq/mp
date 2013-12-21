<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var datagrid;
	var bugAddDialog;
	var bugAddForm;
	var cdescAdd;
	var cpnameAdd;
	var bugEditDialog;
	var bugEditForm;
	var cdescEdit;
	var showCdescDialog;
	$(function() {
		datagrid = $('#datagrid').datagrid({
			url : 'xinwenAction!datagrid.action',
			title : '新闻列表(弹窗修改模式)',
			iconCls : 'icon-save',
			pagination : true,
			pagePosition : 'bottom',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			fitColumns : true,
			nowrap : false,
			border : false,
			idField : 'cid',
			sortName : 'ccreatedatetime',
			sortOrder : 'desc',
			frozenColumns : [ [ {
				title : '编号',
				field : 'cid',
				width : 150,
				sortable : true,
				checkbox : true
			}, {
				title : '新闻主题',
				field : 'cname',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				title : '新闻摘要',
				field : 'cabstract',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}] ],
			columns : [ [ {
				title : '新闻创建时间',
				field : 'ccreatedatetime',
				sortable : true,
				width : 100,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				title : '新闻图片',
				field : 'cpname',
				width : 150,
				formatter : function(value, rowData, rowIndex) {
					return '<span class="icon-search" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span><a href="javascript:void(0);" onclick="showCpname(' + rowIndex + ');">查看详细</a>';
				}
			}, {
				title : '新闻内容',
				field : 'cdesc',
				width : 150,
				formatter : function(value, rowData, rowIndex) {
					return '<span class="icon-search" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span><a href="javascript:void(0);" onclick="showCdesc(' + rowIndex + ');">查看详细</a>';
				}
			} ] ],
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					add();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					del();
				}
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					edit();
				}
			}, '-', {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					datagrid.datagrid('unselectAll');
				}
			}, '-' ],
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});

		bugAddForm = $('#bugAddForm').form({
			url : 'xinwenAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					bugAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});
		
		bugEditForm = $('#bugEditForm').form({
			url : 'xinwenAction!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					bugEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});

		bugAddDialog = $('#bugAddDialog').show().dialog({
			title : '添加新闻',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
					bugAddForm.submit();
				}
			} ]
		});
		
		bugEditDialog = $('#bugEditDialog').show().dialog({
			title : '编辑新闻',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
					bugEditForm.submit();
				}
			} ]
		});
		
		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '新闻内容',
			modal : true,
			closed : true,
			maximizable : true
		});
		
		showCpnameDialog = $('#showCpnameDialog').show().dialog({
			title : '新闻图片',
			modal : true,
			closed : true,
			maximizable : true
		});
		
		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'xinwenAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'xinwenAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});
		
		cpnameAdd = $('#cpnameAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'xinwenAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx,jpg,jpeg,gif,png',
			upImgUrl : 'xinwenAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});
		
		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'xinwenAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'xinwenAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});
		
		cpnameEdit = $('#cpnameEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'xinwenAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx,jpg,jpeg,gif,png',
			upImgUrl : 'xinwenAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

	});

	function add() {
		bugAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		bugAddDialog.dialog('open');
	}
	function del() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].cid);
					}
					$.ajax({
						url : 'xinwenAction!delete.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(response) {
							datagrid.datagrid('load');
							datagrid.datagrid('unselectAll');
							$.messager.show({
								title : '提示',
								msg : '删除成功！'
							});
						}
					});
				}
			});
		} else {
			$.messager.alert('提示', '请选择要删除的记录！', 'error');
		}
	}
	function edit() {
		var rows = datagrid.datagrid('getSelections');
		if (rows.length == 1) {
			$.messager.progress({
				text : '数据加载中....',
				interval : 100
			});
			$.ajax({
				url : 'xinwenAction!showDesc.action',
				data : {
					cid : rows[0].cid
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
				    		bugEditForm.form('load',response);
		                    $('div.validatebox-tip').remove();
		                    bugEditDialog.dialog('open');
					$.messager.progress('close');
				}
			});
		} else {
			$.messager.alert('提示', '请选择一项要编辑的记录！', 'error');
		}
	}
	function showCdesc(index) {
		var rows = datagrid.datagrid('getRows');
		var row = rows[index];
		$.messager.progress({
			text : '数据加载中....',
			interval : 100
		});
		$.ajax({
			url : 'xinwenAction!showDesc.action',
			data : {
				cid : row.cid
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cdesc) {
					showCdescDialog.find('div[name=cdesc]').html(response.cdesc);
					showCdescDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有新闻描述！', 'error');
				}
				$.messager.progress('close');
			}
		});
		datagrid.datagrid('unselectAll');
	}
	
		function showCpname(index) {
		var rows = datagrid.datagrid('getRows');
		var row = rows[index];
		$.messager.progress({
			text : '数据加载中....',
			interval : 100
		});
		$.ajax({
			url : 'xinwenAction!showDesc.action',
			data : {
				cid : row.cid
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cdesc) {
					showCpnameDialog.find('div[name=cpname]').html(response.cpname);
					showCpnameDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有新闻描述！', 'error');
				}
				$.messager.progress('close');
			}
		});
		datagrid.datagrid('unselectAll');
	}
	
	
	
</script>
</head>
<body class="easyui-layout">
	<div region="center" border="false">
		<table id="datagrid"></table>
	</div>

	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="add();" iconCls="icon-add">增加</div>
		<div onclick="del();" iconCls="icon-remove">删除</div>
		<div onclick="edit();" iconCls="icon-edit">编辑</div>
	</div>

	<div id="bugAddDialog" style="display: none;width: 600px;height: 400px;" align="center">
		<form id="bugAddForm" method="post">
			<table class="tableForm">
				<tr>
					<th>新闻主题</th>
					<td><input name="cname" class="easyui-validatebox" required="true" missingMessage="请填写news名称" /></td>
					</tr>
					<tr>
					<th>新闻摘要</th>
					<td><input name="cabstract" class="easyui-validatebox" required="true" missingMessage="请填写news摘要" /></td>
					</tr>
					<tr>
					<th>创建时间</th>
					<td><input name="ccreatedatetime" class="easyui-datetimebox" editable="false" style="width: 155px;" />
					</td>

				</tr>

               <tr>
               <th>新闻图片</th>
					<td colspan="1"><textarea name="cpname" id="cpnameAdd"></textarea></td>
					</tr>
				<tr>
					<th>新闻内容</th>
					<td colspan="3"><textarea name="cdesc" id="cdescAdd"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	
		<div id="bugEditDialog" style="display: none;width: 500px;height: 300px;" align="center">
		<form id="bugEditForm" method="post">
		   <input type="hidden" name="cid" />
<table class="tableForm">
				<tr>
					<th>新闻主题</th>
					<td><input name="cname" class="easyui-validatebox" required="true" missingMessage="请填写news名称" /></td>
					</tr>
					<tr>
					<th>新闻摘要</th>
					<td><input name="cabstract" class="easyui-validatebox" required="true" missingMessage="请填写news摘要" /></td>
					</tr>
					<tr>
					<th>创建时间</th>
					<td><input name="ccreatedatetime" class="easyui-datetimebox" editable="false" style="width: 155px;" />
					</td>

				</tr>

               <tr>
               <th>新闻图片</th>
					<td colspan="1"><textarea name="cpname" id="cpnameEdit"></textarea></td>
					</tr>
				<tr>
					<th>新闻内容</th>
					<td colspan="3"><textarea name="cdesc" id="cdescEdit"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	

	<div id="showCdescDialog" style="display: none;overflow: auto;width: 500px;height: 400px;">
		<div name="cdesc"></div>
	</div>
	
   	<div id="showCpnameDialog" style="display: none;overflow: auto;width: 500px;height: 400px;">
		<div name="cpname"></div>
	</div>
</body>
</html>