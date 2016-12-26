package data;

/**
 * Created by Administrator on 2016/12/12.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDataManager {
    //一些宏定义和声明
    private static final String TAG = "UserDataManager";
    private static final String DB_NAME = "user_data";
    private static final String TABLE_NAME = "users";
    public static final String ID = "_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PWD = "user_pwd";
    //    public static final String SILENT = "silent";
//    public static final String VIBRATE = "vibrate";
    private static final int DB_VERSION = 2;
    private Context mContext = null;

    //创建用户book表
    private static final String DB_CREATE = "CREATE TABLE " + TABLE_NAME + " ("
            + USER_NAME + " varchar primary key,"
            + USER_PWD + " varchar" + ");";

    private SQLiteDatabase mSQLiteDatabase = null;
    private DataBaseManagementHelper mDatabaseHelper = null;
    private android.database.sqlite.SQLiteDatabase db;




    //DataBaseManagementHelper继承自SQLiteOpenHelper
    private static class DataBaseManagementHelper extends SQLiteOpenHelper {

        DataBaseManagementHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i(TAG, "db.getVersion()=" + db.getVersion());
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
            db.execSQL(DB_CREATE);
            Log.i(TAG, "db.execSQL(DB_CREATE)");
            Log.e(TAG, DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i(TAG, "DataBaseManagementHelper onUpgrade");
            onCreate(db);
        }
    }

    public UserDataManager(Context context) {
        mContext = context;
        Log.i(TAG, "UserDataManager construction!");
    }

    //打开数据库
    public void openDataBase() throws SQLException {
        mDatabaseHelper = new DataBaseManagementHelper(mContext);
        mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
    }

    //关闭数据库
    public void closeDataBase() throws SQLException {
        mDatabaseHelper.close();
    }

    //添加新用户，即注册
    public long insertUserData(UserData userData) {
        String userName = userData.getUsername();
        String passWord = userData.getPassword();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, userName);
        values.put(USER_PWD, passWord);
        return mSQLiteDatabase.insert(TABLE_NAME, USER_NAME, values);
    }

    //根据用户名找用户，可以判断注册时用户名是否已经存在
    public int findUserByName(String userName) {
        Log.i(TAG, "findUserByName , userName=" + userName);
        int result = 0;
        Cursor mCursor = mSQLiteDatabase.query(TABLE_NAME, null, USER_NAME + "=" + userName, null, null, null, null);
        if (mCursor != null) {
            result = mCursor.getCount();
            mCursor.close();
            Log.i(TAG, "findUserByName , result=" + result);
        }
        return result;
    }
//    //根据用户名和密码找用户，用于登录
    public int findUserByNameAndPwd(String userName, String password) {
        Log.i(TAG, "findUserByNameAndPwd, userName=" + userName +"password=" + password);
        int result = 0;
        Cursor mCursor = mSQLiteDatabase.query(TABLE_NAME, null, USER_NAME + "=" + userName + " and " + USER_PWD + "=" + password,
                null, null, null, null);
        if (mCursor != null) {
            result = mCursor.getCount();
            mCursor.close();
            Log.i(TAG, "findUserByNameAndPwd , result=" + result);
        }
        return result;
    }
<<<<<<< HEAD
    //更新用户信息，如修改密码
=======
<<<<<<< HEAD
    //更新用户信息，如修改密码
    public boolean updateUserData(UserData userData) {
        //int id = userData.getUserId();
        String userName = userData.getUsername();
        String userPwd = userData.getPassword();
=======
    //修改用户信息
>>>>>>> d880a9a4ee84d9570aadf4edb7986a228c9d2e35
    public boolean updateUserData(UserData userData) {
        //int id = userData.getUserId();
        String userName = userData.getUsername();
        String userPwd = userData.getPassword();
<<<<<<< HEAD
=======

>>>>>>> eca444ff2d41dc03deaba6405ce4fffab06a2c62
>>>>>>> d880a9a4ee84d9570aadf4edb7986a228c9d2e35
        ContentValues values = new ContentValues();
        values.put(USER_NAME, userName);
        values.put(USER_PWD, userPwd);
        return mSQLiteDatabase.update(TABLE_NAME, values,null, null) > 0;
        //return mSQLiteDatabase.update(TABLE_NAME, values, ID + "=" + id, null) > 0;
    }
    //读取用户信息
    public Cursor fetchAllUserDatas() {
<<<<<<< HEAD
        return mSQLiteDatabase.query(TABLE_NAME, null, null, null, null, null,
                null);
    }
    public Cursor fetchUserName(String userName ) {

        Log.i(TAG, "findUserByNameAndPwd, userName=" + userName );

        Cursor mCursor = mSQLiteDatabase.query(TABLE_NAME, null, USER_NAME + "=" + userName  ,
                null, null, null, null);

        return mCursor;
    }
=======
<<<<<<< HEAD
        return mSQLiteDatabase.query(TABLE_NAME, null, null, null, null, null,
                null);
    }
    public Cursor fetchUserName(String userName ) {

        Log.i(TAG, "findUserByNameAndPwd, userName=" + userName );

        Cursor mCursor = mSQLiteDatabase.query(TABLE_NAME, null, USER_NAME + "=" + userName  ,
                null, null, null, null);

        return mCursor;
    }
=======

        return mSQLiteDatabase.query(TABLE_NAME, null, null, null, null, null,
                null);
    }
>>>>>>> eca444ff2d41dc03deaba6405ce4fffab06a2c62
>>>>>>> d880a9a4ee84d9570aadf4edb7986a228c9d2e35
   //根据ID修改用户信息
    public boolean updateUserDataById(String columnName, int id,
                                      String columnValue) {
        ContentValues values = new ContentValues();
        values.put(columnName, columnValue);
        return mSQLiteDatabase.update(TABLE_NAME, values, ID + "=" + id, null) > 0;
    }
    //根据用户名注销
    public boolean deleteUserDatabyname(String name) {
        return mSQLiteDatabase.delete(TABLE_NAME, USER_NAME + "=" + name, null) > 0;
    }

}
