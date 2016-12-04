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
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<%
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd-hh");
java.util.Date currentTime = new java.util.Date();//得到当前系统时间
String str_date = formatter.format(currentTime); //将日期时间格式化
%>

</head>

<body >
<div class="panel panel-default">
  <div class="panel-heading"><h2>物性資料<s:property value="pomNo"/></h2></div>
  <div class="panel-body">
     <form action="filesUpload_uploadFile" method="post" enctype="multipart/form-data" id="form">			
	<div  id="divfile">
	   <div class="file-box">
			<input type="hidden"  name="file" id="uploadify_m" />																				
			<input type="hidden"  id="uploaddate" value="<%=str_date %>"/>
			<input type="hidden"  id="fileuser" value="${loginUser.username}"/>
			<input type="hidden" id="pomNo" value="<s:property value='pomNo'/>"/>
			<a href="javascript:jq('#uploadify_m').uploadify('cancel','*')" class="btn btn-default">取消上传</a>
            <a href="javascript:jq('#uploadify_m').uploadify('upload','*')" class="btn btn-default">上传</a>
		</div>					
	</div>	
	</form>
	<div id="ul_content">
	<table  id="tb" class="table table-bordered table-condensed">
	   <caption>文件下載</caption>
	   <tr><td>文件名</td><td>上傳時間</td><td>操作</td></tr>
	   <tbody id="tbody">
	   <s:iterator value="tabpom.webTabpomfiles" status="x">
	   <tr>
	     <td>
	     <form id="sub_form${x.index}">
	      <input type="hidden" value="<s:property value='id.filename'/>" name="fileName"/>
	      <input type="hidden" value="<s:property value='id.webTabpom.pomNo'/>" name="pomNo"/>
	    </form>	    	     
	     ${x.index+1}.     		        	        	         
	     <a href="javascript:funmix('sub_form${x.index}','download_inline_pom')" ><s:property value="id.filename"/></a>	    		                   	        	        
	     </td>
	     <td><s:property value="createdate"/></td>
	     <td>
	        <a href="javascript:funmix('sub_form${x.index}','download_download_pom')"  style="color:blue">下載</a>&nbsp;          
	         <!--<a href="javascript:isDelete('del_form${x.index}','download_delete')" style="color:red">刪除</a>-->
	     </td>
	   </tr>
	   </s:iterator>
	  </tbody> 
	</table>
	</div>
	
  </div>
</div>

<script>window.jQuery|| document.write('<script src="jquery/jquery-1.9.1.min.js"><\/script>');</script>
<script type="text/javascript" src="jquery/layer/layer.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>	
<script src="uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<!--[if lt IE 9]>  
  <script src="bootstrap/html5.js"></script>
  <script src="bootstrap/respond.min.js"></script>
  <![endif]-->
<script type="text/javascript">
var jq=jQuery.noConflict();
var uploadify_config = {
		'method':'POST',
	    'uploader' : 'webtabpom_uploadfile_nosession;jsessionid=${pageContext.session.id}',
	    'swf' : 'uploadify/uploadify.swf',
	    'removeTimeout' : 0,
	    'width' : 80,
	    'height' : 30,
	    'multi' : true,
	    'debug':false,
	    'auto' : false,
	    'buttonText' : '選擇文件',
	    'fileTypeExts' : '*.png;*.jpg;*.jpeg;*.tif;*.bmp;*.txt;*.pdf;*.doc;*.xls;*.docx;*.xlsx;*.odt',
	    'fileSizeLimit' : '3MB',
	    'queueSizeLimit' : 5,
	    'fileObjName':'file',
	    'formData':{
	    	"filecreatedate":jq("#uploaddate").val(),
	    	"fileusername":jq("#fileuser").val(),
	    	"pomNo":jq("#pomNo").val()
	    	},	    	   
	    'onFallback':function(){
	          alert("您未安裝FLASH控件，無法上傳圖片！請安裝FLASH控件后再試。");
	        },
	    'onDialogOpen':function(){//打開文件選擇時觸發
		    jq("#btn_upload").removeClass("disabled");
		  },     
	    'onQueueComplete' : function(){		   
		   jq("#btn_upload").addClass("disabled");
		   jq("#ul_content").load("webtabpom_findByIdfiles_ajax?pomNo="+jq("#pomNo").val());
	     },
	   'onSelectError' :function(file, errorCode, errorMsg){
	      if(errorCode==-130){
	         alert("文件類型不對");
	      }
	   }	   
	};
jq(function(){
	jq("#uploadify_m").uploadify(uploadify_config);
})

function funmix(subform,method){
   jq("#"+subform).attr("action",method);
   jq("#"+subform).attr("target","_blank");
   jq("#"+subform).submit();
}


</script>	
</body>
</html>
