
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
<title>My JSP '函文減簽' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/form.css" />
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">
<LINK href="css/list.css" type="text/css" rel="stylesheet"> 
<script type="text/javascript">


function go_subform(obj,factno,billno,visasort){
   jq(function(){
     jq("#"+obj).Validform({  
     tiptype:4,
     showAllError:true,
     tipSweep:true,
     ajaxPost:true,
     beforeSubmit:function(){
    	return confirm("確定要刪除嗎?"); 
     },
     callback:function(data){
    	if(data=="0"){
    		jq("#r_content").load("vbm_findById5",{factNo:factno,billNo:billno,visaSort:visasort});
    	} else{
    		layer.msg("操作失敗",3,3);
    	}
     }
    
   });
   })
 
}


function back(){	
	loadUrl("/Login/vbm_findPageBean");
}
</script>
</head>
<body >
	<table class="table table-striped table-hover table-bordered">
	  <h2>函文減簽(带删除)</h2>		
		 <tr class="tr_show">
		  <td>廠別</td><td>類別</td><td>單號</td><td>項次</td><td>姓名</td><td>郵箱</td><td>審核狀態</td><td>是否需要審核</td><td>操作</td>
		  </tr>
		  <s:iterator value="vbm.kyVisabillses" status="x">
		     <s:if test='flowMk=="Y"'>		    
		     <tr>
		      <td><s:property value="id.kyVisabillm.id.factNo"/></td><td><s:property value="id.kyVisabillm.id.visaSort"/></td>
		      <td><s:property value="id.kyVisabillm.id.billNo"/></td><td><s:property value="id.itemNo"/></td>
		      <td><s:property value="visaRank"/></td>
		      <td><s:property value="visaSigner"/></td>
		      <td><s:property value="visaMk"/></td>
		      <td><s:property value="flowMk"/></td>
		      <td>
		      <form action="vbm_minusvisabills2" method="post" id="subform${x.index}">
		        <input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>" name="factNo"/><input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>
		        <input type="hidden" value="<s:property value='id.kyVisabillm.id.billNo'/>" name="billNo"/><input type="hidden" value="<s:property value='id.itemNo'/>" name="itemNo"/>
		      		      		        		      		      
		          <s:if test='flowMk=="Y"'>
		            <s:if test='visaMk=="N"'>
		                <s:if test="vbm.kyVisabillses.size()==1">		                   
		                   <input type="button" value="刪除" class="btn btn-xs disabled"/>
		                   
		                </s:if>
		                <s:else>		                   		                  
		                   <input type="submit" value="刪除"  onclick="go_subform('subform${x.index}','<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>')"
		                   class="btn btn-xs btn-primary"/>	                                                    
		                </s:else>		                
		            </s:if>
		            <s:else>		                 
		                 <input type="button" value="刪除" class="btn btn-xs disabled"/>
		            </s:else> 
		          </s:if>
		          <s:else>
		            <input type="submit" value="減知會"  onclick="go_subform('subform${x.index}','<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>')"
		            class="btn btn-xs btn-primary"/>
		          </s:else>	
		      </form>       		         	        		        		      		      		    		                 
		      </td> 
		     </tr>
		     </s:if>		     	   
	      </s:iterator>		
		<tr><td colspan="10"><input type="button" value="返回" onclick="back()" class="btn btn-primary"/></td></tr>
	</table>
	
</body>
</html>
