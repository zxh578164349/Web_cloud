<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
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
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">	

<script type="text/javascript">
   jq(
      function(){
         jq("#subform1").Validform({
             btnSubmit : "#btn1",
             tiptype:3,
             tipSweep:true,
             showAllError:true
         });
         jq("#subform2").Validform({
             btnSubmit:"#btn2",
             tiptype:3,
             tipSweep:true,
             showAllError:true
         });
          jq("#subform3").Validform({
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
function test(factcode_obj){  //jquery获取复选框值    
  var factcode=factcode_obj.value;
  var chk_value =[];    
  jq('input[id='+factcode+'_factno]:checked').each(function(){    
   chk_value.push(jq(this).val());    
  });    
  alert(chk_value.length==0 ?'你还没有选择任何内容！':chk_value);    
} 
</script>

  </head>
  
  <body>
  <h2>分形態損益表-工廠</h2>
    <form action="vwebmachine_print_fact" method="post" id='subform1' target="_blank">
       <table class="tb_search">
       <h4>全年報表</h4>
          <tr>          
          <td>
          <!--<select name="year" datatype="*">
             <option value="">請選擇</option>
             <option value="2014">2014</option>
             <option value="2015">2015</option>
             <option value="2016">2016</option>
             <option value="2017">2017</option>
             <option value="2018">2018</option>
             <option value="2019">2019</option>
             <option value="2020">2020</option>
          </select>-->
          <span><input type="text" name="year" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy'})" datatype="*"/></span>
          
          
            <span><s:if test="#session.factNo=='tw'">			    
					<select name="factNo" id="factNo" datatype="*">
						<option value="">請選擇工廠</option>						
						<s:iterator value="#session.facts" id="temp">
							<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>								
						</s:iterator>
					</select>					
				</s:if> 
				<s:else>				  
					<select name="factNo" id="factNo" datatype="*">
						<option value="<s:property value="#session.factNo"/>">
							<s:property value="#session.factName" />(<s:property value="#session.factNo"/>)
						</option>
					</select>					
				</s:else>
				</span> 
				 
				 <input type="button" id="btn1" class="btn btn-primary" value="確定"/>     
          </td>
                          
          </tr>
       </table>
       
    </form>
     <hr>
    <form action="vwebmachine_print_month" method="post" id="subform2" target="_blank">
      <table class="tb_search">
      <h4>月份報表</h4>
         <tr>         
          <td>
              <span>
              <input type="text" id="begin" name="yymm_begin" datatype="*" onclick="WdatePicker({minDate:'{%y-1}-%m',maxDate:'#F{$dp.$D(\'end\',{M:-1})||\'%y-{%M-1}\'}'})" class="Wdate" >
              </span>至
              <span>
               <input type="text" id="end" name="yymm_end" datatype="*" onclick="WdatePicker({minDate:'#F{$dp.$D(\'begin\',{M:1})}',maxDate:'%y-%M'})" class="Wdate">
              </span>
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
              <input type="button" id="btn2"  class="btn btn-primary" value="確定"/>
          </td>         
          </tr>
      </table>
    </form>
    <hr>
    
    <s:if test="#session.factNo=='tw'">
    <hr>
    <h2>分形態損益表-台灣</h2>
    <form action="vwebmachine_print_tw_xlsx" method="post" id="subform3" target="_blank">
        <table class="tb_search">        
          <tr>
          <td>
          <span><input type="text" name="yymm" datatype="*" onclick="WdatePicker()" class="Wdate"></span>
          <input type="button" id="btn3" class="btn btn-primary" value="確定"/>
          </td>
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
   </s:if>
  </body>
</html>
