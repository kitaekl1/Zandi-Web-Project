<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/Nav.css' />">
    <link href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/regscript.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/regbuttonscript.js' />"></script>
    
    <style type="text/css">
        .form-input {
            padding: 10px !important;
            margin: 4px !important;
            width: 400px;
            height: 30px;
            background: #FFFFFF;
            border: 1px solid #E0E0E0;
            border-radius: 8px;
        }
        .button-input {
            padding: 4px !important;
            margin: 4px !important;
            width: 88px;
            height: 30px;
            background: #000000;
            border-radius: 8px;
            color: #FFFFFF;
        }
        #tab1 {
            position: relative;
            padding: 30px !important;
            width: 500px;
            background-color: #82BE6D;
            border-radius: 5px;
            top: 35px;
            left: 440px;
        }
        h5 {
            margin-left: 30px !important;
            margin-top: 0px !important;
            margin-bottom: 3px !important;
            font-size: 12px !important;
            color: #333 !important;
        }
        #ren {
            width: 142px !important;
            height: 36px !important;
            box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.05);
            border-radius: 8px;
            color: #FFFFFF;
            margin: 20px !important;
        }
        h1 {
            font-size: 25px !important;
            font-weight: 800 !important;
        }
    </style>
</head>
<body>
    <%@ include file="/zandiMainPage/Navigator.jsp" %>
    <br><br><br><br><br><br>
    <h1 align="center">회원가입</h1>
    <form action="<c:url value='/zandi/register' />" name="regForm" method="post" id="for1">
        <table border="0" id="tab1">
            <tr>
                <td align="left" colspan="3">아이디</td>
            </tr>
            <tr>
                <td><input type="text" name="mId" class="form-input" placeholder="아이디를 입력하세요" data-is-valid="false" data-checked="false" oninput="resetCheckStatus(this)"></td>
                <td><input type="button" value="중복확인" onclick="idCheck(this.form.mId.value)" class="button-input"></td>
            </tr>
            <tr>
                <td align="left">비밀번호</td>
            </tr>
            <tr>
                <td>
                    <input type="password" name="mPw" class="form-input" placeholder="비밀번호를 입력하세요">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="password" name="repass" class="form-input" placeholder="비밀번호를 한번 더 입력해주세요">
                    <h5>8~15자 이내의 특수기호, 숫자를 포함한 비밀번호를 입력하세요</h5>
                </td>
            </tr>
            <tr>
                <td align="left" colspan="3">닉네임/팀네임</td>
            </tr>
            <tr>
                <td><input type="text" name="mNickname" class="form-input" placeholder="닉네임을 입력하세요" data-is-valid="false" data-checked="false" oninput="resetCheckStatus(this)"></td>
                <td><input type="button" value="중복확인" onclick="nickCheck(this.form.mNickname.value)" class="button-input"></td>
            </tr>
            <tr>
                <td align="left">이름</td>
            </tr>
            <tr>
                <td><input type="text" name="mName" class="form-input" placeholder="이름을 입력해주세요"></td>
            </tr>
            <tr>
                <td align="left">전화번호</td>
            </tr>
            <tr>
                <td><input type="text" name="mPhone" class="form-input" placeholder="전화번호를 입력해주세요"></td>
            </tr>
            <tr>
                <td align="left">이메일</td>
            </tr>
            <tr>
                <td><input type="email" name="mMail" class="form-input" placeholder="이메일을 입력해 주세요"></td>
            </tr>
            <tr>
                <td align="left">우편번호</td>
            </tr>
            <tr>
                <td><input type="text" id="sample6_postcode" class="form-input" placeholder="우편번호" readonly="readonly" name="mPost"></td>
                <td><input type="button" onclick="sample6_execDaumPostcode()" class="button-input" value="검색"></td>
            </tr>
            <tr>
                <td align="left">주소</td>
            </tr>
            <tr>
                <td colspan="2"><input type="text" id="sample6_address" class="form-input" placeholder="주소" readonly="readonly" name="mAddress"></td>
            </tr>
            <tr>
                <td align="left">상세주소</td>
            </tr>
            <tr>
                <td colspan="2"><input type="text" id="sample6_detailAddress" class="form-input" placeholder="상세주소" name="mSaddress"></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="button" value="회원가입" id="ren" style="background-color:#000000" onclick="validateForm()">&nbsp;&nbsp;
                    <input type="reset" value="다시작성" id="ren" style="background-color:#828282">
                </td>
            </tr>
        </table>
    </form>
	
    <script>

        function resetCheckStatus(input) {
            input.dataset.isValid = "false";
            input.dataset.checked = "false";
        }

        function idCheck(mId) {
            return new Promise((resolve, reject) => {
                if (mId === "") {
                    alert("아이디를 입력해 주세요.");
                    document.regForm.mId.focus();
                    resolve(false);
                    return;
                }
                if (/[^a-zA-Z0-9]/.test(mId)) {
                    alert("아이디는 영문과 숫자만 입력 가능합니다.");
                    document.regForm.mId.focus();
                    resolve(false);
                    return;
                }
                if (mId.length < 5) {
                    alert("아이디는 최소 5자 이상이어야합니다.");
					document.regForm.mId.focus();
					resolve(false);
					return;
				}
                
            console.log("AJAX 요청 시작: id = " + mId);

            $.ajax({
                url: "<c:url value='/zandi/idCheck' />",
                type: "GET",
                data: { id: mId },
                dataType: "json",
                success: function(response) {
                    console.log("AJAX 요청 성공: 응답 = " + response);
                    if (response) {
                        alert("사용 가능한 아이디입니다.");
                        document.regForm.mId.dataset.isValid = "true";
                        document.regForm.mId.dataset.checked = "true";
                        resolve(true);
                    } else {
                        alert("이미 사용 중인 아이디입니다.");
                        document.regForm.mId.dataset.isValid = "false";
                        document.regForm.mId.dataset.checked = "true";
                        resolve(false);
                    }
                },
                error: function(xhr, status, error) {
                    console.error("AJAX 요청 실패:", error);
                    reject(false);
                }
            });
        });
    }

    function nickCheck(mNickname) {
        return new Promise((resolve, reject) => {
            if (mNickname === "") {
                alert("닉네임을 입력해 주세요.");
                document.regForm.mNickname.focus();
                resolve(false);
                return;
            }

            console.log("AJAX 요청 시작: nickname = " + mNickname);

            $.ajax({
                url: "<c:url value='/zandi/nickCheck' />",
                type: "GET",
                data: { mNickname: mNickname },
                dataType: "json",
                success: function(response) {
                    console.log("AJAX 요청 성공: 응답 = " + response);
                    if (response) {
                        alert("사용 가능한 닉네임입니다.");
                        document.regForm.mNickname.dataset.isValid = "true";
                        document.regForm.mNickname.dataset.checked = "true";
                        resolve(true);
                    } else {
                        alert("이미 사용 중인 닉네임입니다.");
                        document.regForm.mNickname.dataset.isValid = "false";
                        document.regForm.mNickname.dataset.checked = "true";
                        resolve(false);
                    }
                },
                error: function(xhr, status, error) {
                    console.error("AJAX 요청 실패:", error);
                    reject(false);
                }
            });
        });
    }

    async function validateForm() {
        const idField = document.regForm.mId;
        const nicknameField = document.regForm.mNickname;

        // 공백 검사
        if (!inputCheck()) {
            return false;
        }

        // 아이디 중복 확인 여부 검사
        if (idField.dataset.checked !== "true") {
            alert("아이디 중복 확인을 해주세요.");
            idField.focus();
            return false;
        }

        // 닉네임 중복 확인 여부 검사
        if (nicknameField.dataset.checked !== "true") {
            alert("닉네임 중복 확인을 해주세요.");
            nicknameField.focus();
            return false;
        }

        // 아이디와 닉네임 중복 여부 검사
        const idValid = idField.dataset.isValid === "true";
        const nickValid = nicknameField.dataset.isValid === "true";

        if (!idValid) {
            alert("사용할 수 없는 아이디입니다. 다시 확인해 주세요.");
            idField.focus();
            return false;
        }

        if (!nickValid) {
            alert("사용할 수 없는 닉네임입니다. 다시 확인해 주세요.");
            nicknameField.focus();
            return false;
        }

        // 모든 검사가 통과되면 폼을 제출
        document.regForm.submit();
    }
</script>
</body>
</html>
