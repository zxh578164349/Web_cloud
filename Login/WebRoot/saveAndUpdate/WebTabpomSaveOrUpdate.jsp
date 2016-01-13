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
<script type="text/javascript" src="jquery/jquery-form.js"></script>
<script type="text/javascript">
var jq=jQuery.noConflict();
var loadi;
jq(document).ajaxStart(function(){
	loadi=layer.load("正在處理,請稍等...");
});
jq(document).ajaxStop(function(){
	layer.close(loadi);
});
	/*jq(function() {
		var demo = jq("#subform").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			tipSweep:true,
			showAllError : true,
			datatype : {
				"0-8" : /^-?\d{0,8}(\.[0-9]{1,2})?$/
			},									
			beforeSubmit:checkFile,														
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					//location.href="/Login/webwlo_getList";
				}
				if(data=="1"){
					alert("提交失敗");
				}
				if(data=="2"){
					layer.msg("數據已經存在",3,1);
				}
			}
		});
		demo.tipmsg.w["0-8"] = "只能數字且不超過8位數,可保留2位以內小數";
		demo.addRule([{ele:":checkbox:first",datatype:"*"},{ele:":radio:first",datatype:"*"}]); (注意:checkbox:first和:radio:first前面要加":")
	});*/
	jq(function(){
		var options={
				beforeSubmit:checkForm,  		       		       
		        //resetForm: true, 
		        url:"webtabpom_add",
		        dataType:'json' ,
		        success:function(data){
		        	if(data=="0"){
		        		layer.msg("函文申請成功!",3,1);
		        		//location.href="/Login/kyz_findPageBean";
		        	}else if(data=="1"){
		        		layer.msg("函文申請失敗!",3,3);
		        	}else if(data=="2"){
		        		layer.msg("數據已存在該數據",3,3);
		        	}else if(data=="3"){
		        		layer.msg("單個文件不可超過5M",2,3);
		        	}else if(data=="4"){
		        		layer.msg("僅允許上傳圖片文件",2,3);
		        	}else{
		        		alert(data);
		        	}		        			        	        	
		         }		         
		};
		jq("#subform").submit(function(){
			jq(this).ajaxSubmit(options);
			return false;
		})										
	})
	function getAllWebbrank(){
		webbrankjs.findAll(function(x){
			dwr.util.addOptions("dwrWebbrank",x,"BNo","BName");
		});
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


function checkForm(){	
			var agent = navigator.userAgent.toLowerCase();
			var eles=jq("input[name='files']");
			var flag=true;
				if(agent.indexOf("msie") > 0){
					var path;
					var img=new Image();
					jq.each(eles,function(i,ele){
						path=ele.value;
						img.src=path;
						var extname=path.substr(path.lastIndexOf(".")).toLowerCase()
						if(extname!=".jpg"&&extname!=".jpeg"&&extname!=".gif"&&extname!=".bmp"&&extname!=".tif"){
							layer.msg("僅允許上傳圖片文件",2,3);
							flag=false;
							return false;
							
						}
						if(img.fileSize/(1024*1024)>5){
							layer.msg("單個文件不可超過5M",2,3);
							//break;
							flag=false;
							return false;
						}
					})
				}else{
					jq.each(eles,function(i,ele){
						path=ele.value;
						var extname=path.substr(path.lastIndexOf(".")).toLowerCase()
						if(extname!=".jpg"&&extname!=".jpeg"&&extname!=".gif"&&extname!=".bmp"&&extname!=".tif"){
							layer.msg("僅允許上傳圖片文件",2,3);
							flag=false;
							return false;
						}
						if(ele.files[0].size/(1024*1024)>5){
							layer.msg("單個文件不可超過5M",2,3);
							//break;
							flag=false;
							return false;
						}
					})
				}
				
				var component=jq("#component").val();
				var pomName=jq("#pomName").val();
				var dwrWebbrank=jq("#dwrWebbrank").val();
				if(component==""){
					layer.msg("請選擇部件",2,3);
					flag=false;
				}
				if(pomName==""){
					layer.msg("物料名稱不能爲空",2,3);
					flag=false;
				}
				if(dwrWebbrank==""){
					layer.msg("請選擇品牌",2,3);
					flag=false;
				}
				return flag;
}

function lookJson(pomNo,filename){
	//var jQ = jQuery.noConflict();
	/*var loadi;
	filename=encodeURI(encodeURI(filename));
	jq(document).ajaxStart(function(){
		loadi=layer.load(0);
	});
	jq(document).ajaxStop(function(){
		layer.close(loadi);
	});*/
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
	            item_url="javascript:lookJson('"+file.id.webTabpom.pomNo+"',"+"'"+file.id.filename+"')";
	            item="<a href='/upload/"+file.id.webTabpom.pomNo+"/"+file.id.filename+"' target='_blank' title='點擊查看'>"+id.file.filename+            
	            "</a>"+
	            "<a href="+item_url+"><img src='images/icon/del_file.png' alt='刪除' title='刪除' style='border:0px'/></a>&nbsp;";
	            jq("#fileJson").append(item);
	         }) 
	      }
	   })
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
<div style="height:600px;overflow:auto">
	<form action="webtabpom_add" method="post" id="subform" enctype="multipart/form-data">
		<table width="100%"  cellspacing="0" cellpadding="0"
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
						   <input type="text" name="tabpom.pomNo" value="自動生成" id="pomNo" style="color:blue" readonly/>
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
						<td class="td_input"><input type="text" name="tabpom.pomName" value="<s:property value='tabpom.pomName'/>" id="pomName" datatype="*1-30"/></td>
					</tr>
					<tr>
						<td class="td_show_title">指定料</td>
						<td class="td_input">
						<s:if test='tabpom.spematerial=="0"'>
						   是<input type="radio" name="tabpom.spematerial" value="0" checked="checked"/>&nbsp;
						</s:if>
						<s:else>
						 是<input type="radio" name="tabpom.spematerial" value="0" datatype="*"/>&nbsp;   <%--隻要在第一箇radio標明datatype="*" --%>
						</s:else>
						<s:if test='tabpom.spematerial=="1"'>
						  否<input type="radio" name="tabpom.spematerial" value="1" checked="checked"/>&nbsp;
						</s:if>
						<s:else>
						  否<input type="radio" name="tabpom.spematerial" value="1" />&nbsp;
						</s:else>						  
						</td>
						<td class="td_show_title">品牌</td>
						<td class="td_input">
						  <s:if test="tabpom==null">
						     <select name="tabpom.webBrank.BNo" id="dwrWebbrank" datatype="*">
						       <option value="">請選擇</option>						        					        
						     </select>
						  </s:if>
						  <s:else>
						     <input type="text" name="tabpom.webBrank.BNo" value="<s:property value='tabpom.webBrank.BNo'/>" readonly style="color:blue"/>
						  </s:else>   
						</td>
					</tr>				
				<tr>
				<td class="td_show_title">部件</td>
						<td class="td_input">
						  <s:if test="tabpom==null">
						     <select name="tabpom.component" id="component" onchange="makePomNo()" datatype="*">
						        <option value="">請選擇</option>
						        <option value="RB">RB</option>
						        <option value="MD">MD</option>					        
						     </select>
						  </s:if>
						  <s:else>
						      <input type="text" name="tabpom.component" value="<s:property value='tabpom.component'/>" readonly style="color:blue" />
						  </s:else>   
						</td>
					<td class="td_show_title">生產工廠</td>
					
					<td class="td_input">
					 <s:if test="tabpom==null">
					  <div style="height:80px;overflow:auto">
					  <s:iterator value="#session.facts" id="temp">
					     <div><input type="checkbox" name="list_fact" value="${temp[0]}">${temp[1] }</div>
					  </s:iterator>
					  </div>	
					  </s:if>
					  <s:else>
					    <div style="heigth:80px;overflow:auto">					    
					    <s:iterator value="tabpom.webfacts" id="temp2">					     
					        <div><input type="checkbox" name="list_fact" value="<s:property value='factNo'/>" checked="checked"><s:property value='factSname'/></div>					    
					  </s:iterator>					 
					 </div>
					 </s:else> 					 
					</td>  				  					
					</tr>
				
				
				<tr>
					<td class="td_show_title">硬度</td>
					<td class="td_input"><input type="text" name="tabpom.hardness"
						value="<s:property value='tabpom.hardness' />" datatype="0-8">
					</td>
					<td class="td_show_title">拉力</td>
					<td class="td_input"><input type="text" name="tabpom.forces"
						value="<s:property value='tabpom.forces' />" datatype="0-8">
					</td>
					
				</tr>
				<tr>
					<td class="td_show_title">延伸</td>
					<td class="td_input"><input type="text" name="tabpom.extends_"
						value="<s:property value='tabpom.extends_' />" datatype="0-8">
					</td>
					<td class="td_show_title">C型撕裂 <font color="red">↑</font></td>
					<td class="td_input"><input type="text" name="tabpom.tearingC"
						value="<s:property value='tabpom.tearingC' />" datatype="0-8">
					</td>
					
				</tr>
				<tr>
					<td class="td_show_title">褲型撕裂 <font color="red">↑</font></td>
					<td class="td_input"><input type="text" name="tabpom.tearingK"
						value="<s:property value='tabpom.tearingK' />" datatype="0-8" />						
					</td>
					<td class="td_show_title">比重<br/><br/>誤差範圍(±)</td>
					<td class="td_input">
					   <input type="text" name="tabpom.proportion"
						value="<s:property value='tabpom.proportion' />" datatype="0-8" /><br/>
						<input type="text" name="tabpom.proportionA" value="<s:property value='tabpom.proportionA' />"/>
						
					</td>					
				</tr>
				<tr>
					<td class="td_show_title">AKRON耐磨 <font color="green">↓</font></td>
					<td class="td_input"><input type="text" name="tabpom.wresistingAkron"
						value="<s:property value='tabpom.wresistingAkron' />" datatype="0-8" />						
					</td>
					<td class="td_show_title">DIN耐磨 <font color="green">↓</font></td>
					<td class="td_input"><input type="text" name="tabpom.wresistingDin"
						value="<s:property value='tabpom.wresistingDin' />" datatype="0-8" />
						
					</td>					
				</tr>
				<tr>
					<td class="td_show_title">止滑係數 <font color="red">↑</font></td>
					<td class="td_input"><input type="text" name="tabpom.ratioA"
						value="<s:property value='tabpom.ratioA' />" datatype="0-8" />						
					</td>
					<td class="td_show_title">耐油係數 <font color="green">↓</font></td>
					<td class="td_input"><input type="text" name="tabpom.ratioB"
						value="<s:property value='tabpom.ratioB' />" datatype="0-8" />
						
					</td>					
				</tr>
				<tr>
					<td class="td_show_title">抗彎曲</td>
					<td class="td_input"><input type="text" name="tabpom.ableBend"
						value="<s:property value='tabpom.ableBend' />" datatype="0-8" />						
					</td>
					<td class="td_show_title">抗黃變</td>
					<td class="td_input"><input type="text" name="tabpom.ableYellow"
						value="<s:property value='tabpom.ableYellow' />" datatype="*0-20" />
						
					</td>					
				</tr>
				<tr>
					<td class="td_show_title">抗高壓</td>
					<td class="td_input"><input type="text" name="tabpom.defyPress"
						value="<s:property value='tabpom.defyPress' />" datatype="*0-20" />						
					</td>
					<td class="td_show_title">抗靜電</td>
					<td class="td_input"><input type="text" name="tabpom.defyEle"
						value="<s:property value='tabpom.defyEle' />" datatype="0-8" />
						
					</td>					
				</tr>
				<tr>
					<td class="td_show_title">老化水解</td>
					<td class="td_input"><input type="text" name="tabpom.ageing"
						value="<s:property value='tabpom.ageing' />" datatype="*0-20" />						
					</td>
					<td class="td_show_title">收縮</td>
					<td class="td_input"><input type="text" name="tabpom.contract"
						value="<s:property value='tabpom.contract' />" datatype="*0-20" />
						
					</td>					
				</tr>
				<tr>
					<td class="td_show_title">彈性 <font color="red">↑</font></td>
					<td class="td_input"><input type="text" name="tabpom.elasticity"
						value="<s:property value='tabpom.elasticity' />" datatype="0-8" />						
					</td>
					<td class="td_show_title">壓縮 <font color="green">↓</font></td>
					<td class="td_input"><input type="text" name="tabpom.compression"
						value="<s:property value='tabpom.compression' />" datatype="*0-20" />
						
					</td>					
				</tr>
				<tr>
					<td class="td_show_title">分裂 <font color="red">↑</font></td>
					<td class="td_input"><input type="text" name="tabpom.division"
						value="<s:property value='tabpom.division' />" datatype="0-8" />						
					</td>
					<td class="td_show_title">認證</td>
					<td class="td_input">
					<s:if test='tabpom.authentications=="0"'>
					   是<input type="radio" name="tabpom.authentications" value="0" checked="checked"/>
					</s:if>
					<s:else>
					   是<input type="radio" name="tabpom.authentications" value="0"/>
					</s:else>
					<s:if test='tabpom.authentications=="1"'>
					   否<input type="radio" name="tabpom.authentications" value="1" checked="checked"/>
					</s:if>
					<s:else>
					   否<input type="radio" name="tabpom.authentications" value="1"/>
					</s:else>											
					</td>					
				</tr>
				<tr>
					<td class="td_show_title">特性說明</td>
					<td class="td_input" colspan="3">
					<textarea rows="10" cols="150" name="tabpom.instruction"><s:property value='tabpom.instruction' /></textarea>
					<input type="hidden" value="<s:property value='#session.loginUser.username'/>" name="tabpom.userName" />
						<s:if test="tabpom==null">
						   <input type="hidden" value="<%=str_date%>" name="tabpom.tabpomDate" id="tabpomDate"/>
						</s:if>
						<s:else>
						   <input type="hidden" value="<s:property value='tabpom.tabpomDate'/>" name="tabpom.tabpomDate" />
						</s:else>																														      				     				      																									
					</td>					
				</tr>
				  <td class="td_show_title">附檔</td>
					<td class="td_input" colspan="3">
					  <div style="width:300px" id="divfile">
				      <input type="file" name="files" style="width:150px" id="files"/><a href="javascript:addFile()">添加多個</a>
				      <input type="button" value="test" onclick="test()"/>
				      </div>				      				      
                    </td>
				      
				<tr>
				 
				</tr>
			</tbody>
		</table>
		<hr/>
		
		<s:if test="tabpom.webtabfiles.size>0">		
		<div id="webtabfiledao">	
		 <b style="color: blue">附檔:</b><br/>
		 <div id="fileJson">
		   <s:iterator value="tabpom.webtabfiles">
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
		
		<center>
			<input type="submit" id="sub" value="確定"  onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp;			 
				<input type="reset" id="reset" value="重置" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp; 			
				<input type="button" value="返回" id="btn_back"
				onclick="javascript:back()" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
		</center>
	</form>
	</div>
</body>
</html>
