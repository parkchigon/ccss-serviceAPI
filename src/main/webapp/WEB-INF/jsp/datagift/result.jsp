<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String resultMsg = (String)request.getAttribute("RESULT_MESSAGE");
	String resultType = (String)request.getAttribute("RESULT_TYPE");
%>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densityDpi=device-dpi" />
<title>LGU+ Connected Car</title>
<link rel="stylesheet" href="../../css/phone/common.css">
<link rel="stylesheet" href="../../css/phone/style.css">

<script type="text/javascript" src="../../js/phone/jquery-1.10.1.js"></script>
<script type="text/javascript" src="../../js/phone/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../js/phone/custom.js"></script>

<head>
	<script language="javascript">
	
	//*********************************************************************************************
	// 바로전 화면인 payment로 화면 전환...
	//*********************************************************************************************
	function goToPayment(){
		var $form = $('<form></form>');
		$form.attr('action', 'main.do');
		$form.attr('method', 'get');
		//$form.attr('target', '_self');
		$form.appendTo('body');
		
		var carNumber			= $('<input type="hidden" value="<c:out value="${carNumber}" />"			name="carNumber">'			);
		var giftName			= $('<input type="hidden" value="<c:out value="${giftName}" />"				name="giftName">'			);
		var canBuyGift			= $('<input type="hidden" value="<c:out value="${canBuyGift}" />"			name="canBuyGift">'			);
		var dataSize			= $('<input type="hidden" value="<c:out value="${dataSize}" />"				name="dataSize">'			);
		var sellAmount			= $('<input type="hidden" value="<c:out value="${sellAmount}" />"			name="sellAmount">'			);
		var issueNo				= $('<input type="hidden" value="<c:out value="${issueNo}" />"				name="issueNo">'			);
		var giftKub				= $('<input type="hidden" value="<c:out value="${giftKub}" />"				name="giftKub">'			);
		var issueName			= $('<input type="hidden" value="<c:out value="${issueName}" />"			name="issueName">'			);
		var campStartDate		= $('<input type="hidden" value="<c:out value="${campStartDate}" />"		name="campStartDate">'		);
		var campEndDate			= $('<input type="hidden" value="<c:out value="${campEndDate}" />"			name="campEndDate">'		);
		var LGD_BUYER			= $('<input type="hidden" value="<c:out value="${LGD_BUYER}" />"			name="LGD_BUYER">'			);
		var LGD_BUYER_PHONENUM	= $('<input type="hidden" value="<c:out value="${LGD_BUYER_PHONENUM}" />"	name="LGD_BUYER_PHONENUM">'	);	
		var LGD_BUYEREMAIL		= $('<input type="hidden" value="<c:out value="${LGD_BUYEREMAIL}" />"		name="LGD_BUYEREMAIL">'		);
		var LGD_CUSTOM_FIRSTPAY = $('<input type="hidden" value="<c:out value="${LGD_CUSTOM_FIRSTPAY}" />"	name="LGD_CUSTOM_FIRSTPAY">');
		
		$form.append(carNumber)	
			 .append(giftName)
			 .append(canBuyGift)
			 .append(dataSize)
			 .append(sellAmount)
			 .append(issueNo)
			 .append(giftKub)
			 .append(issueName)
			 .append(campStartDate)
			 .append(campEndDate)
			 .append(LGD_BUYER)
			 .append(LGD_BUYER_PHONENUM)
			 .append(LGD_BUYEREMAIL)
			 .append(LGD_CUSTOM_FIRSTPAY)
			 ;
		$form.submit();
	}
	//*********************************************************************************************
	
	//*********************************************************************************************
	// 상품권 구매 메인화면으로 화면 전환
	//*********************************************************************************************
	function goToMain(){
		var $form = $('<form></form>');
		$form.attr('action', 'main.do');
		$form.attr('method', 'get');
		//$form.attr('target', '_self');
		$form.appendTo('body');
		
		var carNumber  = $('<input type="hidden" value="<c:out value="${carNumber}" />" name="carNumber">');
		var canBuyGift = $('<input type="hidden" value="<c:out value="${canBuyGift}" />" name="canBuyGift">');
		var devType = $('<input type="hidden" value="<c:out value="${devType}" />" name="devType">');
		
		$form.append(carNumber);
		$form.append(canBuyGift);
		$form.append(devType);
		$form.submit();
	}
	//*********************************************************************************************
	
	//*********************************************************************************************
	// 상품권 구매 메인화면으로 화면 전환
	//*********************************************************************************************
	function goToHistory(){
		var $form = $('<form></form>');
		$form.attr('action', 'history.do');
		$form.attr('method', 'get');
		//$form.attr('target', '_self');
		$form.appendTo('body');
		
		var carNumber  = $('<input type="hidden" value="<c:out value="${carNumber}" />" name="carNumber">');
		var canBuyGift = $('<input type="hidden" value="<c:out value="${canBuyGift}" />" name="canBuyGift">');
		var devType = $('<input type="hidden" value="<c:out value="${devType}" />" name="devType">');
		
		$form.append(carNumber);
		$form.append(canBuyGift);
		$form.append(devType);
		$form.submit();
	}
	//*********************************************************************************************	
	</script>
