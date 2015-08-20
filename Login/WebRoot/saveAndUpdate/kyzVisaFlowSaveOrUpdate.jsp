<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<title>My JSP 'addBackMat.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/validate.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/form.css" />
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<script type="text/javascript" src="jquery/DatePicker/my_WdatePicker.js"></script>
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
<script type="text/javascript">
	$(function() {
		var jj = jQuery.noConflict();
		var demo = jj("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			showAllError : true,
			tipSweep : true,
			datatype : {
				"*0-6" : /^\d{0,9}(\.[0-9]{1,3})?$/,
				"my0-8": /^\d{0,8}(\.[0-9]{1,4})?$/
			}
		});
		demo.tipmsg.w["*0-6"] = "只能數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["my0-8"]="只能數字且不超過8位數,可保留四位以內小數";
	});

	function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo(mid, function(x) {
			dwr.util.addOptions("dwrFactArea", x);
		});
		
	}
	function getAllFact(){
	   webfactjs.findAllFact2(function(x){
	       dwr.util.addOptions("tempfact",x,"factCode","factSname");
	   });
	}	
var j=0;	
		
	function addRow(){
	     j=j+1;
	     /*刚开始时，审核人员与知会人员两个单选按钮是不可用的
	                当第一次添加行时，才可以使用
	                选择了知会后，就不可以返回选择审核  
	                 把当前j的值赋给单选按钮的value ,用于后台进行判断 ，从哪位开始是知会的              
	     **/
	     /* alert(j);
	     	    	     
	     if(j>0){
	        document.getElementById("per1").disabled=false;
	        document.getElementById("per2").disabled=false;
	        if(document.getElementById("per2").checked==true){
	           document.getElementById("per2").value=j;
	           document.getElementById("per1").disabled=true;	          
	      }
	     } */
	     
	     if(j>9){
	      j=9;
	     }
	    //添加一行
	    /*insertRow()是IE專用的,通用寫法是insertRow(-1)
	      　　出於兼容性問題,插入單元格最好加上序號insertCell(序號)
	    */
       
        if(factno!=""&&typeno!=""){
          document.getElementById("addbtn").disabled=false;
          
        }else{
          document.getElementById("addbtn").disabled=true;
         
        }
        
   
        //设置列内容和属性
        var cboxlist=document.getElementsByName("cbox");
        if(cboxlist.length>9){
           alert("對不起,不能超過10條記錄!");
        }else{
         var newTr = kyzs_body.insertRow(-1);
        //添加列
        var newTd00=newTr.insertCell(0);
        var newTd0 = newTr.insertCell(1);
        var newTd1 = newTr.insertCell(2);
        var newTd2=newTr.insertCell(3);
        var newTd3=newTr.insertCell(4);
        var newTd4=newTr.insertCell(5);
        var newTd5=newTr.insertCell(6);
        
        var factno=document.getElementById("dwrFactNo").value;
        var typeno=document.getElementById("dwr_kytype").value;
        var factno2=document.getElementById("dwrFactNo").options[document.getElementById("dwrFactNo").options.selectedIndex].innerHTML;//獲得下拉框顯示的值
        var typeno2=document.getElementById("dwr_kytype").options[document.getElementById("dwr_kytype").options.selectedIndex].innerHTML;
        
        
        newTd00.innerHTML='<input type="checkbox" name="cbox" disabled/>';
        //newTd0.innerHTML = '<input type="text" name="flows['+j+'].id.factNo" value="" size="15"/>'; 
        /* newTd0.innerHTML='<select name="flows['+j+'].id.factNo" id="fix_factno'+j+'" datatype="*"><option>請選擇</option</select>';
        webfactjs.findAllFact2(function(x){
	       dwr.util.addOptions("fix_factno"+j,x,"factCode","factSname");
	     }); */	   
	    newTd0.innerHTML = '<input type="hidden" name="flows['+j+'].id.factNo" value="'+factno+'"  readonly style="color:blue"/>'+
	    '<input type="text" name="" value="'+factno2+'"  readonly/>'; 
	    newTd1.innerHTML='<input type="hidden" name="flows['+j+'].id.visaSort" value="'+typeno+'" datatype="*"  readonly/>'+
	    '<input type="text" name="" value="'+typeno2+'" datatype="*"  readonly />';
        if((j+1)<10){
          newTd2.innerHTML= '<input type="text" name="flows['+j+'].id.itemNo" value="0'+(j+1)+'" datatype="*" style="color:blue"  readonly/>';
        }else{
          newTd2.innerHTML= '<input type="text" name="flows['+j+'].id.itemNo" value="'+(j+1)+'"  datatype="*" style="color:blue"  readonly/>';
        }     
        newTd3.innerHTML='<input type="text" name="flows['+j+'].id.purmanNo" value="" datatype="*" id="keys'+j+'" onkeyup="gog('+j+')" />'+
        '<div style="position:relative"><div id="tishi'+j+'" style="z-index:100;position:absolute;background:yellow;left:0;top:0px;width:180px;display:none"></div></div>';     
        newTd4.innerHTML='<input type="text" name="flows['+j+'].visaSigner" value="" datatype="e" id="skeys'+j+'" onkeyup="getEmail('+j+')" />'+
        '<div style="position:relative"><div id="emaildwr'+j+'" style="z-index:100;position:absolute;background:yellow;left:0;top:0px;width:180px;display:none"></div></div>'
        newTd5.innerHTML='<input type="text" name="flows['+j+'].visaRank" value="" datatype="*"/>';             
        }
        
        var cboxlist=document.getElementsByName("cbox");
        if(cboxlist.length>1){
           document.getElementById("dwrFactNo").disabled=true;
           document.getElementById("dwr_kytype").disabled=true;
        } 
              
        
	}
	

				
	function delRow(){
	
	//控制好項次的順序
	  j=j-1;
	  if(j<0){
	   j=0;
	  }
	  	   
	   var cboxlist=document.getElementsByName("cbox");
	   //刪除選中行
	 /* for(var k=0;k<cboxlist.length;k++){
	     if(cboxlist[k].checked==true&&k>0){
	        kyzs_body.deleteRow(k+1);
	        k=k-1;
	     }	    
	   } */ 
	   	   
	   if(cboxlist.length>1){
	      kyzs_body.deleteRow(cboxlist.length);
	      
	      /* if(document.getElementById("per2").ckecked=true){
	         document.getElementById("per2").value=cboxlist.length-1;
	      }	 */     
	   }
	   //刪除最後一行
	   if(cboxlist.length==1){
	      document.getElementById("dwrFactNo").disabled=false;
	      document.getElementById("dwr_kytype").disabled=false;
	      
	     /*  document.getElementById("per1").disabled=false;
	      document.getElementById("per1").ckecked=true;
	      document.getElementById("per2").disabled=false; */
	      
	   }
	  
	   /* alert(j); */
	   
	  	   
	}
	function checkAll(){
	  var allbox=document.getElementById("cboxall");
	  var cboxlist=document.getElementsByName("cbox");
	  if(allbox.checked==true){
	       for(var kk=0;kk<cboxlist.length;kk++){
	           cboxlist[kk].checked=true;
	       }
	  }else{
	         for(var kk=0;kk<cboxlist.length;kk++){
	           cboxlist[kk].checked=false;
	       }
	  }
	}
	function getKyType(){
	   kytypejs.findByTypeNo("VV",function(x){
	         dwr.util.addOptions("dwr_kytype",x,"typeName","typeSname");
	   });
	}
	
	function getAddBtn(){
	    var factno=document.getElementById("dwrFactNo").value;
        var typeno=document.getElementById("dwr_kytype").value;
        if(factno!=""&&typeno!=""){
          document.getElementById("addbtn").disabled="";
          document.getElementById("addbtn").style.color="white";
        }else{
          document.getElementById("addbtn").disabled="disabled";
          document.getElementById("addbtn").style.color="grey";
        }
	}
	 function Trim(str)
         { 
             return str.replace(/(^\s*)|(\s*$)/g, ""); 
     }
     
     function checkSame(){
       /* var factno=document.getElementById("dwrFactNo").value;
       var visasort=document.getElementById("dwr_kytype").value;
       
       var visasort_obj=document.getElementById("dwr_kytype");
       var visasort_index=visasort_obj.selectedIndex;
       var visasort_text=visasort_obj.options[visasort_index].text;
       if(factno!=""&&visasort!=""){
          kyzvisaflowjs.findByType_Dwr(factno,visasort,function(x){
               if(x>0){
                 alert("廠別為("+factno+")類別為("+visasort_text+")的審核流程已存在!");
                 document.getElementById("error1").innerHTML='<font color="red">！</font>';
                 document.getElementById("error2").innerHTML='<font color="red">！</font>';
                 document.getElementById("sub").disabled=true;
                 document.getElementById("addbtn").disabled=true;
                 document.getElementById("sub").value="已鎖定";
                 document.getElementById("sub").style.color="red";
                 document.getElementById("addbtn").style.color="red";
               }else{
                 document.getElementById("error1").innerHTML='';
                 document.getElementById("error2").innerHTML='';
                 document.getElementById("sub").disabled=false;
                 document.getElementById("addbtn").disabled=false;
                 document.getElementById("sub").value="確定";
                 document.getElementById("sub").style.color="white";
                 document.getElementById("addbtn").style.color="white";
               }
          });
       } */
     }
     

	function gog(index) {	   	 	    
		var keys = document.getElementById("keys"+index).value;
		var factno=document.getElementById("dwrFactNo").value;
		userjs.findByUserNameDwr(factno,keys,function(data) {							
							if (data != null) { //当查询结果没数据时,不写这句会报错的  
								var keys = document.getElementById("keys"+index).value;
								keys = keys.replace(/(^\s*)|(\s*$)/g, ""); //这个是去掉空格,正则功能很强大的   findByEmailDwr
								if (keys != "") { //如果不判断,一按下键就会有8条数据  
									document.getElementById("tishi"+index).innerHTML = "";
									for ( var i = 0; i < data.length; i++) {
										document.getElementById("tishi"+index).innerHTML += "<div onclick="
												+ "document.getElementById('keys"+index+"').value=this.innerText;"
												+ "document.getElementById('skeys"+index+"').value='"+ data[i].email+"';"												
												+ "document.getElementById('tishi"+index+"').innerText='';"
												+ "this.style.display='none';"												
												
												+ " onmouseout="
												+ "this.style.backgroundColor='yellow'"
												+ " onmouseover="
												+ "this.style.backgroundColor='#3266CC'"
												+ " style='width:180px;background:yellow'>"
												+ data[i].name + "</div>";   

									}
								} else {
									document.getElementById("tishi"+index).innerHTML = "";
								}
							} else {
								document.getElementById("tishi"+index).innerHTML = "";
							}
						});
						document.getElementById("tishi"+index).style.display="block";
						document.getElementById("tishi"+index).style.width="0";
	}
	
	function getEmail(index){
	  var temp=0;	 
	  var skeys = document.getElementById("skeys"+index).value;
		userjs.findByEmailDwr(skeys,function(data) {		           							
							if (data != null) { //当查询结果没数据时,不写这句会报错的  
								var skeys = document.getElementById("skeys"+index).value;
								skeys = skeys.replace(/(^\s*)|(\s*$)/g, ""); //这个是去掉空格,正则功能很强大的   findByEmailDwr   semaildwr0
								if (skeys != "") { //如果不判断,一按下键就会有8条数据  
									document.getElementById("emaildwr"+index).innerHTML = "";									
									for ( var i = 0; i < data.length; i++) {
										document.getElementById("emaildwr"+index).innerHTML += "<div onclick="
												+ "document.getElementById('skeys"+index+"').value=this.innerText;"
												+ "document.getElementById('emaildwr"+index+"').innerText='';"	
												+ "this.style.display='none'"																							
												+ " onmouseout="												
												+ "this.style.backgroundColor='yellow'"																																														
												+ " onmouseover="												
												+ "this.style.backgroundColor='#3266CC'"
												+ " style='width:180px;background:yellow'>"
												+ data[i].email + "</div>";	
												temp=temp+20;											
									}																										
								} else {
									document.getElementById("emaildwr"+index).innerHTML = "";
								}
							} else {
								document.getElementById("emaildwr"+index).innerHTML = "";
							}
						});						
						document.getElementById("emaildwr"+index).style.display="block";
						document.getElementById("emaildwr"+index).style.width="0";
						
						
	}
	
	
