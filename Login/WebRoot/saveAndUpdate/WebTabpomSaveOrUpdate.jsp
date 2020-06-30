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
<!DOCTYPE HTML>
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
	                    <th>物性編號</th><th>配方索引</th><th colspan="3">品牌</th>
	                 </tr>	                          	                
	               <tr>
	                  <td>
	                     <s:if test="tabpom==null">
	                        <input type="text" name="tabpom.pomNo" placeholder="自動生成" id="pomNo" style="color:blue" readonly datatype="*"/>						    
						    <input type="hidden" value="<s:property value='#session.loginUser.username'/>" name="tabpom.username" />
						    <input type="hidden" value="<%=str_date%>" name="tabpom.tabpomDate" id="tabpomDate"/>
						    <input type="hidden" name="nullmk" value="0"/>
	                     </s:if>
	                     <s:else>
	                         <input type="text" name="tabpom.pomNo" value="<s:property value='tabpom.pomNo'/>" id="pomNo" style="color:blue" readonly/>						     
						     <input type="hidden" value="<s:property value='tabpom.username'/>" name="tabpom.username" />
						     <input type="hidden" value="<s:property value='tabpom.tabpomDate'/>" name="tabpom.tabpomDate" />
						     <input type="hidden" value="<s:property value='#session.loginUser.username'/>" name="tabpom.modifyName"/>
						     <input type="hidden" value="<%=str_date%>" name="tabpom.modifyDate" />
						     <input type="hidden" name="nullmk" value="1"/>
	                     </s:else>
	                  </td>
	                  <td>
	                      <input type="text" name="tabpom.formulaId.formulaIndex" value="<s:property value='tabpom.formulaId.formulaIndex'/>" id="formulaIndex2"  datatype="*"  style="color:blue"/>
	                  </td>
	                  <td>
	                      <s:if test="tabpom==null">
						     <select name="tabpom.webBrank.id" id="dwrWebbrank" datatype="*" onchange="makePomNo2('dwrWebbrank','tabpomDate')">						      					        					        
						     </select>
						  </s:if>
						  <s:else>
						     <input type="text"  value="<s:property value='tabpom.webBrank.name'/>" readonly style="color:blue"/>
						     <input type="hidden" name="tabpom.webBrank.id" value="<s:property value='tabpom.webBrank.id'/>"/>
						  </s:else>   
	                  </td>
	               </tr>
	                   <tr class="active">
	                      <th>名稱</th><th>規格</th><th>測試方式說明</th><th>单位</th><th>测试结果</th>	
	                   </tr>	                
	                <tr>
	                   <td>硬度</td>
	                   <td>
	                      <input type="text" name="tabpom.hardness" value="<s:property value='tabpom.hardness'/>"/><br/>
	                      <input type="text" name="tabpom.hardness2" value="<s:property value='tabpom.hardness2'/>">(±值)
	                   </td>
	                   <td>
	                      <input type="text" name="tabpom.hardnessDescription" value="<s:property value='tabpom.hardnessDescription'/>"/>
	                   </td>
	                   <td>
	                      <input type="text" name="tabpom.hardnessUnit" value="<s:property value='tabpom.hardnessUnit'/>" datatype="*0-30"/>
	                   </td>
	                   <td>
	                      <input type="text" name="tabpom.hardnessResult" value="<s:property value='tabpom.hardnessResult'/>" datatype="*0-100"/>
	                   </td>
	                </tr>
	                <tr>
	                   <td>拉力</td>
	                   <td><input type="text" name="tabpom.forces" value="<s:property value='tabpom.forces'/>"/></td>
	                   <td><input type="text" name="tabpom.forcesDescription" value="<s:property value='tabpom.forcesDescription'/>"/></td>
	                   <td><input type="text" name="tabpom.forcesUnit" value="<s:property value='tabpom.forcesUnit'/>" datatype="*0-30"/></td>
	                   <td><input type="text" name="tabpom.forcesResult" value="<s:property value='tabpom.forcesResutl'/>" datatype="*0-100"/></td>
	                </tr>
	                <tr>
	                   <td>延伸</td>
	                   <td><input type="text" name="tabpom.extend" value="<s:property value='tabpom.extend'/>"/></td>
	                   <td><input type="text" name="tabpom.extendsDescription" value="<s:property value='tabpom.extendsDescription'/>"/></td>
	                   <td><input type="text" name="tabpom.extendsUnit" value="<s:property value='tabpom.extendsUnit'/>" datatype="*0-30"/></td>
	                   <td><input type="text" name="tabpom.extendsResult" value="<s:property value='tabpom.extendsResult'/>" datatype="*0-100"/></td>
	                </tr>
	                <tr>
	                   <td>C型撕裂</td>
	                   <td><input type="text" name="tabpom.tearingC" value="<s:property value='tabpom.tearingC'/>"/></td>
	                   <td><input type="text" name="tabpom.tearingCDescription" value="<s:property value='tabpom.tearingCDescription'/>"/></td>
	                   <td><input type="text" name="tabpom.tearingCUnit" value="<s:property value='tabpom.tearingCUnit'/>" datatype="*0-30"/></td>
	                   <td><input type="text" name="tabpom.tearingCResult" value="<s:property value='tabpom.tearingCResult'/>" datatype="*0-100"/></td>
	                </tr>
	                <tr>
	                   <td>褲型撕裂</td>
	                   <td><input type="text" name="tabpom.tearingK" value="<s:property value='tabpom.tearingK'/>"/></td>
	                   <td><input type="text" name="tabpom.tearingKDescription" value="<s:property value='tabpom.tearingKDescription'/>"/></td>
	                   <td><input type="text" name="tabpom.tearingKUnit" value="<s:property value='tabpom.tearingKUnit'/>" datatype="*0-30"/></td>
	                   <td><input type="text" name="tabpom.tearingKResult" value="<s:property value='tabpom.tearingKResult'/>" datatype="*0-100"/></td>
	                </tr>
	                <tr>
	                   <td>比重</td>
	                   <td>
	                      <input type="text" name="tabpom.proportion" value="<s:property value='tabpom.proportion'/>"/><br/>
	                      <input type="text" name="tabpom.proportion2" value="<s:property value='tabpom.proportion2'/>">(±值)
	                   </td>
	                   <td>
	                     <input type="text" name="tabpom.proportionDescription" value="<s:property value='tabpom.proportionDescription'/>"/> 
	                   </td>
	                   <td>
	                     <input type="text" name="tabpom.proportionUnit" value="<s:property value='tabpom.proportionUnit'/>" datatype="*0-30"/> 
	                   </td>
	                   <td>
	                     <input type="text" name="tabpom.proportionResult" value="<s:property value='tabpom.proportionResult'/>" datatype="*0-100"/> 
	                   </td>
	                </tr>
	                <tr>
	                   <td>AKRON耐磨</td>
	                   <td><input type="text" name="tabpom.wresistingAkron" value="<s:property value='tabpom.wresistingAkron'/>"/></td>
	                   <td><input type="text" name="tabpom.wresistingAkronDes" value="<s:property value='tabpom.wresistingAkronDes'/>"/></td>
	                   <td><input type="text" name="tabpom.wresistingAkronUnit" value="<s:property value='tabpom.wresistingAkronUnit'/>" datatype="*0-30"/></td>
	                   <td><input type="text" name="tabpom.wresistingAkronResult" value="<s:property value='tabpom.wresistingAkronResult'/>" datatype="*0-100"/></td>
	                </tr>
	                <tr>
	                   <td>DIN耐磨</td>
	                   <td><input type="text" name="tabpom.wresistingDin" value="<s:property value='tabpom.wresistingDin'/>"/></td>
	                   <td><input type="text" name="tabpom.wresistingDinDes" value="<s:property value='tabpom.wresistingDinDes'/>"/></td>
	                   <td><input type="text" name="tabpom.wresistingDinUnit" value="<s:property value='tabpom.wresistingDinUnit'/>" datatype="*0-30"/></td>
	                   <td><input type="text" name="tabpom.wresistingDinResult" value="<s:property value='tabpom.wresistingDinResult'/>" datatype="*0-100"/></td>
	                </tr>
	                <tr>
	                   <td>止滑係數</td>
	                   <td><input type="text" name="tabpom.ratioA" value="<s:property value='tabpom.ratioA'/>"/></td>
	                   <td><input type="text" name="tabpom.ratioADes" value="<s:property value='tabpom.ratioADes'/>"/></td>
	                   <td><input type="text" name="tabpom.ratioAUnit" value="<s:property value='tabpom.ratioAUnit'/>" datatype="*0-30"/></td>
	                   <td><input type="text" name="tabpom.ratioAResult" value="<s:property value='tabpom.ratioAResult'/>" datatype="*0-100"/></td>
	                </tr>
	                <tr>
	                   <td>耐油係數</td>
	                   <td><input type="text" name="tabpom.ratioB" value="<s:property value='tabpom.ratioB'/>"/></td>
	                   <td><input type="text" name="tabpom.ratioBDes" value="<s:property value='tabpom.ratioBDes'/>"/></td>
	                   <td><input type="text" name="tabpom.ratioBUnit" value="<s:property value='tabpom.ratioBUnit'/>" datatype="*0-30"/></td>
	                   <td><input type="text" name="tabpom.ratioBResult" value="<s:property value='tabpom.ratioBResult'/>" datatype="*0-100"/></td>
	                </tr>
	                <tr>
	                   <td>耐彎曲</td>
	                   <td><input type="text" name="tabpom.ableBend" value="<s:property value='tabpom.ableBend'/>"/></td>
	                   <td><input type="text" name="tabpom.ableBendDes" value="<s:property value='tabpom.ableBendDes'/>"/></td>
	                   <td><input type="text" name="tabpom.ableBendUnit" value="<s:property value='tabpom.ableBendUnit'/>" datatype="*0-30"/></td>
	                   <td><input type="text" name="tabpom.ableBendResult" value="<s:property value='tabpom.ableBendResult'/>" datatype="*0-100"/></td>
	                </tr>
	                <tr>
	                   <td>耐黃變</td>
	                   <td><input type="text" name="tabpom.ableYellow" value="<s:property value='tabpom.ableYellow'/>"/></td>
	                   <td><input type="text" name="tabpom.ableYellowDes" value="<s:property value='tabpom.ableYellowDes'/>"/></td>
	                   <td><input type="text" name="tabpom.ableYellowUnit" value="<s:property value='tabpom.ableYellowUnit'/>" datatype="*0-30"/></td>
	                   <td><input type="text" name="tabpom.ableYellowResult" value="<s:property value='tabpom.ableYellowResult'/>" datatype="*0-300"/></td>
	                </tr>
	                <tr>
	                   <td>抗高壓</td>
	                   <td><input type="text" name="tabpom.defyPress" value="<s:property value='tabpom.defyPress'/>"/></td>
	                   <td><input type="text" name="tabpom.defyPressDes" value="<s:property value='tabpom.defyPressDes'/>"/></td>
	                   <td><input type="text" name="tabpom.defyPressUnit" value="<s:property value='tabpom.defyPressUnit'/>" datatype="*0-30"/></td>
	                   <td><input type="text" name="tabpom.defyPressResult" value="<s:property value='tabpom.defyPressResult'/>" datatype="*0-100"/></td>
	                </tr>
	                <tr>
	                   <td>抗靜電</td>
	                   <td><input type="text" name="tabpom.defyEle" value="<s:property value='tabpom.defyEle'/>"/></td>
	                   <td><input type="text" name="tabpom.defyEleDes" value="<s:property value='tabpom.defyEleDes'/>"/></td>
	                   <td><input type="text" name="tabpom.defyEleUnit" value="<s:property value='tabpom.defyEleUnit'/>" datatype="*0-30"/></td>
	                   <td><input type="text" name="tabpom.defyEleResult" value="<s:property value='tabpom.defyEleResult'/>" datatype="*0-100"/></td>
	                </tr>
	                <tr>
	                   <td>老化水解</td>
	                   <td><input type="text" name="tabpom.ageing" value="<s:property value='tabpom.ageing'/>"/></td>
	                   <td><input type="text" name="tabpom.ageingDes" value="<s:property value='tabpom.ageingDes'/>"/></td>
	                   <td><input type="text" name="tabpom.ageingUnit" value="<s:property value='tabpom.ageingUnit'/>" datatype="*0-30"/></td>
	                   <td><input type="text" name="tabpom.ageingResult" value="<s:property value='tabpom.ageingDesResult'/>" datatype="*0-100"/></td>
	                </tr>
	                <tr>
	                   <td>收縮</td>
	                   <td><input type="text" name="tabpom.contract" value="<s:property value='tabpom.contract'/>"/></td>
	                   <td><input type="text" name="tabpom.contractDes" value="<s:property value='tabpom.contractDes'/>"/></td>
	                   <td><input type="text" name="tabpom.contractUnit" value="<s:property value='tabpom.contractUnit'/>" datatype="*0-30"/></td>
	                   <td><input type="text" name="tabpom.contractResult" value="<s:property value='tabpom.contractResult'/>" datatype="*0-100"/></td>
	                </tr>
	                <tr>
	                   <td>彈性</td>
	                   <td><input type="text" name="tabpom.elasticity" value="<s:property value='tabpom.elasticity'/>"/></td>
	                   <td><input type="text" name="tabpom.elasticityDes" value="<s:property value='tabpom.elasticityDes'/>"/></td>
	                   <td><input type="text" name="tabpom.elasticityUnit" value="<s:property value='tabpom.elasticityUnit'/>" datatype="*0-30"/></td>
	                   <td><input type="text" name="tabpom.elasticityResult" value="<s:property value='tabpom.elasticityResult'/>" datatype="*0-100"/></td>
	                </tr>
	                <tr>
	                   <td>壓縮</td>
	                   <td><input type="text" name="tabpom.compression" value="<s:property value='tabpom.compression'/>"/></td>
	                   <td><input type="text" name="tabpom.compressionDes" value="<s:property value='tabpom.compressionDes'/>"/></td>
	                   <td><input type="text" name="tabpom.compressionUnit" value="<s:property value='tabpom.compressionUnit'/>" datatype="*0-30"/></td>
	                   <td><input type="text" name="tabpom.compressionResult" value="<s:property value='tabpom.compressionResult'/>" datatype="*0-100"/></td>
	                </tr>
	                <tr>
	                   <td>分裂</td>
	                   <td><input type="text" name="tabpom.division" value="<s:property value='tabpom.division'/>"/></td>
	                   <td><input type="text" name="tabpom.divisionDes" value="<s:property value='tabpom.divisionDes'/>"/></td>
	                   <td><input type="text" name="tabpom.divisionUnit" value="<s:property value='tabpom.divisionUnit'/>" datatype="*0-30"/></td>
	                   <td><input type="text" name="tabpom.divisionResult" value="<s:property value='tabpom.divisionResult'/>" datatype="*0-100"/></td>
	                </tr>
	                <tr>
	                   <td>300% Modulus</td>
	                   <td><input type="text" name="tabpom.modulus300" value="<s:property value='tabpom.modulus300'/>"/></td>
	                   <td><input type="text" name="tabpom.modulus300Des" value="<s:property value='tabpom.modulus300Des'/>"/></td>
	                   <td><input type="text" name="tabpom.modulus300Unit" value="<s:property value='tabpom.modulus300Unit'/>" datatype="*0-30"/></td>
	                   <td><input type="text" name="tabpom.modulus300Result" value="<s:property value='tabpom.modulus300Result'/>" datatype="*0-100"/></td>
	                </tr>
	                <tr>
	                   <td>吐霜</td>
	                   <td><input type="text" name="tabpom.spitCream" value="<s:property value='tabpom.spitCream'/>"/></td>
	                   <td><input type="text" name="tabpom.spitCreamDes" value="<s:property value='tabpom.spitCreamDes'/>"/></td>
	                   <td><input type="text" name="tabpom.spitCreamUnit" value="<s:property value='tabpom.spitCreamUnit'/>" datatype="*0-30"/></td>
	                   <td><input type="text" name="tabpom.spitCreamResult" value="<s:property value='tabpom.spitCreamResult'/>" datatype="*0-100"/></td>
	                </tr>
	                <tr>
	                   <td>認證</td>
	                   <td colspan="2">
	                       <s:if test='tabpom.authentications=="1"'>
					                            是<input type="radio" name="tabpom.authentications" value="1" checked="checked" />
								
						</s:if>
						 <s:else>
					                            是<input type="radio" name="tabpom.authentications" value="1" />
						</s:else> 
						<s:if test='tabpom.authentications=="0"'>
					                        否<input type="radio" name="tabpom.authentications" value="0" checked="checked" />								
						</s:if> 
						<s:else>
					                          否<input type="radio" name="tabpom.authentications" value="0" />
						</s:else>
	                   </td>
	                </tr>
	                <tr>
	                   <td>特性說明</td>
	                   <td colspan="4">
	                         <textarea style="width:100%;height:100px" name="tabpom.instruction"><s:property value='tabpom.instruction' /></textarea>
	                         <input type="hidden" name="tabpom.fileMk" value="<s:property value='tabpom.fileMk'/>"/>					         							                     
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
			<a href="javascript:checkpomNo()" class="btn btn-default" id="btn_upload">上傳</a>
			<a href="javascript:cancelFile()" class="btn btn-default">取消</a>           
		</div>					
	</div>
	</div>
