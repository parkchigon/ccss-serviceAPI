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


<script type="text/javascript" src="../../js/jquery-1.10.1.js"></script>
<script type="text/javascript" src="../../js/phone/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../js/phone/custom.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
		
		$("input[id=checkAll]").change(function() {
			if($("[id=checkAll]").prop("checked")==true) {
				agreeActiveBut(true);
			} else {
				agreeActiveBut(false);
			}
		});
		
	});

	function agreeActiveBut(act) {
		if (act==true) {
			$("[id=agreeY]").removeClass("color2");
			$("[id=agreeY]").addClass("color1");
		} else {
			$("[id=agreeY]").removeClass("color1");
			$("[id=agreeY]").addClass("color2");
		}
	}
	
	function allCheckFunc(obj) {
		$("[id=check]").prop("checked", $(obj).prop("checked"));
		//alert("전체 선택1");
	}

	function oneCheckFunc(obj) {
		var allobj = $("[id=checkAll]");
		var objId = $(obj).attr("id");

		if ($(obj).prop("checked")) {

			checkBoxLength = $("[id=" + objId + "]").length;
			checkedLength = $("[id=" + objId + "]:checked").length;

			if (checkBoxLength == checkedLength) {
				allobj.prop("checked", true);
				agreeActiveBut(true);
			} else {
				allobj.prop("checked", false);
				agreeActiveBut(false);
			}

		} else {
			allobj.prop("checked", false);
			agreeActiveBut(false);
		}
	}
	$(function() {
		$("[id=checkAll]").click(function() {
			allCheckFunc(this);
		});
		$("[id=check]").each(function() {
			$(this).click(function() {
				oneCheckFunc($(this));
			});
		});
	});
	
	//동의
	function agreementY() {
		
		if($("[id=checkAll]").prop("checked")!=true) {
			return;
		}
		if (isMobile.iOS()) {
			var message = {
				"action" : "call",
				"function" : "agreementY"
			}
			try {
				webkit.messageHandlers.callbackHandler.postMessage(message);
			} catch (err) {
				alert(err);
			}
		} else if (isMobile.Android()) {
		android.agreementY();
		}
	}
	
	//비동의
	function agreementN() {
		if (isMobile.iOS()) {
			var message = {
				"action" : "call",
				"function" : "agreementN"
			}
			try {
				webkit.messageHandlers.callbackHandler.postMessage(message);
			} catch (err) {
				alert(err);
			}
		} else if (isMobile.Android()) {
		android.agreementN();
		}
	}
	
	//자세히보기
	function callAgreement(flag){
		console.log(arguments);
		url:"/mypage/phone/agreementCustom/";
		location.href="/mypage/phone/agreementCustom/"+flag;
	}
</script>
</head>
<body>

<div class="page page-scroll">
    <div class="pageHead fixed" >
    	<!-- <a href="#" class="headPre">이전</a> -->
    	<div class="headTitle">서비스 이용약관</div>
    	<!-- <a href="#" class="headMenu">메뉴</a> -->
    </div>
    <div class="container">
		<section>
	        <div class="term_checkall_area">
				<div class="chk-all">
					<span class="chk-mgr">
						<input type="checkbox" id="checkAll">
						<label for="check5">이용약관 모두 동의</label>
					</span>
				</div>
	         </div>
        </section>
        <section>
        	<div class="term_agree">
     			<dl>
      				<dt>
						<span class="chk-mgr">
			                <input type="checkbox" id="check">
	                        <label for="check1">서비스 이용 약관동의 (필수)</label>
	                    </span>
					</dt>
					<dd class="right" onclick='callAgreement("agrservice")'><a href="javascript:;"><span>자세히보기</span></a></dd>
				</dl>
     			<dl>
      				<dt>
						<span class="chk-mgr">
			                <input type="checkbox" id="check">
	                        <label for="check2">개인정보 활용 약관동의 (필수)</label>
	                    </span>
					</dt>
					<dd class="right" onclick='callAgreement("information")'><a href="javascript:;"><span>자세히보기</span></a></dd>
				</dl>
     			<dl>
      				<dt>
						<span class="chk-mgr">
			                <input type="checkbox" id="check">
	                        <label for="check3">개인정보 제3자 약관동의 (필수)</label>
	                    </span>
					</dt>
					<dd class="right" onclick='callAgreement("third")'><a href="javascript:;"><span>자세히보기</span></a></dd>
				</dl>
     			<dl>
      				<dt>
						<span class="chk-mgr">
			                <input type="checkbox" id="check">
	                        <label for="check4">위치정보 이용 약관동의 (필수)</label>
	                    </span>
					</dt>
					<dd class="right" onclick='callAgreement("location")'><a href="javascript:;"><span>자세히보기</span></a></dd>
				</dl>
        	</div>
        </section>

		<div class="footerBtnArea fixed">
			<a href="javascript:;" class="footer_btn color2" onclick="agreementN();">동의안함</a>
			<a href="javascript:;" id="agreeY" class="footer_btn color2" onclick="agreementY();">동의</a>
		</div>

    </div>
</div>

</body>
</html>
