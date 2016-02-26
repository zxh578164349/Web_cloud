package services;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.Webcashout;

public interface IWebCashoutServices {
	public void add(Webcashout cashout);
	public PageBean findPageBean(int pageSize,int page,String factNo,String factCode,String date);
	public Webcashout findById(String factNo,String factCode,String date);
	public void delete(String factNo,String factCode,String date,KyzExpectmatmLog delLog);

}
