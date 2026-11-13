package model;

public class OrderItem { //Order items blueprint

    private Product product;
    private int quantity; //order quantity

    //Constructor OrderItem
    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { //Getter method for product
        return product; 
    }
    public int getQuantity() { //Getter for quantity
        return quantity; 
    }

    public double getSubtotal() { //Getter for subtotal price of the order
        return product.getPrice() * quantity;
    }
}