package services;

import java.util.List;

import entity.VWebydataawebcashout;

public interface IVWebydataawebcashoutServices {
	public List<VWebydataawebcashout>findById(String factNo,String factCode,String yymm);
	public VWebydataawebcashout findByIdOne(String factNo,String factCode,String yymmdd);

}
