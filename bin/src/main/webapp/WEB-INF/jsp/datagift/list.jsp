<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W4C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html5/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densityDpi=device-dpi" />
<title>LGU+ Connected Car</title>
<link rel="stylesheet" href="../../css/phone/common.css">
<link rel="stylesheet" href="../../css/phone/style.css">

<script type="text/javascript" src="../../js/phone/jquery-1.10.1.js"></script>
<script type="text/javascript" src="../../js/phone/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../js/phone/custom.js"></script>
<script type="text/javascript">
	$(function() {
		/* layer popup */
		var winH = $(window).height();
		var winW = $(window).width();
		var scrollT = $(window).scrollTop();
	
		$('.popup').css('top', winH/2-$('.popup').height()/2 + scrollT);
		$('.popup').css('left', winW/2-$('.popup').width()/2);
		
		$("#scroll1 ul").find('a').click(function() {
			var str = $(this).parents("li").index();
			alert(str);
		});
		$('#scroll').enscroll({
	        showOnHover: false,
	        verticalTrackClass:'track3',
	        verticalHandleClass:'handle3'
	    });
	
	    $('.full-popup .service_list ul li label').click(function(e) {
			e.preventDefault();
	
			if ($(this).parents("li").hasClass("on")) {
				$(this).parents("li").removeClass("on")
				$(this).parents("li").next('li.info').slideUp();
			} else {
	    		$(this).parents("li").next('li.info').slideDown();
	    		$(this).parents("li").addClass("on");
	    	};
	    });
		$(".dim").click(function(){
			showLayerClose();
		});
		$(".popup_btnArea").click(function(){
			showLayerClose();
		});
	});
		
	function back() {
		document.LGD_PAYINFO.action = '/datagift/postMain.do';
		document.LGD_PAYINFO.submit();
		//history.back();
	}

	function navigationBack()
	{
		//document.LGD_PAYINFO.action = '/datagift/postMain.do';
		//document.LGD_PAYINFO.submit();
		history.back();
	}
	//*********************************************************************************************
	// ????????????
	//*********************************************************************************************
	function order() {
	
		 if($('#giftName').val() == ''){
			//????????? ???????????? ????????????
			showLayerOpen('????????? ???????????????');
			return;
		}
		if($('#LGD_BUYER').val() == ''){
			//????????? ????????? ???????????? ????????????
			showLayerOpen('????????? ????????? ???????????????');
			return;
		}
		if($('#LGD_BUYER_PHONENUM').val() == ''){
			//????????? ??????????????? ???????????? ???????????? ( ????????? ???????????? ????????? ???????????? ?????? ????????? ????????????... ?????? ?????? )
			showLayerOpen('????????? ??????????????? ???????????????');
			$('#LGD_BUYER_PHONENUM').focus();
			return;
		}
		
		var bMail = $('#LGD_BUYEREMAIL').val().trim();
		$('#LGD_BUYEREMAIL').val(bMail);
		bMail = $('#LGD_BUYEREMAIL').val();
		if(bMail != 'Qaz'){
			if(bMail == ''){
				showLayerOpen('????????? ?????? ????????? ???????????????');
				return;
			} else if(validateEmail(bMail)){
				showLayerOpen('????????? ????????? ???????????????');
				return;
			}
		}
		document.LGD_PAYINFO.action = 'payment.do';
		document.LGD_PAYINFO.submit();
	}
	//*********************************************************************************************

	function validateEmail(bEmail) {
		var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
		if (filter.test(bEmail)) {
			return false;
		} else {
			return true;
		}
	}
	
	$(function(){
		$("#LGD_BUYER_PHONENUM").on('keydown', function(e){
			var trans_num = $(this).val().replace(/-/gi,'');
			var k = e.keyCode;
		if(trans_num.length >= 11 && ((k >= 48 && k <=126) || (k >= 12592 && k <= 12687 || k==32 || k==229 || (k>=45032 && k<=55203)) ))
		{
	  	    e.preventDefault();
		}
	    }).on('blur', function(){
			if($(this).val() == '') return;
			var trans_num = $(this).val().replace(/-/gi,'');
	      
			if(trans_num != null && trans_num != '') {
	            if(trans_num.length==11 || trans_num.length==10){   
	                var regExp_ctn = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/;
	                if(regExp_ctn.test(trans_num)){
	                    trans_num = trans_num.replace(/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?([0-9]{3,4})-?([0-9]{4})$/, "$1-$2-$3");                  
	                    $(this).val(trans_num);
	                } else {
	                	showLayerOpen("???????????? ?????? ???????????? ?????????.");
	                    $(this).val("");
	                    $(this).focus();
	                }
	            } else {
	            	showLayerOpen("???????????? ?????? ???????????? ?????????.");
	                $(this).val("");
	                $(this).focus();
				}
			}
		});  
	});
	
	//*********************************************************************************************
	// ????????? ???????????? ?????? ??????
	//*********************************************************************************************
	function funcSelectGood(giftName, dataSize, sellAmount, issueNo, giftKub, issueName, campStartDate, campEndDate){
		$('#giftName').val(giftName);
		$('#dataSize').val(dataSize);
		$('#sellAmount').val(sellAmount);
		$('#issueNo').val(issueNo);
		$('#giftKub').val(giftKub);
		$('#issueName').val(issueName);
		$('#campStartDate').val(campStartDate);
		$('#campEndDate').val(campEndDate);
		if(dataSize == ''){
			$('#selData').html('???????????????');
		}else{
			$('#selData').html(dataSize+'MB/'+sellAmount+'???');	
		}
		closeGoodListPop();
	}
	//*********************************************************************************************
	
	
	
	//*********************************************************************************************
	// ??????????????? ?????? ??????
	//*********************************************************************************************
	function closeGoodListPop(){
		$('.dropdown-container').hide();
        $('.dim').hide();
	}
	//*********************************************************************************************
	
		
	
	//*********************************************************************************************
	// ?????? ?????? ????????? ?????? ??????
	//*********************************************************************************************
	function showLayerOpen(msg){
		$('.info').html(msg);
		$('.dim').show();
		$('.popup').show();
	}
	//*********************************************************************************************
	
	
	
	//*********************************************************************************************
	// ?????? ?????? ????????? ?????? ??????
	//*********************************************************************************************
	function showLayerClose(){
		$('.dim').hide();
		$('.popup').hide();
	}
	//*********************************************************************************************
	
