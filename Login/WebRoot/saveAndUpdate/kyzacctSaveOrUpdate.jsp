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
 	jq(function() {
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			showAllError : true,
			ignoreHidden : true,
			tipSweep : true,
			ajaxPost:true,
			callback : function(data) {
				if(data=="0"){
					layer.msg("操作成功",3,1);
				}else if(data=="1"){
					layer.msg("操作失敗",3,3);
				}else{
					layer.msg("數據已存在",3,3);
				}
				
			},
			datatype : {
				"my0-6" : /^\d{0,9}(\.[0-9]{1,3})?$/,
				"my1-6" : /^[1-9]{1}\d{0,8}(\.[0-9]{1,3})?$/,
				"my0-7" : /^\d{0,7}(\.[0-9]{1})?$/

			}
		});
		demo.tipmsg.w["my0-6"] = "只能數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["my1-6"] = "不為0的數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["my0-7"] = "只能數字且不超過7位數,可保留一位以內小數";

	}); 

	function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo(mid, function(x) {
			//alert(mid);
			dwr.util.addOptions("dwrFactArea", x);
		});
	}

	function getFactArea2(mid) {
		document.getElementById("dwrFactArea2").length = 1;
		webfactjs.findFactCodeByFactNo(mid, function(x) {
			//alert(mid);
			dwr.util.addOptions("dwrFactArea2", x);
		});
	}

	function back() {
		loadUrl("/Login/kyzacct_findPageBean3?backIndex=1"); 
			

	}
	function makeSecno(obj){
	   var factno=document.getElementById("dwrFactNo").value;
	   if(factno!=""){
	      kyzsecjs.makeSecNo(obj,function(x){
	         dwr.util.setValue("secno",x);
	      });
	   }
	}
	
	function checkAcctNo(obj){
	   kyzacctjs.checkAcctNo(obj,function(x){
	          if(x==false){
	             alert("科目代號  "+obj+" 已存在,請重新輸入!");	             	            
	           } 
	   });
	}
	
jq(document).keyup(function(event){
   if(event.keyCode==13){
      submis();
   }
});
	
	

</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kyzsecjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kyzacctjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>

<body>

	<form action="kyzacct_add" method="post" id="form">
		<table class="table table-condensed"
			id="table1">							
					<tr>					  
						<td class="td_show_title" >科目代號</td>
					<s:if test="kyzacct==null">
						<td class="td_input">
						   <input type="text" name="kyzacct.acctNo" datatype="s1-8" onblur="checkAcctNo(this.value)"  />
						   <input type="hidden" name="isnull" value="isNull"/>						   
						</td>
					</s:if>
					<s:else>
					    <td class="td_input">
					       <input type="text" name="kyzacct.acctNo" value="<s:property value='kyzacct.acctNo'/>" style="color:blue" readonly/>
					       <input type="hidden" name="isnull" value="isNotnull">
					    </td>					    
					</s:else>
						<td class="td_show_title">科目名稱</td>
						<td class="td_input"><input type="text" name="kyzacct.acctName" value="<s:property value='kyzacct.acctName'/>" datatype="*0-50"/></td>											
					</tr>														
			　　<tr>
					<td class="td_show_title">分類代號<br></td>
					<td class="td_input"><input type="text" name="kyzacct.bacctNo" datatype="s0-10" value="<s:property value='kyzacct.bacctNo'/>"/></td>						  					
					<td class="td_show_title">分類名稱</td>
					<td class="td_input"><input type="text" name="kyzacct.bacctName"  value="<s:property value='kyzacct.bacctName'/>" datatype="*0-40"/>	</td>					 						
				</tr>				
		</table>


		<center>
			<input type="submit" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; 
			<input type="reset" id="reset" value="重置" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;
			<input type="button" value="返回" id="btn_back"
				onclick="javascript:back()" class="btn btn-primary"/>		
		</center>
	</form>

	

</body>

</html>
