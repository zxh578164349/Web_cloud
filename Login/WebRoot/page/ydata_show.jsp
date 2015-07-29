<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'kongweishu.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<link rel="stylesheet" type="text/css" href="css/general_css.css" />
<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
</head>
<script>
	var jq=jQuery.noConflict();
	function pages(page) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "ydata_findPageBean3",
			data : "page=" + page,
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	function submis() {
		var loadi=layer.load(0);
		var fact = document.getElementById("factNo");
		var ym = document.getElementById("year");
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "ydata_findPageBean2",
			data : "factNo=" + fact.value + "&yymm=" + ym.value,
			success : function(msg) {
			    layer.close(loadi);
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	//你确定要删除吗？
	function isDelete(mid) {
		jConfirm('你确定这么做吗?', '确认对话框', function(r) {
			if (r == true) {
				/* window.location.href = "backmat_delete?billNo=" + mid; */
				document.getElementById(mid).submit();
			}
		});
	}
	function gosubmit(){
	  document.getElementById('mydiv').style.display = 'block';
	  document.getElementById("emailform").submit();
	}
	
function showDiv(){
    jq.layer({
    type: 1,   //0-4的选择,
    title: '每月資料盤點',
    //title:false,
    //border: [0],
    closeBtn: [1,true],
    shade: [0],
    shadeClose: false,
     border: [10, 0.3, '#000'],
   // btns:1,
    fadeIn:300,    
    //shift:'top',
    //move:'.testdiv',
    moveOut:false,
    moveType:1,
    offset:['10px','200px'],
    area: ['650px', '250px'],
    page:{
      url:'page/sum_yiele_data.jsp'   
    }
            
});
    }
  function confirm_show(){
       var flag=confirm("注意!添加盤點資料後，該月所有產量資料不可修改和刪除.\r請確保數據正確完整後，再添加盤點資料.\r確定要添加嗎？");
       if(flag==true){
          this.showDiv();
       }
    }    	
</script>

<body>
	<jsp:include page="publicHead.jsp" flush="true" />
	<hr />
	<s:if test='#session.loginUser.userread!="1"'>
	<input value="添加" type="button" id="addbtn" onclick="javascript:location.href='saveAndUpdate/Yield_data.jsp'" />
	</s:if>
	<br>
	<form action="ydata_print" method="post" target="_blank">
	<table  border="0px">
		<tr>
			<td>廠別</td>
			<td><s:if test="#attr.factNo=='tw'">
			    <div id="uboxstyle">
					<select name="factNo" id="factNo">
						<option value="nothing">請選擇</option>						
						<s:iterator value="#attr.facts" id="temp">
							<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>								
						</s:iterator>
					</select>
					</div>
				</s:if> 
				<s:else>
				  <div id="uboxstyle">
					<select name="factNo" id="factNo">
						<option value="<s:property value="#attr.factNo"/>">
							<s:property value="#attr.factName" />(<s:property value="#attr.factNo"/>)
						</option>
					</select>
					</div>
				</s:else></td>
			<td>開始日期</td>
			<td><input type="text" id="year" name="sdate" 
				onClick="WdatePicker({dateFmt:'yyyyMMdd'})" readonly="readonly" /></td>
			<td>結束日期</td>
			<td><input type="text" id="year_s" name="edate" 
				onClick="WdatePicker({dateFmt:'yyyyMMdd'})" readonly="readonly" /></td>	
			<td>
			 <input value="導出Excel" type="submit" id="search_forday" />			
			</td>
		</tr>
	</table>
	</form>
	<br>
	<s:if test='#session.loginUser.userread!="1"'>
	<span style="float:right">
	  <img alt="" src="images/136.gif">
	  <a href="javascript:showDiv()" style="color:blue;text-decoration:underline;padding-right:30px">
	         添加每月盤點數據</a>
	</span>
	</s:if>
	<span style="float:right"> <img alt="" src="images/136.gif"><a
		href="javascript:window.location.href='sumwebydata_findPageBean'" 
		style="color:blue;text-decoration:underline;padding-right:30px">查看盤點數據</a>
	</span>			
	<span style="float:right"> <img alt="" src="images/136.gif"><a
		href="javascript:window.location.href='ydata_findPageBeanForMonth'" 
		style="color:blue;text-decoration:underline;padding-right:30px">按月合計查看</a>
	</span>	
	<span style="float:right"> <img alt="" src="images/136.gif"><a
		href="ydata_go_temp"
		style="color:blue;text-decoration:underline;padding-right:30px">點擊查找每天漏輸數據廠別</a>
	</span>
	<s:if test='#attr.loginUser.username=="admin"'>
	<form method="post" action="timer_print_manual" id="emailform">
	<span style="float:left"> 	
	<input type="text" name="yymm" onClick="WdatePicker()" readonly="readonly" class="Wdate"/>
		<input type="button" value="發送產量匯總郵件" onclick="gosubmit()"/>	
	</span>
	</form>	
	</s:if>	
	<div id="bodyid">
		<jsp:include page="table1/ydata_show1.jsp" />
	</div>
	<div id="mydiv">
		<p>
			<img alt="" src="images/loading004.gif"><br> Loading....
		</p>
	</div>
</body>
</html>
