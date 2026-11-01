package library.visitor;

public class EBook implements Element {

    private final String title;
    private final double price;
    private final boolean available;

    public EBook(String title, double price, boolean available) {
        this.title = title;
        this.price = price;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitEBook(this);
    }
}