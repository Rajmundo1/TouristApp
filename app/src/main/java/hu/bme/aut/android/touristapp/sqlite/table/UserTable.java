package hu.bme.aut.android.touristapp.sqlite.table;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UserTable {
    public static final String TABLE_USER = "user";

    private static final String DATABASE_CREATE = "create table " + TABLE_USER + "("
            + Columns._id.name() + " integer primary key autoincrement, "
            + Columns.username.name() + " text not null "
            + ");";
 
    public static void onCreate(final SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }
 
    public static void onUpgrade(final SQLiteDatabase database, final int oldVersion, final int newVersion) {
        Log.w(UserTable.class.getName(), "Upgrading from version " + oldVersion + " to " + newVersion);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(database);
    }
 
    public enum Columns {
        _id,
        username
    }
}