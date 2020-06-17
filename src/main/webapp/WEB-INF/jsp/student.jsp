<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/15
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生主页</title>
</head>
<body>
<%
    String student_number = (String)request.getAttribute("student_number");
%>
<div align="center">
    <h2 align="center">请选择以下操作</h2>
    <form id="getHK" action="${pageContext.request.contextPath}/student/getHomework" method="post">
        <table style="line-height: 40px">
            <input id="studentId" name="student_number" value="<%=student_number%>" type="hidden">
            <tr align="center">
                <td><input type="submit" value="查看作业"></td>
            </tr>
        </table>
    </form>
    <a href="${pageContext.request.contextPath}/jsp/login">注销登录</a>
</div>
</body>
</html>
