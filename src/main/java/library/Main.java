
package library;

import library.visitor.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {


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

        Library library = new Library(
                "Central Library",
                List.of(section)
        );

        FineCalculator fineCalculator = new FineCalculator();

        System.out.println("Total fines: "
                + fineCalculator.visitLibrary(library) + " KZT");

        InventoryVisitor inventoryVisitor = new InventoryVisitor();

        System.out.println("Total books: "
                + inventoryVisitor.visitLibrary(library));

        AvailabilityFilter availabilityFilter = new AvailabilityFilter();

        List<Book> availableBooks =
                availabilityFilter.visitLibrary(library);

        System.out.println("Available books:");

        for (Book book : availableBooks) {
            System.out.println("- " + book.getTitle());
        }

        CatalogJsonVisitor jsonVisitor = new CatalogJsonVisitor();

        System.out.println("Library JSON:");
        System.out.println(jsonVisitor.visitLibrary(library));

        CatalogXmlVisitor xmlVisitor = new CatalogXmlVisitor();

        System.out.println("Library XML:");
        System.out.println(xmlVisitor.visitLibrary(library));

        System.out.println("\n--- OBSERVER + VISITOR INTEGRATION ---");

        SectionAggregator aggregator = new SectionAggregator();

        StatisticsListener statistics = new StatisticsListener();

        aggregator.subscribe(
                EventType.VISITOR_REPORT_GENERATED,
                statistics
        );

        VisitorReportListener reportListener =
                new VisitorReportListener(library, aggregator);

        BookAvailabilitySource source = new BookAvailabilitySource();

        source.subscribe(
                EventType.BOOK_AVAILABLE,
                reportListener
        );

        source.makeBookAvailable("B001", "Java Basics");

        System.out.println("Visitor reports: "
                + statistics.getEventCount());
    }
}
