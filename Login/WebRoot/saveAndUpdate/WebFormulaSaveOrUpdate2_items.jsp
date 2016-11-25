<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
java.util.Date currentTime = new java.util.Date();//得到当前系统时间
String str_date = formatter.format(currentTime); //将日期时间格式化
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'WebMixPersonSaveOrUpdate.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<form action="webformula_addItems" method="post" id="form_items">
		<div>
			<ul class="list" id="ul_item">
			    <li class="columnhead">配方階段</li>
				<li class="columnhead">類別</li>
				<li class="columnhead">原料名稱</li>
				<li class="columnhead">PHR</li>
				<li class="columnhead">重量(KG)</li>
				<li class="col_item4">備註</li>
				
				<s:iterator value="formula.webFormulaItemses" status="x">
				   <li><s:property value='sectionNo'/></li>
				   <li><s:property value="fk_weberp_pf.selfchar1Name"/></li>
				   <li>
				   <s:property value="fk_weberp_pf.namec1"/>&nbsp;&nbsp;&nbsp;
				   <s:property value="fk_weberp_pf.namec2"/>
				   </li>
				   <li >
					<input type="text" name="items[${x.index}].phrVal" value="<s:property value='phrVal'/>"/>
				   </li>
				   <li>
					<input type="text" name="items[${x.index}].weightVal" value="<s:property value='weightVal'/>">
				   </li>
				   <li class="col_item3">
					<input type="text" name="items[${x.index}].remark" value="<s:property value='remark'/>">					
					<input type="hidden" value="<s:property value='itemId'/>" name="items[${x.index}].itemId">
					<input type="hidden" value="<s:property value='fk_weberp_pf.itemid'/>" name="items[${x.index}].fk_weberp_pf.itemid">
					<input type="hidden" value="<s:property value='sectionNo'/>" name="items[${x.index}].sectionNo">
					<input type="hidden" value="<s:property value='webFormula.formulaIndex'/>" name="items[${x.index}].webFormula.formulaIndex">
					<input type="hidden" value="<s:property value='createName'/>" name="items[${x.index}].createName">
					<input type="hidden" value="<s:property value='createDate'/>" name="items[${x.index}].createDate">
					<input type="hidden" value="${loginUser.username}" name="items[${x.index}].modifyName">
					<input type="hidden" value="<%=str_date%>" name="items[${x.index}].modifyDate">
					<img src="images/icon/del_file.png" style="border:0" onclick="removeOneItem(jq(this))"/>
				   </li>	
				</s:iterator>							
			</ul> 
			        <s:if test="formula.webFormulaItemses==null">
			           <input type="hidden" id="num_size" value="0"/>
			        </s:if>
			        <s:else>
			            <input type="hidden" id="num_size" value="<s:property value='formula.webFormulaItemses.size'/>"/>
			        </s:else>                      
		</div>		
		&nbsp;<br><br>
		<center>       
			 <input type="button" id="sub_items" value="確定" class="btn btn-primary" />&nbsp;&nbsp;&nbsp;			 
			 <input type="button"  value="新增配方原料" class="btn btn-primary" onclick="addItem()"/>&nbsp;&nbsp;&nbsp;
			 <input type="button" value="返回"  onclick="javascript:back()" class="btn btn-primary" />			            
		</center>
	</form>


<script type="text/javascript">

	jq(function() {
		var demo = jq("#form_items").Validform({
			btnSubmit : "#sub_items",
			tiptype : 4,
			tipSweep:true,
			showAllError : true,
			datatype : {
				"*0-6" : /^-?\d{1,12}(\.[0-9]{1,3})?$/
			},
			ajaxPost:true,
			beforeSubmit:function(){
			    jq("#dwr_factno").removeAttr("disabled");
		        jq("#dwrFactArea").removeAttr("disabled");
			},
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);				
					loadUrl("/Login/webformula_findPageBean");
				}else{
					layer.msg("提交失敗",3,3);
				}				
			}
		});
		demo.tipmsg.w["*0-6"] = "只能數字且不超過12位數,可保留三位以內小數";				
	});

var ii=0;
function addItem(){
	var formulaIndex=jq("#formulaIndex").val();
	var num_size=parseInt(jq("#num_size").val())+ii;
	alert(num_size);
	var item="";
	var options="";
	for(var j=1;j<8;j++){
		options+="<option value='"+j+"'>"+j+"</option>";
	}	
	item+="<li><select name='items["+num_size+"].sectionNo'>"+options+"</select></li>";
	item+="<li><select id='items_type'></select></li>";
	item+="<li><select id='fk_weberp_pf'></select></li>";
	item+="<li><input type='text' name='items["+num_size+"].phrVal'/></li>"
	item+="<li><input type='text' name='items["+num_size+"].weightVal'/></li>"
	item+="<li class='col_item3'><input type='text' name='items["+num_size+"].remark'/>"
	item+="<input type='hidden' value='"+formulaIndex+"' name='items["+num_size+"].webFormula.formulaIndex' readonly/>";
	item+="<input type='hidden' name='items["+num_size+"].createName' value='${loginUser.username}' readonly/>";
	item+="<input type='hidden' name='items["+num_size+"].createDate' value='"+<%=str_date%>+"'/>";
	item+="<img src='images/icon/del_file.png' style='border:0px' onclick='removeOneItem(jq(this))'/></li>";
	jq("#ul_item").append(item);
	ii++;
}

function removeOneItem(obj){
	obj.parent().prev().prev().prev().prev().prev().prev().remove();
	obj.parent().prev().prev().prev().prev().remove();
	obj.parent().prev().prev().prev().remove();
	obj.parent().prev().prev().remove();
	obj.parent().prev().remove();
	obj.parent().remove();
}


</script>
</body>
</html>
