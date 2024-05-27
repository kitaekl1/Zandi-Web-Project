<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="com.member.*" %>
     <%
     	request.setCharacterEncoding("utf-8");
     %>
     <jsp:useBean id="dao" class="com.member.cfmemberDAO" />
     <jsp:useBean id="vo" class="com.member.cfmemberVO">
     <jsp:setProperty property="*" name="vo"/>
     </jsp:useBean>
     
     <%
     String loginID = (String)session.getAttribute("loginID");
     vo.setmId(loginID);
     dao.updateMember(vo);
     %>
     
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="refresh" content="3;url=login.jsp">
<title>회원정보 수정</title>
</head>
<body>

<div align="center">
<font size="5" face="궁서체">
입력하신 내용대로 <b>회원정보가 수정되었습니다.</b><br>
3초 후에 Login Page로 이동합니다.

</font>



</div>

</body>
</html>