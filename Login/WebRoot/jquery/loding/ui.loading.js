/**
 * author: KingViker mail: kingviker88@gmail.com time: 2012-03-28 �芷�閬紡�古s�ss
 * 撟嗅�蝸摰�銋������憒�: 
 * 
 * jQuery(function($) {
 * $(document).ui_loading();
 * })
 * ��桀��舀�
 * 
 * overlay:true/false  boolean �臬撘��典��格��,暺恕true
 * 
 * opacity:0~1.0 number �典��格��摨�敶蛹0�臬�剖撅�⊥���暺恕0.2
 * 
 * supportIframe:true/false boolean �臬�舀�iframe�蝸��,暺恕true
 * 
 * message String �內�折憸�暺恕��'�唳�蝸銝哨�霂瑞�蝑�..'
 * 
 * jQuery(function($) {
 * $(document).ui_loading({
 * overlay:true,
 * opacity:0.2,
 * supportIframe:true,
 * message:'custom  string'
 * });
 * })
 * 
 *
 *�亥�iframe�蝸��銋����支�supportIframe:true憭�瘥活�湔iframe src撅�������閫血� beforeload鈭辣
 * 靘�:Iframe.trigger('beforeload');
 * 
 * lastupdae:2012-01-10
 */
(function($){
	$.ui_loading = function() {};
$.extend($.ui_loading, {
    settings: {
      overlay: true,
      opacity:0.2,
      supportIframe:true,
      message:'�唳�蝸銝哨�霂瑞�蝑�..'
    }});


$.fn.ui_loading = function(settings) {
    if ($(this).length == 0) return
    init(settings);
  };

function init(settings) {
	if ($.ui_loading.settings.inited) return true;
    else $.ui_loading.settings.inited = true;
    
    if (settings) $.extend($.ui_loading.settings, settings);
	$('<div id="ui_loading_progressBar" class="ui_loading_progressBar" style="display: none; ">'+$.ui_loading.settings.message+'...</div>').appendTo('body');
	 $("body").append('<div id="ui_loading_overlay" class="ui_loading_hide"></div>');
	 
	 var $loading = $("#ui_loading_progressBar").hide();
	 $(document).ajaxStart(function(){
		 showOverlay();
		 showLoading();
	 }).ajaxStop(function(){
		 hideOverlay();
		 hideLoading();
	 }); 
	 if($.ui_loading.settings.supportIframe){
		 
		 $('iframe').each(function(){
			$(this).bind('beforeload',function(){
				showOverlay(this);
				showLoading(this);
			});
			$(this).load(function(){
				 hideOverlay();
				 hideLoading();
			});
		 });
		 }
}

function skipOverlay() {
    return $.ui_loading.settings.overlay == false || $.ui_loading.settings.opacity === null;
  }
function isVisible(obj)
{
   if(obj==null) return false;
   return obj.offsetWidth>0&&obj.offsetHeight>0;
}
function showLoading(iframe){
	if(iframe){
		var pIframe = iframe.getBoundingClientRect();
		var left = parseInt((pIframe.left).toFixed(0))+(pIframe.right-pIframe.left)/2+$(document).scrollLeft();
		var top = parseInt((pIframe.top).toFixed(0))+(pIframe.bottom-pIframe.top)/2+$(document).scrollTop();
		$("#ui_loading_progressBar").css({"left":left,"top":top})
		.show();
	}else{
		$("#ui_loading_progressBar").css({"left":"50%","top":"50%"})
		.show();
	}
}
function hideLoading(){
	$("#ui_loading_progressBar").hide();
}
function showOverlay(iframe) {
    if (skipOverlay()) return
    if($('#ui_loading_overlay').length == 0)
    	$("body").append('<div id="ui_loading_overlay" class="ui_loading_hide"></div>');
   
    var $overlay = $("#ui_loading_overlay");
    if(iframe){
    	if(!isVisible(iframe))return;
    	if(isNaN(iframe.height)){
    		$overlay.height(iframe.height);
    	}else{
    		$overlay.height(iframe.height+"px");
    	}
    	if(isNaN(iframe.width)){
    		$overlay.width(iframe.width);
    	}else{
    		$overlay.width(iframe.width+"px");
    	}
    	var pIframe = iframe.getBoundingClientRect();
    	$overlay.css("left",pIframe.left+$(document).scrollLeft()).css("top",pIframe.top+$(document).scrollTop());
    }else{
    	$overlay.height("100%").width("100%").css("top",0).css("left",0);
    }
    $('#ui_loading_overlay').hide().addClass("ui_loading_overlayBG")
      .css('opacity', $.ui_loading.settings.opacity)
      .fadeIn(200);
    return false;
  }

function hideOverlay() {
    if (skipOverlay()) return
    $('#ui_loading_overlay').fadeOut(200, function(){
      $("#ui_loading_overlay").removeClass("ui_loading_overlayBG");
      $("#ui_loading_overlay").addClass("ui_loading_hide");
      $("#ui_loading_overlay").remove();
    });
    return false;
  }
})(jQuery);