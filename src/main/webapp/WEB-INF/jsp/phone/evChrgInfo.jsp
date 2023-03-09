<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
<title>LGU+ Connected Car</title>
<link rel="stylesheet" href="../../css/phone/common.css">
<link rel="stylesheet" href="../../css/phone/style.css">

<script type="text/javascript" src="../../js/phone/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="../../js/phone/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../js/phone/custom.js"></script>
<script type="text/javascript">
	$(function() {
		var status = parseFloat("<c:out value='${ev.chrgStatus}'/>");

		if(status == 1) {
			$("#st").text("충전 중");
			$("#recharge").children('img').attr('src', "../../images/phone/charging_complete.png");
		} else {
			$("#st").text("충전 상태")
			$("#recharge").children('img').attr('src', "../../images/phone/charging_require.png");
		}
		
		document.getElementById('chrgbar').style.width = "<c:out value='${ev.batteryCapa}'/>%";	
	});

	function back() {
		closed();
	}

</script>
</head>
<body>
<div class="page page-scroll">
    <div class="pageHead fixed">
    	<a href="javascript:;" class="headPre" onclick="back();">이전</a>
    	<div class="headTitle">충전상태</div>
    	<!-- <a href="#" class="headMenu">메뉴</a> -->
    </div>
    <div class="container">
         <section>
			<div class="charge-state">
				<div class="refresh">최종 업데이트 : ${ev.lastUpdDt} <a href="/mypage/ev/evChrgInfo.do" class="btn_refresh"></a></div>
				<div class="charge-info">
					<span class="car_name">닛산 리프</span>
					<div class="car_img">
						<img src="../../images/phone/charge_nissan_sample.png" alt="닛산 자동차">
					</div>
					<span id="recharge" class="charging"><img src="" alt="충전"></span>
					<div class="charge">	
						<div class="charge_txt">
							<strong id="st" class="gtxt_left">
								충전상태 
							</strong>
							<ul class="gtxt_right">
								<li><span class="num">${ev.batteryCapa}</span><span class="unit">%</span></li>
								<li><span class="num">${ev.driveAbleDistAirNuse}</span><span class="unit">km</span></li>
							</ul>
						</div>
						<div class="graph_wrap">
							<span id="chrgbar" class="graph"></span><!-- 충전상태만큼 %로 늘어남 -->
						</div>
					</div>
				</div>
			</div>	
        </section>
		
		<!-- 주행가능거리 -->
		<section>
			<div class="data_info_view">
				<dl>
					<dt><strong>주행가능거리</strong></dt>
					<dd>
						<div class="d-grid">
							<table>
								<caption>주행가능거리 정보</caption>
								<colgroup>
									<col style="width:55%">
									<col style="width:auto">
								</colgroup>
								<tbody>
									<tr>
										<th class="has-icon air_no">공조 미 사용 시</th>
										<td class="pay"><span>${ev.driveAbleDistAirNuse}</span><span class="unit"> km</span></td>
									</tr>
									<tr>
										<th class="has-icon air_ok">공조 사용 시</th>
										<td class="pay"><span>${ev.driveAbleDistAirUse}</span><span class="unit"> km</span></td>
									</tr>
								</tbody>
							</table>
						</div>
					</dd>
				</dl>
			</div>
        </section>
		<!-- //주행가능거리 -->

		<!-- 예상충전시간 -->
		<section>
			<div class="data_info_view">
				<dl>
					<dt><strong>예상충전시간</strong></dt>
					<dd>
						<div class="d-grid">
							<table>
								<caption>예상충전시간 정보</caption>
								<colgroup>
									<col style="width:45%">
									<col style="width:auto">
								</colgroup>
								<tbody>
									<tr id=tm1>
										<th class="has-icon time_3">50 Kw</th><!-- 시간에 따라 아이콘뷰 다름. 1,2 케이스 아이콘 필요 -->
										<td class="pay"><span>${ev.expectChrgTm3}</span></td>									
									</tr>
									<tr id=tm2>
										<th class="has-icon time_2">6.6 Kw</th><!-- 시간에 따라 아이콘뷰 다름. 1,2 케이스 아이콘 필요 -->
										<td class="pay"><span>${ev.expectChrgTm1}</span></td>
									</tr>
									<tr id=tm3>
										<th class="has-icon time_1">3.6 Kw</th><!-- 시간에 따라 아이콘뷰 다름. 1,2 케이스 아이콘 필요 -->
										<td class="pay"><span>${ev.expectChrgTm2}</span></td>
									</tr>
								</tbody>
							</table>
						</div>
					</dd>
				</dl>
			</div>
        </section>
		<!-- //예상충전시간 -->

    </div>
</div>

</body>
</html>