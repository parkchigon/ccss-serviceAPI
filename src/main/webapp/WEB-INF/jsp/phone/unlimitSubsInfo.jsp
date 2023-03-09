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
<script type="text/javascript" src="../../js/phone/d3.v3.min.js"></script>
<script type="text/javascript" src="../../js/phone/custom.js"></script>
<script type="text/javascript">
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
	        .attr("transform", "translate(" + (Math.min(width,height) / 2) + "," + (Math.min(width,height) / 2) + ")");
	        
	    var background = svg.append("path")
	        .datum({endAngle: τ})
	        .style("fill", "#efefef")
	        .attr("d", arc);
	
	    var foreground = svg.append("path")
	        .datum({endAngle: 0 * τ})
	        .style("fill", "#242A3B")
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

	function back() {
		history.back();
	}
</script>
</head>
<body>

	<div class="page page-scroll">
		<!-- 스크롤 페이지 경우 .page-scroll -->
		<div class="pageHead fixed">
			<a href="javascript:;" class="headPre" onclick="back();">이전</a>
			<div class="headTitle">가입 정보</div>
			<!-- <a href="#" class="headMenu">메뉴</a> -->
		</div>
		<div class="container">
			<section>
				<div class="pay_list_info">
					<ul class="typeH">
						<li>
							<dl>
								<dt>
									<label>요금제명</label>
								</dt>
								<dd>
									<span class="tit">${svcNm}</span>
								</dd>
							</dl>
						</li>
					</ul>
					<ul class="typeW">
				<%-- 		<li>
							<dl>
								<dt>가입일</dt>
								<dd>${svcFrstStrtDttm2}</dd>
							</dl>
						</li> --%>
						<li>
							<dl>
								<dt>가입일자</dt>
								<dd>
									<span class="txt-brown">${svcFrstStrtDttm1}</span>
								</dd>
							</dl>
						</li>
					</ul>
				</div>
			</section>
			<section>
				<div class="data_info_view">
					<dl>
						<dt>데이터 사용량</dt>
						<dd>
							<div class="fare-info">
								<div class="data">
									<div class="graph">
										<div class="chart-container"></div>
									</div>
									<!-- 그래프영역 -->
									<ul class="rate">
										<li><span class="tt">남은 데이터</span> <em class="mb"><strong>${remainingValue}</strong>
												MB</em></li>
										<li><span class="tt">월 제공 데이터</span> <em class="mb"><strong>${alloValue}</strong>
												MB</em></li>
									</ul>
								</div>
							</div>
						</dd>
					</dl>
				</div>
			</section>
		</div>
	</div>

</body>
</html>