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
	<form action="webemailall_add" method="post" id="form">
		<table class="table table-condensed">		      															   
				    <tr>
				    <td class="td_show_title">姓名</td>
				    <td><input type="text" name="obj.username" value="<s:property value='obj.username'/>" datatype="*"/></td>
				    <td class="td_show_title">Email</td>
				    <td><input type="text" name="obj.email" value="<s:property value='obj.email'/>" datatype="e" id="email"/></td>
				    </tr>
				    <tr>
				    <td class="td_show_title">類型</td>
				    <td>
				     <s:if test="obj==null">
				      <select name="obj.emailTypeFk.eid" id="sel_emailtypes" datatype="*"></select>
				      <input type="hidden" name="obj.createUser.id" value="<s:property value='#session.loginUser.id'/>"/>
				      <input type="hidden" name="obj.createDate" value="<%=createDate %>"/>
				     </s:if>
				     <s:else>
				       <input type="text" value="<s:property value='obj.emailTypeFk.ename'/>" readonly/>
				       <input type="hidden" name="obj.emailTypeFk.eid" value="<s:property value='obj.emailTypeFk.eid'/>"/>
				       <input type="hidden" name="obj.createUser.id" value="<s:property value='obj.createUser.id'/>"/>
				       <input type="hidden" name="obj.createDate" value="<s:property value='obj.createDate'/>"/>
				       <input type="hidden" name="obj.updateUser.id" value="<s:property value='#session.loginUser.id'/>"/>
				       <input type="hidden" name="obj.updateDate" value="<%=createDate %>"/>
				       <input type="hidden" name="obj.eid" value="<s:property value='obj.eid'/>"/>
				       <%-- <input type="hidden" name="obj.emailMk" value="<s:property value='obj.emailMk'/>"/> --%>				       				       		       
				     </s:else>				     				    
				    </td>
				    <td class="td_show_title">EmailOrCc</td>
				    <td>				    
				    <s:if test='obj.emailOrCc=="0"'>
				      Email<input type="radio" name="obj.emailOrCc" value="0" checked/>&nbsp;&nbsp;
				    </s:if>
				    <s:else>
				      Email<input type="radio" name="obj.emailOrCc" value="0"  datatype="*"/>&nbsp;&nbsp;
				    </s:else>
				    <s:if test='obj.emailOrCc=="1"'>
				      Cc<input type="radio" name="obj.emailOrCc" value="1"  checked/>
				    </s:if>
				    <s:else>
				      Cc<input type="radio" name="obj.emailOrCc" value="1"  datatype="*"/>
				    </s:else>
				    </td>
				    </tr>
				    <s:if test="obj!=null">
				       <tr>
				         <td>是否發送郵件</td>
				         <td>
				           <s:if test='obj.emailMk=="Y"'>
				                                             是<input type="radio" name="obj.emailMk" value="Y" checked/>				                                             
				           </s:if>
				           <s:else>
				                                             是<input type="radio" name="obj.emailMk" value="Y"/>
				           </s:else>
				           
				           <s:if test='obj.emailMk=="N"'>
				                                             否<input type="radio" name="obj.emailMk" value="N" checked/>				                                             
				           </s:if>
				           <s:else>
				                                             否<input type="radio" name="obj.emailMk" value="N"/>
				           </s:else>
				           
				         </td>
				       </tr>
				    </s:if>
				    				    				       						    			   				    				   																   											
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
			beforeSubmit:function(){
			  var flag=checkemail();
			  return flag;
			},
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);				
					loadUrl("webemailall_findPageBean");
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
	loadUrl("webemailall_findPageBean3?backIndex=1");
	
}

/*加載所有類型*/
jq.ajax({
	type:"get",
	url:"webemailall_findAllEmailTypes",
	dataType:"json",
	success:function(data){
		jq("#sel_emailtypes").empty();
		jq("#sel_emailtypes").append("<option value=''>類型選擇</option>");
		var item="";
		jq.each(data,function(i,obj){
			item+="<option value='"+obj.eid+"'>"+obj.ename+"</option>";					
		});
		jq("#sel_emailtypes").append(item);
	}
});

function checkemail(){
  var flag=false; 
  if(jq("#email").val()!=""&&jq("#sel_emailtypes").val()!=""&&jq("input[name='obj.emailOrCc']").is(":checked")){
     jq.ajax({
      type:"post",
      url:"webemailall_findByEmailAndEtypeAndEmailOrCc",
      dataType:"json",
      data:{email:jq("#email").val(),eid:jq("#sel_emailtypes").val(),emailOrcc:jq("input[name='obj.emailOrCc']:checked").val()},
      async: false,//同步處理
      success:function(data){
         if(data=="0"){
            flag=true;          
         }else{
            layer.msg("該類郵箱已存在，請重新添加",3,3);           
         }
      },
      error:function(error){
          alert(error.responseText);
      }
    });
  }
  return flag;
  
}
</script>
</body>
</html>
