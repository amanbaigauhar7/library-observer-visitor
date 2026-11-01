
package library;

import java.time.Instant;

public class BookReservationSource
        extends AbstractSubject<LibraryEvent> {

    public void createReservation(
            String bookId,
            String bookTitle) {

        LibraryEvent event = new LibraryEvent(
                EventType.RESERVATION_CREATED,
                bookId,
                bookTitle,
                "A new reservation has been created",
                Instant.now()
        );

        publish(event);
    }

    public void reportOverdue(
            String bookId,
            String bookTitle) {

        LibraryEvent event = new LibraryEvent(
                EventType.BOOK_OVERDUE,
                bookId,
                bookTitle,
                "Book return deadline has passed",
                Instant.now()
        );

        publish(event);
    }
}
