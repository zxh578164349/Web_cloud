
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'publicHead.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<form id="subform">
		<table id="tb_search">
			<tr>
				<td>
					<div class="panel-group" id="accordion">
						<div class="panel panel-default">
							<div class="heading">
								<table>
									<tr>
										<th>廠別</th>
										<th>類別</th>
										<th>單號</th>
									</tr>
									<tr>
										<td><s:if test="#session.factNo=='tw'">
												<select name="factNo" id="factNo" onchange="getType(this.value)">
													<option value="nothing">請選擇廠別</option>
													<s:iterator value="#session.facts" id="temp">
														<option value="${temp[0] }">${temp[1]}(${temp[0]})</option>
													</s:iterator>
												</select>
											</s:if> <s:else>
												<select name="factNo" id="factNo" onchange="getType(this.value)">
													<option value="nothing">請選擇廠別</option>
													<option value="<s:property value="#session.factNo"/>">
														<s:property value="#session.factName" />
														(
														<s:property value="#session.factNo" />
														)
													</option>
												</select>
											</s:else></td>
										<td><select name="visaSort" id="dwr_kytype" style="color:blue">
												<option value="nothing">請選擇類別</option>
												<%-- <s:iterator value="#session.listkytype">
				              <option value="<s:property value='id.typeSno'/>"><s:property value='typeSname'/></option>
				            </s:iterator> --%>
										</select></td>
										<td><input type="text" name="billNo" value="" id="billNo" /> <input value="搜索" type="button" class="btn btn-primary"
											onclick="javascript:submis()" />&nbsp; <input value="導出" type="button" class="btn btn-primary"
											onclick="javascript:print('subform')" />&nbsp;&nbsp;
											 <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">更多</a>
											</td>
									</tr>
								</table>
							</div>
							<div id="collapseOne" class="panel-collapse collapse">
								<div class="panel-body">
									<table>
										<tr>
											<td><s:if test='#session.loginUser.adminMk!="Y"'>
													<input type="text" id="yymmdd" name="yymmdd"
														onClick="WdatePicker({dateFmt:'yyyyMMdd_HH',minDate:'#F{$dp.$D(\'yymmdd2\',{d:-30});}',maxDate:'#F{$dp.$D(\'yymmdd2\',{d:-2});}'})"
														readonly="readonly" class="Wdate" />
				至
			<input type="text" id="yymmdd2" name="yymmdd2"
														onClick="WdatePicker({dateFmt:'yyyyMMdd_HH',minDate:'#F{$dp.$D(\'yymmdd\',{d:2});}',maxDate:'#F{$dp.$D(\'yymmdd\',{d:30});}'})"
														readonly="readonly" class="Wdate" />
												</s:if> <s:else>
													<input type="text" id="yymmdd" name="yymmdd"
														onClick="WdatePicker({dateFmt:'yyyyMMdd_HH',maxDate:'#F{$dp.$D(\'yymmdd2\',{M:-1});}'})" readonly="readonly" class="Wdate" />
				至
			<input type="text" id="yymmdd2" name="yymmdd2" onClick="WdatePicker({dateFmt:'yyyyMMdd_HH',minDate:'#F{$dp.$D(\'yymmdd\',{M:1});}'})"
														readonly="readonly" class="Wdate" />
												</s:else> <input type="radio" value="Y" name="visaMk" />已審核| <input type="radio" value="N" name="visaMk" checked="checked" />未審核| <input
												type="radio" value="T" name="visaMk" />未通過</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>

