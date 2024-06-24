<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>프로젝트 목록</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/Nav.css' />">
    <link href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .project-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            margin-top: 150px;
        }
        .project-card {
            width: 300px;
            margin: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            overflow: hidden;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        .project-card img {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }
        .project-details {
            padding: 15px;
            text-align: center;
        }
        .project-details h3 {
            margin: 10px 0;
            font-size: 1.2em;
        }
        .project-details p {
            margin: 5px 0;
            color: #777;
        }
        .project-progress {
            margin-top: 10px;
            color: #333;
            font-weight: bold;
        }
        .project-time {
            margin-top: 5px;
            color: #999;
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
    <br><br><br>

    <div class="project-container">
        <c:if test="${empty projectList}">
            <p>검색 결과가 없습니다.</p>
        </c:if>
        <c:forEach var="project" items="${projectList}">
            <div class="project-card">
                <img src="<c:url value='/resources/img/projectImage.png'/>" alt="Project Image">
                <div class="project-details">
                    <h3>${project.prName}</h3>
                    <p>${project.prCategory} / ${project.prTeam}</p>
                    <div class="project-progress">
                        <c:out value="${(project.prCurrent / project.prGoal) * 100}" />% ${project.prCurrent}원
                    </div>
                    <div class="project-time">
                        ${project.prEnddate} 까지
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="pagination">
        <c:if test="${groupStartPage > 1}">
            <a href="<c:url value='/zandi/search?searchText=${searchText}&pageNum=${groupStartPage - 1}'/>">이전</a>
        </c:if>
        <c:forEach begin="${groupStartPage}" end="${groupEndPage}" var="i">
            <c:choose>
                <c:when test="${i == currentPage}">
                    <a href="<c:url value='/zandi/search?searchText=${searchText}&pageNum=${i}'/>" class="active">${i}</a>
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/zandi/search?searchText=${searchText}&pageNum=${i}'/>">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${groupEndPage < pageCount}">
            <a href="<c:url value='/zandi/search?searchText=${searchText}&pageNum=${groupEndPage + 1}'/>">다음</a>
        </c:if>
    </div>
</body>
</html>