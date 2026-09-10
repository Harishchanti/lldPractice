package org.practice.exceptionhandling;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

public class RetryableFunctionalInterface {

    public static void main(String[] args) throws InterruptedException {

       // ConcurrentHashMap<String, Integer> counters = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, LongAdder> counters = new ConcurrentHashMap<>();
        Map<String, LongAdder> map = new HashMap<>();


        //System.out.println(counters.computeIfAbsent("ss", (v) -> 1));
       // counters.put("aa", 11);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 100; i++) {
            executorService.submit(new IncrementLogic(counters, map,"t-" + i));
        }
        Thread.sleep(1000* 5);
        System.out.println("Final counters :" + counters);
        System.out.println("Final map :" + map);

        executorService.shutdown();
        // happy path senario
       /* try {

            String result =
                    retry(RetryableFunctionalInterface::positiveSenario, 2, 2);

            System.out.println("Final result: " + result);
        } catch (Exception e) {
            System.err.println("All retries failed: " + e.getMessage());
        }

        // failed senario

        try {
            // Retry the unreliable method up to 5 times with 1-second delay

            String result =
                    retry(RetryableFunctionalInterface::unreliableMethod, 5,
                            1000);

            System.out.println("Final result: " + result);
        } catch (Exception e) {
            System.err.println("All retries failed: " + e.getMessage());
        }
        A a = new A();
        try {
            ResponseObj responseObj = retry(a::getUserInfo, 5, 100);
            System.out.println(responseObj);
        } catch (Exception e) {

        }*/

    }

    public static String unreliableMethod() throws Exception {

        if (Math.random() < 0.7) {
            throw new Exception("Random failure occurred!");
        }
        return "Success!";

    }

    public static String positiveSenario() throws Exception {

        return "Success!";

    }

    @FunctionalInterface
    interface Retryable<T> {
        T execute() throws Exception;
    }

    public static <T> T retry(Retryable<T> action, int maxRetries,
            long delayMillis) throws Exception {
        Exception lastException = null;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                return action.execute();
            } catch (Exception e) {
                lastException = e;
                System.err.println(
                        "Attempt " + attempt + " failed: " + e.getMessage());
                if (attempt < maxRetries) {
                    Thread.sleep(delayMillis);
                }
            }
        }
        throw lastException;
    }

}

class IncrementLogic implements Callable<String> {
    String tName;
    ConcurrentHashMap<String, LongAdder> counters;
    Map<String, LongAdder> map;

    public IncrementLogic(ConcurrentHashMap<String, LongAdder> counters,Map<String,LongAdder> map,
            String name) {
        this.counters = counters;
        this.tName = name;
        this.map = map;
    }

    @Override
    public String call() throws Exception {
        System.out.println(
                "Thread name :" + tName + " is being called and key value for aa is " + counters.get(
                        "A"));
        /*return counters.compute("aa", (k, v) -> v == null ? 1 : v + 1);*/
        /*counters.put("aa",counters.getOrDefault("aa",0)+1);
        return counters.get("aa");*/
        counters.computeIfAbsent("A", k -> new LongAdder()).increment();
        map.computeIfAbsent("A",k->new LongAdder()).increment();
        return "Done";
    }
}

