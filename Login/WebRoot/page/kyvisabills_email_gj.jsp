
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>函文審核(只查看)</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<link rel="shortcut icon" href="images/icon/web_ico.ico" /> 
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- <link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet"> -->

<style type="text/css">
.div-border{
   border:1px solid grey; 
   background-color:#FCF8E3;
}
</style>
</head>
<body>
    <h3 >函文審核(只查看)</h3>
	<table>							
		<tr>
			<td>					
					<a href="#myModal" data-toggle="modal" data-target="#myModal" onclick="showDiv('<s:property value='vbm.id.billNo'/>','<s:property value='vbm.id.factNo'/>')">
		                <s:property value="vbm.id.billNo"/>
		            </a>
		            
		            <s:if test='vbm.visaMk=="Y"||vbm.visaMk=="T"'>
		               <input type="hidden" value="Y" id="isDone"/><!--判斷函文是否簽核完畢-->	
		            </s:if>					
			</td>
		</tr>
		<tr>
			<td>
				<div class="container">
					<div class="row">
						<s:iterator value="vbm.kyVisabillses" status="x">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4 div-border">
								<p class="visible-xs visible-sm "
									style="font-size:2em">
									<font>(${x.index+1})<s:property value="visaRank"/></font>
									<s:if test='flowMk=="Y"'>
										<s:if test='visaMk=="N"'>
											<!-- 1.判斷未審和已審狀態 -->
											<s:if test='id.itemNo==vbm.itemNext'>
												<!--2.判斷當前審核人的項次是否為下一位審核人 的項次 -->
												<s:if test="%{strToLow(visaSigner)==strToLow(vbm.signerNext)}">
													<!-- 3.判斷登錄者是否為當前審核人 -->																											
														<span style="color:red">(當前審核人) </span> 															    																
														<input type="hidden" value="N" id="expiredMk"/><!--判斷郵件審核鏈接是否過期   N:未過期    Y:過期-->												
												</s:if>
												<s:else>
													<a disabled style="color:grey">未審核</a>
													<input type="hidden" value="Y" id="expiredMk"/>	  <!--判斷郵件審核鏈接是否過期   N:未過期    Y:過期--> 
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
									    <s:if test='visible!="N"'>
	                                      <a style="color:#b45b3e">只知會</a>
	                                    </s:if> 										
									</s:else>
									
								</p>
								<p class="visible-md visible-lg" style="font-size:1em">
								<font>(${x.index+1})<s:property value="visaRank"/></font>
									<s:if test='flowMk=="Y"'>
										<s:if test='visaMk=="N"'>
											<!-- 1.判斷未審和已審狀態 -->
											<s:if test='id.itemNo==vbm.itemNext'>
												<!--2.判斷當前審核人的項次是否為下一位審核人 的項次 -->
												<s:if test="%{strToLow(visaSigner)==strToLow(vbm.signerNext)}">
													<!-- 3.判斷登錄者是否為當前審核人 -->																												
															<a style="color:red">															     
															(當前審核人) </a>
															<input type="hidden" value="N" id="expiredMk"/><!--判斷郵件審核鏈接是否過期   N:未過期    Y:過期-->													
												</s:if>
												<s:else>
													<a disabled style="color:grey">未審核</a>
													<input type="hidden" value="Y" id="expiredMk"/>	  <!--判斷郵件審核鏈接是否過期   N:未過期    Y:過期--> 
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
									<s:if test='visible!="N"'>
	                                  <a style="color:#b45b3e">只知會</a>
	                                </s:if>
	                                <s:else>/</s:else>										
									</s:else>									
								</p>
							</div>
						</s:iterator>
					</div>
				</div>
			</td>
		</tr>
	</table>

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

<jsp:include page="../copyright.jsp"/>		
	

<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery/layer/layer.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>	
<!--[if lt IE 9]>  
<script src="bootstrap/html5.js"></script>
<script src="bootstrap/respond.min.js"></script>
<![endif]-->  


<!-- <script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script> 
<script>window.jQuery || document.write('<script src="jquery/jquery-1.9.1.min.js"><\/script>');</script>
<script type="text/javascript" src="jquery/layer/layer.min.js"></script>	
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script> -->
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
function getSrc(billNo){
	var src;
	if(billNo.substring(0,2)=="EM"){
		src="kyz_findById_layer";
	}
	if(billNo.substring(0,2)=="CM"){
		src="kyzletter_findById_layer";
	}
	if(billNo.substring(0,2)=="BM"){
		src="bussletter_findById_layer";
	}
	if(billNo.substring(0,2)=="RM"){
		src="webremit_findById_layer";
	}
	if(billNo.substring(0,2)=="GJ"){
		src="webformula_findById_layer2";
	}
	return src;
}
function showDiv(billNo,factNo){
	var src=getSrc(billNo);
	
		$("#myModal").on("show.bs.modal",function(){
			$("#myModal").find(".modal-body").load(src,{"billNo":billNo,"factNo":factNo});
			$("#myModalA").find(".modal-body").empty();	//	#myModalA跨層子元素				
		});		
}

/*****************************20151027更新***********************************/

var loadi;
$(document).ajaxStart(function(){
	 loadi=layer.load("請稍等...");
});
$(document).ajaxStop(function(){
	layer.close(loadi);
});


$(function(){	
	if($("#expiredMk").val()=="Y"){
		layer.msg("當前函文鏈接已審核",3,0);
	}
	if($("#isDone").val()=="Y"){
		layer.msg("當前函文已簽核完畢",3,1);
	}
});
</script>	
</body>





</html>
