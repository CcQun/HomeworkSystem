<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/16
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加作业</title>
</head>
<body>
<%
    String teacher_number = (String)request.getAttribute("teacher_number");
%>
<div align="center">
    <h2>添加作业</h2>
    <form action="${pageContext.request.contextPath}/teacher/addHomework" method="post">
        <table style="line-height: 40px">
            <input name="teacher_number" value="<%=teacher_number%>" type="hidden">
            <tr>
                <th>作业标题：</th>
                <td><input type="text" name="homework_title" maxlength="20" size="40" style="font-family: 'Arial Black'" required></td>
            </tr>
            <tr>
                <th>作业内容：</th>
                <td><textarea name="homework_content" rows="10" cols="42" style="font-family: 'Arial Black'" required></textarea></td>
            </tr>
            <tr>
                <th>开始时间：</th>
                <td><input name="create_time" type="datetime-local" required/></td>
            </tr>
            <tr>
                <th>截止时间：</th>
                <td><input name="end_time" type="datetime-local" required/></td>
            </tr>
            <tr align="center">
                <td><input type="submit" value="提交"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
