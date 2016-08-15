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

<body >
 <div id="container">
 <div id="content">
	<table class="table table-striped table-hover table-bordered" >
		<h2>
		<s:if test='#session.loginUser.userread!="1"'>
	    <input value="添加" type="button" class="btn btn-info" onclick="loadUrl('saveAndUpdate/Yield_data.jsp')"/>
	    </s:if>
		產量資料
		</h2>
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>日期</th>
				<th>上模數(模)</th>
				<th>人數(人)</th>
				<th>標準產量(模)</th>
				<th>實際產量(模)</th>
				<th>達成率(%)</th>
				<th>天數(天)</th>				
				<th>實際生產雙數(雙)</th>
				<th>客補生產雙數(雙)</th>
				<th>廠補生產雙數(雙)</th>
				<th>樣品生產雙數(雙)</th>
				<th>出貨數(雙)</th>
				<th>退貨數(雙)</th>
				<th>上模總工時(小時)</th>
				<th>工作日/假日</th>
				<th>輸入者</th>
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
				<td><s:date name="id.yymmdd" format="yyyyMMdd" /></td>
				<td><s:property value="%{formatDouble(onModulus)}" /></td>
				<td><s:property value="%{formatDouble(personnum)}" /></td>
				<td><s:property value="%{formatDouble(standardOutput)}" /></td>
				<td><s:property value="%{formatDouble(actualYield)}" /></td>
				<td><s:property value="%{formatDouble_percent(achievingRate)}" /></td>
				<td><s:property value="%{formatDouble(daycount)}" /></td>
				<td><s:property value="%{formatDouble(actualpairs)}" /></td>
				<td><s:property value="%{formatDouble(hostpairs)}" /></td>
				<td><s:property value="%{formatDouble(factpairs)}" /></td>
				<td><s:property value="%{formatDouble(samplepairs)}" /></td>
				<td><s:property value="%{formatDouble(outnum)}" /></td>
				<td><s:property value="%{formatDouble(backnum)}" /></td>
				<td><s:property value="%{formatDouble(workhours)}" /></td>
				<td><s:if test="workorholiday==1">
						<font color="red">假日</font>
					</s:if> <s:if test="workorholiday==2">
						<font color="green">未排產</font>
					</s:if> <s:if test="workorholiday==0">
				                           工作日
				  </s:if></td>
				<td><s:property value="username" /></td>
				<s:if test='#session.loginUser.userread!="1"'>
				<td>
					<form action="ydata_findById" method="post" id="subform${x.index}">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymmdd'/>"
							name="id.yymmdd" />
					</form> 
					<s:if test='username==#attr.loginUser.username||#attr.loginUser.username=="admin"'>					  
					  <a href="javascript:findById_form('subform${x.index}','ydata_findById')" ><img alt="修改" src="images/icon/edit001.png" title="修改" ></a> 				  	
					</s:if>
					<s:else>
					  <a disabled style="color:grey"><img alt="修改" src="images/icon/edit001_1.png" title="修改" ></a>
					</s:else>				
					<form action="ydata_delete" method="post" id="2subform${x.index}"
						style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymmdd'/>"
							name="id.yymmdd" />
					</form>
					 <s:if test='username==#attr.loginUser.username||#attr.loginUser.username=="admin"'>			
					  <a href="javascript:isDelete('2subform${x.index}','ydata_delete')" ><img alt="刪除" src="images/icon/delete001.png" title="刪除"></a>
					 </s:if>
					 <s:else>
					   <a disabled style="color:grey"><img alt="刪除" src="images/icon/delete001_1.jpg" title="刪除"></a>
					 </s:else> 
				</td>
				</s:if>
			</tr>
		</s:iterator>
		</tbody>
	</table>
    </div>
  </div>
		<jsp:include page="pagenation.jsp" flush="true"/>
	
</body>


</html>
