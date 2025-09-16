package com.example.inventory.controller;

import com.example.inventory.entity.Product;
import com.example.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired private ProductService service;

    @PostMapping public Product create(@Valid @RequestBody Product p) { return service.create(p); }
    @GetMapping public List<Product> getAll() { return service.getAll(); }
    @GetMapping("/{id}") public Product getById(@PathVariable Long id) { return service.getById(id); }
    @PutMapping("/{id}") public Product update(@PathVariable Long id, @RequestBody Product p) { return service.update(id, p); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.delete(id); }
    @GetMapping("/supplier/{supplierId}") public List<Product> getBySupplier(@PathVariable Long supplierId) { return service.getBySupplier(supplierId); }
}
