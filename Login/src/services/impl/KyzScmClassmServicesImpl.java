package services.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dao.IKyzScmClassmDao;

import services.IKyzScmClassmServices;

public class KyzScmClassmServicesImpl implements IKyzScmClassmServices{
	private IKyzScmClassmDao kyzscmDao;
	

	public void setKyzscmDao(IKyzScmClassmDao kyzscmDao) {
		this.kyzscmDao = kyzscmDao;
	}

	public Map<String,String> findBN() {
		// TODO Auto-generated method stub
		List<Object[]>list_obj=kyzscmDao.findBN();
		Map<String,String>map=new LinkedHashMap<String,String>();
		for(int i=0;i<list_obj.size();i++){
			Object[]obj=list_obj.get(i);
			String bNo=obj[0].toString();
			String bName=obj[1].toString();
			map.put(bNo,bName+"("+bNo+")");
		}
		return map;
	}

	public Map<String,String> findMN(String bigNo) {
		// TODO Auto-generated method stub
		List<Object[]>list_obj=kyzscmDao.findMN(bigNo);
		Map<String,String>map=new LinkedHashMap<String,String>();
		if(list_obj.size()>0){
			for(int i=0;i<list_obj.size();i++){
				Object[]obj=list_obj.get(i);
				String mNo=obj[0].toString();
				String mName=obj[1].toString();
				map.put(mNo,mName+"("+mNo+")");
			}
		}		
		return map;
	}

	public Map<String,String> findSN(String middleNo) {
		// TODO Auto-generated method stub
		List<Object[]>list_obj=kyzscmDao.findSN(middleNo);
		Map<String,String>map=new LinkedHashMap<String,String>();
		if(list_obj.size()>0){
			for(int i=0;i<list_obj.size();i++){
				Object[]obj=list_obj.get(i);
				String sNo=obj[0].toString();
				String sName=obj[1].toString();
				map.put(sNo, sName+"("+sNo+")");
			}
		}
		return map;
	}

}
