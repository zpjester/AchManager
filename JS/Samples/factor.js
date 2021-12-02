/**
 * Factor number in result of the computation
 */
function processFactorResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + result);
  var js = JSON.parse(result);

  var listOfFactors = js["list"];
  var status       = js["statusCode"];
  
  var factorOutput = document.getElementById('factorizedResult');
  
  if (status == 200) {
    // 2^20
    var output = "";
    var lastFactor = "";
    var exp = 1;
    var showMultiply = false;
	for (var i = 0; i < listOfFactors.length; i++) {
	    var stringValue = listOfFactors[i];
	    
	    if (stringValue != lastFactor) {
	       if (lastFactor != "") {
	          if (showMultiply) { output = output + " * "; }
	          output = output + lastFactor;
	          if (exp != 1) {
	             output = output + "<sup>" + exp + "</sup>";
	             exp = 1;
	          }
              showMultiply = true;
	       } 
	    } else {
	      exp += 1;
	    }
	    lastFactor = stringValue;
    }
   
    if (showMultiply) { output = output + " * "; }
	output = output + lastFactor;
	if (exp != 1) {
      output = output + "<sup>" + exp + "</sup>";
      exp =1;
    }
    
    factorOutput.innerHTML = output;
    
  } else {
    factorOutput.innerHTML = "That value cannot be factorized!";
  }
}

function handleFactorClick(e) {
  var form = document.addForm;
  var result = form.result.value;

  var data = {};
  data["arg1"] = result;

  var js = JSON.stringify(data);
  console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  xhr.open("POST", factor_url, true);

  // send the collected data as JSON
  xhr.send(js);

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    console.log(xhr);
    console.log(xhr.request);
    
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      processFactorResponse(xhr.responseText);
    } else {
      processFactorResponse("N/A");
    }
  };
}

