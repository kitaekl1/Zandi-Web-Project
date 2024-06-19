<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
    <title>회원 탈퇴 결과</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            width: 300px;
            margin: 0 auto;
            padding: 50px 0;
            text-align: center;
        }
        .message {
            color: red;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>회원 탈퇴 결과</h2>
        <c:if test="${not empty message}">
            <p class="message">${message}</p>
        </c:if>
        <a href="${pageContext.request.contextPath}/login">로그인 페이지로 돌아가기</a>
    </div>
</body>
</html>