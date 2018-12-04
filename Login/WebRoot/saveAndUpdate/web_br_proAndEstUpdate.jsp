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
	<form action="webbrpro_add2_3" method="post" id="form_main">	
	   <div class="panel panel-default">	    			
			<div class="panel-heading"><label>產品庫存</label></div>
			<div class="panel-body">
			    <table class="table table-striped">
			        <tr>
			           <th>產品名稱</th><th>庫存數(KG)</th><th>已訂購未入廠(KG)</th><th>當月耗用(KG)</th>
			        </tr>
			        <s:iterator value="listitem" id="obj" status="x">
			        <tr>
			           <td>
			           <s:property value="id.webBrProduct.namec1"/>&nbsp;&nbsp;
			           <s:property value="id.webBrProduct.namec2"/>
			           </td>
			           <td><input type="text" name="listitem[${x.index}].inventory" value="<s:property value='inventory'/>" datatype="*,*8-2"/></td>
			           <td><input type="text" name="listitem[${x.index}].orderNotin" value="<s:property value='orderNotin'/>" datatype="*,*8-2"/></td>
			           <td><input type="text" name="listitem[${x.index}].actualUsed" value="<s:property value='actualUsed'/>" datatype="*,*8-2"/>
			           <input type="hidden" name="listitem[${x.index}].id.webBrProduct.id.webErpProductinFormation.itemid" value="<s:property value='id.webBrProduct.id.webErpProductinFormation.itemid'/>" />
			           <input type="hidden" name="listitem[${x.index}].id.webBrProduct.id.factNo" value="<s:property value='id.webBrProduct.id.factNo'/>"/>
			           <input type="hidden" name="listitem[${x.index}].id.yymmdd" value="<s:property value='id.yymmdd'/>"/>
			           <input type="hidden" name="listitem[${x.index}].createUser.id" value="<s:property value='createUser.id'/>"/>
			           <input type="hidden" name="listitem[${x.index}].createDate" value="<s:property value='createDate'/>"/>
			           <input type="hidden" name="listitem[${x.index}].editUser.id" value="${loginUser.id}"/>
			           <input type="hidden" name="listitem[${x.index}].editDate" value="<%=str_date%>"/>
			           </td>
			           <td>
			              <input type="hidden" value="<s:property value='id.webBrProduct.id.factNo'/>"  id="fno${x.index}"/>
			              <input type="hidden" value="<s:property value='id.yymmdd'/>"  id="ymd${x.index}"/>
			              <input type="hidden" value="<s:property value='id.webBrProduct.id.webErpProductinFormation.itemid'/>"  id="wid${x.index}"/>
			              <input type="button" value="刪除" onclick="removeOneItem_pro('webbrpro_delete_pro_json',this,'fno${x.index}','ymd${x.index}','wid${x.index}')"/>
			           </td>			           
			        </tr>
			        </s:iterator>
			    </table>			 
			</div>	
			
			<div class="panel-heading"><label>產品預估</label></div>
			<div class="panel-body">
			    <table class="table table-striped">
			        <tr>
			           <th>製程</th><th>當月實際生產雙數(含不良)</th><th>次一月預估生產雙數</th><th>次二月預估生產雙數</th><th>次三月預估生產雙數</th>
			        </tr>
			        <s:iterator value="listest" status="x">
			           <tr>
			              <td><input type="text" value="<s:property value='id.factCode'/>" name="listest[${x.index}].id.factCode" readonly/></td>
			              <td><input type="text" value="<s:property value='actualPairs'/>" name="listest[${x.index}].actualPairs" datatype="*,*8-2"/></td>
			              <td><input type="text" value="<s:property value='estimatingPairs1'/>" name="listest[${x.index}].estimatingPairs1" datatype="*,*8-2"/></td>
			              <td><input type="text" value="<s:property value='estimatingPairs2'/>" name="listest[${x.index}].estimatingPairs2" datatype="*,*8-2"/></td>
			              <td><input type="text" value="<s:property value='estimatingPairs3'/>" name="listest[${x.index}].estimatingPairs3" datatype="*,*8-2"/></td>
			              <td><input type="hidden" value="<s:property value='createUser.id'/>" name="listest[${x.index}].createUser.id"/>
			              <input type="hidden" value="<s:property value='createDate'/>" name="listest[${x.index}].createDate"/>
			              <input type="hidden" value="${loginUser.id}" name="listest[${x.index}].editUser.id"/>
			              <input type="hidden" value="<%=str_date%>" name="listest[${x.index}].editDate"/>
			              <input type="hidden" value="<s:property value='id.factNo'/>" name="listest[${x.index}].id.factNo"/>
			              <input type="hidden" value="<s:property value='id.yymmdd'/>" name="listest[${x.index}].id.yymmdd"/>			              			             
			              </td>
			              <td>			                			                  
			                  <input type="hidden" value="<s:property value='id.factNo'/>"   id="factNo${x.index}"/>
			                  <input type="hidden" value="<s:property value='id.factCode'/>"  id="factCode${x.index}"/>
			                  <input type="hidden"  value="<s:property value='id.yymmdd'/>"  id="yymmdd${x.index}"/>
			                  <input type="button"  value="刪除" onclick="removeOneItem_est('webbrpro_delete_est_json',this,'factNo${x.index}','factCode${x.index}','yymmdd${x.index}')"/>
			              </td>
			           </tr>
			        </s:iterator>
			        
			    </table>
			</div>
			</div>
			
		
		
        
		<center>
	        <input type="button" id="sub_main" value="確定" class="btn btn-primary" />&nbsp;&nbsp;&nbsp;			              
			<input type="button" value="返回"  onclick="javascript:back_main('webbrpro_findPageBean_proAndest3?backIndex=1')" class="btn btn-primary" />	
			<span id="msgtip" style="margin-left:30px;"></span>		            
		</center>																								     
	</form>

