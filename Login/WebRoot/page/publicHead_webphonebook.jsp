
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML>
<html>
<head>
<title>My JSP 'publicHead.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">



</head>
<body>
   <form id="public_form" method="post">
	<table id="tb_search" class="table table-condensed">
		<tr>
			<td>廠別</td>
			<td><s:if test="#session.factNo=='tw'">			    
					<select name="factNo" id="factNo" onchange="getDepartments(this.value);getPosts(this.value)">
					    <option value="">請選擇廠別</option>													
						<option value="tw">TW</option>					
						<s:iterator value="#session.facts" id="temp">
							<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>								
						</s:iterator>
					</select>
				</s:if> 
				<s:else>
					<select name="factNo" id="factNo" onchange="getDepartments(this.value);getPosts(this.value)">
						<option value="">請選擇廠別</option>						
						<option value="<s:property value="#session.factNo"/>">
							<s:property value="#session.factName" />(<s:property value="#session.factNo"/>)
						</option>
					</select>
				</s:else>
				</td>
			<td>姓名</td>
			<td><input type="text" name="userName"></td>
			<td rowspan="2">
			 <input value="搜索" type="button" class="btn btn-primary" onclick="submis('public_form')" />	
			 <input value="導出Excel" type="button" class="btn btn-primary" onclick="print('public_form')"/>	
			</td>
		</tr>
		<tr>
		   <td>部門</td>		             		   
		   <td>
		       <!-- <input type="text" name="department" id="department"/> -->
		       <select name="department" id="se_department"></select>		            		       
		   </td>
		   <td>職位</td>
		   <td>		   
		       <!--  <input type="text" name="post" id="post"/>	-->	  
		       <select name="post" id="se_post" ></select>
		   </td>
		</tr>
	</table>
	</form>
	
<script type="text/javascript">

function getDepartments(factno){
	jq.ajax({
		type:"POST",
		dataType:"json",
		data:"factNo="+factno,
		url:"webphonebook_findDepartments",
		success:function(data){
			jq("#se_department").html("");
			var items="<option value=''>請選擇部門</option>";
			if(data!=null){
				jq.each(data,function(i,obj){
					items+="<option value='"+obj+"'>"+obj+"</option>";
				});
				jq("#se_department").append(items);
			}
			
		}
	});
}

function getPosts(factno){	
	jq.ajax({
		type:"POST",
		dataType:"json",
		data:"factNo="+factno,
		url:"webphonebook_findPosts",
		success:function(data){
			jq("#se_post").html("");
			var items="<option value=''>請選擇職位</option>";
			if(data!=null){
				jq.each(data,function(i,obj){
					items+="<option value='"+obj+"'>"+obj+"</option>";
				});
				jq("#se_post").append(items);
			}			
		}
	});
}

jq(function(){
	//getDepartments("");getPosts("");
});
</script>	
</body>
</html>
