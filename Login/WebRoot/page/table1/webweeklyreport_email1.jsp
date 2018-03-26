<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd");
Calendar cal=Calendar.getInstance();
String createDate=sdf.format(cal.getTime());//當前日期
 %>
    <s:if test="obj==null">       
       <input type="hidden" name="obj.createDate" value="<%=createDate %>" />
    </s:if>
    <s:else>            
       <input type="hidden" name="obj.RId" value="<s:property value='obj.RId'/>"/>        	   
	   <input type="hidden" name="obj.createDate" value="<s:property value='obj.createDate'/>" />
	   <input type="hidden" name="obj.updateDate" value="<%=createDate %>"/>	   
    </s:else>  
  
  <div class="form-group">
    <label>本周報告事項</label>
    <textarea  class="form-control" name="obj.RContent" rows="8" datatype="*"><s:property value="obj.RContent"/></textarea>
  </div>
  <div class="form-group">
     <label>上周報告事項</label>
       <textarea  class="form-control" name="obj.RContentLast" rows="8"><s:property value="obj.RContentLast"/></textarea>                
  </div>

