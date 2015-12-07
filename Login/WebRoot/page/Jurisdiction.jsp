<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<title>My JSP 'Jurisdiction.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<LINK href="css/list.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/form.css" />
<script type="text/javascript" src="jquery_alert_dialogs/jquery.js"></script>
<script type="text/javascript" src="jquery_alert_dialogs/jquery.alerts.js"></script>	
<link rel="stylesheet" type="text/css" href="jquery_alert_dialogs/jquery.alerts.css" />	
<script type="text/javascript" src="jquery_alert_dialogs/jquery.ui.draggable.js"></script>	
<!-- <script type="text/javascript" src="jquery/jquery-1.7.2.js"></script> -->
<script type="text/javascript" src="jquery/loding/ui.loading.js"></script>
<link rel="stylesheet" type="text/css" href="jquery/loding/ui.loading.css" />
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>	
</head>
<style>
.td1,caption {
	background-color: #E0E0E0;
}
</style>
<script type="text/javascript">
	window.onload = function() {		
		var i17 = document.getElementById("glyh");
		if (i17 != null) {
			document.getElementById("glyh1").innerHTML = "";
		} else {
			document.getElementById("glyh1").innerHTML = '<input value="用戶管理,管理用戶,usergetuser"  name="checkbox" type="checkbox" />管理用戶';
		}
		var i19 = document.getElementById("clzl");
		if (i19 != null) {
			document.getElementById("clzl1").innerHTML = "";
		} else {
			document.getElementById("clzl1").innerHTML = '<input value="資料輸入,產量資料,ydata_findPageBean" name="checkbox" type="checkbox" />產量資料';
		}

		var i20 = document.getElementById("sjgl");
		if (i20 != null) {
			document.getElementById("sjgl1").innerHTML = "";
		} else {
			document.getElementById("sjgl1").innerHTML = '<input value="數據管理,數據統計,page/StatisticalData.jsp" name="checkbox" type="checkbox" />數據統計';
		}

		var i21 = document.getElementById("cltj");
		if (i21 != null) {
			document.getElementById("cltj1").innerHTML = "";
		} else {
			document.getElementById("cltj1").innerHTML = '<input value="數據管理,各廠產量統計,page/OutputStatistics.jsp" name="checkbox" type="checkbox" />各廠產量統計';
		}

		var i = document.getElementById("xgwdzl");
		if (i != null) {
			document.getElementById("xgwdzl1").innerHTML = "";
		} else {
			document.getElementById("xgwdzl1").innerHTML = '<input value="個人設置,修改我的資料,userinitialUpdate" name="checkbox" type="checkbox" />修改我的資料';
		}

		var i22 = document.getElementById("ckbjip");
		if (i22 != null) {
			document.getElementById("ckbjip1").innerHTML = "";
		} else {
			document.getElementById("ckbjip1").innerHTML = '<input value="個人設置,查看本機Ip,page/selectIp.jsp" name="checkbox"  type="checkbox" />查看本機Ip';
		}
		var i23 = document.getElementById("excel");
		if (i23 != null) {
			document.getElementById("excel1").innerHTML = "";
		} else {
			document.getElementById("excel1").innerHTML = '<input name="checkbox" value="數據管理,EXCEL數據上傳,page/excelUpload.jsp"  type="checkbox" />EXCEL數據上傳';
		}
		
		var i24 = document.getElementById("gdzc");
		if (i24 != null) {
			document.getElementById("gdzc1").innerHTML = "";
		} else {
			document.getElementById("gdzc1").innerHTML = '<input value="資料輸入,固定資產,fix_findPageBean" name="checkbox" type="checkbox" />固定資產';
		}
						
		var i26 = document.getElementById("renshugonsh");
		if (i26 != null) {
			document.getElementById("renshugonsh1").innerHTML = "";
		} else {
			document.getElementById("renshugonsh1").innerHTML = '<input name="checkbox" value="資料輸入,人數工時離職資料,webmixPerson_getList" type="checkbox" />人數工時離職資料';																								
		}
		
		var i27 = document.getElementById("yinshoufeiyong");
		if (i27 != null) {
			document.getElementById("yinshoufeiyong1").innerHTML = "";
		} else {
			document.getElementById("yinshoufeiyong1").innerHTML = '<input name="checkbox" value="資料輸入,營收/費用資料,webmix2_getList" type="checkbox" />營收/費用資料';																								
		}
		
		var i28 = document.getElementById("feipingguanli");
		if (i28 != null) {
			document.getElementById("feipingguanli1").innerHTML = "";
		} else {
			document.getElementById("feipingguanli1").innerHTML = '<input value="資料輸入,廢品管數據,webScrapt_getList" name="checkbox"   type="checkbox" />廢品管數據';																								
		}
		
		var i29 = document.getElementById("chengpingpandian");
		if (i29 != null) {
			document.getElementById("chengpingpandian1").innerHTML = "";
		} else {
			document.getElementById("chengpingpandian1").innerHTML = '<input name="checkbox" value="資料輸入,成品盤點資料,webProduted_getList"  type="checkbox" />成品盤點資料';																								
		}
		
		var i30 = document.getElementById("shuidianyou");
		if (i30 != null) {
			document.getElementById("shuidianyou1").innerHTML = "";
		} else {
			document.getElementById("shuidianyou1").innerHTML = '<input name="checkbox" value="資料輸入,水電油資料,webwlo_getList"  type="checkbox" />水電油資料';																								
		}
		
		var i31 = document.getElementById("zicaichenben");
		if (i31 != null) {
			document.getElementById("zicaichenben1").innerHTML = "";
		} else {
			document.getElementById("zicaichenben1").innerHTML = '<input value="資料輸入,資材資料,webcost_findPageBean" name="checkbox" type="checkbox" />資材資料';																								
		}
		
		var i32 = document.getElementById("yujishch");
		if (i32 != null) {
			document.getElementById("yujishch1").innerHTML = "";
		} else {
			document.getElementById("yujishch1").innerHTML = '<input name="checkbox"  value="資料輸入,預計生產,webestpro_findPageBean"  type="checkbox" />預計生產';																								
		}
		
		var i33 = document.getElementById("huitouliao");
		if (i33 != null) {
			document.getElementById("huitouliao1").innerHTML = "";
		} else {
			document.getElementById("huitouliao1").innerHTML = '<input name="checkbox"  value="資料輸入,回頭料(每月輸入),webbackfeed_findPageBean"  type="checkbox" />回頭料(每月輸入)';																								
		}
		
		var i34 = document.getElementById("renyuankaoqin");
		if (i34 != null) {
			document.getElementById("renyuankaoqin1").innerHTML = "";
		} else {
			document.getElementById("renyuankaoqin1").innerHTML = '<input name="checkbox"  value="資料輸入,人員考勤,webpersonnum_findPageBean"  type="checkbox" />人員考勤';																								
		}
		
		var i35=document.getElementById("yujishengyuqikuanhuizong");
		if(i35!=null){
		   document.getElementById("yujishengyuqikuanhuizong1").innerHTML="";
		}else{
		   document.getElementById("yujishengyuqikuanhuizong1").innerHTML='<input name="checkbox" value="數據管理,預計生產與請款匯總,page/webestproduct_total.jsp" type="checkbox" />預計生產與請款匯總';
									
									
		}
		
		var i36=document.getElementById("shenhe");
		if(i36!=null){
		   document.getElementById("shenhe1").innerHTML="";
		}else{
		   document.getElementById("shenhe1").innerHTML='<input name="checkbox" value="KPI數據,函文申請,kyz_findPageBean" type="checkbox" />函文申請';																		
		}
		
		var i37=document.getElementById("shenheliucheng");
		if(i37!=null){
		   document.getElementById("shenheliucheng1").innerHTML="";
		}else{
		   document.getElementById("shenheliucheng1").innerHTML='<input name="checkbox" value="KPI數據,審核流程,visaflow_findPageBean" type="checkbox" />審核流程';																		
		}
		
		var i38=document.getElementById("hangwenshenhe");
		if(i38!=null){
		    document.getElementById("hangwenshenhe1").innerHTML="";
		}else{
		    document.getElementById("hangwenshenhe1").innerHTML='<input name="checkbox" value="KPI數據,函文審核,vbm_findPageBean" type="checkbox"/>函文審核';
		}
		
		var i39=document.getElementById("feiyongleibie");
		if(i39!=null){
		    document.getElementById("feiyongleibie1").innerHTML="";
		}else{
		    document.getElementById("feiyongleibie1").innerHTML='<input name="checkbox" value="KPI數據,費用組別,kyzsec_findPageBean" type="checkbox"/>費用組別';
		}
		
		var i40=document.getElementById("kuaijikemu");
		if(i40!=null){
		   document.getElementById("kuaijikemu1").innerHTML="";
		}else{
		   document.getElementById("kuaijikemu1").innerHTML='<input name="checkbox" value="KPI數據,會計科目,kyzacct_findPageBean" type="checkbox"/>會計科目';
		}
		
		var i41=document.getElementById("linyongjichu");
		if(i41!=null){
		  document.getElementById("linyongjichu1").innerHTML="";
		}else{
		  document.getElementById("linyongjichu1").innerHTML='<input name="checkbox" value="KPI數據,零用金支出,kyzpetty_findPageBean" type="checkbox"/>零用金支出'; 
		}
				
		var i42 = document.getElementById("fileUpAndDown");
		if (i42 != null) {
			document.getElementById("fileUpAndDown1").innerHTML = "";
		} else {
			document.getElementById("fileUpAndDown1").innerHTML = '<input value="數據管理,文件上傳與下載,filesUpload_findByName" name="checkbox" type="checkbox" />文件上傳與下載';
		}
		
		var i43=document.getElementById("kpi_fact");
		if(i43!=null){
		   document.getElementById("kpi_fact1").innerHTML="";
		}else{
		   document.getElementById("kpi_fact1").innerHTML='<input name="checkbox" value="數據管理,KPI工廠與KPI台灣,page/kpiFactAndTW.jsp"  type="checkbox" />KPI工廠與KPI台灣';																		
		}
		
		var i44=document.getElementById("vwebmachine");
		if(i44!=null){
		   document.getElementById("vwebmachine1").innerHTML="";
		}else{
		   document.getElementById("vwebmachine1").innerHTML='<input name="checkbox" value="數據管理,分形態損益表,page/VWebmachine.jsp"  type="checkbox" />分形態損益表';																		
		}
		
		var i45=document.getElementById("vsumall");
		if(i45!=null){
		   document.getElementById("vsumall1").innerHTML="";
		}else{
		  document.getElementById("vsumall1").innerHTML='<input name="checkbox" value="數據管理,全廠損益表,page/vSumall.jsp" type="checkbox" />全廠損益表';									 									
		}
		
		var i45=document.getElementById("kyzmat");
		if(i45!=null){
		   document.getElementById("kyzmat1").innerHTML="";
		}else{
		  document.getElementById("kyzmat1").innerHTML='<input name="checkbox" value="KPI數據,物料資料,subkyzmat_findPageBean"  type="checkbox"/> 物料資料';
							                                             ;									 									
		}
		
		var i46=document.getElementById("checkinput");
		if(i46!=null){
		   document.getElementById("checkinput1").innerHTML="";
		}else{
		  document.getElementById("checkinput1").innerHTML='<input name="checkbox" value="數據管理,資料輸入狀況,page/checkInput.jsp" type="checkbox"/>資料輸入狀況';																								                                             ;									 										
	    }
	    
	    var i47=document.getElementById("kpifact");
		if(i47!=null){
		   document.getElementById("kpifact1").innerHTML="";
		}else{
		  document.getElementById("kpifact1").innerHTML='<input name="checkbox" value="資料輸入,KPI年度目標,kpifact_findPageBean" type="checkbox"/>KPI年度目標';																								                                             ;									 										
	    }
	    	 	    
	    var i49=document.getElementById("hangwenshenheState");
		if(i49!=null){
		   document.getElementById("hangwenshenheState1").innerHTML="";
		}else{
		  document.getElementById("hangwenshenheState1").innerHTML='<input name="checkbox" value="KPI數據,函文審核狀況,vbm_findPageBean_1"  type="checkbox">函文審核狀況';																								                                             ;									 										
	    }
	    
	    var i50=document.getElementById("kyzcontactletter");
		if(i50!=null){
		   document.getElementById("kyzcontactletter1").innerHTML="";
		}else{
		  document.getElementById("kyzcontactletter1").innerHTML='<input name="checkbox" value="KPI數據,內部聯絡函申請,kyzletter_findPageBean" type="checkbox">內部聯絡函申請';
							                                              																								                                             ;									 										
	    }
	    
	    var i51=document.getElementById("webcashout");
	    if(i51!=null){
	       document.getElementById("webcashout1").innerHTML="";
	    }else{
	       document.getElementById("webcashout1").innerHTML='<input name="checkbox" value="資料輸入,請款資料,webcashout_findPageBean" type="checkbox" />請款資料';																							
	    }
	    
	    var i52=document.getElementById("webcashout_report");
	    if(i52!=null){
	       document.getElementById("webcashout_report1").innerHTML="";
	    }else{
	       document.getElementById("webcashout_report1").innerHTML='<input name="checkbox" value="數據管理,生產與請款達成狀況統計,page/webcashout_report.jsp" type="checkbox" />生產與請款達成狀況統計';																		
	    }
	    
	    var i53=document.getElementById("backemail");
	    if(i53!=null){
	      document.getElementById("backemail1").innerHTML="";
	    }else{
	       document.getElementById("backemail1").innerHTML='<input name="checkbox" value="用戶管理,備簽人管理,webuseremail_findPageBean"  type="checkbox" />備簽人管理'									
	    }
	    
	    var i54=document.getElementById("webtype");
	    if(i54!=null){
	       document.getElementById("webtype1").innerHTML="";
	    }else{
	       document.getElementById("webtype1").innerHTML='<input name="checkbox" value="KPI數據,簽核類別管理,webtype_findPageBean" type="checkbox">簽核類別管理'							                                             
	    }
	    
	    var i55=document.getElementById("webemaila");
	    if(i55!=null){
	       document.getElementById("webemaila1").innerHTML="";
	    }else{
	       document.getElementById("webemaila1").innerHTML='<input name="checkbox" value="用戶管理,知會人管理,webuseremaila_findPageBean" type="checkbox" />知會人管理'																                                             
	    }
	    
	      	    	 
}
		
