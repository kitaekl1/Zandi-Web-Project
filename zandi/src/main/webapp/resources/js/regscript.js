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
    var mId = document.regForm.mId.value.trim();
    var mNickname = document.regForm.mNickname.value.trim();
    var mPw = document.regForm.mPw.value.trim();
    var repass = document.regForm.repass.value.trim();
    var mName = document.regForm.mName.value.trim();
    var mPhone = document.regForm.mPhone.value.trim();
    var mMail = document.regForm.mMail.value.trim();
    var mPost = document.regForm.mPost.value.trim();
    var mAddress = document.regForm.mAddress.value.trim();
    var mSaddress = document.regForm.mSaddress.value.trim();

    // 아이디 공백 검사
    if (mId === "") {
        alert("아이디를 입력해 주세요.");
        document.regForm.mId.focus();
        return false;
    }

    if (/[^a-zA-Z0-9]/.test(mId)) {
        alert("아이디는 영문과 숫자만 입력 가능합니다.");
        document.regForm.mId.focus();
        return false;
    }

    if (mId.length < 5) {
        alert("아이디는 최소 5자 이상이어야 합니다.");
        document.regForm.mId.focus();
        return false;
    }

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

    if (mName === "") {
        alert("이름을 입력하세요.");
        document.regForm.mName.focus();
        return false;
    }

// 전화번호 유효성 검사 부분
if (mPhone === "") {
    alert("전화번호를 입력해 주세요.");
    document.regForm.mPhone.focus();
    return false;
}

// 하이픈이 있는지 검사
if (mPhone.indexOf('-') !== -1) {
    alert("전화번호에 하이픈을 포함할 수 없습니다.");
    document.regForm.mPhone.focus();
    return false;
}

// 한국 전화번호 형식 검사
var phonePattern = /^(01[016789]{1}|02|0[3-6][1-5]|070|080)\d{3,4}\d{4}$/;
if (!phonePattern.test(mPhone)) {
    alert("유효한 한국 전화번호를 입력해 주세요. (예: 010xxxxxxxx, 02xxxxxxxx, 031xxxxxxxx)");
    document.regForm.mPhone.focus();
    return false;
}

// 전화번호 길이 검사 (예: 최소 10자리, 최대 11자리)
if (mPhone.length < 10 || mPhone.length > 11) {
    alert("전화번호는 10자리에서 11자리여야 합니다.");
    document.regForm.mPhone.focus();
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

    if (atPos > 1 && atPos == atLastPos && dotPos > 3 && spacePos == -1 && commaPos == -1 && atPos + 1 < dotPos && atPos + 1 < eMailSize);
    else {
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

    return true; // 모든 입력이 유효한 경우 true 반환
}
