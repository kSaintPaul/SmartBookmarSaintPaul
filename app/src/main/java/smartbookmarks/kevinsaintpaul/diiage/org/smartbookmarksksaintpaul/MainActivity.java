package smartbookmarks.kevinsaintpaul.diiage.org.smartbookmarksksaintpaul;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import Helper.DbHelper;
import Model.Book;
import Model.Comment;

public class MainActivity extends AppCompatActivity {

    private TextView txtStats;

    ArrayList<Book> listBooks;
    ArrayList<Comment> listComments;

    int cptBooks;
    int cptComments;
    int avgCommentByBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtStats = findViewById(R.id.txtStats);

        InitTxtStats();


    }

    private void InitTxtStats() {
        DbHelper helper = new DbHelper(this);

        cptBooks = 0;
        cptComments = 0;
        avgCommentByBook = 0;

        SQLiteDatabase db = helper.getWritableDatabase();

        cptBooks = (int) DatabaseUtils.queryNumEntries(db, DbHelper.TableBook);
        cptComments = (int) DatabaseUtils.queryNumEntries(db, DbHelper.TableComment);

        txtStats.setText("Il y a "+ cptBooks +" livre(s), "+ cptComments +" commentaire(s), et une moyenne de "+ avgCommentByBook +" commentaire(s) par livre\"");
    }

    public void MyCommentary_OnClick(View view) {
        Intent intent = new Intent(this, CommentsActivity.class);

        Bundle bundle = new Bundle();
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void AddCommentary_OnClick(View view) {
        Intent intent = new Intent(this, AddCommentActivity.class);

        Bundle bundle = new Bundle();
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
