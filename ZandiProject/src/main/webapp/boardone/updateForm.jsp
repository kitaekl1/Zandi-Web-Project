<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ page import="com.boardone.BoardDAO" %>
<%@ page import="com.boardone.BoardVO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 글 수정 </title>
<link href="style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="script.js"></script>
</head>
<%
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");

try{
	BoardDAO dbPro = BoardDAO.getInstance();
	BoardVO article = dbPro.updateGetArticle(num);
%>
<body>
<div align="center">
<b>글수정</b>
<form action="updateProc.jsp?pageNum=<%=pageNum %>" method="post" name="writeForm" onsubmit="return writeSave()">

<table width="400" border="1" cellpadding="0" cellspacing="0" align="center">

<tr>
	<td width="70" align="center">제목</td>
	<td width="330" align="left">
		<input type="text" size="40" maxlength="50" name="subject" value="<%=article.getaTitle() %>">
	</td>
</tr>

<tr>
	<td width="70" align="center">내용</td>
	<td width="330" align="left">
		<textarea rows="13" cols="50" name="content"><%=article.getaAnnounce() %></textarea>
	</td>
	
<tr>
	<td width="70" align="center">비밀번호</td>
	<td width="330" align="left">
		<input type="password" size="8" maxlength="12" name="pass">
	</td>
</tr>

<tr>
	<td colspan="2" align="center">
		<input type="submit" value="글수정">
		<input type="reset" value="다시작성">
		<input type="button" value="목록보기" 
		onclick="document.location.href='list.jsp?pageNum=<%=pageNum%>'">
	</td>
</tr>
</table>
</form>
</div>
<%}catch(Exception e){} %>
</body>
</html>