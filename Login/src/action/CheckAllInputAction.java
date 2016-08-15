package action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import services.*;
import util.JasperHelper;
import entity.*;


public class CheckAllInputAction {
	private IWebMixPersonServices mixPersonService;//�H�Ƥu����¾���
	private IWebScraptServices scraptService;//�o�~�޼ƾ�
	private IWebProdutedServices produtedService;//���~�L�I���
	private IWebEstProductServices estProSer;//�w�p�Ͳ�
	private IWebMix2Services mix2Service;//�禬�O�θ��
	private IWebwloServices wloService;//���q�o
	private IWebCostServices costSer;//������
	private IWebBackFeedServices feedSer;//�^�Y��
	//private IVSumWebmix1Services vmix1ser;//���q���
	private ISumWebYieldDataServices sumydateSer;//���q���(�L�I)
	private IWebFactServices webFactSer;
	private IWeballobjServices weballobjser;
	private String factNo;
	private String yymm;
	private String beginDate;
	private String endDate;
	private String temp;
	
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getFactNo() {
		return factNo;
	}
	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}
	public String getYymm() {
		return yymm;
	}
	public void setYymm(String yymm) {
		this.yymm = yymm;
	}
	public void setMixPersonService(IWebMixPersonServices mixPersonService) {
		this.mixPersonService = mixPersonService;
	}
	public void setScraptService(IWebScraptServices scraptService) {
		this.scraptService = scraptService;
	}
	public void setProdutedService(IWebProdutedServices produtedService) {
		this.produtedService = produtedService;
	}
	public void setEstProSer(IWebEstProductServices estProSer) {
		this.estProSer = estProSer;
	}
	public void setMix2Service(IWebMix2Services mix2Service) {
		this.mix2Service = mix2Service;
	}
	public void setWloService(IWebwloServices wloService) {
		this.wloService = wloService;
	}
	public void setCostSer(IWebCostServices costSer) {
		this.costSer = costSer;
	}
	public void setFeedSer(IWebBackFeedServices feedSer) {
		this.feedSer = feedSer;
	}
	
	public void setSumydateSer(ISumWebYieldDataServices sumydateSer) {
		this.sumydateSer = sumydateSer;
	}
	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	
	
	public void setWeballobjser(IWeballobjServices weballobjser){
		this.weballobjser=weballobjser;
	}
	public void print() throws ParseException{
		List<Webwlo>list=new ArrayList<Webwlo>();//�D�?�X�A�D�n�O�Ω�`�����L�l��A�@�n�`�����L9�i�l��A�G�U��i���i�֩�9���A�i�j��9
		for(int i=0;i<10;i++){
			list.add(new Webwlo());
		}
		String mixOne="";
		String fileName="report";
		if(factNo!=null&&beginDate!=null&&endDate!=null){
			String factname=webFactSer.selByid(factNo);
			mixOne=factname+"("+factNo+")"+beginDate+"-"+endDate;
			fileName=fileName+factNo+beginDate+endDate;
		}
		Map<String,Object>map=new HashMap<String,Object>();
		Map<String,Object>map_wlo=new HashMap<String,Object>();
		Map<String,Object>map_cost=new HashMap<String,Object>();
		Map<String,Object>map_mix2=new HashMap<String,Object>();
		Map<String,Object>map_sumydate=new HashMap<String,Object>();
		Map<String,Object>map_backfeed=new HashMap<String,Object>();
		Map<String,Object>map_estpro=new HashMap<String,Object>();
		Map<String,Object>map_mixperson=new HashMap<String,Object>();
		Map<String,Object>map_product=new HashMap<String,Object>();
		Map<String,Object>map_scrapt=new HashMap<String,Object>();
		Map<String,Object>map_weball=new HashMap<String,Object>();
		
		List<Webwlo>list_wlo=new ArrayList<Webwlo>();
		List<Webcost>list_cost=new ArrayList<Webcost>();
		List<Webmix2>list_mix2=new ArrayList<Webmix2>();
		List<SumWebYieldData>list_sumydate=new ArrayList<SumWebYieldData>();
		List<Webbackfeed>list_backfeed=new ArrayList<Webbackfeed>();
		List<Webestproduct>list_estpro=new ArrayList<Webestproduct>();
		List<Webmixperson>list_mixperson=new ArrayList<Webmixperson>();
		List<Webproduted>list_product=new ArrayList<Webproduted>();
		List<Webscrapt>list_scrapt=new ArrayList<Webscrapt>();
		List<Weballobj>list_weball=new ArrayList<Weballobj>();
		
		
		List list_factcodes=webFactSer.findFactCodeByFactNo(factNo);//???
		for(int i=0;i<list_factcodes.size();i++){
			String factCode=list_factcodes.get(i).toString();
			if(beginDate.equals(endDate)){
				/*Webwlo wlo=this.getwlo(factNo, factCode, beginDate);
				Webcost cost=this.getcost(factNo, factCode, beginDate);
				Webmix2 mix2=this.getmix2(factNo, factCode, beginDate);				
				Webbackfeed feed=this.getfeed(factNo, factCode, beginDate);						
				Webmixperson person=this.getperson(factNo, factCode, beginDate);
				Webproduted pro=this.getpro(factNo, factCode, beginDate);
				Webscrapt scr=this.getsra(factNo, factCode, beginDate);				
				list_wlo.add(wlo);
				list_cost.add(cost);
				list_mix2.add(mix2);				
				list_backfeed.add(feed);				
				list_mixperson.add(person);
				list_product.add(pro);
				list_scrapt.add(scr);*/
				
				
				Webestproduct estpro_zd=this.getest(factNo, factCode, beginDate, "zd");	
				Webestproduct estpro_tz=this.getest(factNo, factCode, beginDate, "tz");
				SumWebYieldData ydata=this.getsumydate(factNo, factCode, beginDate);
				Weballobj obj=this.getweball(factNo,factCode,yymm);								
				list_sumydate.add(ydata);
				list_estpro.add(estpro_zd);
				list_estpro.add(estpro_tz);
				list_weball.add(obj);
			}else{
				Date date_beg=myformat().parse(beginDate);
				Date date_end=myformat().parse(endDate);
				Calendar cal_beg=Calendar.getInstance();
				Calendar cal_end=Calendar.getInstance();
				cal_beg.setTime(date_beg);
				cal_end.setTime(date_end);
				int year_beg=cal_beg.get(Calendar.YEAR);
				int year_end=cal_end.get(Calendar.YEAR);
				int month_beg=cal_beg.get(Calendar.MONTH);
				int month_end=cal_end.get(Calendar.MONTH);
				int result=(year_end-year_beg)*12+(month_end-month_beg);
				String yymm2="";
				for(int k=0;k<result+1;k++){
					if(k>0){
						cal_beg.add(Calendar.MONTH,1);
					}
					yymm2=myformat().format(cal_beg.getTime());
					
					/*Webwlo wlo=this.getwlo(factNo, factCode, yymm2);
					Webcost cost=this.getcost(factNo, factCode, yymm2);
					Webmix2 mix2=this.getmix2(factNo, factCode, yymm2);					
					Webbackfeed feed=this.getfeed(factNo, factCode, yymm2);					
					Webmixperson person=this.getperson(factNo, factCode, yymm2);
					Webproduted pro=this.getpro(factNo, factCode, yymm2);
					Webscrapt scr=this.getsra(factNo, factCode, yymm2);										
					list_wlo.add(wlo);
					list_cost.add(cost);
					list_mix2.add(mix2);					
					list_backfeed.add(feed);					
					list_mixperson.add(person);
					list_product.add(pro);
					list_scrapt.add(scr);*/
					
					
					SumWebYieldData ydata=this.getsumydate(factNo, factCode, yymm2);
					Webestproduct estpro_zd=this.getest(factNo, factCode, yymm2, "zd");
					Webestproduct estpro_tz=this.getest(factNo, factCode, yymm2, "tz");
					Weballobj obj=this.getweball(factNo,factCode,yymm2);
					list_sumydate.add(ydata);
					list_estpro.add(estpro_zd);
					list_estpro.add(estpro_tz);
					list_weball.add(obj);
				}
			}
		}
		map_wlo.put("list_wlo", list_wlo);
		map_cost.put("list_cost", list_cost);
		map_mix2.put("list_mix2", list_mix2);	
		map_backfeed.put("list_backfeed", list_backfeed);		
		map_mixperson.put("list_mixperson", list_mixperson);
		map_product.put("list_product", list_product);
		map_scrapt.put("list_scrapt", list_scrapt);
		
		map_sumydate.put("list_sumydate", list_sumydate);
		map_estpro.put("list_estpro", list_estpro);
		map_weball.put("list_weball",list_weball);
		
		map.put("map_wlo", map_wlo);
		map.put("map_cost", map_cost);
		map.put("map_mix2", map_mix2);		
		map.put("map_backfeed",map_backfeed);		
		map.put("map_mixperson", map_mixperson);
		map.put("map_product", map_product);
		map.put("map_scrapt", map_scrapt);
		map.put("mixOne", mixOne);
		
		map.put("map_estpro", map_estpro);
		map.put("map_sumydate", map_sumydate);
		map.put("map_weball",map_weball);
		map.put("SUBREPORT_DIR",ServletActionContext.getRequest().getRealPath("/jasper/input/")+ "/");
		if(temp.equals("look")){
			JasperHelper.exportmain("html", map,"CheckAllReport_new.jasper", list,fileName, "jasper/input/");
		}else{
			JasperHelper.exportmain("excel", map,"CheckAllReport_new.jasper", list,fileName, "jasper/input/");
		}
			
		
	}
	
	public DateFormat myformat(){
		DateFormat format=new SimpleDateFormat("yyyyMM");
		return format;
	}
	public Webwlo getwlo(String factNo,String factCode,String yymm) throws ParseException{		
		Webwlo wlo=wloService.findById(factNo, factCode, yymm);
		if(wlo==null){
			wlo=new Webwlo(new WebwloId(factNo,factCode,myformat().parse(yymm)));
		}
		return wlo;
	}
	public Webcost getcost(String factNo,String factCode,String yymm) throws ParseException{
		Webcost cost=costSer.findById(factNo, factCode, yymm);
		if(cost==null){
			cost=new Webcost(new WebcostId(factNo,factCode,myformat().parse(yymm)));
		}
		return cost;
	}
	public Webmix2 getmix2(String factNo,String factCode,String yymm) throws ParseException{
		Webmix2 mix2=mix2Service.findById(factNo, factCode, yymm);
		if(mix2==null){
			mix2=new Webmix2(new Webmix2Id(factNo,factCode,myformat().parse(yymm)));
		}
		return mix2;
	}
	public SumWebYieldData getsumydate(String factNo,String factCode,String yymm){
		SumWebYieldData ydata=sumydateSer.findById(factNo, factCode, yymm);
		if(ydata==null){
			
			ydata=new SumWebYieldData(new SumWebYieldDataId(new VWebFact(factNo),factCode,yymm));
		}
		return ydata;
	}
	public Webbackfeed getfeed(String factNo,String factCode,String yymm) throws ParseException{
		Webbackfeed feed=feedSer.findById(factNo, factCode, myformat().parse(yymm));
		if(feed==null){
			feed=new Webbackfeed(new WebbackfeedId(factNo,factCode,myformat().parse(yymm)));
		}
		return feed;
	}
	public Webestproduct getest(String factNo,String factCode,String yymm,String type) throws ParseException{
		Webestproduct est=estProSer.findById(factNo, factCode, yymm,type);
		if(est==null){
			est=new Webestproduct(new WebestproductId(factNo,factCode,myformat().parse(yymm),type));
		}
		return est;
	}
	public Webmixperson getperson(String factNo,String factCode,String yymm) throws ParseException{
		Webmixperson person=mixPersonService.findById(factNo, factCode, yymm);
		if(person==null){
			person=new Webmixperson(new WebmixpersonId(factNo,factCode,myformat().parse(yymm)));
		}
		return person;
	}
	public Webproduted getpro(String factNo,String factCode,String yymm) throws ParseException{
		Webproduted pro=produtedService.findById(factNo, factCode, yymm);
		if(pro==null){
			pro=new Webproduted(new WebprodutedId(factNo,factCode,myformat().parse(yymm)));
		}
		return pro;
	}
	public Webscrapt getsra(String factNo,String factCode,String yymm) throws ParseException{
		Webscrapt scr=scraptService.findById(factNo, factCode, yymm);
		if(scr==null){
			scr=new Webscrapt(new WebscraptId(factNo,factCode,myformat().parse(yymm)));
		}
		return scr;
	}
	public Weballobj getweball(String factNo,String factCode,String yymm){
		Weballobj obj=weballobjser.findById(factNo,factCode,yymm);
		if(obj==null){
			obj=new Weballobj(new WeballobjId(new WebFact(new WebFactId(factNo,factCode)),yymm));
		}
		return obj;
	}
	
	
	
	

}
