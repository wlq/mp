<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var datagrid;
	var deviceAddDialog;
	var deviceAddForm;
	var deviceEditDialog;
	var deviceEditForm;
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
			url : 'deviceAction!datagrid.action',
			title : '大型科学仪器列表',
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
				checkbox : true
			},  {
				title : '仪器设备编号',
				field : 'cnumber',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '中文名称',
				field : 'ccname',
				width : 200,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '英文名称',
				field : 'cename',
				width : 200,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '仪器来源',
				field : 'csource',
				width : 200,
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
			columns : [ [ {
				title : '仪器设备负责人',
				field : 'cbackup1',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '购置(研发)时间',
				field : 'cresearchtime',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '规格型号',
				field : 'cversion',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '产地',
				field : 'cfield',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}  , {
				title : '生产厂商',
				field : 'cfactory',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				title : '价格',
				field : 'cprice',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '功能说明',
				field : 'cnote',
				formatter : function(value, rowData, rowIndex) {
					return '<span class="icon-search" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span><a href="javascript:void(0);" onclick="showCdesc(' + rowIndex + ');">查看功能说明</a>';
				},
				width : 100
			},{
				title : '仪器设备所属单位',
				field : 'cunit',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '联系方式',
				field : 'ccontactid',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
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
		
		deviceAddForm = $('#deviceAddForm').form({
			url : 'deviceAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					deviceAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});

		deviceAddDialog = $('#deviceAddDialog').show().dialog({
			title : '添加大型科学仪器信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
					deviceAddForm.submit();
				}
			}]
		});

		deviceEditForm = $('#deviceEditForm').form({
			url : 'deviceAction!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					deviceEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});

		deviceEditDialog = $('#deviceEditDialog').show().dialog({
			title : '编辑大型科学仪器信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
					deviceEditForm.submit();
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'deviceAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'deviceAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});
		
		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'deviceAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'deviceAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '功能说明',
			modal : true,
			closed : true,
			maximizable : true
		});

	});

	function add() {
		deviceAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		deviceAddDialog.dialog('open');
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
						url : 'deviceAction!delete.action',
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
				url : 'deviceAction!showDesc.action',
				data : {
					cid : rows[0].cid
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					deviceEditForm.form('load', response);
					$('div.validatebox-tip').remove();
					deviceEditDialog.dialog('open');
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
			url : 'deviceAction!showDesc.action',
			data : {
				cid : row.cid
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cnote) {
					showCdescDialog.find('div[name=cnote]').html(response.cnote);
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
								url : 'deviceAction!changeFlag.action',
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

	<div id="deviceAddDialog" style="display: none;width: 600px;height: 400px;" align="center">
		<form id="deviceAddForm" method="post">
			<table class="tableForm">
				<tr>
					<th>仪器设备编号</th>
					<td><input name="cnumber" class="easyui-validatebox" required="true" missingMessage="请填写仪器设备编号" /></td>					
					<th>仪器来源</th>
					<td>
						<select  name="csource">
						<option value="自主研发">自主研发</option>
						<option value="购置">购置</option>
						<lect>
					</td>				
				</tr>
				<tr>
					<th>中文名称</th>
					<td><input name="ccname" class="easyui-validatebox" required="true"   missingMessage="请填写中文名称" /></td>				
					<th>英文名称</th>
					<td><input name="cename" class="easyui-validatebox" required="true"   missingMessage="请填写英文名称" /></td>
				</tr>				
				<tr>
					<th>仪器设备负责人</th>
					<td><input name="cbackup1" class="easyui-validatebox" required="true"   missingMessage="请填写仪器设备负责人" /></td>				
					<th>联系方式</th>
					<td><input name="ccontactid" class="easyui-validatebox" required="true"   missingMessage="请填写联系方式" /></td>
					
				</tr>
				<tr>
					<th>规格型号</th>
					<td><input name="cversion" class="easyui-validatebox" required="true"   missingMessage="请填写规格型号" /></td>				
					<th>产地</th>
					<td><input name="cfield" class="easyui-validatebox" required="true"   missingMessage="请填写产地" /></td>
				</tr>
				<tr>
					<th>生产厂商</th>
					<td><input name="cfactory" class="easyui-validatebox" required="true"   missingMessage="请填写生产厂商" /></td>
					<th>价格</th>
					<td><input name="cprice" class="easyui-validatebox" required="true"   missingMessage="请填写价格" /></td>
				</tr>
				<tr>
					<th>购置(研发)时间</th>
					<td><input name="cresearchtime" class="easyui-datebox" editable="false" style="width: 155px;" /></td>
					
					<th>仪器设备所属单位</th>
					<td><input name="cunit" class="easyui-validatebox" required="true"   missingMessage="请填写仪器设备所属单位" /></td>
				</tr>				
				<tr>
					<th>功能说明</th>
					<td colspan="4">
					<textarea id="cdescAdd" name="cnote"  rows="12" cols="80" style="width: 80%"></textarea>
					</td>
				</tr>			
			</table>
		</form>
	</div>

	<div id="deviceEditDialog" style="display: none;width: 600px;height: 400px;" align="center">
		<form id="deviceEditForm" method="post">
			<input type="hidden" name="cid" />
			<table class="tableForm">
				<tr>
					<th>仪器设备编号</th>
					<td><input name="cnumber" class="easyui-validatebox" required="true" missingMessage="请填写仪器设备编号" /></td>					
					<th>仪器来源</th>
					<td>
						<select  name="csource">
						<option value="自主研发">自主研发</option>
						<option value="购置">购置</option>
						<lect>
					</td>
				</tr>
				<tr>
					<th>中文名称</th>
					<td><input name="ccname" class="easyui-validatebox" required="true"   missingMessage="请填写中文名称" /></td>				
					<th>英文名称</th>
					<td><input name="cename" class="easyui-validatebox" required="true"   missingMessage="请填写英文名称" /></td>
				</tr>				
				<tr>
					<th>仪器设备负责人</th>
					<td><input name="cbackup1" class="easyui-validatebox" required="true"   missingMessage="请填写仪器设备负责人" /></td>				
					<th>联系方式</th>
					<td><input name="ccontactid" class="easyui-validatebox" required="true"   missingMessage="请填写联系方式" /></td>
					
				</tr>
				<tr>
					<th>规格型号</th>
					<td><input name="cversion" class="easyui-validatebox" required="true"   missingMessage="请填写规格型号" /></td>				
					<th>产地</th>
					<td><input name="cfield" class="easyui-validatebox" required="true"   missingMessage="请填写产地" /></td>
				</tr>
				<tr>
					<th>生产厂商</th>
					<td><input name="cfactory" class="easyui-validatebox" required="true"   missingMessage="请填写生产厂商" /></td>
					<th>价格</th>
					<td><input name="cprice" class="easyui-validatebox" required="true"   missingMessage="请填写价格" /></td>
				</tr>
				<tr>
					<th>购置(研发)时间</th>
					<td><input name="cresearchtime" class="easyui-datebox" editable="false" style="width: 155px;" /></td>
					
					<th>仪器设备所属单位</th>
					<td><input name="cunit" class="easyui-validatebox" required="true"   missingMessage="请填写仪器设备所属单位" /></td>
				</tr>
				<tr>
					<th>功能说明</th>
					<td colspan="4">
					<textarea id="cdescEdit" name="cnote"  rows="12" cols="80" style="width: 80%"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div id="showCdescDialog" style="display: none;overflow: auto;width: 500px;height: 400px;">
		<div name="cnote"></div>
	</div>

</body>
</html>