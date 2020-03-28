<%@page import="java.util.List"%>
<%@page import="movie.com.a.model.MovieDto"%>
<%@include file ="../include/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
MemberDto mem = (MemberDto)session.getAttribute("loginuser");
List<MovieDto> list = (List<MovieDto>)request.getAttribute("movielist");
MovieDto movie = (MovieDto)request.getAttribute("moviedetail");
%>    
<div id="movieSelectOne">
	<div class="insertFrm">	
	   <form method="post" action="./movieReserveAdd.do">
	      <div class="frm_cont border-btm">
	      <!-- 아이디(히든) -->
	      <input type="hidden" name="id" value="<%=mem.getId() %>">
	      <!-- 영화포스터(히든) -->
	      <input type="hidden" name="movieImg" value="<%=movie.getMovieImg() %>">
	      <!-- 좋아요(히든) -->
	      <input type="hidden" name="movieLike" value="<%=movie.getMovieLike() %>">
	      <!-- CGVSEQ(히든) -->
	      <input type="hidden" name="cgvSeq" value="<%=movie.getCgvSeq() %>">
	      <!-- 감독(히든) -->
	      <input type="hidden" name="director" value="<%=movie.getDirector() %>">
	      <!-- 배우(히든) -->
	      <input type="hidden" name="actor" value="<%=movie.getActor() %>">
	         <div class="category" align="center">
	         <h2>영화제목</h2>
	            <div class="category-radius categoryBox">
	               <%
	               for(int i = 0; i < list.size() ; i++){
	            	   if(movie.getTitle().equals(list.get(i).getTitle())){
	               %>
						<div class="on"><%=list.get(i).getTitle() %><input type="hidden" name="title" value="<%=list.get(i).getTitle() %>"></div>	
	               <%
	            	   } else {
	               %>
	               		<div class="curatorBtn"><%=list.get(i).getTitle() %><input type="hidden" value=""></div>
	               <%	   
	            	   }
	               }
	               %>
	            </div>
	         </div>
	         <div class="category1" align="center">
	         <h2>극장명</h2>
	            <div class="category1-radius categoryBox1">
	               <div class="on">강남<input type="hidden" name="movieLocation" value="강남"></div>	
               		<div class="curatorBtn">대학로<input type="hidden" value="대학로"></div>
               		<div class="curatorBtn">여의도<input type="hidden" value="여의도"></div>
               		<div class="curatorBtn">피카디리1958<input type="hidden" value="피카디리1958"></div>
               		<div class="curatorBtn">용산아이파크<input type="hidden" value="용산아이파크"></div>
               		<div class="curatorBtn">왕십리<input type="hidden" value="왕십리"></div>
               		<div class="curatorBtn">홍대<input type="hidden" value="홍대"></div>
	            </div>
	         </div>
	          <div class="category2" align="center">
	          <h2>시간</h2>
	            <div class="category2-radius categoryBox2">
					<div class="on">10:00<input type="hidden" name="movieTime" value="10:00"></div>	
               		<div class="curatorBtn">12:00<input type="hidden" value="12:00"></div>
               		<div class="curatorBtn">14:00<input type="hidden" value="14:00"></div>
               		<div class="curatorBtn">16:00<input type="hidden" value="16:00"></div>
               		<div class="curatorBtn">18:00<input type="hidden" value="18:00"></div>
               		<div class="curatorBtn">20:00<input type="hidden" value="20:00"></div>
               		<div class="curatorBtn">22:00<input type="hidden" value="22:00"></div>
	            </div>
	         </div>
	      </div>
	      <button class="reserv_btn sBtn">예매하기</button>
	      
	   </form><!-- insertFrm -->
	</div><!-- formWrap -->
</div><!-- joinPage -->

<script type="text/javascript">
$('.categoryBox div').click(function(){
/* 	// 영화선택
   $('.categoryBox div').removeClass('on');
   $(this).addClass('on'); */

 //영화제목 value 넘기기
   if($('.frm_cont .category div').hasClass('on')){   
   	   $('.frm_cont .category div').find('input').attr({"name":""}); 
   	   $(this).find('input').attr({"name":"title"}); 
   }
});  

$('.categoryBox1 div').click(function(){
	// 극장선택 
   $('.categoryBox1 div').removeClass('on');
   $(this).addClass('on');

 //극장명 value 넘기기
   if($('.frm_cont .category1 div').hasClass('on')){   
   	   $('.frm_cont .category1 div').find('input').attr({"name":""}); 
   	   $(this).find('input').attr({"name":"movieLocation"}); 
   }
});

$('.categoryBox2 div').click(function(){
	// 시간선택 
   $('.categoryBox2 div').removeClass('on');
   $(this).addClass('on');

 //시간 value 넘기기
   if($('.frm_cont .category2 div').hasClass('on')){   
   	   $('.frm_cont .category2 div').find('input').attr({"name":""}); 
   	   $(this).find('input').attr({"name":"movieTime"}); 
   }
   
});






</script>


<%@include file ="../include/footer.jsp" %>      