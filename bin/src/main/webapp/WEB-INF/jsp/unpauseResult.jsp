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
	$(function() {
		$("#scroll").enscroll({
			showOnHover : false,
			verticalTrackClass : "track3",
			verticalHandleClass : "handle3"
		});
	});
</script>
</head>
<body>
<!-- style="background:url('../images/temp/TW_pnd_08.jpg') no-repeat 0 0;" -->
<div class="page page-scroll"><!-- 스크롤 페이지 경우 .page-scroll -->
    <div class="container">
        <!-- 정액요금제 -->
        <section class="flat-rate">
            <!-- 서비스변경내역 -->
            <div class="service-change">

              <!-- tab menu -->
				<nav class="tab-menu-etc"> <a href="#t2">서비스 일시정지 내역</a> </nav>

				<div class="scroll-container">
					<!-- 서비스 변경내역 -->
					<div class="inbox-scroll" id="t1">
						<!-- 스크롤영역 -->
						<div class="wrapper">
							<!-- 본문 -->
							서비스 변경내역
						</div>
					</div>
					<!-- //서비스 변경내역 -->

                <div class="scroll-container">
                    <div class="inbox-scroll on" id="t1">
                        <!-- 20190115 서비스 일시정지 해지신정 완료 -->
                        <div class="service_info">
                            <p class="notice">서비스 일시정지 해제 신청이 접수되었습니다.<br>시스템 반영 시까지 다소 시간이 걸릴 수 있습니다.<br><em>약 5분 후 단말을 재부팅 해주시기 바랍니다.</em></p>
                        </div>
                        <!-- //20190115 서비스 일시정지 해지신정 완료 -->
                    </div>
                </div>
            </div>
            <!-- 서비스변경내역 -->
            <a href="#" class="top">TOP</a><!-- 2017.09.29 컨텐츠 영역 내로 이동 -->
        </section>
        <!-- //정액요금제 -->
    </div>
</div>
</body>