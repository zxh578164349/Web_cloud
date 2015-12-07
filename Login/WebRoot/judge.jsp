<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="java.net.URL"%>
<%@page import="javax.jws.soap.SOAPBinding.Use"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="entity.WebUser"%>
<%@page import="util.SessionListener"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'judge.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>
<!--  <script type="text/javascript" src="jquery/jquery-1.7.2.js"></script>-->
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="jquery/layer/layer.min.js"></script>
<script>
	var agent = navigator.userAgent.toLowerCase();
	var regStr_ie = /msie [\d.]+;/gi;
	var regStr_ff = /firefox\/[\d.]+/gi;
	var regStr_chrome = /chrome\/[\d.]+/gi;
	var regStr_saf = /safari\/[\d.]+/gi;
	$(function() {
		layer.load("正在加载,请稍等...");
		if (agent.indexOf("msie") > 0) {			
			window.location.href = "/Login/webfact_findAllfact";
		}else {			
			window.location.href = "/Login/webfact_findAllfact2";
		}
	});
</script>
<body>
	<%-- <%
		WebUser ausers = (WebUser) ActionContext.getContext().getSession()
				.get("loginUser");
		String f = request.getParameter("bj");
		Map<String, Integer> sfdlg = (Map<String, Integer>) ActionContext
				.getContext().getApplication().get("loginCount");
		String url = request.getRequestURL().toString();

		if (f != null && f.equals("1")
				&& url.equals("http://172.17.18.214:8080/Login/judge.jsp")
				&& ausers != null
				|| url.equals("http://172.17.18.214:8080/Login/")) {

			ActionContext.getContext().getSession().remove("only");
			String factNo = (String) session.getAttribute("factNo");

			WebUser auser = (WebUser) ActionContext.getContext()
					.getSession().get("loginUser");
			if (auser != null && factNo != null) {
				if (sfdlg != null) {
					for (String key : sfdlg.keySet()) {
						if (key.equals(auser.getUsername() + factNo)) {
							sfdlg.remove(auser.getUsername() + factNo);
							break;
						}
					}
					ActionContext.getContext().getApplication()
							.put("loginCount", sfdlg);
				}
				ActionContext.getContext().getSession().remove("loginUser");
				ActionContext.getContext().getSession().remove("userCount");
			}
		}
		if (f != null && f.equals("2")) {
			ActionContext.getContext().getSession().remove("only");
			ActionContext.getContext().getSession().remove("loginUser");
			ActionContext.getContext().getSession().remove("userCount");
		}
	%> --%>
</body>
</html>
