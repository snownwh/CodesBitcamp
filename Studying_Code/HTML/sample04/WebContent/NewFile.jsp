<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
// 여기는 Java의 영역
// scriptlet = script +applet
// request == 요청
// response == 응답
String id = request.getParameter("id"); // getParameter로 name을 받아준다. 
System.out.println("id: "+id); // 받아주는 변수로 출력

String password = request.getParameter("pwd");
System.out.println("password: "+password);

// jsp에서 DB에 접근이 가능하다.
%>



</body>
</html>