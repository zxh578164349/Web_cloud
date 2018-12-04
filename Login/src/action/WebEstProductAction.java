package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import util.ImportExcel;
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
	
	private File file;
    private String fileFileName;
    private String fileContentType;
    private final static String SEPARATOR = "__";

	
    
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

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
		try {
			date = format.parse(yymm);
			pro.getId().setYymm(date);
			if (isnull.equals("isnull")) {												
				Webestproduct temp=estProSer.findById(pro.getId().getFactNo(), pro.getId().getFactCode(), yymm, pro.getId().getType());
				if (temp==null) {
					estProSer.add(pro);
					ajaxResult="0";
				}else{
					ajaxResult="2";//數據庫存在數據
				} 
			} else {
				estProSer.add(pro);
				ajaxResult="0";
			}			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ajaxResult="1";
		} 
		return "add";
	}

	public String findPageBean() {
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_yymm");
		ActionContext.getContext().getSession().remove("public_yymm2");
		factNo = (String) ActionContext.getContext().getSession().get("factNo");
		bean = estProSer.findPageBean(20,page, factNo, yymm,yymm2);

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

		bean = estProSer.findPageBean(20,page, factNo, yymm,yymm2);

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
		bean = estProSer.findPageBean(20,page, factNo, yymm,yymm2);

		return result;

	}


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
	
	public void importFile() throws IOException{
		/*factNo="631";
		yymm="201810";*/
		response.setContentType("text/html;charset=utf-8");
		try{
			String path="d:\\Webestpro_backup\\"+new SimpleDateFormat("yyyyMMdd").format(new Date());//Excel文檔存放目錄
			ajaxResult="0";				
			//文件上傳
			if(file!=null){//不為空代表有上傳附檔,不能寫成files.size()>0,否則報空指針
				//File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("KyzexpFile\\"+kyz.getId().getBillNo()));//附檔上傳到項目
				File uploadFile_backup=new File(path);//附檔上傳到D盤(為了避免更新項目時丟失附檔,所在上傳到D盤)			
				if(!uploadFile_backup.exists()){
					uploadFile_backup.mkdirs();
				}																						
						FileInputStream in=new FileInputStream(file);
						FileOutputStream out_backup=new FileOutputStream(uploadFile_backup+"\\"+fileFileName);//備份
						byte[]b=new byte[1024];
						int length=0;
						while((length=in.read(b))>0){
							out_backup.write(b,0,length);//備份
						}																																				
			}
			
			WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");			
			//file=new File("i:\\test.xlsx");
			//Map<String,Object>map=ImportExcel.exportListFromFile(file);
			Map<String,Object>map=ImportExcel.exportListFromFile(new File(path+"\\"+fileFileName));
			List<String>list_factArea=webFactSer.findfactAreaByFactNo(factNo);					
			
			for(String key:map.keySet()){
				if(key==null||!("zd".equals(key)||"tz".equals(key))){
					response.getWriter().print("<script>window.parent.layer.msg('文檔必須包含:暫定版與調整版兩張表格，表格名稱限定為 zd 和 tz')</script>");
					response.getWriter().close();
					break;
					
				}
				
				List<String>list=(List<String>)map.get(key);
				if(!list.get(0).contains("__序號__項目__單位")){				
					response.getWriter().print("<script>window.parent.showDiv();window.parent.layer.msg('表格式不符合要求')</script>");
					//continue;
					response.getWriter().close();
					break;
				}
				List<String>list_factcode=new ArrayList<String>();
				String[] array_head =list.get(0).split("__");
				for(int i=4;i<array_head.length;i++){
					list_factcode.add(array_head[i].trim());
				}
				if(!list_factArea.containsAll(list_factcode)){
					StringBuilder sb=new StringBuilder();
					sb.append("(");					
					for(String factArea:list_factArea){
						sb.append(factArea+" ");
					}
					sb.append(")");
					response.getWriter().print("<script>window.parent.layer.alert('請核對正確的廠別狀態:"+sb.toString()+"',8)</script>");
					break;
				}
				
				List<Webestproduct>list_obj=new ArrayList<Webestproduct>();
				DateFormat frm=new SimpleDateFormat("yyyyMM");
				for(int i=4;i<array_head.length;i++){
					Webestproduct obj=new Webestproduct(new WebestproductId(factNo,array_head[i],frm.parse(yymm),key));
					obj.setMachinepower(Double.valueOf(list.get(1).split(SEPARATOR)[i]));//機台戰力(模/月)
					obj.setEstdays(Double.valueOf(list.get(2).split(SEPARATOR)[i]));//預計生產天數(天)
					obj.setEsteverymodel(Double.valueOf(list.get(3).split(SEPARATOR)[i]));//預計每日上模數(模)
					obj.setEsteverypeople(Double.valueOf(list.get(4).split(SEPARATOR)[i]));//預計每日人數(人)
					obj.setEstmodel(Double.valueOf(list.get(5).split(SEPARATOR)[i]));//預計生產模數(模)
					obj.setEstnum(Double.valueOf(list.get(6).split(SEPARATOR)[i]));//預計生產雙數(雙)
					obj.setEstpay(Double.valueOf(list.get(7).split(SEPARATOR)[i]));//預計請款雙數(雙)
					obj.setEstmoney(Double.valueOf(list.get(8).split(SEPARATOR)[i]));//預計請款金額(USD)
					obj.setHole(Double.valueOf(list.get(9).split(SEPARATOR)[i]));//有效孔位數(孔)
					obj.setTotalhole(Double.valueOf(list.get(10).split(SEPARATOR)[i]));//總機孔(孔)
					obj.setSample(Double.valueOf(list.get(11).split(SEPARATOR)[i]));//工程樣品
					obj.setAccessories(Double.valueOf(list.get(12).split(SEPARATOR)[i]));//輔料
					obj.setOther(Double.valueOf(list.get(13).split(SEPARATOR)[i]));//其它
					
					obj.setUsername(user.getUsername());
					obj.setUsernameUd(user.getUsername());
					list_obj.add(obj);
					estProSer.addMore(list_obj);										
				}												
			}
			response.getWriter().print("<script>window.parent.layer.msg('導入成功',3,1)</script>");
			//response.getWriter().print("<script>alert('導入成功')</script>");
									
		}catch(Exception e){
			System.out.println(e);
			response.getWriter().print("<script>window.parent.layer.msg('導入錯誤',3,3);</script>");
		}
		
	}

}
