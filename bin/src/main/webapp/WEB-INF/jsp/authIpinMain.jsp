<%@ page contentType="text/html;charset=euc-kr"%>
<%@ page language="java" import="Kisinfo.Check.IPIN2Client"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	/********************************************************************************************************************************************
		NICE������ Copyright(c) KOREA INFOMATION SERVICE INC. ALL RIGHTS RESERVED
		
		���񽺸� : �����ֹι�ȣ���� (IPIN) ����
		�������� : �����ֹι�ȣ���� (IPIN) ȣ�� ������
	*********************************************************************************************************************************************/

	String sSiteCode = (String) request.getAttribute("siteCode"); // IPIN ���� ����Ʈ �ڵ�(NICE���������� �߱��� ����Ʈ�ڵ�)
	String sSitePw = (String) request.getAttribute("sitePw"); // IPIN ���� ����Ʈ �н�����	(NICE���������� �߱��� ����Ʈ�н�����)

	/*
	�� sReturnURL ������ ���� ����  ����������������������������������������������������������������������������������������������������������
		NICE������ �˾����� �������� ����� ������ ��ȣȭ�Ͽ� �ͻ�� �����մϴ�.
		���� ��ȣȭ�� ��� ����Ÿ�� ���Ϲ����� URL ������ �ּ���.
		
		* URL �� http ���� �Է��� �ּž��ϸ�, �ܺο����� ������ ��ȿ�� �������� �մϴ�.
		* ��翡�� �����ص帰 ���������� ��, ipin_process.jsp �������� ����� ������ ���Ϲ޴� ���� �������Դϴ�.
		
		�Ʒ��� URL �����̸�, �ͻ��� ���� �����ΰ� ������ ���ε� �� ���������� ��ġ�� ���� ��θ� �����Ͻñ� �ٶ��ϴ�.
		�� - http://www.test.co.kr/ipin_process.jsp, https://www.test.co.kr/ipin_process.jsp, https://test.co.kr/ipin_process.jsp
	������������������������������������������������������������������������������������������������������������������������������������������
	*/
	String sReturnURL = (String) request.getAttribute("returnUrl");

	/*
	�� sCPRequest ������ ���� ����  ����������������������������������������������������������������������������������������������������������
		[CP ��û��ȣ]�� �ͻ翡�� ����Ÿ�� ���Ƿ� �����ϰų�, ��翡�� ������ ���� ����Ÿ�� ������ �� �ֽ��ϴ�.
		
		CP ��û��ȣ�� ���� �Ϸ� ��, ��ȣȭ�� ��� ����Ÿ�� �Բ� �����Ǹ�
		����Ÿ ������ ���� �� Ư�� ����ڰ� ��û�� ������ Ȯ���ϱ� ���� �������� �̿��Ͻ� �� �ֽ��ϴ�.
		
		���� �ͻ��� ���μ����� �����Ͽ� �̿��� �� �ִ� ����Ÿ�̱⿡, �ʼ����� �ƴմϴ�.
	������������������������������������������������������������������������������������������������������������������������������������������
	*/
	String sCPRequest = "";

	// ��ü ����
	IPIN2Client pClient = new IPIN2Client();

	// �ռ� ����帰 �ٿͰ���, CP ��û��ȣ�� ������ ����� ���� �Ʒ��� ���� ������ �� �ֽ��ϴ�.
	sCPRequest = pClient.getRequestNO(sSiteCode);

	// CP ��û��ȣ�� ���ǿ� �����մϴ�.
	// ���� ������ ������ ������ ipin_result.jsp ���������� ����Ÿ ������ ������ ���� Ȯ���ϱ� �����Դϴ�.
	// �ʼ������� �ƴϸ�, ������ ���� �ǰ�����Դϴ�.
	session.setAttribute("CPREQUEST", sCPRequest);

	// Method �����(iRtn)�� ����, ���μ��� ���࿩�θ� �ľ��մϴ�.
	int iRtn = pClient.fnRequest(sSiteCode, sSitePw, sCPRequest, sReturnURL);

	String sEncData = ""; // ��ȣȭ �� ����Ÿ
	String sRtnMsg = ""; // ó����� �޼���

	// Method ������� ���� ó������
	if (iRtn == 0) {

		// fnRequest �Լ� ó���� ��ü������ ��ȣȭ�� �����͸� �����մϴ�.
		// ����� ��ȣȭ�� ����Ÿ�� ��� �˾� ��û��, �Բ� �����ּž� �մϴ�.
		sEncData = pClient.getCipherData();

		sRtnMsg = "���� ó���Ǿ����ϴ�.";

	} else if (iRtn == -1 || iRtn == -2) {
		sRtnMsg = "������ �帰 ���� ��� ��, �ͻ� ����ȯ�濡 �´� ����� �̿��� �ֽñ� �ٶ��ϴ�.<BR>"
				+ "�ͻ� ����ȯ�濡 �´� ����� ���ٸ� ..<BR><B>iRtn ��, ���� ȯ�������� ��Ȯ�� Ȯ���Ͽ� ���Ϸ� ��û�� �ֽñ� �ٶ��ϴ�.</B>";
	} else if (iRtn == -9) {
		sRtnMsg = "�Է°� ���� : fnRequest �Լ� ó����, �ʿ��� 4���� �Ķ���Ͱ��� ������ ��Ȯ�ϰ� �Է��� �ֽñ� �ٶ��ϴ�.";
	} else {
		sRtnMsg = "iRtn �� Ȯ�� ��, NICE������ ���� ����ڿ��� ������ �ּ���.";
	}
