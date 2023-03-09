<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<title>LGU+ Connected Car</title>
<link rel="stylesheet" href="../../css/common.css">
<link rel="stylesheet" href="../../css/style.css">
<link rel="stylesheet" href="../../css/extend.css">

<script type="text/javascript" src="../../js/jquery-1.10.1.js"></script>
<script type="text/javascript" src="../../js/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../js/custom.js"></script>
<script type="text/javascript">
	$(function() {
		$("#t1, #t2").enscroll({
			showOnHover : false,
			verticalTrackClass : "track3",
			verticalHandleClass : "handle3"
		});
	});

	function apply() {
		authChoice();
	}

	function authChoice() {
		window.open("authChoice.do?modelNmGbn=<c:out value='${modelNmGbn}'/>", null, null);
		/* window.open("authKmcMain.do", null, null); */
	}

	function validation(tp, ci, di, pn, nm, bd) {
		unpauseService(tp, ci, di, pn, nm, bd);
	}

	
	function unpauseService(tp, ci, di, pn, nm, bd) {
		var userAgent = android.getUserAgent();

		var sendData = JSON.stringify({
			tp : tp,
			ci : ci,
			di : di,
			pn : pn,
			nm : nm,
			bd : bd
		});

		android.showProgressDialog();

		$.ajax({
			type : "POST",
			url : "unpauseService",
			headers : {
				"User-Agent" : userAgent
			},
			data : sendData,
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			async : true,
			success : function(data) {
					location.href = "/mypage/pnd/unpauseResult.do";
			},
			error : function(xhr, status, error) {
				location.href = "/mypage/pnd/unlimitPauseHistory.do";
			}
		});
	}
	
	
	
</script>
</head>
<body onload="apply()">
</body>
</html>
