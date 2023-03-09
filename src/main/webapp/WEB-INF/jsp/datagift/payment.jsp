<%@ page contentType="text/html;charset=EUC-KR" %>
<%@ page import="java.security.MessageDigest, java.util.*" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="org.slf4j.*" %>
<%!
	static final Logger logger = LoggerFactory.getLogger("payment.jsp");
%>
<%
	/*
	* [결제 인증요청 페이지(STEP1)]
	*
	* 샘플페이지에서는 기본 파라미터만 예시되어 있으며, 별도로 필요하신 파라미터는 연동메뉴얼을 참고하시어 추가 하시기 바랍니다.
	*/
	
	/*
	* 1. 기본결제 인증요청 정보 변경
	*
	* 기본정보를 변경하여 주시기 바랍니다.(파라미터 전달시 POST를 사용하세요)
	*/

	//&&&&PARAMETER EDIT START&&&&
	String devType = (String)session.getAttribute("devType");

	String ctn = (String)request.getAttribute("ctn");
	String giftName = (String)request.getAttribute("giftName");
	String dataSize = (String)request.getAttribute("dataSize");
	String sellAmount = (String)request.getAttribute("sellAmount");
	String issueNo = (String)request.getAttribute("issueNo");
	String carNumber = (String)request.getAttribute("carNumber");
	String canBuyGift = (String)request.getAttribute("canBuyGift");
	
	String giftKub = (String)request.getAttribute("giftKub");				//상품권 구분 {N-일반, Y-청소년}, ex) "N", 
	String issueName = (String)request.getAttribute("issueName");			// 발행명, ex) "OOOO 이벤트"
	String campStartDt = (String)request.getAttribute("campStartDt");		// 캠페인 시작일자, ex) "2014-01-01"
	String campEndDt = (String)request.getAttribute("campEndDt");			// 켐페인 만료일자, ex) "2014-01-15"
	String mgrAppId = (String)request.getAttribute("mgrAppId");				//  매니저앱ID.
	String mgrAppCtn = (String)request.getAttribute("mgrAppCtn");			//  매니저앱 CTN.
	String memberId = (String)request.getAttribute("memberId");				// 회원ID
	
	String ccssToken = (String)request.getAttribute("ccssToken");
	String appVersion = (String)request.getAttribute("appVersion");
	String carOem = (String)request.getAttribute("carOem");
	String devModel = (String)request.getAttribute("devModel");
	String nwType = (String)request.getAttribute("nwType");
	String posX = (String)request.getAttribute("posX");
	String posY = (String)request.getAttribute("posY");
	String userCtn = (String)request.getAttribute("userCtn");
	String uuid = (String)request.getAttribute("uuid");
	
	String CST_PLATFORM = (String)request.getAttribute("CST_PLATFORM");  	//LG유플러스 결제서비스 선택(test:테스트, service:서비스)
	String CST_MID = (String)request.getAttribute("CST_MID");   			//LG유플러스로 부터 발급받으신 상점아이디를 입력하세요.
	String LGD_MERTKEY = (String)request.getAttribute("LGD_MERTKEY");		//"반드시 입력하세요";
	String LGD_RETURNURL = (String)request.getAttribute("LGD_RETURNURL");	// LGD_RETURNURL 을 설정하여 주시기 바랍니다. 
																			// 반드시 현재 페이지와 동일한 프로트콜 및  호스트이어야 합니다. 아래 부분을 반드시 수정하십시요.
	String LGD_OID = (String)request.getAttribute("LGD_OID");				//주문번호(상점정의 유니크한 주문번호를 입력하세요)
	String LGD_PRODUCTINFO = (String)request.getAttribute("LGD_PRODUCTINFO"); 	//상품명
	String LGD_TIMESTAMP = (String)request.getAttribute("LGD_TIMESTAMP");		//타임스탬프
	
	String LGD_MID              = ("test".equals(CST_PLATFORM.trim())?"t":"")+CST_MID;  //테스트 아이디는 't'를 제외하고 입력하세요.
	String LGD_AMOUNT           = sellAmount;											//결제금액("," 를 제외한 결제금액을 입력하세요)                   
	String LGD_BUYER            = (String)request.getAttribute("LGD_BUYER");                    //구매자명
	String LGD_BUYEREMAIL       = (String)request.getAttribute("LGD_BUYEREMAIL");               //구매자 이메일
	String LGD_BUYER_PHONENUM   = (String)request.getAttribute("LGD_BUYER_PHONENUM");    		// 구매자 전화번호
	String LGD_CUSTOM_FIRSTPAY  = (String)request.getAttribute("LGD_CUSTOM_FIRSTPAY");          //상점정의 초기결제수단
	String LGD_PCVIEWYN			= (String)request.getAttribute("LGD_PCVIEWYN");					//페이나우 휴대폰번호 입력 화면 사용 여부(유심칩이 없는 단말기에서 입력-->유심칩이 있는 휴대폰에서 실제 결제)
	String LGD_CUSTOM_SKIN      = "SMART_XPAY2";                                        //상점정의 결제창 스킨
			
	logger.debug(
		String.format("memberId=%s, ctn=%s, mgrAppCtn=%s, giftName=%s, dataSize=%s, sellAmount=%s, issueNo=%s, carNumber=%s, CST_PLATFORM=%s, CST_MID=%s, LGD_MERTKEY=%s, LGD_RETURNURL=%s, " + 
			"LGD_OID=%s, LGD_PRODUCTINFO=%s, LGD_TIMESTAMP=%s, LGD_MID=%s, LGD_AMOUNT=%s, LGD_BUYER=%s, LGD_BUYEREMAIL=%s, LGD_BUYER_PHONENUM=%s, " + 
			"LGD_CUSTOM_FIRSTPAY=%s, LGD_PCVIEWYN=%s, LGD_CUSTOM_SKIN=%s",
			memberId, ctn, mgrAppCtn, giftName, dataSize, sellAmount, issueNo, carNumber, CST_PLATFORM, CST_MID, LGD_MERTKEY, LGD_RETURNURL, 
			LGD_OID, LGD_PRODUCTINFO, LGD_TIMESTAMP, LGD_MID, LGD_AMOUNT, LGD_BUYER, LGD_BUYEREMAIL, LGD_BUYER_PHONENUM, 
			LGD_CUSTOM_FIRSTPAY, LGD_PCVIEWYN, LGD_CUSTOM_SKIN
		)
	);		
	
	logger.debug("LGD_RETURNURL=" + LGD_RETURNURL);
			
	//&&&&PARAMETER EDIT END&&&&

	/*
	* 가상계좌(무통장) 결제 연동을 하시는 경우 아래 LGD_CASNOTEURL 을 설정하여 주시기 바랍니다.
 	*/
	//	String LGD_CASNOTEURL		= "http://172.30.1.49:8080/SmartXpayDemo/cas_noteurl.jsp";		//"http://상점URL/cas_noteurl.jsp";
	
	/*
	* ISP 카드결제 연동을 위한 파라미터(필수)
	* - LGD_KVPMISPWAPURL : ISP비동기방식 사용시, 승인완료 후 사용자에게 보여지는 결제성공 URL.
	* - LGD_KVPMISPCANCELURL : ISP비동기방식 사용시, 결제중 ISP앱 취소시에 사용자에게 보여주는 취소 URL.
	*/
	String LGD_KVPMISPWAPURL = null;
	if("ios".contentEquals(devType.trim())) 
	{
		LGD_KVPMISPWAPURL		= "";
	}else{
		LGD_KVPMISPWAPURL		= "";
	}
	String LGD_KVPMISPCANCELURL     = "";
	
	String LGD_MPILOTTEAPPCARDWAPURL = ""; //iOS 연동시 필수
	
	/*
	* 계좌이체 연동을 위한 파라미터(필수)
	*/
	String LGD_MTRANSFERWAPURL 		= "";
	String LGD_MTRANSFERCANCELURL 	= "";   
	   
	
	/*
    *************************************************
    * 2. MD5 해쉬암호화 (수정하지 마세요) - BEGIN
    *
    * MD5 해쉬암호화는 거래 위변조를 막기위한 방법입니다.
    *************************************************
    *
    * 해쉬 암호화 적용( LGD_MID + LGD_OID + LGD_AMOUNT + LGD_TIMESTAMP + LGD_MERTKEY )
    * LGD_MID          : 상점아이디
    * LGD_OID          : 주문번호
    * LGD_AMOUNT       : 금액
    * LGD_TIMESTAMP    : 타임스탬프
    * LGD_MERTKEY      : 상점MertKey (mertkey는 상점관리자 -> 계약정보 -> 상점정보관리에서 확인하실수 있습니다)
    *
    * MD5 해쉬데이터 암호화 검증을 위해
    * LG유플러스에서 발급한 상점키(MertKey)를 환경설정 파일(lgdacom/conf/mall.conf)에 반드시 입력하여 주시기 바랍니다.
    */

    StringBuffer sb = new StringBuffer();
   	sb.append(LGD_MID);
   	sb.append(LGD_OID);
   	sb.append(LGD_AMOUNT);
   	sb.append(LGD_TIMESTAMP);
   	sb.append(LGD_MERTKEY);

	byte[] bNoti = sb.toString().getBytes();
	MessageDigest md = MessageDigest.getInstance("MD5");
	byte[] digest = md.digest(bNoti);
	
	StringBuffer strBuf = new StringBuffer();
	for (int i=0 ; i < digest.length ; i++) {
	    int c = digest[i] & 0xff;
	    if (c <= 15){
	        strBuf.append("0");
	    }
	    strBuf.append(Integer.toHexString(c));
	}
	
	String LGD_HASHDATA = strBuf.toString();
	String LGD_CUSTOM_PROCESSTYPE = "TWOTR";
	/*
	*************************************************
	* 2. MD5 해쉬암호화 (수정하지 마세요) - END
	*************************************************
	*/
	
	String CST_WINDOW_TYPE = "submit";			//수정불가
	
	HashMap payReqMap = new HashMap();
	
	payReqMap.put("memberId", memberId);
	payReqMap.put("ctn", ctn );
	payReqMap.put("giftName", giftName);
	payReqMap.put("dataSize", dataSize);
	payReqMap.put("sellAmount", sellAmount);
	payReqMap.put("issueNo", issueNo);
	payReqMap.put("carNumber", carNumber);
	payReqMap.put("canBuyGift", canBuyGift);
	
	payReqMap.put("giftKub", giftKub);
	payReqMap.put("issueName", issueName);
	payReqMap.put("campStartDt", campStartDt);
	payReqMap.put("campEndDt", campEndDt);
	payReqMap.put("mgrAppId", mgrAppId);
	payReqMap.put("mgrAppCtn", mgrAppCtn);
	
	payReqMap.put("ccssToken", ccssToken);
	payReqMap.put("appVersion", appVersion);
	payReqMap.put("carOem", carOem);
	payReqMap.put("devModel", devModel);
	payReqMap.put("nwType", nwType);
	payReqMap.put("posX", posX);
	payReqMap.put("posY", posY);
	payReqMap.put("userCtn", userCtn);
	payReqMap.put("uuid", uuid);
	
	payReqMap.put("LGD_BUYER_PHONENUM", LGD_BUYER_PHONENUM);
	
	System.out.println(String.format("* (EUC-KR)엔코딩 체크 => LGD_BUYER=%s", new String(LGD_BUYER.getBytes("EUC-KR"))));
	System.out.println(String.format("* (EUC-KR)엔코딩 체크 => LGD_PRODUCTINFO=%s", new String(LGD_PRODUCTINFO.getBytes("EUC-KR"))));
	
	String euc_kr_LGD_BUYER = null;
	String euc_kr_LGD_PRODUCTINFO = null;
	try {
		euc_kr_LGD_BUYER = new String(LGD_BUYER.getBytes("EUC-KR"), "EUC-KR");
		euc_kr_LGD_PRODUCTINFO = new String(LGD_PRODUCTINFO.getBytes("EUC-KR"), "EUC-KR");
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	payReqMap.put("CST_PLATFORM"				, CST_PLATFORM);						// 테스트, 서비스 구분
	payReqMap.put("CST_MID"						, CST_MID );							// 상점아이디
	payReqMap.put("CST_WINDOW_TYPE"				, CST_WINDOW_TYPE );					// 전송방식 구분
	payReqMap.put("LGD_MID"						, LGD_MID );							// 상점아이디
	payReqMap.put("LGD_OID"						, LGD_OID );							// 주문번호
	payReqMap.put("LGD_BUYER"					, euc_kr_LGD_BUYER);							// 구매자
	payReqMap.put("LGD_PRODUCTINFO"				, euc_kr_LGD_PRODUCTINFO);					// 상품정보
	payReqMap.put("LGD_AMOUNT"					, LGD_AMOUNT );							// 결제금액
	payReqMap.put("LGD_BUYEREMAIL"				, LGD_BUYEREMAIL );						// 구매자 이메일
	payReqMap.put("LGD_CUSTOM_SKIN"				, LGD_CUSTOM_SKIN );					// 결제창 SKIN
	payReqMap.put("LGD_CUSTOM_PROCESSTYPE"		, LGD_CUSTOM_PROCESSTYPE );				// 트랜잭션 처리방식
	payReqMap.put("LGD_TIMESTAMP"				, LGD_TIMESTAMP );						// 타임스탬프
	payReqMap.put("LGD_HASHDATA"				, LGD_HASHDATA );						// MD5 해쉬암호값
	payReqMap.put("LGD_RETURNURL"				, LGD_RETURNURL );						// 응답수신페이지
	payReqMap.put("LGD_VERSION"					, "JSP_Non-ActiveX_SmartXPay");			// 버전정보 (삭제하지 마세요)
	payReqMap.put("LGD_CUSTOM_FIRSTPAY"			, LGD_CUSTOM_FIRSTPAY );				// 디폴트 결제수단
	payReqMap.put("LGD_PCVIEWYN"				, LGD_PCVIEWYN );						// 휴대폰번호 입력 화면 사용 여부
	
	payReqMap.put("LGD_CUSTOM_SWITCHINGTYPE"	, "SUBMIT" );							// 신용카드 카드사 인증 페이지 연동 방식
	

	

	//iOS 연동시 필수
	payReqMap.put("LGD_MPILOTTEAPPCARDWAPURL"	, LGD_MPILOTTEAPPCARDWAPURL );


	/*
	****************************************************
	* 신용카드 ISP(국민/BC)결제에만 적용 - BEGIN 
	****************************************************
	*/
	payReqMap.put("LGD_KVPMISPWAPURL"			, LGD_KVPMISPWAPURL );	
	payReqMap.put("LGD_KVPMISPCANCELURL"		, LGD_KVPMISPCANCELURL );
	/*
	****************************************************
	* 신용카드 ISP(국민/BC)결제에만 적용  - END
	****************************************************
	*/
		
	/*
	****************************************************
	* 계좌이체 결제에만 적용 - BEGIN 
	****************************************************
	*/
	payReqMap.put("LGD_MTRANSFERWAPURL"			, LGD_MTRANSFERWAPURL );	
	payReqMap.put("LGD_MTRANSFERCANCELURL"		, LGD_MTRANSFERCANCELURL );
	
	/*
	****************************************************
	* 계좌이체 결제에만 적용  - END
	****************************************************
	*/
	
	
	/*
	****************************************************
	* 모바일 OS별 ISP(국민/비씨), 계좌이체 결제 구분 값
	****************************************************
	1) Web to Web
	- 안드로이드: A (디폴트)
	- iOS: N
	  ** iOS일 경우, 반드시 N으로 값을 수정
	2) App to Web(반드시 SmartXPay_AppToWeb_연동가이드를 참조합니다.)
	- 안드로이드, iOS: A
	*/
	
	logger.debug(String.format("devType=%s", devType));
	
	if("ios".contentEquals(devType.trim()))
	{
		payReqMap.put("LGD_KVPMISPAUTOAPPYN"	, "N");					// 신용카드 결제 사용시 필수
		payReqMap.put("LGD_MTRANSFERAUTOAPPYN"	, "N");					// 계좌이체 결제 사용시 필수
		
	}else{
		payReqMap.put("LGD_KVPMISPAUTOAPPYN"	, "A");					// 신용카드 결제 사용시 필수
		payReqMap.put("LGD_MTRANSFERAUTOAPPYN"	, "A");					// 계좌이체 결제 사용시 필수
	}
	

	// 가상계좌(무통장) 결제연동을 하시는 경우  할당/입금 결과를 통보받기 위해 반드시 LGD_CASNOTEURL 정보를 LG 유플러스에 전송해야 합니다 .
//	payReqMap.put("LGD_CASNOTEURL"		, LGD_CASNOTEURL );			// 가상계좌 NOTEURL
	
	// Return URL에서 인증 결과 수신 시 셋팅될 파라미터들
	payReqMap.put("LGD_RESPCODE"		, "" );
	payReqMap.put("LGD_PAYKEY"			, "" );
	payReqMap.put("LGD_RESPMSG"			, "" );
	payReqMap.put("devType", devType);
	
	session.setAttribute("PAYREQ_MAP", payReqMap);

 %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densityDpi=device-dpi" />
<meta http-equiv="Cache-Control" content="No-Cache"> 
<meta http-equiv="Pragma" content="No-Cache">

<title>LGU+ Connected Car</title>
<link rel="stylesheet" href="../../css/phone/common.css">
<link rel="stylesheet" href="../../css/phone/style.css">

<!-- SmartXpay javascript 시작 -->
<script language="javascript" src="https://xpay.uplus.co.kr/xpay/js/xpay_crossplatform.js" type="text/javascript"></script>
<script type="text/javascript">

	var LGD_window_type = '<%=CST_WINDOW_TYPE%>';

	/*
	* 수정불가
	*/
	function launchCrossPlatform(){
		
		lgdwin = open_paymentwindow(document.getElementById('LGD_PAYINFO'), '<%= CST_PLATFORM %>', LGD_window_type);
	}
	/*
	* FORM 명만  수정 가능
	*/
	function getFormObject() {
		return document.getElementById("LGD_PAYINFO");
	}

</script>
<!-- SmartXpay javascript 종료 -->

<script type="text/javascript" src="../../js/phone/jquery-1.10.1.js"></script>
<script type="text/javascript" src="../../js/phone/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../js/phone/custom.js"></script>
<script type="text/javascript">

	//*********************************************************************************************
	// jQuery Event 연결
	//*********************************************************************************************
	$(function() {
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
		$(".dim1").click(function(){
			showLayerClose();
		});
		$(".popup_btnArea").click(function(){
			showLayerClose();
		});
	});
	//*********************************************************************************************



	//*********************************************************************************************
	// 이전 화면으로 돌아가기
	//*********************************************************************************************
	function back() {
		//history.back();
		document.LGD_PAYINFO.action = 'list.do';
		document.LGD_PAYINFO.submit();
	}
	//*********************************************************************************************
	function navigationBack()
	{
		document.LGD_PAYINFO.action = 'list.do';
		document.LGD_PAYINFO.submit();
	}
	
	
	//*********************************************************************************************
	// 결재 처리
	//*********************************************************************************************
	function pay() {
		if(!$("input:checkbox[id='check2']").is(":checked")){
			//openAlertMessage();
			showLayerOpen();
			return;
		}
		else if(!$("input:checkbox[id='check3']").is(":checked")){
			//openAlertMessage();
			showLayerOpen1();
			return;
		}
		launchCrossPlatform();
	}
	//*********************************************************************************************
	
		
	
	//*********************************************************************************************
	// 엘럿 메시지 팝업 열기
	//*********************************************************************************************
	function showLayerOpen(){
		var winH = $(window).height();
		var winW = $(window).width();
		var scrollT = $(window).scrollTop();
	
		$('.popup').css('top', winH/2-$('.popup').height()/2 + scrollT);
		$('.popup').css('left', winW/2-$('.popup').width()/2);
		
		$('.dim').show();
		$('.popup').show();
	}
	//*********************************************************************************************
	
	//*********************************************************************************************
	// 엘럿 메시지 팝업 열기
	//*********************************************************************************************
	function showLayerOpen1(){
		var winH = $(window).height();
		var winW = $(window).width();
		var scrollT = $(window).scrollTop();
	
		$('.popup1').css('top', winH/2-$('.popup1').height()/2 + scrollT);
		$('.popup1').css('left', winW/2-$('.popup1').width()/2);
		
		$('.dim1').show();
		$('.popup1').show();
	}
	//*********************************************************************************************
	
	
	//*********************************************************************************************
	// 공통 엘럿 메시지 팝업 닫기
	//*********************************************************************************************
	function showLayerClose(){
		$('.dim').hide();
		$('.dim1').hide();
		$('.popup').hide();
		$('.popup1').hide();
	}
	//*********************************************************************************************
</script>
</head>
<body>
	<form method="get" name="LGD_PAYINFO" id="LGD_PAYINFO" action="">
	<input type="hidden" name="carNumber"  id="carNumber"  value="<c:out value="${carNumber }" />"/>
	<input type="hidden" name="canBuyGift" id="canBuyGift" value="<c:out value="${canBuyGift}" />"/>
	
	<div class="page page-scroll">
		<!-- 스크롤 페이지 경우 .page-scroll -->
		<div class="pageHead">
			<a href="javascript:;" class="headPre" onclick="back();">이전</a>
			<div class="headTitle">구매하기</div>
			<!-- <a href="#" class="headMenu">메뉴</a> -->
		</div>
		<div class="container">
			<section>
			<div class="data_info_view">
				<dl>
					<dd>
						<div class="info_view_inner2">
							<ul class="type2">
								<li>아래 주문 정보를 확인한 후 결제해주세요.</li>
							</ul>
						</div>
					</dd>
				</dl>
			</div>
			</section>
			<section>
			
			<div class="data_info_view">
				<dl>
					<dt>주문정보</dt>
					<dd>
						<div class="d-grid">
							<table>
								<caption>주문정보조회</caption>
								<colgroup>
									<col style="width: 45%">
									<col style="width: auto">
								</colgroup>
								<tbody>
									<tr>
										<th>적용차량</th>
										<td><c:out value="${carNumber}" /></td>
									</tr>
									<tr>
										<th>데이터용량</th>
										<td><c:out value="${dataSize}" />MB</td>
									</tr>
									<tr>
										<th>가격</th>
										<td><c:out value="${sellAmount}" />원<span class="tex">(부가세 포함)</span></td>
									</tr>
									<tr>
										<th>이용기간</th>
										<td>등록 후 1년</td>
									</tr>
								</tbody>
							</table>
						</div>
					</dd>
				</dl>
			</div>
			</section>
			<section>
			<div class="data_info_view">
				<dl>
					<dt>결제정보</dt>
					<dd>
						<div class="d-grid">
							<table>
								<caption>결제정보조회</caption>
								<colgroup>
									<col style="width: 45%">
									<col style="width: auto">
								</colgroup>
								<tbody>
									<tr>
										<th>결제방법</th>
										<td>신용카드</td>
									</tr>
									<tr>
										<th>결제금액</th>
										<td><span class="txt-brown"><c:out value="${sellAmount}" />원</span></td>
									</tr>
								</tbody>
							</table>
						</div>
					</dd>
				</dl>
			</div>
			</section>

			<section>
			<div class="data_info_view">
				<dl>
					<dt>결제자 정보</dt>
					<dd>
						<div class="d-grid">
							<table>
								<caption>결제자 정보 입력</caption>
								<colgroup>
									<col style="width: 45%">
									<col style="width: auto">
								</colgroup>
								<tbody>
									<tr>
										<th>이름</th>
										<td><c:out value="${LGD_BUYER}" /></td>
									</tr>
									<tr>
										<th>휴대폰 번호</th>
										<td><c:out value="${LGD_BUYER_PHONENUM}" /></td>
									</tr>
									<tr>
										<th>이메일</th>
										<td><c:out value="${LGD_BUYEREMAIL}" /></td>
									</tr>
									<tr>
										<td colspan="2">
											<p class="mt10 mb10">
												<!-- <span class="chk-mgr"> -->
												<span class="chk-mgr">
													<input type="checkbox" id="check2">
													<label for="check2">주문정보 및 결제정보를 확인했습니다.</label>
												</span>
											</p>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<p class="mt10 mb10">
												<!-- <span class="chk-mgr"> -->
												<span class="chk-mgr">
													<input type="checkbox" id="check3">
													<label for="check3">구매 후 자동 등록에 동의하며,등록 후 환불할 수 없습니다.</label>
												</span>
											</p>
										</td>
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

			<%
				for(Iterator i = payReqMap.keySet().iterator(); i.hasNext();){
					Object key = i.next();
					out.println("<input type='hidden' name='" + key + "' id='"+key+"' value='" + payReqMap.get(key) + "'>" );
				}
			%>
						
			
			
			<div class="footerBtnArea ">
				<a href="javascript:;" class="footer_btn color2" onclick="back();">취소하기</a>
				<a href="javascript:;" class="footer_btn color1" onclick="pay();">결제하기</a>
			</div>
		</div>
	</div>
	
		<!-- s: popup -->
		<div class="dim" style="display: none;"></div>

		<div class="popup" style="display: none;">
		   <div class="popup_inner">
			   <div class="info">
				   주문정보 및 결제정보 확인에 체크해 주세요.
			   </div>
		   </div>
		   <div class="popup_btnArea">
			   <a href="#" class="btn color2">확인</a>
		   </div>
		</div>
		
		<!-- s: popup -->
		<div class="dim1" style="display: none;"></div>

		<div class="popup1" style="display: none;">
		   <div class="popup_inner">
			   <div class="info">
				   자동 등록 동의 및 환불 불가 안내에 체크해주세요. 
			   </div>
		   </div>
		   <div class="popup_btnArea">
			   <a href="#" class="btn color2">확인</a>
		   </div>
		</div>
	</form>
</body>
</html>