package Model;

public class Book {
    public int Id;
    public String Title;
    public String Author;
    public String genre;
    public int AuthorId;

    public Book() {
    }

    public Book(int id, String text, String author, String genre) {
        Id = id;
        Title = text;
        Author = author;
        this.genre = genre;
    }

    public Book(int id, String text, String author, String genre, int authorId) {
        Id = id;
        Title = text;
        Author = author;
        this.genre = genre;
        AuthorId = authorId;
    }
}
