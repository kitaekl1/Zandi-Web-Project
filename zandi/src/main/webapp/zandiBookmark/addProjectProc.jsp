<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assey.zandi.ProjectDAO" %>
<%@ page import="com.assey.zandi.ProjectVO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Date" %>

<%
// loginID를 받아옴
String loginID = request.getParameter("loginID");

// loginID가 없으면 로그인 페이지로 리다이렉트
if(loginID == null || loginID.isEmpty()) { // loginID가 null 또는 빈 문자열인 경우 처리
    response.sendRedirect("/zandiaccount/login.jsp");
    return; // 리다이렉트 후 코드 실행 중단
}

Timestamp prStartdate = null;
Timestamp prEnddate = null;
String prStartdateStr = request.getParameter("prStartdate");
String prEnddateStr = request.getParameter("prEnddate");

try {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date prStartDate = dateFormat.parse(prStartdateStr);
    Date prEndDate = dateFormat.parse(prEnddateStr);
    prStartdate = new Timestamp(prStartDate.getTime());
    prEnddate = new Timestamp(prEndDate.getTime());
} catch (Exception e) {
    e.printStackTrace();
}

// ProjectVO 객체 생성 및 속성 설정
ProjectVO article = new ProjectVO();
article.setPrId(loginID);
article.setPrName(request.getParameter("prName"));
article.setPrDescription(request.getParameter("prDescription"));
article.setPrTeam(request.getParameter("prTeam"));
article.setPrCategory(request.getParameter("prCategory"));
article.setPrGoal(request.getParameter("prGoal"));
article.setPrCurrent(request.getParameter("prCurrent"));
article.setPrLikecount(Integer.parseInt(request.getParameter("prLikecount")));
article.setPrStartdate(prStartdate);
article.setPrEnddate(prEnddate);


// 데이터베이스에 프로젝트 추가
ProjectDAO dbPro = ProjectDAO.getInstance();
boolean isSuccess = dbPro.addProject(article);

if (isSuccess) {
    response.sendRedirect("/zandiaccount/login.jsp"); // 추가 후 로그인 페이지로 리다이렉트
} else {
    response.sendRedirect("/error/error404.jsp"); // 오류 페이지로 리다이렉트
}
%>