package org.practice.splitwise;

import java.util.List;

public class Expense {
    String id;
    double amount;
    User paidBy;
    List<Split> splits;
    SplitType splitType;

    public Expense(String id, double amount, User paidBy,
            List<Split> splits, SplitType splitType) {
        this.id = id;
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
        this.splitType = splitType;
    }
}
