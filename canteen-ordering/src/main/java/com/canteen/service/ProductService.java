package com.canteen.service;

import com.canteen.model.Product;
import com.canteen.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
  private final ProductRepository repo;
  public ProductService(ProductRepository repo) { this.repo = repo; }

  public List<Product> findAll() { return repo.findAll(); }
  public Product save(Product p) { return repo.save(p); }
  public Product update(Long id, Product dto) {
    Product p = repo.findById(id).orElseThrow(null);
    p.setName(dto.getName());
    p.setPrice(dto.getPrice());
    p.setCategory(dto.getCategory());
    p.setImageUrl(dto.getImageUrl());
    return repo.save(p);
  }
  public void delete(Long id) { repo.deleteById(id); }
}
