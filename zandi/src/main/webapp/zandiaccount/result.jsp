<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입 결과</title>
        <link rel="stylesheet" href="<c:url value='/resources/css/Nav.css' />">
        <style type="text/css">
        h1{

        font-size: 50px !important;
        }
        
        </style>
</head>
<body>
    <%@ include file="/zandiMainPage/Navigator.jsp" %>
    <br><br><br><br><br><br>
    
    
    <h1 align="center">${message}</h1>
    
    <%@ include file="/zandiMainPage/footer.jsp"%>
</body>
</html>