<%@page import="dto.MemberDto"%>
<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");

System.out.println("loginAf id: "+ id);
System.out.println("loginAf pwd: "+ pwd);

MemberDao dao = MemberDao.getInstance();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
MemberDto mem = dao.login(id, pwd);

if(mem !=null && !mem.getId().equals("")){
	session.setAttribute("login", mem);
%>
	
	<script type="text/javascript">
		alert(<%=mem.getId() %>+"님, 환영합니다.");
		location.href="bbslist.jsp";
	</script>
<%	
} else {
%>
	<script type="text/javascript">
		alert("로그인에 실패하였습니다.");
		location.href="login.jsp";
	</script>	
<%	
}
%>
</body>
</html>