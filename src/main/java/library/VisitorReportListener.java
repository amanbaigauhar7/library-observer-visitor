
package library;

import library.visitor.Element;
import library.visitor.FineCalculator;

import java.time.Instant;

public class VisitorReportListener implements Observer<LibraryEvent> {

    private final Element library;
    private final SectionAggregator aggregator;

    public VisitorReportListener(
            Element library,
            SectionAggregator aggregator
    ) {
        this.library = library;
        this.aggregator = aggregator;
    }

    @Override
    public void onEvent(LibraryEvent event) {

        FineCalculator calculator = new FineCalculator();

        double totalFine = library.accept(calculator);

        LibraryEvent report = new LibraryEvent(
                EventType.VISITOR_REPORT_GENERATED,
                event.bookId(),
                event.bookTitle(),
                "Visitor calculated total fines: " + totalFine + " KZT",
                Instant.now()
        );

        aggregator.publish(report);
    }
}
