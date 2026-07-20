package com.example.inventory.service;

import com.example.inventory.model.Inventory;
import com.example.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public Inventory saveInventory(Inventory inventory) {
        return repository.save(inventory);
    }

    public Inventory getInventoryByProductId(Integer productId) {
    return repository.findByProductId(productId);
}
}