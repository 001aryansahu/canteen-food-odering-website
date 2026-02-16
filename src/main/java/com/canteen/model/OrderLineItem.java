package com.canteen.model;

import jakarta.persistence.*;

@Entity
public class OrderLineItem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  private OrderTable order;

  private Long productId;
  private String productName;
  private double unitPrice;
  private int quantity;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public OrderTable getOrder() { return order; }
  public void setOrder(OrderTable order) { this.order = order; }
  public Long getProductId() { return productId; }
  public void setProductId(Long productId) { this.productId = productId; }
  public String getProductName() { return productName; }
  public void setProductName(String productName) { this.productName = productName; }
  public double getUnitPrice() { return unitPrice; }
  public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
  public int getQuantity() { return quantity; }
  public void setQuantity(int quantity) { this.quantity = quantity; }
}
