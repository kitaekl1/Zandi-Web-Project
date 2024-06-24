<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.assey.zandi.account.CfmemberVO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원정보 수정</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/Nav.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/updateProfile.css' />">
    <script type="text/javascript" src="<c:url value='/resources/js/updateScript.js' />"></script>
    <link href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>
</head>
<body>
    <%@ include file="/zandiMainPage/Navigator.jsp" %>
    <div class="container">
        <h1 class="title">회원정보 수정</h1>
        <c:if test="${not empty member}">
        <form name="regForm" action="<c:url value='/zandi/updateProfile' />" method="post" onsubmit="return updateCheck()">
            <div class="form-group">
                <label for="memId">아이디</label>
                <input type="text" id="memId" value="${member.memId}" disabled>
            </div>
            <div class="form-group">
                <label for="memNickname">닉네임</label>
                <input type="text" id="memNickname" name="memNickname" value="${member.memNickname}">
            </div>
            <div class="form-group">
                <label for="memPw">비밀번호</label>
                <input type="password" id="memPw" name="memPw" value="${member.memPw}">
            </div>
            <div class="form-group">
                <label for="repass">비밀번호 재확인</label>
                <input type="password" id="repass" name="repass">
            </div>
            <div class="form-group">
                <label for="memMail">이메일</label>
                <input type="email" id="memMail" name="memMail" value="${member.memMail}">
            </div>
            <div class="form-group">
                <label for="memPost">우편번호</label>
                <input type="text" id="memPost" name="memPost" value="${member.memPost}">
                <button type="button" onclick="sample6_execDaumPostcode()">주소 찾기</button>
            </div>
            <div class="form-group">
                <label for="memAddress">주소</label>
                <input type="text" id="memAddress" name="memAddress" value="${member.memAddress}">
            </div>
            <div class="form-group">
                <label for="memSaddress">상세 주소</label>
                <input type="text" id="memSaddress" name="memSaddress" value="${member.memSaddress}">
            </div>
            <div class="form-buttons">
                <input type="submit" value="수정하기" class="button is-primary">
                <input type="button" value="취소" class="button is-secondary" onclick="window.history.back();">
            </div>
        </form>
        </c:if>
    </div>
</body>
</html>