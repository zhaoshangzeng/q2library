<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isErrorPage="true" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>抱歉的页面</title>
    
    <meta http-equiv="refresh" content="3;url='${pageContext.request.contextPath}'">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <br><br><br><br><br><br>
    <center>对不起，页面出错了！</center> 
    <br/>
    <center>我们在抢修，请换个时间段访问，谢谢！</center>
    <br/>
    <center>3秒后回到首页！</center> 
    <%
    	String message = exception.getMessage();
     %>
  </body>
</html>
