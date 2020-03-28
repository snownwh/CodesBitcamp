<%@page import="dao.BbsDao"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
MemberDto mem = (MemberDto)session.getAttribute("login");

String sSeq = request.getParameter("seq");
int seq = Integer.parseInt(sSeq);
System.out.println("디테일 seq: " + seq);


BbsDao dao = BbsDao.getInstance();
boolean isS = dao.bbsDelete(seq);
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
if(isS){
%>
	<script type="text/javascript">
	alert("삭제 성공");
	location.href="bbslist.jsp";
	</script>
<%	
} else {
%>
	<script type="text/javascript">
	alert("삭제 실패");
	location.href="bbslist.jsp";
	</script>
<%	
}
%>


</body>
</html>