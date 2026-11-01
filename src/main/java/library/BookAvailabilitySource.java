
package library;

import java.time.Instant;

public class BookAvailabilitySource
        extends AbstractSubject<LibraryEvent> {

    public void makeBookAvailable(
            String bookId,
            String bookTitle) {

        LibraryEvent event = new LibraryEvent(
                EventType.BOOK_AVAILABLE,
                bookId,
                bookTitle,
                "Book is now available",
                Instant.now()
        );

        publish(event);
    }

    public void borrowBook(
            String bookId,
            String bookTitle) {

        LibraryEvent event = new LibraryEvent(
                EventType.BOOK_BORROWED,
                bookId,
                bookTitle,
                "Book has been borrowed",
                Instant.now()
        );

        publish(event);
    }

    public void returnBook(
            String bookId,
            String bookTitle) {

        LibraryEvent event = new LibraryEvent(
                EventType.BOOK_RETURNED,
                bookId,
                bookTitle,
                "Book has been returned",
                Instant.now()
        );

        publish(event);
    }
}
