package org.practice.parkingLotV2;


public interface PricingStrategy {
    double calculate(long duration);
}

class HourlyPricing implements PricingStrategy {
    @Override
    public double calculate(long duration) {
        return 0;
    }
}
class FlatRatePricing implements PricingStrategy {
    @Override
    public double calculate(long duration) {
        return 0;
    }
}