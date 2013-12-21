<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
    var searchForm;
	var datagrid;
	var softAddDialog;
	var softAddForm;
	var softEditDialog;
	var softEditForm;
	var cdescEdit;
	var cdescAdd;
	var showCdescDialog;
	$(function() {
	
		searchForm = $('#searchForm').form();
		
	    datagrid = $('#datagrid').datagrid({
			url : 'softAction!datagrid.action',
			title : '软件著作权列表',
			iconCls : 'icon-save',
			striped : true,
			pagination : true,
			pagePosition : 'bottom',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			idField : 'cid',
			sortName : 'csoftname',
			frozenColumns : [ [ 
			{
				title : '编号',
				field : 'cid',
				width : 150,
				checkbox : true
			}, {
				title : '证书编号',
				field : 'csoftid',
				width : 200,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				title : '软件名称',
				field : 'csoftname',
				width : 200,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}] ],
			columns : [ [ 
			{
				title : '著作权人',
				field : 'cpname',
				width : 200,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			} , {
				title : '开发完成日期',
				field : 'cdonetime',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				title : '首次发表日期',
				field : 'cfirsttime',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				title : '权利取得方式',
				field : 'cway',
				width : 200,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				title : '权利范围',
				field : 'cright',
				width : 200,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			} , {
				title : '登记号',
				field : 'ccommitid',
				width : 200,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '审核状态',
				field : 'cflag',
				formatter : function(value) {
					if (value == 1) {
						value = '未审核';
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}else{
						value = '通过审核';
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				},
				width : 100
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
			}, '-', {
				text : '更改审核状态',
				iconCls : 'icon-undo',
				handler : function() {
					changeStatus();
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

		function _search() {
			datagrid.datagrid('load', sy.serializeObject(searchForm));
		}
		function cleanSearch() {
			datagrid.datagrid('load', {});
			searchForm.find('input').val('');
		}
		
		softAddForm = $('#softAddForm').form({
			url : 'softAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					softAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});

		softAddDialog = $('#softAddDialog').show().dialog({
			title : '添加论文信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
					softAddForm.submit();
				}
			}]
		});

		softEditForm = $('#softEditForm').form({
			url : 'softAction!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					softEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});

		softEditDialog = $('#softEditDialog').show().dialog({
			title : '编辑论文信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
					softEditForm.submit();
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'softAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'softAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});
		
		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'softAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'softAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '摘要',
			modal : true,
			closed : true,
			maximizable : true
		});

	});

	function add() {
		softAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		softAddDialog.dialog('open');
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
						url : 'softAction!delete.action',
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
				url : 'softAction!showDesc.action',
				data : {
					cid : rows[0].cid
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					softEditForm.form('load', response);
					$('div.validatebox-tip').remove();
					softEditDialog.dialog('open');
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
			url : 'softAction!showDesc.action',
			data : {
				cid : row.cid
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.csummary) {
					showCdescDialog.find('div[name=csummary]').html(response.csummary);
					showCdescDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有电子凭证 ！', 'error');
				}
				$.messager.progress('close');
			}
		});
		datagrid.datagrid('unselectAll');
	}
	
	function changeStatus() {
		var node = datagrid.datagrid('getSelected');
		
		if (node) {
			$.messager.confirm('询问', '您确定要更改审核的状态？',
					function(b) {
						if (b) {
							$.ajax({
								url : 'softAction!changeFlag.action',
								data : {
									cid : node.cid,
									cstatus : node.cflag
								},
								cache : false,
								dataType : "json",
								success : function(r) {
									if (r.success) {
										datagrid.datagrid('reload');
										$.messager.show({
											msg : r.msg,
											title : '提示'
										});
										editRow = undefined;
									} else {
										$.messager.show({
											msg : '更改失败!',
											title : '提示'
										});
									}
								}
							});
						}
					});
		}
								
				
	}
</script>
</head>
<body class="easyui-layout">
	<!-- <div region="north" border="false" title="过滤条件" style="height: 90px;overflow: hidden;" align="left">
		<form id="searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th>论文名称</th>
					<td><input name="cname" style="width:315px;" /></td>
				</tr>
				<tr>
					<th>第一作者</th>
					<td><input name="cfcontactid" style="width:315px;" /><a href="javascript:void(0);" class="easyui-linkbutton" onclick="_search();">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">取消</a></td></td>
				</tr>				
			</table>
		</form>
	</div> -->
	<div region="center" border="false">
		<table id="datagrid"></table>
	</div>

	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="add();" iconCls="icon-add">增加</div>
		<div onclick="del();" iconCls="icon-remove">删除</div>
		<div onclick="edit();" iconCls="icon-edit">编辑</div>
	</div>

	<div id="softAddDialog" style="display: none;width: 600px;height: 400px;" align="center">
		<form id="softAddForm" method="post">
			<table class="tableForm">
				<tr>
					<th>证书编号</th>
					<td><input name="csoftid" class="easyui-validatebox" required="true" missingMessage="请填写证书编号" /></td>					
					<th>软件名称</th>
					<td><input name="csoftname" class="easyui-validatebox" required="true" missingMessage="请填写软件名称" /></td>
				</tr>
				<tr>
					<th>著作权人</th>
					<td><input name="cpname" class="easyui-validatebox" required="true"   missingMessage="请填写著作权人" /></td>				
					<th>开发完成日期</th>
					<td><input name="cdonetime" class="easyui-datebox" required="true"   missingMessage="请填写开发完成日期种" /></td>
				</tr>
				
				<tr>
					<th>首次发表日期</th>
					<td><input name="cfirsttime" class="easyui-datebox" required="true"   missingMessage="请填写首次发表日期" /></td>				
					<th>权利取得方式</th>
					<td><input name="cway" class="easyui-validatebox" required="true"   missingMessage="请填写权利取得方式" /></td>
				</tr>
				<tr>
					<th>权利范围</th>
					<td><input name="cright" class="easyui-validatebox" required="true"   missingMessage="请填写权利范围" /></td>				
					<th>登记号</th>
					<td><input name="ccommitid" class="easyui-validatebox" required="true"   missingMessage="请填写登记号" /></td>
				</tr>					
			</table>
		</form>
	</div>

	<div id="softEditDialog" style="display: none;width: 600px;height: 400px;" align="center">
		<form id="softEditForm" method="post">
			<input type="hidden" name="cid" />
			<table class="tableForm">
				<tr>
					<th>证书编号</th>
					<td><input name="csoftid" class="easyui-validatebox" required="true" missingMessage="请填写证书编号" /></td>					
					<th>软件名称</th>
					<td><input name="csoftname" class="easyui-validatebox" required="true" missingMessage="请填写软件名称" /></td>
				</tr>
				<tr>
					<th>著作权人</th>
					<td><input name="cpname" class="easyui-validatebox" required="true"   missingMessage="请填写著作权人" /></td>				
					<th>开发完成日期</th>
					<td><input name="cdonetime" class="easyui-datebox" required="true"   missingMessage="请填写开发完成日期种" /></td>
				</tr>
				
				<tr>
					<th>首次发表日期</th>
					<td><input name="cfirsttime" class="easyui-datebox" required="true"   missingMessage="请填写首次发表日期" /></td>				
					<th>权利取得方式</th>
					<td><input name="cway" class="easyui-validatebox" required="true"   missingMessage="请填写权利取得方式" /></td>
				</tr>
				<tr>
					<th>权利范围</th>
					<td><input name="cright" class="easyui-validatebox" required="true"   missingMessage="请填写权利范围" /></td>				
					<th>登记号</th>
					<td><input name="ccommitid" class="easyui-validatebox" required="true"   missingMessage="请填写登记号" /></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="showCdescDialog" style="display: none;overflow: auto;width: 500px;height: 400px;">
		<div name="csummary"></div>
	</div>

</body>
</html>