<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>图书馆前台信息查询系统</title>
    <link rel="stylesheet" href="<c:url value='/css/reset.css'/>">
  	<link rel="stylesheet" href="<c:url value='/css/index.css'/>">
</head>
<body>
<div class="wrap">
    <div class="header">
        <nav>
            <ul>
                <li>图书馆前台信息查询系统  <a href="<c:url value='/'/>">首页</a></li>
		        <li><a href="<c:url value='/UserUI?jspPage=admin_login'/>">图书管理</a></li>
		        <li><a href="<c:url value='/UserUI?jspPage=feedback'/>" style="color:red;">读者反馈</a></li>
		        <li><a href="<c:url value='/UserPageServlet?method=showInform'/>">馆内通知</a></li>
		        <li><a href="<c:url value='/UserPageServlet?method=showNewBooks'/>">新书推荐</a></li>
            </ul>
        </nav>
    </div>
    <div class="content">
        <div class="form_feed">
            <div class="form-tip">
                我们聆听您的意见：
            </div>
            <form action="<c:url value='/UserPageServlet'/>" method="post">
            	<input type="hidden" name="method" value="addFeedback"/>
                <label>内容主题：<input type="text" name="title" value="${oriFB.title}"></label>
                <label for=""></label><textarea name="content" id="" cols="100" rows="8" placeholder="请在这里输入您的想法，同时内容确保属实！">${oriFB.content}</textarea><br/>
                <label>联系方式（手机号码）：<input type="text" name="phone" value="${oriFB.phone}" id="phone" onblur="jy()"></label>&nbsp;&nbsp;
                <input type="submit" value="提交">&nbsp;&nbsp;&nbsp;<span style="color:red;">${addFeedbackMeg}</span>
            </form>
            <script>
            	function jy(){
            		 if(!(/^1[3|4|5|8][0-9]\d{8}$/.test(phone.value))){ 
            		  alert("不是完整的11位手机号或者正确的手机号前七位"); 
            		 }
            	}
            </script>
        </div>
    </div>
    <div class="footer">
        <ul>
            <li>
              <a href="<c:url value='/UserPageServlet?method=showNewBooks'/>">新书推荐</a>|
	          <a href="<c:url value='/UserPageServlet?method=showInform'/>">馆内通知</a>|
	          <a href="<c:url value='/UserUI?jspPage=feedback'/>">读者反馈</a>|
	          <a href="<c:url value='/UserUI?jspPage=admin_login'/>">图书管理</a>
	          <i>&copy;q2library&nbsp;图书馆前台信息查询系统&nbsp;版权所有&nbsp;
	            <a href="javascript:void(0);">粤ICP备07803432号-70</a>
	          </i>
           </li>
        </ul>
    </div>
</div>
</body>
</html>
