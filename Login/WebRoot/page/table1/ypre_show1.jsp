<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
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
<link rel="stylesheet" type="text/css" href="css/mystyle.css" />
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
   function showPage(){
	    var j=jQuery.noConflict();
	    j("#divpage").toggle(200,function(){
	        if(document.getElementById("a_page").innerHTML=="▽"){
	          document.getElementById("a_page").innerHTML="△";
	        }else{
	           document.getElementById("a_page").innerHTML="▽";
	        }
	    });	   
	}
</script>

</head>

<body>
  <div id="container">
  <div id="content">
	<table id="tb" >
		<caption>產量預估</caption>
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>年月</th>
				<th>戰力分析模數</th>
				<th>預計生產模數</th>
				<th>預計請款雙數</th>
				<th>總機孔</th>
				<th>正批</th>
				<th>工程/樣品</th>
				<th>輔料</th>
				<th>其他</th>
				<th>輸入者</th>
				<s:if test='#session.loginUser.userread!="1"'>
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
			<tr onmousemove="click_color(this)" onmouseout="move(this)">
				<td>${ 15*(bean.currentPage-1)+x.index+1}</td>
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
				<s:if test='#session.loginUser.userread!="1"'>
				<td>
					<form action="ypre_findById" method="post" id="subform${x.index}">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymm'/>"
							name="id.yymm" />
					</form> <a
					href="javascript:document.getElementById('subform${x.index}').submit()"
					onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a> &nbsp;
					<form action="ypre_delete" method="post" id="2subform${x.index}"
						style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymm'/>"
							name="id.yymm" />
					</form> <a href="javascript:void(0)"
					onclick="isDelete('2subform${x.index}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>
					</td>
					</s:if>
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
	<center id="center_page">
	　　<a href="javascript:pages(0)">首頁</a>
	    <a href="javascript:pages(<s:property value='bean.currentPage'/>-1)">上一頁</a>	    
	        (第<s:property value="bean.currentPage" />頁 <a href="javascript:void(0)" onclick="showPage()" id="a_page">▽</a>|共<s:property value="bean.totalPage" />頁)
	           <div id="divpage">
	               <c:forEach begin="1"  end="${bean.totalPage}" var="id">
	                   <a href="javascript:pages(${id })">${id}</a>
	               </c:forEach>
	           </div>	  
	    <a href="javascript:pages(<s:property value='bean.currentPage'/>+1)">下一頁</a>
	    <a href="javascript:pages(<s:property value='bean.totalPage'/>)">尾頁</a>		
	</center>
</body>

</html>
