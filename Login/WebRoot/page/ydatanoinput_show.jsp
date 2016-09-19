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
			url : "webyadanoinput_findPageBean3",
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
	function submis(public_form) {
		var fact = document.getElementById("factNo");
		var ym = document.getElementById("year");
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webyadanoinput_findPageBean2",
			data : jq("#"+public_form).serialize(),
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("#bodyid").html(xhr.responseText);
			}
		});
	}


	//無提示刪除
	function delete_ydata(subform){
	   var flag=confirm("確定要刪除嗎?");
	   
	   if(flag==true){	   
	      //document.getElementById(subform).submit();
	      jq.ajax({
	    	  type:"POST",
	    	  dataType:"html",
	    	  data:jq("#"+subform).serialize(),
	    	  url:"ydata_delete",
	    	  success:function(data){
	    		  jq("#bodyid").html(data);
	    	  },
	    	  error:function(err){
	    		  jq("#bodyid").html(err.responseText);
	    	  }
	      })
	   }
	}
	

	
  
function print(public_form){
	var public_form=jq("#"+public_form);
	public_form.attr("action","webyadanoinput_print");
	public_form.attr("target","_blank");
	public_form.submit();
	
}	
  
</script>

<body>
	<jsp:include page="publicHead_webYdate.jsp" flush="true" />
	<hr />	
	<span style="float:right"> <input type="button"
		onclick="loadUrl('ydata_findPageBean')" 
		class="btn btn-link btn-sm" value="按日詳細查看"/>
	</span>		
	<div id="bodyid">
		<jsp:include page="table1/ydatanoinput_show1.jsp" />
	</div>
	</body>
</html>
