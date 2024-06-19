<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mainpage</title>
<link href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css"
	rel="stylesheet">
<style>
.hero.is-primary {
	background-color: #00d1b2;
	color: white;
}
</style>
</head>
<body>
	<%@ include file="Navigator.jsp"%>
	<br>
	<br>
	<!-- jumbotron -->
	<%!String greeting = "ZANDI";
	String tagline = "ZANDI에 오신 것을 환영합니다.";%>

	<section class="hero is-primary">
		<div class="hero-body">
			<div class="container">
				<h1 class="title">
					<%=greeting%>
				</h1>
			</div>
		</div>
	</section>

	<!-- main -->
	<main role="main">
		<div class="container mt-5">
			<div class="content has-text-centered">
				<h3 class="title is-3">
					<%=tagline%>
				</h3>
				<%
					LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
				String formattedNow = now.format(formatter);
				%>
				<p>
					현재 접속 시각
					<%=formattedNow%></p>
			</div>
		</div>
	</main>
	<%@ include file="footer.jsp"%>

	<!-- Bulma JS dependencies (optional, for full Bulma functionality) -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css"></script>
</body>
</html>
