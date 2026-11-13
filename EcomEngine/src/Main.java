import model.*;
import service.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner sc = new Scanner(System.in);
        int orderCounter = 1001;

        while (true) {
            System.out.println("\n=== E-COMMERCE ORDER & INVENTORY ENGINE ===");
            System.out.println("1. View Inventory");
            System.out.println("2. Add New Product (Admin)");
            System.out.println("3. Place an Order (Customer)");
            System.out.println("4. Restock Existing Product (Admin)");
            System.out.println("5. Exit");
            System.out.print("Enter choice (1-5): ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    inventory.displayInventory();
                    break;

                case 2:
                    System.out.print("Product Type (1: Electronics, 2: Clothing): ");
                    int type = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Product ID (e.g. E103): ");
                    String id = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Price: BDT.");
                    double price = sc.nextDouble();
                    System.out.print("Stock Quantity: ");
                    int stock = sc.nextInt();
                    sc.nextLine();

                    if (type == 1) {
                        System.out.print("Warranty (Months): ");
                        int warranty = sc.nextInt();
                        sc.nextLine();
                        inventory.addProduct(new ElectronicsProduct(id, name, price, stock, warranty));
                    } else {
                        System.out.print("Size (e.g. S, M, L, XL): ");
                        String size = sc.nextLine();
                        inventory.addProduct(new ClothingProduct(id, name, price, stock, size));
                    }
                    System.out.println("Product added successfully!");
                    break;

                case 3:
                    Order order = new Order("ORD-" + (orderCounter++));
                    while (true) {
                        inventory.displayInventory();
                        System.out.print("Enter Product ID to buy (or type 'done' to checkout): ");
                        String pId = sc.nextLine();
                        if (pId.equalsIgnoreCase("done")) break;

                        Product p = inventory.getProduct(pId);
                        if (p == null) {
                            System.out.println("Invalid Product ID! Please try again.");
                            continue;
                        }

                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        sc.nextLine();

                        if (order.addItem(p, qty)) {
                            System.out.println(qty + "x " + p.getName() + " added to cart!");
                        }
                    }

                    if (order.isEmpty()) {
                        System.out.println("Cart is empty. Order cancelled.");
                        break;
                    }

                    // Polymorphism in action: Discount Strategy
                    System.out.println("\nSelect Discount Coupon:");
                    System.out.println("1. 10% Festive Discount");
                    System.out.println("2. Flat BDT.200 Cash Discount");
                    System.out.println("3. No Discount");
                    System.out.print("Choose coupon (1-3): ");
                    int coupon = sc.nextInt();
                    sc.nextLine();

                    if (coupon == 1) {
                        order.setDiscountStrategy(new PercentageDiscount(10));
                    } else if (coupon == 2) {
                        order.setDiscountStrategy(new FlatDiscount(200));
                    }

                    order.printAndSaveReceipt();
                    inventory.saveToFile(); // স্টক আপডেট করে ফাইলে সেভ করা
                    break;

                case 4:
                    inventory.displayInventory();
                    System.out.print("Enter Product ID to restock: ");
                    String restockId = sc.nextLine();
                    Product restockProduct = inventory.getProduct(restockId);
                    
                    if (restockProduct == null) {
                        System.out.println("Product not found!");
                    } else {
                        System.out.print("Enter additional stock quantity: ");
                        int addQty = sc.nextInt();
                        sc.nextLine();
                        restockProduct.addStock(addQty);
                        inventory.saveToFile();
                        System.out.println("Stock updated successfully!");
                    }
                    break;

                case 5:
                    System.out.println("Exiting... All data saved. Have a great day!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option! Please enter 1-5.");
            }
        }
    }
}