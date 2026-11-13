# 🛒 E-Commerce Order & Inventory Engine

A modular, console-based backend engine built in **Java** demonstrating the core principles of **Object-Oriented Programming (OOP)** alongside Java Collections and File I/O persistence.

---

## 🎯 Project Overview

The **E-Commerce Order & Inventory Engine** automates product catalog management, real-time stock tracking, order processing, dynamic discount calculation, and invoice generation. Designed with clean separation of concerns (`model` and `service` layers), it serves as a complete reference implementation for an OOP lab project.

---

## ✨ Key Features

- **Real-Time Inventory Management:** View available products, add new items (`Electronics` or `Clothing`), and restock existing inventory.
- **Automated Order & Cart System:** Add multiple items to an order with automatic stock validation (prevents ordering out-of-stock quantities).
- **Dynamic Discount Engine:** Apply runtime promotional coupons (Percentage-based or Flat BDT discounts) using the Strategy Design Pattern.
- **Automated Invoice Generation:** Generates formatted receipts with timestamps and archives all transactions to `order_history.txt`.
- **Persistent Storage (File I/O):** Automatically saves and loads inventory states from `inventory_data.txt`, ensuring data persists across program restarts.

---

## 🧠 OOP Concepts Applied

| OOP Pillar | Implementation in Project |
| :--- | :--- |
| **Encapsulation** | All entity attributes (`id`, `name`, `price`, `stock`) are kept `private` with controlled access and validation through getters/setters (`reduceStock()`, `addStock()`). |
| **Abstraction** | `Product` is defined as an `abstract` base class enforcing `displayDetails()` and `toFileString()`, while `DiscountStrategy` provides an interface contract for discount rules. |
| **Inheritance** | `ElectronicsProduct` (with `warrantyMonths`) and `ClothingProduct` (with `size`) extend the base `Product` class, reusing shared attributes and behavior. |
| **Polymorphism** | Runtime polymorphism is achieved via `DiscountStrategy` (`PercentageDiscount`, `FlatDiscount`) and overridden `displayDetails()` calls across heterogeneous `Product` collections. |

---

## 📂 Project Structure

```
ECommerceEngine/
├── src/
│   ├── model/
│   │   ├── Product.java              # Abstract base entity
│   │   ├── ElectronicsProduct.java   # Subclass with warranty attribute
│   │   ├── ClothingProduct.java      # Subclass with size attribute
│   │   └── OrderItem.java            # Line-item wrapper (Product + Quantity)
│   ├── service/
│   │   ├── DiscountStrategy.java     # Strategy interface for discounts
│   │   ├── PercentageDiscount.java   # Percentage-based discount logic
│   │   ├── FlatDiscount.java         # Fixed amount discount logic
│   │   ├── Inventory.java            # HashMap storage & File I/O manager
│   │   └── Order.java                # Checkout & receipt generation engine
│   └── Main.java                     # Interactive CLI menu driver
├── inventory_data.txt                # Auto-generated persistent product store
├── order_history.txt                 # Auto-generated invoice archive
└── README.md

```

## 🚀 Getting Started

### Prerequisites

* **Java Development Kit (JDK)** 8 or higher installed on your system.

### Compilation & Execution

1. **Clone the repository:**

git clone [https://github.com/rafiul-i02/ECommerceEngine.git](https://github.com/rafiul-i02/ECommerceEngine.git)
cd ECommerceEngine




2. **Compile the source files:**

javac src/model/*.java src/service/*.java src/Main.java



3. **Run the application:**

java -cp src Main



---

## 💻 Sample Console Output

```
=== E-COMMERCE ORDER & INVENTORY ENGINE ===
1. View Inventory
2. Add New Product (Admin)
3. Place an Order (Customer)
4. Restock Existing Product (Admin)
5. Exit
Enter choice (1-5): 3

-------------------- INVOICE --------------------
Order ID: ORD-1001
Date: 2026-11-13 20:15:30
-------------------------------------------------
Wireless Mouse     x 2   = BDT 1700.00
Oversized Tee      x 1   = BDT 650.00
-------------------------------------------------
Subtotal:       BDT 2350.00
Discount:       10.0% Off
Payable Total:  BDT 2115.00
-------------------------------------------------

```

---

## 🛠️ Tech Stack

* **Language:** Java (Core OOP, Collections Framework, `java.io`, `java.time`)
* **Architecture:** Layered (Model-Service-Driver)
* **Storage:** Plain-text CSV/Log File Persistence
