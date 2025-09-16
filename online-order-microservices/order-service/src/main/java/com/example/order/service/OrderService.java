package com.example.order.service;

import com.example.order.client.ProductClient;
import com.example.order.model.Order;
import com.example.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository repo;
    private final ProductClient productClient;

    public OrderService(OrderRepository repo, ProductClient productClient) {
        this.repo = repo;
        this.productClient = productClient;
    }

    @Transactional
    public Order placeOrder(Order order) {
        var product = productClient.getProductById(order.getProductId());
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        if (product.getStockQuantity() == null || product.getStockQuantity() < order.getQuantity()) {
            throw new RuntimeException("Insufficient stock");
        }
        order.setStatus("Completed");
        order.setOrderDate(LocalDate.now());
        return repo.save(order);
    }

    public List<Order> getAll() { return repo.findAll(); }
    public Optional<Order> getById(Long id) { return repo.findById(id); }
    public Order updateStatus(Long id, String status) {
        return repo.findById(id).map(o -> {
            o.setStatus(status);
            return repo.save(o);
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }
    public void delete(Long id) { repo.deleteById(id); }
}
