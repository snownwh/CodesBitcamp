<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
MemberDto mem = (MemberDto)session.getAttribute("login");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>

<h1>글쓰기</h1>
<div align="center">
	<form action="bbswriteAf.jsp">
		<table border="1">
		<col width="200"><col width="500">
			<tr>
				<th>작성자</th>
				<td>
					<input type="text" name="id" value="<%=mem.getId() %>" size="70px" readonly="readonly">
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="title" size="70px">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="content" rows="20" cols="80"></textarea>
				</td>
			</tr>
		</table>
		<input type="submit" value="글쓰기">
		<input type="button" id="listBtn" value="목록">
	</form>
</div>

<script type="text/javascript">
$(function(){
	$("#listBtn").click(function(){
		alert("글목록으로 돌아갑니다.");
		location.href="bbslist.jsp"
	});
});

</script>


</body>
</html>