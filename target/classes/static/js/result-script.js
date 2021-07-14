window.onload = function(){
    var urlParams = new URLSearchParams(window.location.search);
    var type = urlParams.get('result');
    document.getElementById('title').innerHTML = type.toUpperCase();
    document.getElementById('message').innerHTML =getResult(type);
 };

function getResult(type) {
  var response='';
  switch (type) {
      case "success":
        response='<span style="color:green">Your order has been successfully placed</span>';
        break;
      case "failed":
        response='<span style="color:red">The payment was refused. Please try a different payment method or card.</span>';
        break;
      default:
        response='<span style="color:red">Error! Please review response in console and refer to <a href="https://docs.adyen.com/development-resources/response-handling">Response handling.</a></span>';
        break;
  }
  return response;
}