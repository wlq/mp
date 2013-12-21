<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	 <base href="<%=basePath%>">
    
    
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>农村基本医疗卫生关键技术研究与示范协同研究工作平台</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<link href="css/style.css" rel="stylesheet" type="text/css"
			media="screen" />
                <script language="JavaScript">
window.attachEvent("onload", correctPNG);
      // 重载验证码
      function reloadVerifyCode(){
          var timenow = new Date().getTime();                       
          document.getElementById("safecode").src="<%=request.getContextPath()%>/healthAction/ImageServlet?d="+timenow;
       }
</script>
	</head>
	<body>
		<div id="wrapper" align="center">
			<div id="logo">
				<h1 align="left">
					<a href="#">农村基本医疗卫生关键技术研究与示范协同研究工作平台 </a>
				</h1>
			</div>
			<hr />
			<!-- end #logo -->
			<div id="header">
				<div id="menu">
					<ul>
						<li class="current_page_item">
							<a href="index.jsp" class="first">首页</a>
						</li>
						<li>
							<a href="">平台介绍</a>
						</li>
						<li>
							<a href="#">近期动态</a>
						</li>
						<li>
							<a href="#">资源下载</a>
						</li>
						<li>
							<a href="#">联系我们</a>
						</li>
					</ul>
				</div>
				<!-- end #menu -->
				<div id="search">
					<form method="get" action="">
						<fieldset>
							<input type="text" name="s" id="search-text" size="15" />
							<input type="submit" id="search-submit" value="GO" />
						</fieldset>
					</form>
				</div>
				<!-- end #search -->
			</div>
			<!-- end #header -->
			<!-- end #header-wrapper -->
			<div id="page">
				<div id="page-bgtop">
				<div id="page-content" align="left" style = "width:920px;margin:0 auto;text-align:center;">
				<div id="firstlayer" style = "width:920px;margin:0 auto;text-align:center;margin-top:15px;">
				<table align="center">
				<tr align="center">
				<td><a href=""><img src="fimages/xiangmulan.png" />
									</a>
									<h2>
										<a href="" style="text-decoration:none "><strong>项目管理</strong> </a>
									</h2>
									
			   </td>
			   <td width="30"></td>
					<td>
					<a href="index.jsp"><img src="fimages/guanjian.jpg" />
									</a>
									<h2>
										<a href="index.jsp" style="text-decoration:none "><strong>关键技术集成与应用示范</strong> </a>
									</h2>				
			   </td>
				</tr>
				
				
				</table>
				</div>
				
				<div id="secondlayer" style="width:920px">
				
				<div id="secondlayer_1" style="float:left">
				<table>
				<tr>
				<td width="190"></td>
				<td>
				<a href=""><img src="fimages/shujuwajue.jpg"> </a><a
										href=""></a>
									<h2>
										<a href="disp/welcome.jsp" style="text-decoration:none "><strong>数据服务</strong></a>
									</h2>
				</td>
				</tr>
				</table>
				
				<table>
				<tr>
				<td width="80"></td>
				<td>
				<a href=""><img src="fimages/peixun.jpg" /> </a>
									<h2>
										<a href="video.jsp" style="text-decoration:none "><strong>远程培训</strong></a>
									</h2>
				</td>
				</tr>
				
				</table>
				
				</div>
				
				<div id="secondlayer_2" style="float:left;height:283">
					<form action="healthAction/UserLogin.do" method="post"> 
                                                <table height="283" background="fimages/loginpic.jpg" align="left" width="307"> 
 
							<tbody><tr> 
								<td> 
 
									&nbsp;&nbsp;&nbsp; 
								</td> 
							</tr> 
							<tr align="center"> 
								<td> 
 
									<strong>用户登录</strong>&nbsp;&nbsp;&nbsp; 
								</td> 
							</tr> 
							<tr align="center"> 
								<td> 
									用户名： 
									<input type="text" name="username" size="12" value="${requestScope.msgN}" onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" onpaste="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" oncontextmenu="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')"><br> 
									<font color="red">${requestScope.msgName}</font> 
								</td> 
							</tr> 
							<tr align="center"> 
								<td> 
									&nbsp;&nbsp;&nbsp;密码： &nbsp;
									<input type="password" name="password" size="13"><br> 
									<font color="red">${requestScope.msgPwd}</font> 
								</td> 
							</tr> 
							<tr align="center"> 
								<td> 
									验证码： &nbsp;
									<input type="text" id="verifyCode" name="verifyCode" size="6"> <a href="javascript:reloadVerifyCode();" onclick="javascript:reloadVerifyCode();"><img height="22" align="bottom" width="50" alt="换一张" id="safecode" src="<%=request.getContextPath()%>/healthAction/ImageServlet"></a> 
									<br><font color="red">${requestScope.msgYanzhengma}</font>    
								</td> 
								 
								 
							</tr> 
							<tr align="center"> 
								<td> 
									<input type="submit" value="登录"> 
								</td> 
							</tr> 
							<tr> 
								<td> 
 
									&nbsp;&nbsp;&nbsp; 
								</td> 
							</tr> 
							 
						</tbody></table> 
                                            </form><br>
				
				
				
				</div>
				
				<div id="second_layer_3">
				
				<table>
				<tr>
				<td width="10"></td>
				<td>
			<a><img src="fimages/xinxi.jpg"></a>
									<h2>
										<a href="info_show.jsp" style="text-decoration:none "><strong>信息发布</strong> </a>
									</h2>
				</td>
				</tr>
				</table>
				
				<table>
				<tr>
				<td width="80"></td>
				<td>
				<a href=""><img src="fimages/22.jpg" /> </a>
									<h2>
										<a href="" style="text-decoration:none "><strong>示范县信息平台</strong></a>
									</h2>
				</td>
				</tr>
				
				</table>
				</div>
				</div>
				</div>
				</div>
				
				<!-- end #content -->

				<!-- end #sidebar -->
				<div style="clear: both;">
					&nbsp;
				</div>
			</div>
		</div>
		<br>
		<br>
		<br>
		<br><br>
		<br>
		<br>
		<br>
		<br>
		
		<!-- end #page -->
		<div id="footer-bgcontent" align="center">
			<div id="footer">
				<p>
					Copyright (c) 2012 教育部科技司、中国人民解放军总医院 . Design by 中国人民解放军总医院.
				</p>
			</div>
		</div>
		<!-- end #footer -->
	</body>
</html>
