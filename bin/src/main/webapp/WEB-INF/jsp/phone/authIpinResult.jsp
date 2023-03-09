<%@ page contentType="text/html;charset=euc-kr"%>
<%@ page language="java" import="Kisinfo.Check.IPIN2Client"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	/********************************************************************************************************************************************
		NICE������ Copyright(c) KOREA INFOMATION SERVICE INC. ALL RIGHTS RESERVED
		
		���񽺸� : �����ֹι�ȣ���� (IPIN) ����
		�������� : �����ֹι�ȣ���� (IPIN) ��� ������
	*********************************************************************************************************************************************/

	String sSiteCode = (String) request.getAttribute("siteCode"); // IPIN ���� ����Ʈ �ڵ�(NICE���������� �߱��� ����Ʈ�ڵ�)
	String sSitePw = (String) request.getAttribute("sitePw"); // IPIN ���� ����Ʈ �н�����	(NICE���������� �߱��� ����Ʈ�н�����)

	// ����� ���� �� CP ��û��ȣ�� ��ȣȭ�� ����Ÿ�Դϴ�.
	String sResponseData = requestReplace(request.getParameter("enc_data"), "encodeData");

	// CP ��û��ȣ : ipin_main.jsp ���� ���� ó���� ����Ÿ
	String sCPRequest = (String) session.getAttribute("CPREQUEST");

	// ��ü ����
	IPIN2Client pClient = new IPIN2Client();

	/*
	�� ��ȣȭ �Լ� ����  ��������������������������������������������������������������������������������������������������������������������
		Method �����(iRtn)�� ����, ���μ��� ���࿩�θ� �ľ��մϴ�.
		
		fnResponse �Լ��� ��� ����Ÿ�� ��ȣȭ �ϴ� �Լ��̸�,
		"sCPRequest"���� �߰��� �����ø� CP��û��ȣ ��ġ���ε� Ȯ���ϴ� �Լ��Դϴ�. (���ǿ� ���� sCPRequest ����Ÿ�� ����)
		
		���� �ͻ翡�� ���ϴ� �Լ��� �̿��Ͻñ� �ٶ��ϴ�.
	������������������������������������������������������������������������������������������������������������������������������������������
	*/
	int iRtn = pClient.fnResponse(sSiteCode, sSitePw, sResponseData);
	//int iRtn = pClient.fnResponse(sSiteCode, sSitePw, sResponseData, sCPRequest);

	String sRtnMsg = ""; // ó����� �޼���
	String sVNumber = pClient.getVNumber(); // �����ֹι�ȣ (13�ڸ��̸�, ���� �Ǵ� ���� ����)
	String sName = pClient.getName(); // �̸�
	String sDupInfo = pClient.getDupInfo(); // �ߺ����� Ȯ�ΰ� (DI - 64 byte ������)
	String sAgeCode = pClient.getAgeCode(); // ���ɴ� �ڵ� (���� ���̵� ����)
	String sGenderCode = pClient.getGenderCode(); // ���� �ڵ� (���� ���̵� ����)
	String sBirthDate = pClient.getBirthDate(); // ������� (YYYYMMDD)
	String sNationalInfo = pClient.getNationalInfo(); // ��/�ܱ��� ���� (���� ���̵� ����)
	String sCPRequestNum = pClient.getCPRequestNO(); // CP ��û��ȣ
	String sAuthInfo = pClient.getAuthInfo(); // 

	String sCoInfo1 = pClient.getCoInfo1(); // �������� Ȯ�ΰ� (CI - 88 byte ������)
	String sCIUpdate = pClient.getCIUpdate(); // CI ��������

	String TP = "IPIN";
	String CI = "";
	String DI = "";
	String PN = "";
	String NM = "";
	String BD = "";

	// Method ������� ���� ó������
	if (iRtn == 1) {

		/*
		������ ���� ����� ������ ������ �� �ֽ��ϴ�.
		����ڿ��� �����ִ� ������, "�̸�" ����Ÿ�� ���� �����մϴ�.
		
		����� ������ �ٸ� ���������� �̿��Ͻ� ��쿡��
		������ ���Ͽ� ��ȣȭ ����Ÿ(sResponseData)�� ����Ͽ� ��ȣȭ �� �̿��Ͻǰ��� �����մϴ�. (���� �������� ���� ó�����)
		
		����, ��ȣȭ�� ������ ����ؾ� �ϴ� ��쿣 ����Ÿ�� ������� �ʵ��� ������ �ּ���. (����ó�� ����)
		form �±��� hidden ó���� ����Ÿ ���� ������ �����Ƿ� �������� �ʽ��ϴ�.
		*/

		System.out.println("�����ֹι�ȣ : " + sVNumber + "<BR>");
		System.out.println("�̸� : " + sName + "<BR>");
		System.out.println("�ߺ����� Ȯ�ΰ� (DI) : " + sDupInfo + "<BR>");
		System.out.println("���ɴ� �ڵ� : " + sAgeCode + "<BR>");
		System.out.println("���� �ڵ� : " + sGenderCode + "<BR>");
		System.out.println("������� : " + sBirthDate + "<BR>");
		System.out.println("��/�ܱ��� ���� : " + sNationalInfo + "<BR>");
		System.out.println("CP ��û��ȣ : " + sCPRequestNum + "<BR>");
		System.out.println("����Ȯ�� ���� : " + sAuthInfo + "<BR>");
		System.out.println("�������� Ȯ�ΰ� (CI) : " + sCoInfo1 + "<BR>");
		System.out.println("CI �������� : " + sCIUpdate + "<BR>");
		System.out.println("***** ��ȣȭ �� ������ �������� Ȯ���� �ֽñ� �ٶ��ϴ�.<BR><BR><BR><BR>");

		CI = sCoInfo1;
		DI = sDupInfo;
		PN = "";
		NM = sName;
		BD = sBirthDate;

		//sRtnMsg = "���� ó���Ǿ����ϴ�.";
	} else if (iRtn == -1 || iRtn == -4) {
		//sRtnMsg = "iRtn ��, ���� ȯ�������� ��Ȯ�� Ȯ���Ͽ� �ֽñ� �ٶ��ϴ�.";
		PN = "iRtn ��, ���� ȯ�������� ��Ȯ�� Ȯ���Ͽ� �ֽñ� �ٶ��ϴ�.";
	} else if (iRtn == -6) {
		//sRtnMsg = "���� �ѱ� charset ������ euc-kr �� ó���ϰ� ������, euc-kr �� ���ؼ� ����� �ֽñ� �ٶ��ϴ�.<BR>" + "�ѱ� charset ������ ��Ȯ�ϴٸ� ..<BR><B>iRtn ��, ���� ȯ�������� ��Ȯ�� Ȯ���Ͽ� ���Ϸ� ��û�� �ֽñ� �ٶ��ϴ�.</B>";
		PN = "���� �ѱ� charset ������ euc-kr �� ó���ϰ� ������, euc-kr �� ���ؼ� ����� �ֽñ� �ٶ��ϴ�.<BR>"
				+ "�ѱ� charset ������ ��Ȯ�ϴٸ� ..<BR><B>iRtn ��, ���� ȯ�������� ��Ȯ�� Ȯ���Ͽ� ���Ϸ� ��û�� �ֽñ� �ٶ��ϴ�.</B>";
	} else if (iRtn == -9) {
		//sRtnMsg = "�Է°� ���� : fnResponse �Լ� ó����, �ʿ��� �Ķ���Ͱ��� ������ ��Ȯ�ϰ� �Է��� �ֽñ� �ٶ��ϴ�.";
		PN = "�Է°� ���� : fnResponse �Լ� ó����, �ʿ��� �Ķ���Ͱ��� ������ ��Ȯ�ϰ� �Է��� �ֽñ� �ٶ��ϴ�.";
	} else if (iRtn == -12) {
		//sRtnMsg = "CP ��й�ȣ ����ġ : IPIN ���� ����Ʈ �н����带 Ȯ���� �ֽñ� �ٶ��ϴ�.";
		PN = "CP ��й�ȣ ����ġ : IPIN ���� ����Ʈ �н����带 Ȯ���� �ֽñ� �ٶ��ϴ�.";
	} else if (iRtn == -13) {
		//sRtnMsg = "CP ��û��ȣ ����ġ : ���ǿ� ���� sCPRequest ����Ÿ�� Ȯ���� �ֽñ� �ٶ��ϴ�.";
		PN = "CP ��û��ȣ ����ġ : ���ǿ� ���� sCPRequest ����Ÿ�� Ȯ���� �ֽñ� �ٶ��ϴ�.";
	} else {
		//sRtnMsg = "iRtn �� Ȯ�� ��, NICE������ ���� ����ڿ��� ������ �ּ���.";
		PN = "iRtn �� Ȯ�� ��, NICE������ ���� ����ڿ��� ������ �ּ���.";
	}
