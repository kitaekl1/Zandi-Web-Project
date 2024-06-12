<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MainHome</title>
<style>
  /* Adjust the body padding to avoid overlap with fixed top navbar */
  body {
    padding-top: 60px; /* Height of the fixed top navbar */
  }
</style>
</head>
<body>
<!-- menu -->
<nav class="navbar navbar-expand navbar-white bg-white fixed-top">
  <div class="container">
    <div class="navbar-header">
      <a href="./MainPage.jsp" class="navbar-brand">
        <img src="/resources/img/zandiLogo.jpg" width="150" height="50"/>
      </a>
    </div>
    <div class="login_area">
				<div class="login_button"><a href="/zandiaccount/login.jsp">로그인</a></div>
				<span><a href="/zandiaccount/regForm.jsp">회원가입</a></span>
			</div>
  </div>
</nav>

<nav class="navbar navbar-expand navbar-dark bg-dark">
  <div class="container">
    <div class="navbar-menu">
      <a href="./MainPage.jsp" class="navbar-brand">
        	홈
      </a>
      &nbsp;&nbsp;&nbsp;
      <a href="./Popular.jsp" class="navbar-item">
        인기
      </a>
      &nbsp;&nbsp;&nbsp;
      <a href="./Recommend.jsp" class="navbar-item">
        추천
      </a>
      &nbsp;&nbsp;&nbsp;
      <a href="./deadline.jsp" class="navbar-item">
        마감임박
      </a>
      &nbsp;&nbsp;&nbsp;
      <a href="./MyPage.jsp" class="navbar-item">
        마이페이지
      </a>

    </div>
  </div>
</nav>

</body>
</html>