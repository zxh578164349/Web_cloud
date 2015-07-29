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
			if (isnull.equals("isnull")) {
				date = format.parse(yymm);
				feed.getId().setYymm(date);
				String factNo1 = feed.getId().getFactNo();
				String factCode1 = feed.getId().getFactCode();
				String fact = (String) ActionContext.getContext().getSession()
						.get("factNo");
				List<Webbackfeed> list = feedSer.findByFactNo(fact);
				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						if (factCode1.equals(list.get(i).getId().getFactCode())
								&& factNo1.equals(list.get(i).getId()
										.getFactNo())
								&& feed.getId().getYymm()
										.equals(list.get(i).getId().getYymm())) {

							break;
						} else if (i == list.size() - 1) {
							feedSer.add(feed);
							result = "add";
						}
					}// end for
				} else {
					feedSer.add(feed);
					result = "add";
				}

			} else {
				date = format.parse(yymm);
				feed.getId().setYymm(date);
				feedSer.add(feed);
				result = "add";
			}

			if (result == null) { // 判斷返回結果
				response.setContentType("text/html;charset=utf-8");
				String temp1 = feed.getId().getFactNo();
				String temp2 = feed.getId().getFactCode();
				String temp3 = format.format(feed.getId().getYymm());
				response.getWriter()
						.print("<script>alert('數據庫已存在("
								+ temp1
								+ " "
								+ temp2
								+ " "
								+ temp3
								+ ")!');history.back()</script>");
				return result;
			} else {
				return result;
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fails";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fails";
		}
	}

	public String findPageBean() {
		ActionContext.getContext().getApplication().clear();
		factNo = (String) ActionContext.getContext().getSession().get("factNo");
		bean = feedSer.findPageBean(25, page, factNo, yymm);

		return "beanList";

	}

	public String findPageBean2() {
		ActionContext.getContext().getApplication().clear();
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getApplication()
					.put("feed_factNo", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getApplication().put("feed_yymm", yymm);
		}

		bean = feedSer.findPageBean(25, page, factNo, yymm);

		return "beanList1";
	}

	public String findPageBean3() {
		factNo = (String) ActionContext.getContext().getApplication()
				.get("feed_factNo");
		yymm = (String) ActionContext.getContext().getApplication()
				.get("feed_yymm");
		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		bean = feedSer.findPageBean(25, page, factNo, yymm);

		return "beanList1";

	}

	public String findPageBean2_print() {
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
	}

	public String findById() {
		feed = feedSer.findById(id);
		return "findById";

	}

	public String delete() {
		feedSer.delete(id);
		return "delete";
	}

}
