<%@page import="KSJ.files.dto.FilesDto"%>
<%@page import="CYH.ReviewDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@page import="KSJ.exhibit.dto.ExhibitDto"%>
<%@include file ="/include/header.jsp" %>
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
else{
	%>
	<script type="text/javascript">
		alert('로그인 만료!');
	</script>
	<%
}
%>

<%
	request.setCharacterEncoding("utf-8");
	String nowpath = request.getContextPath();

	//등록한 전시 dto
	ExhibitDto edto = (ExhibitDto)request.getAttribute("edto");

	// 표지 파일
	FilesDto titleFile =(FilesDto)request.getAttribute("fdto");
	// 표지 파일 있는 지 확인하기 
	boolean imgExist = true;
	if(titleFile.getFilename() == null){
		imgExist = false;
	}
	System.out.println("imgExist:"+imgExist);
%>
<%!
public String makeDate(String date){
	String result = "";
	String yy = date.split("-")[0];
	String mm = date.split("-")[1];
	String dd = date.split("-")[2].split(" ")[0];

	return yy+"-"+mm+"-"+dd;
}
%>
<link rel="stylesheet" type="text/css" href="<%=nowpath %>/exhibit/css/exdetail.css">
<div class="ex-datail-top clfix">
	<div class="img">
	<%
	if(!imgExist){
		%>
		 <img alt="이미지 없음" src="${pageContext.request.contextPath}/images/sub/noimg.gif"/>
		<%
	}else{
		%>
		 <img alt="이미지 없음" id="title" src="${pageContext.request.contextPath}/filedownload?filepath=<%=titleFile.getFilepath()%>&filename=<%=titleFile.getFilename()%>"/>
		<%
	}
	%>
	</div>
	<div class="cont">
		<h4><%=edto.getTitle() %></h4>
		<p><span>장소</span><%=edto.getPlace() %> </p>
		<p><span>기간</span><%=makeDate(edto.getBegindate()) +" ~ " + makeDate(edto.getEnddate())%></p>
		<p><span>시간	</span><%=edto.getEx_time().substring(0, 2) +":" +edto.getEx_time().substring(2, 4)+" - " + edto.getEx_time().substring(4, 6) +":" +edto.getEx_time().substring(6, 8) %></p>
		<p id="price"><span>관람료</span></p>
		<p><span>문의</span><%= edto.getContact()==null? "": edto.getContact()%></p>
		<div class="ex-btnwrap">
			<a href="#" class="showlist" id="showMyExhibit">목록보기</a>
			<a href="#" class="delete"id="deleteMyExhibit">등록취소</a>
		</div>
		<!-- ${pageContext.request.contextPath}/reservation/reserv.jsp -->
	</div>
</div>
<div class="ex-datail-mid">
	<h5>전시안내</h5>
	<div class="txt">
		<%=edto.getContent() %>
	</div>
</div>
<style>
.ex-btnwrap{
position: absolute; bottom:0; left:0; text-align:center;
font-size:0; width:550px;
}

.ex-btnwrap a {
display:inline-block; width:180px; height:70px; line-height:68px;
transition : all 0.3s ease-in-out; font-size:18px; color:#fff;
border:1px solid #5f0080; background: #5f0080; outline:none;
font-size:18px; color:#fff; text-align: center; border-radius: 10px;
margin:0 10px;
}
.ex-datail-top .cont a.delete:hover,a.showlist:hover{
	color: #5f0080; background: none;
}
</style>
<script>

$(document).ready(function() {
	

	var total = <%=edto.getPrice()%>;
	//ex) 7000원 -> 7,000원
	var len, point, str;	
	total = total + "";
	point = total.length % 3 ;
	len = total.length;
	
	totalPrice = total.substring(0, point);
	while (point < len) {
		if (totalPrice != "") totalPrice += ",";
		totalPrice += total.substring(point, point + 3);
		point += 3;
	}

$("#price").append(totalPrice+"원");


// 목록보기 버튼 클릭시 이벤트
$("#showMyExhibit").click(function() {
	
	if(<%=logincheck%> == false){
			// 로그인이 안됨 
		if(confirm("로그인이 필요합니다. 로그인 페이지로 이동합니다.")){
			location.href="${pageContext.request.contextPath}/member/login.jsp?choice=resv";
			/* 로그인 후 다시 정보를 가지고 이 페이지로 올 수 있도록 설계 해보기?  */
		}
	}else {
		// 전체 목록으로 돌아가기 
		location.href="${pageContext.request.contextPath}/curatorexhibitlist?page=0"; 
	}
		
	
	
	});
// 삭제하기  클릭시 이벤트
$("#deleteMyExhibit").click(function() {
	if(confirm('전시 등록을 취소하시겠습니까? \n(등록 취소 후에는 등록 게시글 및 예매가 불가능합니다.)'))
	if(<%=logincheck%> == false){
			// 로그인이 안됨 
		if(confirm("로그인이 필요합니다. 로그인 페이지로 이동합니다.")){
			location.href="${pageContext.request.contextPath}/member/login.jsp?choice=resv";
			/* 로그인 후 다시 정보를 가지고 이 페이지로 올 수 있도록 설계 해보기?  */
		}
	}else {
		// 전체 목록으로 돌아가기 
		location.href="${pageContext.request.contextPath}/curatorwritedelete?seq=<%=edto.getSeq()%>"; 
	}
		
	
	
	});
});
</script>







<%@include file ="../include/footer.jsp" %>		