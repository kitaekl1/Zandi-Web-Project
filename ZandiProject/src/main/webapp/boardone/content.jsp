<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ page import="java.util.*" %>
<%@ page import="com.boardone.BoardDAO" %>
<%@ page import="com.boardone.BoardVO" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세 보기</title>
<link href="style.css" type="text/css" rel="stylesheet">
</head>
<%
int num = Integer.parseInt(request.getParameter("aNum"));
String pageNum = request.getParameter("pageNum");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

try{// db연결
	BoardDAO dbPro = BoardDAO.getInstance(); 
	BoardVO article = dbPro.getArticle(num);
%>
<body>
<div align="center">
<b>글 상세보기</b><br>

<form action="">
<table width="500" border="1" cellpadding="0" cellspacing="0" align="center">
<tr height="30">
	<td align="center" width="125">글번호</td>
	<td align="center" width="125"><%=article.getaNum() %></td>
	<td align="center" width="125">조회수</td>
	<td align="center" width="125"><%=article.getReadcount() %></td>
</tr>

<tr height="30">
	<td align="center" width="125">작성일</td>
	<td align="center" width="125"><%=article.getaDate() %></td>
</tr>

<tr height="30">
	<td align="center" width="125">글제목</td>
	<td align="center" width="375" colspan="3"><%=article.getaTitle() %></td>
</tr>

<tr height="30">
	<td align="center" width="125">글내용</td>
	<td align="center" width="375" colspan="3">
		<pre><%=article.getaAnnounce() %></pre>
	</td>
</tr>

<tr height="30">
	<td colspan="4" align="right">
	<input type="button" value="글수정" onclick="document.location.href='updateForm.jsp?num=<%=article.getaNum()%>&pageNum=<%=pageNum%>'">
	&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="삭제" onclick="document.location.href='deleteForm.jsp?num=<%=article.getaNum()%>&pageNum=<%=pageNum%>'">
	&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="글목록" onclick="document.location.href='list.jsp?num=<%=article.getaNum()%>'">
	</td>
</tr>
</table>
<%} catch(Exception e){} %>
</form>
</div>
</body>
</html>