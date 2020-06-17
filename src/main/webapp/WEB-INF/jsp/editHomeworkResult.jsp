<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/17
  Time: 8:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑作业结果</title>
    <script>
        function backStudent(id) {
            let studentId = document.getElementById("studentId")
            studentId.setAttribute("value", id)
            let back = document.getElementById("back")
            back.submit()
        }
    </script>
</head>
<body>
<div align="center">
    <%
        String msg = (String) request.getAttribute("msg");
        String student_number = (String) request.getAttribute("student_number");
    %>
    <p style="margin: 50px; font-family: 'Arial Black'; font-size: 45px; color: darkgreen"><%=msg%></p>
    <form id="back" action="${pageContext.request.contextPath}/jsp/backStudent" method="post">
        <table style="line-height: 40px">
            <input id="studentId" name="student_number" type="hidden">
            <tr align="center">
                <td><input type="button" width="100%" value="返回主页" onclick="backStudent(<%=student_number%>)"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
