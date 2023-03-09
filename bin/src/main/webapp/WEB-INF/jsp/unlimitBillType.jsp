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
	var bcategory = "<c:out value='${bltxtKdCd}'/>";
	var secureYn = "<c:out value='${scurMailRcpYn}'/>";
	var emailId = "<c:out value='${billEmailAddr1}'/>";
	var emailDomain = "<c:out value='${billEmailAddr2}'/>";
	var changeEmail = "N";

	var emails = [ "직접입력", "daum.net", /* "dreamwiz.com", */ "gmail.com",
			"hanmail.net", "hotmail.com", "kornet.net", "naver.com" ];
	$(function() {
	$("#t1, #t2").enscroll({
		showOnHover : false,
		verticalTrackClass : "track3",
		verticalHandleClass : "handle3"
	});

	$("#cate-email").click(function() {
		//수령방법 : e-mail(상세) 청구서
		bcategory = "Y";
	});

	$("#cate-phone").click(function() {
		//수령방법 : 휴대폰(간편청구서)
		bcategory = "P";
	});

	$("#lr").click(function() {
		//보안메일수신여부
		secureYn = "Y";
	});

	$("#rr").click(function() {
		//보안메일수신여부
		secureYn = "N";
	});

	$("#m-chk").click(function() {
		if ($("#m-chk").is(":checked")) {
			changeEmail = "Y";
		} else {
			changeEmail = "N";
		}
	});

	$("#direct").click(function() {
		android.showSpinner("selectedEmail", emails, 0);
	});
});
	function selectedEmail(email, index) {
		var email = emails[index];

		if (index == 0) {
			$("#domain").attr("disabled",false);
        	$("#domain").val("");
		} else {
			$("#domain").attr("disabled",true);
            $("#domain").val(email);
		}
	}

	function goPayDetail() {
		location.href = "unlimitPayDetail.do";
	}

	function authChoice() {
		window.open("authChoice.do?modelNmGbn=<c:out value='${modelNmGbn}'/>", null, null);
	}
	
	function authKMCChoice() {
		/* window.open("authChoice.do", null, null); */
		window.open("authKmcMain.do", null, null);
	}

	function validation(tp, ci, di, pn, nm, bd) {
		changeType(tp, ci, di, pn, nm, bd);
	}

	function checkChangeType(s) {
		// Y - e-mail(상세) 청구서
		// P - 휴대폰(간편청구서)
				
		bcategory = s;		

		if ("" == bcategory) {
			//android.showToast("수령방법을 선택해주세요.");
			console.log("수령방법을 선택해주세요.");
			android.showErrorInputAlert();
			return;
			
		}

		if("Y" == bcategory){
			
			if ("" == secureYn) {
				//android.showToast("보안여부를 선택해주세요.");
				console.log("보안여부를 선택해주세요.");
				android.showErrorInputAlert();
				return;
			}
			emailId = $("#id").val();
			if ("" == emailId) {
				//android.showToast("이메일을 입력해주세요.");
				console.log("이메일을 입력해주세요.");
				android.showErrorInputAlert();
				return;
			}

			//
			emailDomain = $("#domain").val();
			if ("" == emailDomain) {
				//android.showToast("이메일을 입력해주세요.");
				console.log("이메일을 입력해주세요.");
				android.showErrorInputAlert();
				return;
			}

			//
			if ("" == changeEmail || "N" == changeEmail ) {
				//android.showToast("변경여부를 선택해주세요.");
				console.log("변경여부를 선택해주세요.");
				android.showErrorInputAlert();
				return;
			}			
			authChoice();			
		}else{
			authKMCChoice();
		} 		
	}

	function changeType(tp, ci, di, pn, nm, bd) {
		var bltxtKdCdNm = "" + bcategory;
		var scurMailRcpYn = "" + secureYn;
		var emailCopyCustYn = "" + changeEmail;
		var billEmailAddr = "" + emailId + "@" + emailDomain;
		var userAgent = android.getUserAgent();
		var dscntSvcNm;
		var dscntSvcCd;
		if(bcategory == "P"){
			dscntSvcNm = "휴대폰(간편) 청구서";
			dscntSvcCd = "LDZ0000739";
		}else{
			dscntSvcNm = "E-MAIL(상세) 청구서";
			dscntSvcCd = "LDZ0000085";
		}
		

		var sendData = JSON.stringify({
			tp : tp,
			ci : ci,
			di : di,
			pn : pn,
			nm : nm,
			bd : bd,
			bltxtKdCdNm : bltxtKdCdNm,
			scurMailRcpYn : scurMailRcpYn,
			emailCopyCustYn : emailCopyCustYn,
			billEmailAddr : billEmailAddr,
			dscntSvcNm : dscntSvcNm,
			dscntSvcCd : dscntSvcCd
		});

		android.showProgressDialog();

		$.ajax({
			type : "POST",
			url : "changeType",
			headers : {
				"User-Agent" : userAgent
			},
			data : sendData,
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			async : true,
			success : function(data) {
				if ("0000" == data.resultCode) {
					android
							.showChangeBillCompleted(data.custNm,
									data.bltxtKdNm);
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
	<!-- style="background:url("../images/temp/TW_pnd_11.jpg") no-repeat 0 0;"  -->
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
						<div class="modify-fm wrapper">
							<!-- 본문 -->
							<h1>현재 청구서</h1>
							<div class="t-grid">
								<table class="fee-info">
									<caption>요금 납부 정보</caption>
									<colgroup>
										<col style="width: 193px">
										<col style="width: auto">
									</colgroup>
									<tbody>
										<tr>
											<th>청구서 유형</th>
											<td class="type">${bltxtKdNm}</td>
										</tr>
										<tr>
											<th>신청일</th>
											<td>${bltxtKdValdStrtDt}</td>
										</tr>
									</tbody>
								</table>
							</div>

							<h1>청구서 반송내역</h1>
							<div class="t-grid">
								<table class="fee-info">
									<thead>
										<tr>
											<th>청구서 계정번호</th>
											<th>청구 년/월</th>
											<th>청구서 종류</th>
											<th>반송일</th>
											<th>반송사유</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${not empty list}">
											<c:forEach var="vo" items="${list}">
												<tr>
													<td>${vo.billAcntNo}</td>
													<td>${vo.billTrgtYymm}</td>
													<td>${vo.retnDvNm}</td>
													<td>${vo.dlvDt}</td>
													<td>${vo.retnRsnNm}</td>
												</tr>
											</c:forEach>
										</c:if>
										<c:if test="${empty list}">
											<tr>
												<td class="return" colspan="5">청구서 반송 내역이 없습니다.</td>
											</tr>
										</c:if>
									</tbody>
								</table>
							</div>

							<h1>청구서 유형 및 이메일 주소 변경</h1>
							<div class="d-grid inquiry">
								<table>
									<caption>정보입력</caption>
									<colgroup>
										<col style="width: 184px">
										<col style="width: auto">
									</colgroup>
									<tbody>
										<tr>
											<th>수령방법</th>
											<td class="t-m"><a href="#inq-email" id="cate-email"
												class="rect-box sel">이메일 상세 청구서</a> <a href="#inq-phone"
												id="cate-phone" class="rect-box">휴대폰 간편 청구서</a>

												<p class="notice sel">입력한 이메일 주소로 청구서가 발송됩니다</p>
												<p class="notice">U+ 고객센터 앱을 통해서도 보실 수 있습니다.</p></td>
										</tr>
									</tbody>
								</table>

								<div class="fm-container">
									<!-- 이메일 청구서 -->
									<div class="private sel" id="inq-email">
										<table>
											<colgroup>
												<col style="width: 184px">
												<col style="width: auto">
											</colgroup>
											<tbody>
												<tr class="nth-1">
													<th>보안 이메일<br>선택
													</th>
													<td><c:if test="${scurMailRcpYn eq 'Y'}">
															<span class="rdo-mgr"> <input type="radio"
																name="r-chk" id="lr" checked="checked"> <label
																for="lr">예</label>
															</span>
															<span class="rdo-mgr"> <input type="radio"
																name="r-chk" id="rr"> <label for="rr">아니오</label>
															</span>
														</c:if> <c:if test="${scurMailRcpYn ne 'Y'}">
															<span class="rdo-mgr"> <input type="radio"
																name="r-chk" id="lr"> <label for="lr">예</label>
															</span>
															<span class="rdo-mgr"> <input type="radio"
																name="r-chk" id="rr" checked="checked"> <label
																for="rr">아니오</label>
															</span>
														</c:if>
														<p class="notice">
															보안메일이란 주민등록번호로 인증을 해야 확인할 수 <br> 있는 메일입니다.
														</p></td>
												</tr>
												<tr class="nth-2">
													<th>이메일 주소</th>
													<td class="email">
													<input type="text"	value="${billEmailAddr1}" id="id"> 
													<span	class="at">@</span> 
													 <!-- 20181220 이메일 직접입력 및 선택 수정 -->
                                                        <input type="text" class="ip-txt"  id="domain" value="naver.com"  disabled="disabled" ><!-- 직접입력시 -->
														 <a href="#email" class="select-box" id="direct"></a>
                                  		            <!-- //20181220 이메일 직접입력 및 선택 수정 -->
													 <%-- <a href="#email" class="select-box2" id="direct">직접입력</a> <!-- 직접입력시 시작-->
													 <input type="text"	class="ip-txt" value="${billEmailAddr2}"
														style="display: none;" id="domain"> <!-- 직접입력시 끝--> --%>
														<span class="chk-mgr"> <input type="checkbox"
															id="m-chk"> <label for="m-chk">회원 정보에도
																변경사항 적용</label>
													</span></td>
												</tr>
											</tbody>
										</table>
										<div class="bt">
											<a href="javascript:;" class="btn-line"
												onclick="history.back();">취소</a> <a href="javascript:;"
												class="btn-line" onclick="checkChangeType('Y');">변경하기</a>
										</div>
									</div>
									<!-- //이메일 청구서 -->

									<!-- 휴대폰 청구서 -->
									<div class="private" id="inq-phone">
										<ul class="note">
											<li>고객 명의의 휴대폰으로만 받을 수 있습니다.</li>
											<li>휴대폰 인증절차 : 이용 중인 통신사 선택 &gt; 휴대폰 번호 입력 &gt; 인증요청
												&gt; 문자 메시지로 전송 된 인증 번호를 입력합니다.</li>
											<li>인증 요청 후 5분 안에 인증번호를 입력해야 합니다.</li>
										</ul>
										<div class="bt">
											<a href="javascript:;" class="btn-line"
												onclick="history.back();">취소</a> <a href="javascript:;"
												class="btn-line" onclick="checkChangeType('P');">변경하기</a>
										</div>
									</div>
									<!-- //휴대폰 청구서 -->
								</div>
							</div>
						</div>
					</div>
					<!-- //요금납부정보 -->

					<!-- 상세내역조회 -->
					<div class="inbox-scroll" id="t2">
						<!-- 스크롤영역 -->
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
