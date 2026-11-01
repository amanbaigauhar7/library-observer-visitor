package library;

import java.util.concurrent.atomic.AtomicInteger;

public class StatisticsListener
        implements Observer<LibraryEvent> {

    private final AtomicInteger eventCount =
            new AtomicInteger();

    @Override
    public void onEvent(LibraryEvent event) {
        eventCount.incrementAndGet();
    }

    public int getEventCount() {
        return eventCount.get();
    }
}