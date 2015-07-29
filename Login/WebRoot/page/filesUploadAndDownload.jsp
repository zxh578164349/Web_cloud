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

<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="jquery/DatePicker/my_WdatePicker.js"></script>

<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
<link href="css/input_file.css" rel="stylesheet" type="text/css">

<%
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd-hh:mm");
java.util.Date currentTime = new java.util.Date();//得到当前系统时间
String str_date = formatter.format(currentTime); //将日期时间格式化
%>




<script type="text/javascript">
 var i=0;	
  function addFile(){
      i++;
      if(i<5){
      var divfile=document.getElementById("divfile");
      var divbox=document.createElement("div");   
      var inputtext=document.createElement("input");
      var inputfile=document.createElement("input");
      var inputbutton=document.createElement("input");     
      var aEle=document.createElement("a");
      
      //var blank=document.createTextNode("  ");//創建一個空格元素
      
      divbox.className="file-box-min";
      inputtext.type="text";
      inputtext.className="txt"; 
      inputbutton.type="button";
      inputbutton.value="瀏覽...";
      inputbutton.className="btn";
      inputfile.className="file";              
      inputfile.type="file";
      inputfile.name="files";
      
      inputfile.onchange=function(){
        inputtext.value=inputfile.value;
      };
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
      divbox.appendChild(inputtext);     
      divbox.appendChild(inputfile);
      divbox.appendChild(inputbutton);  
      divbox.appendChild(aEle);  
      }else{
         alert("附檔不能超過5個!");
      }               
  }
</script>
</head>

<body>
	<%-- <div class="file-box">
		<form action="filesUpload_goHome" method="post"
			enctype="multipart/form-data" target="_blank">
			<input type='text' name='textfield' id='textfield' class='txt' /> 
			<input type='button' class='btn' value='瀏覽...' />
			<input type="file"  name="file" class="file" id="fileField" size="28" onchange="document.getElementById('textfield').value=this.value" />								
			<input type="submit" name="submit" class="btn" value="上传" />
			
			<input type="hidden" name="uploadfile.uploaddate" value="<%=str_date %>"/>
			<input type="hidden" name="uploadfile.fileuser" value="${loginUser.username}"/>
			<input type="hidden" name="uploadfile.filefactno" value="${loginUser.factno}"/>
		</form>		
	</div> --%>
	
	<form action="filesUpload_goHome" method="post" enctype="multipart/form-data" >			
	<div  id="divfile">
	   <div class="file-box">
			<input type='text'  class='txt' id="textfield"/> 
			<input type='button' class='btn1' value='浏览...' />
			<input type="file"  name="files" class="file"  onchange="document.getElementById('textfield').value=this.value" />											
			<input type="button" value="添加多個" onclick="addFile()" class="btn"/>
			<input type="submit" name="submit" class="btn1" value="上传" />						
			<input type="hidden" name="uploadfile.uploaddate" value="<%=str_date %>"/>
			<input type="hidden" name="uploadfile.fileuser" value="${loginUser.username}"/>
			<input type="hidden" name="uploadfile.filefactno" value="${loginUser.factno}"/>
		</div>			
	</div>	
	</form>		
	<hr>
	<div id="container">
	<div id="content">
	<table style="width:800px" id="tb">
	   <caption>文件下載</caption>
	   <tr><td>文件名</td><td>上傳時間</td></tr>
	   <tbody id="tbody">
	   <s:iterator value="#session.uploadfiles" status="x">
	   <tr>
	     <td>	    
	     <span style="float:left">
	     ${x.index+1}.
	     <s:if test='filetype=="text/plain"'>
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
	     </s:if>        		        	        	         
	     <a href="download_download?fileName=<s:property value='filename'/>" ><s:property value="filename"/></a>
	    </span>
	    
	    <span style="float:right">  
	          <a href="download_download?fileName=<s:property value='filename'/>" style="color:blue">下載</a>&nbsp;|&nbsp;	          
	          <a href="download_delete?fileName=<s:property value='filename'/>&id=<s:property value='id'/>" style="color:red">刪除</a>
	     </span>		                   	        	        
	     </td>
	     <td><s:property value="uploaddate"/></td>
	   </tr>
	   </s:iterator>
	  </tbody> 
	</table>
	</div>
	</div>
</body>
</html>
