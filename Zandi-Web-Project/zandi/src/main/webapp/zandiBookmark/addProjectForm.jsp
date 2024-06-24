<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>프로젝트 등록</title>
    <link href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/resources/css/Nav.css' />">
    <style type="text/css">
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: 'Arial', sans-serif;
            background-color: #f0f0f0;
        }
        form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 300px;
            position: relative;
            top: 50px;
        }
        h2 {
            text-align: center;
        }
    </style>
</head>
<body>
    <%@ include file="/zandiMainPage/Navigator.jsp" %>
    <h2>프로젝트 등록</h2>
    <form action="<c:url value='/zandi/addProjectForm'/>" method="post">
        <label for="prName">프로젝트 이름:</label>
        <input type="text" id="prName" name="prName" required><br>

        <label for="prDescription">프로젝트 설명:</label>
        <input type="text" id="prDescription" name="prDescription" required><br>

        <label for="prTeam">팀 이름:</label>
        <input type="text" id="prTeam" name="prTeam" required><br>

        <label for="prCategory">카테고리:</label>
        <select id="prCategory" name="prCategory" required>
            <option value="게임">게임</option>
            <option value="음악">음악</option>
            <option value="도서">도서</option>
            <option value="영화">영화</option>
            <option value="애니메이션">애니메이션</option>
        </select><br>

        <label for="prGoal">목표 금액:</label>
        <input type="text" id="prGoal" name="prGoal" required><br>

        <label for="prStartdate">시작 날짜:</label>
        <input type="datetime-local" id="prStartdate" name="prStartdate" required><br>

        <label for="prEnddate">종료 날짜:</label>
        <input type="datetime-local" id="prEnddate" name="prEnddate" required><br>

        <button type="submit">등록</button>
    </form>
</body>
</html>