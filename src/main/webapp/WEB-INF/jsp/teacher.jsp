<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/15
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>老师主页</title>
</head>
<body>
<%
    String teacher_number = (String)request.getAttribute("teacher_number");
%>
<div align="center">
    <h2 align="center">请选择以下操作</h2>
    <form id="addHK" action="${pageContext.request.contextPath}/jsp/gotoAddHomework" method="post">
        <table style="line-height: 40px">
            <input id="teacherId1" name="teacher_number" value="<%=teacher_number%>" type="hidden">
            <tr align="center">
                <td><input type="submit" value="添加作业"></td>
            </tr>
        </table>
    </form>
    <form id="getMyHK" action="${pageContext.request.contextPath}/teacher/getMyHomework" method="post">
        <table style="line-height: 40px">
            <input id="teacherId2" name="teacher_number" value="<%=teacher_number%>" type="hidden">
            <tr align="center">
                <td><input type="submit" value="查看自己发布的作业"></td>
            </tr>
        </table>
    </form>
    <a href="${pageContext.request.contextPath}/jsp/login">注销登录</a>
</div>
</body>
</html>
