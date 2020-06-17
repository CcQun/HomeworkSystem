<%@ page import="com.example.homework.db.model.Homework" %>
<%@ page import="com.example.homework.core.cm.HKWithTNS" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/17
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取我发布的作业</title>
    <script>
        function gotoSubmitCondition(homework_number, teacher_number) {
            let homework = document.getElementById("homeworkId")
            homework.setAttribute("value", homework_number)
            let teacher = document.getElementById("teacherId")
            teacher.setAttribute("value", teacher_number)
            let submit = document.getElementById("submit")
            submit.setAttribute("action", "${pageContext.request.contextPath}/teacher/submitCondition")
            submit.submit()
        }
        function gotoStatisticalCondition(homework_number, teacher_number) {
            let homework = document.getElementById("homeworkId")
            homework.setAttribute("value", homework_number)
            let teacher = document.getElementById("teacherId")
            teacher.setAttribute("value", teacher_number)
            let submit = document.getElementById("submit")
            submit.setAttribute("action", "${pageContext.request.contextPath}/teacher/statisticalCondition")
            submit.submit()
        }
    </script>
</head>
<body>
<form id="submit" method="post">
    <input id="homeworkId" name="homework_number" type="hidden">
    <input id="teacherId" name="teacher_number" type="hidden">
    <table align="center" width="1300" border="1">
        <tr>
            <th width="5%">作业id</th>
            <th width="10%" style="word-break: break-all;word-wrap: break-word;">作业标题</th>
            <th width="25%" style="word-break: break-all;word-wrap: break-word;">作业内容</th>
            <th width="5%">作业状态</th>
            <th width="15%">更新时间</th>
            <th width="15%">开始时间</th>
            <th width="15%">截止时间</th>
            <th width="5%">查看提交信息</th>
            <th width="5%">查看统计信息</th>
        </tr>
        <%
            String teacher_number = (String) request.getAttribute("teacher_number");
            List<Homework> list = (List<Homework>) request.getAttribute("homework_list");
            if (list == null || list.size() <= 0) {

            } else {
                for (Homework homework : list) {
        %>
        <tr>
            <td><%=homework.getHomework_number()%>
            </td>
            <td><%=homework.getHomework_title()%>
            </td>
            <td><%=homework.getHomework_content()%>
            </td>
            <td><%=homework.getHomework_state()%>
            </td>
            <td><%=homework.getUpdate_time()%>
            </td>
            <td><%=homework.getCreate_time()%>
            </td>
            <td><%=homework.getEnd_time()%>
            </td>
            <td><input type="button" width="100%" value="查看提交情况" align="center"
                       onclick="gotoSubmitCondition(<%=homework.getHomework_number()%>,<%=teacher_number%>)"></td>
            <td><input type="button" width="100%" value="查看作业统计" align="center"
                       onclick="gotoStatisticalCondition(<%=homework.getHomework_number()%>,<%=teacher_number%>)"></td>
        </tr>
        <%
                }
            }
        %>
    </table>
</form>
</body>
</html>
