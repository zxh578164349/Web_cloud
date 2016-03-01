<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'fixedSaveOrUpdate.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/form.css" />
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<LINK href="css/list.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="jquery/DatePicker/my_WdatePicker.js"></script>
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="jquery/layer/layer.min.js"></script>
<script type="text/javascript">
var jq=jQuery.noConflict();
var loadi;
jq(document).ajaxStart(function(){
	loadi=layer.load("正在提交,請稍等...");
});
jq(document).ajaxStop(function(){
	layer.close(loadi);
});
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
					layer.msg("提交成功!",3,1);
					location.href="/Login/fix_findPageBean";
				}
				if(data=="1"){
					alert(data.responseText);
				}
			},
			datatype : {
				"*0-8" : /^\d{0,8}(\.[0-9]{1,2})?$/,
				"*1-6" : /^[1-9]{1}\d{0,8}(\.[0-9]{1,3})?$/,
				"*0-7" : /^\d{0,7}(\.[0-9]{1})?$/,
				"test" : /^\w{0,10}$/
			}
		});
		demo.tipmsg.w["*0-8"] = "只能數字且不超過8位數,可保留兩位以內小數";
		demo.tipmsg.w["*1-6"] = "不為0的數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["*0-7"] = "只能數字且不超過7位數,可保留一位以內小數";
		demo.tipmsg.w["test"] = "字符串";

	});

	function back() {		
			layer.load("正在返回,請稍等...");
			window.location.href = "/Login/fix_findPageBean3?backIndex=1";							
	}

	function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo_show_dw(mid, function(x) {
			//alert(mid);
			dwr.util.addOptions("dwrFactArea", x);
		});

	}
	function getSub(mid) {
		document.getElementById("dwrSub").length = 1;
		if (document.getElementById("dwrMajor").value != "") {
			websubjs.findById(mid, function(x) {
				dwr.util.addOptions("dwrSub", x, "subid", "subname");
			});
		}
	}
	function getMajor() {
		webmajorjs.findAll(function(x) {
			dwr.util.addOptions("dwrMajor", x, "majorid", "majorname");
		});
	}

	function getSub_id(mid) {
		document.getElementById("dwrSub_id").length = 1;
		if (document.getElementById("dwrMajor").value != "") {
			websubjs.findById(mid, function(x) {
				dwr.util.addOptions("dwrSub", x, "subid", "subid");
			});

		}
	}

	function getSubName(id) {
		websubjs.findById2(id, function(x) {
			dwr.util.setValue("subname_hidden", x);
		});
		dwr.util.setValue("subid", id);
	}
	function getMajorName(id) {
		webmajorjs.findById(id, function(x) {
			dwr.util.setValue("majorname_hidden", x);
		});
		dwr.util.setValue("majorid", id);
	}

	function makeSsetscoding() {
		var factno = document.getElementById("dwrFactNo").value;
		var majorid = document.getElementById("dwrMajor").value;
		var yymm = document.getElementById("addtime").value;
		var addscode = document.getElementById("scoding");
		if (factno != "" && majorid != "" && yymm != "" && addscode != null) {
			webfixjs.makeSssetscoding(factno, majorid, yymm, function(x) {
				dwr.util.setValue("scoding", x);

			});
			//dwr.util.setValue("scoding2",addscode.value);

		}
	}

	function goFix() {
		document.getElementById("fid2").value = document.getElementById("fid1").value;
	}

	function goFix2() {
		if (document.getElementById("goclear").checked == true) {
			document.getElementById("fid1").disabled = "";
			document.getElementById("fid1").value = "";
			document.getElementById("fid1").focus();
		} else {
			document.getElementById("fid1").disabled = "disabled";
		}
	}
	
</script>
<script type='text/javascript' src='/Login/dwr/interface/webmajorjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/websubjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webfixjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>
</head>

