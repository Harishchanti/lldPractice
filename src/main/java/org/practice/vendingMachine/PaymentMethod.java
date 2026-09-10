package org.practice.vendingMachine;

public interface PaymentMethod {
    void pay(Double amount);
}

class CashPayment implements PaymentMethod {
    @Override
    public void pay(Double amount) {

    }
}
class CardPayment implements PaymentMethod {
    @Override
    public void pay(Double amount) {

    }
}
class UPIPayment implements PaymentMethod {
    @Override
    public void pay(Double amount) {

    }
}