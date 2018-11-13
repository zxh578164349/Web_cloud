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
import util.GlobalMethod;
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
		Map<String,Object>map=new HashMap<String,Object>();				
		Map<String,Object>map_estpro=new HashMap<String,Object>();
		Map<String,Object>map_sumydate=new HashMap<String,Object>();		
		Map<String,Object>map_weball=new HashMap<String,Object>();	
		
		List<SumWebYieldDataView>list_sumydate=new ArrayList<SumWebYieldDataView>();		
		List<Webestproduct>list_estpro=new ArrayList<Webestproduct>();				
		List<Weballobj>list_weball=new ArrayList<Weballobj>();
		
		List<SumWebYieldDataView>list_a=sumydateSer.findObjs(beginDate,endDate);
		List<Webestproduct>list_b=estProSer.findByYymm_all(beginDate,endDate);
		List<Weballobj>list_c=weballobjser.findObj(beginDate,endDate);
		
		List<String>list_months=GlobalMethod.getDateNum(beginDate,endDate);
		List<WebFact>list_fact=new ArrayList<WebFact>();
		
		String mixOne="";
		String fileName="report";
		if(factNo.equals("all")){
			list_fact=webFactSer.findAllFact_showA();
			mixOne="(所有)"+beginDate+"-"+endDate;
		}else{
			list_fact=webFactSer.findFactById_showA(factNo);
			mixOne="("+factNo+")"+beginDate+"-"+endDate;
		}
				
		/*if(factNo!=null&&beginDate!=null&&endDate!=null){
			String factname=webFactSer.selByid(factNo);
			mixOne=factname+"("+factNo+")"+beginDate+"-"+endDate;
			fileName=fileName+factNo+beginDate+endDate;
		}*/
		
		for(WebFact fact:list_fact){
			for(String month:list_months){
				list_sumydate.add(new SumWebYieldDataView(new SumWebYieldDataId(new VWebFact(fact.getId().getFactNo()),fact.getId().getFactArea(),month)));
				list_estpro.add(new Webestproduct(new WebestproductId(fact.getId().getFactNo(),fact.getId().getFactArea(),myformat(month),"zd")));
				list_estpro.add(new Webestproduct(new WebestproductId(fact.getId().getFactNo(),fact.getId().getFactArea(),myformat(month),"tz")));
				list_weball.add(new Weballobj(new WeballobjId(new WebFact(fact.getId()),month)));			
			}
			
			for(int i=0;i<list_sumydate.size();i++){
				SumWebYieldDataView ydate=list_sumydate.get(i);
				for(SumWebYieldDataView obj:list_a){
					if(ydate.getId().getFactCode().equals(obj.getId().getFactCode())&&ydate.getId().getFactNo().getFactNo().equals(obj.getId().getFactNo().getFactNo())&&ydate.getId().getYymm().equals(obj.getId().getYymm())){
						list_sumydate.remove(i);
						list_sumydate.add(i,obj);
					}
				}
			}
			for(int i=0;i<list_estpro.size();i++){
				Webestproduct pro=list_estpro.get(i);
				for(Webestproduct obj:list_b){
					if(pro.getId().getFactCode().equals(obj.getId().getFactCode())&&pro.getId().getFactNo().equals(obj.getId().getFactNo())&&pro.getId().getYymm().equals(obj.getId().getYymm())&&
							pro.getId().getType().equals(obj.getId().getType())){
						list_estpro.remove(i);
						list_estpro.add(i,obj);
					}
				}
			}
			for(int i=0;i<list_weball.size();i++){
				Weballobj wall=list_weball.get(i);
				for(Weballobj obj:list_c){
					if(wall.getId().getFact().getId().getFactArea().equals(obj.getId().getFact().getId().getFactArea())&&wall.getId().getFact().getId().getFactNo().equals(obj.getId().getFact().getId().getFactNo())&&
							wall.getId().getYymm().equals(obj.getId().getYymm())){
						list_weball.remove(i);
						list_weball.add(i,obj);
					}
				}
			}						
		}
		
		for(SumWebYieldDataView obj:list_sumydate){
			for(WebFact fact:list_fact){
				if(obj.getId().getFactNo().getFactNo().equals(fact.getId().getFactNo())){					
					obj.getId().setFactNo(new VWebFact(fact.getFactSname()));
				}
			}
		}
		for(Webestproduct obj:list_estpro){
			for(WebFact fact:list_fact){
				if(obj.getId().getFactNo().equals(fact.getId().getFactNo())){
					obj.getId().setFactNo(fact.getFactSname());
				}
			}
		}
		for(Weballobj obj:list_weball){
			for(WebFact fact:list_fact){
				if(obj.getId().getFact().getId().getFactNo().equals(fact.getId().getFactNo())){
					obj.getId().getFact().getId().setFactNo(fact.getFactSname());
				}
			}
		}
		
		
		
		
		List<Webwlo>list=new ArrayList<Webwlo>();//�D�?�X�A�D�n�O�Ω�`�����L�l��A�@�n�`�����L9�i�l��A�G�U��i���i�֩�9���A�i�j��9
		for(int i=0;i<4;i++){
			list.add(new Webwlo());
		}
		
				
		/*List list_factcodes=webFactSer.findFactCodeByFactNo(factNo);//???
		for(int i=0;i<list_factcodes.size();i++){
			String factCode=list_factcodes.get(i).toString();									
			for(String yymm2:list_months){															
				SumWebYieldData ydata=this.getsumydate(factNo, factCode, yymm2);
				Webestproduct estpro_zd=this.getest(factNo, factCode, yymm2, "zd");
				Webestproduct estpro_tz=this.getest(factNo, factCode, yymm2, "tz");
				Weballobj obj=this.getweball(factNo,factCode,yymm2);
				list_sumydate.add(ydata);
				list_estpro.add(estpro_zd);
				list_estpro.add(estpro_tz);
				list_weball.add(obj);
			}
		}*/
		
		
		
		map_sumydate.put("list_sumydate", list_sumydate);
		map_estpro.put("list_estpro", list_estpro);
		map_weball.put("list_weball",list_weball);		
		map.put("map_estpro", map_estpro);
		map.put("map_sumydate", map_sumydate);
		map.put("map_weball",map_weball);
		map.put("mixOne", mixOne);
		
		map.put("SUBREPORT_DIR",ServletActionContext.getRequest().getRealPath("/jasper/input/")+ "/");
		if(temp.equals("look")){
			JasperHelper.exportmain("html", map,"CheckAllReport_new.jasper", list,fileName, "jasper/input/");
		}else{
			JasperHelper.exportmain("excel", map,"CheckAllReport_new.jasper", list,fileName, "jasper/input/");
		}
			
		
	}
	
	public Date myformat(String yymm) throws ParseException{
		DateFormat format=new SimpleDateFormat("yyyyMM");
		return format.parse(yymm);
	}
	public Webwlo getwlo(String factNo,String factCode,String yymm) throws ParseException{		
		Webwlo wlo=wloService.findById(factNo, factCode, yymm);
		if(wlo==null){
			wlo=new Webwlo(new WebwloId(factNo,factCode,myformat(yymm)));
		}
		return wlo;
	}
	public Webcost getcost(String factNo,String factCode,String yymm) throws ParseException{
		Webcost cost=costSer.findById(factNo, factCode, yymm);
		if(cost==null){
			cost=new Webcost(new WebcostId(factNo,factCode,myformat(yymm)));
		}
		return cost;
	}
	public Webmix2 getmix2(String factNo,String factCode,String yymm) throws ParseException{
		Webmix2 mix2=mix2Service.findById(factNo, factCode, yymm);
		if(mix2==null){
			mix2=new Webmix2(new Webmix2Id(factNo,factCode,myformat(yymm)));
		}
		return mix2;
	}
	public SumWebYieldDataView getsumydate(String factNo,String factCode,String yymm){
		SumWebYieldDataView ydata=sumydateSer.findById(factNo, factCode, yymm);
		if(ydata==null){
			
			ydata=new SumWebYieldDataView(new SumWebYieldDataId(new VWebFact(factNo),factCode,yymm));
		}
		return ydata;
	}
	public Webbackfeed getfeed(String factNo,String factCode,String yymm) throws ParseException{
		Webbackfeed feed=feedSer.findById(factNo, factCode, myformat(yymm));
		if(feed==null){
			feed=new Webbackfeed(new WebbackfeedId(factNo,factCode,myformat(yymm)));
		}
		return feed;
	}
	public Webestproduct getest(String factNo,String factCode,String yymm,String type) throws ParseException{
		Webestproduct est=estProSer.findById(factNo, factCode, yymm,type);
		if(est==null){
			est=new Webestproduct(new WebestproductId(factNo,factCode,myformat(yymm),type));
		}
		return est;
	}
	public Webmixperson getperson(String factNo,String factCode,String yymm) throws ParseException{
		Webmixperson person=mixPersonService.findById(factNo, factCode, yymm);
		if(person==null){
			person=new Webmixperson(new WebmixpersonId(factNo,factCode,myformat(yymm)));
		}
		return person;
	}
	public Webproduted getpro(String factNo,String factCode,String yymm) throws ParseException{
		Webproduted pro=produtedService.findById(factNo, factCode, yymm);
		if(pro==null){
			pro=new Webproduted(new WebprodutedId(factNo,factCode,myformat(yymm)));
		}
		return pro;
	}
	public Webscrapt getsra(String factNo,String factCode,String yymm) throws ParseException{
		Webscrapt scr=scraptService.findById(factNo, factCode, yymm);
		if(scr==null){
			scr=new Webscrapt(new WebscraptId(factNo,factCode,myformat(yymm)));
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
