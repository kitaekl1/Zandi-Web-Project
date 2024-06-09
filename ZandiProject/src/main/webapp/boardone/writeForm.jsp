<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 올리기</title>
<link href="style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="script.js"></script>
</head>
<body>
<div align="center"><b>공지사항 양식을 작성해주세요</b></div><br>

<form action="writeProc.jsp" method="post" name="writeForm" onsubmit="return writeSave()">
    <table width="400" border="1" cellpadding="0" cellspacing="0" align="center">
        <tr>
            <td width="70" align="center">제목</td>
            <td width="330">
                <input type="text" size="50" maxlength="50" name="aTitle">
            </td>
        </tr>
        <tr>
            <td width="70" align="center">내용</td>
            <td width="330">
                <textarea rows="13" cols="50" name="aAnnounce"></textarea>
            </td>
        </tr>
        <tr>
            <td width="70" align="center">비밀번호</td>
            <td width="330">
                <input type="password" size="10" maxlength="10" name="pass">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="글쓰기">
                <input type="reset" value="다시작성">
                <input type="button" value="목록" onclick="window.location='list.jsp'">
            </td>
        </tr>
    </table>
</form>

</body>
</html>
