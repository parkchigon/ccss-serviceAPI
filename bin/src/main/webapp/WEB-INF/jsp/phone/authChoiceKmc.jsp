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
	$(function() {

	});
	
	function back() {
		window.close();
	}
</script>
</head>
<body>

	<div class="page page-scroll">
		<!-- 스크롤 페이지 경우 .page-scroll -->
		<div class="pageHead fixed">
			<a href="javascript:;" onclick="back();" class="headPre">이전</a>
			<div class="headTitle">본인인증</div>
			<!-- <a href="#" class="headMenu">메뉴</a> -->
		</div>
		<div class="container">
			<section>
				<div class="fare-change">
					<dl>
						<dt>
							<strong>요금제 변경을 위한 본인 인증을 완료해 주세요</strong>
						</dt>
						<dd>
							<div class="fare-change-chk" >
								<ul class="certification" >
									<li style="text-align: center; width: 100%;">
										<h2>휴대폰 본인인증</h2>
										<p>
											본인 명의의 메시지 수신<br> 가능한 휴대폰으로 인증 번호<br> 를 받으실 수 있습니다.
										</p>
										<div class="btnArea">
											<a href="authKmcMain.do" class="btn">인증하기</a>
										</div>
									</li>
									<!-- <li>
										<h2>아이핀 본인인증</h2>
										<p>
											본인 인증 기관을 통한<br> 아이핀(I-PIN) 인증 후<br> 가입하기
										</p>
										<div class="btnArea">
											<a href="authIpinMain.do" class="btn">인증하기</a>
										</div>
									</li> -->
								</ul>
							</div>
						</dd>
					</dl>
				</div>
			</section>
		</div>
	</div>

</body>
</html>