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

	function goPayDetail() {
		location.href = "unlimitPayDetail.do";
	}
</script>
</head>
<body>
	<!-- style="background:url("../images/temp/TW_pnd_03.jpg") no-repeat 0 0;" -->
	<div class="page page-scroll">
		<!-- 스크롤 페이지 경우 .page-scroll -->
		<div class="container">
			<!-- 정액요금제 -->
			<section class="flat-rate"> <!-- 청구서조회 -->
			<div class="bill-inquire">

				<!-- tab menu -->
				<nav class="tab-menu">
				<ul>
					<li class="on"><a href="#t1">요금 납부 정보</a></li>
					<li><a href="#t2" onclick="goPayDetail();">상세 내역 조회</a></li>
				</ul>
				</nav>

				<div class="scroll-container">
					<!-- 요금납부정보 -->
					<div class="inbox-scroll on" id="t1">
						<!-- 스크롤영역 -->
						<div class="wrapper">
							<!-- 본문 -->
							<div class="t-grid">
								<table class="fee-info">
									<caption>요금납부정보</caption>
									<colgroup>
										<col style="width: 193px">
										<col style="width: auto">
										<col style="width: 192px">
									</colgroup>
									<tbody>
										<tr class="prd-name">
											<th>요금제명</th>
											<td colspan="2">${svcNm}</td>
										</tr>
										<tr>
											<th>예금(카드)주명</th>
											<td colspan="2">${cardDepoNm}</td>
										</tr>
										<tr>
											<th>납부방법</th>
											<td class="nt">${cardNm}</td>
											<td class="bt"><a href="unlimitPayMethod.do"
												class="btn-line">납부방법 변경</a></td>
										</tr>
										<tr>
											<th>결제(출금)일</th>
											<td colspan="2">${duedDt}</td>
										</tr>
										<tr class="bill-type">
											<th>청구서유형</th>
											<td class="nt">${bltxtKdNm}</td>
											<td class="bt"><a href="unlimitBillType.do"
												class="btn-line">청구유형 변경</a> <a href="unlimitPayDetail.do"
												class="btn-line">청구서 확인</a></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- //요금납부정보 -->

					<!-- 상세내역조회 -->
					<div class="inbox-scroll" id="t2">
						<div class="wrapper">
							<!-- 본문 -->
						</div>
					</div>
					<!-- //상세내역조회 -->
				</div>
			</div>
			<!-- //청구서조회 --> <a href="#" class="top">TOP</a><!-- 2017.09.29 컨텐츠 영역 내로 이동 -->
			</section>
			<!-- //정액요금제 -->
		</div>
	</div>

</body>
</html>
