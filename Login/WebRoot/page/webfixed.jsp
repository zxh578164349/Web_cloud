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
<script type="text/javascript" src="jquery_alert_dialogs/jquery.js"></script>
<script type="text/javascript" src="jquery_alert_dialogs/jquery.alerts.js"></script>	
<link rel="stylesheet" type="text/css" href="jquery_alert_dialogs/jquery.alerts.css" />	
<script type="text/javascript" src="jquery_alert_dialogs/jquery.ui.draggable.js"></script>		
<link rel="stylesheet" type="text/css" href="css/general_css.css" />
<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>

 <!--[if lt IE 9]>  
  <script src="bootstrap/html5.js"></script>
  <script src="bootstrap/respond.min.js"></script>
  <![endif]-->	
</head>
<script>
	var jq=jQuery.noConflict();
	var loadi;
	jq(document).ajaxStart(function(){
		loadi=layer.load(0);
	});
	jq(document).ajaxStop(function(){
		layer.close(loadi);
	});
	function pages(page) {	    		
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "fix_findPageBean3",
			data : "page=" + page,
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("bodyid").html(xhr.responseText);
			}
		});
	}
	function submis() {		
		var fact = document.getElementById("factNo");
		var ym = document.getElementById("year");
		var ym_s=document.getElementById("year_s");
		var lostmk=document.getElementById("lostmk");
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "fix_findPageBean2",
			data : "factNo=" + fact.value + "&yymm=" + ym.value+"&yymm_s="+ym_s.value+"&lostmk="+lostmk.value,
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("#bodyid").html(xhr.responseText);
			}
		});
	}
	//你确定要删除吗？
	function isDelete(mid) {
		jConfirm('你确定这么做吗?', '确认对话框', function(r) {
			if (r == true) {
				document.getElementById("mydiv").style.display = "block";
				location.href = "fix_delete?id=" + mid;
			}
		});
	}


</script>

<body>
	<jsp:include page="publicHead_webfix.jsp" />
	<hr/>
	<input type="button" id="addbtn" value="添加" onclick="javascript:location.href='saveAndUpdate/fixedSaveOrUpdate.jsp'"/>
	<%-- <s:if test='#session.loginUser.userread!="1"'>
		<form action="fix_toExcel" method="post" id="form" target="_blank">
		<table style="width:1050px">
		<tr>
	      <td> 廠別</td>
	      <td>
	         <s:if test="#session.factNo!='tw'">
	           <div id="uboxstyle_webfixToexcel">	                                    	                                      
                  <select name="factNo_print">
						<option value="<s:property value="#attr.factNo"/>">全部</option>
						<option value="<s:property value="#attr.factNo"/>">
							<s:property value="#attr.factName" />
						</option>
					</select>&nbsp;&nbsp;
					</div>	
	         </s:if>
	         <s:if test="#session.factNo=='tw'">
	            <div id="uboxstyle_webfixToexcel">	                                    
	             <select name="factNo_print" >							 							
								<option value="">全部</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>										
								</s:iterator>
						</select>
				</div>		                       
	         </s:if>
	                        
	        <td>進廠日期</td>
	        <td>
	        <input type="text" name="yymm"  onClick="WdatePicker()"  readonly="readonly" class="Wdate" />			
	      </td>
	       <td>驗收日期</td>
	        <td>
	        <input type="text" name="yymm_s"  onClick="WdatePicker()"  readonly="readonly" class="Wdate" />			
	      </td>
	      <td colspan="20"><input type="submit" value="轉換" id="btn_change"/>&nbsp;<span style="color:blue">(轉換Excel格式)</span>
	      &nbsp;<input type="button" id="addbtn" value="添加" onclick="javascript:location.href='saveAndUpdate/fixedSaveOrUpdate.jsp'"/>	      
	      </td>
	    </tr>
	    </table>
	   </form>  
	   </s:if> --%>
		
	<div id="bodyid">
		<jsp:include page="table1/webfixed1.jsp" />
	</div>	
	<div id="mydiv">
		<p>
			<img alt="" src="images/loading004.gif"><br> Loading....
		</p>
	</div>

</body>
</html>
