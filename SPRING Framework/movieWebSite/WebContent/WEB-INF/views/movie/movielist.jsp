<%@page import="movie.com.a.model.MovieDto"%>
<%@page import="java.util.List"%>
<%@include file ="../include/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/6cf6eed947.js" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="/movieWebSite/css/movielist.css">

<!-- 데이터 뿌리기 -->
<ul class="e_list clfix" id="main-exhibits">
<%
	List<MovieDto> list = (List<MovieDto>)request.getAttribute("movielist");
	/* int allContentSize = (int)request.getAttribute("allContentSize"); */
	
	
	
	for(int i = 0; i < list.size(); i++ ){
		MovieDto dto = list.get(i);
		%>
		<li>
			<div class='img'>
				<a href='./moviedetail.do?cgvSeq=<%=dto.getCgvSeq()%>'>
					 <img width="200" src="<%=dto.getMovieImg() %>"/> 
				</a>
					
			</div>
			<div class='txt'>
				<h3>
					<a href='#'>
						<%=dto.getTitle() %>
					</a>
				</h3>
					<p><i class="fas fa-heart"></i> <%= dto.getMovieLike()	%></p>
					<br>
			</div>
		</li>
		
		<%
	}
%>

</ul>
	<%
	if(list.size() == 0){
		%>
		<div style="height: 100px; line-height:200px; text-align: center;">현재 상영중인 영화가 없습니다.</div>
		<%
	}%>
<input type="hidden" id="current" value="now">




<%@include file ="../include/footer.jsp" %>		