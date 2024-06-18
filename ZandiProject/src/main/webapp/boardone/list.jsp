<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.boardone.BoardDAO" %>
<%@ page import="com.boardone.BoardVO" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
// 한페이지에 보여줄 글수
int pageSize = 3;

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>
<%
String pageNum = request.getParameter("pageNum");
//무엇을 검색할지 파라미터를 가져와야함(작성자, 제목, 내용)
String searchWhat = request.getParameter("searchWhat");
String searchText = request.getParameter("searchText");
//파라미터를 가져와서 한글로 변환처리함
if(searchText!=null){
    searchText=new String(searchText.getBytes("utf-8"),"utf-8");
}

if(pageNum==null){
    pageNum="1";
}
int currentPage = Integer.parseInt(pageNum);
int startRow = (currentPage-1)*pageSize+1;
int endRow = currentPage*pageSize;

int count = 0;
int number =0;

List<BoardVO> articleList = null;
BoardDAO dbPro = BoardDAO.getInstance();

if(searchText==null){ // 검색이 아닐때
    count = dbPro.getArticleCount(); // 전체글의 목록수

    if(count>0){
        articleList = dbPro.getArticles(startRow,endRow);
    }
}else{ //검색일 경우
    count = dbPro.getArticleCount(searchWhat, searchText); // 전체글의 목록수

    if(count>0){
        // 검색목록이 하나라도 존재한다면 검색리스트 출력
        articleList = dbPro.getArticles(searchWhat, searchText, startRow, endRow);
    }
}
number = count-(currentPage-1)*pageSize;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 게시판</title>
<link href="style.css" type="text/css" rel="stylesheet">
</head>
<body>
<div align="center"><b>글목록(전체글:<%=count %>)</b>
<table width="700">
<tr>
    <td align="right"><a href="writeForm.jsp">글쓰기</a></td>
</tr>
</table>
<% if (articleList == null || articleList.isEmpty()) { %>
    <table width="700" border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td align="center" colspan="4">공지사항이 없습니다.</td>
        </tr>
    </table>
<% } else { %>
    <table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
        <tr height="30">
            <td align="center" width="50">번호</td>
            <td align="center" width="250">제목</td>
            <td align="center" width="100">작성일</td>
            <td align="center" width="150">조회수</td>
        </tr>
        <% for (int i = 0; i < articleList.size(); i++) { %>
            <tr height="30">
                <td align="center" width="50"><%= number-- %></td>
                <td width="250">
                    <a href="content.jsp?num=<%= articleList.get(i).getaNum() %>&pageNum=<%= currentPage %>">
                        <%= articleList.get(i).getaTitle() %>
                    </a>
                </td>
                <td align="center" width="100"><%= articleList.get(i).getaDate() %></td>
                <td align="center" width="150"><%= articleList.get(i).getReadcount() %></td>
            </tr>
        <% } %>
    </table>
<% } %>
<!-- 페이징 처리 -->
<%
if(count>0){
    int pageBlock = 5;
    int imsi = count%pageSize ==0?0:1;
    int pageCount = count/pageSize+imsi;
    int startPage = (int)((currentPage-1)/pageBlock)*pageBlock+1;
    int endPage = startPage+pageBlock-1;
    if(endPage>pageCount) endPage = pageCount;
    if(startPage>pageBlock){
        //검색일 경우와 아닐경우 페이징처리해야함
        if(searchText == null){
%>
<a href="list.jsp?pageNum=<%=startPage-pageBlock %>">[이전]</a>
<%}else{ %>
<a href="list.jsp?pageNum=<%=startPage-pageBlock %>&searchWhat=<%=searchWhat%>&searchText=<%=searchText%>">[이전]</a>
<%
}
}
for(int i=startPage; i<=endPage;i++){
    if(searchText==null){ //검색이 아닐경우
%>
<a href="list.jsp?pageNum=<%=i %>">[<%=i %>]</a>
<%}else{ //검색일 경우%> 
<a href="list.jsp?pageNum=<%=i %>&searchWhat=<%=searchWhat%>&searchText=<%=searchText%>">[<%=i %>]</a>
<%}
}
if(endPage<pageCount){
    if(searchText==null){
    %>
    <a href="list.jsp?pageNum=<%=startPage+pageBlock %>">[다음]</a>
    <%} else{%>
    <a href="list.jsp?pageNum=<%=startPage+pageBlock %>&searchWhat=<%=searchWhat%>&searchText=<%=searchText%>">[다음]</a>
<%
        }
    }
} 
%>
<!-- 검색창 -->
<form action="list.jsp">
<select name="searchWhat">
    <option value="title">제목</option>
    <option value="announce">내용</option>
</select>
<input type="text" name="searchText">
<input type="submit" value="검색">
</form>
</div>
</body>
</html>