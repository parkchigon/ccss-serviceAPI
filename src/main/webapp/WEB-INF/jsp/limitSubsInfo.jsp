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
<script type="text/javascript" src="../../js/d3.v3.min.js"></script>
<script>
	$(function() {
		var remain = parseFloat("<c:out value='${remainingValue}'/>");
		var allow = parseFloat("<c:out value='${alloValue}'/>");
		var percentage = remain / allow;
		
		var τ = 2 * Math.PI,
	    width = 100,
	    height = 100,
	    outerRadius = Math.min(width,height) / 2,
	    innerRadius = outerRadius - 4;
	
	    var arc = d3.svg.arc()
	        .innerRadius(innerRadius)
	        .outerRadius(outerRadius)
	        .cornerRadius(outerRadius - innerRadius)
	        .startAngle(0);
	
	    var svg = d3.select(".chart-container").append("svg")
	        .attr("width", "100%")
	        .attr("height", "100%")
	        .attr("viewBox","0 0 " + Math.min(width,height) + " " + Math.min(width,height) )
	        .attr("preserveAspectRatio","xMinYMin")
	        .append("g")
	        .attr("transform", "translate(" + Math.min(width,height) / 2 + "," + Math.min(width,height) / 2 + ")");
	        
	    var background = svg.append("path")
	        .datum({endAngle: τ})
	        .style("fill", "#31282c")
	        .attr("d", arc);
	
	    var foreground = svg.append("path")
	        .datum({endAngle: 0 * τ})
	        .style("fill", "#fd6d6d")
	        .attr("d", arc);
	
	    foreground.transition()
	          .duration(750)
	          .call(arcTween, percentage * τ);
	
	    function arcTween(transition, newAngle) {
	        transition.attrTween("d", function(d) {
	            var interpolate = d3.interpolate(d.endAngle, newAngle);
	            return function(t) {
	                d.endAngle = interpolate(t);
	                // text.text(Math.round((d.endAngle/τ)*100)+"%");
	                return arc(d);
	            };
	        });
	    }
	});
</script>
</head>
<body>

	<div class="page">
		<div class="container">
			<!-- 쿠폰요금제 -->
			<section class="coupon-rate"> <!-- 가입정보 -->
			<div class="fare-info">
				<div class="product">
					<h1 class="name">
						<p class="small">요금제명</p>
						<p class="big">${svcNm}</p>
					</h1>
					<ul class="period">
						<li><span>가입일</span></li>
						<li><em>${svcFrstStrtDttm}</em></li>
						<li><span>가입번호</span></li>
						<li><em>${ctn}</em></li>
						<li style="margin-top: 4px;"><span>무료 이용 기간</span></li>
						<li><em>${svcStrtDttm} ~ ${svcEndDttm}</em></li>
					</ul>
				</div>
				<div class="data">
					<div class="graph">
						<div class="chart-container"></div>
					</div>
					<!-- 그래프영역 -->
					<ul class="rate">
						<li><span class="tt">남은 데이터</span> <em class="mb"> <strong>${remainingValue}</strong>
								MB
						</em></li>
						<li><span class="tt">총 데이터</span> <em class="mb"> <strong>${alloValue}</strong>
								MB
						</em></li>
					</ul>
				</div>
			</div>
			<!-- //가입정보 --> <a href="#" class="top">TOP</a> <!-- 2017.09.29 컨텐츠 영역 내로 이동 -->
			</section>
			<!-- //쿠폰요금제 -->
		</div>
	</div>

</body>
</html>
