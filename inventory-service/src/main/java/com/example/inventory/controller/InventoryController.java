package com.example.inventory.controller;

import com.example.inventory.model.Inventory;
import com.example.inventory.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public Inventory saveInventory(@RequestBody Inventory inventory) {
        return inventoryService.saveInventory(inventory);
    }

    @GetMapping("/{productId}")
    public Inventory getInventoryByProductId(@PathVariable Integer productId) {
        return inventoryService.getInventoryByProductId(productId);
    }
}