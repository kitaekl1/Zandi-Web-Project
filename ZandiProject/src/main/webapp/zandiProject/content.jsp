<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="com.zandiproject.ProjectDAO" %>
    <%@ page import="com.zandiproject.ProjectVO" %>
    <%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세보기</title>
<link href="style.css" type="text/css" rel="stylesheet">
</head>

<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    try{
    
    	ProjectDAO dbPro = ProjectDAO.getInstance();	
    	ProjectVO article = dbPro.getArticle(num);
    

%>

<script>
function addBookmark(prCode) {
    // Ajax 요청을 보내는 부분
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "addBookmark.jsp", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // 요청이 완료되면 수행할 동작
            // 예를 들어, 성공 메시지를 출력하거나 다른 동작을 수행할 수 있음
            alert(xhr.responseText);
        }
    };
    xhr.send("prCode=" + prCode);
}
</script>

<body>
<div align="center">
<b>글 상세보기</b><br>

<form action="" >
<table width="500" border="1" cellpadding="0" cellspacing="0" align="center">

	<tr height="30">
		<td align="center" width="125">글번호</td>
		<td align="center" width="125"><%=article.getPrCode()%></td>
	</tr>
	
	<tr height="30">
		<td align="center" width="125">작성자</td>
		<td align="center" width="125"><%=article.getPrId()%></td>

	</tr>
	
	<tr height="30">
		<td align="center" width="125">글제목</td>
		<td align="center" width="375" colspan="3"><%=article.getPrName() %></td>
	</tr>
	
	<tr height="30">
		<td align="center" width="125">글내용</td>
		<td align="center" width="375" colspan="3">
			<pre><%=article.getPrDescription() %></pre>
		</td>
	</tr>
	
	<tr height="30">
		<td colspan="4" align="right">
<%-- 			<input type="button" value="글수정" onclick="document.location.href='updateForm.jsp?num=<%=article.getNum()%>&pageNum=<%=pageNum%>'">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="삭제" onclick="document.location.href='deleteForm.jsp?num=<%=article.getNum()%>&pageNum=<%=pageNum%>'">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="답변글쓰기" onclick="document.location.href='writeForm.jsp?num=<%=num%>&ref=<%=ref%>&step=<%=step%>&depth=<%=depth%>'">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="글목록" onclick="document.location.href='list.jsp?num=<%=article.getNum()%>'"> --%>
			
			<input type="button" value="찜등록" onclick="addBookmark(<%=article.getPrCode()%>)">
		</td>
	</tr>





</table>
<%}catch(Exception e){} %>
</form>



</div>




</body>
</html>