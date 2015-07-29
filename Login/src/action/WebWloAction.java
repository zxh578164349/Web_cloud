package action;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import services.IWebFactServices;
import services.IWebwloServices;
import util.Page;
import util.PageBean;
import entity.Webscrapt;
import entity.Webwlo;
import entity.WebwloId;

public class WebWloAction extends ActionSupport implements ServletResponseAware {
	private Webwlo wlo;
	private WebwloId id;
	private IWebwloServices wloService;
	private javax.servlet.http.HttpServletResponse response;
	private String factNo;
	private String yymm;
	private int page;
	private String type;
	private String year;
	private String month;
	private int yz;
	private Page pages;
	private String bs;
	private PageBean bean;
	

	public PageBean getBean() {
		return bean;
	}

	public void setBean(PageBean bean) {
		this.bean = bean;
	}

	public String getBs() {
		return bs;
	}

	public void setBs(String bs) {
		this.bs = bs;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;

	}

	public javax.servlet.http.HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(javax.servlet.http.HttpServletResponse response) {
		this.response = response;
	}

	private IWebFactServices webFactSer;

	public IWebFactServices getWebFactSer() {
		return webFactSer;
	}

	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}

	public Webwlo getWlo() {
		return wlo;
	}

	public void setWlo(Webwlo wlo) {
		this.wlo = wlo;
	}

	public WebwloId getId() {
		return id;
	}

	public void setId(WebwloId id) {
		this.id = id;
	}

	public IWebwloServices getWloService() {
		return wloService;
	}

	public void setWloService(IWebwloServices wloService) {
		this.wloService = wloService;
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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public int getYz() {
		return yz;
	}

	public void setYz(int yz) {
		this.yz = yz;
	}

	public Page getPages() {
		return pages;
	}

	public void setPages(Page pages) {
		this.pages = pages;
	}

	/**
	 * 添加數據
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public String addwlo() throws ParseException, IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");// 生成年月格式
		Date d = null;
		StringBuffer ym = new StringBuffer();
		ym.append(year);
		ym.append(month);
		if (year == null && month == null) {
			wlo.getId().setYymm(format.parse(yymm));
		}
		Webwlo wo = wloService.selBycan(wlo.getId().getFactNo(), wlo.getId()
				.getYymm(), wlo.getId().getFactCode());
		if (wo != null && bs == null) {
			response.setContentType("text/html;charset=utf-8");
			String temp1 = wlo.getId().getFactNo();
			String temp2 = wlo.getId().getFactCode();
			String temp3 = format.format(wlo.getId().getYymm());
			response.getWriter()
					.print("<script>alert('數據庫已存在("
							+ temp1
							+ " "
							+ temp2
							+ " "
							+ temp3
							+ ")!');history.back()</script>");
			wlo = null;
			return null;
		} else {
			wloService.addWebWloDao(wlo);
			return "addwlo";
		}
	}

	/**
	 * 取得數據
	 * 
	 * @return
	 */
	public void list() {
		String factNos = null;
		if (type != null) {
			ActionContext.getContext().getSession().put("typeid", type);
		}
		pages.setPage(page);
		if (pages.getPage() > pages.getPageSize()) {
			pages.setPage(pages.getPageSize());
		}
		if (pages.getPage() == 0) {
			pages.setPage(1);
		}
		if (factNo != null && !factNo.equals("")) {
			factNos = factNo;
		} else {
			factNos = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		List<Webwlo> bList = wloService.selectWloDao(factNos, yymm,
				pages.getPage(), pages.getRows());
		if (bList.size() == 0) {
			pages.setPage(0);
		}
		ActionContext.getContext().getSession().put("wloList", bList);
		pages.setCount(wloService.totlePage(factNos, yymm));
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
	/*public String getList() {
		list();
		if (factNo != null && !factNo.equals("") || yz == 1) {
			return "showList1";
		} else {
			return "showList";
		}
	}*/

	/**
	 * 首頁顯示數據
	 */
	public String getList1() {
		list();
		return "list";
	}

	/**
	 * 根據ID查找
	 */
	public String findWloById() {
		List factCodes = webFactSer.findFactCodeByFactNo(id.getFactNo());
		ActionContext.getContext().getSession()
				.put("factAreas_other", factCodes);
		wlo = wloService.findById(id);
		return "finWloById";
	}

	public String delete2() {
		wloService.delete(id);
		return getList();
	}
	
	public String formatDouble(double d){
		DecimalFormat format=new DecimalFormat(",###.#");
		String str=format.format(d);
		return str;
	}
	public String getList(){
		ActionContext.getContext().getApplication().clear();
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=wloService.findPageBean(25, page, factNo, yymm);
		return "showList";
	}
	public String getList2(){
		ActionContext.getContext().getApplication().clear();
		if(factNo!=null&&!factNo.equals("")){
			ActionContext.getContext().getApplication().put("webwlo-factno", factNo);
		}
		if(yymm!=null&&!yymm.equals("")){
			ActionContext.getContext().getApplication().put("webwlo-yymm", yymm);
		}
		bean=wloService.findPageBean(25, page, factNo, yymm);
		return "showList1";
	}
	public String getList3(){
		factNo=(String)ActionContext.getContext().getApplication().get("webwlo-factno");
		yymm=(String)ActionContext.getContext().getApplication().get("webwlo-yymm");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=wloService.findPageBean(25, page, factNo, yymm);
		return "showList1";
	}

}
