<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 탈퇴</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/Nav.css' />">
    <link href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css" rel="stylesheet">
    <style>
        .dele {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh; /* 화면 전체 높이를 채우도록 설정 */
        }
        .password-field {
            margin-top: 20px; /* 필요에 따라 조정 */
        }
        .password-input {
            max-width: 400px; /* 입력 필드의 최대 너비 */
            width: 120%;
        }
        .button-group {
            margin-top: 10px; /* 버튼 그룹과 위의 요소 사이의 간격 */
        }
    </style>
</head>
<body>
    <%@ include file="/zandiMainPage/Navigator.jsp" %>
    <div class="dele">
        <h1 class="title">회원 탈퇴</h1>
        <br>
        <form action="<c:url value='/zandi/deleteAccount'/>" method="post">
            <div class="field password-field">
                <label class="label">비밀번호</label>
                <div class="control">
                    <input class="input password-input" type="password" name="password" placeholder="비밀번호를 입력하세요" required>
                </div>
            </div>
            <div class="field">
                <p>정말로 탈퇴하시겠습니까?</p>
            </div>
            <div class="field">
                <button class="button is-danger" type="submit">탈퇴</button>
                <a class="button" href="<c:url value='/zandiMainPage/MainPage.jsp'/>">취소</a>
            </div>
        </form>
    </div>
</body>
</html>