package action;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.hibernate.transform.ToListResultTransformer;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import entity.WebCc;
import entity.WebEmail;
import entity.WebEmailAll;
import entity.WebFact;
import entity.WebFactId;
import entity.WebydataNoinput;
import entity.WebydataNoinputId;
import entity.custom.ProjectConfig;

import services.IWebEmailService;
import services.IWebEstProductServices;
import services.IWebYieldDataServices;
import services.IWebydataNoinputServices;
import util.GlobalMethod;

/**
 * 系统启动时的监听类 初始化系统数据
 * 
 * @author jhoneder
 * 
 */
public class TestTimerAction extends QuartzJobBean {
	private static final ProjectConfig pc=GlobalMethod.findProjectConfig();
	private String yymm;
	private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗
		
	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	public String getYymm() {
		return yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		// context销毁时，销毁初始化数据
	}

/*	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		try {
			goTimer();
		} catch (Exception e) {
			System.out.println("失败:" + e.getMessage());
		}
	}*/
	
	public static String notInput(String date){
		ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring-action.xml","spring-services.xml","spring-dao.xml","spring.xml"});
		IWebEstProductServices estProSer=(IWebEstProductServices)ac.getBean("estProSer");
		List<String[]>list=estProSer.getFactPrint_show(date);
		StringBuffer result=new StringBuffer();
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[]obj=list.get(i);
				String factname=(String)obj[0];
				String factcode=(String)obj[1];
				result.append(factname+"(");
				result.append(factcode+")");
				result.append("<br/>");
			}
		}else{
			result.append("無");
		}		
		return result.toString();
	}

	public static String notInput(ApplicationContext ac) {
		List<String> list_day=null;
		DateFormat frm=new SimpleDateFormat("yyyyMMdd");
		// 查找當天沒輸數據廠別
		/*ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "spring-action.xml", "spring-dao.xml",
						"spring.xml", "spring-services.xml" });*/
		IWebYieldDataServices dataSer = (IWebYieldDataServices) ac.getBean("dataSer");
		IWebydataNoinputServices webydatenoinputSer=(IWebydataNoinputServices)ac.getBean("webydatenoinputSer");		
		Calendar cal = Calendar.getInstance();
		Date bdate=new Date();
		cal.setTime(bdate);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
			list_day=getTenDay(-3);
		}else{
	        list_day=getTenDay(-1);
		}
		StringBuffer totalTemp = new StringBuffer();
		for (int i = 0; i < list_day.size(); i++) {//for
			List<String[]> list = dataSer.getFactPrint_show(list_day.get(i));
			List<WebydataNoinput>list_noinput=new ArrayList<WebydataNoinput>();
			StringBuffer temp = new StringBuffer();
			for (int j = 0; j < list.size(); j++) {
				Object[] objs = list.get(j);
				String factNo = (String) objs[0];
				String factCode = (String) objs[1];
				temp.append(factNo + "(");
				temp.append(factCode + ")");
				if (j < list.size() - 1) {
					temp.append("<br/>");
				}
				try {
					WebydataNoinput oninput=new WebydataNoinput(new WebydataNoinputId(new WebFact(new WebFactId((String)objs[2],(String)objs[1])),frm.format(new SimpleDateFormat("yyyy/MM/dd").parse(list_day.get(i)))),new SimpleDateFormat("yyMMdd_hh").format(new Date()));
					list_noinput.add(oninput);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			String result = temp.toString();
			if (result.equals("")) {
				if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
				totalTemp.append(list_day.get(i)+"<br/>"+"都已輸入數據");
				}else{
					totalTemp.append("都已輸入數據");
				}
			} else {
				if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
				totalTemp.append("<span style='color:blue'>"+list_day.get(i)+"</span>"+"<br/>"+result);
				}else{
					totalTemp.append(result);
				}
			}
			if (i < list_day.size() - 1) {
				totalTemp.append("<br/>");
			}
			webydatenoinputSer.addLarge(list_noinput);//未输入记录20160410
			
		}//for
		totalTemp.append("<br/>");
		String totalResult = totalTemp.toString();
		return totalResult;
	}

	public static void main(String[] args) {
		/*
		 * Calendar c = Calendar.getInstance(); Date d = new Date();
		 * c.setTime(d); int year = c.get(Calendar.YEAR); int month =
		 * c.get(Calendar.MONTH); c.set(Calendar.DAY_OF_MONTH, 1); // 1号的日期 d =
		 * c.getTime(); String one=d.toLocaleString(); String zh=
		 * one.substring(0, 8); System.err.println(zh);
		 */
	}

	public static String dateAdd(int days) {
		Calendar canlendar = Calendar.getInstance(); 
		canlendar.add(Calendar.DATE, days); 
		String result = (new SimpleDateFormat("yyyy/MM/dd")).format(canlendar
				.getTime());
		//System.out.println(result);
		return result;
	}


	/* 得到最近的日期列表 */
	public static List getTenDay(int t) {
		List list = new ArrayList();
		for (int i =t; i < 0; i++)
			list.add(dateAdd(i));
		return list;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try{
			List<String> ips=GlobalMethod.findIp2();				
			if(ips.size()==0){
				this.init();
			}else{
				for(int i=0;i<ips.size();i++){
					if(ips.get(i).equals(pc.getpHostLoaclB())){
						this.init();
						break;
					}else if(i==ips.size()-1){
						System.out.println("本機不需要發送Email");
					}
				}
			}
			//this.init();
			
		}catch(Exception e){
			
		}	
	}
	
	public void init(){
		//String pname=this.findProjectConfig().getpName();//項目名稱
		SimpleDateFormat format=new SimpleDateFormat("yyyyMM");		
		if(yymm==null||yymm.equals("")){
			Calendar calendar=Calendar.getInstance();
			//當每月的2號是星期一，也就是1號是星期天，則要減2天，以發送上個月全部產量資料
			if(calendar.get(Calendar.DAY_OF_MONTH)==2&&calendar.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY){
				calendar.add(Calendar.DATE, -2);
			}else{
				calendar.add(Calendar.DATE, -1);
			}
			yymm=format.format(calendar.getTime());
			
		}
		try {
			Date bdate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(bdate);
			/*if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){*/
			if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){	
				System.err.print("ok");
			} else {
				HttpClient client = new HttpClient();
				HttpMethod method = new GetMethod(pc.getpUrl()+"/print2Ypoi_print2Y_hb?sdate="+yymm+"&edate="+yymm+"&emailMk=1&type=Excel2003");//(在不同的機器上注意修改IP和端口)
				//HttpMethod method = new GetMethod(pc.getPurllocal()+"/print2Ypoi_print2Y_hb?sdate="+yymm+"&edate="+yymm+"&emailMk=1&type=Excel2003");
				//HttpMethod method = new GetMethod("http://203.85.73.161/"+pname+"/print2Ypoi_print2Y_hb?sdate="+yymm+"&edate="+yymm+"&emailMk=1&type=Excel2003");//(在不同的機器上注意修改IP和端口)
				//HttpMethod method = new GetMethod("http://172.17.18.173:8080/"+pname+"/print2Ypoi_print2Y_hb?sdate="+yymm+"&edate="+yymm+"&emailMk=1&type=Excel2003");
				//HttpMethod method = new GetMethod("http://localhost:8080/"+pname+"/print2Ypoi_print2Y_hb?sdate="+yymm+"&edate="+yymm+"&emailMk=1&type=Excel2003");
				
				client.executeMethod(method);
				method.releaseConnection();
				ApplicationContext ac = new ClassPathXmlApplicationContext(
						new String[] { "spring-action.xml",
								"spring-dao.xml", "spring.xml",
								"spring-services.xml" });
				String totalResult = notInput(ac);				
				IWebEmailService eSer = (IWebEmailService) ac.getBean("emailService");						
				//List<WebEmail> email = eSer.getEmail("Y");
				List<WebEmailAll> email = eSer.findEmail(3, "0");
				//List<WebEmailAll> email = eSer.findEmail(4, "0");
				String[] mail = new String[email.size()];
				for (int i = 0; i < email.size(); i++) {
					if (email.get(i).getUsername() != null
							|| !email.get(i).getUsername().equals("")) {
						//mail[i] = MimeUtility.encodeText(email.get(i).getName())+ "<"+ email.get(i).getEmail()+ ">";
						//解決收件人中文亂碼的問題，因爲因爲數據是utf-8編碼
						mail[i] = MimeUtility.encodeText(email.get(i).getUsername(),"utf-8","Q")+ "<"+ email.get(i).getEmail()+ ">";
					} else {
						mail[i] = email.get(i).getEmail();
					}
				}
				//List<WebCc> Cc = eSer.getCC("Y");
				List<WebEmailAll> Cc = eSer.findEmail(3, "1");
				//List<WebEmailAll> Cc = eSer.findEmail(4, "1");
				String[] cc = new String[Cc.size()];
				for (int j = 0; j < Cc.size(); j++) {
					if (Cc.get(j).getUsername() != null
							|| !Cc.get(j).getUsername().equals("")) {
						//cc[j] = MimeUtility.encodeText(Cc.get(j).getName())+ "<" + Cc.get(j).getEmail() + ">";
						//解決收件人中文亂碼的問題，因爲因爲數據是utf-8編碼
						cc[j] = MimeUtility.encodeText(Cc.get(j).getUsername(),"utf-8","Q")+ "<" + Cc.get(j).getEmail() + ">";		
					} else {
						cc[j] = Cc.get(j).getUsername() + Cc.get(j).getEmail();
					}
				}
				
				/*String[] mail={MimeUtility.encodeText("張錫洪")+"<kyinfo.David@yyin.yydg.com.cn>"};				
				String[] cc = {MimeUtility.encodeText("張錫洪")+"<kyinfo.David@yyin.yydg.com.cn>"};*/
				/*String[] mail={MimeUtility.encodeText("張錫洪")+"<zxh578164349@qq.com>"};				
				String[] cc = {MimeUtility.encodeText("張錫洪")+"<zxh578164349@qq.com>"};*/
				/*String[] mail={MimeUtility.encodeText("張錫洪")+"<kyuen@yydg.com.cn>"};				
				String[] cc = {MimeUtility.encodeText("張錫洪")+"<kyuen@yydg.com.cn>"};*/
				
				AutoSendEmailAction send = new AutoSendEmailAction();
				SimpleDateFormat formatDates = new SimpleDateFormat("yyyy/MM/dd");						
				DateFormat formast = new SimpleDateFormat("MM");
				SimpleDateFormat jinri = new SimpleDateFormat("M/dd");
				//郵件內容
				String affixName=yymm+"各廠產量資料.xls";
				StringBuffer content=new StringBuffer();
				content.append("各主管:好!"						
								+(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY?formatDates.format(formatDates
										.parse(dateAdd(-3)))+"~"+formatDates.format(formatDates
										.parse(dateAdd(-1))):formatDates.format(formatDates
												.parse(dateAdd(-1))))
								+ "未輸入系統廠別如下:"
								+ "<br/>"
								+ "<span style='color:red;font-size:16'>"
								+ totalResult
								+ "</span><br/><br/><br/>"
								+ "本郵件自動發送,請勿回復!如需回复，請回复到"+pc.getpEmail()+"咨訊室或者"+pc.getPlgx()+"譚香林!"
						);
				//每月2號告知哪些廠別未輸入當月的【預計生產】數據
				int my_day=cal.get(Calendar.DAY_OF_MONTH);
				if(my_day==2){
					String result=notInput(format.format(new Date()));
					content.append("<br/><br/>"
							+"補充:"
							+"當月1號未輸入【預計生產】數據的廠別如下:"
							+"<br/>"
							+"<span style='color:blue;font-size:16'>"
							+result
							+"</span><br/>");
				}
				//發送郵件
				String classes_path=Thread.currentThread().getContextClassLoader().getResource("").getPath();			
				String filepath=classes_path.replace("/WEB-INF/classes","/TEMPFILES/"+yymm+".xls");//附檔的路徑20170222
				send.sendmail(
						mail,
						cc,
						formast.format(formatDates.parse(dateAdd(-1)))+ "月份加久各工廠油壓產量表匯總--截止"+ jinri.format(formatDates.parse(dateAdd(-1))),																
						content.toString(),						
						affixName,
						filepath);				
				//File file = new File("d://" + yymm + ".xls");
				File file=new File(filepath);
				if (file.exists()) {
					if (file.isFile()) {
						file.delete();
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 手動發送郵件
	 * @throws ParseException 
	 */
	public String print_manual() throws ParseException {
		String pname=this.findProjectConfig().getpName();//項目名稱
		SimpleDateFormat tformat=new SimpleDateFormat("yyyyMM");
		if (yymm == null || yymm.equals("")) {
			Calendar calendar=Calendar.getInstance();
			// 當每月的2號是星期一，也就是1號是星期天，則要減2天，以發送上個月全部產量資料
			if (calendar.get(Calendar.DAY_OF_MONTH) == 2 && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				calendar.add(Calendar.DATE,-2);
			} else {
				calendar.add(Calendar.DATE,-1);
			}
			yymm=tformat.format(calendar.getTime());
		}
		try {
			Date bdate=new Date();
			Calendar cal=Calendar.getInstance();
			cal.setTime(bdate);
			/*
			 * if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY||
			 * cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			 */
			if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				System.err.print("ok");
			} else {
				HttpClient client=new HttpClient();
				HttpMethod method=new GetMethod(pc.getpUrl()+"/"+pname+"/print2Ypoi_print2Y_hb?sdate=" + yymm + "&edate=" + yymm+ "&emailMk=1&type=Excel2003");// (在不同的機器上注意修改IP和端口)	
				//HttpMethod method=new GetMethod("http://203.85.73.161/"+pname+"/print2Ypoi_print2Y_hb?sdate=" + yymm + "&edate=" + yymm+ "&emailMk=1&type=Excel2003");// (在不同的機器上注意修改IP和端口)						
				//HttpMethod method=new GetMethod("http://172.17.18.173:8080/"+pname+"/print2Ypoi_print2Y_hb?sdate="+yymm+"&edate="+yymm+"&emailMk=1&type=Excel2003");
				// HttpMethod method=new GetMethod("http://localhost:8080/"+pname+"/print2Ypoi_print2Y_hb?sdate="+yymm+"&edate="+yymm+"&emailMk=1&type=Excel2003");
				client.executeMethod(method);
				method.releaseConnection();
				ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring-action.xml","spring-dao.xml","spring.xml","spring-services.xml"});
				String totalResult=notInput(ac);
				IWebEmailService eSer=(IWebEmailService)ac.getBean("emailService");

				//List<WebEmail> email=eSer.getEmail("Y");
				List<WebEmailAll> email=eSer.findEmail(3, "0");
				String[] mail=new String[email.size()];
				for (int i=0; i < email.size(); i++) {
					if (email.get(i).getUsername() != null || !email.get(i).getUsername().equals("")) {
						// mail[i] =
						// MimeUtility.encodeText(email.get(i).getName())+ "<"+
						// email.get(i).getEmail()+ ">";
						// 解決收件人中文亂碼的問題，數據庫是以utf-8編碼
						mail[i]=MimeUtility.encodeText(email.get(i).getUsername(),"utf-8","Q") + "<" + email.get(i).getEmail() + ">";
					} else {
						mail[i]=email.get(i).getEmail();
					}
				}
				//List<WebCc> Cc=eSer.getCC("Y");
				List<WebEmailAll> Cc=eSer.findEmail(3, "1");
				String[] cc=new String[Cc.size()];
				for (int j=0; j < Cc.size(); j++) {
					if (Cc.get(j).getUsername() != null || !Cc.get(j).getUsername().equals("")) {
						// cc[j] = MimeUtility.encodeText(Cc.get(j).getName())+
						// "<" + Cc.get(j).getEmail() + ">";
						// 解決收件人中文亂碼的問題，數據庫是以utf-8編碼
						cc[j]=MimeUtility.encodeText(Cc.get(j).getUsername(),"utf-8","Q") + "<" + Cc.get(j).getEmail() + ">";

					} else {
						cc[j]=Cc.get(j).getUsername() + Cc.get(j).getEmail();
					}
				}

				
				  /*String[] mail={MimeUtility.encodeText("張錫洪")+"<kyinfo.David@yyin.yydg.com.cn>"};
				  String[] cc ={MimeUtility.encodeText("張錫洪")+"<kyinfo.David@yyin.yydg.com.cn>"};*/
				
				/*
				  String[] mail={MimeUtility.encodeText("張錫洪")+"<zxh578164349@qq.com>"};
				  String[] cc = {MimeUtility.encodeText("張錫洪")+"<zxh578164349@qq.com>"};
				 */

				String classes_path=Thread.currentThread().getContextClassLoader().getResource("").getPath();			
				String filepath=classes_path.replace("/WEB-INF/classes","/TEMPFILES/"+yymm+".xls");
				AutoSendEmailAction send=new AutoSendEmailAction();
				String tyymm=tformat.format(new Date());
				String affixName=yymm + "各廠產量資料.xls";
				if (yymm.equals(tyymm)) {
					SimpleDateFormat formatDates=new SimpleDateFormat("yyyy/MM/dd");
					DateFormat formast=new SimpleDateFormat("MM");
					SimpleDateFormat jinri=new SimpleDateFormat("M/dd");

					// 郵件內容

					StringBuffer content=new StringBuffer();
					content.append("各主管:好!"
							+ (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY ? formatDates.format(formatDates.parse(dateAdd(-3))) + "~"
									+ formatDates.format(formatDates.parse(dateAdd(-1))) : formatDates.format(formatDates.parse(dateAdd(-1)))) + "未輸入系統廠別如下:"
							+ "<br/>" + "<span style='color:red;font-size:16'>" + totalResult + "</span><br/><br/><br/>"
							+ "本郵件自動發送,請勿回復!如需回复，請回复到"+pc.getpEmail()+"咨訊室或者"+pc.getPlgx()+"譚香林!");
					// 每月2號告知哪些廠別未輸入當月的【預計生產】數據
					int my_day=cal.get(Calendar.DAY_OF_MONTH);
					if (my_day == 2) {
						String result=notInput(tformat.format(new Date()));
						content.append("<br/><br/>" + "補充:" + "當月1號未輸入【預計生產】數據的廠別如下:" + "<br/>" + "<span style='color:blue;font-size:16'>" + result
								+ "</span><br/>");
					}
					// 發送郵件
					send.sendmail(mail,cc,formast.format(formatDates.parse(dateAdd(-1))) + "月份加久各工廠油壓產量表匯總--截止" + jinri.format(formatDates.parse(dateAdd(-1))),
							content.toString(),affixName,filepath);
				} else {
					send.sendmail(mail,cc,yymm + "加久各工廠油壓產量表匯總","本郵件自動發送,請勿回復!如需回复，請回复到"+pc.getpEmail()+"咨訊室或者lgx@yydg.com.cn譚香林!",affixName,filepath);
				}
				//File file=new File("d://" + yymm + ".xls");
				File file=new File(filepath);
				if (file.exists()) {
					if (file.isFile()) {
						file.delete();
					}
				}
			}
			ajaxResult="0";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ajaxResult="1";
		}
		return "print_manual";
	}
	
	public ProjectConfig findProjectConfig(){
		ProjectConfig pro=GlobalMethod.findProjectConfig();
		return pro;
	}
	
}