<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    
   <link rel="stylesheet" href="<c:url value='/resources/css/Nav.css' />">
    <link href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>
<script type="text/javascript" src="<c:url value='/resources/js/script.js' />"></script>
    <style type="text/css">


        .logform {
            background-color: #f0f0f0; /* 로그인 폼 배경색 */
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            border-radius: 8px;
            width: 400px; /* 폼 너비 */
            margin-top: 100px; /* 네비게이션 바와의 간격 */
            margin-left: auto;
            margin-right: auto;
        }

        #idSub, #passSub {
            box-sizing: border-box;
            width: 100%; /* 전체 너비 사용 */
            height: 40px;
            margin-top: 15px;
            margin-bottom: 15px;
            padding: 15px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-size: 16px;
            font-weight: bold;
        }

        #log, #acc {
            width: 100%;
            height: 40px;
            font-size: 16px;
        }

        #log {
            margin-top: 16px;
            background-color: #000;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        #acc {
            margin-top: 30px;
            background-color: #74AC60;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .error-message {
            color: red;
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <%@ include file="/zandiMainPage/Navigator.jsp" %>
    <br><br><br><br>
    <br><br><br><br>

    
    
    
    <div class="logform">
        <form action="${pageContext.request.contextPath}/zandi/login" method="post">
            <div>
                <label for="idSub">아이디</label>
                <input type="text" id="idSub" name="id" class="input" placeholder="아이디를 입력하세요">

                <label for="passSub">비밀번호</label>
                <input type="password" id="passSub" name="pass" class="input" placeholder="비밀번호를 입력하세요">

                <input type="submit" value="로그인" id="log" class="button is-black">
                <input type="button" value="회원가입" id="acc" onclick="window.location='${pageContext.request.contextPath}/zandiaccount/regForm.jsp'" class="button is-success">

                <c:if test="${not empty errorMessage}">
                    <div class="error-message">${errorMessage}</div>
                </c:if>
            </div>
        </form>
    </div>
</body>
</html>