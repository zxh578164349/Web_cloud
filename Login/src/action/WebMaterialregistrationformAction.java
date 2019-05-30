package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletResponseAware;

import services.IWebMaterialregistrationformServices;
import services.IWebmonthsServices;
import util.GlobalMethod;
import util.ImportExcel;
import util.JasperHelper;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.WebMaterialregistrationform;
import entity.WebMaterialregistrationitems;
import entity.WebMonths;
import entity.WebMonthsId;
import entity.WebSampleschedule;
import entity.WebUser;

public class WebMaterialregistrationformAction extends ActionSupport implements ServletResponseAware{
	private javax.servlet.http.HttpServletResponse response;
	private IWebMaterialregistrationformServices webmateriaser;
	private IWebmonthsServices webmonthsSer;
	private int page;
	private PageBean bean;
	private String sdate;
	private String edate;
	private String mtype;	
	private String ajaxResult;
	private File file;
	private String fileFileName;
	private String fileContentType;
	private final static String SEPARATOR = "__";
	private int backIndex;
	private WebMaterialregistrationform obj;
	private String sdateA;
	private String edateA;
	private String sdateB;
	private String edateB;
	private String sdateC;
	private String edateC;
	private String sdateD;
	private String edateD;
	private String sdateE;
	private String edateE;
	private String materielname;
	private JSONArray jsons;
	private String yymm;
	
		
	
