package service;

public interface DiscountStrategy {

    double applyDiscount(double originalTotal); //discount ammount setting
    String getStrategyName(); //Discount type indicating method

}