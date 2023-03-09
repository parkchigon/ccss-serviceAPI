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

	<div class="page page-scroll">
		<!-- 스크롤 페이지 경우 .page-scroll -->
		<div class="container">
			<section class="terms-condt"> <!-- 이용약관 -->
			<div class="inbox-scroll" id="scroll">
				<!-- 스크롤영역 -->
				<div class="wrapper">
					<!-- 본문 -->
					<div class="title">
						<h1>커넥티드 카 서비스 이용약관</h1>
					</div>
					${termsCont}
				</div>
				<!-- //본문 -->
			</div>
			<!-- //이용약관 --> <a href="#" class="top">TOP</a><!-- 2017.09.29 컨텐츠 영역 내로 이동 -->
			</section>
		</div>
	</div>

</body>
</html>