</script>
</head>
<body>

	<div class="page page-scroll">
		<!-- ????????? ????????? ?????? .page-scroll -->
		<div class="pageHead">
			<a href="javascript:;" class="headPre" onclick="back();">??????</a>
			<div class="headTitle">????????? ??????</div>
			<!-- <a href="#" class="headMenu">??????</a> -->
		</div>
		<div class="container">
		<form method="get" name="LGD_PAYINFO" id="LGD_PAYINFO">
			<input type="hidden" name="giftName" 	id="giftName"   value=""/>
			<input type="hidden" name="dataSize" 	id="dataSize"   value=""/>
			<input type="hidden" name="sellAmount" 	id="sellAmount" value=""/>
			<input type="hidden" name="issueNo" 	id="issueNo"    value=""/>
			<input type="hidden" name="giftKub" 	id="giftKub"    value=""/>
			<input type="hidden" name="issueName" 	id="issueName"    value=""/>
			<input type="hidden" name="campStartDate" 	id="campStartDate"    value=""/>
			<input type="hidden" name="campEndDate" 	id="campEndDate"    value=""/>
			<input type="hidden" name="carNumber" 	value="<c:out value="${carNumber}" />" />
			<input type="hidden" name="canBuyGift"  value="<c:out value="${canBuyGift}" />" />
			<input type="hidden" name="LGD_CUSTOM_FIRSTPAY" id="LGD_CUSTOM_FIRSTPAY" value="SC0010" />
			<input type="hidden" name="devType" id="devType" value="<c:out value="${devType}" />" />
			<input type="hidden" name="mgrAppCtn" id="mgrAppCtn" value="<c:out value="${mgrAppCtn}" />" />
			<input type="hidden" name="ccssToken" id="ccssToken" value="<c:out value="${ccssToken}" />" />
			<input type="hidden" name="appVersion" id="appVersion" value="<c:out value="${appVersion}" />" />
			<input type="hidden" name="carOem" id="carOem" value="<c:out value="${carOem}" />" />
			<input type="hidden" name="devModel" id="devModel" value="<c:out value="${devModel}" />" />
			<input type="hidden" name="nwType" id="nwType" value="<c:out value="${nwType}" />" />
			<input type="hidden" name="posX" id="posX" value="<c:out value="${posX}" />" />
			<input type="hidden" name="posY" id="posY" value="<c:out value="${posY}" />" />
			<input type="hidden" name="userCtn" id="userCtn" value="<c:out value="${userCtn}" />" />
			<input type="hidden" name="uuid" id="uuid" value="<c:out value="${uuid}" />" />
			
			<section>
			<div class="data_info_view">
				<dl>
					<dd>
						<div class="info_view_inner2">
							<ul class="type2">
								<li>????????? ???????????? ???????????? ????????? ???????????? wifi ???????????? ????????? ??? ????????????.</li>
								<li><font color="red">?????? ??? ?????? ????????????, ??????????????? 1????????????.</font></li>
							</ul>
						</div>
					</dd>
				</dl>
			</div>
			</section>
			<section>
			<div class="section_inner">
				<div class="d-grid">
					<table>
						<caption>??????????????????</caption>
						<colgroup>
							<col style="width: 45%">
							<col style="width: auto">
						</colgroup>
						<tbody>
							<tr>
								<th>????????????</th>
								<td>${carNumber}<span class="text">?????? ????????? ????????? ???????????? ???????????????.</span></td>
							</tr>
							<tr>
								<th>???????????????</th>
								<td><a href="#bank" class="select-box"><span id="selData">???????????????</span></a></td>
							</tr>
							<tr>
								<th>????????????</th>
								<td>????????????</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			</section>
			
			<section>
			<div class="data_info_view">
				<dl>
					<dd>
						<div class="info_view_inner2">
							<ul class="type2">
								<li>PG?????? ???????????? <font color="red">KB??????????????? ????????? ??????</font>?????????.</li>
								<li>????????????????????? <font color="red">????????? ?????? ???????????? ?????????????????? ?????? ????????? ???????????? ??????&frasl;??????&frasl;??????&frasl;??????????????? ??????</font>?????????.</li>
							</ul>
						</div>
					</dd>
				</dl>
			</div>
			</section>
			
			<section>
			<div class="data_info_view">
				<dl>
					<dt>????????? ??????</dt>
					<dd>
						<div class="d-grid">
							<table>
								<caption>????????? ?????? ??????</caption>
								<colgroup>
									<col style="width: 45%">
									<col style="width: auto">
								</colgroup>
								<tbody>
									<tr class="input">
										<th>??????</th>
										<td><input type="text" name="LGD_BUYER" id='LGD_BUYER' placeholder="??????"/></td>
									</tr>
									<tr class="input">
										<th>????????? ??????</th>
										<td><input type="text" name="LGD_BUYER_PHONENUM" id='LGD_BUYER_PHONENUM' placeholder="???-??? ?????? ????????? ??????"/></td>
									</tr>
									<tr class="input">
										<th>?????????</th>
										<td><input type="text" name="LGD_BUYEREMAIL" id='LGD_BUYEREMAIL' placeholder="?????????"/></td>
									</tr>
								</tbody>
							</table>
							<br>
							<br>
							<br>
							<br>
							<br>
						</div>
					</dd>
				</dl>
			</div>
			</section>			

			<div class="footerBtnArea fixed">
				<a href="javascript:;" class="footer_btn btnAll color1" onclick="order();">????????????</a>
				<!--
				<a  href="payment.do?carNumber=${carNumber}" class="footer_btn btnAll color1">????????????</a>
                <input type="submit" class="footer_btn btnAll color1" value="????????????" /><br/> 
                -->
			</div>

		<div class="dim"></div>
			<div class="dropdown-container" id="bank">
				<!-- style="background:#282d38 url('../images/temp/TW_pnd_12.jpg') no-repeat 0 0;" -->
				<div class="dropdown-content" id="scroll1">
					<!-- ?????? ????????? ???????????? -->
					<ul>
						
						<!-- <li><a href="#">???????????????</a></li> -->
						<li onclick="javascript:funcSelectGood('', '', '', '', '', '', '', '');">???????????????</li>
						
						<c:forEach var="vo" items="${list}">
							<li onclick="javascript:funcSelectGood( <c:out value="'${vo.giftName}'" />, 
																	<c:out value="'${vo.dataSize}'" />, 
																	<c:out value="'${vo.sellAmount}'" />, 
																	<c:out value="'${vo.issueNo}'" />, 
																	<c:out value="'${vo.giftKub}'" />, 
																	<c:out value="'${vo.issueName}'" />, 
																	<c:out value="'${vo.campStartDate}'" />, 
																	<c:out value="'${vo.campEndDate}'" />);">
				                <c:out value="${vo.dataSize}" />MB/<c:out value="${vo.sellAmount}" />???
							</li>	
						</c:forEach>
					</ul>
				</div>
			</div>
		<!-- s: popup -->
		<div class="popup" style="display: none;">
		   <div class="popup_inner">
			   <div class="info">
				   ????????? ????????? ????????? ?????????.
			   </div>
		   </div>
		   <div class="popup_btnArea">
			   <a href="#" class="btn color2">??????</a>
		   </div>
		</div>
		</form> 
		</div>
	</div>
	</div>
</body>
</html>