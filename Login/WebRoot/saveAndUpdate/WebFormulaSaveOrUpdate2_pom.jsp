<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
java.util.Date currentTime = new java.util.Date();//得到当前系统时间
String str_date = formatter.format(currentTime); //将日期时间格式化
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'WebMixPersonSaveOrUpdate.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />
<link rel="stylesheet" type="text/css" href="uploadify/uploadify.css">
</head>

<body>
	<form action="webtabpom_add" method="post" id="form_pom" enctype="multipart/form-data">	
	    <div class="panel panel-default">	        
	        <div class="panel-body">	        	            	           
	            <table class="table table-condensed">	              
	                 <tr class="active">
	                    <th>物性編號</th><th>配方索引</th><th>品牌</th>
	                 </tr>	                          	                
	               <tr>
	                  <td>
	                     <s:if test="formula.pom==null">
	                        <input type="text" name="tabpom.pomNo" placeholder="自動生成" id="pomNo" style="color:blue" readonly datatype="*"/>
						    <input type="hidden" name="nullmk" value="0"/>
	                     </s:if>
	                     <s:else>
	                         <input type="text" name="tabpom.pomNo" value="<s:property value='formula.pom.pomNo'/>" id="pomNo" style="color:blue" readonly/>
						     <input type="hidden" name="nullmk" value="1"/>
	                     </s:else>
	                  </td>
	                  <td>
	                      <input type="text" name="tabpom.formulaId.formulaIndex" value="<s:property value='formula.formulaIndex'/>" id="pomName" datatype="*" readonly style="color:blue"/>
	                  </td>
	                  <td>
	                      <s:if test="formula.pom==null">
						     <select name="tabpom.webBrank.id" id="dwrWebbrank" datatype="*" onchange="makePomNo2('dwrWebbrank','tabpomDate')">						      					        					        
						     </select>
						  </s:if>
						  <s:else>
						     <input type="text"  value="<s:property value='formula.pom.webBrank.name'/>" readonly style="color:blue"/>
						      <input type="hidden" name="tabpom.webBrank.id" value="<s:property value='formula.pom.webBrank.id'/>" readonly style="color:blue"/>
						  </s:else>   
	                  </td>
	               </tr>
	                   <tr class="active">
	                      <th>名稱</th><th>規格</th><th>測試方式說明</th>	
	                   </tr>	                
	                <tr>
	                   <td>硬度</td>
	                   <td>
	                      <input type="text" name="tabpom.hardness" value="<s:property value='formula.pom.hardness'/>" datatype="*8-2"/><br/>
	                      <input type="text" name="tabpom.hardness2" value="<s:property value='pom.hardness2'/>" datatype="*8-2"/>(±值)
	                   </td>
	                   <td>
	                      <input type="text" name="tabpom.hardnessDescription" value="<s:property value='pom.hardnessDescription'/>" datatype="*0-300"/>
	                   </td>
	                </tr>
	                <tr>
	                   <td>拉力</td>
	                   <td><input type="text" name="tabpom.forces" value="<s:property value='pom.forces'/>" datatype="*8-2"/></td>
	                   <td><input type="text" name="tabpom.forcesDescription" value="<s:property value='pom.forcesDescription'/>" datatype="*0-300"/></td>
	                </tr>
	                <tr>
	                   <td>延伸</td>
	                   <td><input type="text" name="tabpom.extend" value="<s:property value='pom.extend'/>" datatype="*8-2"/></td>
	                   <td><input type="text" name="tabpom.extendsDescription" value="<s:property value='pom.extendsDescription'/>" datatype="*0-300"/></td>
	                </tr>
	                <tr>
	                   <td>C型撕裂</td>
	                   <td><input type="text" name="tabpom.tearingC" value="<s:property value='pom.tearingC'/>" datatype="*8-2"/></td>
	                   <td><input type="text" name="tabpom.tearingCDescription" value="<s:property value='pom.tearingCDescription'/>" datatype="*0-300"/></td>
	                </tr>
	                <tr>
	                   <td>比重</td>
	                   <td>
	                      <input type="text" name="tabpom.proportion" value="<s:property value='pom.proportion'/>" datatype="*8-2"/><br/>
	                      <input type="text" name="tabpom.proportion2" value="<s:property value='pom.proportion2'/>" datatype="*8-2"/>(±值)
	                   </td>
	                   <td>
	                     <input type="text" name="tabpom.proportionDescription" value="<s:property value='pom.proportionDescription'/>" datatype="*0-300"/> 
	                   </td>
	                </tr>
	                <tr>
	                   <td>AKRON耐磨</td>
	                   <td><input type="text" name="tabpom.wresistingAkron" value="<s:property value='pom.wresistingAkron'/>" datatype="*8-2"/></td>
	                   <td><input type="text" name="tabpom.wresistingAkronDes" value="<s:property value='pom.wresistingAkronDes'/>" datatype="*0-300"/></td>
	                </tr>
	                <tr>
	                   <td>DIN耐磨</td>
	                   <td><input type="text" name="tabpom.wresistingDin" value="<s:property value='pom.wresistingDin'/>" datatype="*8-2"/></td>
	                   <td><input type="text" name="tabpom.wresistingDinDes" value="<s:property value='pom.wresistingDinDes'/>" datatype="*0-300"/></td>
	                </tr>
	                <tr>
	                   <td>止滑係數</td>
	                   <td><input type="text" name="tabpom.ratioA" value="<s:property value='pom.ratioA'/>" datatype="*8-2"/></td>
	                   <td><input type="text" name="tabpom.ratioADes" value="<s:property value='pom.ratioADes'/>" datatype="*0-300"/></td>
	                </tr>
	                <tr>
	                   <td>耐油係數</td>
	                   <td><input type="text" name="tabpom.ratioB" value="<s:property value='pom.ratioB'/>" datatype="*8-2"/></td>
	                   <td><input type="text" name="tabpom.ratioBDes" value="<s:property value='pom.ratioBDes'/>" datatype="*0-300"/></td>
	                </tr>
	                <tr>
	                   <td>耐彎曲</td>
	                   <td><input type="text" name="tabpom.ableBend" value="<s:property value='pom.ableBend'/>" datatype="*8-2"/></td>
	                   <td><input type="text" name="tabpom.ableBendDes" value="<s:property value='pom.ableBendDes'/>" datatype="*0-300"/></td>
	                </tr>
	                <tr>
	                   <td>耐黃變</td>
	                   <td><input type="text" name="tabpom.ableYellow" value="<s:property value='pom.ableYellow'/>" datatype="*8-2"/></td>
	                   <td><input type="text" name="tabpom.ableYellowDes" value="<s:property value='pom.ableYellowDes'/>" datatype="*0-300"/></td>
	                </tr>
	                <tr>
	                   <td>抗高壓</td>
	                   <td><input type="text" name="tabpom.defyPress" value="<s:property value='pom.defyPress'/>" datatype="*8-2"/></td>
	                   <td><input type="text" name="tabpom.defyPressDes" value="<s:property value='pom.defyPressDes'/>" datatype="*0-300"/></td>
	                </tr>
	                <tr>
	                   <td>抗靜電</td>
	                   <td><input type="text" name="tabpom.defyEle" value="<s:property value='pom.defyEle'/>" datatype="*8-2"/></td>
	                   <td><input type="text" name="tabpom.defyEleDes" value="<s:property value='pom.defyEleDes'/>" datatype="*0-300"/></td>
	                </tr>
	                <tr>
	                   <td>老化水解</td>
	                   <td><input type="text" name="tabpom.ageing" value="<s:property value='pom.ageing'/>" datatype="*8-2"/></td>
	                   <td><input type="text" name="tabpom.ageingDes" value="<s:property value='pom.ageingDes'/>" datatype="*0-300"/></td>
	                </tr>
	                <tr>
	                   <td>收縮</td>
	                   <td><input type="text" name="tabpom.contract" value="<s:property value='pom.contract'/>" datatype="*8-2"/></td>
	                   <td><input type="text" name="tabpom.contractDes" value="<s:property value='pom.contractDes'/>" datatype="*0-300"/></td>
	                </tr>
	                <tr>
	                   <td>彈性</td>
	                   <td><input type="text" name="tabpom.elasticity" value="<s:property value='pom.elasticity'/>" datatype="*8-2"/></td>
	                   <td><input type="text" name="tabpom.elasticityDes" value="<s:property value='pom.elasticityDes'/>" datatype="*0-300"/></td>
	                </tr>
	                <tr>
	                   <td>壓縮</td>
	                   <td><input type="text" name="tabpom.compression" value="<s:property value='pom.compression'/>" datatype="*8-2"/></td>
	                   <td><input type="text" name="tabpom.compressionDes" value="<s:property value='pom.compressionDes'/>" datatype="*0-300"/></td>
	                </tr>
	                <tr>
	                   <td>分裂</td>
	                   <td><input type="text" name="tabpom.division" value="<s:property value='pom.division'/>" datatype="*8-2"/></td>
	                   <td><input type="text" name="tabpom.divisionDes" value="<s:property value='pom.divisionDes'/>" datatype="*0-300"/></td>
	                </tr>
	                <tr>
	                   <td>300% Modulus</td>
	                   <td><input type="text" name="tabpom.modulus300" value="<s:property value='pom.modulus300'/>" datatype="*8-2"/></td>
	                   <td><input type="text" name="tabpom.modulus300Des" value="<s:property value='pom.modulus300Des'/>" datatype="*0-300"/></td>
	                </tr>
	                <tr>
	                   <td>吐霜</td>
	                   <td><input type="text" name="tabpom.spitCream" value="<s:property value='pom.spitCream'/>" datatype="*8-2"/></td>
	                   <td><input type="text" name="tabpom.spitCreamDes" value="<s:property value='pom.spitCreamDes'/>" datatype="*0-300"/></td>
	                </tr>
	                <tr>
	                   <td>認證</td>
	                   <td colspan="2">
	                       <s:if test='tabpom.authentications=="0"'>
					   是<input type="radio" name="tabpom.authentications" value="0"
								checked="checked" />
						</s:if> <s:else>
					   是<input type="radio" name="tabpom.authentications" value="0" />
						</s:else> <s:if test='tabpom.authentications=="1"'>
					   否<input type="radio" name="tabpom.authentications" value="1"
								checked="checked" />
						</s:if> <s:else>
					   否<input type="radio" name="tabpom.authentications" value="1" />
						</s:else>
	                   </td>
	                </tr>
	                <tr>
	                   <td>特性說明</td>
	                   <td colspan="2">
	                      <textarea style="width:100%;height:100px" name="tabpom.instruction" datatype="*0-300"><s:property value='pom.instruction'/></textarea>					      
						<s:if test="tabpom==null">
						<input type="hidden" value="<s:property value='#session.loginUser.username'/>" name="tabpom.username" />
						<input type="hidden" value="<%=str_date%>" name="tabpom.tabpomDate" id="tabpomDate"/>
						</s:if>
						<s:else>
						   <input type="hidden" value="<s:property value='pom.tabpomDate'/>" name="tabpom.tabpomDate" />
						   <input type="hidden" value="<s:property value='pom.username'/>" name="tabpom.username" />
						   <input type="hidden" value="${loginUser.username }" name="tabpom.modifyName" />
						   <input type="hidden" value="<%=str_date%>" name="tabpom.modifyDate" />
						</s:else>
	                     
	                     <input type="hidden" name="<s:property value='pom.fileMk'/>" value="tabpom.fileMk"/>
	                   </td>                 
	                </tr>
	                                         											
