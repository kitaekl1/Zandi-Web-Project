<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zandiproject.*, java.util.*, java.sql.Timestamp, java.text.SimpleDateFormat, java.util.concurrent.TimeUnit, java.net.URLEncoder, com.google.gson.Gson" %>
<%
int pageSize = 3;
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
String pageNum = request.getParameter("pageNum");

// 검색 파라미터
String searchWhat = request.getParameter("searchWhat");
String searchText = request.getParameter("searchText");

// 파라미터를 가져와서 한글로 변환
if (searchText != null && !searchText.trim().isEmpty()) {
    searchText = new String(searchText.getBytes("utf-8"), "utf-8");
} else {
    searchText = "";
}

if (pageNum == null) {
    pageNum = "1";
}

int currentPage = Integer.parseInt(pageNum);
int startRow = (currentPage - 1) * pageSize + 1;
int endRow = currentPage * pageSize;

int count = 0;
int number = 0;

List<ProjectVO> projectList = null;
ProjectDAO dao = new ProjectDAO();

if (searchText.trim().isEmpty()) { // 검색이 아닐 경우
    count = dao.getAllProjects().size(); // 전체 프로젝트 수
    if (count > 0) {
        projectList = dao.getProject(startRow, endRow); // 페이징된 프로젝트 목록 가져오기
    }
} else { // 검색일 경우
    count = dao.getProjectCount(searchWhat, searchText);
    if (count > 0) {
        projectList = dao.getProject(searchWhat, searchText, startRow, endRow);
    }
}

number = count - (currentPage - 1) * pageSize;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 목록</title>

<script type="text/javascript">
function validateSearch(form) {
    var searchText = form.searchText.value.trim();
    if (searchText.length == 0) {
        alert("검색어를 입력해 주세요.");
        return false;
    }
    return true;
}

window.onload = function() {
    // JSP 변수 값을 JavaScript 변수에 할당
    var loginID = '<%= (String) session.getAttribute("loginID") %>';

    // 페이지가 로드된 후에 toggleBookmark 함수가 호출될 수 있도록 이벤트 핸들러 등록
    var buttons = document.querySelectorAll("[id^='bookmarkButton_']");
    buttons.forEach(function(button) {
        var prCode = button.id.split("_")[1];
        button.onclick = function() {
            toggleBookmark(prCode, loginID);
        };
    });
};

function toggleBookmark(prCode, loginID) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "<%= request.getContextPath() %>/zandiProject/toggleBookmark.jsp", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            if (response.isSuccess) {
                // 업데이트된 좋아요 수 가져와서 해당 프로젝트에 적용
                var likeCountElement = document.getElementById("likeCount_" + prCode);
                likeCountElement.textContent = response.likeCount;
                // 북마크 버튼 텍스트 업데이트
                var bookmarkButton = document.getElementById("bookmarkButton_" + prCode);
                bookmarkButton.textContent = response.isBookmarked ? "북마크 해제" : "북마크 추가";
            } else {
                alert("북마크 처리에 실패하였습니다.");
            }
        }
    };

    var params = "prCode=" + prCode + "&loginID=" + loginID;
    xhr.send(params);
}
</script>

</head>
<body>
<% String loginID = (String) session.getAttribute("loginID"); %>

<h2>프로젝트 목록(전체 프로젝트 : <%= count %>)</h2>

