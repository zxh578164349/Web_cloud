package dao.impl;

import java.util.List;

import dao.Basedao;
import dao.IWebBrankDao;
import entity.WebBrank;

public class WebBrankDaoImpl extends Basedao implements IWebBrankDao{

	public List<WebBrank> findAll() {
		// TODO Auto-generated method stub
		String hql="from WebBrank";
		return super.findAll(hql, null);
	}

}
