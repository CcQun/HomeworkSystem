<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/15
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>老师主页</title>
    <script>
        function addHomework(id) {
            let teacherId = document.getElementById("teacherId")
            teacherId.setAttribute("value", id)
            let addHK = document.getElementById("addHK")
            addHK.submit()
        }
    </script>
</head>
<body>
<%
    String teacher_number = (String)request.getAttribute("teacher_number");
%>
<div align="center">
    <h2 align="center">请选择以下操作</h2>
    <form id="addHK" action="${pageContext.request.contextPath}/jsp/gotoAddHomework" method="post">
        <table style="line-height: 40px">
            <input id="teacherId" name="teacher_number" type="hidden">
            <tr align="center">
                <td><input type="button" width="100%" value="添加作业" onclick="addHomework(<%=teacher_number%>)"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
