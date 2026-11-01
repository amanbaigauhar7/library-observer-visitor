package library.visitor;

public class Magazine implements Element {

    private final String title;
    private final double price;

    public Magazine(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitMagazine(this);
    }
}