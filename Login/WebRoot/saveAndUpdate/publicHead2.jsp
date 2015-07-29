<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="entity.*"%>
<%
	if ((WebUser) ActionContext.getContext().getSession()
			.get("loginUser") == null) {
%>
<%
	response.sendRedirect("../judge.jsp");
%>
<%
	}
%>

