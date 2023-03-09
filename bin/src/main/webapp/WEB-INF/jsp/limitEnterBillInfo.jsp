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
	var pcategory = "<c:out value='${pymMthdCd}'/>"; //납부방법구분 

	var ptype = "I"; //납입유형
	var cname = ""; //카드사명
	var foreigner = ""; //외국여부
	var cnumber = ""; //카드번호
	var oname = ""; //카드소유주
	var rcrno = ""; //사업자번호
	var rrno = ""; //주민번호
	var yyyy = ""; //카드 유효기간
	var mm = ""; //카드 유효기간

	var cards = [ "BC카드", "국민카드", "KEB하나카드", "삼성카드", "신한(구LG)카드", "현대카드", "롯데(구 아멕스)카드", "신한카드", "NH카드", "씨티카드" ];
	var cardCodes = [ "01", "02", "03", "04", "05", "07", "08", "26", "71", "72" ];

	var inOuts = [ "내국인", "외국인" ];
	var inOutCodes = [ "", "Y" ];
	
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1;
	var yyyy = today.getFullYear();
	
	var years = []
	var yearCodes = []		
	for(var i = 0; i < 11; i++){
		var tenYear = yyyy+i
		var yearChar = tenYear+'년'
		yearCodes.push(''+tenYear+'')
		years.push(yearChar)
	}	
			
	/* var years = [ "2017년", "2018년", "2019년", "2020년", "2021년", "2022년",
			"2023년", "2024년", "2025년", "2026년", "2027년" ];
	var yearCodes = [ "2017", "2018", "2019", "2020", "2021", "2022", "2023",
			"2024", "2025", "2026", "2027" ]; */

	var months = [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월",
			"11월", "12월" ];
	var monthCodes = [ "01", "02", "03", "04", "05", "06", "07", "08", "09",
			"10", "11", "12" ];

	$(function() {
		$("#scroll").enscroll({
			showOnHover : false,
			verticalTrackClass : "track3",
			verticalHandleClass : "handle3"
		});

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

		if ("Y" == payResvYn) {
			android.showAlreadyAlert();
		}
	});

	function maxLengthCheck(object) {
		if (object.value.length > object.maxLength) {
			object.value = object.value.slice(0, object.maxLength);
		}
	}

	function selectCard() {
		android.showSpinner("selectedCard", cards, 0);
	}

	function selectedCard(card, index) {
		var cardCode = cardCodes[index];

		$("#cname").text(cards[index]);

		cname = cardCode;
	}

	function selectInOut() {
		android.showSpinner("selectedInOut", inOuts, 0);
	}

	function selectedInOut(inOut, index) {
		var inOutCode = inOutCodes[index];

		$("#inOut").text(inOuts[index]);

		foreigner = inOutCode;
	}

	function selectYear() {
		android.showSpinner("selectedYear", years, 0);
	}

	function selectedYear(year, index) {
		var yearCode = yearCodes[index];

		$("#yyyy").text(years[index]);

		yyyy = yearCode;
	}

	function selectMonth() {
		android.showSpinner("selectedMonth", months, 0);
	}

	function selectedMonth(month, index) {
		var monthCode = monthCodes[index];

		$("#mm").text(months[index]);

		mm = monthCode;
	}

	function authChoice() {
		/* window.open("authChoice.do?planYn=Y", null, null); */
		window.open("authKmcMain.do", null, null);
	}

	function validation(tp, ci, di, pn, nm, bd) {
		applyPlan(tp, ci, di, pn, nm, bd);
	}

	// 요금제변경
	function applyPlan(tp, ci, di, pn, nm, bd) {
		var userAgent = android.getUserAgent();

		var sendData = JSON.stringify({
			tp : tp,
			ci : ci,
			di : di,
			pn : pn,
			nm : nm,
			bd : bd,
		});

		android.showProgressDialog();

		$.ajax({
			type : "POST",
			url : "applyPlan",
			headers : {
				"User-Agent" : userAgent
			},
			data : sendData,
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			async : true,
			success : function(data) {
				if ("0000" == data.resultCode) {
					android.showCompleted(data.name, data.pay, data.date);
				} else if ("1000" == data.resultCode) {
					android.showAlreadyAlert();
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

	// 납부방법변경 
	function changeMethod() {//LTE
		if ("Y" == payResvYn) {
			android.showAlreadyAlert();
			return;
		}

		var lteYn = android.availableLTE();
		if ("N" == lteYn) {
			android.showAlert("알림", "요금제 신청은 LTE 상태에서만 가능합니다.", "닫기");
			return;
		}

		//개인 : I, 법인 : G
		if ("" == ptype) {
			//android.showToast("납부유형을 선택해주세요.");
			console.log("납부유형을 선택해주세요.");
			android.showErrorInputAlert();
			return;
		}

		//"국민카드", "하나카드", "삼성카드"
		//"02", "16", "04"
		if ("" == cname) {
			//android.showToast("카드사를 선택해주세요.");
			console.log("카드사를 선택해주세요.");
			android.showErrorInputAlert();
			return;
		}

		//0000-0000-0000-0000
		cnumber = $("#cnumber").val();
		if ("" == cnumber) {
			//android.showToast("카드번호를 입력해주세요.");
			console.log("카드번호를 입력해주세요.");
			android.showErrorInputAlert();
			return;
		}

		//이름
		oname = $("#oname").val();
		if ("" == oname) {
			//android.showToast("이름을 입력해주세요.");
			console.log("이름을 입력해주세요.");
			android.showErrorInputAlert();
			return;
		}

		var tmp = "";
		if ("I" == ptype) {
			//00000000-0000000
			rrno = "" + $("#rrno01").val() + $("#rrno02").val();
			if ("" == rrno) {
				//android.showToast("주민번호를 입력해주세요.");
				console.log("주민번호를 입력해주세요.");
				android.showErrorInputAlert();
				return;
			}
			tmp = rrno;
		} else if ("G" == ptype) {
			//000-00-00000
			crno = "" + $("#crno01").val() + $("#crno02").val()
					+ $("#crno03").val();
			if ("" == crno) {
				//android.showToast("사업자번호를 입력해주세요.");
				console.log("사업자번호를 입력해주세요.");
				android.showErrorInputAlert();
				return;
			}
			tmp = crno;
		} else {
			//android.showToast("납부유형을 선택해주세요.");
			console.log("납부유형을 선택해주세요.");
			android.showErrorInputAlert();
			return;
		}

		//0000
		//yyyy = "" + $("#yyyy").val();
		if ("" == yyyy) {
			//android.showToast("유효기간을 선택해주세요.");
			console.log("유효기간을 선택해주세요.");
			android.showErrorInputAlert();
			return;
		}

		//00
		//mm = "" + $("#mm").val();
		if ("" == mm) {
			//android.showToast("유효기간을 선택해주세요.");
			console.log("유효기간을 선택해주세요.");
			android.showErrorInputAlert();
			return;
		}

		//var tmp = "";
		var acntOwnrNo = "" + tmp;//주민번호
		var custDvCd = "" + ptype;//개인:I,법인:G
		var persIdind = "" + foreigner;//외국인:Y
		var cdcmpCd = "" + cname;//카드타입
		var cardNo = "" + cnumber;//카드번호
		var cardValdEndYymm = "" + yyyy + mm;//카드유효기간
		var pymCustNm = "" + oname;//이름
		var userAgent = android.getUserAgent();

		var sendData = JSON.stringify({
			acntOwnrNo : acntOwnrNo,
			custDvCd : custDvCd,
			persIdind : persIdind,
			cdcmpCd : cdcmpCd,
			cardNo : cardNo,
			cardValdEndYymm : cardValdEndYymm,
			pymCustNm : pymCustNm
		});

		android.showProgressDialog();

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
					authChoice();
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
	<!--  style="background:url("../images/temp/TW_pnd_07.jpg") no-repeat 0 0"  -->
	<div class="page page-scroll">
		<!-- 스크롤 페이지 경우 .page-scroll -->
		<div class="container">
			<!-- 쿠폰요금제 -->
			<section class="coupon-rate"> <!-- 요금제변경 -->
			<div class="fare-change">
				<div class="inbox-scroll" id="scroll">
					<!-- 스크롤영역 -->
					<div class="wrapper">
						<!-- 본문 -->
						<div class="title">
							<h1><청구 정보 입력></h1>
						</div>

						<div class="d-grid line">
							<!-- 내용 -->
							<div class="fm-container">
								<!-- 신용카드 -->
								<table class="private sel">
									<colgroup>
										<col style="width: 184px">
										<col style="width: auto">
									</colgroup>
									<tbody>
										<tr>
											<th>납부방법</th>
											<td class="o-m">신용카드</td>
										</tr>
										<tr>
											<th>납입유형</th>
											<td><a href="javascript:;" class="rect-box sel" id="rr">개인</a>
												<a href="javascript:;" class="rect-box" id="cr">사업자</a></td>
										</tr>
										<tr>
											<th>카드사명</th>
											<td class="bank"><a href="javascript:;"
												class="select-box" onclick="selectCard();" id="cname">선택하세요</a>
											</td>
										</tr>
										<tr>
											<th>카드 번호</th>
											<td class="acc-n"><input type="number"
												max="9999999999999999" maxlength="16"
												oninput="maxLengthCheck(this);" placeholder="‘-’ 없이 숫자만 입력"
												id="cnumber" value=""></td>
										</tr>
										<tr>
											<th>카드 소유주</th>
											<td class="name"><a href="javascript:;"
												class="select-box" onclick="selectInOut();" id="inOut">내국인</a>
												<input type="text" id="oname" value=""></td>
										</tr>
										<tr id="rrno">
											<th>사업자(개인) 주민 등록번호</th>
											<td class="id-card"><input type="number" max="999999"
												maxlength="6" oninput="maxLengthCheck(this);" id="rrno01"
												value=""> <span class="dash">-</span> <input
												type="number" style="-webkit-text-security: disc;"
												max="9999999" maxlength="7" oninput="maxLengthCheck(this);"
												id="rrno02" value=""></td>
										</tr>
										<tr style="display: none;" id="crno">
											<th>사업자번호</th>
											<td class="id-card"><input style="width: 104px;"
												type="number" max="999" maxlength="3"
												oninput="maxLengthCheck(this);" id="crno01" value="">
												<span class="dash">-</span> <input style="width: 94px;"
												type="number" max="99" maxlength="2"
												oninput="maxLengthCheck(this);" id="crno02" value="">
												<span class="dash">-</span> <input style="width: 184px;"
												type="number" max="99999" maxlength="5"
												oninput="maxLengthCheck(this);" id="crno03" value="">
											</td>
										</tr>
										<tr>
											<th>카드 유효기간</th>
											<td class="inq-n"><a href="javascript:;"
												class="select-box" onclick="selectYear();" id="yyyy">년</a> <a
												href="javascript:;" class="select-box"
												onclick="selectMonth();" id="mm">월</a></td>
										</tr>
										<tr>
											<th>납부 금액</th>
											<td class="pay">
												<strog class="pt-txt-red">13,200원 (부가세 포함금액)</strog>
											</td>
										</tr>
									</tbody>
								</table>
								<!-- //신용카드 -->
							</div>
							<!-- // 내용 -->
						</div>

						<ul class="note">
							<li>결제일은 매월 12일이고 결제일이 주말 또는 공휴일인 경우 돌아오는 첫 평일에 결제됩니다.</li>
							<li>자동 결제일 전에 직접 납부하는 경우 중복으로 결제될 수 있으니, 유의해 주세요.중복 결제된 경우
								자동으로 환불되거나 다음날 요금에서 납부한 만큼 미리 결제됩니다.</li>
							<li>신용카드로 납부하는 경우 휴대폰 또는 아이폰 인증 등의 본인 확인이 필요합니다.</li>
							<li>고객님의 정확한 결제일을 확인하시려면 U+ 홈페이지 1:1 문의하기로 접수해 주세요.</li>
						</ul>

						<!-- 버튼 -->
						<div class="bt">
							<a href="javascript:;" class="btn-line" onclick="history.back();">취소</a>
							<a href="javascript:;" class="btn-line" onclick="changeMethod();">신청하기</a>
						</div>
						<!-- //버튼 -->
					</div>
					<!-- //본문 -->
				</div>
				<!-- //스크롤영역 -->
			</div>
			<!-- //요금제 변경 --> <a href="#" class="top">TOP</a><!-- 2017.09.29 컨텐츠 영역 내로 이동 -->
			</section>
			<!-- //쿠폰요금제 -->
		</div>
	</div>
	<!--  -->
</body>
</html>
