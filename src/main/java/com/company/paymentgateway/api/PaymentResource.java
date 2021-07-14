package com.company.paymentgateway.api;

import com.adyen.Client;
import com.adyen.enums.Environment;
import com.adyen.model.Amount;
import com.adyen.model.checkout.*;
import com.adyen.model.checkout.PaymentMethodsRequest.ChannelEnum;
import com.adyen.service.Checkout;
import com.adyen.service.exception.ApiException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class PaymentResource {

  private final static Logger log = LoggerFactory.getLogger(PaymentResource.class);
  private static String URL = "http://localhost:8080";

  @Value("${ADYEN_MERCHANT_ACCOUNT}")
  private String merchantAccount;
  @Value("${ADYEN_ORIGIN_KEY}")
  private String originKey;


  private final Checkout checkout;

  public PaymentResource(@Value("${ADYEN_API_KEY}") String apiKey) {
    Client client = new Client(apiKey, Environment.TEST);
    this.checkout = new Checkout(client);
  }


  @GetMapping("/originKey")
  public String getOriginKey() {
    return originKey;
  }

  @PostMapping("/getPaymentMethods")
  public ResponseEntity<PaymentMethodsResponse> paymentMethods() throws IOException, ApiException {

    PaymentMethodsRequest paymentMethodsRequest = new PaymentMethodsRequest();
    paymentMethodsRequest.setMerchantAccount(merchantAccount);
    paymentMethodsRequest.setChannel(ChannelEnum.WEB);
    PaymentMethodsResponse paymentMethodsResponse = this.checkout
            .paymentMethods(paymentMethodsRequest);

    return ResponseEntity.ok(paymentMethodsResponse);
  }


  @PostMapping("/makePayment")
  public ResponseEntity<PaymentsResponse> payments(@RequestBody PaymentsRequest body)
          throws IOException, ApiException {
    PaymentsRequest paymentRequest = new PaymentsRequest();
    paymentRequest.setMerchantAccount(merchantAccount);
    paymentRequest.setChannel(PaymentsRequest.ChannelEnum.WEB);

    Amount amount = new Amount()
            .currency("USD")
            .value(9600L);
    paymentRequest.setAmount(amount);

    String orderRef = UUID.randomUUID().toString();
    paymentRequest.setReference(orderRef);
    paymentRequest.setOrigin(URL);
    paymentRequest.setPaymentMethod(body.getPaymentMethod());

    log.info("REST request to make payment {}", paymentRequest);
    PaymentsResponse response = checkout.payments(paymentRequest);
    return ResponseEntity.ok()
            .body(response);
  }

}