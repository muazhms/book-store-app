package bookStore;

public class Book {

    private int book_id;
    private String book_name;
    private String description;
    private double price;
    private int edition;
    private int author_id;

//    public Book(int book_id,
//                String book_name,
//                String description,
//                double price,
//                int edition,
//                int author_id
//    ) {
//        this.book_id = book_id;
//        this.book_name = book_name;
//        this.description = description;
//        this.price = price;
//        this.edition = edition;
//        this.author_id = author_id;
//    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }
}
