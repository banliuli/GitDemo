package DBSql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2016/12/20.
 */
public class DBHelper extends SQLiteOpenHelper{

    private String tablename = "dataSql";
    private String icontable = "icons";
    private Context mcontext=null;
    private String sql = " create table if not exists "+tablename+
            "(_id integer primary key autoincrement, " +
            "title varchar," +
            "content text," +
            "time varchar)";
    String sql2 = "create table if not exists "+icontable+
            "(_id integer primary key autoincrement, " +
            "filename varchar," +
            "path varchar)";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
