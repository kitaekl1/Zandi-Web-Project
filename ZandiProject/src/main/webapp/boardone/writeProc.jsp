<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.boardone.BoardDAO" %>
<%@ page import="java.sql.Timestamp" %>

<%request.setCharacterEncoding("utf-8");%>

<jsp:useBean id="article" class="com.boardone.BoardVO" scope="page">
	<jsp:setProperty name="article" property="*"/>
</jsp:useBean>

<%

	String aTitle = request.getParameter("aTitle");
	String aAnnounce = request.getParameter("aAnnounce");
	String pass = request.getParameter("pass");
	
	article.setaTitle(aTitle);
	article.setaAnnounce(aAnnounce);
	article.setPass(pass);
	BoardDAO dbPro = BoardDAO.getInstance();
	
	// 데이터베이스에서 글 추가하는 메소드 호출
	dbPro.insertArticle(article);
	
	response.sendRedirect("list.jsp");
%>