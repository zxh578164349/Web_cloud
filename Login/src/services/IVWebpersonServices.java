package services;

import entity.VWebperson;

public interface IVWebpersonServices {
	public VWebperson findById(String factNo,String factCode,String yymm);

}
