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

import services.IWebFactServices;
import services.IWebProdutedServices;
import util.Page;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Webproduted;
import entity.WebprodutedId;
import entity.Webscrapt;

public class WebProdutedAction extends ActionSupport implements
		ServletResponseAware {
	private Webproduted produted;
	private WebprodutedId id;
	private IWebProdutedServices produtedService;
	private javax.servlet.http.HttpServletResponse response;
	private String factNo;
	private String yymm;
	private int page;
	private String type;
	private String year;
	private String month;
	private int yz;
	private Page pages;
	private IWebFactServices webFactSer;
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

	public javax.servlet.http.HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(javax.servlet.http.HttpServletResponse response) {
		this.response = response;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;

	}

	public IWebFactServices getWebFactSer() {
		return webFactSer;
	}

	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}

	public Webproduted getProduted() {
		return produted;
	}

	public void setProduted(Webproduted produted) {
		this.produted = produted;
	}

	public WebprodutedId getId() {
		return id;
	}

	public void setId(WebprodutedId id) {
		this.id = id;
	}

	public IWebProdutedServices getProdutedService() {
		return produtedService;
	}

	public void setProdutedService(IWebProdutedServices produtedService) {
		this.produtedService = produtedService;
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
	public String addProduted() throws ParseException, IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");// 生成年月格式
		Date d = null;
		StringBuffer ym = new StringBuffer();
		ym.append(year);
		ym.append(month);
		if (year == null && month == null) {
			produted.getId().setYymm(format.parse(yymm));
		}
		Webproduted pro = produtedService.selBycan(
				produted.getId().getFactNo(), produted.getId().getYymm(),
				produted.getId().getFactCode());
		if (pro != null && bs == null) {
			response.setContentType("text/html;charset=utf-8");
			String temp1 = produted.getId().getFactNo();
			String temp2 = produted.getId().getFactCode();
			String temp3 = format.format(produted.getId().getYymm());
			response.getWriter()
					.print("<script>alert('數據庫已存在("
							+ temp1
							+ " "
							+ temp2
							+ " "
							+ temp3
							+ ")!');history.back()</script>");
			produted = null;
			return null;
		} else {
			produtedService.addWebProdutedDao(produted);
			return "addproduted";
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
		List<Webproduted> bList = produtedService.selectProduted(factNos, yymm,
				pages.getPage(), pages.getRows());
		if (bList.size() == 0) {
			pages.setPage(0);
		}
		ActionContext.getContext().getSession().put("produtedList", bList);
		pages.setCount(produtedService.totlePage(factNos, yymm));
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
	public String findProdutedById() {
		List factCodes = webFactSer.findFactCodeByFactNo(id.getFactNo());
		ActionContext.getContext().getSession()
				.put("factAreas_other", factCodes);
		produted = produtedService.findById(id);
		return "findProdutedById";
	}

	public String delete2() {
		produtedService.delete(id);
		return getList();
	}
	
	public String formatDouble(Double s){
		DecimalFormat format=new DecimalFormat(",###.#");
		String str=format.format(s);
		return str;
		
	}
	public String getList(){
		ActionContext.getContext().getApplication().clear();
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=produtedService.findPageBean(25, page, factNo, yymm);
		return "showList";
	}
	public String getList2(){
		ActionContext.getContext().getApplication().clear();
		if(factNo!=null&&!factNo.equals("")){
			ActionContext.getContext().getApplication().put("produted-factno", factNo);
		}
		if(yymm!=null&&!yymm.equals("")){
			ActionContext.getContext().getApplication().put("produted-yymm", yymm);
		}
		bean=produtedService.findPageBean(25, page, factNo, yymm);
		return "showList1";
	}
	public String getList3(){
		factNo=(String)ActionContext.getContext().getApplication().get("produted-factno");
		yymm=(String)ActionContext.getContext().getApplication().get("produted-yymm");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=produtedService.findPageBean(25, page, factNo, yymm);
		return "showList1";
	}

}