<script type="text/javascript">
	jq(function() {	
		var demo = jq("#form_main").Validform({
			btnSubmit : "#sub_main",
			//tiptype : 4,
			//tipSweep:true,
			//showAllError : true,
			tiptype : function(msg,o,cssctl){
				var objtip=jq("#msgtip");
				cssctl(objtip,o.type);
				objtip.text(msg);
			},
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
					loadUrl("webbrpro_findPageBean_proAndest");
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
	

	


var index=0;
var item_nums=0;

function checkproduct(){
	var factno=jq("#dwr_factno").val();
	jq.ajax({
		type:"post",
		dataType:"json",
		data:{factNo:factno},
		url:"webbrpro_findByFactNo2",
		success:function(data){
			if(data=="0"){
				layer.alert("請設定BR產品",0);	
				jq(".list_item").empty();
				jq(".list_item2").empty();
			}else{
				addSection();
			}
		}
	});
}
function addSection(){
	var li_content="";
	var li_content2="";
	var factno=jq("#dwr_factno").val();
	var yymmdd=jq("input[name='yymmdd']").val();
	var createUser=jq("#createUser").val();
	var createDate=jq("#createDate").val();
	if(factno!=""&&yymmdd!=""){
		jq.ajax({
			type:"post",
			dataType:"json",
			data:{factNo:factno,yymmdd:yymmdd},
			url:"webbrpro_findByfactNoAndyymmdd",
			success:function(data){
				if(data=="1"){
					layer.alert("注意:該廠當前月份部分BR產品庫存已存在,繼續添加會覆蓋舊數據",0);
				}
			}
		});
		
		jq(".list_item").empty();	
		jq(".list_item").append("<li>產品名稱</li><li>庫存數(KG)</li>");
		jq(".list_item").append("<li>已訂購未入廠(KG)</li><li>當月耗用(KG)</li>");
		jq.ajax({
			type:"post",
			dataType:"json",
			data:{factNo:factno,yymmdd:yymmdd},
			url:"webbrpro_findByFactno",
			success:function(data){				
				jq.each(data,function(i,obj){
					li_content+="<li>"+obj[1]+"&nbsp;"+obj[2]+"</li>"+
					"<li><input type='text' name='listitem["+i+"].inventory' datatype='*'/></li>"+
					"<li><input type='text' name='listitem["+i+"].orderNotin' datatype='*'/></li>"+
					"<li><input type='text' name='listitem["+i+"].actualUsed' datatype='*'/>"+
					"<input type='hidden' name='listitem["+i+"].id.webBrProduct.id.webErpProductinFormation.itemid' value='"+obj[0]+"'/>"+
					"<input type='hidden' name='listitem["+i+"].id.webBrProduct.id.factNo' value='"+factno+"'/>"+
					"<input type='hidden' name='listitem["+i+"].id.yymmdd' value='"+yymmdd+"'/>"+
					"<input type='hidden' name='listitem["+i+"].createUser.id' value='"+createUser+"'/>"+
					"<input type='hidden' name='listitem["+i+"].createDate' value='"+createDate+"'/>"+
					"</li>"
					
				})	
				jq(".list_item").append(li_content);
			}			
		});
		
		jq.ajax({
			type:"post",
			dataType:"json",
			data:{factNo:factno,yymmdd:yymmdd},
			url:"webbrpro_findByfactNoAndyymmdd2",
			success:function(data){
				if(data=="1"){
					layer.alert("注意:該廠當前月份BR產品預訂已存在,繼續添加會覆蓋舊數據",0);
				}
			}
		});
		
		jq(".list_item2").empty();	
		jq(".list_item2").append("<li>製程</li><li>當月實際生產雙數(含不良)</li>");
		jq(".list_item2").append("<li>次一月預估生產雙數</li><li>次二月預估生產雙數</li><li>次三月預估生產雙數</li>");
		jq.ajax({
			type:"post",
			dataType:"json",
			data:{factNo:factno},
			url:"webfact_findByFactNo_show_order",
			success:function(data){				
				jq.each(data,function(i,obj){
					li_content2+="<li>"+obj[0]+"</li>"+
					"<li><input type='text' name='listest["+i+"].actualPairs' datatype='*'/></li>"+
					"<li><input type='text' name='listest["+i+"].estimatingPairs1' datatype='*'/></li>"+
					"<li><input type='text' name='listest["+i+"].estimatingPairs2' datatype='*'/></li>"+
					"<li><input type='text' name='listest["+i+"].estimatingPairs3' datatype='*'/>"+
					"<input type='hidden' name='listest["+i+"].id.factNo' value='"+factno+"'/>"+
					"<input type='hidden' name='listest["+i+"].id.yymmdd' value='"+yymmdd+"'/>"+
					"<input type='hidden' name='listest["+i+"].id.factCode' value='"+obj[0]+"'/>"+
					"<input type='hidden' name='listest["+i+"].createUser.id' value='"+createUser+"'/>"+
					"<input type='hidden' name='listest["+i+"].createDate' value='"+createDate+"'/>"+
					"</li>"
				})	
				jq(".list_item2").append(li_content2);
			}			
		});
		
		
	}						    	    	    	    	   	    
}

function removeOneItem_pro(url,obj,par1,par2,par3){
	/*jq(obj).parent().parent().parent().remove();
	layer.alert("刪除成功");*/
	
	var flag=confirm("確定要刪除嗎?");
	if(flag){
		var fno=jq("#"+par1).val();
		var ymd=jq("#"+par2).val();		
		var wid=jq("#"+par3).val();
		jq.ajax({
			type:"post",
			dataType:"json",
			data:{factNo:fno,yymmdd:ymd,wid:wid},
			url:url,
			success:function(data){
				if(data=="0"){
					jq(obj).parent().parent().remove();
					layer.alert("刪除成功");
				}else{
					layer.alert("刪除失敗");
				}
			}			
		});
	}		    						
}

function removeOneItem_est(url,obj,par1,par2,par3){
	/*jq(obj).parent().parent().parent().remove();
	layer.alert("刪除成功");*/
	
	var flag=confirm("確定要刪除嗎?");
	if(flag){
		var fno=jq("#"+par1).val();
		var fcode=jq("#"+par2).val();		
		var ymd=jq("#"+par3).val();
		jq.ajax({
			type:"post",
			dataType:"json",
			data:{factNo:fno,factCode:fcode,yymmdd:ymd},
			url:url,
			success:function(data){
				if(data=="0"){
					jq(obj).parent().parent().remove();
					layer.alert("刪除成功");
				}else{
					layer.alert("刪除失敗");
				}
			}			
		});
	}		    						
}

function checkbtn(){
	var items=jq("input[name='itemids']:checked");
	if(items!=null&&items.length>0){
		jq("#btn_addsec").removeClass("disabled");
	}else{
		jq("#btn_addsec").addClass("disabled");
	}
}



function checkallItems(){
	var one=jq("#all_namece");
	if(one.prop("checked")){
		jq("input[name='itemids']").each(function(){
			jq(this).prop("checked",true);
		});
	}else{
		jq("input[name='itemids']").each(function(){
			jq(this).prop("checked",false);
		});
	}
}
	
</script>
</body>
</html>
