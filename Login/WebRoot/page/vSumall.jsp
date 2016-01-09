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
<link rel="stylesheet" type="text/css" href="css/form.css" />	
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
   $(
      function(){
         $("#subform1").Validform({
             btnSubmit : "#btn1",
             tiptype:3,
             tipSweep:true,
             showAllError:true
         });
         $("#subform2").Validform({
             btnSubmit:"#btn2",
             tiptype:3,
             tipSweep:true,
             showAllError:true
         });
          $("#subform3").Validform({
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
function checkAll() {
	var allcheckboxs=$("input[name='list_factno']");
	var the_all= document.getElementById("all");	
		//遍歷所選廠別狀態對應的所有checkbox
		for ( var i = 0; i < allcheckboxs.length; i++) {
			var checkbox = allcheckboxs[i];
			var font = document.getElementById("font_" + i);
			if (checkbox.checked === false && the_all.checked === true) {					
				// 正选  
				checkbox.checked = true;
				font.style.color = "red";

			} else if (checkbox.checked === true && the_all.checked == false) {
				// 反选  
				checkbox.checked = false;
				font.style.color = "";
			}
		}
	}
	
/**
選擇單個,如果一個也選中,則全部退回
*/	
	function clickOne(checkbox_obj,font){	   
	   var cb_factcode=document.getElementById("all");  
	   if(checkbox_obj.checked==true){
	      document.getElementById(font).style.color="red";
	   }else{
	       document.getElementById(font).style.color="";
	       //全部退回
	       //通過樣式 class獲取對應factCode的所有廠別
	       allcheckboxs=$("input[name='list_factno']");
	       var index=0;
	       for(var i=0;i<allcheckboxs.length;i++){
	           var checkbox=allcheckboxs[i];
	           if(checkbox.checked==false){
	               index++;	              
	           }
	       }
	       if(index==allcheckboxs.length){	           
	           cb_factcode.checked=false;
	       }
	   } 	  
	}
function test(factcode_obj){  //jquery获取复选框值    
  var factcode=factcode_obj.value;
  var chk_value =[];    
  $('input[id='+factcode+'_factno]:checked').each(function(){    
   chk_value.push($(this).val());    
  });    
  alert(chk_value.length==0 ?'你还没有选择任何内容！':chk_value);    
}    
</script>
<style type="text/css">
table{width:700px
    
 }
</style>
  </head>
  
  <body>
    <form action="vsumall_print_fact" method="post" id='subform1' target="_blank">
       <table>
          <caption>全廠損益表-工廠</caption>
          <tr>
          <td>(全年)</td>
          <td>年份</td>
          <td class="td_input">
          <select name="year" datatype="*">
             <option value="">請選擇</option>
             <option value="2014">2014</option>
             <option value="2015">2015</option>
             <option value="2016">2016</option>
             <option value="2017">2017</option>
             <option value="2018">2018</option>
             <option value="2019">2019</option>
             <option value="2020">2020</option>
          </select>
          </td>
          <td>工廠</td>
          <td class="td_input">
             <s:if test="#attr.factNo=='tw'">			    
					<select name="factNo" datatype="*">
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
          <td><input type="button" id="btn1" value="確定"/></td>
          </tr>                 
       </table>
    </form>
    <hr>
    <form action="vsumall_print_month" method="post" id="subform2" target="_blank">
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
    <hr>
    
    <s:if test='#session.factNo=="tw"'>
    <hr>
    <form action="vsumall_print_tw" method="post" id="subform3" target="_blank">
        <table>
          <caption>全廠損益表-台灣</caption>
          <tr>
          <td>日期</td><td><input type="text" name="yymm" datatype="*" onclick="WdatePicker()" class="Wdate"></td>
          <td><input type="button" id="btn3" value="確定"/></td>
          </tr>
        </table>
         <hr>
        <table>
          <tr>
          <td>全選<input type="checkbox" id="all" onclick="checkAll()"  style="width:18px;height:18px"/></td>
          
           <td>
               <s:iterator value="#attr.facts" id="temp" status="x">
                 <input type="checkbox" value="${temp[0]}" id="fact_no_${x.index}" name="list_factno" 
                 onclick="clickOne(this,'font_${x.index}')"/>                
                 <font id="font_${x.index}" >${temp[1]}(${temp[0]})</font>                                 
               </s:iterator>
           </td>
          
         </tr>
    </table>
    </form>
    </s:if>
  </body>
</html>
