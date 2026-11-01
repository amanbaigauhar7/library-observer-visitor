package library;

import java.util.concurrent.atomic.AtomicInteger;

public class InventoryListener
        implements Observer<LibraryEvent> {

    private final AtomicInteger returnedBooks =
            new AtomicInteger();

    @Override
    public void onEvent(LibraryEvent event) {
        if (event.type() == EventType.BOOK_RETURNED) {
            returnedBooks.incrementAndGet();
        }
    }

    public int getReturnedBooks() {
        return returnedBooks.get();
    }
}