</table>
	
</div>
</div>

<div class="panel panel-default">
    <div class="panel-heading">文檔上傳</div>
    <div class="panel-body">
       <div  id="divfile">
	   <div class="file-box">				
			<input type="hidden"  name="file" id="uploadify_m" />																				
			<input type="hidden"  id="uploaddate" value="<%=str_date %>"/>
			<input type="hidden"  id="fileuser" value="${loginUser.username}"/>
			<a href="javascript:jq('#uploadify_m').uploadify('cancel','*')" class="btn btn-default">取消上传</a>
            <!-- <a href="javascript:checkpomNo()" class="btn btn-default">上传</a>-->
		</div>					
	</div>
	</div>
</div>
<center>
			<input type="button" id="sub_pom" value="確定"  class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;			 			
			<input type="button" value="返回" onclick="javascript:back()" class="btn btn-primary"/>
				
</center>
 </form>		
	<hr/>		
     <s:if test="formula.pom.webTabpomfiles.size>0">		
      <div id="webtabfiledao">	
	    <b style="color: blue">附檔:</b><br/>
		 <div id="fileJson">
		   <s:iterator value="formula.pom.webTabpomfiles">
		        <a href="/upload_webtabpom/<s:property value='id.webTabpom.pomNo'/>/<s:property value="%{toUrl2(id.filename)}"/>" target="_blank">
		        <s:property value="id.filename"/>&nbsp;
		        </a>
		        <a href="javascript:lookJson('<s:property value="id.webTabpom.pomNo"/>','<s:property value="%{toUrl(id.filename)}"/>')">
	              <img src="images/icon/del_file.png" alt="刪除" title="刪除" style="border:0px"/>
	           </a>&nbsp;&nbsp;
		   </s:iterator>
		  </div>	  
     </div>		
    <hr/>
