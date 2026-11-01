package library;

import java.time.Instant;

public class SectionAggregator
        extends AbstractSubject<LibraryEvent>
        implements Observer<LibraryEvent> {

    @Override
    public void onEvent(LibraryEvent event) {
        LibraryEvent aggregatedEvent =
                new LibraryEvent(
                        event.type(),
                        event.bookId(),
                        event.bookTitle(),
                        "Section update: " + event.message(),
                        Instant.now()
                );

        publish(aggregatedEvent);
    }
}