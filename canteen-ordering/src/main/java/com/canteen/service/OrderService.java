package com.canteen.service;

import com.canteen.model.*;
import com.canteen.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class OrderService {
  private final OrderRepository orderRepo;
  private final NotificationService notificationService;

  private int tokenCounter = 0;
  private LocalDate tokenDate = LocalDate.now();

  public OrderService(OrderRepository orderRepo, NotificationService notificationService) {
    this.orderRepo = orderRepo;
    this.notificationService = notificationService;
  }

  private int nextToken() {
    LocalDate today = LocalDate.now();
    if (!today.equals(tokenDate)) {
      tokenDate = today;
      tokenCounter = 0;
    }
    tokenCounter++;
    return tokenCounter;
  }

  public OrderTable createFromCart(Cart cart, String paymentId, String paymentStatus) {
    if (paymentStatus == null || !paymentStatus.equalsIgnoreCase("SUCCESS")) {
      throw new RuntimeException("Payment not successful");
    }
    OrderTable order = new OrderTable();
    order.setStatus("PENDING");
    order.setCreatedAt(LocalDateTime.now());
    order.setTokenNumber(nextToken());

    double total = 0.0;
    for (CartItem ci : cart.getItems()) {
      OrderLineItem oli = new OrderLineItem();
      oli.setOrder(order);
      oli.setProductId(ci.getProduct().getId());
      oli.setProductName(ci.getProduct().getName());
      oli.setUnitPrice(ci.getProduct().getPrice());
      oli.setQuantity(ci.getQuantity());
      order.getItems().add(oli);
      total += ci.getProduct().getPrice() * ci.getQuantity();
    }
    order.setTotalAmount(total);

    return orderRepo.save(order);
  }

  public OrderTable updateStatus(Long orderId, String status) {
    OrderTable order = orderRepo.findById(orderId).orElseThrow(null);
    order.setStatus(status);
    OrderTable saved = orderRepo.save(order);
    if ("COMPLETED".equalsIgnoreCase(status)) {
      notificationService.sendOrderCompleted(order.getTokenNumber());
    }
    return saved;
  }
}
