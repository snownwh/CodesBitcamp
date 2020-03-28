<%@include file ="../include/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
div.loginBox{
	width:500px; 
	height:340px; 
	border:1px solid #ddd;
	margin: 0 auto;
	padding: 30px;
	
}
.loginBox ul{}
.loginBox ul li input{border:1px solid black; background:black; color:#fff; font-size:18px; width:400px; height:50px;  padding:0 10px; margin-left:19px; margin-top:15px; display:block}
.loginBox ul li button{border:1px solid black; background:#fff; color:black; font-size:18px; width:400px; height:50px;  padding:0 10px; margin-left:19px; margin-top:15px; display:block}
.loginBox ul li input.textBox{border:1px solid #ddd; background:#fff; color:#000; font-size:14px; width:400px; height:50px;  padding:0 10px; margin-left:19px; margin-top:15px; display:block}
.loginBox ul li a.findId{font-size:14px;  margin-left:250px; display:inline-block; margin-top:10px; }


</style>   
<%
request.setCharacterEncoding("UTF-8");
%>



<div class="loginBox">
	<form action="" method="post">
		<ul>
			<li>
				<!-- 아이디 입력 -->
				<input class="textBox" type="text" id="id" name="id" placeholder="아이디를 입력해주세요" value="snow">
			</li>
			<li>
				<!-- 비밀번호 입력 -->
				<input class="textBox" type="password" id="pwd" name="pwd" placeholder="비밀번호를 입력해주세요" value="qwer">
			</li>
			<li>
				<!-- 로그인 버튼 -->
				<input type="button" class="sBtn" style="cursor:pointer" onclick="memLogin()" value="로그인">
			</li>
			<li>
				<!-- 가입하기 버튼 -->
				<button class="joinBtn" type="button" onclick="location.href='joinPage.do'">가입하기</button>
			</li>	
		</ul>
	</form>
</div>

<script type="text/javascript">
function memLogin(){
	$.ajax({
		url:"./login.do",
		type:"post",
		data:{"id":$("#id").val(),
			 "pwd":$("#pwd").val()
		},
		success:function( data ){
			
			if(data === "success"){
				alert("로그인 성공");
				location.href="./movielist.do";
			} else {
				alert("로그인 실패");
				$("#id").val("");
				$("#pwd").val("");
			}
		},
		error:function(){
			alert("error");	
		}
	});
} 
</script>


<%@include file ="../include/footer.jsp" %>