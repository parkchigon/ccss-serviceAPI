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

		$(".full-popup .service_list ul li label").click(function(e) {
			e.preventDefault();

			if ($(this).parents("li").hasClass("on")) {
				$(this).parents("li").find("img").removeClass("on");
				$(this).parents("li").removeClass("on")
				$(this).parents("li").next("li.info").slideUp();
			} else {
				$(this).parents("li").next("li.info").slideDown();
				$(this).parents("li").addClass("on");
				$(this).parents("li").find("img").addClass("on");
			}
		});

		$(".full-popup .service_list ul li img").click(function(e) {
			e.preventDefault();

			if ($(this).parents("li").hasClass("on")) {
				$(this).parents("li").find("img").removeClass("on");
				$(this).parents("li").removeClass("on");
				$(this).parents("li").next("li.info").slideUp();
			} else {
				$(this).parents("li").next("li.info").slideDown();
				$(this).parents("li").addClass("on");
				$(this).parents("li").find("img").addClass("on");
			}
		});

		<c:forEach items="${list}" var="terms">
		$("#id" + "${terms.termsNo}").click(function() {
			var val = true;
			<c:forEach items="${list}" var="terms">
			val &= $("#id" + "${terms.termsNo}").is(":checked");
			</c:forEach>
			if (!val) {
				$("#check").prop("checked", false);
			} else {
				$("#check").prop("checked", true);
			}
		});
		</c:forEach>
	});

	function toggle() {
		var val = $("#check").is(":checked");
		if (val) {
			<c:forEach items="${list}" var="terms">
			$("#id" + "${terms.termsNo}").prop("checked", true);
			</c:forEach>
		} else {
			<c:forEach items="${list}" var="terms">
			$("#id" + "${terms.termsNo}").prop("checked", false);
			</c:forEach>
		}
	}

	function force() {
		$("#check").prop("checked", true);
		<c:forEach items="${list}" var="terms">
		$("#id" + "${terms.termsNo}").prop("checked", true);
		</c:forEach>
	}

	function agree() {
		var val = true;
		<c:forEach items="${list}" var="terms">
		var flag1 = $("#id" + "${terms.termsNo}").is(":checked");
		var flag2 = ("Y" == "${terms.requiredYn}" ? true : false);
		if (flag2) {
			val &= flag1;
		}
		</c:forEach>

		if (!val) {
			android.showToast("약관에 동의해 주세요.");
			return;
		}

		var list = new Array();
		<c:forEach items="${list}" var="terms">
		var flag = $("#id" + "${terms.termsNo}").is(":checked");
		if (flag) {
			list.push("${terms.termsNo}");
		}
		</c:forEach>

		android.acceptTerms(list);
	}
</script>
</head>
<body>
	<!-- style="background:url("../images/temp/TW_pnd_05.jpg") no-repeat 0 0;" -->
	<div class="page page-scroll full-popup">
		<!-- 페이지 형태 팝업 .full-popup (공통) -->
		<div class="container">
			<!-- 내용(공통) -->
			<section class="content">
			<div class="inner-cont">
				<div class="inbox-scroll" id="scroll">
					<!-- 스크롤영역 -->
					<div class="wrapper">
						<!-- 본문 -->
						<h1>서비스 이용약관</h1>
						<div class="service_list">
							<ul>
								<c:forEach var="vo" items="${list}">
									<li class="title"><span class="chk-mgr"> <input
											type="checkbox" id="id${vo.termsNo}"> <label
											for="check1"> ${vo.termsTitle} <span>&nbsp;</span> <c:if
													test="${vo.requiredYn eq 'Y'}">
													<span class="txt-red">[필수]</span>
												</c:if> <c:if test="${vo.requiredYn eq 'N'}">
													<span class="txt-grey">[선택]</span>
												</c:if>
										</label> <img class="cursor" align="right" />
									</span></li>
									<li class="info">${vo.termsCont}</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<a href="#" class="top">TOP</a><!-- 2017.09.29 컨텐츠 영역 내로 이동 --> </section>
			<!-- //내용(공통) -->

			<!-- 하단(공통) -->
			<footer class="footer"> <span class="chk-mgr"> <input
				type="checkbox" id="check" onclick="toggle();"> <label
				for="check" onclick="toggle();">전체 동의 (선택항목 포함)</label>
			</span>
			<div class="bt">
				<a href="javascript:;" class="btn-line agree" onclick="agree();">이용약관
					동의 및 사용</a>
			</div>
			</footer>
			<!-- //하단(공통) -->
		</div>
	</div>


</body>
</html>
