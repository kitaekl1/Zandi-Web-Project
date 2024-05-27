<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="dao" class="com.member.cfmemberDAO"></jsp:useBean>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

<%
String id = request.getParameter("id");
String pass = request.getParameter("pass");

// 아이디와 비밀번호가 모두 입력되었는지 확인
if ((id == null || id.trim().isEmpty()) && (pass == null || pass.trim().isEmpty())) {
%>
    <script type="text/javascript">
        alert("아이디와 비밀번호를 입력해주세요.");
        history.go(-1);
    </script>
<%
} else if (id == null || id.trim().isEmpty()) {
%>
    <script type="text/javascript">
        alert("아이디를 입력해주세요.");
        history.go(-1);
    </script>
<%
} else if (pass == null || pass.trim().isEmpty()) {
%>
    <script type="text/javascript">
        alert("비밀번호를 입력해주세요.");
        history.go(-1);
    </script>
<%
} else {
    // 아이디와 비밀번호가 모두 입력된 경우에는 로그인 처리를 진행
    int check = dao.loginCheck(id, pass);
    
    if (check == 1) {
        session.setAttribute("loginID", id);
        response.sendRedirect("login.jsp");
    } else if (check == 0) {
%>
        <script type="text/javascript">
            alert("비밀번호가 틀렸습니다.");
            history.go(-1);
        </script>
<%
    } else {
%>
        <script type="text/javascript">
            alert("아이디가 존재하지 않습니다.");
            history.go(-1);
        </script>
<%
    }
}
%>

</body>
</html>