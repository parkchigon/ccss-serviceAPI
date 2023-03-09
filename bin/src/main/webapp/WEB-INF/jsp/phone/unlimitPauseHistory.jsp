<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		bindUserAgent();
	});

	function authChoice() {
		window.open("authChoice.do", null, null);
	}

	function validation(tp, ci, di, pn, nm, bd) {
		if ("${isPause}" == true) {
			unpauseService(tp, ci, di, pn, nm, bd);
		} else {
			stopService(tp, ci, di, pn, nm, bd);
		}
	}
	function unpauseService(tp, ci, di, pn, nm, bd) {
		var userAgent = userAgent;

		var sendData = JSON.stringify({
			tp : tp,
			ci : ci,
			di : di,
			pn : pn,
			nm : nm,
			bd : bd
		});

		showProgressDialog();

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
					showAlert2("서비스 일시정지 해제 확인", "서비스 일시정지해제를 신청했습니다.");
				} else {
					showAlert2("변경 실패 알림",
							"입력된 정보가 정확하지 않습니다.<br>입력된 내용을 다시 확인해 주세요.");
				}
			},
			complete : function(data) {
				dismissProgressDialog();
			},
			error : function(xhr, status, error) {
				showToast("서버와 통신하는 동안 오류가 발생했습니다.");
			}
		});
	}

	function showProgressDialog() {
		$(".dim").show();
		$("#progress").css("display", "block");
	}

	function dismissProgressDialog() {
		$(".dim").hide();
		$("#progress").css("display", "none");
	}

	function showAlert2(title, message) {
		$(".dim").show();
		$("#alert").css("display", "block");
		$("#title").css("display", "block");
		$("#title").text(title);
		$("#message").html(message);
		$("#confirm").click(function() {
			$(".dim").hide();
			$("#alert").css("display", "none");
			$("#title").css("display", "none");
		});
	}

	function showAlert(message) {
		$(".dim").show();
		$("#alert").css("display", "block");
		$("#title").css("display", "none");
		//
		$("#message").html(message);
		$("#confirm").click(function() {
			$(".dim").hide();
			$("#alert").css("display", "none");
			$("#title").css("display", "none");
		});
	}

	function showToast(message) {
		if (isMobile.iOS()) {
			var message = {
				"action" : "call",
				"function" : "showToast",
				"message" : message,
			}
			try {
				webkit.messageHandlers.callbackHandler.postMessage(message);
			} catch (err) {
				alert(err);
			}
		} else if (isMobile.Android()) {
			android.showToast(message);
		}
	}

	var userAgent = "";
	function bindUserAgent() {
		if (isMobile.iOS()) {
			var message = {
				"action" : "bind",
				"name" : "userAgent"
			}
			try {
				webkit.messageHandlers.callbackHandler.postMessage(message);
			} catch (err) {
				alert(err);
			}
		} else if (isMobile.Android()) {
			userAgent = android.getUserAgent();
		}
	}

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
			<div class="headTitle">서비스 일시 정지 내역</div>
			<!-- <a href="#" class="headMenu">메뉴</a> -->
		</div>
		<div class="container">
			<section>
				<div class="data_info_view">
					<dl>
						<dt>총 ${fn:length(list)}건의 일시 정지 내역이 있습니다.</dt>
						<dd>
							<c:if test="${not empty list}">
								<c:forEach var="vo" items="${list}">
									<div class="t-grid mt10 mb10">
										<table>
											<caption>일시정지내역</caption>
											<colgroup>
												<col style="width: 35%">
												<col style="width: auto">
											</colgroup>
											<tbody>
												<tr>
													<th>서비스 명</th>
													<td>${vo.prodNm}</td>
												</tr>
												<tr>
													<th>정지 사유</th>
													<td>${vo.susRsnNm}</td>
												</tr>
												<tr>
													<th>정지 기간</th>
													<td>${vo.term}</td>
												</tr>
												<tr>
													<th>정지일 수</th>
													<td>${vo.susDays}</td>
												</tr>
											</tbody>
										</table>
									</div>
								</c:forEach>
							</c:if>
							<c:if test="${empty list}">
								<div>
									<table>
										<tbody>
											<tr>
												<td>서비스 일시정지 내역이 없습니다.</td>
											</tr>
										</tbody>
									</table>
								</div>
							</c:if>
						</dd>
					</dl>
				</div>
			</section>

			<!-- <section>
				<div class="data_info_view">
					<dl>
						<dd>
							<div class="noData">일시 정지 내역이 없습니다.</div>
						</dd>
					</dl>
				</div>
			</section> -->
			
 			<c:choose>
				<c:when test="${isPause}" >
					 <div class="footerBtnArea fixed">
						<a href="javascript:;" class="footer_btn btnAll color1"
						onclick="authChoice();">서비스 일시 정지 해제 신청</a>
					</div>
					</c:when>
				 <c:otherwise >
					<div class="footerBtnArea fixed">
						<a href="unlimitRequestPause.do" class="footer_btn btnAll color1">서비스
						일시 정지 신청</a>
					</div>
				</c:otherwise>
			</c:choose>	 


			  <%-- <div class="footerBtnArea fixed">
				<c:if test="${isPause ne true}">
					<a href="unlimitRequestPause.do" class="footer_btn btnAll color1">서비스
						일시 정지 신청</a>
				</c:if>
				<c:if test="${isPause ne false}">
					<a href="javascript:;" class="footer_btn btnAll color1"
						onclick="authChoice();">서비스 일시 정지 해제 신청</a>
				</c:if>
			</div>   --%>

			<div class="dim"></div>
			<!-- Progress -->
			<div id="progress" class="progress" style="display: none;">
				<img class="gif" src="../../images/phone/loading_progress.gif" />
			</div>
			<!-- Alert -->
			<div id="alert" class="popup2" style="display: none;">
				<div class="popup_inner">
					<p id="title" class="title"></p>
					<!-- 타이틀 없을경우 삭제 -->
					<div id="message" class="info"></div>
				</div>
				<div class="popup_btnArea">
					<!-- 버튼 2개 -->
					<!-- <a id="cancel" href="javascript:;" class="btn2 color1 duo">닫기</a> -->
					<!-- <a id="ok" href="javascript:;" class="btn2 color2 duo">업데이트</a> -->
					<!-- 버튼 1개 -->
					<a id="confirm" href="javascript:;" class="btn2">확인</a>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
