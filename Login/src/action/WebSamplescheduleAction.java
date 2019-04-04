package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletResponseAware;

import services.IWebSamplescheduleServices;
import util.GlobalMethod;
import util.ImportExcel;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.WebSampleschedule;
import entity.WebTestmouldregistrationform;
import entity.WebUser;

public class WebSamplescheduleAction extends ActionSupport implements ServletResponseAware{
	
	private javax.servlet.http.HttpServletResponse response;
	private IWebSamplescheduleServices websampleser;
	private int page;
	private PageBean bean;
	private String dateA;
	private String dateB;
	private String stype;
	private String samplelevel;
	private String customer;
	private String  brand;
	private String ajaxResult;
	private File file;
	private String fileFileName;
	private String fileContentType;
	private final static String SEPARATOR = "__";
	private int backIndex;
	private JSONArray jsons;
	
	
	
	public JSONArray getJsons() {
		return jsons;
	}

	public void setJsons(JSONArray jsons) {
		this.jsons = jsons;
	}

	public void setWebsampleser(IWebSamplescheduleServices websampleser) {
		this.websampleser = websampleser;
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

	public String getDateA() {
		return dateA;
	}

	public void setDateA(String dateA) {
		this.dateA = dateA;
	}

	public String getDateB() {
		return dateB;
	}

	public void setDateB(String dateB) {
		this.dateB = dateB;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public String getSamplelevel() {
		return samplelevel;
	}

	public void setSamplelevel(String samplelevel) {
		this.samplelevel = samplelevel;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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


	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	
	public void impormtData() throws IOException{
		response.setContentType("text/html;charset=utf-8");
		try{
			WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
			String strHead="接單日__樣品級數__品牌__客戶__索樣人__型體__訂單號碼__雙數__SIZE__樣品配色/材質__物性__客戶所需交期__模具狀況__色卡預計__色卡狀況(NG/簽回)__出料完成日期__燒製完成__實際完成日期__用途(階段)__備註-問題點__是否可請款.雙數__量產預告單";
			DateFormat dfm=new SimpleDateFormat("yyyyMMdd");			
			String path="d:\\Websampleschedule_backup\\"+dfm.format(new Date());//Excel文檔存放目錄						
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
			Map<String,Object>map=ImportExcel.exportListFromFile(new File(path+"\\"+fileFileName),"WebSampleschedule");
																			
				for(String key:map.keySet()){
					List<WebSampleschedule>list_items=new ArrayList<WebSampleschedule>();
					List<String>list=(List<String>)map.get(key);
					if(!list.get(0).contains(strHead)){				
						//response.getWriter().print("<script>window.parent.showDiv();window.parent.layer.msg('表格式不符合要求')</script>");	
						response.getWriter().print("<script>window.parent.layer.msg('表格式不符合要求')</script>");	
						response.getWriter().close();
						break;
						
					}
					String dateA=null;
					String dateB=null;
					String dateC=null;
					String dateD=null;
					String dateE=null;
					
				
					for(int h=1;h<list.size();h++){											
						WebSampleschedule item=new WebSampleschedule();
						dateA=list.get(h).split(SEPARATOR)[1];
						if(GlobalMethod.isValidDate(dateA)){
							item.setDateA(dateA);
						}else{
							response.getWriter().print("<script>window.parent.layer.msg('日期格式不符合要求')</script>");	
							response.getWriter().close();
							break;
						}
							
						
							item.setStype(key);
							item.setSamplelevel(list.get(h).split(SEPARATOR)[2]);
							item.setBrand(list.get(h).split(SEPARATOR)[3]);
							item.setCustomer(list.get(h).split(SEPARATOR)[4]);
							item.setCustomerb(list.get(h).split(SEPARATOR)[5]);
							item.setShape(list.get(h).split(SEPARATOR)[6]);
							item.setOrderno(list.get(h).split(SEPARATOR)[7]);
							item.setPairs(Double.valueOf(list.get(h).split(SEPARATOR)[8]));
							item.setSizes(list.get(h).split(SEPARATOR)[9]);
							item.setSamplemaerial(list.get(h).split(SEPARATOR)[10]);
							item.setCharacteristic(list.get(h).split(SEPARATOR)[11]);
							
							dateB=list.get(h).split(SEPARATOR)[12];	
							if(GlobalMethod.isValidDate(dateB)){
								item.setDateB(dateB);
							}else{
								response.getWriter().print("<script>window.parent.layer.msg('日期格式不符合要求')</script>");	
								response.getWriter().close();
								break;
							}
							
							
							item.setStatusA(list.get(h).split(SEPARATOR)[13]);
							item.setStatusB(list.get(h).split(SEPARATOR)[14]);
							item.setStatusC(list.get(h).split(SEPARATOR)[15]);
							
							dateC=list.get(h).split(SEPARATOR)[16];
							if(GlobalMethod.isValidDate(dateC)){
								item.setDateC(dateC);
							}else{
								response.getWriter().print("<script>window.parent.layer.msg('日期格式不符合要求')</script>");	
								response.getWriter().close();
								break;
							}
																					
							dateD=list.get(h).split(SEPARATOR)[17];	
							if(GlobalMethod.isValidDate(dateD)){
								item.setDateD(dateD);
							}else{
								response.getWriter().print("<script>window.parent.layer.msg('日期格式不符合要求')</script>");	
								response.getWriter().close();
								break;
							}																				
							dateE=list.get(h).split(SEPARATOR)[18];	
							if(GlobalMethod.isValidDate(dateE)){
								item.setDateE(dateE);
							}else{
								response.getWriter().print("<script>window.parent.layer.msg('日期格式不符合要求')</script>");	
								response.getWriter().close();
								break;
							}																					
							item.setPerpose(list.get(h).split(SEPARATOR)[19]);
							item.setQuestions(list.get(h).split(SEPARATOR)[20]);
							item.setPairsmk(list.get(h).split(SEPARATOR)[21]);
							item.setOutputnotice(list.get(h).split(SEPARATOR)[22]);																					
							item.setWebUserByCreateuser(user);
							item.setCreateDate(dfm.format(new Date()));
							list_items.add(item);																							
					}
					
					websampleser.addMore(list_items);
					
				}									
				response.getWriter().print("<script>window.parent.layer.msg('導入成功',3,1);window.parent.loadUrl_bodyid('websample_findPageBean3');</script>");			
				response.getWriter().close();
									
		}catch(Exception e){
			System.out.println(e);
			response.getWriter().print("<script>window.parent.layer.msg('導入錯誤',3,3);</script>");
			response.getWriter().close();
			
		}
	}
	
	
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allRow");
		ActionContext.getContext().getSession().put("stype", stype);
		ActionContext.getContext().getSession().put("s_dateA", dateA);
		ActionContext.getContext().getSession().put("s_dateB", dateB);
		ActionContext.getContext().getSession().put("samplelevel", samplelevel);
		ActionContext.getContext().getSession().put("s_brand", brand);	
		ActionContext.getContext().getSession().put("s_customer", customer);						
		bean=websampleser.findPageBean(page, 0, stype, dateA, dateB, samplelevel, brand, customer);		
		return "findPageBean";
	}
	
	public String findPageBean2(){		
		this.findPageBean();
		return "findPageBean1";
	}
	
	public String findPageBean3(){
		stype=(String)ActionContext.getContext().getSession().get("stype");		
		dateA=(String)ActionContext.getContext().getSession().get("s_dateA");
		dateB=(String)ActionContext.getContext().getSession().get("s_dateB");
		samplelevel=(String)ActionContext.getContext().getSession().get("samplelevel");		
		brand=(String)ActionContext.getContext().getSession().get("s_brand");
		customer=(String)ActionContext.getContext().getSession().get("s_customer");
		bean=websampleser.findPageBean(page, 0, stype, dateA, dateB, samplelevel, brand, customer);
		if(backIndex==1){
			return "findPageBean";
		}else{
			return "findPageBean1";
		}		
	}
	
	
	
	public String findBrand(){
		List<String>list=websampleser.findBrand();
		jsons=JSONArray.fromObject(list);
		return "findBrand";
	}
	
	public String findCustomer(){
		List<String>list=websampleser.findCustomer();
		jsons=JSONArray.fromObject(list);
		return "findCustomer";
	}
	
	
	
	

}
