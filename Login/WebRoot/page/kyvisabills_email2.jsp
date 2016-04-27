
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>函文審核</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">


</style>
<script type="text/javascript" src="page/jquerys/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
 <!--[if lt IE 9]>  
  <script src="bootstrap/html5.js"></script>
  <script src="bootstrap/respond.min.js"></script>
  <![endif]-->	
<script type="text/javascript">
    function showDiv(billNo,factNo){
    	var area_w;
    	var area_h;
    	var src;    	
    	if(billNo.substring(0,2)=='EM'){
    		area_w='650px';
    		area_h='560px';
    		src='kyz_findById_layer?billNo='+billNo+'& factNo='+factNo+'& readMk=Y';
    	}
    	if(billNo.substring(0,2)=='CM'){
    		area_w='620px';
    		area_h='450px';
    		src='kyzletter_findById_layer?billNo='+billNo+'& factNo='+factNo+'& readMk=Y';
    	}
    	if(billNo.substring(0,2)=='BM'){
    		area_w='620px';
    		area_h='450px';
    		src='bussletter_findById_layer?billNo='+billNo+'& readMk=Y';
    	}
    	if(billNo.substring(0,2)=='RM'){
    		area_w='650px';
    		area_h='560px';
    		src='webremit_findById_layer?billNo='+billNo+'& factNo='+factNo+'& readMk=Y';
    	}
    $.layer({
    type: 2,   //0-4的选择,
    title: '函文內容',
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
    area:[area_w,area_h],
    //page:{url:'kyz_findById_layer?billNo='+billNo+'& factNo='+factNo}                   
    iframe:{src:src,scrolling:'auto'}	
    /* close:function(){
		location.reload();
	} */                 
});
}
      
    function check(factNo,visaSort,billNo,itemNo){
    	var area_w;
    	var area_h;
    	var src;    	
    	if(billNo.substring(0,2)=='EM'){
    		area_w='650px';
    		area_h='560px';
    		src='kyz_findById_layer?billNo='+billNo+'& factNo='+factNo+'& itemNo='+itemNo+'& visaSort='+visaSort+'& readMk=N';
    	}
    	if(billNo.substring(0,2)=='CM'){
    		area_w='620px';
    		area_h='500px';
    		src='kyzletter_findById_layer?billNo='+billNo+'& factNo='+factNo+'& itemNo='+itemNo+'& visaSort='+visaSort+'& readMk=N';
    	}
    	if(billNo.substring(0,2)=='BM'){
    		area_w='620px';
    		area_h='500px';
    		src='bussletter_findById_layer?billNo='+billNo+'& factNo='+factNo+'& itemNo='+itemNo+'& visaSort='+visaSort+'& readMk=N';
    	}
    	if(billNo.substring(0,2)=='RM'){
    		area_w='650px';
    		area_h='560px';
    		src='webremit_findById_layer?billNo='+billNo+'& factNo='+factNo+'& itemNo='+itemNo+'& visaSort='+visaSort+'& readMk=N';
    	}
    $.layer({
    type: 2,   //0-4的选择,
    title: '函文內容',
    //border: [0],
    border: [10, 0.3, '#000'],
    closeBtn: [1,true],
    shade: [0],
    //shade: [0.5, '#000'],
    shadeClose: false,
     btns:2,
     btn:['通過','不通過'],
    //fadeIn:300,
    //shift:'top',
    offset:['10px',''],
    area: [area_w, area_h],
    //page:{url:'kyz_findById_layer?billNo='+billNo+'& factNo='+factNo},
    //iframe:{src:'kyz_findById_layer?billNo='+billNo+'& factNo='+factNo,scrolling:'auto'},
    
    /* 修改1   20151025 */
    iframe:{src:src,scrolling:'auto'},
    /* 修改1   20151025 */
    
     yes:function(){              
     //window.location.href='vbm_add?billNo='+billNo+'& visa_mk=Y'+'& factNo='+factNo+'& itemNo='+itemNo+'& visaSort='+visaSort; 
     
     /*********************** 修改2   20151025 ******************************/
     var memo=layer.getChildFrame("#memo_txt",layer.index).val();
     layer.getChildFrame("#visa_mk",layer.index).val('Y'); 
     if(memo.length>150){
        alert("備註不可超過150字");
     }else{
        window.location.href='success.html';             
         layer.getChildFrame("#memo",layer.index).submit();
         layer.load("正在處理，請稍等...");
     }
     
     /*********************** 修改2   20151025 ******************************/  
   
    },
    no:function(){
      //window.location.href='vbm_add?billNo='+billNo+'& visa_mk=T'+'& factNo='+factNo+'& itemNo='+itemNo+'& visaSort='+visaSort;
      /*********************** 修改2   20151025 ******************************/
     var memo=layer.getChildFrame("#memo_txt",layer.index).val();
     layer.getChildFrame("#visa_mk",layer.index).val('T'); 
     if(memo.length>150){
        alert("備註不可超過150字");
     }else{
        window.location.href='success.html';             
         layer.getChildFrame("#memo",layer.index).submit();
         layer.load("正在處理，請稍等...");
     }
       
     /*********************** 修改2   20151025 ******************************/ 
    }       
});
}
    

