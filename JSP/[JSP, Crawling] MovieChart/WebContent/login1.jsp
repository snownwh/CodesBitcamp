<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="http://lab.alexcican.com/set_cookies/cookie.js"
	type="text/javascript"></script>

<style type="text/css">
.center {
	margin: auto;
	width: 60%;
	border: 3px solid #8ac007;
	padding: 10px;
}
</style>

</head>
<body>

	<h2>Login Page</h2>

	<div align="center" class="center">
		<form action="loginAf.jsp" method="post">
			<table border="1">
				<tr>
					<td>ID</td>
					<td><input type="text" id="id" name="id" size="20"> <input
						type="checkbox" id="chk_save_id">save id</td>
				</tr>
				<tr>
					<td>PASSWORD</td>
					<td><input type="password" name="pwd" size="20"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Sign In">
						<button type="button" onclick="location.href='regi.jsp'">Sign
							Up</button></td>
				</tr>
			</table>
		</form>
	</div>

	<script type="text/javascript">
/*
	session : Java -> Server(login한 정보) Object
	cookie : Java Script 
*/
 

$.cookie("user_id", "abc123"); // 쿠키 생성

/* $.removeCookie("user_id", {path:'./'});	// 쿠키 삭제 */ */

var user_id = $.cookie("user_id");
if(user_id != null){
	alert("쿠키 있음");
	$("#id").val(user_id);
	$("#chk_save_id").attr("checked", "checked");
}

$("#chk_save_id").click(function(){
	if($("#chk_save_id").is(":checked")){
		/* alert("checked"); */
		if($("#id").val().trim() == ""){
			alert("ID를 입력해 주십시오");
			$("chk_save_id").porp("checked", false);
		} else{
			/* alert("unchecked"); */
			$.cookie("user_id", $("#id").val().trim(), {expires:7, path:'./'});
		}
	} else{
		$.removeCookie("user_id", {path:'./'});
		alert("쿠키 없음");
	}
	
	
})


</script>

</body>
</html>