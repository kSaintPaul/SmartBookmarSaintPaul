package Model;

import java.util.Date;

public class Comment {
    public int Id;
    public int IdBook;
    public int Page;
    public String Comment;
    public Date Date;

    public Comment() {
    }

    public Comment(int id, int idBool, int page, String comment, java.util.Date date) {
        Id = id;
        IdBook = idBool;
        Page = page;
        Comment = comment;
        Date = date;
    }
}
