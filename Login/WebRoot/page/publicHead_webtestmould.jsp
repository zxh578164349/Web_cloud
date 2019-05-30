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
	<table id="tb_search">
		<tr>
			<td>
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="heading">
							<table>
								<tr>																									
									<th>日期</th>
									<th>品牌</th>	
									<th>客戶</th>
								</tr>
								<tr>
								   <td>
								     <input type="text"  name="dateA" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate"/>~
									 <input type="text"  name="dateB" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate"/>&nbsp; 							     								       								     							    
								   </td>																		
									<td>
									<select name="brand"></select>									 																		
								</td>
								<td>
									 <select name="customer"></select>&nbsp;
								     <input value="搜索" type="button" class="btn btn-primary" onclick="javascript:submis()" />&nbsp;&nbsp;
								     <input value="導出Excel" type="button" class="btn btn-primary" onclick="print('public_form')"/>
										
									 <!-- <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">更多</a> -->
								</td>
								</tr>
							</table>
							<div id="collapseOne" class="panel-collapse collapse">
								<div class="panel-body">
									<!-- <table>
										<tr>
											<td>品牌</td><td>客戶</td>
										</tr>										
										<tr>										   
										   <td>	
										     <select name="brand"></select>    								      
										   </td>
										    <td>	
										     <select name="customer"></select>    								      
										   </td>
										</tr>
									</table> -->
								</div>
							</div>
						</div>
					</div>
				</div></td>
		</tr>
	</table>
</form>	
<script type="text/javascript">
jq(function(){
  jq.ajax({
	      type:"get",
	      dataType:"json",
	      url:"webtestreform_findBrand",	      
	      success:function(datas){
	         jq('select[name="brand"]').empty();
	          var item="<option value=''>請選擇</option>";	          
	         jq.each(datas,function(i,data){
	            item+="<option value='"+data+"'>"+data+"</option>";	        		        		                       
	         }); 
	         jq('select[name="brand"]').append(item);
	      }
	   });
	   
   	jq.ajax({
	      type:"get",
	      dataType:"json",
	      url:"webtestreform_findCustomer",	      
	      success:function(datas){
	         jq('select[name="customer"]').empty();
	          var item="<option value=''>請選擇</option>";	          
	         jq.each(datas,function(i,data){
	            item+="<option value='"+data+"'>"+data+"</option>";	        		        		                       
	         }); 
	         jq('select[name="customer"]').append(item);
	      }
	   });   
});
</script>
</body>
</html>

