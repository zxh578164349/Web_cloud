<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyyMMdd");
java.text.SimpleDateFormat fmt2 = new java.text.SimpleDateFormat("yyyyMMdd_hh");
java.util.Date currentTime = new java.util.Date();//得到当前系统时间
String str_date = fmt.format(currentTime); //将日期时间格式化
String str_date_h = fmt2.format(currentTime); //将时间格式化
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'Yield_prediction.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />


</head>



<body>
	<form action="webcolpro_addItem" method="post" id="form">	
		<table class="table table-condensed">
		<tbody id="tb_list_info2">						
				<tr>
					<td >申請單號</td>
					<td >
					<input type="text" name="item.webColproductMain.billNo" value="<s:property value='item.webColproductMain.billNo'/>"  readonly style="color:blue" />
					
					</td>
					<td >重要性</td>
					<td >
					    <select name="item.importmant">
					     <s:if test='item.importmant=="H"'>
					     <option value="H" selected>高</option>
					     </s:if>
					     <s:else>
					     <option value="H">高</option>
					     </s:else>
					     <s:if test='item.importmant=="M"'>
					     <option value="M" selected>中</option>
					     </s:if>
					     <s:else>
					     <option value="M">中</option>
					     </s:else>
					     <s:if test='item.importmant=="L"'>
					     <option value="L" selected>低</option>
					     </s:if>
					     <s:else>
					     <option value="L">低</option>
					     </s:else>			        			        
			            </select>
					</td>
				</tr>
				<tr>
					<td >型體</td>
					<td ><input type="text" name="item.shape" value="<s:property value='item.shape'/>"/></td>
					<td >結構</td>
					<td >
					<input type="text" value="<s:property value='item.CStructure'/>" name="item.CStructure" />					 
					<input type="hidden" value="notnull" name="isnull" /></td>
				</tr>			
			<tr>
				<td >樣品用途</td>
				<td >
				<input type="text" value="<s:property value='item.purpose'/>" name="item.purpose" />
				</td>
				<td >數量</td>
				<td ><input type="text" value="<s:property value='item.numbers'/>" name="item.numbers" />
				</td>
			</tr>
			<tr>
				<td >單重</td>
				<td ><input type="text" value="<s:property value='item.weight'/>" name="item.weight" />
				</td>
				<td >留底量</td>
				<td ><input type="text" value="<s:property value='item.remainNum'/>" name="item.remainNum" />
				</td>

			</tr>
			<tr>
				<td >不良</td>
				<td ><input type="text" value="<s:property value='item.unhealthNum'/>" name="item.unhealthNum" />
				</td>
				<td >型體負責人</td>
				<td ><input type="text" value="<s:property value='item.picMan'/>" name="item.picMan" /></td>

			</tr>
			
			<tr>
			  <td >可否請款</td>
					<td ><section name="item.paymk">
					<s:if test='item.paymk=="Y"'>
					<option value="Y" selected>是</option>
					</s:if>
					<s:else>
					<option value="Y">是</option>
					</s:else>
			        <s:if test='item.paymk=="N"'>
					<option value="N" selected>否</option>
					</s:if>
					<s:else>
					<option value="N">否</option>
					</s:else>			       
			     </section>
				</td>
			　<td >量產數量</td>
					<td ><input type="text" value="<s:property value='item.numbersb'/>" name="item.numbersb" />
					 </td>
			</tr>
			<tr>
			  <td >需求料的重量</td>
					<td ><input type="text" value="<s:property value='item.weightb'/>" name="item.weightb" />
					 </td>
			　<td >備註</td>
					<td >
					<input type="text" value="<s:property value='item.remarks'/>" name="item.remarks" />
					
					<input type="hidden" name="item.iid" value="<s:property value='item.iid'/>"/>
					<input type="hidden" name="item.webColproductMain.webUserByUpdateUserFid.id" value="<s:property value='#session.loginUser.id'/>"/>
					<input type="hidden" name="item.webColproductMain.updateDate" value="<s:property value='#session.loginUser.id'/>"/>
					 </td>
			</tr>
			
         </tbody>
		</table>
		<center>
			<input type="submit" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; 
			<input type="reset" id="reset" value="重置" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;				
			<input type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary"/>

		</center>
	</form>


<script type="text/javascript">

	jq(function() {
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			tipSweep:true,
			showAllError : true,
			datatype : {
				"*0-9" : /^-?\d{1,9}(\.[0-9]{1,3})?$/,
				"*0-7" : /^-?\d{1,7}(\.[0-9])?$/,
				"*1-9" : /^-?[1-9]{1}\d{1,8}(\.[0-9]{1,3})?$/
			},
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					loadUrl("webcolpro_findPageBean");
					//location.href="webcolpro_findPageBean";
				}
				if(data=="2"){
					layer.msg("數據已存在",3,3);
				}
				if(data=="1"){
					layer.msg("提交失敗",3,3);
				}								
			}
		});
		demo.tipmsg.w["*0-9"] = "只能數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["*0-7"] = "只能數字且不超過7位數,可保留一位小數";
		demo.tipmsg.w["*1-9"] = "十位以上數字且不超過9位數,可保留三位小數";
	});
	
	function back() {
		    loadUrl("webcolpro_findPageBean3?backIndex=1");	
	} 
      /*禁止空格輸入*/
jq(function(){
	goTrim();
}); 
</script>
</body>
</html>
