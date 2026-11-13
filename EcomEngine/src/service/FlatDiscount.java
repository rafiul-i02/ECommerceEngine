package service;

public class FlatDiscount implements DiscountStrategy { //Flat discount   

    private double flatAmount;

    public FlatDiscount(double flatAmount) { //Discount ammount
        this.flatAmount = flatAmount;
    }

    @Override
    public double applyDiscount(double originalTotal) {

        //using Math class & max method to avoid negative value
        return Math.max(0, originalTotal - flatAmount); //Calculating discounted price

    }

    @Override
    public String getStrategyName() {
        return "BDT." + flatAmount + " Flat Off"; //Discount naming
    }
}