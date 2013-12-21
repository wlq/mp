<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
	var loginAndRegDialog;
	var loginTabs;
	var loginInputForm;
	var loginDatagridForm;
	var loginComboboxForm;
	var loginDatagridName;
	var loginComboboxName;

	var regDialog;
	var regForm;

	$(function() {
		var formParam = {
			url : 'userAction!login.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					var user = json.obj;
					if(user.ccd > 0)
					{
						afterLoginSuccess(user.loginName, user.groupId);
					}
					else afterLoginSuccess1(user.loginName, user.groupId);
				} else {
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		};
		loginInputForm = $('#loginInputForm').form(formParam);
		loginDatagridForm = $('#loginDatagridForm').form(formParam);
		loginComboboxForm = $('#loginComboboxForm').form(formParam);
		regForm = $('#regForm').form({
			url : 'userAction!reg.action',
			success : function(data) {
				var d = $.parseJSON(data);
				if (d.success) {
					regDialog.dialog('close');
					loginInputForm.find('input[name=cname]').val(regForm.find('input[name=cname]').val());
					loginInputForm.find('input[name=cpwd]').val(regForm.find('input[name=cpwd]').val());
					loginInputForm.submit();
				} else {
					regForm.find('input[name=cname]').focus();
				}
				$.messager.show({
					msg : d.msg,
					title : '提示'
				});
			}
		});
		$('form input').bind('keyup', function(event) {/* 增加回车提交功能 */
			if (event.keyCode == '13') {
				$(this).submit();
			}
		});

		loginAndRegDialog = $('#loginAndRegDialog').show().dialog({
			buttons : [ /* {
				text : '注册',
				handler : function() {					
						regDialog.dialog('open');
				}
			}, */ {
				text : '登录',
				handler : function() {
					loginInputForm.submit();
				}
			} ]
		});

		regDialog = $('#regDialog').show().dialog({
			modal : true,
			title : '用户注册',
			closed : true,
			buttons : [ {
				text : '注册',
				handler : function() {
					regForm.submit();
				}
			} ],
			onOpen : function() {
				setTimeout(function() {
					regForm.find('input[name=cname]').focus();
				}, 1);
			},
			onClose : function() {
				loginTabs.tabs('select', 0);
			}
		});

		loginTabs = $('#loginTabs').tabs({
			onSelect : function(title) {
				window.setTimeout(function() {
						$('div.validatebox-tip').remove();
						loginInputForm.find('input[name=cname]').focus();
					}, 0);
				/* if ('输入方式' == title) {
					window.setTimeout(function() {
						$('div.validatebox-tip').remove();
						loginInputForm.find('input[name=cname]').focus();
					}, 0);
				}  else if ('表格方式' == title) {
					window.setTimeout(function() {
						if (loginDatagridName.combogrid('options').url == '') {
							loginDatagridName.combogrid({
								//url : 'userAction!loginDatagrid.action'
								url:'javascript:void(0)'
							});
						}
						$('div.validatebox-tip').remove();
						loginDatagridName.combogrid('textbox').focus();
					}, 0);
				} else if ('补全方式' == title) {
					window.setTimeout(function() {
						if (loginComboboxName.combobox('options').url == '') {
							loginComboboxName.combobox({
								url : 'userAction!loginCombobox.action'
							});
						}
						$('div.validatebox-tip').remove();
						loginComboboxName.combobox('textbox').focus();
					}, 0);
				}  */
			}
		});

		loginDatagridName = $('#loginDatagridName').combogrid({
			loadMsg : '数据加载中....',
			panelWidth : 440,
			panelHeight : 180,
			required : true,
			fitColumns : true,
			value : '',
			idField : 'cname',
			textField : 'cname',
			mode : 'remote',
			url : '',
			pagination : true,
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'cid',
				title : '编号',
				width : 60,
				hidden : true
			}, {
				field : 'cname',
				title : '登录名',
				width : 100,
				sortable : true
			}, {
				field : 'ccreatedatetime',
				title : '创建时间',
				width : 150,
				sortable : true
			}, {
				field : 'cmodifydatetime',
				title : '最后修改时间',
				width : 150,
				sortable : true
			} ] ],
			delay : 500
		});

		loginComboboxName = $('#loginComboboxName').combobox({
			required : true,
			url : '',
			textField : 'cname',
			valueField : 'cname',
			mode : 'remote',
			panelHeight : 'auto',
			delay : 500,
			value : ''
		});

		var afterLoginSuccess = function(loginName, ip) {
			$('#indexLayout').layout('panel', 'center').panel('setTitle', sy.fs('欢迎您 :{0}！ 您有：{1}个会议通知，请查看', loginName, ip));
			loginAndRegDialog.dialog('close');
		};
		var afterLoginSuccess1 = function(loginName,ip) {
			$('#indexLayout').layout('panel', 'center').panel('setTitle', sy.fs('欢迎您 :{0}！ 您近期没有会议通知', loginName, ip));
			loginAndRegDialog.dialog('close');
		};
		
	});
</script>
<div id="loginAndRegDialog" modal="true" title="系统登录" closable="false" style="width:250px;height:210px;display: none;overflow: hidden;">
	
			<div class="info">
				<div class="tip icon-tip"></div>
				<div>请输入您的用户名和密码</div>
			</div>
			<div align="center">
				<form id="loginInputForm" method="post">
					<table class="tableForm" style="margin: 5px;">
						<tr>
							<th>用户名</th>
							<td><input name="cname" class="easyui-validatebox" required="true" missingMessage="请填写登录名称"/>
							</td>
						</tr>
						<tr>
							<th>密码</th>
							<td><input name="cpwd" type="password" class="easyui-validatebox" required="true" missingMessage="请填写登录密码" />
							</td>
						</tr>
					</table>
				</form>
			</div>
</div>

<!-- <div id="regDialog" style="width:250px;display: none;padding: 5px;" align="center">
	<form id="regForm" method="post">
		<table class="tableForm">
			<tr>
				<th>登录名</th>
				<td><input name="cname" class="easyui-validatebox" required="true" missingMessage="请填写登录名称" /></td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="cpwd" type="password" class="easyui-validatebox" required="true" missingMessage="请填写密码" /></td>
			</tr>
			<tr>
				<th>重复密码</th>
				<td><input name="rePassword" type="password" class="easyui-validatebox" required="true" missingMessage="请再次填写密码" validType="eqPassword['#regForm input[name=cpwd]']" /></td>
			</tr>
		</table>
	</form>
</div> -->