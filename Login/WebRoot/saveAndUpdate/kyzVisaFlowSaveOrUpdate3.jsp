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
<title>添加知會</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />

</head>
<body>
   <div id="pop">
       <form action="visaflow_addMaxFlow" method="post" id="form">
		<table class="table table-condensed">	    																 			
			<tbody id="visaflow_body">
			 	
			 <s:if test="flows==null">
			    <tr>
			     <td><input type="checkbox" id="cboxall" onclick="checkAll()" disabled/></td>
			     <td>廠別</td>
			     <td >類別</td>			     
			     <td >項次</td>
			     <td >姓名</td>
			     <td >Email地址</td>			     
			 </tr>
			 
			    <tr>
			     <td><input type="checkbox" name="cbox" disabled/></td>
			    
			  
			     <td >			 			  
			       <input type="text" name="flows[0].id.factNo" id="dwrFactNo" value="<s:property value='flow.id.factNo'/>" readonly/>			     
			     <td>
			       <input type="text" name="flows[0].id.visaSort" id="dwr_kytype" value="<s:property value='flow.id.visaSort'/>" readonly/>			     
			     </td>
			     <td ><input type="text" name="flows[0].id.itemNo" value="<s:property value='flow.id.itemNo'/>" readonly/></td>
			     <td >			     			     
			     <input type="text" name="flows[0].id.purmanNo" value=""  datatype="*"  id="keys0" onkeyup="gog(0)" />
			     <div style="position:relative">
			     <div id="tishi0" style="z-index:100;position:absolute;background:yellow;top:0px;left:0px;width:180px;display:none"></div>
			     </div>
			     </td>			     			     
			     <td >			     
			     <input type="text" name="flows[0].visaSigner" value=""  datatype="e" id="skeys0" onkeyup="getEmail(0);"  />
			     <div style="position:relative"  >			     
			     <div id="emaildwr0" style="z-index:100;position:absolute;background:yellow;top:0px;left:0px;width:180px;display:none" ></div>			     
			     </div>
			     
			     <input type="hidden" value="<s:property value='maxItem'/>" id="maxItem"/><!-- 后台传递过来的最大序列号 -->
			     <input type="hidden" name="flows[0].typeMk" id="typeMk" value="<s:property value='flow.typeMk'/>"/>	
			     </td>			    	     			     		      		      
			  </tr>		
			 </s:if>		
			         			  			 	  			
			</tbody>
			<tfoot>
			<tr>
			<td colspan="10">
			  <s:if test="flows==null">
			     <input type="button" value="添加行" onclick="addRow()"  id="addbtn" class="btn btn-info"/>
			     <input type="button" value="刪除行" onclick="delRow()"  id="delbtn" class="btn btn-info"/>			   
			  </s:if>			    			    		    
			</td>
			</tr>
			</tfoot>			
		    
		</table >
			<center>			    
				<input type="submit" id="sub" value="確定" class="btn btn-primary" />&nbsp;&nbsp;&nbsp; 				
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
			datatype : {
				"*0-6" : /^\d{0,9}(\.[0-9]{1,3})?$/,
				"my0-8": /^\d{0,8}(\.[0-9]{1,4})?$/
			},
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					loadUrl("/Login/visaflow_findPageBean");
					//location.href="/Login/visaflow_findPageBean";
				}
				if(data=="1"){
					alert(data.responseText);
				}
			}
		});
		demo.tipmsg.w["*0-6"] = "只能數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["my0-8"]="只能數字且不超過8位數,可保留四位以內小數";
	});
		
var j=0;
var k=0;		
var k2=0;	
	function addRow(){	
	     j=j+1;
	     k=k+1;
	     k2=k+parseInt(document.getElementById("maxItem").value);	     
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
         var newTr = visaflow_body.insertRow(-1);
        //添加列
        var newTd00=newTr.insertCell(0);
        var newTd0 = newTr.insertCell(1);
        var newTd1 = newTr.insertCell(2);
        var newTd2=newTr.insertCell(3);
        var newTd3=newTr.insertCell(4);
        var newTd4=newTr.insertCell(5);
        
        var factno=document.getElementById("dwrFactNo").value;
        var typeno=document.getElementById("dwr_kytype").value;
        var typeMk=document.getElementById("typeMk").value;
                 
        newTd00.innerHTML='<input type="checkbox" name="cbox" disabled/>';          
	    newTd0.innerHTML = '<input type="text" name="flows['+j+'].id.factNo" value="'+factno+'"  readonly style="color:blue"/>';
	    newTd1.innerHTML='<input type="text" name="flows['+j+'].id.visaSort" value="'+typeno+'" datatype="*"  readonly/>';
        if(k2<10){
          newTd2.innerHTML= '<input type="text" name="flows['+j+'].id.itemNo" value="0'+k2+'" datatype="*" style="color:blue"  readonly/>';
        }else{
          newTd2.innerHTML= '<input type="text" name="flows['+j+'].id.itemNo" value="'+k2+'"  datatype="*" style="color:blue"  readonly/>';
        }     
        newTd3.innerHTML='<input type="text" name="flows['+j+'].id.purmanNo" value="" datatype="*" id="keys'+j+'" onkeyup="gog('+j+')" />'+
        '<div style="position:relative"><div id="tishi'+j+'" style="z-index:100;position:absolute;background:yellow;left:0;top:0px;width:180px;display:none"></div></div>';     
        newTd4.innerHTML='<input type="text" name="flows['+j+'].visaSigner" value="" datatype="e" id="skeys'+j+'" onkeyup="getEmail('+j+')" />'+
        '<div style="position:relative"><div id="emaildwr'+j+'" style="z-index:100;position:absolute;background:yellow;left:0;top:0px;width:180px;display:none"></div></div>'+
        '<input type="hidden" name="flows['+j+'].typeMk" value="'+typeMk+'"/>';           
        } 
                                 
	}
	

				
	function delRow(){	 
	//控制好項次的順序
	  j=j-1;
	  k=k-1;
	  if(j<0){
	     j=0;
	     k=0;
	  }	 	  	   
	   var cboxlist=document.getElementsByName("cbox");	  	   	   
	   if(cboxlist.length>1){
	      visaflow_body.deleteRow(cboxlist.length);	      	          
	   }
	   //刪除最後一行
	   if(cboxlist.length==1){
	      document.getElementById("dwrFactNo").disabled=false;
	      document.getElementById("dwr_kytype").disabled=false;	      	     	      
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
	
function back(){	
	loadUrl("/Login/visaflow_findPageBean3?backIndex=1");
}
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kyzvisaflowjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/userjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webtypejs.js'></script>	
</body>
</html>
