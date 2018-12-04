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
<%
java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd");
Calendar cal=Calendar.getInstance();
String createDate=sdf.format(cal.getTime());
cal.setFirstDayOfWeek(Calendar.MONDAY);
cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//本周星期一日期
String sdate=sdf.format(cal.getTime());
cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//本周星期天日期
String edate=sdf.format(cal.getTime());
%>
<!DOCTYPE HTML>
<html>
<title>添加業務周報告</title>
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
				    <s:if test="obj==null">
				       <tr>
				        <td>
				          <s:property value='#session.loginUser.name'/>
				          <input type="hidden" name="obj.webUser.id" id="uid" value="<s:property value='#session.loginUser.id'/>" readonly/>
				        </td>
				        <td>
				           <input type="text" name="obj.SDate" id="sdate" class="Wdate" onclick="WdatePicker({disabledDays:[0,2,3,4,5,6],dateFmt:'yyyyMMdd',maxDate:'%y-%M-%d',onpicked:function(){checkreport();}})" datatype="*"/><!-- onpicked點擊日期控件後觸發事件 -->		          
				           <input type="hidden" name="obj.createDate" value="<%=createDate %>" />
				        </td>
				        <td>
				           <select name="obj.webErpBrankProcess.id" id="dwrWebbrank" datatype="*" onchange="checkreport()">
				              
				           </select>			           
				        </td> 				         				        
				      </tr> 				      
				    </s:if>
				    <s:else>
				      <td>
				       <input type="hidden" name="obj.RId" value="<s:property value='obj.RId'/>"/>
				       <s:property value='#session.loginUser.name'/>
				       <input type="hidden" name="obj.webUser.id" id="uid" value="<s:property value='obj.webUser.id'/>"/>
				      </td>
				      <td>
				        <input type="text" name="obj.SDate" id="sdate" value="<s:property value='obj.SDate'/>" readonly/>
				        <input type="hidden" name="obj.EDate"  value="<s:property value='obj.EDate'/>"/>
				      </td>
				      <td>
				        <!-- <select name="obj.webErpBrankProcess.id" id="dwrWebbrank" datatype="*" onchange="checkreport()"></select> -->
				        <input type="text"  value="<s:property value='obj.webErpBrankProcess.name'/>" readonly/>
				        <input type="hidden" name="obj.webErpBrankProcess.id" value="<s:property value='obj.webErpBrankProcess.id'/>"/>
				        <input type="hidden" id="bid" value="<s:property value='obj.webErpBrankProcess.id'/>" />
				        <input type="hidden" name="obj.createDate" value="<s:property value='obj.createDate'/>" />
				        <input type="hidden" name="obj.updateDate" value="<%=createDate %>"/>
				      </td> 
				    </s:else>
				    
				    <tr>
				      <td colspan="3">
				                               本周報告事項<br/>
				         <textarea style="width:100%;height:130px" name="obj.RContent" datatype="*"><s:property value="obj.RContent"/></textarea>	
				      </td>
				    </tr>
				    <tr>
				      <td colspan="3">
				                               上周報告事項<br/>
				         <textarea style="width:100%;height:130px" name="obj.RContentLast" ><s:property value="obj.RContentLast"/></textarea>	
				      </td>
				    </tr>																   											
		</table>
		<center>
			<input type="submit" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; 
			<input	type="reset" id="reset" value="重置" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;			
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

/*加載所有品牌*/
jq.ajax({
	type:"get",
	url:"weberpbp_findObjOp2",
	dataType:"json",
	success:function(data){
		jq("#dwrWebbrank").empty();
		jq("#dwrWebbrank").append("<option value=''>品牌選擇</option>");
		var item="";
		jq.each(data,function(i,obj){
		    if(obj[0]==jq("#bid").val()){
		       item+="<option value='"+obj[0]+"' selected>"+obj[2]+"</option>";
		    }else{
		       item+="<option value='"+obj[0]+"'>"+obj[2]+"</option>";	
		    }							
		});
		jq("#dwrWebbrank").append(item);
	}
});

function checkreport(){
  if(jq("#sdate").val()!=""&&jq("#dwrWebbrank").val()!=""){
     jq.ajax({
      type:"post",
      url:"webweekly_findByUidASdate",
      dataType:"json",
      data:{sdate:jq("#sdate").val(),uid:jq("#uid").val(),bid:jq("#dwrWebbrank").val()},
      success:function(data){
         if(data=="0"){
            jq("#sub").attr("disabled",false);
         }else{
            layer.msg("項目已存在,請重新選擇",3,3);
            jq("#sub").attr("disabled",true);
         }
      },
      error:function(error){
          alert(error.responseText);
      }
    });
  }
  
}
</script>
</body>
</html>
