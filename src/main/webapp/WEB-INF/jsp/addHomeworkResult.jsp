<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/16
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加作业结果</title>
    <script>
        function backTeacher(id) {
            let teacherId = document.getElementById("teacherId")
            teacherId.setAttribute("value", id)
            let addHK = document.getElementById("back")
            addHK.submit()
        }
    </script>
</head>
<body>
<div align="center">
    <%
        String msg = (String) request.getAttribute("msg");
        String teacher_number = (String) request.getAttribute("teacher_number");
    %>
    <p style="margin: 50px; font-family: 'Arial Black'; font-size: 45px; color: darkgreen"><%=msg%><%=teacher_number%></p>
    <form id="back" action="${pageContext.request.contextPath}/jsp/backTeacher" method="post">
        <table style="line-height: 40px">
            <input id="teacherId" name="teacher_number" type="hidden">
            <tr align="center">
                <td><input type="button" width="100%" value="返回主页" onclick="backTeacher(<%=teacher_number%>)"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
