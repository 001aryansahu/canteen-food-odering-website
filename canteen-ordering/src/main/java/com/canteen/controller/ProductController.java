package com.canteen.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.canteen.model.Product;
import com.canteen.service.ProductService;

// @RestController
// @RequestMapping("/api/products")
// public class ProductController {
//   private final ProductService service;
//   public ProductController(ProductService service) { this.service = service; }

//   @PostMapping(consumes = {"multipart/form-data"})
//     public Product addProduct(
//             @RequestParam("name") String name,
//             @RequestParam("price") double price,
//             @RequestParam("image") MultipartFile file) throws IOException {

    
//         String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//         Path path = Paths.get("uploads/" + fileName);
//         Files.createDirectories(path.getParent());
//         Files.write(path, file.getBytes());

//         Product p = new Product();
//         p.setName(name);
//         p.setPrice(price);
//         p.setImageUrl("/uploads/" + fileName);

//         return service.save(p);}

//   @GetMapping public List<Product> all() { return service.findAll(); }
//   @PostMapping public Product add(@RequestBody Product p) { return service.save(p); }
//   @PutMapping("/{id}") public Product update(@PathVariable Long id, @RequestBody Product p) { return service.update(id, p); }
//   @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.delete(id); }
// }
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service) { this.service = service; }

    // Upload with image
    @PostMapping(consumes = {"multipart/form-data"})
    public Product addProduct(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("image") MultipartFile file) throws IOException {

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get("uploads/" + fileName);
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setImageUrl("/uploads/" + fileName);

        return service.save(p);
    }

    @GetMapping
    public List<Product> all() { return service.findAll(); }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) { 
        return service.update(id, p); 
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
