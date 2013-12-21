<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
    
	var datagrid;
	var noticeAddDialog;
	var noticeAddForm;
	var noticeEditDialog;
	var noticeEditForm;
	var cdescEdit;
	var cdescAdd;
	var showCdescDialog;
	$(function() {
			
		datagrid = $('#datagrid').datagrid({			
			url : 'noticeAction!datagrid.action',
			title : '通知列表',
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
			striped : true,
			sortOrder : 'desc',
			columns : [ [  {
				title : '编号',
				field : 'cid',
				width : 150,
				sortable : true,
				checkbox : true
			}, {
				title : '发信人',
				field : 'cget',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			} , {
				title : '通知内容',
				field : 'cdatei',
				width : 150,
				formatter : function(value, rowData, rowIndex) {
					return '<span class="icon-search" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span><a href="javascript:void(0);" onclick="showCdesc(' + rowIndex + ');">查看通知</a>';
				}
			}, {
				title : '收信人',
				field : 'csend',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				title : '发送时间',
				field : 'csendtime',
				width : 150,				
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
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

		
		
		noticeAddForm = $('#noticeAddForm').form({
			url : 'noticeAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					noticeAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});

		noticeAddDialog = $('#noticeAddDialog').show().dialog({
			title : '发送通知',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
					noticeAddForm.submit();
				}
			}]
		});

		noticeEditForm = $('#noticeEditForm').form({
			url : 'noticeAction!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					noticeEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});

		noticeEditDialog = $('#noticeEditDialog').show().dialog({
			title : '编辑通知',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
					noticeEditForm.submit();
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'noticeAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'noticeAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});
		
		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'noticeAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'noticeAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '通知',
			modal : true,
			closed : true,
			maximizable : true
		});

	});
	

	function add() {
		noticeAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		noticeAddDialog.dialog('open');
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
						url : 'noticeAction!delete.action',
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
				url : 'noticeAction!showDesc.action',
				data : {
					cid : rows[0].cid
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					noticeEditForm.form('load', response);
					$('div.validatebox-tip').remove();
					noticeEditDialog.dialog('open');
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
			url : 'noticeAction!showDesc.action',
			data : {
				cid : row.cid
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cdatei) {
					showCdescDialog.find('div[name=cdatei]').html(response.cdatei);
					showCdescDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有内容 ！', 'error');
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

	<div id="noticeAddDialog" style="display: none;width: 600px;height: 400px;" align="center">
		<form id="noticeAddForm" method="post">
			<table class="tableForm">
				
				<tr>
					<th>收信人</th>
					<td><input name="cget" class="easyui-validatebox" required="true"   missingMessage="请填写收信人" /></td>
				
					<th>发送时间</th>
					<td><input name="csendtime" class="easyui-datebox" editable="false" style="width: 155px;" /></td>
				</tr>
				
				<tr>
					<th>通知内容</th>
					<td colspan="3">
					<textarea id="cdescAdd" name="cdatei"  rows="12" cols="80" style="width: 80%"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div id="noticeEditDialog" style="display: none;width: 600px;height: 400px;" align="center">
		<form id="noticeEditForm" method="post">
			<input type="hidden" name="cid" />
			<table class="tableForm">
				
				<tr>
					<th>收信人</th>
					<td><input name="cget" class="easyui-validatebox" required="true"   missingMessage="请填写收信人" /></td>
				
					<th>发送时间</th>
					<td><input name="csendtime" class="easyui-datebox" editable="false" style="width: 155px;" /></td>
				</tr>
				
				<tr>
					<th>通知内容</th>
					<td colspan="3">
					<textarea id="cdescEdit" name="cdatei"  rows="12" cols="80" style="width: 80%"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div id="showCdescDialog" style="display: none;overflow: auto;width: 500px;height: 400px;">
		<div name="cdatei"></div>
	</div>

</body>
</html>