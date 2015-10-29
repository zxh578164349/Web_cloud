package services;

import entity.VWebstore;

public interface IVWebStoreServices {
	public VWebstore findById(String factNo,String factCode,String yymm);

}
