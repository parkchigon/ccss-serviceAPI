<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String resultMsg = (String)request.getAttribute("RESULT_MESSAGE");
	String resultType = (String)request.getAttribute("RESULT_TYPE");
	String carNumber = (String)request.getAttribute("carNumber");
	String devType = (String)request.getAttribute("devType");
%>
<html>

<title>LGU+ Connected Car</title>
<link rel="stylesheet" href="../../css/phone/common.css">
<link rel="stylesheet" href="../../css/phone/style.css">

<script type="text/javascript" src="../../js/phone/jquery-1.10.1.js"></script>
<script type="text/javascript" src="../../js/phone/enscroll-0.6.1.min.js"></script>
<script type="text/javascript" src="../../js/phone/custom.js"></script>

<head>
	<script language="javascript">
	
	function goHistory(){
		var $form = $('<form></form>');
		$form.attr('action', 'history.do');
		$form.attr('method', 'post');
		$form.appendTo('body');
		
		var carNumber  = $('<input type="hidden" value="<c:out value="${carNumber}" />" name="carNumber">');
		var devType = $('<input type="hidden" value="<c:out value="${devType}" />" name="devType">');
		
		$form.append(carNumber);
		$form.append(devType);
		
		$form.submit();
	}		
	
	</script>
</head>
<body onload="goHistory();">

<!-- 
* resultType=[<%=resultType%>]<br>
* resultMsg=[<%=resultMsg%>]<br>
* carNumber=[<%=carNumber%>]<br>
* devType=[<%=devType%>]<br>
 -->
 
<!-- 
<br>
<br>

========== DEBUG ========= <br>
resultMsg = <%=resultMsg%>  		<br>
resultType = <%=resultType%>  		<br>
carNumber = <c:out value="${carNumber}" />  		<br>
canBuyGift = <c:out value="${canBuyGift}" /> 		<br>
giftName = <c:out value="${giftName}" />  			<br>
dataSize = <c:out value="${dataSize}" />  			<br>
sellAmount = <c:out value="${sellAmount}" />  		<br>
issueNo = <c:out value="${issueNo}" />  			<br>
giftKub = <c:out value="${giftKub}" />  			<br>
issueName = <c:out value="${issueName}" />  		<br>
campStartDate = <c:out value="${campStartDate}" />  <br>
campEndDate = <c:out value="${campEndDate}" />  	<br>
canBuyGift = <c:out value="${canBuyGift}" /> 		<br>
LGD_BUYER = <c:out value="${LGD_BUYER}" />  		<br>
LGD_BUYER_PHONENUM = <c:out value="${LGD_BUYER_PHONENUM}" />  	<br>
LGD_BUYEREMAIL = <c:out value="${LGD_BUYEREMAIL}" />  			<br>
LGD_CUSTOM_FIRSTPAY = <c:out value="${LGD_CUSTOM_FIRSTPAY}" />  <br>
========================== <br>	
-->
 		
</body>
</html>