<%@ page import="dao.IKyFactDao"%>
<%@ page
	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.hibernate.Session"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>顯示窗口</title>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<LINK href="css/list.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/form.css" />
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery_alert_dialogs/jquery.js"></script>
<script type="text/javascript" src="jquery_alert_dialogs/jquery.alerts.js"></script>	
<link rel="stylesheet" type="text/css" href="jquery_alert_dialogs/jquery.alerts.css" />	
<script type="text/javascript" src="jquery_alert_dialogs/jquery.ui.draggable.js"></script>	
<link rel="stylesheet" type="text/css" href="jquery/loding/ui.loading.css" />
<script type="text/javascript" src="jquery/jquery.messager.js"></script>
<script type="text/javascript" src="jquery/loding/ui.loading.js"></script>
	
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">
<script type="text/javascript">
	var jq=jQuery.noConflict();
	function change() {
		var myBiao = document.getElementById("biao");
		var myCang = document.getElementById("cang");
		var myYear = document.getElementById("year");
		if (myBiao.value == "0") {
			myCang.disabled = "disabled";
			myYear.disabled = "disabled";
			jq("#content")
					.html(
							'<span style="padding-left:280px;">请根据上面条件查询你想要的数据！</span>');
		} else {
			/* jQuery(function(jq) {
				jq(document).ui_loading({
					overlay : true,
					opacity : 0.2,
					supportIframe : true,
					message : '請稍後!正在加載中..'
				});
			});
			if (myBiao.value == "") {
				location.reload(location.href);
			} else {
				jq.ajax({
					type : "POST",
					dataType : "Html",
					url : myBiao.value,
					//data : jq("#showForm").serialize(),
					success : function(msg) {
						jq("#content").html(msg);
						jQuery(function(jq) {
							jq(document).ui_loading({
								overlay : true,
								opacity : 0.2,
								supportIframe : false,
								message : ''
							});
						});
					},
					error : function(xhr) {
						alert(xhr.responseText);
					}
				});
			} */
			myCang.disabled = "";
			myYear.disabled = "";
		}
	}

	function submis() {
		jQuery(function(jq) {
			jq(document).ui_loading({
				overlay : true,
				opacity : 0.2,
				supportIframe : true,
				message : '請稍後!正在加載中..'
			});
		});
		var myBiao = document.getElementById("biao");
		var cang = document.getElementById("cang");
		var year = document.getElementById("year");
		
		if (myBiao.value==0&&cang.value == "nothing" && year.value == "") {
			location.reload(location.href);
		} else {
			jq.ajax({
				type : "POST",
				dataType : "Html",
				url : myBiao.value,
				//data : jq("#showForm").serialize(),
				data:"factNo="+cang.value+"& yymm="+year.value,
				success : function(msg) {
					jq("#content").html(msg);
					jQuery(function(jq) {
						jq(document).ui_loading({
							overlay : true,
							opacity : 0.2,
							supportIframe : false,
							message : ''
						});
					});
				},
				error : function(xhr) {
					alert(xhr.responseText);
				}
			});
		}		
	}
	
