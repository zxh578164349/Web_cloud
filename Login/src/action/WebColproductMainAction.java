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

import org.apache.struts2.interceptor.ServletResponseAware;

import services.IKyVisabillmServices;
import services.IWebColproductMainServices;
import services.IWebuserEmailServices;
import util.GlobalMethod;
import util.ImportExcel;
import util.JasperHelper;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyVisabillm;
import entity.KyzExpectmatmLog;
import entity.WebColproductItems;
import entity.WebColproductMain;
import entity.WebNewproduct;


public class WebColproductMainAction extends ActionSupport implements ServletResponseAware{
	 private HttpServletResponse response;
	 private IWebColproductMainServices webcolproServer;
	 private WebColproductItems item;
	 private WebColproductMain obj;
	 private int page;
	 private PageBean bean;
	 private String factNo;
	 private String billNo;
	 private int iid;
	 private String yymmdd;
	 private String yymmdd2;
	 private String createDate;
	 private String ajaxResult;
	 private IKyVisabillmServices visabillmSer;
	 private IWebuserEmailServices webuseremailSer;
	 private String isnull;	 
	 private int backIndex;
	 private String title;
	 private String addorupdate;
	 private String itemNo;
	 private String visaSort;
	 private String readMk;
	 private String lookordown;
	 private File file;
	 private String fileFileName;
	 private String fileContentType;
	 private final static String SEPARATOR = "__";
	 
	 
	 
	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getLookordown() {
		return lookordown;
	}

	public void setLookordown(String lookordown) {
		this.lookordown = lookordown;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getVisaSort() {
		return visaSort;
	}

	public void setVisaSort(String visaSort) {
		this.visaSort = visaSort;
	}

	public String getReadMk() {
		return readMk;
	}

	public void setReadMk(String readMk) {
		this.readMk = readMk;
	}

	public String getAddorupdate() {
		return addorupdate;
	}

	public void setAddorupdate(String addorupdate) {
		this.addorupdate = addorupdate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getBackIndex() {
		return backIndex;
	}

	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}

	public String getIsnull() {
		return isnull;
	}

	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}

	public void setVisabillmSer(IKyVisabillmServices visabillmSer) {
		this.visabillmSer = visabillmSer;
	}

	public void setWebuseremailSer(IWebuserEmailServices webuseremailSer) {
		this.webuseremailSer = webuseremailSer;
	}

	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public WebColproductMain getObj() {
		return obj;
	}

	public void setObj(WebColproductMain obj) {
		this.obj = obj;
	}

	public WebColproductItems getItem() {
		return item;
	}

