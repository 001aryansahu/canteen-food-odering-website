package com.canteen.service;

import com.canteen.model.*;
import com.canteen.repository.*;
import org.springframework.stereotype.Service;

@Service
public class CartService {
  private final CartRepository cartRepo;
  private final ProductRepository productRepo;

  public CartService(CartRepository cartRepo, ProductRepository productRepo) {
    this.cartRepo = cartRepo;
    this.productRepo = productRepo;
  }

  public Cart createCart() { return cartRepo.save(new Cart()); }

  public Cart addToCart(Long cartId, Long productId, int qty) {
    Cart cart = cartRepo.findById(cartId).orElseThrow(null);
    Product product = productRepo.findById(productId).orElseThrow(null);

    CartItem item = new CartItem();
    item.setCart(cart);
    item.setProduct(product);
    item.setQuantity(qty);

    cart.getItems().add(item);
    return cartRepo.save(cart);
  }

  public Cart getCart(Long cartId) { return cartRepo.findById(cartId).orElseThrow(null);}
  public void clearCart(Cart cart) { cart.getItems().clear(); cartRepo.save(cart); }
}
