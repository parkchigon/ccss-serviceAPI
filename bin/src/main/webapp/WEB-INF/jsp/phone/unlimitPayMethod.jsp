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

<!--monthPickers  -->
<link rel="stylesheet" href="../../css/phone/jquery.monthpicker-0.1.css" type="text/css"/>

<script type="text/javascript" src="../../js/phone/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="../../js/phone/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../js/phone/custom.js"></script>
<script type="text/javascript" src="../../js/phone/jquery.monthpicker-0.1.js"></script>
	<script>
		$(function() {	
			$("#yyyymm").monthPicker({
				
			});
		});  
	</script>  
<script type="text/javascript">
	var pcategory = "<c:out value='${pymMthdCd}'/>"; //납부방법구분

	var ptype = "I"; //납입유형
	var cname = ""; //카드사명
	var foreigner = "N"; //외국여부
	var cnumber = ""; //카드번호
	var oname = ""; //카드소유주
	var rcrno = ""; //사업자번호
	var rrno = ""; //주민번호
	var yyyy = ""; //카드 유효기간
	var mm = ""; //카드 유효기간
	var tmp = "";

	$(function() {
		bindUserAgent();
		// 개인
		$("#rr").click(function() {
			if (!$("#rr").hasClass("sel")) {
				//개인
				$("#rr").addClass("sel");
				$("#cr").removeClass("sel");
				$("#rrno").css("display", "");
				$("#crno").css("display", "none");
				//개인 : I, 법인 : G
				ptype = "I";
			}
		});
		// 사업자
		$("#cr").click(function() {
			if (!$("#cr").hasClass("sel")) {
				//사업자
				$("#rr").removeClass("sel");
				$("#cr").addClass("sel");
				$("#rrno").css("display", "none");
				$("#crno").css("display", "");
				//개인 : I, 법인 : G
				ptype = "G";
			}
		});

		// 카드
		$("#cards li").click(function() {
			var array = [ "", "01", "02", "03", "04", "05", "07", "08", "26", "71", "72" ];
			var index = $("#cards li").index(this);
			//alert("index"+index);
			cname = array[index];

			$(".dropdown-container").hide();
			$(".dim").hide();
		});

		// 내/외국인
		$("#inouts li").click(function() {
			var array = [ "", "N", "Y" ];
			var index = $("#inouts li").index(this);

			foreigner = array[index];

			$(".dropdown-container").hide();
			$(".dim").hide();
		});
	
	});

	function maxLengthCheck(object) {
		if (object.value.length > object.maxLength) {
			object.value = object.value.slice(0, object.maxLength);
		}
	}

	function authChoice() {
		window.open("authChoice.do", null, null);
	}

	function validation(tp, ci, di, pn, nm, bd) {
		changeMethod();
	}

	function checkChangeMethod() {
		
		var dateValueChange = $("#yyyymm").val().replace("년","").replace("월","");
		//console.log(dateValueChange);
		
		//개인 : I, 법인 : G
		if ("" == ptype) {
			//showToast("납부유형을 선택해주세요.");
			// console.log("납부유형을 선택해주세요.");
			showErrorInputAlert();
			return;
		}

		//"국민카드", "하나카드", "삼성카드"
		//"02", "16", "04"
		if ("" == cname) {
			//showToast("카드사를 선택해주세요.");
			//console.log("카드사를 선택해주세요.");
			showErrorInputAlert();
			return;
		}

		//0000-0000-0000-0000
		cnumber = $("#cnumber").val();
		if ("" == cnumber) {
			//showToast("카드번호를 입력해주세요.");
			// console.log("카드번호를 선택해주세요.");
			showErrorInputAlert();
			return;
		}

		//이름
		oname = $("#oname").val();
		if ("" == oname) {
			//showToast("이름을 입력해주세요.");
			// console.log("이름을 입력해주세요.");
			showErrorInputAlert();
			return;
		}

		//var tmp = "";
		if ("I" == ptype) {
			//00000000-0000000
			rrno = "" + $("#rrno01").val() + $("#rrno02").val();
			if ("" == rrno) {
				//showToast("주민번호를 입력해주세요.");
				// console.log("주민번호를 입력해주세요.");
				showErrorInputAlert();
				return;
			}
			tmp = rrno;
		} else if ("G" == ptype) {
			//000-00-00000
			crno = "" + $("#crno01").val() + $("#crno02").val()
					+ $("#crno03").val();
			if ("" == crno) {
				//showToast("사업자번호를 입력해주세요.");
				// console.log("사업자번호를 입력해주세요.");
				showErrorInputAlert();
				return;
			}
			tmp = crno;
		} else {
			//showToast("납부유형을 선택해주세요.");
			// console.log("납부유형을 선택해주세요.");
			showErrorInputAlert();
			return;
		}

		//var yyyymm = "" + $("#yyyymm").val();
		if (dateValueChange.length != 6) {
			showErrorInputAlert();
			return;
		}

		//0000
		yyyy = dateValueChange.substring(0, 4);
		if ("" == yyyy) {
			//showToast("유효기간을 선택해주세요.");
			// console.log("유효기간을 선택해주세요.");
			showErrorInputAlert();
			return;
		}

		//00
		mm = dateValueChange.substring(4, 7);
		if ("" == mm) {
			//showToast("유효기간을 선택해주세요.");
			// console.log("유효기간을 선택해주세요.");
			showErrorInputAlert();
			return;
		}

		authChoice();
	}

	// 납부방법변경 
	function changeMethod() {
		var acntOwnrNo = "" + tmp;//주민번호
		var custDvCd = "" + ptype;//개인:I,법인:G
		var persIdind = "" + foreigner;//외국인:Y
		var cdcmpCd = "" + cname;//카드타입
		var cardNo = "" + cnumber;//카드번호
		var cardValdEndYymm = "" + yyyy + mm;//카드유효기간
		var pymCustNm = "" + oname;//이름
		//var userAgent = userAgent;

		var sendData = JSON.stringify({
			acntOwnrNo : acntOwnrNo,
			custDvCd : custDvCd,
			persIdind : persIdind,
			cdcmpCd : cdcmpCd,
			cardNo : cardNo,
			cardValdEndYymm : cardValdEndYymm,
			pymCustNm : pymCustNm
		});

		showProgressDialog();

		$.ajax({
			type : "POST",
			url : "changeMethod",
			headers : {
				"User-Agent" : userAgent
			},
			data : sendData,
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			async : true,
			success : function(data) {
				if ("0000" == data.resultCode) {
					showAlert2("납부방법변경 완료", "납부방법변경이 완료되었습니다.");
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


	function showToast(message) {
		if (isMobile.iOS()) {
			var message = {
				"action" : "call",
				"function" : "showToast",
				"message" : message
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
			<div class="headTitle">납부 방법 변경</div>
			<!-- <a href="#" class="headMenu">메뉴</a> -->
		</div>
		<div class="container">
			<section>
				<div class="data_info_view">
					<dl>
						<dt>가입정보</dt>
						<dd>
							<div class="d-grid">
								<table>
									<caption>가입정보</caption>
									<colgroup>
										<col style="width: 30%">
										<col style="width: auto">
									</colgroup>
									<tbody>
										<tr>
											<th>상품 명</th>
											<td>${svcNm}</td>
										</tr>
										<tr>
											<th>고객 명</th>
											<td>${custNm}</td>
										</tr>
									</tbody>
								</table>
							</div>
						</dd>
					</dl>
				</div>
			</section>

			<section>
				<div class="data_info_view">
					<dl>
						<dt>요금 납부 정보</dt>
						<dd>
							<div class="d-grid">
								<table>
									<caption>요금납부정보</caption>
									<colgroup>
										<col style="width: 30%">
										<col style="width: auto">
									</colgroup>
									<tbody>
										<tr>
											<th>납부 방법</th>
											<td>${pymMthdNm}</td>
										</tr>
										<tr>
											<th>예금(카드)주 명</th>
											<td>${cardDepoNm}</td>
										</tr>
									</tbody>
								</table>
							</div>
						</dd>
					</dl>
				</div>
			</section>

			<section>
				<div class="data_info_view">
					<dl>
						<dt>납부 방법 변경</dt>
						<dd>
							<div class="d-grid">
								<table>
									<caption>납부 방법 변경</caption>
									<colgroup>
										<col style="width: 30%">
										<col style="width: auto">
									</colgroup>
									<tbody>
										<tr>
											<th>납부 방법</th>
											<td>신용카드</td>
										</tr>
										<tr class="input">
											<th>납부자 유형</th>
											<td><a href="javascript:;" class="rect-box sel" id="rr">개인</a>
												<a href="javascript:;" class="rect-box" id="cr">사업자</a></td>
										</tr>
										<tr class="input">
											<th>카드사 명</th>
											<td><a href="#bank" class="select-box" id="cname">선택하세요</a></td>
										</tr>
										<tr class="input">
											<th>카드 번호</th>
											<td><input type="number" max="9999999999999999"
												maxlength="16" oninput="maxLengthCheck(this);"
												placeholder="‘-’ 없이 숫자만 입력" id="cnumber" value=""
												pattern="\d*" ></td>
										</tr>
										<tr class="input">
											<th>카드 명의자</th>
											<td>
												<p class="bt">
													<a href="#name" class="select-box">내국인</a>
												</p> <input type="text" placeholder="성함" id="oname" value="">
											</td>
										</tr>
										<tr class="inputH" id="rrno">
											<td colspan="2" class="id-card">
												<p class="tit">카드 명의자 주민등록 번호</p> <input type="number"
												max="999999" maxlength="6" oninput="maxLengthCheck(this);"
												id="rrno01" value="" pattern="\d*"> <span
												class="dash">-</span> <input type="number"
												style="-webkit-text-security: disc;" max="9999999"
												maxlength="7" oninput="maxLengthCheck(this);" id="rrno02"
												value="" pattern="\d*">
											</td>
										</tr>
										<tr style="display: none;" class="inputH" id="crno">
											<td colspan="2" class="id-card">
												<p class="tit">카드 명의자 사업자 번호</p> <input style="width: 28%"
												type="number" max="999" maxlength="3"
												oninput="maxLengthCheck(this);" id="crno01" value=""
												pattern="\d*"> <span class="dash">-</span> <input
												style="width: 18%" type="number" max="99" maxlength="2"
												oninput="maxLengthCheck(this);" id="crno02" value=""
												pattern="\d*"> <span class="dash">-</span> <input
												style="width: 46%" type="number" max="99999" maxlength="5"
												oninput="maxLengthCheck(this);" id="crno03" value=""
												pattern="\d*">
											</td>
										</tr>
										<tr class="input">
											<th>카드 유효기간</th>
											 <!-- <td><input type="number" max="999999" maxlength="6"
												oninput="maxLengthCheck(this);" placeholder="201709"
												id="yyyymm" value="" pattern="\d*"></td>  -->
											<td><input type="text" placeholder="2017년 9월" id="yyyymm"
														value="" pattern="\d*" onFocus="this.blur()" /></td>
										</tr>
									</tbody>
								</table>
							</div>
						</dd>
					</dl>
				</div>
			</section>
			<div class="footerText">* 변경한 정보는 매월 1일 일괄 반영됩니다.</div>
			<div class="footerBtnArea">
				<a href="javascript:;" class="footer_btn color2" onclick="back();">취소하기</a>
				<a href="javascript:;" class="footer_btn color1"
					onclick="checkChangeMethod();">변경하기</a>
			</div>

			<div class="dim"></div>
			<div class="dropdown-container" id="bank">
				<!-- style="background:#282d38 url("../images/temp/TW_pnd_12.jpg") no-repeat 0 0;" -->
				<div class="dropdown-content" id="scroll1">
					<ul id="cards">
						<li><a href="javascript:;">선택하세요</a></li>
						<li><a href="javascript:;">BC카드</a></li>
						<li><a href="javascript:;">국민카드</a></li>
						<li><a href="javascript:;">KEB하나카드</a></li>
						<li><a href="javascript:;">삼성카드</a></li>
						<li><a href="javascript:;">신한(구LG)카드</a></li>
						<li><a href="javascript:;">현대카드</a></li>
						<li><a href="javascript:;">롯데(구 아멕스)카드</a></li>
						<li><a href="javascript:;">신한카드</a></li>
						<li><a href="javascript:;">NH카드</a></li>
						<li><a href="javascript:;">씨티카드</a></li>
					</ul>
				</div>
			</div>
			<!-- 내/외국인 -->
			<div class="dropdown-container" id="name">
				<!-- style="background:#282d38 url("../images/temp/TW_pnd_12.jpg") no-repeat 0 0;" -->
				<div class="dropdown-content" id="scroll1">
					<ul id="inouts">
						<li><a href="javascript:;">선택하세요</a></li>
						<li><a href="javascript:;">내국인</a></li>
						<li><a href="javascript:;">외국인</a></li>
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
