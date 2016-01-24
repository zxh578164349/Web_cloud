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
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<link rel="stylesheet" type="text/css" href="css/general_css.css" />
<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
<script type="text/javascript" src="jquery/jquery-form.js"></script>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>

 <!--[if lt IE 9]>  
  <script src="bootstrap/html5.js"></script>
  <script src="bootstrap/respond.min.js"></script>
  <![endif]-->	
</head>
<script>
	var jq=jQuery.noConflict();
	var loadi;
	//var global_temp=jq("#global_temp").val();
	jq(document).ajaxStart(function(){		
			loadi=layer.load("正在處理...");					
	});
	jq(document).ajaxStop(function(){
		layer.close(loadi);
	});
	function pages(page) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webfactOrder_findPageBean3",
			data : "page=" + page ,
			success : function(msg) {
				jq("#bodyid").html(msg);				
				jq("html,body").animate({scrollTop:$("#bodyid").offset().top}, 500);
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("#bodyid").html(xhr.responseText);
			}
		});
	}
	function submis(public_form) {
		jq("#global_temp").val("search");
		var topindex=document.getElementById('bodyid').offsetTop;
		var fact = document.getElementById("factNo");
		var ym = document.getElementById("year");
		var subform=jq("#"+public_form);
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webfactOrder_findPageBean2",
			data:subform.serialize(),
			success : function(msg) {	
				jq("#bodyid").html(msg);
				//jq("html,body").scrollTop(500);
				jq("html,body").animate({scrollTop:$("#bodyid").offset().top}, 500);
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("#bodyid").html(xhr.responseText);
			}
		});
	}
	//你确定要删除吗？
	function isDelete(mid) {
		jq("#global_temp").val("delete");
		jConfirm('你确定这么做吗?', '确认对话框', function(r) {
			if (r == true) {
				//document.getElementById(mid).submit();
				jq.ajax({
					type:"POST",
					dataType:"html",
					data:jq("#"+mid).serialize(),
					url:"webfactOrder_delete",
					success:function(data){
						jq("#bodyid").html(data);
					},
					error:function(data){
						jq("#bodyid").html(data.responseText);
					}
				});
			}
		});
	}
	function print(public_form){
		var subform=jq("#"+public_form);
		var checkboxs=jq("input[name='factNos']:checked");
		var checkboxs_factSnames=jq("input[name='factSnames']");
		var checkboxs_branks=jq("input[name='branks']");
		var checkboxs_customers=jq("input[name='customers']");
		
		var checkboxs_factSnames_checked=jq("input[name='factSnames']:checked");
		var checkboxs_branks_checked=jq("input[name='branks']:checked");
		var checkboxs_customers_checked=jq("input[name='customers']:checked");
		subform.attr("action","webfactOrder_print4");
		subform.attr("target","_blank");
		if(checkboxs.length==0){//if1
		   layer.msg("請選擇工廠",3,3);
		   jq("#div_factNos").css("border","2px solid red");
		   return false;
		}//if1
		if(jq("#yymm").val()==""||jq("#yymm2").val()==""){
			layer.msg("請選擇日期",3,3);
			jq("#div_yymm").css("border","2px solid red");
			return false;
		}else{
			jq("#div_yymm").css("border","");
		}
		if(checkboxs.length!=0){//if2
			jq("#div_factNos").css("border","1px dashed blue");
			if(checkboxs_factSnames.length==0&&checkboxs_branks.length==0&&checkboxs_customers.length==0){
				layer.msg("暫無數據可選擇,建議先導入Excel",3,3);
			}else if(checkboxs_factSnames.length!=0&&checkboxs_branks.length!=0&&checkboxs_customers.length!=0){//else 
				if(checkboxs_factSnames_checked.length==0&&checkboxs_branks_checked.length==0&&checkboxs_customers_checked.length==0){
					layer.msg("請選擇工廠細分或品牌或客戶",3,3);
					jq("#div_factSname").css("border","2px solid red");
					jq("#div_brank").css("border","2px solid red");
					jq("#div_customer").css("border","2px solid red");
					return false;
				}else{
					jq("#div_factSname").css("border","");
					jq("#div_brank").css("border","");
					jq("#div_customer").css("border","");
					subform.submit();
				}				
			}else if(checkboxs_factSnames.length!=0&&checkboxs_branks.length==0&&checkboxs_customers.length==0){
				if(checkboxs_factSnames_checked.length==0){
					layer.msg("請選擇工廠細分",3,3);
					jq("#div_factSname").css("border","2px solid red");
					return  false;
										
				}else{
					jq("#div_factSname").css("border","");					
					subform.submit();
				}
			}//else 
								   		   
		}//if2
		
		//jq("#"+subform).removeAttr("action");
		//jq("#"+subform).removeAttr("target");
	}
	
