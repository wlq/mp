<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
    
	var datagrid;
	var bokuanAddDialog;
	var bokuanAddForm;
	var bokuanEditDialog;
	var bokuanEditForm;
	var cdescEdit;
	var cdescAdd;
	var showCdescDialog;
	$(function() {
	
		searchForm = $('#searchForm').form();
		
		datagrid = $('#datagrid').datagrid({			
			url : 'bokuanAction!datagrid.action',
			title : '课题经费拨款信息列表',
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
			frozenColumns : [ [ {
				title : '编号',
				field : 'cid',
				width : 150,
				sortable : true,
				checkbox : true
			}, {
				title : '拨款金额',
				field : 'cmoney',
				align : 'right',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			} , {
				title : '接收人',
				field : 'cprojectid',
				width : 250,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}] ],
			columns : [ [  {
				title : '会计凭证号',
				field : 'ccountId',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}  , {
				title : '记账时间',
				field : 'ccountTime',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				title : '票据号',
				field : 'ctickets',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '电子凭证',
				field : 'cdatei',
				width : 150,
				formatter : function(value, rowData, rowIndex) {
					return '<span class="icon-search" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span><a href="javascript:void(0);" onclick="showCdesc(' + rowIndex + ');">查看电子凭证</a>';
				}
			}] ],
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

		
		
		bokuanAddForm = $('#bokuanAddForm').form({
			url : 'bokuanAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					bokuanAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});

		bokuanAddDialog = $('#bokuanAddDialog').show().dialog({
			title : '添加拨款信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
					bokuanAddForm.submit();
				}
			}]
		});

		bokuanEditForm = $('#bokuanEditForm').form({
			url : 'bokuanAction!edit.action',			
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					alert("sdgh");
					datagrid.datagrid('reload');
					bokuanEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});

		bokuanEditDialog = $('#bokuanEditDialog').show().dialog({
			title : '编辑拨款信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
					bokuanEditForm.submit();
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'bokuanAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'bokuanAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});
		
		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'bokuanAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'bokuanAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '电子凭证',
			modal : true,
			closed : true,
			maximizable : true
		});

	});
	
	function _search() {
	        alert(sy.serializeObject(searchForm));
			//datagrid.datagrid('load', sy.serializeObject(searchForm));
	}
		
	function cleanSearch() {
			datagrid.datagrid('load', {});
			searchForm.find('input').val('');
	}

	function add() {
		bokuanAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		bokuanAddDialog.dialog('open');
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
						url : 'bokuanAction!delete.action',
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
				url : 'bokuanAction!showDesc.action',
				data : {
					cid : rows[0].cid
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					bokuanEditForm.form('load', response);
					$('div.validatebox-tip').remove();
					bokuanEditDialog.dialog('open');	
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
			url : 'bokuanAction!showDesc.action',
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
					$.messager.alert('提示', '没有电子凭证 ！', 'error');
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

	<div id="bokuanAddDialog" style="display: none;width: 600px;height: 400px;" align="center">
		<form id="bokuanAddForm" method="post">
			<table class="tableForm">
				<tr>
					<th>拨款金额</th>
					<td><input name="cmoney" class="easyui-validatebox" required="true" missingMessage="请填写拨款金额" /></td>
				
					<th>票据号</th>
					<td><input name="ctickets" class="easyui-validatebox" required="true" missingMessage="请填写票据号" /></td>					

	
				
				</tr>
				<tr>
					<th>会计凭证号</th>
					<td><input name="ccountId" class="easyui-validatebox" required="true"   missingMessage="请填写会计凭证号" /></td>
				
					<th>记账时间</th>
					<td><input name="ccountTime" class="easyui-datebox" editable="false" style="width: 155px;" /></td>						

		
				</tr>
				<tr>						
					<th>电子凭证</th>
					<td colspan="3">
					<textarea id="cdescAdd" name="cdatei"  rows="12" cols="80" style="width: 80%"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div id="bokuanEditDialog" style="display: none;width: 600px;height: 400px;" align="center">
		<form id="bokuanEditForm" method="post">
			<input type="hidden" name="cid" />
			<table class="tableForm">
				<tr>
					<th>拨款金额</th>
					<td><input name="cmoney" class="easyui-validatebox" required="true" missingMessage="请填写拨款金额" /></td>
				
					<th>票据号</th>
					<td><input name="ctickets" class="easyui-validatebox" required="true" missingMessage="请填写票据号" /></td>									
				</tr>
				<tr>
					<th>会计凭证号</th>
					<td><input name="ccountId" class="easyui-validatebox" required="true"   missingMessage="请填写会计凭证号" /></td>				
					<th>记账时间</th>
					<td><input name="ccountTime" class="easyui-datebox" editable="false" style="width: 155px;" /></td>			
				</tr>
				<tr>
					<th>电子凭证</th>
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