package services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import dao.IWebYieldDataDao;
import entity.WebYieldData;
import entity.WebYieldDataId;
import entity.WebYieldDataLog;
import services.IWebYieldDataServices;
import util.PageBean;

public class WebYieldDataServicesImple implements IWebYieldDataServices {
	private IWebYieldDataDao dataDao;

	public void setDataDao(IWebYieldDataDao dataDao) {
		this.dataDao = dataDao;
	}

	public List<WebYieldData> findDataByFactcode(String factno,
			String factcode, String yymm) {
		// TODO Auto-generated method stub
		return dataDao.findDataByFactcode(factno, factcode, yymm);
	}

	public void addYdata(WebYieldData data) {
		// TODO Auto-generated method stub
		dataDao.addYdata(data);
	}

	public WebYieldData findById(WebYieldDataId id) {
		// TODO Auto-generated method stub
		return dataDao.findById(id);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return dataDao.findYdataWithPage(pageSize, page, factNo, yymm);
	}

	public void delete(WebYieldDataId id) {
		// TODO Auto-generated method stub
		dataDao.delete(id);
	}

	public List<WebYieldData> findAByFactNo(String factNo) {
		// TODO Auto-generated method stub
		return dataDao.findAByFactNo(factNo);
	}

	public List<WebYieldData> selectYDate(String factNo, String yymm, int page,
			int rows) {
		// TODO Auto-generated method stub
		return null;
	}

	public int totlePage(String factNo, String yymm) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<WebYieldData> getAllWithNoPage(String factNo, String yymm) {
		// TODO Auto-generated method stub
		return dataDao.getAllWithNoPage(factNo, yymm);
	}

	public PageBean findAllYDataForMonth(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return dataDao.findAllYDataForMonth(pageSize, page, factNo, yymm);
	}

	public List<WebYieldData> findNullYdata(String factno, String factcode,
			String yymm) {
		// TODO Auto-generated method stub
		return dataDao.findNullYdata(factno, factcode, yymm);
	}

	public List findNullYdata(String yymm) {
		// TODO Auto-generated method stub
		return dataDao.findNullYdata(yymm);
	}

	public Object[] totalWithFactCode(String factCode, String yymmdd) {
		// TODO Auto-generated method stub
		return dataDao.totalWithFactCode(factCode, yymmdd);
	}

	public Object[] testireport(String factno, String factcode, String yymm) {
		// TODO Auto-generated method stub
		return dataDao.testireport(factno, factcode, yymm);
	}

	public void addYdate_log(WebYieldDataLog log) {
		// TODO Auto-generated method stub
		dataDao.addYdate_log(log);
	}

	public List<String[]> getFactPrint(String date) {
		// TODO Auto-generated method stub
		return dataDao.getFactPrint(date);
	}
	
	/**
	 * 只顯示要顯示的廠別狀態
	 */
	public List<String[]> getFactPrint_show(String date) {
		// TODO Auto-generated method stub
		return dataDao.getFactPrint_show(date);
	}

	public List<WebYieldData> findDataByFactcode2(String factno,
			String factcode, String firstyymmdd, String lastyymmdd) {
		// TODO Auto-generated method stub
		return dataDao.findDataByFactcode2(factno, factcode, firstyymmdd,
				lastyymmdd);
	}

	/*public boolean check(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		Boolean flag=false;
		DateFormat format=new SimpleDateFormat("yyyyMMdd");
		Date date=null;
		try {
			date=format.parse(yymm);
			WebYieldDataId id=new WebYieldDataId();
			id.setFactNo(factNo);
			id.setFactCode(factCode);
			id.setYymmdd(date);
			WebYieldData ydata=this.findById(id);
			if(ydata!=null){
				flag=true;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebYieldData>list=this.findAByFactNo(factNo);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++){
				if (factCode.equals(list.get(i).getId().getFactCode())
						&& factNo.equals(list.get(i).getId().getFactNo())
						&& date.equals(list.get(i).getId().getYymmdd())) {																					
                    flag=true;
					break;
				}		    
	       }
      }		
		return flag;
	}*/
	public String check(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String result="0";
		Date today=new Date();
		DateFormat format=new SimpleDateFormat("yyyyMMdd");
		try {
			long from=format.parse(yymm).getTime();//選擇時間
			long to=today.getTime();//今天時間
			long betweenDay=(to-from)/(1000*3600*24);//相差時間
			
			Date date=format.parse(yymm);
			Calendar cal=Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, -1);
			String lastdate=format.format(cal.getTime());
			Date lastdate2=format.parse(lastdate);
			WebYieldDataId id_last=new WebYieldDataId();
			WebYieldDataId id=new WebYieldDataId();
			id.setFactNo(factNo);
			id.setFactCode(factCode);
			id.setYymmdd(date);
			
			id_last.setFactNo(factNo);
			id_last.setFactCode(factCode);
			id_last.setYymmdd(lastdate2);
			WebYieldData ydata_last=this.findById(id_last);
			WebYieldData ydata=this.findById(id);
			if(ydata_last==null){
				//1為提示輸入前天數據，2為提示存在當前數據，0為跳過不用提示
				result="1";
				//如果大於前21天的就不提示輸入
				if(betweenDay>21){
					//如果跳過提示後，有可以出現當前數據不為空，所以也要判斷
					if(ydata!=null){
						result="2";
					}else{
						result="0";
					}					
				}
			}else{
				if(ydata!=null){					
					result = "2";
				}
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	

	public Object[] getSumWebYieldDate(String factNo, String factCode,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return dataDao.getSumWebYieldDate(factNo, factCode, startDate, endDate);
	}

	public List<WebYieldData> findYdateSdateToEnddate(String factNo,
			String factCode, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return dataDao.findYdateSdateToEnddate(factNo, factCode, startDate, endDate);
	}

	public List<WebYieldData> findYdate(String factNo, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return dataDao.findYdate(factNo, startDate, endDate);
	}



}
