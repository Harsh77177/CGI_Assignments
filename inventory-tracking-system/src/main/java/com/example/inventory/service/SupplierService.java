package com.example.inventory.service;

import com.example.inventory.entity.Supplier;
import com.example.inventory.exception.ResourceNotFoundException;
import com.example.inventory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupplierService {
    @Autowired private SupplierRepository repo;

    public Supplier create(Supplier s) { return repo.save(s); }
    public List<Supplier> getAll() { return repo.findAll(); }
    public Supplier getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
    }
    public Supplier update(Long id, Supplier updated) {
        Supplier s = getById(id);
        s.setName(updated.getName());
        s.setEmail(updated.getEmail());
        s.setPhoneNumber(updated.getPhoneNumber());
        s.setAddress(updated.getAddress());
        return repo.save(s);
    }
    public void delete(Long id) { repo.delete(getById(id)); }
}
