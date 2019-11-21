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
	<form action="webobjb_add" method="post" id="form">
		<table class="table table-condensed">

			<s:if test="obj==null">
				<s:if test="#session.factNo!='tw'">
					<tr>
						<td >廠別</td>
						<td class="td_input"><input type="text" style="color:blue" name="obj.id.webFact.id.factNo" value="${factNo}" readonly id="dwr_factno" />
						<span id="error1"></span>
						</td>
						<td >廠別狀態</td>
						<td class="td_input"><select name="obj.id.webFact.id.factArea" datatype="*" id="dwrFactArea" >
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
						<td >廠別</td>
						<td class="td_input"><select style="color:blue" name="obj.id.webFact.id.factNo" datatype="*" onchange="getFactArea(this.value)"
							id="dwr_factno">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1] }&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select><span id="error1"></span>
						</td>
						<td >廠別狀態</td>
						<td class="td_input"><select name="obj.id.webFact.id.factArea" datatype="*" id="dwrFactArea" >
								<option value="">請選擇廠別狀態</option>

						</select><span id="error2"></span>
						</td>
					</tr>
				</s:if>
				<tr>
					<td >日期</td>
					<td class="td_input">
					<input type="text" name="obj.id.yymmdd" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate" datatype="*" id="dwr_yymmdd"  /><span id="error3"></span>
					</td>
					<td class="success">工作日/假日</td>
					<td class="td_input">
					 <select name="obj.workorholiday" datatype='*' onchange="showRow()">
					     <option value="">請選擇</option>
					     <option value=0>工作日</option>
					     <option value=1>假日</option>
					     <option value=2>未排產</option>							
					 </select>					
					  <input type="hidden" value="isnull" name="isnull" />
					  <input type="hidden" value="<s:property value='#session.loginUser.username'/>" name="obj.username" />
					  <input type="hidden" value="<%=str_date%>" name="obj.datecreate" /> <!-- 創建時間 -->	
					</td>

				</tr>
			</s:if>

			<s:if test="obj!=null">
				<tr>
					<td >廠別</td>
					<td class="td_input"><font color="blue"><input type="text" id="factNo" style="color:blue"
							value="<s:property value='obj.id.webFact.id.factNo'/>" name="obj.id.webFact.id.factNo" readonly /> </font>
					</td>
					<td >廠別狀態</td>
					<td class="td_input"><font color="blue"><input type="text" id="billNo" style="color:blue"
							value="<s:property value='obj.id.webFact.id.factArea'/>" name="obj.id.webFact.id.factArea" readonly /> </font>
							 <input type="hidden" value="${loginUser.username}" name="obj.usernameUd" /> <!--  修改者--></td>
				</tr>
				<tr>
					<td >日期</td>
					<td class="td_input">
					  <input type="text" style="color:blue" id="yymmdd" value="<s:property value='obj.id.yymmdd'/>" name="obj.id.yymmdd" readonly />
					</td>
					<td class="success">工作日/假日</td>
					<td class="td_input">
					    <s:if test="obj.workorholiday==0"><font color="blue"><input type="text" value="工作日" readonly/></font></s:if>
					    <s:if test="obj.workorholiday==1"><font color="blue"><input type="text" value="假日" readonly/></font></s:if>	
					    <s:if test="obj.workorholiday==2"><font color="blue"><input type="text" value="未排產" readonly/></font></s:if>
					    <input type="hidden" name="obj.workorholiday" value="<s:property value='obj.workorholiday'/>"/>
					    <input type="hidden" value="<s:property value='obj.username'/>" name="obj.username" />
					    <input type="hidden" value="<s:property value='#session.loginUser.username'/>" name="obj.usernameUd" />					   
					    <input type="hidden" value="<s:property value='obj.datecreate'/>" name="obj.datecreate" />
					    <input type="hidden" value="<%=str_date%>" name="obj.datecreateUd" /> 					    											
					</td>
				</tr>

			</s:if>


			<tr>
				<td>上模數</td>
				<td >
				<input type="text" class="input_hidden" name="obj.onModulus" datatype="9_1" value="<s:property value='obj.onModulus' />"></td>
				<td class="success">人數(拉模手)</td>
				<td class="td_input">
				<input type="text" class="input_hidden" name="obj.personnum" datatype="9_1" value="<s:property value='obj.personnum' />">
				</td>
			</tr>
			<tr>
				<td class="success">標準產量</td>
				<td class="td_input">
				<input type="text" class="input_hidden" name="obj.standardOutput" datatype="9_1" value="<s:property value='obj.standardOutput' />"></td>
				<td class="success">實際產量</td>
				<td class="td_input">
				<input type="text" class="input_hidden" name="obj.actualYield" datatype="9_1" value="<s:property value='obj.actualYield' />">
				</td>
			</tr>
			<tr>
				<td class="success">正批生產雙數</td>
				<td class="td_input"><input type="text" class="input_hidden" name="obj.zpObja" datatype="9_1"
					value="<s:property value='obj.zpObja'/>"></td>
				<td class="success">客補生產雙數</td>
				<td class="td_input"><input type="text" class="input_hidden" name="obj.hostpairs" datatype="9_1"
					value="<s:property value='obj.hostpairs' />"></td>

			</tr>
			<tr>
				<td class="success">廠補生產雙數</td>
				<td class="td_input">
				<input type="text" class="input_hidden" name="obj.factpairs" datatype="9_1" value="<s:property value='obj.factpairs' />">
				</td>
				<td class="success">樣品生產雙數</td>
				<td class="td_input">
				<input type="text" class="input_hidden" name="obj.samplepairs" datatype="9_1" value="<s:property value='obj.samplepairs' />"></td>

			</tr>
			<tr>
				<td class="success">出貨數</td>
				<td class="td_input"><input type="text" class="input_hidden" name="obj.outnum" datatype="9_1"
					value="<s:property value='obj.outnum' />"></td>
				<td class="success">退貨數</td>
				<td class="td_input"><input type="text" class="input_hidden" name="obj.backnum" datatype="9_1"
					value="<s:property value='obj.backnum' />"></td>
			</tr>

			<tr>
				<td class="success">上模總工時</td>
				<td class="td_input">
				<input type="text" class="input_hidden" name="obj.workhours" datatype="9_1" value="<s:property value='obj.workhours' />"></td>
				<td class="success">天數</td>
				<td class="td_input">
				<input type="text" class="input_hidden" name="obj.daycount" datatype="9_1" value="<s:property value='obj.daycount' />"></td>

			</tr>
			<tr>
				<td class="success">成型不良雙數</td>
				<td class="td_input">
				<input type="text" class="input_hidden" name="obj.objA1" datatype="9_1" value="<s:property value='obj.objA1' />"></td>
				<td >慢單狀況(張)</td>
				<td >
				<input type="text" class="input_hidden" name="obj.objA2" datatype="9_1" value="<s:property value='obj.objA2' />"></td>

			</tr>
			<tr>
				<td class="success">慢單狀況(雙)</td>
				<td class="td_input">
				<input type="text" class="input_hidden" name="obj.objA3" datatype="9_1" value="<s:property value='obj.objA3'/>"></td>
				<td >訂單欠數</td>
				<td >
				<input type="text" class="input_hidden" name="obj.objA4" datatype="9_1" value="<s:property value='obj.objA4' />"></td>
			</tr>
			<tr>
				<td >直工人數</td>
				<td >
				<input type="text" class="input_hidden" name="obj.objA5" datatype="n0-10" value="<s:property value='obj.objA5' />"></td>
				<td class="success">間工人數</td>
				<td class="td_input">
				<input type="text" class="input_hidden" name="obj.objA6" datatype="n0-10" value="<s:property value='obj.objA6' />">
				</td>
			</tr>
			<tr>
				<td >招工數</td>
				<td >
				<input type="text" class="input_hidden" name="obj.objA7" datatype="n0-10" value="<s:property value='obj.objA7' />"></td>
				<td >離職數</td>
				<td >
				<input type="text" class="input_hidden" name="obj.objA8" datatype="n0-10" value="<s:property value='obj.objA8' />"></td>
			</tr>
			<tr>
				<td >請假數</td>
				<td >
				<input type="text" class="input_hidden" name="obj.objA9" datatype="n0-10" value="<s:property value='obj.objA9' />"></td>
				<td >品質問題與客訴 </td>
				<td >
				<input type="text" class="input_hidden" name="obj.objA10" datatype="*0-1000" value="<s:property value='obj.objA10' />"></td>
			</tr>
			<tr>
				<td >扣款訊息</td>
				<td >
				<input type="text" class="input_hidden" name="obj.objA11" datatype="*0-1000" value="<s:property value='obj.objA11' />"></td>
				<td >機台狀況/異常</td>
				<td >
				<input type="text" class="input_hidden" name="obj.objA12" datatype="*0-1000" value="<s:property value='obj.objA12' />">					
				</td>
			</tr>



			<tr>
				<td >客人來訪訊息</td>
				<td >
				<input type="text" class="input_hidden" name="obj.objA13" datatype="*0-1000" value="<s:property value='obj.objA13'/>"			
				</td>
				<td >其他提報事項</td>
				<td >
				<input type="text" class="input_hidden" name="obj.objA14" datatype="*0-1000" value="<s:property value='obj.objA14'/>">
				<%-- <input type="text" name="obj.objA14" datatype="*0-1000" value="<fmt:formatNumber value='${obj.objA14 }' pattern='#0.00000'/>"> --%>
				</td>
			</tr>
			
		</table>
		<center>
			<input type="submit" id="sub" value="確定" class="btn btn-primary" />&nbsp;&nbsp;&nbsp; 
			<input type="reset" id="reset" value="重置" class="btn btn-primary" />&nbsp;&nbsp;&nbsp; 
			<input type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary" />
		</center>
	</form>


	<script type="text/javascript">
		jq(function() {
			var demo = jq("#form").Validform({
				btnSubmit : "#sub",
				tiptype : 4,
				showAllError : true,
				tipSweep : true,
				datatype : {
					"9_1" : /^-?\d{0,9}(\.[0-9]{1})?$/,
					"9_2" : /^-?\d{1,9}(\.[0-9]{1,2})?$/,
					"9_4" : /^-?\d{1,9}(\.[0-9]{1,4})?$/,
					"my9_4" : /^-?\d{1,9}(\.[0-9]{4})$/,
					"9_5" : /^-?\d{1,9}(\.[0-9]{1,5})?$/
				},
				ajaxPost : true,
				callback : function(data) {
					if (data == "0") {
						layer.msg("提交成功!", 3, 1);
						//location.href="webobjb_findPageBean";
						loadUrl("webobjb_findPageBean3");
					}
					if (data == "1") {
						//alert(data.responseText);
						layer.msg("提交失敗!", 3, 3);
					}
					if(data=="2"){
					     layer.msg("數據已存在!", 3, 3);
					}
				}
			});
			demo.tipmsg.w["9_1"] = "只能數字且不超過9位數,可保留一位以內小數";
			demo.tipmsg.w["9_2"] = "只能數字且不超過9位數,可保留兩位小數";
			demo.tipmsg.w["9_4"] = "只能數字且不超過9位數,可保留四位小數";
			demo.tipmsg.w["my9_4"] = "只能數字且不超過9位數,和精確到四位小數";
			demo.tipmsg.w["9_5"] = "只能數字且不超過9位數,可保留5位小數";
		});

		function getFactArea(mid) {
			document.getElementById("dwrFactArea").length = 1;
			webfactjs.findFactCodeByFactNo_show_dw(mid, function(x) {
				dwr.util.addOptions("dwrFactArea", x);
			});

		}
		function back() {
			loadUrl("webobjb_findPageBean3?backIndex=1");
		}
		
		function showRow() {
			var workholiday = jq("select[name='obj.workorholiday']");
			var arrays = jq("input[class*='input_hidden']");
			if (workholiday.val() == 1 || workholiday.val() == 2) {
				for ( var i = 0; i < arrays.length; i++) {
					arrays.eq(i).prop("disabled", true);
					arrays.eq(i).val(null);
					//绑定了ignore="ignore"的表单元素，在有输入时，会验证所填数据是否符合datatype所指定数据类型,没有填写内容时则会忽略对它的验证；					
					arrays.eq(i).attr("ignore","ignore");
					arrays.eq(i).css("background","#e8e8e8");
				}
			} else {
				for ( var i = 0; i < arrays.length; i++) {
					arrays.eq(i).prop("disabled", false);
					arrays.eq(i).removeAttr("ignore");
					arrays.eq(i).css("background","");
				}				
			}
		}
				
	</script>
	<script type='text/javascript' src='dwr/interface/webfactjs.js'></script>	
</body>
</html>
