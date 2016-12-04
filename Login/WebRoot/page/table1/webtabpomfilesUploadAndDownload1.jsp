<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

<title>My JSP 'filesUploadAndDownload.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body >
<table  id="tb" class="table table-bordered table-condensed">
	   <caption>文件下載</caption>
	   <tr><td>文件名</td><td>上傳時間</td><td>操作</td></tr>
	   <tbody id="tbody">
	   <s:iterator value="tabpom.webTabpomfiles" status="x">
	   <tr>
	     <td>
	     <form id="sub_form${x.index}">
	      <input type="hidden" value="<s:property value='id.filename'/>" name="fileName"/>
	      <input type="hidden" value="<s:property value='id.webTabpom.pomNo'/>" name="pomNo"/>
	    </form>	    	     
	     ${x.index+1}.     		        	        	         
	     <a href="javascript:funmix('sub_form${x.index}','download_inline_pom')" ><s:property value="id.filename"/></a>	    		                   	        	        
	     </td>
	     <td><s:property value="createdate"/></td>
	     <td>
	        <a href="javascript:funmix('sub_form${x.index}','download_download_pom')"  style="color:blue">下載</a>&nbsp;          
	         <!--<a href="javascript:isDelete('del_form${x.index}','download_delete')" style="color:red">刪除</a>-->
	     </td>
	   </tr>
	   </s:iterator>
	  </tbody> 
	</table>

	
</body>
</html>
