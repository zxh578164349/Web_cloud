
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
<link rel="stylesheet" type="text/css" href="css/form.css" />
<LINK href="css/list.css" type="text/css" rel="stylesheet"> 
<script type="text/javascript">
   
function go_subform(obj,factno,visasort,billno){	
   jq(function(){
     jq("#"+obj).Validform({  
     tiptype:4,
     showAllError:true,
     tipSweep:true,
     ajaxPost:true,
     callback:function(data){
    	 if(data=="0"){
    		 jq("#r_content").load("vbm_findById2",{factNo:factno,visaSort:visasort,billNo:billno});//jq("#r_content")爲main.jsp頁中的div
    	 }else{
    		 layer.msg("操作失敗",3,3);
    	 }
     }    
   });
   })
}

function showDiv2(obj){
  jq("#"+obj).show(200);
}

function hideDiv2(obj){
   jq("#"+obj).hide(200);
}

function back(){	
	loadUrl("vbm_findPageBean");
}
</script>
</head>
<body >   
	<table class="table table-striped table-hover table-bordered">	
	<h2>函文加簽</h2>
		 <tr class="tr_show">
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
		      <form action="vbm_addvisabills" method="post" id="subform${x.index}" >
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
		     	     		      
		      <div style="display:none" id="addmark${x.index}">
		             <table>
		                <tr>
		                <td>姓名:<input type="text" name="visaRank" datatype="*"/></td>
		                <td>Email:<input type="text" name="visaSigner" datatype="e"/></td>
		                </tr>
		                <tr>
		                <td>(加簽位置:之前<input type="radio" name="psMk" value=0 />|之後<input type="radio" name="psMk" value=1 checked/>)</td>
		                </tr>
		             </table>
		            <input type="submit" value="確定"  
		            onclick="go_subform('subform${x.index}','<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>','<s:property value='id.kyVisabillm.id.billNo'/>')" 
		            class="btn btn-primary btn-xs"/>
		             <input type="button" value="取消" onclick="hideDiv2('addmark${x.index}')" class="btn btn-primary btn-xs"/>       
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
