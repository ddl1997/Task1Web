<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2018/8/3
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Display</title>
</head>
<body>
    <div id = "file">
        <a href = <%=session.getAttribute("message")%>>文件地址</a>
    </div>
    <div id = "json">
        <p id = "jsonText"></p>
        <button onclick="display()">生成JSON</button>
    </div>
</body>
    <script>
        function display() {
            document.getElementById("file").style.display = "none";
            document.getElementById("jsonText").innerHTML = '<%=request.getAttribute("json")%>';
        }
    </script>
</html>
