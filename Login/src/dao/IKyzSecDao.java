package dao;

import java.util.List;

import util.PageBean;
import entity.KyzSec;

public interface IKyzSecDao {
	public void add(KyzSec kyzsec);
	public PageBean findPageBean(int pageSize,int page,String factNo,String secNo);
	public List<KyzSec> findByFactno(String factNo);
	public KyzSec findById(String factNo,String secNo);
	public void delete(String factNo,String secNo);
}
