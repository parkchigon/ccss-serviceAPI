<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densityDpi=device-dpi" />
<title>LGU+ Connected Car</title>
<link rel="stylesheet" href="../../css/phone/common.css">
<link rel="stylesheet" href="../../css/phone/style.css">

<script type="text/javascript" src="../../js/phone/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="../../js/phone/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../js/phone/custom.js"></script>
<script type="text/javascript">
	$(function() {
		console.log("window(" + $(window).width() + "," + $(window).height()
				+ ")");
		console.log("document(" + $(document).width() + ","
				+ $(document).height() + ")");
	});

	function back() {
		closed();
	}
</script>
</head>
<body>

	<div class="page page-scroll">
		<!-- 스크롤 페이지 경우 .page-scroll -->
		<div class="pageHead fixed">
			<a href="javascript:;" class="headPre" onclick="back();">이전</a>
			<div class="headTitle">내 정보</div>
			<!-- <a href="#" class="headMenu">메뉴</a> -->
		</div>
		<div class="container">
			<section>
				<div class="pay_list">
					<ul>
						<li><a href="unlimitSubsInfo.do">가입정보</a></li>
						<li><a href="unlimitPayInfo.do">요금 납부 정보</a></li>
						<li><a href="unlimitPayDetail.do">청구 내역 조회</a></li>
						<!-- <li><a href="#">서비스 변경 내역</a></li> -->
						<!-- <li><a href="unlimitPauseHistory.do">서비스 일시 정지 내역</a></li> -->
						<!-- <li><a href="unlimitTerms.do">이용약관</a></li> -->
					</ul>
				</div>
			</section>
		</div>
	</div>

</body>
</html>