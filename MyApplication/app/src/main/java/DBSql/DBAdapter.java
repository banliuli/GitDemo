package DBSql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2016/12/24.
 */
public class DBAdapter {

    static final String KEY_ROWID = "_id";
    static final String KEY_PWD = "password";
    static final String KEY_ENPWD = "repassword";
    static final String TAG = "DBAdapter";

    static final String DATABASE_NAME = "MyDB";
    static final String DATABASE_TABLE = "password";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_CREATE =
            "create table password( _id Long primary key autoincrement, " +
                    "password varchar(20) not null autoincrement, varchar(20) not null);";
    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context cxt)
    {
        this.context = cxt;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {

        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            try
            {
                db.execSQL(DATABASE_CREATE);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub

            onCreate(db);
        }
    }

    //open the database
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }
    public Cursor selectById(int id) {

        //String result[] = {};
        Cursor cursor = null;
        try {
            String sql = "select * from password where _id='" + id + "'";
            cursor = db.rawQuery(sql, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            cursor = null;
        }

        return cursor;
    }
    //close the database
    public void close()
    {
        DBHelper.close();
    }

    //insert a contact into the database
    public long insert(String password, String repassword)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_PWD,password);
        initialValues.put(KEY_ENPWD, repassword);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //retreves all the contacts
    public Cursor selectAll()
    {
        return db.query(DATABASE_TABLE, new String[]{ KEY_ROWID,KEY_PWD,KEY_ENPWD}, null, null, null, null, null);
    }
    //retreves a particular contact
    public Cursor getId(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[]{ KEY_ROWID,
                        KEY_PWD, KEY_ENPWD}, KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null)
            mCursor.moveToFirst();
        return mCursor;
    }
    //updates a contact
    public boolean updateContact(long rowId, String password, String repassword)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_PWD, password);
        args.put(KEY_ENPWD, repassword);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" +rowId, null) > 0;
    }
}
