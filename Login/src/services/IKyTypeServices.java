package services;

import java.util.List;

import entity.KyType;

public interface IKyTypeServices {
	public List<KyType> findByTypeNo(String typeNo);
	public List<KyType> findByTypeNo_action(String typeNo);
	public String getTypeSname(String typNo,String typeSno);

}
