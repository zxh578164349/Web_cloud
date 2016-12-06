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

import services.IWebEstProductServices;
import services.IWebFactServices;
import util.GlobalMethod;
import util.JasperHelper;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmLog;
import entity.WebFact;
import entity.WebUser;
import entity.Webestproduct;
import entity.WebestproductId;

public class WebEstProductAction extends ActionSupport implements
		ServletResponseAware {
	private javax.servlet.http.HttpServletResponse response;
	private IWebEstProductServices estProSer;
	private String factNo;
	private int page;
	private String yymm;
	private PageBean bean;
	private String isnull;
	private WebestproductId id;
	private Webestproduct pro;
	private IWebFactServices webFactSer;
	private String lookordown;
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

	public String getLookordown() {
		return lookordown;
	}

	public void setLookordown(String lookordown) {
		this.lookordown = lookordown;
	}

	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
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

	public WebestproductId getId() {
		return id;
	}

	public void setId(WebestproductId id) {
		this.id = id;
	}

	public Webestproduct getPro() {
		return pro;
	}

	public void setPro(Webestproduct pro) {
		this.pro = pro;
	}

	public void setEstProSer(IWebEstProductServices estProSer) {
		this.estProSer = estProSer;
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
		DateFormat format = new SimpleDateFormat("yyyyMM");
		Date date = null;
		String result = null;
		try {
			date = format.parse(yymm);
			pro.getId().setYymm(date);
			if (isnull.equals("isnull")) {												
				Webestproduct temp=estProSer.findById(pro.getId().getFactNo(), pro.getId().getFactCode(), yymm, pro.getId().getType());
				if (temp==null) {
					estProSer.add(pro);
					result = "add";
					ajaxResult="0";
				} 
			} else {
				estProSer.add(pro);
				result = "add";
				ajaxResult="0";
			}
			if (result == null) {
				response.setContentType("text/html;charset=utf-8");				
				response.getWriter()
						.print("<script>alert('數據庫已經存在("
								+ pro.getId().getFactNo()
								+ " "
								+ pro.getId().getFactCode()
								+ " "
								+ format.format(pro.getId().getYymm())
								+" "
								+pro.getId().getType()
								+ ")!');history.back()</script>");
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="add";
			ajaxResult="1";
		} 
		return result;
	}

	public String findPageBean() {
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_yymm");
		ActionContext.getContext().getSession().remove("public_yymm2");
		factNo = (String) ActionContext.getContext().getSession().get("factNo");
		bean = estProSer.findPageBean(25, page, factNo, yymm,yymm2);

		return "beanList";

	}

	public String findPageBean2() {
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_yymm");
		ActionContext.getContext().getSession().remove("public_yymm2");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getSession().put("public_factno", factNo);					
		}
		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getSession().put("public_yymm", yymm);					
		}
		if (yymm2 != null && !yymm2.equals("")) {
			ActionContext.getContext().getSession().put("public_yymm2", yymm2);					
		}

		bean = estProSer.findPageBean(25, page, factNo, yymm,yymm2);

		return "beanList1";
	}

	public String findPageBean3() {
		String result="beanList1";
		if(backIndex==1){
			result="beanList";
		}
		factNo = (String) ActionContext.getContext().getSession().get("public_factno");				
		yymm = (String) ActionContext.getContext().getSession().get("public_yymm");
		yymm2 = (String) ActionContext.getContext().getSession().get("public_yymm2");		
		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession().get("factNo");
					
		}
		bean = estProSer.findPageBean(25, page, factNo, yymm,yymm2);

		return result;

	}

	/*public String findPageBean2_print() {
		ActionContext.getContext().getApplication().clear();
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getApplication().put("print_estpro_factNo", factNo);					

		} else {
			factNo = (String) ActionContext.getContext().getSession().get("factNo");					
		}
		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getApplication()
					.put("print_estpro_yymm", yymm);
		}
		bean = estProSer.findPageBean(10, page, factNo, yymm);
		return "list";
	}

	public String findPageBean3_print() {
		factNo = (String) ActionContext.getContext().getApplication()
				.get("print_estpro_factNo");
		yymm = (String) ActionContext.getContext().getApplication()
				.get("print_estpro_yymm");
		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		bean = estProSer.findPageBean(10, page, factNo, yymm);
		return "list";
	}*/

	public String findById() {
		pro = estProSer.findById(id);
		return "findById";

	}

	public String delete() {
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setObj("Webestproduct");
		log.setFactCode(id.getFactCode());
		log.setFactNo(id.getFactNo());
		log.setYymm(new SimpleDateFormat("yyyyMM").format(id.getYymm()));
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
		log.setContent(id.getType());
		estProSer.delete(id,log);
		return "delete";
	}
	
	public void print(){
		List<Webestproduct>list_zd=new ArrayList<Webestproduct>();
		List<Webestproduct>list_tz=new ArrayList<Webestproduct>();
		List listfactno=webFactSer.findAllFact();
		for(int i=0;i<listfactno.size();i++){//start for
			Object[] temp_factnos=(Object[])listfactno.get(i);
			String temp_factno=(String)temp_factnos[0];//廠別代號
			String temp_factno2=(String)temp_factnos[1];//廠名
			List<WebFact>listfactcodes=webFactSer.findFactById_show(temp_factno);
			for(int k=0;k<listfactcodes.size();k++){
				String factcode=listfactcodes.get(k).getId().getFactArea();
				Webestproduct product_zd=estProSer.findById(temp_factno, factcode, yymm, "zd");
				Webestproduct product_tz=estProSer.findById(temp_factno, factcode, yymm, "tz");
				if(product_zd==null){
					product_zd=new Webestproduct();
					WebestproductId id=new WebestproductId();
					id.setFactNo(temp_factno);
					id.setFactCode(factcode);
					product_zd.setId(id);					
				}
				if(product_tz==null){
					product_tz=new Webestproduct();
					WebestproductId id=new WebestproductId();
					id.setFactNo(temp_factno);
					id.setFactCode(factcode);
					product_tz.setId(id);
				}
				product_zd.setColTemp(temp_factno2);
				product_tz.setColTemp(temp_factno2);
				list_zd.add(product_zd);
				list_tz.add(product_tz);
			}
		}//end for
		List factcodelist=webFactSer.findAllFactCode_show();//�Ҧ��t�O���A
		List<Webestproduct>sum_list_zd=new ArrayList<Webestproduct>();
		List<Webestproduct>sum_list_tz=new ArrayList<Webestproduct>();
		for(int j=0;j<factcodelist.size();j++){//start for
			String factcode=(String)factcodelist.get(j);
			Object[] obj_zd=estProSer.findSum(factcode, yymm, "zd");
			Object[] obj_tz=estProSer.findSum(factcode, yymm, "tz");
			if(obj_zd[0]!=null&&obj_zd[1]!=null&&obj_zd[2]!=null&&
			   obj_zd[3]!=null&&obj_zd[4]!=null&&obj_zd[5]!=null&&
			   obj_zd[6]!=null&&obj_zd[7]!=null&&obj_zd[8]!=null){
				double hole=(Double)obj_zd[0];
				double machinepower=(Double)obj_zd[1];
				double estdays=(Double)obj_zd[2];
				double esteverymodel=(Double)obj_zd[3];
				double esteverypeople=(Double)obj_zd[4];
				double estmodel=(Double)obj_zd[5];
				double estnum=(Double)obj_zd[6];
				double estpay=(Double)obj_zd[7];
				double estmoney=(Double)obj_zd[8];
				Webestproduct product=new Webestproduct();
				WebestproductId id=new WebestproductId();
				id.setFactCode(factcode);
				product.setId(id);
				product.setHole(hole);
				product.setMachinepower(machinepower);
				product.setEstdays(estdays);
				product.setEsteverymodel(esteverymodel);
				product.setEsteverypeople(esteverypeople);
				product.setEstmodel(estmodel);
				product.setEstnum(estnum);
				product.setEstpay(estpay);
				product.setEstmoney(estmoney);
				sum_list_zd.add(product);
			}else{
				Webestproduct product=new Webestproduct();
				WebestproductId id=new WebestproductId();
				id.setFactCode(factcode);
				product.setId(id);
				sum_list_zd.add(product);
			}
			if(obj_tz[0]!=null&&obj_tz[1]!=null&&obj_tz[2]!=null&&
			   obj_tz[3]!=null&&obj_tz[4]!=null&&obj_tz[5]!=null&&
			   obj_tz[6]!=null&&obj_tz[7]!=null&&obj_tz[8]!=null){
				double hole=(Double)obj_tz[0];
				double machinepower=(Double)obj_tz[1];
				double estdays=(Double)obj_tz[2];
				double esteverymodel=(Double)obj_tz[3];
				double esteverypeople=(Double)obj_tz[4];
				double estmodel=(Double)obj_tz[5];
				double estnum=(Double)obj_tz[6];
				double estpay=(Double)obj_tz[7];
				double estmoney=(Double)obj_tz[8];
				Webestproduct product=new Webestproduct();
				WebestproductId id=new WebestproductId();
				id.setFactCode(factcode);
				product.setId(id);
				product.setHole(hole);
				product.setMachinepower(machinepower);
				product.setEstdays(estdays);
				product.setEsteverymodel(esteverymodel);
				product.setEsteverypeople(esteverypeople);
				product.setEstmodel(estmodel);
				product.setEstnum(estnum);
				product.setEstpay(estpay);
				product.setEstmoney(estmoney);
				sum_list_tz.add(product);
			}else{
				Webestproduct product=new Webestproduct();
				WebestproductId id=new WebestproductId();
				id.setFactCode(factcode);
				product.setId(id);
				sum_list_tz.add(product);
			}
		}//end for
		Map<String,Object>map=new HashMap<String,Object>();
		Map<String,Object>sub_map=new HashMap<String,Object>();
		Map<String,Object>sub_map2=new HashMap<String,Object>();
		Map<String,Object>sub_map3=new HashMap<String,Object>();
		Map<String,Object>sub_map3_3=new HashMap<String,Object>();		
		sub_map.put("list_zd", list_zd);
		sub_map2.put("list_tz",list_tz);		
		sub_map3.put("sum_list_zd", sum_list_zd);
		sub_map3_3.put("sum_list_tz", sum_list_tz);
		map.put("sub_map", sub_map);
		map.put("sub_map2", sub_map2);
		map.put("sub_map3", sub_map3);
		map.put("sub_map3_3", sub_map3_3);
		map.put("yymm", yymm);
		map.put("SUBREPORT_DIR",ServletActionContext.getRequest().getRealPath("/jasper/audit/")+ "/");
		if(lookordown.equals("look")){
			JasperHelper.exportmain("html", map,"webestproduct.jasper", list_zd,yymm, "jasper/audit/");
		}else{
			JasperHelper.exportmain("excel", map,"webestproduct.jasper", list_zd,yymm, "jasper/audit/");
		}
	}
	
	public void print2() throws IOException{
		List<Webestproduct>list=estProSer.findByAny(factNo, yymm, yymm2);
		GlobalMethod.print(list, factNo, yymm, yymm2, "webestproduct.jasper", response);
	}

}
