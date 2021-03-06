
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML>
<html>
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<label>BR消耗進度表</label>
		</div>
		<div class="panel-body">
			<form id="public_form" method="post">
				<table id="tb_search">
					<tr>
						<td>
							<!--<s:if test="#session.factNo=='tw'">
					<select name="factNo" id="factNo" class="search">
						<option value="nothing">請選擇廠別</option>
						<option value="">全部</option>						
						<s:iterator value="#session.facts" id="temp">
							<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>								
						</s:iterator>
					</select>
					
				</s:if> 
				<s:else>				
					<select name="factNo" id="factNo" class="search">						
						<option value="<s:property value="#session.factNo"/>">
							<s:property value="#session.factName" />(<s:property value="#session.factNo"/>)
						</option>
					</select>					
				</s:else>--> 
				     <select name="factCode" datatype="*" id="factcode"></select></td>
						<td>截止日期</td>
						<td><input type="text" name="yymmdd" id="yymmdd"
							onclick="WdatePicker({dateFmt:'yyyyMMdd',maxDate:'%y-%M-%d',opposite:true,disabledDates:['....0228','......30']})" class="Wdate" />
						</td>
						<td>預估月數</td>
						<td><input type="text" name="months" />
						</td>
						<td>
						   <input value="搜索"	type="button" class="btn btn-primary" onclick="print('public_form','webbrpro_findByfactCodeAndfactNoAndYymmdd_print2_inline')"/>&nbsp;
						   <input value="Excel" type="button" class="btn btn-primary" onclick="print('public_form','webbrpro_findByfactCodeAndfactNoAndYymmdd_print2_down')" />
						   
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<label>每月生產雙數表</label>					
		</div>
		<div class="panel-body">
		    <form id="public_form2" method="post">
		    <table id="tb_search">
		      <tr>
						<td>截止日期: <input type="text" name="yymmdd" id="yymmdd2"
							onclick="WdatePicker({dateFmt:'yyyyMMdd',maxDate:'%y-%M-%d',opposite:true,disabledDates:['....0228','......30']})" class="Wdate" />~
							<input type="text" name="yymmdd2" id="yymmdd3"
							onclick="WdatePicker({dateFmt:'yyyyMMdd',maxDate:'%y-%M-%d',opposite:true,disabledDates:['....0228','......30']})" class="Wdate" />
							&nbsp;&nbsp;
						</td>
						<td><input value="搜索" type="button" class="btn btn-primary"
							onclick="print2('public_form2','webbrpro_findEstByYymmdd_print_inline')" /> &nbsp; <input value="Excel" type="button"
							class="btn btn-primary" onclick="print2('public_form2','webbrpro_findEstByYymmdd_print_down')" />
						</td>
					</tr>
		    </table>						
			</form>
		</div>
	</div>

	<script type="text/javascript">
jq.ajax({
	type:"post",
	dataType:"json",
	url:"webfact_findfactarea",
	success:function(data){
		var item;
		jq("#factcode").empty();
		jq("#factcode").append("<option value=''>請選擇製程類別</option><option value='all'>全部</option>");
		jq.each(data,function(i,obj){
			item="<option value='"+obj+"'>"+obj+"</option>";
			jq("#factcode").append(item);
		});
	}
});

function print(subform,action){
	var subform=jq("#"+subform);
	subform.attr("action",action);
	subform.attr("target","_blank");
	if(jq("#factcode").val()==""||jq("#yymmdd").val()==""){
		layer.alert("請選擇製程和截止日期");
		return false;
	}else{
		subform.submit();
	}	
}

function print2(subform,action){
	var subform=jq("#"+subform);
	subform.attr("action",action);
	subform.attr("target","_blank");
	if(jq("#yymmdd2").val()==""||jq("#yymmdd3").val()==""){
		layer.alert("請選擇截止日期區間");
		return false;
	}else{
		subform.submit();
	}
	
}
</script>	
</body>
</html>
