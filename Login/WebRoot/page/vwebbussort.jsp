<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'ypre_null.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">
<link rel="stylesheet" type="text/css" href="css/form.css" />

</head>

<body>
<br/>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">不分形態</h3>
  </div>
  <div class="panel-body">
      <form action="vwebbussortnew_print" method="post" id="subform" target="_blank">
		<table id="tb_search">
			<tr>
				<td>
				<span>開始
				 <input type="text" id="yymm" name="yymm" onClick="WdatePicker({minDate:'{%y-1}-%m',maxDate:'#F{$dp.$D(\'yymm2\',{M:-1})||\'%y-{%M-1}\'}'})"
					readonly="readonly" class="Wdate" datatype="*" />
				</span>
				<span>結束
					<input type="text" id="yymm2" name="yymm2" onClick="WdatePicker({minDate:'#F{$dp.$D(\'yymm\',{M:0})}',maxDate:'%y-%M'})"
					readonly="readonly" class="Wdate" datatype="*"  />			 
				</span>
				 <input type="submit" value="下載"  class="btn btn-primary"/>&nbsp;&nbsp;
				 <!--  <input type="button" value="返回" onclick="javascript:location.href='webestpro_findPageBean'" class="btn btn-primary"/>-->	
				</td>			    
				
			</tr>
		</table>					
	</form>
  </div>
</div>
<br/><br/><br/>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">分形態</h3>
  </div>
  <div class="panel-body">
        <form action="vwebbussortnew_print_fcode" method="post" id="subform2" target="_blank">
		<table id="tb_search">
			<tr>
				<td>
				<span>開始
				 <input type="text" id="begin" name="yymm" onClick="WdatePicker({minDate:'{%y-1}-%m',maxDate:'#F{$dp.$D(\'end\',{M:-1})||\'%y-{%M-1}\'}'})"
					readonly="readonly" class="Wdate" datatype="*" />
				</span>
				<span>結束
					<input type="text" id="end" name="yymm2" onClick="WdatePicker({minDate:'#F{$dp.$D(\'begin\',{M:0})}',maxDate:'%y-%M'})"
					readonly="readonly" class="Wdate" datatype="*"  />			 
				</span>
				 <input type="submit" value="下載"  class="btn btn-primary"/>&nbsp;&nbsp;					
				</td>			    
				
			</tr>
		</table>
		<table>
        <tr><td><input type="checkbox" onclick="selectAll(this)" style="width:18px;height:18px" />全選<hr></td></tr>
          <s:iterator value="#attr.map" id="map" status="x">
          <tr>
           <td>
                 <input type="checkbox" value="<s:property value='key'/>" id="<s:property value='key'/>" name="list_factcode" 
                 onclick="checkAll('${map.key}')" style="width:18px;height:18px" datatype="*"/>
                 <font style="font-size:14px;font-weight:bold" ><s:property value='key'/> </font>
                 <br> 
                 <div id="<s:property value='key'/>_div" >               
                 <s:iterator value="value" status="y">
                    <input type="checkbox" value="${map.key}_<s:property value='id.factNo'/>_<s:property value='factSname'/>" id="<s:property value='key'/>_factno" class="<s:property value='key'/>_factno"
                    name="list_factno" onclick="clickOne(this,'font_${map.key}_${y.index}','${map.key}')"/>
                    <font id="font_<s:property value='key'/>_<s:property value='#attr.y.index'/>">
                    <s:property value="factSname"/>(<s:property value='id.factNo'/>)</font>
                    &nbsp;
                 </s:iterator>
                 </div>                  
           </td>
         </tr>
      </s:iterator>
    </table>					
	</form>
  </div>
</div>


<script type="text/javascript">
function tips(){
	   window.parent.layer.alert("出現無數據時,請檢查<br/>【產量資料(盤點)】【基本數據導入】是否同時具備數據",0); 
}
jq(function(){
    jq("#subform").Validform({
			tiptype : 3,
			showAllError : true,
			ignoreHidden : true,
			tipSweep : true,
			beforeSubmit:tips
    });
    jq("#subform2").Validform({
		tiptype : 1,
		showAllError : true,
		ignoreHidden : true,
		tipSweep : true,
		beforeSubmit:tips
});   
})

function print(){
	if(jq("#yymm").val()==""||jq("#yymm2").val()==""){
		layer.msg("請選擇日期",3,3);
	}else{
		
	}
}



	/**
    全選與反選
   */  	
   function checkAll(factcode) {
   	//通過樣式 class獲取對應factCode的所有廠別
   	var allcheckboxs=jq("."+factcode+"_factno");
   	var allfactno = jq("#"+factcode);	
   		//遍歷所選廠別狀態對應的所有checkbox
   		for ( var i = 0; i < allcheckboxs.length; i++) {//for
   			var checkbox = allcheckboxs[i];//dom對象
   			//var checkbox2=allcheckboxs.eq(i);//jquery對象			
   			var font = jq("#font_" +factcode+"_"+ i);
   			if (checkbox.checked === false&&allfactno.prop("checked")===true) {					
   				// 正选  
   				checkbox.checked=true;
   				font.css("color","red");				
   			}
   			if (checkbox.checked === true&&allfactno.prop("checked")===false) {
   				// 反选  
   				checkbox.checked=false;
   				font.css("color","") ;				
   			}						
   		}//for
   		/*if(allfactno.prop("checked")){
   			   jq("#"+factcode+"_div").show(300);
   			}else{
   			  jq("#"+factcode+"_div").hide(300); 
   			}*/
   	}
   	
   	/**
   全選全部的廠別狀態
   */
   function selectAll(obj){
      var allfactcodes=jq("input[name='list_factcode']");
      if(obj.checked===true){
        for(var i=0;i<allfactcodes.length;i++){  
        allfactcodes[i].checked=true;  
        checkAll(allfactcodes[i].value);
      }  
      }else{
         for(var i=0;i<allfactcodes.length;i++){  
         allfactcodes[i].checked=false;  
         checkAll(allfactcodes[i].value);
      }
      }    
   }  
    
    
	
/**
選擇單個,如果一個也選中,則全部退回
*/	
	function clickOne(checkbox_obj,font,factcode){	   
	   var cb_factcode=document.getElementById(factcode);  
	   if(checkbox_obj.checked==true){
	      document.getElementById(font).style.color="red";
	   }else{
	       document.getElementById(font).style.color="";
	       //全部退回
	       //通過樣式 class獲取對應factCode的所有廠別
	       allcheckboxs=jq("."+factcode+"_factno");
	       var index=0;
	       for(var i=0;i<allcheckboxs.length;i++){
	           var checkbox=allcheckboxs[i];
	           if(checkbox.checked==false){
	               index++;	              
	           }
	       }
	       if(index==allcheckboxs.length){
	           //jq("#"+factcode+"_div").hide(300);
	           cb_factcode.checked=false;
	       }
	   } 	  
	}
</script>

</body>
</html>
