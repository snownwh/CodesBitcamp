<%@page import="CYH.ReviewDto"%>
<%@page import="java.util.List"%>
<%@page import="CYH.ReviewDao"%>
<%@include file ="../include/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// 로그인 세션
MemberDto sdto = (MemberDto)session.getAttribute("loginuser");
boolean logincheck= true;
if(sdto == null ){
	logincheck = false;
}
///String loginuser = mdto.getId();
//System.out.println("id : "+ loginuser);
%>

<%
String title = request.getParameter("title");
String sre_pageNum = request.getParameter("re_pageNum");
System.out.println("sre_pageNum은 " + sre_pageNum);
int re_pageNum = Integer.parseInt(sre_pageNum);
//System.out.println("title은 " + title);
//System.out.println("re_pageNum은 " + re_pageNum);


int allRe_Contents = (int)request.getAttribute("allRe_Contents");
System.out.println("allRe_Contents는 " + allRe_Contents);

int pages = (int)Math.ceil(allRe_Contents/ 5.0);
%>

<%
List<ReviewDto> list = (List<ReviewDto>)request.getAttribute("dtolist");

// 확인용
// System.out.println("list.size()는" + list.size());
%>


<div class="reviewDtail">

<%
for(int i = 0; i < list.size(); i++) {
	ReviewDto dto = list.get(i);
%>
	<div class="box">
		<div class="box-top">
			<div class="rv-info">
				<ul class="clfix">
					<li><span><b>아이디</b></span> <%=dto.getId() %></li>
					<li><span><b>작성일</b></span> <%=dto.getRegdate() %></li>
					<li><span><b>점수</b></span> <%=dto.getStar() %>/5</li>
					<li>
						<%-- <% if(logincheck) { %>
							<a href="/AgencyBgencyy/likeadd?title=<%=title %>&writerid=<%=dto.getId() %>&sessionid=<%=sdto.getId() %>&re_pageNum=<%=i %>&regdate=<%=dto.getRegdate() %>" onclick="reload()"><img src="/AgencyBgencyy/community/LIKE.jpg" width="20" height="20"> 좋아요</a> : <%=dto.getLike_number() %>
							<a href="/AgencyBgencyy/dislikeadd?title=<%=title %>&writerid=<%=dto.getId() %>&sessionid=<%=sdto.getId() %>&re_pageNum=<%=i %>&regdate=<%=dto.getRegdate() %>" onclick="reload()"><img src="/AgencyBgencyy/community/DISLIKE.jpg" width="20" height="20"> 싫어요</a> : <%=dto.getDislike_number() %>
						<%
							} else {
						%>
								<img src="/AgencyBgencyy/community/LIKE.jpg" width="20" height="20"> 좋아요 : <%=dto.getLike_number() %>
								<img src="/AgencyBgencyy/community/DISLIKE.jpg" width="20" height="20"> 싫어요 : <%=dto.getDislike_number() %>
						<%
							}
						%> --%>
					</li>
				</ul>
			</div>
		</div>
		<div class="rv-txt">
		<%-- <%=dto.getReview() %> --%>
			<%-- <%=if( dto.getDel() == 1 ) { %>
				<font color="#ff0000">작성자에 의해 삭제된 리뷰입니다.</font> 
				<%
			}else {
				%>
				
				<%
			}
			 %> --%>
			 <%if(dto.getDel() == 0)  {
				 %>
				 <%=dto.getReview() %>
				 <%
			 }else {
				 %>
				 <font color="#ff0000"><b>작성자에 의해 삭제된 리뷰입니다.</b></font> 
				 <%
			 }
			 %>
		</div>	 		
	</div><!-- //box -->
<%
}
%>

</div><!-- //reviewDtail -->

<div class="pagingWrap">
<%
for(int i = 0; i < pages; i++) {
	if(i == re_pageNum) {
		%>
		<span class="on"><%=i + 1 %></span>
		<%
	}else {
		%>
		<span onclick="location.href='/AgencyBgencyy/onereview?title=<%=title %>&re_pageNum=<%=i %>'"><%=i + 1 %></span>
		<%
	}
	 %>
<%
}
%>
</div>

<script>
document.getElementById("ex-title").innerHTML = "<%=title %>";
</script>


<%@include file ="../include/footer.jsp" %>