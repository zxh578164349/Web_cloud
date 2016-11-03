/**
 * 
 */
package action;

import com.opensymphony.xwork2.ActionContext;

import entity.WebFormula;

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
		bean=webformulaser.findPageBean(page,PAGESIZE,formulaIndex);
		return "findPageBean";
	}
	
	public String findPageBean2(){
		ActionContext.getContext().getSession().remove("allrow");
		ActionContext.getContext().getSession().put("formulaIndex",formulaIndex);
		bean=webformulaser.findPageBean(page,PAGESIZE,formulaIndex);
		return "findPageBean1";		
	}
	
	public String findPageBean3(){
		formulaIndex=(String)ActionContext.getContext().getSession().get("formulaIndex");
		bean=webformulaser.findPageBean(page,PAGESIZE,formulaIndex);
		return "findPageBean1";
	}
	
	
	

}
