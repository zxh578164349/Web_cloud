package services.impl;

import java.util.ArrayList;
import java.util.List;

import dao.IKyzMatDao;
import entity.KyzExpectmatmLog;
import entity.KyzMat;
import services.IKyzMatServices;
import util.PageBean;

public class KyzMatServicesImpl implements IKyzMatServices{
	private IKyzMatDao kyzmatDao;
	

	public void setKyzmatDao(IKyzMatDao kyzmatDao) {
		this.kyzmatDao = kyzmatDao;
	}

	public void add(KyzMat mat) {
		// TODO Auto-generated method stub
		kyzmatDao.add(mat);
	}

	public PageBean findPageBean(int pageSize, int page, String fromDate,
			String endDate,String matCname,String bNo,String mNo,String sNo,String factNo,String matNo) {
		// TODO Auto-generated method stub
		return kyzmatDao.findPageBean(pageSize, page, fromDate, endDate,matCname,bNo,mNo,sNo,factNo,matNo);
	}
	
	public String makeMatNo(String bNo,String mNo,String sNo){
		//��������»P�s��matNo		
		List<String>list_old=kyzmatDao.findAllMatNo();
		List<String>list_new=new ArrayList<String>();
		String matNo_new=bNo+mNo+sNo+"000001";
		for(int i=0;i<list_old.size();i++){
			String matNo_old=list_old.get(i);
			if(matNo_old.length()<12){//�`�N�A�Ҧ��ª�matNo����Ƴ��j��11
				list_new.add(matNo_old);
			}
		}
		//�������smatNo�ح�,�A�����X�ۦP��(�j����+������+�p��)
		if(list_new.size()>0){//start if
			List<String>list_new_1=new ArrayList<String>();
			for(int i=0;i<list_new.size();i++){
				String matNo=list_new.get(i);
				String matNo_str=matNo.substring(0, matNo.length()-6);
				if(matNo_str.equals(bNo+mNo+sNo)){
					list_new_1.add(matNo);
				}
			}
			//�̫�b�ۦP���]�j����+������+�p�����^�����X�̤j���ǦC��
			if(list_new_1.size()>0){//start if2
				int maxNum=0;
				String maxNum_str="1";
				for(int i=0;i<list_new_1.size();i++){
					String matNo=list_new_1.get(i);
					String matNo_num=matNo.substring(matNo.length()-6,matNo.length());
					int temp=Integer.parseInt(matNo_num);
					if(temp>maxNum){
						maxNum=temp+1;
						maxNum_str=maxNum+"";
					}
				}
				if(maxNum_str.length()==1){
					maxNum_str="00000"+maxNum_str;
				}
				if(maxNum_str.length()==2){
					maxNum_str="0000"+maxNum_str;
				}
				if(maxNum_str.length()==3){
					maxNum_str="000"+maxNum_str;
				}
				if(maxNum_str.length()==4){
					maxNum_str="00"+maxNum_str;
				}
				if(maxNum_str.length()==5){
					maxNum_str="0"+maxNum_str;
				}
				matNo_new=bNo+mNo+sNo+maxNum_str;
			}//end if2
		}//end if
		return matNo_new;
		
	}

	public KyzMat findById(String matNo) {
		// TODO Auto-generated method stub
		return kyzmatDao.findById(matNo);
	}

	public void delete(String matNo,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		kyzmatDao.delete(matNo,delLog);
	}

	public List<KyzMat> findWithNoPage(String fromDate, String endDate,
			String matCname, String bNo, String mNo, String sNo,String factNo,String matNo) {
		// TODO Auto-generated method stub
		return kyzmatDao.findWithNoPage(fromDate, endDate, matCname, bNo, mNo, sNo,factNo,matNo);
	}
	
	
}
