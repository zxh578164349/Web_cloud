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
									<th>類別</th>
									<th>物性名称</th>
									<th>日期</th>
								</tr>
								<tr>
								   <td>
								     <select name="mtype"></select>							    
								   </td>									
									<td>
									 <input type="text" name="materielname" />
									</td>
									<td>									 
									<input type="text"  name="sdate" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate"/>~
									<input type="text"  name="edate" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate"/>&nbsp; 
									<input value="搜索" type="button" class="btn btn-primary" onclick="javascript:submis()" />&nbsp;&nbsp; 
									 <input value="導出Excel" type="button" class="btn btn-primary" onclick="print('public_form')"/>		
										
								<!-- <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">更多</a> -->
								</td>
								</tr>
							</table>
							<div id="collapseOne" class="panel-collapse collapse">
								<div class="panel-body">
									<table>
										<tr>
											<td><input type="text" id="yymmdd" name="yymmdd" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" readonly="readonly" class="Wdate" />至 <input
												type="text" id="yymmdd2" name="yymmdd2" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" readonly="readonly" class="Wdate" /></td>
										</tr>
										<tr><th>標題</th></tr>
										<tr>										   
										   <td>	
										     <div class="panel panel-primary">									      
										          <input type="text" name="title" placeholder="標題" id="p_title" />										          										          
										     </div>     								      
										   </td>
										</tr>
									</table>
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
	      url:"webmateria_findmtype",	      
	      success:function(mtypes){
	         jq('select[name="mtype"]').empty();
	          var item="<option value=''>請選擇</option>";	          
	         jq.each(mtypes,function(i,mtype){
	            item+="<option value='"+mtype+"'>"+mtype+"</option>";	        		        		                       
	         }); 
	         jq('select[name="mtype"]').append(item);
	      }
	   });
});
</script>
</body>
</html>