function checkForm(){
	var id_file=jq("#id_file").val();
	var extendName=id_file.substr(id_file.lastIndexOf(".")).toLowerCase();
	if(id_file==""){
		layer.alert("請選擇Excel文檔");
		return false;
	}else if(extendName!=".xls"&&extendName!=".xlsx"){
		layer.alert("僅允許Excel文檔");
		return false;
	}
	
	return true;
}

jq(function(){
	var options={
			beforeSubmit:checkForm,  		       		       
	        //resetForm: true, 
	        url:"webfactOrder_importExcel",
	        dataType:'json' ,
	        success:function(data){
	        	if(data=="0"){
	        		layer.msg("導入成功!",3,1);
	        		//location.href="/Login/kyz_findPageBean";
	        	}else if(data=="1"){
	        		layer.msg("僅允許導入Excel文檔",3,3);
	        	}else if(data=="2"){
	        		layer.msg("數據重複或其它因素,導入失敗",3,3);
	        	}else if(data=="3"){
	        	    //layer.msg("Excel文檔結構不符合要求或數據量過大，禁止導入",3,3);
	        	    showDiv();
	        	    layer.msg("Excel文檔結構不符合要求,或數據量超出預估,或其它因素，禁止導入",3,3);
	        	}else if(data=="4"){
	        		layer.msg("Excel文檔不兼容，或其它因素(建議先打開文檔幷且保存,再重新嘗試導入)",4,3);	        	     
	        	}else{
	        		alert(data);
	        	}		        	       	    									
	         }
	         
	};

	jq("#upload_form").submit(function(){
		jq(this).ajaxSubmit(options);
		return false;
	})
})


function setWindowScrollTop(win, topHeight){
    if(win.document.documentElement){ 
        win.document.documentElement.scrollTop = topHeight;
    }
    if(win.document.body){
        win.document.body.scrollTop = topHeight;
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
    //area: ['800px', '560px'],
    area:['650px','560px'],
    //page:{url:'kyz_findById_layer?billNo='+billNo+'& factNo='+factNo}                   
    iframe:{src:'sample.jsp',scrolling:'auto'}	                    
});
    }
	
</script>

<body>
<div>
    <input type="hidden" id="global_temp"
	<jsp:include page="publicHead_print_webfactorder.jsp" />
	<hr />
	<s:if test='#session.loginUser.userread!="1"'>
	<form  method="post" enctype="multipart/form-data" id="upload_form">	  
	       <input type="file" name="file" style="width:150px" id="id_file"/>
	       <input value="導入Excel" type="submit" id="search_forday" />	     	
	</form>
	<hr/>
	<%-- <input value="添加" type="button" id="addbtn" onclick="javascript:location.href='saveAndUpdate/WebProdutedSaveOrUpdate.jsp'" />--%>
	
	</s:if>	
	<div id="bodyid">
		<jsp:include page="table1/webfactOrder1.jsp" />
	</div>
</div>	
</body>
</html>
