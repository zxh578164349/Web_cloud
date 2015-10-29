package action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import services.IWebCostServices;
import services.IWebFactServices;
import services.IWebPersonnumServices;
import util.JasperHelper;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.*;

public class WebPersonnumAction extends ActionSupport implements
		ServletResponseAware {
	private IWebPersonnumServices personNumSer;
	private String factNo;
	private int page;
	private String yymm;
	private Webpersonnum person;
	private PageBean bean;
	private javax.servlet.http.HttpServletResponse response;
	private String isnull;
	private WebpersonnumId id;
	private String yymmdd;
	private String lookordown;
	private String beginDay;
	private String endDay;
	private IWebFactServices webFactSer;
	

	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	

	public String getLookordown() {
		return lookordown;
	}


	public void setLookordown(String lookordown) {
		this.lookordown = lookordown;
	}


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

	public Webpersonnum getPerson() {
		return person;
	}

	public void setPerson(Webpersonnum person) {
		this.person = person;
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

	public WebpersonnumId getId() {
		return id;
	}

	public void setId(WebpersonnumId id) {
		this.id = id;
	}
	

	public String getBeginDay() {
		return beginDay;
	}


	public void setBeginDay(String beginDay) {
		this.beginDay = beginDay;
	}


	public String getEndDay() {
		return endDay;
	}


	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}


	public void setPersonNumSer(IWebPersonnumServices personNumSer) {
		this.personNumSer = personNumSer;
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
				date = format.parse(yymm);
				person.getId().setYymmdd(date);
				String factNo1 = person.getId().getFactNo();
				String factCode1 = person.getId().getFactCode();
				String fact = (String) ActionContext.getContext().getSession()
						.get("factNo");
				List<Webpersonnum> list = personNumSer.findByFactNo(fact);
				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						if (factCode1.equals(list.get(i).getId().getFactCode())
								&& factNo1.equals(list.get(i).getId()
										.getFactNo())
								&& person
										.getId()
										.getYymmdd()
										.equals(list.get(i).getId().getYymmdd())) {

							break;
						} else if (i == list.size() - 1) {
							personNumSer.add(person);
							result = "add";
						}
					}// end for
				} else {
					personNumSer.add(person);
					result = "add";
				}

			} else {
				date = format.parse(yymm);
				person.getId().setYymmdd(date);
				personNumSer.add(person);
				result = "add";
			}

			if (result == null) { // 判斷返回結果
				response.setContentType("text/html;charset=utf-8");
				String temp1 = person.getId().getFactNo();
				String temp2 = person.getId().getFactCode();
				String temp3 = format.format(person.getId().getYymmdd());
				response.getWriter()
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
		bean = personNumSer.findPageBean(25, page, factNo, null,beginDay,endDay);

		return "beanList";

	}

	public String findPageBean2() {
		ActionContext.getContext().getApplication().clear();
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getApplication()
					.put("personnum_factNo", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getApplication()
					.put("personnum_yymm", yymm);
		}
		if(beginDay!=null&&!beginDay.equals("")){
			ActionContext.getContext().getApplication().put("personnum_begin", beginDay);
		}
		if(endDay!=null&&!endDay.equals("")){
			ActionContext.getContext().getApplication().put("personnum_end", endDay);
		}

		bean = personNumSer.findPageBean(25, page, factNo, null,beginDay,endDay);

		return "beanList1";
	}

	public String findPageBean3() {
		factNo = (String) ActionContext.getContext().getApplication()
				.get("personnum_factNo");
		yymm = (String) ActionContext.getContext().getApplication()
				.get("personnum_yymm");
		beginDay=(String)ActionContext.getContext().getApplication().get("personnum_begin");
		endDay=(String)ActionContext.getContext().getApplication().get("personnum_end");
		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		bean = personNumSer.findPageBean(25, page, factNo, null,beginDay,endDay);

		return "beanList1";

	}

	public String findPageBean2_print() {
		ActionContext.getContext().getApplication().clear();
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getApplication()
					.put("print_personnum_factNo", factNo);

		} else {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getApplication()
					.put("print_personnum_yymm", yymm);
		}
		bean = personNumSer.findPageBean(10, page, factNo, yymm,null,null);
		return "list";
	}

	public String findPageBean3_print() {
		factNo = (String) ActionContext.getContext().getApplication()
				.get("print_personnum_factNo");
		yymm = (String) ActionContext.getContext().getApplication()
				.get("print_personnum_yymm");
		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		bean = personNumSer.findPageBean(10, page, factNo, yymm,null,null);
		return "list";
	}

	public String findById() {
		person = personNumSer.findById(id);
		return "findById";

	}

	public String delete() {
		personNumSer.delete(id);
		return "delete";
	}
	
	public void print(){
		List<Webpersonnum>list=new ArrayList<Webpersonnum>();
		List<Object[]>list_obj=new ArrayList<Object[]>();
		List listfactno=webFactSer.findAllFact();
		for(int x=0;x<listfactno.size();x++){
			Object[] temp_factnos=(Object[])listfactno.get(x);
			String temp_factno=(String)temp_factnos[0];
			List<WebFact>listfactcodes=webFactSer.findFactById(temp_factno);
			for(int y=0;y<listfactcodes.size();y++){
				String temp_factcode=listfactcodes.get(y).getId().getFactArea();
				Webpersonnum person=personNumSer.findById2(temp_factno, temp_factcode,yymmdd);	
				String factname=webFactSer.selByid(temp_factno);
				if(person==null){
					person=new Webpersonnum();
					WebpersonnumId id=new WebpersonnumId();
					id.setFactNo(factname+"("+temp_factno+")");//合成廠別代號與名字,用於顯示在報表
					id.setFactCode(temp_factcode);
					person.setId(id);
				}else{
					person.getId().setFactNo(factname+"("+temp_factno+")");
				}
				list.add(person);
				
			}			
		}
		List factcodelist=webFactSer.findAllFactCode();//所有廠別狀態
		for(int j=0;j<factcodelist.size();j++){
			String factcode=(String)factcodelist.get(j);
			Object[]temp=personNumSer.findByYnmmddAndFactcode(factcode, yymmdd);
			if(temp==null){
				temp=new Object[10];
				temp[0]=factcode;
			}
			list_obj.add(temp);
		}		
		List<Webpersonnum>list_temp=new ArrayList<Webpersonnum>();
		for(int i=0;i<list_obj.size();i++){
			String factcode=(String)list_obj.get(i)[0];
			Double standardnumzg =(Double)list_obj.get(i)[1];
			Double realnumzg=(Double)list_obj.get(i)[2];
			Double standardnumjg=(Double)list_obj.get(i)[3];
			Double realnumjg=(Double)list_obj.get(i)[4];
			Double outstandardnum=(Double)list_obj.get(i)[5];
			Double outrealnum=(Double)list_obj.get(i)[6];	
			Webpersonnum person=new Webpersonnum();
			WebpersonnumId id=new WebpersonnumId();
			id.setFactCode(factcode);
			person.setId(id);
			person.setOutrealnum(outrealnum);
			person.setOutstandardnum(outstandardnum);
			person.setRealnumjg(realnumjg);
			person.setRealnumzg(realnumzg);
			person.setStandardnumjg(standardnumjg);
			person.setStandardnumzg(standardnumzg);
			list_temp.add(person);
		}
		Map<String,Object>map_sub=new HashMap<String,Object>();
		map_sub.put("list_temp", list_temp);
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("map_sub", map_sub);
		map.put("SUBREPORT_DIR",ServletActionContext.getRequest().getRealPath("/jasper/audit/")+ "/");
		//map.put("yymmdd", "20140923");
		//JasperHelper.exportmain("pdf", map,"webpersonnum.jasper", list,"20140923", "jasper/audit/");
		//JasperHelper.exportmain("excel", map,"webpersonnum.jasper", list,"20140923", "jasper/audit/");
		if(lookordown.equals("look")){
			JasperHelper.exportmain("html", map,"webpersonnum.jasper", list,yymmdd, "jasper/audit/");
		}else{
			JasperHelper.exportmain("excel", map,"webpersonnum.jasper", list,yymmdd, "jasper/audit/");
		}
		
		
	}
	
	public String transit() {
		return "transit";
	}

}
