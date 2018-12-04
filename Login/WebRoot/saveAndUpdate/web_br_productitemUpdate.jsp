<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd-hh");
java.util.Date currentTime = new java.util.Date();//得到当前系统时间
String str_date = formatter.format(currentTime); //将日期时间格式化
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'WebMixPersonSaveOrUpdate.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />
<style type="text/css">
  .div_border_green{
    width:95%;height:250px;overflow:auto;text-align:left;font-size: 12px
  }
  .div_border_blue{
    width:95%;height:250px;overflow:auto;text-align:left;font-size: 12px
  }
  
#myTabContent_item ul{margin:0px;padding:0px}
.list_item,.list_item2{margin:0px 0px; width:100%;}  
.list_item li.columnhead,.list_item li.columnhead2{font-size: 12px;font-weight:bold;}  
.list_item li,.list_item li.columnhead{  
    width:25%;height:28px;text-align:left;float:left;margin:0px 0px;list-style:none; border:1px solid ; 
}
.list_item li.column2,.list_item li.columnhead2{  
    width:25%;height:28px;text-align:left;float:left;margin:0px 0px;list-style:none; border:1px solid ; 
} 
.list_item2 li,.list_item2 li.columnhead{  
    width:20%;height:28px;text-align:left;float:left;margin:0px 0px;list-style:none; border:1px solid ; 
}
.list_item2 li.column2,.list_item2 li.columnhead2{  
    width:20%;height:28px;text-align:left;float:left;margin:0px 0px;list-style:none; border:1px solid ; 
}  
.list_item li input[type=text],.list_item li select,.list_item2 li input[type=text],.list_item2 li select{  
   width:100% ; 
}
 
</style>
</head>

<body>
	<form action="webbrpro_update_pro" method="post" id="form_main">	
	   <div class="panel panel-default">	    			
			<div class="panel-heading"><label>產品庫存</label></div>
			<div class="panel-body">
			    <table class="table table-striped">
			        <tr>
			           <th>產品名稱</th><th>庫存數(KG)</th><th>已訂購未入廠(KG)</th><th>當月耗用(KG)</th>
			        </tr>
			        
			        <tr>
			           <td>
			           <s:property value="pro.id.webBrProduct.namec1"/>&nbsp;&nbsp;
			           <s:property value="pro.id.webBrProduct.namec2"/>
			           </td>
			           <td><input type="text" name="pro.inventory" value="<s:property value='pro.inventory'/>" datatype="*,*8-2"/></td>
			           <td><input type="text" name="pro.orderNotin" value="<s:property value='pro.orderNotin'/>" datatype="*,*8-2"/></td>
			           <td><input type="text" name="pro.actualUsed" value="<s:property value='pro.actualUsed'/>" datatype="*,*8-2"/></td>
			           <input type="hidden" name="pro.id.webBrProduct.id.webErpProductinFormation.itemid" value="<s:property value='pro.id.webBrProduct.id.webErpProductinFormation.itemid'/>" />
			           <input type="hidden" name="pro.id.webBrProduct.id.factNo" value="<s:property value='pro.id.webBrProduct.id.factNo'/>"/>
			           <input type="hidden" name="pro.id.yymmdd" value="<s:property value='pro.id.yymmdd'/>"/>
			           <input type="hidden" name="pro.createUser.id" value="<s:property value='pro.createUser.id'/>"/>
			           <input type="hidden" name="pro.createDate" value="<s:property value='pro.createDate'/>"/>
			           <input type="hidden" name="pro.editUser.id" value="${loginUser.id}"/>
			           <input type="hidden" name="pro.editDate" value="<%=str_date%>"/>			           
			        </tr>
			    </table>			 
			</div>							
			</div>
			
		
		
        
		<center>
	        <input type="button" id="sub_main" value="確定" class="btn btn-primary" />&nbsp;&nbsp;&nbsp;			              
			<input type="button" value="返回"  onclick="javascript:back_main('webbrpro_findPageBean_pro3?backIndex=1')" class="btn btn-primary" />	
			<span id="msgtip" style="margin-left:30px;"></span>		            
		</center>																								     
	</form>


<script type="text/javascript">
	jq(function() {	
		var demo = jq("#form_main").Validform({
			btnSubmit : "#sub_main",
			tiptype : 4,
			tipSweep:true,
			//showAllError : true,			
			datatype : {
				"*8-2" : /^-?\d{0,8}(\.[0-9]{0,2})?$/,
				"*9-5" : /^-?\d{0,9}(\.[0-9]{0,2})?$/		
			},
			ajaxPost:true,
			beforeSubmit:function(){
			    jq("#dwr_factno").removeAttr("disabled");
			},
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);				
					loadUrl("webbrpro_findPageBean_pro");
					//loadUrl("webbrpro_findById?formulaIndex="+jq("#formulaIndex").val());
				}
				if(data=="1"){
					layer.msg("提交失敗",3,3);
					jq("#msgtip").text("");
				}
				if(data=="3"){
				    layer.msg("保存附檔失敗",3,3);
				    jq("#msgtip").text("");
				}				
			}
		});
		demo.tipmsg.w["*8-2"] = "只能數字且不超過8位數,可保留2位以內小數";
		demo.tipmsg.w["*9-5"] = "只能數字且不超過9位數,可保留5位以內小數";											
	});	
	
	
</script>
</body>
</html>
