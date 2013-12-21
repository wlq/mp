<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var datagrid;
	var trainlearnerAddDialog;
	var trainlearnerAddForm;
	var trainlearnerEditDialog;
	var trainlearnerEditForm;
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
			url : 'trainlearnerAction!datagrid.action',
			title : '现状调查报告列表',
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
				title : '序号',
				field : 'cseq',
				width : 200,
				sortable : true,
				checkbox : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				title : '人才类型',
				field : 'cstyle',
				width : 200,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			} , {
				title : '培训内容',
				field : 'ccontent',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			} ] ],
			columns : [ [  {
				title : '姓名',
				field : 'cname',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}  , {
				title : '性别',
				field : 'csex',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				title : '年龄',
				field : 'cage',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			} ,{
				title : '附件',
				field : 'ciden',
				width : 100,
				formatter : function(value, rowData, rowIndex) {
					return '<span class="icon-search" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span><a href="javascript:void(0);" onclick="showCdesc(' + rowIndex + ');">查看信息</a>';
				}
			}, {
				title : '单位',
				field : 'ccompany',
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				},
				width : 150
			}, {
				title : '职称',
				field : 'ctitle',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				title : '专业',
				field : 'cmajor',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '联系电话',
				field : 'cphone',
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				},
				width : 100
			},{
				title : '邮箱',
				field : 'cmail',
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				},
				width : 100
			},{
				title : '考核结果',
				field : 'cresult',
				formatter : function(value) {
					if (value) {
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
		
		trainlearnerAddForm = $('#trainlearnerAddForm').form({
			url : 'trainlearnerAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					trainlearnerAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});

		trainlearnerAddDialog = $('#trainlearnerAddDialog').show().dialog({
			title : '添加现状调查报告信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
					trainlearnerAddForm.submit();
				}
			}]
		});

		trainlearnerEditForm = $('#trainlearnerEditForm').form({
			url : 'trainlearnerAction!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					trainlearnerEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});

		trainlearnerEditDialog = $('#trainlearnerEditDialog').show().dialog({
			title : '编辑现状调查报告信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
					trainlearnerEditForm.submit();
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'trainlearnerAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'trainlearnerAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});
		
		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'trainlearnerAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'trainlearnerAction!upload.action',
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
		trainlearnerAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		trainlearnerAddDialog.dialog('open');
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
						url : 'trainlearnerAction!delete.action',
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
				url : 'trainlearnerAction!showDesc.action',
				data : {
					cid : rows[0].cid
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					trainlearnerEditForm.form('load', response);
					$('div.validatebox-tip').remove();
					trainlearnerEditDialog.dialog('open');
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
			url : 'trainlearnerAction!showDesc.action',
			data : {
				cid : row.cid
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.ciden) {
					showCdescDialog.find('div[name=ciden]').html(response.ciden);
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
								url : 'trainlearnerAction!changeFlag.action',
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

	<div id="trainlearnerAddDialog" style="display: none;width: 600px;height: 400px;" align="center">
		<form id="trainlearnerAddForm" method="post">
			<table class="tableForm">
				<tr>
					<th>序号</th>
					<td><input name="cseq" class="easyui-validatebox" required="true" missingMessage="请填写序号" /></td>					
				
					<th>人才类型</th>
					<td><input name="cstyle" class="easyui-validatebox" required="true" missingMessage="请填写人才类型" /></td>
				</tr>
				<tr>
					<th>培训内容</th>
					<td><input name="ccontent" class="easyui-validatebox" required="true"   missingMessage="请填写培训内容" /></td>				
					<th>姓名</th>
					<td><input name="cname" class="easyui-validatebox" required="true"   missingMessage="请填写姓名" /></td>
				</tr>
				<tr>
					<th>性别</th>
					<td><input name="csex" class="easyui-validatebox" required="true"   missingMessage="请填写性别" /></td>				
					<th>年龄</th>
					<td><input name="cage" class="easyui-validatebox" required="true"   missingMessage="请填写年龄" /></td>
				</tr>
				<tr>
					<th>考核结果</th>
					<td><input name="cresult" class="easyui-validatebox" required="true"   missingMessage="请填写考核结果" /></td>
					<th>单位</th>
					<td><input name="ccompany" class="easyui-validatebox" required="true"   missingMessage="请填写单位" /></td>
				</tr>		
				<tr>
					<th>职称</th>
					<td><input name="ctitle" class="easyui-validatebox" required="true"   missingMessage="请填写职称" /></td>
					<th>专业</th>
					<td><input name="cmajor" class="easyui-validatebox" required="true"   missingMessage="请填写专业" /></td>
				</tr>		
				<tr>
					<th>联系电话</th>
					<td><input name="cphone" class="easyui-validatebox" required="true"   missingMessage="请填写联系电话" /></td>
					<th>邮箱</th>
					<td><input name="cmail" class="easyui-validatebox" required="true"   missingMessage="请填写邮箱" /></td>
				</tr>	
				
				<tr>
					<th>附件</th>
					<td colspan="4">
					<textarea id="cdescAdd" name="ciden"  rows="12" cols="80" style="width: 80%"></textarea>
					</td>
				</tr>			
			</table>
		</form>
	</div>

	<div id="trainlearnerEditDialog" style="display: none;width: 600px;height: 400px;" align="center">
		<form id="trainlearnerEditForm" method="post">
			<input type="hidden" name="cid" />
			<table class="tableForm">
				<tr>
					<th>序号</th>
					<td><input name="cseq" class="easyui-validatebox" required="true" missingMessage="请填写序号" /></td>					
				
					<th>人才类型</th>
					<td><input name="cstyle" class="easyui-validatebox" required="true" missingMessage="请填写人才类型" /></td>
				</tr>
				<tr>
					<th>培训内容</th>
					<td><input name="ccontent" class="easyui-validatebox" required="true"   missingMessage="请填写培训内容" /></td>				
					<th>姓名</th>
					<td><input name="cname" class="easyui-validatebox" required="true"   missingMessage="请填写姓名" /></td>
				</tr>
				<tr>
					<th>性别</th>
					<td><input name="csex" class="easyui-validatebox" required="true"   missingMessage="请填写性别" /></td>				
					<th>年龄</th>
					<td><input name="cage" class="easyui-validatebox" required="true"   missingMessage="请填写年龄" /></td>
				</tr>
				<tr>
					<th>考核结果</th>
					<td><input name="cresult" class="easyui-validatebox" required="true"   missingMessage="请填写考核结果" /></td>
					<th>单位</th>
					<td><input name="ccompany" class="easyui-validatebox" required="true"   missingMessage="请填写单位" /></td>
				</tr>		
				<tr>
					<th>职称</th>
					<td><input name="ctitle" class="easyui-validatebox" required="true"   missingMessage="请填写职称" /></td>
					<th>专业</th>
					<td><input name="cmajor" class="easyui-validatebox" required="true"   missingMessage="请填写专业" /></td>
				</tr>		
				<tr>
					<th>联系电话</th>
					<td><input name="cphone" class="easyui-validatebox" required="true"   missingMessage="请填写联系电话" /></td>
					<th>邮箱</th>
					<td><input name="cmail" class="easyui-validatebox" required="true"   missingMessage="请填写邮箱" /></td>
				</tr>	
				
				<tr>
					<th>附件</th>
					<td colspan="4">
					<textarea id="cdescEdit" name="ciden"  rows="12" cols="80" style="width: 80%"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div id="showCdescDialog" style="display: none;overflow: auto;width: 500px;height: 400px;">
		<div name="ciden"></div>
	</div>

</body>
</html>