<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>My JSP 'ydata_show.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
  <div id="container">
	<table class="table table-striped table-hover table-bordered">
		<h3>
		<s:if test='#session.loginUser.userread!="1"'>
	    <input value="添加" type="button" class="btn btn-info"
		onclick="loadUrl('saveAndUpdate/kpifactSaveOrUpdate.jsp')" />
        </s:if>
		<span id="h2_title">KPI年度目標</span>
		</h3>
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>年份</th>
				<th>當月產量(模)</th>				
				<th>月均回轉(回)</th>
				<th>時迴轉(模/H)</th>
				<th>機臺利用率(%)</th>
				<th>產能達成率(%)</th>
				<th>直工人均产能(模/人)</th>
				<th>全厂人均产能(模/人)</th>
				<th>全廠人均時產能(模/H)</th>
				<th>成倉庫存(雙)</th>
				<th>已出未請(雙)</th>
				<th>生產與請款差異率(%)</th>
				<th>銷貨收入(USD)</th>
				<th>主材料成本比率(%)</th>
				<th>人工成本率(%)</th>
				<th>費用成本率(%)</th>
				<th>修繕單耗(USD/模)</th>
				<th>平均單價(USD/雙)</th>
				<th>全廠人均薪資(USD/人)</th>
				<th>人均產值(USD/人)</th>
				<th>人薪產值(USD/人)</th>
				<th>全廠總損耗(%)</th>
				<th>無形損耗(%)</th>
				<th>邊料率(%)</th>
				<th>不良率(%)</th>
				<th>報廢率(%)</th>												
				<th>廠補率(%)</th>
				<th>水用量单耗(噸/模)</th>
				<th>用水金額單耗(USD/模)</th>
				<th>电度数单耗(度/模)</th>
				<th>用電金額單耗(USD/模)</th>
				<th>蒸汽用量單耗(噸/模)</th>
				<th>用汽金額單耗(USD/模)</th>
				<th>回頭料(%)</th>
				<th>油壓退料(%)</th>
				<th>回流率(%)</th>
				<th>藥品用量單耗(g/模)</th>
				<th>色料用量單耗(g/模)</th>
				<th>離型劑金額單耗(USD/模)</th>
				<th>直間比</th>
				<th>直工離職率(%)</th>
				<th>全廠離職率(%)</th>
				<th>工傷件數(件)</th>
												
				<s:if test='#session.loginUser.userread!="1"'>
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
			<tr>
				<td>${bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="id.factNo" /></td>
				<td><s:property value="id.factCode" /></td>
				<td><s:property value="id.yyyy" /></td>
				<td><s:property value="%{formatDouble(thisYield)}" /></td>
				<td><s:property value="%{formatDouble(avgCircle)}" /></td>
				<td><s:property value="%{formatDouble(avgCirclehour)}" /></td>
				<td><s:property value="%{formatPercent(mutiRate)}"/></td>
				<td><s:property value="%{formatPercent(productRate)}"/></td>
				<td><s:property value="%{formatDouble(avgZgpro)}" /></td>
				<td><s:property value="%{formatDouble(avgPerpro)}" /></td>
				<td><s:property value="%{formatDouble(avgFactpro)}" /></td>				
				<td><s:property value="%{formatDouble(storeNum)}" /></td>				
				<td><s:property value="%{formatDouble(outRequest)}" /></td>				
				<td><s:property value="%{formatPercent(outrequestRate)}" /></td>				
				<td><s:property value="%{formatDouble(slIncome)}" /></td>				
				<td><s:property value="%{formatPercent(mainRate)}" /></td>				
				<td><s:property value="%{formatPercent(pcostRate)}" /></td>				
				<td><s:property value="%{formatPercent(ccostRate)}" /></td>				
				<td><s:property value="%{formatDouble_4(wasteUsd)}" /></td>				
				<td><s:property value="%{formatDouble_4(perPrice)}" /></td>				
				<td><s:property value="%{formatDouble_4(perSalar)}" /></td>				
				<td><s:property value="%{formatDouble(avgPermoney)}" /></td>
				<td><s:property value="%{formatDouble(permoney)}" /></td>				
				<td><s:property value="%{formatDouble(wasteFact)}" /></td>				
				<td><s:property value="%{formatDouble(wasteNo)}" /></td>				
				<td><s:property value="%{formatPercent(sideRate)}" /></td>				
				<td><s:property value="%{formatPercent(uhealRate)}" /></td>				
				<td><s:property value="%{formatPercent(wasteRate)}" /></td>				
				<td><s:property value="%{formatPercent(factaddRate)}" /></td>				
				<td><s:property value="%{formatDouble_4(waterTon)}"/></td>
				<td><s:property value="%{formatDouble_4(waterUsd)}" /></td>
				<td><s:property value="%{formatDouble_4(lightDu)}" /></td>
				<td><s:property value="%{formatDouble_4(lightUsd)}" /></td>				
				<td><s:property value="%{formatDouble_4(gasTon)}" /></td>				
				<td><s:property value="%{formatDouble_4(gasUsd)}" /></td>				
				<td><s:property value="%{formatPercent(bheadRate)}" /></td>				
				<td><s:property value="%{formatPercent(bpreRate)}" /></td>				
				<td><s:property value="%{formatPercent(bflowRate)}" /></td>				
				<td><s:property value="%{formatDouble_4(drugWast)}" /></td>				
				<td><s:property value="%{formatDouble_4(clrWast)}" /></td>				
				<td><s:property value="%{formatDouble_4(leaveUsd)}" /></td>				
				<td><s:property value="%{formatDouble_4(zjRate)}" /></td>				
				<td><s:property value="%{formatPercent(zgleaveRate)}" /></td>				
				<td><s:property value="%{formatPercent(factleaveRate)}" /></td>
				<td><s:property value="%{formatDouble(hurtNum)}" /></td>																								
				<s:if test='#session.loginUser.userread!="1"'>
				<td>
					<form action="kpifact_findById" method="post" id="subform${x.index}">						
						<input type="hidden" value="<s:property value='id.factNo'/>" name="factNo" />
						<input type="hidden" value="<s:property value='id.factCode'/>" name="factCode" />							
						<input type="hidden" value="<s:property value='id.yyyy'/>" name="yyyy" />							
					</form> 
					<a href="javascript:findById_form('subform${x.index}','kpifact_findById')"					
					onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>

					<form action="kpifact_delete" method="post" id="2subform${x.index}" style="float:left">						
						<input type="hidden" value="<s:property value='id.factNo'/>" name="factNo" />
						<input type="hidden" value="<s:property value='id.factCode'/>" name="factCode" />							
						<input type="hidden" value="<s:property value='id.yyyy'/>" name="yyyy" />							
					</form> 
					<a href="javascript:void(0)"
					onclick="isDelete('2subform${x.index}','kpifact_delete')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
					
					<form action="kpifact_findById_copy" method="post" id="3subform${x.index}" style="float:left">						
						<input type="hidden" value="<s:property value='id.factNo'/>" name="factNo" />
						<input type="hidden" value="<s:property value='id.factCode'/>" name="factCode" />							
						<input type="hidden" value="<s:property value='id.yyyy'/>" name="yyyy" />							
					</form> 
					<!-- <a href="javascript:findById('3subform${x.index}','kpifact_findById_copy')"					
					onclick=""><img alt="複製" src="images/icon/copy.png" title="複製" ></a> -->
				</td> 
				</s:if>
			</tr>
		</s:iterator>
		</tbody>
	</table>
 </div>
	<jsp:include page="pagenation.jsp" flush="true"/>
	
</body>


</html>
