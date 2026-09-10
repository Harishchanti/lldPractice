package org.practice.ExceptionHandlingV2;

public class TestExceptionHandling {
    public static void main(String[] args) throws Exception {
        String result = RetryExecutor.executeWithRetry( TestExceptionHandling::callExternalService,
                3,      // max retries
                100     // base backoff ms
        );
        System.out.println(result);
    }

    private static String callExternalService() {

        // your business logic
        if (Math.random() < 0.7) {
            throw new TransientException("Temporary failure");
        }
        return "Success";
    }
}
