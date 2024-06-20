<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>마이페이지</title>
        <link rel="stylesheet" href="<c:url value='/resources/css/Nav.css' />">
    <link href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css" rel="stylesheet">
    <style>
        .my-page-container {
            margin-top: 70px;
            padding: 20px;
        }
        .my-page-header {
            margin-bottom: 30px;
        }
        .my-page-section {
            margin-bottom: 20px;
        }
        .my-page-section h2 {
            margin-bottom: 15px;
        }
        .my-page-section a {
            display: block;
            margin-bottom: 10px;
            color: #3273dc;
        }
        .my-page-section a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <%@ include file="/zandiMainPage/Navigator.jsp" %>
    <br><br><br><br><br><br>
    <div class="container my-page-container">
        <h1 class="title my-page-header">마이페이지</h1>
        <div class="columns">
            <div class="column is-one-third">
                <div class="box my-page-section">
                    <h2 class="subtitle">프로젝트 관리</h2>
                    <a href="<c:url value='/zandi/bookmarkList'/>">북마크 리스트</a>
                    <a href="<c:url value='/zandiBookmark/myProjects.jsp'/>">나의 프로젝트</a>
                </div>
                <div class="box my-page-section">
                    <h2 class="subtitle">계정 설정</h2>
                    <a href="<c:url value='/zandiaccount/updateProfile.jsp'/>">회원 정보 수정</a>
                    <a href="<c:url value='/zandiaccount/deleteAccount.jsp'/>">회원 탈퇴</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
