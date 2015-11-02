package services.impl;

import java.util.ArrayList;
import java.util.List;

import dao.IKyzContactLetterDao;
import entity.KyzContactletter;
import services.IKyzContactLetterServices;
import util.PageBean;

public class KyzContactLetterServicesImpl implements IKyzContactLetterServices{
	private IKyzContactLetterDao kyzletterDao;
	

	public void setKyzletterDao(IKyzContactLetterDao kyzletterDao) {
		this.kyzletterDao = kyzletterDao;
	}

	public void add(KyzContactletter letter) {
		// TODO Auto-generated method stub
		kyzletterDao.add(letter);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,String visaSort,String billNo,String userNm,String timeCreate,String timeCreate2) {
		// TODO Auto-generated method stub
		return kyzletterDao.findPageBean(pageSize, page, factNo, visaSort, billNo, userNm, timeCreate, timeCreate2);
	}
	
	public String makeBillNo(String factNo, String timeCreate) {
		StringBuffer billNo = new StringBuffer();
		billNo.append("CM");
		billNo.append(factNo);
		/*
		 * SimpleDateFormat format=new SimpleDateFormat("yyMMdd"); String
		 * temp=format.format(new Date());
		 */
		String temp = timeCreate.substring(2);
		billNo.append(temp);
		List<String> list = kyzletterDao.findBillNo(factNo, timeCreate);
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

	public KyzContactletter findById(String factNo, String billNo) {
		// TODO Auto-generated method stub
		return kyzletterDao.findById(factNo, billNo);
	}

	public void delete(String factNo, String billNo) {
		// TODO Auto-generated method stub
		kyzletterDao.delete(factNo, billNo);
	}

	public String findTitleByBillno(String billNo) {
		// TODO Auto-generated method stub
		return kyzletterDao.findTitleByBillno(billNo);
	}

	public List<String[]> findTitle(String factNo) {
		// TODO Auto-generated method stub
		return kyzletterDao.findTitle(factNo);
	}

}
