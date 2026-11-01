package library;

public class ReservationNotifier
        implements Observer<LibraryEvent> {

    @Override
    public void onEvent(LibraryEvent event) {
        System.out.println(
                "[RESERVATION] " +
                        event.bookTitle() + ": " +
                        event.message()
        );
    }
}