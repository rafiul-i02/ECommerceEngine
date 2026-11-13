package model;

public abstract class Product { //Product for business
    
    private String id;
    private String name;     //As All the products must have these properties
    private double price;
    private int stock; 

    //Product constructor
    public Product(String id, String name, double price, int stock) {  
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Encapsulation - getters & setters
    public String getId() { 
        return id; 
    }
    public String getName() { 
        return name; 
    }
    public double getPrice() { 
        return price;  
    }
    public int getStock() { 
        return stock; 
    }

    public void setPrice(double price) { //Setting price for the products.
        if (price >= 0) this.price = price;
    }


    public void reduceStock(int quantity) { //Stock Maintaining (reducing)
        if (quantity <= this.stock) {
            this.stock -= quantity;
        } else {
            System.out.println("Insufficient stock available!");
        }
    }

    public void addStock(int quantity) { //Stock Maintaining (adding)
        if (quantity > 0) this.stock += quantity;
    }

    // Abstraction - these actions are mandatory for every product.
    public abstract void displayDetails();
    public abstract String toFileString();
}