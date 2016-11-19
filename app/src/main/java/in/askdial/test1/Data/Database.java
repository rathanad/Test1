package in.askdial.test1.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 19-Nov-16.
 */

public class Database {
    String DATABASE_NAME = "database.db";
    int DATABASE_VERSION = 1;
    MyHelper mh;
    SQLiteDatabase sdb;

    public Database(Context context) {
        mh = new MyHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() {
        sdb = mh.getWritableDatabase();
    }

    public void close() {
        sdb.close();
    }

    private class MyHelper extends SQLiteOpenHelper {

        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("Create table IMAGETABLE(_id integer primary key, image_name text, image_text text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public void insertSearchPath(String name, String value ){

        ContentValues cv = new ContentValues();

        cv.put("image_name", name);
        cv.put("image_text", value);
        sdb.insert("IMAGETABLE", null, cv);

    }

    public Cursor search(String image) {
        Cursor c = null;
        c = sdb.rawQuery("SELECT * FROM IMAGETABLE WHERE image_name = "+"'"+image+"'", null);

        return c;
    }
    public Cursor search1() {
        Cursor c = null;
        c = sdb.rawQuery("SELECT * FROM IMAGETABLE", null);

        return c;
    }

}