%>

<html>
<head>
<!--  -->
<meta name="robots" content="noindex">
<c:choose>	
	<c:when test="${modelNmGbn == EL1}">
	    <meta name="viewport"
		content="width=device-width, initial-scale=0.666, maximum-scale=1.0, minimum-scale=0.666, user-scalable=no, target-densityDpi=device-dpi" />	
	</c:when>	
	<c:otherwise>	
		<meta name="viewport" content="width=device-width, user-scalable=no">	
	</c:otherwise>
</c:choose>
<!--  -->
<title>NICE������ �����ֹι�ȣ ����</title>

<link rel="stylesheet" href="../../css/common.css">
<link rel="stylesheet" href="../../css/style.css">
<link rel="stylesheet" href="../../css/extend.css">

<script type="text/javascript" src="../../js/jquery-1.10.1.js"></script>
<script type="text/javascript" src="../../js/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../js/custom.js"></script>

<script type="text/javascript">
	/* window.name = "Parent_window";
	var popupIPIN2; */

	function open() {
		/* 
		window.open("", "popupIPIN2", "width=450, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no");
		document.form_ipin.target = "popupIPIN2";
		document.form_ipin.action = "https://cert.vno.co.kr/ipin.cb";
		document.form_ipin.submit();
		 */

		var UserAgent = navigator.userAgent;
		/* ����� ���� üũ*/

		if (UserAgent
				.match(/iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson/i) != null
				|| UserAgent.match(/LG|SAMSUNG|Samsung/) != null) {
			// ������� ��� (�������� ������� �߰� �ʿ�)
			document.form_ipin.target = "";
		} else {
			/* // ������� �ƴ� ���
			popupIPIN2 = window
					.open(
							"",
							"popupIPIN2",
							"width=450, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no");

			if (popupIPIN2 == null) {
				alert(" �� ������ XP SP2 �Ǵ� ���ͳ� �ͽ��÷η� 7 ������� ��쿡�� \n    ȭ�� ��ܿ� �ִ� �˾� ���� �˸����� Ŭ���Ͽ� �˾��� ����� �ֽñ� �ٶ��ϴ�. \n\n�� MSN,����,���� �˾� ���� ���ٰ� ��ġ�� ��� �˾������ ���ֽñ� �ٶ��ϴ�.");
			}
			document.form_ipin.target = "popupIPIN2"; */
		}
		document.form_ipin.action = "https://cert.vno.co.kr/ipin.cb";
		document.form_ipin.submit();
	}
</script>
</head>

<body onload="open();">
	<!-- <body> -->
	<div class="page">
		<!-- ��ũ�� ������ ��� .page-scroll -->
		<div class="container">
			<!-- ���� -->
			<section class="system">
				<p class="notice">���� ���Դϴ�.</p>
			</section>
			<!-- //���� -->
		</div>
	</div>

	<!-- �����ֹι�ȣ ���� �˾��� ȣ���ϱ� ���ؼ��� ������ ���� form�� �ʿ��մϴ�. -->
	<form name="form_ipin" method="post">
		<input type="hidden" name="m" value="pubmain">
		<!-- �ʼ� ����Ÿ��, �����Ͻø� �ȵ˴ϴ�. -->
		<input type="hidden" name="enc_data" value="<%=sEncData%>">
		<!-- ������ ��ü������ ��ȣȭ �� ����Ÿ�Դϴ�. -->

		<!-- ��ü���� ����ޱ� ���ϴ� ����Ÿ�� �����ϱ� ���� ����� �� ������, ������� ����� �ش� ���� �״�� �۽��մϴ�. �ش� �Ķ���ʹ� �߰��Ͻ� �� �����ϴ�. -->
		<input type="hidden" name="param_r1" value=""> <input
			type="hidden" name="param_r2" value=""> <input type="hidden"
			name="param_r3" value="">
		<!-- <a href="javascript:fnPopup();"><img src="http://image.creditbank.co.kr/static/img/vno/new_img/bt_17.gif" width=218 height=40 border=0></a>  -->
	</form>

	<!-- �����ֹι�ȣ ���� �˾� ���������� ����ڰ� ������ ������ ��ȣȭ�� ����� ������ �ش� �˾�â���� �ްԵ˴ϴ�. ���� �θ� �������� �̵��ϱ� ���ؼ��� ������ ���� form�� �ʿ��մϴ�. -->
	<form name="vnoform" method="post">
		<input type="hidden" name="enc_data">
		<!-- �������� ����� ���� ��ȣȭ ����Ÿ�Դϴ�. -->

		<!-- ��ü���� ����ޱ� ���ϴ� ����Ÿ�� �����ϱ� ���� ����� �� ������, ������� ����� �ش� ���� �״�� �۽��մϴ�. �ش� �Ķ���ʹ� �߰��Ͻ� �� �����ϴ�. -->
		<input type="hidden" name="param_r1" value=""> <input
			type="hidden" name="param_r2" value=""> <input type="hidden"
			name="param_r3" value="">
	</form>
	<!--  -->
</body>
</html>