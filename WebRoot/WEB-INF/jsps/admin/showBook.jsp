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
    <script type="text/javascript" src="<c:url value='/js/jquery-1.5.1.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
    <script language="javascript">
        var bar = new Q6MenuBar("bar", "管理操作"); <!--手风琴式菜单导航-->
        $(function() {
            bar.colorStyle = 4;
            bar.config.imgDir = "<c:url value='/menu/img/'/>";
            bar.config.radioButton=true;

            bar.add("图书管理", "增加图书", "<c:url value='/admin/AdminUI?jspPage=addBook'/>", "");
            bar.add("图书管理", "查看全部图书", "<c:url value='/admin/BookServlet?method=showAllBook'/>", "");
            bar.add("图书管理", "搜索图书", "<c:url value='/admin/AdminUI?jspPage=showBook'/>", "");

            bar.add("馆内通知", "发布通知", "<c:url value='/admin/AdminUI?jspPage=addInform'/>", "");
            bar.add("馆内通知", "查看通知", "<c:url value='/admin/InformServlet?method=showInform'/>", "");

            bar.add("读者反馈", "查看反馈", "<c:url value='/admin/FeedbackServlet?method=showFeedback'/>", "");

            bar.add("账号管理", "修改密码", "<c:url value='/admin/AdminUI?jspPage=updateAccount'/>", "");
            
            bar.add("管理界面", "返回管理界面", "<c:url value='/admin/AdminUI?jspPage=admin'/>", "");
            $("#menu").html(bar.toString());
        });
        function deleteBook(id){
        	var b = window.confirm("您确认删除吗？");
        	if(b){
        		window.location.href="${pageContext.request.contextPath}/admin/BookServlet?method=deleteBook&jspName=search&delid=" + id;	
        	}
        }
    </script>
</head>
<body>
<div class="wrap">
    <div class="header">
        <nav>
            <ul>
                <li>图书馆前台信息查询系统  </li>
		        <li class="bigtext"><a href="<c:url value='/admin/AccountServlet?method=logout'/>">安全退出</a></li>
            </ul>
        </nav>
    </div>
    <div class="content">
        <div id="menu">
        </div>
        <div class="s2book">
	        <div class="search">
	            <form action="<c:url value='/admin/BookServlet'/>" method="get">
	            	<input type="hidden" name="method" value="showBook"/>
	                <label>书名：<input type="text" name="bookName" value="${bookName}">&nbsp;&nbsp;<input type="submit" value="搜索"></label>
	            </form>
	        </div>
	        <table border="1">
	        	<c:if test="${!(empty books.beanMap)}">
	            <tr>
	                <th>书名</th>
	                <th>作者</th>
	                <th>出版社</th>
	                <th>出版日期</th>
	                <th>分类</th>
	                <th>存放编号</th>
	                <th>修改操作</th>
		            <th>删除操作</th>
	            </tr>
	            </c:if>
	            <c:forEach var="book" items="${books.beanMap}">
	            <tr>
	                <td>${book.value.bname}</td>
	                <td>${book.value.author}</td>
	                <td>${book.value.publish}</td>
	                <td>${book.value.pubdate}</td>
	                <td>${book.value.btype}</td>
	                <td>${book.value.bindex}</td>
	                <td><a href="<c:url value='/admin/AdminUI?jspPage=updateBook&upid=${book.key}'/>">[修改]</a></td>
		            <td><a href="javascript:void();" onclick="deleteBook('${book.key}')">[删除]</a></td>
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
	            	<c:when test="${books.tp eq 0}">
	            		抱歉，未找到相应的图书记录！
	            	</c:when>
	            	<c:otherwise>
	            第${books.pc}页/共${books.tp}页&nbsp;
	            <a href="${books.url}&pc=1">首页</a>
	            <c:if test="${books.pc > 1}">
	            	<a href="${books.url}&pc=${books.pc-1}">上一页</a>
	            </c:if>
	            <c:if test="${books.pc < books.tp}">
	            	<a href="${books.url}&pc=${books.pc+1}">下一页</a>
	            </c:if>
	            <a href="${books.url}&pc=${books.tp}">尾页</a>
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
