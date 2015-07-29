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
import services.IWebYielePredictionServices;
import util.Page;
import util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.WebYielePrediction;
import entity.WebYielePredictionId;

public class WebYPreAction extends ActionSupport implements
		ServletResponseAware {
	private IWebYielePredictionServices preSer;
	private WebYielePrediction pre;
	private String year;
	private String month;
	private Page pages;
	private String factNo;
	private String yymm;
	private int page;
	private int yz;
	private WebYielePredictionId id;
	private PageBean bean;
	private javax.servlet.http.HttpServletResponse response;
	private List nulllist_pre;
	private String isnull;
	

	public String getIsnull() {
		return isnull;
	}

	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}

	public List getNulllist_pre() {
		return nulllist_pre;
	}

	public void setNulllist_pre(List nulllist_pre) {
		this.nulllist_pre = nulllist_pre;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public PageBean getBean() {
		return bean;
	}

	public void setBean(PageBean bean) {
		this.bean = bean;
	}

	public WebYielePredictionId getId() {
		return id;
	}

	public void setId(WebYielePredictionId id) {
		this.id = id;
	}

	public void setYz(int yz) {
		this.yz = yz;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Page getPages() {
		return pages;
	}

	public void setPages(Page pages) {
		this.pages = pages;
	}

	public String getFactNo() {
		return factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getYymm() {
		return yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public WebYielePrediction getPre() {
		return pre;
	}

	public void setPre(WebYielePrediction pre) {
		this.pre = pre;
	}

	public void setPreSer(IWebYielePredictionServices preSer) {
		this.preSer = preSer;
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

	public String addYPre() {
		DateFormat format = new SimpleDateFormat("yyyyMM");
		Date date = null;
		String result = null;

		try {
			if (isnull.equals("isnull")) {				
				date = format.parse(yymm.toString());
				pre.getId().setYymm(date);

				String factNo1 = pre.getId().getFactNo();
				String factCode1 = pre.getId().getFactCode();
				String fact = (String) ActionContext.getContext().getSession()
						.get("factNo");
				List<WebYielePrediction> list = preSer.findAByFactNo(fact);
				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						if (factCode1.equals(list.get(i).getId().getFactCode())
								&& factNo1.equals(list.get(i).getId()
										.getFactNo())
								&& pre.getId().getYymm()
										.equals(list.get(i).getId().getYymm())) {

							break;
						} else if (i == list.size() - 1) {
							preSer.addYPre(pre);
							result = "addYPre";
						}
					}// end for
				} else {
					preSer.addYPre(pre);
					result = "addYPre";
				}

			} else {
				date = format.parse(yymm);
				pre.getId().setYymm(date);
				preSer.addYPre(pre);
				result = "addYPre";
			}

			if (result == null) { // 判斷返回結果
				response.setContentType("text/html;charset=utf-8");
				String temp1 = pre.getId().getFactNo();
				String temp2 = pre.getId().getFactCode();
				String temp3 = format.format(pre.getId().getYymm());
				response.getWriter()
						.print("<script>alert('數據庫已存在("
								+ temp1
								+ " "
								+ temp2
								+ " "
								+ temp3
								+ ")!');history.back()</script>");				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 取得數據
	 * 
	 * @return
	 */
	public void list() {
		pages.setPage(page);
		if (pages.getPage() > pages.getPageSize()) {
			pages.setPage(pages.getPageSize());
		}
		if (pages.getPage() == 1 || pages.getPage() < 1) {
			pages.setPage(1);
		}
		if (factNo != null && !factNo.equals("")) {
			ActionContext.getContext().getSession().put("factNo", factNo);
		}
		String factNos = (String) ActionContext.getContext().getSession()
				.get("factNo");
		List<WebYielePrediction> bList = preSer.selectYPre(factNos, yymm,
				pages.getPage(), pages.getRows());
		ActionContext.getContext().getSession().put("ypreList", bList);
		pages.setCount(preSer.totlePage(factNo, yymm));
		if (pages.getCount() % pages.getRows() == 0) {
			pages.setPageSize(pages.getCount() / pages.getRows());
		} else {
			pages.setPageSize(pages.getCount() / pages.getRows() + 1);
		}
	}

	/**
	 * 頁面顯示數據
	 * 
	 * @return
	 */
	public String getList() {
		list();
		if (factNo != null && !factNo.equals("") || yz == 1) {
			return "showList1";
		} else {
			return "showList";
		}
	}

	/**
	 * 首頁顯示數據
	 */
	public String getList1() {
		list();
		return "list";
	}

	public String findById() {
		pre = preSer.findById(id);
		return "findById";
	}

	public String findPageBean() {
		ActionContext.getContext().getApplication().clear();
		factNo = (String) ActionContext.getContext().getSession().get("factNo");// 登錄時保存factNo
		bean = preSer.findPageBean(15, page, factNo, yymm);
		return "beanList";

	}

	public String findPageBean2() {
		ActionContext.getContext().getApplication().clear();
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getApplication()
					.put("pre_factNo", factNo);

		}
		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getApplication().put("pre_yymm", yymm);
		}
		factNo = (String) ActionContext.getContext().getApplication()
				.get("pre_factNo");
		yymm = (String) ActionContext.getContext().getApplication()
				.get("pre_yymm");
		bean = preSer.findPageBean(15, page, factNo, yymm);
		return "beanList1";
	}

	public String findPageBean3() {
		factNo = (String) ActionContext.getContext().getApplication()
				.get("pre_factNo");
		yymm = (String) ActionContext.getContext().getApplication()
				.get("pre_yymm");
		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		bean = preSer.findPageBean(15, page, factNo, yymm);

		return "beanList1";

	}

	public String findPageBean4() {
		String result = this.findPageBean3();
		result = "beanList";
		return result;
	}

	public String delete() {
		preSer.delete(id);
		return "delete";
	}

	public String findPageBean2_print() {
		ActionContext.getContext().getApplication().clear();
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getApplication()
					.put("print_ypre_factNo", factNo);

		} else {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getApplication()
					.put("print_ypre_yymm", yymm);
		}
		bean = preSer.findPageBean(12, page, factNo, yymm);
		ActionContext.getContext().getSession()
				.put("zwcashList", preSer.getAllWithNoPage(factNo, yymm));
		return "list";
	}

	public String findPageBean3_print() {
		factNo = (String) ActionContext.getContext().getApplication()
				.get("print_ypre_factNo");
		yymm = (String) ActionContext.getContext().getApplication()
				.get("print_ypre_yymm");
		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		bean = preSer.findPageBean(12, page, factNo, yymm);
		return "list";
	}

	public String findNulFact() {
		nulllist_pre = preSer.findNullYpre(yymm);
		return "findNulFact";

	}

}
