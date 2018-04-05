package Model;

public class Book {
    public int Id;
    public String Text;
    public String Author;
    public String genre;

    public Book() {
    }

    public Book(int id, String text, String author, String genre) {
        Id = id;
        Text = text;
        Author = author;
        this.genre = genre;
    }
}
