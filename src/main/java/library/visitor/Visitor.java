package library.visitor;

public interface Visitor<R> {

    R visitLibrary(Library library);

    R visitSection(Section section);

    R visitShelf(Shelf shelf);

    R visitBook(Book book);

    R visitJournal(Journal journal);

    R visitEBook(EBook ebook);

    R visitMagazine(Magazine magazine);

    R visitNewspaper(Newspaper newspaper);
}