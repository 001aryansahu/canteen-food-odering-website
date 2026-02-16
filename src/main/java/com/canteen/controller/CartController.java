package com.canteen.controller;

import com.canteen.model.Cart;
import com.canteen.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
  private final CartService service;
  public CartController(CartService service) { this.service = service; }

  @PostMapping("/create")
  public Cart create() { return service.createCart(); }

  @PostMapping("/{cartId}/add")
  public Cart add(@PathVariable Long cartId,
                  @RequestParam Long productId,
                  @RequestParam int qty) {
    return service.addToCart(cartId, productId, qty);
  }

  @GetMapping("/{cartId}")
  public Cart get(@PathVariable Long cartId) { return service.getCart(cartId); }
}
