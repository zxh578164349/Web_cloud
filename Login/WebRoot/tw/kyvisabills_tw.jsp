
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
<script type="text/javascript" src="page/jquerys/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
	<script type="text/javascript">
    function showDiv(billNo,factNo){
    $.layer({
    type: 1,   //0-4的选择,
    title: '函文內容',
    //border: [0],
    closeBtn: [1,true],
    shade: [0],
    //shade: [0.5, '#000'],
    shadeClose: false,
    border: [10, 0.3, '#000'],
   // btns:1,
    //fadeIn:300,
    shift:'top',
    offset:['40px',''],
    area: ['800px', '560px'],
    page:{
      url:'kyz_findById_layer?billNo='+billNo+'& factNo='+factNo    
    }                 
});
    }
    
    function check(factNo,visaSort,billNo,itemNo){
    $.layer({
    type: 1,   //0-4的选择,
    title: '函文內容',
    //border: [0],
    border: [10, 0.3, '#000'],
    closeBtn: [1,true],
    shade: [0],
    //shade: [0.5, '#000'],
    shadeClose: false,
     btns:2,
     btn:['通過','不通過'],
    //fadeIn:300,
    shift:'top',
    offset:['40px',''],
    area: ['800px', '500px'],
    page:{
      url:'kyz_findById_layer?billNo='+billNo+'& factNo='+factNo    
    },
     yes:function(){         
      window.location.href='vbm_add?billNo='+billNo+'& visa_mk=Y'+'& factNo='+factNo+'& itemNo='+itemNo+'& visaSort='+visaSort;
    },
    no:function(){
      window.location.href='vbm_add?billNo='+billNo+'& visa_mk=T'+'& factNo='+factNo+'& itemNo='+itemNo+'& visaSort='+visaSort;
    }              
});
    }
    
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
	</script>
</head>
<body>
	<table class="altrowstable" id="alternatecolor" align="center">
	  <caption>函文審核<!-- (<s:property value="#session.loginUser.email"/>) --></caption>
		<thead>
		 <tr>
		  <td>函文單號</td>
		  <s:iterator value="vbm.kyVisabillses">
		   <td><s:property value="visaRank"/></td>
		  </s:iterator> 
		 </tr>
		</thead>
		<tr>
		  <td><a href="javascript:showDiv('<s:property value='vbm.id.billNo'/>','<s:property value='vbm.id.factNo'/>')">
		  <s:property value="vbm.id.billNo"/></a></td> 
		  <s:iterator value="vbm.kyVisabillses">
	       <td>
	       <!-- <s:property value="vbm.signerNext"/>(<s:property value="visaSigner"/>) -->
	       <s:if test='flowMk=="Y"'>
	       <s:if test='visaMk=="N"'><!-- 1.判斷未審和已審狀態 -->
	         <s:if test='id.itemNo==vbm.itemNext'><!--2.判斷當前審核人的項次是否為下一位審核人 的項次 -->	           
	           <s:if test="visaSigner==#session.loginUser.email"><!-- 3.判斷登錄者是否為當前審核人 -->
	              <a style="color:red" href="javascript:check('<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>',
	              '<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.itemNo'/>')">       
	                                     未審核<s:property value="id.itemNo"/>(當前審核人)
	          </a>
	           </s:if>	
	            <s:else>
	             <a disabled style="color:grey">未審核</a>
	           </s:else>             
	         </s:if>
	          <s:else>
	             <a disabled style="color:grey">未審核</a>
	           </s:else> 	        	       
	       </s:if>
	       <s:if test='visaMk=="Y"'>	       
	        <a style="color:green">已審核</a>
	       </s:if>
	       <s:if test='visaMk=="T"'>
	         <a style="color:blue">未通過</a>
	       </s:if>
	       </s:if>
	       <s:else>
	       <a style="color:#b45b3e">只知會</a>
	       </s:else>
	       </td>
	      </s:iterator>
		</tr>
		<tr><td colspan="<s:property value='vbm.kyVisabillses.size()+1'/>">
		<!-- <input type="button" value="返回" onclick="javascript:window.location.href='vbm_findPageBean'"/> -->		
		<a href="vbm_findPageBean_tw">返回</a>
		</td></tr>
	</table>
</body>
</html>