	public void setItem(WebColproductItems item) {
		this.item = item;
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

	public String getFactNo() {
		return factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public int getIid() {
		return iid;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}

	

	public String getYymmdd() {
		return yymmdd;
	}

	public void setYymmdd(String yymmdd) {
		this.yymmdd = yymmdd;
	}

	public String getYymmdd2() {
		return yymmdd2;
	}

	public void setYymmdd2(String yymmdd2) {
		this.yymmdd2 = yymmdd2;
	}

	public void setWebcolproServer(IWebColproductMainServices webcolproServer) {
		this.webcolproServer = webcolproServer;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
		
	}
	
	public String add(){
		try{			
			List<WebColproductItems>items=(List<WebColproductItems>)ActionContext.getContext().getSession().get("list_items");
			if(items!=null&&items.size()>0){
				for(WebColproductItems item:items){
					item.setWebColproductMain(new WebColproductMain(obj.getBillNo()));
				}
				obj.getWebColproductItemses().addAll(items);
			}			
			obj.setVisaTypeM(obj.getVisaType().substring(0,2));
			if(isnull.equals("isNull")){//start if
				WebColproductMain col=webcolproServer.findByBillNo(obj.getBillNo());
				if(col==null){					
					webcolproServer.add(obj);				
					KyVisabillm vbm=visabillmSer.findById(obj.getFactNo().getFactNo(), obj.getVisaType(), obj.getBillNo());				      
				    List<String>list_emailPwd=webuseremailSer.findByFactNoAEmailPwd2(vbm.getId().getFactNo(),vbm.getSignerNext());											      
					GlobalMethod.sendNewEmail(vbm,list_emailPwd);//發送郵件	
					ajaxResult="0";	//添加成功
					}else{
						ajaxResult="2";	//數據已存在
					}								
			}//end if
			else{				
				webcolproServer.add(obj);
				ajaxResult="0";	
			}			
		}catch(Exception e){
			e.printStackTrace();
			ajaxResult="1";	//添加失敗
		}
		ActionContext.getContext().getSession().remove("list_items");
		return "add";
	}
	
	
	
	public String makeBillNo(){
		String maxbillno=webcolproServer.findByfactNoACreatedate(factNo, createDate);
		StringBuffer bn=new StringBuffer();
		bn.append("CP");
		bn.append(factNo);
		bn.append(createDate.substring(2,8));
		if(maxbillno==null||"".equals(maxbillno)){
			bn.append("01");
		}else{
			int num=Integer.parseInt(maxbillno.substring(maxbillno.length()-2, maxbillno.length()));
			if(num+1<10){
				bn.append("0"+(num+1));
			}else{
				bn.append(num+1);
			}
		}
		ajaxResult=bn.toString();
		return "makeBillNo";	
	}
	
	
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allRow");
		ActionContext.getContext().getSession().remove("list_items");
		ActionContext.getContext().getSession().put("c_factNo", factNo);
		ActionContext.getContext().getSession().put("c_billNo", billNo);
		ActionContext.getContext().getSession().put("c_dateA", yymmdd);
		ActionContext.getContext().getSession().put("c_dateB", yymmdd2);		
		ActionContext.getContext().getSession().put("c_title", title);	
		bean=webcolproServer.findPageBeanMain(page, 0, factNo, billNo, yymmdd, yymmdd2,title);		
		return "findPageBean";
	}
	
	public String findPageBean2(){		
		this.findPageBean();
		return "findPageBean1";
	}
	
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getSession().get("c_factNo");
		billNo=(String)ActionContext.getContext().getSession().get("c_billNo");
		yymmdd=(String)ActionContext.getContext().getSession().get("c_dateA");
		yymmdd2=(String)ActionContext.getContext().getSession().get("c_dateB");	
		title=(String)ActionContext.getContext().getSession().get("c_title");	
		bean=webcolproServer.findPageBeanMain(page, 0, factNo, billNo, yymmdd, yymmdd2,title);
		if(backIndex==1){
			return "findPageBean";
		}else{
			return "findPageBean1";
		}		
	}
	
	public String findByBillNo(){
		addorupdate="update";
		obj=webcolproServer.findByBillNo(billNo);
		return "findByBillNo";
	}
	
	public String findByBillNo_layer(){
		obj=webcolproServer.findByBillNo(billNo);
		return "findByBillNo_layer";
	}
	
	public String delete(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setObj("WebColproduct");
		log.setBillNo(billNo);
		webcolproServer.delete(billNo, log);		
		return "delete";
	}
	
	
	public void print(String factNo,String billNo,String sort) throws IOException{		
		Map<String,Object>map_result=webcolproServer.print(factNo, billNo, sort,null);
		if(map_result!=null&&map_result.size()>0){
			Map<String,Object>map=(Map<String,Object>)map_result.get("map");
			List<WebNewproduct>list=(List<WebNewproduct>)map_result.get("list");
			if(lookordown!=null){
				if(lookordown.equals("look")){
					JasperHelper.exportmain("line", map,"webcolproduct_main.jasper", list,billNo, "jasper/audit/");
				}else{
					JasperHelper.exportmain("pdf", map,"webcolproduct_main.jasper", list,billNo, "jasper/audit/");
				}
			}else{
				JasperHelper.exportmain("pdf", map,"webcolproduct_main.jasper", list,billNo, "jasper/audit/");
			}
		}
								
	}
	
