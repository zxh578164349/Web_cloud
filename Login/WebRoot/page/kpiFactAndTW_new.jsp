<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'kpiFactAndTW.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,Chrome=1">
	<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">		
<link rel="stylesheet" type="text/css" href="css/form.css" />

<script type="text/javascript">
    jq(
      function(){    	 
         jq("#subform1").Validform({
             btnSubmit : "#btn1",
             tiptype:3,
             tipSweep:true,
             showAllError:true,
             beforeCheck:function(){
            	 window.parent.layer.alert("出現無數據時,請檢查<br/>【產量資料(盤點)】【預計生產】【基本數據導入】是否同時具備數據",0); 
             }
         });
         jq("#subform2").Validform({
             btnSubmit:"#btn2",
             tiptype:3,
             tipSweep:true,
             showAllError:true,
             beforeCheck:function(){
            	 window.parent.layer.alert("出現無數據時,請檢查<br/>【產量資料(盤點)】【預計生產】【基本數據導入】是否同時具備數據",0);           	 
             }
         });
         jq("#subform3").Validform({
             btnSubmit:"#btn3",
             tiptype:3,
             tipSweep:true,
             showAllError:true,
             beforeCheck:function(){
            	 window.parent.layer.alert("出現無數據時,請檢查<br/>【產量資料(盤點)】【預計生產】【基本數據導入】是否同時具備數據",0); 
             }
         })
      }  
   )

    
    
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
   		if(allfactno.prop("checked")){
   			   jq("#"+factcode+"_div").show(300);
   			}else{
   			  jq("#"+factcode+"_div").hide(300); 
   			}
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
	           jq("#"+factcode+"_div").hide(300);
	           cb_factcode.checked=false;
	       }
	   } 	  
	}
	
function checkdate(){
     var factno=document.getElementById("factNo").value;
     var year=document.getElementById("year").value;
     document.getElementById("dwr_yymm").length=0;
     if(factno!=""&&year!=""){
        kpifactjs.findDateByFactNo(factno,year,function(x){           
            dwr.util.addOptions("dwr_yymm", x);
        });
     }
}

function tips(){
	layer.msg("出現無數據時,請檢查<br/>【產量資料(盤點)】【預計生產】【基本數據導入】是否同時具備數據",3,1);
}
</script>
<script type='text/javascript' src='/Login/dwr/interface/kpifactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>

  </head>
  
  <body>
  <h2>KPI-工廠</h2>
    <form action="vkpifactnew_print_fact" method="post" id='subform1' target="_blank">
       <table id="table_fact" class="tb_search"> 
          <h4>全年報表</h4>        
          <tr>
          <td class="td_input">
          <span><input type="text" name="year" onclick="WdatePicker({dateFmt:'yyyy'})" datatype="*" id="year" onchange="checkdate()" class="Wdate"/></span>         
          
             <span>
             <s:if test="#session.factNo=='tw'">			    
					<select name="factNo" id="factNo" datatype="*" onchange="checkdate()">
						<option value="">請選擇工廠</option>						
						<s:iterator value="#session.facts" id="temp">
							<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>								
						</s:iterator>
					</select>
					
				</s:if> 
				<s:else>				  
					<select name="factNo" id="factNo" datatype="*" onchange="checkdate()">
					    <option value="">請選擇工廠</option>
						<option value="<s:property value="#session.factNo"/>">
							<s:property value="#session.factName" />(<s:property value="#session.factNo"/>)
						</option>
					</select>					
				</s:else>
				</span>

                                    
           
            <span>目標日期<select name="yymm" id="dwr_yymm">                 
            </select>
            </span>
  
          <input type="button" id="btn1" class="btn btn-primary" value="確定"/>
          </td>
          </tr>
       </table>
    </form>
    <hr>
    <form action="vkpifactnew_print_month" method="post" id="subform2" target="_blank">
      <table class="tb_search">
        <h4>月份報表</h4>
         <tr>
           <td>
               <span><input type="text" id="begin" name="yymm_begin" datatype="*" onclick="WdatePicker({minDate:'{%y-1}-%m',maxDate:'#F{$dp.$D(\'end\',{M:-1})||\'%y-{%M-1}\'}'})" class="Wdate" ></span>至
               <span><input type="text" id="end" name="yymm_end" datatype="*" onclick="WdatePicker({minDate:'#F{$dp.$D(\'begin\',{M:0})}',maxDate:'%y-%M'})" class="Wdate"></span>
             <span>
             <s:if test="#session.factNo=='tw'">			    
					<select name="factNo"  datatype="*">
						<option value="">請選擇工廠</option>						
						<s:iterator value="#session.facts" id="temp">
							<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>								
						</s:iterator>
					</select>					
				</s:if> 
				<s:else>				  
					<select name="factNo"  datatype="*">
						<option value="<s:property value="#session.factNo"/>">
							<s:property value="#session.factName" />(<s:property value="#session.factNo"/>)
						</option>
					</select>					
				</s:else>
				</span>
          
          <input type="button" id="btn2" class="btn btn-primary" value="確定"/></td>
          </tr>
      </table>
    </form>
    <hr >
    <s:if test='#session.factNo=="tw"'>
    <hr>
    <h2>KPI-台灣</h2>
    <form action="vkpifactnew_print_tw" method="post" id="subform3" target="_blank">
        <table class="tb_search">
          <tr>
          <td><input type="text" name="yymm" datatype="*" onclick="WdatePicker()" class="Wdate"></td><td><input type="button" class="btn btn-primary" id="btn3" value="確定"/></td>
          </tr>
        </table>
         <hr>
        <table>
        <tr><td><input type="checkbox" onclick="selectAll(this)" style="width:18px;height:18px"/>全選<hr></td></tr>
          <s:iterator value="#attr.map" id="map" status="x">
          <tr>
           <td>
                 <input type="checkbox" value="<s:property value='key'/>" id="<s:property value='key'/>" name="list_factcode" 
                 onclick="checkAll('${map.key}')" style="width:18px;height:18px"/>
                 <font style="font-size:14px;font-weight:bold" ><s:property value='key'/> </font>
                 <br> 
                 <div id="<s:property value='key'/>_div" style="display:none">               
                 <s:iterator value="value" status="y">
                    <input type="checkbox" value="${map.key}_<s:property value='id.factNo'/>" id="<s:property value='key'/>_factno" class="<s:property value='key'/>_factno"
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
    </s:if>
    
    <hr> 
    <div >
    <table style="border:1px solid">
       <tr>
       <td><img src="images/lamp.jpg"/></td>
       <td>
       <font style="font-size:14px;color:blue">
                                    當KPI報表出現無數據時請檢查:<br/>
                                    以下三項是否同時具備數據：<br/>
                                   【 產量資料(盤點)】  <br/> 
                                   【預計生產】 <br/>   
                                   【基本數據導入】<br/>                                                                                                                                                  
       </font>                             
       </td>
       </tr>
    </table>                                                                                                                                                
    </div>   
  </body>
</html>
