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
	<h2>
	<s:if test='#session.loginUser.userread!="1"'>
	<!--<input type="button" class="btn btn-info" value="添加" onclick="loadUrl('saveAndUpdate/fixedSaveOrUpdate.jsp')"/>-->	
	</s:if>	
	基本數據導入
	</h2>
				<thead>
					<tr class="tr_show">
						<th>序號</th>
						<th>廠別</th>
						<th>廠別狀態</th>
						<th>日期</th>
						<th>銷貨總收入</th>
						<th>請款雙數</th>
						<th>雙數請款金額</th>
						<th>客補請款雙數</th>
						<th>客補請款金額</th>
						<th>樣品請款雙數</th>
						<th>樣品請款金額</th>
						<th>應收帳款過期金額</th>
						<th>客戶扣款金額</th>
						<th>銷售成本金額</th>
						<th>當月生產成本金額</th>
						<th>生產產值</th>
						<th>主材料成本</th>
						<th>色料藥品成本</th>
						<th>不良品(損耗)成本</th>
						<th>當月實際耗料</th>
						<th>當月生產膠底總毛重</th>
						<th>當月生產膠底總淨重</th>
						<th>非膠底產品耗用重量</th>
						<th>直工發薪人數</th>
						<th>間工發薪人數</th>
						<th>直工正班工時</th>
						<th>間工正班工時</th>
						<th>直工加班工時</th>
						<th>間工加班工時</th>
						<th>直工離職人數</th>
						<th>間工離職人數</th>
						<th>工傷件數</th>
						<th>直接應領薪資</th>
						<th>間接應領薪資</th>
						<th>直工加班費</th>
						<th>間工加班費</th>
						<th>獎金金額</th>
						<th>其加金額</th>
						<th>費用合計</th>
						<th>其它收入</th>
						<th>汽車加油費</th>
						<th>快遞費</th>
						<th>雜項購置</th>
						<th>雜項支出-其他</th>
						<th>電腦耗材</th>
						<th>文具用品類</th>
						<th>修繕類-機器設備</th>
						<th>修繕費-其它類</th>
						<th>車輛維修費</th>
						<th>服裝費</th>
						<th>清潔/消毒費</th>
						<th>工程整改費</th>
						<th>工傷費用</th>
						<th>模具修理費用</th>
						<th>差旅費</th>
						<th>交際費</th>
						<th>包裝費用</th>
						<th>用水量</th>
						<th>用水金額</th>
						<th>用電量</th>
						<th>用電金額</th>
						<th>用蒸汽量</th>
						<th>用蒸汽金額</th>
						<th>用祡油量</th>
						<th>用柴油金額</th>
						<th>邊料重量</th>
						<th>邊料金額</th>
						<th>不良雙數</th>
						<th>不良雙數折算金額</th>
						<th>不良重量</th>
						<th>不良重量金額</th>
						<th>其它報廢重量</th>
						<th>其它報廢折算金額</th>
						<th>成倉庫存</th>
						<th>無訂單庫存</th>
						<th>整理庫存</th>
						<th>已出未請雙數</th>
						<th>已請未出雙數</th>
						<th>前倉入庫折算可請款雙數</th>
						<th>生產與請款差異</th>
						<th>退貨數</th>
						<th>總原料庫存量</th>
						<th>總原料庫存金額</th>
						<th>膠類庫存量</th>
						<th>膠類庫存金額</th>
						<th>呆料庫存量</th>
						<th>呆料庫存金額</th>
						<th>色料用量</th>
						<th>色料金額</th>
						<th>藥品用量</th>
						<th>藥品金額</th>
						<th>離型劑用量</th>
						<th>離型劑用量金額</th>
						<th>防霜劑用量</th>
						<th>防霜劑金額</th>
						<th>防粘劑用量</th>
						<th>防粘劑金額</th>
						<th>油漆處理劑用量</th>
						<th>油漆處理劑金額</th>
						<th>白色回收粉</th>
						<th>黑色回收粉</th>
						<th>生膠回收粉</th>
						<th>灰色回收粉</th>
						<th>其它回收粉</th>
						<th>粗坯用量</th>
						<th>裁斷回頭料</th>
						<th>油壓退料</th>
						<th>利潤率<font style="font-style:italic" color="red">(new)</font></th>
						<s:if test='#session.loginUser.userread!="1"'>
							<th>操作</th>
						</s:if>
					</tr>
				</thead>
				<tbody id="tbody">
					<s:iterator value="bean.list" status="x" id="temp">
						<tr>
							<td>${ bean.pageSize*(bean.currentPage-1)+x.index+1}</td>
							<td><s:property value="id.fact.factSname" />
							</td>
							<td><s:property value="id.fact.id.factArea" />
							</td>
							<td><s:property value="id.yymm" />
							</td>
							<td><s:property value="objA100" />
							</td>
							<td><s:property value="objA101" />
							</td>
							<td><s:property value="objA102" />
							</td>
							<td><s:property value="objA103" />
							</td>
							<td><s:property value="objA104" />
							</td>
							<td><s:property value="objA105" />
							</td>
							<td><s:property value="objA106" />
							</td>
							<td><s:property value="objA107" />
							</td>
							<td><s:property value="objA108" />
							</td>
							<td><s:property value="objA109" />
							</td>
							<td><s:property value="objA110" />
							</td>
							<td><s:property value="objA111" />
							</td>
							<td><s:property value="objA112" />
							</td>
							<td><s:property value="objA113" />
							</td>
							<td><s:property value="objA114" />
							</td>
							<td><s:property value="objA115" />
							</td>
							<td><s:property value="objA116" />
							</td>
							<td><s:property value="objA117" />
							</td>
							<td><s:property value="objA118" />
							</td>
							<td><s:property value="objA119" />
							</td>
							<td><s:property value="objA120" />
							</td>
							<td><s:property value="objA121" />
							</td>
							<td><s:property value="objA122" />
							</td>
							<td><s:property value="objA123" />
							</td>
							<td><s:property value="objA124" />
							</td>
							<td><s:property value="objA125" />
							</td>
							<td><s:property value="objA126" />
							</td>
							<td><s:property value="objA127" />
							</td>
							<td><s:property value="objA128" />
							</td>
							<td><s:property value="objA129" />
							</td>
							<td><s:property value="objA130" />
							</td>
							<td><s:property value="objA131" />
							</td>
							<td><s:property value="objA132" />
							</td>
							<td><s:property value="objA133" />
							</td>
							<td><s:property value="objA134" />
							</td>
							<td><s:property value="objA135" />
							</td>
							<td><s:property value="objA136" />
							</td>
							<td><s:property value="objA137" />
							</td>
							<td><s:property value="objA138" />
							</td>
							<td><s:property value="objA139" />
							</td>
							<td><s:property value="objA140" />
							</td>
							<td><s:property value="objA141" />
							</td>
							<td><s:property value="objA142" />
							</td>
							<td><s:property value="objA143" />
							</td>
							<td><s:property value="objA144" />
							</td>
							<td><s:property value="objA145" />
							</td>
							<td><s:property value="objA146" />
							</td>
							<td><s:property value="objA147" />
							</td>
							<td><s:property value="objA148" />
							</td>
							<td><s:property value="objA149" />
							</td>
							<td><s:property value="objA150" />
							</td>
							<td><s:property value="objA151" />
							</td>
							<td><s:property value="objA152" />
							</td>
							<td><s:property value="objA153" />
							</td>
							<td><s:property value="objA154" />
							</td>
							<td><s:property value="objA155" />
							</td>
							<td><s:property value="objA156" />
							</td>
							<td><s:property value="objA157" />
							</td>
							<td><s:property value="objA158" />
							</td>
							<td><s:property value="objA159" />
							</td>
							<td><s:property value="objA160" />
							</td>
							<td><s:property value="objA161" />
							</td>
							<td><s:property value="objA162" />
							</td>
							<td><s:property value="objA163" />
							</td>
							<td><s:property value="objA164" />
							</td>
							<td><s:property value="objA165" />
							</td>
							<td><s:property value="objA166" />
							</td>
							<td><s:property value="objA167" />
							</td>
							<td><s:property value="objA168" />
							</td>
							<td><s:property value="objA169" />
							</td>
							<td><s:property value="objA170" />
							</td>
							<td><s:property value="objA171" />
							</td>
							<td><s:property value="objA172" />
							</td>
							<td><s:property value="objA173" />
							</td>
							<td><s:property value="objA174" />
							</td>
							<td><s:property value="objA175" />
							</td>
							<td><s:property value="objA176" />
							</td>
							<td><s:property value="objA177" />
							</td>
							<td><s:property value="objA178" />
							</td>
							<td><s:property value="objA179" />
							</td>
							<td><s:property value="objA180" />
							</td>
							<td><s:property value="objA181" />
							</td>
							<td><s:property value="objA182" />
							</td>
							<td><s:property value="objA183" />
							</td>
							<td><s:property value="objA184" />
							</td>
							<td><s:property value="objA185" />
							</td>
							<td><s:property value="objA186" />
							</td>
							<td><s:property value="objA187" />
							</td>
							<td><s:property value="objA188" />
							</td>
							<td><s:property value="objA189" />
							</td>
							<td><s:property value="objA190" />
							</td>
							<td><s:property value="objA191" />
							</td>
							<td><s:property value="objA192" />
							</td>
							<td><s:property value="objA193" />
							</td>
							<td><s:property value="objA194" />
							</td>
							<td><s:property value="objA195" />
							</td>
							<td><s:property value="objA196" />
							</td>
							<td><s:property value="objA197" />
							</td>
							<td><s:property value="objA198" />
							</td>
							<td><s:property value="objA199" />
							</td>
							<td><s:property value="objA200" />
							</td>
							<td><s:property value="objA201" />
							</td>
							<td><s:property value="objA202" />
							</td>
							<td><s:property value="objA203" />
							</td>
							<s:if test='#session.loginUser.userread!="1"'>
								<td>
									<%--<form action="weballobj_findWloById" method="post" id="subform${x.index}">										
										<input type="hidden" value="<s:property value='id.fact.id.factNo'/>" name="factNo" />
										<input type="hidden" value="<s:property value='id.fact.id.factCode'/>" name="factCode" />											
										<input type="hidden" value="<s:property value='id.yymm'/>" name="yymm" />											
									</form> 
									<a href="javascript:findById('subform${x.index}','webwlo_findWloById')">
									<img alt="修改" src="images/icon/edit001.png" title="修改">										
								    </a>--%>
									<form action="weballobj_delete" method="post" id="2subform${x.index}" style="float:left">										
										<input type="hidden" value="<s:property value='id.fact.id.factNo'/>" name="factNo" />
										<input type="hidden" value="<s:property value='id.fact.id.factArea'/>" name="factCode" />											
										<input type="hidden" value="<s:property value='id.yymm'/>" name="yymm" />											
									</form> 
									<a href="javascript:isDelete('2subform${x.index}','weballobj_delete')" >
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
	
	
</body>
</html>
