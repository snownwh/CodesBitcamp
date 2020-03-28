<%@include file ="../include/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>    
<div id="joinPage">
	<div class="insertFrm">	
	   <form method="post" action="./join.do">
	      <div class="frm_cont border-btm">
	         <div class="frm_line clfix">
	            <div class="tit">아이디</div>
	            <div class="cont idComentColor">
	               <input class="ttext memberId" type="text" maxlength="20" name="id" id="id" placeholder="6~20자 영문자, 숫자">
	               <button type="button" onclick="idCheck()" class="frm_adr_btn">중복체크</button>
	               <span class="idCheck"></span>
	            </div>            
	         </div>
	         
	         <div class="frm_line clfix">
	            <div class="tit">비밀번호</div>
	            <div class="cont pwdComentColor">
	               <input class="ttext memberPwd" type="password" maxlength="20" name="pwd" id="pwd"  placeholder="6~20자 영문자, 숫자">
	               <span class="pwdCheck"></span>
	            </div>            
	         </div>
	         
	         <div class="frm_line clfix">
	            <div class="tit">비밀번호 재확인</div>
	            <div class="cont pwdReCheckComentColor">
	               <input class="ttext memberPwdReCheck" type="password" maxlength="20" >
	               <span class="pwdEqualCheck"></span>
	            </div>            
	         </div>
	         
	         <div class="frm_line clfix">
	            <div class="tit">이름</div>
	            <div class="cont">
	               <input class="ttext" type="text" name="name" id="name">
	            </div>            
	         </div>
	         
	         <div class="frm_line clfix">
	            <div class="tit">이메일 주소</div>
	            <div class="cont i-mg-none">
	               <input class="memberEmail1" type="text" name="email" id="email">
	            </div>            
	         </div>
	      </div><!-- frm_cont -->      
	      
	      <!-- <input type="submit" value="가입하기"> -->
	      <button type="submit" class="reserv_btn sBtn">가입하기</button>
	      
	   </form><!-- insertFrm -->
	</div><!-- formWrap -->
</div><!-- joinPage -->

<script src="<%=request.getContextPath() %>/js/join.js"></script>




<%@include file ="../include/footer.jsp" %>      