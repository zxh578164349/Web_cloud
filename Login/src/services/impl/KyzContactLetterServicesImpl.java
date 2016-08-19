package services.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.ContextLoader;

import com.spreada.utils.chinese.ZHConverter;

import dao.IKyVisaBillmDao;
import dao.IKyVisaBillsDao;
import dao.IKyzContactLetterDao;
import dao.IKyzExpectmatmFileDao;
import dao.IKyzVisaFlowDao;
import dao.IWebFactDao;
import entity.KyVisabillm;
import entity.KyVisabills;
import entity.KyzContactletter;
import entity.KyzExpectmatmFile;
import entity.KyzExpectmatmLog;
import entity.KyzVisaflow;
import entity.WebUser;
import entity_temp.VisabillsTemp;
import services.IKyzContactLetterServices;
import util.GlobalMethod;
import util.PageBean;

public class KyzContactLetterServicesImpl implements IKyzContactLetterServices{
	private IKyzContactLetterDao kyzletterDao;
	private IWebFactDao webFactDao;
	private IKyVisaBillmDao visabillmDao;
	private IKyzVisaFlowDao visaDao;
	private IKyVisaBillsDao visabillDao;
	private IKyzExpectmatmFileDao kyzexpfileDao;

	
	public void setWebFactDao(IWebFactDao webFactDao) {
		this.webFactDao = webFactDao;
	}

	public void setVisabillmDao(IKyVisaBillmDao visabillmDao) {
		this.visabillmDao = visabillmDao;
	}

	public void setVisaDao(IKyzVisaFlowDao visaDao) {
		this.visaDao = visaDao;
	}

	public void setVisabillDao(IKyVisaBillsDao visabillDao) {
		this.visabillDao = visabillDao;
	}

	public void setKyzexpfileDao(IKyzExpectmatmFileDao kyzexpfileDao) {
		this.kyzexpfileDao = kyzexpfileDao;
	}

	public void setKyzletterDao(IKyzContactLetterDao kyzletterDao) {
		this.kyzletterDao = kyzletterDao;
	}

	public void add(KyzContactletter letter) {
		// TODO Auto-generated method stub
		kyzletterDao.add(letter);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,String visaSort,String billNo,WebUser user,String timeCreate,String timeCreate2) {
		// TODO Auto-generated method stub
		return kyzletterDao.findPageBean(pageSize, page, factNo, visaSort, billNo, user, timeCreate, timeCreate2);
	}
	
	public String makeBillNo(String factNo, String timeCreate) {
		StringBuffer billNo = new StringBuffer();
		billNo.append("CM");
		billNo.append(factNo);
		/*
		 * SimpleDateFormat format=new SimpleDateFormat("yyMMdd"); String
		 * temp=format.format(new Date());
		 */
		String temp = timeCreate.substring(2);
		billNo.append(temp);
		List<String> list = kyzletterDao.findBillNo(factNo, timeCreate);
		List<Integer> numlist = new ArrayList();
		if (list.size() == 0) {
			billNo.append("01");
		} else {
			int maxNum = 0;
			for (int i = 0; i < list.size(); i++) {				
				String temp_num = list.get(i).toString();
				if(!temp_num.contains("-")){
					String num_str = temp_num.substring(temp_num.length() - 2,
							temp_num.length());
					int num = Integer.parseInt(num_str);
					numlist.add(num);
				}				
			}
			for (int j = 0; j < numlist.size(); j++) {
				if (numlist.get(j) > maxNum) {
					maxNum = numlist.get(j);
				}
			}
			if (((maxNum + 1) + "").length() == 1) {
				billNo.append("0" + (maxNum + 1));
			}
			if (((maxNum + 1) + "").length() == 2) {
				billNo.append((maxNum + 1) + "");
			}
		}

		return billNo.toString();
	}

	public KyzContactletter findById(String factNo, String billNo) {
		// TODO Auto-generated method stub
		return kyzletterDao.findById(factNo, billNo);
	}

	public void delete(String factNo, String billNo,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		kyzletterDao.delete(factNo, billNo,delLog);
	}

	public String findTitleByBillno(String billNo) {
		// TODO Auto-generated method stub
		return kyzletterDao.findTitleByBillno(billNo);
	}

	public List<Object[]> findTitle(String factNo) {
		// TODO Auto-generated method stub
		return kyzletterDao.findTitle(factNo);
	}
	
	public List<Object[]> findTitle(List<String>billnos) {
		// TODO Auto-generated method stub
		return kyzletterDao.findTitle(billnos);
	}

	public List<KyzContactletter> findBefor2Month() {
		// TODO Auto-generated method stub
		return kyzletterDao.findBefor2Month();
	}

	public void addLarge(List<KyzContactletter> list) {
		// TODO Auto-generated method stub
		kyzletterDao.addLarge(list);
	}

