<%@page import="java.util.List"%>
<%@page import="movie.com.a.model.MovieDto"%>
<%@include file ="../include/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%
MemberDto mem = (MemberDto)session.getAttribute("loginuser");
List<MovieDto> list = (List<MovieDto>)request.getAttribute("myReserve");
System.out.println("나의예매 페이지에서 뮤비리스트: "+ list);


%>
<div id="myReserve">	
	<table class="reservetbl">
		<colgroup>
			<col style="width:30%">
			<col style="width:15%">
			<col style="width:23%">
			<col style="width:20%">
			<col style="width:12%">
		</colgroup>
		<thead>
			<tr>
				<th>영화제목</th>
				<th>감독</th>
				<th>배우</th>
				<th>장소</th>
				<th>시간</th>
			<tr>
		</thead>
		<tbody>
			<% if (list.size() == 0) {%>
			<tr>
				<td colspan="5">예매 내역이 없습니다</td>
			</tr>
			<% } %>
			<%				
				for (int i = 0; i < list.size(); i++) {
			%>
			<tr>
				<td align="left"><img width="70px" style="vertical-align:middle" src="<%=list.get(i).getMovieImg() %>"><%=list.get(i).getTitle() %></td>
				<td><%=list.get(i).getDirector() %></td>
				<td><%=list.get(i).getActor()%></td>
				<td><%=list.get(i).getMovieLocation()%></td>
				<td><span><%=list.get(i).getMovieTime() %></span></td>
				
			</tr>
			<%
				}				
			%>					
		</tbody>		
	</table>	
</div>	
	




<%@include file ="../include/footer.jsp" %>		