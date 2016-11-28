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
</head>

<body>
	<form  id="form_pom" enctype="multipart/form-data">	
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
	                         <input type="text" name="tabpom.pomNo" value="<s:property value='formula.pom.pomNo'/>" style="color:blue" readonly/>
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
	                      <input type="text" name="tabpom.hardness" value="<s:property value='pom.hardness'/>" datatype="*"/><br/>
	                      <input type="text" name="tabpom.hardness2" value="<s:property value='pom.hardness2'/>">(±值)
	                   </td>
	                   <td>
	                      <input type="text" name="tabpom.hardnessDescription" value="<s:property value='pom.hardnessDescription'/>" datatype="*"/>
	                   </td>
	                </tr>
	                <tr>
	                   <td>拉力</td>
	                   <td><input type="text" name="tabpom.forces" value="<s:property value='pom.forces'/>"/></td>
	                   <td><input type="text" name="tabpom.forcesDescription" value="<s:property value='pom.forcesDescription'/>"/></td>
	                </tr>
	                <tr>
	                   <td>延伸</td>
	                   <td><input type="text" name="tabpom.extend" value="<s:property value='pom.extend'/>"/></td>
	                   <td><input type="text" name="tabpom.extendsDescription" value="<s:property value='pom.extendsDescription'/>"/></td>
	                </tr>
	                <tr>
	                   <td>C型撕裂</td>
	                   <td><input type="text" name="tabpom.tearingC" value="<s:property value='pom.tearingC'/>"/></td>
	                   <td><input type="text" name="tabpom.tearingCDescription" value="<s:property value='pom.tearingCDescription'/>"/></td>
	                </tr>
	                <tr>
	                   <td>比重</td>
	                   <td>
	                      <input type="text" name="tabpom.proportion" value="<s:property value='pom.proportion'/>"/><br/>
	                      <input type="text" name="tabpom.proportion2" value="<s:property value='pom.proportion2'/>">(±值)
	                   </td>
	                   <td>
	                     <input type="text" name="tabpom.proportionDescription" value="<s:property value='pom.proportionDescription'/>"/> 
	                   </td>
	                </tr>
	                <tr>
	                   <td>AKRON耐磨</td>
	                   <td><input type="text" name="tabpom.wresistingAkron" value="<s:property value='pom.wresistingAkron'/>"/></td>
	                   <td><input type="text" name="tabpom.wresistingAkronDes" value="<s:property value='pom.wresistingAkronDes'/>"/></td>
	                </tr>
	                <tr>
	                   <td>DIN耐磨</td>
	                   <td><input type="text" name="tabpom.wresistingDin" value="<s:property value='pom.wresistingDin'/>"/></td>
	                   <td><input type="text" name="tabpom.wresistingDinDes" value="<s:property value='pom.wresistingDinDes'/>"/></td>
	                </tr>
	                <tr>
	                   <td>止滑係數</td>
	                   <td><input type="text" name="tabpom.ratioA" value="<s:property value='pom.ratioA'/>"/></td>
	                   <td><input type="text" name="tabpom.ratioADes" value="<s:property value='pom.ratioADes'/>"/></td>
	                </tr>
	                <tr>
	                   <td>耐油係數</td>
	                   <td><input type="text" name="tabpom.ratioB" value="<s:property value='pom.ratioB'/>"/></td>
	                   <td><input type="text" name="tabpom.ratioBDes" value="<s:property value='pom.ratioBDes'/>"/></td>
	                </tr>
	                <tr>
	                   <td>耐彎曲</td>
	                   <td><input type="text" name="tabpom.ableBend" value="<s:property value='pom.ableBend'/>"/></td>
	                   <td><input type="text" name="tabpom.ableBendDes" value="<s:property value='pom.ableBendDes'/>"/></td>
	                </tr>
	                <tr>
	                   <td>耐黃變</td>
	                   <td><input type="text" name="tabpom.ableYellow" value="<s:property value='pom.ableYellow'/>"/></td>
	                   <td><input type="text" name="tabpom.ableYellowDes" value="<s:property value='pom.ableYellowDes'/>"/></td>
	                </tr>
	                <tr>
	                   <td>抗高壓</td>
	                   <td><input type="text" name="tabpom.defyPress" value="<s:property value='pom.defyPress'/>"/></td>
	                   <td><input type="text" name="tabpom.defyPressDes" value="<s:property value='pom.defyPressDes'/>"/></td>
	                </tr>
	                <tr>
	                   <td>抗靜電</td>
	                   <td><input type="text" name="tabpom.defyEle" value="<s:property value='pom.defyEle'/>"/></td>
	                   <td><input type="text" name="tabpom.defyEleDes" value="<s:property value='pom.defyEleDes'/>"/></td>
	                </tr>
	                <tr>
	                   <td>老化水解</td>
	                   <td><input type="text" name="tabpom.ageing" value="<s:property value='pom.ageing'/>"/></td>
	                   <td><input type="text" name="tabpom.ageingDes" value="<s:property value='pom.ageingDes'/>"/></td>
	                </tr>
	                <tr>
	                   <td>收縮</td>
	                   <td><input type="text" name="tabpom.contract" value="<s:property value='pom.contract'/>"/></td>
	                   <td><input type="text" name="tabpom.contractDes" value="<s:property value='pom.contractDes'/>"/></td>
	                </tr>
	                <tr>
	                   <td>彈性</td>
	                   <td><input type="text" name="tabpom.elasticity" value="<s:property value='pom.elasticity'/>"/></td>
	                   <td><input type="text" name="tabpom.elasticityDes" value="<s:property value='pom.elasticityDes'/>"/></td>
	                </tr>
	                <tr>
	                   <td>壓縮</td>
	                   <td><input type="text" name="tabpom.compression" value="<s:property value='pom.compression'/>"/></td>
	                   <td><input type="text" name="tabpom.compressionDes" value="<s:property value='pom.compressionDes'/>"/></td>
	                </tr>
	                <tr>
	                   <td>分裂</td>
	                   <td><input type="text" name="tabpom.division" value="<s:property value='pom.division'/>"/></td>
	                   <td><input type="text" name="tabpom.divisionDes" value="<s:property value='pom.divisionDes'/>"/></td>
	                </tr>
	                <tr>
	                   <td>300% Modulus</td>
	                   <td><input type="text" name="tabpom.modulus300" value="<s:property value='pom.modulus300'/>"/></td>
	                   <td><input type="text" name="tabpom.modulus300Des" value="<s:property value='pom.modulus300Des'/>"/></td>
	                </tr>
	                <tr>
	                   <td>吐霜</td>
	                   <td><input type="text" name="tabpom.spitCream" value="<s:property value='pom.spitCream'/>"/></td>
	                   <td><input type="text" name="tabpom.spitCreamDes" value="<s:property value='pom.spitCreamDes'/>"/></td>
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
	                           <textarea style="width:100%;height:100px" name="tabpom.instruction"><s:property value='pom.instruction' /></textarea>
					<input type="hidden" value="<s:property value='#session.loginUser.username'/>" name="tabpom.username" />
						<s:if test="tabpom==null">
						   <input type="hidden" value="<%=str_date%>" name="tabpom.tabpomDate" id="tabpomDate"/>
						</s:if>
						<s:else>
						   <input type="hidden" value="<s:property value='pom.tabpomDate'/>" name="tabpom.tabpomDate" />
						</s:else>
	                     
	                   </td>                 
	                </tr>
	                <tr>
	                   <td>附檔上傳</td>
	                   <td colspan="2">	                 
	                       <div style="width:300px" id="divfile">
				               <input type="file" name="files" style="width:150px" id="files"/><a href="javascript:addFile()">添加多個</a>
				           </div>
				      
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
	 </td>
  </tr>                               											
