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
		$("#t1, #t2").enscroll({
			showOnHover : false,
			verticalTrackClass : "track3",
			verticalHandleClass : "handle3"
		});
	});

	function goPayInfo() {
		location.href = "unlimitPayInfo.do";
	}
</script>
</head>
<body>
	<!-- style="background:url("../images/temp/TW_pnd_03.jpg") no-repeat 0 0;" -->
	<div class="page page-scroll">
		<!-- 스크롤 페이지 경우 .page-scroll -->
		<div class="container">
			<!-- 정액요금제 -->
			${unlimitPayDetailHtml}
			<%-- <section class="flat-rate"> <!-- 청구서조회 -->
			<div class="bill-inquire">

				<!-- tab menu -->
				<nav class="tab-menu">
				<ul>
					<li>
						<a href="#t1" onclick="goPayInfo();">요금 납부 정보</a>
					</li>
					<li class="on">
						<a href="#t2">상세 내역 조회</a>
					</li>
				</ul>
				</nav>

				<div class="scroll-container">
					<!-- 요금납부정보 -->
					<div class="inbox-scroll" id="t1">
						<!-- 스크롤영역 -->
						<div class="wrapper">
							<!-- 본문 -->
						</div>
					</div>
					<!-- //요금납부정보 -->

					<!-- 상세내역조회 -->
					<div class="inbox-scroll on" id="t2">
						<div class="wrapper">
							<!-- 본문 -->
							<div class="t-grid">
								<table class="detail">
									<caption>상세내역조회</caption>
									<colgroup>
										<col style="width: 212px">
										<col style="width: 154px">
										<col style="width: 279px">
									</colgroup>
									<thead>
										<tr>
											<th>청구내역</th>
											<th>금액</th>
											<th>상세안내</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>이번 달 청구 요금(A)</td>
											<td>${billAmt}원</td>
											<td></td>
										</tr>
										<tr>
											<td>미납요금(B)</td>
											<td>${upaidChrg}원</td>
											<td></td>
										</tr>
										<tr>
											<td>총 납부 금액(A+B)</td>
											<td>${totPymScdlAmt}원</td>
											<td></td>
										</tr>
										<tr>
											<td>월정액</td>
											<td>12,000원</td>
											<td>
												월정액은 사용일자에 따라 계산<br>됩니다.(서비스 일시 정지 중일때는<br>월정액
												4,000원이 청구됩니다.)
											</td>
										</tr>
										<tr>
											<td>요금할인</td>
											<td>${dscntAmt}원</td>
											<td>${dscntDtl}</td>
										</tr>
										<!-- <tr>
											<td>할인내용</td>
											<td>${dscntDtl}</td>
											<td></td>
										</tr> -->
										<tr>
											<td>부가세</td>
											<td>${txamt}원</td>
											<td></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- //상세내역조회 -->
				</div>
			</div>
			<!-- //청구서조회 --> <a href="#" class="top">TOP</a><!-- 2017.09.29 컨텐츠 영역 내로 이동 -->
			</section> --%>
			<!-- //정액요금제 -->
		</div>
	</div>

</body>
</html>
