<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="dao" class="com.member.cfmemberDAO" />
    
    <%
    	String id = request.getParameter("mId");
   		 boolean check = dao.idCheck(id);
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 중복 체크</title>
<script type="text/javascript" src="script.js"></script>

</head>
<body bgcolor="#FFFFCC">
<br>
<div align="center">
<b><%=id %></b>
<%
if(check){	//true일 경우 아이디가 존재
	out.println("는 이미 존재하는 아이디입니다.<br>");
}else {
	out.println("는 사용 가능한 아이디입니다.<br>");
}
%>
<a href="#" onclick="javascript:self.close()">닫기</a>

</div>

</body>
</html>