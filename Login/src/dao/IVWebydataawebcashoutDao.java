package dao;

import java.util.List;

import entity.VWebydataawebcashout;

public interface IVWebydataawebcashoutDao {
	public List<VWebydataawebcashout>findById(String factNo,String factCode,String yymm);
	public VWebydataawebcashout findByIdOne(String factNo,String factCode,String yymmdd);

}
