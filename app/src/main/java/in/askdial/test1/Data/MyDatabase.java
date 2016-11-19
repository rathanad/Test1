package in.askdial.test1.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 19-Nov-16.
 */

public class MyDatabase {

    MyDatabase myDatabase;

    private SQLiteDatabase database;
    private DatabaseNameDbHelper helper;

    public MyDatabase(Context context) {
        helper = new DatabaseNameDbHelper(context);
    }

    private SQLiteDatabase openReadableDatabaseInstance() {
        return helper.getReadableDatabase();
    }

    private SQLiteDatabase openWritableDatabaseInstance() {
        return helper.getWritableDatabase();
    }

    private void closeDatabaseConnection() {
        database.close();
        helper.close();
    }



    public long updateTableName(long id, String column1, int column2){

        database = openWritableDatabaseInstance();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ImageData.TableNameEntry.IMAGE_NAME, column1);
        contentValues.put(String.valueOf(ImageData.TableNameEntry.IMAGE_VALUE), column2);


        String selection = ImageData.TableNameEntry._ID +" = ? ";
        String[] selectionArgs = {String.valueOf(id)};

        long value = database.update(ImageData.TableNameEntry.TABLE_NAME, contentValues, selection, selectionArgs);
        closeDatabaseConnection();

        return value;
    }


   /* public long deleteAllTableDetails() {
        database = openWritableDatabaseInstance();

        long l= database.delete(TableNameEntry.TABLE_NAME, null, null);

        closeDatabaseConnection();

        return l;
    }

    public ArrayList<Table> getTableDataInArrayList() {
        database = openReadableDatabaseInstance();

        Cursor c =  database.query(TableNameEntry.TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Table> arrayListTables = new ArrayList<>();

        if(c.moveToFirst()){
            do{
                //String stringDate = Utils.SIMPLE_DATE_FORMAT.format(date.getTime());
                Table table = new Table(c.getInt(c.getColumnIndex(TableNameEntry._ID)),
                        c.getString(c.getColumnIndex(TableNameEntry.COLUMN_1_NAME)),
                        c.getInt(c.getColumnIndex(TableNameEntry.COLUMN_2_NAME)));
                arrayListTables.add(table);
            }while (c.moveToNext());
        }
        closeDatabaseConnection();

        return arrayListTables;
    }
*/


    public long createRowTableNameDetails(String column1, int column2 ) {

        database = openWritableDatabaseInstance();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ImageData.TableNameEntry.IMAGE_NAME, column1);
        contentValues.put(ImageData.TableNameEntry._COUNT, column2);
        long value = database.insert(ImageData.TableNameEntry.TABLE_NAME, null, contentValues);

        closeDatabaseConnection();

        return value;

    }

    private class DatabaseNameDbHelper extends SQLiteOpenHelper {

        //region SQL Statements
        private static final String SQL_CREATE_TABLE_NAME_TABLE = "CREATE TABLE " + ImageData.TableNameEntry.TABLE_NAME + "("
                + ImageData.TableNameEntry._ID + " INTEGER PRIMARY KEY, "
                + ImageData.TableNameEntry.IMAGE_NAME + " TEXT NOT NULL, "
                + ImageData.TableNameEntry._COUNT + " INTEGER);";

        private static final String SQL_DROP_TABLE_NAME_TABLE = "DROP TABLE " + ImageData.TableNameEntry.TABLE_NAME + ";";
        //endregion

        private static final String DATABASE_NAME = "DatabaseName.db";

        private static final int DATABASE_VERSION = 1;

        public DatabaseNameDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLE_NAME_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (newVersion > oldVersion) {
                db.execSQL(SQL_DROP_TABLE_NAME_TABLE);
                onCreate(db);
            }
        }
    }


}

