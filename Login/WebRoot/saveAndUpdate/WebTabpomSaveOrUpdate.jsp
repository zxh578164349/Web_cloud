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

<link href="css/validate.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/form.css" />
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="jquery/layer/layer.min.js"></script>
<script type="text/javascript" src="jquery/swfupload/swfuploadv2.2-min.js"></script>
<script type="text/javascript" src="jquery/swfupload/Validform.swfupload.handler-min.js"></script>
<script type="text/javascript">
var jq=jQuery.noConflict();
var loadi;
jq(document).ajaxStart(function(){
	loadi=layer.load("正在提交,請稍等...");
});
jq(document).ajaxStop(function(){
	layer.close(loadi);
});
	jq(function() {
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			tipSweep:true,
			showAllError : true,
			datatype : {
				"0-6" : /^-?\d{0,12}(\.[0-9]{1,3})?$/
			},
			usePlugin:{
				swfupload:{
					file_post_name: "files",
					upload_url: "webtabpom_swfuploadfile",
					button_image_url: "swfupload/images/XPButtonUploadText_61x22.png",
					flash_url: "swfupload/swfupload.swf",
					
					//覆盖默认绑定好的事件;
					file_dialog_complete_handler:function(){
						//reset the default event;
					},
					upload_complete_handler:function(){
						//文件上传完成后触发表单提交事件，通过this.customSettings.form可取得当前表单对象;
						this.customSettings.form.get(0).submit();
					}
				}
			},
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					//location.href="/Login/webwlo_getList";
				}if(data=="1"){
					alert("提交失敗");
				}
				if(data=="2"){
					layer.msg("數據已經存在",3,1);
				}
			}
		});
		demo.tipmsg.w["0-6"] = "只能數字且不超過12位數,可保留三位以內小數";
	});
	
	function getAllWebbrank(){
		webbrankjs.findAll(function(x){
			dwr.util.addOptions("dwrWebbrank",x,"BNo","BName");
		})
	}

/*禁止空格輸入*/
window.onload=function(){            
            var inputs=document.getElementsByTagName("input"); 
            for (var i=0;i<inputs.length; i++) {  
                if(inputs[i].getAttribute("type")=="text") 
                 inputs[i].onkeyup=function(){ 
                    this.value=this.value.replace(/(^\s+)|\s+$/g,""); 
                 }; 
            }  
        }
        
function makePomNo(){
	var tabpomDate=jq("#tabpomDate").val();
	var component=jq("#component").val();	
	if(tabpomDate!=""&&component!=""){
		webtabpomjs.makePomNo(component,tabpomDate,function(x){			
			jq("#pomNo").val(x);
		})
	}
	
}
var i=0;	
function addFile(){
    i++;
    if(i<5){
    var divfile=document.getElementById("divfile");
    var inputfile=document.createElement("input");
    var aEle=document.createElement("a");
    inputfile.type="file";
    inputfile.name="files";
    inputfile.style.width="150px";
    aEle.innerHTML="刪除";
    aEle.style.color="red";
    aEle.href="javascript:void(0)";
    aEle.onclick=function(){
       var parentnode=aEle.parentNode;
       if(parentnode){
          parentnode.removeChild(aEle);
          parentnode.removeChild(inputfile);
          if(i>4){
             i=4;
          }
          i--;
       }
    };
    divfile.appendChild(inputfile);
    divfile.appendChild(aEle);  
    }else{
       alert("附檔不能超過5個!");
    }               
}
function back(){
	layer.load("正在返回,請稍等...");
	location.href="/Login/webtabpom_findPageBean";
}
window.onload=getAllWebbrank;
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webbrankjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webtabpomjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>
</head>

