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
	<hr/>
	<s:if test='#session.loginUser.userread!="1"'>
	<form action="webestpro_importFile"  method="post" enctype="multipart/form-data" id="upload_form" target="frameFile">
	  <table id="tb_search">
	      <td>	 
	         <input type="file" name="file"  id="id_file" class="btn btn-info"/>	        	       
	       </td>
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
	         <input type="text" id="yymm_in" name="yymm" onClick="WdatePicker({dataFmt:'yyyyMM'})" readonly="readonly" class="Wdate search"/>
	       </td>
	       <td>
	       	    &nbsp;<input value="導入Excel" type=button onclick="checkForm()" id="search_forday" class="btn btn-info"/>
	       </td>	       
	  </table>          	
	</form>
	</s:if>
	<iframe id="frameFile" name="frameFile" style="display: none;"></iframe>
	<hr/>	
	
<script>

	function checkForm(){
		var id_file=jq("#id_file").val();
		var extendName=id_file.substr(id_file.lastIndexOf(".")).toLowerCase();
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
	}
	
		
	
function print(public_form,factNo,yymm,yymm2){
	var public_form=jq("#"+public_form);
	public_form.attr("action","weballobj_print");
	public_form.attr("target","_blank");	
	if(jq("#"+factNo).val()=="nothing"||jq("#"+yymm).val()==""||jq("#"+yymm2).val()==""){
		layer.msg("請選擇廠別和日期",3,3);
	}else{
		public_form.submit();
	}	
}


function showDiv(){
jq.layer({
  type: 1,
  title: '樣本格式說明', //不显示标题
  page:{html:"<img src='images/sample-webestpro.jpg'/>"}  //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响  
});
}
</script>		
</body>
</html>
