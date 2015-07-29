package services.impl;

import java.util.List;
import services.*;
import dao.*;

public class KyFactServicesImpl implements IKyFactServices {
	private IKyFactDao factDao;

	public void setFactDao(IKyFactDao factDao) {
		this.factDao = factDao;
	}

	public List findAllKyFact() {
		// TODO Auto-generated method stub
		return factDao.fidnAllKyFact();
	}

	public String selByid(String id) {
		return factDao.selByid(id);
	}

}
