<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<title>My JSP 'kongweishu.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>


<body>
	<jsp:include page="publicHead_webobj.jsp" />	
	<hr/>
	<s:if test='#session.loginUser.userread!="1"'>
	<form action="webobjb_addMore"  method="post" enctype="multipart/form-data" id="upload_form" target="frameFile">
	  <table id="tb_search">
	     <tr>
	       <td>
	          <s:if test="#session.factNo=='tw'">
					<select name="factNo" id="factNo_a" class="search" >
						<option value="nothing">請選擇廠別</option>							
						<s:iterator value="#session.facts" id="temp">
							<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>								
						</s:iterator>
					</select>
					
				</s:if> 
				<s:else>
				
					<select name="factNo" id="factNo_a" class="search">						
						<option value="<s:property value="#session.factNo"/>">
							<s:property value="#session.factName" />(<s:property value="#session.factNo"/>)
						</option>
					</select>
					
				</s:else>
	       </td>	       
	       <td>
	         <input type="text" id="yymm_in" name="yymm" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" readonly="readonly" class="Wdate search"/>
	       </td>
	       <td >
	       <select name="workorholiday" id="workholiday" datatype="*" onchange="changeFile()">
							<option value="">請選擇日期性質</option>
							<option value="0">工作日</option>
							<option value="1">假日</option>
							<option value="2">未排產</option>
			</select>
			</td>
			<td>	 
	         <input type="file" name="file"  id="id_file" class="btn btn-info"/>	        	       
	       </td>		 
	       <td>
	       	    &nbsp;<input value="導入Excel" type=button onclick="checkForm()" id="search_forday" class="btn btn-info"/>
	     </td>
	    </tr> 	       
	  </table>          	
	</form>
	</s:if>
	<iframe id="frameFile" name="frameFile" style="display: none;"></iframe>
	<hr/>
	<div id="bodyid">
		<jsp:include page="table1/webobjb1.jsp" />
	</div>	
	
<script>

	function checkForm(){	
		var id_file=jq("#id_file").val();
		var extendName=id_file.substr(id_file.lastIndexOf(".")).toLowerCase();
		if(jq("#workholiday").val()==""){
		    layer.alert("請選擇日期性質");
			return false;
		}else{
		   if(jq("#workholiday").val()=="0"){
		      if(id_file==""){
			   layer.alert("請選擇Excel文檔");
			   return false;
		      }else if(extendName!=".xls"&&extendName!=".xlsx"){
			   layer.alert("僅允許Excel文檔");
			   return false;
		      }else if(jq("#factNo_a").val()=="nothing"){
			   layer.alert("請選擇廠別");
			   return false;
		      }else if(jq("#yymm_in").val()==""){
			   layer.alert("請選擇日期");
			   return false;
		      }else{
		       layer.load("請稍等...");
			   jq("#upload_form").submit();
		      }
		   }else{
		      if(jq("#factNo_a").val()=="nothing"){
			   layer.alert("請選擇廠別");
			   return false;
		      }else if(jq("#yymm_in").val()==""){
			   layer.alert("請選擇日期");
			   return false;
		      }else{
		       layer.load("請稍等...");
			   jq("#upload_form").submit();
		      }
		}				
	}
}		
	function pages(page) {	    		
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webobjb_findPageBean3",
			data : "page=" + page,
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				jq("bodyid").html(xhr.responseText);
			}
		});
	}
	function submis(public_form) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webobjb_findPageBean2",
			data:jq("#"+public_form).serialize(),
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("#bodyid").html(xhr.responseText);
			}
		});
	}
	
function print(public_form,factNo,yymm){
    clearcss();
	var public_form=jq("#"+public_form);
	public_form.attr("action","webobjb_print");
	public_form.attr("target","_blank");	
	if(jq("#"+factNo).val()=="nothing"||jq("#"+factNo).val()==""||jq("#"+yymm).val()==""){
	    jq("#factNo").css("border-color","red");
		jq("#yymm").css("border-color","red");
		layer.msg("請選擇廠別和（年月）日期",3,3);		
	}else{	
	    jq("#factNo").css("border-color","");    
		jq("#yymm").css("border-color","");
		public_form.submit();
	}	
}

function print3(public_form,yymm){
    clearcss();
	var public_form=jq("#"+public_form);	
	public_form.attr("action","webobjb_print_tw");
	public_form.attr("target","_blank");	
	if(jq("#"+yymm).val()==""){
	    jq("#yymm").css("border-color","red");
		layer.msg("請選（年月）日期",3,3);
	}else{
	    jq("#yymm").css("border-color","");
		public_form.submit();
	}
}

function print4(public_form,yymmdd){
    clearcss();
	var public_form=jq("#"+public_form);	
	public_form.attr("action","webobjb_print_tw2");
	public_form.attr("target","_blank");	
	if(jq("#"+yymmdd).val()==""){
	    jq("#yymmdd").css("border-color","red");
		layer.msg("請選（年月日）日期",3,3);
	}else{
	    jq("#yymmdd").css("border-color","");
		public_form.submit();
	}
}

function print5(public_form,yymmdd){
    clearcss();
	var public_form=jq("#"+public_form);	
	public_form.attr("action","autosw_print_manual");
	public_form.attr("target","_blank");	
	if(jq("#"+yymmdd).val()==""){
	    jq("#yymmdd").css("border-color","red");
		layer.msg("請選（年月日）日期",3,3);
	}else{
	    jq("#yymmdd").css("border-color","");
		public_form.submit();
	}
}

function print6(public_form,factNo,year){
    clearcss();
	var public_form=jq("#"+public_form);	
	public_form.attr("action","webobjb_print3");
	public_form.attr("target","_blank");	
	if(jq("select[name='factNo']").val()=="nothing"||jq("select[name='factNo']").val()==""||jq("#year").val()==""){
	    jq("#factNo").css("border-color","red");
	    jq("#year").css("border-color","red");
		layer.msg("請選廠別和年份",3,3);
	}else{
	    jq("#factNo").css("border-color","");
	    jq("#year").css("border-color","");
		public_form.submit();
	}
}

function showDiv(){
    jq.layer({
    type: 2,   //0-4的选择,
    title: '樣本格式說明',
    //border: [0],
    closeBtn: [1,true],
    shade: [0],
    //shade: [0.5, '#000'],
    shadeClose: false,
    border: [10, 0.3, '#000'],
   // btns:1,
    //fadeIn:300,
    //shift:'top',
    offset:['10px',''],
    area:['570px','450px'],               
    iframe:{src:'page/sample/sample_weballobj.jsp',scrolling:'auto'}	                    
});
}

function changeFile(){
   if(jq("#workholiday").val()=="0"){
     jq("#id_file").removeClass("disabled");
   }else{
     jq("#id_file").addClass("disabled");
   }
}

function clearcss(){
  jq("#factNo").css("border-color","");
  jq("#year").css("border-color","");
  jq("#yymm").css("border-color","");
  jq("#yymmdd").css("border-color","");
  
}
</script>		
</body>
</html>
