<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2018/8/3
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Display</title>
</head>
<body>
    <a href = <%=session.getAttribute("message")%>>文件地址</a>
</body>
    <script>
        function display() {
            var url = window.location.host + <%=session.getAttribute("message")%>;
            alert(url);
            document.getElementById("address").innerHTML = url;
        }
    </script>
</html>
