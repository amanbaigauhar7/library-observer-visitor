
package library.visitor;

public class CatalogXmlVisitor implements Visitor<String> {

    @Override
    public String visitLibrary(Library library) {
        StringBuilder xml = new StringBuilder();

        xml.append("<library name=\"")
                .append(escape(library.getName()))
                .append("\">\n");

        for (Element element : library.getSections()) {
            xml.append(element.accept(this));
        }

        xml.append("</library>\n");

        return xml.toString();
    }

    @Override
    public String visitSection(Section section) {
        StringBuilder xml = new StringBuilder();

        xml.append("  <section name=\"")
                .append(escape(section.getName()))
                .append("\">\n");

        for (Element element : section.getShelves()) {
            xml.append(element.accept(this));
        }

        xml.append("  </section>\n");

        return xml.toString();
    }

    @Override
    public String visitShelf(Shelf shelf) {
        StringBuilder xml = new StringBuilder();

        xml.append("    <shelf name=\"")
                .append(escape(shelf.getName()))
                .append("\">\n");

        for (Element element : shelf.getItems()) {
            xml.append(element.accept(this));
        }

        xml.append("    </shelf>\n");

        return xml.toString();
    }

    @Override
    public String visitBook(Book book) {
        return "      <book title=\"" + escape(book.getTitle())
                + "\" price=\"" + book.getPrice()
                + "\" available=\"" + book.isAvailable()
                + "\" overdueDays=\"" + book.getOverdueDays()
                + "\"/>\n";
    }

    @Override
    public String visitJournal(Journal journal) {
        return "      <journal title=\"" + escape(journal.getTitle())
                + "\" price=\"" + journal.getPrice()
                + "\"/>\n";
    }

    @Override
    public String visitEBook(EBook ebook) {
        return "      <ebook title=\"" + escape(ebook.getTitle())
                + "\" price=\"" + ebook.getPrice()
                + "\" available=\"" + ebook.isAvailable()
                + "\"/>\n";
    }

    @Override
    public String visitMagazine(Magazine magazine) {
        return "      <magazine title=\"" + escape(magazine.getTitle())
                + "\" price=\"" + magazine.getPrice()
                + "\"/>\n";
    }

    private String escape(String value) {
        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}
