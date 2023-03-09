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
	* [���� ������û ������(STEP1)]
	*
	* ���������������� �⺻ �Ķ���͸� ���õǾ� ������, ������ �ʿ��Ͻ� �Ķ���ʹ� �����޴����� �����Ͻþ� �߰� �Ͻñ� �ٶ��ϴ�.
	*/
	
	/*
	* 1. �⺻���� ������û ���� ����
	*
	* �⺻������ �����Ͽ� �ֽñ� �ٶ��ϴ�.(�Ķ���� ���޽� POST�� ����ϼ���)
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
	
	String giftKub = (String)request.getAttribute("giftKub");				//��ǰ�� ���� {N-�Ϲ�, Y-û�ҳ�}, ex) "N", 
	String issueName = (String)request.getAttribute("issueName");			// �����, ex) "OOOO �̺�Ʈ"
	String campStartDt = (String)request.getAttribute("campStartDt");		// ķ���� ��������, ex) "2014-01-01"
	String campEndDt = (String)request.getAttribute("campEndDt");			// ������ ��������, ex) "2014-01-15"
	String mgrAppId = (String)request.getAttribute("mgrAppId");				//  �Ŵ�����ID.
	String mgrAppCtn = (String)request.getAttribute("mgrAppCtn");			//  �Ŵ����� CTN.
	String memberId = (String)request.getAttribute("memberId");				// ȸ��ID
	
	String ccssToken = (String)request.getAttribute("ccssToken");
	String appVersion = (String)request.getAttribute("appVersion");
	String carOem = (String)request.getAttribute("carOem");
	String devModel = (String)request.getAttribute("devModel");
	String nwType = (String)request.getAttribute("nwType");
	String posX = (String)request.getAttribute("posX");
	String posY = (String)request.getAttribute("posY");
	String userCtn = (String)request.getAttribute("userCtn");
	String uuid = (String)request.getAttribute("uuid");
	
	String CST_PLATFORM = (String)request.getAttribute("CST_PLATFORM");  	//LG���÷��� �������� ����(test:�׽�Ʈ, service:����)
	String CST_MID = (String)request.getAttribute("CST_MID");   			//LG���÷����� ���� �߱޹����� �������̵� �Է��ϼ���.
	String LGD_MERTKEY = (String)request.getAttribute("LGD_MERTKEY");		//"�ݵ�� �Է��ϼ���";
	String LGD_RETURNURL = (String)request.getAttribute("LGD_RETURNURL");	// LGD_RETURNURL �� �����Ͽ� �ֽñ� �ٶ��ϴ�. 
																			// �ݵ�� ���� �������� ������ ����Ʈ�� ��  ȣ��Ʈ�̾�� �մϴ�. �Ʒ� �κ��� �ݵ�� �����Ͻʽÿ�.
	String LGD_OID = (String)request.getAttribute("LGD_OID");				//�ֹ���ȣ(�������� ����ũ�� �ֹ���ȣ�� �Է��ϼ���)
	String LGD_PRODUCTINFO = (String)request.getAttribute("LGD_PRODUCTINFO"); 	//��ǰ��
	String LGD_TIMESTAMP = (String)request.getAttribute("LGD_TIMESTAMP");		//Ÿ�ӽ�����
	
	String LGD_MID              = ("test".equals(CST_PLATFORM.trim())?"t":"")+CST_MID;  //�׽�Ʈ ���̵�� 't'�� �����ϰ� �Է��ϼ���.
	String LGD_AMOUNT           = sellAmount;											//�����ݾ�("," �� ������ �����ݾ��� �Է��ϼ���)                   
	String LGD_BUYER            = (String)request.getAttribute("LGD_BUYER");                    //�����ڸ�
	String LGD_BUYEREMAIL       = (String)request.getAttribute("LGD_BUYEREMAIL");               //������ �̸���
	String LGD_BUYER_PHONENUM   = (String)request.getAttribute("LGD_BUYER_PHONENUM");    		// ������ ��ȭ��ȣ
	String LGD_CUSTOM_FIRSTPAY  = (String)request.getAttribute("LGD_CUSTOM_FIRSTPAY");          //�������� �ʱ��������
	String LGD_PCVIEWYN			= (String)request.getAttribute("LGD_PCVIEWYN");					//���̳��� �޴�����ȣ �Է� ȭ�� ��� ����(����Ĩ�� ���� �ܸ��⿡�� �Է�-->����Ĩ�� �ִ� �޴������� ���� ����)
	String LGD_CUSTOM_SKIN      = "SMART_XPAY2";                                        //�������� ����â ��Ų
			
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
	* �������(������) ���� ������ �Ͻô� ��� �Ʒ� LGD_CASNOTEURL �� �����Ͽ� �ֽñ� �ٶ��ϴ�.
 	*/
	//	String LGD_CASNOTEURL		= "http://172.30.1.49:8080/SmartXpayDemo/cas_noteurl.jsp";		//"http://����URL/cas_noteurl.jsp";
	
	/*
	* ISP ī����� ������ ���� �Ķ����(�ʼ�)
	* - LGD_KVPMISPWAPURL : ISP�񵿱��� ����, ���οϷ� �� ����ڿ��� �������� �������� URL.
	* - LGD_KVPMISPCANCELURL : ISP�񵿱��� ����, ������ ISP�� ��ҽÿ� ����ڿ��� �����ִ� ��� URL.
	*/
	String LGD_KVPMISPWAPURL = null;
	if("ios".contentEquals(devType.trim())) 
	{
		LGD_KVPMISPWAPURL		= "";
	}else{
		LGD_KVPMISPWAPURL		= "";
	}
	String LGD_KVPMISPCANCELURL     = "";
	
	String LGD_MPILOTTEAPPCARDWAPURL = ""; //iOS ������ �ʼ�
	
	/*
	* ������ü ������ ���� �Ķ����(�ʼ�)
	*/
	String LGD_MTRANSFERWAPURL 		= "";
	String LGD_MTRANSFERCANCELURL 	= "";   
	   
	
	/*
    *************************************************
    * 2. MD5 �ؽ���ȣȭ (�������� ������) - BEGIN
    *
    * MD5 �ؽ���ȣȭ�� �ŷ� �������� �������� ����Դϴ�.
    *************************************************
    *
    * �ؽ� ��ȣȭ ����( LGD_MID + LGD_OID + LGD_AMOUNT + LGD_TIMESTAMP + LGD_MERTKEY )
    * LGD_MID          : �������̵�
    * LGD_OID          : �ֹ���ȣ
    * LGD_AMOUNT       : �ݾ�
    * LGD_TIMESTAMP    : Ÿ�ӽ�����
    * LGD_MERTKEY      : ����MertKey (mertkey�� ���������� -> ������� -> ���������������� Ȯ���ϽǼ� �ֽ��ϴ�)
    *
    * MD5 �ؽ������� ��ȣȭ ������ ����
    * LG���÷������� �߱��� ����Ű(MertKey)�� ȯ�漳�� ����(lgdacom/conf/mall.conf)�� �ݵ�� �Է��Ͽ� �ֽñ� �ٶ��ϴ�.
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
	* 2. MD5 �ؽ���ȣȭ (�������� ������) - END
	*************************************************
	*/
	
	String CST_WINDOW_TYPE = "submit";			//�����Ұ�
	
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
	
	System.out.println(String.format("* (EUC-KR)���ڵ� üũ => LGD_BUYER=%s", new String(LGD_BUYER.getBytes("EUC-KR"))));
	System.out.println(String.format("* (EUC-KR)���ڵ� üũ => LGD_PRODUCTINFO=%s", new String(LGD_PRODUCTINFO.getBytes("EUC-KR"))));
	
	String euc_kr_LGD_BUYER = null;
	String euc_kr_LGD_PRODUCTINFO = null;
	try {
		euc_kr_LGD_BUYER = new String(LGD_BUYER.getBytes("EUC-KR"), "EUC-KR");
		euc_kr_LGD_PRODUCTINFO = new String(LGD_PRODUCTINFO.getBytes("EUC-KR"), "EUC-KR");
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	payReqMap.put("CST_PLATFORM"				, CST_PLATFORM);						// �׽�Ʈ, ���� ����
	payReqMap.put("CST_MID"						, CST_MID );							// �������̵�
	payReqMap.put("CST_WINDOW_TYPE"				, CST_WINDOW_TYPE );					// ���۹�� ����
	payReqMap.put("LGD_MID"						, LGD_MID );							// �������̵�
	payReqMap.put("LGD_OID"						, LGD_OID );							// �ֹ���ȣ
	payReqMap.put("LGD_BUYER"					, euc_kr_LGD_BUYER);							// ������
	payReqMap.put("LGD_PRODUCTINFO"				, euc_kr_LGD_PRODUCTINFO);					// ��ǰ����
	payReqMap.put("LGD_AMOUNT"					, LGD_AMOUNT );							// �����ݾ�
	payReqMap.put("LGD_BUYEREMAIL"				, LGD_BUYEREMAIL );						// ������ �̸���
	payReqMap.put("LGD_CUSTOM_SKIN"				, LGD_CUSTOM_SKIN );					// ����â SKIN
	payReqMap.put("LGD_CUSTOM_PROCESSTYPE"		, LGD_CUSTOM_PROCESSTYPE );				// Ʈ����� ó�����
	payReqMap.put("LGD_TIMESTAMP"				, LGD_TIMESTAMP );						// Ÿ�ӽ�����
	payReqMap.put("LGD_HASHDATA"				, LGD_HASHDATA );						// MD5 �ؽ���ȣ��
	payReqMap.put("LGD_RETURNURL"				, LGD_RETURNURL );						// �������������
	payReqMap.put("LGD_VERSION"					, "JSP_Non-ActiveX_SmartXPay");			// �������� (�������� ������)
	payReqMap.put("LGD_CUSTOM_FIRSTPAY"			, LGD_CUSTOM_FIRSTPAY );				// ����Ʈ ��������
	payReqMap.put("LGD_PCVIEWYN"				, LGD_PCVIEWYN );						// �޴�����ȣ �Է� ȭ�� ��� ����
	
	payReqMap.put("LGD_CUSTOM_SWITCHINGTYPE"	, "SUBMIT" );							// �ſ�ī�� ī��� ���� ������ ���� ���
	

	

	//iOS ������ �ʼ�
	payReqMap.put("LGD_MPILOTTEAPPCARDWAPURL"	, LGD_MPILOTTEAPPCARDWAPURL );


	/*
	****************************************************
	* �ſ�ī�� ISP(����/BC)�������� ���� - BEGIN 
	****************************************************
	*/
	payReqMap.put("LGD_KVPMISPWAPURL"			, LGD_KVPMISPWAPURL );	
	payReqMap.put("LGD_KVPMISPCANCELURL"		, LGD_KVPMISPCANCELURL );
	/*
	****************************************************
	* �ſ�ī�� ISP(����/BC)�������� ����  - END
	****************************************************
	*/
		
	/*
	****************************************************
	* ������ü �������� ���� - BEGIN 
	****************************************************
	*/
	payReqMap.put("LGD_MTRANSFERWAPURL"			, LGD_MTRANSFERWAPURL );	
	payReqMap.put("LGD_MTRANSFERCANCELURL"		, LGD_MTRANSFERCANCELURL );
	
	/*
	****************************************************
	* ������ü �������� ����  - END
	****************************************************
	*/
	
	
	/*
	****************************************************
	* ����� OS�� ISP(����/��), ������ü ���� ���� ��
	****************************************************
	1) Web to Web
	- �ȵ���̵�: A (����Ʈ)
	- iOS: N
	  ** iOS�� ���, �ݵ�� N���� ���� ����
	2) App to Web(�ݵ�� SmartXPay_AppToWeb_�������̵带 �����մϴ�.)
	- �ȵ���̵�, iOS: A
	*/
	
	logger.debug(String.format("devType=%s", devType));
	
	if("ios".contentEquals(devType.trim()))
	{
		payReqMap.put("LGD_KVPMISPAUTOAPPYN"	, "N");					// �ſ�ī�� ���� ���� �ʼ�
		payReqMap.put("LGD_MTRANSFERAUTOAPPYN"	, "N");					// ������ü ���� ���� �ʼ�
		
	}else{
		payReqMap.put("LGD_KVPMISPAUTOAPPYN"	, "A");					// �ſ�ī�� ���� ���� �ʼ�
		payReqMap.put("LGD_MTRANSFERAUTOAPPYN"	, "A");					// ������ü ���� ���� �ʼ�
	}
	

	// �������(������) ���������� �Ͻô� ���  �Ҵ�/�Ա� ����� �뺸�ޱ� ���� �ݵ�� LGD_CASNOTEURL ������ LG ���÷����� �����ؾ� �մϴ� .
//	payReqMap.put("LGD_CASNOTEURL"		, LGD_CASNOTEURL );			// ������� NOTEURL
	
	// Return URL���� ���� ��� ���� �� ���õ� �Ķ���͵�
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

<!-- SmartXpay javascript ���� -->
<script language="javascript" src="https://xpay.uplus.co.kr/xpay/js/xpay_crossplatform.js" type="text/javascript"></script>
<script type="text/javascript">

	var LGD_window_type = '<%=CST_WINDOW_TYPE%>';

	/*
	* �����Ұ�
	*/
	function launchCrossPlatform(){
		
		lgdwin = open_paymentwindow(document.getElementById('LGD_PAYINFO'), '<%= CST_PLATFORM %>', LGD_window_type);
	}
	/*
	* FORM ��  ���� ����
	*/
	function getFormObject() {
		return document.getElementById("LGD_PAYINFO");
	}

</script>
<!-- SmartXpay javascript ���� -->

<script type="text/javascript" src="../../js/phone/jquery-1.10.1.js"></script>
<script type="text/javascript" src="../../js/phone/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../js/phone/custom.js"></script>
<script type="text/javascript">

	//*********************************************************************************************
	// jQuery Event ����
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
	// ���� ȭ������ ���ư���
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
	// ���� ó��
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
	// ���� �޽��� �˾� ����
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
	// ���� �޽��� �˾� ����
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
	// ���� ���� �޽��� �˾� �ݱ�
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
		<!-- ��ũ�� ������ ��� .page-scroll -->
		<div class="pageHead">
			<a href="javascript:;" class="headPre" onclick="back();">����</a>
			<div class="headTitle">�����ϱ�</div>
			<!-- <a href="#" class="headMenu">�޴�</a> -->
		</div>
		<div class="container">
			<section>
			<div class="data_info_view">
				<dl>
					<dd>
						<div class="info_view_inner2">
							<ul class="type2">
								<li>�Ʒ� �ֹ� ������ Ȯ���� �� �������ּ���.</li>
							</ul>
						</div>
					</dd>
				</dl>
			</div>
			</section>
			<section>
			
			<div class="data_info_view">
				<dl>
					<dt>�ֹ�����</dt>
					<dd>
						<div class="d-grid">
							<table>
								<caption>�ֹ�������ȸ</caption>
								<colgroup>
									<col style="width: 45%">
									<col style="width: auto">
								</colgroup>
								<tbody>
									<tr>
										<th>��������</th>
										<td><c:out value="${carNumber}" /></td>
									</tr>
									<tr>
										<th>�����Ϳ뷮</th>
										<td><c:out value="${dataSize}" />MB</td>
									</tr>
									<tr>
										<th>����</th>
										<td><c:out value="${sellAmount}" />��<span class="tex">(�ΰ��� ����)</span></td>
									</tr>
									<tr>
										<th>�̿�Ⱓ</th>
										<td>��� �� 1��</td>
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
					<dt>��������</dt>
					<dd>
						<div class="d-grid">
							<table>
								<caption>����������ȸ</caption>
								<colgroup>
									<col style="width: 45%">
									<col style="width: auto">
								</colgroup>
								<tbody>
									<tr>
										<th>�������</th>
										<td>�ſ�ī��</td>
									</tr>
									<tr>
										<th>�����ݾ�</th>
										<td><span class="txt-brown"><c:out value="${sellAmount}" />��</span></td>
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
					<dt>������ ����</dt>
					<dd>
						<div class="d-grid">
							<table>
								<caption>������ ���� �Է�</caption>
								<colgroup>
									<col style="width: 45%">
									<col style="width: auto">
								</colgroup>
								<tbody>
									<tr>
										<th>�̸�</th>
										<td><c:out value="${LGD_BUYER}" /></td>
									</tr>
									<tr>
										<th>�޴��� ��ȣ</th>
										<td><c:out value="${LGD_BUYER_PHONENUM}" /></td>
									</tr>
									<tr>
										<th>�̸���</th>
										<td><c:out value="${LGD_BUYEREMAIL}" /></td>
									</tr>
									<tr>
										<td colspan="2">
											<p class="mt10 mb10">
												<!-- <span class="chk-mgr"> -->
												<span class="chk-mgr">
													<input type="checkbox" id="check2">
													<label for="check2">�ֹ����� �� ���������� Ȯ���߽��ϴ�.</label>
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
													<label for="check3">���� �� �ڵ� ��Ͽ� �����ϸ�,��� �� ȯ���� �� �����ϴ�.</label>
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
				<a href="javascript:;" class="footer_btn color2" onclick="back();">����ϱ�</a>
				<a href="javascript:;" class="footer_btn color1" onclick="pay();">�����ϱ�</a>
			</div>
		</div>
	</div>
	
		<!-- s: popup -->
		<div class="dim" style="display: none;"></div>

		<div class="popup" style="display: none;">
		   <div class="popup_inner">
			   <div class="info">
				   �ֹ����� �� �������� Ȯ�ο� üũ�� �ּ���.
			   </div>
		   </div>
		   <div class="popup_btnArea">
			   <a href="#" class="btn color2">Ȯ��</a>
		   </div>
		</div>
		
		<!-- s: popup -->
		<div class="dim1" style="display: none;"></div>

		<div class="popup1" style="display: none;">
		   <div class="popup_inner">
			   <div class="info">
				   �ڵ� ��� ���� �� ȯ�� �Ұ� �ȳ��� üũ���ּ���. 
			   </div>
		   </div>
		   <div class="popup_btnArea">
			   <a href="#" class="btn color2">Ȯ��</a>
		   </div>
		</div>
	</form>
</body>
</html>