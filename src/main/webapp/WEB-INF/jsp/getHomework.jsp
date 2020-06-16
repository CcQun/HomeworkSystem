<%@ page import="com.example.homework.core.cm.HKWithTName" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/16
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业列表</title>
    <script>
        function gotoSubmit(homework_number,student_number) {
            let homework = document.getElementById("homeworkId")
            homework.setAttribute("value", homework_number)
            let student = document.getElementById("studentId")
            student.setAttribute("value", student_number)
            let submit = document.getElementById("submit")
            submit.submit()
        }
    </script>
</head>
<body>
<form id="submit" method="post" action="${pageContext.request.contextPath}/jsp/gotoSubmitHomework">
    <input id="homeworkId" name="homework_number" type="hidden">
    <input id="studentId" name="student_number" type="hidden">
    <table align="center" width="1200" border="1">
        <tr>
            <th width="5%">作业id</th>
            <th width="5%">老师</th>
            <th width="10%" style="word-break: break-all;word-wrap: break-word;">作业标题</th>
            <th width="20%" style="word-break: break-all;word-wrap: break-word;">作业内容</th>
            <th width="5%">作业状态</th>
            <th width="15%">更新时间</th>
            <th width="15%">开始时间</th>
            <th width="15%">截止时间</th>
            <th width="10%">作业提交信息</th>
        </tr>
        <%
            String student_number = (String)request.getAttribute("student_number");
            List<HKWithTName> list = (List<HKWithTName>) request.getAttribute("homework_list");
            if (list == null || list.size() <= 0) {

            } else {
                for (HKWithTName hkWithTName : list) {
        %>
        <tr>
            <td><%=hkWithTName.getHomework_number()%>
            </td>
            <td><%=hkWithTName.getTeacher_name()%>
            </td>
            <td><%=hkWithTName.getHomework_title()%>
            </td>
            <td><%=hkWithTName.getHomework_content()%>
            </td>
            <td><%=hkWithTName.getHomework_state()%>
            </td>
            <td><%=hkWithTName.getUpdate_time()%>
            </td>
            <td><%=hkWithTName.getCreate_time()%>
            </td>
            <td><%=hkWithTName.getEnd_time()%>
            </td>
            <%
                if (hkWithTName.getHomework_state().equals("进行中")) {
            %>
            <td><input type="button" width="100%" value="前往提交作业" align="center"
                       onclick="gotoSubmit(<%=hkWithTName.getHomework_number()%>,<%=student_number%>)"></td>
        </tr>
        <%
                    }
                }
            }
        %>
    </table>
</form>
</body>
</html>