%>
<%!public String requestReplace(String paramValue, String gubun) {
		String result = "";

		if (paramValue != null) {

			paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

			paramValue = paramValue.replaceAll("\\*", "");
			paramValue = paramValue.replaceAll("\\?", "");
			paramValue = paramValue.replaceAll("\\[", "");
			paramValue = paramValue.replaceAll("\\{", "");
			paramValue = paramValue.replaceAll("\\(", "");
			paramValue = paramValue.replaceAll("\\)", "");
			paramValue = paramValue.replaceAll("\\^", "");
			paramValue = paramValue.replaceAll("\\$", "");
			paramValue = paramValue.replaceAll("'", "");
			paramValue = paramValue.replaceAll("@", "");
			paramValue = paramValue.replaceAll("%", "");
			paramValue = paramValue.replaceAll(";", "");
			paramValue = paramValue.replaceAll(":", "");
			paramValue = paramValue.replaceAll("-", "");
			paramValue = paramValue.replaceAll("#", "");
			paramValue = paramValue.replaceAll("--", "");
			paramValue = paramValue.replaceAll("-", "");
			paramValue = paramValue.replaceAll(",", "");

			if (gubun != "encodeData") {
				paramValue = paramValue.replaceAll("\\+", "");
				paramValue = paramValue.replaceAll("/", "");
				paramValue = paramValue.replaceAll("=", "");
			}

			result = paramValue;

		}
		return result;
	}%>

