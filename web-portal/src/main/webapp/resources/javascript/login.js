history.pushState(null, null, "");
window.addEventListener('popstate', function() {
	history.pushState(null, null, "");
});

/*-----------*/

window.dataLayer = window.dataLayer || [];

function gtag() { dataLayer.push(arguments); }

gtag('js', new Date());
gtag('config', 'UA-74242241-3');

/*-----------*/

function emptyCheck() {

	var str1 = removeSpaces(document.getElementById('txtCompare1').value);

	var str2 = removeSpaces(document.getElementById('txtCompare2').value);

	if (str2 == null && str2 == '') {
		alert("please enter Security Code");
		document.getElementById("txtCompare2").focus();
		return false;
	}
}
/* Validating Captcha Function */
function ValidCaptcha() {
	//alert("captcha matches1212121212:");
	var str1 = removeSpaces(document.getElementById('txtCompare1').value);

	var str2 = removeSpaces(document.getElementById('txtCompare2').value);

	if (str2 == null && str2 == '') {
		alert("please enter Security Code");
		//document.getElementById("txtCompare2").focus();
		return false;
	}

	else if (str2 == null || str2 != str1) {
		alert("Wrong Security Code ");
		/* document.getElementById("txtCompare2").value = '';
		document.getElementById("txtCompare2").focus(); */
		return false;
	} else if (str1 == str2) {
		//alert("Captcha Matches:");
		return true;
	}
}

/* Remove spaces from Captcha Code */
function removeSpaces(string) {
	return string.split(' ').join('');
}

function test() {
	$('#txtCompare1').bind("cut copy paste", function(e) {
		e.preventDefault();
		alert("cut,copy & paste not allowed");
	});

	$('#CaptchaDiv').bind("cut copy paste", function(e) {
		e.preventDefault();
		alert("cut,copy & paste not allowed");
	});

}

/*-----------------*/

function submitform() {
	document.formId.submit();
}

/*-----------------*/

function submitDetailsForm() {
	//alert("submit logout form");
	document.getElementById("formId").submit();
}



/*----------------------*/


function newlogin() {

	// alert("submit logout form");
	/*   var tt = ValidateCaptcha(); */
	return true;
}

/*-----------------*/

function forgotPwd() {

	window.location = "${pageContext.request.contextPath}/forgotpwd/forgotPwdByUser";
	form.submit;
	return true;

};

/*-----------------------*/

function onclickTab(e) {
	var KeyID = e.keyCode;
	if (KeyID == 9) {

		txtCompare2.focus();
		return false;
	}
}

/*---------------------*/


const username = document.getElementById("username")
const password = document.getElementById("password")


document.addEventListener('input', (evt) => {
	if (username.value !== '' && password.value !== '') {
		document.getElementById("verification-box").style.display = "block";
		document.getElementById("btnValid").style.display = "none";

		$('#verification-box').attr(
			'disabled', false);
		$('#swipeen').attr(
			'disabled', false);

		document.getElementById('pswitch').classList.remove('pullee-slider-text');
		document.getElementById('swipeen').classList.remove('pullee-readyonly');



	} else {
		//  document.getElementById("verification-box").style.display = "none"
		document.getElementById("btnValid").style.display = "none";
		document.getElementById("verification-box").style.display = "block";

		$('#verification-box').attr(
			'disabled', true);

		$('#swipeen').attr(
			'disabled', true);


		document.getElementById('pswitch').classList.add('pullee-slider-text');
		document.getElementById('swipeen').classList.add('pullee-readyonly');



	}
});

/*--------------------*/

var inputRange = document.getElementsByClassName('pullee')[0],
	maxValue = 150, // the higher the smoother when dragging
	speed = 12, // thanks to @pixelass for this
	currValue, rafID;

// set min/max value
inputRange.min = 0;
inputRange.max = maxValue;

// listen for unlock
function unlockStartHandler() {
	// clear raf if trying again
	window.cancelAnimationFrame(rafID);

	// set to desired value
	currValue = +this.value;
}

function unlockEndHandler() {

	// store current value
	currValue = +this.value;

	// determine if we have reached success or not
	if (currValue >= maxValue) {
		successHandler();
	}
	else {
		rafID = window.requestAnimationFrame(animateHandler);
	}
}

// handle range animation
function animateHandler() {

	// update input range
	inputRange.value = currValue;

	// determine if we need to continue
	if (currValue > -1) {
		window.requestAnimationFrame(animateHandler);
	}

	// decrement value
	currValue = currValue - speed;
}

// handle successful unlock
function successHandler() {
	// alert('unlocked');
	document.getElementById("btnValid").style.display = "block"
	document.getElementById("verification-box").style.display = "none"

	// reset input range
	inputRange.value = 0;
};

// bind events
inputRange.addEventListener('mousedown', unlockStartHandler, false);
inputRange.addEventListener('mousestart', unlockStartHandler, false);
inputRange.addEventListener('mouseup', unlockEndHandler, false);
inputRange.addEventListener('touchend', unlockEndHandler, false);



