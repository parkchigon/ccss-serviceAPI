<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densityDpi=device-dpi" />

<title>LGU+ Connected Car</title>
<link rel="stylesheet" href="../../css/phone/common.css">
<link rel="stylesheet" href="../../css/phone/style.css">

<script type="text/javascript" src="../../js/phone/jquery-1.10.1.js"></script>
<script type="text/javascript" src="../../js/phone/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../js/phone/custom.js"></script>
<script type="text/javascript">
	
	
	$(function() {
	    /* layer popup */
		var winH = $(window).height();
		var winW = $(window).width();
		var scrollT = $(window).scrollTop();
	
		$('.popup').css('top', winH/2-$('.popup').height()/2 + scrollT);
		$('.popup').css('left', winW/2-$('.popup').width()/2);
	});


	//*********************************************************************************************
	// 구매 진행 화면 전환
	//*********************************************************************************************
	function funcGoBuyPage(){
		if($('#canBuyGift').val() == 'true'){
			document.mainForm.action = 'list.do';
			document.mainForm.submit();	
		}else{
			openAlertMessage();			
		}
	}
	//*********************************************************************************************
	
	
	
	//*********************************************************************************************
	// 구매이력으로 화면 전환
	//*********************************************************************************************
	function funcGoHistoryPage(){
		document.mainForm.action = 'history.do';
		document.mainForm.submit();
	}
	//*********************************************************************************************
	
	
	
	//*********************************************************************************************
	// 임의로 추가한 팝업 닫기
	//*********************************************************************************************
	function closePop(){
		//$('.dropdown-container').hide();
		$('#alertDiv').hide();
        $('.dim').hide();
	}
	//*********************************************************************************************
	
	
	
	//*********************************************************************************************
	// 메시지 팝업
	//*********************************************************************************************
	function openAlertMessage(){
		$('.dim').show();
		$('#alertDiv').show();
	}
	//*********************************************************************************************
	
	function navigationBack()
	{
		window.location.href = 'closewebview://';
	}
</script>
</head>
<body>
	<form name="mainForm" method="get">
		<div class="page page-scroll">
			<!-- 스크롤 페이지 경우 .page-scroll -->
			<div class="pageHead">
				<a href="closewebview://" class="headPre">이전</a>
				<div class="headTitle">데이터 상품권</div>
				<!-- <a href="#" class="headMenu">메뉴</a> -->
			</div>
			<div class="container">
				<section>
				<div class="pay_list">
					<ul>
						<!-- 
						<li><a href="list.do?carNumber=${carNumber}&canBuyGift=${canBuyGift}">상품권 구매</a></li>
						<li><a href="history.do">구매내역</a></li>
						-->
						<li><a href="javascript:funcGoBuyPage();">상품권 구매</a></li>
						<li><a href="javascript:funcGoHistoryPage();">구매내역</a></li>
					</ul>
				</div>
				</section>
			</div>
			<div class="dim" style="display: none;"></div>

			<div class="popup" style="display: none;" id="alertDiv">
			   <div class="popup_inner">
				   <div class="info">
					   가입하신 요금제는 상품권을 구매 가능한 요금제(부가서비스)가 아닙니다.
				   </div>
				   <div class="info">
					   현재 요금제는 Basic으로 Premium 이상의 부가서비스 가입이 필요합니다.
				   </div>
			   </div>
			   <div class="popup_btnArea">
				   <a href="#" class="btn color2" onclick="javascript:closePop();">확인</a>
			   </div>
			</div>
		</div>
		<input type="hidden" name="carNumber" 	id="carNumber"    value="<c:out value="${carNumber}" />" />
		<input type="hidden" name="canBuyGift" 	id="canBuyGift"   value="<c:out value="${canBuyGift}" />" />
		<input type="hidden" name="devType" 	id="devType"   value="<c:out value="${devType}" />" />
		<input type="hidden" name="ccssToken" 	id="ccssToken"   value="<c:out value="${ccssToken}" />" />
		<input type="hidden" name="appVersion" 	id="appVersion"   value="<c:out value="${appVersion}" />" />
		<input type="hidden" name="carOem" 	id="carOem"   value="<c:out value="${carOem}" />" />
		<input type="hidden" name="devModel" 	id="devModel"   value="<c:out value="${devModel}" />" />
		<input type="hidden" name="posX" 	id="posX"   value="<c:out value="${posX}" />" />
		<input type="hidden" name="posY" 	id="posY"   value="<c:out value="${posY}" />" />
		<input type="hidden" name="userCtn" 	id="userCtn"   value="<c:out value="${userCtn}" />" />
		<input type="hidden" name="uuid" 	id="uuid"   value="<c:out value="${uuid}" />" />
	</form>
	
	

	<br>
	<br>
	<br>	
	<!--
	<div>
		========== DEBUG ========= <br>
		carNumber = <c:out value="${carNumber}" />   <br>
		canBuyGift = <c:out value="${canBuyGift}" /> <br>
		========================== <br>	
	</div>
	-->
</body>
</html>
