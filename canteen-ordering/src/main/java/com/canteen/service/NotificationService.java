package com.canteen.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
  private final SimpMessagingTemplate template;
  public NotificationService(SimpMessagingTemplate template) { this.template = template; }

  public void sendOrderCompleted(int tokenNumber) {
    // Dedicated topic per token
    template.convertAndSend("/topic/order-status/" + tokenNumber,
      "Your order is ready! Please collect: Token #" + tokenNumber);
  }
}
