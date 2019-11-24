package hu.bme.aut.android.touristapp.sqlite.table;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MainTable {
    public static final String TABLE_MAIN = "content";
 
    private static final String DATABASE_CREATE = "create table " + TABLE_MAIN + "("
            + Columns._id.name() + " integer primary key autoincrement, "
            + Columns.username.name() + " text not null, "
            + Columns.country.name() + " text not null, "
            + Columns.place.name() + " text not null"
            + Columns.description.name() + " text not null, "
            + Columns.isfavorite.name() + " integer not null, "
            + Columns.isvisited.name() + " integer not null, "
            + ");";
 
    public static void onCreate(final SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }
 
    public static void onUpgrade(final SQLiteDatabase database, final int oldVersion, final int newVersion) {
        Log.w(MainTable.class.getName(), "Upgrading from version " + oldVersion + " to " + newVersion);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_MAIN);
        onCreate(database);
    }
 
    public enum Columns {
        _id,
        username,
        country,
        place,
        description,
        isfavorite,
        isvisited
    }
}