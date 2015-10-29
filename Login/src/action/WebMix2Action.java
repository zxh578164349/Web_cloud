package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import services.IWebFactServices;
import services.IWebMix2Services;
import util.Page;
import util.PageBean;
import entity.Webmix2;
import entity.Webmix2Id;
import entity.Webmixperson;

public class WebMix2Action extends ActionSupport implements
		ServletResponseAware {
	private Webmix2 mix2;
	private Webmix2Id id;
	private IWebMix2Services mix2Service;
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

	public IWebFactServices getWebFactSer() {
		return webFactSer;
	}

	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}

	public Webmix2 getMix2() {
		return mix2;
	}

	public void setMix2(Webmix2 mix2) {
		this.mix2 = mix2;
	}

	public Webmix2Id getId() {
		return id;
	}

	public void setId(Webmix2Id id) {
		this.id = id;
	}

	public IWebMix2Services getMix2Service() {
		return mix2Service;
	}

	public void setMix2Service(IWebMix2Services mix2Service) {
		this.mix2Service = mix2Service;
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
	public String addMix2() throws ParseException, IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");// 生成年月格式
		Date d = null;
		StringBuffer ym = new StringBuffer();
		ym.append(year);
		ym.append(month);
		if (year == null && month == null) {
			mix2.getId().setYymm(format.parse(yymm));
		}
		Webmix2 mix2s = mix2Service.selBycan(mix2.getId().getFactNo(), mix2
				.getId().getYymm(), mix2.getId().getFactCode());
		if (mix2s != null && bs == null) {
			response.setContentType("text/html;charset=utf-8");
			String temp1 = mix2.getId().getFactNo();
			String temp2 = mix2.getId().getFactCode();
			String temp3 = format.format(mix2.getId().getYymm());
			response.getWriter()
					.print("<script>alert('數據庫已存在("
							+ temp1
							+ " "
							+ temp2
							+ " "
							+ temp3
							+ ")!');history.back()</script>");
			mix2 = null;
			return null;
		} else {
			mix2Service.addWebMix2(mix2);
			return "addMix2";
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
		List<Webmix2> bList = mix2Service.selectMix2(factNos, yymm,
				pages.getPage(), pages.getRows());
		if (bList.size() == 0) {
			pages.setPage(0);
		}
		ActionContext.getContext().getSession().put("mix2List", bList);
		pages.setCount(mix2Service.totlePage(factNos, yymm));
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
	public String findMix2ById() {
		List factCodes = webFactSer.findFactCodeByFactNo(id.getFactNo());
		ActionContext.getContext().getSession()
				.put("factAreas_other", factCodes);
		mix2 = mix2Service.findById(id);
		return "findmix2ById";
	}

	public String delete2() {
		mix2Service.delete(id);
		return getList();
	}
	
	public String formatDouble(Double s){
		DecimalFormat format=new DecimalFormat(",###.##");
		String temp=format.format(s);
		return temp;
	}
	public String getList(){
		ActionContext.getContext().getApplication().clear();
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=mix2Service.findPageBean(25, page, factNo, yymm);
		return "showList";
	}
	public String getList2(){
		ActionContext.getContext().getApplication().clear();
		if(factNo!=null&&!factNo.equals("")){
			ActionContext.getContext().getApplication().put("webmix2-factno", factNo);
		}
		if(yymm!=null&&!yymm.equals("")){
			ActionContext.getContext().getApplication().put("webmix2-yymm", yymm);
		}
		bean=mix2Service.findPageBean(25, page, factNo, yymm);
		return "showList1";
	}
	public String getList3(){
		factNo=(String)ActionContext.getContext().getApplication().get("webmix2-factno");
		yymm=(String)ActionContext.getContext().getApplication().get("webmix2-yymm");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=mix2Service.findPageBean(25, page, factNo, yymm);
		return "showList1";
	}
}
