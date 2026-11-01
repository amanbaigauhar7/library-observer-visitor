
package library.visitor;

public class FineCalculator implements Visitor<Double> {

    private static final double FINE_PER_DAY = 100.0;

    @Override
    public Double visitLibrary(Library library) {
        double total = 0;

        for (Element element : library.getSections()) {
            total += element.accept(this);
        }

        return total;
    }

    @Override
    public Double visitSection(Section section) {
        double total = 0;

        for (Element element : section.getShelves()) {
            total += element.accept(this);
        }

        return total;
    }

    @Override
    public Double visitShelf(Shelf shelf) {
        double total = 0;

        for (Element element : shelf.getItems()) {
            total += element.accept(this);
        }

        return total;
    }

    @Override
    public Double visitBook(Book book) {
        return book.getOverdueDays() * FINE_PER_DAY;
    }

    @Override
    public Double visitJournal(Journal journal) {
        return 0.0;
    }

    @Override
    public Double visitEBook(EBook ebook) {
        return 0.0;
    }

    @Override
    public Double visitMagazine(Magazine magazine) {
        return 0.0;
    }
}
