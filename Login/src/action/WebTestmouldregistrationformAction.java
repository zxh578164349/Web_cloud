package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import services.IWebTestmouldregistrationformServices;
import util.GlobalMethod;
import util.ImportExcel;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.WebColproductItems;
import entity.WebTestmouldregistrationform;
import entity.WebUser;

public class WebTestmouldregistrationformAction extends ActionSupport implements ServletResponseAware{
	private javax.servlet.http.HttpServletResponse response;
	private int page;
	private PageBean bean;
	private String dateA;
	private String dateB;
	private String customer;
	private String  brand;
	private String ajaxResult;
	private File file;
	private String fileFileName;
	private String fileContentType;
	private final static String SEPARATOR = "__";
	private int backIndex;
	private IWebTestmouldregistrationformServices webtestregisformser;	
	
	
	public int getBackIndex() {
		return backIndex;
	}

	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}

	public void setWebtestregisformser(
			IWebTestmouldregistrationformServices webtestregisformser) {
		this.webtestregisformser = webtestregisformser;
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

	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
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

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public void impormtData() throws IOException{
		response.setContentType("text/html;charset=utf-8");
		try{
			WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
			String strHead="日期__客戶__品牌__季節__型體__部件__量產工廠__型體負責人__試模雙數__不良數__每雙料重";
			DateFormat dfm=new SimpleDateFormat("yyyyMMdd");			
			String path="d:\\Webcolproductitems_backup\\"+dfm.format(new Date());//Excel文檔存放目錄
			ajaxResult="0";				
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
			List<WebTestmouldregistrationform>list_items=new ArrayList<WebTestmouldregistrationform>();			
			if(map.keySet().size()>1){
				response.getWriter().print("<script>window.parent.layer.msg('文檔中只允許一張表')</script>");	
				response.getWriter().close();
			}else{				
				for(String key:map.keySet()){								
					List<String>list=(List<String>)map.get(key);
					if(!list.get(0).contains(strHead)){				
						//response.getWriter().print("<script>window.parent.showDiv();window.parent.layer.msg('表格式不符合要求')</script>");	
						response.getWriter().print("<script>window.parent.layer.msg('表格式不符合要求')</script>");	
						response.getWriter().close();
						break;
						
					}
					String tdate=null;					
					for(int h=1;h<list.size();h++){											
						WebTestmouldregistrationform item=new WebTestmouldregistrationform();
						tdate=list.get(h).split(SEPARATOR)[1];
							if(GlobalMethod.isValidDate(tdate)){
								item.setTdate(tdate);
							}else{
								response.getWriter().print("<script>window.parent.layer.msg('日期需要符合以下格式：yyyyMMdd',3,3);</script>");
								response.getWriter().close();
								break;
							}														
							item.setCustomer(list.get(h).split(SEPARATOR)[2]);
							item.setBrand(list.get(h).split(SEPARATOR)[3]);
							item.setSeason(list.get(h).split(SEPARATOR)[4]);
							item.setShape(list.get(h).split(SEPARATOR)[5]);
							item.setModelno(list.get(h).split(SEPARATOR)[6]);
							item.setFactname(list.get(h).split(SEPARATOR)[7]);
							item.setPicMan(list.get(h).split(SEPARATOR)[8]);
							item.setNums(Double.valueOf(list.get(h).split(SEPARATOR)[9]));
							item.setUnhealthNums(Double.valueOf(list.get(h).split(SEPARATOR)[10]));
							item.setWeights(Double.valueOf(list.get(h).split(SEPARATOR)[11]));
							item.setWebUserByCreateuser(user);
							item.setCreateDate(dfm.format(new Date()));
							list_items.add(item);																							
					}
					
					webtestregisformser.addMore(list_items);
					
				}									
				response.getWriter().print("<script>window.parent.layer.msg('導入成功',3,1);window.parent.load('webtestreform_findPageBean');</script>");			
				response.getWriter().close();
				
			}
		}catch(Exception e){
			System.out.println(e);
			response.getWriter().print("<script>window.parent.layer.msg('導入錯誤',3,3);</script>");
			response.getWriter().close();
		}
		
	}
	
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allRow");		
		ActionContext.getContext().getSession().put("t_dateA", dateA);
		ActionContext.getContext().getSession().put("t_dateB", dateB);
		ActionContext.getContext().getSession().put("t_customer", customer);
		ActionContext.getContext().getSession().put("t_brand", brand);					
		bean=webtestregisformser.findPageBean(page, 0, dateA, dateB, customer, brand);		
		return "findPageBean";
	}
	
	public String findPageBean2(){		
		this.findPageBean();
		return "findPageBean1";
	}
	
	public String findPageBean3(){
		dateA=(String)ActionContext.getContext().getSession().get("c_factNo");
		dateB=(String)ActionContext.getContext().getSession().get("c_billNo");
		customer=(String)ActionContext.getContext().getSession().get("c_dateA");
		brand=(String)ActionContext.getContext().getSession().get("c_dateB");				
		bean=webtestregisformser.findPageBean(page, 0, dateA, dateB, customer, brand);
		if(backIndex==1){
			return "findPageBean";
		}else{
			return "findPageBean1";
		}		
	}

}
