function writeSave(){
	if(document.writeForm.aTitle.value==""){
		alert("제목을 입력해주세요.");
		document.writeForm.aTitle.focus();
		return false;
	}
	
	if(document.writeForm.aAnnounce.value==""){
		alert("내용을 입력해주세요.");
		document.writeForm.aAnnounce.focus();
		return false;
	}
	
	if(document.writeForm.pass.value==""){
		alert("비밀번호를 입력해주세요.");
		document.writeForm.pass.focus();
		return false;
	}
}