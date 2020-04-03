<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyMMddhhmm");
java.util.Date currentTime = new java.util.Date();//得到当前系统时间
String str_date = formatter.format(currentTime); //将日期时间格式化
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'Yield_prediction.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />
</head>


<body>
	<form action="webobjc_add" method="post" id="form">	
		<table class="table table-condensed">
		<tbody id="tb_list_info2">
			<s:if test="obj==null">				
					<tr>
					 <s:if test="#session.factNo!='tw'">
						<td class="td_show_title">廠別</td>
						<td class="td_input"><input type="text" style="color:blue"
							name="obj.id.webFact.id.factNo" value="${factNo}" readonly id="dwr_factno"/><span id="error1"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="obj.id.webFact.id.factArea"
							datatype="*" id="dwrFactArea" onchange="check()">
								<option value="">請選擇廠別狀態</option>
								<s:iterator value="#session.factAreas_login" id="temp">
									<option value="${temp}">${temp}</option>
								</s:iterator>
						</select><span id="error2"></span></td>
						</s:if>
						<s:if test="#session.factNo=='tw'">
						<td class="td_show_title">廠別</td>
						<td class="td_input"><select style="color:blue"
							name="obj.id.webFact.id.factNo" datatype="*"
							onchange="getFactArea(this.value),check()" id="dwr_factno">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select><span id="error1"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="obj.id.webFact.id.factArea"
							datatype="*" id="dwrFactArea" onchange="check()">
								<option value="">請選擇廠別狀態</option>
						</select><span id="error2"></span></td>
						</s:if> 
					</tr>				        
				<tr>
					<td class="td_show_title">日期</td>
					<td class="td_input"><input type="text" name="obj.id.yymmdd"
						onClick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate" datatype="*" id="dwr_yymmdd" onchange="check()"/><span id="error3"></span></td>
					
                   <td class="td_show_title">生產欠數</td>
			 　　　　 <td class="td_input">	
			        <input type="text" name="obj.objA1" datatype="*0-9">		     
			         <span id="error4"></span>                                                 
						 <input type="hidden" value="isnull" name="isnull" />						 
						 <input type="hidden" value="<s:property value='#session.loginUser.username'/>" name="obj.username" />
						 <input type="hidden" value="<%=str_date%>" name="obj.createdate" /> <!-- 創建時間 -->	
						 
			  </td>
			</tr>
			</s:if>
			<s:if test="obj!=null">
				<tr>
					<td class="td_show_title">廠別</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="factNo" style="color:blue"
							value="<s:property value='obj.id.webFact.id.factNo'/>" name="obj.id.webFact.id.factNo"
							readonly /> </font></td>
					<td class="td_show_title">廠別狀態</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="billNo" style="color:blue"
							value="<s:property value='obj.id.webFact.id.factArea'/>"
							name="obj.id.webFact.id.factArea" readonly /> </font>							
							</td>
				</tr>
				<tr>
					<td class="td_show_title">年月</td>
					<td class="td_input">
					<input type="text" style="color:blue" id="yymmdd" value="<s:property value='obj.id.yymmdd'/>" name="obj.id.yymmdd" readonly />					
						</td>
					<td class="td_show_title">生產欠數</td>
					<td class="td_input">
					<input type="text" value="<s:property value='obj.objA1'/>" name="obj.objA1" datatype="*0-9"/>
					 
						<input type="hidden" value="notnull" name="isnull" />
						<input type="hidden" value="<s:property value='obj.username'/>" name="obj.username" />
						<input type="hidden" value="<s:property value='obj.createdate'/>" name="obj.createdate" />
						<input type="hidden" value="<s:property value='#session.loginUser.username'/>" name="obj.usernameUd" />
						<input type="hidden" value="<%=str_date%>" name="obj.updatedate" />  
	
						</td>
				</tr>
			</s:if>

			<tr>
				<td class="td_show_title">孔位數</td>
				<td class="td_input">
				<input type="text" name="obj.objA2" datatype="*0-9" value="<s:property value='obj.objA2'/>"/>									
				</td>
				<td class="td_show_title">上模數</td>
				<td class="td_input">
				<input type="text" name="obj.objA3" datatype="*0-9" value="<s:property value='obj.objA3'/>"/>										
				</td>				

			</tr>
			<tr>
			    <td class="td_show_title">日產能</td>
				<td class="td_input">
				<input type="text" name="obj.objA4" datatype="*0-9" value="<s:property value='obj.objA4'/>"/>										
				</td>
				<td class="td_show_title">回轉數</td>
				<td class="td_input">
				<input type="text" name="obj.objA5" datatype="*0-9" value="<s:property value='obj.objA5'/>">										
				</td>				
			</tr>
			<tr>
			    <td class="td_show_title">出勤人員數</td>
				<td class="td_input">
				<input type="text" name="obj.objA6" datatype="n0-9" value="<s:property value='obj.objA6'/>">										
				</td>
				<td class="td_show_title">離職、資遺人數</td>
				<td class="td_input">
				<input type="text" name="obj.objA7" datatype="*0-9" value="<s:property value='obj.objA7'/>">										
				</td>
							
			</tr>
			<tr>
			    <td class="td_show_title">每日發生費用</td>	
			    <td class="td_input">
				<input type="text" name="obj.objA8" datatype="*0-9" value="<s:property value='obj.objA8'/>">																												
				</td>				
			</tr>			
         </tbody>
		</table>
		<center>
			<input type="submit" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; 
			<input type="reset" id="reset" value="重置" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;				
			<input type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary"/>

		</center>
	</form>


<script type="text/javascript">

	jq(function() {
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			tipSweep:true,
			showAllError : true,
			datatype : {
				"*0-9" : /^-?\d{1,9}(\.[0-9])?$/
			},
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					loadUrl("webobjc_findPageBean");
					//location.href="webobjc_findPageBean";
				}
				if(data=="2"){
					layer.msg("數據已存在",3,3);
				}
				if(data=="1"){
					layer.msg("提交失敗",3,3);
				}								
			}
		});
		demo.tipmsg.w["*0-9"] = "只能數字且不超過9位數,可保留1位小數";		
	});

	function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo_show_dw(mid, function(x) {
			dwr.util.addOptions("dwrFactArea", x);
		});

	}
	function back() {
		    loadUrl("webobjc_findPageBean3?backIndex=1");	
	}
      /*禁止空格輸入*/
jq(function(){
	goTrim();
}); 
</script>
<script type='text/javascript' src='dwr/interface/webfactjs.js'></script>
</body>
</html>
