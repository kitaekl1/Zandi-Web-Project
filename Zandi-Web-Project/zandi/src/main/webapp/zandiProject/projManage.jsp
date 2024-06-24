<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
$(document).ready(function(){
	
	let prName = '<c:out value="${enroll_result}"/>';
	
	checkResult(prName);
	
	function checkResult(result){
		
		if(result === ''){
			return;
		}
		
		alert("프로젝트'"+ prName +"'을 등록하였습니다.");
		
	}

});
</script>
</body>
</html>