</head>
<body>
<div class="dimmed"></div>
<c:set var="resultType" value="<%=resultType%>" />
<c:choose>
    <c:when test="${resultType eq '100'}">
    	<div class="page page-scroll"><!-- 스크롤 페이지 경우 .page-scroll -->
		    <div class="pageHead">
		    	<div class="headTitle">구매 결과</div>
		    </div>
		    <div class="container">
				<section>
			        <div class="info_view_inner3">
						<strong>
							결제가 취소 되었습니다.<br>
						</strong>
			         </div>
		        </section>
				<div class="footerBtnArea fixed" onclick="javascript:goToPayment(); return false">
					<a href="#" class="footer_btn btnAll color1">확인</a>
				</div>
		    </div>
		</div>
    </c:when>
    <c:when test="${resultType eq '150'}">
    	<div class="page page-scroll"><!-- 스크롤 페이지 경우 .page-scroll -->
		    <div class="pageHead">
		    	<div class="headTitle">구매 결과</div>
		    </div>
		    <div class="container">
				<section>
			        <div class="info_view_inner3">
						<strong>
							데이터상품권 결제에 실패했습니다.<br> 
							잠시 후 다시 시도해 주세요.<br>
						</strong>
			         </div>
		        </section>
				<div class="footerBtnArea fixed" onclick="javascript:goToPayment(); return false">
					<a href="#" class="footer_btn btnAll color1">확인</a>
				</div>
		    </div>
		</div>
    </c:when>
    <c:when test="${resultType eq '200'}">
		<div class="page page-scroll"><!-- 스크롤 페이지 경우 .page-scroll -->
		    <div class="pageHead">
		    	<div class="headTitle">구매 결과</div>
		    </div>
		    <div class="container">
				<section>
			        <div class="info_view_inner3">
						<strong>
							데이터 상품권 구매가 완료되었습니다.<br>
							구매한 내역은 [데이터상품권 > 구매내역]에서 확인하실 수 있습니다.
						</strong>
			         </div>
		        </section>
				<div class="footerBtnArea fixed" onclick="javascript:goToMain(); return false">
					<a href="#" class="footer_btn btnAll color1">확인</a>
				</div>
		    </div>
		</div>
    </c:when>
    <c:when test="${resultType eq '250'}">
    	<div class="page page-scroll"><!-- 스크롤 페이지 경우 .page-scroll -->
		    <div class="pageHead">
		    	<div class="headTitle">구매 결과</div>
		    </div>
		    <div class="container">
				<section>
			        <div class="info_view_inner3">
						<strong>
							데이터상품권 결제에 실패했습니다.<br> 
							잠시 후 다시 시도해 주세요.<br>
						</strong>
			         </div>
		        </section>
				<div class="footerBtnArea fixed" onclick="javascript:goToPayment(); return false">
					<a href="#" class="footer_btn btnAll color1">확인</a>
				</div>
		    </div>
		</div>
    </c:when>    
    <c:when test="${resultType eq '400'}">
		<div class="page page-scroll"><!-- 스크롤 페이지 경우 .page-scroll -->
		    <div class="pageHead">
		    	<div class="headTitle">구매 결과</div>
		    </div>
		    <div class="container">
				<section>
			        <div class="info_view_inner3">
						<strong>
							데이터상품권 발급에 실패했습니다.<br>
							잠시 후 다시 시도해 주세요.<br>
						</strong>
			         </div>
		        </section>
				<div class="footerBtnArea fixed" onclick="javascript:goToMain(); return false">
					<a href="#" class="footer_btn btnAll color1">확인</a>
				</div>
		    </div>
		</div>
    </c:when>
    <c:when test="${resultType eq '500'}">
		<div class="page page-scroll"><!-- 스크롤 페이지 경우 .page-scroll -->
		    <div class="pageHead">
		    	<div class="headTitle">구매 결과</div>
		    </div>
		    <div class="container">
				<section>
			        <div class="info_view_inner3">
						<strong>
							데이터상품권 발급에 실패했습니다.<br>
							구매내역에서 결제취소를 해 주세요.<br>
						</strong>
			         </div>
		        </section>
				<div class="footerBtnArea fixed" onclick="javascript:goToHistory(); return false">
					<a href="#" class="footer_btn btnAll color1">확인</a>
				</div>
		    </div>
		</div>
    </c:when>
    <c:otherwise>
		<div class="page page-scroll"><!-- 스크롤 페이지 경우 .page-scroll -->
		    <div class="pageHead">
		    	<div class="headTitle">구매 결과</div>
		    </div>
		    <div class="container">
				<section>
			        <div class="info_view_inner3">
						<strong>
							결재처리에 장애가 발생하였습니다.<br>
							관리자 문의를 바랍니다.
						</strong>
			         </div>
		        </section>
				<div class="footerBtnArea fixed" onclick="javascript:goToMain(); return false">
					<a href="#" class="footer_btn btnAll color1">확인</a>
				</div>
		    </div>
		</div>
    </c:otherwise>
