package smartbookmarks.kevinsaintpaul.diiage.org.smartbookmarksksaintpaul;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import Helper.DbHelper;
import Model.Comment;

public class CommentsActivity extends AppCompatActivity {

    ListView listComments;

    ArrayList<CommentWithNameBook> comments;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        listComments = findViewById(R.id.lvComments);

        comments = new ArrayList<>();

        //Get Comments in Db
        InitComments();

        CommentAdapter commentAdapter = new CommentAdapter(this, comments);

        listComments.setAdapter(commentAdapter);


    }

    /**
     * Get all comments in DB order by date
     */
    private void InitComments() {
        DbHelper helper = new DbHelper(this);
        db = helper.getWritableDatabase();

        Cursor commentsCursor = db.query(DbHelper.TableComment,
                new String[]{"id","bookId","page","comment", "date"},
                null,
                null,
                null ,
                null,
                "date DESC");

        CommentWithNameBook comment;

        while(commentsCursor.moveToNext()){
            comment = new CommentWithNameBook();

            comment.Comment.Id = commentsCursor.getInt(0);
            comment.Comment.IdBook = commentsCursor.getInt(1);
            comment.Comment.Page = commentsCursor.getInt(2);
            comment.Comment.Comment = commentsCursor.getString(3);
            comment.Comment.Date = new Date(commentsCursor.getLong(4) * 1000);

            Cursor bookCursor = db.query(DbHelper.TableBook,
                    new String[]{"title"},
                    "id = ?",
                    new String[]{String.valueOf(comment.Comment.IdBook)},
                    null ,
                    null,
                    "date DESC");

            while(bookCursor.moveToNext()){
                comment.NameBook = bookCursor.getString(0);
            }

            comments.add(comment);
        }
    }

    private static class CommentAdapter extends BaseAdapter {

        Activity context;
        ArrayList<CommentWithNameBook> comments;
        CommentViewHolder commentViewHolder;

        public CommentAdapter(Activity context, ArrayList<CommentWithNameBook> comments) {
            this.context = context;
            this.comments = comments;
        }

        @Override
        public int getCount() {
            return comments.size();
        }

        @Override
        public Object getItem(int position) {
            return comments.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            View v;

            if(view != null){
                v = view;
                commentViewHolder = (CommentViewHolder) view.getTag();
            }else {
                LayoutInflater layoutInflater = this.context.getLayoutInflater();

                v = layoutInflater.inflate(R.layout.item_comment, viewGroup, false);

                TextView lblComment = v.findViewById(R.id.txtComment);
                TextView lblBookTitle = v.findViewById(R.id.txtBookTitle);
                TextView lblPageNumber = v.findViewById(R.id.txtPageNumber);
                TextView lblDateComment = v.findViewById(R.id.txtDateComment);

                commentViewHolder = new CommentViewHolder(lblComment, lblBookTitle, lblPageNumber, lblDateComment);

                v.setTag(commentViewHolder);
            }

            CommentWithNameBook comment = comments.get(position);

            commentViewHolder.Comment.setText(comment.Comment.Comment);
            commentViewHolder.BookTitle.setText(comment.NameBook);
            commentViewHolder.PageNumber.setText(comment.Comment.Page);
            commentViewHolder.DateComment.setText(comment.Comment.Date.toString());

            return v;
        }
    }

    public static class CommentViewHolder{

        public TextView Comment;
        public TextView BookTitle;
        public TextView PageNumber;
        public TextView DateComment;

        public CommentViewHolder(TextView comment, TextView bookTitle, TextView pageNumber, TextView dateComment) {
            Comment = comment;
            BookTitle = bookTitle;
            PageNumber = pageNumber;
            DateComment = dateComment;
        }
    }

    private class CommentWithNameBook{
        public Comment Comment;
        public String NameBook;

        public CommentWithNameBook() {
            Comment = new Comment();
        }
    }

}
