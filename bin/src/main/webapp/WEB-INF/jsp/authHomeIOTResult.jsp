<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<c:choose>	
	<c:when test="${modelNmGbn eq 'EL1'}">	
		<meta name="viewport"
		content="width=device-width, initial-scale=0.666, maximum-scale=1.0, minimum-scale=0.666, user-scalable=no, target-densityDpi=device-dpi" />
	</c:when>	
	<c:otherwise>	
		<meta name="viewport"
		content="width=device-width, initial-scale=1, minimum-scale=1">	
	</c:otherwise>
</c:choose>	


<%-- <meta http-equiv="refresh" content="1; url='${returnUrl}'/> --%>

<title>LGU+ Connected Car</title>
<!-- <link rel="stylesheet" href="../../css/common.css">
<link rel="stylesheet" href="../../css/style.css"> -->
<link rel="stylesheet" href="../../css/extend.css">

<script type="text/javascript" src="../../js/jquery-1.10.1.js"></script>
<script type="text/javascript" src="../../js/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../js/custom.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var returnUrl = "${returnUrl}";
		console.log("returnUrl",returnUrl);
		
		redirect.click();
		
		//autoClick();
		
		//location.href = returnUrl;
		//location.replace("www.naver.com");
		
		//document.location.href=  returnUrl ;
		//$("#target").submit();
		//location.href='${returnUrl}" ;
		//location.replace="www.naver.com" ;
		//submitForm(returnUrl);
		
		/* function submitForm(returnUrl){
			console.log("TEST");
			console.log("TEST3");
			$("#target").submit();
			//var frm=document.target;
			console.log("actionUrl",returnUrl);
			document.target.action=returnUrl;
			document.target.submit();
		} */
		
		
		
		
	});
	</script>
</head>
<body>
	<!-- style="background:url("../images/temp/TW_pnd_05.jpg") no-repeat 0 0;" -->
	<div class="page page-scroll full-popup">
		<!-- 페이지 형태 팝업 .full-popup (공통) -->
		<div class="container">${returnUrl}</div>
	</div>

	<%-- <form id="target" action='${returnUrl}" method="post"> --%>
	<form id="target" method="post">
		<input type="hidden" name="test" />
	</form>

	<a id="redirect" href="${returnUrl}"></a>
</body>
</html>
