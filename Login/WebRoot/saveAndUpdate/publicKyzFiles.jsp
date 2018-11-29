<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
<title>My JSP 'publicHead.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
  <hr/>
	       <div style="color:blue;">附檔:</div><br/>
	       <div id="fileJson" style="width:850px">
	      <s:iterator value="#session.list_filesexp">	        
	           <a href="/<s:property value='fileurl'/>/<s:property value='billno'/>/<s:property value='%{toUrl2(filename)}'/>" target="_blank" title="點擊查看">
	                 <s:property value="%{toUrl(filename)}"/>
	           </a>           
	           <a href="javascript:lookJson('${billno}',${id},'<s:property value="%{toUrl(filename)}"/>','${factNo}')">
	              <img src="images/icon/del_file.png" alt="刪除" title="刪除" style="border:0px"/>
	           </a>&nbsp;&nbsp;	        	        	        
	     </s:iterator>
	     </div>	


<script type="text/javascript">
function lookJson(billNo,id,filename,factNo){
	   jq.ajax({
	      type:"get",
	      dataType:"json",
	      url:"kyzfile_findKyzFileJson",
	      data:{"billNo":billNo,"id":id,"filename":filename,"factNo":factNo},
	      success:function(files){
	         jq("#fileJson").html("");
	          var item="";	          
	         jq.each(files,function(i,file){
	        	var item_url="javascript:lookJson('"+file.billno+"',"+file.id+",'"+file.filename+"','"+file.factNo+"')";	        	
	            item+="<a href='/"+file.fileurl+"/"+file.billno+"/"+file.filename+"' target='_blank' title='點擊查看'>"+file.filename+            
	            "</a>"+
	            "<a href=\""+item_url+"\"><img src='images/icon/del_file.png' alt='刪除' title='刪除' style='border:0px'/></a>&nbsp;";           
	         }); 
	         jq("#fileJson").append(item);
	      }
	   })
	}
</script>	     	     	
</body>
</html>
