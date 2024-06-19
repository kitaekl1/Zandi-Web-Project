<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script.js"></script>

<style type="text/css">

#abc{

padding:4px;
margin:4px;

width: 400px;
height: 30px;

background: #FFFFFF;
border: 1px solid #E0E0E0;
border-radius: 8px;

}

#abcd{

width: 88px;
height: 30px;

background: #000000;
border-radius: 8px;

color:#FFFFFF;
}



table{

  position: relative;
  padding: 20px;
  width: 300px;
  background-color: #82BE6D;
  border-radius: 5px;
  top: 25px;
  left: 400px;
}

h2{
padding: 0 0 0 625px;
}


h5{
	margin-left: 30px;
	margin-top: 0px;
	margin-bottom: 3px;

}

#ren{

width: 142px;
height: 36px;
left: 631px;
top: 1032px;

/* Button Shadow */
box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.05);
border-radius: 8px;

color: #FFFFFF;


}


</style>




</head>
<body>
<a href="#">
<img src="img/zandi.png" height="100" width="150">
</a>
<br>
<h2>회원가입</h2>
<br><br>

<form action="regProc.jsp" name="regForm" method="post">
<table border="0">

	<tr>
		<td align="left" colspan="3">아이디</td>
	</tr>

	<tr>
		<td><input type="text" name="mId" id="abc" placeholder="아이디를 입력하세요"></td>
		<td><input type="button" value="중복확인" onclick="idCheck(this.form.mId.value)" id="abcd"></td>
	</tr>

	<tr>
	<td align="left">비밀번호</td>
	</tr>
	
	<tr>
	<td>
		<input type="password" name="mPw" id="abc" placeholder="비밀번호를 입력하세요">
	</td>
	</tr>
	<tr>
	<td><input type="password" name="repass" id="abc" placeholder="비밀번호를 한번 더 입력해주세요">
	<h5>8~15자 이내의 특수기호, 숫자를 포함한 비밀번호를 입력하세요</h5></td>
	
	<tr>
    	<td align="left" colspan="3">닉네임/팀네임</td>
	</tr>
	<tr>
    	<td><input type="text" name="mNickname" id="abc" placeholder="닉네임을 입력하세요"></td>
    	<td><input type="button" value="중복확인" onclick="nickCheck(this.form.mNickname.value)" id="abcd"></td>
	</tr>

	<tr>
		<td align="left">이름</td>
	</tr>
	
	<tr>
		<td><input type="text" name="mName" id="abc" placeholder="이름을 입력해주세요"></td>
	</tr>

	<tr>
		<td align="left">전화번호</td>
	</tr>
	
	<tr>
		<td><input type="text" name="mPhone" id="abc" placeholder="전화번호를 입력해주세요"></td>
	</tr>

	<tr>
		<td align="left">이메일</td>
	</tr>
	
	<tr>
		<td><input type="email" name="mMail" id="abc" placeholder="이메일을 입력해 주세요"></td>
	</tr>

	<tr>	
		<td align="left" colspan="2">우편번호</td>
	</tr>
	
	<tr>
		<td><input type="text" name="mPost" id="abc"></td>
		<td><input type="button" value="찾기" onclick="zipCheck()" id="abcd"></td>
	</tr>

	<tr>
		<td align="left">주소</td>
	</tr>
	
	<tr>
		<td><input type="text" name="mAddress" size="50" id="abc"></td>
	</tr>

	<tr>
		<td align="left">상세주소</td>
	</tr>
	
	<tr>
		<td><input type="text" name="mSaddress" size="50" id="abc" placeholder="상세주소를 입력해 주세요"></td>
	</tr>

<tr>
	<td colspan="2" align="center">
		<input type="button" value="회원가입" id="ren" style="background-color:#000000" onclick="inputCheck()">&nbsp;&nbsp;
		<input type="reset" value="다시작성" id="ren" style="background-color:#828282">
	</td>
</tr>


</table>
</form>

</body>
</html>