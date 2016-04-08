/**
 * 
 */
package action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import services.IWebydataNoinputServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebydataNoinputAction   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/8 下午4:16:31   
 * 修改人：Administrator   
 * 修改时间：2016/4/8 下午4:16:31   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebydataNoinputAction extends ActionSupport implements ServletResponseAware {
	private IWebydataNoinputServices webydatenoinputSer;
	private javax.servlet.http.HttpServletResponse response;
	private String factNo;
	private String yymmdd;
	private String yymmdd2;
	private int page;
	private PageBean bean;
	

	public String getFactNo() {
		return factNo;
	}


	public void setFactNo(String factNo) {
		this.factNo = factNo;
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


	public void setWebydatenoinputSer(IWebydataNoinputServices webydatenoinputSer) {
		this.webydatenoinputSer = webydatenoinputSer;
	}


	/**
	 * 日期:2016/4/8
	 * 描述:
	 */
	
	
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allrow");//dao層allrow
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		ActionContext.getContext().getSession().put("public_factNo",factNo);
		ActionContext.getContext().getSession().remove("public_yymmdd");
		ActionContext.getContext().getSession().remove("public_yymmdd2");	
		bean=webydatenoinputSer.findPageBean(25, page, factNo, yymmdd, yymmdd2);
		return "findPageBean";
	}
	public String findPageBean2(){
		ActionContext.getContext().getSession().remove("allrow");//dao層allrow
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		ActionContext.getContext().getSession().put("public_factNo", factNo);
		ActionContext.getContext().getSession().put("public_yymmdd", yymmdd);
		ActionContext.getContext().getSession().put("public_yymmdd2", yymmdd2);
		bean=webydatenoinputSer.findPageBean(25, page, factNo, yymmdd, yymmdd2);
		return "findPageBean1";
	}
	public String findPageBean3(){
		ActionContext.getContext().getSession().get("public_factNo");
		ActionContext.getContext().getSession().get("public_yymmdd");
		ActionContext.getContext().getSession().get("public_yymmdd2");
		bean=webydatenoinputSer.findPageBean(25, page, factNo, yymmdd, yymmdd2);
		return"findPageBean1";
	}

}
