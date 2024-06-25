<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후원하기</title>
<script>
	function checkOnlyOne(element) {
	  	const checkboxes = document.getElementsByName("payment");
	  	checkboxes.forEach((cb) => {
	    	cb.checked = false;
	  	})
	  	element.checked = true;
	}
	
	function updateAmount() {
        var amountInput = document.querySelector('input[type="number"]');
        var amountDisplay = document.getElementById('amount');
        amountDisplay.textContent = amountInput.value || 0;
    }
</script>
</head>
<body>
	<h3>프로젝트 후원하기</h3>
	<div id="project">
		프로젝트 정보
	</div>
	<h3>후원금</h3>
	<div id="money">
		<h5><input type="number" placeholder="0" onchange="updateAmount();">원</h5>
	</div>
	<h3>후원자 정보</h3>
	<div id="info">
		<h5>닉네임</h5>
		<h5>연락처</h5>
		<font size="2px" color="gray">위 연락처로 후원 프로젝트 관련 소식이 전달됩니다.</font>
	</div>
	<h3>결제 수단</h3>
	<div id="method">
		<input type="checkbox" name="payment" value="card" checked="checked" onclick='checkOnlyOne(this)'>카드결제
		<input type="checkbox" name="payment" value="account" onclick='checkOnlyOne(this)'>무통장입금
	</div>
	<div id="final">
		<h5>최종 후원 금액</h5>
		<h3><b id="amount">0</b>원</h3>
		<input type="button" value="후원하기">
	</div>
</body>
</html>