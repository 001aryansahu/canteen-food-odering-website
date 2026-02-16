package com.canteen.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OrderTable {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer tokenNumber;
  private String status; // PENDING, PREPARING, COMPLETED
  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderLineItem> items = new ArrayList<>();

  private double totalAmount;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public Integer getTokenNumber() { return tokenNumber; }
  public void setTokenNumber(Integer tokenNumber) { this.tokenNumber = tokenNumber; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
  public LocalDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
  public List<OrderLineItem> getItems() { return items; }
  public void setItems(List<OrderLineItem> items) { this.items = items; }
  public double getTotalAmount() { return totalAmount; }
  public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}
