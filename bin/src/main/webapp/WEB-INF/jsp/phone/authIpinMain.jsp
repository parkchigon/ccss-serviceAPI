<%@ page contentType="text/html;charset=euc-kr"%>
<%@ page language="java" import="Kisinfo.Check.IPIN2Client"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	/********************************************************************************************************************************************
		NICE평가정보 Copyright(c) KOREA INFOMATION SERVICE INC. ALL RIGHTS RESERVED
		
		서비스명 : 가상주민번호서비스 (IPIN) 서비스
		페이지명 : 가상주민번호서비스 (IPIN) 호출 페이지
	*********************************************************************************************************************************************/

	String sSiteCode = (String) request.getAttribute("siteCode"); // IPIN 서비스 사이트 코드(NICE평가정보에서 발급한 사이트코드)
	String sSitePw = (String) request.getAttribute("sitePw"); // IPIN 서비스 사이트 패스워드	(NICE평가정보에서 발급한 사이트패스워드)

	/*
	┌ sReturnURL 변수에 대한 설명  ─────────────────────────────────────────────────────
		NICE평가정보 팝업에서 인증받은 사용자 정보를 암호화하여 귀사로 리턴합니다.
		따라서 암호화된 결과 데이타를 리턴받으실 URL 정의해 주세요.
		
		* URL 은 http 부터 입력해 주셔야하며, 외부에서도 접속이 유효한 정보여야 합니다.
		* 당사에서 배포해드린 샘플페이지 중, ipin_process.jsp 페이지가 사용자 정보를 리턴받는 예제 페이지입니다.
		
		아래는 URL 예제이며, 귀사의 서비스 도메인과 서버에 업로드 된 샘플페이지 위치에 따라 경로를 설정하시기 바랍니다.
		예 - http://www.test.co.kr/ipin_process.jsp, https://www.test.co.kr/ipin_process.jsp, https://test.co.kr/ipin_process.jsp
	└────────────────────────────────────────────────────────────────────
	*/
	String sReturnURL = (String) request.getAttribute("returnUrl");

	/*
	┌ sCPRequest 변수에 대한 설명  ─────────────────────────────────────────────────────
		[CP 요청번호]로 귀사에서 데이타를 임의로 정의하거나, 당사에서 배포된 모듈로 데이타를 생성할 수 있습니다.
		
		CP 요청번호는 인증 완료 후, 암호화된 결과 데이타에 함께 제공되며
		데이타 위변조 방지 및 특정 사용자가 요청한 것임을 확인하기 위한 목적으로 이용하실 수 있습니다.
		
		따라서 귀사의 프로세스에 응용하여 이용할 수 있는 데이타이기에, 필수값은 아닙니다.
	└────────────────────────────────────────────────────────────────────
	*/
	String sCPRequest = "";

	// 객체 생성
	IPIN2Client pClient = new IPIN2Client();

	// 앞서 설명드린 바와같이, CP 요청번호는 배포된 모듈을 통해 아래와 같이 생성할 수 있습니다.
	sCPRequest = pClient.getRequestNO(sSiteCode);

	// CP 요청번호를 세션에 저장합니다.
	// 현재 예제로 저장한 세션은 ipin_result.jsp 페이지에서 데이타 위변조 방지를 위해 확인하기 위함입니다.
	// 필수사항은 아니며, 보안을 위한 권고사항입니다.
	session.setAttribute("CPREQUEST", sCPRequest);

	// Method 결과값(iRtn)에 따라, 프로세스 진행여부를 파악합니다.
	int iRtn = pClient.fnRequest(sSiteCode, sSitePw, sCPRequest, sReturnURL);

	String sEncData = ""; // 암호화 된 데이타
	String sRtnMsg = ""; // 처리결과 메세지

	// Method 결과값에 따른 처리사항
	if (iRtn == 0) {

		// fnRequest 함수 처리시 업체정보를 암호화한 데이터를 추출합니다.
		// 추출된 암호화된 데이타는 당사 팝업 요청시, 함께 보내주셔야 합니다.
		sEncData = pClient.getCipherData();

		sRtnMsg = "정상 처리되었습니다.";

	} else if (iRtn == -1 || iRtn == -2) {
		sRtnMsg = "배포해 드린 서비스 모듈 중, 귀사 서버환경에 맞는 모듈을 이용해 주시기 바랍니다.<BR>"
				+ "귀사 서버환경에 맞는 모듈이 없다면 ..<BR><B>iRtn 값, 서버 환경정보를 정확히 확인하여 메일로 요청해 주시기 바랍니다.</B>";
	} else if (iRtn == -9) {
		sRtnMsg = "입력값 오류 : fnRequest 함수 처리시, 필요한 4개의 파라미터값의 정보를 정확하게 입력해 주시기 바랍니다.";
	} else {
		sRtnMsg = "iRtn 값 확인 후, NICE평가정보 개발 담당자에게 문의해 주세요.";
	}
