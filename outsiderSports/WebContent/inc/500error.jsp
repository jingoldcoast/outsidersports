<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isErrorPage="true" %>
<%response.setStatus(200); %>
<!DOCTYPE html>
<html lang = "ko">
 <head>
  <meta charset="utf-8">
  <title>Insert title here</title>
  <!-- Latest compiled and minified CSS -->
   <link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
   <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <!-- jQuery library -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <!-- Latest compiled JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 </head>
 <body>
  <div class="container">
  <h3>500 ERROR</h3>
  <p>관리자에게 문의 바랍니다.</p>
  <%=exception.getMessage() %>
  </div>
 </body>
</html>