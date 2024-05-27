<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zandiproject.*, java.util.List" %>

<%
String loginID = (String) session.getAttribute("loginID");
if(loginID == null) {
    // 로그인이 되어있지 않은 경우, 로그인 페이지로 이동하거나 다른 처리를 해야 합니다.
    response.sendRedirect("login.jsp");
} else {
    ProjectDAO dao = new ProjectDAO();
    
    // 해당 사용자의 북마크 목록만 가져오도록 수정
    List<ProjectVO> bookmarkedProjects = dao.getBookmarkedProjects(loginID);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 북마크 목록</title>
<link href="style.css" type="text/css" rel="stylesheet">
</head>
<body>
<h2>내 북마크 목록</h2>
<table border="1">
    <tr>
        <th>프로젝트 코드</th>
        <th>프로젝트 이름</th>
        <th>설명</th>
        <th>북마크 삭제</th>
    </tr>
    <%
    for (ProjectVO project : bookmarkedProjects) {
    %>
    <tr>
        <td><%= project.getPrCode() %></td>
        <td><%= project.getPrName() %></td>
        <td><%= project.getPrDescription() %></td>
        <td>
            <form action="removeBookmark.jsp" method="post" style="display:inline;">
                <input type="hidden" name="prCode" value="<%= project.getPrCode() %>">
                <input type="submit" value="삭제">
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
<%
}
%>