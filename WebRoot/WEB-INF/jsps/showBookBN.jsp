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
        <div id="menu">
        </div>
        <div class="s2bookBN">
	        <div class="search">
	            <form action="<c:url value='/UserPageServlet'/>" method="get">
	            	<input type="hidden" name="method" value="showBook"/>
	                <label>书名：<input type="text" name="bookName" value="${bookName}">&nbsp;&nbsp;<input type="submit" value="搜索"></label>
	            </form>
	        </div>
	        <table border="1">
	        	<c:if test="${!(empty userbooks.beanMap)}">
	            <tr>
	                <th>书名</th>
	                <th>作者</th>
	                <th>出版社</th>
	                <th>出版日期</th>
	                <th>分类</th>
	                <th>存放编号</th>
	            </tr>
	            </c:if>
	            <c:forEach var="book" items="${userbooks.beanMap}">
	            <tr>
	                <td>${book.value.bname}</td>
	                <td>${book.value.author}</td>
	                <td>${book.value.publish}</td>
	                <td>${book.value.pubdate}</td>
	                <td>${book.value.btype}</td>
	                <td>${book.value.bindex}</td>
	            </tr>
	            </c:forEach>
	        </table>
	        <!-- 给出分页的链接 -->
	        <br/>
	        <div class="fixdown">
	            <center>
	            ${notEmpty}
	            <c:if test="${empty notEmpty}">
	            <c:choose>
	            	<c:when test="${userbooks.tp eq 0}">
	            		抱歉，未找到相应的图书记录！
	            	</c:when>
	            	<c:otherwise>
	            第${userbooks.pc}页/共${userbooks.tp}页&nbsp;
	            <a href="${userbooks.url}&pc=1">首页</a>
	            <c:if test="${userbooks.pc > 1}">
	            	<a href="${userbooks.url}&pc=${userbooks.pc-1}">上一页</a>
	            </c:if>
	            <c:if test="${userbooks.pc < userbooks.tp}">
	            	<a href="${userbooks.url}&pc=${userbooks.pc+1}">下一页</a>
	            </c:if>
	            <a href="${userbooks.url}&pc=${userbooks.tp}">尾页</a>
	            </c:otherwise>
	            </c:choose>
	            </c:if>
	            </center>
	        </div>
    	</div>
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

</body>
</html>
