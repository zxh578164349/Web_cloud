package action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import mail.MailSenderInfo;
import mail.SimpleMailSender;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import services.IKyFactServices;
import services.IKyVisaBillsServices;
import services.IWebFactServices;
import services.IWebJurisdictionService;
import services.IWebLogService;
import services.IWebMenuServices;
import services.IWebSubmenuService;
import services.IWebTypeServices;
import services.IWebUserService;
import util.GlobalMethod;
import util.PageBean;
import util.Page;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.KyFact;
import entity.KyVisabills;
import entity.KyzExpectmatmLog;
import entity.WebFact;
import entity.WebJurisdiction;
import entity.WebLog;
import entity.WebMenu;
import entity.WebOperationToUser;
import entity.WebSubmenu;
import entity.WebSubmenu2;
import entity.WebType;
import entity.WebUser;
import entity.WebUserOperation;
import entity.custom.ProjectConfig;

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：WebUserAction   
* 類描述：管理用戶
* 創建人：KY2
 */
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
	private String ajax_result;//返回頁面的ajax結果   (0:登錄成功    1:當前用戶已註銷   2:廠別不對    3:賬號或密碼錯誤)
	private IWebTypeServices webtypeSer;
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	private String userType;//用戶類型  0：使用者     1：訪客
	private JSONArray jsons;

	private List<Integer>checkbox2;
	
	
	
	public List<Integer> getCheckbox2(){
		return checkbox2;
	}

	public void setCheckbox2(List<Integer> checkbox2){
		this.checkbox2=checkbox2;
	}

	public JSONArray getJsons(){
		return jsons;
	}

	public void setJsons(JSONArray jsons){
		this.jsons=jsons;
	}

	public String getUserType(){
		return userType;
	}

	public void setUserType(String userType){
		this.userType=userType;
	}

	public int getBackIndex() {
		return backIndex;
	}

	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}

	public void setWebtypeSer(IWebTypeServices webtypeSer) {
		this.webtypeSer = webtypeSer;
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
	private String[] checkbox={};

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
	
	

	public String getAjax_result() {
		return ajax_result;
	}

	public void setAjax_result(String ajax_result) {
		this.ajax_result = ajax_result;
	}

	/**
	 * 用户登录
	 * 
	 * @return
	 */	
	public String login() throws InterruptedException, IOException {
		ActionContext.getContext().getSession().remove("Email");//清除函文郵件中Email(在KyVisaBillmAction中的findById_email方法)
		DateFormat format=new SimpleDateFormat("yyyyMMdd");			
		List userList = webUserService.findMoreUser(webUsers.getUsername().trim());					
		WebUser wUser = webUserService.selByuserId(factNo, webUsers.getUsername().trim());
		/*用戶名,密碼,廠別都正確*/
		if (wUser != null) {//if
			if (wUser.getPwd().equals(webUsers.getPwd().trim())) {//start if2
					try {
						ajax_result="0";							  
						  String ipAddress=GlobalMethod.findIp();
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
						Cookie cookieuser= new Cookie("user",factNo+","+wUser.getUsername()+","+wUser.getPwd());													
						cookieuser.setMaxAge(60*60*24*7);//一周限期					 
						//cookieuser.setPath("/");
						ServletActionContext.getResponse().addCookie(cookieuser);
					}
					/*菜单名统一按名字进行排序*/
					Collections.sort(wUser.getWebJurisdictions(), new Comparator<WebJurisdiction>() {
			            public int compare(WebJurisdiction arg0, WebJurisdiction arg1) {
			                return arg0.getWebMenu().getMenuname().compareTo(arg1.getWebMenu().getMenuname());
			            }
			        });
						
						ActionContext.getContext().getSession().put("loginUser", wUser);
						List<WebMenu>login_menus=menuSer.findAllMenu("0");//使用者類型的菜單目錄						
						ActionContext.getContext().getSession().put("login_menus", login_menus);//登錄時保存所有的菜單項目
						
						List<WebMenu>login_menus_all=menuSer.findAllMenu(null);//全部的菜單目錄  20161216
						ActionContext.getContext().getSession().put("login_menus_all", login_menus_all);//20161216保存爲session,用於修改用戶權限時，要顯示用戶者與訪客的全部菜單
						
						if (factNo.equals("tw")&& wUser.getUsername().equals("admin")) {								
							ActionContext.getContext().getSession().put("factNo", factNo);									
						} else {
							factNo = wUser.getFactno();
							ActionContext.getContext().getSession().put("factNo", factNo);									
							String factName = webFactSer.selByid(factNo);
							ActionContext.getContext().getSession().put("factName", factName);									
							List factCodes = webFactSer.findFactCodeByFactNo_show_dw(factNo);									
							ActionContext.getContext().getSession().put("factAreas_login", factCodes);	//【各廠產量統計】加載的廠別狀態								
						}
						/******************緩存用戶所屬的廠別信息20160125******************/
						List<Object[]>list_fact=webFactSer.findFactByFactNo(factNo);
						ActionContext.getContext().getSession().put("login_facts", list_fact);
																																				
					//如果用戶不可用，也就是available的值為1
					if(wUser.getAvailable()==1){												
						ajax_result="1";
						return "json_login";
					}
					
					List<WebType>list_type=webtypeSer.findByFactNo2(factNo);
					ActionContext.getContext().getSession().put("list_webtype", list_type);/**********20151029登錄時，保存各個廠別的函文類型************/
					} catch (Exception e) {
						e.printStackTrace();
					}										
					return "json_login";
				}//end if2			
			}//if
		/*用戶名正確,但廠別不正確,*/
		if (wUser == null && userList.size() > 0) {			
			ajax_result="2";
			return "json_login";
			//return null;			
		}						
		ajax_result="3";
		return "json_login";
	}
	
	
		
	/**
	 * 修改用戶
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String updateUesr() throws IOException {
		try{
			boolean yz = webUserService.updateUser(updateU);
			ajax_result="0";
		}catch(Exception e){
			ajax_result="1";
		}				
		return "updateUesr";
	}

	/**
	 * 把用戶信息加載在文本框中
	 * 
	 * @return
	 */
	public String initialUpdate(){
		WebUser wU=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		WebUser webUser=null;
		if (id != 0) {
			webUser=webUserService.selByuserId(id);
		} else {
			webUser=webUserService.selByuserId(wU.getId());
		}
		//ActionContext.getContext().getSession().put("User",webUser);
		ServletActionContext.getRequest().setAttribute("webU",webUser);
		id=0;
		return "initialUpdate";
	}
	public String initialUpdate_guest(){
		this.initialUpdate();
		return "initialUpdate_guest";
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
	 * 查看用戶權限
	 */
	public String jurisdiction() {
		/*List<WebMenu>menus=menuSer.findAllMenu();
		ActionContext.getContext().getSession().put("menus", menus);*/
		WebUser webUser = webUserService.selByuserId(id, fact);
		ActionContext.getContext().getSession().put("jurisdiction_user", webUser);
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
		try{			
			List<WebMenu>list_menu=(List<WebMenu>)ActionContext.getContext().getSession().get("login_menus_all");//登錄時記錄（使用者與訪客所有的主菜單）			
            if(list_menu!=null&&list_menu.size()>0){
            	//List<String>menuname_checked=new ArrayList<String>();
            	//menuname_checked.add("退出管理");
    			Map<String,List<WebSubmenu>>map=new HashMap<String,List<WebSubmenu>>();   			
    			WebUser user = (WebUser) ActionContext.getContext().getSession().get("jurisdiction_user");		
    			if(userread!=null&&!userread.equals("")){
    				user.setUserread(userread);
    			}else{
    				user.setUserread("0");
    			}
    			webUserService.add(user);
    			
    			//（1）在更新時，先查找出舊的權限
    			List<WebJurisdiction> list = jurisdictionService.selSub(user.getId());
    			//（2）開始更新權限
    			for(int i=0;i<list_menu.size();i++){//for1
    				List<WebSubmenu> list_submenu = new ArrayList<WebSubmenu>();
    				for(int j=0;j<checkbox.length;j++){
    					String[] info = checkbox[j].split(",");    					
    					if(info[3].equals(list_menu.get(i).getMenuid().toString())){
    						WebSubmenu submenu=new WebSubmenu();
    						submenu.setSubmenuname(info[1]);
    						submenu.setAddress(info[2]);					
    						//submenu.setSubtype("A0" + j);
    						//submenu.setSmenu2(new WebSubmenu2(Integer.parseInt(info[3])));//20160524
    						list_submenu.add(submenu);
    					}					
    				}
    				if(list_submenu.size()>0){
    					map.put(list_menu.get(i).getMenuname(), list_submenu);
    				}    				   				
    			}//for1
    			//map.put("退出管理", new ArrayList<WebSubmenu>());
    			for(String key:map.keySet()){
    				WebJurisdiction webjur=new WebJurisdiction();
    				WebMenu menu=new WebMenu();
    				for(WebMenu temp:list_menu){
    					if(key.equals(temp.getMenuname())){
    						menu=temp;
    						break ;
    					}
    				}
    				webjur.setWebMenu(menu);
    				webjur.setWebUser(user);    				
    				webjur.setWebSubmenus(map.get(key));
    				for(WebSubmenu submenu:map.get(key)){
    					submenu.setWebJurisdiction(webjur);
    				}
    				jurisdictionService.add(webjur);    				
    			}
    			//（3）放在最後刪除舊的權限
    			for (int i = 0; i < list.size(); i++) {			
    				jurisdictionService.delJur(list.get(i));
    			}
    			ajax_result="0";
			}																	
		}catch(Exception e){
			ajax_result="1";
			e.printStackTrace();
		}				
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
	
	
	/**
	 * 分頁查詢
	 * @return
	 */
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("public_conditions");
		ActionContext.getContext().getSession().remove("public_factno");
		bean=webUserService.findPageBean(20,page, conditions, factNo,userType);
		this.findFactName(bean);
		return "beanList";
	}
	
	/**
	 * 分頁查詢2
	 * @return
	 */
	public String findPageBean2(){
		ActionContext.getContext().getSession().remove("public_conditions");
		ActionContext.getContext().getSession().remove("public_factno");

		if(conditions!=null&&!conditions.equals("")){
			ActionContext.getContext().getSession().put("public_conditions", conditions);
		}
		if(factNo!=null&&!factNo.equals("")){
			ActionContext.getContext().getSession().put("public_factno", factNo);
		}
		bean=webUserService.findPageBean(20,page, conditions, factNo,userType);
		this.findFactName(bean);
		return "beanList1";
	}
	
	/**
	 * 分頁查詢3
	 * @return
	 */
	public String findPageBean3(){
		String result="beanList1";
		if(backIndex==1){
			result="beanList";
		}
		conditions=(String)ActionContext.getContext().getSession().get("public_conditions");
		factNo=(String)ActionContext.getContext().getSession().get("public_factno");		
		bean=webUserService.findPageBean(20,page, conditions, factNo,userType);
		
		this.findFactName(bean);
		return result;
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
		/*********************刪除記錄**************************/
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setContent(id+"");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
		log.setObj("WebUser");
		webUserService.delete(id,log);
		return "delete";
	}
	
	
	/**
	 * 添加新用戶
	 * @return
	 */
	public String add(){
		try{
			String factno=webUsers.getFactno().split("__")[0];
			String erpfactno=webUsers.getFactno().split("__")[1];
			webUsers.setFactno(factno);
			webUsers.setErpfactno(erpfactno);			
			webUserService.add(webUsers);
			ajax_result="0";
		}catch(Exception e){
			e.printStackTrace();
			ajax_result="1";
		}		
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
	
	/*
	 *修改用戶資料或權限返回
	 *與findPageBean3方法相同，隻是返回值不一樣，達到返回不同的頁面
	 */
	public String findPageBean3_1(){
		this.findPageBean3();
		return "beanList";
	}
	
	
	/**
	 * 獲取廠別中文名
	 * @param bean
	 */
	public void findFactName(PageBean bean){
		List list_fact=(List)ActionContext.getContext().getSession().get("facts");
		List<WebUser>list_user=bean.getList();
		if(list_user.size()>0){//if
			String result="";
			for(int j=0;j<list_user.size();j++){
				String factno=list_user.get(j).getFactno();
				list_user.get(j).setEmailpassword(factno);
				for(int k=0;k<list_fact.size();k++){
					Object[]obj=(Object[])list_fact.get(k);
					if(factno.equals(obj[0].toString())){
						result=obj[1].toString()+"("+factno+")";
						list_user.get(j).setEmailpassword(result);//借用emailpass當作廠別中文名
						break;
					}
				}
			}
		}//if
		
	}
	
	
	public String login_guest() throws InterruptedException, IOException {
		ActionContext.getContext().getSession().remove("Email");//清除函文郵件中Email(在KyVisaBillmAction中的findById_email方法)
		DateFormat format=new SimpleDateFormat("yyyyMMdd");							
		WebUser wUser = webUserService.selByuserId(factNo, webUsers.getUsername().trim());
		/*用戶名,密碼,廠別都正確*/
		if (wUser != null) {//if
			if (wUser.getPwd().equals(webUsers.getPwd().trim())) {//start if2
					try {
						ajax_result="0";							  
						  String ipAddress=GlobalMethod.findIp();
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
						Cookie cookieuser= new Cookie("user",factNo+","+wUser.getUsername()+","+wUser.getPwd());													
						cookieuser.setMaxAge(60*60*24*7);//一周限期					 
						//cookieuser.setPath("/");
						ServletActionContext.getResponse().addCookie(cookieuser);
					}
					/*菜单名统一按名字进行排序*/
					Collections.sort(wUser.getWebJurisdictions(), new Comparator<WebJurisdiction>() {
			            public int compare(WebJurisdiction arg0, WebJurisdiction arg1) {
			                return arg0.getWebMenu().getMenuname().compareTo(arg1.getWebMenu().getMenuname());
			            }
			        });
						
						ActionContext.getContext().getSession().put("loginUser", wUser);
						List<WebMenu>login_menus=menuSer.findAllMenu("1");//訪客類型的菜單目錄
						ActionContext.getContext().getSession().put("login_menus", login_menus);//登錄時保存所有的菜單項目
						if (factNo.equals("tw")&& wUser.getUsername().equals("admin")) {								
							ActionContext.getContext().getSession().put("factNo", factNo);									
						} else {
							factNo = wUser.getFactno();
							ActionContext.getContext().getSession().put("factNo", factNo);									
							String factName = webFactSer.selByid(factNo);
							ActionContext.getContext().getSession().put("factName", factName);									
							List factCodes = webFactSer.findFactCodeByFactNo_show_dw(factNo);									
							ActionContext.getContext().getSession().put("factAreas_login", factCodes);	//【各廠產量統計】加載的廠別狀態								
						}
						/******************緩存用戶所屬的廠別信息20160125******************/
						List<Object[]>list_fact=webFactSer.findFactByFactNo(factNo);
						ActionContext.getContext().getSession().put("login_facts", list_fact);
																																				
					//如果用戶不可用，也就是available的值為1
					if(wUser.getAvailable()==1){												
						ajax_result="1";
						return "json_login";
					}
					
					List<WebType>list_type=webtypeSer.findByFactNo2(factNo);
					ActionContext.getContext().getSession().put("list_webtype", list_type);/**********20151029登錄時，保存各個廠別的函文類型************/
					} catch (Exception e) {
						e.printStackTrace();
					}										
					return "json_login";
				}//end if2			
			}					
		ajax_result="3";
		return "json_login";
	}
	
	/**
	 * 退出系統
	 * @return
	 */
	public String logout(){
		ActionContext.getContext().getSession().clear();
		return "logout";
	}
	public String logout_guest(){
		this.logout();
		return "logout_guest";
	}
	
	public String findAllOperations(){
		try{
			List<WebUserOperation>list=webUserService.findAllOperations();
			jsons=JSONArray.fromObject(list);
		}catch(Exception e){
			e.printStackTrace();			
		}
		return "findAllOperations";
	}
	
	public String addoperations(){
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("jurisdiction_user");
		List<WebOperationToUser>list=new ArrayList<WebOperationToUser>();
		try{
			List<WebOperationToUser>list2=webUserService.findoperations(user.getId());
			for(Integer id:checkbox2){
				WebOperationToUser obj=new WebOperationToUser();
				obj.setWebUser(user);
				obj.setWebUserOperation(new WebUserOperation(id));
				list.add(obj);
			}
			webUserService.addWebOperations(list);
			webUserService.delete_operation(list2);
			ajax_result="0";
		}catch(Exception e){
			ajax_result="1";
			e.printStackTrace();
		}	
		return "addoperations";
	}
	
	public String sendWeeklyreport(){
		try {
			ajax_result="1";
			ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring-projectconfig.xml"});			
			MailSenderInfo mailInfo = new MailSenderInfo();
			SimpleMailSender sms = new SimpleMailSender();
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			Calendar cal=Calendar.getInstance();
			cal.setFirstDayOfWeek(Calendar.MONDAY);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			String sdate=sdf.format(cal.getTime());
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			String edate=sdf.format(cal.getTime());
			cal.add(Calendar.DAY_OF_WEEK, -7);		
			
			ProjectConfig pc=(ProjectConfig)ac.getBean("proconfig");
			String url=null;
			
			mailInfo.setValidate(true);//要設為True,否則不能發送外部郵件		
				StringBuffer content=new StringBuffer();		
				url=pc.getpUrl()+"/webweekly_email_url?uid="+webUsers.getId()+"&sdate="+sdate+"&edate="+edate+"&uname="+webUsers.getName();
				content.append(sdate+"-"+edate+"週報告：");
				content.append("<a href='"+url+"'>");		
				content.append("點擊進入</a>");
				mailInfo.setContent(content.toString());
				mailInfo.setSubject("業務每週報告表"+sdate+"-"+edate);
				mailInfo.setToAddress(webUsers.getEmail());
				sms.sendHtmlMail(mailInfo);	
				
				content.append("<br/><span style='color:blue'>"+webUsers.getUsername()+"("+webUsers.getName()+")"+"</span>");
				mailInfo.setContent(content.toString());
				mailInfo.setToAddress(pc.getpEmail());
				sms.sendHtmlMail(mailInfo);
		} catch (Exception e) {
			// TODO: handle exception
			ajax_result="2";
			e.printStackTrace();
		}
		
			return "sendWeeklyreport";
	}
	
}
