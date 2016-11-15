/**
 * 
 */
package action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import entity.WebErpBrankProcess;
import entity.WebFormula;
import entity.WebTabpom;

import services.IWebFormulaServices;
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
public class WebFormulaAction{
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

	private IWebFormulaServices webformulaser;

	public void setWebformulaser(IWebFormulaServices webformulaser){
		this.webformulaser=webformulaser;
	}
	
	
	public String findPageBean(){
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
		index.append("GJ"+factNo.split("__")[1]+factCode.split("__")[1]+"-"+createDate);
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
		try{
			String temp=factCode.split("__")[0];
			WebErpBrankProcess obj=new WebErpBrankProcess(Integer.parseInt(temp));			
			formula.setFactCode(obj);
			
			String factno=formula.getFactNo().getFactNo();
			if(isnull!=null&&isnull.equals("isnull")){
				formula.getFactNo().setFactNo(factno.split("__")[0]);
			}
			webformulaser.add(formula);
			ajaxResult="0";
		}catch(Exception e){
			ajaxResult="1";
			System.out.println(e);
		}		
		return "add";
	}
	
	public String delete(){
		try{
			webformulaser.delete(formulaIndex);
			ajaxResult="0";
		}catch(Exception e){
			ajaxResult="1";
			System.out.println(e);
		}
		return "delete";
	}
	
	
	

}
