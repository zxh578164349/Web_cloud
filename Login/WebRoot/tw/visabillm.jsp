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

	<jsp:include page="../page/publicHead_kybillm_new.jsp" flush="true" />
	<hr />	
	<div id="bodyid">
		<jsp:include page="tw1/visabillm1.jsp" />
	</div>

	
<script>
 
	function pages(page) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "vbm_findPageBean3_1",
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
			url : "vbm_findPageBean2_1",
			data:jq("#subform").serialize(),
			//data : "factNo=" + fact.value +"& billNo="+billno.value+"& visaMk="+visaMk.value+"& visaSort="+visaSort.value+"& yymmdd="+yymmdd.value+"& yymmdd2="+yymmdd2.value,
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
	 
function tips(memo,index){
    if(memo==''){
       memo='無';
    }
    layer.tips(memo, '#'+index, {
    style: ['background-color:#78BA32; color:#fff', '#78BA32'],
    maxWidth:240,
    time: 10,
    closeBtn:[0, true]
});
}

/* function goPreviewOrPrint(subform,billNo){
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
} */
</script>
<script type='text/javascript' src='dwr/interface/webtypejs.js'></script>

</body>
</html>
