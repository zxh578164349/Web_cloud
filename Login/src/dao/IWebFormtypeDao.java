package dao;

import java.util.List;

import entity.WebFormtype;

public interface IWebFormtypeDao {
	public List<Object[]> findWebformByFactnoTypeno(String factNo,String typeNo);
	public List<WebFormtype> findWebformByFactno(String factNo);

}
