package dao;

import java.util.List;

import entity.WebMenu;

public interface IWebMenuDao {
	// �ھڥΤ�d����
	public List findAllMenu();

	// �d����������l���
	public WebMenu findSubMenuById(Integer mid);

	// �ھڵ��W�����H
	public WebMenu selByname(String name);
}
