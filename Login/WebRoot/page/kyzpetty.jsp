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
			url : "kyzpetty_findPageBean3",
			data : "page=" + page,
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	
		function submis() {
		var factno = document.getElementById("factNo");
		var yymm=document.getElementById("year");
		
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "kyzpetty_findPageBean2",
			data : "factNo=" + factno.value +"& yymm="+yymm.value,
			success : function(msg) {
				jq("#bodyid").html(msg);				
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	
function showDiv(){
    jq.layer({
    type: 1,   //0-4的选择,
    title: '零用金支出',
    //title:false,
    //border: [0],
    closeBtn: [1,true],
    shade: [0],
    shadeClose: false,
     border: [10, 0.3, '#000'],
   // btns:1,
    fadeIn:300,    
    //shift:'top',
    //move:'.testdiv',
    moveOut:false,
    moveType:1,
    offset:['10px','200px'],
    area: ['790px', '680px'],
    page:{
      url:'kyzpetty_findAllSecNoAndAcctNo'   
    }
            
});
    }
    
    function showDiv2(factno,billno){
    jq.layer({
    type: 1,   //0-4的选择,
    title: '零用金支出',
    //title:false,
    //border: [0],
    closeBtn: [1,true],
    shade: [0],
    shadeClose: false,
     border: [10, 0.3, '#000'],
   // btns:1,
    fadeIn:300,    
    //shift:'top',
    //move:'.testdiv',
    moveOut:false,
    moveType:1,
    offset:['50px','200px'],
    area: ['790px', '680px'],
    page:{
      url:'kyzpetty_findById?factNo='+factno+'& billNo='+billno   
    }
            
});
    }
    
    function showDiv_print(){
    jq.layer({
    type: 1,   //0-4的选择,
    title: '零用金支出報表',
    //border: [0],
    closeBtn: [1,true],
    shade: [0],
    shadeClose: false,
     border: [10, 0.3, '#000'],
   // btns:1,
    fadeIn:300,    
    //shift:'top',
    moveOut:false,
    moveType:0,
    offset:['50px','200px'],
    area: ['600px', '200px'],
    page:{
      url:'search/kyzpetty_search_print.jsp'   
    }
    
            
});
    }
    
  function isDelete(subform){
 	   var flag=confirm("確定要刪除嗎?");
 	   
 	   if(flag==true){	   
 	      //document.getElementById(subform).submit();
 	      jq.ajax({
 	    	  type:"POST",
 	    	  dataType:"html",
 	    	  data:jq("#"+subform).serialize(),
 	    	  url:"kyzpetty_delete",
 	    	  success:function(data){
 	    		  jq("#bodyid").html(data);
 	    	  },
 	    	  error:function(err){
 	    		  jq("#bodyid").html(err.responseText);
 	    	  }
 	      })
 	   }
 	}    
</script>



<body>
	<jsp:include page="publicHead.jsp"/>
	<hr>		
				
	<span style="float:right;padding-bottom:0">
		 <input type="button" onclick="javascript:showDiv_print()" 
		class="btn btn-link" value="零用金報表打印"/>
	</span>	
	<div id="bodyid">
		<jsp:include page="table1/kyzpetty1.jsp" />
	</div>

	


</body>
</html>
