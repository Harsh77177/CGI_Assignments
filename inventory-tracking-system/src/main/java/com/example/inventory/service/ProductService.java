package com.example.inventory.service;

import com.example.inventory.entity.Product;
import com.example.inventory.exception.ResourceNotFoundException;
import com.example.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired private ProductRepository repo;

    public Product create(Product product) { return repo.save(product); }
    public List<Product> getAll() { return repo.findAll(); }
    public Product getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
    public Product update(Long id, Product updated) {
        Product p = getById(id);
        p.setName(updated.getName());
        p.setDescription(updated.getDescription());
        p.setQuantity(updated.getQuantity());
        p.setPrice(updated.getPrice());
        p.setSupplier(updated.getSupplier());
        return repo.save(p);
    }
    public void delete(Long id) { repo.delete(getById(id)); }
    public List<Product> getBySupplier(Long supplierId) {
        return repo.findBySupplierSupplierId(supplierId);
    }
}
