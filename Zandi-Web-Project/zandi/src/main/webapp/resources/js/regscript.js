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





function inputCheck() {
    var memId = document.regForm.memId.value.trim();
    var memNickname = document.regForm.memNickname.value.trim();
    var memPw = document.regForm.memPw.value.trim();
    var repass = document.regForm.repass.value.trim();
    var memName = document.regForm.memName.value.trim();
    var memPhone = document.regForm.memPhone.value.trim();
    var memMail = document.regForm.memMail.value.trim();
    var memPost = document.regForm.memPost.value.trim();
    var memAddress = document.regForm.memAddress.value.trim();
    var memSaddress = document.regForm.memSaddress.value.trim();

    // 아이디 공백 검사
    if (memId === "") {
        alert("아이디를 입력해 주세요.");
        document.regForm.memId.focus();
        return false;
    }

    if (/[^a-zA-Z0-9]/.test(memId)) {
        alert("아이디는 영문과 숫자만 입력 가능합니다.");
        document.regForm.memId.focus();
        return false;
    }

    if (memId.length < 5) {
        alert("아이디는 최소 5자 이상이어야 합니다.");
        document.regForm.memId.focus();
        return false;
    }

    // 비밀번호 검사
    if (memPw === "") {
        alert("비밀번호를 입력해 주세요.");
        document.regForm.memPw.focus();
        return false;
    }

    if (memPw.length < 8 || memPw.length > 15) {
        alert("비밀번호는 8자에서 15자 사이여야 합니다.");
        document.regForm.memPw.focus();
        return false;
    }

    if (!/[a-zA-Z]/.test(memPw) || !/[0-9]/.test(memPw) || !/[~!@#$%^&*()_+]/.test(memPw)) {
        alert("비밀번호는 영문, 숫자, 특수문자(~!@#$%^&*()_+)를 포함해야 합니다.");
        document.regForm.memPw.focus();
        return false;
    }

    if (memPw !== repass) {
        alert("비밀번호가 일치하지 않습니다.");
        document.regForm.repass.focus();
        return false;
    }

    // 닉네임 검사
    if (memNickname === "") {
        alert("닉네임을 입력하세요.");
        document.regForm.memNickname.focus();
        return false;
    }

    if (memNickname.length < 2) {
        alert("닉네임은 최소 2글자 이상이어야 합니다.");
        document.regForm.memNickname.focus();
        return false;
    }

    // 이름 검사
    if (memName === "") {
        alert("이름을 입력하세요.");
        document.regForm.memName.focus();
        return false;
    }

    // 전화번호 유효성 검사
    if (memPhone === "") {
        alert("전화번호를 입력해 주세요.");
        document.regForm.memPhone.focus();
        return false;
    }

    // 하이픈이 있는지 검사
    if (memPhone.indexOf('-') !== -1) {
        alert("전화번호에 하이픈을 포함할 수 없습니다.");
        document.regForm.memPhone.focus();
        return false;
    }

    // 한국 전화번호 형식 검사
    var phonePattern = /^(01[016789]{1}|02|0[3-6][1-5]|070|080)\d{3,4}\d{4}$/;
    if (!phonePattern.test(memPhone)) {
        alert("유효한 한국 전화번호를 입력해 주세요. (예: 010xxxxxxxx, 02xxxxxxxx, 031xxxxxxxx)");
        document.regForm.memPhone.focus();
        return false;
    }

    // 전화번호 길이 검사 (예: 최소 10자리, 최대 11자리)
    if (memPhone.length < 10 || memPhone.length > 11) {
        alert("전화번호는 10자리에서 11자리여야 합니다.");
        document.regForm.memPhone.focus();
        return false;
    }

    // 이메일 검사
    if (memMail === "") {
        alert("이메일을 입력해 주세요.");
        document.regForm.memMail.focus();
        return false;
    }

    // 이메일 형식 검사
    var str = memMail;
    var atPos = str.indexOf('@');
    var atLastPos = str.lastIndexOf('@');
    var dotPos = str.indexOf('.');
    var spacePos = str.indexOf(' ');
    var commaPos = str.indexOf(',');
    var eMailSize = str.length;

    if (atPos > 1 && atPos == atLastPos && dotPos > 3 && spacePos == -1 && commaPos == -1 && atPos + 1 < dotPos && atPos + 1 < eMailSize);
    else {
        alert("E mail 주소 형식이 잘못되었습니다. \n\r 다시 입력해 주세요.");
        document.regForm.memMail.focus();
        return false;
    }

    // 우편번호 검사
    if (memPost === "") {
        alert("우편 번호를 입력해 주세요.");
        document.regForm.memPost.focus();
        return false;
    }

    // 주소 검사
    if (memAddress === "") {
        alert("주소를 입력해 주세요.");
        document.regForm.memAddress.focus();
        return false;
    }

    // 상세주소 검사
    if (memSaddress === "") {
        alert("상세주소를 입력해 주세요.");
        document.regForm.memSaddress.focus();
        return false;
    }

    return true; // 모든 입력이 유효한 경우 true 반환
}