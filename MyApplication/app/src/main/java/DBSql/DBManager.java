package DBSql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/**
 * Created by lenovo on 2016/12/20.
 */
public class DBManager {
    private Context mContext = null;

    private SQLiteDatabase mSQLiteDatabase = null;//用于操作数据库的对象
    private DBHelper dh = null;//用于创建数据库的对象
    Cursor namecursor = null;
    private String dbName = "notepad.db";
    private int dbVersion = 1;

    public DBManager(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 打开数据库
     */
    public void open() {

        try {
            dh = new DBHelper(mContext, dbName, null, dbVersion);
            if (dh == null) {

                return;
            }
            mSQLiteDatabase = dh.getWritableDatabase();
            //dh.onOpen(mSQLiteDatabase);
            Log.i("log", "DB is opened");
        } catch (SQLiteException se) {
            se.printStackTrace();
            Log.i("log", "open DB faile");
        }
    }

    /**
     * 关闭数据库
     */
    public void close(){

        mSQLiteDatabase.close();
        dh.close();
        Log.i("log", "DB is closed");
    }


    //获取列表
    public Cursor selectAll() {
        Cursor cursor = null;
        try {
            String sql = "select * from data";
            cursor = mSQLiteDatabase.rawQuery(sql, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            cursor = null;
        }
        return cursor;
    }

    public Cursor selectById(int id) {

        //String result[] = {};
        Cursor cursor = null;
        try {
            String sql = "select * from data where _id='" + id + "'";
            cursor = mSQLiteDatabase.rawQuery(sql, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            cursor = null;
        }

        return cursor;
    }


    //插入数据
    public long insert(String title, String content) {
        Log.i("log", "readyto insert");
        long datetime = System.currentTimeMillis();
        Log.i("log", "time------>" + datetime);
        long l = -1;
        try {
            ContentValues cv = new ContentValues();
            cv.put("title", title);
            cv.put("content", content);
            cv.put("time", datetime);
            Log.i("log", "data----->" + title + content + datetime);
            l = mSQLiteDatabase.insert("data", null, cv);
            Log.i("log", cv.toString());
            Log.i("log", datetime + "" + l);
        } catch (Exception ex) {
            ex.printStackTrace();
            l = -1;
        }
        return l;

    }

    //删除数据
    public int delete(long id) {
        int affect = 0;
        try {
            Log.i("log", "try to delete the data in databases");
            affect = mSQLiteDatabase.delete("data", "_id=?", new String[]{id + ""});
            Log.i("log", "delete success");
        } catch (Exception ex) {
            ex.printStackTrace();
            affect = -1;
            Log.i("log", "delete faile");
        }

        return affect;
    }

    //修改数据
    public int update(int id, String title, String content) {
        int affect = 0;
        try {
            ContentValues cv = new ContentValues();

            cv.put("title", title);
            cv.put("content", content);
            affect = mSQLiteDatabase.update("data", cv, "_id=?", new String[]{id + ""});
        } catch (Exception ex) {
            ex.printStackTrace();
            affect = -1;
        }
        return affect;
    }
}
