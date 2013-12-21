<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>欢迎使用XXX项目管理系统</title>
<!-- 
inc.jsp为javascript、css、cookie文件
isIe.jsp为判定ie版本
 -->
<jsp:include page="inc.jsp"></jsp:include>
</head>
<body id="indexLayout" class="easyui-layout">
	<div region="north" class="logo" style="height:80px;overflow: hidden;" href="layout/north.jsp"></div>	
	<div region="center" title="欢迎使用XXX项目管理系统" style="overflow: hidden;" href="layout/center.jsp"></div>
	<div region="west" title="功能导航" split="false" style="width:200px;overflow: hidden;" href="layout/west.jsp"></div>
	<div region="south" style="height:20px;overflow: hidden;" href="layout/south.jsp"></div>
	<jsp:include page="user/loginAndReg.jsp"></jsp:include>
	<jsp:include page="isIe.jsp"></jsp:include>
</body>
</html>