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

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.*;
import services.*;
import util.JasperHelper;
import util.Page;
import util.PageBean;
import util.readexcel;

public class WebSideAction extends ActionSupport {
	private WebSide side;
	private IWebSideServices sideSer;
	private WebSideId id;
	private String year;
	private String month;
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

	public WebSideId getId() {
		return id;
	}

	public void setId(WebSideId id) {
		this.id = id;
	}

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public WebSide getSide() {
		return side;
	}

	public void setSide(WebSide side) {
		this.side = side;
	}

	public void setSideSer(IWebSideServices sideSer) {
		this.sideSer = sideSer;
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

	private String billNo;

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	private String printType;
	private List<WebSide> listmat;

	public String getPrintType() {
		return printType;
	}

	public void setPrintType(String printType) {
		this.printType = printType;
	}

	public List<WebSide> getListmat() {
		return listmat;
	}

	public void setListmat(List<WebSide> listmat) {
		this.listmat = listmat;
	}

	private int yz;

	public void setYz(int yz) {
		this.yz = yz;
	}

	/**
	 * 添加數據
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String addSide() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Date d = null;
		String date = df.format(new Date());
		StringBuffer ym = new StringBuffer();
		ym.append(year);
		ym.append(month);
		if (side.getId().getBillNo() == null) {
			StringBuffer index = new StringBuffer();
			String typeId = ActionContext.getContext().getSession()
					.get("typeid").toString();
			index.append(typeId);
			// index.append("QQ");
			String factno = side.getId().getFactNo();
			index.append(factno);

			index.append(date);

			Random rand = new Random();
			int num = 0;
			this.list();

			num = rand.nextInt(1000);
			String temp = (index.append(num)).toString();
			side.getId().setBillNo(temp);

			d = format.parse(ym.toString());
			side.getId().setYymm(d);
			side.setDateTime(date);
			sideSer.addside(side);
		} else {
			d = format.parse(yymm);
			side.getId().setYymm(d);
			side.setDateTime(date);
			sideSer.addside(side);
		}
		return "addSide";
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
		List<WebSide> bList = sideSer.selSide(factNos, yymm, pages.getPage(),
				pages.getRows());
		if (bList.size() == 0) {
			pages.setPage(0);
		}
		ActionContext.getContext().getSession().put("sideList", bList);
		pages.setCount(sideSer.totlePage(factNos, yymm));
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
	 * 
	 * @return
	 */
	public String findById() {
		List list = webFactSer.findFactCodeByFactNo(id.getFactNo());
		ActionContext.getContext().getSession().put("factAreas_other", list);
		side = sideSer.findById(id);
		return "findById";
	}

	public String findPageBean() {

		return getList();
	}

	public String delete2() {
		sideSer.delete(id);
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
						WebSide side = new WebSide();
						WebSideId id = new WebSideId();
						for (int g = 0; g < names.size(); g++) {
							String name = names.get(g);
							if (mes[g] != null || !mes[g].equals("")) {
								if (name.trim().equals("年月")) {
									SimpleDateFormat format = new SimpleDateFormat(
											"yyyyMM");
									Date s1 = format.parse(mes[g]);
									id.setYymm(s1);
									side.setId(id);
								} else if (name.trim().equals("廠別狀態")) {
									side.setFactCode(mes[g]);
								} else if (name.trim().equals("邊料(kg)")) {
									side.setSideWeit(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("平均邊料重(g)")) {
									side.setSideAvg(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("邊料率(%)")) {
									side.setSideExp(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("邊料金額(USD)")) {
									side.setSideCash(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("不良品雙數")) {
									side.setBadCount(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("不良重量(Kg)")) {
									side.setBadWeit(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("不良率(%)")) {
									side.setBadExp(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("不良品金額(USD)")) {
									side.setBadCash(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("不良加邊料重量")) {
									side.setBadSidew(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("不良加邊料金額")) {
									side.setBadSidec(Double.valueOf(mes[g])
											.doubleValue());
								} else if (name.trim().equals("日期起")) {
									side.setDateB(mes[g]);
								} else if (name.trim().equals("日期止")) {
									side.setDateE(mes[g]);
								} else if (name.trim().equals("鎖定")) {
									side.setLockMk(mes[g]);
								} else if (name.trim().equals("輸入者工號")) {
									side.setUserNo(mes[g]);
								} else if (name.trim().equals("輸入時間")) {
									side.setDateTime(mes[g]);
								} else if (name.trim().equals("廠別")) {
									id.setFactNo(mes[g]);
									side.setId(id);
								} else if (name.trim().equals("單號")) {
									id.setBillNo(mes[g]);
									side.setId(id);
								}
							}
						}
						sideSer.addside(side);
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
		bean=sideSer.findPageBean(25, page, factNo, yymm);
		return "showList";
	}
	public String getList2(){
		ActionContext.getContext().getApplication().clear();
		if(factNo!=null&&!factNo.equals("")){
			ActionContext.getContext().getApplication().put("webside-factno", factNo);
		}
		if(yymm!=null&&!yymm.equals("")){
			ActionContext.getContext().getApplication().put("webside-yymm", yymm);
		}
		bean=sideSer.findPageBean(25, page, factNo, yymm);
		return "showList1";
	}
	public String getList3(){
		factNo=(String)ActionContext.getContext().getApplication().get("webside-factno");
		yymm=(String)ActionContext.getContext().getApplication().get("webside-yymm");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=sideSer.findPageBean(25, page, factNo, yymm);
		return "showList1";
	}

}
