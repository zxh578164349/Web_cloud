<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>



<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'backmat1.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
   <div id="container">
   <div id="content">
	<table class="table table-striped table-hover table-bordered"  >
	<h3>
	<s:if test='#session.loginUser.userread!="1"'>
	<input type="button" class="btn btn-info" value="添加" onclick="loadUrl('saveAndUpdate/WebFormulaSaveOrUpdate.jsp')"/>
	</s:if>	
	<span id="h2_title">配方系統</span>
	</h3>
				<thead>
					<tr class="tr_show">
						<th>序號</th>
						<th>配方索引</th>
						<th>廠別</th>
						<th>製程類別</th>
						<th>配方編號</th>
						<th>配方名稱</th>
						<th>倍率</th>
						<th>品牌形體</th>
						<th>帶皮半成品硬度</th>
						<th>成品硬度</th>
						<th>顏色</th>
						<th>發行日期</th>
						<th>品牌指定</th>											
						<s:if test='#session.loginUser.userread!="1"'>
							<th>操作</th>
						</s:if>
					</tr>
				</thead>
				<tbody id="tbody">
					<s:iterator value="bean.list" status="x" id="obj">
						<tr>
							<td>${ bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
							<td><s:property value="formulaIndex" />
							</td>
							<td><s:property value="factNo.factSname" />
							</td>
							<td><s:property value="factCode.name" />
							</td>							
							<td><s:property value="formulaNo" />
							</td>
							<td><s:property value="formulaName" />
							</td>
							<td><s:property value="magnification" />
							</td>
							<td><s:property value="brandBody" />
							</td>
							<td><s:property value="semifinishedProductHardness" />
							</td>
							<td><s:property value="productHardness" />
							</td>
							<td><s:property value="color" />
							</td>
							<td><s:property value="issuedDate" />
							</td>
							<td>
							   <s:if test='assignBrand=="1"'>
							                  指定
							   </s:if>
							   <s:else>
							                  非指定 
							   </s:else>							
							</td>
												
							<s:if test='#session.loginUser.userread!="1"'>
								<td>
									<form  id="subform${x.index}">										
										<input type="hidden" value="<s:property value='formulaIndex'/>" name="formulaIndex" />																					
									</form> 									
									<form  id="2subform${x.index}" style="float:left">										
										<input type="hidden" value="<s:property value='formulaIndex'/>" name="formulaIndex" />
										<input type="hidden" value="<s:property value='pom.pomNo'/>" name="pomNo" />																														
									</form>
									 <form  id="3subform${x.index}" style="float:left">										
										<input type="hidden" value="<s:property value='formulaIndex'/>" name="formulaIndex" />
										<input type="hidden" value="<s:property value='factNo.factNo'/>" name="factNo" />
										<input type="hidden" value="look" name="lookordown"/>																														
									</form>
									<form  id="4subform${x.index}" action="webformula_print2" method="post" style="float:left" target="_blank">										
										<input type="hidden" value="<s:property value='formulaIndex'/>" name="formulaIndex" />
										<input type="hidden" value="<s:property value='factNo.factNo'/>" name="factNo" />
										<input type="hidden" value="look" name="lookordown"/>																														
									</form>
									<form  id="5subform${x.index}" action="webformula_print2" method="post" style="float:left" target="_blank">										
										<input type="hidden" value="<s:property value='formulaIndex'/>" name="formulaIndex" />
										<input type="hidden" value="<s:property value='factNo.factNo'/>" name="factNo" />
										<input type="hidden" value="down" name="lookordown"/>																														
									</form>
									<s:if test="vbm==null">
									<a href="javascript:findById_form('subform${x.index}','webformula_findById')" class="btn btn-xs btn-success">
									  修改									
								    </a>
								    <a href="javascript:isDelete2('2subform${x.index}','webformula_delete','webformula_findPageBean3')"  class="btn btn-xs btn-success">
									 刪除
								    </a>
									<a href="javascript:addvbm('3subform${x.index}')" class="btn btn-xs btn-success">送簽</a>																		
									</s:if>
									<s:else>
									 																									   									 
									 <s:if test='#session.loginUser.adminMk=="Y"'>
									   <a href="javascript:findById_form('subform${x.index}','webformula_findById')" class="btn btn-xs btn-success">
									             修改									
								       </a>
									   <a href="javascript:isDelete2('2subform${x.index}','webformula_delete','webformula_findPageBean3')"  class="btn btn-xs btn-success">
									          刪除
									   </a>
									 </s:if>
									 <s:else>
									    <a href="#" class="btn btn-xs disabled btn-warning"> 鎖定 </a>
									    <a href="#" class="btn btn-xs disabled btn-warning">刪除	</a>
									 </s:else>
									 <a href="#" class="btn btn-xs disabled btn-warning">已送</a>	
									</s:else>
																									 								    
								    <a href="javascript:document.getElementById('4subform${x.index}').submit()" class="btn btn-xs btn-success">預覽</a>
								    <a href="javascript:document.getElementById('5subform${x.index}').submit()" class="btn btn-xs btn-success">下載</a>
								    <a href="webtabpom_findByIdfiles?pomNo=${obj.pom.pomNo}" class="btn btn-xs btn-success" target="_blank">
					                                             附檔</a>
								</td>
							</s:if>
							
						</tr>

					</s:iterator>

				</tbody>

			</table>
  </div>	
</div>
<jsp:include page="pagenation.jsp" flush="true"/>		


<script type="text/javascript">
function addvbm(subform){	
	/*var pageii = jq.layer({
		  type: 1,
		  title: false,
		  area: ['auto', 'auto'],
		  border: [0], //去掉默认边框
		  shade: [0], //去掉遮罩
		  closeBtn: [0, false], //去掉默认关闭按钮
		  shift: 'left', //从左动画弹出
		  page: {
		    html: div
		  }
		});*/
		jq.ajax({
		  type:'post',
		  dataType:'json',
		  data:jq("#"+subform).serialize(),
		  url:'webformula_sendEmail',
		  success:function(data){
			  if(data.length==0){
				  layer.msg("還沒有建立配方送簽流程,暫時不能送簽",3,3);	
			  }else if(data[0]=='1'){				  
				  layer.msg("送簽失敗",3,3);  
			  }else{
				  layer.msg("送簽成功",3,1);
				  loadUrl_bodyid("webformula_findPageBean3");
			  }		     
		  } 
		});
		//自设关闭
		/*jq('#pagebtn').on('click', function(){
		  layer.close(pageii);
		});*/
}

</script>	
	
</body>
</html>
