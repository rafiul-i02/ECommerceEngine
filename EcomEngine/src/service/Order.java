package service;

import model.OrderItem;
import model.Product;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; 
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private List<OrderItem> items = new ArrayList<>();
    private DiscountStrategy discountStrategy;

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public boolean addItem(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero!");
            return false;
        }
        if (product.getStock() < quantity) {
            System.out.println("Stock insufficient for: " + product.getName());
            return false;
        }
        product.reduceStock(quantity);
        items.add(new OrderItem(product, quantity));
        return true;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void setDiscountStrategy(DiscountStrategy strategy) {
        this.discountStrategy = strategy;
    }

    public double calculateSubtotal() {
        double subtotal = 0;
        for (OrderItem item : items) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }

    public double calculateFinalTotal() {
        double subtotal = calculateSubtotal();
        return (discountStrategy != null) ? discountStrategy.applyDiscount(subtotal) : subtotal;
    }

    public void printAndSaveReceipt() {
        double subtotal = calculateSubtotal();
        double finalTotal = calculateFinalTotal();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        StringBuilder sb = new StringBuilder();
        sb.append("\n-------------------- INVOICE --------------------\n");
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Date: ").append(timestamp).append("\n");
        sb.append("-------------------------------------------------\n");
        for (OrderItem item : items) {
            sb.append(String.format("%-18s x %-3d = BDT.%.2f%n",
                    item.getProduct().getName(), item.getQuantity(), item.getSubtotal()));
        }
        sb.append("-------------------------------------------------\n");
        sb.append(String.format("Subtotal:       BDT.%.2f%n", subtotal));
        if (discountStrategy != null) {
            sb.append("Discount:       ").append(discountStrategy.getStrategyName()).append("\n");
        }
        sb.append(String.format("Payable Total:  BDT.%.2f%n", finalTotal));
        sb.append("-------------------------------------------------\n");

        System.out.print(sb.toString());

        // Save receipt to text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("order_history.txt", true))) {
            writer.write(sb.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Failed to archive receipt to file.");
        }
    }
}
