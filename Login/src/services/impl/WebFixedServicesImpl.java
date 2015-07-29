package services.impl;

import java.util.ArrayList;
import java.util.List;

import dao.IWebFactDao;
import dao.IWebFixedDao;
import entity.WebFixed;
import services.IWebFixedServices;
import util.PageBean;

public class WebFixedServicesImpl implements IWebFixedServices {
	private IWebFixedDao fixDao;

	private IWebFactDao webFactDao;

	public void setWebFactDao(IWebFactDao webFactDao) {
		this.webFactDao = webFactDao;
	}

	public void setFixDao(IWebFixedDao fixDao) {
		this.fixDao = fixDao;
	}

	public void addWebFixed(WebFixed fix) {
		// TODO Auto-generated method stub
		fixDao.addWebFixed(fix);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String yymm_s,String lostmk) {
		// TODO Auto-generated method stub
		return fixDao.findFixWithPage(pageSize, page, factNo, yymm,yymm_s,lostmk);
	}

	public WebFixed findById(String id) {
		// TODO Auto-generated method stub
		return fixDao.findById(id);
	}

	public void deleteWebFixed(String id) {
		// TODO Auto-generated method stub
		fixDao.deleteWebFixed(id);
	}

	public List findById2(String id) {
		// TODO Auto-generated method stub
		return fixDao.findById2(id);
	}

	public List findAllFixedassetsId() {
		// TODO Auto-generated method stub
		return fixDao.findAllFixedassetsId();
	}

	public String makeSssetscoding(String factNo, String majorid, String yymm) {
		// TODO Auto-generated method stub
		StringBuffer assetscoding = new StringBuffer();// �]�s�Ǹ�=�t�O�N��+�~��+�j�����N��+�Ǹ�
		String factCode = webFactDao.findByFactNo(factNo);
		assetscoding.append(factCode);
		assetscoding.append(yymm.substring(2, 6));
		assetscoding.append(majorid);
		List assetscoding_list = fixDao.findById2(majorid);
				
		List numlist = new ArrayList();
		for (int i = 0; i < assetscoding_list.size(); i++) {
			String temp = (String) assetscoding_list.get(i);
			//���ǰ]���s���O"�۰ʥͦ�"���A�n�ư��F�_�h�b�ͦ��]���s���|����
			if(!temp.equals("�۰ʥͦ�")){
				String num = temp.substring(temp.length() - 4, temp.length());
				int num2 = Integer.parseInt(num);
				numlist.add(num2);
			}			
		}
		int maxNum = 0;
		for (int k = 0; k < numlist.size(); k++) {
			if ((Integer) numlist.get(k) > maxNum) {
				maxNum = (Integer) numlist.get(k);
			}
		}
		if (((maxNum + 1) + "").length() == 1) {
			assetscoding.append("000" + (maxNum + 1));
		}
		if (((maxNum + 1) + "").length() == 2) {
			assetscoding.append("00" + (maxNum + 1));
		}
		if (((maxNum + 1) + "").length() == 3) {
			assetscoding.append("0" + (maxNum + 1));
		}
		if (((maxNum + 1) + "").length() == 4) {
			assetscoding.append(maxNum + 1);
		}

		return assetscoding.toString();
	}

	public List<WebFixed> findByFactNo(String factNo, String yymm,String yymm_s,String lostmk) {
		// TODO Auto-generated method stub
		return fixDao.findByFactNo(factNo, yymm,yymm_s,lostmk);
	}

}
