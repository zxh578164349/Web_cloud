<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	   <tr><td>文件名</td><td>上傳時間</td></tr>
	   <tbody id="tbody">
	   <s:iterator value="#session.uploadfiles" status="x">
	   <tr>
	     <td>	    
	     <span style="float:left">
	     ${x.index+1}.
	     <s:if test='filetype=="text/plain"'>
	       <img src="images/icon/text.jpg"/>
	     </s:if>
	     <s:if test='filetype=="application/vnd.ms-excel"'>
	       <img src="images/icon/excel.jpg"/>
	     </s:if>
	      <s:if test='filetype=="application/msword"'>
	       <img src="images/icon/word.jpg"/>
	     </s:if>
	     <s:if test='filetype.contains("image")'>
	       <img src="images/icon/picture.jpg"/>
	     </s:if>        		        	        	         
	     <a href="download_download?fileName=<s:property value='filename'/>" ><s:property value="filename"/></a>
	    </span>
	    <form id="del_form${x.index}">
	      <input type="hidden" value="<s:property value='filename'/>" name="fileName"/>
	      <input type="hidden" value="<s:property value='id'/>" name="id"/>
	    </form>
	    <span style="float:right">  
	          <a href="download_download?fileName=<s:property value='filename'/>" style="color:blue">下載</a>&nbsp;|&nbsp;	          
	          <a href="javascript:isDelete('del_form${x.index}','download_delete')" style="color:red">刪除</a>
	     </span>		                   	        	        
	     </td>
	     <td><s:property value="uploaddate"/></td>
	   </tr>
	   </s:iterator>
	  </tbody> 
	</table> 
</body>
</html>
