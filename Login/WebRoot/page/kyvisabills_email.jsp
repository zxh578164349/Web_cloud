
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>函文審核</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<style type="text/css">


</style>


<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/style.css" rel="stylesheet">	
<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
<!--[if lt IE 9]>  
  <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
<script type="text/javascript">
$(function () { $("[data-toggle='popover']").popover(); });
$(function(){
		$("#myModal").on("hidden.bs.modal",function(){
			$(this).removeData("bs.modal");
		});
});
function showDiv(billNo,factNo){
		$("#myModal").on("show.bs.modal",function(){
			$(".modal-body").load("kyz_findById_layer",{"billNo":billNo,"factNo":factNo});			
		});				
}

function showDiv2(billNo,factNo){
	     $("#myModal").on("show.bs.modal",function(){
			 $(".modal-body").load("kyzletter_findById_layer",{"billNo":billNo,"factNo":factNo});
		 });
}

function showDiv3(billNo){
    $("#myModal").on("show.bs.modal",function(){
		 $(".modal-body").load("bussletter_findById_layer",{"billNo":billNo});
	 });
}
/*全局變量*/
var factNo_g,billNo_g,itemNo_g,visaSort_g;

/*****************************20151027更新***********************************/
function check(factNo,visaSort,billNo,itemNo){
		 $("#myModalA").on("show.bs.modal",function(){
			 $(".modal-body").load("kyz_findById_layer",{"billNo":billNo,"factNo":factNo,"visaSort":visaSort,"itemNo":itemNo});
		 });
		 factNo_g=factNo;
		 billNo_g=billNo;
		 itemNo_g=itemNo;
		 visaSort_g=visaSort;
	}
function check2(factNo,visaSort,billNo,itemNo){
	     $("#myModalA").on("show.bs.modal",function(){
			 $(".modal-body").load("kyzletter_findById_layer",{"billNo":billNo,"factNo":factNo,"visaSort":visaSort,"itemNo":itemNo})
		 });
		 factNo_g=factNo;
		 billNo_g=billNo;
		 itemNo_g=itemNo;
		 visaSort_g=visaSort;
}
/*****************************20151027更新***********************************/
function check3(factNo,visaSort,billNo,itemNo){
	     $("#myModalA").on("show.bs.modal",function(){
			 $(".modal-body").load("bussletter_findById_layer",{"billNo":billNo,"factNo":factNo,"visaSort":visaSort,"itemNo":itemNo})
		 });
		 factNo_g=factNo;
		 billNo_g=billNo;
		 itemNo_g=itemNo;
		 visaSort_g=visaSort;
}

function goYes(){
	   //window.location.href='vbm_add?billNo='+billNo_g+'& visa_mk=Y'+'& factNo='+factNo_g+'& itemNo='+itemNo_g+'& visaSort='+visaSort_g;
	 
	  /* 修改5   20151025 */
	  var memo_leg=document.getElementById("memo_txt").value;
	  if(memo_leg.length>150){
	     alert("備註不可超過150字");
	  }else{
	     document.getElementById("visa_mk").value="Y";
	     document.getElementById("memo").submit();
	  } 
	  
	  /* 修改5   20151025 */
} 
function goNo(){
	   //window.location.href='vbm_add?billNo='+billNo_g+'& visa_mk=T'+'& factNo='+factNo_g+'& itemNo='+itemNo_g+'& visaSort='+visaSort_g;
	   /* 修改5   20151025 */
	  var memo_leg=document.getElementById("memo_txt").value;
	  if(memo_leg.length>150){
	     alert("備註不可超過150字");
	  }else{
	     document.getElementById("visa_mk").value="T";
	     document.getElementById("memo").submit();
	  }
	  /* 修改5   20151025 */
}   
    
/*function altRows(id){
	if(document.getElementsByTagName){  
		
		var table = document.getElementById(id);  
		var rows = table.getElementsByTagName("tr"); 
		 
		for(i = 0; i < rows.length; i++){          
			if(i % 2 == 0){
				rows[i].className = "evenrowcolor";
			}else{
				rows[i].className = "oddrowcolor";
			}      
		}
	}
}

window.onload=function(){
	altRows('alternatecolor');
}*/


