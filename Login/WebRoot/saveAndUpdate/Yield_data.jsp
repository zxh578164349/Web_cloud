<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

<title>My JSP 'Yield_data.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />
</head>


<body>

	<form action="ydata_addData" method="post" id="form" role="form" class="form-inline">
		<table class="table table-condensed" id="table1">
			<s:if test="ydata==null">

				<s:if test="#session.factNo!='tw'">
					<tr>
						<td class="td_show_title" width="10%">廠別</td>
						<td class="td_input"><input type="text" style="color:blue" name="ydata.id.factNo" value="${factNo}" readonly id="dwr_factno" /><span id="error1"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="ydata.id.factCode" datatype="*" id="dwrFactArea" >
								<option value="">請選擇廠別狀態</option>
								<s:iterator value="#session.factAreas_login" id="temp">
									<option value="${temp}">${temp}</option>
								</s:iterator>
						</select><span id="error2"></span>
						</td>
					</tr>
				</s:if>
				<s:if test="#session.factNo=='tw'">
					<tr>
						<td class="td_show_title">廠別</td>
						<td class="td_input"><select style="color:blue" name="ydata.id.factNo" datatype="*" id="dwr_factno" onclick="getFactArea(this.value)">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1] }&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select><span id="error1"></span>
						</td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="ydata.id.factCode" datatype="*" id="dwrFactArea" >
								<option value="">請選擇廠別狀態</option>
						</select><span id="error2"></span>
						</td>
					</tr>
				</s:if>

				<tr>


					<td class="td_show_title">日期</td>

					<td class="td_input">
					<input type="text" name="yymmdd" onClick="WdatePicker({dateFmt:'yyyyMMdd',maxDate:'%y-%M-%d'})" class="Wdate" datatype="*" id="yymmdd"/>
					<span id="error3"></span>
					</td>


					<td class="td_show_title">工作日/假日</td>
					<td class="td_input"><select name="ydata.workorholiday" id="workholiday" datatype="*" onclick="showRow()">
							<option value="">請選擇</option>
							<option value=0>工作日</option>
							<option value=1>假日</option>
							<option value=2>未排產</option>
					</select> 
					<input type="hidden" value="isnull" name="isnull" />
					<input type="hidden" value="<s:property value='#session.loginUser.username'/>" name="ydata.username" /><!-- 創建人 -->
					<input type="hidden" value="<%=str_date%>" name="ydata.dateCreate" /> <!-- 創建時間 -->	
					</td>

				</tr>
			</s:if>

			<s:if test="ydata!=null">

				<tr>
					<td class="td_show_title">廠別</td>
					<td class="td_input"><font color="blue"><input type="text" id="factNo" style="color:blue" value="<s:property value='ydata.id.factNo'/>" name="ydata.id.factNo"
							readonly /> </font>
					</td>
					<td class="td_show_title">廠別狀態</td>
					<td class="td_input"><font color="blue"><input type="text" id="billNo" style="color:blue" value="<s:property value='ydata.id.factCode'/>" name="ydata.id.factCode"
							readonly /> </font>
					</td>
				</tr>
				<tr>

					<td class="td_show_title">日期</td>
					<td class="td_input"><input type="text" style="color:blue" id="yymm" value="<s:date name='ydata.id.yymmdd' format='yyyyMMdd'/>" name="yymmdd" readonly />
					 		
					</td>
					<td class="td_show_title">工作日/假日</td>
					<td class="td_input"><select name="ydata.workorholiday" id="workholiday" datatype="*" onchange="showRow()">
							<option value="">請選擇</option>
							<s:if test="ydata.workorholiday==0">
								<option value=0 selected>工作日</option>
							</s:if>
							<s:else>
								<option value=0>工作日</option>
							</s:else>
							<s:if test="ydata.workorholiday==1">
								<option value=1 selected>假日</option>
							</s:if>
							<s:else>
								<option value=1>假日</option>
							</s:else>
							<s:if test="ydata.workorholiday==2">
								<option value=2 selected>未排產</option>
							</s:if>
							<s:else>
								<option value=2>未排產</option>
							</s:else>

					</select>
					 <input type="hidden" value="notnull" name="isnull" /> 						
					 <input type="hidden" value="${loginUser.username}" name="ydata.usernameUd" /> <!--  修改者--> 
					 <input type="hidden" value="<s:property value='ydata.dateCreate'/>" name="ydata.dateCreate" /> <!-- 創建時間 -->
					<input type="hidden" value="<s:property value='ydata.username'/>" name="ydata.username" /><!-- 創建者--> 
					</td>
				</tr>
			</s:if>

			<!-- 以下為隱藏部分 -->

			<tr>
				<td class="td_show_title" width="10%">人數(人)</td>

				<td class="td_input"><input type="text" class="input_hidden" name="ydata.personnum" value="<s:property value='%{formatDouble2(ydata.personnum)}'/>" datatype="*,*0-6"></td>
				<td class="td_show_title">上模數(模)</td>
				<td class="td_input"><input type="text" class="input_hidden" name="ydata.onModulus" datatype="*,*0-6" value="<s:property value='%{formatDouble2(ydata.onModulus)}'/>"></td>
			</tr>

			<tr>
				<td class="td_show_title">標準產量(模)</td>
				<td class="td_input"><input type="text" class="input_hidden" name="ydata.standardOutput" value="<s:property value='%{formatDouble2(ydata.standardOutput)}'/>" datatype="*,*0-6"></td>

				<td class="td_show_title">實際產量(模)</td>
				<td class="td_input"><input type="text" class="input_hidden" name="ydata.actualYield" value="<s:property value='%{formatDouble2(ydata.actualYield)}'/>" datatype="*,*0-6">
				</td>

			</tr>


			<tr>
				<td class="td_show_title">實際生產雙數(雙)</td>
				<td class="td_input"><input type="text" class="input_hidden" name="ydata.actualpairs" value="<s:property value='%{formatDouble2(ydata.actualpairs)}'/>" datatype="*,*0-6">
				</td>

				<td class="td_show_title">客補生產雙數(雙)</td>
				<td class="td_input"><input type="text" class="input_hidden" name="ydata.hostpairs" value="<s:property value='%{formatDouble2(ydata.hostpairs)}'/>" datatype="*,*0-6">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">廠補生產雙數(雙)</td>
				<td class="td_input"><input type="text" class="input_hidden" name="ydata.factpairs" value="<s:property value='%{formatDouble2(ydata.factpairs)}'/>" datatype="*,*0-6"></td>

				<td class="td_show_title">樣品生產雙數(雙)</td>
				<td class="td_input"><input type="text" class="input_hidden" name="ydata.samplepairs" value="<s:property value='%{formatDouble2(ydata.samplepairs)}'/>" datatype="*,*0-6"></td>

			</tr>
			<tr>
				<td class="td_show_title">出貨數(雙)</td>
				<td class="td_input"><input type="text" class="input_hidden" name="ydata.outnum" value="<s:property value='%{formatDouble2(ydata.outnum)}'/>" datatype="*,*0-6"></td>

				<td class="td_show_title">退貨數(雙)</td>
				<td class="td_input"><input type="text" class="input_hidden" name="ydata.backnum" value="<s:property value='%{formatDouble2(ydata.backnum)}'/>" datatype="*,*0-6"></td>

			</tr>
			<tr>
				<td class="td_show_title">天數(天)</td>
				<td class="td_input"><input type="text" class="input_hidden" name="ydata.daycount" value="<s:property value='%{formatDouble2(ydata.daycount)}'/>" datatype="*,*0-2"> 						
				</td>

				<td class="td_show_title">上模總工時(小時)</td>
				<td class="td_input"><input type="text" class="input_hidden" name="ydata.workhours" value="<s:property value='%{formatDouble2(ydata.workhours)}'/>" datatype="*,*0-6"></td>



			</tr>
		</table>





		<center>
			<input type="submit" id="sub" value="確定" class="btn btn-primary" />&nbsp;&nbsp;&nbsp; <input type="reset" id="reset" value="重置" class="btn btn-primary" />&nbsp;&nbsp;&nbsp; <input
				type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary" />
		</center>
	</form>



	<script type="text/javascript">
		jq(function() {
			var demo = jq("#form").Validform({
				btnSubmit : "#sub",
				tiptype : 4,
				showAllError : true,
				ignoreHidden : true,
				tipSweep : true,
				ajaxPost : true,
				callback : function(data) {					
					if (data == "0") {
						layer.msg("提交成功!", 3, 1);
					} 
					if(data=="1") {
						layer.msg("提交失敗", 3, 3);
					}
					if(data=="2"){
						layer.msg("數據已經存在",3,3);
					}
					if(data=="3"){
						layer.msg("請先輸入所選日期的前天數據",3,3);
					}
					
				},
				datatype : {
					"*0-6" : /^-?\d{1,9}(\.[0-9]{1,3})?$/,
					"*1-6" : /^[1-9]{1}\d{0,8}(\.[0-9]{1,3})?$/,
					"*0-7" : /^-?\d{1,7}(\.[0-9]{1})?$/,
					"*0-2" : /^[0-2]{1}(\.[0-9]{1})?$/

				}
			});
			demo.tipmsg.w["*0-6"] = "只能數字且不超過9位數,可保留三位以內小數";
			demo.tipmsg.w["*1-6"] = "不為0的數字且不超過9位數,可保留三位以內小數";
			demo.tipmsg.w["*0-2"] = "天數不符合要求";
			demo.tipmsg.w["*0-7"] = "只能數字且不超過7位數,可保留一位以內小數";
			
			

			goTrim();
			showRow();
		});

		function getFactArea(mid) {
			document.getElementById("dwrFactArea").length = 1;
			webfactjs.findFactCodeByFactNo_show_dw(mid, function(x) {
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
			loadUrl("ydata_findPageBean3?backIndex=1");
		}

		function showRow() {
			var workholiday = jq("#workholiday");
			var arrays = jq("input[class*='input_hidden']");
			if (workholiday.val() == 1 || workholiday.val() == 2) {
				for ( var i = 0; i < arrays.length; i++) {
					arrays.eq(i).prop("disabled", true);
					arrays.eq(i).val(null);
					arrays.eq(i).removeAttr("datatype");
					arrays.eq(i).css("background","#e8e8e8");
				}
			} else {
				for ( var i = 0; i < arrays.length; i++) {
					arrays.eq(i).prop("disabled", false);
					arrays.eq(i).attr("datatype", "*,*0-6");
					arrays.eq(i).css("background","");
				}
				jq("input[name='ydata.daycount']").attr("datatype","*0-2");
			}
		}				
	</script>
	<script type='text/javascript' src='dwr/interface/webfactjs.js'></script>	
</body>

</html>
