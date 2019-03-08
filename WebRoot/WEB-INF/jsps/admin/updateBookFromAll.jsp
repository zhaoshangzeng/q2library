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
        var bar = new Q6MenuBar("bar", "管理操作");
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
        <div class="a2book">
            <form action="<c:url value='/admin/BookServlet'/>" method="post">
                <fieldset>
                    <legend>修改图书</legend>
                    <input type="hidden" name="method" value="updateBook"/>
                    <input type="hidden" name="page" value="all"/>
                    <input type="hidden" name="bid" value="${upBook.bid}"/>
                    <p><label>书名：<input type="text" name="bname" value="${upBook.bname}"></label></p>
                    <p><label>作者：<input type="text" name="author" value="${upBook.author}"></label></p>
                    <p><label>出版社：<input type="text" name="publish" value="${upBook.publish}"></label></p>
                    <p><label>出版日期：<input type="text" name="pubdate" placeholder="格式：2018.9" value="${upBook.pubdate}"></label></p>
                    <p><label>类型：<input type="text" name="btype" value="${upBook.btype}"></label></p>
                    <p><label>存放编号：<input type="text" name="bindex" value="${upBook.bindex}"></label></p>
                    <p class="center"><input type="reset"><input type="submit" value="提交"></p>
                    <p class="center"><span style="color:red;font-size:30px;">${upBookMeg}</span></p>
                </fieldset>
            </form>
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
