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
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">
</head>
<script type="text/javascript">
	
	function golook(){  
		 jq("#form").Validform({
		    btnSubmit : "#btnlook",
			tiptype : 4,
			showAllError : true,
			ignoreHidden : true,
			tipSweep : true,			
			ajaxPost:true,
			callback : function(date) {
				if(date=="0"){
					layer.msg("提交成功",3,1);
					loadUrl("sumwebydata_findPageBean");
					/*window.setTimeout(function(){
						var index = parent.layer.getFrameIndex(window.name);//關閉當前窗口
						parent.layer.close(index);
					},3000);*/					
				}else{
					layer.msg("提交失敗",3,3);
				}
			}			
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
	         <h2>添加盤點資料</h2>
		      <table class="table table-condensed">
		        <tr>
		          <td class="td_show_title">始末日期</td>
		          <td class="td_input">
		                                 開始:<input type="text" name="startDate" id="sDate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'eDate\',{M:-1})||\'%y-%M-%d\'}',dateFmt:'yyyyMMdd'})"  class="Wdate" datatype="*"/><br>
		                                 結束:<input type="text" name="endDate" id="eDate" onclick="WdatePicker({maxDate:'%y-{%M+1}-%d',minDate:'#F{$dp.$D(\'sDate\',{d:20})}',dateFmt:'yyyyMMdd'})" class="Wdate" datatype="*"/>		                              
		             </td>
		          <td class="td_show_title">年月</td>
		          <td class="td_input">
		             <s:if test="sumydata==null">
		             <input type="text" name="yymm" onclick="WdatePicker({minDate:'%y-{%M-12}',maxDate:'%y-%M',dateFmt:'yyyyMM'})" onchange="checkTheSame()" class="Wdate" datatype="*" id="yymm"/>
		             <input type="hidden" name="username" value="${loginUser.username}"/><!-- 輸入者  --> 
		             </s:if>
		             <s:else>
		               <input type="text" name="yymm" value="<s:property value='sumydata.id.yymm'/>" style="color:blue" readonly/>
		               <input type="hidden" name="username" value="<s:property value='sumydata.username'/>"/><!-- 保留原有的輸入者 -->
		               <input type="hidden" name="usernameUd" value="${loginUser.username}"/><!-- 修改者  --> 
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
		                <input type="text" name="factNo" value="<s:property value='sumydata.id.factNo.factNo'/>" style="color:blue" readonly/>
		               </td> 
		             </s:else>         		          		          
		        </tr>
		      </table>
		      <br>
		      <center>
		         <input type="submit" value="確定" onclick="golook()" id="btnlook" class="btn btn-primary"/>&nbsp;&nbsp;
		         <input type="reset" value="重置" class="btn btn-primary"/>
		         
		      </center>
		   </form>

	

</body>

</html>
