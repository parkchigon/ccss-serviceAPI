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

	});

	function back() {
		history.back();
	}
</script>
</head>
<body>

	<div class="page page-scroll">
		<!-- 스크롤 페이지 경우 .page-scroll -->
		<div class="pageHead fixed">
			<a href="javascript:;" class="headPre" onclick="back();">이전</a>
			<div class="headTitle">요금 납부 정보</div>
			<!-- <a href="#" class="headMenu">메뉴</a> -->
		</div>
		<div class="container">
			<section>
				<div class="section_inner">
					<div class="d-grid">
						<table>
							<caption>요금납부정보</caption>
							<colgroup>
								<col style="width: 30%">
								<col style="width: auto">
							</colgroup>
							<tbody>
								<tr>
									<th>요금제명</th>
									<td>${svcNm}</td>
								</tr>
								<tr>
									<th>고객명</th>
									<td>${cardDepoNm}</td>
								</tr>
								<tr>
									<th>납부 방법</th>
									<td>
										<p class="bt">${cardNm}</p> <a href="unlimitPayMethod.do"
										class="btn">납부방법 변경</a>
									</td>
								</tr>
								<tr>
									<th>청구서 유형</th>
									<td class="bt">
										<p class="bt">${bltxtKdNm}</p> <a href="unlimitBillType.do"
										class="btn">청구서 유형 변경</a> <a href="unlimitPayDetail.do"
										class="btn">청구서 확인</a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</section>
		</div>
	</div>

</body>
</html>
