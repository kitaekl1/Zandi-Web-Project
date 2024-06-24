function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = ''; // 주소 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}

function updateCheck() {
    var mNickname = document.regForm.mNickname.value.trim();
    var mPw = document.regForm.mPw.value.trim();
    var repass = document.regForm.repass.value.trim();
    var mMail = document.regForm.mMail.value.trim();
    var mPost = document.regForm.mPost.value.trim();
    var mAddress = document.regForm.mAddress.value.trim();
    var mSaddress = document.regForm.mSaddress.value.trim();

    // 비밀번호 검사
    if (mPw === "") {
        alert("비밀번호를 입력해 주세요.");
        document.regForm.mPw.focus();
        return false;
    }

    if (mPw.length < 8 || mPw.length > 15) {
        alert("비밀번호는 8자에서 15자 사이여야 합니다.");
        document.regForm.mPw.focus();
        return false;
    }

    if (!/[a-zA-Z]/.test(mPw) || !/[0-9]/.test(mPw) || !/[~!@#$%^&*()_+]/.test(mPw)) {
        alert("비밀번호는 영문, 숫자, 특수문자(~!@#$%^&*()_+)를 포함해야 합니다.");
        document.regForm.mPw.focus();
        return false;
    }

    if (mPw !== repass) {
        alert("비밀번호가 일치하지 않습니다.");
        document.regForm.repass.focus();
        return false;
    }

    if (mNickname === "") {
        alert("닉네임을 입력하세요.");
        document.regForm.mNickname.focus();
        return false;
    }

    if (mNickname.length < 2) {
        alert("닉네임은 최소 2글자 이상이어야 합니다.");
        document.regForm.mNickname.focus();
        return false;
    }

    if (mMail === "") {
        alert("이메일을 입력해 주세요.");
        document.regForm.mMail.focus();
        return false;
    }

    // 이메일 형식 검사
    var str = document.regForm.mMail.value;
    var atPos = str.indexOf('@');
    var atLastPos = str.lastIndexOf('@');
    var dotPos = str.indexOf('.');
    var spacePos = str.indexOf(' ');
    var commaPos = str.indexOf(',');
    var eMailSize = str.length;

    if (!(atPos > 1 && atPos === atLastPos && dotPos > 3 && spacePos === -1 && commaPos === -1 && atPos + 1 < dotPos && dotPos + 1 < eMailSize)) {
        alert("E mail 주소 형식이 잘못되었습니다. \n\r 다시 입력해 주세요.");
        document.regForm.mMail.focus();
        return false;
    }

    if (mPost === "") {
        alert("우편 번호를 입력해 주세요.");
        document.regForm.mPost.focus();
        return false;
    }

    if (mAddress === "") {
        alert("주소를 입력해 주세요.");
        document.regForm.mAddress.focus();
        return false;
    }

    if (mSaddress === "") {
        alert("상세주소를 입력해 주세요.");
        document.regForm.mSaddress.focus();
        return false;
    }

    document.regForm.submit();
    return true; // 모든 입력이 유효한 경우 true 반환
}