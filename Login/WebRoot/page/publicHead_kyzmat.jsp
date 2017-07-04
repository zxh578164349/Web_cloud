
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
   <form  method="post" id="public_form">
	<table  id="tb_search">
		<tr>
		    <td>
		    <br>
		           物料編號
		    <input type="text" name="matNo" id="matNo"/><br>
		          中文名稱
		    <input type="text" name="matCname" id="matcname"/>
		    </td>			
			<td>
			<br>			 
			(開始日期)<input type="text" id="beginDate"  name="fromDate"  onClick="WdatePicker({minDate:'{%y-1}-%M-%d',maxDate:'#F{$dp.$D(\'endDate\',{d:-1})||\'%y-%M-{%d-1}\'}',dateFmt:'yyyyMMdd'})" readonly="readonly" class="Wdate"/><br>
		    (結束日期)<input type="text" id="endDate"  name="endDate"  onClick="WdatePicker({minDate:'#F{$dp.$D(\'beginDate\',{d:1})}',maxDate:'%y-%M-%d',dateFmt:'yyyyMMdd'})" readonly="readonly" class="Wdate"/>
			</td>
			<td>
			<br>			
			 <select name="typeBno"  id="bNo" onclick="findallMN(this.value)">
					     <option value="">大分類選擇</option>
			 </select>
			 <br>
			 <select name="typeMno" id="mNo" onclick="findallSN(this.value)">
					         <option value="">中分類選擇</option>
			 </select>
			 <br>
			 <select name="typeSno"  id="sNo" >
					         <option value="">小分類選擇</option>
		     </select>	
			</td>			
			<td>
			<input value="搜索" type="button" class="btn btn-primary" onclick="submis('public_form')" />
			<input value="導出Excel" type="button" class="btn btn-primary"  onclick="print('public_form')"/>			
			</td>
		</tr>
	</table>
	</form>
<script type="text/javascript">
function findallBN(){
    kyzscmjs.findBN(function(x){
        dwr.util.addOptions("bNo",x);
    });                             
}
function findallMN(bNo){
   document.getElementById("mNo").length=1;
   document.getElementById("sNo").length=1;
   kyzscmjs.findMN(bNo,function(x){
   if(bNo!=""){
      dwr.util.addOptions("mNo",x);
   }	         
   });
}
function findallSN(mNo){
   document.getElementById("sNo").length=1;
   kyzscmjs.findSN(mNo,function(x){
   if(mNo!=""){
      dwr.util.addOptions("sNo",x);
   }	         
   });
}
</script>
<script type='text/javascript' src='dwr/interface/kyzscmjs.js'></script>
<script type="text/javascript">
  jq(function(){
	  findallBN();
  });
</script>	 	
</body>
</html>
