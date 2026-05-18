package org.practice.splitwise;

abstract public class Split {
    User user;
    double amount;

    public Split(User user) {
        this.user = user;
    }
}

class EqualSplit extends Split {
    public EqualSplit(User user) {
        super(user);
    }
}

class ExactSplit extends Split {
    public ExactSplit(User user, double amount) {
        super(user);
        this.amount = amount;
    }
}