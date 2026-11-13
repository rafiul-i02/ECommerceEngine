package service;

import model.*;
import java.io.*;
import java.util.*;

public class Inventory {
    private Map<String, Product> productMap = new LinkedHashMap<>();
    private final String FILE_NAME = "inventory_data.txt";

    public Inventory() {
        loadFromFile();
    }

    public void addProduct(Product product) {
        productMap.put(product.getId(), product);
        saveToFile();
    }

    public Product getProduct(String id) {
        return productMap.get(id);
    }

    public Collection<Product> getAllProducts() {
        return productMap.values();
    }

    public void displayInventory() {
        System.out.println("\n====================== CURRENT INVENTORY ======================");
        if (productMap.isEmpty()) {
            System.out.println("Inventory is empty!");
        } else {
            for (Product p : productMap.values()) {
                p.displayDetails();
            }
        }
        System.out.println("===============================================================");
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Product p : productMap.values()) {
                writer.write(p.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            seedDefaultProducts();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length < 6) continue;

                String type = tokens[0];
                String id = tokens[1];
                String name = tokens[2];
                double price = Double.parseDouble(tokens[3]);
                int stock = Integer.parseInt(tokens[4]);

                if (type.equals("ELECTRONICS")) {
                    int warranty = Integer.parseInt(tokens[5]);
                    productMap.put(id, new ElectronicsProduct(id, name, price, stock, warranty));
                } else if (type.equals("CLOTHING")) {
                    String size = tokens[5];
                    productMap.put(id, new ClothingProduct(id, name, price, stock, size));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading inventory file. Loading defaults...");
            seedDefaultProducts();
        }
    }

    private void seedDefaultProducts() {
        addProduct(new ElectronicsProduct("E101", "Wireless Mouse", 850.0, 15, 6));
        addProduct(new ElectronicsProduct("E102", "Mechanical Keybd", 2400.0, 8, 12));
        addProduct(new ClothingProduct("C201", "Oversized Tee", 650.0, 20, "XL"));
        addProduct(new ClothingProduct("C202", "Denim Jacket", 2200.0, 5, "L"));
    }
}