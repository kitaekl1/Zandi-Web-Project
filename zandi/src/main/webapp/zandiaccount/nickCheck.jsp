<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>닉네임 중복 체크</title>
</head>
<body bgcolor="#FFFFCC">
<br>
<div align="center">
<b>${mNickname}</b>
<c:choose>
    <c:when test="${check}">
        는 이미 존재하는 닉네임입니다.<br>
    </c:when>
    <c:otherwise>
        는 사용 가능한 닉네임입니다.<br>
    </c:otherwise>
</c:choose>
<a href="#" onclick="javascript:self.close()">닫기</a>
</div>
</body>
</html>