package dao;

import java.util.List;

import util.PageBean;
import entity.Kpifact;
import entity.KyzExpectmatmLog;

public interface IKpifactDao {
	public void add(Kpifact kpi);
	public Kpifact findById(String factNo,String factCode,String yyyy);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yyyy);
	public void delete(String factNo,String factCode,String yyyy,KyzExpectmatmLog delLog);
	public List<String> findDateByFactNo(String factNo,String yyyy);
	public List<Kpifact>findToPrint(String factNo,String yyyy);

}
