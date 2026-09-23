```java
package com.example.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.product.dto.InventoryResponse;
import com.example.product.model.Product;
import com.example.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/inventory/{productId}")
    public InventoryResponse getInventory(@PathVariable Integer productId) {
        return productService.getInventory(productId);
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
}
```

