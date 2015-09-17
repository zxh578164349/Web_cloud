package services.impl;

import java.util.List;

import dao.IKyzVisaFlowDao;
import entity.KyzVisaflow;
import entity.KyzVisaflowId;
import services.IKyzVisaFlowServices;
import util.PageBean;

public class KyzVisaFlowServicesImpl implements IKyzVisaFlowServices{
	private IKyzVisaFlowDao visaDao;

	public void setVisaDao(IKyzVisaFlowDao visaDao) {
		this.visaDao = visaDao;
	}

	public void add(KyzVisaflow flow) {
		// TODO Auto-generated method stub
		visaDao.add(flow);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String visaSort) {
		// TODO Auto-generated method stub
		return visaDao.findFixWithPage(pageSize, page, factNo, visaSort);
	}

	public KyzVisaflow findById(KyzVisaflowId id) {
		// TODO Auto-generated method stub
		return visaDao.findById(id);
	}

	public void delete(KyzVisaflowId id) {
		// TODO Auto-generated method stub
		visaDao.delete(id);
	}

	public List<KyzVisaflow> findByFactNo(String factno) {
		// TODO Auto-generated method stub
		return visaDao.findByFactNo(factno);
	}

	public List<KyzVisaflow> findByType(String factNo,String visaSort) {
		// TODO Auto-generated method stub
		return visaDao.findByType(factNo,visaSort);
	}

	public int findByType_Dwr(String factNo, String visaSort) {
		// TODO Auto-generated method stub
		int length=this.findByType(factNo, visaSort).size();
		return length;
	}

	public String findVisaSort_dwr(String factNo, String visaSort, String email) {
		// TODO Auto-generated method stub
		return visaDao.findVisaSort_dwr(factNo, visaSort, email);
	}

	public List<String> findVisaSort_C(String factNo,String mainSort) {
		// TODO Auto-generated method stub
		return visaDao.findVisaSort_C(factNo,mainSort);
	}

	public List<KyzVisaflow> findByFactNoVisaSort(String factNo, String visaSort) {
		// TODO Auto-generated method stub
		return visaDao.findByFactNoVisaSort(factNo, visaSort);
	}

}
