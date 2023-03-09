<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1">
	<title>LGU+ Connected Car</title>
<link rel="stylesheet" href="../../../css/phone/common.css">
<link rel="stylesheet" href="../../../css/phone/style.css">


<script type="text/javascript" src="../../../js/jquery-1.10.1.js"></script>
<script type="text/javascript" src="../../../js/phone/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../../js/phone/custom.js"></script>
<script type="text/javascript">
	$(function() {

	});
	
	function back() {
		history.back();
	}
</script>
</head>
<body>

<div class="page page-scroll"><!-- 스크롤 페이지 경우 .page-scroll -->
    <div class="pageHead fixed">
		<a href="javascript:;" class="headPre" onclick="back();">이전</a>
    	<div class="headTitle">${terms.termsTitle}</div>
    </div>
    <div class="container">
		    ${terms.termsCont}
	</div>
</div>

</body>
</html>