function getSub(){
   document.getElementById("subform").submit();
   layer.load("正在處理,請稍等....");
}
function back(){
	layer.load("正在返回...");
	location.href="/Login/userfindPageBean";
}
</script>
<body>
	<form action="userupdateJurisdiction" method="post" id="subform">
		<table border=1
			style="border-collapse: collapse;width:700px;height:300px;">
			<caption><s:property value="#session.user.username"/>&nbsp;&nbsp;
			<s:if test='#session.user.userread=="1"'>
			   <input type="checkbox" name="userread" value="1" checked/>只查看
			</s:if>
			<s:else>
			   <input type="checkbox" name="userread" value="1"/>只查看
			</s:else>
			
			</caption>
			<tr>
				<td class="td1">KPI數據:</td>
				<td>
					<table>					
						<td>
							  <s:iterator value="#session.user.webJurisdictions">
									<s:iterator value="webSubmenus">
										<s:if test="submenuname=='函文申請'">
											<span id="shenhe"><input name="checkbox"
												value="KPI數據,函文申請,kyz_findPageBean" checked="checked"
												type="checkbox" />函文申請</span>
										</s:if>
									</s:iterator>
								</s:iterator> <span id="shenhe1"></span>
							</td>
							<td>
							  <s:iterator value="#session.user.webJurisdictions">
									<s:iterator value="webSubmenus">
										<s:if test="submenuname=='審核流程'">
											<span id="shenheliucheng"><input name="checkbox"
												value="KPI數據,審核流程,visaflow_findPageBean" checked="checked"
												type="checkbox" />審核流程</span>
										</s:if>
									</s:iterator>
								</s:iterator> <span id="shenheliucheng1"></span>
							</td>
							<td>
							   <s:iterator value="#session.user.webJurisdictions">
							      <s:iterator value="webSubmenus">
							          <s:if test="submenuname=='函文審核'">
							             <span id="hangwenshenhe">
							                 <input name="checkbox" value="KPI數據,函文審核,vbm_findPageBean" checked="checked" type="checkbox">
							                                              函文審核
							             </span>
							          </s:if>
							      </s:iterator>							   
							   </s:iterator>
							   <span id="hangwenshenhe1"></span>
							</td>
							
							<td>
							   <s:iterator value="#session.user.webJurisdictions">
							      <s:iterator value="webSubmenus">
							          <s:if test="submenuname=='費用組別'">
							             <span id="feiyongleibie">
							                 <input name="checkbox" value="KPI數據,費用組別,kyzsec_findPageBean" checked="checked" type="checkbox">
							                                              費用組別
							             </span>
							          </s:if>
							      </s:iterator>							   
							   </s:iterator>
							   <span id="feiyongleibie1"></span>
							</td>							
							<td><br> <br>							
							</td>
						</tr>
						<tr>
						   <td>
							   <s:iterator value="#session.user.webJurisdictions">
							      <s:iterator value="webSubmenus">
							          <s:if test="submenuname=='會計科目'">
							             <span id="kuaijikemu">
							                 <input name="checkbox" value="KPI數據,會計科目,kyzacct_findPageBean" checked="checked" type="checkbox">
							                                              會計科目
							             </span>
							          </s:if>
							      </s:iterator>							   
							   </s:iterator>
							   <span id="kuaijikemu1"></span>
							</td>
							<td>
							   <s:iterator value="#session.user.webJurisdictions">
							      <s:iterator value="webSubmenus">
							          <s:if test="submenuname=='零用金支出'">
							             <span id="linyongjichu">
							                 <input name="checkbox" value="KPI數據,零用金支出,kyzpetty_findPageBean" checked="checked" type="checkbox">
							                                              零用金支出
							             </span>
							          </s:if>
							      </s:iterator>							   
							   </s:iterator>
							   <span id="linyongjichu1"></span>
							</td>
							<td>
							   <s:iterator value="#session.user.webJurisdictions">
							      <s:iterator value="webSubmenus">
							          <s:if test="submenuname=='物料資料'">
							             <span id="kyzmat">
							                 <input name="checkbox" value="KPI數據,物料資料,subkyzmat_findPageBean" checked="checked" type="checkbox">
							                                              物料資料
							             </span>
							          </s:if>
							      </s:iterator>							   
							   </s:iterator>
							   <span id="kyzmat1"></span>
							</td>							
							<td>
							   <s:iterator value="#session.user.webJurisdictions">
							      <s:iterator value="webSubmenus">
							          <s:if test="submenuname=='函文審核狀況'">
							             <span id="hangwenshenheState">
							                 <input name="checkbox" value="KPI數據,函文審核狀況,vbm_findPageBean_1" checked="checked" type="checkbox">
							                                              函文審核狀況
							             </span>
							          </s:if>
							      </s:iterator>							   
							   </s:iterator>
							   <span id="hangwenshenheState1"></span>
							</td>
							<td>
							   <s:iterator value="#session.user.webJurisdictions">
							      <s:iterator value="webSubmenus">
							          <s:if test="submenuname=='內部聯絡函申請'">
							             <span id="kyzcontactletter">
							                 <input name="checkbox" value="KPI數據,內部聯絡函申請,kyzletter_findPageBean" checked="checked" type="checkbox">
							                                              內部聯絡函申請
							             </span>
							          </s:if>
							      </s:iterator>							   
							   </s:iterator>
							   <span id="kyzcontactletter1"></span>
							</td>				
						</tr>
						<tr>						   
							 <td>
							   <s:iterator value="#session.user.webJurisdictions">
							      <s:iterator value="webSubmenus">
							          <s:if test="submenuname=='簽核類別管理'">
							             <span id="webtype">
							                 <input name="checkbox" value="KPI數據,簽核類別管理,webtype_findPageBean" checked="checked" type="checkbox">
							                                              簽核類別管理
							             </span>
							          </s:if>
							      </s:iterator>							   
							   </s:iterator>
							   <span id="webtype1"></span>
							</td>
						</tr>
					</table></td>
			</tr>
			
			<tr>
			  <td class="td1">資料輸入</td>
			  <td>
			      <table>
			      <tr>			         			
							<td><s:iterator value="#session.user.webJurisdictions">
									<s:iterator value="webSubmenus">
										<s:if test="submenuname=='人數工時離職資料'">
											<span id="renshugonsh"><input name="checkbox"
												value="資料輸入,人數工時離職資料,webmixPerson_getList" checked="checked"
												type="checkbox" />人數工時離職資料</span>
										</s:if>
									</s:iterator>
								</s:iterator> <span id="renshugonsh1"></span>
							</td>
							<td><s:iterator value="#session.user.webJurisdictions">
									<s:iterator value="webSubmenus">
										<s:if test="submenuname=='營收/費用資料'">
											<span id="yinshoufeiyong"><input name="checkbox"
												value="資料輸入,營收/費用資料,webmix2_getList" checked="checked"
												type="checkbox" />營收/費用資料</span>
										</s:if>
									</s:iterator>
								</s:iterator> <span id="yinshoufeiyong1"></span>
							</td>
							<td><s:iterator value="#session.user.webJurisdictions">
									<s:iterator value="webSubmenus">
										<s:if test="submenuname=='廢品管數據'">
											<span id="feipingguanli"><input
												value="資料輸入,廢品管數據,webScrapt_getList" name="checkbox"
												checked="checked" type="checkbox" />廢品管數據</span>
										</s:if>
									</s:iterator>
								</s:iterator> <span id="feipingguanli1"></span>
							</td>														
							<td><s:iterator value="#session.user.webJurisdictions">
									<s:iterator value="webSubmenus">
										<s:if test="submenuname=='水電油資料'">
											<span id="shuidianyou"><input name="checkbox"
												value="資料輸入,水電油資料,webwlo_getList" checked="checked"
												type="checkbox" />水電油資料</span>
										</s:if>
									</s:iterator>
								</s:iterator> <span id="shuidianyou1"></span>
							</td>
			      </tr> 
			      
			      <tr>
			          		<td><s:iterator value="#session.user.webJurisdictions">
									<s:iterator value="webSubmenus">
										<s:if test="submenuname=='成品盤點資料'">
											<span id="chengpingpandian"><input name="checkbox"
												value="資料輸入,成品盤點資料,webProduted_getList" checked="checked"
												type="checkbox" />成品盤點資料</span>
										</s:if>
									</s:iterator>
								</s:iterator> <span id="chengpingpandian1"></span>
							</td>	
							<td><s:iterator value="#session.user.webJurisdictions">
									<s:iterator value="webSubmenus">
										<s:if test="submenuname=='資材資料'">
											<span id="zicaichenben"><input
												value="資料輸入,資材資料,webcost_findPageBean" name="checkbox"
												checked="checked" type="checkbox" />資材資料</span>
										</s:if>
									</s:iterator>
								</s:iterator> <span id="zicaichenben1"></span>
							</td>
							<td><s:iterator value="#session.user.webJurisdictions">
									<s:iterator value="webSubmenus">
										<s:if test="submenuname=='預計生產'">
											<span id="yujishch"><input name="checkbox"
												value="資料輸入,預計生產,webestpro_findPageBean" checked="checked"
												type="checkbox" />預計生產</span>
										</s:if>
									</s:iterator>
								</s:iterator> <span id="yujishch1"></span>
							</td>
							<td><s:iterator value="#session.user.webJurisdictions">
									<s:iterator value="webSubmenus">
										<s:if test="submenuname=='回頭料(每月輸入)'">
											<span id="huitouliao"><input name="checkbox"
												value="資料輸入,回頭料(每月輸入),webbackfeed_findPageBean" checked="checked"
												type="checkbox" />回頭料(每月輸入)</span>
										</s:if>
									</s:iterator>
								</s:iterator> <span id="huitouliao1"></span>
							</td>
							<td><s:iterator value="#session.user.webJurisdictions">
									<s:iterator value="webSubmenus">
										<s:if test="submenuname=='人員考勤'">
											<span id="renyuankaoqin"><input name="checkbox"
												value="資料輸入,人員考勤,webpersonnum_findPageBean" checked="checked"
												type="checkbox" />人員考勤</span>
										</s:if>
									</s:iterator>
								</s:iterator> <span id="renyuankaoqin1"></span>
							</td>							
			      </tr>
			      <tr>			        
							<td><s:iterator value="#session.user.webJurisdictions">
									<s:iterator value="webSubmenus">
										<s:if test="submenuname=='產量資料'">
											<span id="clzl"><input name="checkbox"
												value="資料輸入,產量資料,ydata_findPageBean" checked="checked"
												type="checkbox" />產量資料</span>
										</s:if>
									</s:iterator>
								</s:iterator> <span id="clzl1"></span>
							</td>
							<td>
							  <s:iterator value="#session.user.webJurisdictions">
									<s:iterator value="webSubmenus">
										<s:if test="submenuname=='固定資產'">
											<span id="gdzc"><input name="checkbox"
												value="資料輸入,固定資產,fix_findPageBean" checked="checked"
												type="checkbox" />固定資產</span>
										</s:if>
									</s:iterator>
								</s:iterator> <span id="gdzc1"></span>
							</td>
							<td>
							  <s:iterator value="#session.user.webJurisdictions">
									<s:iterator value="webSubmenus">
										<s:if test="submenuname=='KPI年度目標'">
											<span id="kpifact"><input name="checkbox"
												value="資料輸入,KPI年度目標,kpifact_findPageBean" checked="checked"
												type="checkbox" />KPI年度目標</span>
										</s:if>
									</s:iterator>
								</s:iterator> <span id="kpifact1"></span>
							</td>
							<td>
							  <s:iterator value="#session.user.webJurisdictions">
									<s:iterator value="webSubmenus">
										<s:if test="submenuname=='請款資料'">
											<span id="webcashout"><input name="checkbox"
												value="資料輸入,請款資料,webcashout_findPageBean" checked="checked"
												type="checkbox" />請款資料</span>
										</s:if>
									</s:iterator>
								</s:iterator> <span id="webcashout1"></span>
							</td>
			      </tr>
			    </table>
			  </td>
			</tr>
			
			<tr>
				<td class="td1">用戶管理:</td>
				<td><s:iterator value="#session.user.webJurisdictions">
						<s:iterator value="webSubmenus">
							<s:if test="submenuname=='管理用戶'">
								<span id="glyh"><input name="checkbox"
									value="用戶管理,管理用戶,userfindPageBean" checked="checked" type="checkbox" />管理用戶</span>
							</s:if>
						</s:iterator>
					</s:iterator> <span id="glyh1"></span>
					<s:iterator value="#session.user.webJurisdictions">
						<s:iterator value="webSubmenus">
							<s:if test="submenuname=='備簽人管理'">
								<span id="backemail"><input name="checkbox"
									value="用戶管理,備簽人管理,webuseremail_findPageBean" checked="checked" type="checkbox" />備簽人管理</span>
							</s:if>
						</s:iterator>
					</s:iterator> <span id="backemail1"></span>
					<s:iterator value="#session.user.webJurisdictions">
						<s:iterator value="webSubmenus">
							<s:if test="submenuname=='知會人管理'">
								<span id="webemaila"><input name="checkbox"
									value="用戶管理,知會人管理,webuseremaila_findPageBean" checked="checked" type="checkbox" />知會人管理</span>
							</s:if>
						</s:iterator>
					</s:iterator> <span id="webemaila1"></span>
				</td>
			</tr>
			<tr>
				<td class="td1">數據管理:</td>
				<td><s:iterator value="#session.user.webJurisdictions">
						<s:iterator value="webSubmenus">
							<s:if test="submenuname=='數據統計'">
								<span id="sjgl"><input name="checkbox"
									value="數據管理,數據統計,page/StatisticalData.jsp" checked="checked"
									type="checkbox" />數據統計</span>
							</s:if>
						</s:iterator>
					</s:iterator> <span id="sjgl1"></span> <s:iterator
						value="#session.user.webJurisdictions">
						<s:iterator value="webSubmenus">
							<s:if test="submenuname=='各廠產量統計'">
								<span id="cltj"><input name="checkbox"
									value="數據管理,各廠產量統計,page/OutputStatistics.jsp" checked="checked"
									type="checkbox" />各廠產量統計</span>
							</s:if>
						</s:iterator>
					</s:iterator> <span id="cltj1"></span> <s:iterator
						value="#session.user.webJurisdictions">
						<s:iterator value="webSubmenus">
							<s:if test="submenuname=='EXCEL數據上傳'">
								<span id="excel"><input name="checkbox"
									value="數據管理,EXCEL數據上傳,page/excelUpload.jsp" checked="checked"
									type="checkbox" />EXCEL數據上傳</span>
							</s:if>
						</s:iterator>
					</s:iterator><span id="excel1"></span>
					<s:iterator value="#session.user.webJurisdictions">						
						<s:iterator value="webSubmenus">
							<s:if test="submenuname=='預計生產與請款匯總'">
								<span id="yujishengyuqikuanhuizong"><input name="checkbox"
									value="數據管理,預計生產與請款匯總,page/webestproduct_total.jsp" checked="checked"
									type="checkbox" />預計生產與請款匯總</span>
							</s:if>
						</s:iterator>
					</s:iterator><span id="yujishengyuqikuanhuizong1"></span>
					<s:iterator value="#session.user.webJurisdictions">						
						<s:iterator value="webSubmenus">
							<s:if test="submenuname=='文件上傳與下載'">
								<span id="fileUpAndDown"><input name="checkbox"
									value="數據管理,文件上傳與下載,filesUpload_findByName" checked="checked"
									type="checkbox" />文件上傳與下載</span>
							</s:if>
						</s:iterator>
					</s:iterator><span id="fileUpAndDown1"></span>
					<s:iterator value="#session.user.webJurisdictions">						
						<s:iterator value="webSubmenus">
							<s:if test="submenuname=='KPI工廠與KPI台灣'">
								<span id="kpi_fact"><input name="checkbox"
									value="數據管理,KPI工廠與KPI台灣,page/kpiFactAndTW.jsp" checked="checked"
									type="checkbox" />KPI工廠與KPI台灣</span>
							</s:if>
						</s:iterator>
					</s:iterator><span id="kpi_fact1"></span>
					<s:iterator value="#session.user.webJurisdictions">						
						<s:iterator value="webSubmenus">
							<s:if test="submenuname=='分形態損益表'">
								<span id="vwebmachine"><input name="checkbox"
									value="數據管理,分形態損益表,page/VWebmachine.jsp" checked="checked"
									type="checkbox" />分形態損益表</span>
							</s:if>
						</s:iterator>
					</s:iterator><span id="vwebmachine1"></span>
					<s:iterator value="#session.user.webJurisdictions">						
						<s:iterator value="webSubmenus">
							<s:if test="submenuname=='全廠損益表'">
								<span id="vsumall"><input name="checkbox"
									value="數據管理,全廠損益表,page/vSumall.jsp" checked="checked"
									type="checkbox" />全廠損益表</span>
							</s:if>
						</s:iterator>
					</s:iterator><span id="vsumall1"></span>
					<s:iterator value="#session.user.webJurisdictions">						
						<s:iterator value="webSubmenus">
							<s:if test="submenuname=='資料輸入狀況'">
								<span id="checkinput"><input name="checkbox"
									value="數據管理,資料輸入狀況,page/checkInput.jsp" checked="checked"
									type="checkbox" />資料輸入狀況</span>
							</s:if>
						</s:iterator>
					</s:iterator><span id="checkinput1"></span>
					<s:iterator value="#session.user.webJurisdictions">						
						<s:iterator value="webSubmenus">
							<s:if test="submenuname=='生產與請款達成狀況統計'">
								<span id="webcashout_report"><input name="checkbox"
									value="數據管理,生產與請款達成狀況統計,page/webcashout_report.jsp" checked="checked"
									type="checkbox" />生產與請款達成狀況統計</span>
							</s:if>
						</s:iterator>
					</s:iterator><span id="webcashout_report1"></span>
					</td>
			</tr>
			<tr>
				<td class="td1">個人設置:</td>
				<td><s:iterator value="#session.user.webJurisdictions">
						<s:iterator value="webSubmenus">
							<s:if test="submenuname=='修改我的資料'">
								<span id="xgwdzl"><input name="checkbox"
									value="個人設置,修改我的資料,userinitialUpdate" checked="checked"
									type="checkbox" />修改我的資料</span>
							</s:if>
						</s:iterator>
					</s:iterator> <span id="xgwdzl1"></span> <s:iterator
						value="#session.user.webJurisdictions">
						<s:iterator value="webSubmenus">
							<s:if test="submenuname=='查看本機Ip'">
								<span id="ckbjip"><input name="checkbox"
									value="個人設置,查看本機Ip,page/selectIp.jsp" checked="checked"
									type="checkbox" />查看本機IP</span>
							</s:if>
						</s:iterator>
					</s:iterator> <span id="ckbjip1"></span></td>
			</tr>
			<tr>
				<td colspan="2"><input style="margin-left:300px;" value="確認修改"
					onclick="getSub()" type="button"><input type="button"
					onclick="back()" value="返回查看">
				</td>
			</tr>
			<tr>
			</tr>

		</table>
	</form>
</body>
</html>