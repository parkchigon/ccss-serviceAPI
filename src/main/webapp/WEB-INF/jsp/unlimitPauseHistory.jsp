<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	var startDate = "";
	var endDate = "";
	var reasonCode = "";
	var reasonName = "";

	var reasons = [ "경제적 사정", "기기분실(모뎀PC,와이파이 공유기)", "서비스 불만", "부모요청", "인터넷 미사용",
			"장기부재", "휴대폰 파손" ];
	var reasonCodes = [ "EX", "12", "13", "14", "15", "ZA", "UP" ];

	$(function() {
		$("#t1, #t2").enscroll({
			showOnHover : false,
			verticalTrackClass : "track3",
			verticalHandleClass : "handle3"
		});
	});

	function requestPause() {
		android.requestPause("selectedReason", reasons, 0);
	}
	
	function requestUnpause() {
		android.requestUnpause();
	}
	
	function requestRsvUnpause() {
		android.requestRsvUnpause(); 
		//unpauseRsvService(tp, ci, di, pn, nm, bd); 
	}

	function selectedReason(reason, index, sdate, edate) {
		//
		if ("" == reason) {
			//android.showToast("일시정지사유를 선택해주세요.");
			console.log("일시정지사유를 선택해주세요.");
			android.showErrorInputAlert();
			return;
		}

		//
		if ("" == sdate) {
			//android.showToast("시작일을 선택해주세요.");
			console.log("시작일을 선택해주세요.");
			android.showErrorInputAlert();
			return;
		}

		//
		if ("" == edate) {
			//android.showToast("종료일을 선택해주세요.");
			console.log("종료일을 선택해주세요.");
			android.showErrorInputAlert();
			return;
		}

		reasonName = "" + reason;
		reasonCode = "" + reasonCodes[index];
		startDate = "" + sdate;
		endDate = "" + edate;

		authChoice();
	}

	function authChoice() {
		window.open("authChoice.do?modelNmGbn=<c:out value='${modelNmGbn}'/>", null, null);
		/* window.open("authKmcMain.do", null, null); */
	}

	function validation(tp, ci, di, pn, nm, bd) {
		if ("${isPause}" =='사용중') {
			stopService(tp, ci, di, pn, nm, bd);
		} else if("${isPause}" =='정지'){
			unpauseService(tp, ci, di, pn, nm, bd);
		} else if("${isPause}" =='정지예약'){
			unpauseRsvService(tp, ci, di, pn, nm, bd);
		}
	}

	function stopService(tp, ci, di, pn, nm, bd) {
		var userAgent = android.getUserAgent();

		var sendData = JSON.stringify({
			tp : tp,
			ci : ci,
			di : di,
			pn : pn,
			nm : nm,
			bd : bd,
			entrSttsValdStrtDt : startDate,
			entrSttsValdEndDt : endDate,
			selRsnCd : reasonCode,
			selRsnNm : reasonName
		});

		android.showProgressDialog();

		$.ajax({
			type : "POST",
			url : "stopService",
			headers : {
				"User-Agent" : userAgent
			},
			data : sendData,
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			async : true,
			success : function(data) {
				if ("0000" == data.resultCode) {
					android.showAlertApplyPause();
				} else if ("1000" == data.resultCode) {
					android.showAlert("서비스 일시정지 확인", data.resultMsg, "확인");
				} else if ("0001" == data.resultCode) {
					android.showAlert("서비스 일시정지 확인", data.resultMsg, "확인");
				} else if ("0002" == data.resultCode) {
					android.showAlert("서비스 일시정지 확인", data.resultMsg, "확인");
				} else {
					android.showAlert("변경 실패 알림",
							"입력된 정보가 정확하지 않습니다.\n입력된 내용을 다시 확인해 주세요.", "닫기");
				}
			},
			complete : function(data) {
				android.dismissProgressDialog();
			},
			error : function(xhr, status, error) {
				android.showToast("서버와 통신하는 동안 오류가 발생했습니다.");
			}
		});
	}
	
	function unpauseService(tp, ci, di, pn, nm, bd) {
		var userAgent = android.getUserAgent();

		var sendData = JSON.stringify({
			tp : tp,
			ci : ci,
			di : di,
			pn : pn,
			nm : nm,
			bd : bd
		});

		android.showProgressDialog();

		$.ajax({
			type : "POST",
			url : "unpauseService",
			headers : {
				"User-Agent" : userAgent
			},
			data : sendData,
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			async : true,
			success : function(data) {
				if ("0000" == data.resultCode) {
					android.showAlertUnPause();
				} else {
					android.showAlert("변경 실패 알림",
							"입력된 정보가 정확하지 않습니다.\n입력된 내용을 다시 확인해 주세요.", "닫기");
				}
			},
			complete : function(data) {
				android.dismissProgressDialog();
			},
			error : function(xhr, status, error) {
				android.showToast("서버와 통신하는 동안 오류가 발생했습니다.");
			}
		});
	}
	
	function unpauseRsvService(tp, ci, di, pn, nm, bd) {
		var userAgent = android.getUserAgent();

		var sendData = JSON.stringify({
			tp : tp,
			ci : ci,
			di : di,
			pn : pn,
			nm : nm,
			bd : bd
		});

		android.showProgressDialog();

		$.ajax({
			type : "POST",
			url : "unpauseRsvService",
			headers : {
				"User-Agent" : userAgent
			},
			data : sendData,
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			async : true,
			success : function(data) {
				if ("0000" == data.resultCode) {
					android.showAlertApplyPause();
				} else {
					android.showAlert("변경 실패 알림",
							"입력된 정보가 정확하지 않습니다.\n입력된 내용을 다시 확인해 주세요.", "닫기");
				}
			},
			complete : function(data) {
				android.dismissProgressDialog();
			},
			error : function(xhr, status, error) {
				android.showToast("서버와 통신하는 동안 오류가 발생했습니다.");
			}
		});
	}
