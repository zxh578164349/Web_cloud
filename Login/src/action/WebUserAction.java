package action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONException;
import services.IKyFactServices;
import services.IKyVisaBillsServices;
import services.IWebFactServices;
import services.IWebJurisdictionService;
import services.IWebLogService;
import services.IWebMenuServices;
import services.IWebSubmenuService;
import services.IWebUserService;
import util.Json;
import util.PageBean;
import util.SessionListener;

import util.Page;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.KyFact;
import entity.KyVisabills;
import entity.WebJurisdiction;
import entity.WebLog;
import entity.WebMenu;
import entity.WebSubmenu;
import entity.WebUser;

public class WebUserAction extends ActionSupport implements ServletResponseAware {
	private IWebUserService webUserService;
	private String factError;
	private String factname;
	private IWebFactServices webFactSer;
	private String yzm;
	private PageBean bean;
	private int pageSize;
	private javax.servlet.http.HttpServletResponse response;
	private String userread;
	private String remembered;
	private IKyVisaBillsServices visabillSer;
	
	
	public void setVisabillSer(IKyVisaBillsServices visabillSer) {
		this.visabillSer = visabillSer;
	}

	public String getRemembered() {
		return remembered;
	}

	public void setRemembered(String remembered) {
		this.remembered = remembered;
	}

	public String getUserread() {
		return userread;
	}

	public void setUserread(String userread) {
		this.userread = userread;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public PageBean getBean() {
		return bean;
	}

	public void setBean(PageBean bean) {
		this.bean = bean;
	}

	public String getYzm() {
		return yzm;
	}

	public void setYzm(String yzm) {
		this.yzm = yzm;
	}

	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}

	public String getFactname() {
		return factname;
	}

	public void setFactname(String factname) {
		this.factname = factname;
	}

	public String getFactError() {
		return factError;
	}

	public void setFactError(String factError) {
		this.factError = factError;
	}

	public IWebUserService getWebUserService() {
		return webUserService;
	}

	public void setWebUserService(IWebUserService webUserService) {
		this.webUserService = webUserService;
	}

	private String factNo;
	private String tempName;

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	// 查詢條件
	private String conditions;

	public String getConditions() {
		return conditions;
	}

