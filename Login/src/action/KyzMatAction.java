package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import services.IKyzMatServices;
import services.ISubKyzmatServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmLog;
import entity.KyzMat;
import entity.SubKyzmat;
import entity.SubKyzmatId;
import entity.WebUser;

/**
 * 物料資料管理
 * @author ky2-qhtj
 *
 */
public class KyzMatAction extends ActionSupport implements ServletResponseAware{
	private IKyzMatServices kyzmatSer;
	private ISubKyzmatServices subkyzmatSer;
	private String fromDate;
	private String endDate;
	private KyzMat mat;
	private PageBean bean;
	private int page;
	private String matNo;
	private String typeBno;
	private String typeMno;
	private String typeSno;
	private String factNo;
	private String matCname;
	private List<String>cb_list;
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗
	
	
	public int getBackIndex() {
		return backIndex;
	}
	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}
	public String getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	public List<String> getCb_list() {
		return cb_list;
	}
	public void setCb_list(List<String> cb_list) {
		this.cb_list = cb_list;
	}
	private javax.servlet.http.HttpServletResponse response;
	
	
	public String getMatCname() {
		return matCname;
	}
	public void setMatCname(String matCname) {
		this.matCname = matCname;
	}
	public String getTypeBno() {
		return typeBno;
	}
	public void setTypeBno(String typeBno) {
		this.typeBno = typeBno;
	}
	public String getTypeMno() {
		return typeMno;
	}
	public void setTypeMno(String typeMno) {
		this.typeMno = typeMno;
	}
	public String getTypeSno() {
		return typeSno;
	}
	public void setTypeSno(String typeSno) {
		this.typeSno = typeSno;
	}
	public String getMatNo() {
		return matNo;
	}
	public void setMatNo(String matNo) {
		this.matNo = matNo;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public PageBean getBean() {
		return bean;
	}
	public void setBean(PageBean bean) {
		this.bean = bean;
	}
	public KyzMat getMat() {
		return mat;
	}
	public void setMat(KyzMat mat) {
		this.mat = mat;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
		
	public String getFactNo() {
		return factNo;
	}
	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}
	
	public void setKyzmatSer(IKyzMatServices kyzmatSer) {
		this.kyzmatSer = kyzmatSer;
	}
		
	public void setSubkyzmatSer(ISubKyzmatServices subkyzmatSer) {
		this.subkyzmatSer = subkyzmatSer;
	}
	public String add(){
		try{
			kyzmatSer.add(mat);
			ajaxResult="0";
		}catch(Exception e){
			ajaxResult="1";
			System.out.println(e);
		}
		
		return "add";
	}		
	public String findPageBean(){
		ActionContext.getContext().getApplication().clear();
		//factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=kyzmatSer.findPageBean(20,page, fromDate, endDate,matCname,typeBno,typeMno,typeSno,factNo,matNo);
		return "findPageBean";
	}
	public String findPageBean2(){
		ActionContext.getContext().getApplication().clear();
		bean=kyzmatSer.findPageBean(20,page, fromDate, endDate,matCname,typeBno,typeMno,typeSno,factNo,matNo);
		if(fromDate!=null&&!fromDate.equals("")){
			ActionContext.getContext().getApplication().put("fromdate", fromDate);
		}
		if(endDate!=null&&!endDate.equals("")){
			ActionContext.getContext().getApplication().put("enddate", endDate);
		}
		if(matNo!=null&&!matNo.equals("")){
			ActionContext.getContext().getApplication().put("matNo", matNo.trim());
		}
		if(typeBno!=null&&!typeBno.equals("")){
			ActionContext.getContext().getApplication().put("bNo", typeBno);
		}
		if(typeMno!=null&&!typeMno.equals("")){
			ActionContext.getContext().getApplication().put("mNo", typeMno);
		}
		if(typeSno!=null&&!typeSno.equals("")){
			ActionContext.getContext().getApplication().put("sNo", typeSno);
		}
		if(factNo!=null&&!factNo.equals("")){
			ActionContext.getContext().getApplication().put("factno", factNo);
		}
		if(matCname!=null&&!matCname.equals("")){
			ActionContext.getContext().getApplication().put("matcname", matCname);
		}
		return "findPageBean1";
	}
	public String findPageBean3(){
		fromDate=(String)ActionContext.getContext().getApplication().get("fromdate");
		endDate=(String)ActionContext.getContext().getApplication().get("enddate");
		matNo=(String)ActionContext.getContext().getApplication().get("matNo");
		typeBno=(String)ActionContext.getContext().getApplication().get("bNo");
		typeMno=(String)ActionContext.getContext().getApplication().get("mNo");
		typeSno=(String)ActionContext.getContext().getApplication().get("sNo");
		factNo=(String)ActionContext.getContext().getApplication().get("factno");
		matCname=(String)ActionContext.getContext().getApplication().get("matcname");

		/*if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}*/
		bean=kyzmatSer.findPageBean(20,page, fromDate, endDate,matCname,typeBno,typeMno,typeSno,factNo,matNo);
		String result="findPageBean1";
		if(backIndex==1){
			result="findPageBean";
		}
		return result;
	}
	public String findById(){
		mat=kyzmatSer.findById(matNo);
		return "findById";
	}
	public String delete(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setObj("KyzMat");
		log.setContent(matNo);
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
		kyzmatSer.delete(matNo,log);
		return "delete";
	}
	
	/**
	 * KyzMat打印共用方法
	 * @param list_mat
	 * @throws IOException
	 */
	public void print_all(List<KyzMat> list_mat) throws IOException{
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("���Ƹ��");
		
		HSSFCellStyle cs=wb.createCellStyle();
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_title=wb.createCellStyle();
		HSSFFont font_title=wb.createFont();
		font_title.setFontHeightInPoints((short)20);
		font_title.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cs_title.setFont(font_title);
		cs_title.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_title.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		HSSFCellStyle cs_column=wb.createCellStyle();
		HSSFFont font_column=wb.createFont();
		font_column.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_column.setFontHeightInPoints((short)14);
		cs_column.setFont(font_column);
		cs_column.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_column.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_column.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_column.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_column.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_column.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_column.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		cs_column.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		String title="物料資料報表";
		List<String>list_column=new ArrayList<String>();
		/*list_column.add("序號");
		list_column.add("物料編號");
		list_column.add("物料中文名稱");
		list_column.add("資材分類");
		list_column.add("物料規格");
		list_column.add("物料顏色");
		list_column.add("會計科目");
		list_column.add("采購政策");
		list_column.add("采購單價");
		list_column.add("中文單位");
		list_column.add("幣別");
		list_column.add("商品名稱");
		list_column.add("商品單重");
		list_column.add("大分類");
		list_column.add("中分類");
		list_column.add("小分類");
		list_column.add("創建人");
		list_column.add("創建日期");*/
		
		list_column.add("序號");
		list_column.add("物料編號");
		list_column.add("物料中文名稱");
		list_column.add("物料英文名稱");
		list_column.add("會計科目");
		list_column.add("中文單位");
		list_column.add("英文單位");
		list_column.add("商品代號");
		list_column.add("商品名稱");
		list_column.add("領用單位");
		list_column.add("領用換算率");
		list_column.add("重量單位");
		list_column.add("公司別");
			
				
		/**
		 * 開始打印
		 */
		//打印標題
		HSSFRow row_title=sheet.createRow(0);
		HSSFCell cell_title=row_title.createCell(0);
		cell_title.setCellValue(title);
		cell_title.setCellStyle(cs_title);
		CellRangeAddress cra_title=new CellRangeAddress(0,(short)0,0,(short)list_column.size()-1);
		sheet.addMergedRegion(cra_title);
		for(int i=1;i<list_column.size();i++){
			HSSFCell cell=row_title.createCell(i);
			cell.setCellStyle(cs_title);
		}
		//打印表頭
		HSSFRow row_column=sheet.createRow(2);
		for(int i=0;i<list_column.size();i++){
			sheet.setColumnWidth(i, 4000);
			if(i==1||i==2){
				sheet.setColumnWidth(i, 6000);
			}
			HSSFCell cell=row_column.createCell(i);
			String column=list_column.get(i);
			cell.setCellValue(column);
			cell.setCellStyle(cs_column);
		}
		//打印數據
		for(int i=0;i<list_mat.size();i++){//start for1
			KyzMat mat=list_mat.get(i);
			int index=i+1;
			String matNo=this.getNull_str(mat.getMatNo());
			String matCname=this.getNull_str(mat.getMatCname());
			String matEname=this.getNull_str(mat.getMatEname());
			String acctNo=this.getNull_str(mat.getAcctNo());
			String eunit=this.getNull_str(mat.getEunit());
			String cunit=this.getNull_str(mat.getCunit());			
			String smartno=this.getNull_str(mat.getSmatNo());
			String smatName=this.getNull_str(mat.getSmatName());
			String lunit=this.getNull_str(mat.getLunit());
			Double clrate=this.getNull_db(mat.getClRate());
			String unitweight=this.getNull_str(mat.getUnitWeit());
			String compno=this.getNull_str(mat.getCompNo());			
			HSSFRow row=sheet.createRow(3+i);
			for(int j=0;j<list_column.size();j++){//start for2
				HSSFCell cell=row.createCell(j);
				switch(j){
				case 0:
					cell.setCellValue(index);
					break;
				case 1:
					cell.setCellValue(matNo);
					break;
				case 2:
					cell.setCellValue(matCname);
					break;
				case 3:
					cell.setCellValue(matEname);
					break;
				case 4:
					cell.setCellValue(acctNo);
					break;
				case 5:
					cell.setCellValue(eunit);
					break;
				case 6:
					cell.setCellValue(cunit);
					break;
				case 7:
					cell.setCellValue(smartno);
					break;
				case 8:
					cell.setCellValue(smatName);
					break;
				case 9:
					cell.setCellValue(lunit);
					break;
				case 10:
					cell.setCellValue(clrate);
					break;
				case 11:
					cell.setCellValue(unitweight);
					break;
				case 12:
					cell.setCellValue(compno);
					break;				
				}
				cell.setCellStyle(cs);
			}//end for2
		}//end for1
		ServletOutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		title=title+".xls";
		String fileName="";
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");
		if(msie>0){
			fileName=java.net.URLEncoder.encode(title,"utf-8");
		}else{
			fileName=new String(title.getBytes("utf-8"),"iso8859-1");
		}
		response.setHeader("Content-disposition","attachment;filename="+fileName);
		wb.write(os);
		os.close();
	}
	/**
	 * 打印搜索的內容(KyzMat)
	 * @throws IOException
	 */
	public void print() throws IOException{
		List<KyzMat>list_mat=kyzmatSer.findWithNoPage(fromDate, endDate, matCname, typeBno, typeMno, typeSno,factNo,matNo);
		this.print_all(list_mat);
	}
	/**
	 * 打印單個KyzMat
	 * @throws IOException
	 */
	public void print_one() throws IOException{
		List<KyzMat>list_mat=new ArrayList<KyzMat>();
		KyzMat mat=kyzmatSer.findById(matNo);
		list_mat.add(mat);
		this.print_all(list_mat);
		
	}
	/**
	 * 打印多個選中的KyzMat
	 * @throws IOException
	 */
	public void print_select() throws IOException{
		List<KyzMat>list_mat=new ArrayList<KyzMat>();
		if(cb_list!=null&&cb_list.size()>0){
			for(int i=0;i<cb_list.size();i++){
				String matNo=cb_list.get(i);
				KyzMat mat=kyzmatSer.findById(matNo);
				list_mat.add(mat);
			}
		}		
		this.print_all(list_mat);
	}
	
	/**
	 * SubKyzmat打印共用方法
	 * @throws IOException
	 */
	public void print_all_sub(List<SubKyzmat>list_mat) throws IOException{
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("物料資料");
		
		HSSFCellStyle cs=wb.createCellStyle();
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_title=wb.createCellStyle();
		HSSFFont font_title=wb.createFont();
		font_title.setFontHeightInPoints((short)20);
		font_title.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cs_title.setFont(font_title);
		cs_title.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_title.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		HSSFCellStyle cs_column=wb.createCellStyle();
		HSSFFont font_column=wb.createFont();
		font_column.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_column.setFontHeightInPoints((short)14);
		cs_column.setFont(font_column);
		cs_column.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_column.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_column.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_column.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_column.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_column.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_column.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		cs_column.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		String title="物料資料報表2";
		List<String>list_column=new ArrayList<String>();				
		list_column.add("序號");
		list_column.add("物料編號");
		list_column.add("物料中文名稱");
		list_column.add("物料英文名稱");
		list_column.add("會計科目");
		list_column.add("中文單位");
		list_column.add("英文單位");
		list_column.add("商品代號");
		list_column.add("商品名稱");
		list_column.add("領用單位");
		list_column.add("領用換算率");
		list_column.add("重量單位");
		list_column.add("公司別");					
						
		/**
		 * 開始打印
		 */
		//打印標題
		HSSFRow row_title=sheet.createRow(0);
		HSSFCell cell_title=row_title.createCell(0);
		cell_title.setCellValue(title);
		cell_title.setCellStyle(cs_title);
		CellRangeAddress cra_title=new CellRangeAddress(0,(short)0,0,(short)list_column.size()-1);
		sheet.addMergedRegion(cra_title);
		for(int i=1;i<list_column.size();i++){
			HSSFCell cell=row_title.createCell(i);
			cell.setCellStyle(cs_title);
		}
		//打印表頭
		HSSFRow row_column=sheet.createRow(2);
		for(int i=0;i<list_column.size();i++){
			sheet.setColumnWidth(i, 4000);
			if(i==1||i==2){
				sheet.setColumnWidth(i, 6000);
			}
			HSSFCell cell=row_column.createCell(i);
			String column=list_column.get(i);
			cell.setCellValue(column);
			cell.setCellStyle(cs_column);
		}
		//打印數據
		for(int i=0;i<list_mat.size();i++){//start for1
			SubKyzmat mat=list_mat.get(i);
			int index=i+1;
			String matNo=this.getNull_str(mat.getId().getKyzMat().getMatNo());
			String matCname=this.getNull_str(mat.getId().getKyzMat().getMatCname());
			String matEname=this.getNull_str(mat.getId().getKyzMat().getMatEname());			
			String acctNo=this.getNull_str(mat.getId().getKyzMat().getAcctNo());			
			String cunit=this.getNull_str(mat.getId().getKyzMat().getCunit());
			String eunit=this.getNull_str(mat.getId().getKyzMat().getEunit());
			String smartno=this.getNull_str(mat.getId().getKyzMat().getSmatNo());
			String smartname=this.getNull_str(mat.getId().getKyzMat().getSmatName());
			String lunit=this.getNull_str(mat.getId().getKyzMat().getLunit());
			Double clrate=this.getNull_db(mat.getId().getKyzMat().getClRate());
			String unitweight=this.getNull_str(mat.getId().getKyzMat().getUnitWeit());
			String compno=this.getNull_str(mat.getId().getKyzMat().getCompNo());			
			HSSFRow row=sheet.createRow(3+i);
			for(int j=0;j<list_column.size();j++){//start for2
				HSSFCell cell=row.createCell(j);
				switch(j){
				case 0:
					cell.setCellValue(index);
					break;
				case 1:
					cell.setCellValue(matNo);
					break;
				case 2:
					cell.setCellValue(matCname);
					break;
				case 3:
					cell.setCellValue(matEname);
					break;			
				case 4:
					cell.setCellValue(acctNo);
					break;
				case 5:
					cell.setCellValue(cunit);
					break;
				case 6:
					cell.setCellValue(eunit);
					break;
				case 7:
					cell.setCellValue(cunit);
					break;
				case 8:
					cell.setCellValue(smartno);
					break;
				case 9:
					cell.setCellValue(smartname);
					break;
				case 10:
					cell.setCellValue(lunit);
					break;
				case 11:
					cell.setCellValue(clrate);
					break;
				case 12:
					cell.setCellValue(unitweight);
					break;
				case 13:
					cell.setCellValue(compno);
					break;				
				}
				cell.setCellStyle(cs);
			}//end for2
		}//end for1
		ServletOutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		title=title+".xls";
		String fileName="";
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");
		if(msie>0){
			fileName=java.net.URLEncoder.encode(title,"utf-8");
		}else{
			fileName=new String(title.getBytes("utf-8"),"iso8859-1");
		}
		response.setHeader("Content-disposition","attachment;filename="+fileName);
		wb.write(os);
		os.close();
	}
	/**
	 * 打印搜索的SubKyzmat
	 * @throws IOException
	 */
	public void print_sub() throws IOException{
		List<SubKyzmat>list_mat=subkyzmatSer.findWithNoPage(fromDate, endDate, matCname, typeBno, typeMno, typeSno,factNo,matNo);
		this.print_all_sub(list_mat);
	}
	/**
	 * 打印單個SubKyzmat
	 * 引用print_one(),因為都是同一個matNo的物料資料
	 * @throws IOException 
	 */
	public void print_one_sub() throws IOException{
		this.print_one();
	}
	/**
	 * 打印選中的SubKyzmat
	 * 引用print_select(),因為都是相同的多個matNo的物料資料
	 * @throws IOException 
	 */
	public void print_select_sub() throws IOException{
		this.print_select();
	}
	

	
	/**
	 * 如果為null,則返加"----"
	 * 判斷String
	 */
	public String getNull_str(String str){
		String result_str="";
		if(str==null||str.equals("")){
			result_str="----";			
		}else{
			result_str=str;
		}
		return result_str;		
	}
	/**
	 * 如果為null,則返回0
	 * 判斷Double
	 */
	public Double getNull_db(Double db){
		Double result_db=0.0;
		if(db!=null){
			result_db=db;
		}
		return result_db;
	}
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}

}
