
<%@page import="movie.com.a.model.MovieDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 

<%@include file ="../include/header.jsp" %>
<!-- font awesome -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.1/css/all.css" integrity="sha384-v8BU367qNbs/aIZIxuivaU55N5GPF89WBerHoGA4QTcbUjYiLQtKdrfXnqAcXyTv" crossorigin="anonymous">

<%
//로그인 세션
MemberDto loginuser = (MemberDto)session.getAttribute("loginuser");
boolean logincheck =false;

//로그인 여부 확인하기
if( loginuser != null){
	logincheck= true;
} 
%>

<%
	// 영화 디테일 dto
	MovieDto movie = (MovieDto)request.getAttribute("moviedetail");
%>
<link rel="stylesheet" type="text/css" href="/movieWebSite/css/moviedetail.css">
<div class="ex-datail-top clfix">
	<div class="img">
		<img alt="NO IMG" src="<%=movie.getMovieImg() %>"/> 
	</div>
	<div class="cont">
		<h4><%=movie.getTitle() %></h4>
		<p><span>감독</span><%=movie.getDirector() %> </p>
		<p><span>배우</span><%=movie.getActor() %></p>
		<p><span><i class="fas fa-heart"></i></span><%=movie.getMovieLike() %></p>
		<a href="./movieSelect.do?cgvSeq=<%=movie.getCgvSeq()%>" id="resvBtn">예매하기</a>
		
	</div>
</div>


<script>


	

</script>







<%@include file ="../include/footer.jsp" %>		