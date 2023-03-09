<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% response.setStatus(417); %> <!-- IOS BACK버튼 작동하지 않아 에러 처리 -->
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densityDpi=device-dpi" />
<title>LGU+ Connected Car</title>
<link rel="stylesheet" href="../../css/phone/common.css">
<link rel="stylesheet" href="../../css/phone/style.css">

<script type="text/javascript" src="../../js/phone/jquery-1.10.1.js"></script>
<script type="text/javascript" src="../../js/phone/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../js/phone/custom.js"></script>

<head>
	<script language="javascript">
	function navigationBack()
	{
		if( document.referrer ) {
			history.back();
		}
		else {
			//location.href = "/datagift/main.do";
			closed();
		}
	}
	</script>
</head>
<body>
<div class="dimmed"></div>
		<div class="page page-scroll"><!-- 스크롤 페이지 경우 .page-scroll -->
		   		<!-- 스크롤 페이지 경우 .page-scroll -->
		<div class="pageHead">
			<!-- <a href="closewebview://" class="headPre">이전</a> -->
			<a href="javascript:;" class="headPre" onclick="navigationBack();">이전</a>
			<div class="headTitle">에러</div>
		</div>
		<div class="container">
			<!-- 공지 -->
			<section class="system">
			<p class="notice">입력하신 페이지 주소가 정확한지 다시 한번 확인해보시기 바랍니다.</p>
			</section>
			<!-- //공지 -->
		</div>
	</div>
<br>
</body>
</html>