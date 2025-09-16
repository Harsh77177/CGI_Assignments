package com.example.inventory.controller;

import com.example.inventory.entity.Supplier;
import com.example.inventory.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @Autowired private SupplierService service;

    @PostMapping public Supplier create(@Valid @RequestBody Supplier s) { return service.create(s); }
    @GetMapping public List<Supplier> getAll() { return service.getAll(); }
    @GetMapping("/{id}") public Supplier getById(@PathVariable Long id) { return service.getById(id); }
    @PutMapping("/{id}") public Supplier update(@PathVariable Long id, @RequestBody Supplier s) { return service.update(id, s); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.delete(id); }
}
