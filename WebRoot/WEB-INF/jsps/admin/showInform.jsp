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
        function deleteInform(id){
        	var b = window.confirm("您确认删除吗？");
        	if(b){
        		window.location.href="${pageContext.request.contextPath}/admin/InformServlet?method=deleteInform&delid=" + id;	
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
        <div class="s_inform">
        <ul>
        	<c:forEach var="one" items="${informs}">
        		<li>
            	<a href="<c:url value='/admin/InformServlet?method=showText&iid=${one.key}'/>">${one.value.title}</a>
            	<div>[${one.value.itime}]</div>
            	<div><a href="javascript:void();" onclick="deleteInform('${one.key}')">[删除]</a></div>
            	<div><a href="<c:url value='/admin/AdminUI?jspPage=updateInform&upid=${one.key}'/>">[修改]</a></div>
            	</li>
        	</c:forEach>
            <%-- <li>
            	<a href="<c:url value='/admin/AdminUI?jspPage=informText'/>">图书馆关于停电闭馆的通知</a>
            	<div>[2018-12-07]</div>
            	<div><a href="#">[删除]</a></div>
            	<div><a href="<c:url value='/admin/AdminUI?jspPage=addInform'/>">[修改]</a></div>
            </li>
            <li>
            	<a href="<c:url value='/admin/AdminUI?jspPage=informText'/>">关于校运会期间图书馆闭馆的通知</a>
            	<div>[2018-11-06]</div>
            	<div><a href="#">[删除]</a></div>
            	<div><a href="<c:url value='/admin/AdminUI?jspPage=addInform'/>">[修改]</a></div>
            </li>
            <li>
            	<a href="<c:url value='/admin/AdminUI?jspPage=informText'/>">关于提高本科生图书借阅册数的通知</a>
            	<div>[2018-10-30]</div>
            	<div><a href="#">[删除]</a></div>
            	<div><a href="<c:url value='/admin/AdminUI?jspPage=addInform'/>">[修改]</a></div>
            </li> --%>
        </ul>
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
