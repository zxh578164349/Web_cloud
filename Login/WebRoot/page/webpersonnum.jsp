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
<!--  <link rel="stylesheet" type="text/css" href="css/button_css.css" />
<link rel="stylesheet" type="text/css" href="css/general_css.css" />
<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />-->
	
</head>
<script>
	
	function pages(page) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webpersonnum_findPageBean3",
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
		var beginday=document.getElementById("beginday");
		var endday=document.getElementById("endday");
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webpersonnum_findPageBean2",
			//data : "factNo=" + fact.value + "&beginDay=" + beginday.value+"&endDay="+endday.value,
			data:jq("#"+public_form).serialize(),
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("#bodyid").html(responseText);
			}
		});
	}
	//你确定要删除吗？
	function isDelete(mid) {
		 var flag=confirm("確定要刪除嗎?");
		
			if (flag == true) {
				//document.getElementById(mid).submit();
				jq.ajax({
					type:"POST",
					dataType:"html",
					data:jq("#"+mid).serialize(),
					url:"webpersonnum_delete",
					success:function(data){
						jq("#bodyid").html(data);
					},
					error:function(data){
						jq("#bodyid").html(data.responseText);
					}
				});
			}
		
	}
	
function showDiv(){
    jq.layer({
    type: 1,   //0-4的选择,
    title: '出勤報表',
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
    area: ['500px', '150px'],
    page:{
      url:'search/webpersonnum_search_print.jsp'   
    },
    close:function(){
       window.location.href='webpersonnum_findPageBean';
    }
            
});
    }
    
    function print(public_form){
    	var public_form=jq("#"+public_form);
    	public_form.attr("action","webpersonnum_print_search");
    	public_form.attr("target","_blank");
    	public_form.submit();
    }
</script>

<body>
	<jsp:include page="publicHead_webPersonnum.jsp" flush="true" />
	<hr />
		<s:if test="#session.factNo=='tw'">
		<span style="float:right">
		<input type="button"
		onclick="javascript:showDiv()" 
		class="btn btn-link btn-sm" value="出勤統計報表"/>
	   </span>
		</s:if>			
	<div id="bodyid">
		<jsp:include page="table1/webpersonnum1.jsp" />
	</div>
</body>
</html>