</c:choose>
<br>
* resultType=[<%=resultType%>]<br>
* resultMsg=[<%=resultMsg%>]<br>

<!-- 
<br>
<br>

========== DEBUG ========= <br>
resultMsg = <%=resultMsg%>  		<br>
resultType = <%=resultType%>  		<br>
carNumber = <c:out value="${carNumber}" />  		<br>
canBuyGift = <c:out value="${canBuyGift}" /> 		<br>
giftName = <c:out value="${giftName}" />  			<br>
dataSize = <c:out value="${dataSize}" />  			<br>
sellAmount = <c:out value="${sellAmount}" />  		<br>
issueNo = <c:out value="${issueNo}" />  			<br>
giftKub = <c:out value="${giftKub}" />  			<br>
issueName = <c:out value="${issueName}" />  		<br>
campStartDate = <c:out value="${campStartDate}" />  <br>
campEndDate = <c:out value="${campEndDate}" />  	<br>
canBuyGift = <c:out value="${canBuyGift}" /> 		<br>
LGD_BUYER = <c:out value="${LGD_BUYER}" />  		<br>
LGD_BUYER_PHONENUM = <c:out value="${LGD_BUYER_PHONENUM}" />  	<br>
LGD_BUYEREMAIL = <c:out value="${LGD_BUYEREMAIL}" />  			<br>
LGD_CUSTOM_FIRSTPAY = <c:out value="${LGD_CUSTOM_FIRSTPAY}" />  <br>
========================== <br>	
-->
 		
</body>
</html>