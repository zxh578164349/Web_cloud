<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<title>添加新用户</title>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />
</head>

<body>
	<form action="webweekly_add" method="post" id="form">
		<table class="table table-condensed">		      						
					<tr>
						<td class="td_show_title">姓名</td>																						
				        <td class="td_show_title">日期</td>
				        <td class="td_show_title">品牌</td>			        						
				    </tr>
				    <tr>
				        <td>
				          <s:property value='#session.loginUser.name'/>
				          <input type="text" name="obj.webUser.id" value="<s:property value='#session.loginUser.id'/>" readonly/>
				        </td>
				        <td>
				           <input type="text" name="obj.SDate" value="" readonly/>-
				           <input type="text" name="obj.EDate" value="" readonly/>
				        </td>
				        <td>
				           <select name="obj.webErpBrankProcess.id">
				              
				           </select>
				        </td> 				         				        
				    </tr>
				    <tr>
				      <td colspan="3">
				         <textarea style="width:100%;height:120px" name="obj.RContent"  wrap="off"   tip="報告事項" altercss="gray" class="gray"   datatype="*"><s:property value="obj.RContent"/></textarea>	
				      </td>
				    </tr>																   											
		</table>
		<center>
			<input type="submit" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; <input
				type="reset" id="reset" value="重置" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;			
            <input type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary"/>
					 
		</center>
	</form>

<script type="text/javascript">

	jq(function() {
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			tipSweep:true,
			showAllError : true	,
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);				
					loadUrl("webweekly_findPageBean");
				}else{
					layer.msg("提交失敗!",3,3);
				}				
			}
		});	
	}); 

	    	       
 /*禁止空格輸入*/        
jq(function(){
        goTrim();
      });
function back(){	
	loadUrl("webweekly_findPageBean3?backIndex=1");
	
}

</script>
</body>
</html>
