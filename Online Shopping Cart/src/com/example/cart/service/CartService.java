package com.example.cart.service;

import com.example.cart.model.CartItem;
import com.example.cart.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    void addProduct(Product product, int quantity);
    void updateProductQuantity(int productId, int quantity);
    void removeProduct(int productId);
    List<CartItem> listCartItems();
    BigDecimal calculateTotal();
}
