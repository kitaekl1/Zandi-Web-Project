<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
    <title>회원 탈퇴</title>
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
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
        }
        button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        .message {
            color: red;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>회원 탈퇴</h2>
        <form action="${pageContext.request.contextPath}/deleteMember" method="post">
            <label for="id">아이디:</label>
            <input type="text" id="id" name="id" required><br>
            <label for="password">비밀번호:</label>
            <input type="password" id="password" name="password" required><br>
            <button type="submit">탈퇴</button>
        </form>
        <c:if test="${not empty message}">
            <p class="message">${message}</p>
        </c:if>
    </div>
</body>
</html>