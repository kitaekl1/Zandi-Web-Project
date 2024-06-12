<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assey.zandi.*, java.util.*, java.sql.Timestamp" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%

String loginID = (String) session.getAttribute("loginID");



// 현재 값 및 좋아요 수를 0으로 설정
String prCurrent = "0";
String prLikecount = "0";


// 프로젝트 객체 생성 및 설정
ProjectVO project = new ProjectVO();

project.setPrCurrent(prCurrent);
project.setPrLikecount(Integer.parseInt(prLikecount));



%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 추가</title>
</head>
<body>
<h2>프로젝트 추가</h2>
<form method="post" action="addProjectProc.jsp">
    프로젝트 이름: <input type="text" name="prName" required><br>
    설명: <textarea name="prDescription" required></textarea><br>
    팀: <input type="text" name="prTeam" required><br>
    카테고리: <input type="text" name="prCategory" required><br>
    목표: <input type="text" name="prGoal" required><br>
    시작 날짜: <input type="date" name="prStartdate" required><br>
    종료 날짜: <input type="date" name="prEnddate" required><br>
        <input type="hidden" name="loginID" value="<%= loginID %>">
        <input type="hidden" name="prCurrent" value="<%= prCurrent %>">
		<input type="hidden" name="prLikecount" value="<%= prLikecount %>">
        
    <input type="submit" value="추가">
    
</form>
</body>
</html>