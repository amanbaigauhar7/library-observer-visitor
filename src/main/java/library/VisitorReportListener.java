package library;

import library.visitor.Element;
import library.visitor.FineCalculator;

import java.time.Instant;

public class VisitorReportListener implements Observer<LibraryEvent> {

    private final Element library;
    private final SectionAggregator aggregator;
    private final FineCalculator calculator;

    public VisitorReportListener(
            Element library,
            SectionAggregator aggregator,
            FineCalculator calculator
    ) {
        this.library = library;
        this.aggregator = aggregator;
        this.calculator = calculator;
    }

    @Override
    public void onEvent(LibraryEvent event) {

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