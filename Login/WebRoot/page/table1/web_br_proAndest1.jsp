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
<title>BR產品設定</title>
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
	<span id="h2_title">BR產品預估作業</span>
	<s:if test='#session.loginUser.userread!="1"'>
	<input type="button" class="btn btn-info" value="添加BR產品" onclick="loadUrl('saveAndUpdate/web_br_productSaveOrUpdate.jsp')"/>&nbsp;
	<input type="button" class="btn btn-info" value="添加BR產品庫存明細" onclick="loadUrl('saveAndUpdate/web_br_productitemSaveOrUpdate.jsp')"/>&nbsp;
	<input type="button" class="btn btn-info" value="添加BR產品預估明細" onclick="loadUrl('saveAndUpdate/web_br_productSaveOrUpdate.jsp')"/>&nbsp;
	</s:if>	
	
	</h3>
				<thead>
					<tr class="tr_show">
						<th>序號</th>
						<th>廠別</th>
						<th>製程</th>
						<th>截止日期</th>
						<th>庫存數(KG)</th>
						<th>已訂購未入廠(KG)</th>
						<th>當月耗用(KG)</th>
						<th>當月實際生產雙數(含不良)</th>
						<th>次一月預估生產雙數</th>
						<th>次二月預估生產雙數</th>
						<th>次三月預估生產雙數</th>																	
						<s:if test='#session.loginUser.userread!="1"'>
							<th>操作</th>
						</s:if>
					</tr>
				</thead>
				<tbody id="tbody">
					<s:iterator value="bean.list" status="x" id="obj">
						<tr>
							<td>${ bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
							<td><s:property value="factNo2.factSname" /></td>
							<td><s:property value="id.factCode" /></td>
							<td><s:property value="id.yymmdd" /></td>
							<td><s:property value="inventory"/></td>
							<td><s:property value="ordernotin"/></td>
							<td><s:property value="actualused"/></td>
							<td><s:property value="actualpairs"/></td>
							<td><s:property value="estimatingpairs1"/></td>
							<td><s:property value="estimatingpairs2"/></td>
							<td><s:property value="estimatingpairs3"/></td>
							


							<s:if test='#session.loginUser.userread!="1"'>
								<td>
									<form  id="subform${x.index}">													
										<input type="hidden" value="<s:property value='id.factNo'/>" name="factNo"/>
										<input type="hidden" value="<s:property value='id.factCode'/>" name="facdCode"/>
										<input type="hidden" value="<s:property value='id.yymmdd'/>" name="yymmdd"/>																				
									</form> 									
									<!--  <form  id="2subform${x.index}" style="float:left">										
										<input type="hidden" value="<s:property value='formulaIndex'/>" name="formulaIndex" />
										<input type="hidden" value="<s:property value='pom.pomNo'/>" name="pomNo" />																														
									</form>
									 <form  id="3subform${x.index}" style="float:left">										
										<input type="hidden" value="<s:property value='formulaIndex'/>" name="formulaIndex" />
										<input type="hidden" value="<s:property value='factNo.factNo'/>" name="factNo" />
										<input type="hidden" value="<s:property value='userId'/>" name="userId"/>
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
									</form>-->																	
								    <a href="javascript:isDelete('subform${x.index}','webbrpro_delete')"  class="btn btn-xs btn-success">
									 刪除
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
}

</script>	
	
</body>
</html>
