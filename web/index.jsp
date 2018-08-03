<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2018/8/2
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
  <head>
    <title>Demo</title>
  </head>
  <body>
      <div>
          <h3>通过sql查询语句生成excel电子表格</h3>
          <form id = "sqlForm" action = "FromDatabaseToExcelServlet">
              请输入sql查询语句: <br>
              <input type="text" id = "sql" name = "sql"><br>
              请输入想要创建文件的地址：<br>
              <input type = "text" name = "path1"><br>
              <button type="button" onclick="submitSqlForm()">提交</button>
          </form>
      </div>
      <div>
          <h3>通过json生成excel电子表格</h3>
          <form id = "jsonForm" action = "FromJsonToExcelServlet">
              请输入json: <br>
              <input type="text" name="json"><br>
              请输入想要创建文件的地址：<br>
              <input type = "text" name = "path2"><br>
              <input type = "submit" value = "提交">
          </form>
      </div>
      <div>
          <h3>通过excel电子表格生成json</h3>
          <form id = "excelForm" action = "FromExcelToJsonServlet" method="post" enctype="multipart/form-data" >
              请选择文件: <br>
              <input type="file" name="excelFile"><br>
              <input type = "submit" value = "提交">
          </form>
      </div>
  </body>
  <script  type="text/javaScript">
      function submitSqlForm() {
          var myForm = document.getElementById("sqlForm");
          var sqlStr = document.getElementById("sql");
          sqlStr.value = window.btoa(sqlStr.value);
          myForm.submit();
      }
  </script>
</html>
