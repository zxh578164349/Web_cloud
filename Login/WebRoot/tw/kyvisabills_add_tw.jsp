
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'backmat1.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- <LINK href="css/list.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/form.css" /> -->
<link rel="stylesheet" type="text/css" href="css/form.css" />
<style type="text/css">
table.altrowstable {
	font-family: verdana,arial,sans-serif;
	font-size:14px;
	color:#333333;
	border-width: 1px;
	border-color: #a9c6c9;
	border-collapse: collapse;
}
table.altrowstable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
table.altrowstable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
table.altrowstable caption{
    font-size:22px;
}
.oddrowcolor{
	background-color:#d4e3e5;
}
.evenrowcolor{
	background-color:#c3dde0;
}


</style>
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
<script type="text/javascript" src="page/jquerys/Validform_v5.3.2_min.js"></script> 
	<script type="text/javascript">
   
function altRows(id){
	if(document.getElementsByTagName){  
		
		var table = document.getElementById(id);  
		var rows = table.getElementsByTagName("tr"); 
		 
		for(i = 0; i < rows.length; i++){          
			if(i % 2 == 0){
				rows[i].className = "evenrowcolor";
			}else{
				rows[i].className = "oddrowcolor";
			}      
		}
	}
}

window.onload=function(){
	altRows('alternatecolor');
}



function go_subform(obj){
   var j=jQuery.noConflict();
   j(function(){
     j("#"+obj).Validform({  
     tiptype:3,
     showAllError:true,
     tipSweep:true
    /*  callback:function(){
       document.getElementById("mydiv").style.display="block";
       j("#"+obj).submit();
       return false;       
     }  */ 
   });
   })
 
}

function showDiv2(obj){
  var j=jQuery.noConflict();
  j("#"+obj).show(300);
}

function hideDiv2(obj){
   var j=jQuery.noConflict();
   j("#"+obj).hide(300);
}


	</script>
</head>
<body >
	<table class="altrowstable" id="alternatecolor" align="center">
	  <caption>函文加簽</caption>		
		 <tr>
		  <td>廠別</td><td>類別</td><td>單號</td><td>項次</td><td>姓名</td><td>郵箱</td><td>審核狀態</td><td>操作</td>
		  </tr>
		  <s:iterator value="vbm.kyVisabillses" status="x">
		  　　<s:if test='flowMk=="Y"'>
		     <tr>
		      <td><s:property value="id.kyVisabillm.id.factNo"/></td><td><s:property value="id.kyVisabillm.id.visaSort"/></td>
		      <td><s:property value="id.kyVisabillm.id.billNo"/></td><td><s:property value="id.itemNo"/></td>
		      <td><s:property value="visaRank"/></td>
		      <td><s:property value="visaSigner"/></td>
		      <td><s:property value="visaMk"/></td>
		       <td>
		      <form action="vbm_addvisabills" method="post" id="subform${x.index}">
		        <input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>" name="factNo"/><input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>
		        <input type="hidden" value="<s:property value='id.kyVisabillm.id.billNo'/>" name="billNo"/><input type="hidden" value="<s:property value='id.itemNo'/>" name="itemNo"/>
		      
		      <s:if test='visaMk=="N"'>
		          <div id="div_button">	       
		         <a href="javascript:void(0)"  onclick="javascript:showDiv2('addmark${x.index}')">加簽</a> 	        		        
		      </div>		      
		      </s:if>
		      <s:else>
		         <font color="grey">加簽</font>
		      </s:else>
		      <%-- <s:if test="#attr.x.index+1==vbm.kyVisabillses.size()">
		         <a href="javascript:void(0)" onclick="javascript:document.getElementById('addmark${x.index}').style.display='block'">知會</a>
		      </s:if>	 --%>	     		      
		      <div style="display:none" id="addmark${x.index}">
		             <table>
		                <tr>
		                <td>姓名:<input type="text" name="visaRank" datatype="*"/></td>
		                <td>Email:<span><input type="text" name="visaSigner" datatype="e"/></td>
		                </tr>
		             </table>
		            <input type="submit" value="確定"  onclick="javascript:go_subform('subform${x.index}')"/>
		             <input type="button" value="取消" onclick="javascript:hideDiv2('addmark${x.index}')"/>       
		                    </div>
		        </form>            
		      </td> 
		     </tr>
		     </s:if>		   
	      </s:iterator>
		
		<tr><td colspan="10"><input type="button" value="返回" onclick="javascript:window.location.href='vbm_findPageBean_tw'"/></td></tr>
	</table>
	<div id="mydiv">
		<p>
			<img alt="" src="images/loading004.gif"><br> Loading....
		</p>
	</div>
</body>
</html>
