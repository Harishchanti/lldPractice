package org.practice.splitwise;

public class ExpenseService {
    private final BalanceSheet balanceSheet;

    public ExpenseService(BalanceSheet balanceSheet) {
        this.balanceSheet = balanceSheet;
    }

    public void addExpense(Expense expense) {
        validate(expense);
        calculateSplits(expense);
        balanceSheet.updateBalance(expense.paidBy, expense.splits);
    }

    private void validate(Expense expense) {
        if (expense.splitType == SplitType.EXACT) {
            double total = 0;
            for (Split split : expense.splits) {
                total += split.amount;
            }
            if (Math.abs(total - expense.amount) > 0.01) {
                throw new RuntimeException("Invalid split amounts");
            }
        }
    }

    private void calculateSplits(Expense expense) {
        if (expense.splitType == SplitType.EQUAL) {
            int totalUsers = expense.splits.size();
            double splitAmount = expense.amount / totalUsers;

            for (Split split : expense.splits) {
                split.amount = splitAmount;
            }
        }
    }
}
