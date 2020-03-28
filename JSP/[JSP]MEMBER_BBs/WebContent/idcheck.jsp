<%@page import="dao.MemberDao"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");

	System.out.println("id:"+id);
	
	// dao
	//boolean <- idcheck 함수
	MemberDao dao = MemberDao.getInstance();
	boolean b = dao.getId(id);
	if(b){
		response.getWriter().write("NO");
	}else{
		response.getWriter().write("YES");
		
	}
		
	
	/* fetch("url").then(function(data){
		
	}) */
	
%>