function tips(memo,index){
    if(memo==''){
       memo='無備註';
    }
    layer.tips(memo, '#'+index, {
    style: ['background-color:#78BA32; color:#fff', '#78BA32'],
    maxWidth:240,
    time: 10,
    closeBtn:[0, true]
});
}
</script>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
		<h3 class="single_h3">函文審核</h3>
	    <table class="table table-bordered table-condensed">
		<thead>
		 <tr class="info">
		  <td>函文單號</td>
		  <s:iterator value="vbm.kyVisabillses">
		   <td><s:property value="visaRank"/></td>
		  </s:iterator> 
		 </tr>
		</thead>
		<tr class="warning">
		  <td>
		  <a id="a_show" href="javascript:showDiv('<s:property value='vbm.id.billNo'/>','<s:property value='vbm.id.factNo'/>')">
		  <s:property value="vbm.id.billNo"/></a>		 
		  </td> 
		  <s:iterator value="vbm.kyVisabillses" status="x">
	       <td>
	       <!-- <s:property value="vbm.signerNext"/>(<s:property value="visaSigner"/>) -->
	       <s:if test='flowMk=="Y"'>
	       <s:if test='visaMk=="N"'><!-- 1.判斷未審和已審狀態 -->
	         <s:if test='id.itemNo==vbm.itemNext'><!--2.判斷當前審核人的項次是否為下一位審核人 的項次 -->	           
	           <s:if test="%{strToLow(visaSigner)==strToLow(email)}"><!-- 3.判斷登錄者是否為當前審核人 -->
	              <a style="color:red" href="javascript:check('<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>',
	              '<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.itemNo'/>')">       
	                                     未審核<s:property value="id.itemNo"/>(當前審核人)
	              </a>	              	              
	           </s:if>	
	            <s:else>
	             <a disabled style="color:grey">未審核</a>
	           </s:else>             
	         </s:if>
	          <s:else>
	             <a disabled style="color:grey">未審核</a>
	           </s:else> 	        	       
	       </s:if>
	       <s:if test='visaMk=="Y"'>	       
	        <a style="color:green" href="javascript:tips('${memo}','index${x.index}')" id="index${x.index}">已審核<br/>(<s:property value="dateVisa"/>)</a>
	       </s:if>
	       <s:if test='visaMk=="T"'>
	         <a style="color:blue" href="javascript:tips('${memo}','index${x.index}')" id="index${x.index}">未通過<br/>(<s:property value="dateVisa"/>)</a>
	       </s:if>
	       </s:if>
	       <s:else>
	       <a style="color:#b45b3e">只知會</a>
	       </s:else>
	       </td>
	      </s:iterator>
		</tr>		
	</table>
 </div>	
</div>	
</div>
<jsp:include page="../copyright.jsp"/>	
</body>
</html>
