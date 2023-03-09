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
	var startDate = "";
	var endDate = "";
	var reasonCode = "";
	var reasonName = "";

	$(function() {
		bindUserAgent();

		// 사유
		$("#reasons li").click(function() {
			var array = [ "", "EX", "12", "13", "14", "15", "ZA", "UP" ];
			var index = $("li").index(this);

			reasonCode = array[index];
			reasonName = $(this).text();

			$(".dropdown-container").hide();
			$(".dim").hide();
		});
	});

	function requestPause() {
		if ("" == reasonCode || "" == reasonName) {
			//showToast("일시정지사유를 선택해주세요.");
			// console.log("일시정지사유를 선택해주세요.");
			showErrorInputAlert();
			return;
		}

		//
		var sdate = "" + $("#sdate").val();
		if (sdate.length != 8) {
			//showToast("시작일을 선택해주세요.");
			// console.log("시작일을 선택해주세요.");
			showErrorInputAlert();
			return;
		}

		//
		var edate = $("#edate").val();
		if (edate.length != 8) {
			//showToast("종료일을 선택해주세요.");
			// console.log("종료일을 선택해주세요.");
			showErrorInputAlert();
			return;
		}

		startDate = "" + sdate;
		endDate = "" + edate;

		authChoice();
	}

	function authChoice() {
		window.open("authChoice.do", null, null);
	}

	function validation(tp, ci, di, pn, nm, bd) {
		stopService(tp, ci, di, pn, nm, bd);
	}

	function stopService(tp, ci, di, pn, nm, bd) {
		var userAgent = userAgent;

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

		showProgressDialog();

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
					showAlert2("서비스 일시정지 확인", "서비스 일시 정지를 신청했습니다.");
				} else if ("1000" == data.resultCode) {
					showAlert2("서비스 일시정지 신청", data.resultMsg);
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

	function showErrorInputAlert() {
		/* showAlert("입력하신 청구 정보 내용에서 오류가 발생했습니다. 내용 확인 후 정확하게 다시 입력해 주세요."); */
		showAlert2("정보입력 오류", "입력된 정보가 잘못되었습니다<br>확인 후 다시 입력해 주세요");
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
			<div class="headTitle">서비스 일시 정지 신청</div>
			<!-- <a href="#" class="headMenu">메뉴</a> -->
		</div>
		<div class="container">
			<section>
				<div class="data_info_view">
					<dl>
						<dt>아래 정보를 모두 입력해주세요.</dt>
						<dd>
							<div class="info_view_inner2">
								<dl>
									<dt>일시정지사유</dt>
									<dd>
										<a href="#select1" class="select-box">해당 사유를 선택해주세요</a>
									</dd>
								</dl>
								<dl>
									<dt>시작일</dt>
									<dd>
										<input type="number" max="99999999" maxlength="8"
											oninput="maxLengthCheck(this);"
											placeholder="일시정지 시작일을 입력해주세요" id="sdate" value=""
											pattern="\d*">
										<!-- https://jqueryui.com/datepicker/ -->
									</dd>
								</dl>
								<dl>
									<dt>종료일</dt>
									<dd>
										<input type="number" max="99999999" maxlength="8"
											oninput="maxLengthCheck(this);"
											placeholder="일시정지 종료일을 입력해주세요" id="edate" value=""
											pattern="\d*">
									</dd>
									<dd class="info">‘장기부재’는 군입대, 해외장기체류, 형 집행 중, 기기 분실 등 해당
										사유가 있는 경우에한해 선택할 수 있습니다.</dd>
								</dl>
							</div>
						</dd>
					</dl>
				</div>
			</section>

			<div class="footerBtnArea fixed">
				<a href="javascript:;" class="footer_btn color2" onclick="back();">취소하기</a>
				<a href="javascript:;" class="footer_btn color1"
					onclick="requestPause();">등록하기</a>
			</div>

			<div class="dim"></div>
			<div class="dropdown-container" id="select1">
				<!-- style="background:#282d38 url("../images/temp/TW_pnd_12.jpg") no-repeat 0 0;" -->
				<div class="dropdown-content" id="scroll1">
					<ul id="reasons">
						<li><a href="javascript:;">선택하세요</a></li>
						<li><a href="javascript:;">기기 분실(모델/PC/와이파이 공유기 등)</a></li>
						<li><a href="javascript:;">서비스 불만</a></li>
						<li><a href="javascript:;">부모 요청</a></li>
						<li><a href="javascript:;">인터넷 미사용</a></li>
						<li><a href="javascript:;">장기부재</a></li>
						<li><a href="javascript:;">휴대폰 파손</a></li>
					</ul>
				</div>
			</div>
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