	public String getYymm() {
		return yymm;
	}
	public void setYymm(String yymm) {
		this.yymm = yymm;
	}
	public void setWebmonthsSer(IWebmonthsServices webmonthsSer) {
		this.webmonthsSer = webmonthsSer;
	}
	public JSONArray getJsons() {
		return jsons;
	}
	public void setJsons(JSONArray jsons) {
		this.jsons = jsons;
	}
	public String getMaterielname() {
		return materielname;
	}
	public void setMaterielname(String materielname) {
		this.materielname = materielname;
	}
	
	
	public String getSdateA() {
		return sdateA;
	}
	public void setSdateA(String sdateA) {
		this.sdateA = sdateA;
	}
	public String getSdateB() {
		return sdateB;
	}
	public void setSdateB(String sdateB) {
		this.sdateB = sdateB;
	}
	public String getSdateC() {
		return sdateC;
	}
	public void setSdateC(String sdateC) {
		this.sdateC = sdateC;
	}
	public String getSdateD() {
		return sdateD;
	}
	public void setSdateD(String sdateD) {
		this.sdateD = sdateD;
	}
	public String getSdateE() {
		return sdateE;
	}
	public void setSdateE(String sdateE) {
		this.sdateE = sdateE;
	}
	public String getEdateA() {
		return edateA;
	}
	public void setEdateA(String edateA) {
		this.edateA = edateA;
	}
	public String getEdateB() {
		return edateB;
	}
	public void setEdateB(String edateB) {
		this.edateB = edateB;
	}
	public String getEdateC() {
		return edateC;
	}
	public void setEdateC(String edateC) {
		this.edateC = edateC;
	}
	public String getEdateD() {
		return edateD;
	}
	public void setEdateD(String edateD) {
		this.edateD = edateD;
	}
	public String getEdateE() {
		return edateE;
	}
	public void setEdateE(String edateE) {
		this.edateE = edateE;
	}
	public WebMaterialregistrationform getObj() {
		return obj;
	}
	public void setObj(WebMaterialregistrationform obj) {
		this.obj = obj;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public PageBean getBean() {
		return bean;
	}
	public void setBean(PageBean bean) {
		this.bean = bean;
	}
	
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	public String getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public int getBackIndex() {
		return backIndex;
	}
	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}
	public void setWebmateriaser(IWebMaterialregistrationformServices webmateriaser) {
		this.webmateriaser = webmateriaser;
	}
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;	
	}
	
	public void impormtData() throws IOException{
		response.setContentType("text/html;charset=utf-8");
		try{
			WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
			//String strHead="物料名稱__單位__期初庫存__客戶__索樣人__型體__訂單號碼__雙數__SIZE__樣品配色/材質__物性__客戶所需交期__模具狀況__色卡預計__色卡狀況(NG/簽回)__出料完成日期__燒製完成__實際完成日期__用途(階段)__備註-問題點__是否可請款.雙數__量產預告單";
			String strHead="物料名稱__物性__單位__期初庫存";
			DateFormat dfm=new SimpleDateFormat("yyyyMMdd");			
			String path="d:\\Webmateria_backup\\"+dfm.format(new Date());//Excel文檔存放目錄						
			//文件上傳
			if(file!=null){//不為空代表有上傳附檔,不能寫成files.size()>0,否則報空指針
				//File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("KyzexpFile\\"+kyz.getId().getBillNo()));//附檔上傳到項目
				File uploadFile_backup=new File(path);//附檔上傳到D盤(為了避免更新項目時丟失附檔,所在上傳到D盤)			
				if(!uploadFile_backup.exists()){
					uploadFile_backup.mkdirs();
				}																						
						FileInputStream in=new FileInputStream(file);
						FileOutputStream out_backup=new FileOutputStream(uploadFile_backup+"\\"+fileFileName);//備份
						byte[]b=new byte[1024];
						int length=0;
						while((length=in.read(b))>0){
							out_backup.write(b,0,length);//備份
						}																																				
			}
			
			//file=new File("i:\\test.xlsx");
			//Map<String,Object>map=ImportExcel.exportListFromFile(file);
			Map<String,Object>map=ImportExcel.exportListFromFile(new File(path+"\\"+fileFileName));
			List<WebMaterialregistrationform>list_main=new ArrayList<WebMaterialregistrationform>();															
				for(String key:map.keySet()){
					WebMaterialregistrationform obj=new WebMaterialregistrationform();
					List<WebMaterialregistrationitems>list_items=new ArrayList<WebMaterialregistrationitems>();
					obj.setMtype(key);
					obj.setSdateA(sdateA);
					obj.setEdateA(edateA);					
					obj.setSdateB(sdateB);
					obj.setEdateB(edateB);
					obj.setSdateC(sdateC);
					obj.setEdateC(edateC);
					obj.setSdateD(sdateD);
					obj.setEdateD(edateD);
					obj.setSdateE(sdateE);
					obj.setEdateE(edateE);
					
					List<String>list=(List<String>)map.get(key);
					if(!list.get(0).contains(strHead)){				
						//response.getWriter().print("<script>window.parent.showDiv();window.parent.layer.msg('表格式不符合要求')</script>");	
						response.getWriter().print("<script>window.parent.layer.msg('表格式不符合要求')</script>");	
						response.getWriter().close();
						break;
						
					}
													
					for(int h=1;h<list.size();h++){											
						WebMaterialregistrationitems item=new WebMaterialregistrationitems();
						if("0.0".equals(list.get(h).split(SEPARATOR)[1])){
							item.setMaterielname("");
						}else{
							item.setMaterielname(list.get(h).split(SEPARATOR)[1]);
						}
						if("0.0".equals(list.get(h).split(SEPARATOR)[2])){
							item.setCharacteristic("");							
						}else{
							item.setCharacteristic(list.get(h).split(SEPARATOR)[2]);
						}
						if("0.0".equals(list.get(h).split(SEPARATOR)[3])){
							item.setUnit("");
						}else{
							item.setUnit(list.get(h).split(SEPARATOR)[3]);
						}														
							item.setStocka(Double.valueOf(list.get(h).split(SEPARATOR)[4]));
							item.setInumsa(Double.valueOf(list.get(h).split(SEPARATOR)[5]));
							item.setOnumsa(Double.valueOf(list.get(h).split(SEPARATOR)[6]));
							item.setInumsb(Double.valueOf(list.get(h).split(SEPARATOR)[7]));
							item.setOnumsb(Double.valueOf(list.get(h).split(SEPARATOR)[8]));
							item.setInumsc(Double.valueOf(list.get(h).split(SEPARATOR)[9]));
							item.setOnumsc(Double.valueOf(list.get(h).split(SEPARATOR)[10]));
							item.setInumsd(Double.valueOf(list.get(h).split(SEPARATOR)[11]));
							item.setOnumsd(Double.valueOf(list.get(h).split(SEPARATOR)[12]));
							item.setStockb(Double.valueOf(list.get(h).split(SEPARATOR)[13]));
														
							if(sdateE!=null&&!"".equals(sdateE)){
								item.setInumse(Double.valueOf(list.get(h).split(SEPARATOR)[14]));
							}
							if(edateE!=null&&!"".equals(edateE)){
								item.setOnumse(Double.valueOf(list.get(h).split(SEPARATOR)[15]));
							}
							if("0.0".equals(list.get(h).split(SEPARATOR)[16])){
								item.setRemark("");
							}else{
								item.setRemark(list.get(h).split(SEPARATOR)[16]);
							}
														
							list_items.add(item);																							
					}
					obj.setWebMaterialregistrationitemses(list_items);
					obj.setWebUserByCreateuser(user);
					obj.setCreateDate(dfm.format(new Date()));
					list_main.add(obj);										
				}				
				//webmateriaser.addMore(list_main);
				WebMonths obj=new WebMonths(new WebMonthsId(yymm,"2"));//0 : web_testmouldregistrationform   1 : web_sampleschedule  2 : web_materialregistrationform
				obj.setWebMaterialregistrationforms(list_main);
				webmonthsSer.addWebmonths(obj);
				response.getWriter().print("<script>window.parent.layer.msg('導入成功',3,1);window.parent.loadUrl_bodyid('webmateria_findPageBean3');</script>");			
				response.getWriter().close();
									
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
			response.getWriter().print("<script>window.parent.layer.msg('導入錯誤',3,3);</script>");
			response.getWriter().close();
			
		}
	}
	
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allRow");	
		ActionContext.getContext().getSession().put("mtype", mtype);
		ActionContext.getContext().getSession().put("m_sdate", sdate);
		ActionContext.getContext().getSession().put("m_edate", edate);
		ActionContext.getContext().getSession().put("materielname", materielname);				
		bean=webmateriaser.findPageBean(page, 0, mtype, sdate, edate,materielname);		
		return "findPageBean";
	}
	
	public String findPageBean2(){		
		this.findPageBean();
		return "findPageBean1";
	}
	
	public String findPageBean3(){
		mtype=(String)ActionContext.getContext().getSession().get("mtype");
		sdate=(String)ActionContext.getContext().getSession().get("m_sdate");
		edate=(String)ActionContext.getContext().getSession().get("m_edate");
		materielname=(String)ActionContext.getContext().getSession().get("materielname");						
		bean=webmateriaser.findPageBean(page, 0, mtype, sdate, edate,materielname);
		if(backIndex==1){
			return "findPageBean";
		}else{
			return "findPageBean1";
		}		
	}
	
	public String findmtype(){
		List<String>list=webmateriaser.findmtype();
		jsons=JSONArray.fromObject(list);
		return "findmtype";
	}
	
	public void print(){
		Map<String,Object>map=new HashMap<String,Object>();		
		List<WebMaterialregistrationitems>list=webmateriaser.findObjsWithNopage(mtype, sdate, edate,materielname);
		StringBuffer fileName=new StringBuffer();
		StringBuffer title=new StringBuffer();
		fileName.append("report");		
				
		if(sdate!=null&&!sdate.equals("")){
			fileName.append("-"+sdate);
			title.append(sdate);
		}
		if(edate!=null&&!edate.equals("")){
			fileName.append("-"+edate);
			title.append("-"+edate);
		}
		title.append("原物料&粗胚進耗存登記表");
		map.put("title", title.toString());
		JasperHelper.exportmain("excel", map,"webmaterialform.jasper", list,fileName.toString(), "jasper/input/");
		
	}
	
}
