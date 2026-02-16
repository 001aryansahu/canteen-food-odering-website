// package com.canteen.service;

// import com.razorpay.Order;
// import com.razorpay.RazorpayClient;
// import com.razorpay.RazorpayException;
// import org.json.JSONObject;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// @Service
// public class PaymentService {
//   @Value("${razorpay.key}")
//   private String keyId;

//   @Value("${razorpay.secret}")
//   private String keySecret;

//   public String getKeyId() {
//     return keyId;
//   }

//   public String createOrder(long amountPaise, String receipt) throws RazorpayException {
//     RazorpayClient client = new RazorpayClient(keyId, keySecret);
//     JSONObject req = new JSONObject();
//     req.put("amount", amountPaise);
//     req.put("currency", "INR");
//     req.put("receipt", receipt);
//     Order order = client.orders.create(req);
//     return order.get("id");
//   }
// }
