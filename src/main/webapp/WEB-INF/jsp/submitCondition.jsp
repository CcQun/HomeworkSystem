<%@ page import="com.example.homework.db.model.Submit" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/17
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看作业提交情况</title>
    <script>
        function submitGrade(homework_number, student_number,teacher_number,i) {
            let homework = document.getElementById("homeworkId")
            homework.setAttribute("value", homework_number)
            let student = document.getElementById("studentId")
            student.setAttribute("value", student_number)
            let teacher = document.getElementById("teacherId")
            teacher.setAttribute("value", teacher_number)
            let temp = document.getElementById("temp")
            temp.setAttribute("value", i)
            let submit = document.getElementById("submit")
            submit.submit()
        }
    </script>
</head>
<body>
<form id="submit" method="post" action="${pageContext.request.contextPath}/teacher/submitGrade">
<input id="homeworkId" name="homework_number" type="hidden">
<input id="studentId" name="student_number" type="hidden">
    <input id="teacherId" name="teacher_number" type="hidden">
    <input id="temp" name="temp" type="hidden">
<table align="center" width="1300" border="1">
    <tr>
        <th width="5%">作业id</th>
        <th width="5%">学生id</th>
        <th width="10%" style="word-break: break-all;word-wrap: break-word;">作业标题</th>
        <th width="25%" style="word-break: break-all;word-wrap: break-word;">作答内容</th>
        <th width="5%">成绩</th>
        <th width="15%">评价</th>
        <th width="15%">提交时间</th>
        <th width="15%">更新时间</th>
        <th width="5%">保存成绩</th>
    </tr>
    <%
        String teacher_number = (String) request.getAttribute("teacher_number");
        List<Submit> list = (List<Submit>) request.getAttribute("submit_list");
        if (list == null || list.size() <= 0) {

        } else {
            int i = 0;
            for (Submit submit : list) {
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
        <td><input name="grade<%=i%>" value="<%=submit.getGrade()%>" oninput="value=value.replace(/[^\d]/g,'')" type="text" required/>
        </td>
        <td><input name="comment<%=i%>" value="<%=submit.getComment()%>" type="text" required/>
        </td>
        <td><%=submit.getCreate_time()%>
        </td>
        <td><%=submit.getUpdate_time()%>
        </td>
        <td><input type="button" width="100%" value="提交成绩" align="center"
                   onclick="submitGrade(<%=submit.getSubmit_pk().getHomework_number()%>,<%=submit.getSubmit_pk().getStudent_number()%>,<%=teacher_number%>,<%=i%>)"></td>
    </tr>
    <%
                i++;
            }
        }
    %>
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
