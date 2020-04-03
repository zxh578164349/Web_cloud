<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
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
	<jsp:include page="publicHead_webobjc.jsp" />	
	<hr/>
	
	<div id="bodyid">
		<jsp:include page="table1/webobjc1.jsp" />
	</div>	
	
<script>			
	function pages(page) {	    		
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webobjc_findPageBean3",
			data : "page=" + page,
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				jq("bodyid").html(xhr.responseText);
			}
		});
	}
	function submis(public_form) {	   	    
	      // clearcss();
	       jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webobjc_findPageBean2",
			data:jq("#"+public_form).serialize(),
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				jq("#bodyid").html(xhr.responseText);
			}
		});
		
	}
	
function print(public_form){
	var public_form=jq("#"+public_form);
	public_form.attr("action","webobjc_print_tw2_c");
	public_form.attr("target","_blank");	
	public_form.submit();	
}

function print3(public_form,yymm){
    clearcss();
	var public_form=jq("#"+public_form);	
	public_form.attr("action","webobjc_print_tw");
	public_form.attr("target","_blank");	
	if(jq("#"+yymm).val()==""){
	    jq("#yymm").css("border-color","red");
		layer.msg("請選（年月）日期",3,3);
	}else{
	    jq("#yymm").css("border-color","");
		public_form.submit();
	}
}

function print4(public_form,yymmdd){
    clearcss();
	var public_form=jq("#"+public_form);	
	public_form.attr("action","webobjc_print_tw2");
	public_form.attr("target","_blank");	
	if(jq("#"+yymmdd).val()==""){
	    jq("#yymmdd").css("border-color","red");
		layer.msg("請選（年月日）日期",3,3);
	}else{
	    jq("#yymmdd").css("border-color","");
		public_form.submit();
	}
}

function print5(public_form){
    /* clearcss();
	var public_form=jq("#"+public_form);	
	public_form.attr("action","autoswc_print_manual");
	public_form.attr("target","_blank");	
	if(jq("#"+yymmdd).val()==""){
	    jq("#yymmdd").css("border-color","red");
		layer.msg("請選（年月日）日期",3,3);
	}else{
	    jq("#yymmdd").css("border-color","");
		public_form.submit();
	} */
	
	var public_form=jq("#"+public_form);	
	public_form.attr("action","autoswc_print_manual");
	public_form.attr("target","_blank");
	public_form.submit();	
}

function print6(public_form,factNo,year){
    clearcss();
	var public_form=jq("#"+public_form);	
	public_form.attr("action","webobjc_print3_1");
	public_form.attr("target","_blank");	
	if(jq("select[name='factNo']").val()=="nothing"||jq("select[name='factNo']").val()==""||jq("#year").val()==""){
	    jq("#factNo").css("border-color","red");
	    jq("#year").css("border-color","red");
		layer.msg("請選廠別和年份",3,3);
	}else{
	    jq("#factNo").css("border-color","");
	    jq("#year").css("border-color","");
		public_form.submit();
	}
}

function showDiv(){
    jq.layer({
    type: 2,   //0-4的选择,
    title: '樣本格式說明',
    //border: [0],
    closeBtn: [1,true],
    shade: [0],
    //shade: [0.5, '#000'],
    shadeClose: false,
    border: [10, 0.3, '#000'],
   // btns:1,
    //fadeIn:300,
    //shift:'top',
    offset:['10px',''],
    area:['570px','450px'],               
    iframe:{src:'page/sample/sample_weballobj.jsp',scrolling:'auto'}	                    
});
}

function changeFile(){
   if(jq("#workorholiday").val()=="0"){
     jq("#btn-a").val("導入Excel");
     jq("#id_file").removeClass("disabled");
   }else{
     jq("#btn-a").val("添加");
     jq("#id_file").addClass("disabled");
   }
}

function clearcss(){
  jq("#factNo").css("border-color","");
  jq("#year").css("border-color","");
  jq("#yymm").css("border-color","");
  jq("#yymmdd").css("border-color","");
  
}

function clearcss2(){
  jq("#workorholiday").css("border-color","");
  jq("#id_file").css("border-color","");
  jq("#factNo_in").css("border-color","");
  jq("#yymm_in").css("border-color","");
  
}


function exp_file(){
    clearcss2();
    var public_form=jq("#upload_form");	
	public_form.attr("action","webobjc_exp_file");
	public_form.attr("target","_blank");	
	if(jq("#factNo_in").val()=="nothing"||jq("#factNo_in").val()==""||jq("#yymm_in").val()==""){	
	    jq("#factNo_in").css("border-color","red");
        jq("#yymm_in").css("border-color","red");    
		layer.msg("請選廠別和日期",3,3);
		return false;
	}else{	
	    jq("#factNo_in").css("border-color","");
        jq("#yymm_in").css("border-color","");    
		public_form.submit();
	}
}
</script>		
</body>
</html>
