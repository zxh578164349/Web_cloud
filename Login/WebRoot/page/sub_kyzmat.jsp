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
	<jsp:include page="publicHead_kyzmat.jsp"/>
	<hr>		
		
		<!--<input value="返回物料資料" type="button" id="search_forday" onclick="backKyzmat()"/>-->
		
		<span style="float:right"> <input type="button"
		onclick="backKyzmat()"
		class="btn btn-link btn-sm" value="返回物料資料"/>
	    </span>		
	<div id="bodyid">
		<jsp:include page="table1/sub_kyzmat1.jsp" />
	</div>

	
<script>
 
	function pages(page) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "kyzmat_findPageBean3",
			data : "page=" + page,
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	
	function submis(public_form) {
		var begindate = document.getElementById("beginDate");
		var enddate=document.getElementById("endDate");
		var matNo=document.getElementById("matNo");
		var bNo=document.getElementById("bNo");
		var mNo=document.getElementById("mNo");
		var sNo=document.getElementById("sNo");
		var matcname=document.getElementById("matcname");
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "kyzmat_findPageBean2",
			/*data : "fromDate=" + begindate.value +"& endDate="+enddate.value+"& matNo="+matNo.value
			        +"& typeBno="+bNo.value+"& typeMno="+mNo.value+"& typeSno="+sNo.value
			        +"& matCname="+matcname.value,*/
			  data:jq("#"+public_form).serialize(),      
			success : function(msg) {
				jq("#bodyid").html(msg);				
			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}
	
function selectAll(){
  var cb_all=document.getElementById("cb_all");
  var cbs=jq("[name='cb_list']");
  if(cb_all.checked==true){
      for(var i=0;i<cbs.length;i++){
          cbs[i].checked=true;
      }
  }else{
      for(var i=0;i<cbs.length;i++){
          cbs[i].checked=false;
      }
  }
  }
  
  function backKyzmat(){
     loadUrl("subkyzmat_findPageBean");
  }
  function print(public_form){
  	var public_form=jq("#"+public_form);
  	public_form.attr("action","kyzmat_print");
  	public_form.attr("target","_blank");
  	public_form.submit();
  }
  
	//你确定要删除吗？	
	function isDelete(matNo) {
		var flag=confirm("確定要刪除嗎");
		
			if (flag == true) {				
				jq.ajax({
					type:"POST",
					dataType:"html",
					data:"matNo="+matNo,
					url:"subkyzmat_delete",
					success:function(data){
						jq("#bodyid").html(data);
					},
					error:function(error){
						jq("#bodyid").html(error);
					}
				});
			}
		
	}
	
function kyzmat_print_select(){
	jq("#subkyzmat_form").attr("action","kyzmat_print_select");
	jq("#subkyzmat_form").attr("target","_blank");
	jq("#subkyzmat_form").submit();
	
}
function subkyzmat_addSubKyzmat(){
	jq.ajax({
		type:"POST",
		dataType:"html",
		data:jq("#subkyzmat_form").serialize(),
		url:"subkyzmat_addSubKyzmat",
		success:function(data){
			jq("#r_content").html(data);
		},
		error:function(error){
			jq("#r_content").html(error.responseText);
		}
	});
}

	
</script>

</body>
</html>
