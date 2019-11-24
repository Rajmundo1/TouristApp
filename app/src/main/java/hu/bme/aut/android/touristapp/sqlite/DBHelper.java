package hu.bme.aut.android.touristapp.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import hu.bme.aut.android.touristapp.sqlite.table.MainTable;
import hu.bme.aut.android.touristapp.sqlite.table.UserTable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "touristApp.db";
 
    private static final int DATABASE_VERSION = 1;
 
    public DBHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        MainTable.onCreate(sqLiteDatabase);
        UserTable.onCreate(sqLiteDatabase);
    }
 
    @Override
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int oldVersion, final int newVersion) {
        MainTable.onUpgrade(sqLiteDatabase, oldVersion, newVersion);
        UserTable.onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }
}