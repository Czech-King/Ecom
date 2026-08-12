// package com.example.product.controller;

// import com.example.product.model.Product;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/products")
// public class ProductController {


//     @GetMapping("/{id}")
//     public Product getProduct(@PathVariable int id) {

//         return new Product(
//                 id,
//                 "Laptop",
//                 50000
//         );
//     }
// }

// package com.example.product.controller;


// import org.springframework.web.bind.annotation.*;
// import com.example.product.model.Product;
// import com.example.product.service.ProductService;


// @RestController
// @RequestMapping("/products")
// public class ProductController {


//     private final ProductService productService;


//     public ProductController(ProductService productService){

//         this.productService = productService;

//     }


//     @GetMapping("/{id}")
//     public Product getProduct(@PathVariable int id){

//         return productService.getProduct(id);

//     }

// }

package com.example.product.controller;
import org.springframework.web.bind.annotation.*;
import com.example.product.model.Product;
import com.example.product.service.ProductService;
import com.example.product.dto.InventoryResponse;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
    @GetMapping("/inventory/{productId}")
public InventoryResponse getInventory(@PathVariable Integer productId) {
    return productService.getInventory(productId);
}

//    @GetMapping("/canary")
  //  public String canary() {
    //    return "Product Service - CANARY v2";

//}


}
