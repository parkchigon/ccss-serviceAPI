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

	});

	
	/* function back() {
		//history.back();
		document.historyForm.action = '/datagift/postMain.do';
		document.historyForm.submit();	
	} */
	
	
	//*********************************************************************************************
	// 타이틀바 back 수행
	//*********************************************************************************************
	function back(){
		var $form = $('<form></form>');
		$form.attr('action', 'postMain.do');
		$form.attr('method', 'get');
		$form.appendTo('body');
		
		var carNumber  = $('<input type="hidden" value="<c:out value="${carNumber}" />" name="carNumber">');
		var devType = $('<input type="hidden" value="<c:out value="${devType}" />" name="devType">');
		var ccssToken = $('<input type="hidden" value="<c:out value="${ccssToken}" />" name="ccssToken">');
		var appVersion = $('<input type="hidden" value="<c:out value="${appVersion}" />" name="appVersion">');
		var carOem = $('<input type="hidden" value="<c:out value="${carOem}" />" name="carOem">');
		var devModel = $('<input type="hidden" value="<c:out value="${devModel}" />" name="devModel">');
		var nwType = $('<input type="hidden" value="<c:out value="${nwType}" />" name="nwType">');
		var posX = $('<input type="hidden" value="<c:out value="${posX}" />" name="posX">');
		var posY = $('<input type="hidden" value="<c:out value="${posY}" />" name="posY">');
		var userCtn = $('<input type="hidden" value="<c:out value="${userCtn}" />" name="userCtn">');
		var uuid = $('<input type="hidden" value="<c:out value="${uuid}" />" name="uuid">');
		
		$form.append(carNumber);
		$form.append(devType);
		$form.append(ccssToken);
		$form.append(appVersion);
		$form.append(carOem);
		$form.append(devModel);
		$form.append(nwType);
		$form.append(posX);
		$form.append(posY);
		$form.append(userCtn);
		$form.append(uuid);
		
		$form.submit();
	}
	//*********************************************************************************************	
	
	function navigationBack()
	{
			var $form = $('<form></form>');
			$form.attr('action', 'postMain.do');
			$form.attr('method', 'get');
			$form.appendTo('body');
			
			var carNumber  = $('<input type="hidden" value="<c:out value="${carNumber}" />" name="carNumber">');
			var devType = $('<input type="hidden" value="<c:out value="${devType}" />" name="devType">');
			var ccssToken = $('<input type="hidden" value="<c:out value="${ccssToken}" />" name="ccssToken">');
			var appVersion = $('<input type="hidden" value="<c:out value="${appVersion}" />" name="appVersion">');
			var carOem = $('<input type="hidden" value="<c:out value="${carOem}" />" name="carOem">');
			var devModel = $('<input type="hidden" value="<c:out value="${devModel}" />" name="devModel">');
			var nwType = $('<input type="hidden" value="<c:out value="${nwType}" />" name="nwType">');
			var posX = $('<input type="hidden" value="<c:out value="${posX}" />" name="posX">');
			var posY = $('<input type="hidden" value="<c:out value="${posY}" />" name="posY">');
			var userCtn = $('<input type="hidden" value="<c:out value="${userCtn}" />" name="userCtn">');
			var uuid = $('<input type="hidden" value="<c:out value="${uuid}" />" name="uuid">');
			
			$form.append(carNumber);
			$form.append(devType);
			$form.append(ccssToken);
			$form.append(appVersion);
			$form.append(carOem);
			$form.append(devModel);
			$form.append(nwType);
			$form.append(posX);
			$form.append(posY);
			$form.append(userCtn);
			$form.append(uuid);
			
			$form.submit();
	}
	
	//*********************************************************************************************
	// 결제 취소 수행
	//*********************************************************************************************
	function cancel(lgd_tid, issueNo, lgdOid, memberId){
		var $form = $('<form></form>');
		$form.attr('action', 'cancel.do');
		$form.attr('method', 'post');
		$form.appendTo('body');
		
		var carNumber  = $('<input type="hidden" value="<c:out value="${carNumber}" />" name="carNumber">');
		var devType = $('<input type="hidden" value="<c:out value="${devType}" />" name="devType">');
		var tid = $('<input type="hidden" value="' + lgd_tid + '" name="LGD_TID">');
		var isNo = $('<input type="hidden" value="' + issueNo + '" name="issueNo">');
		var oid = $('<input type="hidden" value="' + lgdOid + '" name="lgdOid">');
		var mid = $('<input type="hidden" value="' + memberId + '" name="memberId">');
		
		$form.append(carNumber);
		$form.append(devType);
		$form.append(tid);
		$form.append(isNo);
		$form.append(oid);
		$form.append(mid);
		
		$form.submit();
	}
	//*********************************************************************************************	
	

</script>
</head>
<body>
	<!-- <form method="post" name="historyForm" id="historyForm" action="main.do"> -->
		<div class="page page-scroll">
			<!-- 스크롤 페이지 경우 .page-scroll -->
			<div class="pageHead">
				<a href="javascript:;" class="headPre" onclick="back();">이전</a>
				<div class="headTitle">구매내역</div>
				<!-- <a href="#" class="headMenu">메뉴</a> -->
			</div>
			<div class="container">
				<section>
				<div class="data_info_view">
					<dl>
						<dd>
							<div class="info_view_inner2">
								<ul class="type2">
									<c:choose>
    									<c:when test="${dataGiftListCnt eq '0'}">
											<li>최근 구매한 데이터 상품권 내역이 없습니다.</li>
										</c:when>
										<c:when test="${dataGiftListCnt ne '0'}">
											<li>최근 구매한 데이터 상품권 내역입니다.</li>
										</c:when>
									</c:choose>
								</ul>
							</div>
						</dd>
					</dl>
				</div>
				</section>
	
				<section>
				<div class="data_info_view type1">
					<c:forEach var="vo" items="${list}">
						<dl>
							<!-- <dt><c:out value="${vo.startDate}" /></dt>  -->
							<dt><c:out value="${vo.payDate}" /></dt>
							<dd>
								<div class="info_view_inner">
									<dl>
										<dt><c:out value="${vo.dataSize}" />MB/<c:out value="${vo.sellAmount}" />원</dt>
										<dd class="left"><c:out value="${vo.carNumber}" />
										<dd class="right">
											<c:choose>
												<c:when test="${vo.issueRegKup=='E'}">
													<c:out value="${vo.state}" />
													<button onclick="cancel('<c:out value="${vo.tid}" />', '<c:out value="${vo.issueNo}" />', '<c:out value="${vo.lgdOid}" />', '<c:out value="${vo.memberId}" />');">결제취소하기</button>
												</c:when>
												<c:when test="${vo.issueRegKup=='M'}">
													<c:out value="${vo.state}" />
													<button onclick="cancel('<c:out value="${vo.tid}" />', '<c:out value="${vo.issueNo}" />', '<c:out value="${vo.lgdOid}" />', '<c:out value="${vo.memberId}" />');">결제취소하기</button>
												</c:when>
												<c:otherwise>
													<c:out value="${vo.state}" />
												</c:otherwise>										
											</c:choose>
										</dd>
									</dl>
								</div>
							</dd>
						</dl>
					</c:forEach>
				</div>
				</section>
			</div>
		</div>
	<!-- 	
		<input type="hidden" name="carNumber"  id="carNumber"    value="<c:out value="${carNumber}" />" />
		<input type="hidden" name="devType"  id="devType"    value="<c:out value="${devType}" />" />
	 -->	
	<!-- </form>  -->


</body>
</html>