<html>
<head>
<!--  -->
<meta name="robots" content="noindex">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densityDpi=device-dpi" />
<!--  -->
<title>NICE������ �����ֹι�ȣ ����</title>
<!-- <style type="text/css">
BODY {
	COLOR: #7f7f7f;
	FONT-FAMILY: "Dotum", "DotumChe", "Arial";
	BACKGROUND-COLOR: #ffffff;
}
</style> -->

<link rel="stylesheet" href="../../css/phone/common.css">
<link rel="stylesheet" href="../../css/phone/style.css">

<script type="text/javascript" src="../../js/phone/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="../../js/phone/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../js/phone/custom.js"></script>

<script type="text/javascript">
	function result() {
		<%-- window.opener.validation("<%=TP%>", "<%=CI%>", "<%=DI%>", "<%=PN%>", "<%=NM%>", "<%=BD%>"); --%>
		callNative("<%=TP%>", "<%=CI%>", "<%=DI%>", "<%=PN%>", "<%=NM%>", "<%=BD%>");
		window.close();
		//close();
		//popupFinish();
	}
</script>
</head>
<body onload="result();">
	<!-- <body> -->
	<div class="page page-scroll">
		<!-- ��ũ�� ������ ��� .page-scroll -->
		<div class="container">
			<!-- ���� -->
			<section class="system">
				<p class="notice">���� ���Դϴ�.</p>
			</section>
			<!-- //���� -->
		</div>
	</div>
</body>
</html>