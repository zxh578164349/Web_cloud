<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd-hh");
java.util.Date currentTime = new java.util.Date();//得到当前系统时间
String str_date = formatter.format(currentTime); //将日期时间格式化
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'WebMixPersonSaveOrUpdate.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="X-UA-Compatible" content="IE=edge" >
</head>

<body>	   	        	            	           
	            <table class="table table-condensed table-bordered">	              
	                 <tr class="active">
	                    <th>物性編號</th><th>配方索引</th><th colspan="3">品牌</th>
	                 </tr>	                          	                
	               <tr>
	                  <td>	                    	                   
	                      <s:property value='formula.pom.pomNo'/>						     	                   
	                  </td>
	                  <td>
	                      <s:property value='formula.formulaIndex'/>
	                  </td>
	                  <td colspan="3">	                     						
						  <s:property value='formula.pom.webBrank.name'/>						   
	                  </td>
	               </tr>
	                   <tr class="active">
	                      <th>名稱</th><th>規格</th><th>測試方式說明</th><th>單位</th><th>測試結果</th>	
	                   </tr>	                
	                <tr>
	                   <td>硬度</td>
	                   <td>
	                      <s:property value='formula.pom.hardness'/><br/>
	                      <s:property value='formula.pom.hardness2'/>(±值)
	                   </td>
	                   <td>
	                      <s:property value='formula.pom.hardnessDescription'/>
	                   </td>
	                   <td>
	                      <s:property value='formula.pom.hardnessUnit'/>
	                   </td>
	                   <td>
	                      <s:property value='formula.pom.hardnessResult'/>
	                   </td>
	                </tr>
	                <tr>
	                   <td>拉力</td>
	                   <td><s:property value='formula.pom.forces'/></td>
	                   <td><s:property value='formula.pom.forcesDescription'/></td>
	                   <td><s:property value='formula.pom.forcesUnit'/></td>
	                   <td><s:property value='formula.pom.forcesResult'/></td>
	                </tr>
	                <tr>
	                   <td>延伸</td>
	                   <td><s:property value='formula.pom.extend'/></td>
	                   <td><s:property value='formula.pom.extendsDescription'/></td>
	                   <td><s:property value='formula.pom.extendUnit'/></td>
	                   <td><s:property value='formula.pom.extendsResult'/></td>
	                </tr>
	                <tr>
	                   <td>C型撕裂</td>
	                   <td><s:property value='formula.pom.tearingC'/></td>
	                   <td><s:property value='formula.pom.tearingCDescription'/></td>
	                   <td><s:property value='formula.pom.tearingCUnit'/></td>
	                   <td><s:property value='formula.pom.tearingCResult'/></td>
	                </tr>
	                <tr>
	                   <td>褲型撕裂</td>
	                   <td><s:property value='formula.pom.tearingK'/></td>
	                   <td><s:property value='formula.pom.tearingKDescription'/></td>
	                   <td><s:property value='formula.pom.tearingKUnit'/></td>
	                   <td><s:property value='formula.pom.tearingKResult'/></td>
	                </tr>
	                <tr>
	                   <td>比重</td>
	                   <td>
	                      <s:property value='formula.pom.proportion'/><br/>
	                      <s:property value='formula.pom.proportion2'/>(±值)
	                   </td>
	                   <td>
	                     <s:property value='formula.pom.proportionDescription'/>
	                   </td>
	                   <td>
	                     <s:property value='formula.pom.proportionUnit'/>
	                   </td>
	                   <td>
	                     <s:property value='formula.pom.proportionResult'/>
	                   </td>
	                </tr>
	                <tr>
	                   <td>AKRON耐磨</td>
	                   <td><s:property value='formula.pom.wresistingAkron'/></td>
	                   <td><s:property value='formula.pom.wresistingAkronDes'/></td>
	                   <td><s:property value='formula.pom.wresistingAkronUnit'/></td>
	                   <td><s:property value='formula.pom.wresistingAkronResult'/></td>
	                </tr>
	                <tr>
	                   <td>DIN耐磨</td>
	                   <td><s:property value='formula.pom.wresistingDin'/></td>
	                   <td><s:property value='formula.pom.wresistingDinDes'/></td>
	                   <td><s:property value='formula.pom.wresistingDinUnit'/></td>
	                   <td><s:property value='formula.pom.wresistingDinResult'/></td>
	                </tr>
	                <tr>
	                   <td>止滑係數</td>
	                   <td><s:property value='formula.pom.ratioA'/></td>
	                   <td><s:property value='formula.pom.ratioADes'/></td>
	                   <td><s:property value='formula.pom.ratioAUnit'/></td>
	                   <td><s:property value='formula.pom.ratioAResult'/></td>
	                </tr>
	                <tr>
	                   <td>耐油係數</td>
	                   <td><s:property value='formula.pom.ratioB'/></td>
	                   <td><s:property value='formula.pom.ratioBDes'/></td>
	                   <td><s:property value='formula.pom.ratioBUnit'/></td>
	                   <td><s:property value='formula.pom.ratioBResult'/></td>
	                </tr>
	                <tr>
	                   <td>耐彎曲</td>
	                   <td><s:property value='formula.pom.ableBend'/></td>
	                   <td><s:property value='formula.pom.ableBendDes'/></td>
	                   <td><s:property value='formula.pom.ableBendUnit'/></td>
	                   <td><s:property value='formula.pom.ableBendResult'/></td>
	                </tr>
	                <tr>
	                   <td>耐黃變</td>
	                   <td><s:property value='formula.pom.ableYellow'/></td>
	                   <td><s:property value='formula.pom.ableYellowDes'/></td>
	                   <td><s:property value='formula.pom.ableYellowUnit'/></td>
	                   <td><s:property value='formula.pom.ableYellowResult'/></td>
	                </tr>
	                <tr>
	                   <td>抗高壓</td>
	                   <td><s:property value='formula.pom.defyPress'/></td>
	                   <td><s:property value='formula.pom.defyPressDes'/></td>
	                   <td><s:property value='formula.pom.defyPressUnit'/></td>
	                   <td><s:property value='formula.pom.defyPressResult'/></td>
	                </tr>
	                <tr>
	                   <td>抗靜電</td>
	                   <td><s:property value='formula.pom.defyEle'/></td>
	                   <td><s:property value='formula.pom.defyEleDes'/></td>
	                   <td><s:property value='formula.pom.defyEleUnit'/></td>
	                   <td><s:property value='formula.pom.defyEleResult'/></td>
	                </tr>
	                <tr>
	                   <td>老化水解</td>
	                   <td><s:property value='formula.pom.ageing'/></td>
	                   <td><s:property value='formula.pom.ageingDes'/></td>
	                   <td><s:property value='formula.pom.ageingUnit'/></td>
	                   <td><s:property value='formula.pom.ageingResult'/></td>
	                </tr>
	                <tr>
	                   <td>收縮</td>
	                   <td><s:property value='formula.pom.contract'/></td>
	                   <td><s:property value='formula.pom.contractDes'/></td>
	                   <td><s:property value='formula.pom.contractUnit'/></td>
	                   <td><s:property value='formula.pom.contractResult'/></td>
	                </tr>
	                <tr>
	                   <td>彈性</td>
	                   <td><s:property value='formula.pom.elasticity'/></td>
	                   <td><s:property value='formula.pom.elasticityDes'/></td>
	                   <td><s:property value='formula.pom.elasticityUnit'/></td>
	                   <td><s:property value='formula.pom.elasticityResult'/></td>
	                </tr>
	                <tr>
	                   <td>壓縮</td>
	                   <td><s:property value='formula.pom.compression'/></td>
	                   <td><s:property value='formula.pom.compressionDes'/></td>
	                   <td><s:property value='formula.pom.compressionUnit'/></td>
	                   <td><s:property value='formula.pom.compressionResult'/></td>
	                </tr>
	                <tr>
	                   <td>分裂</td>
	                   <td><s:property value='formula.pom.division'/></td>
	                   <td><s:property value='formula.pom.divisionDes'/></td>
	                   <td><s:property value='formula.pom.divisionUnit'/></td>
	                   <td><s:property value='formula.pom.divisionResult'/></td>
	                </tr>
	                <tr>
	                   <td>300% Modulus</td>
	                   <td><s:property value='formula.pom.modulus300'/></td>
	                   <td><s:property value='formula.pom.modulus300Des'/></td>
	                   <td><s:property value='formula.pom.modulus300Unit'/></td>
	                   <td><s:property value='formula.pom.modulus300Result'/></td>
	                </tr>
	                <tr>
	                   <td>吐霜</td>
	                   <td><s:property value='formula.pom.spitCream'/></td>
	                   <td><s:property value='formula.pom.spitCreamDes'/></td>
	                   <td><s:property value='formula.pom.spitCreamUnit'/></td>
	                   <td><s:property value='formula.pom.spitCreamResult'/></td>
	                </tr>
	                <tr>
	                   <td>認證</td>
	                   <td colspan="5">
	                       <s:if test='formula.pom.authentications=="1"'>
					                         是					                      							
						   </s:if> 
						   <s:else>					                      
					                       否
						   </s:else> 						   
	                   </td>
	                </tr>
	                <tr>
	                   <td>特性說明</td>
	                   <td colspan="5">
	                      <textarea style="width:100%;height:100px" readonly><s:property value='formula.pom.instruction'/></textarea>					      							                     	                    
	                   </td>                 
	                </tr>	                                         											
</table>	


	<hr/>		
     <s:if test="formula.pom.webTabpomfiles.size>0">		
      <div id="webtabfiledao">	
	    <b style="color: blue">附檔:</b><br/>
		 <div id="fileJson">
		   <s:iterator value="formula.pom.webTabpomfiles">
		        <a href="/upload_webtabpom/<s:property value='id.webTabpom.pomNo'/>/<s:property value="%{toUrl2(id.filename)}"/>" target="_blank">
		        <s:property value="id.filename"/>&nbsp;
		        </a>		        
		   </s:iterator>
		  </div>	  
     </div>		
    <hr/>
</s:if> 	 
	
</body>
</html>
