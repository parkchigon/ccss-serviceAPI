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

	function closeExitImminent() {
		var val = $("#check").is(":checked");

		android.closeExitImminent(val);
	}

	function goApplyPlan() {
		android.goApplyPlan();
	}
</script>
</head>
<body>
	<!-- style="background:url("../images/temp/TW_pnd_05.jpg") no-repeat 0 0;" -->
	<div class="page page-scroll full-popup">
		<!-- 페이지 형태 팝업 .full-popup (공통) -->
		<div class="container">
			<!-- 내용(공통) -->
			${limitExitImminentHtml}
			<%-- <section class="content">
			<div class="inner-cont">
				<div class="inbox-scroll" id="scroll">
					<!-- 스크롤영역 -->
					<div class="wrapper">
						<!-- 본문 -->
						<h1>'커넥티드카 데이터 10GB' 무료 이용 종료 안내</h1>
						<div class="expire-notice">
							<p class="note">
								고객님께 제공 중인 '커넥티드카 데이터 10GB' 1년 무료 이용 기간이 ${svcEndDttm2}로
								종료됩니다.<br />커넥티드카 서비스를 계속 이용하시려면 서비스 신청을 해 주세요.<br />
								(음성인식,인포테인먼트,LTE DATA)
							</p>
							<p class="price">서비스명 : 커넥티드카 데이터 10GB</p>
							<div class="t-grid">
								<table>
									<caption>월 정액 상품 상세</caption>
									<colgroup>
										<col style="width: 182px">
										<col style="width: 174px">
										<col style="width: 282px">
										<col style="width: 278px">
									</colgroup>
									<thead>
										<tr>
											<th>월 정액</th>
											<th>데이터 기본 제공량</th>
											<th>초과 사용시</th>
											<th>초과 사용요금</th>
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

							<p class="info">커넥티드카 서비스 가입자는 월 12,000원에 서비스를 이용할 수 있습니다. (지니뮤직 마음껏 듣기 포함)</p>
							<p class="info">변경하지 않으면 무료 이용기간 종료 후 자동 해지 됩니다 (종료 예정일 :
								${svcEndDttm2})</p>
						</div>
					</div>
					<!-- //본문 -->
				</div>
				<!-- //스크롤영역 -->
			</div>
			<a href="#" class="top">TOP</a>
			</section> --%>
			<!-- //내용(공통) -->

			<!-- 하단(공통) -->
			<footer class="footer"> <span class="chk-mgr"> <input
				type="checkbox" id="check"> <label for="check">다시 보지
					않기</label>
			</span>
			<div class="bt">
				<a href="javascript:;" class="btn-line"
					onclick="closeExitImminent();">닫기</a> <a href="javascript:;"
					class="btn-line" onclick="goApplyPlan();">신청하기</a>
			</div>
			</footer>
			<!-- //하단(공통) -->
		</div>

	</div>
</body>
</html>