%>

<html>
<head>
<!--  -->
<meta name="robots" content="noindex">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densityDpi=device-dpi" />
<!--  -->
<title>NICE평가정보 가상주민번호 서비스</title>

<link rel="stylesheet" href="../../css/phone/common.css">
<link rel="stylesheet" href="../../css/phone/style.css">

<script type="text/javascript" src="../../js/phone/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="../../js/phone/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../js/phone/custom.js"></script>

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
		/* 모바일 접근 체크*/

		if (UserAgent
				.match(/iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson/i) != null
				|| UserAgent.match(/LG|SAMSUNG|Samsung/) != null) {
			// 모바일일 경우 (변동사항 있을경우 추가 필요)
			document.form_ipin.target = "";
		} else {
			/* // 모바일이 아닐 경우
			popupIPIN2 = window
					.open(
							"",
							"popupIPIN2",
							"width=450, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no");

			if (popupIPIN2 == null) {
				alert(" ※ 윈도우 XP SP2 또는 인터넷 익스플로러 7 사용자일 경우에는 \n    화면 상단에 있는 팝업 차단 알림줄을 클릭하여 팝업을 허용해 주시기 바랍니다. \n\n※ MSN,야후,구글 팝업 차단 툴바가 설치된 경우 팝업허용을 해주시기 바랍니다.");
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
	<div class="page page-scroll">
		<!-- 스크롤 페이지 경우 .page-scroll -->
		<div class="container">
			<!-- 공지 -->
			<section class="system">
				<p class="notice">진행 중입니다.</p>
			</section>
			<!-- //공지 -->
		</div>
	</div>

	<!-- 가상주민번호 서비스 팝업을 호출하기 위해서는 다음과 같은 form이 필요합니다. -->
	<form name="form_ipin" method="post">
		<input type="hidden" name="m" value="pubmain">
		<!-- 필수 데이타로, 누락하시면 안됩니다. -->
		<input type="hidden" name="enc_data" value="<%=sEncData%>">
		<!-- 위에서 업체정보를 암호화 한 데이타입니다. -->

		<!-- 업체에서 응답받기 원하는 데이타를 설정하기 위해 사용할 수 있으며, 인증결과 응답시 해당 값을 그대로 송신합니다. 해당 파라미터는 추가하실 수 없습니다. -->
		<input type="hidden" name="param_r1" value=""> <input
			type="hidden" name="param_r2" value=""> <input type="hidden"
			name="param_r3" value="">
		<!-- <a href="javascript:fnPopup();"><img src="http://image.creditbank.co.kr/static/img/vno/new_img/bt_17.gif" width=218 height=40 border=0></a>  -->
	</form>

	<!-- 가상주민번호 서비스 팝업 페이지에서 사용자가 인증을 받으면 암호화된 사용자 정보는 해당 팝업창으로 받게됩니다. 따라서 부모 페이지로 이동하기 위해서는 다음과 같은 form이 필요합니다. -->
	<form name="vnoform" method="post">
		<input type="hidden" name="enc_data">
		<!-- 인증받은 사용자 정보 암호화 데이타입니다. -->

		<!-- 업체에서 응답받기 원하는 데이타를 설정하기 위해 사용할 수 있으며, 인증결과 응답시 해당 값을 그대로 송신합니다. 해당 파라미터는 추가하실 수 없습니다. -->
		<input type="hidden" name="param_r1" value=""> <input
			type="hidden" name="param_r2" value=""> <input type="hidden"
			name="param_r3" value="">
	</form>
	<!--  -->
</body>
</html>