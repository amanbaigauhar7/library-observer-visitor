package library.visitor;

import java.util.List;

public class Section implements Element {

    private final String name;
    private final List<Element> shelves;

    public Section(String name, List<Element> shelves) {
        this.name = name;
        this.shelves = List.copyOf(shelves);
    }

    public String getName() {
        return name;
    }

    public List<Element> getShelves() {
        return shelves;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitSection(this);
    }
}