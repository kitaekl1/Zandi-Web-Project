<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.member.*" %>
    
    <jsp:useBean id="dao" class="com.member.cfmemberDAO"></jsp:useBean>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정 </title>
<link href="style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="script.js"></script>
</head>
<%
String loginID = (String)session.getAttribute("loginID");
cfmemberVO vo = dao.getMember(loginID);
%>
<body>
<form action="modifyProc.jsp" method="post" name="regForm">
<table border="1">
	<tr>
		<td colspan="2" align="center">회원정보 수정</td>
	</tr>

	<tr>
		<td align="right">아이디</td>
		<td><%=vo.getmId()%></td>
	</tr>

	<tr>
		<td align="right">비밀번호</td>
		<td><input type="password" name="mPw" value="<%=vo.getmPw()%>"></td>
	</tr>
	
	<tr>
		<td align="right">비밀번호 확인</td>
		<td><input type="password" name="repass" value="<%=vo.getmPw()%>"></td>
	</tr>
	
	<tr>
		<td align="right">이름</td>
		<td><%=vo.getmName()%></td>
	</tr>
	
	<tr>
		<td align="right">전화번호</td>
		<td>
		<input type="text" name="mPhone" size="15" value="<%=vo.getmPhone()%>">
		</td>
	</tr>
	
	<tr>
		<td align="right">이메일</td>
		<td>
		<input type="text" name="mMail" value="<%=vo.getmMail()%>">
		</td>
	</tr>
	
	<tr>
		<td align="right">우편번호</td>
		<td>
		<input type="text" name="mPost" value="<%=vo.getmPost()%>">
		<input type="button" value="찾기" onclick="zipCheck()">
		</td>
	</tr>
	
	<tr>
		<td align="right">주소</td>
		<td><input type="text" name="mAddress" size="50" value="<%=vo.getmAddress()%>"></td>
	</tr>
	
	<tr>
		<td align="right">상세 주소</td>
		<td><input type="text" name="mSaddress" size="50" value="<%=vo.getmSaddress()%>"></td>
	</tr>
	
	<tr>
	<td colspan="2" align="center">
		<input type="button" value="정보수정" onclick="updateCheck()">&nbsp;&nbsp;
		<input type="button" value="취소" onclick = "javascript:window.location = 'login.jsp'">
	</td>
	</tr>
	
	
</table>
</form>

</body>
</html>