package dao.impl;

import dao.Basedao;
import dao.IWebPhonebookDao;
import entity.WebPhonebook;

public class WebPhonebookDaoImpl extends Basedao implements IWebPhonebookDao{

	public void add(WebPhonebook phone) {
		// TODO Auto-generated method stub
		super.merge(phone);
	}

}
