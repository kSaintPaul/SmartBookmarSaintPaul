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

    ArrayList<Comment> comments;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        listComments = findViewById(R.id.lvComments);

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

        Cursor cursor = db.query(DbHelper.TableComment,
                new String[]{"id","bookId","page","comment", "date"},
                null,
                null,
                null ,
                null,
                "date DESC");

        Comment comment;

        while(cursor.moveToNext()){
            comment = new Comment();

            comment.Id = cursor.getInt(0);
            comment.IdBook = cursor.getInt(1);
            comment.Page = cursor.getInt(2);
            comment.Comment = cursor.getString(3);
            comment.Date = new Date(cursor.getLong(4) * 1000);

            comments.add(comment);
        }
    }

    private static class CommentAdapter extends BaseAdapter {

        Activity context;
        ArrayList<Comment> comments;
        CommentViewHolder commentViewHolder;

        public CommentAdapter(Activity context, ArrayList<Comment> comments) {
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

            Comment comment = comments.get(position);

            commentViewHolder.Comment.setText(comment.Comment);
            commentViewHolder.BookTitle.setText(comment.IdBook);
            commentViewHolder.PageNumber.setText(comment.Page);
            commentViewHolder.DateComment.setText(comment.Date.toString());

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


}
