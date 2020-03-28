<%@page import="dto.MemberDto"%>
<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
String name = request.getParameter("name");
String email = request.getParameter("email");

System.out.println("regiAf id: "+ id);
System.out.println("regiAf pwd: "+ pwd);
System.out.println("regiAf name: "+ name);
System.out.println("regiAf email: "+ email);
	
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
boolean isS = dao.addMember(new MemberDto(id, pwd, name, email, 0));

if(isS){
%>
	<script type="text/javascript">
	alert("회원가입을 성공했습니다.");
	location.href="login.jsp";
	</script>
<%
	} else {
%>
	<script type="text/javascript">
	alert("회원가입에 실패했습니다.");
	location.href="regi.jsp";
	</script>
<%	
	}
%>


</body>
</html>