</s:if> 	 

			

		


	
<script type="text/javascript">
var uploadify_config = {
		'method':'POST',
	    'uploader' : 'webtabpom_uploadfile_session;jsessionid=${pageContext.session.id}',
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
	    //'buttonImage':'images/btn_login_black.gif',
	    //'fileTypeDesc' : 'Image Files',
	    //'formData' : {"action": "upload", "sid" : ""},
	    // 'onSelect' : uploadify_onSelect,
	    //'onSelectError' : uploadify_onSelectError,
	    //'onUploadError' : uploadify_onUploadError,
	   /*'onQueueComplete' : function(){
		   //loadUrl("filesUpload_findByName");
	   },*/
	   'onSelectError' :function(file, errorCode, errorMsg){
	      if(errorCode==-130){
	         alert("文件類型不對");
	      }
	   }	   
	};
jq(function(){
	jq("#uploadify_m").uploadify(uploadify_config);
	
	
	var demo = jq("#form_pom").Validform({
		btnSubmit : "#sub_pom",
		tiptype : 4,
		tipSweep:true,
		showAllError : true,
		datatype : {
			"*8-2" : /^-?\d{0,8}(\.[0-9]{0,2})?$/
		},									
		beforeSubmit:checkpomNo,														
		ajaxPost:true,
		callback:function(data){
			if(data=="0"){
				layer.msg("提交成功!",3,1);
				loadUrl("/Login/webformula_findById?formulaIndex="+jq("#formulaIndex").val());
			}
			if(data=="1"){
				layer.msg("提交失敗",3,3);
			}
			if(data=="2"){
				layer.msg("數據已經存在",3,1);
			}
			if(data=="3"){
				layer.msg("附檔上傳失敗",3,3);
			}
		}
	});
	demo.tipmsg.w["*8-2"] = "只能數字且不超過8位數,可保留2位以內小數";	
	
	/*加載所有品牌*/
	jq.ajax({
		type:"get",
		url:"weberpbp_findObjOp2",
		dataType:"json",
		success:function(data){
			jq("#dwrWebbrank").empty();
			jq("#dwrWebbrank").append("<option value=''>品牌選擇</option>");
			var item;
			jq.each(data,function(i,obj){
				item="<option value='"+obj[0]+"'>"+obj[2]+"</option>";
				jq("#dwrWebbrank").append(item);
			})
		}
	});
		
})

