

<%@page
	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="dao.IKyFactDao"%>
<%@page import="services.impl.KyFactServicesImpl"%>
<%@page import="services.IKyFactServices"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="entity.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"　"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>My JSP 'publicHead.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">		
<LINK href="css/list.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>


<script type="text/javascript">
   
   //dwr加載函文類別
/*    function getType(){
       kytypejs.findByTypeNo("VV",function(x){
	         dwr.util.addOptions("visaSort",x,"typeName","typeSname");
	   });
   } */
   
jq(document).keyup(function(event) {
		if (event.keyCode == 13) {
			submis();
		}
	});  

</script>
<script type='text/javascript' src='/Login/dwr/interface/kytypejs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>
</head>
<body >
  <div style="width:720px">
	<table  border=0 >
		<tr>
			<td>科目代號</td>
			<td><input type="text" name="acctNo" id="acctno"></td>			
			<td>科目名稱</td>
			<td><input type="text" name="acctName" value="" id="acctname"/></td> 
			<td><!-- <input type="image" onclick="javascript:submis();"  src="images/search002.gif"/> -->
			<input value="搜索" type="submit" id="addbtn" onclick="javascript:submis()" />
			</td>			
		</tr>
	</table>
</div>	
</body>
</html>

