<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assey.zandi.*, java.util.*, com.google.gson.Gson" %>
<%
    // 세션에서 사용자 정보 가져오기
    String loginID = (String) session.getAttribute("loginID");
    int prCode = Integer.parseInt(request.getParameter("prCode"));

    // 현재 북마크 상태 확인
    ProjectDAO projectDAO = new ProjectDAO();
    boolean isBookmarked = projectDAO.isBookmarked(prCode, loginID);

    // 북마크 상태 토글 및 카운트 업데이트
    if (isBookmarked) {
        projectDAO.removeBookmark(prCode, loginID);
    } else {
        projectDAO.addBookmark(prCode, loginID);
    }

    // 업데이트된 북마크 상태와 카운트를 반환
    int updatedLikeCount = projectDAO.getBookmarkCount(prCode); // 이 메서드는 필요에 따라 추가
    boolean updatedIsBookmarked = projectDAO.isBookmarked(prCode, loginID);

    // JSON 응답 생성
    Map<String, Object> responseMap = new HashMap<>();
    responseMap.put("isSuccess", true);
    responseMap.put("likeCount", updatedLikeCount);
    responseMap.put("isBookmarked", updatedIsBookmarked);

    // JSON으로 응답
    String jsonResponse = new Gson().toJson(responseMap);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(jsonResponse);
%>