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
<title>LGU+ Connected Car</title>
<link rel="stylesheet" href="../../css/common.css">
<link rel="stylesheet" href="../../css/style.css">
<link rel="stylesheet" href="../../css/extend.css">

<script type="text/javascript" src="../../js/jquery-1.10.1.js"></script>
<script type="text/javascript" src="../../js/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../js/custom.js"></script>
<script type="text/javascript">
	$(function() {
		$("#scroll").enscroll({
			showOnHover : false,
			verticalTrackClass : "track3",
			verticalHandleClass : "handle3"
		});
	});
</script>
</head>
<body>
	<!--  style="background:url("../images/temp/TW_pnd_06.jpg") no-repeat 0 0;" -->
	<div class="page">
		<div class="container">
			<!-- 쿠폰요금제 -->
			<section class="coupon-rate"> <!-- 요금제변경 -->
			<div class="fare-change">
				<!-- div class="inbox-scroll" id="scroll" 스크롤 영역 -->
				<div class="title">
					<c:if test="${param.planYn eq 'Y'}">
						<p class="note">요금제 변경을 위해 본인 인증을 해주세요.</p>
					</c:if>
					<c:if test="${param.planYn ne 'Y'}">
						<h1>본인 인증하기</h1>
					</c:if>
				</div>

				<ul class="certification">
					<li>
						<h2>휴대폰 본인인증</h2>
						<p class="kmc">
							본인 명의의 휴대폰으로 <br> 인증 번호를 전송해 드립니다.
						</p> <a href="authKmcMain.do" class="btn-line">인증하기</a>
					</li>
					<!-- <li>
						<h2>아이핀 본인인증</h2>
						<p class="ipin">
							본인 인증 기관을 통해 <br> 발급받은 아이핀(I-PIN)으로 <br> 인증할 수 있습니다.
						</p> <a href="authIpinMain.do" class="btn-line">인증하기</a>
					</li> -->
				</ul>
				<!-- /div -->
			</div>
			<!-- //요금제 변경 --> <a href="#" class="top">TOP</a><!-- 2017.09.29 컨텐츠 영역 내로 이동 -->
			</section>
			<!-- //쿠폰요금제 -->
		</div>
	</div>
</body>
</html>
