
async function initializeCheckout() {
  try {
    const originKey =await getRequest("/api/originKey");
    const paymentMethodsResponse = await postRequest("/api/getPaymentMethods");
    const configuration = {
      paymentMethodsResponse: filterUnimplemented(paymentMethodsResponse),
      originKey,
      locale: "en_US",
      environment: "test",
      showPayButton: true,
      paymentMethodsConfiguration: {
        ideal: {
          showImage: true,
        },
        card: {
          hasHolderName: true,
          holderNameRequired: true,
          name: "Credit Card",
          amount: {
            value: 9600,
            currency: "USD",
          },
        }
      },
      onSubmit: (state, component) => {
        if (state.isValid) {
          handleSubmission(state, component, "/api/makePayment");
        }
      },
      onAdditionalDetails: (state, component) => {
      },
    };

    const checkout = new AdyenCheckout(configuration);
    checkout.create('dropin').mount(document.getElementById("payment"));
  } catch (error) {
    console.error(error);
    alert("Error occurred. Look at console for details");
  }
}

function filterUnimplemented(paymentMethods) {
  paymentMethods.paymentMethods = paymentMethods.paymentMethods.filter((it) =>
    [
      "scheme",
    ].includes(it.type)
  );
  return paymentMethods;
}

async function handleSubmission(state, component, url) {
  try {
    const res = await postRequest(url, state.data);
    handleServerResponse(res, component);
  } catch (error) {
    console.error(error);
    alert("Error occurred. Look at console for details");
  }
}

async function postRequest(url, data) {
  const res = await fetch(url, {
    method: "POST",
    body: data ? JSON.stringify(data) : "",
    headers: {
      "Content-Type": "application/json",
    },
  });
  return await res.json();
}
async function getRequest(url) {
  const res = await fetch(url, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  });
  return await res.text();
}

function handleServerResponse(res, component) {
  if (res.action) {
    component.handleAction(res.action);
  } else {
    switch (res.resultCode) {
      case "Authorised":
        window.location.href = "/result.html?result=success";
        break;
      case "Refused":
        window.location.href = "/result.html?result=refused";
        break;
      default:
        window.location.href = "/result.html?result=error";
        break;
    }
  }
}

initializeCheckout();