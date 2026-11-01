
package library.visitor;

public class CatalogJsonVisitor implements Visitor<String> {

    @Override
    public String visitLibrary(Library library) {
        StringBuilder json = new StringBuilder();

        json.append("{");
        json.append("\"name\":\"").append(escape(library.getName())).append("\",");
        json.append("\"sections\":[");

        for (int i = 0; i < library.getSections().size(); i++) {
            if (i > 0) {
                json.append(",");
            }
            json.append(library.getSections().get(i).accept(this));
        }

        json.append("]}");
        return json.toString();
    }

    @Override
    public String visitSection(Section section) {
        StringBuilder json = new StringBuilder();

        json.append("{");
        json.append("\"name\":\"").append(escape(section.getName())).append("\",");
        json.append("\"shelves\":[");

        for (int i = 0; i < section.getShelves().size(); i++) {
            if (i > 0) {
                json.append(",");
            }
            json.append(section.getShelves().get(i).accept(this));
        }

        json.append("]}");
        return json.toString();
    }

    @Override
    public String visitShelf(Shelf shelf) {
        StringBuilder json = new StringBuilder();

        json.append("{");
        json.append("\"name\":\"").append(escape(shelf.getName())).append("\",");
        json.append("\"items\":[");

        for (int i = 0; i < shelf.getItems().size(); i++) {
            if (i > 0) {
                json.append(",");
            }
            json.append(shelf.getItems().get(i).accept(this));
        }

        json.append("]}");
        return json.toString();
    }

    @Override
    public String visitBook(Book book) {
        return "{\"type\":\"Book\","
                + "\"title\":\"" + escape(book.getTitle()) + "\","
                + "\"price\":" + book.getPrice() + ","
                + "\"available\":" + book.isAvailable() + ","
                + "\"overdueDays\":" + book.getOverdueDays() + "}";
    }

    @Override
    public String visitJournal(Journal journal) {
        return "{\"type\":\"Journal\",\"title\":\""
                + journal.getTitle()
                + "\",\"price\":"
                + journal.getPrice()
                + "}";
    }

    @Override
    public String visitEBook(EBook ebook) {
        return "{\"type\":\"EBook\",\"title\":\""
                + ebook.getTitle()
                + "\",\"price\":"
                + ebook.getPrice()
                + ",\"available\":"
                + ebook.isAvailable()
                + "}";
    }

    @Override
    public String visitMagazine(Magazine magazine) {
        return "{\"type\":\"Magazine\",\"title\":\""
                + magazine.getTitle()
                + "\",\"price\":"
                + magazine.getPrice()
                + "}";
    }

    @Override
    public String visitNewspaper(Newspaper newspaper) {
        return "{\"type\":\"Newspaper\",\"title\":\""
                + escape(newspaper.getTitle())
                + "\",\"price\":"
                + newspaper.getPrice()
                + "}";
    }

    private String escape(String value) {
        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
