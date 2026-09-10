package org.practice.ExceptionHandlingV2;

public class RetryExecutor {

    public static <T> T executeWithRetry(RetryableOperation<T> operation,
            int maxRetries, long backoffMillis) throws Exception {

        int attempt = 0;

        while (true) {
            try {
                return operation.execute();
            } catch (Exception ex) {

                attempt++;
                System.out.println(" retry attempt count " + attempt);

                // 🔴 Non-transient → fail fast
                if (isNonTransient(ex)) {
                    throw ex;
                }

                // 🔴 Max retries reached
                if (attempt > maxRetries) {
                    throw ex;
                }

                // 🔁 Retry for transient
                Thread.sleep(backoffMillis * attempt); // exponential backoff
            }
        }
    }

    private static boolean isNonTransient(Exception ex) {
        return ex instanceof NonTransientException;
    }

}