	public String getFactNo() {
		return factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	// 用户账号密码封装
	private WebUser webUsers;

	public WebUser getWebUsers() {
		return webUsers;
	}

	public void setWebUsers(WebUser webUsers) {
		this.webUsers = webUsers;
	}

	// 用戶修改對象封裝
	private WebUser updateU;

	public WebUser getUpdateU() {
		return updateU;
	}

	public void setUpdateU(WebUser updateU) {
		this.updateU = updateU;
	}

	// 验证码
	private String certCode;

	public String getCertCode() {
		return certCode;
	}

	public void setCertCode(String certCode) {
		this.certCode = certCode;
	}

	// 登陆日志对象service层
	private IWebLogService webLogService;

	public IWebLogService getWebLogService() {
		return webLogService;
	}

	public void setWebLogService(IWebLogService webLogService) {
		this.webLogService = webLogService;
	}

	// 分頁
	private Page pages;

	public Page getPages() {
		return pages;
	}

	public void setPages(Page pages) {
		this.pages = pages;
	}

	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	// 用戶名
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// 用戶Id
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// 是否可用
	private int available;

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	// 修改權限廠別
	private String fact;

	public String getFact() {
		return fact;
	}

	public void setFact(String fact) {
		this.fact = fact;
	}

	// 獲取用戶有哪些權限
	private String[] checkbox;

	public String[] getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(String[] checkbox) {
		this.checkbox = checkbox;
	}

	// 獲取廠別信息
	private IKyFactServices factSer;

	public IKyFactServices getFactSer() {
		return factSer;
	}

	public void setFactSer(IKyFactServices factSer) {
		this.factSer = factSer;
	}

	// 權限service
	private IWebJurisdictionService jurisdictionService;

	public IWebJurisdictionService getJurisdictionService() {
		return jurisdictionService;
	}

	public void setJurisdictionService(
			IWebJurisdictionService jurisdictionService) {
		this.jurisdictionService = jurisdictionService;
	}

	// 子菜單service
	private IWebSubmenuService submenuService;

	public IWebSubmenuService getSubmenuService() {
		return submenuService;
	}

	public void setSubmenuService(IWebSubmenuService submenuService) {
		this.submenuService = submenuService;
	}

	// 菜單service

	private IWebMenuServices menuSer;

	public IWebMenuServices getMenuSer() {
		return menuSer;
	}

	public void setMenuSer(IWebMenuServices menuSer) {
		this.menuSer = menuSer;
	}

	/**
	 * 用户登录
	 * 
	 * @return
	 */
	/*int j = 0;
	@SuppressWarnings("unused")
	Map<String, Integer> loginCount = new HashMap<String, Integer>();
	int g = 0;
	List<WebUser> userCount = new ArrayList<WebUser>();*/

	public String login() throws InterruptedException, IOException {
		ActionContext.getContext().getSession().remove("Email");//清除函文郵件中Email(在KyVisaBillmAction中的findById_email方法)
		DateFormat format=new SimpleDateFormat("yyyyMMdd");
		String resultIndex = "chengong";
		List userList = webUserService.findMoreUser(webUsers.getUsername().trim());					
		WebUser wUser = webUserService.selByuserId(factNo, webUsers.getUsername().trim());
		//factname = webFactSer.selByid(factNo);
		/*用戶名,密碼,廠別都正確*/
		if (wUser != null) {			
			if (wUser.getPwd().equals(webUsers.getPwd().trim())) {//start if				
					try {												 
							  String ipAddress = null; ipAddress =ServletActionContext.getRequest().getHeader("x-forwarded-for");									  
						      if(ipAddress == null || ipAddress.length() == 0 ||"unknown".equalsIgnoreCase(ipAddress)){ 									  
						          ipAddress =ServletActionContext.getRequest().getHeader("Proxy-Client-IP");									  
						         }
						      if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {									 
						          ipAddress =ServletActionContext.getRequest().getHeader("WL-Proxy-Client-IP");								  
						         } 
						      if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {									 
						          ipAddress =ServletActionContext.getRequest().getRemoteAddr(); 
						          if(ipAddress.equals("127.0.0.1")){
						             InetAddress inet =null;
						             try { 
						            	 inet = InetAddress.getLocalHost(); 
						             }catch (Exception e) { 
						            	 e.printStackTrace(); 
						            	 }
						              ipAddress = inet.getHostAddress();
						           } 
						        } 
						      if (ipAddress!= null && ipAddress.length() > 15) {
						           if (ipAddress.indexOf(",") > 0) { 
						        	   ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));						 
						                  }  
						      }
						  ActionContext.getContext().getSession() .put("ip",ipAddress);
						  WebLog log =new WebLog();

							  log=new WebLog();
							  Date date =new Date();
							  log.setIp(ipAddress);
							  log.setLogtime(date);
							  log.setUsername(wUser.getUsername());
							  log.setFactno(wUser.getFactno());					  						  
						      webLogService.saveWebLog(log);
						      
						      //更新用戶登錄時間，如果一天登錄多次就不更新
						      if(wUser.getLogdate()==null||!wUser.getLogdate().equals(format.format(new Date()))){
						    	  wUser.setLogdate(format.format(new Date()));
							      webUserService.add(wUser);
						      }						      						  
						  
						  /*設定cookie
						   * 设定有效时间 以秒(s)为单位
						   * 设置Cookie路径和域名
						   * */
					if(remembered!=null){
						Cookie cookieuser= new Cookie("user",factNo+","+wUser.getUsername());													
						cookieuser.setMaxAge(60*60*24*7);//一周限期					 
						//cookieuser.setPath("/");
						ServletActionContext.getResponse().addCookie(cookieuser);
					}	  
						
						ActionContext.getContext().getSession().put("loginUser", wUser);																		
						if (factNo.equals("tw")&& wUser.getUsername().equals("admin")) {								
							ActionContext.getContext().getSession().put("factNo", factNo);									
						} else {
							factNo = wUser.getFactno();
							ActionContext.getContext().getSession().put("factNo", factNo);									
							String factName = webFactSer.selByid(factNo);
							ActionContext.getContext().getSession().put("factName", factName);									
							List factCodes = webFactSer.findFactCodeByFactNo_show(factNo);									
							ActionContext.getContext().getSession().put("factAreas_login", factCodes);	//【各廠產量統計】加載的廠別狀態								
						}
						
						/*ActionContext.getContext().getApplication().clear();
						List<KyVisabills>bills=visabillSer.findByFNN(factNo,wUser.getName());
						if(bills.size()>0){
							ActionContext.getContext().getApplication().put("bills_temp", bills);
						}*/
																								
					//如果用戶不可用，也就是available的值為1
					if(wUser.getAvailable()==1){
						response.setContentType("text/html;charset=utf-8");
						response.getWriter().print("<script>alert('當前用戶已註銷!');history.back()</script>");
						return null;
					}						
					} catch (Exception e) {
						e.printStackTrace();
					}					
					return resultIndex;
				}//end if			
			}
		/*用戶名正確,但廠別不正確,*/
		if (wUser == null && userList.size() > 0) {
			for (int i = 0; i < userList.size(); i++) {
				WebUser u = (WebUser) userList.get(i);
				if (!factNo.equals(u.getFactno())) {
					factError = "(當前用戶不屬於該廠別)";
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().print("<script>alert('對不起,廠別不正確!');history.back()</script>");
					break;									
				}
			}
			return null;
		}
				
