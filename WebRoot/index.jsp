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
          <li><a href="<c:url value='/UserUI?jspPage=feedback'/>">读者反馈</a></li>
          <li><a href="<c:url value='/UserPageServlet?method=showInform'/>">馆内通知</a></li>
		  <li><a href="<c:url value='/UserPageServlet?method=showNewBooks'/>">新书推荐</a></li>
        </ul>
      </nav>
    </div>	
    <div class="content">
      <div class="form">
        <div class="form-tip">
          请输入您要查找的书名：
        </div>
        <form action="<c:url value='/UserPageServlet'/>" method="get">
          <input type="hidden" name="method" value="showBook"/>
          <input type="text" name="bookName">
          <input type="image" src="<c:url value='/img/query.jpg'/>">
        </form>
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
