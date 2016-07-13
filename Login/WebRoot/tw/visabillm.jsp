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
		/* jQuery(function(jq) {
			 jq(document).ui_loading({
				overlay : true,
				opacity : 0.2,
				supportIframe : true,
				message : '請稍後!正在查詢中..'
			});			
		}); */
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
			url : "vbm_findPageBean2_1",
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
	
	
	function check(visaSort,billNo,factNo){
	     jq.layer({
    type: 1,   //0-4的选择,
    title: '函文內容',
    //border: [0],
    closeBtn: [1,true],
    shade: [0],
    //shade: [0.5, '#000'],
    shadeClose: false,
    border: [10, 0.3, '#000'],
   // btns:1,
    //fadeIn:300,
    shift:'top',
    offset:['40px',''],
    area: ['800px', '560px'],
    page:{
      url:'vbm_findById?visaSort='+visaSort+'&billNo='+billNo+'&factNo='+factNo    
    }                 
});
	}
	
	 function getKyType(factno){
		 document.getElementById("dwr_kytype").length=1;
			 if(factno!=null&&factno!=""&&factno!="nothing"){
			     webtypejs.findByFactNo(factno,function(x){
		       if(x.length>0){
		          dwr.util.addOptions("dwr_kytype",x,"webtypeMk","typeName");
		       }        
		     });
			 }   
			}
	 
   /*function getType2(factNo){
     document.getElementById("dwr_kytype").length=1;
     webtypejs.findByFactNo(factNo,function(x){
       if(x.length>0){
          dwr.util.addOptions("dwr_kytype",x,"webtypeMk","typeName");
       }      
     });
   } */
   
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
</script>
<script type='text/javascript' src='/Login/dwr/interface/webtypejs.js'></script>
<!--  <script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>-->



<body>

	<jsp:include page="publicHead_kybillm_tw.jsp" flush="true" />
	<hr />	
	<div id="bodyid">
		<jsp:include page="tw1/visabillm1.jsp" />
	</div>

	


</body>
</html>
