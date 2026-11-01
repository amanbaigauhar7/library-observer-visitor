package library.visitor;

import java.util.List;

public class Library implements Element {

    private final String name;
    private final List<Element> sections;

    public Library(String name, List<Element> sections) {
        this.name = name;
        this.sections = List.copyOf(sections);
    }

    public String getName() {
        return name;
    }

    public List<Element> getSections() {
        return sections;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitLibrary(this);
    }
}