</table>
</div>
</div>

<center>
			<input type="submit" id="sub_pom" value="確定"  class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;			 			
			<input type="button" value="返回" onclick="javascript:back()" class="btn btn-primary"/>
				
</center>	
</form>
	
<script type="text/javascript">

	/*jq(function() {
		var demo = jq("#form_pom").Validform({
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
	
	function checkform2(){
			var demo = jq("#form_pom").Validform({
				btnSubmit : "#sub_pom",
				tiptype : 4,
				//tipSweep:true,
				showAllError : true,
				beforeSubmit:function(){return false;},
				datatype : {
					"0-8" : /^-?\d{0,8}(\.[0-9]{1,2})?$/
				},																							
			});
			demo.tipmsg.w["0-8"] = "只能數字且不超過8位數,可保留2位以內小數";	
	}
	
	
	jq(function(){
		var options={
				beforeSubmit:checkform2,  		       		       
		        //resetForm: true, 
		        url:"webtabpom_add",
		        dataType:'json' ,
		        success:function(data){
		        	if(data=="0"){
		        		layer.msg("操作成功!",3,1);
		        		//location.href="/Login/kyz_findPageBean";
		        	}else if(data=="1"){
		        		layer.msg("操作失敗!",3,3);
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
		jq("#form_pom").submit(function(){
			
			jq(this).ajaxSubmit(options);
			return false;
		});
		
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
		
		//goTrim();//禁止空格
	})
	       
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


function checkForm(){	
			var agent = navigator.userAgent.toLowerCase();
			var eles=jq("input[name='files']");
			var flag=true;
				if(agent.indexOf("msie") > 0){
					var path;
					var img=new Image();
					jq.each(eles,function(i,ele){
						path=ele.value;
						if(path!=""){//if
						img.src=path;
						var extname=path.substr(path.lastIndexOf(".")).toLowerCase()
						if(extname!=".jpg"&&extname!=".jpeg"&&extname!=".gif"&&extname!=".bmp"&&extname!=".tif"&&extname!=""){
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
						}//if
						
					})
				}else{
					jq.each(eles,function(i,ele){
						path=ele.value;
						var extname=path.substr(path.lastIndexOf(".")).toLowerCase()
						if(path!=""){//if
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
						}//if
						
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
					layer.msg("配方索引不能爲空",2,3);
					flag=false;
				}
				if(dwrWebbrank==""){
					layer.msg("請選擇品牌",2,3);
					flag=false;
				}
				return flag;
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
	            item_url="javascript:lookJson('"+file.id.webTabpom.pomNo+"',"+"'"+file.id.filename+"')";
	            item="<a href='/upload/"+file.id.webTabpom.pomNo+"/"+file.id.filename+"' target='_blank' title='點擊查看'>"+file.id.filename+            
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