function showBills(){
   jq("#divBills").show(300);
}
function hideBills(){
   jq("#divBills").hide(300);
}
function hideBills2(){
   jq("#divBills2").hide(300);
}
function findKyVisaBills_Int(){
   kyvisabillsjs.findKyVisaBills_Int(function(x){
       if(x>0){
          //alert("你好，目前還有"+x+"封函文沒有審核!");
          document.getElementById("visabills_int").innerText="你好，目前有"+x+"封函文需要審核!";
          jq("#divBills2").show(300);
       }else{
          document.getElementById("visabills_int").innerText="你好，目前暂无函文需要審核!";
          jq("#divBills2").show(300);
       }
   })
}
setTimeout("showBills(),findKyVisaBills_Int()",1000);
setTimeout("hideBills()",15000);
setTimeout("hideBills2()",18000);	
</script>
<script type='text/javascript' src='/Login/dwr/interface/kyvisabillsjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>
<style>
#content{
  width:100%;
  height:600px;
  overflow-y:auto;
}
#content table {
	width: 100%;
}
</style>
</head>
<body>
	<div id="top">		
			<%-- <table border="0">
				<thead>
					<tr>
						<td >表名 </td>
						<td><div id="uboxstyle_table">
						   <select onchange="change()" name="" id="biao">							
								<option value="0">請選擇</option>
								<option value="machine_getList2">孔位數回轉數</option>
								<option value="count_getList2">損益匯總</option>
								<option value="profit_getList2">損益表</option>
								<option value="inv_getList2">生產與請款</option>
								<option value="avprice_getList2">平均粗胚單價</option>
								<option value="outback_getList2">出貨與退貨</option>
								<option value="mastore_getList2">全廠原料庫存</option>
								<option value="backcp_getList2">回頭料</option>
								<option value="person_getList2">人員效能</option>
								<option value="gw_getList2">關務損失分析表</option>
								<option value="backmat_getList2">回收粉使用統計</option>
								<option value="side_getList2">邊料不良重量分析</option>
								<option value="cash_getList2">水電油</option>
								<option value="mat_getList2">色料藥品耗用</option>
								<option value="mat2_getList2">離型劑耗用</option>
								<option value="store_getList2">成品庫存</option>
								<option value="ypre_findPageBean2_print">產量預估</option>
								<option value="ydata_findPageBean2_print">產量資料(按日查看)</option>
								<option value="ydata_findPageBean2_print_formonth">產量資料(按月查看)</option>
								<option value="fix_findPageBean2_print">固定資產</option>
								<option value="webmixPerson_getList2">人數工時(每月輸入)</option>
								<option value="webmix2_getList2">營收損益(每月輸入)</option>
								<option value="webScrapt_getList2">廢品邊料(每月輸入)</option>
								<option value="webProduted_getList2">成品資料(每月輸入)</option>
								<option value="webwlo_getList2">水電油(每月輸入)</option>
								<option value="webmix1_findPageBean2_print">生產出貨(每日輸入)</option>
								<option value="webcost_findPageBean2_print">資材資料(每月輸入)</option>
								<option value="webestpro_findPageBean2_print">預計生產(每月輸入)</option>
								<option value="webbackfeed_findPageBean2_print">回頭料(每月輸入)</option>
								<option value="webpersonnum_findPageBean2_print">人員考勤(每日輸入)</option>
						</select>
						</div>
						</td>
						<td >廠別</td>
						<td>
						 <s:if test="#attr.factNo=='tw'">
						        <div id="uboxstyle">
								<select name="factNo" id="cang" disabled="disabled">
									<option value="nothing">請選擇</option>
									<option value="tw">查看所有(不帶條件)</option>
									<s:iterator value="#attr.facts" id="temp">
										<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>											
									</s:iterator>
								</select>
								</div>
							</s:if> <s:else>
							    <div id="uboxstyle">
								<select id="cang" disabled="disabled" name="factNo">
									<option value="nothing">請選擇</option>
									<option value="<s:property value='#attr.factNo'/>">
										<s:property value="#attr.factName" />(<s:property value='#attr.factNo'/>)
									</option>
								</select>
								</div>
							</s:else></td>
						<td >年月</td>
						<td>
						<input name="yymm"
							disabled="disabled" type="text" id="year" onClick="WdatePicker()"
							readonly /></td>
						<td>
						<input type="image" onclick="submis()"  src="images/search002.gif" /> 
						</td>
					</tr>
				</thead>
			</table> 
		
		<hr style="border:1px solid #ccc"/>
		<div id="content">
			<span style="padding-left:280px;">请根据上面条件查询你想要的数据！</span>
		</div>--%>
				
		<div style="display:none;width:500px;height:400px;" id="divBills">
		    <img src="images/lamp.jpg"/>
		    <font color="blue" style="font-size:20px">你好,部分用戶原有的([資料輸入]-[生產出貨])已刪除,並轉移到([資料輸入]-[產量資料]),不便之處,請詳解</font>
		    <hr>
		    <img src="images/lamp.jpg"/>
		    <font color="red" style="font-size:20px">你好,部分用戶原有的([資料輸入]-[產量預估])已刪除,並轉移到([資料輸入]-[預計生產]),不便之處,請詳解</font>
		</div>
		<div style="display:none;width:500px;height:500px;" id="divBills2" >
		  <img src="images/lamp.jpg"/>
		   <font color="red" style="font-size:20px" id="visabills_int"></font>
		</div>
				  
	</div>



</body>
</html>
