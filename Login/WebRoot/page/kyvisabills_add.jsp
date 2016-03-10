
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
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">

<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
<script type="text/javascript" src="page/jquerys/Validform_v5.3.2_min.js"></script> 
	<script type="text/javascript">
   




function go_subform(obj){
   jq(function(){
     jq("#"+obj).Validform({  
     tiptype:3,
     showAllError:true,
     tipSweep:true
    /*  callback:function(){
       document.getElementById("mydiv").style.display="block";
       jq("#"+obj).submit();
       return false;       
     }  */ 
   });
   })
 
}

function showDiv2(obj){
  jq("#"+obj).show(300);
}

function hideDiv2(obj){
   jq("#"+obj).hide(300);
}

function back(){
	
	loadUrl("/Login/vbm_findPageBean");
}
</script>
</head>
<body >   
	<table class="table table-striped table-hover table-bordered">	
	<h2>函文加簽</h2>
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
		            <input type="submit" value="確定"  onclick="javascript:go_subform('subform${x.index}')" class="btn btn-primary btn-sm"/>
		             <input type="button" value="取消" onclick="javascript:hideDiv2('addmark${x.index}')" class="btn btn-primary btn-sm"/>       
		      </div>
		        </form>            
		      </td> 
		     </tr>
		     </s:if>		   
	      </s:iterator>
		
		<tr><td colspan="10"><input type="button" value="返回" onclick="back()" class="btn btn-primary"/></td></tr>
	</table>
	
</body>
</html>
