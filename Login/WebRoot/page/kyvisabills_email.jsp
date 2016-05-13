
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
<script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script> 
<script>window.jQuery || document.write('<script src="jquery/jquery-1.9.1.min.js"><\/script>');</script>	
<!--  <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>-->
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
	return src;
}
function showDiv(billNo,factNo){
	var src=getSrc(billNo);
	
		$("#myModal").on("show.bs.modal",function(){
			$(".modal-body").load(src,{"billNo":billNo,"factNo":factNo});			
		});				
}

/*全局變量*/
var factNo_g,billNo_g,itemNo_g,visaSort_g;

/*****************************20151027更新***********************************/
function check(factNo,visaSort,billNo,itemNo){
	var src=getSrc(billNo);
		 $("#myModalA").on("show.bs.modal",function(){
			 $(".modal-body").load(src,{"billNo":billNo,"factNo":factNo,"visaSort":visaSort,"itemNo":itemNo});
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
	     window.location.href='success.html';
	     document.getElementById("memo").submit();
	     layer.load("正在處理，請稍等...");
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
	     window.location.href='success.html';
	     document.getElementById("memo").submit();
	     layer.load("正在處理，請稍等...");
	  }
	  /* 修改5   20151025 */
}   
    
</script>
</head>
<body>
    <h3 class="single_h3">函文審核</h3>
	<table>							
		<tr>
			<td>					
					<a href="#myModal" data-toggle="modal" data-target="#myModal" onclick="showDiv('<s:property value='vbm.id.billNo'/>','<s:property value='vbm.id.factNo'/>')">
		                <s:property value="vbm.id.billNo"/>
		            </a>					
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
														<a style="color:red"
															href="#myModalA" data-toggle="modal" data-target="#myModalA" onclick="check('<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>',
	              '<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.itemNo'/>')">     
															未審核<s:property value="id.itemNo" />(當前審核人) </a>													
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
															<a style="color:red"
															href="#myModalA" data-toggle="modal" data-target="#myModalA" onclick="check('<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>',
	              '<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.itemNo'/>')">     
															<s:property value="id.itemNo" />(當前審核人) </a>													
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
				<form action="vbm_add" method="post" id="memo" role="form" />
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

</html>
