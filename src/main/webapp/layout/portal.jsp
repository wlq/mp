<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
	var portal;
	var panels;
	$(function() {
		
		panels = [ {
			id : 'p1',
			title : '系统概述',
			height : 150,
			collapsible : true,
			collapsed : false,
			size :50,
			href:'layout/portal/about.jsp'
		}, {
			id : 'p2',
			title : '项目管理专业化',
			height : 150,
			collapsible : true,
			collapsed : false,
			href:'layout/portal/link.jsp'
		}, {
			id : 'p3',
			title : '信息发布及时化',
			height : 120,
			collapsible : true,
			collapsed : false,
			href:'layout/portal/repair.jsp'
		}, {
			id : 'p4',
			title : '成果汇交高效化',
			height : 200,
			collapsible : true,
			collapsed : false,
			//成果汇交高效化
			content : '<b><font size="5" face="仿宋"  color=#006699>&nbsp;&nbsp;&nbsp;&nbsp;将资源汇交与课题关键技术集成相结合，与关键技术实证研究和再评价研究相结合。要求汇交的关键技术、实证研究和再评价研究数据真实，结果可靠，确保科技资源质量。</font></b>'
		}, {
			id : 'p5',
			title : '专项经费管理严格化',
			height : 200,
			collapsible : true,
			collapsed : false,
			href:'layout/portal/about2.jsp'
		} /* , {
			id : 'p6',
			title : '我的通知',
			height : 200,
			collapsible : true,
			collapsed : false,
			href:'layout/portal/notice_wei.jsp'
		} */];

		portal = $('#portal').portal({
			border : false,
			fit : true,
			onStateChange : function() {
				$.cookie('portal-state', getPortalState(), {
					expires : 7
				});
			}
		});
		var state = $.cookie('portal-state');
		if (!state) {
			state = 'p1,p2,p3:p4,p5';/*冒号代表列，逗号代表行*/
		}
		addPanels(state);
		portal.portal('resize');

	});

	function getPanelOptions(id) {
		for ( var i = 0; i < panels.length; i++) {
			if (panels[i].id == id) {
				return panels[i];
			}
		}
		return undefined;
	}
	function getPortalState() {
		var aa=[];
		for(var columnIndex=0;columnIndex<2;columnIndex++) {
			var cc=[];
			var panels=portal.portal('getPanels',columnIndex);
			for(var i=0;i<panels.length;i++) {
				cc.push(panels[i].attr('id'));
			}
			aa.push(cc.join(','));
		}
		return aa.join(':');
	}
	function addPanels(portalState) {
		var columns = portalState.split(':');
		for (var columnIndex = 0; columnIndex < columns.length; columnIndex++) {
			var cc = columns[columnIndex].split(',');
			for (var j = 0; j < cc.length; j++) {
				var options = getPanelOptions(cc[j]);
				if (options) {
					var p = $('<div/>').attr('id', options.id).appendTo('body');
					p.panel(options);
					portal.portal('add', {
						panel : p,
						columnIndex : columnIndex
					});
				}
			}
		}
	}
</script>
<div id="portal" style="position:relative">
	<div></div>
	<div></div>
</div>