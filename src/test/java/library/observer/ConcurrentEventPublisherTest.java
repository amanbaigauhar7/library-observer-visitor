package library.observer;

import library.*;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
class ConcurrentEventPublisherTest {

    @Test
    void shouldPublish4000EventsUsingFourThreads()
            throws InterruptedException, ExecutionException {

        BookAvailabilitySource source = new BookAvailabilitySource();

        StatisticsListener statistics = new StatisticsListener();

        source.subscribe(EventType.BOOK_AVAILABLE, statistics);

        library.ConcurrentEventPublisher publisher =
                new library.ConcurrentEventPublisher(source);

        publisher.publishEvents(4, 1000);

        assertEquals(4000, statistics.getEventCount());
    }
}
