<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'kpiFactAndTW.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/form.css" />
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">	


</head>
  
  <body>   
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">工廠報表</h3>
		</div>
		<div class="panel-body">
			<form action="weballobj_printVWeballobjasumwebyield2019" method="post" id="subform1"
				target="_blank">
				<table class="tb_search">
					<tr>
						<td><span> <input type="text" id="begin" name="yymm"
								datatype="*"
								onclick="WdatePicker({minDate:'{%y-1}-%M',maxDate:'#F{$dp.$D(\'end\')}'})" 								
								class="Wdate"> </span>至 <span> <input type="text"
								id="end" name="yymm2" datatype="*"
								onclick="WdatePicker({minDate:'#F{$dp.$D(\'begin\')}',maxDate:'%y-%M'})"
								class="Wdate"> </span> <span> <s:if
									test="#session.factNo=='tw'">
									<select name="factNo" datatype="*">
										<option value="">請選擇工廠</option>
										<s:iterator value="#session.facts" id="temp">
											<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>
										</s:iterator>
									</select>
								</s:if> <s:else>
									<select name="factNo" datatype="*">
										<option value="<s:property value="#session.factNo"/>">
											<s:property value="#session.factName" />
											(
											<s:property value="#session.factNo" />
											)
										</option>
									</select>
								</s:else> </span>
								<input type="hidden" name="a_type" value="a"/> 
								<input type="button" id="btn1" class="btn btn-primary" value="確定" />
							</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<br/></br><br/>
	<s:if test="#session.factNo=='tw'">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">台灣報表</h3>
		</div>
		<div class="panel-body">
			<form action="weballobj_printVWeballobjasumwebyield2019" method="post" id="subform2"
				target="_blank">
				<table class="tb_search">
					<tr>
						<td>																
							<span><input type="text" id="begin2" name="yymm" datatype="*"
								onclick="WdatePicker({minDate:'{%y-1}-%M',maxDate:'#F{$dp.$D(\'end2\')}'})"
								class="Wdate"> </span>至
								 <span> <input type="text" id="end2" name="yymm2" datatype="*"
								onclick="WdatePicker({minDate:'#F{$dp.$D(\'begin2\')}',maxDate:'%y-%M'})"
								class="Wdate"></span> 								
						
						<input type="hidden" name="a_type" value="b"/>
						<input type="button" id="btn2" class="btn btn-primary" value="確定"  />
						</td>
					</tr>
				</table>
				<hr>
				
			</form>
		</div>
	</div>
   </s:if>
 
<script type="text/javascript">
   function tips(){
	   window.parent.layer.alert("出現無數據時,請檢查<br/>【產量資料(盤點)】【基本數據導入】是否同時具備數據",0); 
   }
   jq(
      function(){
         jq("#subform1").Validform({
             btnSubmit : "#btn1",
             tiptype:3,
             tipSweep:true,
             showAllError:true,
             beforeSubmit:tips
         });
         jq("#subform2").Validform({
             btnSubmit:"#btn2",
             tiptype:3,
             tipSweep:true,
             showAllError:true,
             beforeSubmit:tips
         });
          
      }  
   );
		
</script>   
  </body>
</html>
