<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var searchForm;

	var datagrid;
	var patentAddDialog;
	var patentAddForm;
	var patentEditDialog;
	var patentEditForm;
	var cdescEdit;
	var cdescAdd;
	var showCdescDialog;
	$(function() {
	
		searchForm = $('#searchForm').form();
		datagrid = $('#datagrid').datagrid({
			rowStyler:function(index,row){     
			    if (index%2==0){     
			            return 'background-color:#EFEFEF;';     
			        }     
			    },
			url : 'patentAction!datagrid.action',
			title : '专利列表',
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
			frozenColumns : [ [ {
				title : '编号',
				field : 'cid',
				width : 150,
				sortable : true,
				checkbox : true
			}, {
				title : '专利名称',
				field : 'cname',
				width : 200,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			} , {
				title : '发明单位',
				field : 'ccountry',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			} ] ],
			columns : [ [  {
				title : '专利编号',
				field : 'cnumber',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}  , {
				title : '专利类型',
				field : 'cclassify',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				title : '发明设计人',
				field : 'cinvent',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			} , {
				title : '专利权人',
				field : 'cpatentee',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				title : '摘要',
				field : 'csummary',
				formatter : function(value, rowData, rowIndex) {
					return '<span class="icon-search" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span><a href="javascript:void(0);" onclick="showCdesc(' + rowIndex + ');">查看摘要</a>';
				},
				width : 150
			}, {
				title : '录入人',
				field : 'ctypeman',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				title : '录入时间',
				field : 'ctypetime',
				width : 150,
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

		
		
		patentAddForm = $('#patentAddForm').form({
			url : 'patentAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					patentAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});

		patentAddDialog = $('#patentAddDialog').show().dialog({
			title : '添加专利信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
					patentAddForm.submit();
				}
			}]
		});

		patentEditForm = $('#patentEditForm').form({
			url : 'patentAction!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					patentEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});

		patentEditDialog = $('#patentEditDialog').show().dialog({
			title : '编辑专利信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
					patentEditForm.submit();
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'patentAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'patentAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});
		
		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'patentAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'patentAction!upload.action',
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
		patentAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		patentAddDialog.dialog('open');
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
						url : 'patentAction!delete.action',
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
				url : 'patentAction!showDesc.action',
				data : {
					cid : rows[0].cid
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					patentEditForm.form('load', response);
					$('div.validatebox-tip').remove();
					patentEditDialog.dialog('open');
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
			url : 'patentAction!showDesc.action',
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
	
	function _search() {
			datagrid.datagrid('load', sy.serializeObject(searchForm));
		}
	function cleanSearch() {
			datagrid.datagrid('load', {});
			searchForm.find('input').val('');
		}
		
	function changeStatus() {
		var node = datagrid.datagrid('getSelected');
		
		if (node) {
			$.messager.confirm('询问', '您确定要更改审核的状态？',
					function(b) {
						if (b) {
							$.ajax({
								url : 'patentAction!changeFlag.action',
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
	<div region="north" border="false" title="过滤条件" style="height: 90px;overflow: hidden;" align="left">
		<form id="searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th>专利名称</th>
					<td><input name="cname" style="width:315px;" />
					<th>专利发明人</th>
					<td><input name="cinvent" style="width:315px;" />
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="_search();">过滤</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">取消</a>
					</td>
				</tr>								
			</table>
		</form>
	</div> 
	<div region="center" border="false">
		<table id="datagrid"></table>
	</div>

	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="add();" iconCls="icon-add">增加</div>
		<div onclick="del();" iconCls="icon-remove">删除</div>
		<div onclick="edit();" iconCls="icon-edit">编辑</div>
	</div>

	<div id="patentAddDialog" style="display: none;width: 600px;height: 400px;" align="center">
		<form id="patentAddForm" method="post">
			<table class="tableForm">
				<tr>
					<th>专利名称</th>
					<td><input name="cname" class="easyui-validatebox" required="true" missingMessage="请填写专利名称" /></td>					
				
					<th>专利编号</th>
					<td><input name="cnumber" class="easyui-validatebox" required="true" missingMessage="请填写专利编号" /></td>
				</tr>
				<tr>
					<th>授权国</th>
					<td><input name="ccountry" class="easyui-validatebox" required="true"   missingMessage="请填写授权国" /></td>				
					<th>专利类型</th>
					<td><input name="cclassify" class="easyui-validatebox" required="true"   missingMessage="请填写专利类型" /></td>
				</tr>
				<tr>
					<th>发明设计人</th>
					<td><input name="cinvent" class="easyui-validatebox" required="true"   missingMessage="请填写发明设计人" /></td>				
					<th>专利权人</th>
					<td><input name="cpatentee" class="easyui-validatebox" required="true"   missingMessage="请填写专利权人" /></td>
				</tr>
				<tr>
					<th>录入人</th>
					<td><input name="ctypeman" class="easyui-validatebox" required="true"   missingMessage="请填写录入人" /></td>
					<th>录入时间</th>
					<td><input name="ctypetime" class="easyui-datebox" editable="false" style="width: 155px;" /></td>
				</tr>				
				<tr>
					<th>摘要</th>
					<td colspan="4">
					<textarea id="cdescAdd" name="csummary"  rows="12" cols="80" style="width: 80%"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div id="patentEditDialog" style="display: none;width: 600px;height: 400px;" align="center">
		<form id="patentEditForm" method="post">
			<input type="hidden" name="cid" />
			<table class="tableForm">
				<tr>
					<th>专利名称</th>
					<td><input name="cname" class="easyui-validatebox" required="true" missingMessage="请填写专利名称" /></td>					
				
					<th>专利编号</th>
					<td><input name="cnumber" class="easyui-validatebox" required="true" missingMessage="请填写专利编号" /></td>
				</tr>
				<tr>
					<th>授权国</th>
					<td><input name="ccountry" class="easyui-validatebox" required="true"   missingMessage="请填写授权国" /></td>				
					<th>专利类型</th>
					<td><input name="cclassify" class="easyui-validatebox" required="true"   missingMessage="请填写专利类型" /></td>
				</tr>
				<tr>
					<th>发明设计人</th>
					<td><input name="cinvent" class="easyui-validatebox" required="true"   missingMessage="请填写发明设计人" /></td>				
					<th>专利权人</th>
					<td><input name="cpatentee" class="easyui-validatebox" required="true"   missingMessage="请填写专利权人" /></td>
				</tr>
				<tr>
					<th>录入人</th>
					<td><input name="ctypeman" class="easyui-validatebox" required="true"   missingMessage="请填写录入人" /></td>
					<th>录入时间</th>
					<td><input name="ctypetime" class="easyui-datebox" editable="false" style="width: 155px;" /></td>
				</tr>
				<tr>
					<th>摘要</th>
					<td colspan="4">
					<textarea id="cdescEdit" name="csummary"  rows="12" cols="80" style="width: 80%"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div id="showCdescDialog" style="display: none;overflow: auto;width: 500px;height: 400px;">
		<div name="csummary"></div>
	</div>

</body>
</html>