package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import services.IWebCpStoreServices;
import services.IWebFactServices;
import util.JasperHelper;
import util.Page;
import util.PageBean;
import util.readexcel;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.*;

public class WebCpStoreAction extends ActionSupport {
	private WebCpstore store;
	private IWebCpStoreServices storeSer;
	private WebCpstoreId id;
	private String year;
	private String month;
	private List<WebCpstore> cpstorelist;
	private PageBean bean;
	private IWebFactServices webFactSer;
	private File[] files;
	private String[] filesFileName;
	private String[] filesContentType;
	private String message = "你已成功上传文件";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public String[] getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String[] filesFileName) {
		this.filesFileName = filesFileName;
	}

	public String[] getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(String[] filesContentType) {
		this.filesContentType = filesContentType;
	}

	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}

	public PageBean getBean() {
		return bean;
	}

	public void setBean(PageBean bean) {
		this.bean = bean;
	}

	public List<WebCpstore> getCpstorelist() {
		return cpstorelist;
	}

	public void setCpstorelist(List<WebCpstore> cpstorelist) {
		this.cpstorelist = cpstorelist;
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

	public WebCpstoreId getId() {
		return id;
	}

	public void setId(WebCpstoreId id) {
		this.id = id;
	}

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public WebCpstore getStore() {
		return store;
	}

	public void setStore(WebCpstore store) {
		this.store = store;
	}

	public void setStoreSer(IWebCpStoreServices storeSer) {
		this.storeSer = storeSer;
	}

	private String factNo;

	public String getFactNo() {
		return factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	private String yymm;

	public String getYymm() {
		return yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

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

	public String billNo;

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	private String printType;

	public String getPrintType() {
		return printType;
	}

	public void setPrintType(String printType) {
		this.printType = printType;
	}

	private int yz;

	public void setYz(int yz) {
		this.yz = yz;
	}

	/**
	 * 添加與成品庫存
	 * 
	 * @throws ParseException
	 */
	public String addWebCpStore() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Date d = null;
		String date = df.format(new Date());
		StringBuffer ym = new StringBuffer();
		ym.append(year);
		ym.append(month);
		if (store.getId().getBillNo() == null) {
			StringBuffer index = new StringBuffer();
			String typeId = ActionContext.getContext().getSession()
					.get("typeid").toString();
			index.append(typeId);
			// index.append("QQ");
			String factno = store.getId().getFactNo();
			index.append(factno);

			index.append(date);

			Random rand = new Random();
			int num = 0;
			this.list();

			num = rand.nextInt(1000);
			String temp = (index.append(num)).toString();
			store.getId().setBillNo(temp);

			d = format.parse(ym.toString());
			store.getId().setYymm(d);
			store.setDateTime(date);
			storeSer.addWebCpStore(store);

		} else {
			d = format.parse(yymm);
			store.getId().setYymm(d);
			store.setDateTime(date);
			storeSer.addWebCpStore(store);
		}

		return "addWebCpStore";
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
		List<WebCpstore> bList = storeSer.selCpstore(factNos, yymm,
				pages.getPage(), pages.getRows());
		if (bList.size() == 0) {
			pages.setPage(0);
		}
		ActionContext.getContext().getSession().put("cpstoreList", bList);
		pages.setCount(storeSer.totlePage(factNos, yymm));
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
	public String findById() {
		List list = webFactSer.findFactCodeByFactNo(id.getFactNo());
		ActionContext.getContext().getSession().put("factAreas_other", list);
		store = storeSer.findById(id);
		return "findById";
	}

	public String pdf() {
		return "pdf";
	}

	public String findPageBean() {

		return getList();
	}

	public String delete2() {
		storeSer.delete(id);
		return getList();
	}

	public String excel() throws Exception {
		readexcel s = new readexcel();
		String path = ServletActionContext.getRequest().getRealPath(
				"/uploadfile");
		try {
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				FileInputStream inputStream = new FileInputStream(f);
				FileOutputStream outputStream = new FileOutputStream(path
						+ "\\" + filesFileName[i]);
				byte[] buf = new byte[1024];
				int length = 0;
				while ((length = inputStream.read(buf)) != -1) {
					outputStream.write(buf, 0, length);
				}
				inputStream.close();
				outputStream.close();
				outputStream.flush();
				List<String[]> infoList = s
						.fill(path + "\\" + filesFileName[i]);
				String[] mes = null;
				List<String> names = new ArrayList<String>();
				for (int b = 0; b < infoList.size(); b++) {
					mes = infoList.get(b);
					if (b < 1) {
						for (int j = 0; j < mes.length; j++) {
							if (j > mes.length) {
								break;
							}
							names.add(mes[j]);
						}
					} else {
						WebCpstore cpstore = new WebCpstore();
						WebCpstoreId id = new WebCpstoreId();

						for (int g = 0; g < names.size(); g++) {
							String name = names.get(g);
							if (mes[g] != null || !mes[g].equals("")) {
								if (name.trim().equals("年月")) {
									SimpleDateFormat format = new SimpleDateFormat(
											"yyyyMM");
									Date s1 = format.parse(mes[g]);
									id.setYymm(s1);
									cpstore.setId(id);
								} else if (name.trim().equals("廠別狀態")) {
									cpstore.setFactCode(mes[g]);
								} else if (name.trim().equals("上月成倉庫存")) {
									cpstore.setCpstoreB(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("上月無訂單庫存")) {
									cpstore.setNoodrB(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("上月已出未請數")) {
									cpstore.setNoinvB(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("本月 生產雙數")) {
									cpstore.setCpT(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("本月成倉庫存數")) {
									cpstore.setCpstoreT(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("本月無訂單庫存")) {
									cpstore.setNoodrT(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("本月已出未請數")) {
									cpstore.setNoinvT(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("本應請款數")) {
									cpstore.setInvT(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("本月實際請款數")) {
									cpstore.setInvactT(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("生產與請款差異數")) {
									cpstore.setCpinvExp(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("廠客補樣品未請數")) {
									cpstore.setOtherT(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("無形差異")) {
									cpstore.setOtherLoss(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("日期起")) {
									cpstore.setDateB(mes[g]);
								} else if (name.trim().equals("日期止")) {
									cpstore.setDateE(mes[g]);
								} else if (name.trim().equals("鎖定")) {
									cpstore.setLockMk(mes[g]);
								} else if (name.trim().equals("輸入者工號")) {
									cpstore.setUserNo(mes[g]);
								} else if (name.trim().equals("輸入時間")) {
									cpstore.setDateTime(mes[g]);
								} else if (name.trim().equals("廠別")) {
									id.setFactNo(mes[g]);
									cpstore.setId(id);
								} else if (name.trim().equals("單號")) {
									id.setBillNo(mes[g]);
									cpstore.setId(id);
								}
							}
						}
						storeSer.addWebCpStore(cpstore);
					}
				}
				deleteFile(path + "\\" + filesFileName[i], files[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "对不起,文件上传失败了!!!!";
		}
		return "upload";
	}

	public void deleteFile(String path, File files) {
		if (files.isFile() && files.exists()) {
			files.delete();
		}
		File file = new File(path);
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}
	public String getList(){
		ActionContext.getContext().getApplication().clear();
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=storeSer.findPageBean(25, page, factNo, yymm);
		return "showList";
	}
	public String getList2(){
		ActionContext.getContext().getApplication().clear();
		if(factNo!=null&&!factNo.equals("")){
			ActionContext.getContext().getApplication().put("webcpstore-factno", factNo);
		}
		if(yymm!=null&&!yymm.equals("")){
			ActionContext.getContext().getApplication().put("webcpstore-yymm", yymm);
		}
		bean=storeSer.findPageBean(25, page, factNo, yymm);
		return "showList1";
	}
	public String getList3(){
		factNo=(String)ActionContext.getContext().getApplication().get("webcpstore-factno");
		yymm=(String)ActionContext.getContext().getApplication().get("webcpstore-yymm");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=storeSer.findPageBean(25, page, factNo, yymm);
		return "showList1";
	}

}
