package library.visitor;

import java.util.List;

public class Shelf implements Element {

    private final String name;
    private final List<Element> items;

    public Shelf(String name, List<Element> items) {
        this.name = name;
        this.items = List.copyOf(items);
    }

    public String getName() {
        return name;
    }

    public List<Element> getItems() {
        return items;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitShelf(this);
    }
}