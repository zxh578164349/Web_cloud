<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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

	<jsp:include page="publicHead_kybillm_new.jsp" flush="true" />
	<hr />	
	<div id="bodyid">
		<jsp:include page="table1/visabillm1_new.jsp" />
	</div>

	

<script>
  
	function pages(page) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "vbm_findPageBean3",
			data : "page=" + page,
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("#bodyid").html(responseText);
			}
		});
	}
	
		function submis() {		
		var fact = document.getElementById("factNo");
		var billno=document.getElementById("billNo");
		var visaMks=document.getElementsByName("visaMk");
		var visaSort=document.getElementById("dwr_kytype");
		var yymmdd=document.getElementById("yymmdd");
		var yymmdd2=document.getElementById("yymmdd2");
		var visaMk;

		for(var i=0;i<visaMks.length;i++){
		   if(visaMks[i].checked==true){
		      visaMk=visaMks[i]
		      break;
		   }
		}				
		 jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "vbm_findPageBean2",
			data : "factNo=" + fact.value +"& billNo="+billno.value+"& visaMk="+visaMk.value+"& visaSort="+visaSort.value+"& yymmdd="+yymmdd.value+"& yymmdd2="+yymmdd2.value,
			 success : function(msg) {			    
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("#bodyid").html(xhr.responseText);
			} 			
		}); 
		//jq.post("vbm_findPageBean2",{"factNo":fact.value,"billNo":billno.value,"visaMk":visaMk.value},function(data){jq("#bodyid").html(data)});
	}
	

   function getType(factno){
     document.getElementById("dwr_kytype").length=1;
     if(factno!=null&&factno!=""&&factno!="nothing"){
    	 webtypejs.findByFactNo(factno,function(x){
    	       if(x.length>0){
    	          dwr.util.addOptions("dwr_kytype",x,"webtypeMk","typeName");
    	       }   	         
    	     });
     }    
   }
   
   function goPreviewOrPrint(subform,billNo){
	   if(billNo.substring(0,2)=="EM"){
		   jq("#"+subform).attr("action","kyz_print2");
	   }
	   if(billNo.substring(0,2)=="CM"){
		   jq("#"+subform).attr("action","kyzletter_print2");
	   }
	   if(billNo.substring(0,2)=="BM"){
		   jq("#"+subform).attr("action","bussletter_print2");
	   }
	   if(billNo.substring(0,2)=="RM"){
		   jq("#"+subform).attr("action","webremit_print");
	   }
	   jq("#"+subform).submit();
   }
   
   function print(subform){
		var subform=jq("#"+subform);
		subform.attr("action","vbm_printList");
		subform.attr("target","_blank");
		subform.submit();
	}
</script>
<script type='text/javascript' src='/Login/dwr/interface/webtypejs.js'></script>
</body>
</html>
