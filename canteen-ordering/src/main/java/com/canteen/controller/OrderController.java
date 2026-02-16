package com.canteen.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.canteen.model.Cart;
import com.canteen.model.OrderTable;
import com.canteen.repository.OrderRepository;
import com.canteen.service.CartService;
import com.canteen.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
  private final CartService cartService;
  private final OrderService orderService;
  private final OrderRepository orderRepo;

  public OrderController(CartService cartService, OrderService orderService, OrderRepository orderRepo) {
    this.cartService = cartService;
    this.orderService = orderService;
    this.orderRepo = orderRepo;
  }

  // Create Order from Cart after payment success
  @PostMapping("/create/{cartId}")
  public OrderTable create(@PathVariable Long cartId,
                           @RequestParam String paymentId,
                           @RequestParam(defaultValue = "SUCCESS") String status) {
    Cart cart = cartService.getCart(cartId);
    OrderTable order = orderService.createFromCart(cart, paymentId, status);
    cartService.clearCart(cart);
    return order;
  }

  @GetMapping
  public List<OrderTable> all() { return orderRepo.findAll(); }

  // Admin marks status (PENDING / PREPARING / COMPLETED)
  @PutMapping("/{orderId}/status")
  public OrderTable updateStatus(@PathVariable Long orderId, @RequestParam String status) {
    return orderService.updateStatus(orderId, status);
  }

  @GetMapping("/{orderId}")
  public OrderTable get(@PathVariable Long orderId) { return orderRepo.findById(orderId).orElseThrow(null); }
}
