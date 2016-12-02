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
					<input type="text" name="items[${x.index}].phrVal" value="<s:property value='phrVal'/>" datatype="*9-2"/>
				   </li>
				   <li>
					<input type="text" name="items[${x.index}].weightVal" value="<s:property value='weightVal'/>" datatype="*9-2"/>
				   </li>
				   <li class="col_item3">
					<input type="text" name="items[${x.index}].remark" value="<s:property value='remark'/>" datatype="*0-200"/>					
					<input type="hidden" value="<s:property value='itemId'/>" name="items[${x.index}].itemId">
					<input type="hidden" value="<s:property value='fk_weberp_pf.itemid'/>" name="items[${x.index}].fk_weberp_pf.itemid">
					<input type="hidden" value="<s:property value='sectionNo'/>" name="items[${x.index}].sectionNo">
					<input type="hidden" value="<s:property value='webFormula.formulaIndex'/>" name="items[${x.index}].webFormula.formulaIndex">
					<input type="hidden" value="<s:property value='createName'/>" name="items[${x.index}].createName">
					<input type="hidden" value="<s:property value='createDate'/>" name="items[${x.index}].createDate">
					<input type="hidden" value="${loginUser.username}" name="items[${x.index}].modifyName">
					<input type="hidden" value="<%=str_date%>" name="items[${x.index}].modifyDate">
					<img src="images/icon/del_file.png" style="border:0" onclick="deleteItem(<s:property value='itemId'/>,jq(this))"/>
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
		     <input type="button"  value="新增配方原料" class="btn btn-primary" onclick="addItem()"/>&nbsp;&nbsp;&nbsp;      
			 <input type="button" id="sub_items" value="確定" class="btn btn-primary" />&nbsp;&nbsp;&nbsp;			 			 
			 <input type="button" value="返回"  onclick="javascript:back()" class="btn btn-primary" />
			 <span id="msgtip" style="margin-left:30px;"></span>			            
		</center>
	</form>


<script type="text/javascript">

	jq(function() {
		var demo = jq("#form_items").Validform({
			btnSubmit : "#sub_items",
			tiptype : function(msg,o,cssctl){
				var objtip=jq("#msgtip");
				cssctl(objtip,o.type);
				objtip.text(msg);
			},
			//tipSweep:true,
			//showAllError : true,
			datatype : {
				"*9-2" : /^-?\d{0,9}(\.[0-9]{0,2})?$/
			},
			ajaxPost:true,
			
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);				
					loadUrl("/Login/webformula_findById?formulaIndex="+jq("#formulaIndex").val());
				}else{
					layer.msg("提交失敗",3,3);
				}			
			}
		});
		demo.tipmsg.w["*9-2"] = "只能數字且不超過9位數,可保留2位以內小數";				
	});

var ii=0;
function addItem(){
	var formulaIndex=jq("#formulaIndex").val();
	var num_size=parseInt(jq("#num_size").val())+ii;
	var item="";
	var options="<option value=''>請選擇配方階段</option>";
	for(var j=1;j<8;j++){
		options+="<option value='"+j+"'>"+j+"</option>";
	}	
	item+="<li><select name='items["+num_size+"].sectionNo' datatype='*'>"+options+"</select></li>";
	item+="<li><select id='items_type"+ii+"' onchange='loadNamece(jq(this))' datatype='*'></select></li>";
	item+="<li><select id='fk_weberp_pf"+ii+"' name='items["+num_size+"].fk_weberp_pf.itemid' datatype='*'></select></li>";
	item+="<li><input type='text' name='items["+num_size+"].phrVal' datatype='*9-2'/></li>";
	item+="<li><input type='text' name='items["+num_size+"].weightVal' datatype='*9-2'/></li>";
	item+="<li class='col_item3'><input type='text' name='items["+num_size+"].remark' datatype='*0-200'/>";
	item+="<input type='hidden' value='"+formulaIndex+"' name='items["+num_size+"].webFormula.formulaIndex' readonly/>";
	item+="<input type='hidden' name='items["+num_size+"].createName' value='${loginUser.username}' readonly/>";
	item+="<input type='hidden' name='items["+num_size+"].createDate' value='"+<%=str_date%>+"'/>";
	item+="<img src='images/icon/del_file.png' style='border:0px' onclick='removeOneItem(jq(this))'/></li>";
	jq("#ul_item").append(item);
	var kk="#items_type"+ii;
	jq.ajax({
			type:"post",
			dataType:"json",
			url:"weberppf_findTypeNo",
			success:function(data){		
				var item2="<option value=''>請選擇類別</option>";
				jq.each(data,function(index,obj){
					item2+="<option value='"+obj.selfchar1+"'>"+obj.selfchar1name+"</option>";					
				});		
				jq(kk).append(item2);
				//jq("#items_type"+ii).append(item2);这样写就变成ii的全局变量，也就是ii自加后结果
			}
		});
	ii++;
}

function loadNamece(item_typeno){
    item_typeno.parent().next().children().empty();	
	if(item_typeno.val()!=""){
		jq.ajax({
			type:"post",
			dataType:"json",
			traditional:true,
			data:{'selfchar1':item_typeno.val()},
			url:"weberppf_findNamece",
			success:function(data){
				var item="<option value=''>請選擇配方原料</option>";
				jq.each(data,function(i,obj){
					item+="<option value='"+obj[0]+"'>"+obj[2]+"&nbsp;&nbsp;"+obj[3]+"__"+obj[1]+"</option>";					
				});
				item_typeno.parent().next().children().append(item);
			}
			
		});
	}else{
		item_typeno.parent().next().children().append("<label style='color:red'>請先選擇配方類別</label>");
	}	
}

function removeOneItem(obj){
	obj.parent().prev().prev().prev().prev().prev().remove();
	obj.parent().prev().prev().prev().prev().remove();
	obj.parent().prev().prev().prev().remove();
	obj.parent().prev().prev().remove();
	obj.parent().prev().remove();
	obj.parent().remove();
	//ii--;
}

function deleteItem(itemid,obj){
   jq.ajax({
     type:"post",
     dataType:"json",
     data:"itemid="+itemid,
     url:"webformula_deleteItem",
     success:function(data){
       if(data=="0"){
          removeOneItem(obj);
          layer.msg("刪除成功",3,1);
       }else{
          layer.msg("刪除失敗",3,3);
       }
     }
   });
}

</script>
</body>
</html>
