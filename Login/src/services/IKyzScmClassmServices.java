package services;

import java.util.List;
import java.util.Map;

public interface IKyzScmClassmServices {
	public Map<String,String> findBN();
	public Map<String,String> findMN(String bigNo);
	public Map<String,String> findSN(String middleNo);

}