		/*用戶名或者密碼錯誤*/		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print("<script>alert('用戶或密碼錯誤!');history.back()</script>");				
		return null;
	}
		
	/**
	 * 修改用戶
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String updateUesr() throws IOException {
		boolean yz = webUserService.updateUser(updateU);
		if (yz == true) {
			//return "updateSuccess";
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('修改成功！');history.back()</script>");
			return null;
		} else {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('修改失敗！');history.back()</script>");
			return null;
			//return NONE;
		}
	}

	/**
	 * 把用戶信息加載在文本框中
	 * 
	 * @return
	 */
	public String initialUpdate() {
		WebUser wU = (WebUser) ActionContext.getContext().getSession()
				.get("loginUser");
		WebUser webUser = null;
		if (id != 0) {
			webUser = webUserService.selByuserId(id);
		} else {
			webUser = webUserService.selByuserId(wU.getId());
		}
		ActionContext.getContext().getSession().put("User", webUser);
		ServletActionContext.getRequest().setAttribute("webU", webUser);
		id = 0;
		return "initial";
	}

	/**
	 * 恢復用戶信息
	 * 
	 * @return
	 */
	public String recoveryData() {
		WebUser webUser = (WebUser) ActionContext.getContext().getSession()
				.get("User");
		boolean yz = webUserService.updateUser(webUser);
		if (yz == true) {
			webUsers = webUserService.selByuserId(webUser.getId());
			ServletActionContext.getRequest().setAttribute("webU", webUsers);
			return "initial";
		} else {
			return null;
		}
	}

	/**
	 * 查詢所有用戶
	 */

	public String getuser() {
		pages.setPage(page);
		if (pages.getPage() > pages.getPageSize()) {
			pages.setPage(pages.getPageSize());
		}
		if (pages.getPage() == 1 || pages.getPage() < 1) {
			pages.setPage(1);
		}
		String fact = (String) ActionContext.getContext().getSession()
				.get("factNo");
		List<WebUser> users = webUserService.getUsers(pages.getPage(),
				pages.getRows(), conditions, fact);
		ActionContext.getContext().getSession().put("allUser", users);
		pages.setCount(webUserService.totlePage(conditions));
		if (pages.getCount() % pages.getRows() == 0) {
			pages.setPageSize(pages.getCount() / pages.getRows());
		} else {
			pages.setPageSize(pages.getCount() / pages.getRows() + 1);
		}
		return "all";
	}

	/**
	 * 查看用戶權限
	 */
	public String jurisdiction() {
		WebUser webUser = webUserService.selByuserId(id, fact);
		ActionContext.getContext().getSession().put("user", webUser);
		return "qx";
	}

	/**
	 * 修改用戶權限
	 * WebUser（用戶）包含WebJurisdiction（權限），
	 * WebJurisdiction（權限）又包含WebSubment（子菜單）
	 * @throws IOException
	 * @throws JSONException
	 */
	public String updateJurisdiction() {
		WebUser user = (WebUser) ActionContext.getContext().getSession().get("user");		
		if(userread!=null&&!userread.equals("")){
			user.setUserread(userread);
		}else{
			user.setUserread("0");
		}
		webUserService.add(user);
		
		//（1）在更新時，先查找出舊的權限
		List<WebJurisdiction> list = jurisdictionService.selSub(user.getId());
		//（2）開始更新權限
		List<String> name = new ArrayList<String>();
		List<WebSubmenu> KPIList = new ArrayList<WebSubmenu>();
		List<WebSubmenu> yhglList = new ArrayList<WebSubmenu>();
		List<WebSubmenu> grszist = new ArrayList<WebSubmenu>();
		List<WebSubmenu> sjglList = new ArrayList<WebSubmenu>();
		List<WebSubmenu> zlsr = new ArrayList<WebSubmenu>();
		for (int j = 0; j < checkbox.length; j++) {
			String[] info = checkbox[j].split(",");
			if (!name.contains(info[0])) {
				name.add(info[0]);
			}
			if (info[0].equals("KPI數據")) {
				WebSubmenu submenu = new WebSubmenu();
				submenu.setAddress(info[2]);
				submenu.setSubmenuname(info[1]);
				submenu.setSubtype("A0" + j);
				KPIList.add(submenu);
			}
			if (info[0].equals("用戶管理")) {
				WebSubmenu submenu = new WebSubmenu();
				submenu.setAddress(info[2]);
				submenu.setSubmenuname(info[1]);
				submenu.setSubtype("A0" + j);
				yhglList.add(submenu);
			}
			if (info[0].equals("數據管理")) {
				WebSubmenu submenu = new WebSubmenu();
				submenu.setAddress(info[2]);
				submenu.setSubmenuname(info[1]);
				submenu.setSubtype("A0" + j);
				sjglList.add(submenu);
			}
			if (info[0].equals("個人設置")) {
				WebSubmenu submenu = new WebSubmenu();
				submenu.setAddress(info[2]);
				submenu.setSubmenuname(info[1]);
				submenu.setSubtype("A0" + j);
				grszist.add(submenu);
			}
			if (info[0].equals("資料輸入")) {
				WebSubmenu submenu = new WebSubmenu();
				submenu.setAddress(info[2]);
				submenu.setSubmenuname(info[1]);
				submenu.setSubtype("A0" + j);
				zlsr.add(submenu);
			}
		}
		name.add("退出管理");
		Map<String, List<WebSubmenu>> su = new HashMap<String, List<WebSubmenu>>();
		su.put("KPI數據", KPIList);
		su.put("用戶管理", yhglList);
		su.put("數據管理", sjglList);
		su.put("個人設置", grszist);
		su.put("資料輸入", zlsr);
		for (int g = 0; g < name.size(); g++) {//start for
			WebMenu menu = menuSer.selByname(name.get(g));
			WebJurisdiction jurisdiction = new WebJurisdiction();
			jurisdiction.setWebMenu(menu);
			jurisdiction.setWebUser(user);
			jurisdiction.setWebSubmenus(su.get(name.get(g)));
			boolean s = jurisdictionService.add(jurisdiction);
			if (s == true) {
				//WebJurisdiction jurisdiction2 = jurisdictionService.selBymenuName(name.get(g), user.getId());						
				WebJurisdiction jurisdiction2=jurisdictionService.findById(jurisdiction.getJurisdictionid());
				if (jurisdiction2.getWebMenu().getMenuname().equals("KPI數據")) {
					for (int i = 0; i < KPIList.size(); i++) {
						WebSubmenu submenu = new WebSubmenu();
						submenu.setWebJurisdiction(jurisdiction2);
						submenu.setSubmenuname(KPIList.get(i).getSubmenuname());
						submenu.setAddress(KPIList.get(i).getAddress());
						submenu.setSubtype(KPIList.get(i).getSubtype());
						submenuService.addSubmenu(submenu);
					}
				}
				if (jurisdiction2.getWebMenu().getMenuname().equals("用戶管理")) {
					for (int i = 0; i < yhglList.size(); i++) {
						WebSubmenu submenu = new WebSubmenu();
						submenu.setWebJurisdiction(jurisdiction2);
						submenu.setSubmenuname(yhglList.get(i).getSubmenuname());
						submenu.setAddress(yhglList.get(i).getAddress());
						submenu.setSubtype(yhglList.get(i).getSubtype());
						submenuService.addSubmenu(submenu);
					}
				}
				if (jurisdiction2.getWebMenu().getMenuname().equals("數據管理")) {
					for (int i = 0; i < sjglList.size(); i++) {
						WebSubmenu submenu = new WebSubmenu();
						submenu.setWebJurisdiction(jurisdiction2);
						submenu.setSubmenuname(sjglList.get(i).getSubmenuname());
						submenu.setAddress(sjglList.get(i).getAddress());
						submenu.setSubtype(sjglList.get(i).getSubtype());
						submenuService.addSubmenu(submenu);
					}
				}
				if (jurisdiction2.getWebMenu().getMenuname().equals("個人設置")) {
					for (int i = 0; i < grszist.size(); i++) {
						WebSubmenu submenu = new WebSubmenu();
						submenu.setWebJurisdiction(jurisdiction2);
						submenu.setSubmenuname(grszist.get(i).getSubmenuname());
						submenu.setAddress(grszist.get(i).getAddress());
						submenu.setSubtype(grszist.get(i).getSubtype());
						submenuService.addSubmenu(submenu);
					}
				}
				if (jurisdiction2.getWebMenu().getMenuname().equals("資料輸入")) {
					for (int i = 0; i < zlsr.size(); i++) {
						WebSubmenu submenu = new WebSubmenu();
						submenu.setWebJurisdiction(jurisdiction2);
						submenu.setSubmenuname(zlsr.get(i).getSubmenuname());
						submenu.setAddress(zlsr.get(i).getAddress());
						submenu.setSubtype(zlsr.get(i).getSubtype());
						submenuService.addSubmenu(submenu);
					}
				}
			}
		}//end for
		
		//（3）放在最後刪除舊的權限
		for (int i = 0; i < list.size(); i++) {			
			jurisdictionService.delJur(list.get(i));
		}
		
		/*Json json = new Json();
		try {
			json.writeJson("修改權限成功");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return "updateJurisdiction";
	}

	/**
	 * 設置賬戶是否可用
	 */
	public String updateKy() {
		boolean y = webUserService.updateKy(id, available);
		if (y == true) {
			//getuser();
			this.findPageBean();
			return "all";
		} else {
			return null;
		}
	}

	/**
	 * 返回到index.jsp;
	 * 
	 * @return
	 */
	public String findMoreusers() {
		List list = webUserService.findMoreUser(tempName);
		ActionContext.getContext().getSession().put("", list);
		return "findMoreusers";
	}

	/**
	 * 返回到login.jsp;
	 * 
	 * @return
	 */
	public String findMoreusers2() {
		List list = webUserService.findMoreUser(tempName);
		ActionContext.getContext().getSession().put("", list);
		return "findMoreusers2";
	}
	
	public String findPageBean(){
		ActionContext.getContext().getApplication().clear();
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=webUserService.findPageBean_init(25, page, conditions, factNo);
		return "beanList";
	}
	public String findPageBean2(){
		ActionContext.getContext().getApplication().clear();
		//factNo=(String)ActionContext.getContext().getSession().get("factNo");
		if(conditions!=null&&!conditions.equals("")){
			ActionContext.getContext().getApplication().put("webuser_conditions", conditions);
		}
		if(factNo!=null&&!factNo.equals("")){
			ActionContext.getContext().getApplication().put("webuser_factno", factNo);
		}
		bean=webUserService.findPageBean(25, page, conditions, factNo);
		return "beanList1";
	}
	public String findPageBean3(){
		conditions=(String)ActionContext.getContext().getApplication().get("webuser_conditions");
		factNo=(String)ActionContext.getContext().getApplication().get("webuser_factno");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
			bean=webUserService.findPageBean_init(25, page, conditions, factNo);
		}else{
			bean=webUserService.findPageBean(25, page, conditions, factNo);
		}		
		return "beanList1";
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	/**
	 * WebUser（用戶）包含WebJurisdiction（權限），WebJurisdiction（權限）又包含WebSubment（子菜單）
	 * 級聯刪除
	 * @return
	 */
	public String delete(){
		webUserService.delete(id);
		return "delete";
	}
	public String add(){
		webUserService.add(webUsers);
		return "add";
	}
	
	/**
	 * 導出用戶的權限
	 * @throws IOException
	 */
	public void printJurs() throws IOException{
		XSSFWorkbook book=new XSSFWorkbook();
		XSSFSheet sheet=book.createSheet("Jurs");
		
		//標題樣式
		XSSFCellStyle cs_title=book.createCellStyle();
		cs_title.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_title.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		XSSFFont font_title=book.createFont();
		font_title.setFontHeightInPoints((short)20);
		font_title.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		cs_title.setFont(font_title);
		//表頭樣式
		XSSFCellStyle cs_column=book.createCellStyle();
		cs_column.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_column.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_column.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_column.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_column.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_column.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		XSSFFont font_column=book.createFont();
		font_column.setFontHeightInPoints((short)20);
		font_column.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		cs_column.setFont(font_column);
		cs_column.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		cs_column.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		//序號列樣式
		XSSFCellStyle cs_num=book.createCellStyle();
		cs_num.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_num.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_num.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_num.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_num.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_num.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_num.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		cs_num.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		//基本樣式
		XSSFCellStyle cs=book.createCellStyle();
		cs.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		
		//數據
		String[]objs={"序號","廠別","帳號","姓名","主菜單","子菜單"};
		factNo="632";
		List<WebSubmenu>list_sub=submenuService.findByFactno(factNo);
		
		//初始化
		for(int i=0;i<list_sub.size()+2;i++){
			sheet.createRow(i);
			for(int j=0;j<objs.length;j++){
				sheet.getRow(i).createCell(j).setCellStyle(cs);
			}
		}
		/**
		 * 開始打印
		 */
		//(標題)
		CellRangeAddress cra=new CellRangeAddress(0,(short)0,0,(short)objs.length-1);
		sheet.addMergedRegion(cra);
		sheet.getRow(0).getCell(0).setCellValue("大標題");
		for(int i=0;i<objs.length;i++){
			sheet.getRow(0).getCell(i).setCellStyle(cs_title);
		}
		//表頭
		for(int i=0;i<objs.length;i++){
			sheet.setColumnWidth(i, 4000);
			sheet.getRow(1).getCell(i).setCellValue(objs[i]);
			sheet.getRow(1).getCell(i).setCellStyle(cs_column);
		}
		//序號列
		for(int i=0;i<list_sub.size();i++){
			sheet.getRow(2+i).getCell(0).setCellValue(i+1);
			sheet.getRow(2+i).getCell(0).setCellStyle(cs_num);
		}
		//數據
		for(int i=0;i<list_sub.size();i++){
			WebSubmenu sub=list_sub.get(i);
			sheet.getRow(2+i).getCell(1).setCellValue(sub.getWebJurisdiction().getWebUser().getFactno());
			sheet.getRow(2+i).getCell(2).setCellValue(sub.getWebJurisdiction().getWebUser().getUsername());
			sheet.getRow(2+i).getCell(3).setCellValue(sub.getWebJurisdiction().getWebUser().getName());
			sheet.getRow(2+i).getCell(4).setCellValue(sub.getWebJurisdiction().getWebMenu().getMenuname());
			sheet.getRow(2+i).getCell(5).setCellValue(sub.getSubmenuname());
		}
				
		ServletOutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		String fileName="jurs.xlsx";
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");
		if(msie>0){
			fileName=java.net.URLEncoder.encode(fileName,"utf-8");
		}else{
			fileName=new String(fileName.getBytes("utf-8"),"iso8859-1");
		}
		response.setHeader("Content-disposition","attachment;filename="+fileName);
		book.write(os);
		os.close();
		
		
	}
	//不能刪除，用於搭配jetty容器使用
	public String judge(){
		return "judge";
	};
	
}
