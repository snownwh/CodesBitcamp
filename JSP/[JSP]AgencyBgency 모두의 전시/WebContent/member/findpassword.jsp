<%@page import="NWH.member.dto.MemberDto"%>
<%@include file ="../include/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%
request.setCharacterEncoding("UTF-8");
%>
<style>
.findPasswordBox {
	width:500px; 
	height:440px; 
	border:1px solid #ddd;
	margin: 0 auto;
	padding: 30px;
}
.findPasswordBox ul li #id{margin-left:20px; margin-top:0px;}
.findPasswordBox ul li p{margin-left:20px; margin-top:20px;}
.findPasswordBox ul li input{border:1px solid #ddd; background:#fff; color:#000; font-size:14px; width:395px; height:50px;  padding:0 10px; margin-left:20px; margin-top:3px; display:block}
.findPasswordBox ul li select{border:1px solid #5f0080; background:#fff; color:#000; font-size:14px; width:395px; height:50px;  padding:0 10px; margin-left:20px; margin-top:3px; display:block}
.findPasswordBox ul li .answer{border:1px solid #ddd; background:#fff; color:#000; font-size:14px; width:200px; height:25px;  padding:0 10px; margin-left:20px; margin-top:7px; display:block}
.findPasswordBox ul li .sBtn{border:1px solid #5f0080; color:#5f0080; width:300px; height:40px; margin-left: 68px; margin-top: 25px;}
</style>


<div class="findPasswordBox">
	<form action="<%=request.getContextPath() %>/memberfindpassword" method="post">
		<ul>
			<li>	
				<!-- 아이디 입력 -->
				<p id="id">아이디</p>
				<input type="text" id="id" name="memberId">
			</li>
			<li>
				<!-- 이름 입력 -->
				<p>이름</p>
				<input type="text" name="memberName">
			</li>
		
			<!-- 힌트 질문/답 입력 -->
		
        	<li>
        		<p>비밀번호 찾기 질문</p>
        	</li>
        	<li>
 	      		<select name="memberQuestion">
    	    		<option value="1" selected>기억에 남는 추억의 장소는?</option>
		            <option value="2">나의 보물 1호는?</option>
		            <option value="3">나의 출신 초등학교는?</option>
		            <option value="4">가장 좋아하는 색깔은?</option>
		            <option value="5">아버지 성함은?</option>
           		</select>
           	</li>
           	<li>
           		<input type="text" class="answer" name="memberAnswer" placeholder="답을 입력해주세요">
            </li>        
          	<li>
				<!-- 비밀번호 찾기 버튼 -->
				<input type="submit" style="cursor:pointer" class="sBtn" value="비밀번호 찾기">
			</li>
		</ul>
	</form>
</div>


<%@include file ="../include/footer.jsp" %>