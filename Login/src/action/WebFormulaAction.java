/**
 * 
 */
package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
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
import entity.WebErpBrankProcess;
import entity.WebFormula;
import entity.WebFormulaItems;
import entity.WebTabpom;
import entity.WebUser;

import services.IKyVisabillmServices;
import services.IKyzVisaFlowServices;
import services.IWebFormulaServices;
import services.IWebuserEmailServices;
import util.GlobalMethod;
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
	private List<WebFormulaItems>items;
	private HttpServletResponse response;
	private IWebFormulaServices webformulaser;
	private IKyzVisaFlowServices visaSer;
	private IKyVisabillmServices visabillmSer;
	private IWebuserEmailServices webuseremailSer;
	
	
	
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
	

	public String findPageBean(){
        this.clearSession();
		ActionContext.getContext().getSession().remove("allrow");//dao層  allrow
		ActionContext.getContext().getSession().remove("formulaIndex");
		bean=webformulaser.findPageBean(page,PAGESIZE,formula,issuedDate_a,issuedDate_b);
		return "findPageBean";
	}
	
	public String findPageBean2(){
		ActionContext.getContext().getSession().remove("allrow");
		ActionContext.getContext().getSession().put("formulaIndex",formulaIndex);
		bean=webformulaser.findPageBean(page,PAGESIZE,formula,issuedDate_a,issuedDate_b);
		return "findPageBean1";		
	}
	
	public String findPageBean3(){
		formulaIndex=(String)ActionContext.getContext().getSession().get("formulaIndex");
		bean=webformulaser.findPageBean(page,PAGESIZE,formula,issuedDate_a,issuedDate_b);
		String temp="findPageBean1";
		if(backIndex!=null&&backIndex.equals("1")){
			temp="findPageBean";//
		}
		return temp;
	}
	
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
		jsons=JSONArray.fromObject(list);
		if(jsons.size()>0){
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
				for(KyzVisaflow flow:list){
					KyVisabills vbs=new KyVisabills(new KyVisabillsId(vbm,flow.getId().getItemNo()));
					vbs.setVisaSigner(flow.getVisaSigner());
					vbs.setVisaRank(flow.getVisaRank());
					vbs.setVisaMk("N");
					vbs.setFlowMk(flow.getFlowMk());
					vbm.getKyVisabillses().add(vbs);
				}				
				visabillmSer.add(vbm);
				List<String>list_emailPwd=webuseremailSer.findByFactNoAEmailPwd2(vbm.getId().getFactNo(),vbm.getSignerNext());
				GlobalMethod.sendNewEmail(vbm,list_emailPwd);//發送郵件
				jsons.add(0,"0");
			}catch(Exception e){
				jsons.add(0,"1");
				System.out.println(e);
			}						
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
		Map<String,Object>map_result=webformulaser.print2(formula, issuedDate_a, issuedDate_b);		
		if(map_result!=null&&map_result.size()>0){
			map=(Map<String,Object>)map_result.get("map");
			List<WebFormula>list=(List<WebFormula>)map_result.get("list");
			if(lookordown!=null){
				if(lookordown.equals("look")){
					JasperHelper.exportmain("line", null,"webformul_excel.jasper", list,"reportlist", "jasper/audit/");
				}else{
					JasperHelper.exportmain("excel", null,"webformul_excel.jasper", list,"reportlist", "jasper/audit/");
				}
			}else{
				JasperHelper.exportmain("excel", null,"webformul_excel.jasper", list,"reportlist", "jasper/audit/");
			}
		}else{
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('無數據');window.close()</script>");
		}
		
	}
	
	
	
	

}
