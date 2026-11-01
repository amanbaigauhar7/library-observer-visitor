package library.visitor;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VisitorTest {

    private Library createLibrary() {
        Book book1 = new Book("Java Basics", 5000, true, 3);
        Book book2 = new Book("Clean Code", 7000, false, 0);
        Book book3 = new Book("Python Guide", 4500, true, 2);

        Journal journal = new Journal("Science Journal", 2000);
        EBook ebook = new EBook("Java E-Book", 3000, true);
        Magazine magazine = new Magazine("Tech Magazine", 1500);

        Shelf shelf1 = new Shelf(
                "Programming Shelf",
                List.of(book1, book2, journal)
        );

        Shelf shelf2 = new Shelf(
                "Digital Shelf",
                List.of(book3, ebook, magazine)
        );

        Section section = new Section(
                "Technology",
                List.of(shelf1, shelf2)
        );

        return new Library("Central Library", List.of(section));
    }

    @Test
    void shouldCalculateTotalFines() {
        Library library = createLibrary();

        FineCalculator calculator = new FineCalculator();

        double result = library.accept(calculator);

        assertEquals(500.0, result);
    }

    @Test
    void shouldCountPhysicalBooks() {
        Library library = createLibrary();

        InventoryVisitor visitor = new InventoryVisitor();

        int result = library.accept(visitor);

        assertEquals(3, result);
    }

    @Test
    void shouldFindAvailableBooks() {
        Library library = createLibrary();

        AvailabilityFilter visitor = new AvailabilityFilter();

        List<Book> result = library.accept(visitor);

        assertEquals(2, result.size());
        assertEquals("Java Basics", result.get(0).getTitle());
        assertEquals("Python Guide", result.get(1).getTitle());
    }

    @Test
    void shouldExportLibraryAsJson() {
        Library library = createLibrary();

        CatalogJsonVisitor visitor = new CatalogJsonVisitor();

        String result = library.accept(visitor);

        System.out.println(result);

        assertTrue(result.contains("Central Library"));
        assertTrue(result.contains("Technology"));
        assertTrue(result.contains("Java Basics"));
        assertTrue(result.contains("Science Journal"));
    }

    @Test
    void shouldExportLibraryAsXml() {
        Library library = createLibrary();

        CatalogXmlVisitor visitor = new CatalogXmlVisitor();

        String result = library.accept(visitor);

        assertTrue(result.contains("<library"));
        assertTrue(result.contains("<section"));
        assertTrue(result.contains("<shelf"));
        assertTrue(result.contains("Java Basics"));
        assertTrue(result.contains("</library>"));
    }

    @Test
    void shouldCollectAllTitles() {
        Library library = createLibrary();

        BookTitleVisitor visitor = new BookTitleVisitor();

        List<String> result = library.accept(visitor);

        assertEquals(6, result.size());
        assertTrue(result.contains("Java Basics"));
        assertTrue(result.contains("Clean Code"));
        assertTrue(result.contains("Python Guide"));
        assertTrue(result.contains("Science Journal"));
        assertTrue(result.contains("Java E-Book"));
        assertTrue(result.contains("Tech Magazine"));
    }
}