function getContextPath() {
   var hostIndex = location.href.indexOf( location.host ) + location.host.length;
   return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
};


// 아이디 체크(정규식 + 멘트)
var memId = document.querySelector(".memberId");
memId.addEventListener("change", function(e){
	var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
	if($(".memberId").val() == ""){ // 빈칸일 때
		if($('span.idCheck').hasClass('alert-green')) { 
    		$('.idComentColor span').removeClass('alert-red');
    		$('.idComentColor span').removeClass('alert-green');
    	} else if($('span.idCheck').hasClass('alert-red')) {
    		$('.idComentColor span').removeClass('alert-red');
    		$('.idComentColor span').removeClass('alert-green');
    	}
		document.querySelector(".idCheck").innerHTML = "";
	} else { // 빈칸이 아닐때
	    if( !idReg.test( $(".memberId").val() )) { // 정규식 조건에 맞지 않을때
	    	if($('span.idCheck').hasClass('alert-green')) { // alert-green이 있으면
	    		$('.idComentColor span').removeClass('alert-green');
	    	}
	    	$('.idComentColor span').addClass('alert-red');
	    	document.querySelector(".idCheck").innerHTML = "아이디는 영문자(소문자)로 시작하는 6~20자 영문자 또는 숫자이어야 합니다.";
	        return;
	    }  else {
	    	if($('span.idCheck').hasClass('alert-green')) { 
	    		$('.idComentColor span').removeClass('alert-green');
	    		$('.idComentColor span').addClass('alert-red');
	    		document.querySelector(".idCheck").innerHTML = "중복체크 전입니다.";
	    	} else {
	    		$('.idComentColor span').addClass('alert-red');
	    		document.querySelector(".idCheck").innerHTML = "중복체크 전입니다.";
	    	}
	    }
	}
});

var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
function idCheck(){
	var id = $("#id").val();
	$.ajax({
		url : "./idCheck.do",
		type : "get",
		data : {"id":id},
		success:function(data){
			if(idReg.test( $(".memberId").val() )){
				if(data === "true"){
					$('.idComentColor span').removeClass('alert-green');
					$('.idComentColor span').addClass('alert-red');
					document.querySelector(".idCheck").innerHTML = "사용할 수 없는 아이디입니다.";

				} else {
					$('.idComentColor span').removeClass('alert-red');
					$('.idComentColor span').addClass('alert-green');
					document.querySelector(".idCheck").innerHTML = "사용가능한 아이디입니다.";
				}
			}
		},
		error:function(request,status,error){
			
		}
		
	});
};


 // 패스워드 체크(정규식 + 멘트)
    var memPwd = document.querySelector(".memberPwd");
    memPwd.addEventListener("change", function(e){
    	var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
    	if($(".memberPwd").val() == ""){
    		if($('span.pwdCheck').hasClass('alert-red')) {
        		$('.pwdComentColor span').removeClass('alert-red');
        	}
    		document.querySelector(".pwdCheck").innerHTML = "";
    	} else {
        if( !idReg.test( $(".memberPwd").val() )) {
        	if($('span.pwdCheck').hasClass('alert-green')) {
        		$('.pwdComentColor span').removeClass('alert-green');
        	}
        	$('.pwdComentColor span').addClass('alert-red');
        
        	document.querySelector(".pwdCheck").innerHTML = "비밀번호는 영문자로 시작하는 6~20자 영문자 또는 숫자이어야 합니다.";
            return;
        } else {
        	if($('span.pwdCheck').hasClass('alert-red')) {
        		$('.pwdComentColor span').removeClass('alert-red');
        	}
        	$('.pwdComentColor span').addClass('alert-green');
        	document.querySelector(".pwdCheck").innerHTML = "사용가능한 비밀번호 입니다";
        }
    	}
    });
    
// 패스워드 재확인 + 멘트
var pw = document.querySelector(".memberPwdReCheck");

pw.addEventListener("change", function(e){
	var pw1 = document.querySelector(".memberPwd").value;
	var pw2 = document.querySelector(".memberPwdReCheck").value;	
	if(pw1 == "" || pw2 == ""){
		if($('span.idCheck').hasClass('alert-green')) {
    		$('.idComentColor span').removeClass('alert-green');
    	}
		document.querySelector(".pwdEqualCheck").innerHTML = "";
	} else {
	if(pw1 == pw2){
		if($('span.pwdEqualCheck').hasClass('alert-red')) {
    		$('.pwdReCheckComentColor span').removeClass('alert-red');
    	}
    	$('.pwdReCheckComentColor span').addClass('alert-green');
		document.querySelector(".pwdEqualCheck").innerHTML = "일치합니다.";
		
	} else {
		if($('span.pwdEqualCheck').hasClass('alert-green')) {
    		$('.pwdReCheckComentColor span').removeClass('alert-green');
    	}
    	$('.pwdReCheckComentColor span').addClass('alert-red');
		document.querySelector(".pwdEqualCheck").innerHTML = "비밀번호를 다시 확인해 주세요";
	}
	}
});



