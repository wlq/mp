<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var datagrid;
	var applicationNoteAddDialog;
	var applicationNoteAddForm;
	var applicationNoteEditDialog;
	var applicationNoteEditForm;
	var cdescEdit;
	var cdescAdd;
	var showCdescDialog;
	$(function() {
		datagrid = $('#datagrid').datagrid({
			rowStyler:function(index,row){     
			    if (index%2==0){     
			            return 'background-color:#EFEFEF;';     
			        }     
			    },
			url : 'applicationNoteAction!datagrid.action',
			title : '关键技术应用列表',
			iconCls : 'icon-save',
			pagination : true,
			pagePosition : 'bottom',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			idField : 'cid',
			sortOrder : 'desc',
			frozenColumns : [[ {
				title : '编号',
				field : 'cid',
				width : 150,
				sortable : true,
				checkbox : true
			},{
				title : '关键技术名称',
				field : 'cname',
				width : 200,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}] ],
			columns : [ [ {
				title : '关键技术适用对象',
				field : 'csuitableid',
				width : 200,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '关键技术使用说明',
				field : 'cnote',
				width : 200,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '疗效评价',
				field : 'cevaluation',
				width : 100,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '成果鉴定',
				field : 'cidentify',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '知识产权',
				field : 'crighted',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '编写人信息',
				field : 'ccontactid',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '编写时间信息',
				field : 'ctypetime',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '关键技术概述',
				field : 'cinformation',
				formatter : function(value, rowData, rowIndex) {
					return '<span class="icon-search" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span><a href="javascript:void(0);" onclick="showCdesc(' + rowIndex + ');">查看摘要</a>';
				},
				width : 300
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
			}, '-' , {
				text : '更改审核状态',
				iconCls : 'icon-undo',
				handler : function() {
					changeStatus();
				}
			}, '-'],
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
		
		applicationNoteAddForm = $('#applicationNoteAddForm').form({
			url : 'applicationNoteAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					applicationNoteAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});

		applicationNoteAddDialog = $('#applicationNoteAddDialog').show().dialog({
			title : '添加关键技术应用信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
					applicationNoteAddForm.submit();
				}
			}]
		});

		applicationNoteEditForm = $('#applicationNoteEditForm').form({
			url : 'applicationNoteAction!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					applicationNoteEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});

		applicationNoteEditDialog = $('#applicationNoteEditDialog').show().dialog({
			title : '编辑关键技术应用信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
					applicationNoteEditForm.submit();
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'applicationNoteAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'applicationNoteAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});
		
		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'applicationNoteAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'applicationNoteAction!upload.action',
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
		applicationNoteAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		applicationNoteAddDialog.dialog('open');
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
						url : 'applicationNoteAction!delete.action',
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
				url : 'applicationNoteAction!showDesc.action',
				data : {
					cid : rows[0].cid
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					applicationNoteEditForm.form('load', response);
					$('div.validatebox-tip').remove();
					applicationNoteEditDialog.dialog('open');
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
			url : 'applicationNoteAction!showDesc.action',
			data : {
				cid : row.cid
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cinformation) {
					showCdescDialog.find('div[name=cinformation]').html(response.cinformation);
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
								url : 'applicationNoteAction!changeFlag.action',
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
					<th>负责人</th>
					<td><input name="cprojectid" style="width:315px;" /></td>
				</tr>
				<tr>
					<th>记账时间</th>
					<td><input name="ccountTimeStart" class="easyui-datetimebox" editable="false" style="width: 155px;" />至<input name="ccountTimeEnd" class="easyui-datetimebox" editable="false" style="width: 155px;" /><a href="javascript:void(0);" class="easyui-linkbutton" onclick="_search();">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">取消</a></td></td>
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

	<div id="applicationNoteAddDialog" style="display: none;width: 600px;height: 400px;" align="center">
		<form id="applicationNoteAddForm" method="post">
			<table class="tableForm">
				<tr>
					<th>关键技术名称</th>
					<td><input name="cname" class="easyui-validatebox" required="true" missingMessage="请填写关键技术名称" /></td>					
					<th>关键技术适用对象</th>
					<td><input name="csuitableid" class="easyui-validatebox" required="true" missingMessage="请填写关键技术适用对象" /></td>
				</tr>
				<tr>
					<th>关键技术使用说明</th>
					<td><input name="cnote" class="easyui-validatebox" required="true"   missingMessage="请填写关键技术使用说明" /></td>				
					<th>疗效评价</th>
					<td><input name="cevaluation" class="easyui-validatebox" required="true"   missingMessage="请填写疗效评价" /></td>
				</tr>				
				<tr>
					<th>成果鉴定</th>
					<td><input name="cidentify" class="easyui-validatebox" required="true"   missingMessage="请填写成果鉴定" /></td>				
					<th>知识产权</th>
					<td><input name="crighted" class="easyui-validatebox" required="true"   missingMessage="请填写知识产权" /></td>
				</tr>
				<tr>
					<th>编写人信息</th>
					<td><input name="ccontactid" class="easyui-validatebox" required="true"   missingMessage="请填写编写人信息" /></td>				
					<th>编写时间信息</th>
					<td><input name="ctypetime" class="easyui-datebox" required="true"   missingMessage="请填写编写时间信息" /></td>
				</tr>
				<tr>
					<th>关键技术概述</th>
					<td colspan="4">
					<textarea id="cdescAdd" name="cinformation"  rows="12" cols="80" style="width: 80%"></textarea>
					</td>
				</tr>			
			</table>
		</form>
	</div>

	<div id="applicationNoteEditDialog" style="display: none;width: 600px;height: 400px;" align="center">
		<form id="applicationNoteEditForm" method="post">
			<input type="hidden" name="cid" />
			<table class="tableForm">
				<tr>
					<th>关键技术名称</th>
					<td><input name="cname" class="easyui-validatebox" required="true" missingMessage="请填写关键技术名称" /></td>					
					<th>关键技术适用对象</th>
					<td><input name="csuitableid" class="easyui-validatebox" required="true" missingMessage="请填写关键技术适用对象" /></td>
				</tr>
				<tr>
					<th>关键技术使用说明</th>
					<td><input name="cnote" class="easyui-validatebox" required="true"   missingMessage="请填写关键技术使用说明" /></td>				
					<th>疗效评价</th>
					<td><input name="cevaluation" class="easyui-validatebox" required="true"   missingMessage="请填写疗效评价" /></td>
				</tr>				
				<tr>
					<th>成果鉴定</th>
					<td><input name="cidentify" class="easyui-validatebox" required="true"   missingMessage="请填写成果鉴定" /></td>				
					<th>知识产权</th>
					<td><input name="crighted" class="easyui-validatebox" required="true"   missingMessage="请填写知识产权" /></td>
				</tr>
				<tr>
					<th>编写人信息</th>
					<td><input name="ccontactid" class="easyui-validatebox" required="true"   missingMessage="请填写编写人信息" /></td>				
					<th>编写时间信息</th>
					<td><input name="ctypetime" class="easyui-datebox" required="true"   missingMessage="请填写编写时间信息" /></td>
				</tr>
				<tr>
					<th>关键技术概述</th>
					<td colspan="4">
					<textarea id="cdescEdit" name="cinformation"  rows="12" cols="80" style="width: 80%"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div id="showCdescDialog" style="display: none;overflow: auto;width: 500px;height: 400px;">
		<div name="cinformation"></div>
	</div>

</body>
</html>