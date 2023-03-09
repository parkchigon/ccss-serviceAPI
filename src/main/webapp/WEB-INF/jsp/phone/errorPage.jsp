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
		history.back();
	}

	function exit() {
		if(document.referrer){
			history.back();
		}else{
			closed();
		}
	}
</script>
</head>
<body>

	<div class="page page-scroll">
		<!-- 스크롤 페이지 경우 .page-scroll -->
		<div class="pageHead fixed">
			<a href="javascript:;" class="headPre" onclick="back();">이전</a>
			<div class="headTitle">에러</div>
			<!-- <a href="#" class="headMenu">메뉴</a> -->
		</div>
		<div class="container">
			<section>
				<div class="data_info_view">
					<dl>
						<dd>
							<div class="noData">입력하신 페이지 주소가 정확한지 다시 한번 확인해보시기 바랍니다.</div>
						</dd>
					</dl>
				</div>
			</section>

			<div class="footerBtnArea fixed">
				<a href="javascript:;" class="footer_btn btnAll color1"
					onclick="exit();">닫기</a>
			</div>
		</div>
	</div>

</body>
</html>