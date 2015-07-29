<%@page import="java.net.URL"%>
<%@page import="javax.jws.soap.SOAPBinding.Use"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="entity.WebUser"%>
<%@page import="util.SessionListener"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'recode.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<%
		String url = request.getRequestURL().toString();
		String f = request.getParameter("bj");
		if (f != null && f.equals("1")
				|| url.equals("http://172.17.18.211/Login/")) {
			ActionContext.getContext().getSession().remove("userCount");
			ActionContext.getContext().getSession().remove("only");
			String factNo = (String) session.getAttribute("factNo");
			Map<String, Integer> sfdlg = (Map<String, Integer>) ActionContext
					.getContext().getApplication().get("loginCount");
			WebUser auser = (WebUser) ActionContext.getContext()
					.getSession().get("loginUser");
			if (auser != null && factNo != null) {
				if (sfdlg != null) {
					for (String key : sfdlg.keySet()) {
						if (key.equals(auser.getUsername() + factNo)) {
							//sfdlg.put(auser.getUsername()+factNo,1);
							sfdlg.remove(auser.getUsername() + factNo);
						}
					}
					ActionContext.getContext().getApplication()
							.put("loginCount", sfdlg);
				}
			}
			response.sendRedirect("judge.jsp");
		}

		String s = request.getParameter("bj1");
		if (s != null && s.equals("1")) {
			ActionContext.getContext().getSession().remove("userCount");
			ActionContext.getContext().getSession().remove("only");
			response.sendRedirect("judge.jsp");
		}
	%>
</body>
</html>
