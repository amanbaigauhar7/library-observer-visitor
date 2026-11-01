
package library.visitor;

import java.util.ArrayList;
import java.util.List;

public class AvailabilityFilter implements Visitor<List<Book>> {

    private final List<Book> availableBooks = new ArrayList<>();

    @Override
    public List<Book> visitLibrary(Library library) {
        for (Element element : library.getSections()) {
            element.accept(this);
        }
        return List.copyOf(availableBooks);
    }

    @Override
    public List<Book> visitSection(Section section) {
        for (Element element : section.getShelves()) {
            element.accept(this);
        }
        return List.copyOf(availableBooks);
    }

    @Override
    public List<Book> visitShelf(Shelf shelf) {
        for (Element element : shelf.getItems()) {
            element.accept(this);
        }
        return List.copyOf(availableBooks);
    }

    @Override
    public List<Book> visitBook(Book book) {
        if (book.isAvailable()) {
            availableBooks.add(book);
        }
        return List.copyOf(availableBooks);
    }

    @Override
    public List<Book> visitJournal(Journal journal) {
        return List.copyOf(availableBooks);
    }

    @Override
    public List<Book> visitEBook(EBook ebook) {
        return List.copyOf(availableBooks);
    }

    @Override
    public List<Book> visitMagazine(Magazine magazine) {
        return List.copyOf(availableBooks);
    }

    @Override
    public List<Book> visitNewspaper(Newspaper newspaper) {
        return List.copyOf(availableBooks);
    }
}
