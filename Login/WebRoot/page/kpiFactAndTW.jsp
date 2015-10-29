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
<link rel="stylesheet" type="text/css" href="css/form.css" />	
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
    var j=jQuery.noConflict();
    j(
      function(){      
         j("#subform1").Validform({
             btnSubmit : "#btn1",
             tiptype:3,
             tipSweep:true,
             showAllError:true
         });
         j("#subform2").Validform({
             btnSubmit:"#btn2",
             tiptype:3,
             tipSweep:true,
             showAllError:true
         });
         j("#subform3").Validform({
             btnSubmit:"#btn3",
             tiptype:3,
             tipSweep:true,
             showAllError:true
         })
      }  
   )
/**
 全選與反選
*/  	
function checkAll(thisform,factcode_obj,index) {
	//選中的廠別狀態
	factcode=factcode_obj.value;
	//通過樣式 class獲取對應factCode的所有廠別
	var allcheckboxs=j("."+factcode+"_factno");
	var allfactno = document.getElementById(factcode);
	
		//遍歷所選廠別狀態對應的所有checkbox
		for ( var i = 0; i < allcheckboxs.length; i++) {
			var checkbox = allcheckboxs[i];
			var font = document.getElementById("font_" +factcode+"_"+ i);
			if (checkbox.checked === false && allfactno.checked === true) {					
				// 正选  
				checkbox.checked = true;
				font.style.color = "red";
				j("#"+factcode+"_div").show(300);

			} else if (checkbox.checked === true && allfactno.checked == false) {
				// 反选  
				checkbox.checked = false;
				font.style.color = "";
				j("#"+factcode+"_div").hide(300);
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
	       allcheckboxs=j("."+factcode+"_factno");
	       var index=0;
	       for(var i=0;i<allcheckboxs.length;i++){
	           var checkbox=allcheckboxs[i];
	           if(checkbox.checked==false){
	               index++;	              
	           }
	       }
	       if(index==allcheckboxs.length){
	           j("#"+factcode+"_div").hide(300);
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
</script>
<script type='text/javascript' src='/Login/dwr/interface/kpifactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>
<style type="text/css">
table{width:700px}
 #table_fact{width:920px}  

</style>
  </head>
  
  <body>
    <form action="vkpifact_print_fact" method="post" id='subform1' target="_blank">
       <table id="table_fact">
          <caption>KPI-工廠</caption>
          <tr>
          <td>(全年)</td>
          <td>年份</td>
          <td class="td_input">
          <input type="text" name="year" onclick="WdatePicker({dateFmt:'yyyy'})" datatype="*" id="year" onchange="checkdate()"/>         
          </td>
          <td>工廠</td>
          <td class="td_input">
             <s:if test="#attr.factNo=='tw'">			    
					<select name="factNo" id="factNo" datatype="*" onchange="checkdate()">
						<option value="">請選擇</option>						
						<s:iterator value="#attr.facts" id="temp">
							<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>								
						</s:iterator>
					</select>
					
				</s:if> 
				<s:else>				  
					<select name="factNo" id="factNo" datatype="*" onchange="checkdate()">
					    <option value="">請選擇</option>
						<option value="<s:property value="#attr.factNo"/>">
							<s:property value="#attr.factName" />(<s:property value="#attr.factNo"/>)
						</option>
					</select>
					
				</s:else>
          </td>
          <td>目標日期</td>
          <td class="td_input">           
            <select name="yymm" id="dwr_yymm">                 
            </select>
          </td>
          <td><input type="button" id="btn1" value="確定"/></td>
          </tr>
       </table>
    </form>
    <hr>
    <form action="vkpifact_print_month" method="post" id="subform2">
      <table>
         <tr>
          <td>(分段)</td>
           <td>
                                開始&nbsp;<input type="text" id="begin" name="yymm_begin" datatype="*" onclick="WdatePicker({minDate:'{%y-1}-%m',maxDate:'#F{$dp.$D(\'end\',{M:-1})||\'%y-{%M-1}\'}'})" class="Wdate" ><br>
                                結束&nbsp;<input type="text" id="end" name="yymm_end" datatype="*" onclick="WdatePicker({minDate:'#F{$dp.$D(\'begin\',{M:1})}',maxDate:'%y-%M'})" class="Wdate">
          </td>
          <td>工廠</td>
          <td class="td_input">
             <s:if test="#attr.factNo=='tw'">			    
					<select name="factNo"  datatype="*">
						<option value="">請選擇</option>						
						<s:iterator value="#attr.facts" id="temp">
							<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>								
						</s:iterator>
					</select>					
				</s:if> 
				<s:else>				  
					<select name="factNo"  datatype="*">
						<option value="<s:property value="#attr.factNo"/>">
							<s:property value="#attr.factName" />(<s:property value="#attr.factNo"/>)
						</option>
					</select>
					
				</s:else>
          </td>
          <td><input type="button" id="btn2" value="確定"/></td>
          </tr>
      </table>
    </form>
    <hr >
    <s:if test='#session.factNo=="tw"'>
    <hr>
    <form action="vkpifact_print_tw" method="post" id="subform3">
        <table>
          <caption>KPI-台灣</caption>
          <tr>
          <td>日期</td><td><input type="text" name="yymm" datatype="*" onclick="WdatePicker()" class="Wdate"></td><td><input type="button" id="btn3" value="確定"/></td>
          </tr>
        </table>
         <hr>
        <table>
          <s:iterator value="#attr.map" id="map" status="x">
          <tr>
           <td>
                 <input type="checkbox" value="<s:property value='key'/>" id="<s:property value='key'/>" name="list_factcode" 
                 onclick="checkAll(this.form,<s:property value='key'/>,${x.index})" style="width:18px;height:18px"/>
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
                                    在同一廠別,同一廠別狀態,同一月份,以下7個是否都有數據：<br/>
                                   【營收/費用資料】<br/> 
                                   【水電油資料】             <br/>
                                   【資材資料】    <br/>             
                                   【 產量資料】  <br/>
                                   【人數工時離職】     <br/>
                                   【成品盤點資料】   <br/>
                                   【廢品管數據】 
       </font>                             
       </td>
       </tr>
    </table>                                                                                                                                                
    </div>   
  </body>
</html>
