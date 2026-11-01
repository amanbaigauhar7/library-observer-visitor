
# Library Observer and Visitor System

## Project Overview

This project demonstrates two behavioral design patterns: Observer and Visitor.

The system represents a library where books and other materials can be managed, monitored, and analyzed.

The project is developed using Java 17 and JUnit 5.

## 1. Observer Pattern

The Observer Pattern is used to notify different listeners when library events occur.

### Event Sources
- BookAvailabilitySource
- BookReservationSource

### Event Listeners
- LibrarianPanel
- ReservationNotifier
- OverdueAlert
- StatisticsListener
- InventoryListener
- SectionAggregator
- VisitorReportListener

The system supports:
- Subscribing to events
- Unsubscribing from events
- Filtering events by type
- Adding and removing observers at runtime
- Publishing aggregated events through another subject

Events are represented by immutable LibraryEvent records.

## 2. Visitor Pattern

The Visitor Pattern separates operations from the library element classes.

### Composite Structure

The library has the following hierarchy:

Library
- Section
    - Shelf
        - Book
        - Journal
        - EBook
        - Magazine

### Visitors

1. FineCalculator — calculates overdue book fines.
2. InventoryVisitor — counts physical books.
3. AvailabilityFilter — finds available books.
4. CatalogJsonVisitor — exports the library catalog as JSON.
5. CatalogXmlVisitor — exports the library catalog as XML.

The element classes store data and implement accept() for double dispatch.

Visitors own the traversal logic.

## 3. Observer and Visitor Integration

The VisitorReportListener connects both patterns.

When a BOOK_AVAILABLE event occurs:
1. The Observer receives the event.
2. The listener runs FineCalculator on the library structure.
3. A new VISITOR_REPORT_GENERATED event is created.
4. SectionAggregator publishes the generated report.
5. StatisticsListener receives the report.

## 4. Concurrency

The project uses ExecutorService to publish events from multiple producer threads.

The observer registry uses:
- ConcurrentHashMap
- CopyOnWriteArrayList

Shared counters use AtomicInteger.

The stress test checks 4 producer threads with 1000 events each.

Expected event count: 4000.

## 5. Testing

The project uses JUnit 5.

The tests cover:
- Observer subscription and unsubscription
- Event type filtering
- Multiple observers
- Runtime observer changes
- Concurrent event publishing
- Fine calculation
- Inventory counting
- Availability filtering
- JSON and XML export

Total: 15 tests.

## 6. Design Decisions

### Observer Pattern
The observer registry is thread-safe. CopyOnWriteArrayList allows safe iteration while observers subscribe or unsubscribe.

### Visitor Pattern
Traversal is implemented inside visitors rather than element classes. This keeps element classes focused on storing data and accepting visitors.

### Composite Structure
Library, Section, and Shelf are container elements. Book, Journal, EBook, and Magazine are leaf elements.

### Concurrency
ExecutorService manages producer threads. AtomicInteger protects shared counters without locking the entire subject.

## 7. How to Run

Requirements:
- Java 17
- Maven
- IntelliJ IDEA (recommended)

Run the Main class to see the project demonstration.

To execute tests, open the Maven panel and select:

Lifecycle → test

## 8. UML Class Diagram

![UML Class Diagram](library_uml_diagram.png)


## 9. Technologies

- Java 17
- Maven
- JUnit 5
- IntelliJ IDEA
- Git and GitHub
