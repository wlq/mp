<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var datagrid;
	var empiricalResearchAddDialog;
	var empiricalResearchAddForm;
	var empiricalResearchEditDialog;
	var empiricalResearchEditForm;
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
			url : 'empiricalResearchAction!datagrid.action',
			title : '实证研究报告列表',
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
			},{
				title : '实证研究分类',
				field : 'cclassify',
				width : 120,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '名称',
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
				title : '数据集语种',
				field : 'clanguage',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '关键词',
				field : 'cckeyword',
				width : 200,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '实证研究质量说明',
				field : 'cnote',
				width : 200,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '数据存储状态',
				field : 'cstorage',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '实证研究保护期限',
				field : 'cyear',
				width : 150,
				sortable : true,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '研究单位',
				field : 'cunit',
				width : 150,
				sortable : true,
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
			},{
				title : '数据录入人',
				field : 'ctypeman',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '数据提交时间',
				field : 'ctypetime',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				title : '实证研究描述',
				field : 'cinformation',
				formatter : function(value, rowData, rowIndex) {
					return '<span class="icon-search" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span><a href="javascript:void(0);" onclick="showCdesc(' + rowIndex + ');">查看信息</a>';
				},
				width : 100
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
		
		empiricalResearchAddForm = $('#empiricalResearchAddForm').form({
			url : 'empiricalResearchAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					empiricalResearchAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});

		empiricalResearchAddDialog = $('#empiricalResearchAddDialog').show().dialog({
			title : '添加实证研究报告信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
					empiricalResearchAddForm.submit();
				}
			}]
		});

		empiricalResearchEditForm = $('#empiricalResearchEditForm').form({
			url : 'empiricalResearchAction!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					empiricalResearchEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		});

		empiricalResearchEditDialog = $('#empiricalResearchEditDialog').show().dialog({
			title : '编辑实证研究报告信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
					empiricalResearchEditForm.submit();
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'empiricalResearchAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'empiricalResearchAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});
		
		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'empiricalResearchAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'empiricalResearchAction!upload.action',
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
		empiricalResearchAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		empiricalResearchAddDialog.dialog('open');
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
						url : 'empiricalResearchAction!delete.action',
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
				url : 'empiricalResearchAction!showDesc.action',
				data : {
					cid : rows[0].cid
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					empiricalResearchEditForm.form('load', response);
					$('div.validatebox-tip').remove();
					empiricalResearchEditDialog.dialog('open');
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
			url : 'empiricalResearchAction!showDesc.action',
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
								url : 'empiricalResearchAction!changeFlag.action',
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

	<div id="empiricalResearchAddDialog" style="display: none;width: 600px;height: 400px;" align="center">
		<form id="empiricalResearchAddForm" method="post">
			<table class="tableForm">
				<tr>
					<th>实证研究分类</th>
					<td>
						<select  name="cclassify">
						<option value="常见病">常见病</option>
						<option value="慢性病">慢性病</option>
						<option value="心脑血管病">心脑血管病</option>
						<option value="肿瘤">肿瘤</option>
						<option value="营养">营养</option>
						<option value="医疗仪器">医疗仪器</option>
						<option value="综合集成示范">综合集成示范</option>
						<lect>
					</td>
				<th>名称</th>
					<td><input name="cname" class="easyui-validatebox" required="true" missingMessage="请填写名称" /></td>
				</tr>
				<tr>
					<th>数据集语种</th>
					<td>
						<select  name="clanguage">
						<option value="zh-CN(中文计算机默认)">zh-CN(中文计算机默认)</option>
						<option value="En(英语)">En(英语)</option>
						<option value="zh-HK(中文-香港)">zh-HK(中文-香港)</option>
						<option value="zh-MO(中文-澳门)">zh-MO(中文-澳门)</option>
						<option value="zh-CHS(中文)">zh-CHS(中文)</option>
						<option value="zh-SG(中文-新加坡)">zh-SG(中文-新加坡)</option>
						<option value="zh-TW(中文-台湾)">zh-TW(中文-台湾)</option>
						<option value="zh-CHT(中文繁体)">zh-CHT(中文繁体)</option>
						<lect>
					</td>
					<th>关键词</th>
					<td><input name="cckeyword" class="easyui-validatebox" required="true"   missingMessage="请填写关键词" /></td>
				</tr>				
				<tr>
					<th>实证研究质量说明</th>
					<td><input name="cnote" class="easyui-validatebox" required="true"   missingMessage="请填写实证研究质量说明" /></td>				
					<th>实证研究数据存储状态</th>
					<td>
						<select  name="cstorage">
						<option value="纸质">纸质</option>
						<option value="光盘">光盘</option>
						<option value="胶片">胶片</option>
						<option value="磁带">磁带</option>
						<option value="计算机">计算机</option>
						<option value="其他">其他</option>
						<lect>
					</td>
				</tr>
				<tr>
					<th>实证研究保护期限</th>
					<td>
						<select  name="cyear">
						<option value="0">0</option>
						<option value="1年">1年</option>
						<option value="2年">2年</option>
						<option value="3年">3年</option>
						<option value="4年">4年</option>
						<lect>
					</td>
					<th>实证研究单位</th>
					<td><input name="cunit" class="easyui-validatebox" required="true"   missingMessage="请填写实证研究单位" /></td>
				</tr>
				<tr>
					<th>联系方式</th>
					<td><input name="ccontactid" class="easyui-validatebox" required="true"   missingMessage="请填写联系方式" /></td>
					<th>数据录入人</th>
					<td><input name="ctypeman" class="easyui-validatebox" required="true"   missingMessage="请填写数据录入人" /></td>
				</tr>
				<tr>
					<th>数据提交时间</th>
					<td><input name="ctypetime" class="easyui-datebox" required="true"   missingMessage="请填写数据提交时间" /></td>
				</tr>
				<tr>
					<th>实证研究描述信息</th>
					<td colspan="4">
					<textarea id="cdescAdd" name="cinformation"  rows="12" cols="80" style="width: 80%"></textarea>
					</td>
				</tr>			
			</table>
		</form>
	</div>

	<div id="empiricalResearchEditDialog" style="display: none;width: 600px;height: 400px;" align="center">
		<form id="empiricalResearchEditForm" method="post">
			<input type="hidden" name="cid" />
			<table class="tableForm">
				<tr>
					<th>实证研究分类</th>
					<td>
						<select  name="cclassify">
						<option value="常见病">常见病</option>
						<option value="慢性病">慢性病</option>
						<option value="心脑血管病">心脑血管病</option>
						<option value="肿瘤">肿瘤</option>
						<option value="营养">营养</option>
						<option value="医疗仪器">医疗仪器</option>
						<option value="综合集成示范">综合集成示范</option>
						<lect>
					</td>
				<th>名称</th>
					<td><input name="cname" class="easyui-validatebox" required="true" missingMessage="请填写名称" /></td>
				</tr>
				<tr>
					<th>数据集语种</th>
					<td>
						<select  name="clanguage">
						<option value="zh-CN(中文计算机默认)">zh-CN(中文计算机默认)</option>
						<option value="En(英语)">En(英语)</option>
						<option value="zh-HK(中文-香港)">zh-HK(中文-香港)</option>
						<option value="zh-MO(中文-澳门)">zh-MO(中文-澳门)</option>
						<option value="zh-CHS(中文)">zh-CHS(中文)</option>
						<option value="zh-SG(中文-新加坡)">zh-SG(中文-新加坡)</option>
						<option value="zh-TW(中文-台湾)">zh-TW(中文-台湾)</option>
						<option value="zh-CHT(中文繁体)">zh-CHT(中文繁体)</option>
						<lect>
					</td>
					<th>关键词</th>
					<td><input name="cckeyword" class="easyui-validatebox" required="true"   missingMessage="请填写关键词" /></td>
				</tr>				
				<tr>
					<th>实证研究质量说明</th>
					<td><input name="cnote" class="easyui-validatebox" required="true"   missingMessage="请填写实证研究质量说明" /></td>				
					<th>实证研究数据存储状态</th>
					<td>
						<select  name="cstorage">
						<option value="纸质">纸质</option>
						<option value="光盘">光盘</option>
						<option value="胶片">胶片</option>
						<option value="磁带">磁带</option>
						<option value="计算机">计算机</option>
						<option value="其他">其他</option>
						<lect>
					</td>
				</tr>
				<tr>
					<th>实证研究保护期限</th>
					<td>
						<select  name="cyear">
						<option value="0">0</option>
						<option value="1年">1年</option>
						<option value="2年">2年</option>
						<option value="3年">3年</option>
						<option value="4年">4年</option>
						<lect>
					</td>
					<th>实证研究单位</th>
					<td><input name="cunit" class="easyui-validatebox" required="true"   missingMessage="请填写实证研究单位" /></td>
				</tr>
				<tr>
					<th>联系方式</th>
					<td><input name="ccontactid" class="easyui-validatebox" required="true"   missingMessage="请填写联系方式" /></td>
					<th>数据录入人</th>
					<td><input name="ctypeman" class="easyui-validatebox" required="true"   missingMessage="请填写数据录入人" /></td>
				</tr>
				<tr>
					<th>数据提交时间</th>
					<td><input name="ctypetime" class="easyui-datebox" required="true"   missingMessage="请填写数据提交时间" /></td>
				</tr>
				<tr>
					<th>实证研究描述信息</th>
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