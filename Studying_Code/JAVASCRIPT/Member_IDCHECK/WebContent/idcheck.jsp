<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>

<h1>회원가입</h1>
<div>
	<div>
id : <input type="text" id="_id" name="uid">
<input type="button" id="btn" value="중복체크">
	</div>
	<div>
	<p id="showMsg"></p>
	</div>
	<div>
패스워드 : <input type="password" id="_password">
	</div>
	<div>
패스워드 확인 : <input type="password" id="_passwordCkd">
<input type="button" id="pwdBtn" value="재확인">
	</div>


</div>



<script>
$(function(){
	$("#btn").click(function(){
		var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
	    if( !idReg.test( $("input[name=uid]").val() ) ) {
	    	document.querySelector("#showMsg").innerHTML = "아이디는 영문자로 시작하는 6~20자 영문자 또는 숫자이어야 합니다.";
	        return;
	    } else {
	    	document.querySelector("#showMsg").innerHTML = "사용가능";
	    }
	});
	
	$("#pwdBtn").click(function(){
		var pw1 = document.querySelector("#_password").value;
		var pw2 = document.querySelector("#_passwordCkd").value;
		if(pw1 == pw2){
			alert("사용가능");
			
		} else {
			alert("재확인 요망");
			document.querySelector("#_password").value = "";
			document.querySelector("#_passwordCkd").value = "";
		}
	});
});
	


</script>



</body>
</html>