package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;



import services.IWebMix1Services;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


import entity.KyzExpectmatmLog;
import entity.WebUser;
import entity.WebYieldData;
import entity.WebYieldDataId;
import entity.Webcost;
import entity.Webmix1;
import entity.Webmix1Id;

public class WebMix1Action extends ActionSupport implements
		ServletResponseAware {
	private String factNo;
	private int page;
	private String yymm;
	private WebYieldData mix1;
	private PageBean bean;
	private javax.servlet.http.HttpServletResponse response;
	private String isnull;
	private IWebMix1Services mix1Ser;
	private WebYieldDataId id;
	private String yymmdd;

	public String getYymmdd() {
		return yymmdd;
	}

	public void setYymmdd(String yymmdd) {
		this.yymmdd = yymmdd;
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
	



	public WebYieldData getMix1() {
		return mix1;
	}

	public void setMix1(WebYieldData mix1) {
		this.mix1 = mix1;
	}

	public WebYieldDataId getId() {
		return id;
	}

	public void setId(WebYieldDataId id) {
		this.id = id;
	}

	public void setMix1Ser(IWebMix1Services mix1Ser) {
		this.mix1Ser = mix1Ser;
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
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		String result = null;
		try {
			if (isnull.equals("isnull")) {
				date = format.parse(yymmdd);
				mix1.getId().setYymmdd(date);
				String factNo1 = mix1.getId().getFactNo();
				String factCode1 = mix1.getId().getFactCode();
				String fact = (String) ActionContext.getContext().getSession()
						.get("factNo");
				List<WebYieldData> list = mix1Ser.findByFactNo(fact);
				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						if (factCode1.equals(list.get(i).getId().getFactCode())
								&& factNo1.equals(list.get(i).getId()
										.getFactNo())
								&& mix1.getId()
										.getYymmdd()
										.equals(list.get(i).getId().getYymmdd())) {

							break;
						} else if (i == list.size() - 1) {
							mix1Ser.add(mix1);
							result = "add";
						}
					}// end for
				} else {
					mix1Ser.add(mix1);
					result = "add";
				}

			} else {
				date = format.parse(yymmdd);
				mix1.getId().setYymmdd(date);
				mix1Ser.add(mix1);
				result = "add";
			}

			if (result == null) { // �P�_��^���G
				response.setContentType("text/html;charset=utf-8");				
				String temp1 = mix1.getId().getFactNo();
				String temp2 = mix1.getId().getFactCode();
				String temp3 = format.format(mix1.getId().getYymmdd());
				/*response.getOutputStream().write("�ƾڮw�w�s�b".getBytes("utf-8"));*/
				//out.print("<script>window.open('none.jsp',target='blank')</script>");
				response.getWriter()
						.print("<script>alert('�ƾڮw�w�s�b("
								+ temp1
								+ " "
								+ temp2
								+ " "
								+ temp3
								+ ")!',history.back())</script>");				
				
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
		return result;
	}
	


	public String findPageBean() {
		ActionContext.getContext().getApplication().clear();
		factNo = (String) ActionContext.getContext().getSession().get("factNo");
		bean = mix1Ser.findPageBean(25, page, factNo, yymm);

		return "beanList";

	}

	public String findPageBean2() {
		ActionContext.getContext().getApplication().clear();
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getApplication()
					.put("mix1_factNo", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getApplication().put("mix1_yymm", yymm);
		}

		bean = mix1Ser.findPageBean(25, page, factNo, yymm);

		return "beanList1";
	}

	public String findPageBean3() {
		factNo = (String) ActionContext.getContext().getApplication()
				.get("mix1_factNo");
		yymm = (String) ActionContext.getContext().getApplication()
				.get("mix1_yymm");
		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		bean = mix1Ser.findPageBean(25, page, factNo, yymm);

		return "beanList1";

	}

	public String findPageBean2_print() {
		ActionContext.getContext().getApplication().clear();
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getApplication()
					.put("print_mix1_factNo", factNo);

		} else {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getApplication()
					.put("print_mix1_yymm", yymm);
		}
		bean = mix1Ser.findPageBean(10, page, factNo, yymm);
		return "list";
	}

	public String findPageBean3_print() {
		factNo = (String) ActionContext.getContext().getApplication()
				.get("print_mix1_factNo");
		yymm = (String) ActionContext.getContext().getApplication()
				.get("print_mix1_yymm");
		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		bean = mix1Ser.findPageBean(10, page, factNo, yymm);
		return "list";
	}

	public String findById() {
		mix1 = mix1Ser.findById(id);
		return "findById";

	}

	public String delete() {
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setFactCode(id.getFactCode());
		log.setFactNo(id.getFactNo());
		log.setYymm(new SimpleDateFormat("yyyyMM").format(id.getYymmdd()));
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
		mix1Ser.delete(id,log);
		return "delete";
	}

}
