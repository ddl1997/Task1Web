<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2018/8/2
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Demo</title>
  </head>
  <body>
    <h4>通过sql查询语句生成excel电子表格</h4>
    <form id = "sqlForm" action = "FromDatabaseToExcelServlet">
        请输入sql查询语句: <br>
        <input type="text" id = "sql" name = "sql"><br>
        请输入想要创建文件的地址：<br>
        <input type = "text" name = "path1"><br>
        <button type="button" onclick="submitSqlForm()">提交</button>
    </form>
    <h4>通过json生成excel电子表格</h4>
    <form id = "jsonForm" action = "FromJsonToExcelServlet">
        请输入json: <br>
        <input type="text" name="json"><br>
        请输入想要创建文件的地址：<br>
        <input type = "text" name = "path2"><br>
        <button type="button" onclick="submitJsonForm()">提交</button>
    </form>
  </body>
  <script  type="text/javaScript">
      function submitSqlForm() {
          var myForm = document.getElementById("sqlForm");
          var sqlStr = document.getElementById("sql");
          sqlStr.value = window.btoa(sqlStr.value);
          myForm.style.display = "none";
          myForm.submit();
      }

      function submitJsonForm() {
          var myForm = document.getElementById("jsonForm");
          myForm.style.display = "none";
          myForm.submit();
          //document.body.removeChild(myForm);
      }
  </script>
</html>
