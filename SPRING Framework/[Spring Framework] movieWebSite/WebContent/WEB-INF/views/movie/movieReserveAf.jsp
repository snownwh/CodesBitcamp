<%@include file ="../include/header.jsp" %>
<%
MemberDto mem = (MemberDto)session.getAttribute("loginuser");

%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/6cf6eed947.js" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="/movieWebSite/css/movielist.css">
<link rel="stylesheet" type="text/css" href="/movieWebSite/css/moviedetail.css">

<div id="movieSelectOne">
<div class="insertFrm">	
<div class="cont">
	
		<button class="reserv_btn sBtn" onclick="location.href='mainPage.do'">메인으로 돌아가기</button>
		<button class="reserv_btn sBtn" onclick="location.href='movieMyReserve.do?id=<%=mem.getId()%>'">나의 예매</button>
</div>
</div>
</div>




<%@include file ="../include/footer.jsp" %>		