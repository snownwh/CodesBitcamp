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
String number = request.getParameter("number");
System.out.println("number: "+ number);

String date = request.getParameter("date");
System.out.println("date: "+date);

String color = request.getParameter("color");
System.out.println("color: "+color);

String range = request.getParameter("range");
System.out.println("range: "+range);

String search = request.getParameter("search");
System.out.println("search: "+search);
%>
</body>
</html>