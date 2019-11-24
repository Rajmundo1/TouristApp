package hu.bme.aut.android.touristapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.android.touristapp.model.Content;
import hu.bme.aut.android.touristapp.model.User;
import hu.bme.aut.android.touristapp.sqlite.table.MainTable;
import hu.bme.aut.android.touristapp.sqlite.table.UserTable;

public class PersistentDataHelper {
    private SQLiteDatabase database;
 
    private DBHelper dbHelper;

 
    private String[] mainColumns = {
            MainTable.Columns._id.name(),
            MainTable.Columns.username.name(),
            MainTable.Columns.country.name(),
            MainTable.Columns.place.name(),
            MainTable.Columns.description.name(),
            MainTable.Columns.isfavorite.name(),
            MainTable.Columns.isvisited.name()

    };

    private String[] userColumns = {
            UserTable.Columns._id.name(),
            UserTable.Columns.username.name()
    };
 
    public PersistentDataHelper(final Context context) {
        dbHelper = new DBHelper(context);
    }
 
    public void open() throws SQLiteException {
        database = dbHelper.getWritableDatabase();
    }
 
    public void close() {
        dbHelper.close();
    }

    public void persistContent(final List<Content> contents) {
        clearContent();
        for (final Content item : contents) {
            final ContentValues values = new ContentValues();
            values.put(MainTable.Columns.username.name(), item.getUsername());
            values.put(MainTable.Columns.country.name(), item.getCountry());
            values.put(MainTable.Columns.place.name(), item.getPlace());
            values.put(MainTable.Columns.description.name(), item.getDescription());
            values.put(MainTable.Columns.isfavorite.name(), item.isIsfavorite());
            values.put(MainTable.Columns.isvisited.name(), item.isIsvisited());
            database.insert(MainTable.TABLE_MAIN, null, values);
        }
    }

    public List<Content> restoreContent() {
        final List<Content> contents = new ArrayList<>();
        final Cursor cursor = database.query(MainTable.TABLE_MAIN, mainColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final Content content = cursorToContent(cursor);
            contents.add(content);
            cursor.moveToNext();
        }
        cursor.close();
        return contents;
    }
 
    public void clearContent() {
        database.delete(MainTable.TABLE_MAIN, null, null);
    }

    private Content cursorToContent(final Cursor cursor) {
        final Content content= new Content();
        content.setUsername(cursor.getString(MainTable.Columns.username.ordinal()));
        content.setCountry(cursor.getString(MainTable.Columns.country.ordinal()));
        content.setPlace(cursor.getString(MainTable.Columns.place.ordinal()));
        content.setDescription(cursor.getString(MainTable.Columns.description.ordinal()));
        content.setIsfavorite(cursor.getInt(MainTable.Columns.isfavorite.ordinal()));
        content.setIsvisited(cursor.getInt(MainTable.Columns.isvisited.ordinal()));
        return content;
    }





    public void persistUser(final List<User> users) {
        clearUser();
        for (final User item : users) {
            final ContentValues values = new ContentValues();
            values.put(UserTable.Columns.username.name(), item.getUsername());
            database.insert(UserTable.TABLE_USER, null, values);
        }
    }

    public List<User> restoreUser() {
        final List<User> users = new ArrayList<>();
        final Cursor cursor = database.query(UserTable.TABLE_USER, userColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final User user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        return users;
    }

    public void clearUser() {
        database.delete(UserTable.TABLE_USER, null, null);
    }

    private User cursorToUser(final Cursor cursor) {
        final User user= new User();
        user.setUsername(cursor.getString(UserTable.Columns.username.ordinal()));
        return user;
    }

}