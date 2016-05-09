package com.example.shakabreaux.corotanfinalproject;

/**
 * Created by ShakaBreaux on 6/1/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.shakabreaux.corotanfinalproject.TableData.TableInfo;


public class DatabaseOperations extends SQLiteOpenHelper {
    public static final int database_version = 1;
    public String CREATE_QUERY = "CREATE TABLE "+TableInfo.TABLE_NAME+"( "
            +TableInfo.ROOM_NUMBER+" INTEGER,"
            +TableInfo.FIRST+" TEXT,"
            +TableInfo.LAST+" TEXT,"
            +TableInfo.EMAIL+" TEXT,"
            +TableInfo.PHONE_NUMBER+" TEXT,"
            +TableInfo.WIDTH+" INTEGER,"
            +TableInfo.HEIGHT+" INTEGER );";

    public String DELETE_QUERY = "DROP TABLE IF EXISTS "+TableInfo.TABLE_NAME;
    public DatabaseOperations(Context context){
        super(context, TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database Operations", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL(DELETE_QUERY);
        sdb.execSQL(CREATE_QUERY);
        Log.d("Database Operations", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sdb, int i, int i1) {
        sdb.execSQL(DELETE_QUERY);
    }

    public void putInformation(DatabaseOperations dop, int room_num, String first_name, String last_name, String email, String phone_num, int width, int height){
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableInfo.ROOM_NUMBER, room_num);
        cv.put(TableInfo.FIRST, first_name);
        cv.put(TableInfo.LAST, last_name);
        cv.put(TableInfo.EMAIL, email);
        cv.put(TableInfo.PHONE_NUMBER, phone_num);
        cv.put(TableInfo.WIDTH, width);
        cv.put(TableInfo.HEIGHT, height);
        long k = SQ.insert(TableInfo.TABLE_NAME, null, cv);
        Log.d("Database Operations", "One row inserted");
    }

    public Cursor getInformation(DatabaseOperations dop){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns = {TableInfo.ROOM_NUMBER, TableInfo.EMAIL, TableInfo.PHONE_NUMBER, TableInfo.WIDTH, TableInfo.HEIGHT};
        Cursor CR = SQ.query(TableInfo.TABLE_NAME, columns, null, null, null, null, null);
        return CR;
    }

    public Cursor getFacultyInfo(DatabaseOperations dop, String lastname){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] proj = {TableInfo.ROOM_NUMBER,TableInfo.FIRST, TableInfo.LAST, TableInfo.EMAIL, TableInfo.PHONE_NUMBER, TableInfo.WIDTH, TableInfo.HEIGHT};
        //String select = TableInfo.NAME+" = ? ",;
        String[] selectState = {lastname};
        Cursor CR = SQ.query(TableInfo.TABLE_NAME, proj, "last = ?", selectState, null, null, null);
        return CR;
    }
}
