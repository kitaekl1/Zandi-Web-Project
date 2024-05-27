<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="dao" class="com.member.cfmemberDAO" />
    
    <%
    	String nickname = request.getParameter("mNickname");
   		 boolean check = dao.nickCheck(nickname);
   		 
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>닉네임 중복 체크</title>
<script type="text/javascript" src="script.js"></script>
</head>
<body bgcolor="#FFFFCC">
<br>
<div align="center">
<b><%=nickname %></b>
<%
if(check){	//true일 경우 아이디가 존재
	out.println("는 이미 존재하는 닉네임입니다.<br>");
}else {
	out.println("는 사용 가능한 닉네임입니다.<br>");
}
%>
<a href="#" onclick="javascript:self.close()">닫기</a>

</div>

</body>
</html>