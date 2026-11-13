package model;

public class ElectronicsProduct extends Product { //Electronic products
    
    private int warrantyMonths; //product warranty

    //Constructor ElectronicsProduct
    public ElectronicsProduct(String id, String name, double price, int stock, int warrantyMonths) {
        super(id, name, price, stock);
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() { 
        return warrantyMonths; 
    }

    @Override
    public void displayDetails() {
        System.out.printf("[Electronics] ID: %-5s | %-16s | Price: BDT.%-8.2f | Stock: %-4d | Warranty: %d months%n", getId(), getName(), getPrice(), getStock(), warrantyMonths);
    }

    @Override
    public String toFileString() {
        return String.format("ELECTRONICS,%s,%s,%.2f,%d,%d", getId(), getName(), getPrice(), getStock(), warrantyMonths); //Converting into string format.
    }
}