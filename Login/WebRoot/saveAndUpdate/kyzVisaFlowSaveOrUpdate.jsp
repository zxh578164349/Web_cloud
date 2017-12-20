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
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'addBackMat.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />

</head>
<body  >
   <div id="pop">
       <form action="visaflow_add" method="post" id="form">
		<table class="table table-condensed" >
		    		    																 			
			<tbody id="visaflow_body">
			 	
			 <s:if test="flows==null">
			    <tr>
			     <td><input type="checkbox" id="cboxall" onclick="checkAll()" disabled/></td>
			     <td>廠別</td>
			     <td >類別</td>			     
			     <td >項次</td>
			     <td >姓名</td>
			     <td >Email地址</td>
			     <td >職務</td>
			     <td>是否可見<div style="width:200px;visibility:hidden"></div></td><!-- 此div用於撐開單元格 -->
			 </tr>
			 <tr>
			     <td colspan="11">
			        <div style="float:left">是否分部門&nbsp;&nbsp;&nbsp;
			                          是<input type="radio" name="trMk_r" value="Y" datatype="*"  onclick="rplvalue(this.value),checkSame()"/>&nbsp;&nbsp;
			                          否<input type="radio" name="trMk_r" value="N" onclick="rplvalue(this.value),checkSame()"/>
			            <input type="hidden" name="trMk"/>
			         </div> 
			         <div id="div_dep" style="display:none"><select name="depId" datatype="*" onchange="checkSame()"></select></div>                   
			     </td>
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
							onchange="getAddBtn(),checkSame(),getValue('dwrFactNo','dwrFactNo2'),checkWebtype()">
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
			     <select  id="dwr_kytype" onchange="getAddBtn(),checkSame(),getValue('dwr_kytype','dwr_kytype2')" datatype="*" >
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
			     <input type="text" name="flows[0].visaSigner" value=""  datatype="e" id="skeys0" onkeyup="getEmail(0);" onblur="getLow(this),checkSame()" />
			     <div style="position:relative"  >			     
			     <div id="emaildwr0" style="z-index:100;position:absolute;background:yellow;top:0px;left:0px;width:180px;display:none" ></div>			     
			     </div>
			     </td>
			     <td ><input type="text" name="flows[0].visaRank" value="" datatype="*"/>
			       <input type="hidden" name="isnull" value="yes"/><!-- 變量isnull -->
			     </td>	
			     <td>
			                   是<input type="radio" name="flows[0].visible" value="Y" checked/>
			                   否<input type="radio" name="flows[0].visible" value="N"/>
			     </td>	     			     		      		      
			  </tr>				  	
			 </s:if>		
			         			  			 	  			
			</tbody>
			<tfoot>
			<tr>
			<td colspan="11">
			  <s:if test="flows==null">
			     <input type="button" value="添加行" onclick="addRow()"  id="addbtn" class="btn btn-info" disabled style="color:grey"/>
			     <input type="button" value="刪除行" onclick="delRow()"  id="delbtn" class="btn btn-info"/>			    
			  </s:if>			    			    		    
			</td>
			</tr>
			</tfoot>			
		    
		</table >
			<center>			    
				<input type="submit" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; 				
			    <input type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary"/>
			</center>
							
	</form>
	</div>
	
<script type="text/javascript">

	jq(function() {
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			showAllError : true,
			tipSweep : true,
			ignoreHidden:true,
			datatype : {
				"*0-6" : /^\d{0,9}(\.[0-9]{1,3})?$/,
				"my0-8": /^\d{0,8}(\.[0-9]{1,4})?$/
			},
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					//location.href="visaflow_findPageBean";
					loadUrl("visaflow_findPageBean");
				}
				if(data=="1"){
					//alert(data.responseText);
					layer.msg("提交失敗",3,3);
				}
			}
		});
		demo.tipmsg.w["*0-6"] = "只能數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["my0-8"]="只能數字且不超過8位數,可保留四位以內小數";
	});
			
