/**
 * 
 */
package action;

import java.util.List;

import services.IWebBrProductServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.WebBrProduct;

/**   
 *    
 * 项目名称：WebLogin   
 * 类名称：WebBrProductAction   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2017/7/13 下午2:06:46   
 * 修改人：Administrator   
 * 修改时间：2017/7/13 下午2:06:46   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebBrProductAction extends ActionSupport{
		
	private IWebBrProductServices webbrproSer;
	private String factNo;
	private List<WebBrProduct> listbrpro;
	private int page;
	private PageBean bean;
	
	
	public int getPage(){
		return page;
	}
	public void setPage(int page){
		this.page=page;
	}
	public PageBean getBean(){
		return bean;
	}
	public void setBean(PageBean bean){
		this.bean=bean;
	}
	public String getFactNo(){
		return factNo;
	}
	public void setFactNo(String factNo){
		this.factNo=factNo;
	}
	public void setWebbrproSer(IWebBrProductServices webbrproSer){
		this.webbrproSer=webbrproSer;
	}
	
	
	
	
	public List<WebBrProduct> getListbrpro(){
		return listbrpro;
	}
	public void setListbrpro(List<WebBrProduct> listbrpro){
		this.listbrpro=listbrpro;
	}
	public String findByFactno(){
		listbrpro=webbrproSer.findByFactno(factNo);
		return "findByFactno";
	}
	
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("webbrproFactNo");
		bean=webbrproSer.findPageBean(20,page,factNo);
		return "findPageBean";
	}
	
	public String findPageBean2(){
		ActionContext.getContext().getSession().put("webbrproFactNo",factNo);
		bean=webbrproSer.findPageBean(20,page,factNo);
		return "findPageBean1";
	}
	
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getSession().get("webbrproFactNo");
		bean=webbrproSer.findPageBean(20,page,factNo);
		return "findPageBean1";
	}
	
	

}
