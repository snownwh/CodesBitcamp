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
String name = request.getParameter("name");
System.out.println("name: "+name);

String answer = request.getParameter("answer");
System.out.println("answer: "+answer);

String[] languages = request.getParameterValues("language");
if(languages != null && languages.length > 0){
	for(String s : languages){
		System.out.println("language: "+s);
	}
}

String opinion = request.getParameter("opinion");
System.out.println("opinion: "+opinion);

String age = request.getParameter("ages");
System.out.println("age: "+age);


%>

</body>
</html>