/* 	function checkName(obj,index){
	   var factno=document.getElementById("dwrFactNo").value;
	   if(obj!=""&&factno!=""){
	      userjs.findByNameAndFactNoDwr(factno,obj,function(x){
	           if(x==null){
	              alert("該廠不存在當前用戶!");
	           }else{
	             document.getElementById("skeys"+index).value=x.email;	
	                        
	           }
	      });
	   }
	} */

 function getValue(str1,str2){
    document.getElementById(str2).value=document.getElementById(str1).value;
 }

function clickOne(){
   if(document.getElementById("per2").checked==true){
      document.getElementById("per1").disabled=true;
      document.getElementById("per2").value=j;
   }
   alert("当前为知会人员"+document.getElementById("per2").value);
}	
</script>
<script type='text/javascript' src='/Login/dwr/interface/kyzjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kytypejs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kyzvisaflowjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/userjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>

<style type="text/css">
table.gridtable {
	/* font-family: verdana,arial,sans-serif; */
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-style:solid;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	 border-width: 1px; 
	/* padding: 8px; */
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;	
	/* text-align:justify;
    text-justify:distribute-all-lines;
    -moz-text-align-last:justify; */
}
.bluecss{
   font-color:blue;
   border-color:blue;
   background-color:blue;
}
input[type="text"],select{
  width:180px;
}

