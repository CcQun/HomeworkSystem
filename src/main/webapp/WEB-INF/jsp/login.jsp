<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/15
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业管理系统</title>
</head>
<body>
<div align="center">
    <h2>登录</h2>
    <form action="${pageContext.request.contextPath}/rl/login" method="post">
        <table style="line-height: 40px">
            <tr>
                <th>ID：</th>
                <td><input type="text" name="id" maxlength="20" size="40" style="font-family: 'Arial Black'" required/>
                </td>
            </tr>
            <tr>
                <th>密码：</th>
                <td><input type="password" name="password" maxlength="20" size="40" style="font-family: 'Arial Black'"
                           required/></td>
            </tr>
            <tr>
                <th>类型：</th>
                <td><input type="radio" name="ts" value="0" required/>学生
                    <input type="radio" name="ts" value="1" required/>老师</td>
            </tr>
            <tr align="center">
                <td><input type="submit" value="登录"></td>.
            </tr>
        </table>
    </form>
    <a href="${pageContext.request.contextPath}/jsp/register">前往注册</a>
</div>
</body>
</html>
