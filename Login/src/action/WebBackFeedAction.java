package action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import services.IWebBackFeedServices;
import services.IWebCostServices;
import util.GlobalMethod;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.*;

public class WebBackFeedAction extends ActionSupport implements
		ServletResponseAware {
	private IWebBackFeedServices feedSer;
	private String factNo;
	private int page;
	private String yymm;
	private Webbackfeed feed;
	private PageBean bean;
	private javax.servlet.http.HttpServletResponse response;
	private String isnull;
	private WebbackfeedId id;
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

	public Webbackfeed getFeed() {
		return feed;
	}

	public void setFeed(Webbackfeed feed) {
		this.feed = feed;
	}

	public PageBean getBean() {
		return bean;
	}

	public void setBean(PageBean bean) {
		this.bean = bean;
	}

	public String getIsnull() {
		return isnull;
	}

	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}

	public WebbackfeedId getId() {
		return id;
	}

	public void setId(WebbackfeedId id) {
		this.id = id;
	}

	public void setFeedSer(IWebBackFeedServices feedSer) {
		this.feedSer = feedSer;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
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
			feed.getId().setYymm(date);
			if (isnull.equals("isnull")) {								
				Webbackfeed temp=feedSer.findById(feed.getId().getFactNo(), feed.getId().getFactCode(), date);
				if (temp==null) {					
					feedSer.add(feed);
					result = "add";
					ajaxResult="0";
				} 
			} else {				
				feedSer.add(feed);
				result = "add";
				ajaxResult="0";
			}
			if (result == null) { 
				response.setContentType("text/html;charset=utf-8");				
				response.getWriter()
						.print("<script>alert('數據庫已存在("
								+ feed.getId().getFactNo()
								+ " "
								+ feed.getId().getFactCode()
								+ " "
								+  format.format(feed.getId().getYymm())
								+ ")!');history.back()</script>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result="add";
			ajaxResult="1";
			e.printStackTrace();
		}
		return result;
	}

	public String findPageBean() {
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_yymm");
		ActionContext.getContext().getSession().remove("public_yymm2");
		factNo = (String) ActionContext.getContext().getSession().get("factNo");
		bean = feedSer.findPageBean(25, page, factNo, yymm,yymm2);

		return "beanList";

	}

	public String findPageBean2() {
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_yymm");
		ActionContext.getContext().getSession().remove("public_yymm2");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getSession().put("public_factno", factNo);
					
		}
		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getSession().put("public_yymm", yymm);
		}
		if (yymm2 != null && !yymm2.equals("")) {
			ActionContext.getContext().getSession().put("public_yymm2", yymm2);
		}

		bean = feedSer.findPageBean(25, page, factNo, yymm,yymm2);

		return "beanList1";
	}

	public String findPageBean3() {
		factNo = (String) ActionContext.getContext().getSession().get("public_factno");				
		yymm = (String) ActionContext.getContext().getSession().get("public_yymm");
		yymm2 = (String) ActionContext.getContext().getSession().get("public_yymm2");
				
		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession().get("factNo");					
		}
		bean = feedSer.findPageBean(25, page, factNo, yymm,yymm2);

		return "beanList1";

	}

	/*public String findPageBean2_print() {
		ActionContext.getContext().getApplication().clear();
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getApplication()
					.put("print_feed_factNo", factNo);

		} else {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getApplication()
					.put("print_feed_yymm", yymm);
		}
		bean = feedSer.findPageBean(10, page, factNo, yymm);
		return "list";
	}

	public String findPageBean3_print() {
		factNo = (String) ActionContext.getContext().getApplication()
				.get("print_feed_factNo");
		yymm = (String) ActionContext.getContext().getApplication()
				.get("print_feed_yymm");
		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		bean = feedSer.findPageBean(10, page, factNo, yymm);
		return "list";
	}*/

	public String findById() {
		feed = feedSer.findById(id);
		return "findById";

	}

	public String delete() {
		feedSer.delete(id);
		return "delete";
	}
	
	/**
	 * 打印文檔
	 * @throws IOException 
	 */
	public void print() throws IOException{
		List<Webbackfeed>list=feedSer.findByAny(factNo, yymm, yymm2);
		GlobalMethod.print(list, factNo, yymm, yymm2, "webbackfeed.jasper", response);
	}

}
