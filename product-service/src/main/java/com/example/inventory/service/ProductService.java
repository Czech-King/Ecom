package com.example.product.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.product.dto.InventoryResponse;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;
    private final MeterRegistry meterRegistry;

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    public ProductService(ProductRepository productRepository,
                          RestTemplate restTemplate,
                          MeterRegistry meterRegistry) {

        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
        this.meterRegistry = meterRegistry;
    }

    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public Product saveProduct(Product product) {

        Product savedProduct = productRepository.save(product);

        Counter.builder("products_created_total")
                .description("Total successfully created products")
                .tag("source", product.getSource())
                .register(meterRegistry)
                .increment();

        System.out.println("Counter incremented for source = " + product.getSource());

        return savedProduct;
    }

    @CircuitBreaker(name = "inventoryCircuit")
    @Retry(name = "inventoryRetry")
    public InventoryResponse getInventory(Integer productId) {

        System.out.println("Calling Inventory Service...");

        String url = inventoryServiceUrl + "/inventory/" + productId;

        return restTemplate.getForObject(url, InventoryResponse.class);
    }
}