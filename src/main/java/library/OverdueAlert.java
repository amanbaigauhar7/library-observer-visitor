package library;

public class OverdueAlert
        implements Observer<LibraryEvent> {

    @Override
    public void onEvent(LibraryEvent event) {
        System.out.println(
                "[OVERDUE ALERT] " +
                        event.bookTitle() + ": " +
                        event.message()
        );
    }
}