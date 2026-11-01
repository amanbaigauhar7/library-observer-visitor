
package library;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ConcurrentEventPublisher {

    private final BookAvailabilitySource source;

    public ConcurrentEventPublisher(BookAvailabilitySource source) {
        this.source = source;
    }

    public void publishEvents(int threadCount, int eventsPerThread)
            throws InterruptedException, ExecutionException {

        if (threadCount < 1 || eventsPerThread < 1) {
            throw new IllegalArgumentException(
                    "Thread count and event count must be positive"
            );
        }

        ExecutorService executor =
                Executors.newFixedThreadPool(threadCount);

        List<Future<?>> tasks = new ArrayList<>();

        try {
            for (int i = 0; i < threadCount; i++) {
                final int producerId = i;

                Future<?> task = executor.submit(() -> {
                    for (int j = 0; j < eventsPerThread; j++) {
                        String bookId = "P" + producerId + "-" + j;
                        String title = "Book " + bookId;

                        source.makeBookAvailable(bookId, title);
                    }
                });

                tasks.add(task);
            }

            for (Future<?> task : tasks) {
                task.get();
            }

        } finally {
            executor.shutdown();

            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();

                if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                    System.err.println("Executor did not terminate");
                }
            }
        }
    }
}
