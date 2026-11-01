
package library.visitor;

public class InventoryVisitor implements Visitor<Integer> {

    @Override
    public Integer visitLibrary(Library library) {
        int total = 0;

        for (Element element : library.getSections()) {
            total += element.accept(this);
        }

        return total;
    }

    @Override
    public Integer visitSection(Section section) {
        int total = 0;

        for (Element element : section.getShelves()) {
            total += element.accept(this);
        }

        return total;
    }

    @Override
    public Integer visitShelf(Shelf shelf) {
        int total = 0;

        for (Element element : shelf.getItems()) {
            total += element.accept(this);
        }

        return total;
    }

    @Override
    public Integer visitBook(Book book) {
        return 1;
    }

    @Override
    public Integer visitJournal(Journal journal) {
        return 0;
    }

    @Override
    public Integer visitEBook(EBook ebook) {
        return 0;
    }

    @Override
    public Integer visitMagazine(Magazine magazine) {
        return 0;
    }
}