var j=0;	
		
	function addRow(){
	     j=j+1;
	    	     
	     if(j>14){
	      j=14;
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
        if(cboxlist.length>14){
           alert("對不起,不能超過10條記錄!");
        }else{
         var newTr = visaflow_body.insertRow(-1);
        //添加列
        var newTd00=newTr.insertCell(0);
        var newTd0 = newTr.insertCell(1);
        var newTd1 = newTr.insertCell(2);
        var newTd2=newTr.insertCell(3);
        var newTd3=newTr.insertCell(4);
        var newTd4=newTr.insertCell(5);
        var newTd5=newTr.insertCell(6);
        var newTd6=newTr.insertCell(7);
        
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
        newTd5.innerHTML='<input type="text" name="flows['+j+'].visaRank" value="" datatype="*"/>'
        newTd6.innerHTML='是<input type="radio" name="flows['+j+'].visible" value="Y" checked/>&nbsp;&nbsp;'+
        '否<input type="radio" name="flows['+j+'].visible" value="N"/>'
        ; 
        
        }
        
        var cboxlist=document.getElementsByName("cbox");
        if(cboxlist.length>1){
           document.getElementById("dwrFactNo").disabled=true;
           document.getElementById("dwr_kytype").disabled=true;
           jq("input[name='trMk_r']").attr("disabled","disabled");
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
	   if(cboxlist.length>1){
	      visaflow_body.deleteRow(cboxlist.length+1);	 //因為   visaflow_body的行數比  cboxlist的數量要多，所以cboxlist要加   +1     
	   }
	   //刪除最後一行
	   if(cboxlist.length==1){
	      document.getElementById("dwrFactNo").disabled=false;
	      document.getElementById("dwr_kytype").disabled=false;	
	      jq("input[name='trMk_r']").removeAttr("disabled");
	   }
	  	   	  	   
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
	
	function getAddBtn(){
	    var factno=document.getElementById("dwrFactNo").value;
        var typeno=document.getElementById("dwr_kytype").value;
        var trMk=jq("input[name='trMk']").val();
        if(factno!=""&&typeno!=""&&trMk!=""&&trMk!=null){
          document.getElementById("addbtn").disabled="";
          document.getElementById("addbtn").style.color="white";
        }else{
          document.getElementById("addbtn").disabled="disabled";
          document.getElementById("addbtn").style.color="grey";
        }
	}
	      
	function rplvalue(vlu){
		jq("input[name='trMk']").val(vlu);
	}
		
	function checkdepments(){		
		var factno=jq("#dwrFactNo").val();
		var trMk=jq("input[name='trMk']").val();
		var result=0;
		if(trMk=="Y"&&factno!=""){
			jq.ajax({
				type:"post",
				dateType:"json",
				data:{factNo:factno},
				url:"webdep_findWebDepartmentByFactNo",
				success:function(data){
					alert(data.length);
					jq("select[name='depId']").empty();
					var item="";
					if(data.length>0){
						item+="<option value=''>請選擇部門</option>";
						jq.each(data,function(i,obj){
							item+="<option value='"+obj.depId+"'>"+obj.depName+"</option>"						
						})
						jq("select[name='depId']").append(item);
						jq("#div_dep").show();
						result=data.length;										
					}else{
						jq("#div_dep").hide();						
					}				
					
				}
			})
		}else{
			jq("#div_dep").hide();
		}		
		return result;
	}
	
     function checkSame(){
    	 alert(result);
       var factno=document.getElementById("dwrFactNo").value;
       var visasort=document.getElementById("dwr_kytype").value.split("__")[0]; 
       var visasort2=document.getElementById("dwr_kytype").value.split("__")[1]; 
       var visaSigner=document.getElementById("skeys0").value;
       var visasort_obj=document.getElementById("dwr_kytype");
       var visasort_index=visasort_obj.selectedIndex;
       var visasort_text=visasort_obj.options[visasort_index].text;
       var trMk=jq("input[name='trMk']").val();       
       if(factno!=""&&visasort!=""&&trMk!=""&&trMk!=null){
    	   if(visasort2=="0"){//【其它類】
    		   if(visaSigner!=""){                                   
                   if(trMk=="Y"){
                	   /*if(result>0){
                		   alert("繼續");
                		   var depId=jq("select[name='depId']").val();
                		   alert(depId);
                		   kyzvisaflowjs.findVisaSort_dwr5(factno,visasort,depId,trMk,function(x){
                			   if(x!=null&&x.length>0){                          	
                                  	alert("該部門審核流程已存在!");                           	                                                    
                                  	lockbtn();                             
                                  }else{                             
                                  	unlockbtn();
                                  }
                		   })
                	   }else{
                		   kyzvisaflowjs.findVisaSort_dwr4(factno,visasort,visaSigner,trMk,function(x){
                           if(x!=null&&x.length>0){                          	
                           	alert("該Email("+visaSigner+")的審核流程已存在!");                           	                                                    
                           	lockbtn();                             
                           }else{                             
                           	unlockbtn();
                           }
                      });                		   
                	   } */              	   
                      //checkdepments(factno,visasort,visaSigner,trMk);
                   }else{
                	   jq("#div_dep").hide();
                	   visasort=visasort+"_AA";
                	   kyzvisaflowjs.findNums(factno,visasort,function(x){
            			   if(x!=0){
            				   alert("該廠的審核流程(不分部門)已存在!");                               
                               lockbtn();
            			   }else{           				   
            				   unlockbtn();
            			   }
            		   });
                   }
                 }  
    	   }else{//【出差類】【配方類】
    		   if(visaSigner!=""){
    			   if(visasort2=="TR"){
    				   var result=checkdepments();
    				   if(result>0){
                		   alert("繼續");               		   
                		   var depId=jq("select[name='depId']").val();
                		   alert(depId);
                		   kyzvisaflowjs.findVisaSort_dwr5(factno,visasort,depId,trMk,function(x){
                			   if(x!=null&&x.length>0){                          	
                                  	alert("該部門審核流程已存在!");                           	                                                    
                                  	lockbtn();                             
                                  }else{                             
                                  	unlockbtn();
                                  }
                		   })
                	   }else{
                		   alert("該廠還沒有建立部門資料,無法創建流程");
                		   lockbtn();
                		   /*kyzvisaflowjs.findVisaSort_dwr4(factno,visasort,visaSigner,trMk,function(x){
                           if(x!=null&&x.length>0){                          	
                           	alert("該Email("+visaSigner+")的審核流程已存在!");                           	                                                    
                           	lockbtn();                             
                           }else{                             
                           	unlockbtn();
                           }
                           });*/                		   
                	   }
    			   }
    			   
    			   kyzvisaflowjs.findNums(factno,visasort,function(x){
        			   if(x!=0){
        				   alert("該廠的審核流程已存在!");                         
                           lockbtn();
        			   }else{        				  
        				   unlockbtn();
        			   }
        		   });
    		   }    		   
    	   }                   
       } 
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
												+ "document.getElementById('skeys"+index+"').focus();"
												+ "document.getElementById('emaildwr"+index+"').innerText='';"	
												+ "this.style.display='none';"																																			
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
	
 function getValue(str1,str2){
    document.getElementById(str2).value=document.getElementById(str1).value;
 }

/**申請人的Email自動轉化爲小寫***/
function getLow(obj){
  obj.value=obj.value.toLowerCase();
}


function back(){
	loadUrl("visaflow_findPageBean3?backIndex=1");
}

function checkWebtype(){
	jq("#dwr_kytype").empty();
	var factno=jq("#dwrFactNo");
	if(factno!=null){
		jq.ajax({
			type:"post",
			dataType:"json",
			url:"webtype_findTypes",
			data:{factNo:factno.val()},
			success:function(data){
				var item="";
				if(data.length>0){
					item+="<option value=''>請選擇類別</option>";
					jq.each(data,function(i,obj){
						item+="<option value='"+obj[0]+"__"+obj[2]+"'>"+obj[1]+"</option>";
					});
				}else{
					item+="<option value=''>無數據</option>";
				}				
				jq("#dwr_kytype").append(item);
			}
		});
	}
	
}

function lockbtn(){
	document.getElementById("error1").innerHTML='<font color="red">！</font>';
    document.getElementById("error2").innerHTML='<font color="red">！</font>';
    document.getElementById("sub").disabled=true;
    document.getElementById("addbtn").disabled=true;
    document.getElementById("sub").value="已鎖定";
    document.getElementById("sub").style.color="red";
    document.getElementById("addbtn").style.color="red";
}
function unlockbtn(){
	 document.getElementById("error1").innerHTML='';
     document.getElementById("error2").innerHTML='';
     document.getElementById("sub").disabled=false;
     document.getElementById("addbtn").disabled=false;
     document.getElementById("sub").value="確定";
     document.getElementById("sub").style.color="white";
     document.getElementById("addbtn").style.color="white";
}

//var result=0;
jq(function(){
	checkWebtype();
	//result=checkdepments();	
});
</script>

<script type='text/javascript' src='dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='dwr/interface/kyzvisaflowjs.js'></script>
<script type='text/javascript' src='dwr/interface/userjs.js'></script>
</body>
</html>
