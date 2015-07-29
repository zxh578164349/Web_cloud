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

import services.IWebCostServices;
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
			if (isnull.equals("isnull")) {
				date = format.parse(yymm);
				cost.getId().setYymm(date);
				String factNo1 = cost.getId().getFactNo();
				String factCode1 = cost.getId().getFactCode();
				String fact = (String) ActionContext.getContext().getSession()
						.get("factNo");
				List<Webcost> list = costSer.findByFactNo(fact);
				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						if (factCode1.equals(list.get(i).getId().getFactCode())
								&& factNo1.equals(list.get(i).getId()
										.getFactNo())
								&& cost.getId().getYymm()
										.equals(list.get(i).getId().getYymm())) {

							break;
						} else if (i == list.size() - 1) {
							costSer.add(cost);
							result = "add";
						}
					}// end for
				} else {
					costSer.add(cost);
					result = "add";
				}

			} else {
				date = format.parse(yymm);
				cost.getId().setYymm(date);
				costSer.add(cost);
				result = "add";
			}

			if (result == null) { // 判斷返回結果
				respone.setContentType("text/html;charset=utf-8");
				String temp1 = cost.getId().getFactNo();
				String temp2 = cost.getId().getFactCode();
				String temp3 = format.format(cost.getId().getYymm());
				respone.getWriter()
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
		bean = costSer.findPageBean(25, page, factNo, yymm);

		return "beanList";

	}

	public String findPageBean2() {
		ActionContext.getContext().getApplication().clear();
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getApplication()
					.put("cost_factNo", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getApplication().put("cost_yymm", yymm);
		}

		bean = costSer.findPageBean(25, page, factNo, yymm);

		return "beanList1";
	}

	public String findPageBean3() {
		factNo = (String) ActionContext.getContext().getApplication()
				.get("cost_factNo");
		yymm = (String) ActionContext.getContext().getApplication()
				.get("cost_yymm");
		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		bean = costSer.findPageBean(25, page, factNo, yymm);

		return "beanList1";

	}

	public String findPageBean2_print() {
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
	}

	public String findById() {
		cost = costSer.findById(id);
		return "findById";

	}

	public String delete() {
		costSer.delete(id);
		return "delete";
	}

}
