package services;

import java.util.List;
import entity.WebMenu;

public interface IWebMenuServices {
	// �ھڥΤ�d����������
	public List findAllMenu();

	// �ھڵ��d��������l���
	public WebMenu findMenuById(Integer mid);

	// �ھڵ��W��^����H
	public WebMenu selByname(String name);

}
