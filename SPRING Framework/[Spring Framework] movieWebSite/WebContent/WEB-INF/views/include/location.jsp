<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String lourl = request.getServletPath();  //프로젝트의 경로값만 가져옴
// System.out.println(ifurl);

if (lourl.contains("aboutus.jsp")){
%>
<div class="location">
	<span>HOME</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>ABOUT US</span>
</div>
<%
}	
%>



<!-- member -->
<% 
if (lourl.contains("/login.jsp")){
%>
<div class="location">
	<span>HOME</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>MEMBER</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>LOGIN</span>
</div>
<%
}	
%>	

<% 
if (lourl.contains("/join.jsp")){
%>
<div class="location">
	<span>HOME</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>MEMBER</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>회원가입</span>
</div>
<%
}	
%>

<!-- 아이디 찾기 -->
<% 
if (lourl.contains("/findid.jsp")){
%>
<div class="location">
	<span>HOME</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>MEMBER</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>LOGIN</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>아이디 찾기</span>
</div>
<%
}	
%>

<!-- 비밀번호 찾기 -->
<% 
if (lourl.contains("/findpassword.jsp")){
%>
<div class="location">
	<span>HOME</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>MEMBER</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>LOGIN</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>비밀번호 찾기</span>
</div>
<%
}	
%>	



<!-- 영화리스트 -->
<% 
if (lourl.contains("/movielist")){
%>
<div class="location">
	<span>HOME</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>영화리스트</span>
</div>
<%
}	
%>

<!-- 상세정보 -->
<% 
if (lourl.contains("/movie_detail")){
%>
<div class="location">
	<span>HOME</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>영화리스트</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>상세정보</span>
</div>
<%
}	
%>

<!-- 영화예매 -->
<% 
if (lourl.contains("/movieSelectOne")){
%>
<div class="location">
	<span>HOME</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>영화리스트</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>상세정보</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>예매하기</span>
</div>
<%
}	
%>

<!-- 영화예매 -->
<% 
if (lourl.contains("/movieReserveAf")){
%>
<div class="location">
	<span>HOME</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>영화리스트</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>상세정보</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>예매하기</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>예매완료</span>
</div>
<%
}	
%>

<!-- 나의예매 -->
<% 
if (lourl.contains("/movieReserv.jsp")){
%>
<div class="location">
	<span>HOME</span>
	<img src="${pageContext.request.contextPath}/images/sub/lo_arrow.jpg" alt=">">
	<span>나의 예매</span>
</div>
<%
}	
%>
