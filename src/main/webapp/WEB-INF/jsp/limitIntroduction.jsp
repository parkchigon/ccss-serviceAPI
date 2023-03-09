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

	function exit() {
		android.exit();
	}

	function showRegister() {
		android.showRegister();
	}
</script>
</head>
<body>

	<!-- style="background:url("../images/temp/TW_pnd_05.jpg") no-repeat 0 0;" -->
	<div class="page page-scroll full-popup">
		<!-- 페이지 형태 팝업 .full-popup (공통) -->
		<div class="container">
			<!-- 내용(공통) -->
			<section class="content">
			<div class="inner-cont">
				<div class="inbox-scroll" id="scroll">
					<!-- 스크롤영역 -->
					<div class="wrapper">
						<!-- 본문 -->
						<h1>신규 가입 및 개통 안내</h1>
						<p class="join_title">
							<c:choose><c:when test="${modelNmGbn=='EL1'}">U+</c:when><c:otherwise>아이나비</c:otherwise></c:choose> 커넥티드 서비스 가입을 환영합니다.</p>
						<p class="join_eclipse">가입 가능 시간 : 오전 9시~오후 6시</p>
						<p class="join_default">※ 주말 및 공휴일은 신청만 가능하며 개통은 평일 오전에 진행됩니다.</p>
						<p class="join_eclipse">가입 전 준비사항</p>
						<p class="join_hyphen">본인인증 : 아이핀 또는 문자 메시지</p>
						<p class="join_hyphen">신분증 확인 : 주민등록증 또는 운전면허증</p>
						<p class="join_eclipse">가입 및 개통 절차</p>
						<p class="join_space">본인인증 &gt; 가입 신청서 작성 &gt; 개통 확인 메시지 발송
							&gt; 자동차 재시동 &gt; LTE 최초 접속 &gt; 서비스 약관 동의 &gt; "커넥티드서비스"사용 시작</p>
						<p class="join_default" style="margin-top: 12px;">※ 유의사항</p>
						<p class="join_hyphen">가입신청서 작성 완료까지 5~10분 정도가 걸립니다. 중복가입되지
							않도록 유의하세요.</p>
						<p class="join_hyphen">가입에 문제가 있는 경우 LG유플러스 고객센터 상담사를 통해
							문의해주세요.</p>
						<p class="join_space">1644-7000 (평일 오전 9시~오후 6시)</p>
						</p>
					</div>
				</div>
			</div>
			<a href="#" class="top">TOP</a><!-- 2017.09.29 컨텐츠 영역 내로 이동 --> </section>
			<!-- //내용(공통) -->

			<!-- 하단(공통) -->
			<footer class="footer"> <a href="javascript:;"
				class="btn-line dual" onclick="exit();">닫기</a> <a
				href="javascript:;" class="btn-line dual" onclick="showRegister();">서비스
				가입하기</a> </footer>
			<!-- //하단(공통) -->
		</div>
	</div>

</body>
</html>
