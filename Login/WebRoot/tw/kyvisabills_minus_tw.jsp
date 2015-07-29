
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
    offset:['100px',''],
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
    offset:['100px',''],
    area: ['800px', '560px'],
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

function addBtn(){
   document.getElementById("addmark").style.display="block";
   /* var div_btn=document.getElementById("div_button");
   var input=document.createElement("input");
   input.type="button";
   input.value="取消";
   input.onclick=function(){
      document.getElementById("addmark").style.display="none";   
   } */  
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
if(confirm("確定當前簽核人及前面未審核的人將全部減簽?")){
   layer.load('正在處理,并發送郵件....');
   document.getElementById(obj).submit();
}
}

function hideDiv2(obj){
   var j=jQuery.noConflict();
   j("#"+obj).hide(300);
}


	</script>
</head>
<body >
	<table class="altrowstable" id="alternatecolor" align="center">
	  <caption>函文減簽</caption>		
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
		      <form action="vbm_minusvisabills" method="post" id="subform${x.index}">
		        <input type="hidden" value="<s:property value='id.kyVisabillm.id.factNo'/>" name="factNo"/><input type="hidden" value="<s:property value='id.kyVisabillm.id.visaSort'/>" name="visaSort"/>
		        <input type="hidden" value="<s:property value='id.kyVisabillm.id.billNo'/>" name="billNo"/><input type="hidden" value="<s:property value='id.itemNo'/>" name="itemNo"/>
		      
		      <s:if test='visaMk=="N"&&#attr.x.index>0'>
		          <div id="div_button">	       
		         <a href="javascript:void(0)"  onclick="javascript:showDiv2('subform${x.index}')">減簽</a> 	        		        
		      </div>		      
		      </s:if>
		      <s:else>
		         <font color="grey">減簽</font>
		      </s:else>
		    
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
