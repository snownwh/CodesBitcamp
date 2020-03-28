<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	// String data = request.getParameter("name명");

	String jobs[] = request.getParameterValues("job");

	if (jobs != null && jobs.length > 0) {
		for (String s : jobs) {
			System.out.println(s);
		}
	}
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>