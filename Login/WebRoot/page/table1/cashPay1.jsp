<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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

<title>My JSP 'ypre_show1.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="jquerys/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<script type="text/javascript">
	$(function() {
		var j = jQuery.noConflict();
		var demo = j("#form").Validform({
			tiptype : 1,
		//showAllError : true,
		});
	});
	var defaultColor="#97CBFF";
	 var clickColor="#CCFFFF";
	 function click_color(obj){
        var tbody=document.getElementById("tbody");
        var length=document.getElementById("tbody").rows.length;
        for(var i=0;i<length;i++){
            tbody.rows[i].style.backgroundColor=defaultColor;
        }
        obj.style.backgroundColor=clickColor;        
    }
     function move(obj){
     obj.style.backgroundColor=defaultColor;
    }
</script>

</head>

<body>
  <div id="container">
  <div id="content">
	<table id="tb" bgColor=#97CBFF border=1
		style="margin:5px 0px 25px 0px;border-collapse:collapse;">
		<caption>產量預估</caption>
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>所屬部門</th>
				<th>代號</th>
				<th>借貸</th>
				<th>科目代號</th>
				<th>科目名稱</th>
				<th>憑證代號</th>
				<th>流水號</th>
				<th>支付金額</th>
				<th>支付稅額</th>
				<th>合計金額</th>
				<th>摘要說明</th>
				<th>A欄</th>
				<th>B欄</th>
				<th>C欄</th>
				<th>D欄</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
			<tr onmousemove="click_color(this)" onmouseout="move(this)">
				<td>${ 12*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="id.factNo" />
				</td>
				<td><s:property value="id.factCode" />
				</td>
				<td><s:date name="id.yymm" format="yyyyMM" />
				</td>
				<td><s:property value="%{formatDouble(forceAnalysis)}" />
				</td>
				<td><s:property value="%{formatDouble(expectedProduction)}" />
				</td>
				<td><s:property value="%{formatDouble(expectedPayment)}" />
				</td>
				<td><s:property value="%{formatDouble(hole)}" />
				</td>
				<td><s:property value="%{formatDouble(positiveNumber)}" />
				</td>
				<td><s:property value="%{formatDouble(sample)}" />
				</td>
				<td><s:property value="%{formatDouble(accessories)}" />
				</td>
				<td><s:property value="%{formatDouble(other)}" />
				</td>
				<td><s:property value="username" />
				</td>
				<td>
					<form action="ypre_findById" method="post" id="subform${x.index}">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymm'/>"
							name="id.yymm" />
					</form> <a
					href="javascript:document.getElementById('subform${x.index}').submit()"
					onclick="">修改</a> &nbsp;
					<form action="ypre_delete" method="post" id="2subform${x.index}"
						style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymm'/>"
							name="id.yymm" />
					</form> <a href="javascript:void(0)"
					onclick="isDelete('2subform${x.index}')">刪除</a></td>
			</tr>
		</s:iterator>
		<tr>
		 <td colspan="14">
		   <div style="width:500px">
		   	<form action="ypre_findNulFact" method="post" id="form">
		  <table>
			<tr>
				<td class="tr_show"><input type="text" name="yymm"
					onClick="WdatePicker()" readonly="readonly" class="Wdate"
					datatype="*" /></td>
				<td><input type="submit" value="查找" id="addbtn"/>&nbsp;<span
					style="color:blue">(查找每月漏輸數據廠別)</span>
				</td>
			</tr>
		</table>
	</form>
	</div>
		 </td>
		</tr>
		</tbody>
	</table>
	</div>
</div>	

	<hr />
	<center>
	<span><a href="javascript:pages(0)">首頁</a>&nbsp;<a
		href="javascript:pages(<s:property value="bean.currentPage"/>-1)">上一頁</a><span><s:property
				value="bean.currentPage" />/<s:property value="bean.totalPage" />
	</span><a href="javascript:pages(<s:property value="bean.currentPage"/>+1)">下一頁</a>&nbsp;<a
		href="javascript:pages(<s:property value="bean.totalPage"/>)">尾頁</a> </span>
		</center>
</body>

</html>
