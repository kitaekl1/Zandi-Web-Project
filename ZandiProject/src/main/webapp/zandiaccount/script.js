function idCheck(mId){
	if(mId == ""){
        alert("아이디를 입력해 주세요.");
        document.regForm.mId.focus();
        return;
    }
    // 아이디가 한글인지 확인
   	else if (/[^a-zA-Z0-9]/.test(mId)) {
        alert("아이디는 영문과 숫자만 입력 가능합니다.");
        document.regForm.mId.focus();
        return;
    }
    else if (mId.length < 5) {
        alert("아이디는 최소 5자 이상이어야 합니다.");
        document.regForm.mId.focus();
        return;
    }else{
    url="idCheck.jsp?mId="+mId;
    window.open(url, "post","width=300, height=150");
	}
}

function nickCheck(mNickname){
	if(mNickname == ""){
		alert("닉네임을 입력해 주세요.");
		document.regForm.mNickname.focus();
	}else{
		url="nickCheck.jsp?mNickname="+mNickname;
		window.open(url, "post","width=300, height=150");
	}
}


function zipCheck(){
	
	url="zipCheck.jsp?check=y";
	window.open(url,"post","toolbar=no, width=500, height=300, directories=no, status=yes, scrollbars=yes, menubar=no");
	}

	function dongCheck(){
	if(document.zipForm.dong.value==""){
		alert("동 이름을 입력해 주세요.");
		document.zipForm.dong.focus();
		return;
	}
	document.zipForm.submit();
	
}


	function sendAddress(zipcode,sido,gugun,dong,ri,bunji){
		var address = sido+" "+gugun+" "+dong+" "+ri+" "+bunji;
		opener.document.regForm.mPost.value=zipcode;
		opener.document.regForm.mAddress.value=address;
		self.close();
}
	
	
	function inputCheck(){
		if(document.regForm.mId.value==""){
			alert("아이디를 입력하세요.");
			document.regForm.mId.focus();
			return;
		}

   		 if (/[^a-zA-Z0-9]/.test(document.regForm.mId.value)) {
      	  	alert("아이디는 영문과 숫자만 입력 가능합니다.");
        	document.regForm.mId.focus();
        	return;
   		}
    	if (document.regForm.mId.value.length < 5) {
        	alert("아이디는 최소 5자 이상이어야 합니다.");
      		document.regForm.mId.focus();
       		return;
		}
		if(document.regForm.mPw.value==""){
		alert("비밀번호를 입력하세요.");
		document.regForm.mPw.focus();
		return;
		}
		
		if(document.regForm.repass.value==""){
		alert("비밀번호를 한번 더 입력하세요.");
		document.regForm.repass.focus();
		return;
		}
		
		if(document.regForm.mPw.value != document.regForm.repass.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.regForm.repass.focus();
		return;
		}
		
		 var password = document.regForm.mPw.value;
		 var pattern = /^(?=.*[!@#$%^&*()])(?=.*[a-zA-Z])(?=.*[0-9]).{8,15}$/;
		//^: 입력 문자열의 시작 , (?=.*[!@#$%^&*()]): 특수 문자 중 하나를 최소한 한 번 포함해야 함
		//(?=.*[a-zA-Z]):알파벳 중 하나를 최소한 한 번 포함해야 
		//(?=.*[0-9]):숫자 중 하나를 최소한 한 번 포함해야 함 , $: 입력 문자열의 끝
    
		if(!pattern.test(password)){//test()는 문자열이 정규식과 일치하는지 여부를 확인하는 반면에, equals()는 두 문자열이 완전히 동일한지 비교합니다.
       		 alert("비밀번호는 특수문자, 영어, 숫자가 포함된 8자 이상 15자 이하이어야 합니다.");
       		 document.regForm.mPw.focus();
        	return;
   		 }
		
		
		if(document.regForm.mNickname.value==""){
		alert("닉네임을 입력하세요.");
		document.regForm.mNickname.focus();
		return;
		}
		
		if(document.regForm.mName.value==""){
		alert("이름을 입력하세요.");
		document.regForm.mName.focus();
		return;
		}
		
		if(document.regForm.mPhone.value ==""){
		alert("전화번호를 입력해 주세요.");
		document.regForm.mPhone.focus();
		return;
		}
		
		
		if(document.regForm.mMail.value ==""){
		alert("이메일을 입력해 주세요.");
		document.regForm.mMail.focus();
		return;
		}
		
		//이메일 형식검사
		var str=document.regForm.mMail.value;
		var atPos = str.indexOf('@');
		var atLastPos = str.lastIndexOf('@');
		var dotPos = str.indexOf('.');
		var spacePos = str.indexOf(' ');
		var commaPos = str.indexOf(',');
		var eMailSize = str.length;
		
		if(atPos > 1 && atPos == atLastPos && dotPos >3 && spacePos == -1 && commaPos == -1 && atPos+1 < dotPos && atPos+1 <eMailSize);
		else{
			alert("E mail 주소 형식이 잘못되었습니다. \n\r 다시 입력해 주세요.");
			document.regForm.mMail.focus();
			return;
			
		}	
	
	
		if(document.regForm.mPost.value ==""){
		alert("우편 번호를 입력해 주세요.");
		document.regForm.mPost.focus();
		return;
		}
		
		if(document.regForm.mAddress.value ==""){
		alert("주소를 입력해 주세요.");
		document.regForm.mAddress.focus();
		return;
		}
		
		if(document.regForm.mSaddress.value ==""){
		alert("상세주소를 입력해 주세요.");
		document.regForm.mSaddress.focus();
		return;
		}
		
		document.regForm.submit();
	}


function updateCheck(){
	
	if(document.regForm.mPw.value==""){
		alert("비밀번호를 입력하세요.");
		document.regForm.mPw.focus();
		return;
		}
		
		if(document.regForm.repass.value==""){
		alert("한번 더 비밀번호를 입력하세요.");
		document.regForm.repass.focus();
		return;
		}
		
		if(document.regForm.mPw.value != document.regForm.repass.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.regForm.repass.focus();
		return;
		}
		
		
		if(document.regForm.mPhone.value ==""){
		alert("전화번호를 입력해 주세요.");
		document.regForm.mPhone.focus();
		return;
		}
		
		
		if(document.regForm.mMail.value ==""){
		alert("이메일을 입력해 주세요.");
		document.regForm.mMail.focus();
		return;
		}
		
		//이메일 형식검사
		var str=document.regForm.mMail.value;
		var atPos = str.indexOf('@');
		var atLastPos = str.lastIndexOf('@');
		var dotPos = str.indexOf('.');
		var spacePos = str.indexOf(' ');
		var commaPos = str.indexOf(',');
		var eMailSize = str.length;
		
		if(atPos > 1 && atPos == atLastPos && dotPos >3 && spacePos == -1 && commaPos == -1 && atPos+1 < dotPos && atPos+1 <eMailSize);
		else{
			alert("E mail 주소 형식이 잘못되었습니다. \n\r 다시 입력해 주세요.");
			document.regForm.mMail.focus();
			return;
			
		}	
	
	
		if(document.regForm.mPost.value ==""){
		alert("우편 번호를 입력해 주세요.");
		document.regForm.mPost.focus();
		return;
		}
		
		if(document.regForm.mAddress.value ==""){
		alert("주소를 입력해 주세요.");
		document.regForm.mAddress.focus();
		return;
		}
		
		if(document.regForm.mSaddress.value ==""){
		alert("상세주소를 입력해 주세요.");
		document.regForm.mSaddress.focus();
		return;
		}
		document.regForm.submit();
}

function begin(){
	document.myForm.mPw.focus();
	
	
}

function checkIt(){
	if(!document.myForm.mPw.value){
		alert("비밀번호가 입력되지 않았습니다.");
		document.myForm.mPw.focus();
		return;
	}
}


