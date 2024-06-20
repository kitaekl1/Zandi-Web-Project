<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 중복 체크</title>
</head>
<body bgcolor="#FFFFCC">
<br>
<div align="center">
    <b>ID: ${param.id}</b>
    <c:choose>
        <c:when test="${check}">
            는 이미 존재하는 아이디입니다.<br>
        </c:when>
        <c:otherwise>
            는 사용 가능한 아이디입니다.<br>
        </c:otherwise>
    </c:choose>
    <br>
    <button onclick="window.close();">닫기</button>
</div>
</body>
</html>
