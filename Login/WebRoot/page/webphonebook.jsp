<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
<title>My JSP 'managerUser.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

 <LINK href="css/list.css" type="text/css" rel="stylesheet"> 
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script> 	
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="jquery/loding/ui.loading.css" />	
<script type="text/javascript" src="jquery/loding/ui.loading.js"></script>
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">	
<link rel="stylesheet" type="text/css" href="css/general_css.css" />
<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
<script type="text/javascript" src="jquery/jquery-form.js"></script>
</head>
<script type="text/javascript">
	var jq=jQuery.noConflict();
    var loadi;
    jq(document).ajaxStart(function(){
    	loadi=layer.load("正在處理,請稍後....");
    });
    jq(document).ajaxStop(function(){
    	layer.close(loadi);
    });
	function pages(page) {
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webphonebook_findPageBean3",
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
		var public_form=jq("#"+public_form);
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "webphonebook_findPageBean2",
			data :public_form.serialize(),
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("#bodyid").html(xhr.responseText);
			}
		});
	}
	
var defaultColor="#97CBFF";
var clickColor="#CCFFFF";
function click_color(obj){
        var tbody=document.getElementById("tbody");
        var length=document.getElementById("tbody").rows.length;
        for(var i=0;i<length;i++){
            tbody.rows[i].style.backgroundColor=defaultColor;
        }
        obj.style.backgroundColor=clickColor;        
    }
    
function move(obj){
     obj.style.backgroundColor=defaultColor;
    }
   
/* jq(document).keyup(function(event){
    if(event.keyCode==13){
       submis();
    }
})  */
 
 function mydelete(pbId){
    var flag=confirm("確定要刪除嗎?");
    if(flag==true){
       /* window.location.href="userdelete?id="+id;
       var loadi=layer.load("正在處理,請稍後...."); */    
       jq.ajax({
          type:"POST",
          dataType:"html",
          data:"pbId="+pbId,
          url:"webphonebook_delete",
          success:function(msg){
              //layer.close(loadi);
              jq("#bodyid").html(msg);
          },
          error:function(xhr){
              alert(xhr.reponseText);
          }
       });
    }
}



function loaduser(pbId){
	layer.load("请稍等...");
	location.href="/Login/webphonebook_findById?pbId="+pbId;
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
	        url:"webphonebook_importExcel",
	        dataType:'json' ,
	        success:function(data){
	        	if(data=="0"){
	        		layer.msg("導入成功!",3,1);
	        		//location.href="/Login/kyz_findPageBean";
	        	}else if(data=="1"){
	        		layer.msg("導入失敗",3,3);
	        		showDiv();
	        	}else if(data=="2"){
	        		layer.msg("數據重複或其它因素,導入失敗",3,3);
	        	}else if(data=="3"){
	        	    //layer.msg("Excel文檔結構不符合要求或數據量過大，禁止導入",3,3);
	        	    
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
    area:['650px','450px'],
    //page:{url:'kyz_findById_layer?billNo='+billNo+'& factNo='+factNo}                   
    iframe:{src:'page/sample/sample2.jsp',scrolling:'auto'}	                    
});
    }
</script>

<body>
	<jsp:include page="publicHead_webphonebook.jsp" />
	<hr />								  	       	       	     		
	<form  method="post" enctype="multipart/form-data" id="upload_form">
	<table>
	  <tr>
	   <s:if test='#session.loginUser.userread!="1"'>
	   <td>
	      <input value="添加新用戶" type="button" id="search_forday" onclick="javascript:location.href='saveAndUpdate/webphonebookSaveOrUpdate.jsp'"/> 
	      &nbsp;&nbsp;
	   </td>
	   </s:if>
	   <td>
	    <input value="導入Excel" type="submit" id="search_forday" />	      
	   </td>
	   <td>
	     <input type="file" name="file" style="width:150px" id="id_file"/>
	   </td>
	  </tr>
	</table>
	</form>
	<div id="bodyid">
		<jsp:include page="table1/webphonebook1.jsp" />
	</div>
</body>
</html>
