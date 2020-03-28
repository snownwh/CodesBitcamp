<%@page import="dao.BbsDao"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String id = request.getParameter("id");
String title = request.getParameter("title");
String content = request.getParameter("content");

System.out.println("롸이트af id:" + id);
System.out.println("롸이트af title:" + title);
System.out.println("롸이트af content:" + content);

MemberDto mem = (MemberDto)session.getAttribute("login");
BbsDao bbs = BbsDao.getInstance();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
boolean isS = bbs.addBbs(id, title, content);
System.out.println("롸이트af isS:" + isS);

if(isS){
%>
	<script type="text/javascript">
		alert("글등록이 완료되었습니다.");
		location.href="bbslist.jsp";
	</script>
	<%
} else {
%>
	<script type="text/javascript">
		alert("글등록에 오류가 있습니다.");
		location.href="bbslist.jsp";
	</script>
<%	
}
	%>
</body>
</html>