<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
									<%--<form action="weballobj_findWloById" method="post" id="subform${x.index}">										
										<input type="hidden" value="<s:property value='formulaIndex'/>" name="formulaIndex" />																					
									</form> 
									<a href="javascript:findById('subform${x.index}','webwlo_findWloById')">
									<img alt="修改" src="images/icon/edit001.png" title="修改">										
								    </a>--%>
									<form  id="2subform${x.index}" style="float:left">										
										<input type="hidden" value="<s:property value='formulaIndex'/>" name="formulaIndex" />																														
									</form> 
									<a href="javascript:addvbm('${obj.factNo.factNo }')" class="btn btn-sm">送簽</a>
									<a href="javascript:isDelete2('2subform${x.index}','webformula_delete','webformula_findPageBean3')" >
									<img alt="刪除" src="images/icon/delete001.png" title="刪除">
								    </a>
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
function addvbm(factNo){	
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
		  data:{factNo:factNo},
		  url:'webtype_findPF',
		  success:function(data){
			  if(data==null){
				  layer.msg("還沒有建立配方送簽流程,不能送簽",3,3);
			  }else if(data=='1'){
				  layer.msg("送簽失敗",3,3);  
			  }else{
				  layer.msg("送簽成功",3,1);
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
