package services.impl;

import java.util.List;
import entity.*;
import dao.*;
import services.*;
import util.PageBean;

public class WebMachineServicesImpl implements IWebMachineServices {
	private IWebMachineDao machineDao;

	public void setMachineDao(IWebMachineDao machineDao) {
		this.machineDao = machineDao;
	}

	public void addMachine(WebMachin machine) {
		// TODO Auto-generated method stub

		machineDao.addmachine(machine);

	}

	public List<WebMachin> selMachin(String factNo, String yymm, int page,
			int rows) {
		return machineDao.selectWebMachin(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return machineDao.totlePage(factNo, yymm);
	}

	public WebMachin findById(WebMachinId id) {
		// TODO Auto-generated method stub
		return machineDao.findById(id);
	}

	public List<WebMachin> selByYymm(String yymm, String factNo) {
		return machineDao.selByYymm(yymm, factNo);
	}

	public void delete(WebMachinId id) {
		// TODO Auto-generated method stub
		machineDao.delete(id);

	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return machineDao.findPageBean(pageSize, page, factNo, yymm);
	}

}
