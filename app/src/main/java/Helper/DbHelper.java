package Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static String NameDb = "smartbook.db";

    public static String TableComment = "Comment";
    public static String TableBook = "Book";

    public DbHelper(Context context) {
        super(context, NameDb, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TableBook +"(" +
                "id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , " +
                "title TEXT NOT NULL , " +
                "author TEXT NOT NULL , " +
                "genre TEXT NOT NULL )");

        db.execSQL("CREATE TABLE "+ TableComment +" (" +
                "id  PRIMARY KEY AUTOINCREMENT NOT NULL , " +
                "bookId INTEGER  NOT NULL, " +
                "page INTEGER NOT NULL , " +
                "comment TEXT NOT NULL , " +
                "date DATETIME NOT NULL )");

        db.execSQL("INSERT INTO Books VALUES(1,'Les fleurs du mal','Charles Baudelaire','Poèmes'); ");
        db.execSQL("INSERT INTO Books VALUES(2,'Germinal','Emile Zola','Roman'); ");
        db.execSQL("INSERT INTO Books VALUES(3,'Les misérables','Victor Hugo','Roman'); ");
        db.execSQL("INSERT INTO Books VALUES(4,'1984','George Orwell','Science-Fiction'); ");
        db.execSQL("INSERT INTO Books VALUES(5,'Le Meilleur des mondes','Aldous Huxley','Science-Fiction'); ");
        db.execSQL("INSERT INTO Books VALUES(6,'Vingt mille lieues sous les mers','Jules Verne','Aventure'); ");
        db.execSQL("INSERT INTO Books VALUES(7,'Les Trois Mousquetaires','Alexandre Dumas','Aventure'); ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
