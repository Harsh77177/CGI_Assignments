package com.example.cart.service;

import com.example.cart.model.CartItem;
import com.example.cart.model.Product;

import java.math.BigDecimal;
import java.util.*;

public class CartServiceImpl implements CartService {
    private final Map<Integer, CartItem> items = new HashMap<>();

    @Override
    public void addProduct(Product product, int quantity) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null");
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be > 0");

        CartItem existing = items.get(product.getId());
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
        } else {
            items.put(product.getId(), new CartItem(product, quantity));
        }
    }

    @Override
    public void updateProductQuantity(int productId, int quantity) {
        CartItem existing = items.get(productId);
        if (existing == null) {
            System.out.println("No product with id " + productId + " in cart.");
            return;
        }
        if (quantity <= 0) {
            items.remove(productId);
        } else {
            existing.setQuantity(quantity);
        }
    }

    @Override
    public void removeProduct(int productId) {
        if (items.remove(productId) == null) {
            System.out.println("Product id " + productId + " not present in cart.");
        }
    }

    @Override
    public List<CartItem> listCartItems() {
        return Collections.unmodifiableList(new ArrayList<>(items.values()));
    }

    @Override
    public BigDecimal calculateTotal() {
        return items.values().stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
