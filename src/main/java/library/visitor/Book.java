
package library.visitor;

public class Book implements Element {

    private final String title;
    private final double price;
    private final boolean available;
    private final int overdueDays;

    public Book(String title, double price, boolean available, int overdueDays) {
        this.title = title;
        this.price = price;
        this.available = available;
        this.overdueDays = overdueDays;
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

    public int getOverdueDays() {
        return overdueDays;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitBook(this);
    }
}
