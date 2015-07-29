package services;

import java.util.List;

import entity.WebJurisdiction;

public interface IWebJurisdictionService {
	// �s�W�v��
	public boolean addJurisdiction(WebJurisdiction jurisdiction);

	// �R���Τ᪺�Ҧ��v��
	public int delByUserId(int userId);

	// �d�ݥΤ�Ψ����v��
	public List<WebJurisdiction> selSub(int userid);

	// �R���v��
	public boolean delJur(WebJurisdiction jurisdiction);

	public boolean add(WebJurisdiction jurisdiction);

	public WebJurisdiction selBymenuName(String name, int id);
	public WebJurisdiction findById(int id);
}
