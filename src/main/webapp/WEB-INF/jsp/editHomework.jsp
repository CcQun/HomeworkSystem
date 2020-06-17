<%@ page import="com.example.homework.db.model.Submit" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/17
  Time: 8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑作业</title>
</head>
<body>
<%
    String student_number = (String) request.getAttribute("student_number");
    String homework_number = (String) request.getAttribute("homework_number");
    Submit submit = (Submit) request.getAttribute("submit");
%>
<div align="center">
    <h2>编辑作业<%=homework_number%></h2>
    <form action="${pageContext.request.contextPath}/student/editHomework" method="post">
        <input value="<%=homework_number%>" name="homework_number" type="hidden">
        <input value="<%=student_number%>" name="student_number" type="hidden">
        <table style="line-height: 40px">
            <tr>
                <th>作业标题：</th>
                <td><input type="text" name="homework_title" value="<%=submit.getSubmit_title()%>" maxlength="20" size="40" style="font-family: 'Arial Black'"
                           required></input></td>
            </tr>
            <tr>
                <th>作业内容：</th>
                <td><textarea name="homework_content" rows="10" cols="42" style="font-family: 'Arial Black'" required><%=submit.getSubmit_content()%></textarea></td>
            </tr>
            <tr align="center">
                <td><input type="submit" value="提交"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
