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

	<jsp:include page="publicHead_sampleschedule.jsp"  />
	<hr />
	
	<form action="websample_impormtData"  method="post" enctype="multipart/form-data" id="upload_form" target="frameFile">
			<table class="table-condensed">
	            <tr>
	              <td>月份</td>
	              <td>
	               <input type="text"  name="yymm" onClick="WdatePicker({dateFmt:'yyyyMM'})" class="Wdate"/>
	              </td>
	              <td>
	                <input type="file" name="file" id="id_file" style="width:150px"/>
	              </td>
	              <td>
	                <input value="導入數據" type=button onclick="checkForm()"  class="btn btn-info"/>
	              </td>
	            </tr>
	           </table>     			     				     			     
	</form>
	<iframe id="frameFile" name="frameFile" style="display: none;"></iframe>	
			
	<div id="bodyid">
		<jsp:include page="table1/websample1.jsp" />
	</div>

	
<script>

	function pages(page) {	
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "websample_findPageBean3",
			data : "page=" + page,
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("#bodyid").html(xhr.responseText);
			}
		});
	}
	function submis() {	
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "websample_findPageBean2",
			//data : "factNo=" + fact.value + "& visaSort=" + visasort.value+"& billNo="+billno.value+"& yymmdd="+yymmdd.value+"& yymmdd2="+yymmdd2.value,
			data:jq("#public_form").serialize(),
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("#bodyid").html(xhr.responseText);
			}
		});
	}
		
function showDivList(factNo,billNo){	    	
	    jq.layer({
	    type: 2,   //0-4的选择,
	    title: '函文申請',
	    closeBtn: [1,true],
	    shade: [0],
	    shadeClose: false,
	    border: [10, 0.3, '#000'],	   
	    offset:['10px',''],
	    area:['600px','560px'],
	    //page:{url:'websample_findById_layer?billNo='+billNo+'& factNo='+factNo}                   
	    iframe:{src:'websample_findById_layer3?factNo='+factNo+'&billNo='+billNo,scrolling:'auto'}		                   
	});
	    }
	    
function checkForm(){
		var id_file=jq("#id_file").val();
		var extendName=id_file.substr(id_file.lastIndexOf(".")).toLowerCase();
		if(id_file==""){
			layer.alert("請選擇Excel文檔");
			return false;
		}else if(extendName!=".xls"&&extendName!=".xlsx"){
			layer.alert("僅允許Excel文檔");
			return false;
		}else if(jq('input[name="yymm"]').val()==""){
		    layer.alert("請填寫月份");
		    return false;
		}else{
		    layer.load("請稍等...");
			jq("#upload_form").submit();		
		}	
					
	}
	
function print(public_form){
	var public_form=jq("#"+public_form);
	public_form.attr("action","websample_print");
	public_form.attr("target","_blank");
	public_form.submit();
	
}		    		
</script>

</body>
</html>
