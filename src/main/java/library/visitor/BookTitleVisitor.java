
package library.visitor;

import java.util.ArrayList;
import java.util.List;

public class BookTitleVisitor implements Visitor<List<String>> {

    private final List<String> titles = new ArrayList<>();

    @Override
    public List<String> visitLibrary(Library library) {
        for (Element section : library.getSections()) {
            section.accept(this);
        }
        return List.copyOf(titles);
    }

    @Override
    public List<String> visitSection(Section section) {
        for (Element shelf : section.getShelves()) {
            shelf.accept(this);
        }
        return List.copyOf(titles);
    }

    @Override
    public List<String> visitShelf(Shelf shelf) {
        for (Element item : shelf.getItems()) {
            item.accept(this);
        }
        return List.copyOf(titles);
    }

    @Override
    public List<String> visitBook(Book book) {
        titles.add(book.getTitle());
        return List.copyOf(titles);
    }

    @Override
    public List<String> visitJournal(Journal journal) {
        titles.add(journal.getTitle());
        return List.copyOf(titles);
    }

    @Override
    public List<String> visitEBook(EBook eBook) {
        titles.add(eBook.getTitle());
        return List.copyOf(titles);
    }

    @Override
    public List<String> visitMagazine(Magazine magazine) {
        titles.add(magazine.getTitle());
        return List.copyOf(titles);
    }
}
