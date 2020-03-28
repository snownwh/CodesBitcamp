<%@page import="movie.com.a.model.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
MemberDto memdto = (MemberDto)session.getAttribute("loginuser");
%>

<ul class="gnb clfix">
	<li class="navi_btn"><a href="#none">
		<div class="hamburger">
          <span class="line"></span>
          <span class="line"></span>
          <span class="line"></span>
        </div>
		전체메뉴
	</a></li>
	<li><a href="movielist.do">영화 리스트</a></li>
	<%
	if(memdto != null){
	%>
	<li><a href="./movieMyReserve.do?id=<%=memdto.getId() %>">나의예매</a></li>
	<%
		}
	%>
			
</ul>	

<div class="all_menu">
	<div class="inner">
		<ul>
			<li>
				<strong>영화 리스트</strong>
				<a href="./movielist.do">영화리스트</a>
			</li>
			<%
			if(memdto != null){
			%>
			<li>
				<strong>예매내역</strong>
				<a href="./movieMyReserve.do?id=<%=memdto.getId() %>">나의예매</a>
			</li>
			<%
			}
			 %>
		</ul>
	</div>
</div>

<script>
$(document).ready(function(){
	  $(".navi_btn").click(function(){
	    $(this).find('.hamburger').toggleClass("active");
	    $('.all_menu').toggleClass("active");
	    $('html, body').toggleClass("active");n
	});
});
</script>