<body onload="getMajor()">
	<div>
		<form action="fix_addFix" method="post" id="form">
			<table width="100%" align="center" cellspacing="0" cellpadding="0"
				id="msg1">
				　<caption>固定資產</caption>
				<s:if test="fix==null">
				
					<tr>
						<s:if test="#session.factNo!='tw'">
							<td class="td_show_title" width="10%">廠別</td>
							<td class="td_input"><input type="text" style="color:blue"
								name="fix.factNo" value="${factNo}" readonly id="dwrFactNo" />
							</td>
							<td class="td_show_title">廠別狀態</td>
							<td class="td_input"><select name="fix.factcode"
								datatype="*" id="factarea">
									<option value="">請選擇廠別狀態</option>
									<s:iterator value="#session.factAreas_login" id="temp">
										<s:if test="%{fix.factcode==#session.temp}">
											<option value="${temp}" selected>${temp}</option>
										</s:if>
										<s:else>
											<option value="${temp}">${temp}</option>
										</s:else>
									</s:iterator>
							</select>
							<input type="hidden" value="${loginUser.username}" name="fix.username"/> <!--  創建者-->
							</td>
						</s:if>
						<s:if test="#session.factNo=='tw'">
							<td class="td_show_title">廠別</td>
							<td class="td_input"><select style="color:blue"
								name="fix.factNo" datatype="*" id="dwrFactNo"
								onchange="getFactArea(this.value),makeSsetscoding()">
									<option value="">請選擇廠別</option>
									<s:iterator value="#session.facts" id="temp">
										<s:if test="%{fix.factNo==#session.temp[0]}">
											<option value="${temp[0]}" selected>${temp[1]
												}&nbsp;(${temp[0]})</option>
										</s:if>
										<s:else>
											<option value="${temp[0]}">${temp[1]
												}&nbsp;(${temp[0]})</option>
										</s:else>
									</s:iterator>
							</select></td>
							<td class="td_show_title">廠別狀態</td>
							<td class="td_input"><select name="fix.factcode"
								datatype="*" id="dwrFactArea">
									<option value="">請選擇廠別狀態</option>
									<s:if test="fix!=null">
										<option value="<s:property value='fix.factcode'/>" selected>
											<s:property value='fix.factcode' />
										</option>
									</s:if>

							</select>
							<input type="hidden" value="${loginUser.username}" name="fix.username"/> <!--  創建者-->
							</td>
						</s:if>


					</tr>
				</s:if>
				<s:else>
					<tr>
						<td class="td_show_title" width="10%">廠別</td>
						<td><input type="text"
							value="<s:property value='fix.factNo'/>" name="fix.factNo"
							readonly style="color:blue" />
						</td>
						<td class="td_show_title">廠別狀態</td>
						<td><input type="text"
							value="<s:property value='fix.factcode'/>" name="fix.factcode"
							readonly style="color:blue" />
							<input type="hidden" value="${loginUser.username}" name="fix.usernameUd"/> <!--  修改者-->
						</td>
					</tr>
				</s:else>
				<tr>


					<td class="td_show_title">財產編號</td>
					<td class="td_input"><s:if test="fix==null">
							<input type="text" value="" readonly id="scoding"
								name="fix.assetscoding"  style="color:blue" datatype="*"/>
						</s:if> <s:else>
							<input type="text" value="<s:property value='fix.assetscoding'/>"
								name="fix.assetscoding" readonly style="color:blue" />
						</s:else></td>


					<td class="td_show_title">集團財編</td>

					<s:if test="fix==null">
						<td class="td_input"><input type="text"
							value="<s:property value='fix.fixedId'/>" name="fix.fixedId"
							datatype="*" /></td>
					</s:if>
					<s:else>
						<td class="td_input"><input type="text"
							value="<s:property value='fix.fixedId'/>" id="fid1"
							onblur="gofix1()" disabled /> (修改<input type="radio"
							value="yclear" name="goclear" id="goclear" onclick="goFix2()" />&nbsp;
							不修改<input type="radio" value="nclear" name="goclear"
							onclick="goFix2()" checked /><font color="blue">&nbsp;!若修改則清空該輸入框</font>)
							<input type="hidden" value="<s:property value='fix.fixedId'/>"
							id="fid2" name="fix.fixedId" /></td>
					</s:else>
				</tr>

				<tr>


					<td class="td_show_title">進廠時間</td>
					<td class="td_input"><input type="text" id="addtime"
						value="<s:property value='fix.addTime'/>" name="fix.addTime"
						onClick="WdatePicker()" class="Wdate" datatype="*"
						onblur="makeSsetscoding()" /> <input type="hidden"
						id="majorname_hidden" value="<s:property value='fix.majorName'/>"
						name="fix.majorName" readonly /> <input type="hidden"
						id="subname_hidden" value="<s:property value='fix.subName'/>"
						name="fix.subName" readonly /></td>
					<td class="td_show_title">名稱</td>
					<td class="td_input"><input type="text"
						value="<s:property value='fix.assetname'/>" name="fix.assetname"
						datatype="*0-100" /></td>

				</tr>

				<tr>
					<td class="td_show_title">大分類名稱</td>
					<td class="td_input"><select name="fix.majorId"
						onchange="getSub(this.value),getMajorName(this.value),makeSsetscoding()"
						id="dwrMajor" datatype="*">
							<option value="">請選擇</option>
							<s:if test="fix!=null">
								<option value="<s:property value='fix.majorId'/>" selected>
									<s:property value="fix.majorName" />
								</option>
							</s:if>
					</select></td>
					<td class="td_show_title">小分類名稱</td>
					<td class="td_input"><select name="fix.subId" id="dwrSub"
						onchange="getSubName(this.value)" >
							<option value="">請選擇</option>
							<s:if test="fix!=null">
								<option value="<s:property value='fix.subId'/>" selected>
									<s:property value="fix.subName" />
								</option>
							</s:if>
					</select></td>

				</tr>
				<tr>
					<td class="td_show_title">大分類ID</td>
					<td class="td_input"><input type="text" id="majorid"
						value="<s:property value='fix.majorId'/>" style="color:blue"
						readonly /></td>
					<td class="td_show_title">小分類ID</td>
					<td class="td_input"><input type="text" id="subid"
						value="<s:property value='fix.subId'/>" style="color:blue"
						readonly /></td>


				</tr>
				<tr>
					<td class="td_show_title">購入單價</td>
					<td class="td_input"><input type="text"
						value="<s:property value='%{formatDouble2(fix.priceIn)}'/>"
						name="fix.priceIn" datatype="*0-8" /></td>
					<td class="td_show_title">型號</td>
					<td class="td_input"><input type="text"
						value="<s:property value='fix.model'/>" name="fix.model" /></td>

				</tr>
				<tr>
					<td class="td_show_title">品牌</td>
					<td class="td_input"><input type="text"
						value="<s:property value='fix.brand'/>" name="fix.brand" /></td>
					<td class="td_show_title">產地</td>
					<td class="td_input"><input type="text"
						value="<s:property value='fix.manufacturer'/>"
						name="fix.manufacturer" /></td>

				</tr>
				<tr>
					<td class="td_show_title">單位ID</td>
					<td class="td_input"><input type="text"
						value="<s:property value='fix.unitsId'/>" name="fix.unitsId" /></td>
					<td class="td_show_title">單位名稱</td>
					<td class="td_input"><input type="text"
						value="<s:property value='fix.unitsName'/>" name="fix.unitsName" />

					</td>
				</tr>

				<tr>
					<td class="td_show_title">區域</td>
					<td class="td_input"><input type="text"
						value="<s:property value='fix.areaName'/>" name="fix.areaName" />
					</td>

					<td class="td_show_title">增加方式ID</td>
					<td class="td_input"><input type="text"
						value="<s:property value='fix.addwaysId'/>" name="fix.addwaysId" />

					</td>


				</tr>

				<tr>
					<td class="td_show_title">增加方式名稱</td>
					<td class="td_input"><input type="text"
						value="<s:property value='fix.addwaysName'/>"
						name="fix.addwaysName" /></td>
					<td class="td_show_title">折舊計算方式ID</td>
					<td class="td_input"><input type="text"
						value="<s:property value='fix.methodId'/>" name="fix.methodId" />

					</td>


				</tr>
				<tr>
					<td class="td_show_title">折舊計算方式名稱</td>
					<td class="td_input"><input type="text"
						value="<s:property value='fix.methodName'/>" name="fix.methodName" />

					</td>

					<td class="td_show_title">處分</td>
					<td class="td_input"><select name="fix.lostMk" datatype="*">
							<option value="N">請選擇</option>
							<s:if test='fix.lostMk=="N1"'>
								<option value="N1" selected>使用中</option>
							</s:if>
							<s:else>
								<option value="N1">使用中</option>
							</s:else>
							<s:if test='fix.lostMk=="Y"'>
								<option value="Y" selected>報廢</option>
							</s:if>
							<s:else>
								<option value="Y">報廢</option>							
							</s:else>
							<s:if test='fix.lostMk=="N2"'>
								<option value="N2" selected>出售</option>
							</s:if>
							<s:else>
								<option value="N2">出售</option>
							</s:else>
							<s:if test='fix.lostMk=="N3"'>
								<option value="N3" selected>閒置</option>
							</s:if>
							<s:else>
								<option value="N3">閒置</option>
							</s:else>
							
					</select></td>
				</tr>
				
				<tr>
					<td class="td_show_title">幣別</td>
					<td class="td_input"><select name="fix.currency">
