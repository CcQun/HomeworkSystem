<%@ page import="com.example.homework.core.cm.HKInforSTT" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/17
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看统计信息</title>
</head>
<body>
<form id="submit" method="post" action="${pageContext.request.contextPath}/teacher/submitGrade">
    <table align="center" width="1300" border="1">
        <tr>
            <th width="15%">待评分学生数</th>
            <th width="15%">及格学生数</th>
            <th width="15%">已提交学生数</th>
            <th width="15%">平均分</th>
            <th width="10%">最高分</th>
            <th width="10%">最低分</th>
            <th width="20%">取得最高分的同学</th>
        </tr>
        <%
            String teacher_number = (String) request.getAttribute("teacher_number");
            HKInforSTT hkInforSTT = (HKInforSTT)request.getAttribute("stt");
        %>
        <tr>
            <td><%=hkInforSTT.getTo_evaluate_student_count()%>
            </td>
            <td><%=hkInforSTT.getPass_student_count()%>
            </td>
            <td><%=hkInforSTT.getSubmit_student_count()%>
            </td>
            <td><%=hkInforSTT.getAverage()%>
            </td>
            <td><%=hkInforSTT.getMax_point()%>
            </td>
            <td><%=hkInforSTT.getMin_point()%>
            </td>
            <td><%=hkInforSTT.getMax_point_owner()%>
            </td>
        </tr>
    </table>
</form>
<form id="back" action="${pageContext.request.contextPath}/jsp/backTeacher" method="post">
    <table style="line-height: 40px">
        <input id="teaId" name="teacher_number" value="<%=teacher_number%>" type="hidden">
        <tr align="center">
            <td><input type="submit" value="返回主页"></td>
        </tr>
    </table>
</form>
</body>
</html>
