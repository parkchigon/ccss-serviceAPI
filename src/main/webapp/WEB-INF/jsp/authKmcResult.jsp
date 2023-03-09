
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
	String TP = "KMC";
	String CI = "";
	String DI = "";
	String PN = "";
	String NM = "";
	String BD = "";

	try {
		String rec_cert = request.getParameter("rec_cert").trim();
		String certNum = request.getParameter("certNum").trim();

		//01. 암호화 모듈 (jar) Loading
		com.icert.comm.secu.IcertSecuManager seed = new com.icert.comm.secu.IcertSecuManager();

		//02. 1차 복호화
		//수신된 certNum를 이용하여 복호화
		rec_cert = seed.getDec(rec_cert, certNum);

		//03. 1차 파싱
		int inf1 = rec_cert.indexOf("/", 0);
		int inf2 = rec_cert.indexOf("/", inf1 + 1);

		String encPara = rec_cert.substring(0, inf1); //암호화된 통합 파라미터
		String encMsg1 = rec_cert.substring(inf1 + 1, inf2); //암호화된 통합 파라미터의 Hash값

		//04. 위변조 검증
		String encMsg2 = seed.getMsg(encPara);

		String msgChk = "N";
		if (encMsg2.equals(encMsg1)) {
			msgChk = "Y";
		}

		//05. 2차 복호화
		rec_cert = seed.getDec(encPara, certNum);

		//06. 2차 파싱
		int info1 = rec_cert.indexOf("/", 0);
		int info2 = rec_cert.indexOf("/", info1 + 1);
		int info3 = rec_cert.indexOf("/", info2 + 1);
		int info4 = rec_cert.indexOf("/", info3 + 1);
		int info5 = rec_cert.indexOf("/", info4 + 1);
		int info6 = rec_cert.indexOf("/", info5 + 1);
		int info7 = rec_cert.indexOf("/", info6 + 1);
		int info8 = rec_cert.indexOf("/", info7 + 1);
		int info9 = rec_cert.indexOf("/", info8 + 1);
		int info10 = rec_cert.indexOf("/", info9 + 1);
		int info11 = rec_cert.indexOf("/", info10 + 1);
		int info12 = rec_cert.indexOf("/", info11 + 1);
		int info13 = rec_cert.indexOf("/", info12 + 1);
		int info14 = rec_cert.indexOf("/", info13 + 1);
		int info15 = rec_cert.indexOf("/", info14 + 1);
		int info16 = rec_cert.indexOf("/", info15 + 1);
		int info17 = rec_cert.indexOf("/", info16 + 1);
		int info18 = rec_cert.indexOf("/", info17 + 1);

		String string01 = rec_cert.substring(0, info1);// 요청번호
		String string02 = rec_cert.substring(info1 + 1, info2);// 요청일시
		String string03 = rec_cert.substring(info2 + 1, info3);// 연계정보(CI)
		String string04 = rec_cert.substring(info3 + 1, info4);// 휴대폰번호
		String string05 = rec_cert.substring(info4 + 1, info5);// 이동통신사
		String string06 = rec_cert.substring(info5 + 1, info6);// 생년월일
		String string07 = rec_cert.substring(info6 + 1, info7);// 성별
		String string08 = rec_cert.substring(info7 + 1, info8);// 내/외국인
		String string09 = rec_cert.substring(info8 + 1, info9);// 성명
		String string10 = rec_cert.substring(info9 + 1, info10);// 결과값
		String string11 = rec_cert.substring(info10 + 1, info11);// 인증방법
		String string12 = rec_cert.substring(info11 + 1, info12);// IP주소
		String string13 = rec_cert.substring(info12 + 1, info13);// 미성년자 성명
		String string14 = rec_cert.substring(info13 + 1, info14);// 미성년자 생년월일
		String string15 = rec_cert.substring(info14 + 1, info15);// 미성년자 성별
		String string16 = rec_cert.substring(info15 + 1, info16);// 미성년자 내외국인
		String string17 = rec_cert.substring(info16 + 1, info17);// 추가정보
		String string18 = rec_cert.substring(info17 + 1, info18);// 중복가입확인정보(DI)

		//
		if (msgChk.equals("N")) {
			PN = "";
		} else {
			PN = string04;
		}

		//07. CI, DI 복호화
		CI = seed.getDec(string03, certNum);
		DI = seed.getDec(string18, certNum);

		NM = string09;
		BD = string06;

		System.out.println("01.[" + string01 + "]");
		System.out.println("02.[" + string02 + "]");
		System.out.println("03.[" + string03 + "]");
		System.out.println("04.[" + string04 + "]");
		System.out.println("05.[" + string05 + "]");
		System.out.println("06.[" + string06 + "]");
		System.out.println("07.[" + string07 + "]");
		System.out.println("08.[" + string08 + "]");
		System.out.println("09.[" + string09 + "]");
		System.out.println("10.[" + string10 + "]");
		System.out.println("11.[" + string11 + "]");
		System.out.println("12.[" + string12 + "]");
		System.out.println("13.[" + string13 + "]");
		System.out.println("14.[" + string14 + "]");
		System.out.println("15.[" + string15 + "]");
		System.out.println("16.[" + string16 + "]");
		System.out.println("17.[" + string17 + "]");
		System.out.println("18.[" + string18 + "]");
		System.out.println("CI.[" + CI + "]");
		System.out.println("DI.[" + DI + "]");

		// 현재 서버 시각 구하기
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREAN);
		String current = simpleDateFormat.format(new Date());

		Date to = simpleDateFormat.parse(current);
		Date from = simpleDateFormat.parse(string02);
		long diff = to.getTime() - from.getTime();

		if (diff < -30 * 60 * 1000 || 30 * 60 * 100 < diff) {
			PN = "";
		}

		// 사용자IP 구하기
		String ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		if (ip != null) {
			if (ip.indexOf(",") != -1)
				ip = ip.substring(0, ip.indexOf(","));
		}
		if (ip == null || ip.length() == 0) {
			ip = request.getRemoteAddr();
		}
		if (!ip.equals(string12)) {
			TP = "KMC";
			CI = "";
			DI = "";
			PN = "";
			NM = "";
			BD = "";
		}
	} catch (Exception e) {
		TP = "KMC";
		CI = "";
		DI = "";
		PN = "";
		NM = "";
		BD = "";
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<title>본인확인</title>

<link rel="stylesheet" href="../../css/common.css">
<link rel="stylesheet" href="../../css/style.css">
<link rel="stylesheet" href="../../css/extend.css">

<script type="text/javascript" src="../../js/jquery-1.10.1.js"></script>
<script type="text/javascript" src="../../js/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../js/custom.js"></script>

<script type="text/javascript">
	function result() {
		window.opener.validation("<%=TP%>", "<%=CI%>", "<%=DI%>", "<%=PN%>", "<%=NM%>", "<%=BD%>");
		window.close();
	}
</script>
</head>
<body onload="result();">
	<!-- <body> -->
	<div class="page">
		<!-- 스크롤 페이지 경우 .page-scroll -->
		<div class="container">
			<!-- 공지 -->
			<section class="system">
			<p class="notice">진행 중입니다.</p>
			</section>
			<!-- //공지 -->
		</div>
	</div>
</body>
</html>