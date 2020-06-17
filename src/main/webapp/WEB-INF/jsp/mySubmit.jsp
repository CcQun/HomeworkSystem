<%@ page import="com.example.homework.db.model.Submit" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/17
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的提交</title>
</head>
<body>
<form id="submit" method="post" action="${pageContext.request.contextPath}/teacher/submitGrade">
    <table align="center" width="1300" border="1">
        <tr>
            <th width="5%">作业id</th>
            <th width="5%">学生id</th>
            <th width="10%" style="word-break: break-all;word-wrap: break-word;">作业标题</th>
            <th width="30%" style="word-break: break-all;word-wrap: break-word;">作答内容</th>
            <th width="5%">成绩</th>
            <th width="15%">评价</th>
            <th width="15%">提交时间</th>
            <th width="15%">更新时间</th>
        </tr>
        <%
            String student_number = (String) request.getAttribute("student_number");
            Submit submit = (Submit) request.getAttribute("submit");
        %>
        <tr>
            <td><%=submit.getSubmit_pk().getHomework_number()%>
            </td>
            <td><%=submit.getSubmit_pk().getStudent_number()%>
            </td>
            <td><%=submit.getSubmit_title()%>
            </td>
            <td><%=submit.getSubmit_content()%>
            </td>
            <td><%=submit.getGrade()%>
            </td>
            <td><%=submit.getComment()%>
            </td>
            <td><%=submit.getCreate_time()%>
            </td>
            <td><%=submit.getUpdate_time()%>
            </td>
        </tr>
    </table>
</form>
<form id="back" action="${pageContext.request.contextPath}/jsp/backStudent" method="post">
    <table style="line-height: 40px">
        <input id="stuId" name="student_number" value="<%=student_number%>" type="hidden">
        <tr align="center">
            <td><input type="submit" value="返回主页"></td>
        </tr>
    </table>
</form>
</body>
</html>
