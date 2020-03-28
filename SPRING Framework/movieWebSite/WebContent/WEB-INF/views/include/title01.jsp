<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String ifurl = request.getServletPath();  //프로젝트의 경로값만 가져옴
 System.out.println(ifurl);
%>


<% 
if (ifurl.contains("login.jsp")){
%>
<h2>LOGIN</h2>
<%
}	
%>

<% 
if (ifurl.contains("join.jsp")){
%>
<h2>JOIN</h2>
<%
}	
%>

<% 
if (ifurl.contains("findid.jsp")){
%>
<h2>아이디 찾기</h2>
<%
}	
%>

<% 
if (ifurl.contains("findpassword.jsp")){
%>
<h2>비밀번호 찾기</h2>
<%
}	
%>

<% 
if (ifurl.contains("mypage.jsp")){
%>
<h2>회원정보</h2>
<%
}   
%>

<% 
if (ifurl.contains("memupdate.jsp")){
%>
<h2>회원정보 수정</h2>
<%
}   
%>

<% 
if (ifurl.contains("memdelete.jsp")){
%>
<h2>회원 탈퇴</h2>
<%
}   
%>

<!-- 영화리스트 -->
<% 
if (ifurl.contains("movielist")){
%>
<h2>영화리스트</h2>
<%
}	
%>

<!-- 상세정보 -->
<% 
if (ifurl.contains("movie_detail")){
%>
<h2>상세정보</h2>
<%
}	
%>	

<!-- 예매하기 -->
<% 
if (ifurl.contains("movieSelectOne")){
%>
<h2>예매하기</h2>
<%
}	
%>

<!-- 예매완료 -->
<% 
if (ifurl.contains("movieReserveAf")){
%>
<h2>예매완료</h2>
<%
}	
%>

<!-- 나의예매 -->
<% 
if (ifurl.contains("movieReserv.jsp")){
%>
<h2>나의 예매</h2>
<%
}	
%>
