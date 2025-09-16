package com.example.cart.controller;

import com.example.cart.model.CartItem;
import com.example.cart.model.Product;
import com.example.cart.service.CartService;
import com.example.cart.service.CartServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static int productIdCounter = 1;

    public static void main(String[] args) {
        CartService cart = new CartServiceImpl();

        while (true) {
            System.out.println("\n=== Shopping Cart Menu ===");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product Quantity");
            System.out.println("3. Remove Product");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout & Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again.");
                continue;
            }

            switch (choice) {
                case 1 -> addProduct(cart);
                case 2 -> updateProduct(cart);
                case 3 -> removeProduct(cart);
                case 4 -> printCart(cart);
                case 5 -> {
                    System.out.println("\n=== Final Cart ===");
                    printCart(cart);
                    System.out.println("Thank you for shopping!");
                    return;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void addProduct(CartService cart) {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter product price: ");
        BigDecimal price;
        try {
            price = new BigDecimal(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity.");
            return;
        }

        Product product = new Product(productIdCounter++, name, price);
        cart.addProduct(product, quantity);
        System.out.println("Product added to cart.");
        printCart(cart);
    }

    private static void updateProduct(CartService cart) {
        System.out.print("Enter product ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter new quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        cart.updateProductQuantity(id, quantity);
        System.out.println("Product updated.");
        printCart(cart);
    }

    private static void removeProduct(CartService cart) {
        System.out.print("Enter product ID to remove: ");
        int id = Integer.parseInt(scanner.nextLine());
        cart.removeProduct(id);
        System.out.println("Product removed (if existed).");
        printCart(cart);
    }

    private static void printCart(CartService cart) {
        List<CartItem> items = cart.listCartItems();
        if (items.isEmpty()) {
            System.out.println("[Cart is empty]");
            return;
        }
        for (CartItem item : items) {
            System.out.println(item.getProduct().getId() + " → " + item);
        }
        System.out.println("Cart total: " + cart.calculateTotal());
    }
}
