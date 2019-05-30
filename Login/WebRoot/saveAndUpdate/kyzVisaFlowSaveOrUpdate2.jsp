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
<body >
   <div id="pop">
       <form action="visaflow_update" method="post" id="form">
		<table class="table table-condensed" >		    	    																 			
			<tbody >			 				 						
			   <tr>
			   <td>廠別</td>
			   <td><input type="text" value="<s:property value='flow.id.factNo'/>" readonly style="color:blue" name="flow.id.factNo" id="dwrFactNo"/></td>
			   <td>類別</td>
			   <td><input type="text" value="<s:property value='flow.id.visaSort'/>" readonly style="color:blue" name="flow.id.visaSort"/></td>
			   </tr>
			   <tr>
			   <td>姓名</td>
			   <td>
			   <input type="text" value="<s:property value='flow.id.purmanNo'/>"  name="flow.id.purmanNo" id="keys" datatype="*1-30"/>
			   <div style="position:relative">
			     <div id="tip" style="z-index:100;position:absolute;background:yellow;top:0px;left:0px;width:180px;display:none"></div>
			    </div>
			   </td>
			   <td>項次</td>
			   <td><input type="text" value="<s:property value='flow.id.itemNo'/>" readonly style="color:blue" name="flow.id.itemNo"/></td>
			   </tr>
			   <tr>
			   <td>Email地址</td>
			   <td><input type="text" value="<s:property value='flow.visaSigner'/>"  datatype="e" name="flow.visaSigner" id="skeys" onkeyup="gog()"/>
			   <div style="position:relative"  >			     
			     <div id="tip2" style="z-index:100;position:absolute;background:yellow;top:0px;left:0px;width:180px;display:none" ></div>			     
			     </div>
			   </td>
			   <td>職務</td>
			   <td>
			   <input type="text" value="<s:property value='flow.visaRank'/>"  name="flow.visaRank"/>
			   <input type="hidden" value="<s:property value='flow.flowMk'/>"  name="flow.flowMk"/>
			   <input type="hidden" value="<s:property value='flow.typeMk'/>"  name="flow.typeMk"/>
			   <input type="hidden" value="<s:property value='flow.trMk'/>"  name="flow.trMk"/>
			   <input type="hidden" value="<s:property value='flow.depId.depId'/>" name="depId"/>
			    <input type="hidden" value=<s:property value='flow.webformtype.fid'/> name="fid"/>
			   </td>			   
			   </tr>
			   <tr>
			     <td>是否可見</td>
			   <td>
			     <s:if test='flow.visible=="Y"'>
			                    是<input type="radio" value="Y" name="flow.visible" checked/>
			     </s:if>
			     <s:else>
			                   是<input type="radio" value="Y" name="flow.visible"/>
			     </s:else>
			      <s:if test='flow.visible=="N"'>
			                    否<input type="radio" value="N" name="flow.visible" checked/>
			     </s:if>
			     <s:else>
			                   否<input type="radio" value="N" name="flow.visible"/>
			     </s:else>       
			   </td>
			   </tr>			          			  			 	  			
			</tbody>							    
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
			datatype : {
				"*0-6" : /^\d{0,9}(\.[0-9]{1,3})?$/,
				"my0-8": /^\d{0,8}(\.[0-9]{1,4})?$/
			},
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					loadUrl("visaflow_findPageBean3");
					//location.href="visaflow_findPageBean";
					
				}
				if(data=="1"){
					alert(data.responseText);
				}
			}
		});
		demo.tipmsg.w["*0-6"] = "只能數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["my0-8"]="只能數字且不超過8位數,可保留四位以內小數";
	});

	function gog() {	   	 	    
		var skeys = document.getElementById("skeys").value;
		var factno=document.getElementById("dwrFactNo").value;
		userjs.findByEmailDwr2(factno,skeys,function(data) {							
							if (data != null) { //当查询结果没数据时,不写这句会报错的  
								var skeys = document.getElementById("skeys").value;
								skeys = skeys.replace(/(^\s*)|(\s*$)/g, ""); //这个是去掉空格,正则功能很强大的   findByEmailDwr
								if (skeys != "") { //如果不判断,一按下键就会有8条数据  
									document.getElementById("tip2").innerHTML = "";
									for ( var i = 0; i < data.length; i++) {
										document.getElementById("tip2").innerHTML += "<div onclick="
												+ "document.getElementById('skeys"+"').value=this.innerText;"
												+ "document.getElementById('tip2"+"').innerText='';"
												//+ "document.getElementById('keys"+"').value='"+data[i].name+"';"
												+ "this.style.display='none';"												
												
												+ " onmouseout="
												+ "this.style.backgroundColor='yellow'"
												+ " onmouseover="
												+ "this.style.backgroundColor='#3266CC'"
												+ " style='width:180px;background:yellow'>"
												+ data[i].email + "</div>";   

									}
								} else {
									document.getElementById("tip2").innerHTML = "";
								}
							} else {
								document.getElementById("tip2").innerHTML = "";
							}
						});
						document.getElementById("tip2").style.display="block";
						document.getElementById("tip2").style.width="0";
	}
	
	function back(){
		loadUrl("visaflow_findPageBean3?backIndex=1");
	}
</script>
<script type='text/javascript' src='dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='dwr/interface/kyzvisaflowjs.js'></script>
<script type='text/javascript' src='dwr/interface/userjs.js'></script>	
</body>
</html>
