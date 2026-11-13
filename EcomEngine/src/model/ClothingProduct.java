package model;

public class ClothingProduct extends Product { //Clothing products

    private String size; //Cloth size

    //Constructor ClothingProduct
    public ClothingProduct(String id, String name, double price, int stock, String size) {
        super(id, name, price, stock);
        this.size = size;
    }

    public String getSize() { 
        return size; 
    }

    @Override
    public void displayDetails() {
        System.out.printf("[Clothing]    ID: %-5s | %-16s | Price: BDT.%-8.2f | Stock: %-4d | Size: %s%n", getId(), getName(), getPrice(), getStock(), size);
    }

    @Override
    public String toFileString() {
        return String.format("CLOTHING,%s,%s,%.2f,%d,%s",
                getId(), getName(), getPrice(), getStock(), size);
    }
}