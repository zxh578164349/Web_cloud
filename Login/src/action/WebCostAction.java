package action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import services.IWebCostServices;
import util.GlobalMethod;
import util.JasperHelper;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.WebYielePrediction;
import entity.Webcost;
import entity.WebcostId;

public class WebCostAction extends ActionSupport implements
		ServletResponseAware {
	private IWebCostServices costSer;
	private String factNo;
	private int page;
	private String yymm;
	private Webcost cost;
	private PageBean bean;
	private HttpServletResponse respone;
	private String isnull;
	private WebcostId id;
	private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗
	private String yymm2;
	

	public String getYymm2() {
		return yymm2;
	}

	public void setYymm2(String yymm2) {
		this.yymm2 = yymm2;
	}

	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	public HttpServletResponse getRespone() {
		return respone;
	}

	public void setRespone(HttpServletResponse respone) {
		this.respone = respone;
	}

	public void setServletResponse(HttpServletResponse respone) {
		this.respone = respone;
	}

	public WebcostId getId() {
		return id;
	}

	public void setId(WebcostId id) {
		this.id = id;
	}

	public String getIsnull() {
		return isnull;
	}

	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}

	public PageBean getBean() {
		return bean;
	}

	public void setBean(PageBean bean) {
		this.bean = bean;
	}

	public Webcost getCost() {
		return cost;
	}

	public void setCost(Webcost cost) {
		this.cost = cost;
	}

	public String getFactNo() {
		return factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getYymm() {
		return yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public void setCostSer(IWebCostServices costSer) {
		this.costSer = costSer;
	}

	public String formatDouble(double s) {
		DecimalFormat format = new DecimalFormat(",###.##");
		String temp = format.format(s);
		return temp;
		// return temp.replace(",", "");
	}

	public String formatDouble2(double s) {
		DecimalFormat format = new DecimalFormat("#.##");
		String temp = format.format(s);
		return temp;
	}

	public String add() {
		DateFormat format = new SimpleDateFormat("yyyyMM");
		Date date = null;
		String result = null;

		try {
			date = format.parse(yymm);
			cost.getId().setYymm(date);	
			if (isnull.equals("isnull")) {											
				Webcost temp=costSer.findById(cost.getId().getFactNo(), cost.getId().getFactCode(), yymm);
				if (temp==null) {
					costSer.add(cost);
					result = "add";
					ajaxResult="0";
				} 
			}else {				
				costSer.add(cost);
				result = "add";
				ajaxResult="0";
			}
			if (result == null) {
				respone.setContentType("text/html;charset=utf-8");				
				respone.getWriter()
						.print("<script>alert('數據庫已存在("
								+cost.getId().getFactNo()
								+ " "
								+ cost.getId().getFactCode()
								+ " "
								+ format.format(cost.getId().getYymm())
								+ ")!');history.back()</script>");
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result="add";
			ajaxResult="1";
			e.printStackTrace();
			//return "fails";
		} 
		return result;
	}

	public String findPageBean() {
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("pubic_factno");
		ActionContext.getContext().getSession().remove("pubic_yymm");
		ActionContext.getContext().getSession().remove("pubic_yymm2");
		factNo = (String) ActionContext.getContext().getSession().get("factNo");
		bean = costSer.findPageBean(25, page, factNo, yymm,yymm2);

		return "beanList";

	}

	public String findPageBean2() {
		ActionContext.getContext().getApplication().clear();
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getSession().put("pubic_factno", factNo);					
		}
		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getSession().put("pubic_yymm", yymm);
		}
		if (yymm2 != null && !yymm2.equals("")) {
			ActionContext.getContext().getSession().put("pubic_yymm2", yymm2);
		}

		bean = costSer.findPageBean(25, page, factNo, yymm,yymm2);

		return "beanList1";
	}

	public String findPageBean3() {
		factNo = (String) ActionContext.getContext().getSession().get("pubic_factno");				
		yymm = (String) ActionContext.getContext().getSession().get("pubic_yymm");
		yymm2 = (String) ActionContext.getContext().getSession().get("pubic_yymm2");		
		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession().get("factNo");					
		}
		bean = costSer.findPageBean(25, page, factNo, yymm,yymm2);

		return "beanList1";

	}

	/*public String findPageBean2_print() {
		ActionContext.getContext().getApplication().clear();
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getApplication()
					.put("print_cost_factNo", factNo);

		} else {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getApplication()
					.put("print_cost_yymm", yymm);
		}
		bean = costSer.findPageBean(10, page, factNo, yymm);
		return "list";
	}

	public String findPageBean3_print() {
		factNo = (String) ActionContext.getContext().getApplication()
				.get("print_cost_factNo");
		yymm = (String) ActionContext.getContext().getApplication()
				.get("print_cost_yymm");
		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		bean = costSer.findPageBean(10, page, factNo, yymm);
		return "list";
	}*/

	public String findById() {
		cost = costSer.findById(id);
		return "findById";

	}

	public String delete() {
		costSer.delete(id);
		return "delete";
	}
	/**
	 * 打印文檔
	 * @throws IOException 
	 */
	public void print() throws IOException{
		List<Webcost>list=costSer.findByAny(factNo, yymm, yymm2);
		GlobalMethod.print(list, factNo, yymm, yymm2, "webcost.jasper", respone);					
	}

}
