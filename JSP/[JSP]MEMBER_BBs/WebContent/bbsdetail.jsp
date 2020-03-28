<%@page import="dto.BbsDto"%>
<%@page import="dao.BbsDao"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
MemberDto mem = (MemberDto)session.getAttribute("login");

String sSeq = request.getParameter("seq");
int seq = Integer.parseInt(sSeq);
System.out.println("디테일 seq: " + seq);

BbsDao bbs = BbsDao.getInstance();
BbsDto bdto = bbs.bbsSelectOne(seq);
System.out.println("디테일 bdto: " + bdto);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<h1>글 상세보기</h1>
<div align="center">
	<form action="bbsUpdate.jsp">
		<table border="1">
		<col width="200"><col width="500">
			<tr>
				<th>작성자</th>
				<td>
					<input type="text" name="id" value="<%=mem.getId() %>" size="70px" readonly="readonly">
					<input type="hidden" name="seq" value=<%=seq %>>
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="title" value="<%=bdto.getTitle() %>" size="70px" readonly="readonly">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="content" rows="20" cols="80" readonly="readonly"><%=bdto.getContent() %></textarea>
				</td>
			</tr>
		</table>
		<input type="submit" value="수정">
		<input type="button" id="deleteBtn" value="삭제">
		<input type="button" onclick="location.href='bbslist.jsp'" value="목록">
	</form>
</div>

<script type="text/javascript">
$(function(){
	$("#deleteBtn").click(function(){
		location.href="bbsdelete.jsp?seq=" + <%=seq %>;
	});
});

</script>



</body>
</html>