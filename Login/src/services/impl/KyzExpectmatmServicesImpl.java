package services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import dao.IKyzExpectmatmDao;
import entity.KyzExpectmatm;
import entity.KyzExpectmatmId;
import entity.KyzExpectmats;
import services.IKyzExpectmatmServices;
import util.PageBean;

public class KyzExpectmatmServicesImpl implements IKyzExpectmatmServices {
	private IKyzExpectmatmDao kyzDao;

	/*
	 * public void setKyzDao(IKyzExpectmatmDao kyzDao) { kyzDao = kyzDao; }
	 */
	public void setKyzDao(IKyzExpectmatmDao kyzDao) {
		this.kyzDao = kyzDao;
	}

	public void add(KyzExpectmatm kyz) {
		// TODO Auto-generated method stub
		kyzDao.add(kyz);
	}

	public String makeBillNo(String factNo, String timeCreate) {
		StringBuffer billNo = new StringBuffer();
		billNo.append("EM");
		billNo.append(factNo);
		/*
		 * SimpleDateFormat format=new SimpleDateFormat("yyMMdd"); String
		 * temp=format.format(new Date());
		 */
		String temp = timeCreate.substring(2);
		billNo.append(temp);
		List<String> list = kyzDao.findBillNo(factNo, timeCreate);
		List<Integer> numlist = new ArrayList();
		if (list.size() == 0) {
			billNo.append("01");
		} else {
			int maxNum = 0;
			for (int i = 0; i < list.size(); i++) {				
				String temp_num = list.get(i).toString();
				if(!temp_num.contains("-")){
					String num_str = temp_num.substring(temp_num.length() - 2,
							temp_num.length());
					int num = Integer.parseInt(num_str);
					numlist.add(num);
				}				
			}
			for (int j = 0; j < numlist.size(); j++) {
				if (numlist.get(j) > maxNum) {
					maxNum = numlist.get(j);
				}
			}
			if (((maxNum + 1) + "").length() == 1) {
				billNo.append("0" + (maxNum + 1));
			}
			if (((maxNum + 1) + "").length() == 2) {
				billNo.append((maxNum + 1) + "");
			}
		}

		return billNo.toString();
	}

	public List<KyzExpectmatm> getList(String memoSmk) {
		return kyzDao.getList(memoSmk);
	}


	public KyzExpectmatm findById(KyzExpectmatmId id) {
		// TODO Auto-generated method stub
		return kyzDao.findById(id);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String visaSort,String billNo,String userNm,String timeCreate,String timeCreate2) {
		// TODO Auto-generated method stub
		return kyzDao.findFixWithPage(pageSize, page, factNo, visaSort,billNo,userNm,timeCreate,timeCreate2);
	}

	public void delete(KyzExpectmatmId id) {
		// TODO Auto-generated method stub
		kyzDao.delete(id);
	}

	public List<KyzExpectmatm> findByFactNo(String factno) {
		// TODO Auto-generated method stub
		return kyzDao.findByFactNo(factno);
	}

	public List<KyzExpectmatm> findById_Print(KyzExpectmatmId id) {
		// TODO Auto-generated method stub
		return kyzDao.findById_Print(id);
	}

	public KyzExpectmatm findById2(String billNo) {
		// TODO Auto-generated method stub
		return kyzDao.findById2(billNo);
	}

	public String findTitleByBillno(String billNo) {
		// TODO Auto-generated method stub
		return kyzDao.findTitleByBillno(billNo);
	}

	public List<Object[]> findTitle(String factNo) {
		// TODO Auto-generated method stub
		return kyzDao.findTitle(factNo);
	}

	public KyzExpectmatm findById(String factNo, String billNo) {
		// TODO Auto-generated method stub
		return kyzDao.findById(factNo, billNo);
	}

	public List<KyzExpectmatm> findBefor2Month() {
		// TODO Auto-generated method stub
		return kyzDao.findBefor2Month();
	}

	public void addLarge(List<KyzExpectmatm> list) {
		// TODO Auto-generated method stub
		kyzDao.addLarge(list);
	}
	

	


	




}
