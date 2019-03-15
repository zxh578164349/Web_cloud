<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

 <s:iterator value="#session.list_items" status="x" id="temp">
			    <tr class="bluecss">
			     <td><input type="hidden" name="cbox"/></td>			           			          			          			            			          	     
			     <td >			    
			     
			     <input type="text" value="<s:property value='importmant'/>" readonly style="color:blue"/>			     
			     </td>			    
			     <td><input type="text"  value="<s:property value='shape'/>"  readonly style="color:blue"/></td>			    			     			     
			     <td ><input type="text"  value="<s:property value='CStructure'/>" readonly style="color:blue"/></td>
			     <td ><input type="text"  value="<s:property value='orderFactoryAndMan'/>" readonly style="color:blue"/></td>			     
			     <td ><input type="text"  value="<s:property value='purpose'/>"  readonly style="color:blue"/></td>
			     <td ><input type="text"  value="<s:property value='numbers'/>"   readonly style="color:blue"/></td>
			     <td ><input type="text"  value="<s:property value='weight'/>"   readonly style="color:blue"/></td>
			     <td ><input type="text"  value="<s:property value='remainNum'/>"  readonly style="color:blue"/></td>
			     <td ><input type="text"  value="<s:property value='unhealthNum'/>"  readonly style="color:blue"/></td>
			    
			     <td ><input type="text"  value="<s:property value='picMan'/>" readonly style="color:blue"/></td>
			     <td >			     
			     <input type="text"  value="<s:property value='paymk'/>" readonly style="color:blue"/>		     
			     </td>
			     <td >			     
			     <input type="text"  value="<s:property value='numbersbMk'/>" readonly style="color:blue"/>		     
			     </td>
			     <td ><input type="text"  value="<s:property value='numbersb'/>" readonly style="color:blue"/></td>
			     <td ><input type="text"  value="<s:property value='weightb'/>" readonly style="color:blue"/></td>
			      <td >
			      <input type="text"  value="<s:property value='remarks'/>" readonly style="color:blue"/>			      			     	     
			      </td>			      		      
			  </tr>
			  </s:iterator>	
