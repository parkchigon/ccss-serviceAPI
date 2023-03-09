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
	var bcategory = "<c:out value='${bltxtKdCd}'/>";
	var secureYn = "<c:out value='${scurMailRcpYn}'/>";
	var emailId = "<c:out value='${billEmailAddr1}'/>";
	var emailDomain = "<c:out value='${billEmailAddr2}'/>";
	var changeEmail = "N";

	$(function() {
		//이메일 선택
		$('#select-box2').on('click', function(e){
	        e.preventDefault();
	        var selBox = $(this);
	        var path = $(this).attr('href');
	        var dropdown = $(path).find('ul li');
	        
	        
	       $('#select-box2').removeClass('focus');
	        $(this).addClass('focus');
	        $(path).show();
	        $('.dim').show();       
	        
	        dropdown.each(function(){
	            $(this).each(function(){
	                $(this).find('a').on('click', function(){
	                	
	                    var txt = $(this).text();

	                    $(this).parent().addClass('active').siblings().removeClass('active');
	                    //selBox.text(txt);

	                    if($(this).text()=="직접입력") {
	                    	$("#ip-txt").attr("disabled",false);
	                    	$("#ip-txt").val("");
	                    } else {
		                    $("#ip-txt").attr("disabled",true);
		                    $("#ip-txt").val(txt);
	                    }
	                    
	                    $(path).hide();
	                    $('.dim').hide();
	                });
	            });
	        });
	        
	        
	        $('input').on('focus', function(){
	            selBox.removeClass('focus');
	        });
	        $('.dim').on('click', function(){
	            $('.dropdown-container').hide();
	            $('.dim').hide();
	        });
	    });
		
		
		bindUserAgent();

		$("#ie").click(function() {
			if (!$("#ie").hasClass("sel")) {
				$("#ie").addClass("sel");
				$("#ip").removeClass("sel");
				$("#iei").css("display", "");
				$("#ipi").css("display", "none");
				$("#iet1").css("display", "");
				$("#iet2").css("display", "");
				$("#ipt").css("display", "none");
				//수령방법 : e-mail(상세) 청구서
				bcategory = "Y";
			}
		});

		$("#ip").click(function() {
			if (!$("#ip").hasClass("sel")) {
				$("#ip").addClass("sel");
				$("#ie").removeClass("sel");
				$("#ipi").css("display", "");
				$("#iei").css("display", "none");
				$("#ipt").css("display", "");
				$("#iet1").css("display", "none");
				$("#iet2").css("display", "none");
				//수령방법 : 휴대폰(간편청구서)
				bcategory = "P";
			}
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
	
	function authKMCChoice(){
		window.open("authKmcMain.do", null, null);
	}
	

	function validation(tp, ci, di, pn, nm, bd) {
		changeType(tp, ci, di, pn, nm, bd);
	}

	function checkChangeType() {
		// Y - e-mail(상세) 청구서
		// P - 휴대폰(간편청구서)
		if ("" == bcategory) {
			//showToast("수령방법을 선택해주세요.");
			// console.log("수령방법을 선택해주세요.");
			showErrorInputAlert();
			return;
		}
		if("Y" == bcategory){
			
			if ("" == secureYn) {
				//showToast("보안여부를 선택해주세요.");
				// console.log("보안여부를 선택해주세요.");
				showErrorInputAlert();
				return;
			}
	
			//
			emailId = $("#id").val();
			if ("" == emailId) {
				//showToast("이메일을 입력해주세요.");
				// console.log("이메일을 입력해주세요.");
				showErrorInputAlert();
				return;
			}
	
			//
			emailDomain = $("#domain").val();
			if ("" == emailDomain) {
				//showToast("이메일을 입력해주세요.");
				// console.log("이메일을 입력해주세요.");
				showErrorInputAlert();
				return;
			}
	
			//
			if ("" == changeEmail) {
				//showToast("변경여부를 선택해주세요.");
				// console.log("변경여부를 선택해주세요.");
				showErrorInputAlert();
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
		//var userAgent = userAgent;
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

		showProgressDialog();

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
					//android.showChangeBillCompleted(data.custNm, data.bltxtKdNm);
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

	function showChangeBillCompleted(pname, ptype) {
		$(".dim").show();
		$("#completed").css("display", "block");
		$("#ptitle").val(ptype);
		$("#pname").val(pname);
		$("#ptype").val(ptype);
		$("#confirm").click(function() {
			$(".dim").hide();
			$("#completed").css("display", "none");
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
			<div class="headTitle">청구서 유형 변경</div>
			<!-- <a href="#" class="headMenu">메뉴</a> -->
		</div>
		<div class="container">
			<section>
				<div class="data_info_view">
					<dl>
						<dt>현재 청구서</dt>
						<dd>
							<div class="d-grid">
								<table>
									<caption>현재청구서</caption>
									<colgroup>
										<col style="width: 30%">
										<col style="width: auto">
									</colgroup>
									<tbody>
										<tr>
											<th>청구서 유형</th>
											<td>${bltxtKdNm}</td>
										</tr>
										<tr>
											<th>신청일</th>
											<td>${bltxtKdValdStrtDt}</td>
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
						<dt>청구서 반송 내역</dt>
						<dd>
							<div class="t-grid mt10 mb10">
								<table>
									<caption>청구서반송내역</caption>
									<colgroup>
										<col style="width: 35%">
										<col style="width: auto">
									</colgroup>
									<c:if test="${not empty list}">
										<c:forEach var="vo" items="${list}">
											<tbody>
												<tr>
													<th>청구서 계정번호</th>
													<td>1</td>
												</tr>
												<tr>
													<th>청구 년/월</th>
													<td>${vo.billTrgtYymm}</td>
												</tr>
												<tr>
													<th>청구서 종류</th>
													<td>${vo.retnDvNm}</td>
												</tr>
												<tr>
													<th>반송일</th>
													<td>${vo.dlvDt}</td>
												</tr>
												<tr>
													<th>반송사유</th>
													<td>${vo.retnRsnNm}</td>
												</tr>
											</tbody>
										</c:forEach>
									</c:if>
									<c:if test="${empty list}">
										<tbody>
											<tr>
												<td>청구서 반송 내역이 없습니다.</td>
											</tr>
										</tbody>
									</c:if>
								</table>
							</div>
						</dd>
					</dl>
				</div>
			</section>

			<section>
				<div class="data_info_view">
					<dl>
						<dt>청구서 수령 방법 / 주소 변경 </dt>
						<dd>
							<div class="info_view_inner2">
								<dl>
									<dt>수령 방법</dt>
									<dd>
										<a href="javascript:;" class="rect-box sel" id="ie">이메일 상세
											청구서</a> <a href="javascript:;" class="rect-box" id="ip">휴대폰
											간편 청구서</a> 
									</dd>
									<dd class="info" id="iei">입력한 이메일 주소로 청구서가 발송됩니다</dd>
									<dd class="info" style="display: none;" id="ipi">U+ 고객센터
										앱을 통해서도 보실 수 있습니다.</dd>
								</dl>
								<dl id="iet1">
									<dt>보안 이메일 선택</dt>
									<dd>
										<span class="rdo-mgr"> <input type="radio" name="r-chk"
											id="check4" checked="checked"> <label for="check4">예</label>
										</span> <span class="rdo-mgr"> <input type="radio"
											name="r-chk" id="check5"> <label for="check5">아니오</label>
										</span>
									</dd>
									<dd class="info">보안 메일이란 주민등록번호로 인증을 해야 확인할 수 있는 메일 입니다.</dd>
								</dl>
								<dl id="iet2">
									<dt>이메일 주소</dt>
									<dd class="email">
										<input type="text" value="${billEmailAddr1}"  id="id"> <span class="at">@</span>
										<%-- <a href="#email" class="select-box">naver.com</a> 
										<input type="text" class="ip-txt" value="${billEmailAddr2}"  style="display: none;"> --%>
											<!-- 20181220 이메일 직접입력 및 선택 수정 -->
											<input type="text" class="ip-txt"  id="ip-txt" value="naver.com"  disabled="disabled" ><!-- 직접입력시 -->
											 <a href="#email" class="select-box2" id="select-box2"></a>
											<!-- //20181220 이메일 직접입력 및 선택 수정 -->
									</dd>
									<dd>
										<span class="chk-mgr"> <input type="checkbox"
											id="check2"> <label for="check2">회원정보에도 동일하게
												적용</label>
										</span>
									</dd>
								</dl>
								<ul style="display: none;" id="ipt">
									<li>고객 명의의 휴대폰으로만 받을 수 있습니다.</li>
									<li>휴대폰 인증절차 : <br />이용중인 통신사 선택 > 휴대폰 번호입력 > 인증 요청 >
										문자메세지로 전송된 인증번호를 입력합니다.
									</li>
									<li>인증요청 후 5분 안에 인증번호를 입력해야 합니다.</li>
								</ul>
							</div>
						</dd>
					</dl>
				</div>
			</section>

			<div class="footerBtnArea">
				<a href="javascript:;" class="footer_btn color2" onclick="back();">취소하기</a>
				<a href="javascript:;" class="footer_btn color1"
					onclick="checkChangeType();">변경하기</a>
			</div>

			<div class="dim"></div>
			<div class="dropdown-container" id="email">
				<!-- style="background:#282d38 url("../images/temp/TW_pnd_12.jpg") no-repeat 0 0;" -->
				<div class="dropdown-content" id="scroll1">
					<ul id="emails">
 						<li><a href="javascript:;" id="direct">직접입력</a></li>
						<li><a href="javascript:;">daum.net</a></li>
						<li><a href="javascript:;">dreamwiz.com</a></li>
						<li><a href="javascript:;">gmail.com</a></li>
						<li><a href="javascript:;">hanmail.net</a></li>
						<li><a href="javascript:;">hotmail.com</a></li>
						<li><a href="javascript:;">kornet.net</a></li>
						<li><a href="javascript:;">lycos.net</a></li>
						<li><a href="javascript:;">msn.com</a></li>
						<li><a href="javascript:;">nate.com</a></li>
						<li><a href="javascript:;">naver.com</a></li>
						<li><a href="javascript:;">yahoo.co.kr</a></li>
						<li><a href="javascript:;">yahoo.com</a></li>
					</ul>
				</div>
			</div>
			<!-- Progress -->
			<div id="progress" class="progress" style="display: none;">
				<img class="gif" src="../../images/phone/loading_progress.gif" />
			</div>
			<!-- Alert -->
			<div id="alert" class="popup2" style="display: none;">
				<div class="popup_inner2">
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
			<!-- 청구서 신청 변경 완료 -->
			<div id="completed" class="popup2" style="display: none;">
				<div class="popup_inner2">
					<p class="title">청구서 신청 변경 완료</p>
					<!-- 타이틀 없을경우 삭제 -->
					<div class="info">
						<span><span id="ptitle">지로</span>로 변경되었습니다.</span>
					</div>
					<div class="p-grid">
						<table>
							<caption>요금제변경 신청 완료</caption>
							<colgroup>
								<col style="width: 76px;">
								<col style="width: auto">
							</colgroup>
							<tbody>
								<tr>
									<th>고객명</th>
									<td id="pname">
										<!-- 김제이 -->
									</td>
								</tr>
								<tr>
									<th>청구서 유형</th>
									<td id="ptype">
										<!-- 휴대폰(간편)청구서 -->
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="comment">다음달부터 변경된 청구서가 발송됩니다.</div>
				</div>
				<div class="popup_btnArea">
					<!-- <a href="#" class="btn2 color1 duo">닫기</a> -->
					<!-- <a href="#" class="btn2 color2 duo">업데이트</a> -->
					<!-- 버튼 1개일경우 -->
					<a id="confirm" href="javascript:;" class="btn2">확인</a>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
