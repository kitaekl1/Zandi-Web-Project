<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 삭제</title>
<link href="style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="script.js"></script>
</head>
<body>
<div align="center">
<b>글삭제</b><br>

<form action="deleteProc.jsp?pageNum=<%=pageNum %>" method="post" name="delForm" onsubmit="return writeSave()">

<table width="360" border="1" cellpadding="0" cellspacing="0" align="center">
<tr height="30">
	<td align="center">
		<b>비밀번호를 입력해 주세요.</b>
	</td>
</tr>

<tr height="30">
	<td align="center">
		<input type="password" size="8" maxlength="12" name="pass">
		<input type="hidden" name="num" value="<%=num %>">
	</td>
</tr>

<tr height="30">
	<td align="center">
		<input type="submit" value="글삭제">
		<input type="button" value="목록보기" 
		onclick="document.location.href='list.jsp?pageNum=<%=pageNum%>'">
	</td>
</tr>
</table>
</form>
</div>
</body>
</html>