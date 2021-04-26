
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html >
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'backmat1.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
</style>

<script type="text/javascript">
	function showDiv(billNo, factNo) {
		var area_w;
		var area_h;
		var src;
		if (billNo.substring(0, 2) == 'EM') {
			area_w = '650px';
			area_h = '560px';
			src = 'kyz_findById_layer?billNo=' + billNo + '& factNo=' + factNo
					+ '& readMk=Y';
		}
		if (billNo.substring(0, 2) == 'CM') {
			area_w = '620px';
			area_h = '450px';
			src = 'kyzletter_findById_layer?billNo=' + billNo + '& factNo='
					+ factNo + '& readMk=Y';
		}
		if (billNo.substring(0, 2) == 'BM') {
			area_w = '620px';
			area_h = '450px';
			src = 'bussletter_findById_layer?billNo=' + billNo + '& readMk=Y';
		}
		if (billNo.substring(0, 2) == 'RM') {
			area_w = '650px';
			area_h = '560px';
			src = 'webremit_findById_layer?billNo=' + billNo + '& readMk=Y';
		}
		if (billNo.substring(0, 2) == 'GJ') {
			area_w = '650px';
			area_h = '560px';
			src = 'webformula_findById_layer?billNo=' + billNo + '& readMk=Y';
		}
		if (billNo.substring(0, 2) == 'NP') {
			area_w = '650px';
			area_h = '560px';
			src = 'webnewpro_findByBillNo_layer?billNo=' + billNo + '& readMk=Y';
		}
		if (billNo.substring(0, 2) == 'CP') {
			area_w = '750px';
			area_h = '560px';
			src = 'webcolpro_findByBillNo_layer?billNo=' + billNo + '& readMk=Y';
		}
		
		jq.layer({
			type : 2, //0-4的选择,
			title : '函文內容',
			//border: [0],
			closeBtn : [ 1, true ],
			shade : [ 0 ],
			//shade: [0.5, '#000'],
			shadeClose : false,
			border : [ 10, 0.3, '#000' ],
			// btns:1,
			//fadeIn:300,
			//shift:'top',
			offset : [ '10px', '' ],
			//area: ['800px', '560px'],
			area : [ area_w, area_h ],
			maxmin : true,
			//page:{url:'kyz_findById_layer?billNo='+billNo+'& factNo='+factNo}                   
			iframe : {
				src : src,
				scrolling : 'auto'
			}
		/* close:function(){
			location.reload();
		} */
		});
	}

	function check(factNo, visaSort, billNo, itemNo) {
		var area_w;
		var area_h;
		var src;
		if (billNo.substring(0, 2) == 'EM') {
			area_w = '650px';
			area_h = '560px';
			src = 'kyz_findById_layer?billNo=' + billNo + '& factNo=' + factNo
					+ '& itemNo=' + itemNo + '& visaSort=' + visaSort
					+ '& readMk=N';
		}
		if (billNo.substring(0, 2) == 'CM') {
			area_w = '620px';
			area_h = '500px';
			src = 'kyzletter_findById_layer?billNo=' + billNo + '& factNo='
					+ factNo + '& itemNo=' + itemNo + '& visaSort=' + visaSort
					+ '& readMk=N';
		}
		if (billNo.substring(0, 2) == 'BM') {
			area_w = '620px';
			area_h = '500px';
			src = 'bussletter_findById_layer?billNo=' + billNo + '& factNo='
					+ factNo + '& itemNo=' + itemNo + '& visaSort=' + visaSort
					+ '& readMk=N';
		}
		if (billNo.substring(0, 2) == 'RM') {
			area_w = '650px';
			area_h = '560px';
			src = 'webremit_findById_layer?billNo=' + billNo + '& factNo='
					+ factNo + '& itemNo=' + itemNo + '& visaSort=' + visaSort
					+ '& readMk=N';
		}
		if (billNo.substring(0, 2) == 'GJ') {
			area_w = '650px';
			area_h = '560px';
			src = 'webformula_findById_layer?billNo=' + billNo + '& factNo='
					+ factNo + '& itemNo=' + itemNo + '& visaSort=' + visaSort
					+ '& readMk=N';
		}
		if (billNo.substring(0, 2) == 'NP') {
			area_w = '650px';
			area_h = '560px';
			src = 'webnewpro_findByBillNo_layer?billNo=' + billNo + '& factNo='
					+ factNo + '& itemNo=' + itemNo + '& visaSort=' + visaSort
					+ '& readMk=N';
		}
		if (billNo.substring(0, 2) == 'CP') {
			area_w = '750px';
			area_h = '560px';
			src = 'webcolpro_findByBillNo_layer?billNo=' + billNo + '& factNo='
					+ factNo + '& itemNo=' + itemNo + '& visaSort=' + visaSort
					+ '& readMk=N';
		}
		jq.layer({
			type : 2, //0-4的选择,
			title : '函文內容',
			//border: [0],
			border : [ 10, 0.3, '#000' ],
			closeBtn : [ 1, true ],
			shade : [ 0 ],
			//shade: [0.5, '#000'],
			shadeClose : false,
			btns : 2,
			btn : [ '通過', '不通過' ],
			//fadeIn:300,
			//shift:'top',
			offset : [ '10px', '' ],
			area : [ area_w, area_h ],
			maxmin : true,
			//page:{url:'kyz_findById_layer?billNo='+billNo+'& factNo='+factNo},
			//iframe:{src:'kyz_findById_layer?billNo='+billNo+'& factNo='+factNo,scrolling:'auto'},   
			iframe : {
				src : src,
				scrolling : 'auto'
			},

			yes : function() {
				yesorno('Y')
			},
			no : function() {
				yesorno('T')
			}
		});
	}

	function tips(memo, index) {
		if (memo == '') {
			memo = '無';
		}
		layer.tips(memo, '#' + index, {
			style : [ 'background-color:#78BA32; color:#fff', '#78BA32' ],
			maxWidth : 240,
			time : 10,
			closeBtn : [ 0, true ]
		});
	}

	function back() {

		loadUrl("vbm_findPageBean");
	}
	function yesorno(passMk) {
		var memo = layer.getChildFrame("#memo_txt", layer.index).val();
		layer.getChildFrame("#visa_mk", layer.index).val(passMk);
		if (memo.length > 1400) {
			alert("備註不可超過1400字符");
		} else {
			/*layer.getChildFrame("#memo",layer.index).submit();
			window.setTimeout(function(){parent.layer.close(parent.layer.getFrameIndex(window.name));},1500);       
			loadUrl("vbm_findPageBean");*/
			jq.ajax({
				type : "POST",
				dataType : "json",
				url : "vbm_add",
				data : layer.getChildFrame("#memo", layer.index).serialize(),
				//async:false,
				success : function(data) {
					if (data == "0") {
						layer.msg("簽核成功", 2, 1);
						window.setTimeout(function() {
							layer.closeAll();
							loadUrl("vbm_findPageBean")
						}, 2000);
						//layer.closeAll();loadUrl("vbm_findPageBean");
					} else {
						layer.msg("簽核失敗", 2, 3);
					}
					layer.index = layer.index - 2;//(減2箇層：加載層    信息層)
				},
				error : function(error) {
					alert(error.responseText);
				}
			});

		}
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h3>函文審核</h3>
				<table class="table table-bordered table-condensed">
					<thead>
						<tr class="info">
							<td>函文單號</td>
							<s:iterator value="vbm.kyVisabillses">
							   <s:if test='visible!="N"'>
	                             <td><s:property value="visaRank"/></td>
	                           </s:if>
	                           <s:else><td>*</td></s:else>								
							</s:iterator>
						</tr>
					</thead>
					<tr class="warning">
						<td><a id="a_show"
							href="javascript:showDiv('<s:property value='vbm.id.billNo'/>','<s:property value='vbm.id.factNo'/>')">
								<s:property value="vbm.id.billNo" />
						</a></td>
						<s:iterator value="vbm.kyVisabillses" status="x">
							<td>
								<!-- <s:property value="vbm.signerNext"/>(<s:property value="visaSigner"/>) --> <s:if
									test='flowMk=="Y"'>
									<s:if test='visaMk=="N"'>
										<!-- 1.判斷未審和已審狀態 -->
										<s:if test='id.itemNo==vbm.itemNext'>
											<!--2.判斷當前審核人的項次是否為下一位審核人 的項次 -->
											<s:if test='%{strToLow(visaSigner)==strToLow(#session.loginUser.email)}'>
												<!-- 3.判斷登錄者是否為當前審核人 -->
												<a id="a_check" style="color:red"
													href="javascript:check('<s:property value='id.kyVisabillm.id.factNo'/>','<s:property value='id.kyVisabillm.id.visaSort'/>',
	              '<s:property value='id.kyVisabillm.id.billNo'/>','<s:property value='id.itemNo'/>')">
													未審核<s:property value="id.itemNo" />(當前審核人) </a>
											</s:if>
											<s:else>
												<a disabled style="color:grey">未審核</a>
											</s:else>
										</s:if>
										<s:else>
											<a disabled style="color:grey">未審核</a>
										</s:else>
									</s:if>
									<s:if test='visaMk=="Y"'>
										<a style="color:green" href="javascript:tips('${memo}','index${x.index}')"
											id="index${x.index}">已審核<br />(<s:property value="dateVisa" />)</a>
									</s:if>
									<s:if test='visaMk=="T"'>
										<a style="color:blue" href="javascript:tips('${memo}','index${x.index}')"
											id="index${x.index}">未通過<br />(<s:property value="dateVisa" />)</a>
									</s:if>
								</s:if> <s:else>
									<s:if test='visible!="N"'>
										<a style="color:#b45b3e">只知會</a>
									</s:if>
									<s:else>/</s:else>									
								</s:else></td>
						</s:iterator>
					</tr>
					<tr class="success">
						<td colspan="<s:property value='vbm.kyVisabillses.size()+1'/>"><a
							href="javascript:back()" class="btn btn-info">返回</a></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
