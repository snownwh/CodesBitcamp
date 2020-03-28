<%@page import="dto.BbsDto"%>
<%@page import="dao.BbsDao"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
MemberDto mem = (MemberDto)session.getAttribute("login");

String sSeq = request.getParameter("seq");
int seq = Integer.parseInt(sSeq);

System.out.println("업데이트 seq:"+ seq);
BbsDao dao = BbsDao.getInstance();
BbsDto dto = dao.bbsSelectOne(seq);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>글 수정하기</h1>
<div align="center">
	<form action="bbsUpdateAf.jsp">
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
					<input type="text" name="title" value="<%=dto.getTitle() %>" size="70px" >
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="content" rows="20" cols="80"><%=dto.getContent() %></textarea>
				</td>
			</tr>
		</table>
		<input type="submit" value="수정하기">
		<input type="button" id="listBtn" value="취소">
	</form>
</div>
</body>
</html>