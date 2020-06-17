<%@ page import="com.example.homework.core.cm.HKWithTNS" %>
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
        function gotoSubmit(homework_number, student_number) {
            let homework = document.getElementById("homeworkId")
            homework.setAttribute("value", homework_number)
            let student = document.getElementById("studentId")
            student.setAttribute("value", student_number)
            let submit = document.getElementById("submit")
            submit.setAttribute("action","${pageContext.request.contextPath}/jsp/gotoSubmitHomework")
            submit.submit()
        }
        function gotoEdit(homework_number, student_number) {
            let homework = document.getElementById("homeworkId")
            homework.setAttribute("value", homework_number)
            let student = document.getElementById("studentId")
            student.setAttribute("value", student_number)
            let submit = document.getElementById("submit")
            submit.setAttribute("action","${pageContext.request.contextPath}/jsp/gotoEditHomework")
            submit.submit()
        }
        function backStudent(id) {
            let studentId = document.getElementById("stuId")
            studentId.setAttribute("value", id)
            let back = document.getElementById("back")
            back.submit()
        }
    </script>
</head>
<body>
<form id="submit" method="post">
    <input id="homeworkId" name="homework_number" type="hidden">
    <input id="studentId" name="student_number" type="hidden">
    <table align="center" width="1300" border="1">
        <tr>
            <th width="5%">作业id</th>
            <th width="5%">老师</th>
            <th width="10%" style="word-break: break-all;word-wrap: break-word;">作业标题</th>
            <th width="20%" style="word-break: break-all;word-wrap: break-word;">作业内容</th>
            <th width="5%">作业状态</th>
            <th width="5%">提交状态</th>
            <th width="15%">更新时间</th>
            <th width="15%">开始时间</th>
            <th width="15%">截止时间</th>
            <th width="5%">作业提交信息</th>
        </tr>
        <%
            String student_number = (String) request.getAttribute("student_number");
            List<HKWithTNS> list = (List<HKWithTNS>) request.getAttribute("homework_list");
            if (list == null || list.size() <= 0) {

            } else {
                for (HKWithTNS hkWithTNS : list) {
        %>
        <tr>
            <td><%=hkWithTNS.getHomework_number()%>
            </td>
            <td><%=hkWithTNS.getTeacher_name()%>
            </td>
            <td><%=hkWithTNS.getHomework_title()%>
            </td>
            <td><%=hkWithTNS.getHomework_content()%>
            </td>
            <td><%=hkWithTNS.getHomework_state()%>
            </td>
            <td><%=hkWithTNS.getSubmit_state()%>
            </td>
            <td><%=hkWithTNS.getUpdate_time()%>
            </td>
            <td><%=hkWithTNS.getCreate_time()%>
            </td>
            <td><%=hkWithTNS.getEnd_time()%>
            </td>
            <%
                if (hkWithTNS.getHomework_state().equals("进行中")) {
                    if (hkWithTNS.getSubmit_state().equals("未提交")) {
            %>
            <td><input type="button" width="100%" value="提交作业" align="center"
                       onclick="gotoSubmit(<%=hkWithTNS.getHomework_number()%>,<%=student_number%>)"></td>

            <%
            } else {
            %>
            <td><input type="button" width="100%" value="编辑作业" align="center"
                       onclick="gotoEdit(<%=hkWithTNS.getHomework_number()%>,<%=student_number%>)"></td>
            <%
                            }
                        }
                    }
                }
            %>
        </tr>
    </table>
</form>
<form id="back" action="${pageContext.request.contextPath}/jsp/backStudent" method="post">
    <table style="line-height: 40px">
        <input id="stuId" name="student_number" type="hidden">
        <tr align="center">
            <td><input type="button" width="100%" value="返回主页" onclick="backStudent(<%=student_number%>)"></td>
        </tr>
    </table>
</form>
</body>
</html>