</style>

</head>
<%@ include file="../saveAndUpdate/publicHead2.jsp"%>
<body onload="getKyType()" >
   <div id="pop">
       <form action="visaflow_add" method="post" id="form">
		<table width="1080" align="center" cellspacing="0" cellpadding="0"  class="gridtable" >
		    <caption style="font-size:30;font-weight:bold">審核流程<br><br></caption>		    																 			
			<tbody id="kyzs_body">
			 	
			 <s:if test="flows==null">
			    <tr>
			     <td><input type="checkbox" id="cboxall" onclick="checkAll()" disabled/></td>
			     <td>廠別</td>
			     <td >類別</td>			     
			     <td >項次</td>
			     <td >姓名</td>
			     <td >Email地址</td>
			     <td >職務</td>
			 </tr>
			 
			    <tr>
			     <td><input type="checkbox" name="cbox" disabled/></td>
			     <s:if test="#session.factNo!='tw'">
			        <td >
							<select  datatype="*" id="dwrFactNo"
							onchange="getAddBtn(),checkSame()">
							    <option value="${factNo}">${factNo}</option>
							</select>
							<input type="hidden" name="flows[0].id.factNo" value="${factNo}"/>
							<span id="error1"></span>														
					</td>			     
			  </s:if>
			  <s:else>
			     <td ><select 
							 datatype="*" id="dwrFactNo"
							onchange="getAddBtn(),checkSame(),getValue('dwrFactNo','dwrFactNo2')">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select>
						<input type="hidden" name="flows[0].id.factNo" id="dwrFactNo2"/>
						<span id="error1"></span></td>
			  </s:else>   			     
			     <td>
			     <select  id="dwr_kytype" onchange="getAddBtn(),checkSame(),getValue('dwr_kytype','dwr_kytype2')" datatype="*">
			       <option value="">請選擇</option>
			     </select>
			     <input type="hidden" name="flows[0].id.visaSort" id="dwr_kytype2"/>
			     <span id="error2"></span>
			     </td>
			     <td ><input type="text" name="flows[0].id.itemNo" value="01" datatype="*"  readonly/></td>
			     <td >			     			     
			     <input type="text" name="flows[0].id.purmanNo" value=""  datatype="*"  id="keys0" onkeyup="gog(0)" />
			     <div style="position:relative">
			     <div id="tishi0" style="z-index:100;position:absolute;background:yellow;top:0px;left:0px;width:180px;display:none"></div>
			     </div>
			     </td>			     			     
			     <td >			     
			     <input type="text" name="flows[0].visaSigner" value=""  datatype="e" id="skeys0" onkeyup="getEmail(0)"/>
			     <div style="position:relative"  >			     
			     <div id="emaildwr0" style="z-index:100;position:absolute;background:yellow;top:0px;left:0px;width:180px;display:none" ></div>			     
			     </div>
			     </td>
			     <td ><input type="text" name="flows[0].visaRank" value="" datatype="*"/>
			       <input type="hidden" name="isnull" value="yes"/><!-- 變量isnull -->
			     </td>		     			     		      		      
			  </tr>		
			 </s:if>		
			         			  			 	  			
			</tbody>
			<tfoot>
			<tr>
			<td colspan="10">
			  <s:if test="flows==null">
			     <input type="button" value="添加行" onclick="addRow()"  id="addbtn" disabled style="color:grey"/>
			     <input type="button" value="刪除行" onclick="delRow()"  id="delbtn"/>
			    <!--  <input type="radio" value="Y" name="index" id="per1" checked disabled/>审核人员&nbsp;
			     <input type="radio" value="N" id="per2" name="index" onclick="clickOne()" disabled/>知会人员 -->
			  </s:if>			    			    		    
			</td>
			</tr>
			</tfoot>			
		    
		</table >
			<center>			    
				<input type="submit" id="sub" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'" />&nbsp;&nbsp;&nbsp; 
				<input type="reset" id="reset" value="重置" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'" disabled="false" style="color:red"/>
			</center>
							
	</form>
	</div>
</body>
</html>
