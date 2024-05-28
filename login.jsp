<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String loginID = (String)session.getAttribute("loginID");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log in</title>

<style type="text/css">
    #log{
        box-sizing: border-box;
        position: relative;
        width: 394px;
        height: 48px;
        background: #000000;
        border: 1px solid #E0E0E0;
        border-radius: 8px;
        color: #FFFFFF;
    }

    #acc{
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        padding: 14px 24px;
        gap: 8px;
        position: relative;
        width: 394px;
        height: 48px;
        left: 0px;
        top: -19px;
        background: #74AC60;
        /* Button Shadow */
        box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.05);
        border-radius: 8px;
        border:0px;
    }

    #idSub, #passSub {
        box-sizing: border-box;
        position: relative;
        width: 394px;
        height: 48px;
        background: #FFFFFF;
        border: 1px solid #E0E0E0;
        border-radius: 8px;
    }

    #logbef, #logaft {
        position: absolute;
        padding: 20px;
        width: 300px;
        background-color: #EEEFF1;
        border-radius: 5px;
    }

    #logbef {
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }

    #logaft {
        top: 20px;
        right: 15px;
    }
</style>

<script type="text/javascript">
function validateSearch(form) {
    var searchText = form.searchText.value.trim();
    if (searchText.length == 0) {
        alert("검색어를 입력해 주세요.");
        return false;
    }
    return true;
}

function toggleBookmark(prCode, loginID) {
    var bookmarkButton = document.getElementById("bookmarkButton_" + prCode);
    bookmarkButton.disabled = true; // 클릭 중복 방지

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "<%= request.getContextPath() %>/zandiProject/toggleBookmark.jsp", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                if (response.isSuccess) {
                    // 업데이트된 좋아요 수 가져와서 해당 프로젝트에 적용
                    var likeCountElement = document.getElementById("likeCount_" + prCode);
                    likeCountElement.textContent = response.likeCount;
                    // 북마크 버튼 텍스트 업데이트
                    bookmarkButton.textContent = response.isBookmarked ? "북마크 해제" : "북마크 추가";
                } else {
                    alert("북마크 처리에 실패하였습니다.");
                }
            } else {
                alert("서버와의 통신에 문제가 발생했습니다.");
            }
            bookmarkButton.disabled = false; // 요청 완료 후 버튼 활성화
        }
    };

    var params = "prCode=" + prCode + "&loginID=" + loginID;
    xhr.send(params);
}

window.onload = function() {
    // 페이지 로드 시 북마크 버튼 이벤트 핸들러 등록
    var loginID = '<%= loginID %>';
    var buttons = document.querySelectorAll("[id^='bookmarkButton_']");
    buttons.forEach(function(button) {
        var prCode = button.id.split("_")[1];
        button.onclick = function() {
            toggleBookmark(prCode, loginID);
        };
    });
};
</script>

</head>
<body>

<a href="/zandiaccount/login.jsp">
    <img src="img/zandi.png" height="100" width="150">
</a>

<%
    if (loginID != null) { // 로그인 성공
%>
    <form action="">
    <table width="300" border="0" id="logaft">
        <tr>
            <td colspan="3" align="center">
                <%= loginID %>님 환영합니다.
            </td>
        </tr>
        <tr>
            <td width="100" align="center">
                <a href="modifyForm.jsp">마이페이지</a>
            </td>
            <td width="100" align="center">
                <a href="deleteForm.jsp">회원탈퇴</a>
            </td>
            <td width="100" align="center">
                <a href="logout.jsp">로그아웃</a>
            </td>
            <td width="100" align="center">
                <a href="/zandiProject/dibsview.jsp">찜목록</a>
            </td>
            <td width="100" align="center">
                <a href="/zandiProject/addProjectForm.jsp">프로젝트 올리기</a>
            </td>
        </tr>
    </table>
    </form>

    <div class="project-list">
        <jsp:include page="/zandiProject/listProjects.jsp" />
    </div>

<%
    } else { // 로그인 실패시
%>

<form action="loginProc.jsp" method="post">
    <table width="300" border="0" id="logbef">
        <tr>
            <td align="left" width="100"> 아이디 </td>
        </tr>
        <tr>
            <td width="200">&nbsp;&nbsp;
                <input type="text" name="id" size="20" id="idSub" placeholder="아이디를 입력하세요"><br><br>
            </td>
        </tr>
        <tr>
            <td align="left" width="100"> 비밀번호 </td>
        </tr>
        <tr>
            <td width="200">&nbsp;&nbsp;
                <input type="password" name="pass" id="passSub" size="20" placeholder="비밀번호를 입력하세요"><br><br><br><br>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="로그인" id="log">&nbsp;&nbsp; <br><br><br>
                <input type="button" value="회원가입" id="acc" onclick="javascript:window.location='regForm.jsp'">
            </td>
        </tr>
    </table>
</form>
<%
    }
%>

</body>
</html>