<%
if (count == 0) {
%>
<p>프로젝트가 없습니다.</p>

<%
} else {
%>
<table border="1">
    <tr>
        <th>프로젝트 코드</th>
        <th>프로젝트 이름</th>
        <th>내용</th>
        <th>기간</th>
        <th>찜등록 수</th>
        <th>북마크</th>
    </tr>

<%
for (int i = 0; i < projectList.size(); i++) {
    ProjectVO project = projectList.get(i);
    Date startDate = project.getPrStartdate();
    Date endDate = project.getPrEnddate();

    long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
    long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    
    // 프로젝트의 작성자와 현재 로그인한 사용자가 같은지 여부를 확인
    boolean isMyProject = loginID != null && loginID.equals(project.getPrId());
    
    // 북마크 상태 확인
    boolean isBookmarked = dao.isBookmarked(project.getPrCode(), loginID);
%>
<tr>
    <td><%= project.getPrCode() %></td>
    <td><a href="<%= request.getContextPath() %>/zandiProject/getProject.jsp?prCode=<%= project.getPrCode() %>"><%= project.getPrName() %></a></td>
    <td><%= project.getPrDescription() %></td>
    <td><%= sdf.format(startDate) %> ~ <%= sdf.format(endDate) %> (총 <%= diffInDays %>일)</td>
    <td id="likeCount_<%= project.getPrCode() %>"><%= project.getPrLikecount() %></td>
    <td>
        <%
        // 내 프로젝트인 경우 북마크 버튼 표시하지 않음
        if (!isMyProject) {
        %>
        <button id="bookmarkButton_<%= project.getPrCode() %>" onclick="toggleBookmark('<%= project.getPrCode() %>', '<%= loginID %>')"><%= isBookmarked ? "북마크 해제" : "북마크 추가" %></button>
        <%
        } else {
            // 내 프로젝트인 경우 북마크 버튼 대신에 '-' 표시
            out.println("-");
        }
        %>
    </td>
</tr>
<% } %>
</table>
<% } %>

<%-- 페이징 처리 --%>
<%
if (count > 0) {
    int pageBlock = 4;
    int pageCount = (count + pageSize - 1) / pageSize; // 전체 페이지 수 계산

    int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
    int endPage = Math.min(startPage + pageBlock - 1, pageCount);

    if (startPage > pageBlock) {
        // 이전 페이지로 이동하는 링크 추가
        if (searchText.trim().isEmpty()) {
%>
    <a href="<%= request.getContextPath() %>/zandiaccount/login?pageNum=<%= startPage - pageBlock %>">[이전]</a>
<%} else {%>
    <a href="<%= request.getContextPath() %>/zandiaccount/login?pageNum=<%= startPage - pageBlock %>&searchWhat=<%= searchWhat %>&searchText=<%= URLEncoder.encode(searchText, "UTF-8") %>">[이전]</a>
<%
        }
    }

    // 페이지 번호 출력
    for (int i = startPage; i <= endPage; i++) {
        if (i == currentPage) {
            // 현재 페이지인 경우 링크 없이 출력
%>
<%= i %>
<%} else {
            // 다른 페이지인 경우 해당 페이지로 이동하는 링크 출력
            if (searchText.trim().isEmpty()) {
%>
    <a href="<%= request.getContextPath() %>/zandiaccount/login.jsp?pageNum=<%= i %>"><%= i %></a>
<%} else {%>
    <a href="<%= request.getContextPath() %>/zandiaccount/login.jsp?pageNum=<%= i %>&searchWhat=<%= searchWhat %>&searchText=<%= URLEncoder.encode(searchText, "UTF-8") %>"><%= i %></a>
<%
            }
        }
    }

    if (endPage < pageCount) {
        // 다음 페이지로 이동하는 링크 추가
        if (searchText.trim().isEmpty()) {
%>
    <a href="<%= request.getContextPath() %>/zandiaccount/login.jsp?pageNum=<%= startPage + pageBlock %>">[다음]</a>
<%
        } else {
%>
    <a href="<%= request.getContextPath() %>/zandiaccount/login.jsp?pageNum=<%= startPage + pageBlock %>&searchWhat=<%= searchWhat %>&searchText=<%= URLEncoder.encode(searchText, "UTF-8") %>">[다음]</a>
<%
}
}
}
%>
<!-- 검색창 -->
<form action="<%= request.getContextPath() %>/zandiaccount/login.jsp" method="get" onsubmit="return validateSearch(this);">
    <select name="searchWhat">
        <option value="prName" <%= "prName".equals(searchWhat) ? "selected" : "" %>>프로젝트 이름</option>
        <option value="prDescription" <%= "prDescription".equals(searchWhat) ? "selected" : "" %>>내용</option>
    </select>
    <input type="text" name="searchText" value="<%= searchText != null ? searchText : "" %>">
    <input type="submit" value="검색">
</form>
</body>
</html>