	public void print2() throws IOException{
		this.print(factNo, billNo, visaSort);
	}
	
	
	public void importFile() throws IOException{
		response.setContentType("text/html;charset=utf-8");
		try{
			/*List<String>filetypes=new ArrayList<String>();
			filetypes.add(".xls");
			filetypes.add(".xlsx");
			GlobalMethod.judgeFile(file, fileFileName, response, filetypes);//判斷文件類型，大小*/
			
			//String str="重要性__型體__結構__鞋廠及下單人__樣品用途__數量__單重(G)__留底量__不良__型體負責人__可否請款__是否量產__量產數量__需求料的重量__備註";
			String str="重要性__型體__結構__鞋廠及下單人__樣品用途__數量__單重(G)__留底量__不良__型體負責人__可否請款__階段__需求料的重量__備註";
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
			List<WebColproductItems>list_items=new ArrayList<WebColproductItems>();			
			if(map.keySet().size()>1){
				response.getWriter().print("<script>window.parent.layer.msg('文檔中只允許一張表')</script>");	
				response.getWriter().close();
			}else{				
				for(String key:map.keySet()){								
					List<String>list=(List<String>)map.get(key);
					if(!list.get(0).contains(str)){				
						//response.getWriter().print("<script>window.parent.showDiv();window.parent.layer.msg('表格式不符合要求')</script>");	
						response.getWriter().print("<script>window.parent.layer.msg('表格式不符合要求')</script>");	
						response.getWriter().close();
						break;
						
					}
					String importmant=null;
					String payMk=null;
					String numbersbMk=null;//是否量產
					String stage=null;//階段
					for(int h=1;h<list.size();h++){											
							WebColproductItems item=new WebColproductItems();															
							importmant=list.get(h).split(SEPARATOR)[1].trim();
							if(importmant!=null&&("A".equals(importmant)||"B".equals(importmant)||"C".equals(importmant))){
								item.setImportmant(importmant);
							}else{
								response.getWriter().print("<script>window.parent.layer.msg('重要性：A 或 B 或 C',3,3);</script>");
								response.getWriter().close();
								break;
							}														
							item.setShape(list.get(h).split(SEPARATOR)[2]);
							item.setCStructure(list.get(h).split(SEPARATOR)[3]);
							item.setOrderFactoryAndMan(list.get(h).split(SEPARATOR)[4]);
							item.setPurpose(list.get(h).split(SEPARATOR)[5]);
							item.setNumbers(Double.valueOf(list.get(h).split(SEPARATOR)[6]));//
							item.setWeight(Double.valueOf(list.get(h).split(SEPARATOR)[7]));
							item.setRemainNum(Double.valueOf(list.get(h).split(SEPARATOR)[8]));//
							item.setUnhealthNum(Double.valueOf(list.get(h).split(SEPARATOR)[9]));//
							item.setPicMan(list.get(h).split(SEPARATOR)[10]);
							payMk=list.get(h).split(SEPARATOR)[11].trim();
							if(payMk!=null&&("Y".equals(payMk)||"N".equals(payMk))){
								item.setPaymk(payMk);
							}else{
								response.getWriter().print("<script>window.parent.layer.msg('可否請款：Y 或  N ',3,3);</script>");
								response.getWriter().close();
								break;
							}
							
							//目前只在試模或樣品階段，不確定是否量產
							/*numbersbMk=list.get(h).split(SEPARATOR)[12].trim();
							if(numbersbMk!=null&&("Y".equals(numbersbMk)||"N".equals(numbersbMk))){
								item.setNumbersbMk(numbersbMk);
							}else{
								response.getWriter().print("<script>window.parent.layer.msg('是否量產：Y 或  N ',3,3);</script>");
								response.getWriter().close();
								break;
							}
							item.setNumbersb(Double.valueOf(list.get(h).split(SEPARATOR)[13]));*/
							stage=list.get(h).split(SEPARATOR)[12].trim();
							if(stage!=null&&("試作".equals(stage)||"樣品".equals(stage))){
								item.setStage(stage);
							}else{
								response.getWriter().print("<script>window.parent.layer.msg('階段：試作 或  樣品 ',3,3);</script>");
								response.getWriter().close();
								break;
							}
							
							item.setWeightb(Double.valueOf(list.get(h).split(SEPARATOR)[13]));							
							item.setRemarks(list.get(h).split(SEPARATOR)[14].equals("0.0")?"":list.get(h).split(SEPARATOR)[14]);							
							list_items.add(item);																							
					}																						
				}	
				
				ActionContext.getContext().getSession().put("list_items", list_items);
				response.getWriter().print("<script>window.parent.layer.msg('導入成功',3,1);window.parent.loadUrl_importData();</script>");			
				response.getWriter().close();
				
			}	
						
		}catch(Exception e){
			System.out.println(e);
			response.getWriter().print("<script>window.parent.layer.msg('導入錯誤',3,3);</script>");
			response.getWriter().close();
		}
			
	}
	
	
	
	 

}
