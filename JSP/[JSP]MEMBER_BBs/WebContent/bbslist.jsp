<%@page import="dto.BbsDto"%>
<%@page import="java.util.List"%>
<%@page import="dao.BbsDao"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
MemberDto mem = (MemberDto)session.getAttribute("login");

BbsDao bbs = BbsDao.getInstance();
List<BbsDto> list = bbs.getBbsList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<h4 align="right" style="background-color: #f0f0f0">
	환영합니다 <%=mem.getId() %>님
</h4>

<h1>게시판</h1>

<div align="center">
	<form action="bbswrite.jsp">
	<table border="1">
	<col width="70"><col width="600"><col width="150">
		<tr>
			<th>번호</th><th>제목</th><th>작성자</th>
		</tr>
		<%
		if(list == null || list.size() == 0){
		%>
			<tr>
				<td colspan="3">작성된 글이 없습니다.	</td>
			</tr>
		<%
			} else {
				for(int i = 0; i<list.size(); i++){
					BbsDto bdto = list.get(i);
		%>
				<tr>
					<td align="center">
						<%=bdto.getSeq() %>
					</td>
					<td>
						<%
						if(bdto.getDel() == 0){
						%>
							<a href="bbsdetail.jsp?seq=<%=bdto.getSeq() %>"><%=bdto.getTitle() %></a>
						<%
						} else {
						%>
							<font color="#ff0000">이 글은 작성자에 의해 삭제되었습니다.</font>
						<%	
						}
						%>
						
					</td>
					<td align="center">
						<%=bdto.getId() %>
					</td>
				</tr>
		<%					
				}
		%>
		
		<%
		}
		%>
	</table>

	<div align="center">
	<input type="submit" value="글쓰기">
	<input type="button" id="logout" value="로그아웃">
	</div>
	
</form>
</div>

<script type="text/javascript">

	

</script>




</body>
</html>