<body>
	<form action="webtabpom_add" method="post" id="form" enctype="multipart/form-data">
		<table width="100%" align="center" cellspacing="0" cellpadding="0"
			id="msg1">
			  <caption>實驗室形體物性</caption>
			<tbody id="tb_list_info">
				<tr></tr>
			</tbody>
			<tbody id="tb_list_info2">
				
					<tr>
						<td class="td_show_title">物性編號</td>
						<s:if test="tabpom==null">
						<td class="td_input">
						   <input type="text" name="tabpom.pomNo" id="pomNo" style="color:blue" readonly/>
						   <input type="hidden" name="nullmk" value="0"/>
						</td>
						</s:if>
						<s:else>
						<td class="td_input">
						   <input type="text" name="tabpom.pomNo" value="<s:property value='tabpom.pomNo'/>" style="color:blue" readonly/>
						   <input type="hidden" name="nullmk" value="1"/>
						</td>   
						</s:else>
						<td class="td_show_title">物料名稱</td>
						<td class="td_input"><input type="text" name="tabpom.pomName" value="<s:property value='tabpom.pomName'/>"/></td>
					</tr>
					<tr>
						<td class="td_show_title">指定料</td>
						<td class="td_input">
						<s:if test='tabpom.spematerial=="0"'>
						   是<input type="radio" name="tabpom.spematerial" value="0" checked="checked"/>&nbsp;
						</s:if>
						<s:else>
						 是<input type="radio" name="tabpom.spematerial" value="0"/>&nbsp;
						</s:else>
						<s:if test='tabpom.spematerial=="1"'>
						  否<input type="radio" name="tabpom.spematerial" value="1" checked="checked"/>&nbsp;
						</s:if>
						<s:else>
						  否<input type="radio" name="tabpom.spematerial" value="1"/>&nbsp;
						</s:else>						  
						</td>
						<td class="td_show_title">品牌</td>
						<td class="td_input">
						     <select name="tabpom.webBrank.BNo" id="dwrWebbrank">
						        					        
						     </select>
						</td>
					</tr>				
				<tr>
				<td class="td_show_title">部件</td>
						<td class="td_input">
						     <select name="tabpom.component" id="component" onchange="makePomNo()">
						        <option value="">請選擇</option>
						        <option value="RB">RB</option>
						        <option value="MD">MD</option>					        
						     </select>
						</td>
					<td class="td_show_title">生產工廠</td>
					<s:if test="tabpom==null">
					<td class="td_input">
					  <input type="checkbox" name="list_fact" value="631">加元一廠&nbsp;
					  <input type="checkbox" name="list_fact" value="632">加元二廠&nbsp;	
					</td>  				  
					</s:if>
					<s:else>
					<td class="td_input">
					  <s:iterator value="tabpom.webfacts">
					     <s:if test='factNo=="631"'>
					        <input type="checkbox" name="list_fact" value="631" checked="checked">加元一廠&nbsp;
					    </s:if>
					    <s:else>
					       <input type="checkbox" name="list_fact" value="631">加元一廠&nbsp;
					    </s:else>
					    <s:if test='factNo=="632"'>
					        <input type="checkbox" name="list_fact" value="632" checked="checked">加元二廠&nbsp;
					    </s:if>
					    <s:else>
					       <input type="checkbox" name="list_fact" value="632">加元二廠&nbsp;
					    </s:else>
					  </s:iterator>					   
					</td>
					</s:else>
					
					</tr>
				
				
				<tr>
					<td class="td_show_title">硬度</td>
					<td class="td_input"><input type="text" name="tabpom.hardness"
						value="<s:property value='tabpom.hardness' />" datatype="0-6">
					</td>
					<td class="td_show_title">拉力</td>
					<td class="td_input"><input type="text" name="tabpom.forces"
						value="<s:property value='tabpom.forces' />" datatype="0-6">
					</td>
					
				</tr>
				<tr>
					<td class="td_show_title">延伸</td>
					<td class="td_input"><input type="text" name="tabpom.extends_"
						value="<s:property value='tabpom.extends_' />" datatype="0-6">
					</td>
					<td class="td_show_title">C型撕裂</td>
					<td class="td_input"><input type="text" name="tabpom.tearingC"
						value="<s:property value='tabpom.tearingC' />" datatype="0-6">
					</td>
					
				</tr>
				<tr>
					<td class="td_show_title">褲型撕裂</td>
					<td class="td_input"><input type="text" name="tabpom.tearingK"
						value="<s:property value='tabpom.tearingK' />" datatype="0-6" />						
					</td>
					<td class="td_show_title">比重</td>
					<td class="td_input"><input type="text" name="tabpom.proportion"
						value="<s:property value='tabpom.proportion' />" datatype="0-6" />
						
					</td>					
				</tr>
				<tr>
					<td class="td_show_title">AKRON耐磨</td>
					<td class="td_input"><input type="text" name="tabpom.wresistingAkron"
						value="<s:property value='tabpom.wresistingAkron' />" datatype="0-6" />						
					</td>
					<td class="td_show_title">DIN耐磨</td>
					<td class="td_input"><input type="text" name="tabpom.wresistingDin"
						value="<s:property value='tabpom.wresistingDin' />" datatype="0-6" />
						
					</td>					
				</tr>
				<tr>
					<td class="td_show_title">止滑係數</td>
					<td class="td_input"><input type="text" name="tabpom.ratioA"
						value="<s:property value='tabpom.ratioA' />" datatype="0-6" />						
					</td>
					<td class="td_show_title">耐油係數</td>
					<td class="td_input"><input type="text" name="tabpom.ratioB"
						value="<s:property value='tabpom.ratioB' />" datatype="0-6" />
						
					</td>					
				</tr>
				<tr>
					<td class="td_show_title">抗彎曲</td>
					<td class="td_input"><input type="text" name="tabpom.ableBend"
						value="<s:property value='tabpom.ableBend' />" datatype="0-6" />						
					</td>
					<td class="td_show_title">抗黃變</td>
					<td class="td_input"><input type="text" name="tabpom.ableYellow"
						value="<s:property value='tabpom.ableYellow' />" datatype="0-6" />
						
					</td>					
				</tr>
				<tr>
					<td class="td_show_title">抗高壓</td>
					<td class="td_input"><input type="text" name="tabpom.defyPress"
						value="<s:property value='tabpom.defyPress' />" datatype="0-6" />						
					</td>
					<td class="td_show_title">抗靜電</td>
					<td class="td_input"><input type="text" name="tabpom.defyEle"
						value="<s:property value='tabpom.defyEle' />" datatype="0-6" />
						
					</td>					
				</tr>
				<tr>
					<td class="td_show_title">老化水解</td>
					<td class="td_input"><input type="text" name="tabpom.ageing"
						value="<s:property value='tabpom.ageing' />" datatype="0-6" />						
					</td>
					<td class="td_show_title">收縮</td>
					<td class="td_input"><input type="text" name="tabpom.contract"
						value="<s:property value='tabpom.contract' />" datatype="0-6" />
						
					</td>					
				</tr>
				<tr>
					<td class="td_show_title">彈性</td>
					<td class="td_input"><input type="text" name="tabpom.elasticity"
						value="<s:property value='tabpom.elasticity' />" datatype="0-6" />						
					</td>
					<td class="td_show_title">壓縮</td>
					<td class="td_input"><input type="text" name="tabpom.compression"
						value="<s:property value='tabpom.compression' />" datatype="0-6" />
						
					</td>					
				</tr>
				<tr>
					<td class="td_show_title">分裂</td>
					<td class="td_input"><input type="text" name="tabpom.division"
						value="<s:property value='tabpom.division' />" datatype="0-6" />						
					</td>
					<td class="td_show_title">認證</td>
					<td class="td_input"><input type="text" name="tabpom.authentications"
						value="<s:property value='tabpom.authentications' />" datatype="0-6" />
						
					</td>					
				</tr>
				<tr>
					<td class="td_show_title">特性說明</td>
					<td class="td_input"><input type="text" name="tabpom.instruction"
						value="<s:property value='tabpom.instruction' />" datatype="0-6" />						
					</td>
					<td class="td_show_title">附檔</td>
					<td class="td_input">
					  <%--<div style="width:300px" id="divfile">
				      <input type="file" name="files" style="width:150px"/><a href="javascript:addFile()">添加多個</a>
				      </div>--%>
				      <input type="text" value="" id="txtFileName2" disabled="disabled" autocomplete="off" class="inputxt" plugin="swfupload" /> 
				      <span id="spanButtonPlaceholder"></span> (10 MB max)
                      <input type="hidden" pluginhidden="swfupload" name="hidFileID" id="hidFileID" value="" />
				      
				      <input type="text" value="" id="txtFileName2" disabled="disabled" autocomplete="off" class="inputxt" plugin="swfupload" /> 
				      <span id="spanButtonPlaceholder"></span> (10 MB max)
                      <input type="hidden" pluginhidden="swfupload" name="hidFileID" id="hidFileID" value="" />
				      
				      										
						<input type="hidden" value="<s:property value='#session.loginUser.username'/>" name="tabpom.userName" />
						<s:if test="tabpom==null">
						   <input type="hidden" value="<%=str_date%>" name="tabpom.tabpomDate" id="tabpomDate"/>
						</s:if>
						<s:else>
						   <input type="hidden" value="<s:property value='tabpom.tabpomDate'/>" name="tabpom.tabpomDate" />
						</s:else>
						
						
					</td>					
				</tr>
			</tbody>
		</table>
		<center>
			<input type="button" id="sub" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp;			 
				<input type="reset" id="reset" value="重置" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp; 			
				<input type="button" value="返回" id="btn_back"
				onclick="javascript:back()" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
		</center>
	</form>
</body>
</html>
