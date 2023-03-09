
<%
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Pragma", "no-store");
	response.setHeader("Cache-Control", "no-cache");
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="java.util.regex.*"%>
<%@ page import="java.text.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	String today = simpleDateFormat.format(calendar.getTime());

	java.util.Random random = new Random();

	int length = 6;

	String string = "";

	for (int i = 0; i < length; i++) {
		string += random.nextInt(10);
	}

	//tr_cert 데이터 선언 ---------------------------------------------------------------
	String cpId = (String) request.getAttribute("cpId"); // 회원사ID
	String urlCode = (String) request.getAttribute("urlCode"); // URL코드
	String certNum = today + string; // 요청번호
	String date = today; // 요청일시
	String certMet = "M"; // 본인인증방법
	String extendVar = "0000000000000000"; // 확장변수
	String tr_url = (String) request.getAttribute("returnUrl");// 본인인증서비스 결과수신 POPUP URL
	//tr_cert 데이터 선언 ---------------------------------------------------------------

	//01. 한국모바일인증(주) 암호화 모듈 선언
	com.icert.comm.secu.IcertSecuManager seed = new com.icert.comm.secu.IcertSecuManager();

	//02. 1차 암호화 (tr_cert 데이터변수 조합 후 암호화)
	String tmp_tr_cert = cpId + "/" /**/
			+ urlCode + "/" /**/
			+ certNum + "/" /**/
			+ date + "/" /**/
			+ certMet + "/"/**/
			+ "" + "/"/*생년월일*/
			+ "" + "/"/*성별*/
			+ "" + "/"/*성명*/
			+ "" + "/"/*전화번호*/
			+ "" + "/"/*통신사*/
			+ "" + "/"/*내/외국인*/
			+ "" + "/"/*추가정보*/
			+ extendVar;
	String enc_tr_cert = seed.getEnc(tmp_tr_cert, "");

	//03. 1차 암호화 데이터에 대한 위변조 검증값 생성 (HMAC)
	String hmacMsg = seed.getMsg(enc_tr_cert);

	//04. 2차 암호화 (1차 암호화 데이터, HMAC 데이터, extendVar 조합 후 암호화)
	String tr_cert = seed.getEnc(enc_tr_cert + "/" + hmacMsg + "/" + extendVar, "");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--  -->
<meta name="robots" content="noindex">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densityDpi=device-dpi" />
<!--  -->
<title>본인인증</title>

<link rel="stylesheet" href="../../css/phone/common.css">
<link rel="stylesheet" href="../../css/phone/style.css">

<script type="text/javascript" src="../../js/phone/jquery-1.10.1.js"></script>
<script type="text/javascript" src="../../js/phone/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../js/phone/custom.js"></script>


<script type="text/javascript">
	/* window.name = "KMCISWindow";
	var KMCIS_window; */

	function open() {

		var UserAgent = navigator.userAgent;
		/* 모바일 접근 체크*/

		if (UserAgent
				.match(/iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson/i) != null
				|| UserAgent.match(/LG|SAMSUNG|Samsung/) != null) {
			// 모바일일 경우 (변동사항 있을경우 추가 필요)
			document.reqKMCISForm.target = "";
		} else {
			/* // 모바일이 아닐 경우
			KMCIS_window = window
					.open(
							"",
							"KMCISWindow",
							"width=425, height=550, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=435, top=250");

			if (KMCIS_window == null) {
				alert(" ※ 윈도우 XP SP2 또는 인터넷 익스플로러 7 사용자일 경우에는 \n 화면 상단에 있는 팝업 차단 알림줄을 클릭하여 팝업을 허용해 주시기 바랍니다. \n\n※ MSN,야후,구글 팝업 차단 툴바가 설치된 경우 팝업허용을 해주시기 바랍니다.");
			}
			document.reqKMCISForm.target = "KMCISWindow"; */
		}
		document.reqKMCISForm.action = "https://www.kmcert.com/kmcis/web/kmcisReq.jsp";
		document.reqKMCISForm.submit();
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
	<form name="reqKMCISForm" method="post">
		<input type="hidden" name="tr_cert" value="<%=tr_cert%>" /> <input
			type="hidden" name="tr_url" value="<%=tr_url%>" />
	</form>
	<!--  -->
</body>
</html>