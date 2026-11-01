
package library.observer;

import library.*;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ObserverBehaviorTest {

    @Test
    void shouldNotifySubscribedObserver() {
        BookAvailabilitySource source = new BookAvailabilitySource();
        AtomicInteger count = new AtomicInteger(0);

        Observer<LibraryEvent> listener =
                event -> count.incrementAndGet();

        source.subscribe(EventType.BOOK_AVAILABLE, listener);
        source.makeBookAvailable("B1", "Java Basics");

        assertEquals(1, count.get());
    }

    @Test
    void shouldNotNotifyUnsubscribedObserver() {
        BookAvailabilitySource source = new BookAvailabilitySource();
        AtomicInteger count = new AtomicInteger(0);

        Observer<LibraryEvent> listener =
                event -> count.incrementAndGet();

        source.subscribe(EventType.BOOK_AVAILABLE, listener);
        source.unsubscribe(EventType.BOOK_AVAILABLE, listener);

        source.makeBookAvailable("B1", "Java Basics");

        assertEquals(0, count.get());
    }

    @Test
    void shouldNotifyObserverOnlyForSubscribedEventType() {
        BookAvailabilitySource source = new BookAvailabilitySource();
        AtomicInteger count = new AtomicInteger(0);

        Observer<LibraryEvent> listener =
                event -> count.incrementAndGet();

        source.subscribe(EventType.BOOK_RETURNED, listener);
        source.makeBookAvailable("B1", "Java Basics");

        assertEquals(0, count.get());
    }

    @Test
    void shouldNotifyObserverForTwoEvents() {
        BookAvailabilitySource source = new BookAvailabilitySource();
        AtomicInteger count = new AtomicInteger(0);

        Observer<LibraryEvent> listener =
                event -> count.incrementAndGet();

        source.subscribe(EventType.BOOK_AVAILABLE, listener);

        source.makeBookAvailable("B1", "Java Basics");
        source.makeBookAvailable("B2", "Clean Code");

        assertEquals(2, count.get());
    }

    @Test
    void shouldAllowObserverToJoinAndLeaveDuringEventHandling() {
        BookAvailabilitySource source = new BookAvailabilitySource();

        AtomicInteger secondObserverCount = new AtomicInteger();
        AtomicReference<Observer<LibraryEvent>> firstObserver =
                new AtomicReference<>();

        Observer<LibraryEvent> secondObserver =
                event -> secondObserverCount.incrementAndGet();

        firstObserver.set(event -> {
            source.unsubscribe(EventType.BOOK_AVAILABLE, firstObserver.get());
            source.subscribe(EventType.BOOK_AVAILABLE, secondObserver);
        });

        source.subscribe(EventType.BOOK_AVAILABLE, firstObserver.get());

        source.makeBookAvailable("B001", "Java Basics");

        assertEquals(0, secondObserverCount.get());

        source.makeBookAvailable("B002", "Clean Code");

        assertEquals(1, secondObserverCount.get());
    }

    @Test
    void shouldNotifyObserverFromReservationSource() {
        BookReservationSource source = new BookReservationSource();
        AtomicInteger count = new AtomicInteger(0);

        Observer<LibraryEvent> listener =
                event -> count.incrementAndGet();

        source.subscribe(EventType.RESERVATION_CREATED, listener);

        source.createReservation("B001", "Java Basics");

        assertEquals(1, count.get());
    }

    @Test
    void shouldNotifyObserverAboutOverdueBook() {
        BookReservationSource source = new BookReservationSource();
        AtomicInteger count = new AtomicInteger(0);

        Observer<LibraryEvent> listener =
                event -> count.incrementAndGet();

        source.subscribe(EventType.BOOK_OVERDUE, listener);

        source.reportOverdue("B002", "Clean Code");

        assertEquals(1, count.get());
    }

    @Test
    void shouldNotifyMultipleObservers() {
        BookAvailabilitySource source = new BookAvailabilitySource();

        AtomicInteger firstCount = new AtomicInteger();
        AtomicInteger secondCount = new AtomicInteger();

        Observer<LibraryEvent> first =
                event -> firstCount.incrementAndGet();

        Observer<LibraryEvent> second =
                event -> secondCount.incrementAndGet();

        source.subscribe(EventType.BOOK_AVAILABLE, first);
        source.subscribe(EventType.BOOK_AVAILABLE, second);

        source.makeBookAvailable("B003", "Python Guide");

        assertEquals(1, firstCount.get());
        assertEquals(1, secondCount.get());
    }

    @Test
    void shouldNotifyObserverWhenBookIsReturned() {
        BookAvailabilitySource source = new BookAvailabilitySource();
        AtomicInteger count = new AtomicInteger();

        Observer<LibraryEvent> listener =
                event -> count.incrementAndGet();

        source.subscribe(EventType.BOOK_RETURNED, listener);

        source.returnBook("B004", "Clean Code");

        assertEquals(1, count.get());
    }
}
