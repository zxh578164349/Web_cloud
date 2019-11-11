package dao;

import java.util.List;

import entity.VWebobjBYdate;
import entity.VWebydatabyfcode2;

public interface IWebObjsBDao {
	public List<VWebobjBYdate> findByYymm(String yymm);
	public List<VWebydatabyfcode2>findByYymm2(String yymm);

}
