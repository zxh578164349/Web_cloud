package dao;

import java.util.List;

import entity.WebJurisdiction;

public interface IWebJurisdictionDao {
	// �s�W�v��
	public void addJurisdiction(WebJurisdiction Jurisdiction);

	// �ھڥΤ�R���v��
	public int delByUserId(int userId);

	// ��^�Τᦳ�����v��
	public List<WebJurisdiction> selbyid(int userid);

	// �R���v��
	public void delJur(WebJurisdiction jurisdiction);

	// ���Τ���t�v��
	public void add(WebJurisdiction jurisdiction);

	// �ھڵ��W���H
	public WebJurisdiction selBymenuName(String name, int userid);
	
	public WebJurisdiction findById(int id);
}
