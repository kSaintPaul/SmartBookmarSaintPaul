package smartbookmarks.kevinsaintpaul.diiage.org.smartbookmarksksaintpaul;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;

import Helper.DbHelper;
import Model.Book;

public class AddCommentActivity extends AppCompatActivity {

    private EditText Comment;
    private EditText NumPage;
    private Spinner Books;

    private ArrayList<Book> listBooks;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        Comment = findViewById(R.id.editText);
        NumPage = findViewById(R.id.editText2);
        Books = findViewById(R.id.spnBooks);

        listBooks = new ArrayList<>();

        InitBooks();



    }

    private void InitBooks() {
        DbHelper helper = new DbHelper(this);
        db = helper.getWritableDatabase();

        Cursor commentsCursor = db.query(DbHelper.TableBook,
                new String[]{"id","title"},
                null,
                null,
                null ,
                null,
                "title");

        Book book;

        while(commentsCursor.moveToNext()){
            book = new Book();

            book.Id = commentsCursor.getInt(0);
            book.Title = commentsCursor.getString(1);

            listBooks.add(book);
        }
    }

    public void AddComment_OnClick(View view) {
    }
}
