<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    String loginID = (String)session.getAttribute("loginID");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Navigator</title>
<link href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/slide.css' />">

</head>
<body>
<nav class="navbar is-white is-fixed-top">
   <div class="container">
    <div class="navbar-brand">
      <a href="<c:url value='/zandiMainPage/MainPage.jsp' />" class="navbar-brand">
        <img src="<c:url value='/resources/img/zandiLogo.jpg' />" alt="ZANDI Logo">
      </a>
    </div>
    <div class="navbar-menu">
      <div class="navbar-end">
        <div class="navbar-item">
          <div class="buttons">
            <c:if test="${loginID == null}">
              <div class="project_button mr-2">
                <a href="<c:url value='/zandiaccount/login.jsp'/>" class="button is-small">프로젝트 올리기</a>
              </div>
              <div class="login_button">
                <a href="<c:url value='/zandiaccount/login.jsp'/>" class="button is-small">로그인 / 회원가입</a>
              </div>
            </c:if>
            <c:if test="${loginID != null}">
              <div class="project_button mr-2">
               <a href="<c:url value='/zandiBookmark/addProjectForm.jsp'/>" class="button is-small">프로젝트 올리기</a>
              </div>
              <div class="MyPage_button mr-2">
                <a href="<c:url value='/zandiaccount/myPage.jsp'/>" class="button is-small">마이페이지</a>
              </div>
              <div class="logout_button">
           <a href="<c:url value='/logout'/>" class="button is-small">로그아웃</a>
              </div>
            </c:if>
          </div>
        </div>
      </div>
    </div>
  </div>
</nav>
<nav class="navbar is-white second-navbar is-fixed-top" style="top: 52px;">
  <div class="container is-flex is-justify-content-space-between is-align-items-center">
    <div class="navbar-menu is-flex is-align-items-center">
      <div class="navbar-item">
        <div class="dropdown">
          <a class="navbar-item has-text-black">카테고리</a>
          <div class="dropdown-content">
            <a href="#" class="navbar-item">전체보기</a>
            <a href="#" class="navbar-item">게임</a>
            <a href="#" class="navbar-item">음악</a>
            <a href="#" class="navbar-item">도서</a>
            <a href="#" class="navbar-item">영화</a>
            <a href="#" class="navbar-item">애니메이션</a>
          </div>
        </div>
        <a href="./MainPage.jsp" class="navbar-item has-text-black">홈</a>
        <a href="./Popular.jsp" class="navbar-item has-text-black">인기</a>
        <a href="./Recommend.jsp" class="navbar-item has-text-black">추천</a>
        <a href="./deadline.jsp" class="navbar-item has-text-black">마감임박</a>
      </div>
    </div>
    <div class="navbar-end">
      <div class="navbar-item">
 	 <form class="field has-addons" action="<c:url value='/zandi/search'/>" method="get">
        <div class="control">
            <input class="input" type="search" name="searchText" placeholder="프로젝트 검색" aria-label="Search">
        </div>
        <div class="control">
            <button class="button is-info" type="submit">
                <img src="<c:url value='/resources/img/searchIcon.png'/>" alt="search Icon">
            </button>
        </div>
    </form>
      </div>
    </div>
  </div>
</nav>
</body>
</html>