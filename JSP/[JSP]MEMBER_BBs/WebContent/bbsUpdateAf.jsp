<%@page import="dao.BbsDao"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
MemberDto mem = (MemberDto)session.getAttribute("login");

String sSeq = request.getParameter("seq");
int seq = Integer.parseInt(sSeq);
String title = request.getParameter("title");
String content = request.getParameter("content");

System.out.println("업데이트에프터 seq:"+ seq);
System.out.println("업데이트에프터 title:"+ title);
System.out.println("업데이트에프터 content:"+ content);
BbsDao dao = BbsDao.getInstance();
boolean isS = dao.bbsUpdate(seq, title, content);

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
	alert("업데이트 성공");
	location.href="bbslist.jsp";
	</script>
<%	
} else {
%>
	<script type="text/javascript">
	alert("업데이트 실패");
	location.href="bbslist.jsp";
	</script>
<%	
}
%>


</body>
</html>