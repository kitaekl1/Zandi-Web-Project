<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assey.zandi.*, java.util.List" %>

<%
String loginID = (String) session.getAttribute("loginID");
if (loginID == null) {
    response.sendRedirect("login.jsp");
    return;
}

ProjectDAO dao = new ProjectDAO();

int pageSize = 2; // 페이지당 표시할 항목 수
int currentPage = 1; // 현재 페이지 번호
if (request.getParameter("pageNum") != null) {
    currentPage = Integer.parseInt(request.getParameter("pageNum"));
}

int startRow = (currentPage - 1) * pageSize + 1;
int endRow = currentPage * pageSize;

// 해당 사용자의 북마크 목록 가져오기
List<ProjectVO> bookmarkedProjects = dao.getBookmarkedProjects(loginID, startRow, endRow);

// 전체 북마크 개수 가져오기
int totalBookmarks = dao.getBookmarkedProjectCount(loginID);
int number = totalBookmarks - (currentPage - 1) * pageSize;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 북마크 목록</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@1.0.0/css/bulma.min.css">
<style type="text/css">
body {
    font-family: 'Roboto', sans-serif;
    background-color: #f4f4f9;
    margin: 0;
    padding: 0;
    padding-top: 150px;
    display: flex;
    flex-direction: column;
    align-items: center;
}

h2 {
    color: #333;
    text-align: center;
    margin-bottom: 20px;
    font-size: 2em;
    font-weight: 700;
}

table {
    width: 90%;
    min-width: 800px;
    max-width: 800px;
    border-collapse: collapse;
    margin: 20px 0;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
    overflow: hidden;
    text-align: center;
}

table th, table td {
    border: 1px solid #ddd;
    padding: 12px;
}

table th {
    background-color: #000000; 
    color: #ffffff; 
    font-weight: 500;
}

table tr:nth-child(even) {
    background-color: #f9f9f9;
}

table tr:hover {
    background-color: #f1f1f1;
}

button, input[type="submit"] {
    background-color: #ff5722;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    text-transform: uppercase;
    font-weight: 500;
    width: 100px;
    height: 28px;
}

button:hover, input[type="submit"]:hover {
    background-color: #e64a19;
}

.pagination {
    display: flex;
    justify-content: center;
    margin-top: 20px;
}

.pagination a {
    margin: 0 5px;
    padding: 10px 15px;
    color: #333;
}

.pagination a.active {
    color: black;
    font-size: 1.5em;
    font-weight: bold;
}

</style>
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
    if (bookmarkedProjects != null) {
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
    }
    %>
</table>

<div class="pagination">
    <%
    int pageCount = (int) Math.ceil(totalBookmarks / (double) pageSize);
    int pageBlock = 10;
    int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
    int endPage = startPage + pageBlock - 1;
    if (endPage > pageCount) {
        endPage = pageCount;
    }

    if (startPage > 1) {
    %>
        <a href="dibsview.jsp?pageNum=<%= startPage - 1 %>">이전</a>
    <%
    }
    for (int i = startPage; i <= endPage; i++) {
        	if (i == currentPage) {
        	    %>
        	        <a href="dibsview.jsp?pageNum=<%= i %>" class="active"><%= i %></a>
    <%
        } else {
    %>
        <a href="dibsview.jsp?pageNum=<%= i %>"><%= i %></a>
    <%
        }
    }
    if (endPage < pageCount) {
    %>
        <a href="dibsview.jsp?pageNum=<%= endPage + 1 %>">다음</a>
    <%
    }
    %>
</div>

</body>
</html>