	/**
	 * 日期:2016/2/23
	 * 描述:
	 */
	
	
	public Map<String, Object> print(String factNo, String billNo, String sort,KyVisabillm vbm) {
		// TODO Auto-generated method stub
		Map<String,Object>map_result=new HashMap<String,Object>();
		List<KyzContactletter>list=new ArrayList<KyzContactletter>();
		Map<String,Object>map=new HashMap<String,Object>();
		String factname=webFactDao.selByid(factNo);
		String secNo="";//承辦單位
		KyzContactletter letter=kyzletterDao.findById(factNo,billNo);
		if(letter==null){
			return null;
		}else{
			/*******************簡轉繁體********************/						
			letter.setUserNm(ZHConverter.convert(letter.getUserNm(), ZHConverter.TRADITIONAL));
			letter.setToUser(ZHConverter.convert(letter.getToUser(), ZHConverter.TRADITIONAL));
			letter.setChargeList(ZHConverter.convert(letter.getChargeList(), ZHConverter.TRADITIONAL));
			letter.setTitle(ZHConverter.convert(letter.getTitle(), ZHConverter.TRADITIONAL));
			letter.setMemoMk(ZHConverter.convert(letter.getMemoMk(), ZHConverter.TRADITIONAL));
			/*******************簡轉繁體********************/
			list.add(letter);
		}
		if(letter.getSecNo()!=null&&!letter.getSecNo().equals("")){
			secNo="("+letter.getSecNo()+")";
		}
		String result=factname+secNo+"內部聯絡函";
		//map = new HashMap<String, Object>();
		//map.put("SUBREPORT_DIR",ServletActionContext.getRequest().getRealPath("/jasper/audit/")+ "/");
		//map.put("pic", ServletActionContext.getRequest().getRealPath("/jasper/audit/images/")+ "/");//圖片路徑
		map.put("SUBREPORT_DIR",ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/jasper/audit/")+ "/");
		map.put("pic", ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/jasper/audit/images/")+ "/");//圖片路徑
		map.put("pfactno", factNo);
		map.put("pbillno",billNo);
		map.put("title",result);
		map.put("factname",factname);
		
				
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		if(vbm==null){
			vbm=visabillmDao.findById(factNo, sort, billNo);
		}		
		List<KyVisabills>list_visa=vbm.getKyVisabillses();
		List<KyzVisaflow>list_visaflow=visaDao.findByType(factNo,sort);
		
		/**
		 * 最後個不用審核的,就去掉
		 */
		int nos=visabillDao.findBillsWithNo(sort, billNo);
		/*if(nos>0){
			for(int i=0;i<nos;i++){
				list_visa.remove(list_visa.size()-1);
				list_visaflow.remove(list_visaflow.size()-1);
			}
		}*/		
		List<VisabillsTemp>list_visabillstemp=new ArrayList<VisabillsTemp>();		
		for(int i=0;i<list_visa.size()-nos;i++){//for
			VisabillsTemp visabillstemp=new VisabillsTemp();
			String visa_result="";
			String visamk_temp="";
			Date date=null;
			
			String datestr=list_visa.get(i).getDateVisa();
			try {
				if(datestr!=null){
					date=format.parse(datestr);
					visabillstemp.setCreateDate(date);
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String name=list_visa.get(i).getVisaRank();
			String visamk=list_visa.get(i).getVisaMk();
			String memo=list_visa.get(i).getMemo();
			if(visamk.equals("Y")){
				visamk_temp="(已審核)";
			}
			if(visamk.equals("N")){
				visamk_temp="(未審核)";
			}
			if(visamk.equals("T")){
				visamk_temp="(未通過)";
			}			
			visa_result=name+visamk_temp;
			visabillstemp.setVisaNameAndMk(visa_result);			
			if(list_visa.size()==list_visaflow.size()-nos){
				String visaRank=list_visaflow.get(i).getVisaRank();
				visabillstemp.setVisaRank(visaRank+":");
			}
			if(memo!=null){
				visabillstemp.setMemo("(備註:"+memo+")");
			}
			visabillstemp.setVisaSigner(list_visa.get(i).getVisaSigner());
			visabillstemp.setVisaMk(list_visa.get(i).getVisaMk());
			visabillstemp.setVisaName(name);
			list_visabillstemp.add(visabillstemp);
		}//for
		/*********************簡體轉繁體******************/
		for(int i=0;i<list_visabillstemp.size();i++){
			list_visabillstemp.get(i).setMemo(ZHConverter.convert(list_visabillstemp.get(i).getMemo(), ZHConverter.TRADITIONAL));
			list_visabillstemp.get(i).setVisaName(ZHConverter.convert(list_visabillstemp.get(i).getVisaName(), ZHConverter.TRADITIONAL));
			list_visabillstemp.get(i).setVisaNameAndMk(ZHConverter.convert(list_visabillstemp.get(i).getVisaNameAndMk(), ZHConverter.TRADITIONAL));
			list_visabillstemp.get(i).setVisaRank(ZHConverter.convert(list_visabillstemp.get(i).getVisaRank(), ZHConverter.TRADITIONAL));			
		}
		/*********************簡體轉繁體******************/
		
		
		Map<String,Object> visa_map=new HashMap<String,Object>();
		visa_map.put("list_visa", list_visabillstemp);
		
		map.put("visa_map", visa_map);
		/*函文附檔*/
		//String pic_file=ServletActionContext.getRequest().getRealPath("/KyzexpFile/"+id.getBillNo()+"/")+"/";//函文附檔圖片路徑(附檔在項目的路徑)
		String pic_file=new File("d:\\KyzletterexpFile_backup\\"+billNo).toString();//函文附檔圖片路徑(附檔在D盤的路徑)
		List<KyzExpectmatmFile>list_kyzexpfile=kyzexpfileDao.findByBillNo(billNo);
		if(pic_file!=null&&list_kyzexpfile.size()>0){
			map.put("pic_file", pic_file+"\\");
			Map<String,Object> file_map=new HashMap<String,Object>();
			file_map.put("list_kyzexpfile", list_kyzexpfile);
			map.put("file_map", file_map);
		}
		
		String sub_file=GlobalMethod.getSubfile(list_visa.size()-nos);
		map.put("sub_file",sub_file);
		map_result.put("map", map);
		map_result.put("list", list);
		return map_result;
	}

}
