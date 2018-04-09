package services.impl;

import java.util.List;

import dao.IKyzVisaFlowDao;
import entity.KyzExpectmatmLog;
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
			String visaSort,String trMk) {
		// TODO Auto-generated method stub
		return visaDao.findFixWithPage(pageSize, page, factNo, visaSort,trMk);
	}

	public KyzVisaflow findById(KyzVisaflowId id) {
		// TODO Auto-generated method stub
		return visaDao.findById(id);
	}

	public void delete(KyzVisaflowId id,KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		visaDao.delete(id,log);
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
	
	public String findVisaSort_dwr2(String factNo, String visaSort, String email,String trMk) {
		// TODO Auto-generated method stub
		return visaDao.findVisaSort_dwr(factNo, visaSort, email,trMk);
	}
	
	public List<Object[]> findVisaSort_dwr3(String factNo, String visaSort, String email,String trMk){
		return visaDao.findVisaSort_dwr2(factNo,visaSort,email,trMk);
	}
	
	public String findVisaSort_dwr3(String factNo,String visaSort,String email,String trMk,String depId){
		return visaDao.findVisaSort_dwr2(factNo,visaSort,email,trMk,depId);
	}
	
	public List<String> findVisaSort_dwr4(String factNo, String visaSort, String email,String trMk){
		return visaDao.findVisaSort_dwr3(factNo,visaSort,email,trMk);
	}

	public List<String> findVisaSort_C(String factNo,String mainSort) {
		// TODO Auto-generated method stub
		return visaDao.findVisaSort_C(factNo,mainSort);
	}

	public List<KyzVisaflow> findByFactNoVisaSort(String factNo, String visaSort) {
		// TODO Auto-generated method stub
		return visaDao.findByFactNoVisaSort(factNo, visaSort);
	}
	
	public KyzVisaflow findMaxFlow(String factNo, String visaSort) {
		// TODO Auto-generated method stub
		return visaDao.findMaxFlow(factNo, visaSort);
	}

	public void delete2(KyzVisaflow flow) {
		// TODO Auto-generated method stub
		visaDao.delete2(flow);
	}

	public String findVisaSigner(String factNo, String visaSort) {
		// TODO Auto-generated method stub
		return visaDao.findVisaSigner(factNo, visaSort);
	}

	public long findWebbuss(String factNo) {
		// TODO Auto-generated method stub
		return visaDao.findWebbuss(factNo);
	}

	/**
	 * 日期:2016/11/17
	 * 描述:
	 */
	
	
	public Long findNums(String factNo,String visaSort){
		// TODO Auto-generated method stub
		return visaDao.findNums(factNo,visaSort);
	}

	/**
	 * 日期:2016/11/18
	 * 描述:
	 */
	
	
	public List<KyzVisaflow> findTR(String factNo){
		// TODO Auto-generated method stub
		return visaDao.findTR(factNo);
	}

	/**
	 * 日期:2016/11/18
	 * 描述:
	 */
	
	
	public List<KyzVisaflow> findPF(String factNo){
		// TODO Auto-generated method stub
		return visaDao.findPF(factNo);
	}

	/**
	 * 日期:2017/5/9
	 * 描述:
	 */
	
	
	public void add_d(KyzVisaflow f1,KyzVisaflow f2){
		// TODO Auto-generated method stub
		visaDao.add_d(f1,f2);
	}

	/**
	 * 日期:2017/12/19
	 * 描述:
	 */
	
	
	public List<String> findVisaSort_dwr5(String factNo,String visaSort,String depId,String trMk){
		// TODO Auto-generated method stub
		return visaDao.findVisaSort_dwr4(factNo,visaSort,depId,trMk);
	}

	public List<KyzVisaflow> findByFnoAndVsortAndTrmk(String factNo,
			String visaSort, String trMk) {
		// TODO Auto-generated method stub
		return visaDao.findByFnoAndVsortAndTrmk(factNo, visaSort, trMk);
	}

}
