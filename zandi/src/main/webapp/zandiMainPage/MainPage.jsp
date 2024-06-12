<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mainpage</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
</head>
<body>
<%@ include file="Navigator.jsp" %>
<br>
<br>
<!-- jumbotron -->
<%! String greeting = "ZANDI";
    String tagline = "ZANDI에 오신것을 환영합니다.";
    %>
 
  <div class="jumbotron">
    <div class="container">
      <h1 class="display-3">
        <%= greeting %>
      </h1>
    </div>
  </div>


<!-- main -->
<main role="main">
  <div class="container">
    <div class="text-center">
      <h3>
        <%= tagline %>
      </h3>
      <%
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        String formattedNow = now.format(formatter);
      %>
      <p>현재 접속 시각 <%= formattedNow %></p>
    </div>
  </div>
</main>
<%@ include file="footer.jsp" %>

</body>
</html>