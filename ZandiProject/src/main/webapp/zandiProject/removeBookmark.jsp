<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zandiproject.*, java.util.*" %>

<%
String loginID = (String) session.getAttribute("loginID");
String prCodeStr = request.getParameter("prCode");

// prCode를 int로 변환
int prCode = Integer.parseInt(prCodeStr);

// prCode를 사용하여 북마크를 삭제하고 prlikecount를 감소시킵니다.
ProjectDAO dao = new ProjectDAO();

// 북마크 삭제와 prlikecount 감소를 동시에 수행하기 위해 트랜잭션 처리
boolean success = dao.removeBookmarkAndDecrementLikeCount(loginID, prCode);

if (success) {
    out.println("<script>alert('북마크가 삭제되었습니다.'); window.location.href='/zandiaccount/login.jsp';</script>");
} else {
    out.println("<script>alert('북마크 삭제에 실패했습니다.'); history.go(-1);</script>");
}
%>
