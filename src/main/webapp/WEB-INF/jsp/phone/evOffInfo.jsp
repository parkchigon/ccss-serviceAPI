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
<script type="text/javascript" src="../../js/phone/custom.js"></script>
<script type="text/javascript">
	$(function() {
	});

	function back() {
		closed();
	}

</script>
</head>
<body>

	<div class="page page-scroll">
	    <div class="pageHead">
	    	<a href="javascript:;" class="headPre" onclick="back();">이전</a>
	    	<div class="headTitle">충전상태</div>
	    	<!-- <a href="#" class="headMenu">메뉴</a> -->
	    </div>
	    <div class="container">
	        <section>
	        	<div class="evcharge-state">
	     			<dl>
	      				<dt>
							<div class="evcharge_area"><!-- 충전 상태별 -->
 								<c:choose>
   									<c:when test="${ev.batteryCapa eq '-1'}">
										<div class="evcharge_low">충전 low</div>
									</c:when>
									<c:when test="${ev.batteryCapa eq '-2'}">
										<div class="evcharge_middle">충전 middle</div>
									</c:when>
									<c:when test="${ev.batteryCapa eq '-3'}">
										<div class="evcharge_high">충전 high</div>
									</c:when>
								</c:choose>						
							</div>
						</dt>
	      				<dd>
				            <div class="evcharge_info">
				                <p class="text">
									시동이 꺼진 상태에서는 정확한 충전<br>
									상태를 확인할 수 없습니다.
				                </p>
				            </div>
	      				</dd>
	     			</dl>
	        	</div>
	        </section>
	    </div>
	</div>

</body>
</html>