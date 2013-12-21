<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript" charset="UTF-8">
	var editRow;
	var editType;/*add or edit or undefined*/
	var treegrid;
	var iconData;
	$(function() {

		iconData = [ {
			value : '',
			text : '默认'
		}, {
			value : 'icon-add',
			text : 'icon-add'
		}, {
			value : 'icon-edit',
			text : 'icon-edit'
		}, {
			value : 'icon-remove',
			text : 'icon-remove'
		}, {
			value : 'icon-save',
			text : 'icon-save'
		}, {
			value : 'icon-cut',
			text : 'icon-cut'
		}, {
			value : 'icon-ok',
			text : 'icon-ok'
		}, {
			value : 'icon-no',
			text : 'icon-no'
		}, {
			value : 'icon-cancel',
			text : 'icon-cancel'
		}, {
			value : 'icon-reload',
			text : 'icon-reload'
		}, {
			value : 'icon-search',
			text : 'icon-search'
		}, {
			value : 'icon-print',
			text : 'icon-print'
		}, {
			value : 'icon-help',
			text : 'icon-help'
		}, {
			value : 'icon-undo',
			text : 'icon-undo'
		}, {
			value : 'icon-redo',
			text : 'icon-redo'
		}, {
			value : 'icon-back',
			text : 'icon-back'
		}, {
			value : 'icon-sum',
			text : 'icon-sum'
		}, {
			value : 'icon-tip',
			text : 'icon-tip'
		} ];

		treegrid = $('#treegrid').treegrid({
			url : 'traincontentAction!treegrid.action',
			rowStyler:function(index,row){     
			    if (index%2==0){     
			            return 'background-color:#EFEFEF;';     
			        }     
			    },
			toolbar : [ {
				text : '展开',
				iconCls : 'icon-redo',
				handler : function() {
					var node = treegrid.treegrid('getSelected');
					if (node) {
						treegrid.treegrid('expandAll', node.cid);
					} else {
						treegrid.treegrid('expandAll');
					}
				}
			}, '-', {
				text : '折叠',
				iconCls : 'icon-undo',
				handler : function() {
					var node = treegrid.treegrid('getSelected');
					if (node) {
						treegrid.treegrid('collapseAll', node.cid);
					} else {
						treegrid.treegrid('collapseAll');
					}
				}
			}, '-', {
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					editRow = undefined;
					editType = undefined;
					treegrid.treegrid('reload');
				}
			}, '-', {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					append();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					remove();
				}
			}, '-', {
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					edit();
				}
			}, '-', {
				text : '保存',
				iconCls : 'icon-save',
				handler : function() {
					if (editRow != undefined) {
						treegrid.treegrid('endEdit', editRow.cid);
						treegrid.treegrid('reload');
					}
				}
			}, '-'/* , {
				text : '取消编辑',
				iconCls : 'icon-undo',
				handler : function() {
					if (editRow) {
						treegrid.treegrid('cancelEdit', editRow.cid);
						var p = treegrid.treegrid('getParent', editRow.cid);
						if (p) {
							treegrid.treegrid('reload', p.cid);
						} else {
							treegrid.treegrid('reload');
						}
						editRow = undefined;
						editType = undefined;
					}
				}
			}, '-', {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					treegrid.treegrid('unselectAll');
				}
			}, '-' */, {
				text : '更改状态',
				iconCls : 'icon-undo',
				handler : function() {
					changeStatus();
				}
			}, '-' ],
			title : '',
			iconCls : 'icon-save',
			fit : true,
			fitColumns : false,
			nowrap : true,
			animate : false,
			border : false,
			idField : 'cid',
			treeField : 'cname',
			frozenColumns : [ [  {
				title : 'cid',
				field : 'cid',
				width : 50,
				hidden : false,
				checkbox : true
			},  {
				field : 'curl',
				title : '项目编号',
				width : 150,
				height : 200,
				editor : {
					type : 'text'
				},
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				field : 'cname',
				title : '项目名称',
				width : 350,
				size : 100,				
				editor : {
					type : 'validatebox',
					options : {
						required : true
					}
				},
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			} ] ],
			columns : [ [ 
			{
				field : 'cseq',
				title : '序号',
				align : 'right',
				width : 80,		
				editor : {
					type : 'validatebox',
					options : {
						required : true
					}
				},		
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			} ,{
				field : 'ccontent',
				title : '培训内容',
				align : 'center',
				width : 100,
				editor : {
					type : 'validatebox',
					options : {
						required : true
					}
				},		
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}					
					
			},{
				field : 'cstyle',
				title : '培训人才类型',
				align : 'center',
				width : 100,
				editor : {
					type : 'validatebox',
					options : {
						required : true
					}
				},		
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}					
					
			} /*,{
				field : 'cendtime',
				title : '结束时间',
				width : 150,
				formatter : function(value, rowData, rowIndex) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				},				
				editor : {
					type : 'datebox'
				}
			}  */,{
				field : 'cresponsecompany',
				title : '负责单位',
				width : 150,
				align : 'center',
				editor : {
					type : 'text'
				},
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			} ,{
				field : 'cresponser',
				title : '负责人',
				width : 150,
				editor : {
					type : 'text'
				},
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			} ,{
				field : 'cstarttime',
				title : '时间',
				align : 'center',
				width : 150,
				formatter : function(value, rowData, rowIndex) {
				    var now ;
				    
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				},
				editor : {
					type : 'datebox'
				}
			},{
				field : 'cplace',
				title : '地点',
				width : 150,
				editor : {
					type : 'validatebox',
					options : {
						required : true
					}
				},	
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}
			,{
				field : 'cnum',
				title : '人数',
				width : 150,
				editor : {
					type : 'validatebox',
					options : {
						required : true
					}
				},	
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				field : 'cmaterial',
				title : '相关资料',
				width : 150,
				editor : {
					type : 'validatebox',
					options : {
						required : true
					}
				},	
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				field : 'cassess',
				title : '考核指标',
				width : 150,
				editor : {
					type : 'validatebox',
					options : {
						required : true
					}
				},	
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			},{
				field : '123',
				title : '合计',
				width : 150,
				editor : {
					type : 'validatebox',
					options : {
						required : true
					}
				},	
				formatter : function(value) {
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				}
			}/* ,{
				field : 'cuid',
				title : '负责人',
				width : 150,
				formatter : function(value, rowData, rowIndex) {
					value=rowData.cuname;
					if (value) {
						return sy.fs('<span style="font-size:14px" title="{0}">{1}</span>', value, value);
					}
				},
				editor : {
					type : 'combobox',
					options : {
						url : 'projectAction!projectCombobox.action',
						valueField : 'cid',
						textField : 'cname'
					}
				}
			} */] ],
			onDblClickRow : function(row) {
				if (editRow != undefined) {
					treegrid.treegrid('endEdit', editRow.cid);
				}

				if (editRow == undefined) {
					treegrid.treegrid('beginEdit', row.cid);
					editRow = row;
					editType = 'edit';
					treegrid.treegrid('unselectAll');
				}
			},
			onAfterEdit : function(row, changes) {
				if (editType == undefined) {
					editRow = undefined;
					treegrid.treegrid('unselectAll');
					return;
				}

				var url = '';
				if (editType == 'add') {
					url = 'traincontentAction!add.action';
				}
				if (editType == 'edit') {
					url = 'traincontentAction!edit.action';
				}

				$.ajax({
					url : url,
					data : row,
					dataType : 'json',
					success : function(r) {
						if (r.success) {
							treegrid.treegrid('reload');

							$.messager.show({
								msg : r.msg,
								title : '成功'
							});
							editRow = undefined;
							editType = undefined;
						} else {
							/*datagrid.datagrid('rejectChanges');*/
							treegrid.treegrid('beginEdit', editRow.cid);
							$.messager.alert('错误', r.msg, 'error');
						}
						treegrid.treegrid('unselectAll');
					}
				});

			},
			onContextMenu : function(e, row) {
				e.preventDefault();
				$(this).treegrid('unselectAll');
				$(this).treegrid('select', row.cid);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
			onLoadSuccess : function(row, data) {
				/*var t = $(this);
				if (data) {
					$(data).each(function(index, d) {
						if (this.state == 'closed') {
							t.treegrid('expandAll');
						}
					});
				}*/
			},
			onExpand : function(row) {
				treegrid.treegrid('unselectAll');
			},
			onCollapse : function(row) {
				treegrid.treegrid('unselectAll');
			},
			//点击显示选中和取消选中事件
			onClickRow : function(row) {
				datagrid-toolbar;
			}
		});

	});

	function edit() {
		var node = treegrid.treegrid('getSelected');
		if (node && node.cid) {
			if (editRow != undefined) {
				treegrid.treegrid('endEdit', editRow.cid);
			}

			if (editRow == undefined) {
				treegrid.treegrid('beginEdit', node.cid);
				editRow = node;
				editType = 'edit';
			}
		} else {
			$.messager.show({
				msg : '请选择一项进行修改！',
				title : '错误'
			});
		}
	}
	function changeStatus() {
		var node = treegrid.treegrid('getSelected');
		
		if (node) {
			$.messager.confirm('询问', '您确定要更改【' + node.cname + '】的状态？',
					function(b) {
						if (b) {
							$.ajax({
								url : 'traincontentAction!changeStatus.action',
								data : node,
								cache : false,
								dataType : "json",
								success : function(r) {
									if (r.success) {
										treegrid.treegrid('reload');
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
	function append() {

	    if (editRow != undefined) {
			treegrid.treegrid('endEdit', editRow.cid);
		}

		if (editRow == undefined) {
			var node = treegrid.treegrid('getSelected');
			var row = [ {
				cid : sy.UUID(),
				cname : '名称',
				curl : '',
				cseq : '',
				cpid : node == null ? '' : node.cid
			} ];
			treegrid.treegrid('append', {
				parent : node == null ? '' : node.cid,
				data : row
			});

			editRow = row[0];
			editType = 'add';
			treegrid.treegrid('select', editRow.cid);
			treegrid.treegrid('beginEdit', editRow.cid);
		} ;
			
	}
	function remove() {
		var node = treegrid.treegrid('getSelected');
		if (node) {
			$.messager.confirm('询问', '您确定要删除【' + node.cname + '】？',
					function(b) {
						if (b) {
							$.ajax({
								url : 'traincontentAction!delete.action',
								data : {
									cid : node.cid
								},
								cache : false,
								dataType : "json",
								success : function(r) {
									if (r.success) {
										treegrid.treegrid('remove', r.obj);
										$.messager.show({
											msg : r.msg,
											title : '提示'
										});
										editRow = undefined;
									} else {
										$.messager.show({
											msg : '删除失败!',
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
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<table id="treegrid"></table>
	</div>

	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="append();" iconCls="icon-add">增加</div>
		<div onclick="remove();" iconCls="icon-remove">删除</div>
		<div onclick="edit();" iconCls="icon-edit">编辑</div>
	</div>
</body>
</html>
