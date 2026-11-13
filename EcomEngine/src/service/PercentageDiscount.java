package service;

public class PercentageDiscount implements DiscountStrategy { //Percentage type discount

    private double percentage;

    public PercentageDiscount(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double applyDiscount(double originalTotal) {
        return originalTotal - (originalTotal * (percentage / 100.0)); //Calculating percentage discounted price of a product
    }

    @Override
    public String getStrategyName() {
        return percentage + "% Off"; //Discount naming
    }
}