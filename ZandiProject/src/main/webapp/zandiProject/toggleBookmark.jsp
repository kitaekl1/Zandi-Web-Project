<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zandiproject.*" %>

<%
int prCode = Integer.parseInt(request.getParameter("prCode"));
String loginID = request.getParameter("loginID");
ProjectDAO dao = new ProjectDAO();
boolean isBookmarked = false;
int likeCount = 0;

// 북마크 토글
boolean isSuccess = dao.toggleBookmark(prCode, loginID);

if (isSuccess) {
    // 북마크가 추가된 경우에만 좋아요 수 증가
    if (dao.isBookmarked(prCode, loginID)) {
        dao.increaseLikeCount(prCode);
        isBookmarked = true;
    } else {
        // 북마크가 제거된 경우, 좋아요 수 감소
        dao.decreaseLikeCount(prCode);
    }
    // 업데이트된 좋아요 수 가져오기
    likeCount = dao.getLikeCount(prCode);
}

// JSON 응답 생성
response.setContentType("application/json");
response.setCharacterEncoding("UTF-8");
out.print("{\"isSuccess\": " + isSuccess + ", \"isBookmarked\": " + isBookmarked + ", \"likeCount\": " + likeCount + "}");
out.flush();
%>