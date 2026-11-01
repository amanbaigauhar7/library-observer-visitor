
package library;

public class LibrarianPanel implements Observer<LibraryEvent> {

    @Override
    public void onEvent(LibraryEvent event) {
        System.out.println(
                "[LIBRARIAN] " +
                        event.bookTitle() + ": " +
                        event.message()
        );
    }
}
