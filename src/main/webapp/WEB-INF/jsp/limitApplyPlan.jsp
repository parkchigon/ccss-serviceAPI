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
	var payResvYn = "<c:out value='${payResvYn}'/>"; //요금제예약신청

	$(function() {
		$("#scroll, #terms-scroll").enscroll({
			showOnHover : false,
			verticalTrackClass : "track3",
			verticalHandleClass : "handle3"
		});

		if ("Y" == payResvYn) {
			android.showAlreadyAlert();
		}
	});

	function cancelApplayPlan() {
		history.back();
	}

	function showAlertApplyPlan() {
		if ("Y" == payResvYn) {
			android.showAlreadyAlert();
			return;
		}

		var val = $("#check").is(":checked");

		if (!val) {
			android.showToast("사용 연장 및 유료 이용에 동의가 필요합니다.");
			return;
		}

		android.showAlertApplyPlan();
	}
</script>
</head>
<body>
	<!-- style="background:url("../images/temp/TW_pnd_02.jpg") no-repeat 0 0;" -->
	<div class="page page-scroll">
		<!-- 스크롤 페이지 경우 .page-scroll -->
		<div class="container">
			<!-- 쿠폰요금제 -->
			${limitApplyPlanHtml}
			<%-- <section class="coupon-rate"> <!-- 요금제변경 -->
			<div class="fare-change">
				<div class="inbox-scroll" id="scroll">
					<!-- 스크롤영역 inbox-scroll -->
					<div class="wrapper">
						<!-- 본문 -->
						<div class="article part1">
							<p class="note">
								현재 커넥티드카 데이터 10GB 요금제를 사용 중입니다.<br> 무료 이용기간이 끝나는
								${svcEndDttm1} 전에 사용 연장 신청을 해 주세요.
							</p>

							<h2 class="tit-h2">커넥티드카 데이터 10GB 요금제 상품 설명</h2>
							<p class="price">월 정액 12,000원 (VAT 포함)</p>

							<div class="t-grid">
								<table>
									<caption>상품상세</caption>
									<colgroup>
										<col style="width: 128px">
										<col style="width: 130px">
										<col style="width: 193px">
										<col style="width: 194px">
									</colgroup>
									<thead>
										<tr>
											<th>요금(원)</th>
											<th>데이터</th>
											<th>초과 사용시</th>
											<th>초과 요율</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td rowspan="3">12,000</td>
											<td rowspan="3">10GB</td>
											<td>0~880MB 미만</td>
											<td>0.011원/0.5KB</td>
										</tr>
										<tr>
											<td>880MB~3GB 미만</td>
											<td>19,800원 정액과금</td>
										</tr>
										<tr>
											<td>3GB 이상~</td>
											<td>0.0033원/0.5KB</td>
										</tr>
									</tbody>
								</table>
							</div>

							<p class="info" style="margin-top: 10px;">
								커넥티드카 서비스 가입자는 월 12,000원에 서비스를 이용할 수 있습니다.<br />(지니뮤직 마음껏 듣기
								포함)
							</p>
							<p class="info">변경하지 않으면 무료 이용 기간 종료 후 자동 해지됨 (종료 예정일 :
								${svcEndDttm2})</p>
						</div>

						<div class="article part2">
							<span class="chk-mgr">
								<input type="checkbox" id="check">
								<label for="check">사용 연장 및 유료 이용에 동의합니다.</label>
							</span>
						</div>
						<!-- //본문 -->
					</div>

					<!-- 버튼 -->
					<div class="bt" style="margin-top: 30px">
						<a href="javascript:;" class="btn-line"
							onclick="cancelApplayPlan();">취소</a>
						<a href="javascript:;" class="btn-line"
							onclick="showAlertApplyPlan();">신청하기</a>
					</div>
					<!-- //버튼 -->
				</div>
				<!-- //스크롤영역 -->
			</div>
			<!-- //요금제 변경 --> <a href="#" class="top">TOP</a><!-- 2017.09.29 컨텐츠 영역 내로 이동 -->
			</section> --%>
			<!-- //쿠폰요금제 -->
		</div>
	</div>

</body>
</html>
