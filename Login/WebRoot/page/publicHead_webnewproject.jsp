<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
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
   <form id="public_form" method="post">
	<table id="tb_search">
		<tr>
			<td>
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="heading">
							<table>
								<tr>
									<th>廠別</th>
									<!-- <th>類別</th> -->
									<th>單號</th>
								</tr>
								<tr>
									<td><s:if test="#session.factNo=='tw'">
											<select name="factNo" id="factNo" >
												<option value="">請選擇廠別</option>
												<s:iterator value="#session.facts" id="temp">
													<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>
												</s:iterator>
											</select>
										</s:if> <s:else>
											<select name="factNo" id="factNo" >
												<option value="nothing">請選擇廠別</option>
												<option value="<s:property value="#session.factNo"/>">
													<s:property value="#session.factName" />
													(
													<s:property value="#session.factNo" />
													)
												</option>
											</select>
										</s:else></td>
										
									<!-- <td><select name="visaSort" id="visaSort">
											<option value="">請選擇類別</option>
									</select></td> -->
									
									<td><input type="text" name="billNo" value="" id="billNo" />&nbsp; 
									<input value="搜索" type="button" class="btn btn-primary"
										onclick="javascript:submis()" />&nbsp;&nbsp;
										 <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">更多</a></td>
								</tr>
							</table>
							<div id="collapseOne" class="panel-collapse collapse">
								<div class="panel-body">
									<table>
										<tr>
											<td><input type="text" id="yymmdd" name="createDateA" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" readonly="readonly" class="Wdate" />至 <input
												type="text" id="yymmdd2" name="createDateB" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" readonly="readonly" class="Wdate" /></td>
										</tr>
										<tr><th>名稱</th></tr>
										<tr>										   
										   <td>	
										     <div class="panel panel-primary">									      
										          <input type="text" name="pname" placeholder="名稱" id="p_title" />										          										          
										     </div>     								      
										   </td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div></td>
		</tr>
	</table>
</form>
	<script type='text/javascript' src='dwr/interface/webtypejs.js'></script>
	<script type="text/javascript">
		function getType(factNo) {
			document.getElementById("visaSort").length = 1;
			webtypejs.findByFactNo(factNo,
					function(x) {
						if (x.length > 0) {
							dwr.util.addOptions("visaSort", x, "webtypeMk",
									"typeName");
						}

					});
		}
	</script>
</body>
</html>

