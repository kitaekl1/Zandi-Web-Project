<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>내 찜등록 목록</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/Nav.css' />">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@1.0.0/css/bulma.min.css">
    <style type="text/css">
        body {
            font-family: 'Roboto', sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        h1 {
            font-size: 25px !important;
            font-weight: 800 !important;
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
            color: #ffffff !important; 
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
            border: 1px solid #ddd;
            border-radius: 4px;
            text-decoration: none;
            font-size: 0.9em;
        }
        .pagination a.active {
            color: #fff;
            background-color: #3273dc;
            border-color: #3273dc;
            font-weight: bold;
        }
        .pagination a:hover {
            background-color: #3273dc;
            color: #fff;
        }
    </style>
</head>
<body>
    <%@ include file="/zandiMainPage/Navigator.jsp" %>
    <br><br><br><br><br><br>
    <h1>내 찜등록 목록</h1>

    <c:if test="${not empty bookmarkedProjects}">
        <table border="1">
            <thead>
                <tr>
                    <th>프로젝트 코드</th>
                    <th>프로젝트 이름</th>
                    <th>설명</th>
                    <th>북마크 삭제</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="project" items="${bookmarkedProjects}">
                    <tr>
                        <td>${project.prCode}</td>
                        <td>${project.prName}</td>
                        <td>${project.prDescription}</td>
                        <td>
                            <form action="<c:url value='/zandi/bookmark/delete'/>" method="post" style="display:inline;">
                                <input type="hidden" name="prCode" value="${project.prCode}">
                                <input type="submit" value="삭제">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty bookmarkedProjects}">
        <p>No bookmarks found.</p>    
    </c:if>

    <div class="pagination">
        <c:if test="${groupStartPage > 1}">
            <a href="<c:url value='/zandi/bookmarkList?pageNum=${groupStartPage - 1}'/>">이전</a>
        </c:if>
        <c:forEach begin="${groupStartPage}" end="${groupEndPage}" var="i">
            <c:choose>
                <c:when test="${i == currentPage}">
                    <a href="<c:url value='/zandi/bookmarkList?pageNum=${i}'/>" class="active">${i}</a>
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/zandi/bookmarkList?pageNum=${i}'/>">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${groupEndPage < pageCount}">
            <a href="<c:url value='/zandi/bookmarkList?pageNum=${groupEndPage + 1}'/>">다음</a>
        </c:if>
    </div>

</body>
</html>