<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
	function logout(b) {
		$.post('userAction!logout.action', function() {
			//强制刷新
			window.location.reload(); 
			if (b) {
				if (sy.isLessThanIe8()) {
					loginAndRegDialog.dialog('open');
				} else {
					location.replace(sy.bp());
				}
			} else {
				loginAndRegDialog.dialog('open');
			}
		});
	}

	var userInfoWindow;
	/* function showUserInfo() {
		userInfoWindow = $('<div/>').window({
			modal : true,
			title : '当前用户信息',
			width : 350,
			height : 300,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			href : 'userAction!showUserInfo.action',
			onClose : function() {
				$(this).window('destroy');
			}
		});
	} */
	function showUserInfo() {
		var p = parent.sy.dialog({
			title : '用户信息',
			href : 'userAction!showUserInfo.action',
			width : 290,
			height : 255,
			buttons : [ {
				text : '修改密码',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : 'userAction!editUserInfo.action',
						success : function(d) {
							var json = $.parseJSON(d);
							if (json.success) {
								p.dialog('close');
							}
							parent.sy.messagerShow({
								msg : json.msg,
								title : '提示'
							});
						}
					});
				}
			} ]
			/* onLoad : function() {
				var authIds = p.find('ul');
				var authIdsTree = authIds.tree({
					url : 'authAction!treeRecursive.action',
					lines : true,
					checkbox : true,
					onLoadSuccess : function(node, data) {
						var f = p.find('form');
						var ids = f.find('input[name=authIds]').val();
						var idList = sy.getList(ids);
						if (idList.length > 0) {
							for ( var i = 0; i < idList.length; i++) {
								var n = authIdsTree.tree('find', idList[i]);
								authIdsTree.tree('check', n.target);
							}
						}
						authIdsTree.unbind();
					}
				});
			} */
		});
	}
</script>
<div style="position: absolute; right: 0px; bottom: 0px; ">
	<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_pfMenu" iconCls="icon-ok">更换皮肤</a> <a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="icon-help">控制面板</a> <a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_zxMenu" iconCls="icon-back">注销</a>
</div>
<div id="layout_north_pfMenu" style="width: 120px; display: none;">
	<div onclick="sy.changeTheme('default');">default</div>
	<div onclick="sy.changeTheme('gray');">gray</div>
	<div onclick="sy.changeTheme('cupertino');">cupertino</div>
	<div onclick="sy.changeTheme('dark-hive');">dark-hive</div>
	<div onclick="sy.changeTheme('pepper-grinder');">pepper-grinder</div>
	<div onclick="sy.changeTheme('sunny');">sunny</div>
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div onclick="showUserInfo();">个人信息</div>
	<div class="menu-sep"></div>
	<div>
		<span>更换主题</span>
		<div style="width: 120px;">
			<div onclick="sy.changeTheme('default');">default</div>
			<div onclick="sy.changeTheme('gray');">gray</div>
			<div onclick="sy.changeTheme('cupertino');">cupertino</div>
			<div onclick="sy.changeTheme('dark-hive');">dark-hive</div>
			<div onclick="sy.changeTheme('pepper-grinder');">pepper-grinder</div>
			<div onclick="sy.changeTheme('sunny');">sunny</div>
		</div>
	</div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div onclick="loginAndRegDialog.dialog('open');">锁定窗口</div>
	<div class="menu-sep"></div>
	<div onclick="logout();">重新登录</div>
	<div onclick="logout(true);">退出系统</div>
</div>