function checkpomNo(){
	if(jq("#pomNo").val()==""){
		alert("請完整資料,再上傳附檔");
	}else{
		//jq.load("webtabpom_clearSession");
		jq.ajax({
			type:"get",
			url:"webtabpom_clearSession",
			success:function(){
				jq("#uploadify_m").uploadify("upload","*");
			}
		});
		
	}
}

			
	       
/*物性編號*/
function makePomNo2(dwrWebbrank,tabpomDate){
	var brank=jq("#"+dwrWebbrank).val();
	var tabpomDate=jq("#"+tabpomDate).val();
	if(brank!=""&&brank!=null&&tabpomDate!=""&&tabpomDate!=null){
		jq.ajax({
			type:"POST",
			dataType:"json",
			url:"webtabpom_makePomNo",
			data:{"brank":brank,"tabpomDate":tabpomDate},
			//data:"{'brank':'"+ brank +"','tabpomDate':'"+ tabpomDate +"'}",
			success:function(data){
				jq("#pomNo").val(data);
			}			
		});
	}	
}

function lookJson(pomNo,filename){
	   jq.ajax({
	      type:"get",
	      dataType:"json",
	      url:"webtabpomfile_findwebtabpomFileJson",
	      data:"pomNo="+pomNo+"&filename="+filename,
	      success:function(files){
	         jq("#fileJson").html("");
	          var item;
	          var item_url;
	         jq.each(files,function(i,file){
	            item_url="javascript:lookJson('"+file[0]+"',"+"'"+file[1]+"')";
	            item="<a href='/upload/"+file[0]+"/"+file[1]+"' target='_blank' title='點擊查看'>"+file[1]+            
	            "</a>"+
	            "<a href="+item_url+"><img src='images/icon/del_file.png' alt='刪除' title='刪除' style='border:0px'/></a>&nbsp;";
	            jq("#fileJson").append(item);
	         }) 
	      }
	   })
	}
	
</script>
</body>
</html>
