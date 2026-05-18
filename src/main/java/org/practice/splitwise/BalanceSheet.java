package org.practice.splitwise;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class BalanceSheet {

    private final Map<String, Map<String, Double>> balances =
            new ConcurrentHashMap<>();
    private final ReentrantLock lock = new ReentrantLock();

    public void updateBalance(User paidBy, List<Split> splits) {
        lock.lock(); // 🔒 concurrency control
        try {
            for (Split split : splits) {
                if (split.user.id.equals(paidBy.id))
                    continue;

                balances.putIfAbsent(split.user.id, new ConcurrentHashMap<>());
                balances.putIfAbsent(paidBy.id, new ConcurrentHashMap<>());

                Map<String, Double> debtorMap = balances.get(split.user.id);
                Map<String, Double> creditorMap = balances.get(paidBy.id);

                double prev = debtorMap.getOrDefault(paidBy.id, 0.0);
                debtorMap.put(paidBy.id, prev + split.amount);

                // reverse entry (optional but useful)
                double reversePrev =
                        creditorMap.getOrDefault(split.user.id, 0.0);
                creditorMap.put(split.user.id, reversePrev - split.amount);
            }
        } finally {
            lock.unlock();
        }
    }

    public void printBalances() {
        for (String user : balances.keySet()) {
            for (Map.Entry<String, Double> entry : balances.get(user)
                    .entrySet()) {
                if (entry.getValue() > 0) {
                    System.out.println(
                            user + " owes " + entry.getKey() + ": " + entry.getValue());
                }
            }
        }
    }
}
