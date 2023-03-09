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
   var payResvYn = "<c:out value='${payResvYn}'/>"; //요금제예약신청

   $(function() {
      if ("Y" == payResvYn) {
         // android.showAlreadyAlert();
      }
   });

   function showAlertApplyPlan() {
      if ("Y" == payResvYn) {
         // android.showAlreadyAlert();
         $('#confirm').show();
         $("#ok").hide();
		 $("#cancel").hide();         
    	 showInfoInputAlert();         
         return;
      }

      var val = $("#check").is(":checked");

      if (!val) {
         // android.showToast("사용 연장 및 유료 이용에 동의가 필요합니다.");
         $('#confirm').show();
         $("#ok").hide();
		 $("#cancel").hide();
			 showErrorInputAlert();
         return;
         
      }else if(val){
    	  $('#confirm').hide();
          $("#ok").show();
 		$("#cancel").show();
           
    	  showOkInputAlert();
      }
      // android.showAlertApplyPlan();
      
   }
   
  function showErrorInputAlert() {
		showAlert("커넥티드카 매월 정기결제 동의가 필요합니다.");
	} 
   
   function showOkInputAlert() {
		showAlert("매월 정기 결제를 신청할까요?");
	}
   
   function showInfoInputAlert() {
		showAlert("이미 신청 완료한 요금제가 있습니다.");
	}   

	function showAlert(title, message) {
		$(".dim").show();
		$("#alert").css("display", "block");
		$("#title").css("display", "block");
		$("#title").text(title);
		$("#message").html(message);
		$("#confirm").click(function() {
			$(".dim").hide();
			$("#alert").css("display", "none");
			$("#title").css("display", "none");
		});
	} 
	
	function showAlert(message) {
		$(".dim").show(); 
		$("#alert").css("display", "block");
		$("#title").css("display", "none");
		$("#message").html(message);
		$("#cancel").click(function() {
			$(".dim").hide();
			$("#alert").css("display", "none");
			$("#title").css("display", "none");
		});
		$("#ok").click(function() {
			$(".dim").hide();
			$("#alert").css("display", "none");
			$("#title").css("display", "none");
			location.href = "limitEnterBillInfo.do";
		});
		$("#confirm").click(function() {
			$(".dim").hide();
			$("#alert").css("display", "none");
			$("#title").css("display", "none");
		});
	}
   
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
         <div class="headTitle">요금제 신청</div>
         <!-- <a href="#" class="headMenu">메뉴</a> -->
      </div>
      <div class="container">
         <section>
            <div class="info_view_inner3">
               <strong> 현재 커넥티드카 데이터 10GB 요금제를 사용 중입니다.<br /> 무료 이용기간이 끝나는
                  ${svcEndDttm1} 전에 매월 정기 결제를 신청 해 주세요.<!-- 월정액 요금제 2로 변경해 주세요. -->
               </strong>
            </div>
         </section>

         <section>
            <div class="data_info_view">
               <dl>
                  <dt>상품 설명</dt>
                  <dd>
                     <p class="subTit">
                        <span class="tit">매월 13,200원</span> (부가세 포함금액)
                     </p>
                     <div class="t-grid mt10 mb10">
                        <table>
                           <caption>월정약상품설명</caption>
                           <colgroup>
                              <col style="width: 40%">
                              <col style="width: auto">
                           </colgroup>
                           <tbody>
                              <tr>
                                 <th>데이터 기본 제공량</th>
                                 <td>10GB</td>
                              </tr>
                              <tr>
                                 <th rowspan="3">초과사용 시</th>
                                 <td>0~880MB 미만</td>
                              </tr>
                              <tr>
                                 <td>880MB~3GB 미만</td>
                              </tr>
                              <tr>
                                 <td>3GB 이상</td>
                              </tr>
                              <tr>
                                 <th rowspan="3">추가요금</th>
                                 <td>0.011원/0.5KB</td>
                              </tr>
                              <tr>
                                 <td>19,800원 정액 요금</td>
                              </tr>
                              <tr>
                                 <td>최대 200kbps 속도로 무제한 사용 가능</td>
                              </tr>
                           </tbody>
                        </table>
                     </div>

                     <div class="info_view_inner2">
                        <ul class="type1">
                           <!-- <li>커넥티드카 서비스 가입자는 커넥티드카 할인을 통해 월 13,200원에 이용하실 수 있습니다. <br />(지니뮤직
                              마음껏 듣기 포함)
                           </li> -->
                           <li>매월 정기결제를 신청하지 않으면 무료 이용기간 종료 후 자동 해지됩니다<br />(종료 예정일 :
                              ${svcEndDttm2})
                           </li>
                        </ul>
                        <p class="mt10 mb10">
                           <span class="chk-mgr"> <input type="checkbox" id="check">
                              <label for="check2">커넥티드카 매월 정기 결제 신청 동의</label>
                           </span>
                        </p>
                     </div>
                  </dd>
               </dl>
            </div>
         </section>

         <div class="footerBtnArea">
            <a href="javascript:;" class="footer_btn color2" onclick="back();">취소하기</a>
            <a href="javascript:;" class="footer_btn color1"
               onclick="showAlertApplyPlan();">신청하기</a>
         </div>
		<!-- Alert -->
			<div id="alert" class="popup2" style="display: none;">
				<div class="popup_inner2">
					<p id="title" class="title"></p>
					<!-- 타이틀 없을경우 삭제 -->
					<div id="message" class="info"></div>
				</div>
				<div class="popup_btnArea">
					<!-- 버튼 2개 -->
					<a id="cancel" href="javascript:;" class="btn2 color1 duo">아니오</a> 
					 <a id="ok" href="javascript:;" class="btn2 color2 duo">네</a>
					<!-- 버튼 1개 -->
					 <a id="confirm" href="javascript:;" class="btn2">확인</a> 
				</div>
			</div>
      </div>
   </div>

</body>
</html>