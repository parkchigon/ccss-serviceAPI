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
			<div class="headTitle">상세 내역 조회</div>
			<!-- <a href="#" class="headMenu">메뉴</a> -->
		</div>
		<div class="container">
			<section>
				<div class="section_inner">
					<div class="d-grid">
						<table>
							<caption>상세내역조회</caption>
							<colgroup>
								<col style="width: 70%">
								<col style="width: auto">
							</colgroup>
							<tbody>
								<tr>
									<th>이번 달 청구 요금(A)</th>
									<td class="pay">${billAmt}원</td>
								</tr>
								<tr>
									<th>미납 요금(B)</th>
									<td class="pay">${upaidChrg}원</td>
								</tr>
								<tr>
									<th>총 납부 금액(A+B)</th>
									<td class="pay">${totPymScdlAmt}원</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</section>

			<section>
				<div class="data_info_view">
					<dl>
						<dt><이번달 요금 상세 내역></dt>
						<dd>
							<div class="info_view_inner">
								<dl>
									<dt>월 정액</dt>
									<dd class="pay">12,000원</dd>
									<dd class="inner_info">월 정액은 사용일자에 따라 계산 됩니다. (서비스 일시정지 중일
										때는, 월 정액 월 4,000원이 청구 됩니다.)</dd>
								</dl>
								<dl>
									<dt class="txt-brown">요금 할인</dt>
									<dd class="pay txt-brown">${dscntAmt}원</dd>
								</dl>
								<dl>
									<dt>할인 내용</dt>
									<dd class="pay">${dscntDtl}</dd>
								</dl>
								<dl>
									<dt>부가세</dt>
									<dd class="pay">${txamt}원</dd>
								</dl>
							</div>
						</dd>
					</dl>
				</div>
			</section>
		</div>
	</div>

</body>
</html>
