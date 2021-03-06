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
import services.IWebScraptServices;
import util.GlobalMethod;
import util.Page;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmLog;
import entity.WebUser;
import entity.Webscrapt;
import entity.WebscraptId;

public class WebScraptAction extends ActionSupport implements
		ServletResponseAware {
	private Webscrapt scrapt;
	private WebscraptId id;
	private IWebScraptServices scraptService;
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
	private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗
	private String yymm2;
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑

	
	public int getBackIndex() {
		return backIndex;
	}

	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}

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

	public Webscrapt getScrapt() {
		return scrapt;
	}

	public void setScrapt(Webscrapt scrapt) {
		this.scrapt = scrapt;
	}

	public WebscraptId getId() {
		return id;
	}

	public void setId(WebscraptId id) {
		this.id = id;
	}

	public IWebScraptServices getScraptService() {
		return scraptService;
	}

	public void setScraptService(IWebScraptServices scraptService) {
		this.scraptService = scraptService;
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
	 * �K�[�ƾ�
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public String addscrapt() throws ParseException, IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");// �ͦ��~��榡
		StringBuffer ym = new StringBuffer();
		String result=null;
		ym.append(year);
		ym.append(month);
		try{
			if (year == null && month == null) {
				scrapt.getId().setYymm(format.parse(yymm));
			}
			Webscrapt scr = scraptService.selBycan(scrapt.getId().getFactNo(),scrapt.getId().getYymm(), scrapt.getId().getFactCode());				
			if (scr != null && bs == null) {
				response.setContentType("text/html;charset=utf-8");		
				response.getWriter()
						.print("<script>alert('數據庫已經存在("
								+ scrapt.getId().getFactNo()
								+ " "
								+ scrapt.getId().getFactCode()
								+ " "
								+ format.format(scrapt.getId().getYymm())
								+ ")!');history.back()</script>");
			} else {
				scraptService.addWebScraptDao(scrapt);
				result="addscrapt";
				ajaxResult="0";
			}
		}catch(Exception e){
			e.printStackTrace();
			result="addscrapt";
			ajaxResult="1";
		}		
		return result;
	}

	/**
	 * ��o�ƾ�
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
		List<Webscrapt> bList = scraptService.selectScrapt(factNos, yymm,
				pages.getPage(), pages.getRows());
		if (bList.size() == 0) {
			pages.setPage(0);
		}
		ActionContext.getContext().getSession().put("scraptList", bList);
		pages.setCount(scraptService.totlePage(factNos, yymm));
		if (pages.getCount() % pages.getRows() == 0) {
			pages.setPageSize(pages.getCount() / pages.getRows());
		} else {
			pages.setPageSize(pages.getCount() / pages.getRows() + 1);
		}
	}

	/**
	 * ������ܼƾ�
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
	 * ������ܼƾ�
	 */
	public String getList1() {
		list();
		return "list";
	}

	/**
	 * �ھ�ID�d��
	 */
	public String findScraptById() {
		List factCodes = webFactSer.findFactCodeByFactNo(id.getFactNo());
		ActionContext.getContext().getSession()
				.put("factAreas_other", factCodes);
		scrapt = scraptService.findById(id);
		return "findScraptById";
	}

	public String delete2() {
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setObj("Webscrapt");
		log.setFactCode(id.getFactCode());
		log.setFactNo(id.getFactNo());
		log.setYymm(new SimpleDateFormat("yyyyMM").format(id.getYymm()));
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
		scraptService.delete(id,log);
		return "delete";
	}
	
	public String formatDouble(double d){
		DecimalFormat format=new DecimalFormat(",###.#");
		String str=format.format(d);
		return str;
	}
	public String getList(){
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_yymm");
		ActionContext.getContext().getSession().remove("public_yymm2");
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=scraptService.findPageBean(20,page, factNo, yymm,yymm2);
		return "showList";
	}
	public String getList2(){
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_yymm");
		ActionContext.getContext().getSession().remove("public_yymm2");
		if(factNo!=null&&!factNo.equals("")){
			ActionContext.getContext().getSession().put("public_factno", factNo);
		}
		if(yymm!=null&&!yymm.equals("")){
			ActionContext.getContext().getSession().put("public_yymm", yymm);
		}
		if(yymm2!=null&&!yymm2.equals("")){
			ActionContext.getContext().getSession().put("public_yymm2", yymm2);
		}
		bean=scraptService.findPageBean(20,page, factNo, yymm,yymm2);
		return "showList1";
	}
	public String getList3(){
		String result="showList1";
		if(backIndex==1){
			result="showList";
		}
		factNo=(String)ActionContext.getContext().getSession().get("public_factno");
		yymm=(String)ActionContext.getContext().getSession().get("public_yymm");
		yymm2=(String)ActionContext.getContext().getSession().get("public_yymm2");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=scraptService.findPageBean(20,page, factNo, yymm,yymm2);
		return result;
	}
	/**
	 * 打印文檔
	 * @throws IOException 
	 */
	public void print() throws IOException{
		List<Webscrapt>list=scraptService.findByAny(factNo, yymm, yymm2);
		GlobalMethod.print(list, factNo, yymm, yymm2, "webscrapt .jasper", response);
	}

}