</div>
<center>
			<input type="button" id="sub_pom" value="確定"  class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;			 
			<!-- <input type="reset" id="reset" value="重置" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;  -->			
			<input type="button" value="返回" id="btn_back" onclick="javascript:back()" class="btn btn-primary"/>
				
</center>	
</form>
<hr/>	
<s:if test="tabpom.webTabpomfiles.size>0">		
		<div id="webtabfiledao">	
		 <b style="color: blue">附檔:</b><br/>
		 <div id="fileJson">
		   <s:iterator value="tabpom.webTabpomfiles">
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
	    'onDialogOpen':function(){//打開文件選擇時觸發
		    jq("#btn_upload").removeClass("disabled");
		  },     
	    'onQueueComplete' : function(){
		   //loadUrl("filesUpload_findByName");
		   jq("#btn_upload").addClass("disabled");
		   layer.msg("請按下面的【確定】保存",3,1);
	     },
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
		//beforeSubmit:checkpomNo,														
		ajaxPost:true,
		callback:function(data){
			if(data=="0"){				
				if(jq("hidden[name='isnull']").val()==null||jq("hidden[name='isnull']").val()==""){
				  layer.msg("修改成功!",3,1);
				  loadUrl("webformula_findById?formulaIndex="+jq("#formulaIndex").val());				  
				}else{
				  layer.msg("添加成功，請按【確定】保存",3,0);
				  jq("#a_webformula").click();
				}				
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
				var item="";
				jq.each(data,function(i,obj){
					item+="<option value='"+obj[0]+"'>"+obj[2]+"</option>";					
				});
				jq("#dwrWebbrank").append(item);
			}
		});
	})

function checkpomNo(){
	if(jq("#pomNo").val()==""){
		alert("請完整資料,再上傳附檔");
	}else{		
		jq.ajax({
			type:"get",
			url:"webtabpom_clearSession",
			async:false,
			success:function(){
				jq("#uploadify_m").uploadify("upload","*");								
			}
		});
	}
}
function cancelFile(){
	jq("#uploadify_m").uploadify("cancel","*");
	jq("#btn_upload").addClass("disabled");
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
	          var item="";
	          var item_url;
	         jq.each(files,function(i,file){
	            item_url="javascript:lookJson('"+file[0]+"',"+"'"+file[1]+"')";
	            item+="<a href='/upload_webtabpom/"+file[0]+"/"+file[1]+"' target='_blank' title='點擊查看'>"+file[1]+            
	            "</a>"+
	            "<a href="+item_url+"><img src='images/icon/del_file.png' alt='刪除' title='刪除' style='border:0px'/></a>&nbsp;";	            
	         });
	         jq("#fileJson").append(item); 
	      }
	   });
	}
	
function back(){
	loadUrl("webtabpom_findPageBean3?backIndex=1");
}
/*禁止空格輸入*/
goTrim();	

</script>
</body>
</html>