</script>
</head>
<body>
	<table>
		<caption>
			<h1>函文審核</h1>
		</caption>						
		<tr>
			<td><s:if test='vbm.id.billNo.substring(0,2)=="EM"'>
					<!--<a
						href="javascript:showDiv('<s:property value='vbm.id.billNo'/>','<s:property value='vbm.id.factNo'/>')">
						<s:property value="vbm.id.billNo" /> </a>
					-->	
					<a href="#myModal" data-toggle="modal" data-target="#myModal" onclick="showDiv('<s:property value='vbm.id.billNo'/>','<s:property value='vbm.id.factNo'/>')">
		                <s:property value="vbm.id.billNo"/>
		            </a>	
				</s:if> 
				<s:if test='vbm.id.billNo.substring(0,2)=="CM"'>
					<!--<a
						href="javascript:showDiv2('<s:property value='vbm.id.billNo'/>','<s:property value='vbm.id.factNo'/>')">
						<s:property value="vbm.id.billNo" /> </a>
					-->	
					<a href="#myModal" data-toggle="modal" data-target="#myModal" onclick="showDiv2('<s:property value='vbm.id.billNo'/>','<s:property value='vbm.id.factNo'/>')">
					    <s:property value="vbm.id.billNo" />
					</a>	
				</s:if>
				<s:if test='vbm.id.billNo.substring(0,2)=="BM"'>
					<!--<a
						href="javascript:showDiv2('<s:property value='vbm.id.billNo'/>','<s:property value='vbm.id.factNo'/>')">
						<s:property value="vbm.id.billNo" /> </a>
					-->	
					<a href="#myModal" data-toggle="modal" data-target="#myModal" onclick="showDiv3('<s:property value='vbm.id.billNo'/>')">
					    <s:property value="vbm.id.billNo" />
					</a>	
				</s:if>
			</td>
		</tr>
		<tr>
			<td>
				<div class="container">
					<div class="row">
						<s:iterator value="vbm.kyVisabillses" status="x">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4"
								style="background-color: #dedef8; box-shadow: 
                          inset 1px -1px 1px #444, inset -1px 1px 1px #444;">
								<p class="visible-xs visible-sm "
									style="font-size:2em">
									(${x.index+1}) 
									<s:if test='flowMk=="Y"'>
										<s:if test='visaMk=="N"'>
											<!-- 1.判斷未審和已審狀態 -->
											<s:if test='id.itemNo==vbm.itemNext'>
												<!--2.判斷當前審核人的項次是否為下一位審核人 的項次 -->
												<s:if test="%{strToLow(visaSigner)==strToLow(email)}">
													<!-- 3.判斷登錄者是否為當前審核人 -->
													<s:if test='vbm.id.billNo.substring(0,2)=="EM"'>
														<!--<a style="color:red"
															href="javascript:check('<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>',
	              '<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.itemNo'/>')">
															未審核<s:property value="id.itemNo" />(當前審核人) </a>
														-->	
														<a style="color:red"
															href="#myModalA" data-toggle="modal" data-target="#myModalA" onclick="check('<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>',
	              '<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.itemNo'/>')">     
															未審核<s:property value="id.itemNo" />(當前審核人) </a>	
													</s:if>
													<s:if test='vbm.id.billNo.substring(0,2)=="CM"'>
														<!--<a style="color:red"
															href="javascript:check2('<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>',
	              '<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.itemNo'/>')">
															未審核<s:property value="id.itemNo" />(當前審核人) </a>
														-->	
														<a style="color:red"
															href="#myModalA" data-toggle="modal" data-target="#myModalA" onclick="check2('<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>',
	              '<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.itemNo'/>')">     
															未審核<s:property value="id.itemNo" />(當前審核人) </a>	
													</s:if>
													<s:if test='vbm.id.billNo.substring(0,2)=="BM"'>
														<!--<a style="color:red"
															href="javascript:check2('<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>',
	              '<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.itemNo'/>')">
															未審核<s:property value="id.itemNo" />(當前審核人) </a>
														-->	
														<a style="color:red"
															href="#myModalA" data-toggle="modal" data-target="#myModalA" onclick="check3('<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>',
	              '<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.itemNo'/>')">     
															未審核<s:property value="id.itemNo" />(當前審核人) </a>	
													</s:if>

												</s:if>
												<s:else>
													<a disabled style="color:grey">未審核</a>
												</s:else>
											</s:if>
											<s:else>
												<a disabled style="color:grey">未審核</a>
											</s:else>
										</s:if>
										<s:if test='visaMk=="Y"'>
											<a style="color:green"  data-toggle="popover" data-placement="top" title="備註" data-content="${memo}">
											已審核<br/>(<s:property value="dateVisa"/>)</a>
										</s:if>
										<s:if test='visaMk=="T"'>
											<a style="color:blue"  data-toggle="popover" data-placement="top" title="備註" data-content="${memo}">
											未通過<br/>(<s:property value="dateVisa"/>)</a>
										</s:if>
									</s:if>
									<s:else>
										<a style="color:#b45b3e">只知會</a>
									</s:else>
									(<s:property value="visaRank"/>)
								</p>
								<p class="visible-md visible-lg" style="font-size:1em">
								(${x.index+1}) 
									<s:if test='flowMk=="Y"'>
										<s:if test='visaMk=="N"'>
											<!-- 1.判斷未審和已審狀態 -->
											<s:if test='id.itemNo==vbm.itemNext'>
												<!--2.判斷當前審核人的項次是否為下一位審核人 的項次 -->
												<s:if test="%{strToLow(visaSigner)==strToLow(email)}">
													<!-- 3.判斷登錄者是否為當前審核人 -->
													<s:if test='vbm.id.billNo.substring(0,2)=="EM"'>
														<!--<a style="color:red"
															href="javascript:check('<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>',
	              '<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.itemNo'/>')">
															未審核<s:property value="id.itemNo" />(當前審核人) </a>
														-->	
															<a style="color:red"
															href="#myModalA" data-toggle="modal" data-target="#myModalA" onclick="check('<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>',
	              '<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.itemNo'/>')">     
															<s:property value="id.itemNo" />(當前審核人) </a>
													</s:if>
													<s:if test='vbm.id.billNo.substring(0,2)=="CM"'>
														<!--<a style="color:red"
															href="javascript:check2('<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>',
	              '<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.itemNo'/>')">
															未審核<s:property value="id.itemNo" />(當前審核人) </a>
														-->	
															<a style="color:red"
															href="#myModalA" data-toggle="modal" data-target="#myModalA" onclick="check2('<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>',
	              '<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.itemNo'/>')">     
															<s:property value="id.itemNo" />(當前審核人) </a>
													</s:if>
													<s:if test='vbm.id.billNo.substring(0,2)=="BM"'>
														<!--<a style="color:red"
															href="javascript:check2('<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>',
	              '<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.itemNo'/>')">
															未審核<s:property value="id.itemNo" />(當前審核人) </a>
														-->	
															<a style="color:red"
															href="#myModalA" data-toggle="modal" data-target="#myModalA" onclick="check3('<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>',
	              '<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.itemNo'/>')">     
															<s:property value="id.itemNo" />(當前審核人) </a>
													</s:if>

												</s:if>
												<s:else>
													<a disabled style="color:grey">未審核</a>
												</s:else>
											</s:if>
											<s:else>
												<a disabled style="color:grey">未審核</a>
											</s:else>
										</s:if>
										<s:if test='visaMk=="Y"'>
											<a style="color:green"  data-toggle="popover" data-placement="top" title="備註" data-content="${memo}">
											已審核(<s:property value="dateVisa"/>)</a>
										</s:if>
										<s:if test='visaMk=="T"'>
											<a style="color:blue"  data-toggle="popover" data-placement="top" title="備註" data-content="${memo}">
											未通過(<s:property value="dateVisa"/>)</a>
										</s:if>
									</s:if>
									<s:else>
										<a style="color:#b45b3e">只知會</a>
									</s:else>
									(<s:property value="visaRank"/>)
								</p>
							</div>
						</s:iterator>
					</div>
				</div>
			</td>
		</tr>
	</table>
