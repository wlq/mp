<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var datagrid;
	var resourceAddDialog;
	var resourceAddForm;
	var resourceEditDialog;
	var resourceEditForm;
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
			url : 'resourceAction!datagrid.action',
			title : '实物资源信息列表',
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
			}, {
				title : '实物资源名称',
				field : 'cname',
				width : 200,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			} ] ],
			columns : [ [ {
				title : '实物资源分类',
				field : 'cclassify',
				width : 200,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			} ,{
				title : '关键词',
				field : 'cinformation',           
				width : 200,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '实物资源数量',
				field : 'cmount',
				width : 100,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '实物资源描述',
				field : 'csummary',
				formatter : function(value, rowData, rowIndex) {
					return '<span class="icon-search" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span><a href="javascript:void(0);" onclick="showCdesc(' + rowIndex + ');">查看实物资源描述</a>';
				},
				width : 120
			},{
				title : '储存方式',
				field : 'cstorage',
				width : 100,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '所在单位名称',
				field : 'cunit',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '负责人',
				field : 'cprinciple',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}  , {
				title : '管理员联系方式',
				field : 'ccontactid',
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

		function _search() {
			datagrid.datagrid('load', sy.serializeObject(searchForm));
		}
		function cleanSearch() {
			datagrid.datagrid('load', {});
			searchForm.find('input').val('');
		}
		
		resourceAddForm = $('#resourceAddForm').form({
			url : 'resourceAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					resourceAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});

		resourceAddDialog = $('#resourceAddDialog').show().dialog({
			title : '添加实物资源信息信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
					resourceAddForm.submit();
				}
			}]
		});

		resourceEditForm = $('#resourceEditForm').form({
			url : 'resourceAction!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					resourceEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});

		resourceEditDialog = $('#resourceEditDialog').show().dialog({
			title : '编辑实物资源信息信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
					resourceEditForm.submit();
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'resourceAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'resourceAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});
		
		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'resourceAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'resourceAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '实物资源描述',
			modal : true,
			closed : true,
			maximizable : true
		});

	});

	function add() {
		resourceAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		resourceAddDialog.dialog('open');
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
						url : 'resourceAction!delete.action',
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
				url : 'resourceAction!showDesc.action',
				data : {
					cid : rows[0].cid
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					resourceEditForm.form('load', response);
					$('div.validatebox-tip').remove();
					resourceEditDialog.dialog('open');
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
			url : 'resourceAction!showDesc.action',
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
								url : 'resourceAction!changeFlag.action',
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

	<div id="resourceAddDialog" style="display: none;width: 600px;height: 400px;" align="center">
		<form id="resourceAddForm" method="post">
			<table class="tableForm">
				<tr>
					<th>实物资源名称</th>
					<td><input name="cname" class="easyui-validatebox" required="true" missingMessage="请填写实物资源名称" /></td>					
					<th>实物资源分类</th>
					<td>
						<select  name="cclassify">
						<option value="实验动物">实验动物</option>
						<option value="基因">基因</option>
						<option value="血清">血清</option>
						<option value="病理标本">病理标本</option>
						<option value="微生物">微生物</option>
						<option value="其他">其他</option>
						<select>
					</td>	
				</tr>
				<tr>
					<th>关键词</th>
					<td><input name="cinformation" class="easyui-validatebox" required="true"   missingMessage="请填写关键词" /></td>				
					<th>实物资源数量</th>
					<td><input name="cmount" class="easyui-validatebox" required="true"   missingMessage="请填写实物资源数量" /></td>
				</tr>				
				<tr>
					<th>管理员联系方式</th>
					<td><input name="ccontactid" class="easyui-validatebox" required="true"   missingMessage="请填写实物资源管理员与联系方式" /></td>
					<th>储存方式</th>
					<td><input name="cstorage" class="easyui-validatebox" required="true"   missingMessage="请填写储存方式" /></td>
				</tr>
				<tr>
					<th>所在单位名称</th>
					<td><input name="cunit" class="easyui-validatebox" required="true"   missingMessage="请填写所在单位名称" /></td>				
					<th>负责人</th>
					<td><input name="cprinciple" class="easyui-validatebox" required="true"   missingMessage="请填写负责人" /></td>
				</tr>
				<tr>
					<th>实物资源描述</th>
					<td colspan="4">
					<textarea id="cdescAdd" name="csummary"  rows="12" cols="80" style="width: 80%"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div id="resourceEditDialog" style="display: none;width: 600px;height: 400px;" align="center">
		<form id="resourceEditForm" method="post">
			<input type="hidden" name="cid" />
			<table class="tableForm">
				<tr>
					<th>实物资源名称</th>
					<td><input name="cname" class="easyui-validatebox" required="true" missingMessage="请填写实物资源名称" /></td>					
					<th>实物资源分类</th>
					<td>
						<select  name="cclassify">
						<option value="实验动物">实验动物</option>
						<option value="基因">基因</option>
						<option value="血清">血清</option>
						<option value="病理标本">病理标本</option>
						<option value="微生物">微生物</option>
						<option value="其他">其他</option>
						<select>
					</td>
				</tr>
				<tr>
					<th>关键词</th>
					<td><input name="cinformation" class="easyui-validatebox" required="true"   missingMessage="请填写关键词" /></td>				
					<th>实物资源数量</th>
					<td><input name="cmount" class="easyui-validatebox" required="true"   missingMessage="请填写实物资源数量" /></td>
				</tr>				
				<tr>
					<th>管理员联系方式</th>
					<td><input name="ccontactid" class="easyui-validatebox" required="true"   missingMessage="请填写实物资源管理员与联系方式" /></td>
					<th>储存方式</th>
					<td><input name="cstorage" class="easyui-validatebox" required="true"   missingMessage="请填写储存方式" /></td>
				</tr>
				<tr>
					<th>所在单位名称</th>
					<td><input name="cunit" class="easyui-validatebox" required="true"   missingMessage="请填写所在单位名称" /></td>				
					<th>负责人</th>
					<td><input name="cprinciple" class="easyui-validatebox" required="true"   missingMessage="请填写负责人" /></td>
				</tr>
				<tr>
					<th>实物资源描述</th>
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