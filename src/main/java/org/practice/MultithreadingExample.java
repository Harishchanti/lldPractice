package org.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class MultithreadingExample {
    public static void main(String[] args)
            throws ExecutionException, InterruptedException {
        List<Orders> ordersList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ordersList.add(new Orders(i % 10, String.valueOf(i), 1));
        }
        Map<Integer, Long> userAmountAgg = new HashMap<>();
        int n = 3;
        int batchSize = 10;
        ExecutorService service = Executors.newFixedThreadPool(n);
        List<Future<Map<Integer, Long>>> futureList = new ArrayList<>();
        for (int j = 0; j < batchSize; j++) {
            int start = j * batchSize;
            int end = (j == batchSize - 1) ? ordersList.size() :
                    start + batchSize;
            futureList.add(
                    service.submit(new CostAggregator(start, end, ordersList)));
        }

        for (Future<Map<Integer, Long>> mapFuture : futureList) {
            Map<Integer, Long> sumMap = mapFuture.get();

            for (Map.Entry<Integer, Long> e : sumMap.entrySet()) {
                if (userAmountAgg.containsKey(e.getKey())) {
                    userAmountAgg.put(e.getKey(),
                            userAmountAgg.get(e.getKey()) + e.getValue());
                } else {
                    userAmountAgg.put(e.getKey(), e.getValue());
                }
            }
        }

        for (Map.Entry<Integer, Long> e : userAmountAgg.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
        service.shutdown();

    }
}

class CostAggregator implements Callable<Map<Integer, Long>> {
    int start;
    int end;
    List<Orders> ordersList;
    Map<Integer, Long> sumMap = new HashMap<>();

    CostAggregator(int start, int end, List<Orders> ordersList) {
        this.start = start;
        this.end = end;
        this.ordersList = ordersList;
    }

    @Override
    public Map<Integer, Long> call() throws Exception {

        for (int i = start; i < end; i++) {

            if (sumMap.containsKey(ordersList.get(i).id)) {
                sumMap.put(ordersList.get(i).id,
                        sumMap.get(ordersList.get(i).id) + ordersList.get(
                                i).amount);
            } else {
                sumMap.put(ordersList.get(i).id, ordersList.get(i).amount);
            }
        }

        return sumMap;
    }
}

class Orders {
    int id;
    String name;
    long amount;

    Orders(int id, String name, long amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }
}