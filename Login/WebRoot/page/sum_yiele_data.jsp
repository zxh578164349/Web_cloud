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

<title>My JSP 'Yield_data.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/validate.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/form.css" />
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="jquery/DatePicker/my2_WdatePicker.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script> 
</head>
<script type="text/javascript">
	var j = jQuery.noConflict();
	function golook(){  
		 j("#form").Validform({
		 btnSubmit : "#btnlook",
			tiptype : 3,
			showAllError : true,
			ignoreHidden : true,
			tipSweep : true
			//ajaxPost:false
			/* callback : function(form) {
				document.getElementById("mydiv").style.display = "block";				
				form[0].submit();
			} */			
		});		
	}
 		 
	/* function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo(mid, function(x) {
			//alert(mid);
			dwr.util.addOptions("dwrFactArea", x);
		});
	} */
	function checkTheSame(){
	    var factno=document.getElementById("factno").value;
	    var yymm=document.getElementById("yymm").value;
	    if(factno!=""&&yymm!=""){
	       sumwebydatajs.findByFactNo(factno,yymm,function(x){
	        if(x!=0){
	           alert("廠別:"+factno+",年月:"+yymm+"已存在,請重新添加");
	           document.getElementById("btnlook").disabled=true;
	           document.getElementById("btnlook").value="鎖定";
	        }else{
	        document.getElementById("btnlook").disabled=false;
	        document.getElementById("btnlook").value="確定";
	        }
	       }) 
	    } 
	}
	
/* function showlayer(){
   j("#mydiv").show();
} */
	
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/sumwebydatajs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>

<body>

	<form action="sumwebydata_add" method="post" id="form">
		      <table align="center">
		        <tr>
		          <td class="td_show_title">始末日期</td>
		          <td class="td_input">
		                                 開始:<input type="text" name="startDate" id="sDate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'eDate\',{M:-1})||\'%y-%M-%d\'}',dateFmt:'yyyyMMdd'})"  class="Wdate" datatype="*"/><br>
		                                 結束:<input type="text" name="endDate" id="eDate" onclick="WdatePicker({maxDate:'%y-{%M+1}-%d',minDate:'#F{$dp.$D(\'sDate\',{d:25})}',dateFmt:'yyyyMMdd'})" class="Wdate" datatype="*"/>
		              <input type="hidden" name="username" value="${loginUser.username}"/><!-- 輸入者  -->                  
		             </td>
		          <td class="td_show_title">年月</td>
		          <td class="td_input">
		             <s:if test="sumydata==null">
		             <input type="text" name="yymm" onclick="WdatePicker({minDate:'%y-{%M-6}',maxDate:'%y-%M',dateFmt:'yyyyMM'})" onchange="checkTheSame()" class="Wdate" datatype="*" id="yymm"/>
		             </s:if>
		             <s:else>
		               <input type="text" name="yymm" value="<s:property value='sumydata.id.yymm'/>" style="color:blue" readonly/>
		             </s:else>
		          </td>	   		          
		        </tr>
		        <tr>
		          <td class="td_show_title">廠別</td>
		             <s:if test="sumydata==null">
		                 <s:if test="#session.factNo=='tw'">
		               <td class="td_input">
		                <select style="color:blue" name="factNo" onchange="checkTheSame()" datatype="*" id="factno">														
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]}&nbsp;(${temp[0]})</option>										
								</s:iterator>
						</select>
						</td>
		             </s:if>		             
		             <s:else>
		               <td class="td_input">
		                <input type="text" style="color:blue" name="factNo" value="${factNo}" readonly id="factno" />							
					   </td>		
		             </s:else>	
		             </s:if>
		          　　　　　<s:else>
		               <td class="td_input">
		                <input type="text" name="factNo" value="<s:property value='sumydata.id.factNo'/>" style="color:blue" readonly/>
		               </td> 
		             </s:else>         		          		          
		        </tr>
		      </table>
		      <br>
		      <center>
		         <input type="submit" value="確定" onclick="golook()" id="btnlook"/>&nbsp;&nbsp;
		         <input type="reset" value="重置" />
		      </center>
		   </form>

	<div id="mydiv">
		<p>
			<img alt="" src="images/loading004.gif"><br> Loading....
		</p>
	</div>

</body>

</html>
