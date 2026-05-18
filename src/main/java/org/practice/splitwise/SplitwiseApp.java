package org.practice.splitwise;

import java.util.Arrays;
import java.util.List;

public class SplitwiseApp {

    public static void main(String[] args) throws InterruptedException {

        BalanceSheet balanceSheet = new BalanceSheet();
        ExpenseService expenseService = new ExpenseService(balanceSheet);

        User u1 = new User("U1", "Alice");
        User u2 = new User("U2", "Bob");
        User u3 = new User("U3", "Charlie");

        // Create threads to simulate concurrency
        Thread t1 = new Thread(() -> {
            List<Split> splits = Arrays.asList(
                    new EqualSplit(u1),
                    new EqualSplit(u2),
                    new EqualSplit(u3)
            );

            Expense e1 = new Expense("E1", 300, u1, splits, SplitType.EQUAL);
            expenseService.addExpense(e1);
        });

        Thread t2 = new Thread(() -> {
            List<Split> splits = Arrays.asList(
                    new ExactSplit(u1, 100),
                    new ExactSplit(u2, 100),
                    new ExactSplit(u3, 100)
            );

            Expense e2 = new Expense("E2", 300, u2, splits, SplitType.EXACT);
            expenseService.addExpense(e2);
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("\nFinal Balances:");
        balanceSheet.printBalances();
    }
}
