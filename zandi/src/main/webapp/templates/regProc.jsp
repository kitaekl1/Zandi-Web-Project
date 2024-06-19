<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.member.cfmemberDAO" %>
<%@ page import="com.member.cfmemberVO" %>
    
<%    request.setCharacterEncoding("utf-8");  %>
    <jsp:useBean id="dao" class="com.member.cfmemberDAO" />
    <jsp:useBean id="vo" class="com.member.cfmemberVO" />
	<jsp:setProperty property="*" name="vo"/>
<%
boolean flag = dao.membereInsert(vo);


%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="refresh" content="3;url=login.jsp">
<title>회원가입 확인</title>
</head>
<body bgcolor = "#FFFFCC">
<br>
<div align="center">
<%
	if(flag){
		out.println("<b>회원가입을 진심으로 감사합니다.</b><br>");
		/* out.println("<a href=login.jsp>로그인</a>"); */
	}else{	
		out.println("<b>다시 작성하여 주십시오.</b><br>");
		out.println("<a href=regForm.jsp>다시작성</a>");
	}
%>
</div>

</body>
</html>