　　　　　　　　　　　　　　　<s:if test="fix!=null">
                               <option value="<s:property value='fix.currency'/>"><s:property value='fix.currency'/></option>
                            </s:if> 
                            <option value="">請選擇</option>                           
                            <option value="RMB">RMB</option>
                            <option value="USD">USD</option>
                            <option value="NTD">NTD</option>
                            <option value="HKD">HKD</option>
                            <option value="VND">VND</option>
                         </select></td>

					<td class="td_show_title">存放位置/保管人</td>
					<td class="td_input">
						<input type="text" value="<s:property value='fix.keeper'/>" name="fix.keeper"/></td>
				</tr>
				<tr>
				

					<td class="td_show_title">驗收日期</td>
					<td class="td_input">
						<input type="text" 
						value="<s:property value='fix.checkdate'/>" name="fix.checkdate"
						onClick="WdatePicker()" class="Wdate"/> 
						</td>
						
						<td class="td_show_title">是否可刪除</td>
					<td class="td_input"><select name="fix.delMk" datatype="*">
							<option value="N">請選擇</option>
							<s:if test='fix.delMk=="Y"'>
								<option value="Y" selected>是</option>
							</s:if>
							<s:else>
								<option value="Y">是</option>
							</s:else>
							<s:if test='fix.delMk=="N"'>
								<option value="N" selected>否</option>
							</s:if>
							<s:else>
								<option value="N">否</option>
							</s:else>

					</select> <s:if test="fix==null">
							<input type="hidden" name="isnull" value="isnull" />
							<input type="hidden" name="" datetype="*" value="後台自動生成" disabled />
						</s:if> <s:else>
							<input type="hidden" name="isnull" value="isnotnull" />
							<input type="hidden" id="factNo" style="color:blue"
								value="<s:property value='fix.fixedassetsId'/>"
								name="fix.fixedassetsId" />
						</s:else></td>
				</tr>
				
				


				<%-- 	<tr>					   					
						 <s:if test="fix==null">
					   <td class="td_show_title">財編序號</td>
						<td class="td_input">
								<input type="text"  name=""  datetype="*" value="後台自動生成" disabled/> 
								<input type="hidden" name="isnull" value="isnull"/>		
						</td>
					</s:if>
					<s:else>
					     <td class="td_show_title">財編序號</td>
						<td class="td_input"><input
								type="text" id="factNo" style="color:blue"
								value="<s:property value='fix.fixedassetsId'/>" name="fix.fixedassetsId" readonly/> 
								<input type="hidden" name="isnull" value="isnotnull"/> 
						</td>
					</s:else>								
					</tr> --%>

				<tr>
					<td colspan="6"><center>
							<input type="button" id="sub" value="確定" onclick="goFix()" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp;							
								<input type="button" value="返回" onclick="back()" id="btn_back" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>

						</center>
					</td>
				</tr>


			</table>
		</form>

	</div>


	<%--<div id="mydiv">
		<p>
			<img alt="" src="images/loading004.gif"><br> Loading....
		</p>
	</div>--%>
</body>
</html>