</script>
</head>
<body>
	<!-- style="background:url("../images/temp/TW_pnd_08.jpg") no-repeat 0 0;" -->
	<div class="page page-scroll">
		<!-- 스크롤 페이지 경우 .page-scroll -->
		<div class="container">
			<!-- 정액요금제 -->
			<section class="flat-rate"> <!-- 서비스변경내역 -->
			<div class="service-change">

				<!-- tab menu -->
				<nav class="tab-menu-etc"> <a href="#t2">서비스 일시정지 내역</a> </nav>

				<div class="scroll-container">
					<!-- 서비스 변경내역 -->
					<div class="inbox-scroll" id="t1">
						<!-- 스크롤영역 -->
						<div class="wrapper">
							<!-- 본문 -->
							서비스 변경내역
						</div>
					</div>
					<!-- //서비스 변경내역 -->

					<!-- 서비스 일시정지 내역 -->
					<div class="inbox-scroll on" id="t2">
						<!-- 스크롤영역 -->
						<div class="wrapper">
							<!-- 본문 -->
							<c:choose>
								<c:when test="${not empty list}">
									<c:forEach var="vo" items="${list}">
										<c:if test="${vo.entrSttsNm ne '정지예약'}">
											<div class="t-grid_head">
											</div>
										</c:if>
										<c:if test="${vo.entrSttsNm eq '정지중'}">
											<div class="t-grid_head">
												<p class="note">서비스 일시정지 상태입니다.</p>
											</div>
										</c:if>
										<c:if test="${vo.entrSttsNm eq '정지예약'}">
											<div class="t-grid_head">
												<p class="note">서비스 일시정지 예약상태입니다.</p>
											</div>
										</c:if>
									</c:forEach>
								</c:when>
							</c:choose>
							
							<div class="t-grid">
							<table class="detail">
								<caption>상세내역조회</caption>
								<colgroup>
									<col style="width: 262px">
									<col style="width: 150px">
									<col style="width: 130px">
									<col style="width: 100px">
								</colgroup>
								<thead>
									<tr>
										<th>요금제 명</th>
										<th>사유</th>
										<th>기간</th>
										<th>정지 일 수</th>
									</tr>
								</thead>
								<tbody>
								<c:choose>
									<c:when test="${not empty list}">
										<c:forEach var="vo" items="${list}">
											<c:if test="${vo.entrSttsNm eq '개통'}">
												<tr>
													<td class="empty" colspan="4">서비스 일시정지 내역이 없습니다.</td>
												</tr>
											</c:if>
											<c:if test="${vo.entrSttsNm ne '개통'}">
												<tr>
													<td>${vo.prodNm}</td>
													<td>${vo.entrSttsChngRsnDtlNm}</td>
													<td>${vo.entrSttsValdStrtDt} ~ ${vo.entrSttsValdEndDt}</td>
													<td>${vo.prodNo}</td>
												</tr>
											</c:if>
										</c:forEach>
									</c:when>
								</c:choose>
								</tbody>
							</table>
						</div>
							

							<c:choose>
								<%-- <c:when test="${not empty list}">
									<c:forEach var="vo" items="${list}">
										<c:if test="${vo.entrSttsNm eq '개통'}">
											<div class="bt">
												<a href="javascript:;" class="btn-line"
													onclick="requestPause();">일시정지 신청하기</a>
											</div>
										</c:if>
										<c:if test="${vo.entrSttsNm eq '정지예약'}">
											<div class="bt">
												<a href="javascript:;" class="btn-line"
													onclick="requestUnpause();">서비스 일시정지 예약 취소하기</a>
											</div>
										</c:if>
										<c:if test="${vo.entrSttsNm eq '정지중'}">
											<div class="bt">
												<a href="javascript:;" class="btn-line"
													onclick="requestUnpause();">서비스 일시정지 해제하기</a>
											</div>
										</c:if>
									</c:forEach>
								</c:when> --%>
								<c:when test="${isPause eq '정지'}">
									<div class="bt">
										<a href="javascript:;" class="btn-line"
											onclick="requestUnpause();">서비스 일시정지 해제</a>
									</div>
								</c:when>
								<c:when test="${isPause eq '정지예약'}">
									<div class="bt">
										<a href="javascript:;" class="btn-line"
											onclick="requestRsvUnpause();">서비스 일시정지 예약 취소하기</a>
									</div>
								</c:when>
								<c:when test="${isPause eq '사용중'}">
									<div class="bt">
										<a href="javascript:;" class="btn-line"
											onclick="requestPause();">일시정지 신청하기</a>
									</div>
								</c:when>
							</c:choose>
						</div>
					</div>
					<!-- //서비스 일시정지 내역 -->
				</div>
			</div>
			<!-- 서비스변경내역 --> <a href="#" class="top">TOP</a><!-- 2017.09.29 컨텐츠 영역 내로 이동 -->
			</section>
			<!-- //정액요금제 -->
		</div>
	</div>

</body>
</html>
