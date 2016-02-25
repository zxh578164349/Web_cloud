package services;

import java.util.List;

import entity.KyzExpectmatmFile;

public interface IKyzExpectmatmFileServices {
	public void add(KyzExpectmatmFile file);
	public List<KyzExpectmatmFile> findByBillNo(String billNo);
	public void delete(KyzExpectmatmFile file);
	
	public KyzExpectmatmFile findById(Integer id);
	public boolean delete(Integer id);
	/**
	 * 查找附檔單號
	 * @Title: findBillNo
	 * @Description: TODO
	 * @param @param factNo
	 * @param @param visaTypeM
	 * @param @return
	 * @return List<String>
	 * @throws
	 * @author web
	 * @date 2016/2/25
	 */
	public List<String>findBillNo(String factNo,String visaTypeM);

}
