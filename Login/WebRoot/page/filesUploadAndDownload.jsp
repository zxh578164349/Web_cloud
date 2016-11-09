<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'filesUploadAndDownload.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="uploadify/uploadify.css">

<%
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd-hh:mm");
java.util.Date currentTime = new java.util.Date();//得到当前系统时间
String str_date = formatter.format(currentTime); //将日期时间格式化
%>

</head>

<body >
<br/>
    <div id="bodyid">
	<form action="filesUpload_uploadFile" method="post" enctype="multipart/form-data" id="form">			
	<div  id="divfile">

	   <div class="file-box">
			<!-- <input type="file"  name="files" id="uploadify_m" /> -->	
			<input type="hidden"  name="files" id="uploadify_m" />																				
			<input type="hidden" name="uploadfile.uploaddate" id="uploaddate" value="<%=str_date %>"/>
			<input type="hidden" name="uploadfile.fileuser" id="fileuser" value="${loginUser.username}"/>
			<input type="hidden" name="uploadfile.filefactno" id="filefactno" value="${loginUser.factno}"/>
			<a href="javascript:jq('#uploadify_m').uploadify('cancel','*')" class="btn btn-default">取消上传</a>
            <a href="javascript:jq('#uploadify_m').uploadify('upload','*')" class="btn btn-default">上传</a>
		</div>					
	</div>
	<!--  <input type="button" value="添加多個" onclick="addFile()"/>	
	<input type="button"  value="上传" onclick="upload()"/>-->		
	</form>	
	<div id="ul_content">
	<table  id="tb" class="table table-bordered table-condensed">
	   <caption>文件下載</caption>
	   <tr><td>文件名</td><td>上傳時間</td></tr>
	   <tbody id="tbody">
	   <s:iterator value="#session.uploadfiles" status="x">
	   <tr>
	     <td>	    
	     <span style="float:left">
	     ${x.index+1}.
	     <!-- <s:if test='filetype=="text/plain"'>
	       <img src="images/icon/text.jpg"/>
	     </s:if>
	     <s:if test='filetype=="application/vnd.ms-excel"'>
	       <img src="images/icon/excel.jpg"/>
	     </s:if>
	      <s:if test='filetype=="application/msword"'>
	       <img src="images/icon/word.jpg"/>
	     </s:if>
	     <s:if test='filetype.contains("image")'>
	       <img src="images/icon/picture.jpg"/>
	     </s:if> -->       		        	        	         
	     <a href="download_download?fileName=<s:property value='filename'/>" ><s:property value="filename"/></a>
	    </span>
	    <form id="del_form${x.index}">
	      <input type="hidden" value="<s:property value='filename'/>" name="fileName"/>
	      <input type="hidden" value="<s:property value='id'/>" name="id"/>
	    </form>
	    <span style="float:right">  
	          <a href="download_download?fileName=<s:property value='filename'/>" style="color:blue">下載</a>&nbsp;|&nbsp;	          
	          <a href="javascript:isDelete('del_form${x.index}','download_delete')" style="color:red">刪除</a>
	     </span>		                   	        	        
	     </td>
	     <td><s:property value="uploaddate"/></td>
	   </tr>
	   </s:iterator>
	  </tbody> 
	</table>
	</div>
	</div>

<script type="text/javascript">
//var jq = jQuery.noConflict();
var uploadify_config = {
		'method':'POST',
	    'uploader' : 'filesUpload_uploadFile;jsessionid=${pageContext.session.id}',
	    'swf' : 'uploadify/uploadify.swf',
	    'removeTimeout' : 0,
	    'width' : 80,
	    'height' : 30,
	    'multi' : true,
	    'debug':false,
	    'auto' : false,
	    'buttonText' : '選擇文件',
	    'fileTypeExts' : '*.png;*.jpg;*.jpeg;*.tif;*.bmp;*.txt',
	    'fileSizeLimit' : '3MB',
	    'queueSizeLimit' : 5,
	    'fileObjName':'files',
	    'formData':{
	    	"uploadfile.uploaddate":jq("#uploaddate").val(),
	    	"uploadfile.fileuser":jq("#fileuser").val(),
	    	"uploadfile.filefactno":jq("#filefactno").val()
	    	},
	    'onFallback':function(){
	            alert("您未安裝FLASH控件，無法上傳圖片！請安裝FLASH控件后再試。");
	        },	
	    //'buttonImage':'images/btn_login_black.gif',

	    //'fileTypeDesc' : 'Image Files',
	    //'formData' : {"action": "upload", "sid" : ""},
	   // 'onSelect' : uploadify_onSelect,
	    //'onSelectError' : uploadify_onSelectError,
	    //'onUploadError' : uploadify_onUploadError,
	   'onQueueComplete' : function(){
		   loadUrl("filesUpload_findByName");
	   }	   
	};
jq(function(){
	jq("#uploadify_m").uploadify(uploadify_config);
})


 var i=0;	
  function addFile(){
      i++;
      if(i<5){
      var divfile=document.getElementById("divfile");
      var divbox=document.createElement("div");   
      var inputfile=document.createElement("input");    
      var aEle=document.createElement("a");
      
      //var blank=document.createTextNode("  ");//創建一個空格元素
      
      divbox.className="file-box-min"; 
      inputfile.className="file";              
      inputfile.type="file";
      inputfile.name="files";     
      aEle.innerHTML="刪除";
      aEle.style.color="red";
      aEle.href="javascript:void(0)";
      aEle.onclick=function(){
         var parentnode=divbox.parentNode;
         if(parentnode){
            parentnode.removeChild(divbox);
          
            if(i>4){
               i=4;
            }
            i--;
         }
      };
      
      divfile.appendChild(divbox);      
      divbox.appendChild(inputfile);
      divbox.appendChild(aEle);  
      }else{
         alert("附檔不能超過5個!");
      }               
  }
  
  function upload(){
	  jq.ajax({
		  type:"POST",
		  dataType:"html",
		  data:jq("#form").serialize(),
		  url:"filesUpload_goHome",
		  success:function(data){
			  jq("#ul_content").html(data);
		  },
		  error:function(error){
			  jq("#ul_content").html(error.responseText);
		  }
	  });
  }
  


  
</script>	
</body>
</html>
