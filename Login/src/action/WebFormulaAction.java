/**
 * 
 */
package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import net.sf.json.JSONArray;

import com.opensymphony.xwork2.ActionContext;
import entity.KyVisabillm;
import entity.KyVisabillmId;
import entity.KyVisabills;
import entity.KyVisabillsId;
import entity.KyzExpectmatmLog;
import entity.KyzVisaflow;
import entity.VWebFact;
import entity.WebErpBrankProcess;
import entity.WebErpProductinFormation;
import entity.WebFact;
import entity.WebFactId;
import entity.WebFormula;
import entity.WebFormulaItems;
import entity.WebObjsA;
import entity.WebObjsAId;
import entity.WebTabpom;
import entity.WebUser;
import freemarker.template.SimpleDate;

import services.IKyVisabillmServices;
import services.IKyzVisaFlowServices;
import services.IWebFormulaServices;
import services.IWebuserEmailServices;
import util.GlobalMethod;
import util.ImportExcel;
import util.ImportExcel_B;
import util.JasperHelper;
import util.PageBean;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebFormulaAction   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/11/3 上午11:18:03   
 * 修改人：Administrator   
 * 修改时间：2016/11/3 上午11:18:03   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebFormulaAction implements ServletResponseAware{
	private int page;
	private final static int PAGESIZE=20;
	private final static String SEPARATOR = "__";
	private String formulaIndex;
	private PageBean bean;
	private WebFormula formula;
	private String factNo;
	private String factCode;
	private String createDate;
	private String ajaxResult;
	private String backIndex;//返回標籤
	private String isnull;//添加  修改標識
	private String issuedDate_a;
	private String issuedDate_b;
	private JSONArray jsons;
	private String lookordown;
	private int itemid;
	private String pomNo;
	private String billNo;
	private String itemNo;
	private String visaSort;
	private Map<String,Object> map;
	private String readMk;//標識返回函文查看頁面(Y)，還是返回函文提交頁面(N)
	private Integer userId;
	private List<WebFormulaItems>items;
	private HttpServletResponse response;
	private IWebFormulaServices webformulaser;
	private IKyzVisaFlowServices visaSer;
	private IKyVisabillmServices visabillmSer;
	private IWebuserEmailServices webuseremailSer;
	private File file;
    private String fileFileName;
    private String fileContentType;
    private WebTabpom tabpom;
	
	
	
    
	public WebTabpom getTabpom() {
		return tabpom;
	}

	public void setTabpom(WebTabpom tabpom) {
		this.tabpom = tabpom;
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

	public Integer getUserId(){
		return userId;
	}

	public void setUserId(Integer userId){
		this.userId=userId;
	}

	public String getBillNo(){
		return billNo;
	}

	public void setBillNo(String billNo){
		this.billNo=billNo;
	}

	public String getItemNo(){
		return itemNo;
	}

	public void setItemNo(String itemNo){
		this.itemNo=itemNo;
	}

	public String getVisaSort(){
		return visaSort;
	}

	public void setVisaSort(String visaSort){
		this.visaSort=visaSort;
	}

	public String getReadMk(){
		return readMk;
	}

	public void setReadMk(String readMk){
		this.readMk=readMk;
	}

	public String getPomNo(){
		return pomNo;
	}

	public void setPomNo(String pomNo){
		this.pomNo=pomNo;
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public List<WebFormulaItems> getItems(){
		return items;
	}

	public void setItems(List<WebFormulaItems> items){
		this.items=items;
	}

	public String getLookordown(){
		return lookordown;
	}

	public void setLookordown(String lookordown){
		this.lookordown=lookordown;
	}

	public Map<String,Object> getMap(){
		return map;
	}

	public void setMap(Map<String,Object> map){
		this.map=map;
	}

	public JSONArray getJsons(){
		return jsons;
	}

	public void setJsons(JSONArray jsons){
		this.jsons=jsons;
	}

	public String getIssuedDate_a(){
		return issuedDate_a;
	}

	public void setIssuedDate_a(String issuedDate_a){
		this.issuedDate_a=issuedDate_a;
	}

	public String getIssuedDate_b(){
		return issuedDate_b;
	}

	public void setIssuedDate_b(String issuedDate_b){
		this.issuedDate_b=issuedDate_b;
	}

	public String getIsnull(){
		return isnull;
	}

	public void setIsnull(String isnull){
		this.isnull=isnull;
	}

	public String getBackIndex(){
		return backIndex;
	}

	public void setBackIndex(String backIndex){
		this.backIndex=backIndex;
	}

	public String getAjaxResult(){
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult){
		this.ajaxResult=ajaxResult;
	}

	public String getFactNo(){
		return factNo;
	}

	public void setFactNo(String factNo){
		this.factNo=factNo;
	}

	public String getFactCode(){
		return factCode;
	}

	public void setFactCode(String factCode){
		this.factCode=factCode;
	}

	public String getCreateDate(){
		return createDate;
	}

	public void setCreateDate(String createDate){
		this.createDate=createDate;
	}

	public WebFormula getFormula(){
		return formula;
	}

	public void setFormula(WebFormula formula){
		this.formula=formula;
	}

	public PageBean getBean(){
		return bean;
	}

	public void setBean(PageBean bean){
		this.bean=bean;
	}

	public int getPage(){
		return page;
	}

	public void setPage(int page){
		this.page=page;
	}

	public String getFormulaIndex(){
		return formulaIndex;
	}

	public void setFormulaIndex(String formulaIndex){
		this.formulaIndex=formulaIndex;
	}
	
	public void setVisaSer(IKyzVisaFlowServices visaSer){
		this.visaSer=visaSer;
	}

	public void setWebformulaser(IWebFormulaServices webformulaser){
		this.webformulaser=webformulaser;
	}
	
	
	public void setVisabillmSer(IKyVisabillmServices visabillmSer){
		this.visabillmSer=visabillmSer;
	}
	
	

	public void setWebuseremailSer(IWebuserEmailServices webuseremailSer){
		this.webuseremailSer=webuseremailSer;
	}
	public void setServletResponse(HttpServletResponse response){
		// TODO Auto-generated method stub
		this.response=response;
	}
	

	/**********************************使用者findPageBean********************************************/
	public String findPageBean(){
        this.clearSession();
		ActionContext.getContext().getSession().remove("allrow");//dao層  allrow
		//ActionContext.getContext().getSession().remove("formulaIndex");
		ActionContext.getContext().getSession().remove("formula");
		bean=webformulaser.findPageBean(page,PAGESIZE,formula,issuedDate_a,issuedDate_b);
		return "findPageBean";
	}
	
	public String findPageBean2(){
		ActionContext.getContext().getSession().remove("allrow");
		//ActionContext.getContext().getSession().put("formulaIndex",formulaIndex);
		ActionContext.getContext().getSession().put("formula",formula);
		bean=webformulaser.findPageBean(page,PAGESIZE,formula,issuedDate_a,issuedDate_b);
		return "findPageBean1";		
	}
	
	public String findPageBean3(){
		//formulaIndex=(String)ActionContext.getContext().getSession().get("formulaIndex");
		formula=(WebFormula)ActionContext.getContext().getSession().get("formula");
		bean=webformulaser.findPageBean(page,PAGESIZE,formula,issuedDate_a,issuedDate_b);
		String temp="findPageBean1";
		if(backIndex!=null&&backIndex.equals("1")){
			temp="findPageBean";//
		}
		return temp;
	}
	/**********************************使用者findPageBean********************************************/
	
	/**********************************訪客findPageBean********************************************/
	public String findPageBean_guest(){
        this.findPageBean();
		return "findPageBean_guest";
	}
	
	public String findPageBean2_guest(){
		this.findPageBean2();
		return "findPageBean_guest1";		
	}
	
	public String findPageBean3_guest(){
		this.findPageBean3();
		return "findPageBean_guest1";
	}
	/**********************************訪客findPageBean********************************************/
	
	
	public String makeFormulaIndex(){
		//int jj=2/0;
		StringBuffer index=new StringBuffer();
		index.append("GJ"+factNo.split("__")[1]+factCode.split("__")[1]+"-"+createDate.split("-")[0]);
		List<String>list=webformulaser.findFormulaIndex(factNo.split("__")[0],factCode.split("__")[0],createDate);
		if(list.size()>0){
			String temp=(Integer.parseInt(list.get(0).substring(list.get(0).length()-3))+1)+"";
			if(temp.length()==1){
				index.append("00"+Integer.parseInt(temp));
			}else if(temp.length()==2){
				index.append("0"+Integer.parseInt(temp));
			}else{
				index.append(temp);
			}			
		}else{
			index.append("001");
		}
		ajaxResult=index.toString();
		return "makeFormulaIndex";		
	}
	
	public String add(){
		//Map<String,Object>map=new HashMap<String,Object>();
		try{
			if(isnull!=null&&isnull.equals("isnull")){
				String temp=factCode.split("__")[0];
				WebErpBrankProcess obj=new WebErpBrankProcess(Integer.parseInt(temp));			
				formula.setFactCode(obj);				
				String factno=formula.getFactNo().getFactNo();
				formula.getFactNo().setFactNo(factno.split("__")[0]);							
				WebTabpom tabpom=(WebTabpom)ActionContext.getContext().getSession().get("tabpom");
				if(tabpom!=null){
					formula.setPom(tabpom);
					try{						
						GlobalMethod.uploadfile(tabpom);												
						List<BufferedInputStream>ins=(List<BufferedInputStream>)ActionContext.getContext().getSession().get("ins");
						List<BufferedOutputStream>outs=(List<BufferedOutputStream>)ActionContext.getContext().getSession().get("outs");
						if(outs!=null&&outs.size()>0){
							GlobalMethod.uploadFiles(ins, outs);
						}
						
					}catch(Exception e){
						e.printStackTrace();
						ajaxResult="3";
						return "add";
					}
				}				
			}
			webformulaser.add(formula);	
			this.clearSession();//添加成功后,清除session
			ajaxResult="0";
		}catch(Exception e){
			ajaxResult="1";
			System.out.println(e);
		}			
		return "add";
	}
	public String addItems(){
		try{
			Iterator ite=items.listIterator();
			while(ite.hasNext()){
				WebFormulaItems obj=(WebFormulaItems)ite.next();
				if(obj==null){
					ite.remove();
				}
			}
			webformulaser.addItems(items);
			ajaxResult="0";
		}catch(Exception e){
			ajaxResult="1";
			System.out.println(e);
		}
		return "addItems";
		
	}
	
	public String delete(){
		try{					
			KyzExpectmatmLog log=new KyzExpectmatmLog();
			log.setObj("WebFormula");
			log.setBillNo(formulaIndex);
			WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
			log.setUsername(user.getUsername());
			webformulaser.delete(formulaIndex,log);	
			
			File file=new File("d:\\WebtabpomFile_backup\\"+pomNo);
			if(file.exists()){
				GlobalMethod.deletefile(file);//引用下面刪除文件夾方法
			}
			ajaxResult="0";
		}catch(Exception e){
			ajaxResult="1";
			System.out.println(e);
		}
		return "delete";
	}
	
	public String deleteItem(){
		try{
			KyzExpectmatmLog log=new KyzExpectmatmLog();
			log.setObj("WebFormulaItem");
			WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
			log.setUsername(user.getUsername());
			webformulaser.deleteItems(itemid,log);
			ajaxResult="0";
			
		}catch(Exception e){
			ajaxResult="1";
			System.out.println(e);
		}
		return "deleteItem";
	}
	
	public String findById(){
		this.clearSession();
		formula=webformulaser.findById(formulaIndex);
		tabpom=formula.getPom();
		return "findById";
	}
	public String findById_layer(){
		formula=webformulaser.findById(billNo);//注意,應該是billNo,因爲簽核頁面統一使用變量名billNo
		return "findById_layer";
	}
	public String findById_layer2(){
		this.findById_layer();//手機訪問頁面
		return "findById_layer2";
	}
	
	public String sendEmail() throws ParseException{
		List<KyzVisaflow>list=visaSer.findPF(factNo);
		//jsons=JSONArray.fromObject(list);
		if(list.size()>0){
			try{
				String visaType=list.get(0).getId().getVisaSort();
				KyVisabillm vbm=new KyVisabillm(new KyVisabillmId(factNo,visaType,formulaIndex));
				WebUser user=GlobalMethod.getLoginUser();
				vbm.setUserCreate(user.getUsername());
				vbm.setPurmanNo(list.get(0).getId().getPurmanNo());
				vbm.setSignerNext(list.get(0).getVisaSigner());
				String date=GlobalMethod.to_date2(new Date(),"yyyyMMdd_hh");
				vbm.setVisaMk("N");
				vbm.setRevisaMk("N");
				vbm.setItemNext("01");
				vbm.setDateCreate(date);
				vbm.setUserId(new WebUser(userId));
				for(KyzVisaflow flow:list){
					KyVisabills vbs=new KyVisabills(new KyVisabillsId(vbm,flow.getId().getItemNo()));
					vbs.setVisaSigner(flow.getVisaSigner());
					vbs.setVisaRank(flow.getVisaRank());
					vbs.setVisaMk("N");
					vbs.setFlowMk(flow.getFlowMk());
					vbs.setVisible(flow.getVisible());
					vbm.getKyVisabillses().add(vbs);
				}				
				visabillmSer.add(vbm);
				List<String>list_emailPwd=webuseremailSer.findByFactNoAEmailPwd2(vbm.getId().getFactNo(),vbm.getSignerNext());
				GlobalMethod.sendNewEmail(vbm,list_emailPwd);//發送郵件
				//jsons.add(0,"0");
				ajaxResult="1";
			}catch(Exception e){
				//jsons.add(0,"1");
				ajaxResult="2";
				System.out.println(e);
			}						
		}else{
			ajaxResult="3";
		}					
		return "sendEmail";
	}
	
	public void print2() throws IOException{
		print(factNo,formulaIndex,null);
	}

	public void print(String factNo,String billNo,KyVisabillm vbm) throws IOException{		
		Map<String,Object>map_result=webformulaser.print(factNo,billNo,null);		
		if(map_result!=null&&map_result.size()>0){
			map=(Map<String,Object>)map_result.get("map");
			List<WebFormula>list=(List<WebFormula>)map_result.get("list");
			if(lookordown!=null){
				if(lookordown.equals("look")){
					JasperHelper.exportmain("line", map,"web_formula.jasper", list,billNo, "jasper/audit/");
				}else{
					JasperHelper.exportmain("pdf", map,"web_formula.jasper", list,billNo, "jasper/audit/");
				}
			}else{
				JasperHelper.exportmain("pdf", map,"web_formula.jasper", list,billNo, "jasper/audit/");
			}
		}else{
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('單號為"+billNo+"的函文不存在!');window.close()</script>");
		}						
				
	}
	
	public String toUrl(String filename) throws UnsupportedEncodingException{
		//String filename2=filename.replace("/u", "%");
		String urlname2=URLDecoder.decode(filename,"utf-8");
		return urlname2;
	}
	/**
	 * 解決url中空格轉換成 +號的問題
	 */
	public String toUrl2(String filename){
		return filename.replace("+", "%20");
	}
	
	
	/**
	 * 每次點擊上傳附檔時都要清除session
	 * @Title: clearSession
	 * @Description: TODO
	 * @param 
	 * @return void
	 * @throws
	 * @author web
	 * @date 2016/11/30
	 */
	public void clearSession(){
		ActionContext.getContext().getSession().remove("list_tabfile");		
		ActionContext.getContext().getSession().remove("filenames");
		ActionContext.getContext().getSession().remove("ins");
		ActionContext.getContext().getSession().remove("outs");
		ActionContext.getContext().getSession().remove("tabpom");
	}
	
	public void printlist() throws IOException{
		response.setContentType("text/html;charset=utf-8");
		Map<String,Object>map_result=webformulaser.print2(formula, issuedDate_a, issuedDate_b);	
		map=(Map<String,Object>)map_result.get("map");
		List<WebFormula>list=(List<WebFormula>)map_result.get("list");
		if(list!=null&&list.size()>0){
			if(list.size()>1500){
				response.getWriter().print("<script>alert('數據過多,導出失敗');window.close()</script>");
			}else{
				JasperHelper.exportmain("excel", map,"webformul_excel.jasper", list,"reportlist", "jasper/audit/");
			}
		}else{			
			response.getWriter().print("<script>alert('無數據');window.close()</script>");
		}						
	}
	
	
	
	
	
	
	/**
	 * 導入數據
	 * @throws IOException
	 */
	public void importFile() throws IOException{
		response.setContentType("text/html;charset=utf-8");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");	
		try{	
				String path="d:\\WebFormula_backup\\"+new SimpleDateFormat("yyyyMMdd").format(new Date());//Excel文檔存放目錄
				ajaxResult="0";				
				/*文件上傳*/
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
			Map<String, Object> map = ImportExcel_B.exportListFromFile(new File(path + "\\" + fileFileName));
			
			String temp=factCode.split("__")[0];
			WebErpBrankProcess obj=new WebErpBrankProcess(Integer.parseInt(temp));			
			formula.setFactCode(obj);
			formula.setFactNo(new VWebFact(factNo.split("__")[0]));
			
			a: for (String key : map.keySet()) {// for a
				List<String> list = (List<String>) map.get(key);
				if("Sheet1".equals(key)){
					formula.setFormulaName(list.get(0).split("__")[2]);//配方名稱
					formula.setMagnification(Double.valueOf(list.get(1).split("__")[2]));//倍率
					formula.setBrandBody(list.get(2).split("__")[2]);
					formula.setSemifinishedProductHardness(list.get(3).split("__")[2]);
					formula.setColor(list.get(4).split("__")[2]);
					formula.setProductHardness(list.get(5).split("__")[2]);
					formula.setIssuedDate(list.get(6).split("__")[2]);
					formula.setAssignBrand("是".equals(list.get(7).split("__")[2])?"1":"0");
					
				}else if("Sheet2".equals(key)){
					List<WebFormulaItems>ll=new ArrayList<WebFormulaItems>();
					String[] array_head = list.get(0).split("__");
					for (int i = 2; i < array_head.length; i++) {
						WebFormulaItems item=new WebFormulaItems();
						item.setFk_weberp_pf(new WebErpProductinFormation(Double.valueOf(list.get(0).split("__")[i]).intValue()));
						item.setPhrVal(Double.valueOf(list.get(3).split("__")[i]));
						item.setWeightVal(Double.valueOf(list.get(4).split("__")[i]));
						item.setRemark(list.get(0).split("__")[i]);
						item.setWebFormula(formula);
						ll.add(item);
					}
					//formula.setWebFormulaItemses(ll);
				}else{
					//硬度
					formula.getPom().setHardnessDescription(list.get(1).split("__")[2]);
					formula.getPom().setHardness(Double.valueOf(list.get(1).split("__")[4]));
					formula.getPom().setHardnessUnit(list.get(1).split("__")[3]);
					formula.getPom().setHardnessResult(list.get(1).split("__")[5]);
					
					//硬度±值
					formula.getPom().setHardness2(Double.valueOf(list.get(2).split("__")[2]));
					//拉力
					formula.getPom().setForcesDescription(list.get(3).split("__")[2]);
					formula.getPom().setForces(Double.valueOf(list.get(3).split("__")[4]));
					formula.getPom().setForcesUnit(list.get(3).split("__")[3]);
					formula.getPom().setForcesResult(list.get(3).split("__")[5]);
					//延伸
					formula.getPom().setExtendsDescription(list.get(4).split("__")[2]);
					formula.getPom().setExtend(Double.valueOf(list.get(4).split("__")[4]));
					formula.getPom().setExtendsUnit(list.get(4).split("__")[3]);
					formula.getPom().setExtendsResult(list.get(4).split("__")[5]);
					//C型撕裂
					formula.getPom().setTearingCDescription(list.get(5).split("__")[2]);
					formula.getPom().setTearingC(Double.valueOf(list.get(5).split("__")[4]));
					formula.getPom().setTearingCUnit(list.get(5).split("__")[3]);
					formula.getPom().setTearingCResult(list.get(5).split("__")[5]);
					//褲型撕裂
					formula.getPom().setTearingKDescription(list.get(6).split("__")[2]);
					formula.getPom().setTearingK(Double.valueOf(list.get(6).split("__")[4]));
					formula.getPom().setTearingKUnit(list.get(6).split("__")[3]);
					formula.getPom().setTearingKResult(list.get(6).split("__")[5]);
					//比重
					formula.getPom().setProportionDescription(list.get(7).split("__")[2]);
					formula.getPom().setProportion(Double.valueOf(list.get(7).split("__")[4]));
					formula.getPom().setProportionUnit(list.get(7).split("__")[3]);
					formula.getPom().setProportionResult(list.get(7).split("__")[5]);
				    //比重±值
					formula.getPom().setProportion2(Double.valueOf(list.get(8).split("__")[2]));
					//AKRON耐磨
					formula.getPom().setWresistingAkronDes(list.get(9).split("__")[2]);
					formula.getPom().setWresistingAkron(Double.valueOf(list.get(9).split("__")[4]));
					formula.getPom().setWresistingAkronUnit(list.get(9).split("__")[3]);
					formula.getPom().setWresistingAkronResult(list.get(9).split("__")[5]);
					//DIN耐磨
					formula.getPom().setWresistingDinDes(list.get(10).split("__")[2]);
					formula.getPom().setWresistingDin(Double.valueOf(list.get(10).split("__")[4]));
					formula.getPom().setWresistingDinUnit(list.get(10).split("__")[3]);
					formula.getPom().setWresistingDinResult(list.get(10).split("__")[5]);
					//止滑係數
					formula.getPom().setRatioADes(list.get(11).split("__")[2]);
					formula.getPom().setRatioA(Double.valueOf(list.get(11).split("__")[4]));
					formula.getPom().setRatioAUnit(list.get(11).split("__")[3]);
					formula.getPom().setRatioAResult(list.get(11).split("__")[5]);
					//耐油係數
					formula.getPom().setRatioBDes(list.get(12).split("__")[2]);
					formula.getPom().setRatioB(Double.valueOf(list.get(12).split("__")[4]));
					formula.getPom().setRatioBUnit(list.get(12).split("__")[3]);
					formula.getPom().setRatioBResult(list.get(12).split("__")[5]);
					//300% Modulus
					formula.getPom().setModulus300Des(list.get(13).split("__")[2]);
					formula.getPom().setModulus300(Double.valueOf(list.get(13).split("__")[4]));
					formula.getPom().setModulus300Unit(list.get(13).split("__")[3]);
					formula.getPom().setModulus300Result(list.get(13).split("__")[5]);
					//抗彎曲
					formula.getPom().setAbleBendDes(list.get(14).split("__")[2]);
					formula.getPom().setAbleBend(Double.valueOf(list.get(14).split("__")[4]));
					formula.getPom().setAbleBendUnit(list.get(14).split("__")[3]);
					formula.getPom().setAbleBendResult(list.get(14).split("__")[5]);
					//抗黃變
					formula.getPom().setAbleYellowDes(list.get(15).split("__")[2]);
					formula.getPom().setAbleYellow(Double.valueOf(list.get(15).split("__")[4]));
					formula.getPom().setAbleYellowUnit(list.get(15).split("__")[3]);
					formula.getPom().setAbleYellowResult(list.get(15).split("__")[5]);
					//抗高壓
					formula.getPom().setDefyPressDes(list.get(16).split("__")[2]);
					formula.getPom().setDefyPress(Double.valueOf(list.get(16).split("__")[4]));
					formula.getPom().setDefyPressUnit(list.get(16).split("__")[3]);
					formula.getPom().setDefyPressResult(list.get(16).split("__")[5]);
					//抗靜電
					formula.getPom().setDefyEleDes(list.get(17).split("__")[2]);
					formula.getPom().setDefyEle(Double.valueOf(list.get(17).split("__")[4]));
					formula.getPom().setDefyEleUnit(list.get(17).split("__")[3]);
					formula.getPom().setDefyEleResult(list.get(17).split("__")[5]);
					//老化水解
					formula.getPom().setAgeingDes(list.get(18).split("__")[2]);
					formula.getPom().setAgeing(Double.valueOf(list.get(18).split("__")[4]));
					formula.getPom().setAgeingUnit(list.get(18).split("__")[3]);
					formula.getPom().setAgeingResult(list.get(18).split("__")[5]);
					//收縮
					formula.getPom().setContractDes(list.get(19).split("__")[2]);
					formula.getPom().setContract(Double.valueOf(list.get(19).split("__")[4]));
					formula.getPom().setContractUnit(list.get(19).split("__")[3]);
					formula.getPom().setContractResult(list.get(19).split("__")[5]);
					//彈性
					formula.getPom().setElasticityDes(list.get(20).split("__")[2]);
					formula.getPom().setElasticity(Double.valueOf(list.get(20).split("__")[4]));
					formula.getPom().setElasticityUnit(list.get(20).split("__")[3]);
					formula.getPom().setElasticityResult(list.get(20).split("__")[5]);
					//壓縮
					formula.getPom().setCompressionDes(list.get(21).split("__")[2]);
					formula.getPom().setCompression(Double.valueOf(list.get(21).split("__")[4]));
					formula.getPom().setCompressionUnit(list.get(21).split("__")[3]);
					formula.getPom().setCompressionResult(list.get(21).split("__")[5]);
					
					//分裂
					formula.getPom().setDivisionDes(list.get(22).split("__")[2]);
					formula.getPom().setDivision(Double.valueOf(list.get(22).split("__")[4]));
					formula.getPom().setDivisionUnit(list.get(22).split("__")[3]);
					formula.getPom().setDivisionResult(list.get(22).split("__")[5]);
					//吐霜
					formula.getPom().setSpitCreamDes(list.get(23).split("__")[2]);
					formula.getPom().setSpitCream(Double.valueOf(list.get(23).split("__")[4]));
					formula.getPom().setSpitCreamUnit(list.get(23).split("__")[3]);
					formula.getPom().setSpitCreamResult(list.get(23).split("__")[5]);
					//認證
					formula.getPom().setAuthentications("是".equals(list.get(24).split("__")[2])?"1":"0");
					//特性說明
					formula.getPom().setInstruction(list.get(25).split("__")[2]);
					formula.getPom().setFormulaId(formula);
					formula.getPom().setUsername(user.getUsername());
					formula.getPom().setTabpomDate(new SimpleDateFormat("yyyyMMdd-hh").format(new Date()));
				}											
			}// for a
			formula.setCreateName(user.getUsername());
			formula.setCreateDate(new SimpleDateFormat("yyyyMMdd-hh").format(new Date()));
			webformulaser.add(formula);	
			response.getWriter().print("<script>window.parent.layer.msg('導入成功',3,1)</script>");	
			
											
		}catch(Exception e){
			System.out.println(e);
			response.getWriter().print("<script>window.parent.layer.msg('導入錯誤',3,3);</script>");
		}
	}
	
	
	
	

}
