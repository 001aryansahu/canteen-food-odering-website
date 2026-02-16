// package com.canteen.controller;

// import com.canteen.service.PaymentService;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/payments")
// public class PaymentController {
//   private final PaymentService payService;
//   public PaymentController(PaymentService ps){ this.payService = ps; }

//   @GetMapping("/key")
//   public String key(){ return payService.getKeyId(); }

//   @PostMapping("/create")
//   public String create(@RequestParam long amountPaise, @RequestParam String receipt) throws Exception {
//     return payService.createOrder(amountPaise, receipt);
//   }
// }