</body>



<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">函文内容</h4>
				</div>
				<div class="modal-body"></div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<!-- <button type="button" class="btn btn-primary">提交更改</button> -->
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
</div>
<div class="modal fade" id="myModalA" tabindex="-1" role="dialog"
aria-labelledby="myModalLabelA" aria-hidden="true">
     <div class="modal-dialog">
	     <div class="modal-content">
		     <div class="modal-header">
			     <button type="button" class="close" data-dismiss="modal"
				   aria-hidden="true">&times;</button>
				 <h4 class="modal-title" id="myModalLabelA">函文内容</h4>  
			 </div>
			 <div class="modal-body">
			   		  			 
			 </div>
			 <div class="modal-footer">
			 
			    <!--------------------------- 修改4 20151025 --------------------------------------->
				<form action="vbm_add2" method="post" id="memo" role="form" />
				<div class="form-group">
					<label>備註↓↓↓</label>
					<textarea class="form-control" rows="3" name="memo" id="memo_txt"></textarea>			             			          
				</div>				
				<input type="hidden" value="<s:property value='factNo'/>" name="factNo"/>
				<input type="hidden" value="<s:property value='billNo'/>" name="billNo"/>
				<input type="hidden" value="<s:property value='visaSort'/>" name="visaSort"/>
				<input type="hidden" value="<s:property value='itemNo'/>" name="itemNo" />
				<input type="hidden" name="visa_mk" id="visa_mk"/>					
				</form> 
				 <!--------------------------- 修改4 20151025 --------------------------------------->
				 
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				 </button>
				 <a href="javascript:goYes()" 
				 class="btn btn-primary">通過</a>
				 <a href="javascript:goNo()"
				 type="button" class="btn btn-primary">不通過</a>
			 </div>
		 </div>
	 </div>

</div>
<jsp:include page="../copyright.jsp"/>	
<%--<div style="width:100%;position:fixed;bottom:0" align="center" id="tab3">
<font color="grey" style="font-size:10px;font-family: Arial, Helvetica, sans-serif;">
Copyright © 2014,東莞加元鞋材制品有限公司,All Rights Reserved
</font></div>	
--%>

</html>
