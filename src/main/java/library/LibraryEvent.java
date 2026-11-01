package library;

import java.time.Instant;

public record LibraryEvent(
        EventType type,
        String bookId,
        String bookTitle,
        String message,
        Instant occurredAt
) implements Event {

    public LibraryEvent {
        if (type == null || bookId == null ||
                bookTitle == null || message == null ||
                occurredAt == null) {
            throw new IllegalArgumentException(
                    "Event fields cannot be null"
            );
        }
    }
}