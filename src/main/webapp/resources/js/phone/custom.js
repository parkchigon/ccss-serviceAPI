var $=jQuery.noConflict();

$(function(){
	
	/* layer popup */
	var layerId = $('.dropdown-container');
	var winH = $(window).height();
	var winW = $(window).width();
	var scrollT = $(window).scrollTop();
	// alert(scrollT);

	$(layerId).css('top', winH/2-$(layerId).height()/2 + scrollT);
	$(layerId).css('left', winW/2-$(layerId).width()/2);


	/* layer popup */
	var popupId = $('.popup2');
	var winH = $(window).height();
	var winW = $(window).width();
	var scrollT = $(window).scrollTop();
	// alert(scrollT);

	$(popupId).css('top', winH/2-$(popupId).height()/2 + scrollT);
	$(popupId).css('left', winW/2-$(popupId).width()/2);
	
	/* progress */
    var progressId = $('.progress');
    
    $(progressId).css('top', winH/2-$(progressId).height()/2 + scrollT);
    $(progressId).css('left', winW/2-$(progressId).width()/2);
    
    /* dropdown popup */
    $('.select-box').on('click', function(e){
        e.preventDefault();
        var selBox = $(this);
        var path = $(this).attr('href');
        var dropdown = $(path).find('ul li');
        
        $('.select-box2').removeClass('focus');
        $(this).addClass('focus');
        $(path).show();
        $('.dim').show();        
        
        dropdown.each(function(){
            $(this).each(function(){
                $(this).find('a').on('click', function(){
                    var txt = $(this).text();
                    $(this).parent().addClass('active').siblings().removeClass('active');
                    selBox.text(txt);
                    $(path).hide();
                    $('.dim').hide();
                });
            });
        });
        $('input').on('focus', function(){
            selBox.removeClass('focus');
        });
        $('.dim').on('click', function(){
            $('.dropdown-container').hide();
            $('.dim').hide();
        });
    });
 });

var isMobile = {
        Android: function () {
                 return navigator.userAgent.match(/Android/i);
        },
        BlackBerry: function () {
                 return navigator.userAgent.match(/BlackBerry/i);
        },
        iOS: function () {
                 return navigator.userAgent.match(/iPhone|iPad|iPod/i);
        },
        Opera: function () {
                 return navigator.userAgent.match(/Opera Mini/i);
        },
        Windows: function () {
                 return navigator.userAgent.match(/IEMobile/i);
        },
        any: function () {
                 return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
        }
};

// function showProgressDialog() {
// if (isMobile.iOS()) {
// var message = {
// "action" : "call",
// "function" : "showProgressDialog"
// }
// try {
// webkit.messageHandlers.callbackHandler.postMessage(message);
// } catch (err) {
// alert(err);
// }
// } else if (isMobile.Android()) {
// android.showProgressDialog();
// }
// }
//
// function dismissProgressDialog() {
// if (isMobile.iOS()) {
// var message = {
// "action" : "call",
// "function" : "dismissProgressDialog"
// }
// try {
// webkit.messageHandlers.callbackHandler.postMessage(message);
// } catch (err) {
// alert(err);
// }
// } else if (isMobile.Android()) {
// android.dismissProgressDialog();
// }
// }
//
// function showAlert(title, message) {
// if (isMobile.iOS()) {
// var message = {
// "action" : "call",
// "function" : "showAlert",
// "title" : title,
// "message" : message,
// }
// try {
// webkit.messageHandlers.callbackHandler.postMessage(message);
// } catch (err) {
// alert(err);
// }
// } else if (isMobile.Android()) {
// android.showAlert(title, message);
// }
// }

function showToast(message) {
	if (isMobile.iOS()) {
		var message = {
			"action" : "call",
			"function" : "showToast",
			"message" : message,
		}
		try {
			webkit.messageHandlers.callbackHandler.postMessage(message);
		} catch (err) {
			alert(err);
		}
	} else if (isMobile.Android()) {
		android.showToast(message);
	}
}

function closed() {
	if (isMobile.iOS()) {
		var message = {
			"action" : "call",
			"function" : "webviewFinish"
		}
		try {
			webkit.messageHandlers.callbackHandler.postMessage(message);
		} catch (err) {
			alert(err);
		}
	} else if (isMobile.Android()) {
		android.webviewFinish();
	}
}
 
//KMC 인증 페이지 닫기 
function popupFinish() {
	if (isMobile.iOS()) {
		var message = {
			"action" : "call",
			"function" : "popupFinish"
		}
		try {
			webkit.messageHandlers.callbackHandler.postMessage(message);
		} catch (err) {
			alert(err);
		}
	} else if (isMobile.Android()) {
		android.popupFinish();
	}
}

//핸드폰 인증
function	callNative(tp, ci, di, pn, nm, bd) {
	if (isMobile.iOS()) {
		var message = {
			"action" : "call",
			"function" : "callNative",
			"tp" : tp,
			"ci":ci,
			"di": di,
			"pn":pn,
			"nm":nm,
			"bd":bd
		}
		try {
			webkit.messageHandlers.callbackHandler.postMessage(message);
		} catch (err) {
			alert(err);
		}
	} else if (isMobile.Android()) {
		//window.location.href = "ccs://callNative?tp="+tp +"&ci="+ci + "&di="+ di+"&pn="+pn +"&nm="+nm+"&bd="+bd;
		android.callNative(tp, ci, di, pn, nm, bd);
	}
}

